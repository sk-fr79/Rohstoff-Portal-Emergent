package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class FP__UMBUCHER
{

	/**
	 * 
	 * @param reclistUmzubuchendeFuhren
	 * @param cNewDate
	 * @param cID_LKW_NEU
	 * @throws myException
	 */
	public FP__UMBUCHER(RECLIST_VPOS_TPA_FUHRE reclistUmzubuchendeFuhren, String cNewDate,String cID_LKW_NEU) throws myException
	{
		super();

		TestingDate oTest = new TestingDate(cNewDate);
		if (!oTest.testing())
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Falsches Umbuchungs-Zieldatum eingegeben !!"), false);
			return;
		}

		String cFahrerNEU = null;
		String cID_ANHAENGER_NEU = null;

		String cID_LKW_Help = new MyLong(cID_LKW_NEU).get_cUF_LongString();
		
		// nachschauen, ob es bereits fahrten in dem neuen Fahrplan gibt, und wenn ja, welcher fahrer / anhaenger dort hinterlegt ist
		// damit diese dann angeglichen werden koennen
		RECLIST_VPOS_TPA_FUHRE  reclistNeuerFahrplan = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_MASCHINEN_LKW_FP="+cID_LKW_NEU+
																					" AND TO_CHAR(DAT_FAHRPLAN_FP,'yyyy-MM-dd')="+oTest.get_ISO_DateFormat(true));
		
		if (reclistNeuerFahrplan.get_vKeyValues().size()>0)
		{
			for (String cID_VPOS_TPA_FUHRE:reclistNeuerFahrplan.get_vKeyValues())
			{
				if (reclistNeuerFahrplan.get(cID_VPOS_TPA_FUHRE).is_DELETED_NO() && reclistNeuerFahrplan.get(cID_VPOS_TPA_FUHRE).is_IST_STORNIERT_NO())
				{
					//fahrer und anhaenger vom ersten moeglichen nehmen
					cFahrerNEU = reclistNeuerFahrplan.get(cID_VPOS_TPA_FUHRE).get_FAHRER_FP_cUF();
					cID_ANHAENGER_NEU = reclistNeuerFahrplan.get(cID_VPOS_TPA_FUHRE).get_ID_MASCHINEN_ANH_FP_cUF();
					break;
				}
			}
		}

		
		if (bibMSG.get_bIsOK())
		{
			String cDateFormated = oTest.get_FormatedDateString("dd.mm.yyyy");
			
			Vector<String> vSQL = new Vector<String>();
			
			for (int i=0;i<reclistUmzubuchendeFuhren.get_vKeyValues().size();i++)
			{
				reclistUmzubuchendeFuhren.get(i).set_NEW_VALUE_DAT_FAHRPLAN_FP(cDateFormated);
				reclistUmzubuchendeFuhren.get(i).set_NEW_VALUE_ID_MASCHINEN_LKW_FP(cID_LKW_NEU);
				reclistUmzubuchendeFuhren.get(i).set_NEW_VALUE_TRANSPORTKENNZEICHEN(new RECORD_MASCHINEN(cID_LKW_Help).get_KFZKENNZEICHEN_cUF_NN(""));
				
				if (S.isFull(cFahrerNEU))	{reclistUmzubuchendeFuhren.get(i).set_NEW_VALUE_FAHRER_FP(cFahrerNEU);	}
				if (S.isFull(cID_ANHAENGER_NEU))	
				{
					reclistUmzubuchendeFuhren.get(i).set_NEW_VALUE_ID_MASCHINEN_ANH_FP(cID_ANHAENGER_NEU);
					reclistUmzubuchendeFuhren.get(i).set_NEW_VALUE_ANHAENGERKENNZEICHEN(new RECORD_MASCHINEN(cID_ANHAENGER_NEU).get_KFZKENNZEICHEN_cUF_NN(""));
				}
				
				

				vSQL.add(reclistUmzubuchendeFuhren.get(i).get_SQL_UPDATE_STATEMENT(null, true));
			}
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl umgebuchte Fahrten: "+vSQL.size()));
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Umbuchen !!!"));
			}
		}
	}

}
