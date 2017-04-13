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
package de.alpharogroup.wicket.base.util.parameter;

import org.apache.wicket.util.string.StringValue;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class PageParametersExtensionsTest
{

	@Test
	public void testIsNotNullOrEmpty()
	{
		boolean actual = PageParametersExtensions.isNotNullOrEmpty(null);
		AssertJUnit.assertFalse("Should be false.", actual);
		StringValue sv = StringValue.valueOf("");
		actual = PageParametersExtensions.isNotNullOrEmpty(sv);
		AssertJUnit.assertFalse("Should be false.", actual);
		final String s = null;
		sv = StringValue.valueOf(s);
		actual = PageParametersExtensions.isNotNullOrEmpty(sv);
		AssertJUnit.assertFalse("Should be false.", actual);
	}

	@Test
	public void testIsNullOrEmpty()
	{
		boolean actual = PageParametersExtensions.isNullOrEmpty(null);
		AssertJUnit.assertTrue("Should be true.", actual);
		StringValue sv = StringValue.valueOf("");
		actual = PageParametersExtensions.isNullOrEmpty(sv);
		AssertJUnit.assertTrue("Should be true.", actual);
		final String s = null;
		sv = StringValue.valueOf(s);
		actual = PageParametersExtensions.isNullOrEmpty(sv);
		AssertJUnit.assertTrue("Should be true.", actual);
	}

}
