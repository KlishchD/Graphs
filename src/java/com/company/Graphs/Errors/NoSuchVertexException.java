package com.company.Graphs.Errors;

public class NoSuchVertexException extends Exception {
    public NoSuchVertexException(){
        super();
    }
    public NoSuchVertexException(String string){
        super(string);
    }

}
