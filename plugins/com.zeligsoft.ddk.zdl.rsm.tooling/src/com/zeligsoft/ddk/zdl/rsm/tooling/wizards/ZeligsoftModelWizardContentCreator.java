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

package com.zeligsoft.ddk.zdl.rsm.tooling.wizards;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.Package;

import com.zeligsoft.ddk.zdl.rsm.tooling.ZDLPlugin;
import com.zeligsoft.ddk.zdl.rsm.tooling.l10n.ZDLMessages;


/**
 * Creates content for Zeligsoft project and model wizard
 * 
 * @author ysroh
 * 
 */
public class ZeligsoftModelWizardContentCreator {

	/**
	 * Create a model using given template path and save it under given project
	 * name. Project will be created if necessary
	 * 
	 * @param projectName
	 *            Name of the project that the model will be created under.
	 * @param destFolder
	 *            The full path of the destination folder of the new model.
	 * @param modelName
	 *            Name of the new model.
	 * @param templateURI
	 *            The URI to the template file.
	 * @param templateFileName
	 *            The Filename of the template model.
	 * @return whether the content was successfully created
	 */
	public static boolean createContent(String projectName, String destFolder,
			String modelName, URI templateURI,
			String templateFileName) {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (projectName == null || projectName == "") { //$NON-NLS-1$
			projectName = getAvailableProjectName();
		}
		IProject project = root.getProject(projectName);
		if (!project.exists()) {
			try {
				project.create(null);
				project.open(null);
			} catch (CoreException e) {
				ZDLPlugin
					.getDefault()
					.error(
						ZDLMessages.ZeligsoftModelWizardContentCreator_ProjectCreateionFailedLog,
						e);
				return false;
			}
		}

		String newFileName;
		newFileName = modelName + ".emx"; //$NON-NLS-1$

		// Get template to copy
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource template = resourceSet.getResource(templateURI, true);

		// Save template on current project
		String modelPath;
		if (destFolder == null || destFolder == "") { //$NON-NLS-1$
			modelPath = projectName + "/" + newFileName; //$NON-NLS-1$
		} else {
			modelPath = destFolder + "/" + newFileName; //$NON-NLS-1$
		}
		URI modelURI = URI.createPlatformResourceURI(modelPath, true);
		Resource modelResource = resourceSet.createResource(modelURI);
		modelResource.getContents().addAll(template.getContents());
		Package model = (Package) modelResource.getContents().get(0);
		model.setName(modelName);

		// Save the model
		try {
			modelResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			ZDLPlugin
				.getDefault()
				.error(
					ZDLMessages.ZeligsoftModelWizardContentCreator_ResourceSaveFailedLog,
					e);
			return false;
		}

		// Open model using default editor
		final IFile targetFile = root.getFile(new Path(modelPath));

		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorRegistry reg = page.getWorkbenchWindow().getWorkbench()
			.getEditorRegistry();
		String editorID = reg.getDefaultEditor(targetFile.toString()).getId();

		try {
			page.openEditor(new FileEditorInput(targetFile), editorID);
			IPerspectiveDescriptor perspective = PlatformUI.getWorkbench()
				.getPerspectiveRegistry().findPerspectiveWithId(
					"com.zeligsoft.ddk.ui.perspectives.DDKPerspective"); //$NON-NLS-1$
			page.setPerspective(perspective);
		} catch (PartInitException e) {
			ZDLPlugin.getDefault().error(
				ZDLMessages.ZeligsoftModelWizardContentCreator_ModelOpenFailedLog,
				e);
			return false;
		}

		return true;
	}

	/**
	 * Find available project name
	 * 
	 * @return project name
	 */
	private static String getAvailableProjectName() {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String projectName = ZDLMessages.ZeligsoftModelWizardContentCreator_ZeligsoftDefaultProjectName;
		if (!root.getProject(projectName).exists()) {
			return projectName;
		}
		int i = 1;
		while (true) {
			if (!root.getProject(projectName + i).exists()) {
				return (projectName + i);
			}
			i++;
		}
	}

	/**
	 * Find the first available filename of the pattern filename1, filename2...
	 * 
	 * @param project
	 *            project
	 * @param filename
	 *            default file name. e.g., SCAModel
	 * @param extension
	 *            file extension. e.g., emx
	 * @return first available filename
	 */
	public static String getAvailableModelFileName(IProject project,
			String filename, String extension) {

		if (!project.getFile(filename + "." + extension).exists()) { //$NON-NLS-1$
			return filename;
		}

		int i = 1;
		while (true) {
			StringBuilder newFileName = new StringBuilder(filename).append(i)
				.append(".").append(extension); //$NON-NLS-1$
			if (!project.getFile(newFileName.toString()).exists()) {
				return filename + i;
			}
			i++;
		}
	}

}
