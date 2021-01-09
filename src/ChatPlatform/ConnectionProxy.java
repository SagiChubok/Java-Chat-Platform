package ChatPlatform;

import java.io.*;
import java.net.*;

/**
 *
 * This class behave as mediate layer that promise the server
 * and the client will not corrupted.
 *
 */

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
        consumer = null;
        if(is != null)
        {
            try {
                is.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        if(os != null)
        {
            try {
                os.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        if(dis != null)
        {
            try {
                dis.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        if(dos != null)
        {
            try {
                dos.flush();
                dos.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        if(socket != null)
        {
            try {
                socket.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void consume(String str) {
        try{
            dos.writeUTF(str);
        }
        catch (IOException e){
        }
    }

    @Override
    public void run(){
        try{
            while(socketExist()) {
                String msg = dis.readUTF();
                if(msg.equals("disconnect")){
                    consumer.consume(msg);
                    break;
                }
                consumer.consume(msg);
            }
            removeConsumer(consumer);
        }
        catch(IOException e) {
            removeConsumer(consumer);
        }
    }

    public String readName() {
        try{
            return this.dis.readUTF();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "Problem at read the name of the user - not received any name";
        }
    }

    public boolean socketExist(){
        return this.socket.isConnected();
    }
}