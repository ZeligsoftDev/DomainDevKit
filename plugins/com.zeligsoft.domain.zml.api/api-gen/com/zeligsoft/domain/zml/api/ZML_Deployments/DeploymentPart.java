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
package com.zeligsoft.domain.zml.api.ZML_Deployments;

import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;
import com.zeligsoft.domain.zml.api.ZML_Configurations.Configuration;
import com.zeligsoft.domain.zml.api.ZML_Core.NamedElement;

public interface DeploymentPart extends NamedElement {
	java.util.List<DeploymentPart> getNestedPart();

	void addNestedPart(DeploymentPart val);

	Configuration getConfiguration();

	void setConfiguration(Configuration val);

	NamedElement getModelElement();

	void setModelElement(NamedElement val);

	DeploymentSpecification getSpecification();

	void setSpecification(DeploymentSpecification val);

	org.eclipse.uml2.uml.Property asProperty();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of DeploymentPart
	 */
	static final TypeSelectPredicate<DeploymentPart> type = new TypeSelectPredicate<DeploymentPart>(
			"ZMLMM::ZML_Deployments::DeploymentPart", //$NON-NLS-1$
			DeploymentPart.class);
}
