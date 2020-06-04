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

import com.zeligsoft.base.zdl.util.ZDLUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ZDLFilterUtil {
  public void findConcepts(final org.eclipse.uml2.uml.Package pkg, final String concept) {
    EList<PackageableElement> _packagedElements = pkg.getPackagedElements();
    final Function1<EObject,Boolean> _function = new Function1<EObject,Boolean>() {
        public Boolean apply(final EObject eobj) {
          boolean _isZDLConcept = ZDLUtil.isZDLConcept(eobj, concept);
          return Boolean.valueOf(_isZDLConcept);
        }
      };
    final Iterable<PackageableElement> conceptElements = IterableExtensions.<PackageableElement>filter(_packagedElements, _function);
    final Function1<EObject,EObject> _function_1 = new Function1<EObject,EObject>() {
        public EObject apply(final EObject eobj) {
          return eobj;
        }
      };
    final Iterable<EObject> result = IterableExtensions.<PackageableElement, EObject>map(conceptElements, _function_1);
  }
}
