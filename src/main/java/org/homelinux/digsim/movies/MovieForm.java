package org.homelinux.digsim.movies;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.homelinux.digsim.account.Account;
import org.homelinux.digsim.signup.EmailExists;
import org.homelinux.digsim.signup.SignupForm;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
public class MovieForm {
	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotBlank(message = MovieForm.NOT_BLANK_MESSAGE)
	private String title;

	private int year;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Movie createMovie() {
		return new Movie(getTitle(), getYear());
	}
}
