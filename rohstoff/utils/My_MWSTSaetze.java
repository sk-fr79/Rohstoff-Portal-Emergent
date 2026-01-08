/*
 * 
 */
package rohstoff.utils;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;


/**
 * 
 * klasse enthält methoden, um mwst-sätze zu id_adresse und/oder id_artikel zu finden
 * 
 */
public class My_MWSTSaetze
{

    
    // drei vectoren mit elementen vom typ MWST
    private Vector<My_MWST> vMWSTFirma = new Vector<My_MWST>();
    private Vector<My_MWST> vMWSTArtBez = new Vector<My_MWST>();
    private Vector<My_MWST> vMWSTBoth = new Vector<My_MWST>();
    private Vector<My_MWST> vMWSTAll = new Vector<My_MWST>();
    

    
    public My_MWSTSaetze(String cID_ADRESSE,String cID_ARTIKEL_BEZ) throws myException
    {
        
        // hilfsvector fuer das einfache rausfinden der schnittmenge
        Vector<String> vHelp = new Vector<String>();
        
        if (!bibALL.isEmpty(cID_ADRESSE))
        {
	        /*
	         * jetzt das mwst-satzarray erzeugen fuer einen kunden aufbauen, dieses ist die basis, ab der weitergeschaut wird
	         */
			String cSQL = "SELECT JT_MWSTSCHLUESSEL.STEUERSATZ,   NVL(IST_STANDARD,'N') FROM  "+
							bibE2.cTO()+".JT_MWSTSCHLUESSEL,"+bibE2.cTO()+".JT_KUNDE_MWST WHERE JT_MWSTSCHLUESSEL.ID_MWSTSCHLUESSEL=JT_KUNDE_MWST.ID_MWSTSCHLUESSEL AND " +
							"JT_KUNDE_MWST.ID_ADRESSE="+ cID_ADRESSE+ " ORDER BY STEUERSATZ";
			
			String cHelp[][] =  bibDB.EinzelAbfrageInArray(cSQL);

			if (cHelp == null || cHelp.length==0)
			{
			    if (cHelp == null)
			        throw new myExceptionForUser("Fehler bei Abfrage MWST-Sätze (finderMWSTSaetze:init");
			}
			else
			{
				for (int i=0;i<cHelp.length;i++)
				{
					double dHelp = new Double(cHelp[i][0]).doubleValue();
					this.vMWSTFirma.add(new My_MWST(dHelp,(cHelp[i][1].equals("Y"))));
					vHelp.add(cHelp[i][0]);
				}
			}
        }


        if (!bibALL.isEmpty(cID_ARTIKEL_BEZ))
        {
	        /*
	         * jetzt das mwst-satzarray erzeugen fuer einen kunden aufbauen, dieses ist die basis, ab der weitergeschaut wird
	         */
			String cSQL = "SELECT STEUERSATZ,   NVL(IST_STANDARD,'N') FROM  "+
									bibE2.cTO()+".JT_MWSTSCHLUESSEL,"+bibE2.cTO()+".JT_ARTIKEL_BEZ_MWST " +
									"WHERE JT_MWSTSCHLUESSEL.ID_MWSTSCHLUESSEL=JT_ARTIKEL_BEZ_MWST.ID_MWSTSCHLUESSEL AND " +
									"JT_ARTIKEL_BEZ_MWST.ID_ARTIKEL_BEZ="+cID_ARTIKEL_BEZ+ " ORDER BY STEUERSATZ";;
			
				
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String cHelp[][] =  oDB.EinzelAbfrageInArray(cSQL);
			bibALL.destroy_myDBToolBox(oDB);
			
			if (cHelp == null || cHelp.length==0)
			{
			    if (cHelp == null)
			        throw new myException("Fehler bei Abfrage MWST-Sätze (finderMWSTSaetze:init");
			}
			else
			{
				for (int i=0;i<cHelp.length;i++)
				{
					double dHelp = new Double(cHelp[i][0]).doubleValue();
					this.vMWSTArtBez.add(new My_MWST(dHelp,(cHelp[i][1].equals("Y"))));
					
					if (vHelp.contains(cHelp[i][0]))
					{
						this.vMWSTBoth.add(new My_MWST(dHelp,(cHelp[i][1].equals("Y"))));
					}
				}
			}
        }
        
        
        // jetzt noch alle steuersaetze einlesen

        /*
         * jetzt das mwst-satzarray erzeugen fuer einen kunden aufbauen, dieses ist die basis, ab der weitergeschaut wird
         */
		String cSQL = "SELECT STEUERSATZ,   NVL(IST_STANDARD,'N') FROM  "+
								bibE2.cTO()+".JT_MWSTSCHLUESSEL  ORDER BY STEUERSATZ";;
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String cHelp[][] =  oDB.EinzelAbfrageInArray(cSQL);
		bibALL.destroy_myDBToolBox(oDB);
		
		if (cHelp == null || cHelp.length==0)
		{
		    if (cHelp == null)
		        throw new myException("Fehler bei Abfrage MWST-Sätze (finderMWSTSaetze:init");
		}
		else
		{
			for (int i=0;i<cHelp.length;i++)
			{
				double dHelp = new Double(cHelp[i][0]).doubleValue();
				this.vMWSTAll.add(new My_MWST(dHelp,(cHelp[i][1].equals("Y"))));
			}
		}
        
    }


    
    
    
    /**
     * @param vMESTSaetze
     * @param bMitNullWert
     * @return s Array for dropdown/Comboboxes
     */
    private String[][] __get_MwstArray(Vector<My_MWST> vMESTSaetze, boolean bMitNullWert)
    {
        String[][] cRueck = null;
        int iOffset = 0;
        if (bMitNullWert)
        {
            cRueck = new String[vMESTSaetze.size()+1][2];
            cRueck[0][0]="-";
            cRueck[0][1]="";
            iOffset = 1;
        }
        else
        {
            cRueck = new String[vMESTSaetze.size()][2];
        }
        for (int i=0;i<vMESTSaetze.size();i++)
        {
            My_MWST oMW = vMESTSaetze.get(i);
            
            cRueck[i+iOffset][0]=oMW.get_cMWST_Formated();
            cRueck[i+iOffset][1]=oMW.get_cMWST_Formated();
            
        }
        return cRueck;
    }
    
    

    
  
