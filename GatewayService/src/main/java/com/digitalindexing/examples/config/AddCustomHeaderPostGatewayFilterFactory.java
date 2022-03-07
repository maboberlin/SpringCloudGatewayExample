package com.digitalindexing.examples.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AddCustomHeaderPostGatewayFilterFactory
    extends AbstractGatewayFilterFactory<AddCustomHeaderPostGatewayFilterFactory.Config> {

  public AddCustomHeaderPostGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      log.info("from add custom post header filter before request");
      return chain
          .filter(exchange)
          .then(
              Mono.fromRunnable(
                  () -> {
                    log.info("from add custom post header filter after request");
                    exchange.getResponse().getHeaders().set("X-Custom-Post", config.msg);
                  }));
    };
  }

  public static class Config {
    String msg;

    public void setMsg(String msg) {
      this.msg = msg;
    }
  }
}
