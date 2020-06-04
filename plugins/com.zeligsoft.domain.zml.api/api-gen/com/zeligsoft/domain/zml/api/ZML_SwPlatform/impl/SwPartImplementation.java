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
package com.zeligsoft.domain.zml.api.ZML_SwPlatform.impl;

import com.zeligsoft.base.zdl.staticapi.util.ZDLFactoryRegistry;
import com.zeligsoft.base.zdl.staticapi.internal.core.ZObjectImpl;

import com.zeligsoft.domain.zml.api.ZML_SwPlatform.SwPart;

import com.zeligsoft.domain.zml.api.ZML_SwPlatform.SwComponent;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public class SwPartImplementation extends ZObjectImpl implements SwPart {
	protected SwComponent _definition;

	public SwPartImplementation(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public SwComponent getDefinition() {
		if (_definition == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(), "ZMLMM::ZML_SwPlatform::SwPart",
							"definition");
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_definition = ZDLFactoryRegistry.INSTANCE.create(
						(org.eclipse.emf.ecore.EObject) rawValue,
						SwComponent.class);
			}
		}
		return _definition;
	}

	@Override
	public void setDefinition(SwComponent val) {
		ZDLUtil.setValue(element, "ZMLMM::ZML_SwPlatform::SwPart",
				"definition", val.eObject());
	}

	@Override
	public org.eclipse.uml2.uml.Property asProperty() {
		return (org.eclipse.uml2.uml.Property) eObject();
	}
}
