package com.example.starter.verticles.MultipleConnection;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServerOptions;

public class Server1 extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var server = vertx.createNetServer();

    server.connectHandler(handler -> {

    }).listen(9999, "");
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
