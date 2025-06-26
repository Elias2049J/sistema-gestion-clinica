package org.sistema.interfaces;

import java.util.List;

public interface ReportesInterface<T> {
    List<String> getListaPacientesReport(List<T> lista);
    boolean exportarReporte(List<T> lista);
    List<String> getListaPacientesTerceraEdadReport();
    boolean exportarReporteTerceraEdad();
}
