package com.example.demo.config;

import com.example.demo.model.ConvertAdapter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ConvertAdapter adapter() {
    return new ConvertAdapter();
}
}
