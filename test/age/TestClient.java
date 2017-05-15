/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package age;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
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
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(".setAtributos(9*9, 1, 9).setMetaAptitud(0).setMaxTiempoCalculo(0, 4, 0).setNumIndividuos(100000).setParamReset(10, 25).setMetodoGeneracion(new GeneracionRestringida(new RestriccionSudoku(Sudoku.leeArchivoSudokus(\"0.txt\").get(0)))).setFuncionAptitud(new FuncionAptitudSudoku())");
            System.out.println(scanner.nextLine());
        } catch (Exception ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
