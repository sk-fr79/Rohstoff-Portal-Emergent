package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.BANKENSTAMM;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSK_LIST_ComponentMAP extends E2_ComponentMAP
{


	
	public FSK_LIST_ComponentMAP() throws myException
	{
		super(new FSK_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_(_DB.KONTO$IBAN_NR)),new MyE2_String("Iban"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("KONTONUMMER")),new MyE2_String("Konto"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("U_BANKLEITZAHL")),new MyE2_String("BLZ"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("U_"+BANKENSTAMM.swiftcode.fn())),new MyE2_String("Bic/Swift-Adresse"));
		//2014-01-16: iban und swift-code anzeigen
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("A_NAME1")),new MyE2_String("Name der Bank"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("A_ORT")),new MyE2_String("Ort"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ID_KONTO")),new MyE2_String("ID(Konto)"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("U_ID_BANKENSTAMM")),new MyE2_String("ID(Bank)"));
		
		((MyE2IF__Component)this.get("KONTONUMMER")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("U_BANKLEITZAHL")).EXT().set_oColExtent(new Extent(150));
		
		((MyE2IF__Component)this.get(_DB.KONTO$IBAN_NR)).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("U_"+BANKENSTAMM.swiftcode.fn())).EXT().set_oColExtent(new Extent(150));
		
		((MyE2IF__Component)this.get("A_NAME1")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("A_ORT")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("ID_KONTO")).EXT().set_oColExtent(new Extent(50));
		((MyE2IF__Component)this.get("U_ID_BANKENSTAMM")).EXT().set_oColExtent(new Extent(50));
		
	}

}
