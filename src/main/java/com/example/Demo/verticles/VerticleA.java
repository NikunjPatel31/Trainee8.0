package com.example.Demo.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class VerticleA extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    System.out.println("hello from start");
    super.start(startPromise);
    System.out.println("hello after promise");

  }


  @Override
  public void stop() throws Exception {
    System.out.println("hello from stop");
    super.stop();
    System.out.println("hello from after stop ");
  }

  @Override
  public void start() throws Exception {

    System.out.println("hello i am dhaval bera");

    this.vertx.undeploy(this.deploymentID());

    System.out.println("after undeploy");

  }

}
