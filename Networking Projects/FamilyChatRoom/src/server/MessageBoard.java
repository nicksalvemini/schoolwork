package server;

import java.util.ArrayList;

public class MessageBoard {

    private final ArrayList<Chatter> users;

    public MessageBoard(){
        users = new ArrayList<>();
    }

    public synchronized void addUser(Chatter user){
        users.add(user);
    }
    public synchronized void removeUser(Chatter user){
        users.remove(user);
    }

    public void serverMessage(String serverName, String message){
        for(Chatter user : users) user.writeMessage(serverName, message);
    }
    public synchronized void writeMessage(String name, String message, Chatter sender) {
        for (Chatter user : users)
            if (!user.equals(sender))
                user.writeMessage(name, message);
    }
}
