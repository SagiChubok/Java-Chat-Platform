package ChatPlatform;

public class ClientDescriptor implements StringConsumer, StringProducer {

    private StringConsumer consumer = null;
    private String name;

    public ClientDescriptor(String name){
        this.name = name;
    }

    public void addConsumer(StringConsumer sc) { consumer = sc; }

    public void removeConsumer(StringConsumer sc) {}

    public void consume(String str) {}

    public String getName() {
        return name;
    }
}
