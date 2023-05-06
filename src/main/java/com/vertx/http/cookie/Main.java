package com.vertx.http.cookie;

import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class Main {

  private static Vertx vertx = Vertx.vertx();

  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    vertx.deployVerticle(Server.class.getName()).onComplete(event -> {
      if (event.succeeded()) {
        logger.info("Verticle Deployed!");
      } else {
        logger.error("Fail to deploy verticle" + event.cause().getMessage());
      }
    });

    vertx.deployVerticle(Client.class.getName()).onComplete(event -> {
      if (event.succeeded()) {
        System.out.println("Verticle Deployed!");
      } else {
        System.out.println("Fail to deploy verticle" + event.cause().getMessage());
      }
    });
  }
}
