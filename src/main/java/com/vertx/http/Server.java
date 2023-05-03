package com.vertx.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServer;


public class Server extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    HttpServer server = vertx.createHttpServer();

    /*server.requestHandler(request -> {

      System.out.println("\n\n");

      System.out.println(request.uri());

      System.out.println(request.absoluteURI());

      System.out.println(request.version());

      System.out.println(request.method());

      System.out.println(request.path());

      System.out.println(request.query());

      //      System.out.println(request.headers());

      System.out.println(request.host());

      System.out.println("Params:"+request.params());

      System.out.println(request.remoteAddress());

//      request.endHandler(event -> {
//        System.out.println("read full request");
//      });

//      request.handler(buffer->{
//        System.out.println("Chunk:"+buffer.toString());
//      });

      request.bodyHandler(buffer -> {
        System.out.println("Chunk:" + buffer.toString());
      });

      request.response().send("Hello From Vert.x!");
    }).listen(8090, "0.0.0.0").onComplete(event -> {
      if (event.succeeded())
      {
        System.out.println("Successfully Started Web Server! ");
      }
      else
      {
        System.out.println("There is some issue in starting web server " + event.cause().getMessage());
      }
    });*/

    server.requestHandler(request -> {
      request.setExpectMultipart(true);

      request.endHandler(v -> {
        MultiMap formAttributes = request.formAttributes();
        System.out.println(formAttributes);
        request.response().send("Successfully Received Your Data!");
      });

    }).listen(8091, "0.0.0.0").onComplete(event -> {
      if (event.succeeded())
      {
        System.out.println("Successfully Started Web Server! ");
      }
      else
      {
        System.out.println("There is some issue in starting web server " + event.cause().getMessage());
      }
    });
  }

}
