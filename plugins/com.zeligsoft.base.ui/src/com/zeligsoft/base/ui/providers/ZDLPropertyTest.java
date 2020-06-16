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
package com.zeligsoft.base.ui.providers;

import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.uml2.uml.Element;

import com.zeligsoft.base.zdl.util.ZDLUtil;

public class ZDLPropertyTest extends PropertyTester {

	public static final String IS_ZDLCONCEPT = "isZDLConcept";//$NON-NLS-1$

	public static final String IS_ZDLPROFILE = "isZDLProfile";//$NON-NLS-1$

	/**
	 *
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 *      java.lang.String, java.lang.Object[], java.lang.Object)
	 *
	 * @param receiver
	 * @param property
	 * @param args
	 * @param expectedValue
	 * @return
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (IS_ZDLCONCEPT.equals(property) && (receiver instanceof IStructuredSelection)
				&& (expectedValue instanceof String)) {
			return hasZDLConcept((IStructuredSelection) receiver, (String) expectedValue);
		} else if (IS_ZDLPROFILE.equals(property) && (receiver instanceof IStructuredSelection)
				&& (expectedValue instanceof String)) {
			return hasZDLProfile((IStructuredSelection) receiver, (String) expectedValue);
		}
		return false;
	}

	protected boolean hasZDLProfile(IStructuredSelection selection, String profile) {
		if (!selection.isEmpty()) {
			EObject eo = null;
			Iterator<?> iter = selection.iterator();
			while (iter.hasNext()) {
				eo = EMFHelper.getEObject(iter.next());
				if (eo instanceof Element) {
					break;
				}
			}
			return ZDLUtil.isZDLProfile((Element) eo, profile);
		}
		return false;
	}

	protected boolean hasZDLConcept(IStructuredSelection selection, String concept) {
		if (!selection.isEmpty()) {
			EObject eo = null;
			Iterator<?> iter = selection.iterator();
			while (iter.hasNext()) {
				eo = EMFHelper.getEObject(iter.next());
				if (eo != null) {
					break;
				}
			}
			return ZDLUtil.isZDLConcept(eo, concept);
		}
		return false;
	}
}
