package rohstoff.Echo2BusinessLogic.FAHRPLANVARIANTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FV_LIST_ComponentMAP extends E2_ComponentMAP
{

	public FV_LIST_ComponentMAP() throws myException
	{
		super(new FV_LIST_SQLFieldMap());
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(new FV_SEARCH_Adress(oSQLFM.get_("ID_ADRESSE_START")), new MyE2_String("Startadresse"));
		this.add_Component(new FV_SEARCH_Adress(oSQLFM.get_("ID_ADRESSE_ZIEL")), new MyE2_String("Zieladresse"));
		this.add_Component(new FV_SEARCH_Sorte(oSQLFM.get_("ID_ARTIKEL")), new MyE2_String("Sorte"));
		
	}

}
