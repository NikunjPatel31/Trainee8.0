package com.vertx.clientserverfile;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpServer;

public class Server extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    FileSystem fileSystem = vertx.fileSystem();

    server.requestHandler(request -> {
      request.setExpectMultipart(true);

      request.uploadHandler(upload -> {
        fileSystem.createFile("/home/chandresh/Downloads/new_" + upload.filename()).onComplete(result -> {
          if (result.succeeded()) {
            System.out.println("File Created!");
            fileSystem.open("/home/chandresh/Downloads/new_" + upload.filename(), new OpenOptions().setDsync(true)).onComplete(writer -> {
              if (writer.succeeded()) {
                System.out.println("File Opened!");
                AsyncFile file = writer.result();

                upload.pipeTo(file).onComplete(event -> {
                  if(event.succeeded()){
                    file.close();
                    request.response().send("Successfully Uploaded");
                  }else{
                    request.response().send("not-Successfully Uploaded");
                  }
                });
              } else {
                System.out.println("Unable to open file!");
              }
            });
          } else {
            System.out.println("Unable to create file!");
          }
        });
      });
    }).listen(9092, "0.0.0.0").onComplete(hadler -> {
      if (hadler.succeeded()) {
        startPromise.complete();
      } else {
        startPromise.fail("Fail to start server!");
      }
    });
  }
}
