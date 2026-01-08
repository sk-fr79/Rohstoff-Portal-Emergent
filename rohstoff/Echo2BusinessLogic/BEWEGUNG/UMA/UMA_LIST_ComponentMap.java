package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class UMA_LIST_ComponentMap extends E2_ComponentMAP 
{

	public UMA_LIST_ComponentMap() throws myException
	{
		super(new UMA_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(UMA_CONST.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(UMA_CONST.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		this.add_Component(UMA_CONST.NAME_OF_LOCK_UNLOCK_BUTTON,	new UMA_LIST_BT_LOCK_UMA_KONTRAKT(),new MyE2_String("?"));
		
		this.add_Component(	new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("INFO_ID_ADRESSE"),null,"NAME_ORT","JT_ADRESSE.NAME1 ASC","JT_ADRESSE.NAME1 DESC",200,100), 
				new MyE2_String("Name Kunde"),	true,true, new MyE2_String("Name Kunde"),null,null);

		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_VERTRAG")), 			new MyE2_String("Vertragsdatum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BETREUER")), 				new MyE2_String("Betreuer"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BETREUER")), 				new MyE2_String("Betreuer"));
		
		this.add_Component(UMA_CONST.LIST_COMP_TOCHTER_LIEFERSORTEN, 			new UMA_LIST_GridSortenAnzeige(true), 						new MyE2_String("Liefer-Sorte"));
		this.add_Component(UMA_CONST.LIST_COMP_TOCHTER_RUECKLIEFERSORTEN, 		new UMA_LIST_GridSortenAnzeige(false), 						new MyE2_String("Rückliefer-Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_ARTIKEL_AUSGANG")), 	new MyE2_String("Menge Ausgang"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_ARTIKEL_ZIEL")), 	new MyE2_String("Menge Ziel"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNGEN"),true), 		new MyE2_String("Bemerkungen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_UMA_KONTRAKT")), 		new MyE2_String("ID"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DEL_DATE")), 				new MyE2_String("Lösch-Datum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DEL_KUERZEL")), 			new MyE2_String("Lösch-Kürzel"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DEL_GRUND"),true), 		new MyE2_String("Lösch-Grund"));

		this.get__Comp("DEL_DATE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("DEL_KUERZEL").EXT().set_bIsVisibleInList(false);
		this.get__Comp("DEL_GRUND").EXT().set_bIsVisibleInList(false);
		
		this.set_oSubQueryAgent(new UMA_LIST_FORMATING_Agent());
	}

}
