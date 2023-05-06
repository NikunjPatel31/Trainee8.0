package com.vertx.clientserverfile;

import io.vertx.core.Vertx;

public class Main {

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args) {
    vertx.deployVerticle(Server.class.getName()).onComplete(handler -> {
      if (handler.succeeded()) {
        System.out.println("Verticle Deployed!");
      } else {
        System.out.println("Unable to deploy verticle!");
      }
    });
  }
}
