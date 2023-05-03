package com.example.Demo.vertical2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;



public class verticle extends AbstractVerticle
{
  private  final Logger logger = (Logger) LoggerFactory.getLogger(verticle.class);


  @Override
  public void stop() throws Exception {



    System.out.println("stopeed");
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {





    vertx.setPeriodic(2000 , handler->{

      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });



    vertx.eventBus().consumer("proise" , obj ->{

      System.out.println(obj.body().toString());

        obj.reply("i am dhaval");

    });




  }



  public  void hello()
  {

    logger.info("hello");
  }
}
