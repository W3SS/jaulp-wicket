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
package de.alpharogroup.wicket.components.form;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * A list view that renders a list of radio buttons with appropriate labels based on an
 * {@link IChoiceRenderer}. When used in combination with a
 * {@link org.apache.wicket.markup.html.form.RadioGroup}, this a good alternative to Wicket's
 * built-in {@link org.apache.wicket.markup.html.form.RadioChoice}, because it gives you full
 * control over the markup and is extensible.
 * <p>
 * Your markup have to contain the following:
 * <ul>
 * <li>{@code <input type="radio" wicket:id="radio" />} where you want the radio button to appear.
 * </li>
 * <li>A component with {@code wicket:id="label"} where you want the display value to appear.</li>
 * </ul>
 * <p>
 * For example:
 *
 * <pre class="example">
 * &lt;wicket:container wicket:id="group"&gt;
 *   &lt;label wicket:id="choices"&gt;
 *     &lt;input type="radio" wicket:id="radio" /&gt;
 *     &lt;wicket:container wicket:id="label"&gt;Label&lt;/wicket:container&gt;
 *   &lt;/label&gt;
 * &lt;/wicket:container&gt;
 *
 * add(new RadioGroup("group", selectedItemModel)
 *     .add(new RadioChoicesListView("choices", choicesModel, renderer)));
 * </pre>
 * <p>
 * You can also override {@link #populateItem(ListItem) populateItem()} if you want to display
 * additional data per radio choice, like a description paragraph.
 *
 * This class is inspired from fiftyfive.wicket.core project. Some changes with the generic types
 * was done. For more information read this blog:
 * <a href="http://blog.55minutes.com/2011/10/how-to-implement-radio-buttons-in-wicket/" >http://
 * blog.55minutes.com/2011/10/how-to-implement-radio-buttons-in-wicket/</a>
 *
 * @param <T>
 *            the generic type
 */
public class RadioChoicesListView<T> extends ChoicesListView<T>
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a list view for {@link Radio} objects that will expose the specified
	 * IChoiceRenderer for rendering its list items.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the list of choices
	 * @param renderer
	 *            the choice renderer
	 */
	public RadioChoicesListView(final String id, final IModel<List<T>> choices,
		final IChoiceRenderer<T> renderer)
	{
		super(id, choices, renderer);
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param label
	 *            the string for the label
	 * @return the new {@link Label}
	 */
	protected Label newLabel(final String id, final String label)
	{
		return ComponentFactory.newLabel(id, label);
	}

	/**
	 * Factory method for create a new {@link Radio}. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a new
	 * {@link Radio}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param index
	 *            the index
	 * @return the new {@link Radio}.
	 */
	protected Radio<T> newRadio(final String id, final IModel<T> model, final int index)
	{
		final Radio<T> radio = new Radio<T>("radio", model)
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public IModel<String> getLabel()
			{
				return Model.of(getChoiceLabel(getModelObject()));
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected boolean getStatelessHint()
			{
				return true;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getValue()
			{
				return getChoiceValue(getModelObject(), index);
			}
		};
		return radio;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void populateItem(final ListItem<T> item)
	{
		item.add(newLabel("label", getChoiceLabel(item.getModelObject())));
		item.add(newRadio("radio", item.getModel(), item.getIndex()));
	}
}
