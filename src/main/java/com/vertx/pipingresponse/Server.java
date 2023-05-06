package com.vertx.pipingresponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import jdk.jfr.Frequency;

public class Server extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request->{
            request.response().setChunked(true);

            request.pipeTo(request.response());
        }).listen(8192,"0.0.0.0");
    }
}
