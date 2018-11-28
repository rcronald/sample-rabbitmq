import com.rabbitmq.client.*;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by rcronald on 7/24/17.
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer{

    public QueueConsumer(String queueName) throws IOException, TimeoutException {
        super(queueName);
    }

    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(queueName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }


    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {
        System.out.println("handleRecoverOk");
    }

    /**
     * Called when new message is available.
     */
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);
        System.out.println("Message Number "+ map.get("message number") + " received.");
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
        System.out.println("handleShutdownSignal - " + consumerTag + "->" + arg1.toString() );
    }
}
