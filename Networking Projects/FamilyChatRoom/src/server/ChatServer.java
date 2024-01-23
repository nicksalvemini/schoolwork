package server;

import java.net.*;
import java.io.*;
import java.util.HashMap;

public class ChatServer implements Runnable{

    private final MessageBoard board;
    private ServerSocket server;
    private int activeUsers;
    private final HashMap<Chatter, String> users;
    private final HashMap<Chatter, Thread> userThreads;
    private final ServerMessager writeFromServer;

    public ChatServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Chat Room Initialized on IP " + Inet4Address.getLocalHost().getHostAddress());
        }
        catch (IOException i) {i.printStackTrace();}

        board = new MessageBoard();
        users = new HashMap<>();
        userThreads = new HashMap<>();
        writeFromServer = new ServerMessager(this, board);
        writeFromServer.start();
        activeUsers = 0;
    }

    private void acceptConnections(){
        Socket sock;
        while(true){
            sock = null;
            try {
                sock = server.accept();
                Chatter user = new Chatter(sock, board, this);
                Thread userTh = new Thread(user);
                userThreads.put(user, userTh);
                writeFromServer.writeMessage("New user connecting, waiting for name...");
                userTh.start();
            }
            catch(SocketException s){break;}
            catch(IOException i){i.printStackTrace(); break;}
        }
        try {
            assert sock != null;
            sock.close();
        } catch (Exception ignored) {}
    }

    public synchronized void joinUser(Chatter user){
        users.put(user, user.getChatterName());
        writeFromServer.writeMessage(users.get(user) +
                " connected successfully.\n\tUsers connected: " + ++activeUsers +
                "\n\tWelcome to the server " + users.get(user) + '!');
        System.out.println(users.get(user) + " just joined the server.");
    }

    public synchronized void leaveUser(Chatter user){
        writeFromServer.writeMessage(users.get(user) +
                " disconnected.\n\tUsers connected: " + --activeUsers);
        System.out.println(users.get(user) + " just left the server.");
        users.remove(user);
    }

    public void close(){
        try {
            server.close();
        }catch(IOException i){i.printStackTrace();}
    }

    @Override
    public void run(){
        System.out.println("Now accepting connections...");
        acceptConnections();
        System.out.println("No longer accepting connections.");
        writeFromServer.writeMessage("No longer accepting incoming connections.");

        for(Thread th : userThreads.values())
            try {
                th.join();
            }catch(InterruptedException ignored){}
        try {
            writeFromServer.join();
        }catch(InterruptedException ignored){}
    }
}
