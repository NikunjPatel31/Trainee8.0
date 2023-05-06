package com.vertx.workingwithfilesystem;

import com.vertx.http.cookie.Client;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class CheckAndDelete extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

  @Override
  public void start(Promise<Void> startPromise) {
    try {
      vertx.fileSystem()
        .exists("/home/chandresh/Downloads/junk.txt")
        .compose(exist -> {
          if (exist) {
            return vertx.fileSystem().delete("/home/chandresh/Downloads/junk.txt");
          } else {
            return Future.failedFuture("File does not exist");
          }
        }).onComplete(result -> {
          if (result.succeeded()) {
            LOGGER.info("File deleted");
          } else {
            LOGGER.error("Oh oh ... - cannot delete the file: " + result.cause().getMessage());
          }
        });
      startPromise.complete();
    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
      startPromise.fail("Some Exception Occurred!" + exception.getMessage());
    }
  }
}
