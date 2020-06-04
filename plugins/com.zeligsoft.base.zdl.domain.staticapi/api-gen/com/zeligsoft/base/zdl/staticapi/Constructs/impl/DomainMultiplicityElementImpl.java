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
package com.zeligsoft.base.zdl.staticapi.Constructs.impl;

import com.zeligsoft.base.zdl.staticapi.Constructs.DomainMultiplicityElement;
import com.zeligsoft.base.zdl.staticapi.internal.core.ZObjectImpl;
import com.zeligsoft.base.zdl.util.ZDLUtil;

public abstract class DomainMultiplicityElementImpl extends ZObjectImpl
		implements DomainMultiplicityElement {
	public DomainMultiplicityElementImpl(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public Integer getUpper() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZDL::Constructs::DomainMultiplicityElement",
				"upper");
		return (Integer) rawValue;
	}

	@Override
	public void setUpper(Integer val) {
		ZDLUtil.setValue(element, "ZDL::Constructs::DomainMultiplicityElement",
				"upper", val);
	}

	@Override
	public Boolean getOrdered() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZDL::Constructs::DomainMultiplicityElement",
				"ordered");
		return (Boolean) rawValue;
	}

	@Override
	public void setOrdered(Boolean val) {
		ZDLUtil.setValue(element, "ZDL::Constructs::DomainMultiplicityElement",
				"ordered", val);
	}

	@Override
	public Integer getLower() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZDL::Constructs::DomainMultiplicityElement",
				"lower");
		return (Integer) rawValue;
	}

	@Override
	public void setLower(Integer val) {
		ZDLUtil.setValue(element, "ZDL::Constructs::DomainMultiplicityElement",
				"lower", val);
	}

	@Override
	public Boolean getUnique() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZDL::Constructs::DomainMultiplicityElement",
				"unique");
		return (Boolean) rawValue;
	}

	@Override
	public void setUnique(Boolean val) {
		ZDLUtil.setValue(element, "ZDL::Constructs::DomainMultiplicityElement",
				"unique", val);
	}

}
