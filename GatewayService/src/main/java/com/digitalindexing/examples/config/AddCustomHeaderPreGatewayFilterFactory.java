package com.digitalindexing.examples.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AddCustomHeaderPreGatewayFilterFactory
    extends AbstractGatewayFilterFactory<AddCustomHeaderPreGatewayFilterFactory.Config> {

  public AddCustomHeaderPreGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest().mutate().header("X-Custom-Pre", config.msg).build();
      log.info("from add custom pre header filter before request");
      return chain
          .filter(exchange.mutate().request(request).build())
          .then(
              Mono.fromRunnable(
                  () -> {
                    log.info("from add custom pre header filter after request");
                  }));
    };
  }

  public static class Config {
    private String msg;

    public void setMsg(String msg) {
      this.msg = msg;
    }
  }
}
