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
package com.zeligsoft.ddk.zdl.zdlgen;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Palette Drawer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.zeligsoft.ddk.zdl.zdlgen.GenPaletteDrawer#getSpecializes <em>Specializes</em>}</li>
 *   <li>{@link com.zeligsoft.ddk.zdl.zdlgen.GenPaletteDrawer#getPalette <em>Palette</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.zeligsoft.ddk.zdl.zdlgen.ZDLGenPackage#getGenPaletteDrawer()
 * @model
 * @generated
 */
public interface GenPaletteDrawer extends GenPaletteToolContainer {

	/**
	 * Returns the value of the '<em><b>Specializes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specializes</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specializes</em>' reference.
	 * @see #setSpecializes(GenPaletteDrawer)
	 * @see com.zeligsoft.ddk.zdl.zdlgen.ZDLGenPackage#getGenPaletteDrawer_Specializes()
	 * @model ordered="false"
	 * @generated
	 */
	GenPaletteDrawer getSpecializes();

	/**
	 * Sets the value of the '{@link com.zeligsoft.ddk.zdl.zdlgen.GenPaletteDrawer#getSpecializes <em>Specializes</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specializes</em>' reference.
	 * @see #getSpecializes()
	 * @generated
	 */
	void setSpecializes(GenPaletteDrawer value);

	/**
	 * Returns the value of the '<em><b>Palette</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.zeligsoft.ddk.zdl.zdlgen.GenPalette#getOwnedDrawers <em>Owned Drawer</em>}'.
	 * <p>
	 * This feature subsets the following features:
	 * <ul>
	 *   <li>'{@link com.zeligsoft.ddk.zdl.zdlgen.GenDomainObject#getOwner() <em>Owner</em>}'</li>
	 * </ul>
	 * </p>
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Palette</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Palette</em>' container reference.
	 * @see #setPalette(GenPalette)
	 * @see com.zeligsoft.ddk.zdl.zdlgen.ZDLGenPackage#getGenPaletteDrawer_Palette()
	 * @see com.zeligsoft.ddk.zdl.zdlgen.GenPalette#getOwnedDrawers
	 * @model opposite="ownedDrawer" required="true" transient="false" ordered="false"
	 * @generated
	 */
	GenPalette getPalette();

	/**
	 * Sets the value of the '{@link com.zeligsoft.ddk.zdl.zdlgen.GenPaletteDrawer#getPalette <em>Palette</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Palette</em>' container reference.
	 * @see #getPalette()
	 * @generated
	 */
	void setPalette(GenPalette value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 * @generated
	 */
	EList<GenPaletteTool> allTools();

} // GenPaletteDrawer
