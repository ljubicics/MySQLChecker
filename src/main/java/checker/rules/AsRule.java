package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;

public class AsRule implements Rule {
    //da li posle as strane reci imaju navodnike ako ih je vise
    //ako je jedna rec onda na kraju ima zarez ili kljucnu rec posle
    //
    //NE RADI ZA SVE PRIMERE
    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        boolean fleg = false;
        for (int i = 0;i < reci.length; i++){
            if(reci[i].equalsIgnoreCase("as")){
                fleg = true;
                continue;
            }else if (fleg == true && !(KeywordsLoader.getInstance().getKeywordsList().contains(reci[i].toUpperCase()))){
               if(reci[i].contains(",") || KeywordsLoader.getInstance().getKeywordsList().contains(reci[i + 1].toUpperCase())){
                   fleg = false;
                   continue;
               } else {
                   if(reci[i].indexOf('"') == 0) {
                       int br = i;
                       while(true) {
                           br++;
                           if((reci[br].indexOf('"') == reci[br].length()-2  && reci[br].indexOf(',') == reci[br].length()-1) ||
                                   (reci[br].indexOf('"') == reci[br].length()-2 && KeywordsLoader.getInstance().getKeywordsList().contains(reci[br + 1].toUpperCase()))){
                               fleg = false;
                               break;
                           } else {
                               if(KeywordsLoader.getInstance().getKeywordsList().contains(reci[br + 1].toUpperCase()) || reci[br].indexOf(',') == reci[br].length()-1) {
                                   return "NIJE ISPOSTOVAN ALIAS ZA VISE RECI";
                               } else {
                                   continue;
                               }
                           }
                       }
                   } else {
                       return "NIJE ISPOSTOVAN ALIAS ZA VISE RECI";
                   }
               }
            }
        }
        return "SIUUUUU";
    }
}
