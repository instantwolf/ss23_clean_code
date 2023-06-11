package edu.aau.cleancode.webcrawler.parser;

public enum HtmlHeadingLevel {
    H1(1),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6);
    private final int headingLevel;

    HtmlHeadingLevel(int headinglevel){
        this.headingLevel = headinglevel;
    }

    public static boolean isValidHtmlHeadingLevel(int level){
        return level >= 1 && level <= 6;
    }

    public static HtmlHeadingLevel createFromInt(int headingLevel){
        return
               switch(headingLevel){
                   case 1 ->  H1;
                   case 2 ->  H2;
                   case 3 ->  H3;
                   case 4 ->  H4;
                   case 5 ->  H5;
                   case 6 ->  H6;
                   default -> throw new IllegalStateException("Unexpected value: " + headingLevel);
               };
    }

    public static HtmlHeadingLevel getDefaultLevel(){
        return HtmlHeadingLevel.H1;
    }
}
