package mx.clicktwocell.simulador.business;

import lombok.extern.slf4j.Slf4j;
import mx.clicktwocell.simulador.entities.Product;
import mx.clicktwocell.simulador.repositories.ProductRepository;
import mx.clicktwocell.simulador.services.ApiProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductBusiness implements ApiProduct {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Optional<Product> getConfigurationActive(long Id) {
        return productRepository.getConfigurationActive(Id);
    }
}
