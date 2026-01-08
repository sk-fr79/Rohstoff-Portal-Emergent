package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class INSTAT_LIST_ComponentMap extends E2_ComponentMAP 
{

	public INSTAT_LIST_ComponentMap() throws myException
	{
		super(new INSTAT_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(INSTAT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(INSTAT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder
		MyE2_DB_Label_INROW lbl = null;
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_INTRASTAT_MELDUNG")), new MyE2_String("ID"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String(""));
		
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE")), new MyE2_String("ID Fuhre"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")), new MyE2_String("ID FuhrenOrt"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")), new MyE2_String("Sorte"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EIGENMASSE_NUM")), new MyE2_String("Eigenmasse"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KOSTEN_OHNE_FRACHT")), new MyE2_String("Preis"));
		
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FRACHTKOSTEN_NUM")), new MyE2_String("Eingerechnete Transp.-Kost."));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("RECHBETRAG_NUM")), new MyE2_String("Meldepreis"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STATWERT_NUM")), new MyE2_String("Stat. Wert"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EXPORTIERT_AM")), new MyE2_String("Exportiert am"));
		

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MELDEART")), new MyE2_String("1Ein-/2Ausfuhr"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String("Einfuhr: 1 / Ausfuhr: 2"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANMELDEFORM")), new MyE2_String("Anmeldeform"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String("Einfuhr: 1 / Ausfuhr: 2"));

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANMELDEMONAT")), new MyE2_String("Monat"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PAGINIERNUMMER")), new MyE2_String("PosNr"));

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUNDESLAND_FA")), new MyE2_String("Land FA"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERNR")), new MyE2_String("Steuernr"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("UNTERSCHEIDUNGSNR")), new MyE2_String("UnterscheidungsNr"));

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BESTIMM_LAND")), new MyE2_String("1Versende-/2Bestimmungsland"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String("Einfuhr: Das Versendeland / Ausfuhr: Das Bestimmungsland"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BESTIMM_REGION")), new MyE2_String("1Bestimm-/2Ursprungsregion"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String("Einfuhr: die Bestimmungsregion / Ausfuhr: die Ursprungsregion"));

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESCHAEFTSART")), new MyE2_String("Geschäftsart"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VERKEHRSZWEIG")), new MyE2_String("Verkehrszweig"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WARENNR")), new MyE2_String("WarenNr"));
//		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EIGENMASSE")), new MyE2_String("Eigenmasse"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MASSEINHEIT")), new MyE2_String("Masseinheit"));
		
		
//		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("RECHBETRAG")), new MyE2_String("Preis"));

		
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PREISTYP")), new MyE2_String("Preistyp"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String("Preisursprung: F:Fuhre - R:Rechnung - K:Kontrakt - A:Angebot"));
	
//		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STATISTISCHER_WERT")), new MyE2_String("Stat. Wert"));
		lbl.EXT().set_ToolTipStringForListHeader(new MyE2_String("Anteilige Transportkosten. Sind schon im Preis einberechnet."));

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEZUGSMONAT")), new MyE2_String("Bezugsmonat"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEZUGSJAHR")), new MyE2_String("Bezugsjahr"));

		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LAND_URSPRUNG")), new MyE2_String("1Ursprungsland"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNGSKENNZIFFER")), new MyE2_String("Waehrung"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EXPORT_NO_PRICE")), new MyE2_String("Export wenn 0 Euro"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NICHT_MELDEN")), new MyE2_String("Nicht melden"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID_Artikel"));
		
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("UMSATZSTEUER_LKZ")), new MyE2_String("USt-LKZ"));
		lbl = (MyE2_DB_Label_INROW)this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("UMSATZSTEUERID")), new MyE2_String("USt-ID"));
		

		this.set_oSubQueryAgent(new INSTAT_LIST_FORMATING_Agent());
	}

}
