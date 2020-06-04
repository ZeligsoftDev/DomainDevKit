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

import java.util.Set;

import org.eclipse.core.runtime.IPath;

/**
 * The information that is necessary for a project.
 * 
 * 
 * @author Toby McClean (tmcclean)
 *
 */
public interface IProjectInfo {
	void setProjectName(String name);
	String getProjectName();
	
	IPath getLocation();
	void setLocation(IPath path);
	
	String[] getNatures();
	void setNatures(String[] natures);
	
	Set<String> getBuilders();
	void setBuilders(Set<String> builders);
	
	Set<String> getRequiredBundles();
	void setRequiredBundles(Set<String> bundles);
	
	Set<String> getImportedPackages();
	void setImportedPackages(Set<String> importedPackages);
	
	boolean deleteIfExists();
	void setDeleteIfExists(boolean delete);
	
}
