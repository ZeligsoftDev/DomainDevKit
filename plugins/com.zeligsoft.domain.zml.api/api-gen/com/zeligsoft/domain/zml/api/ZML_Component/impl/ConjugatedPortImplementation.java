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

import com.zeligsoft.base.zdl.staticapi.internal.core.ZObjectImpl;

import com.zeligsoft.domain.zml.api.ZML_Component.ConjugatedPort;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public class ConjugatedPortImplementation extends ZObjectImpl implements
		ConjugatedPort {
	public ConjugatedPortImplementation(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public Boolean getIsConjugated() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZMLMM::ZML_Component::ConjugatedPort",
				"isConjugated");
		return (Boolean) rawValue;
	}

	@Override
	public void setIsConjugated(Boolean val) {
		ZDLUtil.setValue(element, "ZMLMM::ZML_Component::ConjugatedPort",
				"isConjugated", val);
	}

}
