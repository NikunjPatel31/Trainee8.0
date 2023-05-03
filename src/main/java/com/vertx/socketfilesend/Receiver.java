package com.vertx.socketfilesend;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;


public class Receiver extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {

    NetClient client = vertx.createNetClient(new NetClientOptions().setReconnectAttempts(10).setReconnectInterval(3000));

    client.connect(8998,"localhost").onComplete(event -> {
      if (event.succeeded())
      {
        NetSocket socket = event.result();

        socket.sendFile(".gitignore").onComplete(fileSent -> {
          if (fileSent.succeeded())
          {
            System.out.println("File Sent!");
          }
          else
          {
            System.out.println("Failed to send file " + fileSent.cause().getMessage());
          }
        });

        startPromise.complete();
      }
      else
      {
        startPromise.fail("Fail To Connect" + event.cause().getMessage());
      }
    });
  }

}
