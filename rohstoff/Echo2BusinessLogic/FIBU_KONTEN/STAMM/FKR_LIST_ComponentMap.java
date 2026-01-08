package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;

public class FKR_LIST_ComponentMap extends E2_ComponentMAP 
{

	public FKR_LIST_ComponentMap() throws myException
	{
		super(new FKR_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(FKR_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FKR_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_FIBU_KONTENREGEL")), 		new MyE2_String("Regelnummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DEF_EK_VK")), 					new MyE2_String("Einkauf/Verkauf"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DIENSTLEISTUNG")), 			new MyE2_String("Dienstl?"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PRIVAT")), 					new MyE2_String("Privat?"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERSATZ")), 				new MyE2_String("Steuersatz"));
		
//		this.add_Component(FS_CONST.LIST_COL_INFO_INFOS,			new MyE2_Label(""), new MyE2_String("Infos"));

		
//		oFM.addCompleteTable_FIELDLIST(cTable, ListOfFields, bIncludeList, Writeable, cFieldAliasPrefix);
	
		//this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("QUELLEN_LAENDER")), 		new MyE2_String("Quellenländer"));
		this.add_Component(FKR__CONST.LIST_COMPONENT_QUELLEN_LAND_TABLE, new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11()), new MyE2_String("Quellenländer"));
		this.add_Component(FKR__CONST.LIST_COMPONENT_ZIEL_LAND_TABLE, new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11()), new MyE2_String("Zielländer"));
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZIEL_LAENDER")), 			new MyE2_String("Zielländer"));
		
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_TAX")), 					new MyE2_String("ID_TAX"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTIKELGRUPPE")), 			new MyE2_String("Fibu-Artikelgruppe"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ARTIKEL_GRUPPE_FIBU")), 	new MyE2_String("ID_ARTIKEL_GRUPPE_FIBU"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTIKEL_GESAMT")), 			new MyE2_String("Artikel"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ARTIKEL")), 				new MyE2_String("ID_ARTIKEL"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("AD_NAME_GESAMT")), 			new MyE2_String("Kunde"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ADRESSE")), 				new MyE2_String("ID_ADRESSE"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FIBU_KONTO_GESAMT")), 		new MyE2_String("Buchungskonto"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_KONTO")), 			new MyE2_String("ID_FIBU_KONTO"));

		
		this.set_oSubQueryAgent(new FKR_LIST_FORMATING_Agent());
	}

}
