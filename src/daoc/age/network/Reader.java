
package daoc.age.network;

import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Reader extends Thread {
    private volatile boolean receiveTasks = true;    
    private Scanner input;
    private Tasker tasker;
    
    public Reader(Scanner input, Tasker tasker) {
        this.input = input;
        this.tasker = tasker;
    }
    
    @Override
    public void run() {
        while(input.hasNextLine()) {
            String task = input.nextLine();
            tasker.addTask(task);
        }
        
    }
    
}
