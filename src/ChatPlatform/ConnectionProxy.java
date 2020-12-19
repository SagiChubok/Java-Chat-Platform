package ChatPlatform;

import java.io.*;
import java.net.Socket;

public class ConnectionProxy extends Thread implements StringProducer,StringConsumer{

    private StringConsumer consumer = null;
    private Socket connectionSocket = null;
    private InputStream is = null;
    private OutputStream os = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    ConnectionProxy(Socket socket) {
        try {
            connectionSocket = socket;
            is = socket.getInputStream();
            os = socket.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
        }
        catch(IOException e) {
            /* Block of code to handle errors */
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
        try{
            dos.writeUTF(str);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}