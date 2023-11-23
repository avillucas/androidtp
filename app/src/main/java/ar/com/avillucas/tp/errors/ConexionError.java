package ar.com.avillucas.tp.errors;

import java.io.IOException;

public class ConexionError extends IOException {
    int status = 500;

    public ConexionError() {
        this.status = 500;
    }

    public ConexionError(String message, int status) {
        super(message);
        this.status = status;
    }

    public ConexionError(String message, Throwable cause) {
        super(message, cause);
    }

    public ConexionError(Throwable cause) {
        super(cause);
    }
}
