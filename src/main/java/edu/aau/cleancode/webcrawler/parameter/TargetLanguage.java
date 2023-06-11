package edu.aau.cleancode.webcrawler.parameter;

//TODO: Rename into WebURLParameter and change the second generic Type into a URL class that uses the java.net.url implementation
//for now we leave it as String
public class TargetLanguage extends Parameter<String> {

    public TargetLanguage(String lanugage){
        super(lanugage);
    }

  // public TargetLanguage(String value, ParameterValidator<TargetLanguage> validator){
       //    super(value,validator);
 //  }

  // public Targe

  // public boolean isValid(){
      // return super.isValid(this);
  // }




}
