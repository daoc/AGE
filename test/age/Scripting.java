/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package age;

import java.io.FileReader;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 *
 * @author diego
 */
public class Scripting {

    public static void main(String args[]) throws Throwable {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        engine.eval(new FileReader("script.js"));
        //engine.eval("script.js");
        
//        System.load("nashorn:mozilla_compat.js");
//
//        importClass(java.util.HashSet);
//        var set = new HashSet();
//
//        importPackage(java.util);
//        var list = new ArrayList(); 
//
//
//        
//        
//        engine.eval("new daoc.age.ejemplos.sudoku.SudokuParalelo();");
//        System.out.println(engine.eval("sum(1, 2);"));
    }

    public static void test(String args[]) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        List<ScriptEngineFactory> factories
                = mgr.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println("ScriptEngineFactory Info");
            String engName = factory.getEngineName();
            String engVersion = factory.getEngineVersion();
            String langName = factory.getLanguageName();
            String langVersion = factory.getLanguageVersion();
            System.out.printf("\tScript Engine: %s (%s)\n",
                    engName, engVersion);
            List<String> engNames = factory.getNames();
            for (String name : engNames) {
                System.out.printf("\tEngine Alias: %s\n", name);
            }
            System.out.printf("\tLanguage: %s (%s)\n",
                    langName, langVersion);
        }
    }    
    
}
