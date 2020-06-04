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

import com.google.common.collect.Iterables;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainModel;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainPackageableElement;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public class GenDomainModelExtensions {
  public Iterable<GenDomainBlock> domainBlocks(final GenDomainModel model) {
    EList<GenDomainPackageableElement> _elements = model.getElements();
    Iterable<GenDomainBlock> _filter = Iterables.<GenDomainBlock>filter(_elements, GenDomainBlock.class);
    return _filter;
  }
}
