package org.homelinux.digsim.movies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "movie")
public class Movie implements java.io.Serializable{
	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private int year;
	private double imdbRating;

	public Movie(){}

	public Movie(String title, int year){
		this.title = title;
		this.year = year;
		imdbRating = 0d;
	}

	public Long getId() {
		return id;
	}


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

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
}
