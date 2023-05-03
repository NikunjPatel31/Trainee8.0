package com.vertx.socketfilesend;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {

    vertx.deployVerticle(Sender.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println(handler.result());
      }
      else
      {
        System.out.println(handler.cause().getMessage());
      }
    });

    vertx.deployVerticle(Receiver.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println(handler.result());
      }
      else
      {
        System.out.println(handler.cause().getMessage());
      }
    });
  }

}
