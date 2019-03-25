import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Server {

    String [] advice = {"Hello", "hi", "Ooooo", "Wau", "dfvesdgfe", "vdgegeg"};


    public void go() {


        try {
            ServerSocket serverSocket = new ServerSocket(4245);

            while (true){
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                String advice = getAdvice();

                writer.append(advice);

                writer.close();
                System.out.println(advice);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }





    }
    private String getAdvice(){
        int random = new Random().nextInt(advice.length);
        return advice[random];
    }



    public static void main(String[] args) {
        Server server = new Server();

        server.go();


    }
}