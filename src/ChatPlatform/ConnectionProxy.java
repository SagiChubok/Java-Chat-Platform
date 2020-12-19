package ChatPlatform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionProxy extends Thread implements StringProducer,StringConsumer{

    private StringConsumer consumer = null;

    @Override
    public void addConsumer(StringConsumer sc) {
        consumer = sc;
    }
    @Override
    public void removeConsumer(StringConsumer sc) {
    }
    @Override
    public synchronized void consume(String str) {
    }
}