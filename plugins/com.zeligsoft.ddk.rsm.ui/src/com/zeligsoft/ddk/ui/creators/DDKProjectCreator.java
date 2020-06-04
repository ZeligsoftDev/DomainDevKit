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
package com.zeligsoft.ddk.ui.creators;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.uml2.uml.Package;

import com.zeligsoft.ddk.rsm.ui.Activator;
import com.zeligsoft.ddk.rsm.ui.l10n.Messages;
import com.zeligsoft.ddk.zdl2zdlgen.ui.wizards.ZDLImporter;
import com.zeligsoft.ddk.zdl2zdlgen.util.QuickUMLExporterUtil;

/**
 * @author Toby McClean (tmcclean)
 * 
 */
public class DDKProjectCreator extends DefaultProjectCreator {

	private static final String PDE_SCHEMA_BUILDER_ID = "org.eclipse.pde.SchemaBuilder"; //$NON-NLS-1$

	private static final String PDE_MANIFEST_BUILDER_ID = "org.eclipse.pde.ManifestBuilder"; //$NON-NLS-1$

	private static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$

	protected static final String JAVA_NATURE_ID = JavaCore.NATURE_ID;

	protected static final String PLUGIN_NATURE_ID = "org.eclipse.pde.PluginNature"; //$NON-NLS-1$

	protected static final String ZDL_MODEL_TEMPLATE_PATH = PATH_SEPARATOR
			+ Activator.PLUGIN_ID + "/modelTemplates/NewDomain.emx"; //$NON-NLS-1$

	private static final URI ZDL_MODEL_TEMPLATE_URI = URI
			.createPlatformPluginURI(ZDL_MODEL_TEMPLATE_PATH, true);

	/**
	 * Initialize me.
	 */
	public DDKProjectCreator() {
		super();
	}

	/**
	 * Initialize me.
	 * 
	 * @param rule
	 */
	public DDKProjectCreator(ISchedulingRule rule) {
		super(rule);
	}

	/**
	 * Run the process of creating the project
	 */
	@Override
	protected void execute(IProgressMonitor theMonitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		IProgressMonitor monitor = (theMonitor == null) ? new NullProgressMonitor()
				: theMonitor;

		monitor.beginTask(
				Messages.DDKProjectCreator__Monitor_Creating_Project_Message
						+ getProjectInfo().getProjectName(), 1);

		createDDKProject(monitor);
	}

	/**
	 * The workflow to create a new project for the DDK, which includes creating
	 * the domain model, the UML model and the ZDLGen model.
	 * 
	 * @param monitor
	 *            The monitor to track the progress of the workflow.
	 * @return The project that is created by the workflow.
	 */
	private IProject createDDKProject(IProgressMonitor monitor) {
		IProject result = null;
		Set<String> builders = new HashSet<String>(getProjectInfo().getBuilders());
		builders.add(PDE_MANIFEST_BUILDER_ID);
		builders.add(PDE_SCHEMA_BUILDER_ID);
		builders.add(JavaCore.BUILDER_ID);
		getProjectInfo().setBuilders(builders);
		
		List<String> natures = new ArrayList<String>(Arrays.asList(getProjectInfo().getNatures()));
		if(!natures.contains(JAVA_NATURE_ID)) {
			natures.add(JAVA_NATURE_ID);
		}
		if(!natures.contains(PLUGIN_NATURE_ID)) {
			natures.add(PLUGIN_NATURE_ID);
		}
		getProjectInfo().setNatures(natures.toArray(new String[natures.size()]));
		
		try {
			result = createProject(getDDKProjectInfo(),	monitor, null);

			monitor.beginTask(Messages.DDKProjectCreator__Monitor_Populating_Project_Message, 8);
			
			// get the template to copy
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Loading_ZDL_Template_Message);
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource template = resourceSet.getResource(ZDL_MODEL_TEMPLATE_URI,
					true);
			monitor.worked(1);

			// save the template to the current project
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Copying_ZDL_Template_Message);
			String modelPath;
			modelPath = getDDKProjectInfo().getProjectName() + PATH_SEPARATOR
					+ getDDKProjectInfo().getModelResource();
			URI modelURI = URI.createPlatformResourceURI(modelPath, true);
			Resource modelResource = resourceSet.createResource(modelURI);
			modelResource.getContents().addAll(template.getContents());
			Package model = (Package) modelResource.getContents().get(0);
			model.setName(getDDKProjectInfo().getDomainName());
			monitor.worked(1);
			
