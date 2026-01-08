package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_LIST_ComponentMap extends E2_ComponentMAP 
{

	public FIBU_EXPORT_LIST_ComponentMap() throws myException
	{
		super(new FIBU_EXPORT_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(FIBU_EXPORT_ModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FIBU_EXPORT_ModuleContainer.NAME_OF_LISTMARKER_IN_LIST,		new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_EXPORT_LOG")), 			new MyE2_String("Export-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MATERIALISIERT")), 		new MyE2_String("fest?"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERZEUGT_VON_EL")), 		new MyE2_String("Erzeugt von"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERZEUGT_AM_EL")), 			new MyE2_String("Erzeugt am"));

		// The "Belegdatum" is now changed to "Datum", since this date may be 
		// corrected by the DatevExporter
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("O_DRUCKDATUM")), 			new MyE2_String("Datum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("RECHNUNGSPOSITION")), 		new MyE2_String("Position"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUMME")), 					new MyE2_String("Umsatz"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNG")), 				new MyE2_String("Währung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEGENKONTO")), 			new MyE2_String("Gegenkonto"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NUMMER")), 				new MyE2_String("Buchungsnummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KONTO")), 					new MyE2_String("Fibu-Konto"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FIBU_KONTENREGEL")), 	new MyE2_String("Kontenregel-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PRIO")), 					new MyE2_String("Errechnete Prio"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_EXPORT_RG")), 		new MyE2_String("Posten"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_RG_PARENT")), 		new MyE2_String("Elternposten"));
		
		
		this.set_oSubQueryAgent(new FIBU_EXPORT_LIST_FormattingAgent());
	}

}
