package mx.clicktwocell.simulador.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class Constants {

	public static final String EMPTY_STRING = "";
	public static final String HEADER = "header";
	public static final String UUID = "uuid";
	public static final String CONTROLLER_METRIC_TIMED = "example.metric";

	@Value("${product.simulador.id}")
	private long productIdSimulador;

	@Value("BadRequest - Parámetros inválidos")
	public String invalidParamsMessage;

	@Value("Petición no valida")
	public String invalidRequest;

	@Value("MediaType no soportado en recurso solicitado")
	public String mediaTypeNotSupportedMessage;

	@Value("Sin Autorización")
	public String unauthorizedExceptionMessage;

	private Constants() {
	}

}
