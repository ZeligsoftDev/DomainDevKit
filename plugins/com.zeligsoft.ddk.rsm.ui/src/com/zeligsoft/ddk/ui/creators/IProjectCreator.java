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

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The contract that must be satisfied by a class that creates projects of a
 * specific type.
 * 
 * @author Toby McClean (tmcclean)
 * 
 */
public interface IProjectCreator {

	/**
	 * Create the project.
	 * 
	 * @param monitor
	 *            The element that will monitor progress during the creation of
	 *            the project.
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException;

	/**
	 * Provide the information needed to create the project.
	 * 
	 * @param projectInfo
	 *            The object containing the information required to create the
	 *            project.
	 */
	void setProjectInfo(IProjectInfo projectInfo);

}
