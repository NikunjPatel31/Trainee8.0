package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class SubRouter extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router employees = Router.router(vertx);

    Router employeeEndPoints = Router.router(vertx);

    employees.route("/employees/*").subRouter(employeeEndPoints);

    employeeEndPoints.get("/get").handler(context -> {
      context.json(new JsonObject().put("message", "List of all employees!"));
    });

    employeeEndPoints.post("/:emailId").handler(context -> {
      System.out.println(context.pathParam("emailId"));
      context.json(new JsonObject().put("message", "Employee Added!"));
    });

    server.requestHandler(employees).listen(8008).onComplete(ready -> {
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
