package Portafolio.Forohub.Topico;

import Portafolio.Forohub.Usuario.Usuario;
import tools.jackson.databind.deser.jdk.StringArrayDeserializer;

import java.time.LocalDateTime;

public record DatosDetalladoTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fecha,
                                   StatusTopico status,
                                   String autor,
                                   String curso) {
    public DatosDetalladoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().toString());
    }
}
