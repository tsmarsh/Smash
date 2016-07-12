package com.tailoredshapes.smash;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class SmashServer {

    private final Server server;
    String contextPath;
    int port;
    Logger logger = LoggerFactory.getLogger(SmashServer.class);

    public SmashServer(String contextPath, int port) {
        this.contextPath = contextPath;
        this.port = port;

        server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(server, contextPath, ServletContextHandler.SESSIONS);
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(javax.servlet.DispatcherType.REQUEST, javax.servlet.DispatcherType.ASYNC));

        context.addEventListener(new HibernateServletConfig());

        server.setHandler(context);
    }

    public void start() {
        logger.info("Starting SmashServer on port " + port);
        try {
            server.start();
        } catch (Exception e) {
            logger.error("Server failed to start because: " + e.getMessage());
        }
    }

    public void join() {
        try {
            server.join();
        } catch (InterruptedException e) {
            logger.error("User action interupted the server");
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            logger.error("Had trouble stopping the server: " + e.getMessage());
        }
    }
}
