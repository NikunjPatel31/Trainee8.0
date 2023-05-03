package com.example.Demo.HttpClient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;

public class Httpclient extends AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    HttpClientOptions options = new HttpClientOptions();
    options.setLogActivity(true);


    HttpClient client = vertx.createHttpClient(options);

    System.out.println("hello world");
    client.request(HttpMethod.POST , 9999 , "localhost" , "/")
      .onSuccess(res ->{

        System.out.println("completed");
        var result  = res;
        result.putHeader("sirname" , "bera");
        res.send().onSuccess(response ->
        {
          System.out.println(response.headers());
          response.bodyHandler(buffer ->{
            System.out.println(buffer.toString());
          });
        });
          });





  }


  public static void main(String[] args) {


    var vertx = Vertx.vertx();

    vertx.deployVerticle(new Httpclient());
  }
}
