package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class ErrorHandling extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.get("/some/path").handler(context -> {
      context.response().setChunked(true);
      throw new RuntimeException();
    });

    router.route().failureHandler(context -> {
      System.out.println(context.statusCode());
    });

    server.requestHandler(router).listen(8080).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println("Started Listening!");
        startPromise.complete();
      } else {
        System.out.println("Some Internal Error Occurred!" + ready.cause().getMessage());
        startPromise.fail("Some Internal Error Occurred!" + ready.cause().getMessage());
      }
    });
  }
}
