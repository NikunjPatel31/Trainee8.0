package com.example.Demo.verticles;

import io.vertx.core.*;

public class MainVerticle
{

  public static void main(String[] args) {


    var vertx = Vertx.vertx();



     //vertx.deployVerticle(new com.example.Demo.MainVerticle());

    Future<String> future3 = vertx.deployVerticle(new NetVerticle());


    future3.onFailure(handler->{
      System.out.println("net fail");
    });

    future3.onSuccess(handler->{

      System.out.println("success");
    });

    future3.onComplete(handler ->{
      System.out.println("net complete");
    });






    //Vertx.vertx().deployVerticle(new com.example.Demo.MainVerticle());



  }
}


