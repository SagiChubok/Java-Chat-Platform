package ChatPlatform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionProxy extends Thread implements StringProducer,StringConsumer{

    private StringConsumer consumer = null;
    private Socket connectionSocket = null;
    private InputStream is = null;
    private OutputStream os = null;

    ConnectionProxy(Socket socket) {
        try {
            connectionSocket = socket;
            is = socket.getInputStream();
            os = socket.getOutputStream();
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }
    }

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