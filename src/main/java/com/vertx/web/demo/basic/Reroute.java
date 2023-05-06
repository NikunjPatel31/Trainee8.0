package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class Reroute extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.get("/some/path").handler(ctx -> {

      ctx.put("foo", "bar");
      ctx.next();

    });

    router.get("/some/path/C").handler(ctx -> {
      System.out.println("---->----->----->" + ctx.request().params());
      ctx.json(new Student(1, "def"));
    });

    router.get("/some/path/B").handler(ctx -> {
      System.out.println("---->----->" + ctx.request().params());
      ctx.reroute("/some/path/C");
    });

    router.get("/some/path/A").handler(ctx -> {
      System.out.println("---->" + ctx.request().params());
      ctx.reroute("/some/path/B");
    });


    router.get("/some/path").handler(ctx -> {
      System.out.println("Params:" + ctx.request().params());
      System.out.println("Rerouting!!!");
//      ctx.response().setStatusCode(400);

      ctx.put("param1", "Hello");
      ctx.reroute("/some/path/A?msg=kyubhai");
    });

    server.requestHandler(router).listen(8184).onComplete(ready -> {
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
