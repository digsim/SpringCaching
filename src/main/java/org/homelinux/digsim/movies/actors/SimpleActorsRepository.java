package org.homelinux.digsim.movies.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Digsim
 * Created on 03/01/18.
 */
@Component
public class SimpleActorsRepository implements ActorsRepository {

	private static Logger LOG = LoggerFactory.getLogger(ActorsRepository.class);

	@Override
	@CacheResult(cacheName = "actors")
	public List<Actor> getByFirstname(String name) {
		simulateSlowService();
		Actor a = new Actor();
		a.setFirstname(name);
		a.setLastname("Ford");
		a.setId(5L);
		return Arrays.asList(a);
	}

	@Override
	@CacheResult(cacheName = "actors")
	public List<Actor> findAll() {
		LOG.debug("Trying to find all actors");
		simulateSlowService();
		List<Actor> actors = new ArrayList<>();
		Actor a = new Actor();
		a.setFirstname("Perfect");
		a.setLastname("Ford");
		a.setId(5L);
		actors.add(a);
		return actors;

	}

	@Override
	@Cacheable(cacheManager = "simpleCacheManager", cacheNames = "bitfinexAuthCache")
	public List<Actor> findGoodOnes() {
		LOG.debug("Trying to find good actors");
		simulateSlowService();
		List<Actor> actors = new ArrayList<>();
		Actor a = new Actor();
		a.setFirstname("Good");
		a.setLastname("One");
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
