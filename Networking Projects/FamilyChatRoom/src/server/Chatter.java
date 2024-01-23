package server;

import java.io.*;
import java.net.*;

public class Chatter implements Runnable{

    private final static String DEFAULT_NAME = "NoUsername";
    private static final String LEAVE = "leave";

    private final Socket socket;
    private final MessageBoard board;
    private final ChatServer server;

    private String name;
    private final DataInputStream in;
    private final DataOutputStream out;

    public Chatter(Socket socket, MessageBoard board, ChatServer server) throws IOException{
        this.socket = socket;
        this.board = board;
        board.addUser(this);
        this.server = server;
        name = DEFAULT_NAME;

        // takes input from socket
        in = new DataInputStream(socket.getInputStream());

        // sends output to the socket
        out = new DataOutputStream(socket.getOutputStream());
    }

    private void setName(){
        try {
            this.name = in.readUTF();
        }catch(IOException ignored){}
    }

    public void writeMessage(String name, String message){
        try {
            out.writeUTF('[' + name + "] " + message);
        }
        catch(SocketException s){System.err.println("Connection terminated by user " + this.name);}
        catch(IOException i){i.printStackTrace();}
    }

    public synchronized String getChatterName(){
        if(name.equals(DEFAULT_NAME)) try{wait();}catch(InterruptedException ignored){}
        return name;
    }

    private void chat(){
        String message;
        while(true){
            try{
                message = in.readUTF();
                if(message.equals(LEAVE))
                    break;
                board.writeMessage(name, message, this);
            }catch(IOException i){
                System.err.println("Connection terminated by client [" + name + ']');
                break;
            }
        }
    }

    private void leave() throws IOException{
        board.removeUser(this);
        server.leaveUser(this);
        in.close();
        out.close();
        socket.close();
    }

    @Override
    public void run(){
        setName();
        server.joinUser(this);
        chat();
        try{
            leave();
        }catch(IOException ignored){}
    }
}
