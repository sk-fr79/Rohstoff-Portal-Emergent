package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;



/*
 * abfragehashmap, sammelt alle relevanten infos zu einer KONTRAKTPOS oder einem Vector aus KONTRAKTPOS itionen
 */
public class My_HM_KONTRAKT_POS_INFO extends MyDataRecordHashMap 
{

//	public My_HM_KONTRAKT_POS_INFO(Vector<String> vID_VPOS_KON)  throws myException
//	{
//		super();
//
//		this.putAll(
//				new MyDataRecordHashMap("COUNT(*) AS ANZAHL_FUHREN_EK,"+		
//				"  NVL(SUM(  NVL(MENGE_VORGABE_KO,0)),0) AS VORGABE_MENGE_IN_FUHRE_EK, " +
//				"  NVL(SUM(  NVL(MENGE_AUFLADEN_KO,0)),0) AS AUFLADE_MENGE_IN_FUHRE_EK, " +
//				"  NVL(SUM(  NVL(MENGE_ABLADEN_KO,0)),0) AS ABLADE_MENGE_IN_FUHRE_EK",
//				bibE2.cTO()+".JT_VPOS_TPA_FUHRE", 
//				"ID_VPOS_KON_EK IN ("+bibALL.Concatenate(vID_VPOS_KON,",","-1")+") AND   NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'", true));
//		
//		this.putAll(
//				new MyDataRecordHashMap("COUNT(*) AS ANZAHL_FUHREN_VK,"+		
//				"  NVL(SUM(  NVL(MENGE_VORGABE_KO,0)),0) AS VORGABE_MENGE_IN_FUHRE_VK, " +
//				"  NVL(SUM(  NVL(MENGE_AUFLADEN_KO,0)),0) AS AUFLADE_MENGE_IN_FUHRE_VK, " +
//				"  NVL(SUM(  NVL(MENGE_ABLADEN_KO,0)),0) AS ABLADE_MENGE_IN_FUHRE_VK",
//				bibE2.cTO()+".JT_VPOS_TPA_FUHRE", 
//				"ID_VPOS_KON_VK IN ("+bibALL.Concatenate(vID_VPOS_KON,",","-1")+") AND   NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'", true));
//
//		this.putAll(new MyDataRecordHashMap("  NVL(SUM(  NVL(ANZAHL,0)),0) AS GESAMTMENGE_POSITION",
//				bibE2.cTO()+".JT_VPOS_KON", 
//				"   NVL(DELETED,'N')='N' AND ID_VPOS_KON IN ("+bibALL.Concatenate(vID_VPOS_KON,",","-1")+")", 
//				true));
//
//		this.putAll(new MyDataRecordHashMap(" COUNT(*) AS ANZAHL_OFFENE_KONTRAKT_POS",
//					bibE2.cTO()+".JT_VPOS_KON_TRAKT", 
//					"   NVL(DELETED,'N')='N' AND   NVL(ABGESCHLOSSEN,'N')='N' AND ID_VPOS_KON IN ("+bibALL.Concatenate(vID_VPOS_KON,",","-1")+")", 
//					true));
//
//		this.putAll(new MyDataRecordHashMap("  NVL(SUM(  NVL(LAGERMENGE,0)),0) AS GESAMTE_PLAN_LAGERMENGE",
//				bibE2.cTO()+".JT_VPOS_KON_LAGER", 
//				"ID_VPOS_KON IN ("+bibALL.Concatenate(vID_VPOS_KON,",","-1")+")", true));
//		
//		
//	}
//	

	

