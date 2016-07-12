package com.tailoredshapes.smash;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.tailoredshapes.clobber.modules.jpa.InventoryRootJPAModule;
import com.tailoredshapes.clobber.modules.jpa.UserRootJPAModule;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class HibernateServletConfig extends GuiceServletContextListener {

    private final Integer port;
    private final String host;

    public HibernateServletConfig() {
        Yaml yaml = new Yaml();
        Map root = (Map) yaml.load(Main.class.getResourceAsStream("/smash.yml"));
        String config = System.getProperty("config");
        config = config == null ? "test" : config;

        Map envRoot = (Map) root.get(config);

        port = (Integer) envRoot.get("port");
        host = (String) envRoot.get("host");
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new JpaPersistModule("smash_server"),
                new ServerModule(host, port),
                new RoutesModule(true, true),
                new UserRootJPAModule(),
                new InventoryRootJPAModule());
    }
}

