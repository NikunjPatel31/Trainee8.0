package com.vertx.workingwithfilesystem;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;

public class RandomWrite extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.fileSystem()
      .open("/home/chandresh/Downloads/hello.txt", new OpenOptions())
      .onComplete(result -> {
        if (result.succeeded()) {
          AsyncFile file = result.result();
          for (int i = 0; i < 5; i++) {
            file
              .write(Buffer.buffer("Hello World!"), 12 * i)
              .onComplete(ar -> {
                if (ar.succeeded()) {
                  System.out.println("Written ok!");
                  // etc
                } else {
                  System.err.println("Failed to write: " + ar.cause());
                }
              });
          }
        } else {
          System.err.println("Cannot open file " + result.cause());
        }
      });
  }
}
