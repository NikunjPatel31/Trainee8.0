package com.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;


public class SimpleSetPeriodic extends AbstractVerticle
{

  /*public static void main(String[] args) throws InterruptedException
  {

    Vertx vertx = Vertx.vertx();

    vertx.setPeriodic(50000, id -> {
      System.out.println("Hello World! " + id);
    });

    vertx.setTimer(5000, id -> {
      System.out.println("Hello World! " + id);
    });
  }*/

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    System.out.println("Deploying Instance!");
    vertx.setPeriodic(3000, id -> {
      System.out.println("Hello World! " + id);
    });

    vertx.setTimer(10000, id -> {
      System.out.println("Stopping Instance!");

      vertx.undeploy(this.context.deploymentID()).onComplete(handler -> {
        if (handler.succeeded())
        {
          System.out.println("Undeployed");
        }
        else
        {
          System.out.println("Failed to undeployed: "+handler.cause().getMessage());
        }
      });
    });

    startPromise.complete();

  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {

    System.out.println("Cleaning Up!");

    stopPromise.complete();
  }

}
