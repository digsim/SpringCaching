package org.homelinux.digsim.movies;

import org.homelinux.digsim.support.web.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
@Controller
@RequestMapping(value = "/movies")
public class MoviesControler {

	private static Logger LOG = LoggerFactory.getLogger(MoviesControler.class);

	@Autowired
	private MoviesService moviesService;

	@ModelAttribute("module")
	String module() {
		return "movies";
	}

	@GetMapping(value="/")
	public String getMovies( Model model){
		LOG.debug("Entering getMovies()");
		List<Movie> movies = moviesService.findAll();
		model.addAttribute("movies", movies);
		return "movies/movies";
	}

	@GetMapping(value="/add")
	public String addMovies( Model model){
		LOG.debug("Entering addMovies()");
		model.addAttribute(new MovieForm());
		return "movies/addMovie";
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping(value = "/add")
	public String add(@Valid @ModelAttribute MovieForm input, Errors errors, RedirectAttributes ra) {
		LOG.debug("Entering add()");
		LOG.debug("Movie Title: {}", input.getTitle());
		if (errors.hasErrors()) {
			LOG.debug("found some errors");
			return "movies/addMovie";
		}
		Movie movie = moviesService.saveMovie(input.createMovie());
		// see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
		MessageHelper.addSuccessAttribute(ra, "signup.success");
		LOG.debug("Successfully added movie {}", movie.getTitle());
		return "redirect:/movies/";
	}

	@GetMapping(value="/highRated")
	public String getHighRatedMovies(Model model){
		List<Movie> movies = moviesService.findSixPlusMovies();
		model.addAttribute("movies", movies);
		return "movies/movies";
	}



	@RequestMapping(value="/{id}")
	public String getMovie(@PathVariable long id, Model model){
		Movie movie =  moviesService.findOne(id);
		LOG.debug("Found movie {}", movie.getTitle());
		model.addAttribute("movie", movie);
		if(movie != null) {
			return "movies/movieDetails";
		}
		else{
			return "redirect:/movies/";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMovie(@PathVariable long id){
		moviesService.delete(id);
	}

}
