package ChatPlatform;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * This class represent the client side and implements the client side view.
 * Host: 127.0.0.1
 * Port: 8080
 *
 */

public class ClientGUI implements  StringConsumer, StringProducer {

    private JFrame chatBoard;
    private JPanel chat_panel;
    private JPanel send_panel;
    private JTextArea chatArea;
    private JTextField text_field;
    private JButton connectBtn;
    private JButton sendBtn;

    private StringConsumer consumer;
    private StringProducer producer;

    @Override
    public void addConsumer(StringConsumer sc) {
        consumer = sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        consumer = null;
    }

    @Override
    public synchronized void consume(String str) {
        // Append message to chat
        chatArea.append("\n" + str);
        // Set the scroll down when appending new message
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public void go() {
        /* Chat GUI structure */

        /* Layout */
        chatBoard = new JFrame("Chat Platform");
        chatBoard.setLayout(new BorderLayout());
        chatBoard.setSize(530, 500);

        chat_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chat_panel.setBackground(new Color(0x99b1c9));
        send_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        send_panel.setBackground(new Color(0x99b1c9));

        chatArea = new JTextArea ( 25, 49);
        chatArea.append("Java Course Shenkar (Chat Platform Assignment)");
        chatArea.setEditable ( false ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane (chatArea);
        scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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


        // Chat panel
        chatBoard.add(chat_panel, BorderLayout.NORTH);

        // Send panel
        chatBoard.add(send_panel, BorderLayout.SOUTH);
        send_panel.add(text_field);
        text_field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                text_field.setText(null); // Empty the text field when it receives focus
            }
            @Override
            public void focusLost(FocusEvent arg0) {
            }
        });

        /* Add layout elements */
        send_panel.add(connectBtn);
        send_panel.add(sendBtn);
        chat_panel.add(scroll); // add scroll that contains chatArea


        send_panel.getRootPane().setDefaultButton(connectBtn);


        // Show frame
        chatBoard.setVisible(true);

        // Frame Closing Operation
        chatBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatBoard.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (consumer != null){
                    consumer.consume("disconnect");
                }
                e.getWindow().dispose();
            }
        });

        //Frame Disable resize operation
        chatBoard.setResizable(false);
    }

    /* Actions listeners */
    class ConnectActionListener implements ActionListener {
        private final ClientGUI gui;

        public ConnectActionListener(ClientGUI gui) {
            this.gui = gui;
        }

        public void actionPerformed(ActionEvent ae) {
            String action = ae.getActionCommand();
            if(action.equals("Connect")) {
                try{
                    ConnectionProxy proxy = new ConnectionProxy(new Socket("127.0.0.1",8080));
                    connectBtn.setVisible(false);
                    sendBtn.setVisible(true);
                    send_panel.getRootPane().setDefaultButton(sendBtn); // KeyPress Enter
                    consumer = proxy;
                    producer = proxy;
                    producer.addConsumer(this.gui);
                    proxy.start();
                    consume("Connection established...\n");

                    consumer.consume(text_field.getText());
                    text_field.setText("");
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(action.equals("Send")){
                if (!text_field.getText().equals(""))
                    consumer.consume(text_field.getText());
                text_field.setText("");
            }
        }
    }

    public static void main(String[] args) {
        ClientGUI gui = new ClientGUI();
        gui.go();
    }
}