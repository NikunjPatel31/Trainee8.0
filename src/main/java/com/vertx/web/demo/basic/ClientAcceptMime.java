package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class ClientAcceptMime extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

//    router.route("/some/path").consumes("application/json").produces("text/plain").respond(context -> Future.succeededFuture("Hello From text/plain"));

//    router.route("/some/path").consumes("application/json").produces("application/json").respond(context -> Future.succeededFuture(new JsonObject().put("msg", "hello-world")));

    /*router.route("/some/more/path").consumes("application/json").produces("text/plain").produces("application/json").respond(context -> {
      System.out.println(context.getAcceptableContentType());
      return Future.succeededFuture(new JsonObject().put("Hello", "hii")); //whatever it produces, respond adds content-type by the value type in future.
    });*/

    router.get("/some/more/path").consumes("application/json").produces("text/plain").respond(context -> {
      System.out.println(context.getAcceptableContentType());
      return Future.succeededFuture(new JsonObject().put("Hello", "hii")); //whatever it produces, respond adds content-type by the value type in future.
    });

    server.requestHandler(router).listen(8182).onComplete(ready -> {
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
