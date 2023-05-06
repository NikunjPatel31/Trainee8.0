package com.vertx.http.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class FileResponseServer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            request.response().sendFile("/home/chandresh/Downloads/T1 (2).png").onComplete(result -> {
                if (result.succeeded()) {
                    System.out.println("File Sent Successfully");
                } else {
                    System.out.println("Some Error Occurred!" + result.cause().getMessage());
                }
            });
        }).listen(8092, "0.0.0.0");
    }
}
