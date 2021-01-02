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

    @Override
    public void addConsumer(StringConsumer sc) { consumer = sc; }

    @Override
    public void removeConsumer(StringConsumer sc) { consumer = null; }

    @Override
    public synchronized void consume(String str) {
        if(consumer != null){
            if(str.equals("disconnect")){
                consumer.consume(this.name + " disconnected from the chat.");
                removeConsumer(consumer);
            }
            else consumer.consume(this.name + ": " + str);
        }
    }

    public String getName() {
        return name;
    }
}
