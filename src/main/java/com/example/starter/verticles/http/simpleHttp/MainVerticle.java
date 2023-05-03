package com.example.starter.verticles.http.simpleHttp;

import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(Server.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
//        vertx.deployVerticle(Client.class.getName())
//          .onComplete(result -> {
//            if (result.succeeded())
//            {
//              System.out.println("Client deployed...");
//            }
//            else
//            {
//              System.out.println(result.cause().getMessage());
//            }
//          });
      }
      else
      {
        System.out.println(handler.cause().getMessage());
      }
    });
  }
}
