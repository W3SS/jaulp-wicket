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
package de.alpharogroup.wicket.components.listview;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.BasePanel;
import lombok.Getter;

/**
 * The Class RefreshingViewPanel takes a {@link org.apache.wicket.markup.repeater.RefreshingView} of
 * a generic type.
 *
 * @param <T>
 *            the generic type of model object
 */
public abstract class RefreshingViewPanel<T extends Serializable> extends BasePanel<List<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The {@link RefreshingView}. */
	@Getter
	private final RefreshingView<T> refreshingView;

	/**
	 * Instantiates a new {@link de.alpharogroup.wicket.components.listview.RefreshingViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public RefreshingViewPanel(final String id, final IModel<List<T>> model)
	{
		super(id, Args.notNull(model, "model"));
		add(refreshingView = newRefreshingView("refreshingView", model));
	}

	/**
	 * Instantiates a new {@link de.alpharogroup.wicket.components.listview.RefreshingViewPanel}.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 */
	public RefreshingViewPanel(final String id, final List<T> list)
	{
		this(id, new ListModel<>(list));
	}

	/**
	 * Factory method for creating a new {@link Item}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of the
	 * new {@link Item}.
	 *
	 * @param id
	 *            the id
	 * @param index
	 *            the index
	 * @param model
	 *            the model
	 * @return the new {@link Item}.
	 */
	protected Item<T> newItem(final String id, final int index, final IModel<T> model)
	{
		return new Item<T>(id, index, model);
	}

	/**
	 * Abstract factory method for creating the new {@link Component} in the list. This method is
	 * invoked in the {@link ListView#populateItem(ListItem)} from the derived classes and can be
	 * overridden so users can provide their own version of a new {@link Component} in the list.
	 *
	 * @param id
	 *            the id
	 * @param item
	 *            the item
	 * @return the new {@link Component} in the list.
	 */
	protected abstract Component newListComponent(final String id, final Item<T> item);

	/**
	 * Factory method for creating a new {@link RefreshingView}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of the new {@link RefreshingView}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link RefreshingView}.
	 */
	protected RefreshingView<T> newRefreshingView(final String id, final IModel<List<T>> model)
	{
		final RefreshingView<T> listView = new RefreshingView<T>(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Iterator<IModel<T>> getItemModels()
			{
				return new ModelIteratorAdapter<T>(getModelObject().iterator())
				{

					/**
					 * {@inheritDoc}
					 */
					@Override
					protected IModel<T> model(final T object)
					{
						return Model.of(object);
					}
				};
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected Item<T> newItem(final String id, final int index, final IModel<T> model)
			{
				return RefreshingViewPanel.this.newItem(id, index, model);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(final Item<T> item)
			{
				item.add(newListComponent("item", item));
			}

		};
		listView.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());
		return listView;
	}
}
