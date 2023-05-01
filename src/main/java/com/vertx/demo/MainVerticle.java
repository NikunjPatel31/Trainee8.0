package com.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;


public class MainVerticle extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    System.out.println("Hello Verticle from " + Thread.currentThread().getName());

    vertx.createHttpServer().requestHandler(req -> {

      System.out.println("Request Handled By" + Thread.currentThread().getName());
      req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
    })  .listen(8110, http -> {
      if (http.succeeded())
      {
        startPromise.complete();
        System.out.println("HTTP server started on port 8110");
      }
      else
      {
        startPromise.fail(http.cause());
      }
    });
  }

}
