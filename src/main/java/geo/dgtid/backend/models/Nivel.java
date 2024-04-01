package geo.dgtid.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_nivel")
public class Nivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10)
    private String descripcionNivel;

    // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_nodo")
    // private Nodo nodo;
}
