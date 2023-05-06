package com.vertx.http.cookie;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class Client extends AbstractVerticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(Client.class.getName());

  @Override
  public void start() throws Exception {
    HttpClient client = vertx.createHttpClient();

    client.request(HttpMethod.GET, 9091, "localhost", "/").onComplete(request -> {
      if (request.succeeded()) {
        HttpClientRequest clientRequest = request.result();

        LOGGER.info("Connected Successfully!");

        clientRequest.send().onComplete(response -> {
          if (response.succeeded()) {
            LOGGER.info(response.result().cookies());
          } else {
            LOGGER.info(response.cause().getMessage());
          }
        });
      } else {
        LOGGER.info(request.cause().getMessage());
      }
    });

  }
}
