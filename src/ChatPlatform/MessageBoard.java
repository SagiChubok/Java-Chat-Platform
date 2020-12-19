package ChatPlatform;

import java.io.IOException;

public class MessageBoard implements StringProducer,StringConsumer {
    @Override
    public void addConsumer(StringConsumer sc) {}
    @Override
    public void removeConsumer(StringConsumer sc) {
    }
    @Override
    public synchronized void consume(String str) {}
}
