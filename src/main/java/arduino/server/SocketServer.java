package arduino.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liu.jianyixiang on 2019/03/01.
 */
public class SocketServer {
    private int port = 8080;
    public SocketServer(){}

    public SocketServer(int port){
        this.port = port;
    }

    public void service(){
        try {
            ServerSocket server=new ServerSocket(port,50, InetAddress.getByName ("192.168.128.100"));
            //ServerSocket server=new ServerSocket(port);
            System.out.println(server.getLocalSocketAddress());
            Socket client = server.accept();
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (true) {
                    String line = null;
                    line = in.readLine();
                    if (line != null) {
                        System.out.println(line);
                        try {
                            Thread.currentThread().sleep(1000);
                            out.println("as");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                client.close();
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            new SocketServer().service();
    }
}
