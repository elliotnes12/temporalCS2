package mx.clicktwocell.simulador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "main_configuration")
@Getter
@Setter
public class MainConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long configuracionPk;

    private String tasaInteres;

    private String factorIngresoPago;

    private String aforoIncial;

    private String gastosNotariales;

    private String comisionApertura;

    private String factorAvaluo;

    private String comisionMensual;

    private String factorSeguroVida;

    private String factorSeguroDano;

    private String montoMinimoCredito;

    private String valorVivienda;

    private String cuadroTasas;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean active;

    @JsonIgnore
    @ManyToMany(mappedBy = "configurationList", fetch = FetchType.LAZY)
    private List<Product> products;

    @JsonManagedReference
    @JoinTable(
            name = "deadlines_configuration",
            joinColumns = @JoinColumn(name = "configuration_fk", nullable = false),
            inverseJoinColumns = @JoinColumn(name="deadlines_fk", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Deadlines> deadlines;

    public MainConfiguration(){
        this.deadlines = new ArrayList();
    }

}
