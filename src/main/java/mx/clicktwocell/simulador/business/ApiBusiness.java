package mx.clicktwocell.simulador.business;


import mx.clicktwocell.simulador.constants.Constants;
import mx.clicktwocell.simulador.constants.LogValues;
import mx.clicktwocell.simulador.exceptions.UnauthorizedException;
import mx.clicktwocell.simulador.request.RequestApi;
import mx.clicktwocell.simulador.response.ResponseApi;
import mx.clicktwocell.simulador.services.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiBusiness implements ApiService {

	@Override
	public ResponseApi example(RequestApi request, MultiValueMap<String, String> headers) {
		log.debug(LogValues.START_BUSINESS, request);

		if(request.getOriginId().equals("1")) {
			throw new UnauthorizedException("El origen 1 no está autorizado.");
		}
		
		ResponseApi response = ResponseApi.builder().imageId(Constants.EMPTY_STRING).url(Constants.EMPTY_STRING)
				.build();

		log.debug(LogValues.FINISH_BUSINESS, response);
		return response;
	}

}
