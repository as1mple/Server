import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Iterator;

public class Simple_Server {

    ArrayList clientOutputsStream;

    public class ClientHandler implements Runnable{
        BufferedReader reader;

        Socket socket;

        public ClientHandler(Socket clientSocket) {

            try {

                socket = clientSocket;

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

                reader = new BufferedReader(inputStreamReader);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            public void run(){

            String mess;

            try{

                while ((mess = reader.readLine()) !=null){
                    System.out.println("reader " +  mess);
                    tellEveryone(mess);


                }
            }catch (Exception ex ){
                ex.printStackTrace();
            }


        }

    }

    public static void main(String[] args) {
        new Simple_Server().go();
    }
    public  void go (){
        clientOutputsStream = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true){
                Socket clientSocket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

                clientOutputsStream.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));

                t.start();

                System.out.println("got a connection");
            }


        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
public void tellEveryone( String mess){
    Iterator it = clientOutputsStream.iterator();

    while (it.hasNext()){
        try{
            PrintWriter writer = (PrintWriter) it.next();

            writer.println(mess);

            writer.flush();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}








}
