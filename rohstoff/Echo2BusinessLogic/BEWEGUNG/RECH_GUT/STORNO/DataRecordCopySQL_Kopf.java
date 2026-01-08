package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class DataRecordCopySQL_Kopf extends myDataRecordCopySQLString
{
	/**
	 * 
	 * @param recVKopfRG_original
	 * @param cID_VKOPF_STORNO
	 * @param cID_VKOPF_NEU
	 * @param bStornoSatz
	 * @throws myException
	 */
	public DataRecordCopySQL_Kopf(RECORD_VKOPF_RG   recVKopfRG_original, String cID_VKOPF_NEU, boolean bStornoSatz) throws myException
	{
		super("JT_VKOPF_RG", "ID_VKOPF_RG", recVKopfRG_original.get_ID_VKOPF_RG_VALUE_FOR_SQLSTATEMENT(), null,null, null,null, false);
		
		
		BS__SETTING oSetting = new BS__SETTING(recVKopfRG_original.get_VORGANG_TYP_cUF());
		String cBuchungsvorsatz = oSetting.get_cBuchungsNrVorsatz();
		if (S.isFull(cBuchungsvorsatz))
		{
			cBuchungsvorsatz = bibALL.MakeSql(cBuchungsvorsatz);
		}
		else
		{
			cBuchungsvorsatz="''";   //aenderung 2010-12-20: buchungsnummer leer ist erlaubt
		}

		
		
		HashMap<String,String> hmZusatzErsatz = new HashMap<String, String>();
		
		//druckzaehler des neuen satzes immer 0
		hmZusatzErsatz.put("DRUCKZAEHLER","0");
		
		if (bStornoSatz) 
		{
			hmZusatzErsatz.put("BUCHUNGSNUMMER",cBuchungsvorsatz+"||TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+recVKopfRG_original.get_VORGANG_TYP_cUF()+".NEXTVAL)");
			hmZusatzErsatz.put("ID_VKOPF_RG_STORNO_VORGAENGER",recVKopfRG_original.get_ID_VKOPF_RG_VALUE_FOR_SQLSTATEMENT());
			hmZusatzErsatz.put("ID_VKOPF_RG_STORNO_NACHFOLGER","NULL");
			hmZusatzErsatz.put("ABGESCHLOSSEN","'Y'");
		}
		else            //der 2. wieder offene satz
		{
			hmZusatzErsatz.put("BUCHUNGSNUMMER","NULL");
			hmZusatzErsatz.put("ID_VKOPF_RG_STORNO_VORGAENGER","NULL");
			hmZusatzErsatz.put("ID_VKOPF_RG_STORNO_NACHFOLGER","NULL");
			hmZusatzErsatz.put("ABGESCHLOSSEN","'N'");
		}
		hmZusatzErsatz.put("ID_VKOPF_RG",cID_VKOPF_NEU);
		hmZusatzErsatz.putAll(DB_STATICS.get_hmZusatzFelder_STD());

		this.add_ErsatzFields(hmZusatzErsatz);
	}

}
