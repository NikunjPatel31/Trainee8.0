package com.vertx.tcpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;


public class RandomPort extends AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    NetServer server = vertx.createNetServer();

    server.connectHandler(socket -> {
      System.out.println(socket.toString());
    }).listen(0, "localhost").onComplete(result -> {
      if (result.succeeded())
      {
        System.out.println("Server Started Listening On: " + server.actualPort());
        startPromise.complete();
      }
      else
      {
        System.out.println("Not able to start the server ");
        startPromise.fail("Not able to start the server " + result.cause().getMessage());
      }
    });
  }

}
