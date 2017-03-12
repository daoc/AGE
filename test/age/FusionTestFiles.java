/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package age;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class FusionTestFiles {
    
    public static void main(String[] args) throws IOException {
        ///Users/diego/Dropbox/tmp/TestThreads
        
        File dir = new File("/Users/diego/Dropbox/tmp/TestEstad");
        File[] file = dir.listFiles();
        for(File f : file) {
            String fileName = f.getName() + ";";
            Files.lines(f.toPath()).forEach(x -> System.out.println(fileName + x));
        }
    }
    
}
