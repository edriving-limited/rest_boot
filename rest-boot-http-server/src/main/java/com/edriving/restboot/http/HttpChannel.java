package com.edriving.restboot.http;

import jakarta.servlet.Servlet;

public interface HttpChannel {

    int getPort();

    void addServlet(String path, Servlet servlet);

    Servlet getServlet(String path);

    void setAttribute(String name, Object value);

    Object getAttribute(String name);
}
