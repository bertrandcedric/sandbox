package fr.bertrand.cedric.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@ServerInterceptor
public class LoggingInterceptor implements PreProcessInterceptor, PostProcessInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		logger.info(String.format("%s url %s.", request.getHttpMethod(), request.getUri().getRequestUri()));
		return null;
	}

	@Override
	public void postProcess(ServerResponse response) {
		logger.info(String.format("return status %s.", response.getStatus()));
	}

}
