package com.digitalindexing.examples.controllers;

import com.digitalindexing.examples.dto.MessageRequest;
import com.digitalindexing.examples.dto.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/service-b")
public class ServiceBController {

  @PostMapping("/message")
  public MessageResponse message(@RequestHeader("X-Custom-Pre") String customHeader, @RequestBody MessageRequest msg) {
    log.info("Message (ServiceB): {}", msg.getMessage());
    log.info("X-Custom-Pre header (ServiceB): {}", customHeader);
    Map<String, String> headerMap = buildHeaderMap();
    return new MessageResponse("Message received in Service B: " + msg.getMessage(), headerMap);
  }

  private Map<String, String> buildHeaderMap() {
    return new HashMap<>();
  }
}
