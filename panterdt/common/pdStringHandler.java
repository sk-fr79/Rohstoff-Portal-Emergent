/*
 * Created on 23.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panterdt.common;


/**
 * hilfsklasse um strings zu bearbeiten
 *
 */
public class pdStringHandler
{
    

    
    public static String ReplaceTeilString(String cQuelle, String cSuchen, String cErsetzen)
    {
        return pdStringHandler.ReplaceTeilString(cQuelle,cSuchen,cErsetzen,false);
    }    
    
    
    
    // methode macht ein replace auf einen string
    // hier wird der suchvorgang immer auf grossbuchstaben durchgeführt
    public static String ReplaceTeilString(String cQuelle, String cSuchen, String cErsetzen, boolean vglUPPER)
    {
        String 			cRueck 		= cQuelle;
        String			ccQuelle 	= cQuelle;
        String			ccSuchen  	= cSuchen;
       
        if (cQuelle == null || cSuchen == null || cErsetzen == null || cQuelle.equals("") || cSuchen.equals("") || cErsetzen.equals(""))
            return cRueck;
        
        if (vglUPPER) 
        {
            ccQuelle = cQuelle.toUpperCase();
            ccSuchen = cSuchen.toUpperCase();
        }
 
        int iStart 	= 	0;
        int iPos	=	ccQuelle.indexOf(ccSuchen,iStart);
        
        if (iPos == -1)                 // not found
            return cRueck;

        StringBuffer 	cNeu 	= new StringBuffer(cQuelle);
        
        do
        {
             iPos = ccQuelle.indexOf(ccSuchen,iStart);

            if (iPos >= 0)
            {
                cNeu = cNeu.replace(iPos, iPos + cSuchen.length(), cErsetzen);

                if (vglUPPER) 
                    ccQuelle = cNeu.toString().toUpperCase();
                else
                    ccQuelle = cNeu.toString();
                
                iStart = iPos+cErsetzen.length();
            }
            
        }
        while (iPos >= 0);

        return (cNeu.toString());
    }

    
    
}
