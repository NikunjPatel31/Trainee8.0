package com.vertx.http;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {

    vertx.deployVerticle(FileUploadServer.class.getName()).onComplete(event -> {
      if (event.succeeded())
      {
        System.out.println("Verticle Deployed!");
      }
      else
      {
        System.out.println("Fail to deploy verticle" + event.cause().getMessage());
      }
    });
  }

}
