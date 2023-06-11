package edu.aau.cleancode.webcrawler.parser;

public class ParseSelector {

     String selector;

     ParseSelectorType type;

    public ParseSelector(String selector, ParseSelectorType type) {
        this.selector = selector;
        this.type = type;
    }

    public String getSelector(){
        return this.selector;
    }

    public ParseSelectorType getSelectorType(){
        return this.type;
    }
}
