import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Developed by András Ács (acsandras@gmail.com)
 * Zealand / www.zealand.dk
 * Licensed under the MIT License
 * 2019-08-27
 */

public class WebServer {

    int port;


    public WebServer(int port) {
        this.port = port;
    }

    public boolean start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running");
            Socket server = serverSocket.accept();

            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Min første webserver</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    Min første webserver, jubii!\n" +
                    "</body>\n" +
                    "</html>";

            /*DataInputStream dis = new DataInputStream(server.getInputStream());
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());

            dis.readUTF();
            dos.writeUTF(html);
            dos.flush();*/

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream());

            in.readLine();
            out.println(html);
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Connection received.");

        return true;
    }

}
