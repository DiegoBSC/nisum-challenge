package com.nisum.service.apirestusers.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionBase {
    private String message;
}
