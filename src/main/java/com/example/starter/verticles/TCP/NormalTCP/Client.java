package com.example.starter.verticles.TCP.NormalTCP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;

public class Client extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var client = vertx.createNetClient();

    client.connect(6001, "10.20.40.229", handler -> {
      if (handler.succeeded())
      {
        var socket = handler.result();

        socket.write("Hello this is client...");

        socket.handler(message -> {
          if (handler.succeeded())
          {
            System.out.println("reply from server: "+message.toString());
          }
          else
          {
            System.out.println("Hanlder failed in client: "+handler.cause().getMessage());
          }
        });

        startPromise.complete();
      }
      else
      {
        System.out.println(handler.cause().getMessage());

        startPromise.fail("Failed to connect");
      }
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    System.out.println("Stop method of: "+getClass().getName());
    stopPromise.complete();
  }
}
