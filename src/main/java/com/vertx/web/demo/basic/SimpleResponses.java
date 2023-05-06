package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.ArrayList;
import java.util.List;

public class SimpleResponses extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    //The response is returned in JSON.
    router.get("/employees/add").<JsonObject>respond(context -> Future.succeededFuture(new JsonObject().put("message", "Hello World!")));

    //If there is an error processing the handler, a proper error is returned.
    router.get("/employees/error").<JsonObject>respond(context -> {
      int x = 10;
      if (x == 10) {
        throw new RuntimeException();
      }
      return Future.succeededFuture(new JsonObject().put("message", "Hello World!"));
    });

    // this handler will ensure that the Pojo is serialized to json
    router.get("/students").<Student>respond(context -> Future.succeededFuture(new Student(1, "Chandresh")));

    router.get("/students/list").<List<Student>>respond(context -> {
      List<Student> s = new ArrayList<>();
      s.add(new Student(1, "Chandresh"));
      s.add(new Student(2, "Nikunj"));
      return Future.succeededFuture(s);
    });

    router.get("/some/path").respond(ctx -> ctx.response().putHeader("Content-Type", "text/plain").end("hello world!"));

    router.get("/some/more/path")
      // in this case, the handler ensures that the connection is ended
      .respond(ctx -> ctx.response().setChunked(true).write("Write some text..."));

    server.requestHandler(router).listen(9090).onComplete(ready -> {
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
