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

import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.base.util.WicketComponentExtensions;

/**
 * The Class AbstractGenericBasePage.
 * 
 * @param <T>
 *            the generic type of the model
 */
public abstract class AbstractGenericBasePage<T> extends GenericWebPage<T>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link AbstractGenericBasePage} with no parameters.
	 */
	public AbstractGenericBasePage()
	{
	}

	/**
	 * Instantiates a new {@link AbstractGenericBasePage} with an IModel object.
	 *
	 * @param model
	 *            the model
	 */
	public AbstractGenericBasePage(final IModel<T> model)
	{
		super(model);
		if (model != null)
		{
			setModel(model);
		}
	}

	/**
	 * Instantiates a new {@link AbstractGenericBasePage} with parameters.
	 *
	 * @param parameters
	 *            the parameters
	 */
	public AbstractGenericBasePage(final PageParameters parameters)
	{
		super(parameters);
	}

	/**
	 * Gets the request url.
	 *
	 * @return the request url
	 */
	public String getRequestURL()
	{
		return WicketComponentExtensions.getRequestURL();
	}

	/**
	 * Sets the given parameter to the page.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setParameter(final String key, final Object value)
	{
		getPageParameters().add(key, value);
	}

}