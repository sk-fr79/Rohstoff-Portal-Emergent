package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;

public class DataRecordCopySQL_Pos extends myDataRecordCopySQLString
{

	/**
	 * 
	 * @param recVPosRG
	 * @param cID_VKOPF_RG_neu
	 * @param cID_VPOS_RG_neu
	 * @param cID_VPOS_RG_Vorgaenger  (wenn null, dann ist es der neue Storno-Datensatz )
	 * @throws myException
	 */
	public DataRecordCopySQL_Pos(RECORD_VPOS_RG   recVPosRG, String cID_VKOPF_RG_neu, String cID_VPOS_RG_neu,  String cID_VPOS_RG_Vorgaenger, int iPositionsnummer) throws myException
	{
		super("JT_VPOS_RG", "ID_VPOS_RG", recVPosRG.get_ID_VPOS_RG_VALUE_FOR_SQLSTATEMENT(), null,null, null,null, false);
		
		
		HashMap<String,String> hmZusatzErsatz = new HashMap<String, String>();
		if (S.isFull(cID_VPOS_RG_Vorgaenger))            // das ist der storno-beleg, der 2. ist eine dublette der ersten, nicht abgeschlossen
		{
			hmZusatzErsatz.put("ANZAHL","-1*ANZAHL");
			hmZusatzErsatz.put("ID_VPOS_RG_STORNO_VORGAENGER", cID_VPOS_RG_Vorgaenger);
			hmZusatzErsatz.put("ID_VPOS_RG_STORNO_NACHFOLGER", "NULL");
			hmZusatzErsatz.put("POSITIONSNUMMER", ""+iPositionsnummer);
		}
		else
		{
			hmZusatzErsatz.put("ID_VPOS_RG_STORNO_VORGAENGER", "NULL");
			hmZusatzErsatz.put("ID_VPOS_RG_STORNO_NACHFOLGER", "NULL");
			hmZusatzErsatz.put("POSITIONSNUMMER", ""+iPositionsnummer);
		}
		hmZusatzErsatz.put("ID_VKOPF_RG",S.isFull(cID_VKOPF_RG_neu)?cID_VKOPF_RG_neu:"NULL");
		hmZusatzErsatz.put("ID_VPOS_RG",cID_VPOS_RG_neu);
		hmZusatzErsatz.putAll(DB_STATICS.get_hmZusatzFelder_STD());

		this.add_ErsatzFields(hmZusatzErsatz);
	}
	 // @param cID_VPOS_RG_Vorgaenger  

}
