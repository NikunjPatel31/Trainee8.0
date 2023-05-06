package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class PathParameters extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    //capturing simple path parameters
    router.get("/employees/get/:empId").respond(context -> {
      String empID = context.pathParam("empId");
      return Future.succeededFuture("Welcome " + empID);
    });

    //A parameter is not required to be a path segment.
    router.get("/employees/get/:empId-:dep").respond(context -> {
      String empID = context.pathParam("empId");
      String department = context.pathParam("dep");
      System.out.println(empID);
      System.out.println(department);
      return Future.succeededFuture("Welcome " + empID + "...." + department);
    });

    server.requestHandler(router).listen(8080).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println("Server Started Listening");
        startPromise.complete();
      } else {
        System.out.println("Some Internal Error Occurred!" + ready.cause().getMessage());
        startPromise.fail("Some Internal Error Occurred!" + ready.cause().getMessage());
      }
    });
  }
}
