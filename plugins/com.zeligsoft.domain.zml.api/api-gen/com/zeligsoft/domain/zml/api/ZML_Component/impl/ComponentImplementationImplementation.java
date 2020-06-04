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
package com.zeligsoft.domain.zml.api.ZML_Component.impl;

import com.zeligsoft.base.zdl.staticapi.util.ZDLFactoryRegistry;
import com.zeligsoft.base.zdl.staticapi.internal.core.ZObjectImpl;

import com.zeligsoft.domain.zml.api.ZML_Component.ComponentImplementation;

import com.zeligsoft.domain.zml.api.ZML_Component.Implementation;
import com.zeligsoft.domain.zml.api.ZML_Component.StructuralRealization;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public class ComponentImplementationImplementation extends ZObjectImpl
		implements ComponentImplementation {
	protected Implementation _implementation;
	protected StructuralRealization _structuralRealization;

	public ComponentImplementationImplementation(
			org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public Implementation getImplementation() {
		if (_implementation == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(),
							"ZMLMM::ZML_Component::ComponentImplementation",
							"implementation");
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_implementation = ZDLFactoryRegistry.INSTANCE.create(
						(org.eclipse.emf.ecore.EObject) rawValue,
						Implementation.class);
			}
		}
		return _implementation;
	}

	@Override
	public void setImplementation(Implementation val) {
		ZDLUtil.setValue(element,
				"ZMLMM::ZML_Component::ComponentImplementation",
				"implementation", val.eObject());
	}

	@Override
	public StructuralRealization getStructuralRealization() {
		if (_structuralRealization == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(),
							"ZMLMM::ZML_Component::ComponentImplementation",
							"structuralRealization");
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_structuralRealization = ZDLFactoryRegistry.INSTANCE.create(
						(org.eclipse.emf.ecore.EObject) rawValue,
						StructuralRealization.class);
			}
		}
		return _structuralRealization;
	}

	@Override
	public void setStructuralRealization(StructuralRealization val) {
		ZDLUtil.setValue(element,
				"ZMLMM::ZML_Component::ComponentImplementation",
				"structuralRealization", val.eObject());
	}

	@Override
	public org.eclipse.uml2.uml.Manifestation asManifestation() {
		return (org.eclipse.uml2.uml.Manifestation) eObject();
	}
}
