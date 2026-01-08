package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_STAT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public LAG_STAT_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_STATUS_LAGER", "", false);
		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:", true, "JT_STATUS_LAGER.ID_SORTE=JT_ARTIKEL.ID_ARTIKEL", "", 
			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:"
				, true, "JT_STATUS_LAGER.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE", "", 
			bibALL.get_HashMap("ADDRESS_INFO", "NVL(JT_ADRESSE.PLZ,'') ||' '|| NVL(JT_ADRESSE.ORT,'') ||', ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'')"));

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("AVG_WERT_GESAMT").set_cDefaultValueFormated("");
		this.get_("AVG_WERT_MIT_NULL").set_cDefaultValueFormated("");
		this.get_("AVG_WERT_NICHT_NULL").set_cDefaultValueFormated("");
		this.get_("BUCHUNGSDATUM").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE").set_cDefaultValueFormated("");
		this.get_("ID_SORTE").set_cDefaultValueFormated("");
		this.get_("ID_STATUS_LAGER").set_cDefaultValueFormated("");
		this.get_("MENGE_GESAMT").set_cDefaultValueFormated("");
		this.get_("MENGE_PREISE_LEER").set_cDefaultValueFormated("");
		this.get_("MENGE_PREISE_NICHT_NULL").set_cDefaultValueFormated("");
		this.get_("MENGE_PREISE_NULL").set_cDefaultValueFormated("");
		this.get_("SUM_RESTWERT").set_cDefaultValueFormated("");
		
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_STATUS_LAGER_WICHTIGKEIT","BESCHREIBUNG","ID_LAG_STAT_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_LAG_STAT_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_STATUS_LAGER.ID_USER="+cID_USER+" OR JT_STATUS_LAGER.ID_LAG_STAT IN (SELECT ID_LAG_STAT FROM "+bibE2.cTO()+".JT_STATUS_LAGER_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
