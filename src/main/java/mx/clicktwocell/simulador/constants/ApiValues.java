package mx.clicktwocell.simulador.constants;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ApiValues {

	public static final String BASE_PATH = "${constants.api.uri.basePath}";
	public static final String SPECIFIC_PATH = "${constants.api.uri.specificPath}";
	public static final String BASE_PATH_CONSUMES_EXAMPLE = "${constants.api.uri.consumes.path}";

	public static final String OPERATION_API = "Examle Operation Api";
	public static final String OK = "ok";
	public static final String BAD_REQUEST = "BadRequest";
	public static final String INTERNAL_ERROR = "Error interno en el servidor";

	public static final int CODE_OK = 200;
	public static final int CODE_BAD_REQUEST = 400;
	public static final int CODE_INTERNAL_ERROR = 500;


	@Value("${constants.api.basePackage}")
	private String basePackage;

	@Value("${constants.swagger.title}")
	private String swaggerTitle;

	@Value("${constants.swagger.description}")
	private String swaggerDescription;

	@Value("${constants.swagger.version}")
	private String swaggerVersion;

	@Value("${constants.swagger.developer.name}")
	private String swaggerDeveloperName;

	@Value("${constants.swagger.developer.email}")
	private String swaggerDeveloperEmail;

	@Value("${constants.swagger.contact}")
	private String swaggerContact;

	private ApiValues() {
	}

}
