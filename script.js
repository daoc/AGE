print(2+2);
load("nashorn:mozilla_compat.js");
importPackage(Packages.daoc.age);
importPackage(Packages.daoc.age.ejemplos.sudoku);

poblacion = new Poblacion()
    .setAtributos(9*9, 1, 9)
    .setMetaAptitud(0)
    .setMaxTiempoCalculo(0, 4, 0)
    .setNumIndividuos(100000)
    .setParamReset(10, 25)
    .setMetodoGeneracion(
        new GeneracionRestringida(
            new RestriccionSudoku(Sudoku.leeArchivoSudokus("p096_sudoku.txt").get(0))))
    .setFuncionAptitud(new FuncionAptitudSudoku())
    .setReporteador(new ReporteSudoku());
    
poblacion.evolucionar();
//SudokuParalelo.main(null);

//s.main(null);