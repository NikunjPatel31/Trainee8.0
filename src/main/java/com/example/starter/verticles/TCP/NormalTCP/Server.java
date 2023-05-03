package com.example.starter.verticles.TCP.NormalTCP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

public class Server extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    NetServer server = vertx.createNetServer();

    server.connectHandler(socket -> {
        // connections will be handled here

        Buffer buf = Buffer.buffer().appendString("This is a message from server");

        socket.handler(message -> {
          System.out.println("Message from client: "+message.toString());

          socket.write(buf).onComplete(result -> {
            if (result.succeeded())
            {
              System.out.println("Message from server sent");
              socket.close();
            }
            else
            {
              System.out.println("Failed to send message from server: "+result.cause().getMessage());
            }
          });

        }).closeHandler(closeHandler -> {
          System.out.println("Socket is closed.");
        });



//        socket.write(buf).onComplete(result -> {
//          if (result.succeeded())
//          {
//            System.out.println("message is send!");
//          }
//          else
//          {
//            System.out.println("Error in sending: "+result.cause().getMessage());
//          }
//        });

      })
      .listen(9999, "localhost", handler ->
      {
        if (handler.succeeded()) {
          System.out.println("Server is now listening");

          startPromise.complete();
        } else {
          System.out.println("Fail to bind: " + handler.cause().getMessage());

          startPromise.fail("failed to bind");
        }
      });

  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("Stop called: " + getClass().getName());

    stopPromise.complete();
  }
}
