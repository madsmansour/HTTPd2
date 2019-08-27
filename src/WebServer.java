import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Developed by András Ács (acsandras@gmail.com)
 * Zealand / www.zealand.dk
 * Licensed under the MIT License
 * 2019-08-27
 */

public class WebServer implements Runnable {

    int port;
    private Socket socket;
    private ServerSocket serverSocket;


    public WebServer(int port) {
        this.port = port;
    }

    public WebServer(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server is running");
            while (true) {
                WebServer webServer = new WebServer(serverSocket.accept());
                Thread thread = new Thread(webServer);
                thread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Connection received.");
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Min første webserver</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    Min første webserver, jubii!\n" +
                    "<img src='https://berlingske.bmcdn.dk/media/cache/resolve/image_x_large/image/14/147568/17903031-19ind36376117-100137jpg.jpeg' width='500px' height='auto' >" +
                    "</body>\n" +
                    "</html>";
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            //header
            out.println("HTTP/1.1 200 OK");
            out.println("Server: Mansour Server");
            out.println("Date: " + new Date());
            out.println("Content type: text/html" );
            out.println();
            out.println(html);


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
                out.close();
                socket.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }


            /*DataInputStream dis = new DataInputStream(server.getInputStream());
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());

            dis.readUTF();
            dos.writeUTF(html);
            dos.flush();*/

    }
}
