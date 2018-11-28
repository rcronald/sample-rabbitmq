import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by rcronald on 7/24/17.
 */
public class QueueProducer extends EndPoint{

    public QueueProducer(String queueName) throws IOException, TimeoutException {
        super(queueName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }
}
