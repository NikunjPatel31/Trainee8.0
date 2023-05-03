package com.example.starter.verticles.TaskVivekSir.http;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(Server.class.getName(), new DeploymentOptions().setInstances(5)).onComplete(result -> {
      if (result.succeeded())
      {

      }
      else
      {
        System.out.println("Server failed: "+result.cause().getMessage());
      }
    });

//    vertx.deployVerticle(new Client())
//      .onComplete(handler -> {
//        if (handler.succeeded())
//        {
//          System.out.println("Client deployment successful");
//        }
//        else
//        {
//          System.out.println("Client failed: "+handler.cause().getMessage());
//        }
//      });
  }
}
