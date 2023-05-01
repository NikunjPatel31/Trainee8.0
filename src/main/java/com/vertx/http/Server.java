package com.vertx.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;


public class Server extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    var server = vertx.createHttpServer();

    server.requestHandler(request -> {
      request.body(fileContent -> {
        if (fileContent.succeeded())
        {
          System.out.println(fileContent.result());

          var fileSystem = vertx.fileSystem();

          fileSystem.createFile("file.txt").onComplete(result -> {
            if (result.succeeded())
            {
              // create file successful
              System.out.println("File created successfully");
              fileSystem.writeFileBlocking("file.txt", fileContent.result());
            }
            else
            {
              System.out.println("Failed to create file: " + result.cause().getMessage());
            }
          });
        }
        else
        {
          System.out.println("failed");
        }
      });
    }).listen(6001,"10.20.40.229").onComplete(result -> {
      if (result.succeeded())
      {
        System.out.println("Server is listening");
        startPromise.complete();
      }
      else
      {
        System.out.println("Failed to listen: " + result.cause().getMessage());

        startPromise.fail("Failed: " + result.cause().getMessage());
      }
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {

    stopPromise.complete();
  }

}
