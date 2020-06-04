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
package com.zeligsoft.domain.zml.api.ZML_Configurations.impl;

import com.zeligsoft.domain.zml.api.ZML_Configurations.BuildConfiguration;
import com.zeligsoft.domain.zml.api.ZML_Configurations.impl.ConfigurationImplementation;

public class BuildConfigurationImplementation extends
		ConfigurationImplementation implements BuildConfiguration {
	public BuildConfigurationImplementation(
			org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

}
