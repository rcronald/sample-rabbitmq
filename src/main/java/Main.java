import java.util.HashMap;

/**
 * Created by rcronald on 7/24/17.
 */
public class Main {
    public Main() throws Exception{

        QueueConsumer consumer = new QueueConsumer("queue-demo");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();



    }

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception{
        new Main();
    }
}
