package com.vertx.promisvsfuture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;


public class FutureComposition extends AbstractVerticle
{

  public Future<Void> executeAfterTwoSecond()
  {

    Promise<Void> promise = Promise.promise();

    vertx.setTimer(2000, handler -> {
      System.out.println("executeAfterTwoSecond called!");
      promise.fail("main karyu fail!");
    });

    return promise.future();
  }

  public Future<Void> executeAfterFourSecond()
  {

    Promise<Void> promise = Promise.promise();

    vertx.setTimer(4000, handler -> {
      System.out.println("executeAfterFourSecond called!");
      promise.complete();
    });

    return promise.future();
  }

  public Future<Void> executeAfterSixSecond()
  {

    Promise<Void> promise = Promise.promise();

    vertx.setTimer(6000, handler -> {
      System.out.println("executeAfterSixSecond called!");
      promise.complete();
    });

    return promise.future();
  }


  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    executeAfterFourSecond().compose(result -> executeAfterTwoSecond()).compose(result -> executeAfterSixSecond())
      .onComplete(event -> {
      if (event.succeeded())
      {
        System.out.println("All Task Completed!");
        startPromise.complete();
      }
      else
      {
        System.out.println("Some task faild! " + event.cause().getMessage());
        startPromise.fail("Some task faild! " + event.cause().getMessage());
      }
    });

    /*CompositeFuture.join(executeAfterFourSecond(), executeAfterTwoSecond(), executeAfterSixSecond()).onComplete(event -> {
      if (event.succeeded())
      {
        System.out.println("All Task Executed Successfully!");
        startPromise.complete();
      }
      else
      {
        System.out.println("Some task faild! " + event.cause().getMessage());
        startPromise.fail("Some task faild! " + event.cause().getMessage());
      }
    });*/
  }

}
