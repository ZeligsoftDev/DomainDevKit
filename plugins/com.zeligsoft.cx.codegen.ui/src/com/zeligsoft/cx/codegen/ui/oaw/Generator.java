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
package com.zeligsoft.cx.codegen.ui.oaw;

import com.zeligsoft.cx.codegen.ui.internal.OawUtil;

public class Generator extends org.eclipse.xpand2.Generator
{
	public void setMetaModelClasses( String metaModels )
	{
		OawUtil.setMetaModelClasses( this, metaModels );
	}

	public void setProfileMetaModels( String profiles )
	{
		OawUtil.setProfileMetaModels( this, profiles );
	}

	public void setEMFMetaModelPackages( String mmPackages )
	{
		OawUtil.setEMFMetaModelPackages( this, mmPackages );
	}

	public void setZDLMetaModels( String zdlMMs )
	{
		OawUtil.setZDLMetaModels( this, zdlMMs );
	}
}
