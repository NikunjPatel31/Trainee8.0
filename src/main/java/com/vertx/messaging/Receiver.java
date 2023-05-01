package com.vertx.messaging;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;


public class Receiver extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    //    System.out.println("Receiver Verticle Deployment Started!");

    EventBus bus = vertx.eventBus();

    bus.consumer("hello.chandresh", message -> {

      System.out.println("Handler 1! I have received! " + message.body());

      /*message.replyAndRequest("Hello Server How Are You! From Handler 1!").onComplete(replyHandler -> {
        if (replyHandler.succeeded())
        {
          System.out.println(replyHandler.result().body());
          replyHandler.result().reply("Good Good!");
        }
        else
        {
          System.out.println("Some error occured! " + replyHandler.cause().getMessage());
        }
      });*/


    });

    /*bus.consumer("hello.chandresh", message -> {

      System.out.println("Handler 2! I have received! " + message.body());
      message.reply("Hello Server How Are You! From Handler 2!");


    });

    bus.consumer("hello.chandresh", message -> {

      System.out.println("Handler 3! I have received! " + message.body());
      message.reply("Hello Server How Are You! From Handler 3!");
    });*/

    //    System.out.println("Done Deploying Receiver Verticle!");
  }

}
