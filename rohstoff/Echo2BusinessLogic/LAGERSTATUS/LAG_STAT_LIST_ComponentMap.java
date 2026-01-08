package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_STAT_LIST_ComponentMap extends E2_ComponentMAP 
{

	public LAG_STAT_LIST_ComponentMap() throws myException
	{
		super(new LAG_STAT_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(LAG_STAT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LAG_STAT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_STATUS_LAGER")), new MyE2_String("IDStatus"));
		MyE2IF__DB_Component o = null;
		
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ADRESSE")), new MyE2_String("ID Adresse"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("ID_Status_Lager"));
		
		o = this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Lager"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Lieferant"));

		o = this.add_Component(new MyE2_DB_Label(oFM.get_("ID_SORTE")), new MyE2_String("ID Sorte"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("ID_Sorte"));
		
		o = this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")), new MyE2_String("Sorte"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Sorte"));

		o = this.add_Component(new MyE2_DB_Label(oFM.get_("BUCHUNGSDATUM")), new MyE2_String("Buchungsdatum"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Datum der Generierung"));

		o = this.add_Component(new MyE2_DB_Label(oFM.get_("MENGE_GESAMT")), new MyE2_String("Gesamtmenge"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Summe aller nicht verbuchten Wareneingänge, mit und ohne Preise."));
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("MENGE_PREISE_NICHT_NULL")), new MyE2_String("Menge Preise > 0"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Summe aller Mengen, die einen Preis > 0 Euro haben."));
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("MENGE_PREISE_NULL")), new MyE2_String("Menge 0-Preise"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Summe aller Mengen, die einen Preis mit 0 Euro haben."));
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("MENGE_PREISE_LEER")), new MyE2_String("Menge unbepreist"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Summe aller Mengen, die noch keine Preise zugeordnet haben."));
		
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("SUM_RESTWERT")), new MyE2_String("Wert bepreist"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtwert des Lagers. Unbepreiste Mengen werden mit 0 Euro betrachtet."));
		
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("AVG_WERT_GESAMT")), new MyE2_String("Ø Gesamt"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Durchschnittspreis aller Mengen ( Unbepreiste Mengen werden mit 0 Euro betrachtet)"));
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("AVG_WERT_MIT_NULL")), new MyE2_String("Ø mit 0-Preisen"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Durchschnittspreis aller bepreisten Mengen. (Es werden auch Mengen berücksichtigt, die mit 0 Euro bepreist sind.)"));
		o = this.add_Component(new MyE2_DB_Label(oFM.get_("AVG_WERT_NICHT_NULL")), new MyE2_String("Ø ohne 0-Preise"));
		o.EXT().set_ToolTipStringForListHeader(new MyE2_String("Durchschnittspreis der Mengen, die einen Preis > 0 Euro haben."));

		this.set_oSubQueryAgent(new LAG_STAT_LIST_FORMATING_Agent());
		
		
		/**
		 * 		m_lblDescMengeGesamt = new MyE2_Label("Gesamtmenge");
		m_lblDescMengeGesamt.setToolTipText(new MyString("Summe aller nicht verbuchten Wareneingänge, mit und ohne Preise.").CTrans());
		m_lblDescMengePreiseUngleich0 = new MyE2_Label("Menge Preise > 0");
		m_lblDescMengePreiseUngleich0.setToolTipText(new MyString("Summe aller Mengen, die einen Preis > 0 Euro haben.").CTrans());
		
		m_lblDescMengeNur0Preise = new MyE2_Label("Menge 0-Preise");
		m_lblDescMengeNur0Preise.setToolTipText(new MyString("Summe aller Mengen, die einen Preis mit 0 Euro haben.").CTrans());
		m_lblDescMengeOhnePreise = new MyE2_Label("Menge unbepreist");
		m_lblDescMengeOhnePreise.setToolTipText(new MyString("Summe aller Mengen, die noch keine Preise zugeordnet haben.").CTrans());

		m_lblDescSumRestwert = new MyE2_Label("Wert bepreist");
		m_lblDescSumRestwert.setToolTipText(new MyString("Gesamtwert des Lagers. Unbepreiste Mengen werden mit 0 Euro betrachtet.").CTrans());
		
		m_lblDescAVGRestwertGesamt = new MyE2_Label("Ø Gesamt");
		m_lblDescAVGRestwertGesamt.setToolTipText(new MyString("Durchschnittspreis aller Mengen ( Unbepreiste Mengen werden mit 0 Euro betrachtet)").CTrans());
		m_lblDescAVGWertOhne0Preise = new MyE2_Label("Ø ohne 0-Preise");
		m_lblDescAVGWertOhne0Preise.setToolTipText(new MyString("Durchschnittspreis der Mengen, die einen Preis > 0 Euro haben.").CTrans());
		m_lblDescAVGWertMit0Preise = new MyE2_Label("Ø mit 0-Preisen");
		m_lblDescAVGWertMit0Preise.setToolTipText(new MyString("Durchschnittspreis aller Mengen die bepreist sind. Es werden auch die 0 Euro-Mengen betrachtet.").CTrans());

		 */
		
	}

}
