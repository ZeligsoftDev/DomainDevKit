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
package com.zeligsoft.base.util;

import java.util.Map;

import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;

public class WorkflowCallbacks extends IWorkflowCallbacks.Adapter
{
	private final Map<String, Object> slots;

	public WorkflowCallbacks( Map<String, Object> slots )
	{
		this.slots = slots;
	}

	@Override
	public void preInvoke(WorkflowContext ctx, Issues issues) {
		if (this.slots == null)
			return;

		for (Map.Entry<String, Object> next : this.slots.entrySet())
			ctx.set(next.getKey(), next.getValue());
	}
}
