
package daoc.age.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Connection {
    private static final int SERVER_PORT = 8888;
    private final Tasker tasker;
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Reader reader;
    private Responder writer;
    
    public Connection(Tasker tasker) {
        this.tasker = tasker;
    }
    
    public void openTaskReceiver() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            socket = serverSocket.accept();
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());          
            reader = new Reader(input, tasker);
            reader.start();
            writer = new Responder(tasker);
            writer.start();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void sendResponse(Task answer) {
        try {
            output.writeObject(answer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
