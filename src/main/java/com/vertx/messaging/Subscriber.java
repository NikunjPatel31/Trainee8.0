package com.vertx.messaging;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;


public class Subscriber extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    EventBus bus = vertx.eventBus();

    bus.consumer("hello.world", message -> {
      System.out.println(message.body());
    });

    bus.consumer("hello.how", message -> {
      System.out.println(message.body());
    });
  }

}
