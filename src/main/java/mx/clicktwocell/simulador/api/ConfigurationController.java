package mx.clicktwocell.simulador.api;

import javax.validation.Valid;

import io.swagger.annotations.*;
import mx.clicktwocell.simulador.business.ProductBusiness;
import mx.clicktwocell.simulador.constants.ApiValues;
import mx.clicktwocell.simulador.constants.Constants;
import mx.clicktwocell.simulador.entities.MainConfiguration;
import mx.clicktwocell.simulador.entities.Product;
import mx.clicktwocell.simulador.request.RequestApi;
import mx.clicktwocell.simulador.response.ErrorResponse;
import mx.clicktwocell.simulador.response.ResponseApi;
import mx.clicktwocell.simulador.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {


	@Autowired
	private ProductBusiness productBusiness;

	/**
	 * Método obtiene todos los productos con sus configuraciones
	 * @return
	 */
	@GetMapping("/all/products")
	@ApiOperation(value = "Obtiene todo los productos con sus configuraciones")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity findAllProducts(){

		List<Product> products = productBusiness.findAllProducts();

		if(products.isEmpty()){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(products);
	}

	/**
	 * Método obtiene el producto por Id con su configuración
	 * @param id+
	 *
	 * @return
	 */
	@GetMapping("/product/{id}")
	@ApiOperation(value = "Obtiene un producto por ID con sus configuraciones")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found")
	})
	public ResponseEntity findById(@ApiParam(name = "id",value = "Id del producto") @PathVariable("id") Long id){

		Optional<Product> opt = productBusiness.getConfigurationActive(id);

		if(!opt.isPresent()){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(opt.get());
	}



}