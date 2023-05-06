package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MultipleHandlers extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    Route getEmployeeRoute = router.route("/employee/get");

    getEmployeeRoute.handler(context -> {
      context.response().setChunked(true);

      int i = 0;

      context.response().write("Hello World! From Handler" + i + "\n");

      context.put("index", "" + i);

      vertx.setTimer(3000, tid -> context.next());
    });

    getEmployeeRoute.handler(context -> {
      int i = Integer.parseInt(context.get("index"));
      context.response().write("Hello World! From Handler" + (++i) + "\n");
      context.response().end();
    });

    server.requestHandler(router).listen(8081).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println("Successfully Started Listening!");
        startPromise.complete();
      } else {
        System.out.println("Some Internal Error Occurred!" + ready.cause().getMessage());
        startPromise.fail("Some Internal Error Occurred!" + ready.cause().getMessage());
      }
    });
  }
}
