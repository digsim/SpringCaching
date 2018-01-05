package org.homelinux.digsim.movies.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActorServiceImpl implements ActorService {

	private static Logger LOG = LoggerFactory.getLogger(ActorService.class);

	@Autowired
	private ActorsRepository actorBd;

	@PostConstruct
	protected void initialize() {
		Actor a = new Actor("Harrison", "Ford");
		saveActor(a);
		a = new Actor("The", "Rock");
		saveActor(a);
	}

	@Override
	public List<Actor> findAll() {
		LOG.debug("In the Service class");
		List<Actor> actors = actorBd.findAll();
		return actors;
	}

	@Override
	public Actor saveActor(Actor actor) {
		return null;
	}

	@Override
	public Actor findOne(long id) {
		return null;
	}

	@Override
	public List<Actor> findByFirstname(String firstname) {
		return actorBd.getByFirstname(firstname);
	}

	@Override
	public boolean exists(String firstname) {
		return false;
	}

	@Override
	public void delete(long id) {

	}

	@Override
	public List<Actor> findGoodActors() {
		LOG.debug("In the Service class");
		List<Actor> actors = actorBd.findGoodOnes();
		return actors;
	}
}
