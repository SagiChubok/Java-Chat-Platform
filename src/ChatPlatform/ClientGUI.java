package ChatPlatform;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI implements StringConsumer {

    private JFrame chatBoard;
    private JPanel chat_panel;
    private JPanel send_panel;
    private JTextArea chatArea;
    private JTextField text_field;
    private JButton connectBtn;
    private JButton sendBtn;

    @Override
    public synchronized void consume(String str) {}

    public static void main(String[]args)
    {
        ClientGUI gui = new ClientGUI();
        gui.go();
    }

    public void go() {
        /* Chat GUI structure */

        // Layout
        chatBoard = new JFrame("Chat Platform");
        chatBoard.setLayout(new BorderLayout());
        chatBoard.setSize(530, 500);

        chat_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        send_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Layout elements
        chatArea = new JTextArea("Java Course Shenkar (Chat Platform Assignment)");
        chatArea.setEditable(false);
        chatArea.setPreferredSize(new Dimension(503, 400));

        text_field = new JTextField(" Enter your name...");
        text_field.setPreferredSize(new Dimension(400, 40));

        connectBtn = new JButton("Connect");
        connectBtn.setPreferredSize(new Dimension(100, 40));
        connectBtn.addActionListener(new ConnectActionListener(this));
        connectBtn.setActionCommand("Connect");

        sendBtn = new JButton("Send");
        sendBtn.setPreferredSize(new Dimension(100, 40));
        sendBtn.addActionListener(new ConnectActionListener(this));
        sendBtn.setActionCommand("Send");
        sendBtn.setVisible(false);

        JScrollPane scroller = new JScrollPane(this.chatArea);

    }

    //Actions listeners
    class ConnectActionListener implements ActionListener {

        public ConnectActionListener(ClientGUI gui){}

        public void actionPerformed(ActionEvent ae) {}
    }


}