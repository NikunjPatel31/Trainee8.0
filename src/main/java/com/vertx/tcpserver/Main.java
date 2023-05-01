package com.vertx.tcpserver;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {

    /*vertx.deployVerticle(RandomPort.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println("Successfully Deployed Verticle");
      }
      else
      {
        System.out.println("Not able to deploy verticle " + handler.cause().getMessage());
      }
    });*/

    vertx.deployVerticle(Sender.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println("Successfully Deployed Verticle");
      }
      else
      {
        System.out.println("Not able to deploy verticle " + handler.cause().getMessage());
      }
    });

    vertx.deployVerticle(Receiver.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println("Successfully Deployed Verticle");
      }
      else
      {
        System.out.println("Not able to deploy verticle " + handler.cause().getMessage());
      }
    });
  }

}
