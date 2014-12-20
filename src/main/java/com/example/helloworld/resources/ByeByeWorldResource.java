package com.example.helloworld.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.core.Saying;
import com.google.common.base.Optional;
import com.google.inject.Inject;

@Path("/ByeBye-World")
@Produces(MediaType.APPLICATION_JSON)
public class ByeByeWorldResource {
    private final String defaultName;
    private final AtomicLong counter;

    @Inject
    public ByeByeWorldResource(String defaultName) {
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayByeBye(@QueryParam("name") Optional<String> names) {
        final String value = String.format("Die die DIIIIIEEEE %s motafoca", names.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}