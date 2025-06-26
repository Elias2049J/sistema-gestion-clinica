package org.sistema.persistencia;

import org.sistema.entidad.Paciente;
import org.sistema.entidad.Cita;

public class PersistenciaPaciente extends Persistencia<Paciente>{
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_cuentas.txt";
    }

    @Override
    protected Paciente parsearLinea(String linea) {
        return null;
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-12s %-14s %-14s %-14s %-16s %-14s",
                "Id_Cuenta", "Saldo", "Tipo_Moneda", "Tipo_Cuenta", "Tasa_Interes", "Limite_Sobre_Giros", "Id_Cliente");
    }

    @Override
    protected String formatObjeto(Paciente c) {
        return null;
    }
}
