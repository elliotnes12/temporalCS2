package mx.clicktwocell.simulador.response;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String code;
	private String details;
	private String message;
	private String location;
	private ZonedDateTime timestamp;
	
}
