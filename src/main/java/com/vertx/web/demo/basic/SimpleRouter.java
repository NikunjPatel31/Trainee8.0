package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class SimpleRouter extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.route().handler(request -> {

      HttpServerResponse response = request.response();

      response.putHeader("content-length", "12");

      response.end("Hell0 World!");
    });

    server.requestHandler(router).listen(8080).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println("Server Started Listening!");
        startPromise.complete();
      } else {
        System.out.println("Some Internal Error Occurred! " + ready.cause().getMessage());
        startPromise.fail("Some Internal Error Occurred! " + ready.cause().getMessage());
      }
    });
  }
}
