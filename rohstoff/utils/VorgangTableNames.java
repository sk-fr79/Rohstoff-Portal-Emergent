
package rohstoff.utils;

import panter.gmbh.basics4project.myCONST;


/**
 * definition der benutzten vorgangs-kopf/position/druckinfo - tabelle bei verschiedenen vorgangsarten
 */
public class VorgangTableNames
{
    
    
    private String cVKOPF_TAB = null;
    private String cVPOS_TAB = null;
    private String cVKOPF_DRUCK_TAB = null;
    private String cVPOS_ZUSATZ_TAB = null;
    
    
    /*
     * primary keys
     */
    private String cVKOPF_PK	= null;
    private String cVKOPF_DRUCK_PK	= null;
    private String cVPOS_PK	= null;
    private String cVPOS_ZUSATZ_TAB_PK = null;
   
    
    /*
     * Sequences
     */
    private String cVKOPF_SEQ= null;
    private String cVKOPF_DRUCK_SEQ= null;
    private String cVPOS_SEQ	= null;
    private String cVPOS_ZUSATZ_TAB_SEQ = null;
       

    /*
     * modulkennerzusatz fuer die autentifizierung der buttons
     */
    private String cMODULKENNER_ADDON = null;
    
    
    private String cVORGANG_TYP = null;

    /**
     * @param vorgangsArt
     */
    public VorgangTableNames(String vorgangsArt)
    {
        super();
        cVORGANG_TYP = vorgangsArt;
        
        /*
         * jetzt feststellen, welcher typ und wie demnach der report-basisname heist
         */
        String cVKOPF_BASE = "";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT)) 			cVKOPF_BASE="VKOPF_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_AUFT_BEST)) 		cVKOPF_BASE="VKOPF_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT)) 		cVKOPF_BASE="VKOPF_RG";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_LIEFERSCHEIN)) 	cVKOPF_BASE="VKOPF_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_RECHNUNG)) 		cVKOPF_BASE="VKOPF_RG";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)) 	cVKOPF_BASE="VKOPF_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT)) 		cVKOPF_BASE="VKOPF_TPA";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)) 		cVKOPF_BASE="VKOPF_KON";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)) 		cVKOPF_BASE="VKOPF_KON";
        
        
        String cVPOS_BASE = "";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT)) 			cVPOS_BASE="VPOS_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_AUFT_BEST)) 		cVPOS_BASE="VPOS_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT)) 		cVPOS_BASE="VPOS_RG";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_LIEFERSCHEIN)) 	cVPOS_BASE="VPOS_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_RECHNUNG)) 		cVPOS_BASE="VPOS_RG";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)) 	cVPOS_BASE="VPOS_STD";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT)) 		cVPOS_BASE="VPOS_TPA";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)) 		cVPOS_BASE="VPOS_KON";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)) 		cVPOS_BASE="VPOS_KON";

        this.cVKOPF_TAB = 			"JT_"+cVKOPF_BASE;
        this.cVKOPF_PK = 			"ID_"+cVKOPF_BASE;
        this.cVKOPF_DRUCK_TAB= 		"JT_"+cVKOPF_BASE+"_DRUCK";
        this.cVKOPF_DRUCK_PK= 		"ID_"+cVKOPF_BASE+"_DRUCK";
        this.cVPOS_TAB = 			"JT_"+cVPOS_BASE;
        this.cVPOS_PK = 			"ID_"+cVPOS_BASE;
        this.cVKOPF_SEQ = 			"SEQ_"+cVKOPF_BASE;
        this.cVPOS_SEQ = 			"SEQ_"+cVPOS_BASE;
        this.cVKOPF_DRUCK_SEQ= 		"SEQ_"+cVKOPF_BASE+"_DRUCK";

        
        
        this.cMODULKENNER_ADDON = "";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT)) 			this.cMODULKENNER_ADDON="_ANGEBOT";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_AUFT_BEST)) 		this.cMODULKENNER_ADDON="_AUFT_BEST";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT)) 		this.cMODULKENNER_ADDON="_GUTSCHRIFT";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_LIEFERSCHEIN)) 	this.cMODULKENNER_ADDON="_LIEERSCHEIN";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_RECHNUNG)) 		this.cMODULKENNER_ADDON="_RECHNUNG";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)) 	this.cMODULKENNER_ADDON="_ABNAHMEANGEBOT";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT)) 		this.cMODULKENNER_ADDON="_TRANSPORT";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)) 		this.cMODULKENNER_ADDON="_EK_KONTRAKT";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)) 		this.cMODULKENNER_ADDON="_VK_KONTRAKT";

        
        // zusatztabellen definieren
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT))
        {
        	this.cVPOS_ZUSATZ_TAB = 		"JT_VPOS_TPA_FUHRE";
        	this.cVPOS_ZUSATZ_TAB_PK = 		"ID_VPOS_TPA_FUHRE";
        	this.cVPOS_ZUSATZ_TAB_SEQ = 	"SEQ_VPOS_TPA_FUHRE";
        }

        // zusatztabellen definieren
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT) ||
        	this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)	)
        {
        	this.cVPOS_ZUSATZ_TAB = 		"JT_VPOS_KON_TRAKT";
        	this.cVPOS_ZUSATZ_TAB_PK = 		"ID_VPOS_KON_TRAKT";
        	this.cVPOS_ZUSATZ_TAB_SEQ = 	"SEQ_VPOS_KON_TRAKT";
        }

        // zusatztabellen definieren
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT) || 
        	this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT))
        {
        	this.cVPOS_ZUSATZ_TAB = 		"JT_VPOS_STD_ANGEBOT";
        	this.cVPOS_ZUSATZ_TAB_PK = 		"ID_VPOS_STD_ANGEBOT";
        	this.cVPOS_ZUSATZ_TAB_SEQ = 	"SEQ_VPOS_STD_ANGEBOT";
        }
      
        
    }
    
    
    
    
    
    public String get_cVKOPF_DRUCK_TAB()      	{        return cVKOPF_DRUCK_TAB;    }
    public String get_cVKOPF_DRUCK_PK()    		{        return cVKOPF_DRUCK_PK;    }
    public String get_cVKOPF_PK()    			{        return cVKOPF_PK;    }
    public String get_cVKOPF_SEQ()    			{        return cVKOPF_SEQ;    }
    public String get_cVKOPF_DRUCK_SEQ()    	{	     return cVKOPF_DRUCK_SEQ;    }
    public String get_cVKOPF_TAB()    			{        return cVKOPF_TAB;    }
    public String get_cVORGANG_TYP()    		{        return cVORGANG_TYP;    }
    public String get_cVPOS_PK()    			{        return cVPOS_PK;    }
    public String get_cVPOS_SEQ()    			{        return cVPOS_SEQ;    }
    public String get_cVPOS_TAB()    			{        return cVPOS_TAB;    }
    
    public String get_cVPOS_ZUSATZ_TAB_PK()   	{        return cVPOS_ZUSATZ_TAB_PK;    }
    public String get_cVPOS_ZUSATZ_TAB_SEQ()   	{        return cVPOS_ZUSATZ_TAB_SEQ;    }
    public String get_cVPOS_ZUSATZ_TAB()    	{        return cVPOS_ZUSATZ_TAB;    }
    
    public String get_cMODULKENNER_ADDON()  	{      return cMODULKENNER_ADDON;   }
}
