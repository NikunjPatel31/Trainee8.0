package com.vertx.workingwithfuture;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;


public class FutureCompositeDemo
{

  private static Vertx vertx = Vertx.vertx();

  static FileSystem fs = vertx.fileSystem();

  public static void main(String[] args)
  {

    fs.createFile("foo.txt").compose(result -> fs.writeFile("foot.txt", Buffer.buffer().appendString("Hello Chandresh"))).compose(result -> fs.move("foo.txt", "bar.txt")).onComplete(result -> {
      if (result.succeeded())
      {
        System.out.println("Operations Completed!");
      }
      else
      {
        System.out.println("Something Went Wrong! " + result.cause().getMessage());
      }
    });
  }

}
