package Portafolio.Forohub.Usuario;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.naming.factory.SendMailFactory;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String password;


    private String login;
    private String email;

    public Usuario(@Valid RegistroDatosUsuario datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.login = datos.email();
        this.password = datos.password();
    }
}