package org.sistema.model.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {
    private Integer idDiagnostico;
    private String descripcion;
}
