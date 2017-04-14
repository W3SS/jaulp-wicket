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
package de.alpharogroup.wicket.components.sign.in.password.reset;

import java.io.Serializable;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.base.pageparameters.ParameterKeys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class {@link ResetPasswordBean} captures the data for reset the password action.
 * 
 * @author Asterios Raptis
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static ResetPasswordBean getResetPasswordBean(final PageParameters parameters)
	{
		final ResetPasswordBean bean = ResetPasswordBean.builder()
			.username(parameters.get(ParameterKeys.USERNAME).toString())
			.confirmationCode(parameters.get(ParameterKeys.CONFIRMATION_CODE).toString().trim())
			.build();
		return bean;
	}

	/** The username. */
	private String username;

	/** The confirmation code. */
	private String confirmationCode;
}
