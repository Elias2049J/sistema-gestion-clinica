package org.sistema.persistencia;

import org.sistema.entidad.Cita;

public class PersistenciaCita extends Persistencia<Cita> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_clientes.txt";
    }

    @Override
    protected Cita parsearLinea(String linea) {
        return null;
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-30s %-12s %-15s %-15s %-10s %-30s %-15s",
                "Id_Cliente", "Direccion", "Tipo_Cliente", "Nombre", "Apellido", "DNI", "Razon_Social", "RUC");
    }

    @Override
    protected String formatObjeto(Cita c) {
        return null;
    }
}
