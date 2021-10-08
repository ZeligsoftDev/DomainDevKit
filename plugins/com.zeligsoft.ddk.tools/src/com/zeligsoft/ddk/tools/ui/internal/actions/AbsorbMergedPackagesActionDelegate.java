/*******************************************************************************
 * Copyright (c) 2021 Northrop Grumman Systems Corporation.
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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.osgi.framework.Bundle;

import com.zeligsoft.ddk.tools.ui.internal.handlers.AbsorbMergedPackagesWorker;
import com.zeligsoft.ddk.tools.ui.internal.handlers.Messages;

/**
 * Action delegate for 'Absorb Merged Packages' command.
 *
 */
public class AbsorbMergedPackagesActionDelegate extends ActionDelegate implements IObjectActionDelegate {

	private ISelection selection = null;
	
	/**
	 * 
	 */
	public AbsorbMergedPackagesActionDelegate() {
		// nothing to do
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
	
	
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
		// Verify that a single .uml file is selected
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
				if (!"uml".equals(file.getFileExtension())) {
					file = null;
				}
			}
		}

		action.setEnabled(file != null);
	}

	private void logError(CoreException e) {
	    Bundle bundle = Platform.getBundle(com.zeligsoft.ddk.tools.Activator.PLUGIN_ID);
	    ILog log = Platform.getLog(bundle);
	    log.log(e.getStatus());
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

	@Override
	public void run(IAction action) {
		try {
			new AbsorbMergedPackagesWorker(selection).doWork();
		} catch (CoreException e) {
			if(e.getStatus().getSeverity() == IStatus.ERROR) {
				MessageDialog.openError(
						getShell(),
						Messages.AbsorbMergedPackagesHandler_DialogTitle,
						e.getStatus().getMessage());
				logError(e);
			} else if(e.getStatus().getSeverity() == IStatus.WARNING) {
				MessageDialog.openWarning(
						getShell(),
						Messages.AbsorbMergedPackagesHandler_DialogTitle,
						e.getStatus().getMessage());
			}
		}
	}
}
