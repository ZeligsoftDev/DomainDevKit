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
package com.zeligsoft.ddk.ui.wizard;

import com.zeligsoft.ddk.rsm.ui.l10n.Messages;
import com.zeligsoft.ddk.ui.creators.DDKProjectCreator;
import com.zeligsoft.ddk.ui.creators.IProjectInfo;

/**
 * The wizard for creating a new DDK project.
 * 
 * @author Toby McClean (tmcclean)
 *
 */
public class ZDLNewProjectWizard extends DDKNewProjectWizard {
	private ZDLNewProjectMainPage mainPage;
	
	/**
	 * Initialize me.
	 */
	public ZDLNewProjectWizard() {
		setWindowTitle(Messages.ZDLNewProjectWizard__Wizard_Window_title);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		mainPage = new ZDLNewProjectMainPage("mainPage"); //$NON-NLS-1$
		addPage(mainPage);
	}
	
	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.wizard.DDKNewProjectWizard#getProjectInfo()
	 */
	@Override
	protected IProjectInfo getProjectInfo() {
		DDKProjectCreator.DDKProjectInfo projectInfo = new DDKProjectCreator.DDKProjectInfo();
		
		// information from the main page
		projectInfo.setProjectName(mainPage.getProjectName());
		projectInfo.setDomainName(mainPage.getDomainName());
		
		// information from the model page
		projectInfo.setZDLModelResource(mainPage.getModelName());
		projectInfo.setZDLGenModelResource(mainPage.getGenModelName());
		projectInfo.setUMLModelResource(mainPage.getUMLModelName());
		projectInfo.setZDL_NS_URI(mainPage.getNSURI());
		
		return projectInfo;
	}

}
