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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.zeligsoft.ddk.rsm.ui.l10n.Messages;

/**
 * A page to specify the information required for creating a new DDK project.
 * 
 * @author Toby McClean (tmcclean)
 * 
 */
public class ZDLNewProjectMainPage extends WizardPage {

	private static int TEXT_FIELD_WIDTH = 200;
	private static int NAME_LABEL_WIDTH = 100;
	private static int CHECK_BOX_WIDTH = 100;

	private Text domainNameField;
	private Text projectNameField;
	private Text nsURIField;
	private Text modelNameField;
	private Text genModelNameField;
	private Text organizationNameField;
	private Text umlModelNameField;

	private Button nsUsesDefault;
	private Button modelNameUsesDefault;
	private Button genModelNameUsesDefault;
	private Button umlModelNameUsesDefault;

	private String defaultModelName = "models/domain.emx"; //$NON-NLS-1$
	private String defaultGenModelName = "models/domain.zdlgen"; //$NON-NLS-1$
	private String defaultDomainName = "domain"; //$NON-NLS-1$
	private String defaultProjectName = "com.company.domain"; //$NON-NLS-1$
	private String defaultNSURI = "http://company/domain/2009"; //$NON-NLS-1$
	private String defaultOrganizationName = "company"; //$NON-NLS-1$
	private String defaultUMLModelName = "models/domains.uml"; //$NON-NLS-1$

	private static final String DEFAULT_NSURI_FORMAT = "http://%1$s/%2$s/2009"; //$NON-NLS-1$
	private static final String DEFAULT_GEN_MODEL_FORMAT = "models/%1$s.zdlgen"; //$NON-NLS-1$
	private static final String DEFAULT_MODEL_FORMAT = "models/%1$s.emx"; //$NON-NLS-1$
	private static final String DEFAULT_UML_MODEL_FORMAT = "models/%1$s.uml"; //$NON-NLS-1$

	// Name field modify listener
	private Listener nameFieldModifyListener = new Listener() {

		@Override
		public void handleEvent(Event e) {
			validateName();
		}

	};

	// Domain name field modification listener which will update
	// model names and NS URI if they use the default name
	private Listener domainNameFieldModifyListener = new Listener() {
		@Override
		public void handleEvent(Event e) {
			if (nsUsesDefault.getSelection()) {
				nsURIField.setText(String.format(DEFAULT_NSURI_FORMAT,
						organizationNameField.getText(), domainNameField
								.getText()));
			}

			if (modelNameUsesDefault.getSelection()) {
				modelNameField.setText(String.format(DEFAULT_MODEL_FORMAT,
						domainNameField.getText()));
			}

			if (genModelNameUsesDefault.getSelection()) {
				genModelNameField.setText(String.format(
						DEFAULT_GEN_MODEL_FORMAT, domainNameField.getText()));
			}

			if (umlModelNameUsesDefault.getSelection()) {
				umlModelNameField.setText(String.format(
						DEFAULT_UML_MODEL_FORMAT, domainNameField.getText()));
			}
		}
	};

	// Organization name field modification listener, which will update
	// the NS URI field if it uses the default.
	private Listener organizationNameFieldModifyListener = new Listener() {
		@Override
		public void handleEvent(Event e) {
			if (nsUsesDefault.getSelection()) {
				nsURIField.setText(String.format(DEFAULT_NSURI_FORMAT,
						organizationNameField.getText(), domainNameField
								.getText()));
			}
		}
	};

