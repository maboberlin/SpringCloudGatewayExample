package com.digitalindexing.examples.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ReadPostBodyPreGatewayFilterFactory extends AbstractGatewayFilterFactory<ReadPostBodyPreGatewayFilterFactory.Config> {

    public ReadPostBodyPreGatewayFilterFactory() {
        super(ReadPostBodyPreGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(ReadPostBodyPreGatewayFilterFactory.Config config) {
        return (exchange, chain) -> Mono.fromRunnable(
            () -> {
                exchange.getRequest().getBody().subscribe(dataBuffer -> {
                    try {
                        String body = IOUtils.toString(dataBuffer.asInputStream(), StandardCharsets.UTF_8);
                        log.info("Request Body:\n{}", body);
                    } catch (IOException e) {
                        log.error("Error reading request body");
                    }
                });
            }
        );
    }

    public static class Config {
        private String msg;

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
