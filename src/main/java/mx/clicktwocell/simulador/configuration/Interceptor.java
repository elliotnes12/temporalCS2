package mx.clicktwocell.simulador.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.clicktwocell.simulador.constants.Constants;
import mx.clicktwocell.simulador.constants.LogValues;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler)
			throws ServletRequestBindingException {

		long startTx = System.currentTimeMillis();
		request.setAttribute("startTx", startTx);
		
		request.getHeaderNames();

		MDC.put(Constants.UUID, request.getHeader(Constants.UUID));

		log.info(LogValues.START_INTERCEPTOR, request.getRequestURI());

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler,
			Exception ex) {

		long startTx = (long) request.getAttribute("startTx");
		long timeElapsed = System.currentTimeMillis() - startTx;

		log.info(LogValues.FINISH_INTERCEPTOR, request.getRequestURI(), timeElapsed);

		request.removeAttribute("startTX");

		MDC.clear();
	}

}