package com.example.starter.verticles.TaskVivekSir.TCP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Server extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var server = vertx.createNetServer();

    server
      .connectHandler(socket -> {

        socket.handler(message -> {

        var fileSystem = vertx.fileSystem();

        fileSystem.createFile("file.txt")
          .onComplete(result ->{
            if (result.succeeded())
            {
              // create file successful
              fileSystem.writeFileBlocking("file.txt", message);
            }
            else
            {
              System.out.println("Failed to create file: "+result.cause().getMessage());
            }
          });

        });
      })
      .listen(6001, "10.20.40.229", handler -> {
      if (handler.succeeded())
      {
        startPromise.complete();
      }
      else
      {
        System.out.println("Error in listen handler: "+handler.cause().getMessage());
        startPromise.fail(handler.cause().getCause());
      }
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
