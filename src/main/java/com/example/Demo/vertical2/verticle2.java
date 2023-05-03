package com.example.Demo.vertical2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class verticle2 extends AbstractVerticle
{




  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    Promise<String> promise = Promise.promise();

    Future<String> future1 = promise.future();

    future1.onSuccess(handler->{
      System.out.println("sucess from future1");
    });

    future1.onFailure(handler ->{

      System.out.println("failed from future1");
    });

    future1.onComplete(handler->{

      System.out.println("copleted the task");
    });

    vertx.setTimer(1500 , handler->{

      vertx.eventBus().publish("hello" , "died");
      System.out.println("hello");
      vertx.eventBus().request("proise" , "promise" , reply->{

        if(reply.succeeded())
        {
          System.out.println(reply.result().body().toString());
          System.out.println("completed suc");
          promise.complete();
        }
        else {
          promise.fail("fail");
        }

      });

    });




    startPromise.complete();




  }
}
