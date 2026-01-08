
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class WF_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -303550560928851151L;

	public WF_LIST_SqlFieldMAP(String ID_LAUFZETTEL, String ID_USER_BEARBEITER) throws myException 
	{
		super("JT_LAUFZETTEL_EINTRAG", "", false);
		
		this.add_SQLField(new SQLField("ID_LAUFZETTEL_EINTRAG","ID_LAUFZETTEL_EINTRAG_B",new MyE2_String("2.ID"),bibE2.get_CurrSession()), true);
		

		this.add_SQLField(new SQLField("LETZTE_AENDERUNG",WF_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG,new MyE2_String("Letzte Änderung"),bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("GEAENDERT_VON",WF_CONST.HASH_SONDERFELD_GEANDERT_VON,new MyE2_String("Geändert von"),bibE2.get_CurrSession()), true);


		
		/*
		 * beispiel fuer die definition von standard-werten
		   
		   --- nicht nötig !
		   
		   
		this.get_("ABGESCHLOSSEN_AM").set_cDefaultValueFormated("");
		this.get_("ID_USER_ABGESCHLOSSEN_VON").set_cDefaultValueFormated("");
		this.get_("ANGELEGT_AM").set_cDefaultValueFormated("");
		
		this.get_("AUFGABE").set_cDefaultValueFormated("");
		this.get_("BERICHT").set_cDefaultValueFormated("");
		this.get_("FAELLIG_AM").set_cDefaultValueFormated("");
		this.get_("GEAENDERT_VON").set_cDefaultValueFormated("");
		this.get_("ID_LAUFZETTEL").set_cDefaultValueFormated("");
		this.get_("ID_LAUFZETTEL_EINTRAG").set_cDefaultValueFormated("");
		this.get_("ID_LAUFZETTEL_PRIO").set_cDefaultValueFormated("");
		this.get_("ID_LAUFZETTEL_STATUS").set_cDefaultValueFormated("");
		this.get_("ID_USER_BEARBEITER").set_cDefaultValueFormated("");
		this.get_("ID_USER_BESITZER").set_cDefaultValueFormated("");
		this.get_("LETZTE_AENDERUNG").set_cDefaultValueFormated("");
		 */
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_LAUFZETTEL_EINTRAG_WICHTIGKEIT","BESCHREIBUNG","ID_WF_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_WF_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_LAUFZETTEL_EINTRAG.ID_USER="+cID_USER+" OR JT_LAUFZETTEL_EINTRAG.ID_WF IN (SELECT ID_WF FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		if (!bibALL.isEmpty(ID_LAUFZETTEL))
		{
			this.add_SQLField(new SQLFieldForRestrictTableRange(
					"JT_LAUFZETTEL_EINTRAG",
					"ID_LAUFZETTEL",
					"ID_LAUFZETTEL",
					new MyE2_String("ID_Laufzettel"),ID_LAUFZETTEL,bibE2.get_CurrSession()), true);
		}
		

		
		
		this.initFields();
	}
	
}
