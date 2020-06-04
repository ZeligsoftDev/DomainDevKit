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

import org.eclipse.emf.ecore.EObject;

import com.zeligsoft.base.zdl.util.ZDLUtil;

/**
 * An enumeration for ZDL::Validation::ConstraintKind
 *
 * @author ZDL API Generator
 *
 */
public enum ConstraintKind {
	/**
	 * 
	 *
	 */
	WELLFORMEDNESS {
		public EObject eObject(EObject context) {
			return ZDLUtil.getZDLEnumLiteral(context,
					"ZDL::Validation::ConstraintKind", "wellFormedness");
		}

		public EObject eObject(
				com.zeligsoft.base.zdl.staticapi.core.ZObject context) {
			return eObject(context.eObject());
		}
	},

	/**
	 * 
	 *
	 */
	REMINDER {
		public EObject eObject(EObject context) {
			return ZDLUtil.getZDLEnumLiteral(context,
					"ZDL::Validation::ConstraintKind", "reminder");
		}

		public EObject eObject(
				com.zeligsoft.base.zdl.staticapi.core.ZObject context) {
			return eObject(context.eObject());
		}
	},
	/**
	 * Literal for cases when the value is UNKNOWN
	 */
	UNKNOWN {
		public EObject eObject(EObject context) {
			return null;
		}

		public EObject eObject(
				com.zeligsoft.base.zdl.staticapi.core.ZObject context) {
			return null;
		}
	};

	/**
	 * @param literal
	 *    the raw object to create the instance from
	 * @return
	 *    an instance of this enumeration based on the literal passed in and
	 *    UNKNOWN if the literal is unrecognized
	 */
	public static ConstraintKind create(EObject literal) {
		if (literal == ZDLUtil.getZDLEnumLiteral(literal,
				"ZDL::Validation::ConstraintKind", "wellFormedness")) { //$NON-NLS-1$//$NON-NLS-2$
			return WELLFORMEDNESS;
		} else if (literal == ZDLUtil.getZDLEnumLiteral(literal,
				"ZDL::Validation::ConstraintKind", "reminder")) { //$NON-NLS-1$//$NON-NLS-2$
			return REMINDER;
		} else {
			return UNKNOWN;
		}
	}

	public abstract EObject eObject(EObject context);

	public abstract EObject eObject(
			com.zeligsoft.base.zdl.staticapi.core.ZObject context);
}
