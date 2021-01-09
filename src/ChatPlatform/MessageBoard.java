package ChatPlatform;

import java.util.ArrayList;

/**
 *
 * This class holds the list of consumers and handling
 * the receiving and sending massages to each client
 *
 */

public class MessageBoard implements StringProducer,StringConsumer {

    private ArrayList<ConnectionProxy> consumers;

    MessageBoard() {
        consumers = new ArrayList<>();
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        consumers.add((ConnectionProxy)sc);
    }

    @Override
    public void removeConsumer(StringConsumer sc) { consumers.remove(sc);}

    @Override
    public synchronized void consume(String str) {
        int listSize = consumers.size();
        for(int i = 0 ; i < listSize ; i++){
            ConnectionProxy consumer = consumers.get(i);
            if(consumer != null) {
                if (consumer.socketExist()) {
                    consumer.consume(str);
                } else removeConsumer(consumer);
            }
        }
    }
}