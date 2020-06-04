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

import com.zeligsoft.base.zdl.staticapi.core.ZObject;
import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;

public interface Allocation extends ZObject {
	java.util.List<DeploymentPart> getDeployed();

	void addDeployed(DeploymentPart val);

	DeploymentPart getDeployedOn();

	void setDeployedOn(DeploymentPart val);

	org.eclipse.uml2.uml.Dependency asDependency();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of Allocation
	 */
	static final TypeSelectPredicate<Allocation> type = new TypeSelectPredicate<Allocation>(
			"ZMLMM::ZML_Deployments::Allocation", //$NON-NLS-1$
			Allocation.class);
}
