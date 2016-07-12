package com.tailoredshapes.smash;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class ServerModule implements Module {
    private final String host;
    private final int port;

    public ServerModule(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void configure(Binder binder) {

        binder.bind(String.class)
                .annotatedWith(Names.named("host"))
                .toInstance(host);

        binder.bind(Integer.class)
                .annotatedWith(Names.named("port"))
                .toInstance(port);

        binder.bind(String.class)
                .annotatedWith(Names.named("protocol"))
                .toInstance("http");
    }
}

