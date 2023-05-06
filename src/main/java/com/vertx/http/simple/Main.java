package com.vertx.http.simple;

import com.vertx.http.simple.FileUploadServer;
import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {

    vertx.deployVerticle(Server.class.getName()).onComplete(event -> {
      if (event.succeeded())
      {
        System.out.println("Verticle Deployed!");
      }
      else
      {
        System.out.println("Fail to deploy verticle" + event.cause().getMessage());
      }
    });

    vertx.deployVerticle(Client.class.getName()).onComplete(event -> {
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
