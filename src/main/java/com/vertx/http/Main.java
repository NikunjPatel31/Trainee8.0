package com.vertx.http;

import io.vertx.core.Vertx;


public class Main
{

  private static Vertx vertx = Vertx.vertx();

  public static void main(String[] args)
  {
    vertx.deployVerticle(Server.class.getName());
//    vertx.deployVerticle(Client.class.getName());
  }

}
