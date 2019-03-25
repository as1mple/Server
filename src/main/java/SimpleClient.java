import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {


    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main(String[] args) {
        SimpleClient client = new SimpleClient();
        client.go();

    }

    public void  go(){


        JFrame frame = new JFrame("Simple chat");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetwork();

        Thread thread = new Thread(new IncomingREader());

        thread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400,500);
        frame.setVisible(true);


    }

    private void setUpNetwork(){
        try{
            socket = new Socket("127.0.0.1",500);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(socket.getOutputStream());

            System.out.println("networking established");


        } catch ( IOException ex ){
            ex.fillInStackTrace();
        }
    }


    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev ){
            try{
                writer.println(outgoing.getText());
                writer.flush();
            }catch (Exception ex){
                ex.printStackTrace();
            }



            outgoing.setText("");
            outgoing.requestFocus();
        }

    }

    public  class  IncomingREader implements  Runnable{
        public void run(){
            String mes;
            try{
                while ((mes=reader.readLine())!= null){
                    System.out.println("reader " + mes);
                    incoming.append(mes );
                }
            }catch (Exception ex ){
                ex.printStackTrace();
            }
        }
    }
}
