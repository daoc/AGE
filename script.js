
for (var i = 0; i < 6; i++) {
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

    poolExecute(poblacion);
}