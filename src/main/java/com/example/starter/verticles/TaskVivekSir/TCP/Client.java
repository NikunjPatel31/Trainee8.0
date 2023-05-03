package com.example.starter.verticles.TaskVivekSir.TCP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Client extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var client = vertx.createNetClient();

    client.connect(6001, "10.20.40.229", handler -> {
      if (handler.succeeded())
      {
        var fileSystem = vertx.fileSystem();

        fileSystem.readFile("file.txt", fileHandler -> {
          if (fileHandler.succeeded())
          {
            var socket = handler.result();

            socket.sendFile("file.txt").onComplete(result -> {
              if (result.succeeded())
              {
                System.out.println("File send successful");

                startPromise.complete();
              }
              else
              {
                startPromise.fail(result.cause().getMessage());

                System.out.println("Failed to send file: "+result.cause().getMessage());
              }
            });
          }
          else
          {
            startPromise.fail(fileHandler.cause().getMessage());

            System.out.println("File opening failed: "+fileHandler.cause().getMessage());
          }
        });
      }
      else
      {
        System.out.println("Client handler failed: "+handler.cause().getMessage());

        startPromise.fail(handler.cause().getMessage());
      }
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
