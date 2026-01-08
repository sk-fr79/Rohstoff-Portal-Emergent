package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_VPOS_TPA_FUHRE_ORT_EXT extends RECORD_VPOS_TPA_FUHRE_ORT 
{

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT() throws myException 
	{
		super();
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT(long lIDUnformated, MyConnection Conn)	throws myException 
	{
		super(lIDUnformated, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT(long lIDUnformated) throws myException 
	{
		super(lIDUnformated);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT(MyConnection Conn) throws myException 
	{
		super(Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT(RECORD_VPOS_TPA_FUHRE_ORT recordOrig) 
	{
		super(recordOrig);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT(String cIDOrWHEREBLOCKORSQL,MyConnection Conn) throws myException 
	{
		super(cIDOrWHEREBLOCKORSQL, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_EXT(String cIDOrWHEREBLOCKORSQL) throws myException 
	{
		super(cIDOrWHEREBLOCKORSQL);
	}

	
	
	
	/**
	 * @throws myException
	 */
	public void fill_PreisInfo(DatenTypUndMenge dtEK_VK) throws myException
	{

		RECLIST_VPOS_RG  recListVPOS_RG_Haupt = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord(
								" NVL(JT_VPOS_RG.DELETED,'N')='N'" +
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


		//fuellt in der datenstruktur automatisch die richtige seite
		if (this.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
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
					dtEK_VK.ADD("GESAMTPREIS_WE",				lastEK_POS_FUHRE.get_GESAMTPREIS_bdValue(BigDecimal.ZERO));
					
					dtEK_VK.ADD("GPREIS_ABZ_MGE_WE",			lastEK_POS_FUHRE.get_GPREIS_ABZ_MGE_bdValue(BigDecimal.ZERO));
					dtEK_VK.ADD("GPREIS_ABZ_AUF_NETTOMGE_WE",	lastEK_POS_FUHRE.get_GPREIS_ABZ_AUF_NETTOMGE_bdValue(BigDecimal.ZERO));
					
					dtEK_VK.ADD("MENGE_RG_POS_ABZUG_WE",		lastEK_POS_FUHRE.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO));
					dtEK_VK.ADD("MENGE_RG_POS_ABZUG_LAG_REL_WE",lastEK_POS_FUHRE.get_ANZAHL_ABZUG_LAGER_bdValue(BigDecimal.ZERO));
					
					dtEK_VK.ADD("MENGE_RG_POS_WE",				lastEK_POS_FUHRE.get_ANZAHL_bdValue(BigDecimal.ZERO));
				}
			}
		}
		else
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
					dtEK_VK.ADD("GESAMTPREIS_WA",				lastVK_POS_FUHRE.get_GESAMTPREIS_bdValue(BigDecimal.ZERO));
					
					dtEK_VK.ADD("GPREIS_ABZ_MGE_WA",			lastVK_POS_FUHRE.get_GPREIS_ABZ_MGE_bdValue(BigDecimal.ZERO));
					dtEK_VK.ADD("GPREIS_ABZ_AUF_NETTOMGE_WA",	lastVK_POS_FUHRE.get_GPREIS_ABZ_AUF_NETTOMGE_bdValue(BigDecimal.ZERO));

					dtEK_VK.ADD("MENGE_RG_POS_ABZUG_WA",		lastVK_POS_FUHRE.get_ANZAHL_ABZUG_bdValue(BigDecimal.ZERO));
					dtEK_VK.ADD("MENGE_RG_POS_ABZUG_LAG_REL_WA",lastVK_POS_FUHRE.get_ANZAHL_ABZUG_LAGER_bdValue(BigDecimal.ZERO));

					dtEK_VK.ADD("MENGE_RG_POS_WA",				lastVK_POS_FUHRE.get_ANZAHL_bdValue(BigDecimal.ZERO));
				}
			}
		}
	}

	
	
	
	
	
	
}
