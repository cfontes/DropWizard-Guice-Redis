package com.example.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.example.helloworld.healt.TemplateHealthCheck;
import com.example.helloworld.resources.ByeByeWorldResource;
import com.example.helloworld.resources.HelloWorldResource;
import com.hubspot.dropwizard.guice.GuiceBundle;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    private GuiceBundle<HelloWorldConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<HelloWorldConfiguration>newBuilder()
                                 .addModule(new HelloWorldModule())
                                 .enableAutoConfig(getClass().getPackage().getName())
                                 .setConfigClass(HelloWorldConfiguration.class)
                                 .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        environment.jersey().register(new HelloWorldResource(configuration.getTemplate(),
                                                             configuration.getDefaultName()));
        environment.jersey().register(new ByeByeWorldResource(configuration.getDefaultName()));
        environment.healthChecks().register("template", new TemplateHealthCheck(configuration.getTemplate()));

    }

}