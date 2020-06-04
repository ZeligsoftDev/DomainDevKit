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

import com.zeligsoft.base.zdl.staticapi.core.ZObject;
import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;

public interface DomainConstraint extends ZObject {
	EvaluationModeKind getEvaluationMode();

	void setEvaluationMode(EvaluationModeKind val);

	SeverityKind getSeverity();

	void setSeverity(SeverityKind val);

	String getMessage();

	void setMessage(String val);

	String getId();

	void setId(String val);

	ConstraintKind getKind();

	void setKind(ConstraintKind val);

	Integer getStatusCode();

	void setStatusCode(Integer val);

	java.util.List<DomainConstraint> getRedefinedConstraint();

	void addRedefinedConstraint(DomainConstraint val);

	org.eclipse.uml2.uml.Constraint asConstraint();

	/**
	 * A predicate which returns true if the Object is an
	 * instance of DomainConstraint
	 */
	static final TypeSelectPredicate<DomainConstraint> type = new TypeSelectPredicate<DomainConstraint>(
			"ZDL::Validation::DomainConstraint", //$NON-NLS-1$
			DomainConstraint.class);
}
