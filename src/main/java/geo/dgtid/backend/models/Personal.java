package geo.dgtid.backend.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "personal", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rfc"}),
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"curp"})
})
public class Personal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @OneToOne
    // @JoinColumn(name = "id_extension")
    // private ExtensionTelefonica extensionTelefonica;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @Column(length = 13)
    private String rfc;

    @Column(length = 18)
    private String curp;

    @Email(message = "Email incorrecto")
    @NotEmpty
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dependencia")
    private Dependencia dependencia;

    public Personal(String nombre,String apellidoPaterno, String apellidoMaterno, String rfc, String curp, String email, Dependencia dependencia) {
        super();
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.curp = curp;
        this.email = email;
        this.dependencia = dependencia;
    }

    public Personal() {
    }

}
