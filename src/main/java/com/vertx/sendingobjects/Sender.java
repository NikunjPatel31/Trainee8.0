package com.vertx.sendingobjects;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


public class Sender extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    EventBus bus = vertx.eventBus();

    String json = "{\"studentName\" : \"Chandresh\", \"rollNo\" : 6,\"school\":\"Divine Life\"}";

    JsonObject jsonObject = new JsonObject(json);

    String array = "[\"studentName\", \"rollNo\",\"school\"]";

    JsonArray jsonArray = new JsonArray(array);


    vertx.setTimer(3000, id -> {
      System.out.println("Sending Data");
      bus.send("student.info", jsonObject);
    });

    vertx.setTimer(3000, id -> {
      System.out.println("Sending Data");
      bus.send("student.info", jsonArray);
    });

    vertx.setTimer(3000, id -> {
      System.out.println("Sending Data");
      bus.send("student.object", new Student(10, "Chandresh")); //No message codec for type: class com.vertx.sendingobjects.Student
    });
  }

}
