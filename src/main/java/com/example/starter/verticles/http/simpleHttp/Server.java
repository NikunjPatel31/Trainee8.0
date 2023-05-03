package com.example.starter.verticles.http.simpleHttp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class Server extends AbstractVerticle
{

  @Override
  public void start() throws Exception {
    System.out.println("Yash tiwari is a legend...");
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    var serverOption = new HttpServerOptions();

    var server = vertx.createHttpServer();

    server
      .requestHandler(request -> {

        System.out.println(request.remoteAddress());
        String  reply = "hello from server";
        request.setExpectMultipart(true);
        request.response()
          .sendFile("/home/nikunj/Pictures/Screenshot from 2023-01-09 12-28-39.png");
         /* .putHeader("Content-Length", Integer.toString(reply.length()))
          .putHeader("content-type" , "text/plain")
          .write(reply);*/

        request.endHandler(handler -> {
          var formAttribute = request.formAttributes();

          System.out.println("formattribute: "+formAttribute);
        });
      })
      .listen(6001)
      .onComplete(handler -> {
        if (handler.succeeded())
        {
          System.out.println("Server is listening...! on 6001");
          startPromise.complete();
        }
        else
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
