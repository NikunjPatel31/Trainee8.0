package com.vertx.workingwithfilesystem;

import com.vertx.http.cookie.Client;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class Main {
  private static Vertx vertx = Vertx.vertx();

  private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

  public static void main(String[] args) {
    vertx.deployVerticle(RandomWrite.class.getName()).onComplete(result -> {
      if (result.succeeded()) {
        LOGGER.info("Succeeded");
      } else {
        LOGGER.error("Some Thing Happened! " + result.cause().getMessage());
      }
    });
  }
}
