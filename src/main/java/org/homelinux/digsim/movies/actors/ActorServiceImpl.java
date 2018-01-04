package org.homelinux.digsim.movies.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Digsim
 * Created on 04/01/18.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActorServiceImpl implements ActorService {

	private static Logger LOG = LoggerFactory.getLogger(ActorService.class);

	@Autowired
	private SimpleActorsRepository actorBd;

	private Cache<String, List<Actor>> cache;

	@PostConstruct
	protected void initialize() {
		Actor a = new Actor("Harrison", "Ford");
		saveActor(a);
		a = new Actor("The", "Rock");
		saveActor(a);

		CachingProvider jcacheProvider = Caching.getCachingProvider();
		CacheManager jcacheManager = jcacheProvider.getCacheManager();
		MutableConfiguration<String, List<Actor>> configuration = new MutableConfiguration<>();
		configuration.setStoreByValue(true).setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 3)));
		cache = jcacheManager.createCache("actorscache", configuration);
	}

	@Override
	public List<Actor> findAll() {
		List<Actor> cachedActors = cache.get("one");
		List<Actor> actors;
		if (cachedActors != null && !cachedActors.isEmpty()) {
			LOG.debug("Cache hit");
			actors = cachedActors;
		}
		else {
			LOG.debug("Nothing found in Cache");
			actors = actorBd.findAll();
			cache.put("one", actors);
		}
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
}
