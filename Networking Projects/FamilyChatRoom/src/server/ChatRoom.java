package server;

public class ChatRoom {
    public static void main(String[] args) {
        ChatServer server = new ChatServer(5000);
        Thread serverTh = new Thread(server);
        serverTh.start();

        try {
            serverTh.join();
        }catch(InterruptedException ignored){}
    }
}
