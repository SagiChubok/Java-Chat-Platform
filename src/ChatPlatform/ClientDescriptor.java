package ChatPlatform;

import java.io.IOException;

public class ClientDescriptor implements StringConsumer, StringProducer {
    public void addConsumer(StringConsumer sc) {}
    public void removeConsumer(StringConsumer sc) {}
    public void consume(String str) {}
}
