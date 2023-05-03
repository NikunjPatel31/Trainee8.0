package com.vertx.promisvsfuture;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {

    /*vertx.deployVerticle(DemoVerticle.class.getName()).onComplete(result -> {
      System.out.println("Completed!!!");

      if (result.succeeded())
      {
        System.out.println("Result Succeeded! " + result.result());
      }
      else
      {
        System.out.println("Not successful to deploy verticle! " + result.cause().getMessage());
      }
    });*/

    vertx.deployVerticle(FutureComposition.class.getName()).onComplete(result -> {

      if (result.succeeded())
      {
        System.out.println("Result Succeeded! " + result.result());
      }
      else
      {
        System.out.println("Not successful to deploy verticle! " + result.cause().getMessage());
      }
    });
  }

}
