package org.homelinux.digsim.movies.actors;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Digsim
 * Created on 03/01/18.
 */
@Component
public class SimpleActorsRepository implements ActorsRepository {

	@Override
	//@Cacheable("actors")
	public List<Actor> getByFirstname(String name) {
		simulateSlowService();
		Actor a = new Actor();
		a.setFirstname(name);
		a.setLastname("Ford");
		a.setId(5L);
		return Arrays.asList(a);
	}

	public List<Actor> findAll() {
		simulateSlowService();
		List<Actor> actors = new ArrayList<>();
		Actor a = new Actor();
		a.setFirstname("Perfect");
		a.setLastname("Ford");
		a.setId(5L);
		actors.add(a);
		return actors;

	}

	// Don't do this at home
	private void simulateSlowService() {
		try {
			long time = 10000L;
			Thread.sleep(time);
		}
		catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
