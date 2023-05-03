package com.example.starter.verticles.executeBlocking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle
{

  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(MainVerticle.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var executor = vertx.createSharedWorkerExecutor("Custom-thread-pool", 100);

    /*executor.executeBlocking(handler ->
    {
      try
      {
        Thread.sleep(10000);

        System.out.println("10 second...");

      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }, false);

    executor.executeBlocking(handler ->
    {
      try
      {
        Thread.sleep(10000);

        System.out.println("2-10 second...");

      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }, false);

    executor.executeBlocking(handler ->
    {
      try
      {
        Thread.sleep(10000);

        System.out.println("3-10 second...");

      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }, false);*/

    executor.executeBlocking(handler ->
    {
      try
      {
        Thread.sleep(10000);

        System.out.println("10 second...");

      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }, true);

    executor.executeBlocking(handler ->
    {
      try
      {
        Thread.sleep(10000);

        System.out.println("2-10 second...");

      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }, true);

    executor.executeBlocking(handler ->
    {
      try
      {
        Thread.sleep(10000);

        System.out.println("3-10 second...");

      } catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }, true);

    startPromise.complete();

  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
