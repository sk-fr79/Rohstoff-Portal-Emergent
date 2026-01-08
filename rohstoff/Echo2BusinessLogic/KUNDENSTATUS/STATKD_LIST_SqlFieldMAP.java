package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class STATKD_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public STATKD_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_STATUS_KUNDE", "", false);
		

		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:"
				, true, "JT_STATUS_KUNDE.ID_ADRESSE = JT_ADRESSE.ID_ADRESSE", "", 
			bibALL.get_HashMap("ADDRESS_INFO", "NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') ||', ' ||  NVL(JT_ADRESSE.PLZ,'') ||' '|| NVL(JT_ADRESSE.ORT,'')"));

		
		/*
		 * beispiel fuer die definition von standard-werten
		 */


		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_STATUS_KUNDE_WICHTIGKEIT","BESCHREIBUNG","ID_STATKD_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_STATKD_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_STATUS_KUNDE.ID_USER="+cID_USER+" OR JT_STATUS_KUNDE.ID_STATKD IN (SELECT ID_STATKD FROM "+bibE2.cTO()+".JT_STATUS_KUNDE_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.add_ORDER_SEGMENT("BUCHUNGSDATUM DESC");
		this.initFields();
	}
	
}
