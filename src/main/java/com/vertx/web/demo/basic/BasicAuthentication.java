package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthenticationHandler;
import io.vertx.ext.web.handler.BasicAuthHandler;

public class BasicAuthentication extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    Router subRouter = Router.router(vertx);

//    AuthenticationHandler authenticationHandler = BasicAuthHandler.create();

//    router.route("/private/*").handler(authenticationHandler).subRouter(subRouter);

    subRouter.get("/get").respond(context -> Future.succeededFuture("Shhh!!! You're name is chandresh!"));

  }
}
