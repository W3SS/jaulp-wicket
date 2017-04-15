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
package de.alpharogroup.wicket.components.sign.in.password.change;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;


/**
 * The Class {@link ChangePasswordFormPanel}.
 */
public abstract class ChangePasswordFormPanel extends BasePanel<ChangePasswordModelBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private final Label buttonLabel;

	/** The submit button. */
	@Getter
	private final Button submitButton;

	/** The form. */
	@Getter
	private final Form<?> form;

	/**
	 * Instantiates a new {@link ChangePasswordFormPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the component model
	 */
	public ChangePasswordFormPanel(final String id, final IModel<ChangePasswordModelBean> model)
	{
		super(id, model);
		form = newForm("form", model);
		add(form);
		form.add(new ChangePasswordPanel("changePasswordPanel", model));
		// Create submit button for the form
		submitButton = newButton("submitButton");
		submitButton.add(
			buttonLabel = newButtonLabel("buttonLabel", "global.update.button.label", "Update"));
		form.add(submitButton);
	}

	/**
	 * Factory method for creating the new {@link Button}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Button}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Button}
	 */
	protected Button newButton(final String id)
	{
		return new Button(id)
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onSubmit()
			{
				onChangePassword(null);
			}
		};
	}

	/**
	 * Factory method for creating the new {@link Label} for the button. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link Label} for the button.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the new {@link Label} for the button.
	 */
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue)
	{
		return ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(resourceKey, this, defaultValue));
	}

	/**
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Form}
	 */
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Abstract callback method that is called when the action on changin the password.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onChangePassword(final AjaxRequestTarget target);

}
