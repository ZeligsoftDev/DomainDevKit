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
package com.zeligsoft.ddk.zdl.rsm.tooling.ext.types;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;

import com.zeligsoft.ddk.zdl.rsm.tooling.ext.l10n.Messages;

/**
 * Advice to set the visibility of DomainReference ends public.
 * 
 * @author Christian W. Damus (Zeligsoft)
 */
public class SetEndVisibilityAdvice
		extends AbstractEditHelperAdvice {

	/**
	 * Initializes me.
	 */
	public SetEndVisibilityAdvice() {
		super();
	}

	@Override
	protected ICommand getAfterConfigureCommand(final ConfigureRequest request) {
		// the command doesn't need affected files because we are only modifying
		// a newly created model element
		return new AbstractTransactionalCommand(request.getEditingDomain(),
			Messages.SetEndVisibilityAdvice_label, null) {

			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {

				Association domainRef = (Association) request.getElementToConfigure();
				
				for (Property end : domainRef.getMemberEnds()) {
					end.setVisibility(VisibilityKind.PUBLIC_LITERAL);
				}
				
				return CommandResult.newOKCommandResult(domainRef);
			}
		};
	}
}
