package org.homelinux.digsim.movies.actors;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
public class ActorForm {
	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotBlank(message = ActorForm.NOT_BLANK_MESSAGE)
	private String firstname;

	@NotBlank(message = ActorForm.NOT_BLANK_MESSAGE)
	private String lastname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Actor createActor() {
		return new Actor(getFirstname(), getLastname());
	}
}
