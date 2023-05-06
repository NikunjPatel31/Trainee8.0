package com.vertx.runningblockingcode;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sample {
  static {
    System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
  }

  private static Vertx vertx = Vertx.vertx();

  private static final Logger LOGGER = LoggerFactory.getLogger(Sample.class);


  public static void main(String[] args) {
    vertx.executeBlocking(promise -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      LOGGER.info("Hello World! 1");
      promise.complete();
    });

    vertx.executeBlocking(promis -> {
      System.out.println("Hello World! 2");
      promis.complete();
    });

    vertx.<String>executeBlocking(promise -> {
      System.out.println("Main Promise");
      promise.complete("Completed!");
    }, false, result -> {
      if (result.succeeded()) {
        System.out.println(result.result());
      } else {
        System.out.println(result.cause().getMessage());
      }
    });

    vertx.executeBlocking(promis -> {
      System.out.println("Hello World! 3");
      promis.complete();
    });
    vertx.executeBlocking(promis -> {
      System.out.println("Hello World! 4");
      promis.complete();
    });

  }
}
