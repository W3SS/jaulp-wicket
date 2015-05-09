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
package de.alpharogroup.wicket.annotated.header.contributors.examples.panels.home;

import org.apache.wicket.markup.html.basic.Label;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.base.BasePanel;

/**
 * @author admin
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "HomePanel.css", resourceType = "css", index = 0),
		@ImportResource(resourceName = "HomePanel.js", resourceType = "js", index = 1),
		@ImportResource(resourceName = "HomePanel-new.js", resourceType = "js", index = 2) })
public class HomePanel extends BasePanel<Object>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public HomePanel(String id)
	{
		super(id);

		// Add the simplest type of label
		add(new Label("message",
			"If you see this message wicket is properly configured and running"));
	}

}