			// save the model
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Saving_ZDL_Message);
			try {
				modelResource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				Activator
						.getDefault()
						.error(
								Messages.DDKProjectCreator__Resource_Save_Failed_Log_Message,
								e);
				return result;
			}
			monitor.worked(1);

			// export to UML resource
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Exporting_UML_Message);
			modelPath = getDDKProjectInfo().getProjectName() + PATH_SEPARATOR
					+ getDDKProjectInfo().getUMLModelResource();
			URI umlModelURI = URI.createPlatformResourceURI(modelPath, true);

			QuickUMLExporterUtil umlExporter = new QuickUMLExporterUtil(
					modelURI, umlModelURI);
			umlExporter.export(monitor);
			monitor.worked(1);

			// create all of the ZDL gen stuff
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Creating_ZDLGen_Message);
			modelPath = getDDKProjectInfo().getProjectName() + PATH_SEPARATOR
					+ getDDKProjectInfo().getGenModelResource();
			URI genModelURI = URI.createPlatformResourceURI(modelPath, true);
			Resource genModelResource = resourceSet.createResource(genModelURI);
			monitor.worked(1);

			monitor.subTask(Messages.DDKProjectCreator__Monitor_Saving_ZDLGen_Message);
			try {
				genModelResource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				Activator
						.getDefault()
						.error(
								Messages.DDKProjectCreator__Resource_Save_Failed_Log_Message,
								e);
			}
			monitor.worked(1);

			// clean up the resource set
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Cleaning_ResourceSet_Message);
			for (Resource res : resourceSet.getResources()) {
				res.unload();
			}
			monitor.worked(1);

			// import the ZDL model into the ZDLGen
			monitor.subTask(Messages.DDKProjectCreator__Monitor_Importing_UML_Message);
			ZDLImporter importer = new ZDLImporter();
			try {
				importer.load(umlModelURI);
				importer.convert(genModelURI, null, monitor);
			} catch (IOException e) {
				Activator
						.getDefault()
						.error(
								Messages.DDKProjectCreator__Failed_to_Load_ZDL_into_ZDLGen_Message,
								e);
				return result;
			} catch (InvocationTargetException e) {
				Activator
						.getDefault()
						.error(
								Messages.DDKProjectCreator__Failed_to_Load_ZDL_into_ZDLGen_Message,
								e);
				return result;
			} catch (InterruptedException e) {
				Activator
						.getDefault()
						.error(
								Messages.DDKProjectCreator__Failed_to_Load_ZDL_into_ZDLGen_Message,
								e);
				return result;
			}
			monitor.worked(1);
		} catch (CoreException e) {
			Activator
				.getDefault()
				.error(Messages.DDKProjectCreator__Failed_to_Create_Project_Error_Message, e);
		}
		return result;
	}

	/**
	 * Get the information that is being used to create the project.
	 * 
	 * @return
	 * 		The object containing the information being used to create the 
	 * 		project.
	 */
	protected DDKProjectInfo getDDKProjectInfo() {
		return (DDKProjectInfo) getProjectInfo();
	}

	/**
	 * A class that contains all of the information necessary for the project
	 * creator to create the project.
	 * 
	 * @author Toby McClean (tmcclean)
	 * 
	 */
	public static class DDKProjectInfo 
		extends DefaultProjectInfo {

		private String domainName;
		private String modelResource;
		private String genModelResource;
		private String umlModelResource;
		private String zdlnsuri;

		/**
		 * Set the name of the domain that will be created in the project.
		 * 
		 * @param domainName
		 *            The name of the new domain that is being created.
		 */
		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}

		/**
		 * Set the path including file extension of the model that will contain
		 * the domain model. The path is relative to the project.
		 * 
		 * @param modelResource
		 *            A path relative to the project of the resource to store
		 *            the domain model in.
		 */
		public void setZDLModelResource(String modelResource) {
			this.modelResource = modelResource;
		}

		/**
		 * Set the path including file extension of the model that will contain
		 * the ZDLGen model. The path is relative to the project.
		 * 
		 * @param genModelResource
		 *            A path relative to the project of the resource to store
		 *            the ZDLGen model in.
		 */
		public void setZDLGenModelResource(String genModelResource) {
			this.genModelResource = genModelResource;
		}

		/**
		 * Set the namespace URI of the new domain model being created.
		 * 
		 * @param zdlnsuri
		 *            The namespace URI of the domain that will be created in
		 *            the project.
		 */
		public void setZDL_NS_URI(String zdlnsuri) {
			this.zdlnsuri = zdlnsuri;
		}

		/**
		 * The name of the domain that will be created in the project.
		 * 
		 * @return the domainName The name of the domain that will be created in
		 *         the project.
		 */
		public String getDomainName() {
			return domainName;
		}

		/**
		 * The path including file extension of the model that will contain the
		 * domain model. The path is relative to the project.
		 * 
		 * @return the modelResource
		 */
		public String getModelResource() {
			return modelResource;
		}

		/**
		 * The path including file extension of the model that will contain the
		 * ZDLGen model. The path is relative to the project.
		 * 
		 * @return the genModelResource
		 */
		public String getGenModelResource() {
			return genModelResource;
		}

		/**
		 * The path including file extension of the model that will contain the
		 * UML version of the domain model. The path is relative to the project.
		 * 
		 * @return
		 */
		public String getUMLModelResource() {
			return umlModelResource;
		}

		/**
		 * Set the namespace URI of the new domain model being created.
		 * 
		 * @return the zdlnsuri
		 */
		public String getZDL_NS_URI() {
			return zdlnsuri;
		}

		/**
		 * Set the path including file extension of the model that will contain
		 * the UML version of the domain model. The path is relative to the
		 * project.
		 * 
		 * @param modelName
		 */
		public void setUMLModelResource(String modelName) {
			umlModelResource = modelName;
		}
	}
}
