package com.ems.ems.config;

public class GenericResponse {
    private final int code;
    private final String status;
    private final Object message;

    public GenericResponse(int code, String status, Object message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public Object getMessage() {
        return message;
    }
}
