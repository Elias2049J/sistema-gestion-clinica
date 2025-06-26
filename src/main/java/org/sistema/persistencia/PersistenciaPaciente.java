package org.sistema.persistencia;

import org.sistema.entidad.Paciente;

import java.util.ArrayList;

public class PersistenciaPaciente extends Persistencia<Paciente>{
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/pacientes/pacientes.txt";
    }

    @Override
    protected Paciente parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\s{2,}");
        if (campos.length < 8) return null;

        try {
            Integer idPaciente = Integer.parseInt(campos[0].trim());
            String nombre = campos[1].trim();
            String apellido = campos[2].trim();
            Integer edad = Integer.parseInt(campos[3].trim());
            String dni = campos[4].trim();
            String direccion = campos[5].trim();
            String telefono = campos[6].trim();
            String estado = campos[7].trim();

            Paciente p = new Paciente();
            p.setIdPaciente(idPaciente);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setEdad(edad);
            p.setDni(dni);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setEstado(estado);
            p.setHistorialCitas(new ArrayList<>());

            return p;
        } catch (Exception e) {
            System.out.println("Error al parsear línea: " + linea);
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    protected String getCabecera() {
        return String.format("%-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO");
    }

    @Override
    protected String formatObjeto(Paciente p) {
        return String.format("%-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                p.getIdPaciente(),
                p.getNombre() != null ? p.getNombre() : "",
                p.getApellido() != null ? p.getApellido() : "",
                p.getEdad() != null ? p.getEdad() : "",
                p.getDni() != null ? p.getDni() : "",
                p.getDireccion() != null ? p.getDireccion() : "",
                p.getTelefono() != null ? p.getTelefono() : "",
                p.getEstado() != null ? p.getEstado() : ""
        );
    }
}
