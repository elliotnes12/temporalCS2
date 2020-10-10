package mx.clicktwocell.simulador.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestApi {

	@NotBlank
	private String productId;
	@NotBlank
	private String originId;

}
