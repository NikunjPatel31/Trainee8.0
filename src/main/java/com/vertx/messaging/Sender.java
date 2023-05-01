package com.vertx.messaging;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;


public class Sender extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    //    System.out.println("Sender Verticle Deployment Started!");

    EventBus bus = vertx.eventBus();

    vertx.setPeriodic(3000, handler -> {
      //bus.publish("hello.chandresh", "Hello Chandresh How Are You!");
      bus.request("hello.chandresh", "Hello Chandresh How Are You!", new DeliveryOptions().setSendTimeout(4000)).onComplete(replyHandler -> {
        if (replyHandler.succeeded())
        {
          System.out.println(replyHandler.result().body());

          replyHandler.result().replyAndRequest("I'm Fine How Are You?").onComplete(replyHandler1 -> {
            if (replyHandler1.succeeded())
            {
              System.out.println(replyHandler1.result().body());
            }
            else
            {
              System.out.println("Some Error Occured! " + replyHandler1.cause().getMessage());
            }
          });
        }
        else
        {
          System.out.println("Some Error Occured!" + replyHandler.cause().getMessage());
        }
      });
    });

    //    System.out.println("Done Deploying Sender Verticle!");
  }

}
