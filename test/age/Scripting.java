
package age;

import java.nio.file.Files;
import java.nio.file.Paths;
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
        
        List<String> lines = Files.readAllLines(Paths.get("preRun.js"));
        lines.addAll(Files.readAllLines(Paths.get("script.js")));
        lines.addAll(Files.readAllLines(Paths.get("postRun.js")));

        engine.eval(String.join("\n", lines));
    }

    public static void test(String args[]) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = mgr.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println("ScriptEngineFactory Info");
            String engName = factory.getEngineName();
            String engVersion = factory.getEngineVersion();
            String langName = factory.getLanguageName();
            String langVersion = factory.getLanguageVersion();
            System.out.printf("\tScript Engine: %s (%s)\n", engName, engVersion);
            List<String> engNames = factory.getNames();
            for (String name : engNames) {
                System.out.printf("\tEngine Alias: %s\n", name);
            }
            System.out.printf("\tLanguage: %s (%s)\n", langName, langVersion);
        }
    }    
    
}
