package com.example.Demo.close;

import io.vertx.core.*;

import java.util.concurrent.TimeUnit;

public class Close extends AbstractVerticle
{

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("stopeed close verticle");
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    System.out.println(Thread.currentThread().getName());

      vertx.setTimer(10000 , handler -> {
        System.out.println("hello world timer");
        System.out.println(Thread.currentThread().getName());
      });
    startPromise.future().onComplete(handler ->{
      try {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(50000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
      startPromise.complete();
      /*Thread.sleep(50000);*/



  }


  public static void main(String[] args) throws InterruptedException {


    Vertx vertx  = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(30).setBlockedThreadCheckInterval(10).setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS));


    vertx.deployVerticle(Close.class.getName(), new DeploymentOptions());


  }
}

class VerticlB extends  AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("b");
    startPromise.complete();
    Thread.sleep(10000);
    vertx.close();

    System.out.println("end b");
  }
}
