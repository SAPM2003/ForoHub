package Portafolio.Forohub.controller;


import Portafolio.Forohub.Topico.*;
import Portafolio.Forohub.Usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        var yaExiste = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (yaExiste) {
            return ResponseEntity.badRequest().body("Ya existe un topico con el mismo titulo y mensaje.");
        }
        if (!usuarioRepository.existsById(datos.usuario_id())) {
            return ResponseEntity.badRequest().body("Error: el ID del autor " + datos.usuario_id() + " no existe en la base de datos.");
        }
        var autor = usuarioRepository.getReferenceById(datos.usuario_id());
        var topico = new Topico(datos, autor);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalladoTopico(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion) {
        var pagina = repository.findByStatusNot(StatusTopico.INACTIVO, paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El topico con ID " + id + " no fue encontrado.");
        }
        var topico = topicoOptional.get();
        return ResponseEntity.ok(new DatosDetalladoTopico(topico));
    }
    @Transactional
    @PutMapping("{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            topico.actualizarInformacion(datos);
            return ResponseEntity.ok(new DatosDetalladoTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var topico = repository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();

    }
}
