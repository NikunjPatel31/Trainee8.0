package com.vertx.workingwithbuffer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;


public class Receiver extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    EventBus eventBus = vertx.eventBus();

    eventBus.consumer("get.buffer", buff -> {
      Buffer buffer = (Buffer) buff.body();
      System.out.println("Handler1! " + buffer.getInt(0));
    });

    eventBus.consumer("get.buffer", buff -> {
      Buffer buffer = (Buffer) buff.body();
      System.out.println("Handler2! " + buffer.getInt(0));
    });

  }

}
