package com.example.Demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    String message = "hello from dhaval";

    System.out.println("hello from http");
    vertx.createHttpServer().requestHandler(req -> {


      vertx.executeBlocking(hashCode->{

        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        req.response()
          .putHeader("content-type", "text/plain")
          .putHeader("Content-Length", Integer.toString(message.length()))
          .write("hello from dhaval ddd");

        System.out.println(req.connection().remoteAddress());
      });

    }).listen(8888 ,hanler ->{


      if(hanler.succeeded())
      {
        System.out.println("http server started 8888");
        startPromise.complete();
      }
      else
      {
        startPromise.fail("fail");
      }
    });
  }
}
