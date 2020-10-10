package mx.clicktwocell.simulador.business;

import mx.clicktwocell.simulador.entities.MainConfiguration;
import mx.clicktwocell.simulador.response.ResponseSimuladorHipoteca;
import mx.clicktwocell.simulador.services.ApiSimulador;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SimuladorBusiness implements ApiSimulador {
    public ResponseSimuladorHipoteca calculaHipoteca(MainConfiguration mainConfiguration, String precioVivienda, String montoPrestamo, int plazo){

        //Importe del préstamo
        BigDecimal va = new BigDecimal(montoPrestamo);

        //Precio de la vivienda
        BigDecimal pVivienda = new BigDecimal(precioVivienda);

        int nper = plazo * 12;
        int tasa = 12 / 12;
        BigDecimal van_neg = new BigDecimal(va.doubleValue() * -1);

        System.out.println(van_neg);

        //Formula Pago(taza,nper,importe = va)
        BigDecimal pago = new BigDecimal("51853.25");

        //Enganche Minimo
        BigDecimal engancheMinimo = new BigDecimal(pVivienda.doubleValue() - va.doubleValue());

        //Ingreso Minimo
        BigDecimal ingresoMinimo = new BigDecimal(pago.doubleValue() / Double.parseDouble(mainConfiguration.getFactorIngresoPago()));

        //Porcentaje de gastos notariales (db)
        int notarialesPorcentaje = Integer.parseInt(mainConfiguration.getGastosNotariales().replace("%",""));

        BigDecimal gastosNotariales = new BigDecimal((pVivienda.doubleValue() * notarialesPorcentaje)  / 100);

        double factorAvaluo = Double.parseDouble(mainConfiguration.getFactorAvaluo());

        BigDecimal avaluo = new  BigDecimal(pVivienda.doubleValue() * factorAvaluo);

        //Porcentaje de cobertura (db)
        double capertura =  Double.parseDouble(mainConfiguration.getComisionApertura().replace("%",""));

        BigDecimal comisionApertura = new BigDecimal(pVivienda.doubleValue() * capertura / 100);

        BigDecimal gastosIniciales =new BigDecimal(gastosNotariales.doubleValue() + avaluo.doubleValue() + comisionApertura.doubleValue());

        //Respuesta
        ResponseSimuladorHipoteca responseSimulador = new ResponseSimuladorHipoteca();

        responseSimulador.setPrecioVivienda(pVivienda.toString());
        responseSimulador.setPagoMensual(pago.toString());
        responseSimulador.setPlazoCredito(plazo);
        responseSimulador.setEngacheMinimo(engancheMinimo.toString());
        responseSimulador.setIngresoMinimo(ingresoMinimo.toString());
        responseSimulador.setCreditoMaximo(va.toString());
        responseSimulador.setAforo(mainConfiguration.getAforoIncial());
        responseSimulador.setGastosNotariales(gastosNotariales.toString());
        responseSimulador.setAvaluo(avaluo.toString());
        responseSimulador.setComisionApertura(comisionApertura.toString());
        responseSimulador.setGastosIniciales(gastosIniciales.toString());
        responseSimulador.setTasaInteres(mainConfiguration.getTasaInteres());

        return responseSimulador;
    }
}
