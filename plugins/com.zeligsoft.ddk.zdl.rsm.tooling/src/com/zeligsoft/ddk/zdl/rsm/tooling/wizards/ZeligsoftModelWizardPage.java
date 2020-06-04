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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import com.zeligsoft.ddk.zdl.rsm.tooling.l10n.ZDLMessages;

/**
 * Wizard page for creating Zeligsoft model
 * 
 * @author ysroh
 * 
 */
public class ZeligsoftModelWizardPage
		extends WizardPage {

	private Text modelNameField;

	private Text folderNameField;

	private Label folderErrorLabel;

	private IResource resource;

	private String dest;

	private String defaultModelName = ""; //$NON-NLS-1$

	private static int TEXT_FIELD_WIDTH = 200;

	private static int NAME_LABEL_WIDTH = 100;

	private static int FOLDER_NAME_FIELD_WIDTH = 200;

	// Name field modify listener
	private Listener nameFieldModifyListener = new Listener() {

		@Override
		public void handleEvent(Event e) {
			validateName();
		}

	};

	// Folder name field modify listener
	private Listener folderNameFieldModifyListener = new Listener() {

		@Override
		public void handleEvent(Event e) {
			validateFolderName();
		}

	};

	public ZeligsoftModelWizardPage(String pageName) {

		super(pageName);
		resource = null;
	}

	/**
	 * Constructor
	 * 
	 * @param pageName
	 *            The name of the page
	 * @param selectedResource
	 *            This resource is used to validate the path
	 */
	public ZeligsoftModelWizardPage(String pageName, IResource selectedResource) {

		super(pageName);
		resource = selectedResource;
		if (selectedResource == null) {
			return;
		}
		if (!(resource instanceof IContainer)) {
			dest = resource.getParent().getFullPath().toString();
		} else {
			dest = resource.getFullPath().toString();
		}
	}

	@Override
	public void createControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createModelNameGroup(composite);

		if (resource != null) {
			createFolderNameGroup(composite);
		}

		setControl(composite);

	}

	/**
	 * Creates an folder name group
	 * 
	 * @param parent
	 *            Parent composite
	 */
	private void createFolderNameGroup(Composite parent) {

		Composite folderNameArea = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		folderNameArea.setLayout(layout);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		folderNameArea.setLayoutData(data);

		Label folderLabel = new Label(folderNameArea, SWT.LEFT);
		folderLabel
			.setText(ZDLMessages.ZeligsoftModelWizardPage_DestinationFolderLabel);
		data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		data.grabExcessHorizontalSpace = false;
		folderLabel.setLayoutData(data);
		folderLabel.setFont(parent.getFont());

		folderNameField = new Text(folderNameArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = FOLDER_NAME_FIELD_WIDTH;
		folderNameField.setLayoutData(data);
		folderNameField.setText(dest);
		folderNameField.setFont(parent.getFont());
		folderNameField.addListener(SWT.Modify, folderNameFieldModifyListener);

		final ContainerSelectionDialog dialog = new ContainerSelectionDialog(
			getShell(),
			resource.getProject().getFolder(dest),
			false,
			ZDLMessages.ZeligsoftModelWizardPage_BrowseFolderDialogDescriptionLabel);

		Button browseButton = new Button(folderNameArea, SWT.NULL);
		browseButton
			.setText(ZDLMessages.ZeligsoftModelWizardPage_BrowseButtonLabel);
		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (dialog.open() == Window.OK) {
					folderNameField.setText(((IPath) dialog.getResult()[0])
						.toString());
				}
			}
		});

		new Label(folderNameArea, SWT.NULL);
		folderErrorLabel = new Label(folderNameArea, SWT.NONE);
		folderErrorLabel
			.setText(ZDLMessages.ZeligsoftModelWizardPage_FolderErrorMessage);
		folderErrorLabel.setFont(parent.getFont());
		folderErrorLabel.setForeground(new Color(parent.getForeground()
			.getDevice(), 200, 0, 0));
		folderErrorLabel.setVisible(false);

	}

	/**
	 * Creates model name group
	 * 
	 * @param parent
	 *            Parent composite
	 */
	private final void createModelNameGroup(Composite parent) {
		Composite modelNameArea = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		modelNameArea.setLayout(layout);
		modelNameArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label modelLabel = new Label(modelNameArea, SWT.NONE);
		modelLabel.setText(ZDLMessages.ZeligsoftModelWizardPage_ModelNameLabel);
		GridData data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		modelLabel.setLayoutData(data);

		modelNameField = new Text(modelNameArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		modelNameField.setText(defaultModelName);
		modelNameField.selectAll();
		modelNameField.setLayoutData(data);
		modelNameField.addListener(SWT.Modify, nameFieldModifyListener);

	}

	/**
	 * Returns model name
	 * 
	 * @return model name
	 */
	public String getModelName() {
		if (modelNameField == null) {
			return ""; //$NON-NLS-1$
		}
		return modelNameField.getText();
	}

	/**
	 * Returns destination folder path in string
	 * 
	 * @return
	 */
	public String getFolderPath() {
		if (folderNameField == null) {
			return ""; //$NON-NLS-1$
		}
		return folderNameField.getText();
	}

	/**
	 * Validate the model name
	 */
	private void validateName() {
		if (modelNameField.getText() == "") { //$NON-NLS-1$
			setPageComplete(false);
			return;
		}

		setPageComplete(true);

		if (resource != null) {

			// check to see if model exists already
			String destFile = folderNameField.getText() + "/" //$NON-NLS-1$
				+ modelNameField.getText() + ".emx"; //$NON-NLS-1$
			IFile file = resource.getWorkspace().getRoot().getFile(
				new Path(destFile));
			if (file.exists()) {
				setErrorMessage(ZDLMessages.ZeligsoftModelWizardPage_ModelNameErrorMessage);
				setPageComplete(false);
			} else {
				setErrorMessage(null);
			}
		}
	}

	/**
	 * Validate folder name
	 */
	private void validateFolderName() {

		// Contains project name only
		String[] pathSeg = folderNameField.getText().split("/"); //$NON-NLS-1$
		if (pathSeg.length == 1
			|| (pathSeg.length == 2 && pathSeg[0].length() == 0)) {
			if (!(resource.getWorkspace().getRoot().getProject(folderNameField
				.getText())).exists()) {
				setPageComplete(false);
				folderErrorLabel.setVisible(true);
			} else {
				setPageComplete(true);
				folderErrorLabel.setVisible(false);
			}
			return;
		}

		// Contains project name and folder name
		IFolder folder = resource.getWorkspace().getRoot().getFolder(
			new Path(folderNameField.getText()));
		if (!folder.exists()) {
			setPageComplete(false);
			folderErrorLabel.setVisible(true);
			return;
		}
		setPageComplete(true);
		folderErrorLabel.setVisible(false);
	}

	/**
	 * Set the default model name
	 * 
	 * @param modelName
	 *            default model name
	 */
	public void setDefaultModelName(String modelName) {
		this.defaultModelName = modelName;
	}
}
