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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

import com.zeligsoft.ddk.rsm.ui.l10n.Messages;

/**
 * A tool for specifying the subset-superset relationship between associations.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class SpecifySubsetActionDelegate
		extends ActionDelegate
		implements IObjectActionDelegate {

	private Set<Property> properties;

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		properties = null;

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;

			if (ssel.size() == 2) {
				properties = new java.util.HashSet<Property>();

				for (Object next : ssel.toArray()) {
					if (next instanceof IAdaptable) {
						next = ((IAdaptable) next).getAdapter(EObject.class);
					}

					if (next instanceof Property) {
						properties.add((Property) next);
					}
				}

				if (properties.size() != 2) {
					properties = null;
				}
			}
		}

		action.setEnabled(properties != null);
	}

	@Override
	public void run(IAction action) {

		if ((properties != null) && (properties.size() == 2)) {
			IOperationHistory history = PlatformUI.getWorkbench()
				.getOperationSupport().getOperationHistory();

			try {
				history.execute(new AbstractEMFOperation(TransactionUtil
					.getEditingDomain(properties.iterator().next()), action
					.getText()) {

					@Override
					protected IStatus doExecute(IProgressMonitor monitor,
							IAdaptable info)
							throws ExecutionException {
						Iterator<Property> iter = properties.iterator();
						configureSubsetting(iter.next(), iter.next());
						return Status.OK_STATUS;
					}

				}, new NullProgressMonitor(), PlatformUI.getWorkbench());
			} catch (ExecutionException e) {
				MessageDialog.openError(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(),
					Messages.SpecifySubsetActionDelegate_errorDlgTitle,
					Messages.SpecifySubsetActionDelegate_subsetFailed
						+ e.getLocalizedMessage());
			}
		}
	}

	private void configureSubsetting(Property a, Property b)
			throws ExecutionException {
		// first, figure out which is the subset and which the superset
		// by analyzing the subsetting context

		EList<Type> aCtx = a.subsettingContext();
		EList<Type> bCtx = b.subsettingContext();

		if (!aCtx.isEmpty() && !bCtx.isEmpty()) {
			Classifier aCtxCls = (Classifier) aCtx.get(0);
			Classifier bCtxCls = (Classifier) bCtx.get(0);

			Property subset;
			Property superset;

			if (aCtxCls.conformsTo(bCtxCls)) {
				subset = a;
				superset = b;
			} else if (bCtxCls.conformsTo(aCtxCls)) {
				subset = b;
				superset = a;
			} else {
				throw new ExecutionException(
					Messages.SpecifySubsetActionDelegate_noSubsetContext);
			}

			if (!subset.getSubsettedProperties().contains(superset)) {
				subset.getSubsettedProperties().add(superset);
			}

			Association superAssoc = superset.getAssociation();
			Association subAssoc = subset.getAssociation();
			if ((superAssoc != null) && (subAssoc != null)) {
				Property superOther = superset.getOtherEnd();
				Property subOther = subset.getOtherEnd();

				if (!subOther.getSubsettedProperties().contains(superOther)) {
					subOther.getSubsettedProperties().add(superOther);
				}

				if (!subAssoc.getGenerals().contains(superAssoc)) {
					subAssoc.getGenerals().add(superAssoc);
				}
			}
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// nothing to do
	}
}
