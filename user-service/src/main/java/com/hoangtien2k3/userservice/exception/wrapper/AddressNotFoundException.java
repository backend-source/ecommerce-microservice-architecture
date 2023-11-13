package com.hoangtien2k3.userservice.exception.wrapper;

import java.io.Serial;

public class AddressNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

}
