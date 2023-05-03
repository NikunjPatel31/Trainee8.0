package com.example.Demo.filesystem;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

public class FilePractical extends AbstractVerticle
{

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    FileSystem file = vertx.fileSystem();

    file.readFile("/home/dhaval/hacker" ).onComplete(handler -> {

      if(handler.succeeded())
      {
        //System.out.println(handler.result().toString());
      }
      else
      {
        handler.cause().printStackTrace();

      }
    });

    file.writeFile("/home/dhaval/bera" , Buffer.buffer("i am dhaval bera from vert.x filesystem")).onComplete(
      handler ->{

        System.out.println(handler.succeeded());
      }
    );


    // Async file with open method

    file.open("/home/dhaval/bera" , new OpenOptions()).onComplete(
      handler ->{

        if(handler.succeeded())
        {
          AsyncFile asyncFile = handler.result();

            Buffer bufer = Buffer.buffer();

          System.out.println(asyncFile.read(bufer , 0 , 0 , 100 , res->{
            if(res.succeeded())
            {
              System.out.println(res.result().toString());
            }
          } ));
        }
      }
    );



    




    file.exists("/home/dhaval/hacker")
      .compose(exits ->{
        System.out.println(exits);;

        if(exits)
        {
          System.out.println("file exits");
          return Future.future(handler ->{

            System.out.println("file exits");
          });
        }
        System.out.println("file does not exits");
        return Future.failedFuture("file does not eits");
      });

    startPromise.complete();
  }


  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new FilePractical());

  }
}
