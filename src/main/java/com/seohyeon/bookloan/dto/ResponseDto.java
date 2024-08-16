package com.seohyeon.bookloan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<D> {
    private boolean success;
    private String message;
    private D data;

    public static <D> ResponseDto<D> ok(D data){
        return new ResponseDto<>(true, null, data);
    }

    public static ResponseDto<?> error(String message) {
        return new ResponseDto<>(false, message, null);
    }
}