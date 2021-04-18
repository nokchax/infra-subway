package nextstep.subway;

import nextstep.subway.helper.VersionHelper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Profile("test")
@Configuration
public class TestWebConfigurer implements WebMvcConfigurer {
    public static final String PREFIX_STATIC_RESOURCES = "/resources";
    private final VersionHelper versionHelper;

    public TestWebConfigurer(VersionHelper versionHelper) {
        this.versionHelper = versionHelper;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + versionHelper.getVersion() +  "/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(
                        CacheControl.noCache()
                                .cachePrivate()
                );
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registration.setFilter(etagHeaderFilter);
        registration.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return registration;
    }
}
