package com.example.starter.verticles.http.upload;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerOptions;

public class Server extends AbstractVerticle
{
  public static void main(String[] args)
  {
    Vertx.vertx().deployVerticle(Server.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var server = vertx.createHttpServer(new HttpServerOptions().setTcpKeepAlive(true));

    server.requestHandler(request ->
      {
        request.response().putHeader("Content-Length", "5000").write("This is message 1");
        request.response().end();

        request.handler(buffer -> {
          System.out.println(buffer);
          System.out.println("----------------------------");
        });


        request.setExpectMultipart(true);

        System.out.println(request.remoteAddress());

        request.uploadHandler(uploadHandler ->
        {
          uploadHandler.handler(handler -> {
//            System.out.println(handler);
          });
          System.out.println(uploadHandler.contentType());
        });

        for (Cookie c : request.cookies())
        {
          System.out.println(c.getName()+" : "+c.getValue());
        }

        request.response().addCookie(Cookie.cookie("Name", "Nikunj"));

        request
          .response()
          .addCookie(Cookie.cookie("Name", "NikunjPatel"))
          .sendFile("/home/nikunj/Downloads/Task 2-5-23/Updated-file.json");
      })
      .listen(6001)
      .onComplete(handler ->
      {
        if (handler.succeeded())
        {
          System.out.println("Server is listening...!");

          startPromise.complete();
        } else
        {
          startPromise.fail(handler.cause().getMessage());
        }
      });
  }
}
