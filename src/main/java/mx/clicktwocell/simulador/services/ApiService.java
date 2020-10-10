package mx.clicktwocell.simulador.services;

import mx.clicktwocell.simulador.request.RequestApi;
import mx.clicktwocell.simulador.response.ResponseApi;
import org.springframework.util.MultiValueMap;


public interface ApiService {

	ResponseApi example(RequestApi request, MultiValueMap<String, String> headers);

}
