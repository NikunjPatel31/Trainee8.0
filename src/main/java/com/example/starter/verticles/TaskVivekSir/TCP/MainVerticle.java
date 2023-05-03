package com.example.starter.verticles.TaskVivekSir.TCP;

import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(new Client()).onComplete(handler -> {
      if (handler.succeeded())
      {
//        vertx.deployVerticle(new Client());
        System.out.println("Client deployed");
      }
      else
      {
        System.out.println("Client deployment failed");
      }
    });
  }
}
