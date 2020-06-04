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
package com.zeligsoft.base.zdl.l10n;

import org.eclipse.osgi.util.NLS;

/**
 * Localized messages from the ZDL oAW plug-in.
 *
 * @author Christian W. Damus (cdamus)
 */
public final class Messages
		extends NLS {

	private static final String BUNDLE_NAME = "com.zeligsoft.base.zdl.l10n.messages"; //$NON-NLS-1$

	public static String AbstractZDLAdvice_autname;

	public static String OawEvaluationUtil_EvaluationException;

	public static String OawEvaluationUtil_UnexpectedExpression;

	public static String ZDLDestroyReferenceAdvice_commandLabel;

	public static String ZDLElementTypeManager_reyTypeName;

	public static String ZDLMetamodel_ERROR_RESOLVING_CONTEXT_PROFILE;

	public static String ZDLMetamodelManager_analyzeDelta;

	public static String ZDLMetamodelManager_closeJar;

	public static String ZDLMetamodelManager_interrupt;

	public static String ZDLMetamodelManager_locating_zdls;

	public static String ZDLMetamodelManager_openJar;

	public static String ZDLMetamodelManager_updateZDL;

	public static String ZDLMetamodelManager_updating_zdls;

	public static String ZDLUtil_conceptIcon;

	public static String ZDLUtil_referenceIcon;
	
	public static String ZDLElementTypeManager_unabledToLoadHintedType;
	
	public static String ZDLElementTypeManager_errorWhileTryingToLoadHintedElementTypePropertyResource;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * Construct me.
	 */
	private Messages() {
		super();
	}
}
