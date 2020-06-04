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
package com.zeligsoft.domain.zml.api.ZML_Component;

import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;
import com.zeligsoft.domain.zml.api.ZML_Core.Type;

public interface PortType extends Type, PortTypeable {
	PortType getInverse();

	void setInverse(PortType val);

	java.util.List<InterfaceRealization> getProvidedInterfaces();

	void addProvidedInterfaces(InterfaceRealization val);

	<T extends InterfaceRealization> T addProvidedInterfaces(
			Class<T> typeToCreate, String concept);

	InterfaceRealization addProvidedInterfaces();

	org.eclipse.uml2.uml.Class asClass();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of PortType
	 */
	static final TypeSelectPredicate<PortType> type = new TypeSelectPredicate<PortType>(
			"ZMLMM::ZML_Component::PortType", //$NON-NLS-1$
			PortType.class);
}
