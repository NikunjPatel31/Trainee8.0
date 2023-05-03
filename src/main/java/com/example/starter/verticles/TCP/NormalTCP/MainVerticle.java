package com.example.starter.verticles.TCP.NormalTCP;

import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(new Server()).onComplete(handler -> {

      if(handler.succeeded()) {

        vertx.deployVerticle(new Client()).onComplete(asyncResult -> {

          if(asyncResult.succeeded()) {

            System.out.println("Client verticle deployed");
          }
          else
          {
            System.out.println("Client verticle not deployed: "+asyncResult.cause().getMessage());
          }

        });

        System.out.println("Server verticle deployed");
      } else {
        System.out.println("Server verticle not deployed: "+handler.cause().getMessage());
      }

    });
  }
}
