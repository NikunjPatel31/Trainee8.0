package com.vertx.pipingresponse;

import io.vertx.core.Vertx;

public class Main {
    private static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        vertx.deployVerticle(Server.class.getName()).onComplete(handler -> {
            if (handler.succeeded()) {
                System.out.println("Verticle Deployed Successfully!");
            } else {
                System.out.println("Some Error Occurred!" + handler.cause().getMessage());
            }
        });
    }
}
