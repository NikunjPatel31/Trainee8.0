package com.example.Demo.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.io.File;

public class HttpServerFile extends AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception {



    vertx.createHttpServer().requestHandler(req ->{

      //System.out.println(Long.toString(new  File("/home/dhaval/video.mp4").getTotalSpace()));
      req.response().putHeader("content-type" , "video/mp4")
        .putHeader("Content-Length" , Long.toString(new  File("/home/dhaval/video.mp4").length()))
        .setChunked(true)
        .sendFile("/home/dhaval/video.mp4")

        .onSuccess(handler ->{
        System.out.println("sucess fully sent file");
      });

    }).listen(8888).onComplete(handler ->{
      System.out.println("server is started on 8888");
    });
  }

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new HttpServerFile());

  }
}