	// NS URI check box state changed listener, which controls whether the
	// NS URI field is enabled or not
	private SelectionAdapter nsURIDefaultModifyListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			nsURIField.setEnabled(!nsUsesDefault.getSelection());
		}
	};

	// Model name check box state changed listener, which controls whether the
	// model resource name field is enabled
	private SelectionAdapter modelNameDefaultModifyListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			modelNameField.setEnabled(!modelNameUsesDefault.getSelection());
		}
	};

	// Gen model name check box state changed listener, which controls whether
	// the gen model name field is enabled or not
	private SelectionAdapter genModelNameDefaultModifyListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			genModelNameField.setEnabled(!genModelNameUsesDefault
					.getSelection());
		}
	};

	// UML model name check box state changed listener, which controls whether
	// the UML model name field is enabled or not
	private SelectionAdapter umlModelNameDefaultModifyListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			umlModelNameField.setEnabled(!umlModelNameUsesDefault
					.getSelection());
		}
	};

	/**
	 * Create me with my name.
	 * 
	 * @param pageName
	 * 		My name.
	 */
	public ZDLNewProjectMainPage(String pageName) {
		super(pageName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createProjectNameGroup(composite);
		createDomainNameGroup(composite);
		createResourceGroup(composite);

		setControl(composite);
	}

	/**
	 * Create the group on the page related to the project name and any
	 * other project configuration.
	 * 
	 * @param parent
	 * 		The container of the project group.
	 */
	private final void createProjectNameGroup(Composite parent) {
		Group projectNameArea = new Group(parent, SWT.NONE);
		projectNameArea.setText(Messages.ZDLNewProjectMainPage__Project_Configuration_group_label);

		// layout object
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		projectNameArea.setLayout(layout);
		projectNameArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// contents of the group
		Label organizationNameLabel = new Label(projectNameArea, SWT.NONE);
		organizationNameLabel.setText(Messages.ZDLNewProjectMainPage__Organization_label);
		GridData data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		organizationNameLabel.setLayoutData(data);

		organizationNameField = new Text(projectNameArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		organizationNameField.setText(defaultOrganizationName);
		organizationNameField.selectAll();
		organizationNameField.setLayoutData(data);
		organizationNameField.addListener(SWT.Modify,
				organizationNameFieldModifyListener);

		Label projectNameLabel = new Label(projectNameArea, SWT.NONE);
		projectNameLabel.setText(Messages.ZDLNewProjectMainPage__Project_Name_label);
		data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		projectNameLabel.setLayoutData(data);

		projectNameField = new Text(projectNameArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		projectNameField.setText(defaultProjectName);
		projectNameField.selectAll();
		projectNameField.setLayoutData(data);
		projectNameField.addListener(SWT.Modify, nameFieldModifyListener);

	}

	/**
	 * Create the group on the page related to the domain name and any
	 * other domain configuration.
	 * 
	 * @param parent
	 * 		The container of the domain group.
	 */
	private final void createDomainNameGroup(Composite parent) {
		Group domainNameArea = new Group(parent, SWT.NONE);
		domainNameArea.setText(Messages.ZDLNewProjectMainPage__Domain_Configuration_group_label);

		// layout object
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		domainNameArea.setLayout(layout);
		domainNameArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// contents of the group
		Label domainNameLabel = new Label(domainNameArea, SWT.NONE);
		domainNameLabel.setText(Messages.ZDLNewProjectMainPage__Domain_Name_label);
		GridData data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		domainNameLabel.setLayoutData(data);

		domainNameField = new Text(domainNameArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		data.widthHint = TEXT_FIELD_WIDTH;
		data.grabExcessHorizontalSpace = true;
		domainNameField.setText(defaultDomainName);
		domainNameField.selectAll();
		domainNameField.setLayoutData(data);
		domainNameField.addListener(SWT.Modify, domainNameFieldModifyListener);

		Label nsURILabel = new Label(domainNameArea, SWT.NONE);
		nsURILabel.setText(Messages.ZDLNewProjectMainPage__Namespace_URI_label);
		data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		nsURILabel.setLayoutData(data);

		nsURIField = new Text(domainNameArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		data.grabExcessHorizontalSpace = true;
		nsURIField.setText(defaultNSURI);
		nsURIField.selectAll();
		nsURIField.setEnabled(false);
		nsURIField.setLayoutData(data);

		nsUsesDefault = new Button(domainNameArea, SWT.CHECK);
		nsUsesDefault.setText(Messages.ZDLNewProjectMainPage__Use_Default_Checkbox_label);
		nsUsesDefault.setSelection(true);
		data = new GridData();
		data.widthHint = CHECK_BOX_WIDTH;
		nsUsesDefault.setLayoutData(data);
		nsUsesDefault.addSelectionListener(nsURIDefaultModifyListener);

	}

	/**
	 * Create the group on the page related to the model resources
	 * 
	 * @param parent
	 * 		The container of the model resources group.
	 */
	private final void createResourceGroup(Composite parent) {
		Group resourceArea = new Group(parent, SWT.NONE);
		resourceArea.setText(Messages.ZDLNewProjectMainPage__Resource_Location_group_label);

		// layout object
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		resourceArea.setLayout(layout);
		resourceArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// contents of the group
		Label modelResourceLabel = new Label(resourceArea, SWT.NONE);
		modelResourceLabel.setText(Messages.ZDLNewProjectMainPage__ZDL_Resource_label);
		GridData data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		modelResourceLabel.setLayoutData(data);

		modelNameField = new Text(resourceArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		data.grabExcessHorizontalSpace = true;
		modelNameField.setText(defaultModelName);
		modelNameField.setEnabled(false);
		modelNameField.selectAll();
		modelNameField.setLayoutData(data);
		modelNameField.addListener(SWT.Modify, nameFieldModifyListener);

		modelNameUsesDefault = new Button(resourceArea, SWT.CHECK);
		modelNameUsesDefault.setText(Messages.ZDLNewProjectMainPage__Use_Default_Checkbox_label);
		modelNameUsesDefault.setSelection(true);
		data = new GridData();
		data.widthHint = CHECK_BOX_WIDTH;
		modelNameUsesDefault.setLayoutData(data);
		modelNameUsesDefault
				.addSelectionListener(modelNameDefaultModifyListener);

		Label umlResourceLabel = new Label(resourceArea, SWT.NONE);
		umlResourceLabel.setText(Messages.ZDLNewProjectMainPage__UML_Resource_label);
		data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		umlResourceLabel.setLayoutData(data);

		umlModelNameField = new Text(resourceArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		data.grabExcessHorizontalSpace = true;
		umlModelNameField.setText(defaultUMLModelName);
		umlModelNameField.setEnabled(false);
		umlModelNameField.selectAll();
		umlModelNameField.setLayoutData(data);

		umlModelNameUsesDefault = new Button(resourceArea, SWT.CHECK);
		umlModelNameUsesDefault.setText(Messages.ZDLNewProjectMainPage__Use_Default_Checkbox_label);
		umlModelNameUsesDefault.setSelection(true);
		data = new GridData();
		data.widthHint = CHECK_BOX_WIDTH;
		umlModelNameUsesDefault.setLayoutData(data);
		umlModelNameUsesDefault
				.addSelectionListener(umlModelNameDefaultModifyListener);

		Label genResourceLabel = new Label(resourceArea, SWT.NONE);
		genResourceLabel.setText(Messages.ZDLNewProjectMainPage__Gen_Resource_label);
		data = new GridData();
		data.widthHint = NAME_LABEL_WIDTH;
		genResourceLabel.setLayoutData(data);

		genModelNameField = new Text(resourceArea, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = TEXT_FIELD_WIDTH;
		data.grabExcessHorizontalSpace = true;
		genModelNameField.setText(defaultGenModelName);
		genModelNameField.setEnabled(false);
		genModelNameField.selectAll();
		genModelNameField.setLayoutData(data);

		genModelNameUsesDefault = new Button(resourceArea, SWT.CHECK);
		genModelNameUsesDefault.setText(Messages.ZDLNewProjectMainPage__Use_Default_Checkbox_label);
		genModelNameUsesDefault.setSelection(true);
		data = new GridData();
		data.widthHint = CHECK_BOX_WIDTH;
		genModelNameUsesDefault.setLayoutData(data);
		genModelNameUsesDefault
				.addSelectionListener(genModelNameDefaultModifyListener);
	}

	/**
	 * Retrieves the project name that was specified on the page.
	 * 
	 * @return
	 * 		The project name that was entered by the user.
	 */
	public String getProjectName() {
		if (this.projectNameField == null) {
			return null;
		}

		return projectNameField.getText();
	}

	/**
	 * Retrieves the domain name that was specified on the page.
	 * 
	 * @return
	 * 		The domain name that was entered by the user.
	 */
	public String getDomainName() {
		if (this.domainNameField == null) {
			return null;
		}

		return domainNameField.getText();
	}

	/**
	 * Retrieves the model resource name that was specified on the page.
	 * 
	 * @return
	 * 		The model resource name that was entered by the user.
	 */
	public String getModelName() {
		if (this.modelNameField == null) {
			return null;
		}

		return modelNameField.getText();
	}

	/**
	 * Retrieves the gen model name that was specified on the page.
	 * 
	 * @return
	 * 		The gen model name that was entered by the user.
	 */
	public String getGenModelName() {
		if (this.genModelNameField == null) {
			return null;
		}

		return genModelNameField.getText();
	}

	/**
	 * Retrieves the UML model name that was specified on the page.
	 * 
	 * @return
	 * 		The UML model name that was entered by the user.
	 */
	public String getUMLModelName() {
		if (this.umlModelNameField == null) {
			return null;
		}

		return umlModelNameField.getText();
	}

	/**
	 * Retrieves the organization that was specified on the page.
	 * 
	 * @return
	 * 		The organization that was entered by the user.
	 */
	public String getOrganization() {
		if (this.organizationNameField == null) {
			return null;
		}

		return organizationNameField.getText();
	}

	/**
	 * Retrieves the namespace URI that was specified on the page.
	 * 
	 * @return
	 * 		The namespace URI that was entered by the user.
	 */
	public String getNSURI() {
		if (this.nsURIField == null) {
			return null;
		}

		return nsURIField.getText();
	}

	/**
	 * Validate the model name
	 */
	private void validateName() {
		if (modelNameField.getText().equals("")) { //$NON-NLS-1$
			setPageComplete(false);
			return;
		}

		setPageComplete(true);
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
