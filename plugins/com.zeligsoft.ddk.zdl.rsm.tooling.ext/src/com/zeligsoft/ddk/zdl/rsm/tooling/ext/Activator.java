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
package com.zeligsoft.ddk.zdl.rsm.tooling.ext;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.zeligsoft.ddk.zdl.rsm.tooling.ext.types.ConfigureDomainConstraintAdvice;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator
		extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID = "com.zeligsoft.ddk.zdl.rsm.tooling.ext"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
		super();
	}

	@Override
	public void start(BundleContext context)
			throws Exception {
		super.start(context);
		plugin = this;

		UMLModeler.getEditingDomain().addResourceSetListener(
			ConfigureDomainConstraintAdvice.INSTANCE);
	}

	@Override
	public void stop(BundleContext context)
			throws Exception {
		UMLModeler.getEditingDomain().removeResourceSetListener(
			ConfigureDomainConstraintAdvice.INSTANCE);

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
