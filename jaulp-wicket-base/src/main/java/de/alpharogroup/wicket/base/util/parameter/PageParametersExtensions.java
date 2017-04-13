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
package de.alpharogroup.wicket.base.util.parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.INamedParameters;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.StringValueConversionException;

/**
 * The Class PageParametersUtils.
 */
public final class PageParametersExtensions
{

	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(PageParametersExtensions.class.getName());

	/**
	 * Adds the given parameters to the given map.
	 *
	 * @param parameters
	 *            The parameters to add to the map.
	 * @param parameterMap
	 *            The map to add the parameters.
	 */
	public static void addParameters(final IRequestParameters parameters,
		final Map<String, String[]> parameterMap)
	{
		final Set<String> parameterNames = parameters.getParameterNames();
		for (final String parameterName : parameterNames)
		{
			final List<StringValue> parameterValues = parameters.getParameterValues(parameterName);
			String[] stringArray = { };
			if ((parameterValues != null) && !parameterValues.isEmpty())
			{
				stringArray = new String[parameterValues.size()];
				for (int i = 0; i < parameterValues.size(); i++)
				{
					stringArray[i] = parameterValues.get(i).toString();
				}
				if (parameterMap.containsKey(parameterName))
				{
					stringArray = ArrayUtils.addAll(parameterMap.get(parameterName), stringArray);
				}
			}
			parameterMap.put(parameterName, stringArray);
		}
	}

	/**
	 * Adds the given parameters to the given map.
	 *
	 * @param parameters
	 *            The parameters to add to the map.
	 * @param parameterMap
	 *            The map to add the parameters.
	 */
	public static void addToParameters(final IRequestParameters parameters,
		final Map<String, List<StringValue>> parameterMap)
	{
		for (final String parameterName : parameters.getParameterNames())
		{
			final List<StringValue> parameterValues = parameters.getParameterValues(parameterName);
			parameterMap.put(parameterName, parameterValues);
		}
	}

	/**
	 * Copies all given source {@link org.apache.wicket.request.mapper.parameter.PageParameters} to
	 * the given destination {@link org.apache.wicket.request.mapper.parameter.PageParameters}.
	 *
	 * @param source
	 *            The source {@link org.apache.wicket.request.mapper.parameter.PageParameters}.
	 * @param destination
	 *            The destination {@link org.apache.wicket.request.mapper.parameter.PageParameters}.
	 * @return The destination {@link org.apache.wicket.request.mapper.parameter.PageParameters}
	 *         with the copied keys and values.
	 */
	public static PageParameters copy(final PageParameters source, final PageParameters destination)
	{
		Args.notNull(source, "source");
		Args.notNull(destination, "destination");
		final List<INamedParameters.NamedPair> namedPairs = source.getAllNamed();
		for (final INamedParameters.NamedPair namedPair : namedPairs)
		{
			destination.add(namedPair.getKey(), namedPair.getValue());
		}
		return destination;
	}


	/**
	 * Copies all given source {@link org.apache.wicket.request.mapper.parameter.PageParameters} to
	 * the given session {@link org.apache.wicket.Session}.
	 * 
	 * @param source
	 *            The source {@link org.apache.wicket.request.mapper.parameter.PageParameters}.
	 * @param session
	 *            The session where the
	 *            {@link org.apache.wicket.request.mapper.parameter.PageParameters} are stored.
	 */
	public static void copyToWicketSession(final PageParameters source, final Session session)
	{
		final List<INamedParameters.NamedPair> namedPairs = source.getAllNamed();
		for (final INamedParameters.NamedPair namedPair : namedPairs)
		{
			session.setAttribute(namedPair.getKey(), namedPair.getValue());
		}
	}

	/**
	 * Gets the Integer object or returns null if the given StringValue is null or empty.
	 *
	 * @param stringValue
	 *            the user id as StringValue object
	 * @return the Integer object or null if the given StringValue is null or empty.
	 * @deprecated use instead the {@link PageParametersExtensions#toInteger(StringValue)}
	 */
	@Deprecated
	public static Integer getInteger(final StringValue stringValue)
	{
		return toInteger(stringValue);
	}

	/**
	 * Gets a map with all parameters. Looks in the query, request and post parameters.
	 *
	 * @param request
	 *            the request
	 * @return a map with all parameters.
	 */
	public static Map<String, List<StringValue>> getPageParametersMap(final Request request)
	{
		final Map<String, List<StringValue>> map = new HashMap<>();
		addToParameters(request.getRequestParameters(), map);
		addToParameters(request.getQueryParameters(), map);
		addToParameters(request.getPostParameters(), map);
		return map;
	}

