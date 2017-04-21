load("nashorn:mozilla_compat.js");
importPackage(Packages.daoc.age);
importPackage(Packages.daoc.age.ejemplos);
importPackage(Packages.daoc.age.ejemplos.sudoku);
importPackage(Packages.daoc.age.ejemplos.timetable);
importPackage(Packages.age);
importPackage(java.util.concurrent);

pool = Executors.newFixedThreadPool(4);

function poolExecute(poblacion) {
    pool.execute(poblacion);
}


