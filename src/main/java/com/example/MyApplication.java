package com.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.hubspot.dropwizard.guice.GuiceBundle;

public class MyApplication extends Application<MyAppConfiguration> {


    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<MyAppConfiguration> bootstrap) {
        GuiceBundle<MyAppConfiguration> guiceBundle =
                GuiceBundle.<MyAppConfiguration>newBuilder().addModule(new MyModule())
                           .enableAutoConfig(getClass().getPackage().getName())
                           .setConfigClass(MyAppConfiguration.class).build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(MyAppConfiguration Configuration, Environment environment) {
    }
}