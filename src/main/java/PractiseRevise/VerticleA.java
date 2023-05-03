package PractiseRevise;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    System.out.println("hello world");

    vertx.setPeriodic(2000 , res -> {

      System.out.println("event loop " +Thread.currentThread().getName());

      vertx.executeBlocking(handler-> {

        try {

          System.out.println( "worker  " + Thread.currentThread().getName());

          handler.complete();

          Thread.sleep(3000);

        } catch (Exception e) {

          handler.fail(e);
          throw new RuntimeException(e);
        }

      } , result -> {

        if (result.succeeded())
        {
          System.out.println("success");
        }
        else
        {
          System.out.println("fail");
        }

      });
    });






  }
}
