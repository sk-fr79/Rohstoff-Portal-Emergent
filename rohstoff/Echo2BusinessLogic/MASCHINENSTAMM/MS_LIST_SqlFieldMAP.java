package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class MS_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public MS_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_MASCHINEN", "", false);
		

		//2016-09-27: kostenstelle am stueck
		this.add_SQLField(new SQLField(MS__CONST.ADDON_FIELDS.LIST_KOSTENSTELLE_GESAMT),false);
		
		this.add_SQLField(new SQLField(MS__CONST.ADDON_FIELDS.LIST_ANZAHL_KOSTEN), false);
		
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("AKTIV").set_cDefaultValueFormated("");
		this.get_("BEMERKUNG").set_cDefaultValueFormated("");
		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
		this.get_("BRIEFNUMMER").set_cDefaultValueFormated("");
		this.get_("DATUM_ANSCHAFFUNG").set_cDefaultValueFormated("");
		this.get_("ERZEUGT_AM").set_cDefaultValueFormated("");
		this.get_("ERZEUGT_VON").set_cDefaultValueFormated("");
		this.get_("FAHRGESTELLNUMMER").set_cDefaultValueFormated("");
		this.get_("GEKAUFT_AB").set_cDefaultValueFormated("");
		this.get_("GEWAEHRLEISTUNG_BIS").set_cDefaultValueFormated("");
		this.get_("HERSTELLER").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_STANDORT").set_cDefaultValueFormated("");
		this.get_("ID_MASCHINEN").set_cDefaultValueFormated("");
		this.get_("ID_MASCHINENTYP").set_cDefaultValueFormated("");
		this.get_("ID_USER_BEDIENER1").set_cDefaultValueFormated("");
		this.get_("ID_USER_BEDIENER2").set_cDefaultValueFormated("");
		this.get_("KFZKENNZEICHEN").set_cDefaultValueFormated("");
		this.get_("KOSTEN_ANSCHAFFUNG").set_cDefaultValueFormated("");
		this.get_("KOSTENSTELLE1").set_cDefaultValueFormated("");
		this.get_("KOSTENSTELLE2").set_cDefaultValueFormated("");
		this.get_("LEASING_BIS").set_cDefaultValueFormated("");
		this.get_("TYPENBEZ").set_cDefaultValueFormated("");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_MASCHINEN_WICHTIGKEIT","BESCHREIBUNG","ID_MS_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_MS_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_MASCHINEN.ID_USER="+cID_USER+" OR JT_MASCHINEN.ID_MS IN (SELECT ID_MS FROM "+bibE2.cTO()+".JT_MASCHINEN_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
