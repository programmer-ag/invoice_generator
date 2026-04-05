package proj.ig.security.nav;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);

                        // If the file exists (like .js, .css, .png), serve it
                        // If it starts with /api, return null (let the Controller handle it)
                        if (requestedResource.exists() && requestedResource.isReadable()) {
                            return requestedResource;
                        } else if (resourcePath.startsWith("api/")) {
                            return null;
                        }

                        // Otherwise, it's a React route (like /login), so serve index.html
                        return location.createRelative("index.html");
                    }
                });
    }
}