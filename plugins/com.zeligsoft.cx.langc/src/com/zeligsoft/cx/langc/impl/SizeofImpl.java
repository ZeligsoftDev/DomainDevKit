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
package com.zeligsoft.cx.langc.impl;

import org.eclipse.emf.ecore.EClass;

import com.zeligsoft.cx.langc.BuiltInType;
import com.zeligsoft.cx.langc.ElementReference;
import com.zeligsoft.cx.langc.LangCFactory;
import com.zeligsoft.cx.langc.LangCPackage;
import com.zeligsoft.cx.langc.PrimitiveType;
import com.zeligsoft.cx.langc.Sizeof;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sizeof</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class SizeofImpl extends ExpressionImpl implements Sizeof {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SizeofImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LangCPackage.Literals.SIZEOF;
	}

	private static ElementReference intRef;
	static {
		BuiltInType intType = LangCFactory.eINSTANCE.createBuiltInType();
		intType.setType(PrimitiveType.INT32);

		intRef = LangCFactory.eINSTANCE.createElementReference();
		intRef.setElement(intType);
	}

	@Override
	public ElementReference basicGetType() {
		return intRef;
	}

} //SizeofImpl
