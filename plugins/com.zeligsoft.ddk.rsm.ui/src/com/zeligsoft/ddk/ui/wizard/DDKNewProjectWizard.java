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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.zeligsoft.ddk.rsm.ui.l10n.Messages;
import com.zeligsoft.ddk.ui.creators.DDKProjectCreator;
import com.zeligsoft.ddk.ui.creators.IProjectCreator;
import com.zeligsoft.ddk.ui.creators.IProjectInfo;

/**
 * An abstract class for DDK project wizards.
 * 
 * @author Toby McClean (tmcclean)
 * 
 */
public abstract class DDKNewProjectWizard extends Wizard implements INewWizard {

	protected IStructuredSelection selection;
	private IWorkbench workbench;
	private IProjectCreator creator;

	/**
	 * Initialize me.
	 */
	public DDKNewProjectWizard() {
		super();
		creator = new DDKProjectCreator();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Access the project information that will be used to create the project
	 * specified in the wizard.
	 * 
	 * @return The project information for the project to be created
	 */
	protected abstract IProjectInfo getProjectInfo();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final IProjectInfo projectInfo = getProjectInfo();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(projectInfo, monitor);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(),
					Messages.DDKNewProjectWizard__Error_During_Finish_Title,
					realException.getLocalizedMessage());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	/**
	 * Accessor for the workbench that the wizard was opened in.
	 * 
	 * @return The workbench the wizard was opened in.
	 */
	public IWorkbench getWorkbench() {
		return workbench;
	}

	/**
	 * Handle the Finish button being clicked in the wizard
	 * 
	 * @param projectInfo
	 * @param monitor
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	protected void doFinish(final IProjectInfo projectInfo,
			final IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		creator.setProjectInfo(projectInfo);
		creator.run(monitor);
	}

}
