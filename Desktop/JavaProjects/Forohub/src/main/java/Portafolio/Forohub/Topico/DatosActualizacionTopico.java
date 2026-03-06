package Portafolio.Forohub.Topico;

import Portafolio.Forohub.Curso;

public record DatosActualizacionTopico(
        String titulo,
        String mensaje,
        StatusTopico estado,
        Curso curso
) {
}
