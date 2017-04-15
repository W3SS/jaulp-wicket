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
package de.alpharogroup.wicket.components.socialnet.twitter.follow;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.alpharogroup.wicket.base.BasePanel;

/**
 * The Class {@link TwitterFollowPanel}.
 */
public class TwitterFollowPanel extends BasePanel<TwitterFollowModelBean>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link TwitterFollowPanel}.
	 *
	 * @param id
	 *            the id
	 */
	public TwitterFollowPanel(final String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new {@link TwitterFollowPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public TwitterFollowPanel(final String id, final IModel<TwitterFollowModelBean> model)
	{
		super(id, model);
		final ExternalLink twitterFollowLink = new ExternalLink("url", model.getObject().getUrl());
		twitterFollowLink.add(
			new AttributeModifier("data-show-count", model.getObject().getShowCount().toString()));
		add(twitterFollowLink);
		final Label twitterNameLabel = new Label("twitterNameLabel",
			model.getObject().getUsername());
		twitterFollowLink.add(twitterNameLabel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		final PackageResourceReference resourceReference = new PackageResourceReference(getClass(),
			"follow.js");
		response.render(JavaScriptHeaderItem.forReference(resourceReference, "twitterFollow"));
	}

}
