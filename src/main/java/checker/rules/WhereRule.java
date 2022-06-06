package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;

public class WhereRule implements Rule {
    //gde se u queriju nalazi where i onda da mu stavimo fleg koji proverava
    // za svaku rec posle where a pre sledece kljucne reci da li ima otvorenu zagradu

    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        boolean fleg=false;
       for (int i=0;i< reci.length;i++){
           if (reci[i].equalsIgnoreCase("where")){
              fleg=true;
              continue;
           } else if (fleg==true && !(KeywordsLoader.getInstance().getKeywordsList().contains(reci[i].toUpperCase()))){
               if (reci[i].contains("(")){
                   return "WHERE";
               }
           }
       }
       return "null";
    }
}
