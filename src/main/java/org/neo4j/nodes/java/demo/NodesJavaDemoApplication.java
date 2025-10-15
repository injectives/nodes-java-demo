package org.neo4j.nodes.java.demo;

import io.micrometer.observation.ObservationRegistry;
import org.neo4j.driver.observation.micrometer.MicrometerObservationProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.neo4j.autoconfigure.ConfigBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NodesJavaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodesJavaDemoApplication.class, args);
    }

    @Bean
    ConfigBuilderCustomizer observationCustomizer(ObservationRegistry observationRegistry) {
        return configBuilder -> {
            var observationProvider = MicrometerObservationProvider.builder(observationRegistry)
                    .alwaysIncludeQuery(true)
                    .includeQueryParameters(true)
                    .includeUrlScheme(true)
                    .includeUrlTemplate(true)
                    .requestHeaderPredicate(name -> true)
                    .responseHeaderPredicate(name -> true)
                    .build();
            configBuilder.withObservationProvider(observationProvider);
        };
    }
}
