import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class  client{



    public void go(){

        try {
            Socket socket = new Socket("127.0.0.2",4245);

            InputStreamReader input = new InputStreamReader(socket.getInputStream());

            BufferedReader reader = new BufferedReader(input);

            String advice = reader.readLine();

            System.out.println(advice);




            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        client client = new client();

        client.go();
    }
}
