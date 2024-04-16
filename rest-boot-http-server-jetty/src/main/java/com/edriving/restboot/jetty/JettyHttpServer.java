package com.edriving.restboot.jetty;

import com.edriving.restboot.http.HttpServer;
import com.edriving.restboot.http.HttpChannel;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JettyHttpServer implements HttpServer {
    private final Server server;
    private final HandlerCollection handlerCollection = new HandlerCollection();
    private final Map<Integer, HttpChannel> channels = new ConcurrentHashMap<>();

    JettyHttpServer() {
        QueuedThreadPool connectorThreadPool = new QueuedThreadPool();
        connectorThreadPool.setName("jetty-server");

        server = new Server();

        server.setHandler(handlerCollection);
    }

    @Override
    public HttpChannel createChannel(int port) {
        channels.put(port, newChannel(port));
        return channels.get(port);
    }

    @Override
    public HttpChannel createOrGetChannel(int port) {
        return channels.computeIfAbsent(port, this::newChannel);
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
        server.destroy();
    }

    protected HttpChannel newChannel(int port) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setName(String.valueOf(port));

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSendServerVersion(false);

        connector.addConnectionFactory(new HttpConnectionFactory(httpConfig));

        server.addConnector(connector);

        return new JettyHttpChannel(connector, handlerCollection);
    }
}
