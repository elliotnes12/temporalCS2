package mx.clicktwocell.simulador.repositories;

import mx.clicktwocell.simulador.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p from Product p join FETCH p.configurationList c")
    public List<Product> findAllProducts();

    @Query("SELECT p from Product p join FETCH p.configurationList c WHERE p.id = ?1 and c.active = true")
    public Optional<Product> getConfigurationActive(long id);
}
