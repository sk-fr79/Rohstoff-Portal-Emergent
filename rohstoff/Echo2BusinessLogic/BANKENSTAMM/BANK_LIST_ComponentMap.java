package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BANK_LIST_ComponentMap extends E2_ComponentMAP 
{

	public BANK_LIST_ComponentMap() throws myException
	{
		super(new BANK_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(BANK_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(BANK_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")), new MyE2_String("Name 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")), new MyE2_String("Ort"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("B_BANKLEITZAHL")), new MyE2_String("BLZ"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("B_SWIFTCODE")), new MyE2_String("BIC/Swift-Adresse"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("B_IBAN_NR")), new MyE2_String("IBAN-NR"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("B_ID_BANKENSTAMM")), new MyE2_String("ID-Bank"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")), new MyE2_String("ID-Adresse"));


		this.set_oSubQueryAgent(new BANK_LIST_FORMATING_Agent());
	}

}
