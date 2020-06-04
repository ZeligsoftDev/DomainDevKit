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
package com.zeligsoft.domain.zml.api.ZML_Configurations.impl;

import com.zeligsoft.base.zdl.staticapi.internal.core.ZObjectImpl;

import com.zeligsoft.domain.zml.api.ZML_Configurations.ConfigurationSlot;

import com.zeligsoft.domain.zml.api.ZML_Configurations.ConfigurationSlotKind;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public class ConfigurationSlotImplementation extends ZObjectImpl implements
		ConfigurationSlot {
	protected ConfigurationSlotKind _slotKind;

	public ConfigurationSlotImplementation(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public ConfigurationSlotKind getSlotKind() {
		final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil.getValue(
				eObject(), "ZMLMM::ZML_Configurations::ConfigurationSlot",
				"slotKind");

		if (_slotKind == null) {
			if (rawValue instanceof org.eclipse.emf.ecore.EObject) {
				_slotKind = ConfigurationSlotKind
						.create((org.eclipse.emf.ecore.EObject) rawValue);
			}
		}
		return _slotKind;
	}

	@Override
	public void setSlotKind(ConfigurationSlotKind val) {
		ZDLUtil.setValue(element,
				"ZMLMM::ZML_Configurations::ConfigurationSlot", "slotKind",
				val.eObject(element));
	}

	@Override
	public org.eclipse.uml2.uml.Slot asSlot() {
		return (org.eclipse.uml2.uml.Slot) eObject();
	}
}
