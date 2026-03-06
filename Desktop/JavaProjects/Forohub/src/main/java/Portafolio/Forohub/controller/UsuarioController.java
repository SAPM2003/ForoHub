package Portafolio.Forohub.controller;



import Portafolio.Forohub.Usuario.RegistroDatosUsuario;
import Portafolio.Forohub.Usuario.Usuario;
import Portafolio.Forohub.Usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid RegistroDatosUsuario datos) {
        var usuario = new Usuario(datos);
        repository.save(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }
}