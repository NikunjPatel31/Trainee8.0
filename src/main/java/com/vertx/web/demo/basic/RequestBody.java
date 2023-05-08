package com.vertx.web.demo.basic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.List;

public class RequestBody extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    //asJsonObject & asPojo
    router.get("/student/add").consumes("application/json").handler(BodyHandler.create()).handler(context -> {
//      JsonObject studentDetail = context.body().asJsonObject();
//      System.out.println(studentDetail);
//      System.out.println(studentDetail.getString("name"));

      Student student = context.body().asPojo(Student.class); //This will require default constructor as it first creates instance and then uses the getter and setter.
      System.out.println(student.getStudentName());
      System.out.println(student.getStudentId());

      context.response().setChunked(true);
      context.end("Student Added Successfully");
    });

    //asString and limiting body size
    router.get("/student/add").consumes("text/plain").handler(BodyHandler.create().setBodyLimit(3)).handler(context -> {
      System.out.println(context.body().asString());
      context.response().setChunked(true);
      context.end("Student Added Successfully");
    });

    //buffer
    router.get("/student/add").consumes("text/html").handler(BodyHandler.create()).handler(context -> {
      System.out.println(context.body().buffer());
      context.response().setChunked(true);
      context.end("Student Added Successfully");
    });

    //formAttributes
    router.post("/student/add").handler(BodyHandler.create()).handler(context -> {
      System.out.println("In Post");
      System.out.println(context.request().formAttributes());
      //in this case form data is in request only but still it is reuired to provide BodyHandler.create()
      //and yes we have not setExceptMultipart(true) which we are using in core.
    });

    //Handling file uploads
    router.post("/student/image").handler(BodyHandler.create().setUploadsDirectory("/home/chandresh/Downloads")).handler(context -> {
      System.out.println("File Uploaded!");
      context.response().setChunked(true);
      context.end("File Uploaded!");

      List<FileUpload> files = context.fileUploads();

      System.out.println(files.get(0).fileName());
    });



    /*router.get("/student/add").handler(context -> {
      context.request().bodyHandler(body -> {
        System.out.println("-->" + body.toString()); //Hello World!
      });

      System.out.println(context.body().asString()); //null ???
    });*/

    server.requestHandler(router).listen(8080, ready -> {
      if (ready.succeeded()) {
        System.out.println("Server Started Listening!");
        startPromise.complete();
      } else {
        System.out.println("Fail To Start Server");
        startPromise.fail(ready.cause().getMessage());
      }
    });

  }
}
