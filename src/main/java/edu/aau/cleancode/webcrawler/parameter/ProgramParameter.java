package edu.aau.cleancode.webcrawler.parameter;

import edu.aau.cleancode.webcrawler.parameter.ParameterProvider;
import edu.aau.cleancode.webcrawler.parameter.ParameterValidator;

public abstract class ProgramParameter<T> implements ParameterValidator<T>, ParameterProvider<T> {

    public T param;

    public String Name;


     public abstract boolean validateParameter(T param);

    /**
     * Gets the parameter from the specified source. The exact implementation depends on the implementor-class
     * @return parameter of Type T
     */
     public abstract T provideParameter();


}
