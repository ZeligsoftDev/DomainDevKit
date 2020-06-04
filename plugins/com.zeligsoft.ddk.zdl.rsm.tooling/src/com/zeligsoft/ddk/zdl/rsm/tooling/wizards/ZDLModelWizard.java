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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.uml2.common.util.UML2Util;

import com.zeligsoft.base.ui.utils.BaseUIUtil;
import com.zeligsoft.ddk.zdl.rsm.tooling.ZDLPlugin;
import com.zeligsoft.ddk.zdl.rsm.tooling.l10n.ZDLMessages;

/**
 * Creates a ZDL model project.
 * 
 * @author ysroh
 * 
 */
public class ZDLModelWizard
		extends Wizard
		implements INewWizard {

	private static String MODEL_NAME = ZDLMessages.ZDLModelWizard_ZDLDefaultModelName;

	private static String MODEL_NAME_EXTENSION = "emx"; //$NON-NLS-1$

	private String projectName = null;

	private WizardNewProjectCreationPage projectPage;

	private ZeligsoftModelWizardPage modelPage;

	private IResource resource;

	private static String MODEL_FILE_NAME = MODEL_NAME + "." //$NON-NLS-1$
		+ MODEL_NAME_EXTENSION;

	private static final String TEMPLATE_FILE_PATH = "/templates/" + MODEL_FILE_NAME; //$NON-NLS-1$

	@Override
	public boolean performFinish() {

		URI templateURI = URI.createPlatformPluginURI(
			"/" + ZDLPlugin.ID + TEMPLATE_FILE_PATH, true); //$NON-NLS-1$

		String modelName = modelPage.getModelName();
		if (UML2Util.isEmpty(modelName)) {
			modelName = MODEL_NAME;
		}

		if (resource == null) {
			// This is project wizard
			return ZeligsoftModelWizardContentCreator.createContent(projectPage
				.getProjectName(), null, modelName, templateURI,
				MODEL_FILE_NAME);
		}

		return ZeligsoftModelWizardContentCreator.createContent(projectName,
			modelPage.getFolderPath(), modelName, templateURI, MODEL_FILE_NAME);

	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		resource = BaseUIUtil.getIResourceFromSelection(selection);
		if (resource == null) {
			// if no selection is given then choose first project available
			// user will be able to select the target folder from the dialog
			// anyways.
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
			if (projects.length == 0) {
				// no project so perform project wizard instead of model wizard
				setWindowTitle(ZDLMessages.ZeligsoftModelWizard_ProjectTitle);
				projectPage = new WizardNewProjectCreationPage("ZDLProjectPage"); //$NON-NLS-1$
				projectPage
					.setTitle(ZDLMessages.ZeligsoftModelWizard_ProjectTitle);
				projectPage
					.setDescription(ZDLMessages.ZeligsoftModelWizard_ProjectPageDescription);

				addPage(projectPage);

				modelPage = new ZeligsoftModelWizardPage("ZDLModelPage"); //$NON-NLS-1$
				modelPage
					.setTitle(ZDLMessages.ZDLModelWizard_ModelWizardWindowTitle);
				modelPage.setDefaultModelName(MODEL_NAME);
				modelPage
					.setDescription(ZDLMessages.ZDLModelWizard_ModelWizardPageDescription);
				addPage(modelPage);
				return;
			}
			resource = projects[0];

		}

		projectName = resource.getProject().getName();

		setWindowTitle(ZDLMessages.ZDLModelWizard_ModelWizardPageTitle);
		modelPage = new ZeligsoftModelWizardPage("ZDLModelPage", resource); //$NON-NLS-1$
		modelPage.setTitle(ZDLMessages.ZDLModelWizard_ModelWizardWindowTitle);

		String modelName = ZeligsoftModelWizardContentCreator
			.getAvailableModelFileName(resource.getProject(), MODEL_NAME,
				MODEL_NAME_EXTENSION);
		modelPage.setDefaultModelName(modelName);
		modelPage
			.setDescription(ZDLMessages.ZDLModelWizard_ModelWizardPageDescription);
		addPage(modelPage);
	}
}
