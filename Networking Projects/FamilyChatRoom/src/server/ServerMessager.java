package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerMessager extends Thread{

    private static final String NAME = "SERVER";
    private static final String QUIT = "stop";

    private final BufferedReader consoleMessage;
    private final MessageBoard board;
    private final ChatServer server;

    public ServerMessager(ChatServer server, MessageBoard board){
        this.server = server;
        this.board = board;
        consoleMessage = new BufferedReader(new InputStreamReader(System.in));
    }

    public synchronized void writeMessage(String message){
        board.serverMessage(NAME, message);
    }

    private void closeServer(){
        server.close();
    }

    @Override
    public void run(){
        String message;
        while (true)
        {
            try
            {
                message = consoleMessage.readLine();
                if(message.equals(QUIT)) {
                    System.out.println("Closing server...");
                    closeServer();
                    break;
                }else{
                    writeMessage(message);
                }
            }
            catch(IOException i)
            {
                System.err.println("This exception should never be thrown.");
                break;
            }
        }
    }
}