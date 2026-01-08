package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.Locale;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;


/*
 * positionen-validator, der dafuer sorgt, dass 
 * in einem abnahmeangebot dienstleistungen nur mit einer negativen
 * menge eingetragen werden koennen, dagegen bei einem Verkaufsangebot
 * dienstleistungen nur positive mengen haben koennen
 */
public class BSA_P_MASK_MapValidator_Dienstleitung_positiv_negativ extends XX_MAP_ValidBeforeSAVE
{
	private BS__SETTING oSetting = null;
	

	public BSA_P_MASK_MapValidator_Dienstleitung_positiv_negativ(BS__SETTING setting)
	{
		super();
		oSetting = setting;
	}


	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		

		// menge beschaffen
		String cMenge = bibALL.null2leer((String) oInputMap.get("ANZAHL"));
		if (cMenge.equals(""))
			return oMV;				// alles ok
		
		
		// id_artikelbez beschaffen
		String cID_ArtikelBez = bibALL.null2leer((String) oInputMap.get("ID_ARTIKEL_BEZ"));
		if (cID_ArtikelBez.equals(""))
			return oMV;				// alles ok oder wird woanders gefangen
		
		cID_ArtikelBez = bibALL.ReplaceTeilString(cID_ArtikelBez, ".", "");
		
		try
		{
			RECORD_ARTIKEL_BEZ  recARTBEZ = new RECORD_ARTIKEL_BEZ(cID_ArtikelBez);
			
			if (recARTBEZ.get_UP_RECORD_ARTIKEL_id_artikel().is_DIENSTLEISTUNG_NO())
				return oMV;
		} 
		catch (myException e)
		{
			e.printStackTrace();
			oMV.add_MESSAGE(e.get_ErrorMessage());
			return oMV;
		}
		
		
		DotFormatter oDF = new DotFormatter(cMenge,0,Locale.GERMAN,true,3);
		
		if (oDF.doFormat())
		{
			double dMenge = oDF.getDoubleValue();
			
			if (this.oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_ABNAHMEANGEBOT))
			{
				if (dMenge>0)
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Dienstleistungsartikel dürfen nur NEGATIVE Mengen in Abnahmeangeboten haben !!"), false);
			}
			else
			{
				if (dMenge<0)
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Dienstleistungsartikel dürfen nur POSITIVE Mengen in Verkaufsangeboten haben !!"), false);
			}
		}		
		

		return oMV;
	}

	
	
}
