package mx.clicktwocell.simulador.clients;

import mx.clicktwocell.simulador.constants.ApiValues;
import mx.clicktwocell.simulador.request.RequestApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "MsProductsDomain", url = "http://localhost:8080/")
public interface IFeignClient {

	@PostMapping(value = ApiValues.BASE_PATH_CONSUMES_EXAMPLE)
	String consultExample(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody RequestApi request);

}
