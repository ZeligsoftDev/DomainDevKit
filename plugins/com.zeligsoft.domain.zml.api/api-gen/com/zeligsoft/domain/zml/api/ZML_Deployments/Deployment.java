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
import com.zeligsoft.domain.zml.api.ZML_Core.NamedElement;

public interface Deployment extends NamedElement {
	java.util.List<DeploymentPart> getPart();

	void addPart(DeploymentPart val);

	<T extends DeploymentPart> T addPart(Class<T> typeToCreate, String concept);

	DeploymentPart addPart();

	java.util.List<Allocation> getAllocation();

	void addAllocation(Allocation val);

	<T extends Allocation> T addAllocation(Class<T> typeToCreate, String concept);

	Allocation addAllocation();

	org.eclipse.uml2.uml.Component asComponent();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of Deployment
	 */
	static final TypeSelectPredicate<Deployment> type = new TypeSelectPredicate<Deployment>(
			"ZMLMM::ZML_Deployments::Deployment", //$NON-NLS-1$
			Deployment.class);
}
