package com.vertx.http.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;


public class Client extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpClient client = vertx.createHttpClient();


        client.request(HttpMethod.GET, 8090, "localhost", "/?name=chandresh").onComplete(handler -> {
            if (handler.succeeded()) {
                HttpClientRequest request = handler.result();

                request.send().onComplete(response -> {
                    if (response.succeeded()) {
                        System.out.println("Client Side Response!!");
                        System.out.println(response.result().statusCode());

                        response.result().bodyHandler(buff->{
                            System.out.println(buff.toString());
                        });
                    } else {
                        System.out.println(response.cause().getMessage());
                    }
                });
            } else {
                System.out.println("Some Error Occurred! " + handler.cause().getMessage());
            }
        });
    }

}
