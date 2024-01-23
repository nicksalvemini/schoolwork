// A Java program for a Client
import java.net.*;
import java.io.*;

public class Client
{
    private String name = "Nick";

    // initialize socket and input output streams
    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream out	 = null;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "STOP" is input
        while (!line.equals("STOP"))
        {
            try
            {
                System.out.print("> ");
                line = input.readLine();
                out.writeUTF('[' + name + ']' + ' ' + line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public String getName(){return name;}

    public static void main(String args[])
    {
        Client client = new Client("192.168.1.43", 5000);
    }
}