	public My_HM_KONTRAKT_POS_INFO(String cID_VPOS_KON)  throws myException
	{
		super();

		this.putAll(
				new MyDataRecordHashMap("COUNT(*) AS ANZAHL_FUHREN_EK,"+		
				"  NVL(SUM(  NVL(MENGE_VORGABE_KO,0)),0) AS VORGABE_MENGE_IN_FUHRE_EK, " +
				"  NVL(SUM(  NVL(MENGE_AUFLADEN_KO,0)),0) AS AUFLADE_MENGE_IN_FUHRE_EK, " +
				"  NVL(SUM(  NVL(MENGE_ABLADEN_KO,0)),0) AS ABLADE_MENGE_IN_FUHRE_EK",
				bibE2.cTO()+".JT_VPOS_TPA_FUHRE", 
				"ID_VPOS_KON_EK ="+cID_VPOS_KON+" AND   NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'", true));
		
		this.putAll(
				new MyDataRecordHashMap("COUNT(*) AS ANZAHL_FUHREN_VK,"+		
				"  NVL(SUM(  NVL(MENGE_VORGABE_KO,0)),0) AS VORGABE_MENGE_IN_FUHRE_VK, " +
				"  NVL(SUM(  NVL(MENGE_AUFLADEN_KO,0)),0) AS AUFLADE_MENGE_IN_FUHRE_VK, " +
				"  NVL(SUM(  NVL(MENGE_ABLADEN_KO,0)),0) AS ABLADE_MENGE_IN_FUHRE_VK",
				bibE2.cTO()+".JT_VPOS_TPA_FUHRE", 
				"ID_VPOS_KON_VK ="+cID_VPOS_KON+" AND   NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'", true));

		
		this.putAll(new MyDataRecordHashMap("  NVL(ANZAHL,0) AS GESAMTMENGE_POSITION",
				bibE2.cTO()+".JT_VPOS_KON", 
				"   NVL(DELETED,'N')='N' AND ID_VPOS_KON ="+cID_VPOS_KON, 
				true));

		this.putAll(new MyDataRecordHashMap(" COUNT(*) AS ANZAHL_OFFENE_KONTRAKT_POS",
				bibE2.cTO()+".JT_VPOS_KON_TRAKT", 
				"   NVL(DELETED,'N')='N' AND   NVL(ABGESCHLOSSEN,'N')='N' AND ID_VPOS_KON = "+cID_VPOS_KON, 
				true));
		
		this.putAll(new MyDataRecordHashMap("  NVL(SUM(  NVL(LAGERMENGE,0)),0) AS GESAMTE_PLAN_LAGERMENGE",
				bibE2.cTO()+".JT_VPOS_KON_LAGER", 
				"ID_VPOS_KON="+cID_VPOS_KON, true));

		
		
		this.putAll(new MyDataRecordHashMap("SELECT JT_VKOPF_KON.VORGANG_TYP FROM "+bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON "+
				" WHERE JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON AND JT_VPOS_KON.ID_VPOS_KON="+cID_VPOS_KON));

		
		if (this.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_EK_KONTRAKT) || this.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			if (this.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_EK_KONTRAKT))
			{
				this.putAll(new MyDataRecordHashMap("SELECT   NVL(SUM(  NVL(JT_EK_VK_BEZUG.ANZAHL,0)),0) AS ZUGEORDNETE_ANZAHL FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+cID_VPOS_KON));
			}
			else
			{
				this.putAll(new MyDataRecordHashMap("SELECT   NVL(SUM(  NVL(JT_EK_VK_BEZUG.ANZAHL,0)),0) AS ZUGEORDNETE_ANZAHL FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+cID_VPOS_KON));
			}
		}
		else
		{
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:Constructor: Not correct VORGANGS_ART");
		}
		
	}
	

	
	public String get_Unformated_ANZAHL_FUHREN_EK() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_FUHREN_EK");
	}
	public String get_Unformated_VORGABE_MENGE_IN_FUHRE_EK() throws myException
	{
		return this.get_UnFormatedValue("VORGABE_MENGE_IN_FUHRE_EK");
	}
	public String get_Unformated_AUFLADE_MENGE_IN_FUHRE_EK() throws myException
	{
		return this.get_UnFormatedValue("AUFLADE_MENGE_IN_FUHRE_EK");
	}
	public String get_Unformated_ABLADE_MENGE_IN_FUHRE_EK() throws myException
	{
		return this.get_UnFormatedValue("ABLADE_MENGE_IN_FUHRE_EK");
	}
	public String get_Unformated_ANZAHL_FUHREN_VK() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_FUHREN_VK");
	}
	public String get_Unformated_VORGABE_MENGE_IN_FUHRE_VK() throws myException
	{
		return this.get_UnFormatedValue("VORGABE_MENGE_IN_FUHRE_VK");
	}
	public String get_Unformated_AUFLADE_MENGE_IN_FUHRE_VK() throws myException
	{
		return this.get_UnFormatedValue("AUFLADE_MENGE_IN_FUHRE_VK");
	}
	public String get_Unformated_ABLADE_MENGE_IN_FUHRE_VK() throws myException
	{
		return this.get_UnFormatedValue("ABLADE_MENGE_IN_FUHRE_VK");
	}
	public String get_Unformated_GESAMTMENGE_POSITION() throws myException
	{
		return this.get_UnFormatedValue("GESAMTMENGE_POSITION");
	}
	public String get_Unformated_ANZAHL_OFFENE_KONTRAKT_POSITIONEN() throws myException
	{
		return this.get_UnFormatedValue("ANZAHL_OFFENE_KONTRAKT_POS");
	}
	public String get_Unformated_GESAMTE_PLAN_LAGERMENGE() throws myException
	{
		return this.get_UnFormatedValue("GESAMTE_PLAN_LAGERMENGE");
	}
	

	
	
	
	public String get_Formated_ANZAHL_FUHREN_EK() throws myException
	{
		return this.get_FormatedValue("ANZAHL_FUHREN_EK");
	}
	public String get_Formated_VORGABE_MENGE_IN_FUHRE_EK() throws myException
	{
		return this.get_FormatedValue("VORGABE_MENGE_IN_FUHRE_EK");
	}
	public String get_Formated_AUFLADE_MENGE_IN_FUHRE_EK() throws myException
	{
		return this.get_FormatedValue("AUFLADE_MENGE_IN_FUHRE_EK");
	}
	public String get_Formated_ABLADE_MENGE_IN_FUHRE_EK() throws myException
	{
		return this.get_FormatedValue("ABLADE_MENGE_IN_FUHRE_EK");
	}
	public String get_Formated_ANZAHL_FUHREN_VK() throws myException
	{
		return this.get_FormatedValue("ANZAHL_FUHREN_VK");
	}
	public String get_Formated_VORGABE_MENGE_IN_FUHRE_VK() throws myException
	{
		return this.get_FormatedValue("VORGABE_MENGE_IN_FUHRE_VK");
	}
	public String get_Formated_AUFLADE_MENGE_IN_FUHRE_VK() throws myException
	{
		return this.get_FormatedValue("AUFLADE_MENGE_IN_FUHRE_VK");
	}
	public String get_Formated_ABLADE_MENGE_IN_FUHRE_VK() throws myException
	{
		return this.get_FormatedValue("ABLADE_MENGE_IN_FUHRE_VK");
	}
	public String get_Formated_GESAMTMENGE_POSITION() throws myException
	{
		return this.get_FormatedValue("GESAMTMENGE_POSITION");
	}
	public String get_Formated_ANZAHL_OFFENE_KONTRAKT_POSITIONEN() throws myException
	{
		return this.get_FormatedValue("ANZAHL_OFFENE_KONTRAKT_POS");
	}
	public String get_Formated_GESAMTE_PLAN_LAGERMENGE() throws myException
	{
		return this.get_FormatedValue("GESAMTE_PLAN_LAGERMENGE");
	}

