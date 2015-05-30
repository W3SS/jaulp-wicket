package de.alpharogroup.wicket.components.labeled;

import java.io.Serializable;

import lombok.Getter;

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentPanel;

/**
 * The Class LabeledTwoFormComponentPanel is a container for two FormComponent. Default they are
 * TextField objects but can be overwritten by the factory methods to return any other input field.
 *
 * @param <L>
 *            the generic type of the model from the left FormComponent
 * @param <R>
 *            the generic type of the model from the left FormComponent
 */
public class LabeledTwoFormComponentPanel<L extends Serializable, R extends Serializable> 
	extends
		LabeledFormComponentPanel<TwoFormComponentBean<L, R>>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The left text field.
	 */
	@Getter
	private TwoFormComponentPanel<L, R> twoFormComponent;

	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public LabeledTwoFormComponentPanel(String id, IModel<TwoFormComponentBean<L, R>> model,
		IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(twoFormComponent = newTwoFormComponentPanel("twoFormComponent", model));

		add(feedback = newComponentFeedbackPanel("feedback", twoFormComponent));

		String markupId = twoFormComponent.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * New left text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected TwoFormComponentPanel<L, R> newTwoFormComponentPanel(String id,
		IModel<TwoFormComponentBean<L, R>> model)
	{
		return LabeledComponentFactory.newTwoFormComponentPanel(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void convertInput()
	{
		setConvertedInput(getModelObject());
	}

}
