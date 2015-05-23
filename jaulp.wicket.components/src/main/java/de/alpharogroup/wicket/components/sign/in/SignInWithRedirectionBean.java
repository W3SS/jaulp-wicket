package de.alpharogroup.wicket.components.sign.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.wicket.Page;

import de.alpharogroup.auth.models.UsernameSignInModel;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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