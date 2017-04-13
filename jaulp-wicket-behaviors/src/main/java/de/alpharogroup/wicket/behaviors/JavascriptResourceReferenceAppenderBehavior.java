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
package de.alpharogroup.wicket.behaviors;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.TextTemplateResourceReference;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;

/**
 * The Class AddJsResourceReferenceBehavior adds a javascript file to given WebPage class as a
 * JavaScriptHeaderItem.
 */
public class JavascriptResourceReferenceAppenderBehavior extends Behavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page class. */
	private final Class<? extends Page> pageClass;

	/** The filename from the file that contains the javascript code. */
	private final String filename;

	/**
	 * The unique id for the javascript element. This can be null, however in that case the ajax
	 * header contribution can't detect duplicate script fragments.
	 */
	private final String id;

	/**
	 * Instantiates a new adds the js resource reference behavior.
	 *
	 * @param pageClass
	 *            the page class
	 * @param filename
	 *            the filename
	 * @param id
	 *            the id
	 */
	public JavascriptResourceReferenceAppenderBehavior(final Class<? extends Page> pageClass,
		final String filename, String id)
	{
		Args.notNull(pageClass, "pageClass");
		Args.notNull(filename, "filename");
		this.pageClass = pageClass;
		this.filename = filename;
		if (id == null)
		{
			id = String.valueOf(UUID.randomUUID());
		}
		this.id = id;
	}

	/**
	 * Gets the resource reference.
	 *
	 * @return the resource reference
	 */
	private ResourceReference getResourceReference()
	{
		final Map<String, Object> map = new HashMap<>();
		map.put("url", WicketUrlExtensions.getUrlAsString(pageClass));
		final ResourceReference resourceReference = new TextTemplateResourceReference(pageClass,
			this.filename, "text/javascript", Model.ofMap(map));
		return resourceReference;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final Component component, final IHeaderResponse response)
	{
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(getResourceReference(), this.id));
	}

}
