package org.sistema.model;

import org.sistema.entidad.Cita;
import org.sistema.interfaces.CitaUseCase;
import org.sistema.persistencia.PersistenciaTicketCita;

public class CitaModel implements CitaUseCase {
    private PersistenciaTicketCita persistenciaTicketCita;

    public CitaModel(){
        persistenciaTicketCita = new PersistenciaTicketCita();
    }
    @Override
    public boolean imprimirCita(Cita c) {
        return persistenciaTicketCita.imprimirTicket(c);
    }
}
