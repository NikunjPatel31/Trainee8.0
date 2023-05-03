package com.example.Demo.Route;

import com.example.Demo.HttpServer.Constants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class RouterExample extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    var server  = vertx.createHttpServer();



    Router router = Router.router(vertx);

    router.get("/hello").handler(handler -> {

      System.out.println("hello");
      String  reply = "this is hello branch";
        handler.request().response().putHeader(Constants.CONTENT_TYPE , "text/plain")
          .putHeader(Constants.CONTENT_LENGTH ,Integer.toString(reply.length()) )
          .write(reply);

    });

    router.get("/blocking").blockingHandler(handler ->{

      System.out.println("blocking code");
      String  reply = "this is blocking coede is ran in working thread ";
      handler.request().response().putHeader(Constants.CONTENT_TYPE , "text/plain")
        .putHeader(Constants.CONTENT_LENGTH ,Integer.toString(reply.length()) )
        .write(reply);


    });

    router.post("/hello").handler(handler -> {

      System.out.println("hello post");
      String  reply = "this is hello post branch";
      handler.request().response().putHeader(Constants.CONTENT_TYPE , "text/plain")
        .putHeader(Constants.CONTENT_LENGTH ,Integer.toString(reply.length()) )
        .write(reply);

    });

    router.get("/get").handler(handler -> {

      System.out.println("get");
      String  reply = "this is get branch";
      handler.request().response().putHeader(Constants.CONTENT_TYPE , "text/plain")
        .putHeader(Constants.CONTENT_LENGTH ,Integer.toString(reply.length()) )
        .write(reply);

    });

    server.requestHandler(router);

   /* server.requestHandler(reqest ->
    {

      String  reply = "this is main branch";
     reqest.response().putHeader(Constants.CONTENT_TYPE , "text/plain")
        .putHeader(Constants.CONTENT_LENGTH ,Integer.toString(reply.length()) )
        .write(reply);
    });
*/
    server.listen(7777).onComplete(handler ->{

      if(handler.succeeded())
      {
        System.out.println("server is running on 7777 port");
      }
      else
      {
        System.out.println("There is some internal error");
      }


    });

  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new RouterExample());

  }
}
