package ChatPlatform;
public class ConnectionProxy extends Thread implements StringProducer,StringConsumer{
    @Override
    public void addConsumer(StringConsumer sc) {
    }
    @Override
    public void removeConsumer(StringConsumer sc) {
    }
    @Override
    public synchronized void consume(String str) {
    }
}