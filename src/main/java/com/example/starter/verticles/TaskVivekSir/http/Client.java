package com.example.starter.verticles.TaskVivekSir.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;

public class Client extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    var client = vertx.createHttpClient();

    client.request(HttpMethod.GET, 6001,"10.20.40.229", "/")
      .compose(request -> {

        var fileSystem = vertx.fileSystem();

        fileSystem.readFile("file.txt", fileHandler -> {
          if (fileHandler.succeeded())
          {

            System.out.println("File read successful");

            request.send(fileHandler.result());
          }
          else
          {
            System.out.println("File opening failed: "+fileHandler.cause().getMessage());
          }
        });
        return startPromise.future();
      })
        .onComplete(result -> {
          if (result.succeeded())
          {
            System.out.println("Request sent");

            startPromise.complete();
          }
          else
          {
            System.out.println("Request failed to sent");

            startPromise.fail("Failed: "+result.cause().getMessage());
          }
        });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
