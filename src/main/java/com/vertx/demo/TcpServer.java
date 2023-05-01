package com.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;


public class TcpServer extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    System.out.println("Starting TCP server");
    NetServer server = vertx.createNetServer();

    server.listen(8080, "localhost", handler -> {
      if (handler.succeeded())
      {
        System.out.println("Server Started On 8080!");
      }
      else
      {
        System.out.println("Faild TO Stat Thread " + handler.cause().getMessage());
      }
    });
  }

}
