package edu.aau.cleancode.webcrawler.parser;

public class HtmlHeading {

    HtmlHeadingLevel level;

    String content = "";

    HtmlHeading(HtmlHeadingLevel level, String content){
        this.level = level;
        this.content = content;
    }

    HtmlHeading(int headingLevel, String content){

        this.level =  HtmlHeadingLevel.isValidHtmlHeadingLevel(headingLevel) ?
                 HtmlHeadingLevel.createFromInt(headingLevel) : HtmlHeadingLevel.getDefaultLevel();
        this.content = content;
   }

    public String getString(){
        return this.content;
    }
}
