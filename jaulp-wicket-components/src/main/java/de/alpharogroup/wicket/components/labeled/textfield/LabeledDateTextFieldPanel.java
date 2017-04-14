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
package de.alpharogroup.wicket.components.labeled.textfield;

import java.util.Date;
import java.util.Locale;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.labeled.LabeledFormComponentPanel;
import lombok.Getter;

/**
 * Convenience class for labeled DateTextfield.
 *
 * @param <T>
 *            the generic type of model object from the {@link DateTextField}
 * @param <M>
 *            the generic type of model object
 */
public class LabeledDateTextFieldPanel<T, M> extends LabeledFormComponentPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text field. */
	@Getter
	private final DateTextField dateTextField;

	/**
	 * Instantiates a new {@link LabeledDateTextFieldPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public LabeledDateTextFieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);

		setOutputMarkupId(true);

		add(dateTextField = newDateTextField("dateTextField", model));

		add(feedback = newComponentFeedbackPanel("feedback", dateTextField));

		final String markupId = dateTextField.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public FormComponent<T> getFormComponent()
	{
		return (FormComponent<T>)this.dateTextField;
	}

	/**
	 * Factory method for create the new {@link DateTextField}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link DateTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link DateTextField}
	 */
	protected DateTextField newDateTextField(final String id, final IModel<M> model)
	{
		final IModel<Date> textFieldModel = new PropertyModel<>(model.getObject(), getId());

		final DateTextField dateTextField = new DateTextField(id, textFieldModel,
			new StyleDateConverter("S-", true))
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Locale getLocale()
			{
				return getSession().getLocale();
			}
		};
		final DatePicker datePicker = new DatePicker()
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected boolean enableMonthYearSelection()
			{
				return true;
			}
		};
		datePicker.setShowOnFieldClick(true);
		dateTextField.add(datePicker);
		dateTextField.setOutputMarkupId(true);
		return dateTextField;
	}

}
