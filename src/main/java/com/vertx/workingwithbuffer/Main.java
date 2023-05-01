package com.vertx.workingwithbuffer;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {

    vertx.deployVerticle(Sender.class.getName());
    vertx.deployVerticle(Receiver.class.getName());
  }

}
