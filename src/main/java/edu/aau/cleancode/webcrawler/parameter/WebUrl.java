package edu.aau.cleancode.webcrawler.parameter;

//TODO: Rename into WebURLParameter and change the second generic Type into a URL class that uses the java.net.url implementation
//for now we leave it as String
public final class WebUrl extends Parameter<String> {

   public WebUrl(String value){
         super(value);
   }


    /**
     *
     * @return

    public boolean isValid(){
       return super.isValid(this);
   }
    */



}