    /**
     * @param bMitNullWert
     * @return s Array mit werten fuer selectfield (maskekonform formatiert)
     */
    public String[][] get_MWST_DropDownArray_AdressMWST(boolean bMitNullWert) 
    {
    	return this.__get_MwstArray(this.vMWSTFirma,bMitNullWert);
    }
    

    /**
     * @param bMitNullWert
     * @return s Array mit werten fuer selectfield (maskekonform formatiert)
     */
    public String[][] get_MWST_DropDownArray_ArtBez(boolean bMitNullWert) 
    {
    	return this.__get_MwstArray(this.vMWSTArtBez,bMitNullWert);
    }
    

    /**
     * @param bMitNullWert
     * @return s Array mit werten fuer selectfield (maskekonform formatiert)
     */
    public String[][] get_MWST_DropDownArray_Schnittmenge(boolean bMitNullWert) 
    {
    	return this.__get_MwstArray(this.vMWSTBoth,bMitNullWert);
    }

    
    /**
     * @param bMitNullWert
     * @return s Array mit werten fuer selectfield (maskekonform formatiert)
     */
    public String[][] get_MWST_DropDownArray_AllMWST(boolean bMitNullWert) 
    {
    	return this.__get_MwstArray(this.vMWSTAll,bMitNullWert);
    }
    

    

    
    public Vector<My_MWST> get_vMWSTAll()		{		return vMWSTAll;	}
	public Vector<My_MWST> get_vMWSTArtBez()	{		return vMWSTArtBez;	}
	public Vector<My_MWST> get_vMWSTBoth()		{		return vMWSTBoth;	}
	public Vector<My_MWST> get_vMWSTFirma()		{		return vMWSTFirma;	}
    
	
    
}
