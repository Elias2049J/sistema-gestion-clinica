package org.sistema.model.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    private Integer idConsulta;
    private String motivo;
    private String observaciones;
}
