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
package com.zeligsoft.base.zdl.staticapi.Constructs;

import com.zeligsoft.base.zdl.staticapi.core.ZObject;
import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;

public interface DomainElement extends ZObject {
	java.util.List<DomainElement> getOwnedElement();

	void addOwnedElement(DomainElement val);

	<T extends DomainElement> T addOwnedElement(Class<T> typeToCreate,
			String concept);

	DomainElement getOwner();

	void setOwner(DomainElement val);

	/**
	 * A predicate which returns true if the Object is an
	 * instance of DomainElement
	 */
	static final TypeSelectPredicate<DomainElement> type = new TypeSelectPredicate<DomainElement>(
			"ZDL::Constructs::DomainElement", //$NON-NLS-1$
			DomainElement.class);
}
