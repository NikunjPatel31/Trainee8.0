package com.example.starter.verticles.Client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class Server extends AbstractVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(Server.class.getName());
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var server = vertx.createHttpServer();

    server
      .requestHandler(request ->
      {

        System.out.println("Dhavalllll: "+request.headers());

        request.response().setChunked(true);

        request.handler(requestHandler ->
        {
          System.out.println(requestHandler);

          System.out.println("Chunk Received!"+requestHandler.length());

          /*request.response()
            .putHeader("content-length", "15")
            .putHeader("content-type", "text/plain")
            .write("hello").onComplete(result -> {
              if (result.succeeded())
              {

              }
              else
              {
                System.out.println("First write: "+result.cause().getMessage());
              }
            });*/

          request.response().write("This is first hello").onFailure(handler -> {
            System.out.println(handler.getMessage());
          }).onSuccess(handler -> {
            System.out.println("response send...");
          });
          request.response().write("This is second hello");

          System.out.println(request.response().headers());
        });

      })
      .listen(6001)
      .onComplete(handler ->
      {
        if (handler.succeeded())
        {
          System.out.println("Server is listening");
          startPromise.complete();
        } else
        {
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
