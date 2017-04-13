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
package de.alpharogroup.wicket.components.radio;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class AjaxRadioPanel.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AjaxRadioPanel<T extends Serializable> extends BasePanel<RadioGroupModelBean<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * the form.
	 */
	@Getter
	private Form<?> form;

	/**
	 * the radio group.
	 */
	@Getter
	private RadioGroup<T> radioGroup;

	/**
	 * Instantiates a new {@link AjaxRadioPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public AjaxRadioPanel(final String id, final IModel<RadioGroupModelBean<T>> model)
	{
		super(id, model);
		add(form = newForm("form"));
		form.add(radioGroup = newRadioGroup("radioGroup",
			new PropertyModel<T>(model.getObject(), "selected")));
		radioGroup.add(newRadios(radioGroup, model));
	}

	/**
	 * Factory method for create the new {@link AjaxRadio}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link AjaxRadio}.
	 *
	 * @param id
	 *            the id
	 * @param group
	 *            the group
	 * @param item
	 *            the item
	 * @return the new {@link AjaxRadio}.
	 */
	protected AjaxRadio<T> newAjaxRadio(final String id, final RadioGroup<T> group,
		final ListItem<T> item)
	{
		return new AjaxRadio<T>("radio", item.getModel())
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				onRadioSelect(target, group.getModelObject());
			}
		};
	}

	/**
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Form}
	 */
	protected Form<?> newForm(final String id)
	{
		return ComponentFactory.newForm(id);
	}

	/**
	 * Factory method for create the new {@link RadioGroup}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link RadioGroup}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link RadioGroup}
	 */
	protected RadioGroup<T> newRadioGroup(final String id, final IModel<T> model)
	{
		return ComponentFactory.newRadioGroup(id, model);
	}

	/**
	 * Factory method for create the new {@link ListView} for the {@link AjaxRadio} objects. This
	 * method is invoked in the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a new {@link ListView} for the {@link AjaxRadio} objects.
	 *
	 * @param group
	 *            the group
	 * @param model
	 *            the model
	 * @return the new {@link ListView} for the {@link AjaxRadio} objects.
	 */
	protected Component newRadios(final RadioGroup<T> group, final IModel<RadioGroupModelBean<T>> model)
	{
		return new ListView<T>("radioButtons", model.getObject().getRadios())
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<T> item)
			{
				final AjaxRadio<T> radio = newAjaxRadio("radio", group, item);
				final Label label = ComponentFactory.newLabel("label", radio.getMarkupId(),
					new PropertyModel<String>(item.getModel(),
						model.getObject().getLabelPropertyExpression()));
				item.add(radio);
				item.add(label);
			}
		};
	}

	/**
	 * Abstract callback method to provide special behavior when an radio button is selected.
	 *
	 * @param target
	 *            the target
	 * @param newSelection
	 *            the new selection
	 */
	protected abstract void onRadioSelect(final AjaxRequestTarget target, final T newSelection);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(
			new JavaScriptResourceReference(AjaxRadioPanel.class, "AjaxRadioPanel.js")));
	}
}
