package ChatPlatform;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;


public class ClientGUI implements StringConsumer, StringProducer {
    @Override
    public void addConsumer(StringConsumer sc) {}

    @Override
    public void removeConsumer(StringConsumer sc) {}

    @Override
    public synchronized void consume(String str) {}

    public static void main(String[]args)
    {
        ClientGUI gui = new ClientGUI();
        gui.go();
    }

    public void go() {

    }

}
