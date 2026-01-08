package rohstoff.utils;

import javax.servlet.http.HttpSession;

import panter.gmbh.indep.exceptions.myException;



/**
 *  kleine hilfsklasse,
 *  um infos zu einem vorgang zu holen   
 */
public class MyVKopf extends MyVKopfSMALL
{

    
    private 	MyAdress		oAdress						= null;
     
     
    public MyVKopf(String cid_vkopf,VorgangTableNames	otn, HttpSession osES) throws myException
    {
    	super(cid_vkopf,otn,osES);
    	
        /*
         * jetzt die id_adresse benutzten, um das myAdress - objekt zu bauen
         */
        this.oAdress = new MyAdress(this.get_oVKopfRecord().get_resultValue("ID_ADRESSE"), false);
        
    }

    public MyAdress get_oAdress()
    {
        return oAdress;
    }

}