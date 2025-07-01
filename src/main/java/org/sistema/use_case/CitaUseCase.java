package org.sistema.use_case;

import org.sistema.entidad.Cita;

public interface CitaUseCase {
    boolean imprimirCita(Cita c);
    boolean cancelarCita(Cita c);
}
