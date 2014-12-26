package com.example;

import javax.inject.Named;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MyModule extends AbstractModule {
	
	@Override
	protected void configure() {

	}
	
	@Provides
	@Named("template")
	public String provideTemplate(MyAppConfiguration myAppConfiguration) {
		return myAppConfiguration.getTemplate();
	}

	@Provides
	@Named("defaultName")
	public String provideDefaultName(MyAppConfiguration myAppConfiguration) {
		return myAppConfiguration.getDefaultName();
	}

	@Provides
	@Named("redisPool")
	public JedisPool provideJedisPool(MyAppConfiguration applicationMyAppConfiguration) {
		RedisConfiguration redisConfig = applicationMyAppConfiguration.getRedis();
		return new JedisPool(new JedisPoolConfig(), redisConfig.getHostname(), redisConfig.getPort());
	}
}