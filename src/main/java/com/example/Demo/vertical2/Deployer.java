package com.example.Demo.vertical2;

import io.vertx.core.*;

public class Deployer
{


  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(50));


    DeploymentOptions opt = new DeploymentOptions()
      .setInstances(5)
      .setWorker(true);


    vertx.deployVerticle(verticle.class.getName() , opt);
    Future<String> future = vertx.deployVerticle(verticle.class.getName() , opt);

    future.onComplete(handler ->{

      System.out.println("succesfully deployed the verticle");
    });


    future.onFailure(ar->{


      System.out.println("verticle is not deplyed");
    });


  future.onSuccess(handler->{
    System.out.println("on success");
  });



    vertx.deployVerticle(verticle.class.getName() , opt);

    vertx.deployVerticle(new verticle2());


  }
}
