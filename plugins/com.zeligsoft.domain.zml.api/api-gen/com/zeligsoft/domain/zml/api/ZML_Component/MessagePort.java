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
package com.zeligsoft.domain.zml.api.ZML_Component;

import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;
import com.zeligsoft.domain.zml.api.ZML_Core.Type;

public interface MessagePort extends Port {
	java.util.List<Interface> getProvidedInterface();

	java.util.List<Interface> getRequiredInterface();

	@Override
	Type getType();

	@Override
	void setType(Type val);

	/**
	 * A predicate which returns true if the Object is an
	 * instance of MessagePort
	 */
	static final TypeSelectPredicate<MessagePort> type = new TypeSelectPredicate<MessagePort>(
			"ZMLMM::ZML_Component::MessagePort", //$NON-NLS-1$
			MessagePort.class);
}
