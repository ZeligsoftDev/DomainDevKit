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
package com.zeligsoft.base.zdl.staticapi.Constructs.impl;

import com.zeligsoft.base.zdl.staticapi.Constructs.DomainBlock;
import com.zeligsoft.base.zdl.staticapi.Constructs.DomainClassifier;
import com.zeligsoft.base.zdl.staticapi.Validation.ExternalDomainConstraint;
import com.zeligsoft.base.zdl.staticapi.util.ZDLFactoryRegistry;
import com.zeligsoft.base.zdl.util.ZDLUtil;

public class DomainBlockImpl extends DomainNamedElementImpl implements
		DomainBlock {
	protected java.util.List<ExternalDomainConstraint> _constraint;
	protected java.util.List<DomainClassifier> _classifier;

	public DomainBlockImpl(org.eclipse.emf.ecore.EObject element) {
		super(element);
	}

	@Override
	public java.util.List<ExternalDomainConstraint> getConstraint() {
		if (_constraint == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(), "ZDL::Constructs::DomainBlock",
							"constraint");
			_constraint = new java.util.ArrayList<ExternalDomainConstraint>();
			@SuppressWarnings("unchecked")
			final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
			for (Object next : rawList) {
				if (next instanceof org.eclipse.emf.ecore.EObject) {
					ExternalDomainConstraint nextWrapper = ZDLFactoryRegistry.INSTANCE
							.create((org.eclipse.emf.ecore.EObject) next,
									ExternalDomainConstraint.class);
					_constraint.add(nextWrapper);
				}
			}
		}
		return _constraint;
	}

	@Override
	public void addConstraint(ExternalDomainConstraint val) {
		// make sure the constraint list is created
		getConstraint();

		final Object rawValue = ZDLUtil.getValue(element,
				"ZDL::Constructs::DomainBlock", "constraint");
		@SuppressWarnings("unchecked")
		final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
		rawList.add(val.eObject());
		if (_constraint != null) {
			_constraint.add(val);
		}
	}

	@Override
	public <T extends ExternalDomainConstraint> T addConstraint(
			Class<T> typeToCreate, String concept) {
		// make sure the constraint list is created
		getConstraint();
		org.eclipse.emf.ecore.EObject newConcept = ZDLUtil.createZDLConcept(
				element, "ZDL::Constructs::DomainBlock", "constraint", concept);
		T element = ZDLFactoryRegistry.INSTANCE.create(
				(org.eclipse.emf.ecore.EObject) newConcept, typeToCreate);
		if (_constraint != null) {
			_constraint.add(element);
		}
		return element;
	}

	@Override
	public ExternalDomainConstraint addConstraint() {
		// make sure the constraint list is created
		getConstraint();
		org.eclipse.emf.ecore.EObject newConcept = ZDLUtil.createZDLConcept(
				element, "ZDL::Constructs::DomainBlock", "constraint",
				"ZDL::Validation::ExternalDomainConstraint");
		ExternalDomainConstraint element = ZDLFactoryRegistry.INSTANCE.create(
				(org.eclipse.emf.ecore.EObject) newConcept,
				ExternalDomainConstraint.class);
		if (_constraint != null) {
			_constraint.add(element);
		}
		return element;
	}

	@Override
	public java.util.List<DomainClassifier> getClassifier() {
		if (_classifier == null) {
			final Object rawValue = com.zeligsoft.base.zdl.util.ZDLUtil
					.getValue(eObject(), "ZDL::Constructs::DomainBlock",
							"classifier");
			_classifier = new java.util.ArrayList<DomainClassifier>();
			@SuppressWarnings("unchecked")
			final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
			for (Object next : rawList) {
				if (next instanceof org.eclipse.emf.ecore.EObject) {
					DomainClassifier nextWrapper = ZDLFactoryRegistry.INSTANCE
							.create((org.eclipse.emf.ecore.EObject) next,
									DomainClassifier.class);
					_classifier.add(nextWrapper);
				}
			}
		}
		return _classifier;
	}

	@Override
	public void addClassifier(DomainClassifier val) {
		// make sure the classifier list is created
		getClassifier();

		final Object rawValue = ZDLUtil.getValue(element,
				"ZDL::Constructs::DomainBlock", "classifier");
		@SuppressWarnings("unchecked")
		final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;
		rawList.add(val.eObject());
		if (_classifier != null) {
			_classifier.add(val);
		}
	}

	@Override
	public <T extends DomainClassifier> T addClassifier(Class<T> typeToCreate,
			String concept) {
		// make sure the classifier list is created
		getClassifier();
		org.eclipse.emf.ecore.EObject newConcept = ZDLUtil.createZDLConcept(
				element, "ZDL::Constructs::DomainBlock", "classifier", concept);
		T element = ZDLFactoryRegistry.INSTANCE.create(
				(org.eclipse.emf.ecore.EObject) newConcept, typeToCreate);
		if (_classifier != null) {
			_classifier.add(element);
		}
		return element;
	}

	@Override
	public org.eclipse.uml2.uml.Package asPackage() {
		return (org.eclipse.uml2.uml.Package) eObject();
	}
}
