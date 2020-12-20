package ChatPlatform;

import java.util.LinkedList;

public class MessageBoard implements StringProducer,StringConsumer {
    private LinkedList<StringConsumer> list = null;

    MessageBoard() {
        list = new LinkedList<StringConsumer>();
    }

    @Override
    public void addConsumer(StringConsumer sc) {}
    @Override
    public void removeConsumer(StringConsumer sc) {
    }
    @Override
    public synchronized void consume(String str) {}
}
