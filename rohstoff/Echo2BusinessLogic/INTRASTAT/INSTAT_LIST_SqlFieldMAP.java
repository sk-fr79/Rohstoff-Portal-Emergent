package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class INSTAT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public INSTAT_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_INTRASTAT_MELDUNG", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("ID_INTRASTAT_MELDUNG").set_cDefaultValueFormated("");
		this.get_("EXPORTFREIGABE").set_cDefaultValueFormated("");
		this.get_("EXPORTIERT_AM").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_cDefaultValueFormated("");

		this.get_("MELDEART").set_cDefaultValueFormated("");
		this.get_("ANMELDEFORM").set_cDefaultValueFormated("");

		this.get_("ANMELDEMONAT").set_cDefaultValueFormated("");
		this.get_("PAGINIERNUMMER").set_cDefaultValueFormated("");

		this.get_("BUNDESLAND_FA").set_cDefaultValueFormated("");
		this.get_("STEUERNR").set_cDefaultValueFormated("");
		this.get_("UNTERSCHEIDUNGSNR").set_cDefaultValueFormated("");

		this.get_("BESTIMM_LAND").set_cDefaultValueFormated("");
		this.get_("BESTIMM_REGION").set_cDefaultValueFormated("");
		
		this.get_("GESCHAEFTSART").set_cDefaultValueFormated("");
		this.get_("VERKEHRSZWEIG").set_cDefaultValueFormated("");
		this.get_("WARENNR").set_cDefaultValueFormated("");
		this.get_("EIGENMASSE").set_cDefaultValueFormated("");
		this.get_("MASSEINHEIT").set_cDefaultValueFormated("");
		this.get_("RECHBETRAG").set_cDefaultValueFormated("");
		this.get_("STATISTISCHER_WERT").set_cDefaultValueFormated("");

		this.get_("BEZUGSMONAT").set_cDefaultValueFormated("");
		this.get_("BEZUGSJAHR").set_cDefaultValueFormated("");

		this.get_("LAND_URSPRUNG").set_cDefaultValueFormated("");
		this.get_("WAEHRUNGSKENNZIFFER").set_cDefaultValueFormated("");
		this.get_("PREISTYP").set_cDefaultValueFormated("");
		this.get_("EXPORT_NO_PRICE").set_cDefaultValueFormated("");
		
		this.get_("UMSATZSTEUERID").set_cDefaultValueFormated("");
		this.get_("UMSATZSTEUER_LKZ").set_cDefaultValueFormated("");
		
		
		
		this.add_SQLField(new SQLField("to_number(JT_INTRASTAT_MELDUNG.STATISTISCHER_WERT) "  ,
				"STATWERT_NUM",
				new MyString("Stat.Wert"),
				bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("to_number(JT_INTRASTAT_MELDUNG.RECHBETRAG) "  ,
				"RECHBETRAG_NUM",
				new MyString("Preis"),
				bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("to_number(JT_INTRASTAT_MELDUNG.EIGENMASSE) "  ,
				"EIGENMASSE_NUM",
				new MyString("Stat.Wert"),
				bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("to_number(JT_INTRASTAT_MELDUNG.FRACHTKOSTEN) "  ,
				"FRACHTKOSTEN_NUM",
				new MyString("Stat.Wert"),
				bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField("to_number(JT_INTRASTAT_MELDUNG.RECHBETRAG) - nvl(to_number(JT_INTRASTAT_MELDUNG.FRACHTKOSTEN),0)"  ,
				"KOSTEN_OHNE_FRACHT",
				new MyString("Preis ohne Fracht"),
				bibE2.get_CurrSession()), true);

		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:", true, "JT_INTRASTAT_MELDUNG.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL", "", 
			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_INTRASTAT_MELDUNG_WICHTIGKEIT","BESCHREIBUNG","ID_INSTAT_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_INSTAT_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_INTRASTAT_MELDUNG.ID_USER="+cID_USER+" OR JT_INTRASTAT_MELDUNG.ID_INSTAT IN (SELECT ID_INSTAT FROM "+bibE2.cTO()+".JT_INTRASTAT_MELDUNG_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
