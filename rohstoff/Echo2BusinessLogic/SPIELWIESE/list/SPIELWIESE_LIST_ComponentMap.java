package rohstoff.Echo2BusinessLogic.SPIELWIESE.list;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.SPIELWIESE.SPIELWIESE_BasicModuleContainer;

public class SPIELWIESE_LIST_ComponentMap extends E2_ComponentMAP 
{

	public SPIELWIESE_LIST_ComponentMap() throws myException
	{
		super(new SPIELWIESE_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(SPIELWIESE_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(SPIELWIESE_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("AKTIV"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_KONTENREGEL_NEU")), new MyE2_String("ID_FIBU_KONTENREGEL_NEU"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_KONTO")), new MyE2_String("ID_FIBU_KONTO"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FILTER")), new MyE2_String("ID_FILTER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("KOMMENTAR")), new MyE2_String("KOMMENTAR"));
		


		this.set_oSubQueryAgent(new SPIELWIESE_LIST_FORMATING_Agent());
	}

}
