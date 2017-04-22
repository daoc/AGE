/* global Packages, java */

load("nashorn:mozilla_compat.js");
importPackage(Packages.daoc.age);
importPackage(Packages.daoc.age.ejemplos);
importPackage(Packages.daoc.age.ejemplos.sudoku);
importPackage(Packages.daoc.age.ejemplos.timetable);
importPackage(Packages.age);
importPackage(java.util.concurrent);

NUM_THREADS = 5;

pool = Executors.newFixedThreadPool(NUM_THREADS);

function poolExecute(poblacion) {
    pool.execute(poblacion);
}


