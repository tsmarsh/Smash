package com.tailoredshapes.smash;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class Main {
    public static void main(String... args) {

        Yaml yaml = new Yaml();
        Map root = (Map) yaml.load(Main.class.getResourceAsStream("/smash.yml"));
        String config = System.getProperty("config");
        config = config == null ? "test" : config;

        Map envRoot = (Map) root.get(config);

        Integer port = (Integer) envRoot.get("port");

        SmashServer server = new SmashServer("/", port);

        server.start();
        server.join();
    }
}
