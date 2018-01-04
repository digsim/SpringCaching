package org.homelinux.digsim.movies.actors;

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
@RequestMapping(value = "/actors")
public class ActorsController {

	private static Logger LOG = LoggerFactory.getLogger(ActorsController.class);

	@Autowired
	private ActorService actorService;

	@ModelAttribute("module")
	String module() {
		return "actors";
	}

	@GetMapping(value = "/")
	public String getActors(Model model) {
		LOG.debug("Entering getActors()");
		List<Actor> actors = actorService.findAll();
		model.addAttribute("actors", actors);
		return "actors/actors";
	}

	@GetMapping(value = "/add")
	public String addActors(Model model) {
		LOG.debug("Entering addActors()");
		model.addAttribute(new ActorForm());
		return "movies/addMovie";
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping(value = "/add")
	public String add(@Valid @ModelAttribute ActorForm input, Errors errors, RedirectAttributes ra) {
		LOG.debug("Entering add()");
		LOG.debug("Actor Firstname: {}", input.getFirstname());
		if (errors.hasErrors()) {
			LOG.debug("found some errors");
			return "actors/addActor";
		}
		Actor actor = actorService.saveActor(input.createActor());
		// see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
		MessageHelper.addSuccessAttribute(ra, "signup.success");
		LOG.debug("Successfully added actor {}", actor.getFirstname());
		return "redirect:/actors/";
	}

	@RequestMapping(value = "/{id}")
	public String getMovie(@PathVariable long id, Model model) {
		Actor actor = actorService.findOne(id);
		LOG.debug("Found actor {}", actor.getFirstname());
		model.addAttribute("actor", actor);
		if (actor != null) {
			return "actors/actorDetails";
		}
		else {
			return "redirect:/actors/";
		}
	}
}
