package com.example.Demo.NetServer;

import com.example.Demo.verticles.HttpServerFile;
import com.example.Demo.verticles.NetVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NetServer extends AbstractVerticle
{


  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    vertx.createNetServer().connectHandler(connection -> {



    //  Promise promise = Promise.promise();


    /*  connection.handler(buffer -> {
        String data = new String(buffer.getBytes(), Charset.forName("UTF-16"));
        System.out.println(data);
        promise.complete(); // Complete the promise after processing the data
      }).exceptionHandler(cause -> {
        promise.fail(cause); // Handle any exceptions that may occur
      });

      promise.future().onSuccess(handler -> {
        connection.write(Buffer.buffer("hello from server".getBytes(Charset.forName("UTF-16"))))
          .onComplete(write -> {
            if (write.succeeded()) {
              System.out.println("Data written successfully");
            } else {
              System.out.println("Failed to write data: " + write.cause());
            }
            connection.close(); // Close the connection after writing the data
          });
      });
*/

      NetSocket socket = connection.handler(buffer ->
      {
        String data = new String(buffer.getBytes() , Charset.forName("UTF-16"));
        System.out.println(data);
        connection.close();

      //  vertx.eventBus().send("send" , "sent");


      });

      connection.write(Buffer.buffer(("hello from server" + '\0').getBytes(Charset.forName("UTF-16")))).onSuccess(dd ->{
        System.out.println("sent the data");


     // connection.close();


      });










    }).listen(9999 ).onComplete(res ->
    {



      if(res.succeeded())
      {
        startPromise.complete();
        System.out.println("net server is started on 9999");
      }
      else
      {
        startPromise.fail("server is not listning ");
      }
    });
  }

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new NetServer());
  }
}
