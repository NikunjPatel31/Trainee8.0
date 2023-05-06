package com.vertx.http.cookie;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServer;

public class Server extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    HttpServer server = vertx.createHttpServer();

    server.requestHandler(request -> {
      request.response().addCookie(Cookie.cookie("access-token", "ybsuydcbub2b1uyb8723ybuyb").setMaxAge(10000))
        .setStatusCode(200)
        .setStatusMessage("OKK")
        .send();
    }).listen(9091);
  }
}
