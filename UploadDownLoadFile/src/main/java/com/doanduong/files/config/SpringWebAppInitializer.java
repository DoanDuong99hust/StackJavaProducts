package com.doanduong.files.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // config context container
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationContextConfig.class);

        // config dispatcher
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        servletContext.addListener( new ContextLoaderListener(context));
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding","true");
        fr.addMappingForServletNames(null,true,"/*");
    }
}
