package com.edriving.restboot.jetty;

import com.edriving.restboot.http.HttpChannel;
import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JettyHttpChannel implements HttpChannel {
    private final ServerConnector connector;
    private final ServletContextHandler servletContextHandler;
    private final Map<String, Servlet> servlets = new ConcurrentHashMap<>();

    public JettyHttpChannel(ServerConnector connector, HandlerCollection handlerCollection) {
        this.connector = connector;

        servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        servletContextHandler.setContextPath("/");
        servletContextHandler.setVirtualHosts(new String[]{"@" + connector.getPort()});

        handlerCollection.addHandler(servletContextHandler);
    }

    @Override
    public int getPort() {
        return connector.getPort();
    }

    @Override
    public void addServlet(String path, Servlet servlet) {
        ServletHolder servletHolder = new ServletHolder(servlet);
        servletContextHandler.addServlet(servletHolder, path);
        servlets.put(path, servlet);
    }

    @Override
    public Servlet getServlet(String path) {
        return servlets.get(path);
    }

    @Override
    public void setAttribute(String name, Object value) {
        servletContextHandler.setAttribute(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return servletContextHandler.getAttribute(name);
    }
}
