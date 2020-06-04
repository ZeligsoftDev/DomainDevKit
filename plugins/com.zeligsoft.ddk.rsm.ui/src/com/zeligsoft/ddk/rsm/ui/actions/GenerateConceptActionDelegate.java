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
package com.zeligsoft.ddk.rsm.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.core.search.TypeDeclarationMatch;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Package;

import com.zeligsoft.ddk.rsm.ui.Activator;
import com.zeligsoft.ddk.rsm.ui.DomainDevelopmentConstants;
import com.zeligsoft.ddk.rsm.ui.l10n.Messages;
import com.zeligsoft.ddk.rsm.ui.operations.GenerateConceptsOperation;

/**
 * Action Delegate to generate ZDL Java style constants.
 * 
 * @author jcorchis
 * 
 */
public class GenerateConceptActionDelegate
		extends ActionDelegate
		implements IObjectActionDelegate {

	private IStructuredSelection selection;

	private IWorkbenchPart myPart;

	private String packageName;

	private IFolder folder;

	private List<IType> searchResults;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myPart = targetPart;
	}

	@Override
	public void run(IAction action) {

		if (selection != null) {
			Object object = selection.getFirstElement();
			if (object instanceof IAdaptable) {
				Package model = (Package) ((IAdaptable) object)
					.getAdapter(Package.class);
				if (model != null) {
					findExistingType(model);
					if (folder == null) {
						selectFolder();
					}
					if (folder != null) {
						GenerateConceptsOperation op = new GenerateConceptsOperation(
							getShell(), model, folder, packageName);
						op.run();
					}

				}
			}
		}

	}
	
	@Override
	public void dispose() {
		selection = null;
		myPart = null;
		folder = null;
		searchResults = null;
		packageName = null;
		super.dispose();
	}

	/**
	 * Caches the selection
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) selection;
		}
	}

	private Shell getShell() {
		return (myPart == null)
			? Display.getDefault().getActiveShell()
			: myPart.getSite().getShell();
	}

	/**
	 * Returns the IContainer for the Java type to be created by first searching
	 * for an existing Java type (based on the name of the model).
	 * 
	 * @param model
	 * @return
	 */
	private void findExistingType(Package model) {

		String fileNamePattern = UML2Util.getValidJavaIdentifier(model
			.getName())
			+ "Names";//$NON-NLS-1$

		SearchPattern pattern = SearchPattern.createPattern(fileNamePattern,
			IJavaSearchConstants.CLASS, IJavaSearchConstants.DECLARATIONS,
			SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE);

		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();

		searchResults = new ArrayList<IType>();

		SearchRequestor requestor = new SearchRequestor() {

			@Override
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				if (match instanceof TypeDeclarationMatch) {
					TypeDeclarationMatch m = (TypeDeclarationMatch) match;
					Object o = m.getElement();
					if (o instanceof IType) {
						IType ty = (IType) o;
						searchResults.add(ty);
					}

				}
			}

		};

		SearchEngine searchEngine = new SearchEngine();
		try {
			searchEngine.search(pattern, new SearchParticipant[]{SearchEngine
				.getDefaultSearchParticipant()}, scope, requestor, null);
		} catch (CoreException e) {
			Activator
				.getDefault()
				.error(
					NLS
						.bind(
							Messages.ZDLConstantsGenerationActionDelegate_workspaceSearchException,
							fileNamePattern), e);
		}

		if (searchResults.size() > 1) {
			// Select among existing locations
			IJavaElement[] scopeElements = new IJavaElement[searchResults
				.size()];
			for (int i = 0; i < searchResults.size(); i++) {
				scopeElements[i] = searchResults.get(i).getPackageFragment();
			}
			scope = SearchEngine.createJavaSearchScope(scopeElements,
				IJavaSearchScope.SOURCES);

			IRunnableContext context = new IRunnableContext() {

				@Override
				public void run(boolean fork, boolean cancelable,
						IRunnableWithProgress runnable)
						throws InvocationTargetException, InterruptedException {
					runnable.run(new NullProgressMonitor());
				}
			};

			SelectionDialog dialog = JavaUI.createPackageDialog(getShell(),
				context, scope, false, true, "");//$NON-NLS-1$
			dialog
				.setTitle(Messages.ZDLConstantsGenerationActionDelegate_selectionDlgTitle);
			dialog
				.setMessage(Messages.ZDLConstantsGenerationActionDelegate_multipleMatches);
			if (dialog.open() == Window.OK) {
				Object[] resultArray = dialog.getResult();
				if(resultArray.length > 0) {
					Object result = resultArray[0];
					if (result instanceof IPackageFragment) {
						IPackageFragment packageFragment = (IPackageFragment) result;
						packageName = packageFragment.getElementName();
						try {
							folder = (IFolder) packageFragment
								.getCorrespondingResource();
						} catch (JavaModelException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else if (searchResults.size() == 1) {
			IType type = searchResults.get(0);
			IPackageFragment pf = type.getPackageFragment();
			packageName = pf.getElementName();
			try {
				folder = (IFolder) pf.getCorrespondingResource();
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Opens a workspace Package selection dialog.
	 * 
	 * @param model
	 * @return the selected package
	 */
	private void selectFolder() {

		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
		IRunnableContext context = new IRunnableContext() {

			@Override
			public void run(boolean fork, boolean cancelable,
					IRunnableWithProgress runnable)
					throws InvocationTargetException, InterruptedException {
				runnable.run(new NullProgressMonitor());
			}
		};

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String namesPackageFilter = store
			.getString(DomainDevelopmentConstants.NAMES_PACKAGE_FILTER);

		SelectionDialog dialog = JavaUI.createPackageDialog(getShell(),
			context, scope, false, true, namesPackageFilter);
		dialog
			.setTitle(Messages.ZDLConstantsGenerationActionDelegate_selectionDlgTitle);
		dialog
			.setMessage(Messages.ZDLConstantsGenerationActionDelegate_selectionDlgDescription);

		if (dialog.open() == Window.OK) {
			IPackageFragment pf = (IPackageFragment) dialog.getResult()[0];
			folder = (IFolder) pf.getResource();
			packageName = pf.getElementName();
		}
	}
}
