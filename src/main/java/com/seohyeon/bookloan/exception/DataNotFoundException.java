package com.seohyeon.bookloan.exception;

import java.lang.reflect.Type;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String request, Type data) {
        super(request + "에 해당하는 데이터를 찾을 수 없었습니다.");
    }
}
