package mx.clicktwocell.simulador.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_pk;

    private String name;

    @JoinTable(
            name = "product_configuration",
            joinColumns = @JoinColumn(name = "product_fk", nullable = false),
            inverseJoinColumns = @JoinColumn(name="configuration_fk", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<MainConfiguration> configurationList;

    public Product(){
        this.configurationList = new ArrayList();
    }
}
