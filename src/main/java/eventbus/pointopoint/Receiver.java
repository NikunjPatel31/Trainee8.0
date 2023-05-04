package eventbus.pointopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class Receiver extends AbstractVerticle
{
    @Override
    public void start() throws Exception
    {
        EventBus eventBus = getVertx().eventBus();

        eventBus.consumer("10.20.40.228:5000", message ->
        {
            System.out.println(message.body());

            message.reply("Ping Received!");
        });

    }
}
