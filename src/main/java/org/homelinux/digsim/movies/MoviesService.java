package org.homelinux.digsim.movies;

import java.util.List;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
public interface MoviesService {
	public List<Movie> findAll();

	public Movie saveMovie(Movie movie);

	public Movie findOne(long id);

	public List<Movie> findByTitle(String title);

	public boolean exists(String title);

	public List<Movie> findSixPlusMovies();

	public void delete(long id);
}
