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

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.uml2.common.util.UML2Util;

import com.zeligsoft.ddk.zdl.rsm.tooling.ZDLPlugin;
import com.zeligsoft.ddk.zdl.rsm.tooling.l10n.ZDLMessages;

/**
 * Creates an SCA domain project.
 * 
 * @author ysroh
 * 
 */
public class ZDLProjectWizard
		extends Wizard
		implements INewWizard {

	private static String MODEL_NAME = ZDLMessages.ZDLModelWizard_ZDLDefaultModelName;

	private static String MODEL_FILE_NAME = "NewDomain.emx"; //$NON-NLS-1$

	private static final String TEMPLATE_FILE_PATH = "/templates/" + MODEL_FILE_NAME; //$NON-NLS-1$

	private WizardNewProjectCreationPage projectPage;

	private ZeligsoftModelWizardPage modelPage;

	@Override
	public boolean performFinish() {

		URI templateURI = URI.createPlatformPluginURI(
			"/" + ZDLPlugin.ID + TEMPLATE_FILE_PATH, true); //$NON-NLS-1$

		String modelName = modelPage.getModelName();
		if (UML2Util.isEmpty(modelName)) {
			modelName = MODEL_NAME;
		}
		return ZeligsoftModelWizardContentCreator.createContent(projectPage
			.getProjectName(), null, modelName, templateURI, MODEL_FILE_NAME);

	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		setWindowTitle(ZDLMessages.ZeligsoftModelWizard_ProjectTitle);
		projectPage = new WizardNewProjectCreationPage("ZDLProjectPage"); //$NON-NLS-1$
		projectPage.setTitle(ZDLMessages.ZeligsoftModelWizard_ProjectTitle);
		projectPage
			.setDescription(ZDLMessages.ZeligsoftModelWizard_ProjectPageDescription);

		addPage(projectPage);

		modelPage = new ZeligsoftModelWizardPage("ZDLModelPage"); //$NON-NLS-1$
		modelPage.setTitle(ZDLMessages.ZDLModelWizard_ModelWizardWindowTitle);
		modelPage.setDefaultModelName(MODEL_NAME);
		modelPage
			.setDescription(ZDLMessages.ZDLModelWizard_ModelWizardPageDescription);
		addPage(modelPage);
	}

}
