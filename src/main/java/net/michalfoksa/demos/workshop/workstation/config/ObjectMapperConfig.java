package net.michalfoksa.demos.workshop.workstation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Ignore null and empty values when writing json.
        mapper.setSerializationInclusion(Include.NON_EMPTY);

        return mapper;
    }

}
