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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.zeligsoft.ddk.rsm.ui.l10n.Messages;

/**
 * Provides default implementations for a project creator.
 * 
 * @author Toby McClean (tmcclean)
 * 
 */
public class DefaultProjectCreator extends WorkspaceModifyOperation
		implements IProjectCreator {

	private IProjectInfo projectInfo;

	/**
	 * Initialize me.
	 */
	public DefaultProjectCreator() {
		super();
	}

	/**
	 * Initialize me with a scheduling rule.
	 * 
	 * @param rule
	 *            The scheduling rule to initialize me with.
	 */
	public DefaultProjectCreator(ISchedulingRule rule) {
		super(rule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core
	 * .runtime.IProgressMonitor)
	 */
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		createProject(getProjectInfo(), monitor, null);
	}

	/**
	 * A project creator builds the project using project info, this method
	 * provides access to that information.
	 * 
	 * @return the projectInfo The object with the information being used to
	 *         create the project, it may be null.
	 */
	protected IProjectInfo getProjectInfo() {
		return projectInfo;
	}

	/**
	 * @see #getProjectInfo()
	 * 
	 * @param projectInfo
	 *            the projectInfo that will be used to create the project
	 */
	@Override
	public void setProjectInfo(IProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	/**
	 * Provides the default logic for creating a new project from project
	 * information provided. It will create a project with the natures and
	 * builders specified in the project information at the location specified.
	 * If the location is null then it will create the project in the current
	 * workspace.
	 * 
	 * @param info
	 * 		The information to create the project from.
	 * @param monitor
	 * 		A progress monitor to track the creation of the project.
	 * @param shell
	 * 		The shell used during the creation of the project.
	 * @return
	 * 		The newly created project.
	 * @throws CoreException
	 */
	protected static IProject createProject(final IProjectInfo info,
			final IProgressMonitor monitor,
			final Shell shell) throws CoreException {
		
		monitor.beginTask(Messages.DefaultProjectCreator__Monitor_Creating_Project_Message + info.getProjectName(), 8);

		IProject ddkProject;
		try {
			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Check_Project_Exists_Message);

			final IWorkspace workspace = ResourcesPlugin.getWorkspace();
			ddkProject = workspace.getRoot().getProject(info.getProjectName());

			// clean up the old project if necessary
			if (ddkProject.exists()) {
				if (info.deleteIfExists()) {
					ddkProject.delete(true, true, new SubProgressMonitor(
							monitor, 1));
				} else {
					return null;
				}
			}
			monitor.worked(1);

			
			final IProjectDescription projectDescription = ResourcesPlugin
					.getWorkspace().newProjectDescription(info.getProjectName());
			
			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Setting_Project_Location_Message);
			projectDescription.setLocation(info.getLocation());
			monitor.worked(1);

			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Setting_Project_Natures_Message);
			projectDescription.setNatureIds(info.getNatures());
			monitor.worked(1);

			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Setting_Project_Builders_Message);
			ArrayList<ICommand> buildSpec = new ArrayList<ICommand>(info.getBuilders()
					.size());

			for (String builderId : info.getBuilders()) {
				final ICommand builder = projectDescription.newCommand();
				builder.setBuilderName(builderId);
				buildSpec.add(builder);
			}
			projectDescription.setBuildSpec(buildSpec
					.toArray(new ICommand[info.getBuilders().size()]));
			monitor.worked(1);
			
			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Creating_Physical_Project_Message + info.getProjectName());
			ddkProject.create(projectDescription, new SubProgressMonitor(
					monitor, 1));
			monitor.worked(1);

			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Opening_Project_Message + info.getProjectName());
			ddkProject.open(new SubProgressMonitor(monitor, 1));
			monitor.worked(1);
			
			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Creating_META_INF);
			IFolder pf = ddkProject.getFolder("META-INF"); //$NON-NLS-1$
			if(!pf.exists()) {
				pf.create(true, false, monitor);
			}
			monitor.worked(1);
			
			IFile manifest = pf.getFile("MANIFEST.MF"); //$NON-NLS-1$
			if(!manifest.exists()) {
				manifest.create(null, true, monitor);
			}

			monitor.subTask(Messages.DefaultProjectCreator__Monitor_Refreshing_Project_Message + info.getProjectName());
			ddkProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			monitor.worked(1);
			
		} finally {
			monitor.done();
		}

		return ddkProject;
	}

}
