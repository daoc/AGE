
package daoc.age.ejemplos.timetable;

import daoc.age.Reporte;
import java.io.PrintWriter;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ReporteTimetable extends Reporte {
    
    @Override
    public void reportarFinal() {
        super.reportarFinal();
        try {
            PrintWriter pw = new PrintWriter("mejorhorario.html");
            pw.println("<html><body>");
            for(int aula = 0; aula < Timetable.AULAS; aula++) {
                pw.println("<p>Aula: " + aula + "</p>");
                pw.println("<table border='1'>");
                for(int hora = 0; hora < Timetable.HORAS; hora++) {
                    pw.println("<tr>");
                    for(int dia = 0; dia < Timetable.DIAS; dia++) {
                        pw.println("<td>");
                        pw.println(Timetable.getClase(getPoblacion().getMasApto(), aula, dia, hora));
                        pw.println("</td>");
                    }
                    pw.println("</tr>");
                }
                pw.println("</table>");
            }
            pw.println("</body></html>");
            pw.flush();
            pw.close();
        } catch(Exception e) {}
    }
    
}
