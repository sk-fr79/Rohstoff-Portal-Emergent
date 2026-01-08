/*
 * Created on 20.06.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package rohstoff.utils;


import panter.gmbh.indep.dataTools.myDataRecord;
import panter.gmbh.indep.exceptions.myException;

public class MyVPos_TPA_FUHRE extends myDataRecord
{

     /**
     * @param cID_VPOS_TPA_FUHRE
     * @throws myException
     */
    public MyVPos_TPA_FUHRE(String cID_VPOS_TPA_FUHRE ) throws myException
    {
        super("JT_VPOS_TPA_FUHRE", cID_VPOS_TPA_FUHRE);
    }

    public String get_ID_ADRESSE_START()   {   return this.get_resultValue("ID_ADRESSE_START");  }
    public String get_ID_ADRESSE_ZIEL()   	{   return this.get_resultValue("ID_ADRESSE_ZIEL");  }
    public String get_ID_VPOS_KON_EK()   	{   return this.get_resultValue("ID_VPOS_KON_EK");  }
    public String get_ID_VPOS_KON_VK()   	{   return this.get_resultValue("ID_VPOS_KON_VK");  }
    public String get_ID_LAGER_START()   	{   return this.get_resultValue("ID_LAGER_START");  }
    public String get_ID_LAGER_ZIEL()   	{   return this.get_resultValue("ID_LAGER_ZIEL");  }
    
    public String get_MENGE_VORGABE_KO()   	{   return this.get_resultValue("MENGE_VORGABE_KO");  }
    public String get_MENGE_AUFLADEN_KO()   	{   return this.get_resultValue("MENGE_AUFLADEN_KO");  }
    public String get_MENGE_ABLADEN_KO()   	{   return this.get_resultValue("MENGE_ABLADEN_KO");  }
    
    public String get_NAME1_LIEFERANT()				{	return this.get_resultValue("L_NAME1");  }
    public String get_NAME2_LIEFERANT()				{	return this.get_resultValue("L_NAME2");  }
    public String get_NAME1_ABNEHMER()				{	return this.get_resultValue("A_NAME1");  }
    public String get_NAME2_ABNEHMER()				{	return this.get_resultValue("A_NAME2");  }
    
        
}
