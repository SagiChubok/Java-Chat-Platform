package ChatPlatform;

import java.util.LinkedList;

/**
 *
 * This class holds the list of consumers and handling
 * the receiving and sending massages to each client
 *
 */

public class MessageBoard implements StringProducer,StringConsumer {

    private LinkedList<ConnectionProxy> listCP;

    MessageBoard() {
        listCP = new LinkedList<ConnectionProxy>();
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        listCP.add((ConnectionProxy)sc);
    }

    @Override
    public void removeConsumer(StringConsumer sc) {}

    @Override
    public synchronized void consume(String str) {
        int listSize = listCP.size();
        for(int i = 0 ; i < listSize ; i++){
            ConnectionProxy consumer = listCP.get(i);
            if(consumer != null) {
                if (consumer.socketExist()) {
                    consumer.consume(str);
                }
            }
        }
    }
}