package mx.clicktwocell.simulador.api;


import mx.clicktwocell.simulador.business.ProductBusiness;
import mx.clicktwocell.simulador.business.SimuladorBusiness;
import mx.clicktwocell.simulador.entities.MainConfiguration;
import mx.clicktwocell.simulador.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/hipoteca")
public class SimuladorHipotecaController {


    @Value("${product.simulador.id}")
    private long productId;

    @Autowired
    private ProductBusiness productBusiness;

    @Autowired
    private SimuladorBusiness simuladorBusiness;


    /**
     * Método hace el calculo para el simulador
     *
     * @param precioVivienda
     * @param montoPrestamo
     * @param plazo
     * @return
     */
    @GetMapping("calculadora/{precioVivienda}/{montoPrestamo}/{plazo}")
    public ResponseEntity calculadora(
            @PathVariable("precioVivienda") String precioVivienda,
            @PathVariable("montoPrestamo") String montoPrestamo,
            @PathVariable("plazo") int plazo ) {


        Optional<Product> opt = productBusiness.getConfigurationActive(productId);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //Obtenemos la primera configuración activa
        MainConfiguration mainConfiguration = opt.get().getConfigurationList().get(0);


        return ResponseEntity.ok(simuladorBusiness.calculaHipoteca(mainConfiguration,precioVivienda,montoPrestamo,plazo));

    }
}