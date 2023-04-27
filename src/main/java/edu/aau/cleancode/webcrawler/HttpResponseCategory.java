package edu.aau.cleancode.webcrawler;

/**
 * This class is introduced so that responses from servers can be classified correctly
 * This class provides a set of static predefined Categories according to the HTTP standard
 */
public enum HttpResponseCategory {

    INFORMATIONAL(1, "Informational"),
    SUCCESSFUL(2, "Successful"),
    REDIRECTION(3, "Redirection"),
    CLIENT_ERROR(4, "Client error"),
    SERVER_ERROR(5, "Server error");

    private final int categoryNumber;
    private final String categoryDescription;

    /**
     * This constructor shall remain private because it´s only purpose is to enable the creation of the
     * statically predefined instances of this enum.
     * This prohibits the creation of invalid instances of an HTTP response category
     *
     * @param categoryNumber: the number of the category according to the http standard
     */
     HttpResponseCategory(int categoryNumber, String categoryDescription){
        this.categoryNumber = categoryNumber;
        this.categoryDescription = categoryDescription;
    }

    /** Takes an instance of HttpResponseStatusCode as argument and determines its http response code category based on
     *  the first digit of the response code
     *
     * @throws IllegalArgumentException if the statusCode provided is not part of the official http response code categories
     * @param statusCode a given instance of the HTTPResponseStatusCode enum class
     * @return a predefined instance of HttpResponseCategory based on the provided HttpResponseStatusCode
     */
    public static HttpResponseCategory getHttpResponseCategory(HttpResponseStatusCode statusCode){
        //get the first digit from the code
        int code = statusCode.getCode();
        int firstDigit = getFirstDigit(code);

        //searches the predefined static categories and returns the correct one based on the first digit of the responseCode
        for (HttpResponseCategory category : values()) {
            if(category.categoryNumber == firstDigit){
                return category;
            }
        }
        //if we did not find a match, an illegal argument (invalid instance of HttpResponseStatusCode) was provided
        throw new IllegalArgumentException("Invalid HTTP response code: " + code);
    }

    @Override
    public String toString() {
        return "HTTP Response Category: "+categoryDescription;
    }

    /**
     * @throws IllegalArgumentException: No negative numbers are allowed in this context
     * @param number: a positive number of which the first digit shall be extracted
     * @return the first digit of a given non-negative number as integer
     */
    private static int getFirstDigit(int number) {
        // Convert the number to a string to get its first digit without further calculations
        String numberStr = Integer.toString(number);

        //negative numbers are not allowed in this context
        if(number < 0){
            throw new IllegalArgumentException("Invalid number provided for conversion: "+number);
        }

        // Get the first character of the string and convert it back to an integer
        char firstChar = numberStr.charAt(0);

        return Character.getNumericValue(firstChar);
    }
}
