package setinstance;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class Example  extends AbstractVerticle
{
  public  static  int  counter = 0;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    System.out.println("hello world - " + ++counter);

  }

  public static void main(String[] args) {



    var vertx = Vertx.vertx();

    vertx.deployVerticle( Example.class.getName() , new DeploymentOptions().setInstances(200000).setWorker(true));


  }
}
