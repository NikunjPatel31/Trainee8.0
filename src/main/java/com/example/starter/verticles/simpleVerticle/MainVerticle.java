package com.example.starter.verticles.simpleVerticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx(new VertxOptions().setMaxEventLoopExecuteTime(1000));

    vertx.deployVerticle(SimpleVerticle.class.getName());
  }
}
