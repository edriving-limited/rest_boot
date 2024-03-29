package com.edriving.commons.rest.boot.server;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.servlet.ServletContainer;

public class WebServer {
    private static final String API_PORT_PROPERTY = "api-port";
    private static final String ADMIN_PORT_PROPERTY = "admin-port";
    private static final String API_PORT_DEFAULT = "8080";

    private static final String PACKAGE_API_PROPERTY = "api-package";
    private static final String PACKAGE_ADMIN_PROPERTY = "admin-package";
    private static final String PACKAGE_SERVER = "com.edriving.commons.rest.boot.server";
    private static final String PACKAGE_ADMIN = "com.edriving.commons.rest.boot.admin";

    private static final String ENABLE_REQUEST_CONTENT_ENCODING_PROPERTY = "enable-request-content-encoding";

    public static void main(String[] args) throws Exception {
        //logs settings
        System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
        System.setProperty("org.eclipse.jetty.LEVEL", "INFO");
        //read parameters PORTS
        int apiPort = Integer.parseInt(System.getProperty(API_PORT_PROPERTY, API_PORT_DEFAULT));
        int adminPort = Integer.parseInt(System.getProperty(ADMIN_PORT_PROPERTY, String.valueOf(apiPort)));
        //read parameters PACKAGES
        String apiPackage = System.getProperty(PACKAGE_API_PROPERTY);
        if (apiPackage == null || apiPackage.trim().isEmpty()) {
            throw new IllegalArgumentException("PLEASE SPECIFY VALUE FOR '" + PACKAGE_API_PROPERTY + "' property. (Example: -D" + PACKAGE_API_PROPERTY + "=com.domain.api)");
        }
        apiPackage = PACKAGE_SERVER + ";" + apiPackage.trim();
        String adminPackage = System.getProperty(PACKAGE_ADMIN_PROPERTY);
        adminPackage = PACKAGE_ADMIN +  (adminPackage == null ? "" : ";" + adminPackage.trim());
        if (adminPort == apiPort) {
            apiPackage = apiPackage + ";" + adminPackage;
        }
        //SERVER
        Server jettyServer = new Server();
        HandlerCollection handlerCollection = new HandlerCollection();
        setConnector("Api", apiPackage, apiPort, jettyServer, handlerCollection);
        if (adminPort != apiPort) {
            setConnector("Admin", adminPackage, adminPort, jettyServer, handlerCollection);
        }
        jettyServer.setHandler(handlerCollection);
        // start server
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Throwable t) {
            System.err.println("An error occurred during server startup:\n");
            t.printStackTrace();
            jettyServer.stop();
        } finally {
            jettyServer.destroy();
        }
    }
    private static void setConnector(String appName, String appPackage, int port, Server jettyServer, HandlerCollection handlerCollection) {
        System.out.println("Setting connector '" + appName + "'; package='" + appPackage + "'; port='" + port + "'." );
        ServletContextHandler apiContext = createServletContextHandler(appName, appPackage, "/*", "/");
        createServerConnector(jettyServer, appName, port);
        handlerCollection.addHandler(apiContext);
    }
    private static ServletContextHandler createServletContextHandler(String appName, String packages, String pathSpec, String contextPath) {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(true, packages);
        resourceConfig.setApplicationName(appName);
        resourceConfig.register(MultiPartFeature.class);
        resourceConfig.register(GsonMessageBodyHandler.class);
        String encodingEnabled = System.getProperty(ENABLE_REQUEST_CONTENT_ENCODING_PROPERTY);
        if (encodingEnabled != null && (
                encodingEnabled.equalsIgnoreCase("yes")
                        || encodingEnabled.equalsIgnoreCase("y")
                        || encodingEnabled.equalsIgnoreCase("true")
                        || encodingEnabled.equalsIgnoreCase("t")
                        || encodingEnabled.equalsIgnoreCase("1")
                )) {
            EncodingFilter.enableFor(resourceConfig, GZipEncoder.class);
        }
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ServletHolder externalServletHolder = new ServletHolder(new ServletContainer(resourceConfig));
        contextHandler.addServlet(externalServletHolder, pathSpec);
        contextHandler.setContextPath(contextPath);
        contextHandler.setVirtualHosts(new String[]{"@" + appName});
        GzipHandler gzip = new GzipHandler();
        gzip.setIncludedMethods("GET", "POST", "PUT", "PATCH");
        gzip.setIncludedMimeTypes("application/json");
        contextHandler.insertHandler(gzip);
        return contextHandler;
    }
    private static void createServerConnector(Server jettyServer, String name, int port) {
        ServerConnector connector = new ServerConnector(jettyServer, new ConnFactory());
        connector.setPort(port);
        connector.setName(name);
        jettyServer.addConnector(connector);
    }
    //TODO make parameter
    private static class ConnFactory extends HttpConnectionFactory {
        private static final HttpConfiguration config = new HttpConfiguration();
        static {
            config.setRequestHeaderSize(16*1024);
            config.setHeaderCacheSize(8*1024);
        }
        public ConnFactory() {
            super(config);
        }
    }
}
