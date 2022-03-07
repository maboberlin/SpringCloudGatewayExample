package com.digitalindexing.examples.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class MessageResponse {
    private String message;
    private Map<String, String> headerMap;
}
