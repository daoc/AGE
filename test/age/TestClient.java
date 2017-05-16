
package age;

import daoc.age.network.Task;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class TestClient {
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());           
            String inst = ".setAtributos(9*9, 1, 9).setMetaAptitud(0).setMaxTiempoCalculo(0, 4, 0).setNumIndividuos(100000).setParamReset(10, 25).setMetodoGeneracion(new GeneracionRestringida(new RestriccionSudoku(Sudoku.leeArchivoSudokus(\"0.txt\").get(0)))).setFuncionAptitud(new FuncionAptitudSudoku())";
            Task task = new Task(socket.getLocalAddress().getHostName(), socket.getLocalPort(), 
                socket.getInetAddress().getHostName(), socket.getPort(),
                "0.txt", "0", inst, null);
            output.writeObject(task);
            task = (Task) input.readObject();
            System.out.println(task.response);
        } catch (Exception ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
