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
package de.alpharogroup.wicket.model.dropdownchoices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.lang.Args;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class {@link TwoDropDownChoicesModel}.
 *
 * @param <T>
 *            the generic type of the model object
 */
public class TwoDropDownChoicesModel<T> implements IClusterable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The selected root option. */
	@Getter
	@Setter
	private T selectedRootOption;

	/** The selected child option. */
	@Getter
	@Setter
	private T selectedChildOption;

	/** The models map. */
	@Getter
	@Setter
	private Map<T, List<T>> modelsMap = new HashMap<>();

	/** The root choices. */
	@Getter
	private final IModel<List<T>> rootChoices;

	/** The child choices. */
	@SuppressWarnings("unused")
	private IModel<List<T>> childChoices;

	/**
	 * Instantiates a new {@link TwoDropDownChoicesModel}.
	 *
	 * @param selectedOption
	 *            the selected option
	 * @param modelsMap
	 *            the models map
	 */
	public TwoDropDownChoicesModel(final T selectedOption, final Map<T, List<T>> modelsMap)
	{
		this.modelsMap = Args.notNull(modelsMap, "modelsMap");

		rootChoices = new AbstractReadOnlyModel<List<T>>()
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public List<T> getObject()
			{
				final Set<T> keys = modelsMap.keySet();
				final List<T> keysList = new ArrayList<>(keys);
				return keysList;
			}

		};
		this.selectedRootOption = Args.notNull(selectedOption, "selectedOption");

		this.childChoices = getChildChoices();

	}

	/**
	 * Gets the child choices.
	 *
	 * @return the child choices
	 */
	public IModel<List<T>> getChildChoices()
	{
		final T selectedRootOption = TwoDropDownChoicesModel.this.selectedRootOption;
		final IModel<List<T>> childChoices = new AbstractReadOnlyModel<List<T>>()
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public List<T> getObject()
			{
				List<T> models = getModelsMap().get(selectedRootOption);
				if (models == null)
				{
					models = Collections.emptyList();
				}
				return models;
			}

		};
		return childChoices;
	}

}
