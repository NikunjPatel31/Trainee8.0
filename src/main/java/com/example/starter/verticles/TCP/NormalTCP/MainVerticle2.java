package com.example.starter.verticles.TCP.NormalTCP;

import io.vertx.core.Vertx;

public class MainVerticle2
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(new Client()).onComplete(result-> {
      if (result.succeeded())
      {
        System.out.println("Client deployed");
      }
      else
      {
        System.out.println("Client deployment failed: "+result.cause().getMessage());
      }
    });
  }
}
