package com.colvir.webinar18.dto;

import lombok.Builder;

@Builder
public class KafkaErrorResponse {

    private String payload;
    private String exceptionMessage;
    private String exceptionStacktrace;
}
