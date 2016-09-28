package com.silicus.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(1)
public class WebApplnitializer implements WebApplicationInitializer {
	
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		//create applicationContext
		AnnotationConfigWebApplicationContext webapCtx
		= new AnnotationConfigWebApplicationContext();
		webapCtx.register(SpringAppConfig.class);
		webapCtx.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(webapCtx));
		// Set the Jersey used property to it won't load a ContextLoaderListener
        servletContext.setInitParameter("contextConfigLocation", "");
		/*servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, apCtx);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("jerysey", new ServletContainer());
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);*/
	}
	 
}
