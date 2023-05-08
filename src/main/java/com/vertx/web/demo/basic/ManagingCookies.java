package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class ManagingCookies extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.get("/cookie/get").handler(context -> {
      context.response().addCookie(Cookie.cookie("name", "chandresh"));
      context.response().setChunked(true);
      context.response().end("Got It!");
    });

    router.post("/cookie/add").handler(context -> {
      Cookie cookie = context.request().getCookie("name");
      System.out.println(cookie.getName());
      System.out.println(cookie.getValue());
      System.out.println(cookie.getDomain());
      System.out.println(cookie.getPath());
      System.out.println(cookie.getMaxAge());

      context.response().setChunked(true);
      context.response().end("Hello " + cookie.getValue());
    });

    server.requestHandler(router).listen(8081).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println("Server Started Listening!");
        startPromise.complete();
      } else {
        System.out.println("Some Internal Error Occurred!" + ready.cause().getMessage());
        startPromise.fail("Some Internal Error Occurred!" + ready.cause().getMessage());
      }
    });
  }
}
