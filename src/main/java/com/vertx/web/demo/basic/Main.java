package com.vertx.web.demo.basic;

import io.vertx.core.Vertx;

public class Main {
  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args) {

    vertx.deployVerticle(CookieLessSession.class.getName()).onComplete(handler -> {
      if (handler.succeeded()) {
        System.out.println("Verticle Deployed!");
      } else {
        System.out.println(handler.cause().getMessage());
      }
    });
  }
}
