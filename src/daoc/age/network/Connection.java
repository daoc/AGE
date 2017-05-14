
package daoc.age.network;

import java.io.IOException;
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
    private Scanner input;
    private PrintWriter output;
    private Reader reader;
    
    public Connection(Tasker tasker) {
        this.tasker = tasker;
    }
    
    public void openTaskReceiver() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            socket = serverSocket.accept();
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            reader = new Reader(input, tasker);
            reader.start();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void sendResponse(String answer) {
        output.println(answer);
    }
    
}
