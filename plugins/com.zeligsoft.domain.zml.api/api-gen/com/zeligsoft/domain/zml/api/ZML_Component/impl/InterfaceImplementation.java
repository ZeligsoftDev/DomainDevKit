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

import com.zeligsoft.domain.zml.api.ZML_Component.Interface;
import com.zeligsoft.domain.zml.api.ZML_Component.impl.PortTypeableImplementation;

import com.zeligsoft.domain.zml.api.ZML_Component.Operation;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public abstract class InterfaceImplementation extends
		PortTypeableImplementation implements Interface {
	protected java.util.List<Operation> _operation;

	public InterfaceImplementation(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public java.util.List<Operation> getOperation() {
		if (_operation == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(), "ZMLMM::ZML_Component::Interface",
							"operation");
			_operation = new java.util.ArrayList<Operation>();
			@SuppressWarnings("unchecked")
			final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
			for (Object next : rawList) {
				if (next instanceof org.eclipse.emf.ecore.EObject) {
					Operation nextWrapper = ZDLFactoryRegistry.INSTANCE.create(
							(org.eclipse.emf.ecore.EObject) next,
							Operation.class);
					_operation.add(nextWrapper);
				}
			}
		}
		return _operation;
	}

	@Override
	public void addOperation(Operation val) {
		// make sure the operation list is created
		getOperation();

		final Object rawValue = ZDLUtil.getValue(element,
				"ZMLMM::ZML_Component::Interface", "operation");
		@SuppressWarnings("unchecked")
		final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
		rawList.add(val.eObject());
		if (_operation != null) {
			_operation.add(val);
		}
	}

	@Override
	public <T extends Operation> T addOperation(Class<T> typeToCreate,
			String concept) {
		// make sure the operation list is created
		getOperation();
		org.eclipse.emf.ecore.EObject newConcept = ZDLUtil.createZDLConcept(
				element, "ZMLMM::ZML_Component::Interface", "operation",
				concept);
		T element = ZDLFactoryRegistry.INSTANCE.create(
				newConcept, typeToCreate);
		if (_operation != null) {
			_operation.add(element);
		}
		return element;
	}

	@Override
	public Operation addOperation() {
		// make sure the operation list is created
		getOperation();
		org.eclipse.emf.ecore.EObject newConcept = ZDLUtil.createZDLConcept(
				element, "ZMLMM::ZML_Component::Interface", "operation",
				"ZMLMM::ZML_Component::Operation");
		Operation element = ZDLFactoryRegistry.INSTANCE.create(
				newConcept, Operation.class);
		if (_operation != null) {
			_operation.add(element);
		}
		return element;
	}

	@Override
	public org.eclipse.uml2.uml.Interface asInterface() {
		return (org.eclipse.uml2.uml.Interface) eObject();
	}
}
