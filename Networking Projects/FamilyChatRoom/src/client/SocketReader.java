package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

public class SocketReader implements Runnable{

    private final DataInputStream fromServer;

    public SocketReader(DataInputStream dis){
        fromServer = dis;
    }

    @Override
    public void run(){
        String line;
        while(true) {
            try {
                line = fromServer.readUTF();
                System.out.println(line);
            }
            catch(SocketException s){
                System.err.println("Lost connection to chat room. No longer receiving messages.");
                break;
            }
            catch (IOException i) {
                i.printStackTrace();
                break;
            }
        }
        try{
            fromServer.close();
        }catch(IOException i){i.printStackTrace();}
    }
}
