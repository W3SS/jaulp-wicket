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
package de.alpharogroup.wicket.behaviors.css;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;

/**
 * The Class RemoveCssClass removes the given css classes.
 */
public class RemoveCssClass extends AttributeModifier
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link RemoveCssClass}.
	 *
	 * @param cssClass
	 *            the css class
	 */
	public RemoveCssClass(final String cssClass)
	{
		super("class", new Model<>(cssClass));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String newValue(final String currentValue, final String cssClass)
	{
		if (currentValue != null)
		{
			return currentValue.replaceAll(cssClass, "");
		}
		return "";
	}
}