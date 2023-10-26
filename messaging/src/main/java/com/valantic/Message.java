package com.valantic;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String source;
    private String message;

    @Override
    public String toString() {
        return "Message { source='" + source + "'" + ", message='" + message + "'}";
    }
}
