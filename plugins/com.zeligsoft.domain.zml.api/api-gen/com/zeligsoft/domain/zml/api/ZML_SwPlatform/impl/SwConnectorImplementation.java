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

import com.zeligsoft.domain.zml.api.ZML_SwPlatform.SwConnector;
import com.zeligsoft.domain.zml.api.ZML_SwPlatform.impl.SwCommunicationMediumImplementation;

import com.zeligsoft.domain.zml.api.ZML_SwPlatform.SwCommunicationEndPoint;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public class SwConnectorImplementation extends
		SwCommunicationMediumImplementation implements SwConnector {
	protected java.util.List<SwCommunicationEndPoint> _end;

	public SwConnectorImplementation(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public java.util.List<SwCommunicationEndPoint> getEnd() {
		if (_end == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(), "ZMLMM::ZML_SwPlatform::SwConnector",
							"end");
			_end = new java.util.ArrayList<SwCommunicationEndPoint>();
			@SuppressWarnings("unchecked")
			final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
			for (Object next : rawList) {
				if (next instanceof org.eclipse.emf.ecore.EObject) {
					SwCommunicationEndPoint nextWrapper = ZDLFactoryRegistry.INSTANCE
							.create((org.eclipse.emf.ecore.EObject) next,
									SwCommunicationEndPoint.class);
					_end.add(nextWrapper);
				}
			}
		}
		return _end;
	}

	@Override
	public void addEnd(SwCommunicationEndPoint val) {
		// make sure the end list is created
		getEnd();

		final Object rawValue = ZDLUtil.getValue(element,
				"ZMLMM::ZML_SwPlatform::SwConnector", "end");
		@SuppressWarnings("unchecked")
		final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
		rawList.add(val.eObject());
		if (_end != null) {
			_end.add(val);
		}
	}

	@Override
	public org.eclipse.uml2.uml.Connector asConnector() {
		return (org.eclipse.uml2.uml.Connector) eObject();
	}
}
