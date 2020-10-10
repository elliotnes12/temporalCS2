package mx.clicktwocell.simulador.services;

import mx.clicktwocell.simulador.entities.MainConfiguration;
import mx.clicktwocell.simulador.response.ResponseSimuladorHipoteca;

public interface ApiSimulador {

    public ResponseSimuladorHipoteca calculaHipoteca(MainConfiguration configuration,String precioVivienda,String montoPrestamo,int plazo);
}
