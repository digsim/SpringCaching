package org.homelinux.digsim.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MoviesServiceImpl implements MoviesService {
	private static Logger LOG = LoggerFactory.getLogger(MoviesService.class);

	@Autowired
	private MoviesRepository moviesBd;

	@PostConstruct
	protected void initialize() {
		Movie a = new Movie("From Dusk Till Dawn", 1995);
		a.setImdbRating(6.7);
		saveMovie(a);
		a = new Movie("Star Trek", 1979);
		a.setImdbRating(8.7);
		saveMovie(a);
		a = new Movie("Star Trek - The search for Spock", 1983);
		a.setImdbRating(8.1);
		saveMovie(a);
	}

	@Override
	public List<Movie> findAll() {
		return moviesBd.findAll();
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return moviesBd.save(movie);
	}

	@Override
	public Movie findOne(long id) {
		Assert.notNull(id, "Id can't be null");
		Movie m = moviesBd.findOne(id);
		return m;
	}

	@Override
	public List<Movie> findByTitle(String title) {
		Assert.notNull(title, "A title must be provided");
		Assert.isTrue(!title.isEmpty(), "Title can't be empty");
		return moviesBd.findByTitle(title);
	}

	public boolean exists(String title){
		List<Movie> movies = findByTitle(title);
		if(movies != null && !movies.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<Movie> findSixPlusMovies() {
		List<Movie> movies =  moviesBd.findHighRatedMovies(new Double(6));
		return movies;
	}

	@Override
	public void delete(long id) {
		moviesBd.delete(id);
	}
}
