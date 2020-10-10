package mx.clicktwocell.simulador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deadlines")
@Getter
@Setter
public class Deadlines {

    @Id
    public Long deaddlines_pk;

    public String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "deadlines", fetch = FetchType.LAZY)
    private List<MainConfiguration> mainConfigurations;

    public Deadlines(){
         this.mainConfigurations = new ArrayList<>();
    }
}
