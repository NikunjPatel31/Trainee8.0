package com.vertx.workingwithbuffer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;


public class Sender extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    EventBus eventBus = vertx.eventBus();

    Buffer buffer = Buffer.buffer();
    buffer.appendInt(1000);

    eventBus.send("get.buffer", buffer);

    buffer.setInt(0, 3000);
    eventBus.send("get.buffer", buffer);
  }

}
