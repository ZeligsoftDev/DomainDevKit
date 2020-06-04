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

import com.zeligsoft.domain.zml.api.ZML_Component.Port;
import com.zeligsoft.domain.zml.api.ZML_Core.impl.TypedElementImplementation;

import com.zeligsoft.domain.zml.api.ZML_Component.WiringKind;
import com.zeligsoft.domain.zml.api.ZML_Component.PortTypeable;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public abstract class PortImplementation extends TypedElementImplementation
		implements Port {
	protected WiringKind _wiring;
	protected PortTypeable _porttype;

	public PortImplementation(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public WiringKind getWiring() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZMLMM::ZML_Component::Port", "wiring");

		if (_wiring == null) {
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_wiring = WiringKind
						.create((org.eclipse.emf.ecore.EObject) rawValue);
			}
		}
		return _wiring;
	}

	@Override
	public void setWiring(WiringKind val) {
		ZDLUtil.setValue(element, "ZMLMM::ZML_Component::Port", "wiring",
				val.eObject(element));
	}

	@Override
	public Boolean getIsExternal() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZMLMM::ZML_Component::Port", "isExternal");
		return (Boolean) rawValue;
	}

	@Override
	public void setIsExternal(Boolean val) {
		ZDLUtil.setValue(element, "ZMLMM::ZML_Component::Port", "isExternal",
				val);
	}

	@Override
	public String getQualifiedName() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZMLMM::ZML_Core::NamedElement", "qualifiedName");
		return (String) rawValue;
	}

	@Override
	public String getName() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZMLMM::ZML_Core::NamedElement", "name");
		return (String) rawValue;
	}

	@Override
	public void setName(String val) {
		ZDLUtil.setValue(element, "ZMLMM::ZML_Core::NamedElement", "name", val);
	}

	@Override
	public PortTypeable getPorttype() {
		if (_porttype == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(), "ZMLMM::ZML_Component::Port",
							"porttype");
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_porttype = ZDLFactoryRegistry.INSTANCE.create(
						(org.eclipse.emf.ecore.EObject) rawValue,
						PortTypeable.class);
			}
		}
		return _porttype;
	}

	@Override
	public void setPorttype(PortTypeable val) {
		ZDLUtil.setValue(element, "ZMLMM::ZML_Component::Port", "porttype",
				val.eObject());
	}

	@Override
	public org.eclipse.uml2.uml.Port asPort() {
		return (org.eclipse.uml2.uml.Port) eObject();
	}

	@Override
	public org.eclipse.uml2.uml.NamedElement asNamedElement() {
		return (org.eclipse.uml2.uml.NamedElement) eObject();
	}
}
