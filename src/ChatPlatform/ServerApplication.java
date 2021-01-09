package ChatPlatform;

import java.io.*;
import java.net.*;

/**
 *
 * This class represent the server side and implements the server side principles.
 * Provide TCP connection to other users on same socket.
 * Host: 127.0.0.1
 * Port: 8080
 *
 */

public class ServerApplication
{
    public static void main(String args[])
    {
        ServerSocket server = null;
        MessageBoard mb = new MessageBoard();

        try
        {
            server = new ServerSocket(8080,5);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

        ConnectionProxy connection = null;

        while(true)
        {
            try
            {
                assert server != null;
                Socket socket = server.accept();
                connection = new ConnectionProxy(socket);
                ClientDescriptor client = new ClientDescriptor(connection.readName());
                connection.addConsumer(client);
                client.addConsumer(mb);
                mb.addConsumer(connection);
                mb.consume(client.getName() + " has joined the chat!");
                connection.start();
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}