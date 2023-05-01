package com.vertx.messaging;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

import java.util.concurrent.atomic.AtomicInteger;


public class Publisher extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    EventBus eventBus = vertx.eventBus();

    AtomicInteger integer = new AtomicInteger(0);

    vertx.setPeriodic(2000, id -> {

      eventBus.publish("hello.world", "Hello World!" + integer.getAndIncrement()).publish("hello.how", "How Are You!");

    });

  }

}
