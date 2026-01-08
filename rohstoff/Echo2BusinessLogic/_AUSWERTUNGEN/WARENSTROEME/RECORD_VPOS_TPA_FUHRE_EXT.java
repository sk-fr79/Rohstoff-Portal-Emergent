package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;



import java.math.BigDecimal;
import java.util.Map.Entry;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_VPOS_TPA_FUHRE_EXT extends RECORD_VPOS_TPA_FUHRE {

	public static int STRECKE = 	0;
	public static int EX_LAGER = 	1;
	public static int IN_LAGER = 	2;
	public static int MIXED = 		3;
	public static int INTERN = 		4;
	
	public RECORD_VPOS_TPA_FUHRE_EXT() throws myException 
	{
		super();
	}

	public RECORD_VPOS_TPA_FUHRE_EXT(long lIDUnformated, MyConnection Conn) throws myException 
	{
		super(lIDUnformated, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_EXT(long lIDUnformated) throws myException 
	{
		super(lIDUnformated);
	}

	public RECORD_VPOS_TPA_FUHRE_EXT(MyConnection Conn) throws myException 
	{
		super(Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_EXT(RECORD_VPOS_TPA_FUHRE recordOrig) 
	{
		super(recordOrig);
	}

	public RECORD_VPOS_TPA_FUHRE_EXT(String cIDOrWHEREBLOCKORSQL,MyConnection Conn) throws myException 
	{
		super(cIDOrWHEREBLOCKORSQL, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_EXT(String cIDOrWHEREBLOCKORSQL) throws myException 
	{
		super(cIDOrWHEREBLOCKORSQL);
	}
	
	
	
	public int get_FuhrenTyp() throws myException
	{
		int iRueck = 0;
		
		String cAdressIdMandant = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("@");
		
		int iAnzahlEigeneLadeorte = 0;
		int iAnzahlFremdLadeorte = 0;
		int iAnzahlEigeneAbladeorte = 0;
		int iAnzahlFremdAbladeorte = 0;
		
		if (this.get_ID_ADRESSE_START_cUF_NN("").equals(cAdressIdMandant))
		{
			iAnzahlEigeneLadeorte++;
		}
		else
		{
			iAnzahlFremdLadeorte++;
		}

		if (this.get_ID_ADRESSE_ZIEL_cUF_NN("").equals(cAdressIdMandant))
		{
			iAnzahlEigeneAbladeorte++;
		}
		else
		{
			iAnzahlFremdAbladeorte++;
		}
		
		
		RECLIST_VPOS_TPA_FUHRE_ORT reclistFuhreOrt = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'","",true);
		
		for (Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> oEntry: reclistFuhreOrt.entrySet())
		{
			RECORD_VPOS_TPA_FUHRE_ORT recOrt=oEntry.getValue();
			
			if (recOrt.is_DELETED_NO())
			{
				if (recOrt.get_ID_ADRESSE_cUF_NN("-").equals(cAdressIdMandant))
				{
					if (recOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK"))
					{
						iAnzahlEigeneLadeorte++;
					}
					else
					{
						iAnzahlEigeneAbladeorte++;
					}
				}
				else
				{
					if (recOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK"))
					{
						iAnzahlFremdLadeorte++;
					}
					else
					{
						iAnzahlFremdAbladeorte++;
					}
				}
			}
		}
		

		if (iAnzahlFremdLadeorte==0 && iAnzahlFremdAbladeorte==0)
		{
			iRueck=RECORD_VPOS_TPA_FUHRE_EXT.INTERN;
		}
		else if (iAnzahlEigeneLadeorte==0 && iAnzahlEigeneAbladeorte==0)
		{
			iRueck=RECORD_VPOS_TPA_FUHRE_EXT.STRECKE;
		}
		else if (iAnzahlEigeneLadeorte==1 && iAnzahlEigeneAbladeorte==0 && iAnzahlFremdLadeorte==0 && iAnzahlFremdAbladeorte>0)
		{
			iRueck=RECORD_VPOS_TPA_FUHRE_EXT.EX_LAGER;
		}
		else if (iAnzahlEigeneLadeorte==0 && iAnzahlEigeneAbladeorte==1 && iAnzahlFremdLadeorte>0 && iAnzahlFremdAbladeorte==0)
		{
			iRueck=RECORD_VPOS_TPA_FUHRE_EXT.IN_LAGER;
		}
		else
		{
			iRueck=RECORD_VPOS_TPA_FUHRE_EXT.MIXED;
		}

		return iRueck;
	}
	
	
	/**
	 * @throws myException
	 */
	public void fill_PreisInfos(DatenTypUndMenge dtEK, DatenTypUndMenge  dtVK) throws myException
	{

		// Manfred: Selektion der Rechnungen ohne Sortierung -> Schneller
		RECLIST_VPOS_RG  recListVPOS_RG_Haupt = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
				"JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL " +
				" AND NVL(JT_VPOS_RG.DELETED,'N')='N' " +
				" AND JT_VPOS_RG.ID_VPOS_RG_STORNO_VORGAENGER IS NULL " +
				" AND JT_VPOS_RG.ID_VPOS_RG_STORNO_NACHFOLGER IS NULL ","JT_VPOS_RG.ID_VPOS_RG",true);


		
		
		RECORD_VPOS_RG   lastEK_POS_FUHRE = null;
		RECORD_VPOS_RG   lastVK_POS_FUHRE = null;
		
		//die letzte (damit gueltige) rg-position raussuchen
		for (int i=0;i<recListVPOS_RG_Haupt.get_vKeyValues().size();i++)
		{
		
			RECORD_VPOS_RG recRG = recListVPOS_RG_Haupt.get(i);

			if (recRG.get_LAGER_VORZEICHEN_lValue(new Long(0))==1)   //wareneingang
			{
				lastEK_POS_FUHRE = recRG;
			}
			else      //warenausgang
			{
				lastVK_POS_FUHRE = recRG;
			}
		}


		if (dtEK != null)
		{
			if (lastEK_POS_FUHRE!=null)   //wareneingang
			{
				//damit es der letzte geschriebene positionssatz zur fuhre ist, muss folgendes gelten:
				//Menge>0 und keines der Storno-Merkmale wurde geschrieben
				if (	lastEK_POS_FUHRE.get_ANZAHL_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0 && 
						S.isEmpty(lastEK_POS_FUHRE.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) &&
						S.isEmpty(lastEK_POS_FUHRE.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{

					//2011-07-13: erweiterung der werte
					dtEK.ADD("GESAMTPREIS_WE",					lastEK_POS_FUHRE.get_GESAMTPREIS_bdValue(BigDecimal.ZERO));
					
					dtEK.ADD("GPREIS_ABZ_MGE_WE",				lastEK_POS_FUHRE.get_GPREIS_ABZ_MGE_bdValue(BigDecimal.ZERO));
					dtEK.ADD("GPREIS_ABZ_AUF_NETTOMGE_WE",		lastEK_POS_FUHRE.get_GPREIS_ABZ_AUF_NETTOMGE_bdValue(BigDecimal.ZERO));
					
					dtEK.ADD("MENGE_RG_POS_ABZUG_WE",			lastEK_POS_FUHRE.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO));
					dtEK.ADD("MENGE_RG_POS_ABZUG_LAG_REL_WE",	lastEK_POS_FUHRE.get_ANZAHL_ABZUG_LAGER_bdValue(BigDecimal.ZERO));
					
					dtEK.ADD("MENGE_RG_POS_WE",					lastEK_POS_FUHRE.get_ANZAHL_bdValue(BigDecimal.ZERO));
				}
			}
		}
		
		if (dtVK != null)
		{
			if (lastVK_POS_FUHRE!=null)   //warenausgang
			{
				//damit es der letzte geschriebene positionssatz zur fuhre ist, muss folgendes gelten:
				//Menge>0 und keines der Storno-Merkmale wurde geschrieben
				if (	lastVK_POS_FUHRE.get_ANZAHL_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0 && 
						S.isEmpty(lastVK_POS_FUHRE.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) &&
						S.isEmpty(lastVK_POS_FUHRE.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{

					//2011-07-13: erweiterung der werte
					dtVK.ADD("GESAMTPREIS_WA",					lastVK_POS_FUHRE.get_GESAMTPREIS_bdValue(BigDecimal.ZERO));
					
					dtVK.ADD("GPREIS_ABZ_MGE_WA",				lastVK_POS_FUHRE.get_GPREIS_ABZ_MGE_bdValue(BigDecimal.ZERO));
					dtVK.ADD("GPREIS_ABZ_AUF_NETTOMGE_WA",		lastVK_POS_FUHRE.get_GPREIS_ABZ_AUF_NETTOMGE_bdValue(BigDecimal.ZERO));

					dtVK.ADD("MENGE_RG_POS_ABZUG_WA",			lastVK_POS_FUHRE.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO));
					dtVK.ADD("MENGE_RG_POS_ABZUG_LAG_REL_WA",	lastVK_POS_FUHRE.get_ANZAHL_ABZUG_LAGER_bdValue(BigDecimal.ZERO));

					dtVK.ADD("MENGE_RG_POS_WA",					lastVK_POS_FUHRE.get_ANZAHL_bdValue(BigDecimal.ZERO));
					
				}
			}
		}
	}

}
