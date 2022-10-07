package br.com.books.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/*
	 * SELECIONANDO RETORNO DE TIPO POR QUERY PARAMS
	 * 
	 * @Override public void
	 * configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	 * configurer.favorParameter(true).parameterName("mediaType").ignoreAcceptHeader
	 * (true) .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.
	 * APPLICATION_JSON) .mediaType("json",
	 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML); }
	 */

	
	/*
	 * SELECIONANDO RETORNO DE TIPO POR HEADER
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false).parameterName("mediaType").ignoreAcceptHeader(false)
				.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
	}
}
