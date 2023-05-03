package com.example.Demo.HttpServer;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerOptions;
import  java.net.http.HttpHeaders;
import java.io.File;

public class HttpServer extends AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception {

        HttpServerOptions options = new HttpServerOptions();
        options.setMaxHeaderSize(10 * 1024 * 1024);

        var server =  vertx.createHttpServer();

         server.requestHandler(reqest -> {

           //reqest.setExpectMultipart(true);

           String response = "hui jane have";


           //System.out.println( "remote adress -> " + reqest.connection().remoteAddress());
           System.out.println("api type -> " + reqest.method());
           //System.out.println("uri -> " + reqest.uri());
           System.out.println("path -> " + reqest.path());
           //System.out.println("query -> " + reqest.query());
           System.out.println("request headers -> " + reqest.headers());
           //System.out.println("host -> " + reqest.host());
           //System.out.println("params -> " + reqest.params());
           //System.out.println("remote adress -> " + reqest.remoteAddress());
          // System.out.println("absolute uri ->" + reqest.absoluteURI());
           //System.out.println("end handler -> " + reqest.endHandler() );

          /* reqest.handler(handler -> {

             System.out.println("data is " + handler.toString());
             System.out.println("\n\n\n\n\n");
           });*/

           /*reqest.endHandler(handler -> {

             MultiMap attribute = reqest.formAttributes();

             System.out.println(attribute.get("name"));
           });*/
           System.out.println("cockie -> " + reqest.cookies());

           reqest.cookies().stream().forEach(x -> {
             System.out.println(x.getName() + " : " + x.getValue());

           });


           reqest.response()
             .putHeader(Constants.CONTENT_TYPE , "text/plain")
             .putHeader("Content-Length" , Integer.toString(response.length()))
             .write(response);
             /*.putHeader(Constants.CONTENT_TYPE , "text/plain")
             .putHeader("Content-Length" , Integer.toString(response.length()))
             .addCookie(Cookie.cookie("sirname" , "bera"))
             .write(response)
             .onComplete(handler -> {

               System.out.println("\n\nresponse has been sent successfully to " + reqest.remoteAddress());
             });*/


           });


         server.listen(9999).onSuccess(handler ->
         {
             System.out.println("http server is running on 9999");
         })
         .onFailure(handler ->
         {
             System.out.println("Server is not started");
             System.out.println(handler.getCause().getMessage());
         });
  }


  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new HttpServer());



  }

}

