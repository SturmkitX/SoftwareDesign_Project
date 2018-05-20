package controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/media/**").addResourceLocations("file:///home/bogdan/SoftwareDesign_Project/media/");
        registry.addResourceHandler("/cover/**").addResourceLocations("file:///home/bogdan/SoftwareDesign_Project/cover/");
    }
}
