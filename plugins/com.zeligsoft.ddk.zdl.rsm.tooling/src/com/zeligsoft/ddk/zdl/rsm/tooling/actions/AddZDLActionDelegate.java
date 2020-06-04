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

package com.zeligsoft.ddk.zdl.rsm.tooling.actions;

import java.util.HashMap;
import java.util.Map;

import com.zeligsoft.base.ui.actions.AbstractAddZDLActionDelegate;
import com.zeligsoft.base.zdl.ZDLNames;

/**
 * ActionDelegate which supports the creation of ZDL node types.
 * 
 * @author jcorchis
 * 
 */
public class AddZDLActionDelegate
		extends AbstractAddZDLActionDelegate {

	/**
	 * Map which contains a mapping from Action id to concept.
	 */
	protected static Map<String, String> actionId2IElementType = new HashMap<String, String>();

	static {

		actionId2IElementType.put("addZDL_Domain_Package_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_PACKAGE);

		actionId2IElementType.put("addZDL_Domain_Block_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_BLOCK);

		actionId2IElementType.put("addZDL_Domain_Specialization_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_SPECIALIZATION);

		actionId2IElementType.put("addZDL_Domain_Data_Type_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_DATA_TYPE);

		actionId2IElementType.put("addZDL_Domain_Concept_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_CONCEPT);

		actionId2IElementType.put("addZDL_Domain_Model_Library_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_MODEL_LIBRARY);

		actionId2IElementType.put("addZDL_Domain_Enumeration_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_ENUM);

		actionId2IElementType.put("addZDL_Domain_Enumeration_Literal_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_ENUM_LITERAL);

		actionId2IElementType.put("addZDL_Domain_Attribute_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_ATTRIBUTE);

		actionId2IElementType.put("addZDL_Domain_Constraint_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_CONSTRAINT);

		actionId2IElementType.put("addZDL_Domain_Link_Constraint_ActionId", //$NON-NLS-1$
			ZDLNames.DOMAIN_LINK_CONSTRAINT);
	}

	@Override
	protected String getDomainConcept() {
		return actionId2IElementType.get(getAction().getId());
	}
}
