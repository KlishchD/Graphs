package com.company.Graphs.Errors;

public class EdgeAlreadyExistsException extends Exception {
    public EdgeAlreadyExistsException() {
        super();
    }
    public EdgeAlreadyExistsException(String string) {
        super(string);
    }

}
