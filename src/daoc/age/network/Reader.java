
package daoc.age.network;

import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Reader extends Thread {
    private volatile boolean receiveTasks = true;    
    private ObjectInputStream input;
    private Tasker tasker;
    
    public Reader(ObjectInputStream input, Tasker tasker) {
        this.input = input;
        this.tasker = tasker;
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                Task task = (Task) input.readObject();
                tasker.addTask(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
    
}
