package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class STATKD_LIST_ComponentMap extends E2_ComponentMAP 
{

	public STATKD_LIST_ComponentMap() throws myException
	{
		super(new STATKD_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(STATKD_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(STATKD_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_STATUS_KUNDE")), new MyE2_String("IDKundenstatus"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")), new MyE2_String("ID Adresse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Kunde"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSDATUM")), new MyE2_String("Buchungsdatum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FIBU_FORDERUNG")), new MyE2_String("Forderungen in Fibu"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_RECHNUNG_FORDERUNG")), new MyE2_String("Forderungen auf Rechnungen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FREIE_POS_FORDERUNG")), new MyE2_String("Forderungen in Freien Positionen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FUHREN_FORDERUNG")), new MyE2_String("Forderungen durch Fuhren"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FUHREN_FORDERUNG_GEPLANT")), new MyE2_String("Forderungen d. gepl. Fuhren"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_GESAMT_FORDERUNG")), new MyE2_String("Forderungen Gesamt"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FIBU_VERB")), new MyE2_String("Verbindlichkeiten in Fibu"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_RECHNUNG_VERB")), new MyE2_String("Verbindlichkeiten auf Rechnungen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FREIE_POS_VERB")), new MyE2_String("Verbindlichkeiten in Freien Positionen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FUHREN_VERB")), new MyE2_String("Verbindlichkeit durch Fuhren"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_FUHREN_VERB_GEPLANT")), new MyE2_String("Verbindlichkeit d. gepl. Fuhren"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_GESAMT_VERB")), new MyE2_String("Verbindlichkeiten Gesamt"));
	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUM_GESAMT")), new MyE2_String("Summe Ford./Verb."));
		
		// tooltips
		this.get__Comp("BUCHUNGSDATUM").EXT().set_ToolTipStringForListHeader(new MyE2_String("Datum der Ermittlung"));
		this.get__Comp("SUM_FIBU_FORDERUNG").EXT().set_ToolTipStringForListHeader(new MyE2_String("Forderungen, die in der FIBU nicht ausgeglichen sind"));
		this.get__Comp("SUM_RECHNUNG_FORDERUNG").EXT().set_ToolTipStringForListHeader(new MyE2_String("Forderungen durch Rechnungen, die noch nicht abgeschlossen sind"));
		this.get__Comp("SUM_FREIE_POS_FORDERUNG").EXT().set_ToolTipStringForListHeader(new MyE2_String("Forderungen aus den Freien Positionen."));
		this.get__Comp("SUM_FUHREN_FORDERUNG").EXT().set_ToolTipStringForListHeader(new MyE2_String("Forderungen in Fuhren, die noch nicht verbucht sind, aber wahrscheinlich schon gefahren wurden. Preis wird ggf. durch den Durchschnitt d. letzten 10 R/G ermittelt."));
		this.get__Comp("SUM_FUHREN_FORDERUNG_GEPLANT").EXT().set_ToolTipStringForListHeader(new MyE2_String("Forderungen in Fuhren, die geplant sind. Preis wird ggf. durch den Durchschnitt d. letzten 10 R/G ermittelt."));
		this.get__Comp("SUM_GESAMT_FORDERUNG").EXT().set_ToolTipStringForListHeader(new MyE2_String("Alle Forderungen summiert"));

		this.get__Comp("SUM_FIBU_VERB").EXT().set_ToolTipStringForListHeader(new MyE2_String("Verbindlichkeiten, die in der FIBU nicht ausgeglichen sind"));
		this.get__Comp("SUM_RECHNUNG_VERB").EXT().set_ToolTipStringForListHeader(new MyE2_String("Verbindlichkeiten durch Gutschriften, die noch nicht abgeschlossen sind"));
		this.get__Comp("SUM_FREIE_POS_VERB").EXT().set_ToolTipStringForListHeader(new MyE2_String("Verbindlichkeiten aus den Freien Positionen "));
		this.get__Comp("SUM_FUHREN_VERB").EXT().set_ToolTipStringForListHeader(new MyE2_String("Verbindlichkeiten in Fuhren, die noch nicht verbucht sind, aber wahrscheinlich schon gefahren wurden. Preis wird ggf. durch den Durchschnitt d. letzten 10 R/G ermittelt."));
		this.get__Comp("SUM_FUHREN_VERB_GEPLANT").EXT().set_ToolTipStringForListHeader(new MyE2_String("Verbindlichkeiten in Fuhren, die geplant sind. Preis wird ggf. durch den Durchschnitt d. letzten 10 R/G ermittelt."));
		this.get__Comp("SUM_GESAMT_VERB").EXT().set_ToolTipStringForListHeader(new MyE2_String("Alle Verbindlichkeiten summiert"));
		this.get__Comp("SUM_GESAMT").EXT().set_ToolTipStringForListHeader(new MyE2_String("Forderungen und Verbindlichkeiten verrechnet."));
		
		

		this.set_oSubQueryAgent(new STATKD_LIST_FORMATING_Agent());
	}

}
