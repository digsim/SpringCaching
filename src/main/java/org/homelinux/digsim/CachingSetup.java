package org.homelinux.digsim;

import org.ehcache.jcache.JCacheCachingProvider;
import org.ehcache.jcache.JCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author AdNovum Informatik AG
 * Created on 04/01/18.
 */
@Configuration
@EnableCaching
public class CachingSetup extends JCacheConfigurerSupport {

	@Bean
	public CacheManager cacheManager() {
		JCacheCacheManager cacheManager = new JCacheCacheManager();
		cacheManager.setCacheManager(new JCacheManager(new JCacheCachingProvider(), ehcache(), null, null));
		return cacheManager;
	}

	private net.sf.ehcache.CacheManager ehcache() {
		net.sf.ehcache.CacheManager cacheManager = new net.sf.ehcache.CacheManager();
		return cacheManager;
	}

	@Bean("simpleCacheManager")
	public SimpleCacheManager simpleCacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("bitfinexAuthCache")));

		return simpleCacheManager;

	}
}
