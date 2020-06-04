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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

import com.zeligsoft.base.licensing.FeatureStatus;
import com.zeligsoft.base.licensing.internal.l10n.Messages;

/**
 * The RLM license manager.
 * 
 * @author mtate
 */
public class RlmLicenseManager extends LicenseManager {

	private static final String ver = "0.01"; //minimum required version to check out //$NON-NLS-1$
	
	private static final String availableProducts = ""; //string to use for handle to check what available products available  //$NON-NLS-1$
	
	private static final String RLM_LICENSE_FILE_NAME = "Zeligsoft_rlm.lic"; //$NON-NLS-1$
	
	private static String libName = "rlm902"; //$NON-NLS-1$
	
	private File licenseFile;
		
	HashMap <String, String> currentlyCheckedOut = new HashMap <String, String> ();
	
	private static RlmLicenseManager INSTANCE = new RlmLicenseManager();
	
	
	/**
	 * Not instantiable by clients.
	 */
	private RlmLicenseManager() {
		super();
		if (System.getProperty("org.osgi.framework.processor").equals("x86-64")){ //$NON-NLS-1$ //$NON-NLS-2$
			libName = "64".concat(libName); //$NON-NLS-1$
		}
		if (System.getProperty("os.name").toLowerCase().contains("windows")){ //$NON-NLS-1$ //$NON-NLS-2$
			libName = libName.concat(".dll"); //$NON-NLS-1$
		}else {
			libName = libName.concat(".so"); //$NON-NLS-1$
		}
		initializeUIListener();
	}
	
	public static RlmLicenseManager getInstance(){
		return INSTANCE;
	}
	
	
	private void refreshDefaultHandle(){
	}
	
	
	/**
	 * Verify the license status of a given client bundle.
	 * 
	 * @param bundle
	 *            the licensed client bundle to verify
	 * @param bundleName
	 *            the localized name to show for the bundle in the event of a
	 *            problem
	 * @param cancelOnError
	 * 			  if we should have an IStatus of severity cancel instead of error
	 * 			when failing checks
	 * @param message
	 *			  optional message to display to users on failure
	 * 
	 * @return the status of the license check, which will either be OK on
	 *         success or an error status on failure to verify the license
	 */
	@Override
	public IStatus check(Bundle bundle, String bundleName, boolean cancelOnError, String message, boolean notifyListeners){
		IStatus result = Status.OK_STATUS;
		return result;
	}
	
	
	public IStatus checkProduct(Bundle bundle, String productName, String productVersion, boolean cancelOnError, String message){
		IStatus result = Status.OK_STATUS;
		return result;
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
	 * Summarizes the current license state of all known features for RLM.
	 * If there is more than one license for a product available, only the
	 * one with the shortest amount of time to expiry will be shown
	 * 
	 */
	public void summarizeFeatures(List <FeatureStatus> summary){
		return;
	}
	
	/**
	 * Summarizes the current license state of all known features.
	 * 
	 * @return a list of the statuses of currently known features
	 */
	@Override
	public List<String> listFeatures(){
		ArrayList<String> licenses = new ArrayList<String>();
		return licenses;
	}

	/**
	 * Imports the licenses in the specified file into the current RLM license file.
	 * If there is already a license for the product in the RLM license file,
	 * we will not import that license. 
	 * 
	 * @param fileToImport
	 *            the license file to import
	 */
	public void importLicenseFile(File fileToImport){
		return;
	}
	
	
	private void reCheckoutLicenses(){
		return;
	}
	
	/**
	 * Imports a license file from the activation server using an activation key
	 * 
	 * @param key
	 *            the activation key to use
	 */
	public boolean importActivationKey(String key, String server){
		return true;
	}
	
	
	/**
	 * Get the default license file for RLM
	 *  
	 * @return the file containing the recently activated license
	 * 
	 */
	protected synchronized File getLicenseFile()
		throws IOException {
		
		if (licenseFile == null) {
			licenseFile = getLicenseFile(RLM_LICENSE_FILE_NAME);
		}
		return licenseFile;
	}
	
	
	@Override
	public void notifyListeners(String bundleName, IStatus status, String message) {
		super.notifyListeners(bundleName, status, message);
	}
	
}