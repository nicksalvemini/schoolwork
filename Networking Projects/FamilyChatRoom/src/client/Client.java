package client;

import java.net.*;
import java.io.*;

public class Client {

    private static final String LEAVE = "leave";

    private Socket socket;
    private DataOutputStream out;
    private BufferedReader in;
    private DataInputStream fromSocket;
    private final Thread incomingTh;

    public Client(String address, int port, String name){
        while(socket == null) {
            try {
                socket = new Socket(address, port);
                System.out.println("Connected to Chat Room.");

                // Input from client
                in = new BufferedReader(new InputStreamReader(System.in));

                // Output to send to socket
                out = new DataOutputStream(socket.getOutputStream());

                // Data from socket
                fromSocket = new DataInputStream(socket.getInputStream());
            } catch (ConnectException c) {
                System.err.println("Server not running! Verify serve is running and IP has been entered correctly.");
            } catch (IOException i) {
                i.printStackTrace();
                System.exit(2);
            }
        }

        SocketReader socketReader = new SocketReader(fromSocket);
        incomingTh = new Thread(socketReader);
        incomingTh.start();

        try{
            out.writeUTF(name);
        }catch(IOException i){
            i.printStackTrace();
            System.exit(3);
        }
    }
    public Client(String address, int port) {
        while(socket == null) {
            try {
                socket = new Socket(address, port);
                System.out.println("Connected to Chat Room.");

                // Input from client
                in = new BufferedReader(new InputStreamReader(System.in));

                // Output to send to socket
                out = new DataOutputStream(socket.getOutputStream());

                // Data from socket
                fromSocket = new DataInputStream(socket.getInputStream());
            } catch (ConnectException c) {
                System.err.println("Server not running! Verify serve is running and IP has been entered correctly.");
            } catch (IOException i) {
                i.printStackTrace();
                System.exit(2);
            }
        }

        SocketReader socketReader = new SocketReader(fromSocket);
        incomingTh = new Thread(socketReader);
        incomingTh.start();

        synchronized(this){
            try{
                wait(500);
            }catch(InterruptedException ignored){}
            System.out.print("Enter your name > ");
            try {
                String line = in.readLine();
                out.writeUTF(line);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    private void chat(){
        String line;

        while (true)
        {
            try
            {
                line = in.readLine();
                out.writeUTF(line);
                if(line.equals(LEAVE)) {
                    System.out.println("\nQuitting gracefully...");
                    break;
                }
            }
            catch(IOException i)
            {
                System.err.println("Connection terminated by remote host.");
                break;
            }
        }
    }

    private void close(){
        try {
            in.close();
            out.close();
            socket.close();
            incomingTh.join();
            System.out.println("Quit successfully.");
        }catch(IOException i){
            i.printStackTrace();
        }
        catch(InterruptedException ignored){}
    }

    public static void main(String[] args) {
        Client client = null;
        if(args.length == 0) {
            System.out.println("Usage: <Server IP> <name [optional]>");
            System.exit(3);
        }
        else if(args.length == 1)
            client = new Client(args[0], 5000);
        else
            client = new Client(args[0], 5000, args[1]);
        client.chat();
        client.close();
    }
}
