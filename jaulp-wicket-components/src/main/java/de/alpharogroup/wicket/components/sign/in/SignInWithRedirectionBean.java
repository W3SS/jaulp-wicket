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
package de.alpharogroup.wicket.components.sign.in;

import org.apache.wicket.Page;

import de.alpharogroup.auth.models.UsernameSignInModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class {@link SignInWithRedirectionBean} captures the data for sign in action with redirection feature.
 *
 * @author Asterios Raptis
 * @deprecated use instead SignInWithRedirectionModel from auth-security
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Deprecated
public class SignInWithRedirectionBean implements UsernameSignInModel
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The email. */
	private String email;

	/** The password. */
	private String password;

	/** The redirect page. */
	private Class<? extends Page> redirectPage;

	/** The username. */
	private String username;
}
