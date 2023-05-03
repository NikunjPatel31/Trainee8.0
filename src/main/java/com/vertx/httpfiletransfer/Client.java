package com.vertx.httpfiletransfer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;


public class Client extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    HttpClient client = vertx.createHttpClient();

    client.request(HttpMethod.POST, 8080, "localhost", "/")
      .compose(req -> req.send("Hello World!"))
      .onComplete(result->{
        if(result.succeeded()){
          System.out.println("Request Sent!");
        }else{
          System.out.println("Request Not Sent!"+result.cause().getMessage());
        }
      });

  }

}
