package org.homelinux.digsim.movies.actors;

import java.util.List;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
public interface ActorService {
	public List<Actor> findAll();

	public Actor saveActor(Actor actor);

	public Actor findOne(long id);

	public List<Actor> findByFirstname(String firstname);

	public boolean exists(String title);

	public void delete(long id);
}
