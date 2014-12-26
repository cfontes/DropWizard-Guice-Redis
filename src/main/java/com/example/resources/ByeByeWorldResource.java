package com.example.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.example.core.Saying;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Path("/ByeBye-World")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ByeByeWorldResource {
    private final String defaultName;
    private final AtomicLong counter;

    @Inject
    public ByeByeWorldResource(@Named("defaultName")String defaultName) {
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayByeBye(@QueryParam("name") Optional<String> names) {
        final String value = String.format("Bye bye %s", names.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}