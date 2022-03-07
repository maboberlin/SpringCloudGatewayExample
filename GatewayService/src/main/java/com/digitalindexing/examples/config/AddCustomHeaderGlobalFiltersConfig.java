package com.digitalindexing.examples.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class AddCustomHeaderGlobalFiltersConfig {

  @Bean
  public GlobalFilter loggingGlobalFilter() {
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest().mutate().header("X-Custom-Global", "Global-Header").build();
      log.info("from add custom header global filter before request");
      return chain
              .filter(exchange.mutate().request(request).build())
              .then(
                      Mono.fromRunnable(
                              () -> {
                                log.info("from add custom header global filter after request");
                              }));
    };
  }
}
