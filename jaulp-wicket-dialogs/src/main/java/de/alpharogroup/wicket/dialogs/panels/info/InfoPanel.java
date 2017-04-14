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
package de.alpharogroup.wicket.dialogs.panels.info;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class {@link InfoPanel}.
 *
 * @param <T>
 *            the generic type of the model object
 */
public abstract class InfoPanel<T> extends BasePanel<T>
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The close button.
	 */
	@Getter
	final AjaxButton closeButton;

	/**
	 * Instantiates a new {@link InfoPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public InfoPanel(final String id, final IModel<T> model, final IModel<String> labelModel)
	{
		super(id, model);
		add(newLabel("message", labelModel));
		add(closeButton = newCloseButton("closeButton"));
	}

	/**
	 * Factory method for creating a new no {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a no {@link AjaxButton}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newCloseButton(final String id)
	{
		final AjaxButton ajaxButton = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final T object = InfoPanel.this.getModelObject();
				onClose(target, object);
			}
		};
		final IModel<String> noLabelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("global.no.label").defaultValue("No").build(), this);
		ajaxButton.add(newLabel("noLabel", noLabelModel));
		return ajaxButton;
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Label newLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}


	/**
	 * Abstract callback method that have to be overwritten to provide specific action for close.
	 *
	 * @param target
	 *            the target
	 * @param object
	 *            the object
	 */
	public abstract void onClose(final AjaxRequestTarget target, final T object);

}
