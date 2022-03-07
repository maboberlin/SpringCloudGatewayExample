package com.digitalindexing.examples.controllers;

import com.digitalindexing.examples.dto.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/service-a")
public class ServiceAController {

  @GetMapping("/message")
  public MessageResponse message(@RequestHeader("X-Custom-Pre") String customHeader, @RequestHeader("X-Message") String messageHeader) {
    log.info("X-Custom-Pre header (ServiceA): {}", customHeader);
    log.info("X-Message header (ServiceA): {}", messageHeader);
    Map<String, String> headerMap = buildHeaderMap();
    return new MessageResponse("Message from Service A", headerMap);
  }


  private Map<String, String> buildHeaderMap() {
    return new HashMap<>();
  }
}
