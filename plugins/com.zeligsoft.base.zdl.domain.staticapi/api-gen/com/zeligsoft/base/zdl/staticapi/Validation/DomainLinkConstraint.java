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
package com.zeligsoft.base.zdl.staticapi.Validation;

import com.zeligsoft.base.zdl.staticapi.Constructs.DomainConcept;
import com.zeligsoft.base.zdl.staticapi.Constructs.DomainNamedElement;
import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;

public interface DomainLinkConstraint extends DomainNamedElement {
	LinkConstraintKind getKind();

	void setKind(LinkConstraintKind val);

	java.util.List<DomainLinkConstraint> getRedefinedConstraint();

	void addRedefinedConstraint(DomainLinkConstraint val);

	DomainConcept getSource();

	void setSource(DomainConcept val);

	DomainConcept getTarget();

	void setTarget(DomainConcept val);

	DomainNamedElement getContext();

	void setContext(DomainNamedElement val);

	org.eclipse.uml2.uml.Constraint asConstraint();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of DomainLinkConstraint
	 */
	static final TypeSelectPredicate<DomainLinkConstraint> type = new TypeSelectPredicate<DomainLinkConstraint>(
			"ZDL::Validation::DomainLinkConstraint", //$NON-NLS-1$
			DomainLinkConstraint.class);
}
