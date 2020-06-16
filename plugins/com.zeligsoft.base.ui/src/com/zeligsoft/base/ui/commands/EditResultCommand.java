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

package com.zeligsoft.base.ui.commands;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;

/**
 * A Command to edit an element of a viewer.
 */
public class EditResultCommand extends CommandWrapper {

	/** The ViewPart */
	private IViewPart viewPart = null;

	/** Number of the column which contains the element. */
	private int numColumn = 0;

	/**
	 *
	 * Constructor.
	 *
	 * @param command   The command.
	 * @param viewPart  The IViewPart
	 * @param numColumn The number of the column.
	 */
	public EditResultCommand(Command command, IViewPart viewPart, int numColumn) {
		super(command);
		this.viewPart = viewPart;
		this.numColumn = numColumn;
	}

	/**
	 * Wrap.
	 *
	 * @param command   The command.
	 * @param viewPart  The IViewPart.
	 * @param numColumn The number of the column.
	 * @return The wrapped column.
	 */
	public static Command wrap(Command command, IViewPart viewPart, int numColumn) {
		return new EditResultCommand(command, viewPart, numColumn);
	}

	/**
	 * Wrap.
	 *
	 * @param command  The command.
	 * @param viewPart The IViewPart.
	 * @return The wrapped command.
	 */
	public static Command wrap(Command command, IViewPart viewPart) {
		return wrap(command, viewPart, 0);
	}

	/**
	 * Gets the results command.
	 *
	 * @return the results command
	 */
	private Collection<?> getResultsCommand() {
		// Get results list
		Collection<?> resultsCommand = getResult();
		if (getResult().isEmpty()) {

			// Get affected objects list if it is not a command with result
			resultsCommand = getAffectedObjects();
		}

		return resultsCommand;
	}

	/**
	 * Edit the result of the command.
	 */
	private void editResult() {
		Collection<?> results = getResultsCommand();
		if (!results.isEmpty()) {
			Iterator<?> resultIterator = results.iterator();

			// Get first result
			final Object result = resultIterator.next();
			Display.getCurrent().asyncExec(new Runnable() {

				@Override
				public void run() {

					if (viewPart instanceof ModelExplorerView) {
						if (result instanceof EObject) {
							((ModelExplorerView) viewPart).editElement((EObject) result, numColumn);
						}
					}
				}
			});
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		super.execute();
		editResult();
	}
}
