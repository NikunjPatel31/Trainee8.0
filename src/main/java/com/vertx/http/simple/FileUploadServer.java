package com.vertx.http.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServer;


public class FileUploadServer extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    HttpServer server = vertx.createHttpServer();

    server.requestHandler(request -> {
      request.setExpectMultipart(true);

      request.bodyHandler(data->{
        MultiMap formData = request.formAttributes();
        System.out.println(formData);
      });

      request.uploadHandler(file -> {
        System.out.println("Received Some Chunk Of Data:"+file.filename());

        file.streamToFileSystem("/home/chandresh/Downloads/"+file.filename());
      });



      request.response().send("Done!");
    }).listen(9090);
  }
}
