package com.example.Demo.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;

public class httpCaller extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    URL url = new URL("http://127.0.0.1:8888");
    long startTime = System.currentTimeMillis();
    for(int i = 0 ; i<100 ; i++)
    {
      if(i == 99)
      {
        vertx.executeBlocking(handler->{

          long startingTime = System.currentTimeMillis();



          HttpURLConnection connection = null;
          try {
            connection = (HttpURLConnection)url.openConnection();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }

          handler.complete();


        }).onComplete(handler->{

          System.out.println((startTime - System.currentTimeMillis()));

        });


      }
      vertx.executeBlocking(handler->{

        long startingTime = System.currentTimeMillis();



        HttpURLConnection connection = null;
        try {
          connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        System.out.println(connection.getContentType());
        handler.complete();

      });
    }

  }

  public static void main(String[] args) throws IOException {


    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new httpCaller());


  }
}
