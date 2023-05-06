package com.vertx.http.requestresponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class Server extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            request.response().setChunked(true);
            request.bodyHandler(buffer -> {
                request.response().write("Hello Wold!");
                request.response().write("Hiii");

                request.response().end("New World!!!!");
            });
        }).listen(8082, "0.0.0.0");
    }
}
