package com.example.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.example.core.Saying;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private final Jedis redisPool;

    final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    @Inject
    public HelloWorldResource(@Named("template") String template, @Named("defaultName") String defaultName, @Named("redisPool") JedisPool redisPool) {
        logger.info("Creating a new HelloWorldResource!");
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.redisPool = redisPool.getResource();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        Long counter = this.counter.incrementAndGet();
        logger.info("Saying Hello " + name.or("None..."));
        final String value = String.format(template, name.or(defaultName));
        redisPool.append("Saying", value);
        return new Saying(counter, redisPool.get("Saying"));
    }

}