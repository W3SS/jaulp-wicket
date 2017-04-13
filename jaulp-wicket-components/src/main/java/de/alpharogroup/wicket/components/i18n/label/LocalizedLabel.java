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
package de.alpharogroup.wicket.components.i18n.label;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class LocalizedLabel initializes the Label with a StringResourceModel.
 *
 * @deprecated use instead Label from wicket and create IModel with the
 *             {@link de.alpharogroup.wicket.base.util.resource.ResourceModelFactory}.
 */
@Deprecated
public class LocalizedLabel extends Label
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 5198063608075182201L;

	/**
	 * Instantiates a new {@link LocalizedLabel}.
	 *
	 * @param id
	 *            the id
	 * @param resourceBundleKey
	 *            the resourceBundleKey
	 */
	public LocalizedLabel(final String id, final ResourceBundleKey resourceBundleKey)
	{
		super(id);
		setDefaultModel(ResourceModelFactory.newResourceModel(resourceBundleKey, this));
	}

	/**
	 * Instantiates a new {@link LocalizedLabel}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 */
	public LocalizedLabel(final String id, final String resourceKey, final Component component)
	{
		super(id);
		setDefaultModel(ResourceModelFactory.newResourceModel(resourceKey, component));
	}

	/**
	 * Instantiates a new {@link LocalizedLabel}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 */
	public LocalizedLabel(final String id, final String resourceKey, final Component component,
		final IModel<?> model, final String defaultValue, final Object... parameters)
	{
		super(id);
		setDefaultModel(ResourceModelFactory.newResourceModel(resourceKey, component, model,
			defaultValue, parameters));
	}

	/**
	 * Instantiates a new {@link LocalizedLabel}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param parameters
	 *            the parameters
	 */
	public LocalizedLabel(final String id, final String resourceKey, final Component component,
		final Object... parameters)
	{
		super(id);
		setDefaultModel(ResourceModelFactory.newResourceModel(resourceKey, component, parameters));
	}

	/**
	 * Instantiates a new {@link LocalizedLabel}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param defaultValue
	 *            the default value
	 */
	public LocalizedLabel(final String id, final String resourceKey, final Component component,
		final String defaultValue)
	{
		super(id);
		setDefaultModel(ResourceModelFactory.newResourceModel(resourceKey, component, defaultValue));
	}

	/**
	 * Instantiates a new {@link LocalizedLabel}.
	 *
	 * @param id
	 *            the id
	 * @param stringResourceModel
	 *            the string resource model
	 */
	public LocalizedLabel(final String id, final StringResourceModel stringResourceModel)
	{
		super(id, stringResourceModel);
	}

}
