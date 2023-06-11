package edu.aau.cleancode.webcrawler.parameter;

/**
 * This interface declares the methods that need to be implemented by a ParameterValidator class , to ensure that
 * the parameters provided to the webcrawler are correct
 */
public interface ParameterValidator<T> {

    /**
     * @return a boolean that indicates whether the provided parameter fits the validation criteria
     */
    boolean validateParameter(T paramToValidate);
}
