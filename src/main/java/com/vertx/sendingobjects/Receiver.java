package com.vertx.sendingobjects;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


public class Receiver extends AbstractVerticle
{

  @Override
  public void start() throws Exception
  {

    EventBus bus = vertx.eventBus();

    bus.consumer("student.info", data -> {
      Object object = Json.decodeValue(Json.encode(data.body()));

      if (object instanceof JsonObject)
      {
        JsonObject jsonObject = (JsonObject) data.body();

        System.out.println(jsonObject.getString("studentName"));
      }
      else if (object instanceof JsonArray)
      {
        JsonArray jsonArray = (JsonArray) data.body();

        System.out.println(jsonArray.getString(0));
      }
      else
      {
        Student student = (Student) object;
        System.out.println(student.getStudentName());
      }
    });
  }

}
