package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class HelperMethods extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    FileSystem fileSystem = vertx.fileSystem();

    //attachment
    router.get("/get/image").handler(context -> {
      context.attachment("word.jpg");
//      context.response().setChunked(true);

      fileSystem.readFile("/home/chandresh/Downloads/Fathersday_4K.jpg", handler -> {
        if (handler.succeeded()) {
          context.end(handler.result());
        } else {
          context.end("Failed To Load The File");
        }
      });
    });

    //Send a JSON response to the client:
    router.get("/get/json").handler(context -> {
      context.json(new JsonObject().put("name", "chandresh"));
    });

    //Perform a redirect to a different page or host
    router.get("/redirect").handler(context -> {
      context.redirect("https://www.google.com");
//      context.redirect("back");
    });

    //Simple content type check:
    router.get("/check/type").handler(context -> {
      System.out.println(context.is("text/plain"));
      context.json(new JsonObject().put("ans", context.is("text/plain")));
    });

    server.requestHandler(router).listen(8184,"0.0.0.0").onComplete(ready -> {
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
