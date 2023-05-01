package com.vertx.json;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


public class JsonObjectDemo
{

  public static void main(String[] args)
  {

    String json = "{\"studentName\" : \"Chandresh\", \"rollNo\" : 6,\"school\":\"Divine Life\"}";

    JsonObject jsonObject = new JsonObject(json);

    System.out.println(jsonObject.getString("studentName"));

    System.out.println("-->" + jsonObject.encode());

    String array = "[\"studentName\", \"rollNo\",\"school\"]";

    JsonArray jsonArray = new JsonArray(array);

    System.out.println(jsonArray.getString(0));

    System.out.println("-->" + jsonArray.encode());

    System.out.println("\n\n");
    Object obj = Json.decodeValue(array);

    if (obj instanceof JsonArray)
    {
      System.out.println("It's a JsonArray");
    }
    else if (obj instanceof JsonObject)
    {
      System.out.println("It's a JsonObject");
    }
  }


}
