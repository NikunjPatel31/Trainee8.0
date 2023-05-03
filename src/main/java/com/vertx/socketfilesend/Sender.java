package com.vertx.socketfilesend;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;


public class Sender extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    NetServer server = vertx.createNetServer();

    server.connectHandler(request -> {

      request.handler(System.out::println);

    }).listen(8998).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println("Successfully Started Listening! ");
        startPromise.complete();
      }
      else
      {
        System.out.println("Cannot Listen " + handler.cause().getMessage());
        startPromise.fail("Cannot Listen " + handler.cause().getMessage());
      }
    });
  }

}
