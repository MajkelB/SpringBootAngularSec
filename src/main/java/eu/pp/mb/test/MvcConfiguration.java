package eu.pp.mb.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import org.springframework.core.io.Resource;

import java.io.IOException;

// https://stackoverflow.com/questions/46148843/spring-boot-angular-4-routing-in-app-hits-the-server

@Configuration
@EnableWebMvc
//public class MvcConfiguration {
public class MvcConfiguration implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(MvcConfiguration.class);

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/**")
////                .addResourceLocations("classpath:/static/")
////                .resourceChain(true)
////                .addResolver(new PathResourceResolver() {
////
////                    @Override
////                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
////                        Resource requestedResource = location.createRelative(resourcePath);
////
////                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
////                                : new ClassPathResource("/static/index.html");
////                    }
////                });
//
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/")
//                .resourceChain(true)
//                .addResolver(n);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        logger.info( "*************************************************** " + resourcePath + " / " + location );
                        Resource requestedResource = location.createRelative(resourcePath);

                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/app/index.html");
                    }
                });

        registry.addResourceHandler("/**/*")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);

                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/app/index.html");
                    }
                });
    }

}
