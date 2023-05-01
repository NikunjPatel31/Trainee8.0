package com.vertx.tcpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;


public class Sender extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    NetServer server = vertx.createNetServer();

    server.connectHandler(socket -> {
      socket.handler(buffer -> {
        System.out.println("Sender....Received Some Content: " + buffer.length() + "\n");

        System.out.println(buffer.getFloat(0));
        System.out.println(buffer.getInt(1));
      });

      socket.write("Hello World!").onComplete(result -> {
        if (result.succeeded())
        {
          System.out.println("Sender....Message Sent Successfully!");
        }
      });

      socket.closeHandler(v -> {
        System.out.println("The socket has been closed");
      });
      
    }).listen(8080);
  }

}
