package PractiseRevise;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class MainVertx
{


  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(30));

    vertx.deployVerticle(new VerticleA());



  }
}
