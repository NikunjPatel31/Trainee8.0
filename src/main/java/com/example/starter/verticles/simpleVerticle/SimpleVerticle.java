package com.example.starter.verticles.simpleVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class SimpleVerticle extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    vertx.setPeriodic(5000, handler -> {
      try
      {
        Thread.sleep(2000);
      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      System.out.println("This is simple verticle");
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
