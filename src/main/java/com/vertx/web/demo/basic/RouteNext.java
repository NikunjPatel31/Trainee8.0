package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RouteNext extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.get("/employees/get").handler(context -> {
      context.response().setChunked(true);

      context.response().write("Hello World From Handler1!");
      vertx.setTimer(5000, tid -> {
        context.next();
      });
    });

    // What if????
    /*router.post("/employees/get").handler(context -> {
      context.response().end("Hello World From Handler2!");
    });*/

    router.get("/employees/get").handler(context -> {
      context.response().end("Hello World From Handler2!");
    });

    server.requestHandler(router).listen(8081).onComplete(ready -> {
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
