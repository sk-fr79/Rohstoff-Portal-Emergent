package panter.gmbh.basics4project.specialViews.Views_4_tempTables;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_ListDefined_With_Globale_TempTables extends XXX_ViewBuilder
{
 
	/**
	 * hashmap enthaelt: 	key=	name der Datei
	 * 						value=	name_des_view (mit #MANDANT# als platzhalter
	 */
	private HashMap<String, String>  hmNAMES = null;
	private Vector<String>           vFileNamesReihenfolge = null;
	
	
	/**
	 * 
	 * @param names enthaelt: 	key=	name der Datei
	 * 							value=	name_des_view (mit #MANDANT# als platzhalter
	 * @param namesReihenfolge: die hashkeys in der noetigen reihenfolge
	 */
	public build_view_ListDefined_With_Globale_TempTables(HashMap<String, String> names, Vector<String> namesReihenfolge) {
		super();
		this.hmNAMES = names;
		this.vFileNamesReihenfolge = namesReihenfolge;
	}






	@Override
	public boolean build_View_forAll_Mandants() throws myException 
	{
		
		 
		
		for (String cFileName: this.vFileNamesReihenfolge) {
			
			String cViewNameFirstMANDANT = null;
			
			//name des temptables ist bei S#MANDANT#_TEST_VIEW: TT_TEST_VIEW 
			String cNameOfTempTable = "";
			
			DEBUG.System_println("Table: "+cFileName,null);
			
			
			int iPosPlaceholder = this.hmNAMES.get(cFileName).indexOf("#MANDANT#");
			if (iPosPlaceholder<=0) {
				throw new myException("build_view_ListDefined:build_View_forAll_Mandants: viewname without #MANDANT# is no allowed !!!");
			}
			cNameOfTempTable = "ST"+this.hmNAMES.get(cFileName).substring(iPosPlaceholder+("#MANDANT#").length());
			
			
			String 			cSQL_ViewDefinitionAusDatei = 		new VIEW_ResourceStringLoader_4_tempTables(cFileName).get_cText();
			RECLIST_MANDANT reclistMandanten = 	new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT ORDER BY ID_MANDANT");
			
			
//			for (RECORD_MANDANT recMandant : reclistMandanten.values())
			for (int m=0;m<reclistMandanten.get_vKeyValues().size();m++)
			{
				RECORD_MANDANT recMandant = reclistMandanten.get(m);
				
				String cID_Mandant = 			recMandant.get_ID_MANDANT_cUF();
				
				String cID_ADRESSE_MANDANT = 	recMandant.get_EIGENE_ADRESS_ID_cUF_NN("");
				
				String cViewName = bibALL.ReplaceTeilString(this.hmNAMES.get(cFileName),"#MANDANT#",cID_Mandant);
				
				if (S.isEmpty(cViewNameFirstMANDANT)) {
					cViewNameFirstMANDANT = cViewName;   //wird gebraucht, um die evtl. gewuenschte temp-Table zu erzeugen
				}
				
				if (S.isFull(cID_ADRESSE_MANDANT)) { 
					String cSQL_View1 = 	bibALL.ReplaceTeilString(cSQL_ViewDefinitionAusDatei,"#MANDANT#",cID_Mandant);
					cSQL_View1 = 			bibALL.ReplaceTeilString(cSQL_View1,"#ID_ADRESSE_MANDANT#",cID_ADRESSE_MANDANT);
					
					DEBUG.System_print(cSQL_View1, null);
					
					if (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL_View1, true))
					{
						MyE2_String cInfo = new MyE2_String("Der View ",true,cViewName,false, " wurde erfolgreich erstellt.",true);
						bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
						
					}
					else
					{
						MyE2_String cInfo = new MyE2_String("Der View ",true,cViewName,false, " wurde NICHT erstellt: FEHLER !",true);
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
					}
					
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der View ",true,cViewName,false," wurde nicht erstellt! Der Mandant hat keine Angabe über seine eigene Adress-ID !",true)));
				}
			}
			
			
			/*
			 * temporaere tabellen aufbauen 
			 */
			bibMSG.add_MESSAGE(this.create_TempTable(cViewNameFirstMANDANT, cNameOfTempTable));
			
			
		}
		
		return true;
	}

}
