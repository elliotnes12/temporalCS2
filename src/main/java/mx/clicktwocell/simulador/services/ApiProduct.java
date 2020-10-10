package mx.clicktwocell.simulador.services;

import mx.clicktwocell.simulador.entities.MainConfiguration;
import mx.clicktwocell.simulador.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ApiProduct {

    public List<Product> findAllProducts();

    public Optional<Product> getConfigurationActive(long Id);
}
