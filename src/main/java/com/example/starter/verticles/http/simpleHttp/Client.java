package com.example.starter.verticles.http.simpleHttp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;

public class Client extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    HttpClient client = vertx.createHttpClient();

    client.request(HttpMethod.POST, 6001, "localhost", "/")
      .compose(result -> {
        return result.write("Hello server... amigo");
      }).onComplete(result -> {
        if (result.succeeded())
        {
          System.out.println("Request sent successfully ");
        }
        else
        {
          System.out.println("Failed to sent request");

          startPromise.fail(result.cause().getMessage());
        }
      });;



  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    stopPromise.complete();
  }
}
