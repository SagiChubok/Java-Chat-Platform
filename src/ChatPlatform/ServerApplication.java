package ChatPlatform;

import java.io.*;
import java.net.*;

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
                if(connection != null)
                    connection.closeConnection();
                System.exit(1);
            }
        }
    }
}