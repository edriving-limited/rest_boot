package com.edriving.restboot.http;


public interface HttpServer {

    HttpChannel createChannel(int port);

    HttpChannel createOrGetChannel(int port);

    void start() throws Exception;

    void stop() throws Exception;
}
