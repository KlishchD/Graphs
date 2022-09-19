package com.company.Graphs.Errors;

public class NoSuchEdgeException extends Exception {
    public NoSuchEdgeException(){
        super();
    }
    public NoSuchEdgeException(String string) {
        super(string);
    }
}
