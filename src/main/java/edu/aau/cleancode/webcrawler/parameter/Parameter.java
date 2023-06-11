package edu.aau.cleancode.webcrawler.parameter;

import javax.xml.validation.Validator;

abstract class Parameter<T>{

    protected T value; //t might have the value, name , feedback

    public Parameter(T val){
        this.value = val;
    }

    public T getValue(){
        return value;
    }
}
