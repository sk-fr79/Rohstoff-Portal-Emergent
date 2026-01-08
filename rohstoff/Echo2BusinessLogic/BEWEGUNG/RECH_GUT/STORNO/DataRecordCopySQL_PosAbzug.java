package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG_ABZUG;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_CONST_ABZUG;

public class DataRecordCopySQL_PosAbzug extends myDataRecordCopySQLString
{
	/**
	 * 
	 * @param recVPosRG_Abzug
	 * @param cID_VPOS_RG_neu
	 * @param bStornoSatz
	 * @throws myException
	 */
	public DataRecordCopySQL_PosAbzug(RECORD_VPOS_RG_ABZUG  recVPosRG_Abzug, String cID_VPOS_RG_neu,  boolean bStornoSatz) throws myException
	{
		super("JT_VPOS_RG_ABZUG", "ID_VPOS_RG_ABZUG", recVPosRG_Abzug.get_ID_VPOS_RG_ABZUG_VALUE_FOR_SQLSTATEMENT(), null,null, null,null, true);
		
		HashMap<String,String> hmZusatzErsatz = new HashMap<String, String>();

		//nur die absolute Abzugsmenge wird negiert, alles andere ist prozentual oder auf den preis definiert
		if (bStornoSatz)
		{
			if (recVPosRG_Abzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT))
			{
				hmZusatzErsatz.put("ABZUG","-1*ABZUG");	
			}
			if (recVPosRG_Abzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT))
			{
				hmZusatzErsatz.put("ABZUG","-1*ABZUG");	
			}
			
			//2011-12-02: neuer absolut-abzug in basiswaehrung muss auch negiert werden beim storno 
			if (recVPosRG_Abzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT))
			{
				hmZusatzErsatz.put("ABZUG","-1*ABZUG");	
			}
			
			//2016-03-04: neuer absolut-abzug in basiswaehrung muss auch negiert werden beim storno 
			if (recVPosRG_Abzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND))
			{
				hmZusatzErsatz.put("ABZUG","-1*ABZUG");	
			}
		
			
		}
		hmZusatzErsatz.put("ID_VPOS_RG",cID_VPOS_RG_neu);
		this.add_ErsatzFields(hmZusatzErsatz);
	}

}
