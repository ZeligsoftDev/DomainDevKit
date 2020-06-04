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
package com.zeligsoft.base.zdl.staticapi.Validation.impl;

import com.zeligsoft.base.zdl.staticapi.Constructs.DomainConcept;
import com.zeligsoft.base.zdl.staticapi.Validation.DomainCreateLinkConstraint;
import com.zeligsoft.base.zdl.staticapi.util.ZDLFactoryRegistry;
import com.zeligsoft.base.zdl.util.ZDLUtil;

public class DomainCreateLinkConstraintImpl extends DomainLinkConstraintImpl
		implements DomainCreateLinkConstraint {
	protected DomainConcept _createsConcept;

	public DomainCreateLinkConstraintImpl(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public DomainConcept getCreatesConcept() {
		if (_createsConcept == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(),
							"ZDL::Validation::DomainCreateLinkConstraint",
							"createsConcept");
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_createsConcept = ZDLFactoryRegistry.INSTANCE.create(
						(org.eclipse.emf.ecore.EObject) rawValue,
						DomainConcept.class);
			}
		}
		return _createsConcept;
	}

	@Override
	public void setCreatesConcept(DomainConcept val) {
		ZDLUtil.setValue(element,
				"ZDL::Validation::DomainCreateLinkConstraint",
				"createsConcept", val.eObject());
	}

}
