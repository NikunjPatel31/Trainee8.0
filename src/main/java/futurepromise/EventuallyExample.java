package futurepromise;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class EventuallyExample extends AbstractVerticle {

    static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {

        Promise<String> promise = Promise.promise();

        promise.complete();

        Future<String> rs = promise.future().compose(handler -> failed().future()).eventually(handle -> succeded().future());

        System.out.println(rs.result());

    }

    static Promise<String> succeded() {

        Promise<String> promise = Promise.promise();

        promise.complete("Pass");

        return promise;

    }

    static Promise<String> failed() {

        Promise<String> promise = Promise.promise();

        promise.fail("Fail");

        return promise;

    }
}
