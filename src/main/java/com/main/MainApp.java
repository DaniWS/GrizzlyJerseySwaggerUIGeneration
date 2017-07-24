
package com.main;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.swagger.jaxrs.config.BeanConfig;

public class MainApp {
	// Base URI the Grizzly HTTP server will listen on
	public static final URI BASE_URI = URI.create("http://0.0.0.0:8080");

	public static HttpServer getLookupServer() {

		String resources = "com.main";
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.1");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setBasePath("");
		beanConfig.setResourcePackage(resources);
		beanConfig.setScan(true);

		final ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages(resources);
		resourceConfig.register(io.swagger.jaxrs.listing.ApiListingResource.class);
		resourceConfig.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		resourceConfig.register(JacksonFeature.class);
		resourceConfig.register(JacksonJsonProvider.class);

		return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
	}

	public static void main(String[] args) throws IOException {
		final HttpServer server = getLookupServer();
		server.start();
		ClassLoader loader = MainApp.class.getClassLoader();
		CLStaticHttpHandler docsHandler = new CLStaticHttpHandler(loader, "swagger-ui/");
		docsHandler.setFileCacheEnabled(false);

		ServerConfiguration cfg = server.getServerConfiguration();
		cfg.addHttpHandler(docsHandler, "/docs/");

	}

}