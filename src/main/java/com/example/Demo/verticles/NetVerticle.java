package com.example.Demo.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;

public class NetVerticle extends AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception {


     Future<NetServer> result =  vertx.createNetServer().listen(9999);


     result.onSuccess(res ->{


       System.out.println("success");

     });

     result.onComplete(handler ->{


       System.out.println("com");
     });

  }
}
