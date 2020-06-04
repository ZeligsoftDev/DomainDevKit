/*******************************************************************************
 * Copyright (c) 2020 Northrop Grumman Systems Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.zeligsoft.ddk.tools.ui.internal.actions;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

import com.zeligsoft.base.util.ZeligsoftURIConverter;
import com.zeligsoft.base.zdl.ZDLNames;
import com.zeligsoft.base.zdl.util.ZDLUtil;
import com.zeligsoft.ddk.tools.Activator;

/**
 * A convenient action that quickly and efficiently exports a ZDL domain model
 * from RSA (emx) to Eclipse (uml) format. We take advantage of our knowledge of
 * how constrained these models are in the use of cross-references to other
 * domain models (always in UML format) and profiles (also in UML format) to do
 * the export much more efficiently than does RSM.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class QuickExportToUMLActionDelegate extends ActionDelegate implements
		IObjectActionDelegate {

	private static final IContentType EMX_TYPE = Platform
			.getContentTypeManager().getContentType(
					"com.ibm.xtools.uml.msl.umlModelContentType");

	// add to these as the need arises
	private static final Set<String> UML_ANNOTATIONS = new java.util.HashSet<String>(
			Arrays.asList("http://www.eclipse.org/uml2/2.0.0/UML"));

	private IFile selectedFile;

	private Shell shell;

	private IWorkbenchPart part;

	private static int WORD_WRAP_SIZE = 100;

	/**
	 * Initializes me.
	 */
	public QuickExportToUMLActionDelegate() {
		super();
	}

	@Override
	public void run(IAction action) {
		if (selectedFile == null) {
			return;
		}

		try {
			part.getSite().getPage().getWorkbenchWindow().getWorkbench()
					.getProgressService()
					.busyCursorWhile(new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							doRun(monitor);
						}
					});
		} catch (Exception e) {
			MessageDialog.openError(shell, "Quick Export to UML",
					"Export failed: " + e.getLocalizedMessage());
		}
	}

	private void doRun(IProgressMonitor monitor) {
		monitor.beginTask("Exporting ... ", 7);

		ResourceSet rset = new ResourceSetImpl();
		ZeligsoftURIConverter.install(rset);

		// load a UMLResource from the file contents
		Resource uml = rset.createResource(URI.createPlatformResourceURI(
				selectedFile.getFullPath().removeFileExtension()
						.addFileExtension("uml").toString(), true));

		monitor.subTask("Loading the model");
		loadUMLResource(uml);
		monitor.worked(1);

		// ensure the cache adapter
		Package root = (Package) EcoreUtil.getObjectByType(uml.getContents(),
				UMLPackage.Literals.PACKAGE);
		root.getAppliedStereotypes(); // uses the cache adapter
		CacheAdapter cache = CacheAdapter.getCacheAdapter(root);

		Collection<EObject> nukeEm = new java.util.ArrayList<EObject>();
		Map<EObject, Resource> crossResourceContainments = new java.util.HashMap<EObject, Resource>();

		monitor.subTask("Finding elements to delete");
		findElementsToDelete(root, nukeEm, crossResourceContainments);
		monitor.worked(1);

		monitor.subTask("Resolving proxies");
		// resolve entire resource set because references in stereotype
		// applications in fragment resources would not be covered by resolveAll
		// on the root resource
		EcoreUtil.resolveAll(rset);
		monitor.worked(1);

		monitor.subTask("Deleting elements");
		deleteElements(cache, nukeEm);
		monitor.worked(1);

		monitor.subTask("Absorbing model fragments");
		absorbFragments(uml, crossResourceContainments);
		monitor.worked(1);

		monitor.subTask("Cleaning up stereotype applications");
		cleanUpStereotypes(uml);
		monitor.worked(1);

		monitor.subTask("Performing localization");
		doLocalization(root, selectedFile.getFullPath().removeFileExtension()
				.addFileExtension("properties"));
		monitor.worked(1);

		// save
		monitor.subTask("Saving UML model");
		try {
			uml.save(Collections.singletonMap(
					Resource.OPTION_SAVE_ONLY_IF_CHANGED,
					Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER));
			selectedFile.getParent().refreshLocal(1, null);
		} catch (final IOException e) {
			shell.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					ErrorDialog.openError(shell, "Export failed",
							"Failed to save UML model", new Status(
									IStatus.ERROR, Activator.PLUGIN_ID,
									"Failed to save UML model", e));
				}
			});
		} catch (final CoreException e) {
			shell.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					ErrorDialog.openError(shell, "Export failed",
							"Failed to save UML model", e.getStatus());
				}
			});
		}

		for (Resource next : rset.getResources()) {
			next.unload();
			next.eAdapters().clear();
		}
		monitor.worked(1);

		rset.getResources().clear();
		rset.eAdapters().clear();

		monitor.done();
	}

	/**
	 * 
	 * @param rootPackage
	 * @param path
	 */
	private void doLocalization(Package rootPackage, IPath path) {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(path);
		if (!file.exists()) {
			try {
				file.create(null, true, null);
				file.setContents(new ByteArrayInputStream("".getBytes()),
						IResource.FORCE, null);
			} catch (CoreException e) {
				ErrorDialog.openError(getShell(), "Open File Failed",
						"Failed to open localization file", e.getStatus());
			}
		}

		Properties bundle = new Properties();
		try {
			bundle.load(file.getContents());

		} catch (Exception e) {
			ErrorDialog.openError(getShell(),
					"Loading localization bundle failed",
					"Failed to load localization bundle", new Status(
							IStatus.ERROR, Activator.PLUGIN_ID,
							"Failed to load localization bundle", e));
		}

		boolean bundleChanged = false;
		TreeIterator<EObject> iter = rootPackage.eAllContents();
		String lineSeparator = System.getProperty("line.separator");
		while (iter.hasNext()) {
			EObject eObject = iter.next();

			if (ZDLUtil.isZDLConcept(eObject, ZDLNames.DOMAIN_MODEL_LIBRARY)) {
				iter.prune();
				continue;
			}

			boolean isLocalizable = false;
			isLocalizable = ZDLUtil.isZDLConcept(eObject,
					ZDLNames.DOMAIN_CLASSIFIER)
					|| ZDLUtil.isZDLConcept(eObject, ZDLNames.DOMAIN_BLOCK)
					|| ZDLUtil.isZDLConcept(eObject, ZDLNames.DOMAIN_MODEL)
					|| ZDLUtil.isZDLConcept(eObject, ZDLNames.DOMAIN_PACKAGE)
					|| ZDLUtil
							.isZDLConcept(eObject, ZDLNames.DOMAIN_CONSTRAINT)
					|| ZDLUtil.isZDLConcept(eObject, ZDLNames.DOMAIN_ATTRIBUTE)
					|| ZDLUtil.isZDLConcept(eObject, ZDLNames.DOMAIN_REFERENCE)
					|| ZDLUtil.isZDLConcept(eObject,
							ZDLNames.DOMAIN_ENUM_LITERAL);

			// not all of the properties are DOMAIN_ATTRIBUTE so we have to
			// check for Property as well
			if (eObject instanceof Property) {
				isLocalizable = true;
			}

			if (isLocalizable) {
				String qualifiedName = EMFCoreUtil.getQualifiedName(eObject,
						true);
				if (UML2Util.isEmpty(qualifiedName)) {
					continue;
				}
				String localLabel = "_label_"
						+ qualifiedName.replace(NamedElement.SEPARATOR, "__");

				localLabel = UML2Util.getValidJavaIdentifier(localLabel);

				String name = EMFCoreUtil.getName(eObject);
				if (UML2Util.isEmpty(name)) {
					continue;
				}
				if (!bundle.containsKey(localLabel)) {
					bundleChanged = true;
					bundle.put(localLabel, getFormattedString(name));
				}
			}
		}

		if (!bundleChanged) {
			return;
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		StringBuilder comments = new StringBuilder();

		comments.append(" ").append(lineSeparator);
		comments.append("# Copyright (c) " + year + " Zeligsoft Inc").append(
				lineSeparator);
		comments.append("# All rights reserved.").append(lineSeparator);
		comments.append("# ").append(lineSeparator);
		comments.append(
				"# THIS PROGRAM IS THE UNPUBLISHED, PROPRIETARY PROPERTY OF ZELIGSOFT INC. AND")
				.append(lineSeparator);
		comments.append(
				"# IS TO BE MAINTAINED IN STRICT CONFIDENCE.  UNAUTHORIZED REPRODUCTION,")
				.append(lineSeparator);
		comments.append(
				"# DISTRIBUTION OR DISCLOSURE OF THIS PROGRAM, OR ANY PROGRAM DERIVED FROM IT,")
				.append(lineSeparator);
		comments.append("# IS STRICTLY PROHIBITED.").append(lineSeparator);
		comments.append("# ").append(lineSeparator);

		try {
			FileOutputStream out = new FileOutputStream(file.getLocation()
					.toOSString());

			// Save the bundle
			bundle.store(out, "");

			BufferedReader reader = new BufferedReader(new FileReader(file
					.getLocation().toOSString()));
			String line;
			ArrayList<String> contents = new ArrayList<String>();
			while (((line = reader.readLine()) != null)) {
				if (!UML2Util.isEmpty(line))
					contents.add(line);
			}
			Object sortedContents[] = contents.toArray();
			Arrays.sort(sortedContents, new Comparator<Object>() {

				@Override
				public int compare(Object object1, Object object2) {
					String string1 = object1.toString().replaceFirst(
							"^_[^_]+_", "");
					String string2 = object2.toString().replaceFirst(
							"^_[^_]+_", "");
					return string1.compareTo(string2);
				}
			});
			StringBuilder finalOutput = new StringBuilder(comments.toString());
			for (Object text : sortedContents) {
				String string = (String) text;
				if (string.length() > WORD_WRAP_SIZE - 1) {
					String splitString[] = string.split("=");
					if (splitString.length > 1) {
						finalOutput.append(splitString[0]).append("=\\")
								.append(lineSeparator);
						for (int i = 1; i < splitString.length; i++) {
							String temp = splitString[i];
							while (temp.length() > WORD_WRAP_SIZE) {
								finalOutput.append("\t");
								String subString = temp.substring(0,
										WORD_WRAP_SIZE - 1);
								int spaceIndex = subString.lastIndexOf(' ') + 1;
								finalOutput
										.append(temp.substring(0, spaceIndex))
										.append("\\").append(lineSeparator);
								temp = temp
										.substring(spaceIndex, temp.length());
							}
							finalOutput.append("\t");
							finalOutput.append(temp);
						}
						finalOutput.append(lineSeparator);
					} else {
						finalOutput.append(text).append(lineSeparator);
					}

				} else {
					finalOutput.append(text).append(lineSeparator);
				}
			}
			file.setContents(new ByteArrayInputStream(finalOutput.toString()
					.getBytes()), IResource.FORCE, null);

		} catch (Exception e) {
			ErrorDialog.openError(getShell(),
					"Saving localization bundle failed",
					"Failed to save localization bundle", new Status(
							IStatus.ERROR, Activator.PLUGIN_ID,
							"Failed to save localization bundle", e));
		}
	}

	/**
	 * @param uml
	 */
	private void cleanUpStereotypes(Resource uml) {
		// and remove any unrecognized stereotype applications (those whose
		// schema came from profiles that we didn't resolve) or whose profiles
		// are EPXes
		for (ListIterator<EObject> iter = uml.getContents().listIterator(
				uml.getContents().size()); iter.hasPrevious();) {

			EObject prev = iter.previous();

			if ((prev instanceof AnyType)
					|| "epx".equals(prev.eClass().eResource().getURI()
							.fileExtension())) {
				iter.remove();
			}
		}
	}

	/**
	 * @param uml
	 * @param crossResourceContainments
	 */
	private void absorbFragments(Resource uml,
			Map<EObject, Resource> crossResourceContainments) {
		// absorb cross-resource containments
		for (Map.Entry<EObject, Resource> pair : crossResourceContainments
				.entrySet()) {

			pair.getValue().getContents().remove(pair.getKey());

			// any other contents move to root resource (e.g., stereotype
			// applications)
			uml.getContents().addAll(pair.getValue().getContents());
		}
	}

	/**
	 * @param cache
	 * @param nukeEm
	 */
	private void deleteElements(CacheAdapter cache, Collection<EObject> nukeEm) {
		// destroy the annotations
		for (EObject nukeIt : nukeEm) {
			for (EStructuralFeature.Setting setting : cache
					.getInverseReferences(nukeIt)) {
				EcoreUtil.remove(setting, nukeIt);
			}

			EcoreUtil.remove(nukeIt);
		}
	}

	/**
	 * @param root
	 * @param nukeEm
	 * @param crossResourceContainments
	 */
	private void findElementsToDelete(EObject root, Collection<EObject> nukeEm,
			Map<EObject, Resource> crossResourceContainments) {
		// strip non-Eclipse annotations and absorb fragments
		for (TreeIterator<EObject> iter = root.eAllContents(); iter.hasNext();) {
			EObject next = iter.next();

			if (next instanceof EAnnotation) {
				EAnnotation annot = (EAnnotation) next;

				if (!UML_ANNOTATIONS.contains(annot.getSource())) {
					nukeEm.add(annot);
				}

				iter.prune();
			} else if (next instanceof ProfileApplication) {
				ProfileApplication app = (ProfileApplication) next;
				Profile profile = app.getAppliedProfile();

				if (profile.eIsProxy()
						|| "epx".equals(profile.eResource().getURI()
								.fileExtension())) {

					// pathmap is only registered with GMF? It's an RSM profile
					nukeEm.add(app);
				}

				iter.prune();
			} else {
				// handle cross-resource-contained element
				Resource res = ((InternalEObject) next).eDirectResource();
				if (res != null) {
					crossResourceContainments.put(next, res);
				}
			}
		}
	}

	/**
	 * @param uml
	 */
	private void loadUMLResource(Resource uml) {
		InputStream contents = null;
		try {
			contents = selectedFile.getContents();
			uml.load(contents, null);
		} catch (IOException e) {
			ErrorDialog
					.openError(PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getShell(),
							"Export failed", "Failed to load EMX model",
							new Status(IStatus.ERROR, Activator.PLUGIN_ID,
									"Failed to load EMX model", e));
		} catch (CoreException e) {
			ErrorDialog.openError(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "Export failed",
					"Failed to load EMX model", e.getStatus());
		} finally {
			if (contents != null) {
				try {
					contents.close();
				} catch (IOException e) {
					// no defense
					Log.error(Activator.getDefault(), 0, "Failed to close file input stream", e);
				}
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		selectedFile = null;
		IFile file = null;

		if (!selection.isEmpty() && (selection instanceof IStructuredSelection)) {
			IStructuredSelection ssel = (IStructuredSelection) selection;

			Object sel = ssel.getFirstElement();

			if (sel instanceof IFile) {
				file = (IFile) sel;
			} else if (sel instanceof IAdaptable) {
				file = (IFile) ((IAdaptable) sel).getAdapter(IFile.class);
			}

			if (file != null) {
				if ("emx".equals(file.getFileExtension())) {
					String fileName = file.getName();
					InputStream contents = null;

					try {
						contents = file.getContents();
						IContentType[] matches = Platform
								.getContentTypeManager().findContentTypesFor(
										contents, fileName);
						for (IContentType next : matches) {
							if (next.isKindOf(EMX_TYPE)) {
								selectedFile = file;
								break;
							}
						}
					} catch (IOException e) {
						Log.error(Activator.getDefault(), 0,
								"Failed to examine content-types of file: "
										+ file.getFullPath(), e);
					} catch (CoreException e) {
						Log.error(Activator.getDefault(), 0,
								"Failed to examine content-types of file: "
										+ file.getFullPath(), e);
					} finally {
						if (contents != null) {
							try {
								contents.close();
							} catch (IOException e) {
								// no defense
								Log.error(Activator.getDefault(), 0,
										"Failed to close file input stream", e);
							}
						}
					}
				}
			}
		}

		action.setEnabled(selectedFile != null);
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		part = targetPart;
		shell = (targetPart == null) ? null : targetPart.getSite().getShell();
	}

	/**
	 * Guess localization string from the given property name. Users have to
	 * manually polish the names after the auto generation.
	 * 
	 * @param name
	 * @return
	 */
	private String getFormattedString(String name) {

		String newName = name.substring(0, 1).toUpperCase()
				+ name.substring(1, name.length());

		return CodeGenUtil.format(newName, ' ', "", false, false);

	}

	private Shell getShell() {
		try {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getShell();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
