package com.silicus.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
/**
 * Swagger configurations for REST APIs
 * @author SMurmu
 *
 */
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.silicus");

		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		io.swagger.jaxrs.config.BeanConfig beanConfig = new io.swagger.jaxrs.config.BeanConfig();
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/greatauk/api");
		beanConfig.setResourcePackage("com.silicus");
		beanConfig.setScan(true);
		beanConfig.setPrettyPrint(true);

		io.swagger.models.Info info = new io.swagger.models.Info();
		io.swagger.models.Contact contact = new io.swagger.models.Contact();
		contact.setEmail("sandip.murmu@gmail.com");
		contact.setName("Sandip Murmu");
		contact.setUrl("https://sandipmurmu.github.io");
		info.setContact(contact);
		info.setDescription("REST Api Examples");
		info.setTitle("REST APIs");
		info.setVersion("1.0");
		beanConfig.setInfo(info);

	}
}