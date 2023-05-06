package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RouteMime extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.route("/some/path").consumes("text/plain").respond(context -> Future.succeededFuture("Hello From text/plain"));

    router.route("/some/path").consumes("application/json").respond(context -> Future.succeededFuture("Hello From application/json"));

    router.route("/some/path").consumes("text/html").respond(context -> Future.succeededFuture("Hello From text/html"));

    server.requestHandler(router).listen(8183).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println("Successfully stated Listening");
        startPromise.complete();
      } else {
        System.out.println("Some Issue Occurred!" + ready.cause().getMessage());
        startPromise.fail("Some Issue Occurred!" + ready.cause().getMessage());
      }
    });
  }
}
