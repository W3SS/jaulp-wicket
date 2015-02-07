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
package org.jaulp.wicket.base.util.resource;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * A factory for creating StringResourceModel objects.
 */
public final class ResourceModelFactory
{

	/**
	 * Factory method to create a new {@link StringResourceModel} from the given ResourceBundleKey.
	 *
	 * @param resourceBundleKey
	 *            the resource bundle key
	 * @param component
	 *            the component
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final ResourceBundleKey resourceBundleKey,
		final Component component)
	{
		IModel<String> resourceModel;
		String resourceKey = resourceBundleKey.getKey();
		resourceModel = newResourceModel(resourceKey, resourceBundleKey.getParameters(), component,
			resourceBundleKey.getDefaultValue());
		return resourceModel;
	}

	/**
	 * Factory method to create a new {@link StringResourceModel} from the given resource key, the
	 * parameters as an Object array, the component and a default value.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param parameters
	 *            the parameters
	 * @param component
	 *            the component
	 * @param defaultValue
	 *            the default value
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
		final Object[] parameters, final Component component, String defaultValue)
	{
		if (parameters != null && parameters.length > 0)
		{
			if (defaultValue != null && !defaultValue.isEmpty())
			{
				return ResourceModelFactory.newResourceModel(resourceKey, defaultValue, component,
					parameters);
			}
			else
			{
				return ResourceModelFactory.newResourceModel(resourceKey, component, parameters);
			}
		}
		else
		{
			if (defaultValue != null && !defaultValue.isEmpty())
			{
				return ResourceModelFactory.newResourceModel(resourceKey, component, defaultValue);
			}
			else
			{
				return ResourceModelFactory.newResourceModel(resourceKey, component);
			}
		}
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
		final Component component)
	{
		return newResourceModel(resourceKey, component, null, "");
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param defaultValue
	 *            the default value
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
		final Component component, String defaultValue)
	{
		return newResourceModel(resourceKey, component, null, defaultValue);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param parameters
	 *            the parameters
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
		final Component component, final Object... parameters)
	{
		return newResourceModel(resourceKey, component, null, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @param parameters
	 *            the parameters
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey, String defaultValue,
		final Component component, final Object... parameters)
	{
		return newResourceModel(resourceKey, component, null, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey, final IModel<?> model,
		final Object... parameters)
	{
		return newResourceModel(resourceKey, null, model, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component
	 * @param model
	 *            the model
	 * @param parameters
	 *            the parameters
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
		final Component component, final IModel<?> model, final Object... parameters)
	{
		return newResourceModel(resourceKey, component, model, null, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
	 * @param resourceKey
	 *            the resource key
	 * @param model
	 *            the model
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey, final IModel<?> model,
		final String defaultValue, final Object... parameters)
	{
		return newResourceModel(resourceKey, null, model, defaultValue, parameters);
	}

	/**
	 * Factory method to create a new StringResourceModel from the given resource key.
	 *
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
	 * @return a new {@link StringResourceModel} as an {@link IModel}
	 */
	public static IModel<String> newResourceModel(final String resourceKey,
		final Component component, final IModel<?> model, final String defaultValue,
		final Object... parameters)
	{
		for (int i = 0; i < parameters.length; i++)
		{
			if (parameters[i] != null && parameters[i] instanceof ResourceBundleKey)
			{
				ResourceBundleKey parameter = (ResourceBundleKey)parameters[i];
				IModel<String> parameterValue = newResourceModel(parameter, component);
				parameters[i] = parameterValue.getObject();
			}
		}
		return new StringResourceModel(resourceKey, component, model, defaultValue, parameters);
	}

}
