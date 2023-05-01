package com.vertx.demo;

import io.vertx.core.Vertx;


public class CustomLauncher
{

  public static void main(String[] args) throws InterruptedException
  {

    Vertx vertx = Vertx.vertx();

    //    vertx.deployVerticle(SimpleSetPeriodic.class.getName(), new DeploymentOptions().setInstances(20));
    vertx.deployVerticle(TcpServer.class.getName());
  }

}