	public String get_cVORGANG_TYP() throws myException
	{
		return this.get_FormatedValue("VORGANG_TYP");
	}

	
	
	
	public Integer get_ANZAHL_FUHREN_EK() throws myException
	{
		try
		{
			return new Integer(this.get_UnFormatedValue("ANZAHL_FUHREN_EK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_ANZAHL_FUHREN_EK:"+ex.getLocalizedMessage());
		}
	}

	public Double get_VORGABE_MENGE_IN_FUHRE_EK() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("VORGABE_MENGE_IN_FUHRE_EK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_VORGABE_MENGE_IN_FUHRE_EK:"+ex.getLocalizedMessage());
		}
	}
	
	
	public Double get_AUFLADE_MENGE_IN_FUHRE_EK() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("AUFLADE_MENGE_IN_FUHRE_EK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_VORGABE_MENGE_IN_FUHRE_EK:"+ex.getLocalizedMessage());
		}
		
	}
	
	
	public Double get_ABLADE_MENGE_IN_FUHRE_EK() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("ABLADE_MENGE_IN_FUHRE_EK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_ABLADE_MENGE_IN_FUHRE_EK:"+ex.getLocalizedMessage());
		}
	}
	
	public Integer get_ANZAHL_FUHREN_VK() throws myException
	{
		try
		{
			return new Integer(this.get_UnFormatedValue("ANZAHL_FUHREN_VK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_ANZAHL_FUHREN_VK:"+ex.getLocalizedMessage());
		}
	}

	public Double get_VORGABE_MENGE_IN_FUHRE_VK() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("VORGABE_MENGE_IN_FUHRE_VK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_VORGABE_MENGE_IN_FUHRE_VK:"+ex.getLocalizedMessage());
		}
	}
	
	
	public Double get_AUFLADE_MENGE_IN_FUHRE_VK() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("AUFLADE_MENGE_IN_FUHRE_VK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_AUFLADE_MENGE_IN_FUHRE_VK:"+ex.getLocalizedMessage());
		}
	}
	
	
	public Double get_ABLADE_MENGE_IN_FUHRE_VK() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("ABLADE_MENGE_IN_FUHRE_VK"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_ABLADE_MENGE_IN_FUHRE_VK:"+ex.getLocalizedMessage());
		}

	}
	
	
	public Double get_GESAMTMENGE_POSITION() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("GESAMTMENGE_POSITION"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_GESAMTMENGE_POSITION:"+ex.getLocalizedMessage());
		}
	}

	public Double get_ANZAHL_OFFENE_KONTRAKT_POSITIONEN() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("ANZAHL_OFFENE_KONTRAKT_POS"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_ANZAHL_OFFENE_KONTRAKT_POS:"+ex.getLocalizedMessage());
		}
	}
	
	public Double get_GESAMTE_PLAN_LAGERMENGE() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("GESAMTE_PLAN_LAGERMENGE"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:get_GESAMTE_PLAN_LAGERMENGE:"+ex.getLocalizedMessage());
		}

	}

	
	
	public boolean get_bALLE_KONTRAKT_POS_ABGESCHLOSSEN() throws myException
	{
		if (this.get_ANZAHL_OFFENE_KONTRAKT_POSITIONEN().intValue()==0)
			return true;
		else
			return false;
			
	}
	
	
	
	public String get_Formated_ZUGEORDNETE_KONTRAKTMENGE() throws myException
	{
		return this.get_FormatedValue("ZUGEORDNETE_ANZAHL");
	}

	public String get_UnFormated_ZUGEORDNETE_KONTRAKTMENGE() throws myException
	{
		return this.get_UnFormatedValue("ZUGEORDNETE_ANZAHL");
	}
	public Double get_ZUGEORDNETE_KONTRAKTMENGE() throws myException
	{
		try
		{
			return new Double(this.get_UnFormatedValue("ZUGEORDNETE_ANZAHL"));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK___DRHM_KONTRAKT_POS_INFO:ZUGEORDNETE_ANZAHL:"+ex.getLocalizedMessage());
		}

	}
	
	public Double get_ZUGEORDNETE_KONTRAKTMENGE_0_WENN_LEER() 
	{
		try
		{
			return new Double(this.get_UnFormatedValue("ZUGEORDNETE_ANZAHL"));
		}
		catch (Exception ex)
		{
			return new Double(0);
		}

	}

	
	
	public static String HASHKEY_SUMME_FUHRENMENGEN_PLAN_ODER_LADEGEWICHT = "SUMME_FUHRENMENGEN_PLAN_ODER_LADEGEWICHT";
	public static String HASHKEY_MENGE_IN_LAGER = "MENGE_IN_LAGER";
	public static String HASHKEY_MENGE_IN_KONTRAKT_POSITION = "MENGE_IN_KONTRAKT_POSITION";
	public static String HASHKEY_STATUS_KONTRAKT_POS = "STATUS_KONTRAKT_POS";

	

	
	
	
	
}
