package internet_store.web_ui.configuration;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@ServletComponentScan(basePackages = "internet_store.web_ui.servlet")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resourceViewResolver = new SpringResourceTemplateResolver();
        resourceViewResolver.setPrefix("classpath:/templates/internet_store/");
        resourceViewResolver.setSuffix(".html");
        resourceViewResolver.setTemplateMode(TemplateMode.HTML);
        resourceViewResolver.setCharacterEncoding("UTF-8");
        resourceViewResolver.setCheckExistence(false);
        return resourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("classpath:/templates/internet_store/images/")
                .setCachePeriod(320000)
                .resourceChain(false)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("estore");
    }
}