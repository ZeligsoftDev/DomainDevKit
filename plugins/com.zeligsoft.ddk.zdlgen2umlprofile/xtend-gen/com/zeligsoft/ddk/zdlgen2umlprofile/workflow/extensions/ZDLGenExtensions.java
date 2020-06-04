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
package com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions;

import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainModel;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainPackage;
import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;

@SuppressWarnings("all")
public class ZDLGenExtensions {
  protected GenDomainModel _domainModel(final GenDomainConcept concept) {
    GenDomainBlock _block = concept.getBlock();
    GenDomainModel _domainModel = this.domainModel(_block);
    return _domainModel;
  }
  
  protected GenDomainModel _domainModel(final GenDomainBlock block) {
    EObject _eContainer = block.eContainer();
    GenDomainModel _domainModel = this.domainModel(_eContainer);
    return _domainModel;
  }
  
  protected GenDomainModel _domainModel(final GenDomainPackage pkg) {
    EObject _eContainer = pkg.eContainer();
    GenDomainModel _domainModel = this.domainModel(_eContainer);
    return _domainModel;
  }
  
  protected GenDomainModel _domainModel(final GenDomainModel model) {
    return model;
  }
  
  protected GenDomainModel _domainModel(final Object eobj) {
    return null;
  }
  
  public GenDomainModel domainModel(final Object model) {
    if (model instanceof GenDomainModel) {
      return _domainModel((GenDomainModel)model);
    } else if (model instanceof GenDomainBlock) {
      return _domainModel((GenDomainBlock)model);
    } else if (model instanceof GenDomainConcept) {
      return _domainModel((GenDomainConcept)model);
    } else if (model instanceof GenDomainPackage) {
      return _domainModel((GenDomainPackage)model);
    } else if (model != null) {
      return _domainModel(model);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(model).toString());
    }
  }
}