	/**
	 * Gets the parameter or returns null if it does not exists or is empty.
	 *
	 * @param parameters
	 *            the parameters
	 * @param name
	 *            the name
	 * @return the parameter or returns null if it does not exists or is empty.
	 */
	public static String getParameter(final PageParameters parameters, final String name)
	{
		return getString(parameters.get(name));
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 *
	 * @param request
	 *            the request
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 */
	public static String getParameter(final Request request, final String parameterName)
	{
		String parameterValue = request.getRequestParameters().getParameterValue(parameterName)
			.toString();
		if ((parameterValue == null) || parameterValue.isEmpty())
		{
			parameterValue = request.getPostParameters().getParameterValue(parameterName)
				.toString();
		}
		if ((parameterValue == null) || parameterValue.isEmpty())
		{
			parameterValue = request.getQueryParameters().getParameterValue(parameterName)
				.toString();
		}
		if ((parameterValue == null) || parameterValue.isEmpty())
		{
			parameterValue = request.getRequestParameters().getParameterValue(parameterName)
				.toString();
		}
		return parameterValue;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 *
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 */
	public static String getParameter(final String parameterName)
	{
		final Request request = RequestCycle.get().getRequest();
		return getParameter(request, parameterName);
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 *
	 * @return a map with all parameters.
	 */
	public static Map<String, String[]> getParameterMap()
	{
		final Request request = RequestCycle.get().getRequest();
		return getParameterMap(request);
	}

	/**
	 * Gets a map with all parameters. Looks in the query, request and post parameters. Migration
	 * method from 1.4.* to 1.5.*.
	 *
	 * @param request
	 *            the request
	 * @return a map with all parameters.
	 */
	public static Map<String, String[]> getParameterMap(final Request request)
	{
		final Map<String, String[]> map = new HashMap<>();
		addParameters(request.getRequestParameters(), map);
		addParameters(request.getQueryParameters(), map);
		addParameters(request.getPostParameters(), map);
		return map;
	}

	/**
	 * Gets the string from the given {@link StringValue} or null if it is null or is empty.
	 *
	 * @param value
	 *            the {@link StringValue}
	 * @return the string or null if it is null or is empty.
	 */
	public static String getString(final StringValue value)
	{
		if (isNotNullOrEmpty(value))
		{
			return value.toString();
		}
		return null;
	}

	/**
	 * <p>
	 * Checks if the given StringValue is not null and the value of the given StringValue object is
	 * not null and the value of the given StringValue object is not empty.
	 * </p>
	 *
	 * @param stringValue
	 *            the StringValue to check, may be null
	 * @return <code>true</code> if the StringValue is not null and the value of the given
	 *         StringValue object is not null and the value of the given StringValue object is not
	 *         empty otherwise false.
	 */
	public static final boolean isNotNullOrEmpty(final StringValue stringValue)
	{
		return (stringValue != null) && !stringValue.isNull() && !stringValue.isEmpty();
	}

	/**
	 * <p>
	 * Checks if the given StringValue is null or the value of the given StringValue object is null
	 * or the value of the given StringValue object is empty.
	 * </p>
	 *
	 * @param stringValue
	 *            the StringValue to check, may be null
	 * @return <code>true</code> if the StringValue is null or the value of the given StringValue
	 *         object is null or the value of the given StringValue object is empty.
	 */
	public static final boolean isNullOrEmpty(final StringValue stringValue)
	{
		return (stringValue == null) || stringValue.isNull() || stringValue.isEmpty();
	}

	/**
	 * Gets the Integer object or returns null if the given StringValue is null or empty.
	 *
	 * @param stringValue
	 *            the user id as StringValue object
	 * @return the Integer object or null if the given StringValue is null or empty.
	 */
	public static Integer toInteger(final StringValue stringValue)
	{
		Integer value = null;
		if (isNotNullOrEmpty(stringValue))
		{
			try
			{
				value = stringValue.toInteger();
			}
			catch (final StringValueConversionException e)
			{
				LOGGER.error("Error by converting the given StringValue.", e);
			}
		}
		return value;
	}

	/**
	 * Converts the given Map to a {@link PageParameters} object.
	 *
	 * @param parameters
	 *            the {@link Map} with the parameters to set.
	 * @return the {@link PageParameters}
	 */
	public static PageParameters toPageParameters(final Map<String, String> parameters)
	{
		final PageParameters param = new PageParameters();
		for (final Entry<String, String> parameter : parameters.entrySet())
		{
			param.add(parameter.getKey(), parameter.getValue());
		}
		return param;
	}

}
