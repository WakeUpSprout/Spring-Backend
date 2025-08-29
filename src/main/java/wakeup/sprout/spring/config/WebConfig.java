package wakeup.sprout.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream()
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                .forEach(converter -> {
                    // Jackson JSON 컨버터의 기본 문자 인코딩을 UTF-8로 설정
                    MappingJackson2HttpMessageConverter jsonConverter =
                            (MappingJackson2HttpMessageConverter) converter;
                    jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
                });
    }
}
