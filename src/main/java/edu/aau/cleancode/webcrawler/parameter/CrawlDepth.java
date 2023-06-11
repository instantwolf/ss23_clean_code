package edu.aau.cleancode.webcrawler.parameter;

  public final class CrawlDepth extends Parameter<Integer> {



   public CrawlDepth(Integer value, ParameterValidator<CrawlDepth> validator){
        super(value);
   }



   /**
    * public boolean isValid(){
       return super.isValid(this);
   }
    */


    public class DefaultCrawlDepthValidator implements ParameterValidator<CrawlDepth>{
        @Override
        public boolean validateParameter(CrawlDepth paramToValidate) {
            return true;
        }
    }

}
