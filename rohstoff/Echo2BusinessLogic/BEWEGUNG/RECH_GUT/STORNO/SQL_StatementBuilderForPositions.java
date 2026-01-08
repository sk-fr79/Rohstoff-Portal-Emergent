package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG_ABZUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class SQL_StatementBuilderForPositions
{
	private RECLIST_VPOS_RG  	recListToStorno = null;
	private Vector<String>		vSQL_Statements = new Vector<String>();

	/**
	 * 
	 * @param recListToStorno
	 * @param cID_VKOPF_STORNO_BELEG
	 * @param cID_VKOPF_NEU_BELEG
	 * @throws myException
	 */
	public SQL_StatementBuilderForPositions(RECLIST_VPOS_RG recListToStorno, String cID_VKOPF_STORNO_BELEG, String cID_VKOPF_NEU_BELEG, boolean bErzeugeAuchNeuePosition) throws myException
	{
		super();
		this.recListToStorno = recListToStorno;
		
		int iAnzahlPos = this.recListToStorno.get_vKeyValues().size();
		
		// hier werden zwei aufeinanderfolgende stapel von positionsids erzeugt, die jeweils einen aufeinanderfolgenden block bilden 
		Vector<String> vNewIDs_BelegPos_STORNO = 	bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VPOS_RG", iAnzahlPos);
		
		int iPosNummer = 0;
		
		//erster stapel abarbeiten (stornierte)
		for (int k=0;k<iAnzahlPos;k++)
		{
			String 			cID_POS_NEU_STORNO = 	vNewIDs_BelegPos_STORNO.get(k);   // eine id holen
			RECORD_VPOS_RG  recVPOS = 		this.recListToStorno.get(k);
			vSQL_Statements.add(new DataRecordCopySQL_Pos(recVPOS, cID_VKOPF_STORNO_BELEG, cID_POS_NEU_STORNO,recVPOS.get_ID_VPOS_RG_cUF(),iPosNummer++).get_cINSERT_String());
			
			//der nachfolger wird in den ursprungssatz geschrieben
			recVPOS.set_NEW_VALUE_ID_VPOS_RG_STORNO_NACHFOLGER(cID_POS_NEU_STORNO);
			recVPOS.set_NEW_VALUE_ID_VPOS_RG_STORNO_VORGAENGER(null);
			vSQL_Statements.add(recVPOS.get_SQL_UPDATE_STATEMENT(null, true));
			
			for (int l=0;l<recVPOS.get_DOWN_RECORD_LIST_VPOS_RG_ABZUG_id_vpos_rg().get_vKeyValues().size();l++)
			{
				RECORD_VPOS_RG_ABZUG recAbzug = recVPOS.get_DOWN_RECORD_LIST_VPOS_RG_ABZUG_id_vpos_rg().get(l);
				vSQL_Statements.add(new DataRecordCopySQL_PosAbzug(recAbzug,cID_POS_NEU_STORNO,true).get_cINSERT_String());
			}
		}
		
		
		if (bErzeugeAuchNeuePosition)
		{
			Vector<String> vNewIDs_BelegPos_NEU = 		bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VPOS_RG", iAnzahlPos);

			//zweiter stapel abarbeiten (neue datensaetze)
			for (int k=0;k<this.recListToStorno.get_vKeyValues().size();k++)
			{
				String 			cID_POS_Help = 	vNewIDs_BelegPos_NEU.get(k);   // eine id holen
	
				RECORD_VPOS_RG   recVPOS = this.recListToStorno.get(k);
				vSQL_Statements.add(new DataRecordCopySQL_Pos(recVPOS, cID_VKOPF_NEU_BELEG, cID_POS_Help, null,iPosNummer++).get_cINSERT_String());
				
				for (int l=0;l<recVPOS.get_DOWN_RECORD_LIST_VPOS_RG_ABZUG_id_vpos_rg().get_vKeyValues().size();l++)
				{
					RECORD_VPOS_RG_ABZUG recAbzug = recVPOS.get_DOWN_RECORD_LIST_VPOS_RG_ABZUG_id_vpos_rg().get(l);
					vSQL_Statements.add(new DataRecordCopySQL_PosAbzug(recAbzug,cID_POS_Help,false).get_cINSERT_String());
				}
			}
		}

	}

	public Vector<String> get_vSQL_Statements()
	{
		return vSQL_Statements;
	}
	
	
}
