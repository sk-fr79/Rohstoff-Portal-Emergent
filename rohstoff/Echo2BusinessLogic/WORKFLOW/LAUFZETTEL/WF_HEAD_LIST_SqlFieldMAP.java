package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3856337216531984004L;

	public WF_HEAD_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_LAUFZETTEL", "", false);
		
		this.add_SQLField(new SQLField("LETZTE_AENDERUNG",WF_HEAD_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG,new MyE2_String("Letzte Änderung"),bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("GEAENDERT_VON",WF_HEAD_CONST.HASH_SONDERFELD_GEANDERT_VON,new MyE2_String("Geändert von"),bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("NVL(PRIVAT,'N')", WF_HEAD_CONST.HASH_SONDERFELD_WORKFLOW_PRIVAT_NN, new MyE2_String("Privat"), bibE2.get_CurrSession()), true);
		
		// wenn der Benutzer nicht Supervisor ist, müssen alle privaten einträge aller anderen ausgefiltert werden:
		String cID_USER = bibALL.get_ID_USER();

//		String sSQLPrivat = bibE2.cTO()+".JT_LAUFZETTEL.ID_LAUFZETTEL not in (select ID_LAUFZETTEL from " + bibE2.cTO() + ".JT_LAUFZETTEL_EINTRAG " + 
//							" WHERE JT_LAUFZETTEL_EINTRAG.PRIVAT = 'Y' AND JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER != " + cID_USER +  
//							" AND (JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER != " + cID_USER + " or JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER is null) )";
//
//		String sSQLPrivat2 = " ((jt_laufzettel.PRIVAT is null or jt_laufzettel.PRIVAT ='N') "+
//							 " OR (jt_laufzettel.PRIVAT = 'Y' AND ( jt_laufzettel.ID_USER_BESITZER = " + cID_USER + 
//							 " OR jt_laufzettel.ID_USER_SUPERVISOR = " + cID_USER + ")))";

		
		// Ausblenden aller anderen Private Einträge, nur berücksichtigen der offenen und der eigenen Einträge
		String sSQLPrivat = "(" + bibE2.cTO()+".JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
										" SELECT DISTINCT e.id_laufzettel " +
										" FROM jt_laufzettel_eintrag e" +
										" WHERE  e.ID_USER_BESITZER = " + cID_USER +  
										" OR e.ID_USER_BEARBEITER = " + cID_USER +
										" UNION" +
										" SELECT distinct l.id_laufzettel" +
										" FROM " + bibE2.cTO()+".jt_laufzettel l" +
										" WHERE l.ID_USER_BESITZER =  " + cID_USER +  
										" OR l.ID_USER_SUPERVISOR =  " + cID_USER +  
										" ) OR " +
										"   NVL(JT_LAUFZETTEL.PRIVAT,'N')='N' "
										+ " )"
										;

				
	
		
//		if (!bibALL.get_bIST_SUPERVISOR())
//		{
//			this.add_BEDINGUNG_STATIC(sSQLPrivat );
////			this.add_BEDINGUNG_STATIC(sSQLPrivat2);
//		}
		if (new RECORD_USER(bibALL.get_RECORD_USER()).is_DEVELOPER_YES() || new RECORD_USER(bibALL.get_RECORD_USER()).is_GESCHAEFTSFUEHRER_YES()) {
			this.add_BEDINGUNG_STATIC(sSQLPrivat );
	    }
		
		/*
		 * beispiel fuer die definition von standard-werten
	
		this.get_("ABGESCHLOSSEN_AM").set_cDefaultValueFormated("");
		this.get_("ID_USER_ABGESCHLOSSEN_VON").set_cDefaultValueFormated("");
		this.get_("ANGELEGT_AM").set_cDefaultValueFormated("");
		this.get_("GEAENDERT_VON").set_cDefaultValueFormated("");
		this.get_("ID_LAUFZETTEL").set_cDefaultValueFormated("");
		this.get_("ID_LAUFZETTEL_STATUS").set_cDefaultValueFormated("");
		this.get_("ID_MANDANT").set_cDefaultValueFormated("");
		this.get_("ID_USER_BESITZER").set_cDefaultValueFormated("");
		this.get_("ID_USER_SUPERVISOR").set_cDefaultValueFormated("");
		this.get_("LETZTE_AENDERUNG").set_cDefaultValueFormated("");
		this.get_("TEXT").set_cDefaultValueFormated("");
			 */
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_LAUFZETTEL_WICHTIGKEIT","BESCHREIBUNG","ID_WF_HEAD_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_WF_HEAD_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_LAUFZETTEL.ID_USER="+cID_USER+" OR JT_LAUFZETTEL.ID_WF_HEAD IN (SELECT ID_WF_HEAD FROM "+bibE2.cTO()+".JT_LAUFZETTEL_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(0);
		*/
		this.initFields();
	}
	
}
