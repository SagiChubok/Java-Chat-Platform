package ChatPlatform;

/**
 *
 * This class hold the client name and the message that sent,
 * When client send any message his name attached to the message.
 *
 */

public class ClientDescriptor implements StringConsumer, StringProducer {

    private StringConsumer consumer = null;
    private final String name;

    public ClientDescriptor(String name){
        this.name = name;
    }

    public void addConsumer(StringConsumer sc) { consumer = sc; }

    public void removeConsumer(StringConsumer sc) {}

    public void consume(String str) {
        if(consumer != null){
            consumer.consume(this.name + ": " + str);
        }
    }

    public String getName() {
        return name;
    }
}
