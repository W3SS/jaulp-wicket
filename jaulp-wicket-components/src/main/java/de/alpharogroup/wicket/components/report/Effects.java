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
package de.alpharogroup.wicket.components.report;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import de.alpharogroup.wicket.behaviors.DisplayNoneBehavior;
import lombok.experimental.UtilityClass;

/**
 * The Class {@link Effects}.
 */
@UtilityClass
public class Effects
{

	/**
	 * Replace.
	 *
	 * @param target
	 *            the target
	 * @param component
	 *            the component
	 */
	public static void replace(final AjaxRequestTarget target, final Component component)
	{
		component.add(new DisplayNoneBehavior());
		target.add(component);
		target.appendJavaScript("jQuery('#" + component.getMarkupId() + "').slideDown(100);");
	}
}