package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


//eine klasse, die eine beliebige Rechung/Gutschrift uebernimmt und deren positionen sortiert nach Leistungsdatum/Sortenbez
public class __SORTER_POSITIONEN_DATUM_SORTE
{
	private RECORD_VKOPF_RG  recRG = null;

	public __SORTER_POSITIONEN_DATUM_SORTE(RECORD_VKOPF_RG rec_RG, String SortString,String cInfoString) throws myException
	{
		super();
		this.recRG = rec_RG;
		
//		RECLIST_VPOS_RG  recListVpos = recRG.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg(null,"AUSFUEHRUNGSDATUM,ARTBEZ1",true);
		RECLIST_VPOS_RG  recListVpos = recRG.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg(null,SortString,true);
		
		Vector<String> vSQL = new Vector<String>();

		int iSort = 1;
		for (int i=0;i<recListVpos.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_RG recPos = recListVpos.get(i);
			recPos.set_NEW_VALUE_POSITIONSNUMMER(""+(iSort++));
			vSQL.add(recPos.get_SQL_UPDATE_STATEMENT(null, true));
		}

		bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
		
		if (bibMSG.get_bIsOK())
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Positionen wurden nach den Feldern: ["+cInfoString+"] neu sortiert !!"));
		}
		
	}
	
	
	
}
