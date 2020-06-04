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
package com.zeligsoft.domain.zml.api.ZML_HwPlatform;

import com.zeligsoft.base.zdl.staticapi.core.ZObject;
import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;

public interface HwComponent extends ZObject {
	java.util.List<HwPort> getPort();

	void addPort(HwPort val);

	<T extends HwPort> T addPort(Class<T> typeToCreate, String concept);

	HwPort addPort();

	java.util.List<HwPart> getPart();

	void addPart(HwPart val);

	<T extends HwPart> T addPart(Class<T> typeToCreate, String concept);

	HwPart addPart();

	java.util.List<HwConnector> getConnector();

	void addConnector(HwConnector val);

	<T extends HwConnector> T addConnector(Class<T> typeToCreate, String concept);

	HwConnector addConnector();

	org.eclipse.uml2.uml.Component asComponent();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of HwComponent
	 */
	static final TypeSelectPredicate<HwComponent> type = new TypeSelectPredicate<HwComponent>(
			"ZMLMM::ZML_HwPlatform::HwComponent", //$NON-NLS-1$
			HwComponent.class);
}
