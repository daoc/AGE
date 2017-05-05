.setAtributos(9*9, 1, 9)
.setMetaAptitud(0)
.setMaxTiempoCalculo(0, 4, 0)
.setNumIndividuos(100000)
.setParamReset(10, 25)
.setMetodoGeneracion(
    new GeneracionRestringida(
        new RestriccionSudoku(
            Sudoku.leeArchivoSudokus("0.txt").get(0))))
.setFuncionAptitud(new FuncionAptitudSudoku())
.setReporteador(new ReporteSudoku())
