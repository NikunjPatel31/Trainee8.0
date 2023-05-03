package com.example.starter.verticles.Client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import jdk.jfr.Frequency;

public class Client extends AbstractVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(Client.class.getName());
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var client = vertx.createHttpClient();

    client
      .request(HttpMethod.POST, 6001, "localhost", "")
      .onSuccess(handler -> {

        System.out.println("Dhaval");

        handler.setChunked(true);

        /*handler.putHeader("content-length", "10")
          .putHeader("content-type", "text/plain")
          .write("helo");*/

        for (int i = 0; i < 10; i++)
        {
          try
          {
            Thread.sleep(100);
          } catch (InterruptedException e)
          {
            throw new RuntimeException(e);
          }
          handler.write("helo").onComplete(h -> {
            if (h.succeeded())
            {
              var response = h.result();


            }
            else
            {
              System.out.println("Failed: "+h.cause().getMessage());
            }
          });
        }

//        handler.send("Hello").onSuccess(result ->{
//
//          result.endHandler(res ->{
//            System.out.println("ended");
//          });
//         result.bodyHandler(response ->{
//
//           System.out.println(response.toString());
//         });
//
//        });

      });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
