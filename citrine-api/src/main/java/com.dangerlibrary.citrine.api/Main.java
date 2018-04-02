package com.dangerlibrary.citrine.api;

import com.dangerlibrary.citrine.api.resource.UnitApplication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;


public class Main {

    private static final String ROOT = "/";

    public Main() {}

    public static void main( String[] args ) throws Exception {
        try {
            new Main().run();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void run() throws Exception {
        final int port = 8080;
        final Server server = new Server(port);

        final ServletContextHandler context = new ServletContextHandler(server, ROOT);
        final ServletHolder servlet = new ServletHolder(new HttpServletDispatcher());
        servlet.setInitParameter("resteasy.servlet.mapping.prefix",ROOT);
        servlet.setInitParameter("javax.ws.rs.Application", UnitApplication.class.getCanonicalName());
        context.addServlet(servlet,  "/*");

        server.start();
        server.join();
    }
}

