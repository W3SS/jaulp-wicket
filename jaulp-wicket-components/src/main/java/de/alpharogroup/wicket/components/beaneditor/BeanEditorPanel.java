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
package de.alpharogroup.wicket.components.beaneditor;

import java.lang.reflect.Field;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class BeanEditorPanel.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class BeanEditorPanel<T> extends BasePanel<T>
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The form. */
	@Getter
	private final Form<?> form;
	/** The button of the form. */
	@Getter
	private final Button button;
	/** The button of the form. */
	@Getter
	private final RepeatingView fields;

	/**
	 * Instantiates a new {@link BeanEditorPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public BeanEditorPanel(final String id, final IModel<T> model)
	{
		super(id, model);

		add(form = newForm("form", model));
		form.add(fields = newRepeatingView("fields", model));
		form.add(button = newSubmitButton("button", form));
	}

	/**
	 * Factory method for an editor for the given field.
	 *
	 * @param id
	 *            the id
	 * @param field
	 *            the field
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected abstract Component newEditorForBeanField(final String id, final Field field,
		final IModel<?> model);

	/**
	 * Factory method for creating the Form. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating the RepeatingView. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a
	 * RepeatingView.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 *
	 * @return the RepeatingView
	 */
	protected RepeatingView newRepeatingView(final String id, final IModel<T> model)
	{

		final RepeatingView fields = new RepeatingView("fields");
		form.add(fields);
		final T modelObject = model.getObject();
		for (final Field field : modelObject.getClass().getDeclaredFields())
		{
			// skip serialVersionUID...
			if (field.getName().equalsIgnoreCase("serialVersionUID"))
			{
				continue;
			}
			final WebMarkupContainer row = new WebMarkupContainer(fields.newChildId());
			fields.add(row);

			final IModel<String> labelModel = Model.of(field.getName());

			row.add(new Label("name", labelModel));
			final IModel<?> fieldModel = new PropertyModel<Object>(modelObject, field.getName());

			// Create the editor for the field.
			row.add(newEditorForBeanField("editor", field, fieldModel));

		}
		return fields;
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @return the Button
	 */
	protected Button newSubmitButton(final String id, final Form<?> form)
	{
		final Button button = new AjaxFallbackButton(id, form)
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				BeanEditorPanel.this.onSubmit(target, form);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				BeanEditorPanel.this.onSubmit(target, form);
			}
		};
		return button;
	}

	/**
	 * Hook method for the submit.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
	{
	}
}