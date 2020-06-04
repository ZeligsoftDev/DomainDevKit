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
package com.zeligsoft.base.licensing.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;


import com.zeligsoft.base.licensing.FeatureStatus;
import com.zeligsoft.base.licensing.internal.l10n.Messages;

/**
 * The Flex license manager.
 * 
 * @author mtate
 */
public class FlexLicenseManager extends LicenseManager{

	private File licenseFile;
	
	private static final String FLEX_LICENSE_FILE_NAME = "Zeligsoft.lic"; //$NON-NLS-1$
	
	private static FlexLicenseManager INSTANCE = new FlexLicenseManager();
	
	private final Object licenseSourceLock = new Object();
		
	/**
	 * Not instantiable by clients.
	 */
	private FlexLicenseManager() {
		super();

		initializeUIListener();
	}

	/**
	 * Obtains the singleton license manager object.
	 * 
	 * @return the license manager
	 */
	public static FlexLicenseManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Checks the license status of a given client bundle.
	 * 
	 * @param bundle
	 *            the licensed client bundle to verify
	 * @param bundleName
	 *            the localized name to show for the bundle in the event of a
	 *            problem
	 * @param message
	 *			  optional message to display to users on failure
	 * @param cancelOnError
	 * 			  determines if we should have an IStatus of severity cancel instead of error
	 * 			when failing checks
	 * 
	 * @return the status of the license check, which will either be OK on
	 *         success or an error status on failure to verify the license
	 */
	@Override
	public IStatus check(Bundle bundle, String bundleName, boolean cancelOnError, String message, boolean notifyListeners) {
		IStatus result;
		
		return Status.OK_STATUS;
	}
	
	
	/**
	 * Verify the license status of a given client bundle.
	 * 
	 * @param featureName
	 *            the name of the feature that we're checking to license, used
	 *            in error messages displayed to the user
	 * @param features
	 *            A list of features with versions to check for licensing.  If one
	 *            feature produces an OK status for license check, the return of the
	 *            method is an OK status
	 * 
	 * @return the status of the license check, which will either be OK on
	 *         success or an error status on failure to verify the license
	 */
	public IStatus checkForWorkflowEntryLicense(String featureName, LinkedHashMap<String, String> features){
		return Status.OK_STATUS;
	}

	
	/**
	 * Summarizes the current license state of all known features.
	 * 
	 * @return a list of the statuses of currently known features
	 */
	public List<FeatureStatus> summarizeFeatures() {
		List<FeatureStatus> result = new java.util.ArrayList<FeatureStatus>();
		return result;
	}
	
	/**
	 * Summarizes the current license state of all known features.
	 * 
	 * @return a list of the statuses of currently known features
	 */
	@Override
	public List<String> listFeatures(){
		ArrayList<String> result = new ArrayList<String>();
		return result;
	}

	/**
	 * Imports the licenses in the specified file into the current license file.
	 * We first check that it is a sensible Zeligsoft license file.
	 * 
	 * @param fileToImport
	 *            the license file to import
	 */
	public void importLicenseFile(File fileToImport)
			throws Exception{
		return;
	}

	private synchronized File getLicenseFile()
			throws IOException {
		if (licenseFile == null) {
			licenseFile = getLicenseFile(FLEX_LICENSE_FILE_NAME);
		}
		return licenseFile;
	}
	
	/**
	 * Notifies listeners of the check result.
	 * We need this so that we can integrate with Rlm
	 * and use that status as well
	 * 
	 * @param bundle
	 *            the bundle we're checking the license for
	 * @param bundleName
	 *            the name of bundle we're checking the license for
	 * @param message
	 *            optional message to be used to take place of dialog message
	 * @param status
	 *            the result status of the license check
	 */
	public void notify(Bundle bundle, String bundleName, IStatus status, String message){
		notifyListeners(bundleName, status, message);
	}
	
	public void notify(Bundle bundle, String bundleName, IStatus status){
		notifyListeners(bundleName, status, null);
	}
}