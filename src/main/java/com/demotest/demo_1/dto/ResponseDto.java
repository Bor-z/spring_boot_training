package com.demotest.demo_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ResponseDto {
    private String status_code;

    private String status_message;
}
