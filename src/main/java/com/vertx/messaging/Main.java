package com.vertx.messaging;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static Vertx getVertx()
  {

    return vertx;
  }

  public static void main(String[] args)
  {

    vertx.deployVerticle(Sender.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        //        System.out.println("Successfully Started Publishing Messages!");
      }
      else
      {
        System.out.println("Some Issue occured!" + handler.cause().getMessage());
      }
    });

    vertx.deployVerticle(Receiver.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
//        System.out.println("Successfully Started Publishing Messages!");
      }
      else
      {
        System.out.println("Some Issue occured!" + handler.cause().getMessage());
      }
    });

    /*vertx.deployVerticle(Publisher.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println("Successfully Started Publishing Messages!");
      }
      else
      {
        System.out.println("Some Issue occured!" + handler.cause().getMessage());
      }
    });

    vertx.deployVerticle(Subscriber.class.getName()).onComplete(handler -> {
      if (handler.succeeded())
      {
        System.out.println("Successfully Started Getting Messages!");
      }
      else
      {
        System.out.println("Some Issue occured!" + handler.cause().getMessage());
      }
    });*/
  }

}
