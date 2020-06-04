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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IPath;

/**
 * Provides an implementation of the IProjectInfo interface,
 * which provides sufficient information to create a project.
 * 
 * @author Toby McClean (tmcclean)
 *
 */
public class DefaultProjectInfo implements IProjectInfo {
	private Set<String> builders = new HashSet<String>();
	private Set<String> importedPackages = new HashSet<String>();
	private IPath location =  null;
	private String[] natures = new String[0];
	private String projectName;
	private Set<String> requiredBundles = new HashSet<String>();
	private boolean deleteIfExists = false;
	
	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#getBuilders()
	 */
	@Override
	public Set<String> getBuilders() {
		return builders;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#getImportedPackages()
	 */
	@Override
	public Set<String> getImportedPackages() {
		return importedPackages;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#getLocation()
	 */
	@Override
	public IPath getLocation() {
		return location;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#getNatures()
	 */
	@Override
	public String[] getNatures() {
		return natures;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#getProjectName()
	 */
	@Override
	public String getProjectName() {
		return projectName;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#getRequiredBundles()
	 */
	@Override
	public Set<String> getRequiredBundles() {
		return requiredBundles;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setBuilders(java.util.Set)
	 */
	@Override
	public void setBuilders(Set<String> builders) {
		this.builders = builders;

	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setImportedPackages(java.util.Set)
	 */
	@Override
	public void setImportedPackages(Set<String> importedPackages) {
		this.importedPackages = importedPackages;

	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setLocation(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public void setLocation(IPath path) {
		location = path;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setNatures(java.lang.String[])
	 */
	@Override
	public void setNatures(String[] natures) {
		this.natures = natures;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setProjectName(java.lang.String)
	 */
	@Override
	public void setProjectName(String name) {
		this.projectName = name;
	}

	/* (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setRequiredBundles(java.util.Set)
	 */
	@Override
	public void setRequiredBundles(Set<String> bundles) {
		this.requiredBundles = bundles;
	}

	/*
	 * (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#deleteIfExists()
	 */
	@Override
	public boolean deleteIfExists() {
		return deleteIfExists;
	}

	/*
	 * (non-Javadoc)
	 * @see com.zeligsoft.ddk.ui.creators.IProjectInfo#setDeleteIfExists(boolean)
	 */
	@Override
	public void setDeleteIfExists(boolean delete) {
		deleteIfExists = delete;
	}

}
