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
package de.alpharogroup.wicket.components.actions;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * In use with DataTable for a column with a link that provides an action like select, delete etc.
 * This class provides also the html template.
 */
public abstract class AbstractActionPanel<T> extends BasePanel<T>
{

	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constant id for the action link.
	 */
	private static final String ACTION_LINK_ID = "actionLink";

	/**
	 * The constant id for the label of the action link.
	 */
	private static final String ACTION_LINK_LABEL_ID = "actionLinkLabel";

	/**
	 * Instantiates a new {@link AbstractActionPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public AbstractActionPanel(final String id, final IModel<T> model)
	{
		super(id, model);
		add(newActionLink(ACTION_LINK_ID)
			.add(newActionLinkLabel(ACTION_LINK_LABEL_ID, newActionLinkLabelModel())));
	}

	/**
	 * Abstract factory method for creating the new action {@link AbstractLink}. This method is
	 * invoked in the constructor from the derived classes and have to be implemented so users can
	 * provide their own version of a new {@link AbstractLink}.
	 *
	 * @param id
	 *            the id
	 * @return the new action {@link AbstractLink}.
	 */
	protected abstract AbstractLink newActionLink(final String id);

	/**
	 * Factory method for creating the new Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a new Label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newActionLinkLabel(final String id, final IModel<String> model)
	{
		final Label label = ComponentFactory.newLabel(id, model);
		return label;
	}

	/**
	 * Factory method for creating the new {@link IModel} for the label of the action link. This
	 * method is invoked in the constructor and can be overridden so users can provide their own
	 * version of a new {@link IModel} for the label of the action link.
	 *
	 * @return the {@link IModel} for the label of the action link.
	 */
	protected abstract IModel<String> newActionLinkLabelModel();
}
