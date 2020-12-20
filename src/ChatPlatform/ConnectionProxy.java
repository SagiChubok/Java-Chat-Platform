package ChatPlatform;

import java.io.*;
import java.net.Socket;

public class ConnectionProxy extends Thread implements StringProducer,StringConsumer{

    private StringConsumer consumer = null;
    private Socket socket = null;
    private InputStream is = null;
    private OutputStream os = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    ConnectionProxy(Socket socket) {
        try {
            this.socket = socket;
            is = socket.getInputStream();
            os = socket.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
        }
        catch(IOException e) {
            System.out.println("Problem at ConnectionProxy class");
            e.printStackTrace();
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
            System.out.println("Problem at ConnectionProxy class - 'Consume method'");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            while(true) {
                String msg = dis.readUTF();
                consumer.consume(msg);
            }
        }
        catch(IOException e){
            System.out.println("Problem at ConnectionProxy class - 'Run method'");
            e.printStackTrace();
        }
    }

    public String readName(){
        try{
            return this.dis.readUTF();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "Problem not received any name";
        }
    }


}