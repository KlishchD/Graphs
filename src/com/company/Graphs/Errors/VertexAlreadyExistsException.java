package com.company.Graphs.Errors;

public class VertexAlreadyExistsException extends Exception {
    public VertexAlreadyExistsException() {
        super();
    }

    public VertexAlreadyExistsException(String string) {
        super(string);
    }
}
