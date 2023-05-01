package com.vertx.tcpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;


public class Receiver extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    Buffer buffer = Buffer.buffer().appendFloat(12.34f).appendInt(123);

    NetClient client = vertx.createNetClient();

    client.connect(8080, "localhost").onComplete(result -> {
      if (result.succeeded())
      {
        System.out.println("Receiver....Connected! Successfully!");
        NetSocket socket = result.result();

        socket.write(buffer).onComplete(result1 -> {
          if (result1.succeeded())
          {
            System.out.println("Receiver....Message Sent Successfully!");
          }
        });

        socket.handler(handler -> {
          System.out.println("Receiver....Some Data Received!");
          System.out.println(handler.getString(0, 7));
        });

        socket.closeHandler(v -> {
          System.out.println("The socket has been closed");
        });
      }
      else
      {
        System.out.println("Connection Refused!" + result.cause().getMessage());
      }

    });
  }

}
