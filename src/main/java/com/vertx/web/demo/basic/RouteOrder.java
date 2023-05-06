package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RouteOrder extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.get("/employees/list").order(1).handler(context -> {
      context.response().write("There Are 1 Employee");
      context.next();
    });

    router.get("/employees/list").order(0).handler(context -> {
      context.response().setChunked(true); //must be set in 0th order otherwise we will get content-length vali error.
      context.response().write("There Are 0 Employee");
      context.next();
    });

    router.get("/employees/list").order(2).handler(context -> {
      context.response().write("There Are 2 Employee");
//      context.next(); if we write .next() but if there are no next then we will get exception
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
