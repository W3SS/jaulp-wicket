/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.base;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * The Class BasePanel.
 * 
 * @param <T>
 *            the generic type of the model
 */
public abstract class BasePanel<T> extends GenericPanel<T>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link BasePanel}.
	 *
	 * @param id
	 *            the id
	 */
	public BasePanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link BasePanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public BasePanel(final String id, final IModel<T> model)
	{
		super(id, model);
		if (model != null)
		{
			setModel(model);
		}
	}

}