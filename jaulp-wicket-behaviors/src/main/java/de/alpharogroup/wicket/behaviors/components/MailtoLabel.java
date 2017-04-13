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
package de.alpharogroup.wicket.behaviors.components;

import org.apache.wicket.markup.html.basic.Label;

import de.alpharogroup.wicket.behaviors.MailtoBehavior;
import de.alpharogroup.wicket.behaviors.models.MailtoModel;

/**
 * The Class MailtoLabel.
 *
 * @author Asterios Raptis
 */
public class MailtoLabel extends Label
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = -3924484332764709856L;

	/**
	 * Instantiates a new mailto label.
	 *
	 * @param id
	 *            the id
	 * @param mailtoModel
	 *            the mailto model
	 */
	public MailtoLabel(final String id, final MailtoModel mailtoModel)
	{
		super(id, mailtoModel.getMailtoViewModel());
		setOutputMarkupId(true);
		add(new MailtoBehavior<MailtoModel>(mailtoModel));
	}

}
