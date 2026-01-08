package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSM_MASK_Component_DAUGHTER_ZUSATZINFOS extends MyE2_DBC_DaughterTable
{

	public FSM_MASK_Component_DAUGHTER_ZUSATZINFOS(		SQLFieldMAP 			fieldMAPMotherTable, 
														E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMap_ADRESSE_INFO = new SQLFieldMAP("JT_ADRESSE_INFO",bibE2.get_CurrSession());
		oSQLFieldMap_ADRESSE_INFO.addCompleteTable_FIELDLIST("JT_ADRESSE_INFO",":ID_ADRESSE_INFO:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap_ADRESSE_INFO.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE_INFO","ID_ADRESSE_INFO","ID_ADRESSE_INFO",new MyE2_String("ID-Adresse-Info"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE_INFO.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMap_ADRESSE_INFO.add_SQLField(new SQLFieldJoinOutside("JT_ADRESSE_INFO","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMap_ADRESSE_INFO.get_("IST_MESSAGE").set_cDefaultValueFormated("N");
		oSQLFieldMap_ADRESSE_INFO.initFields();
		
		
		E2_ComponentMAP 			oMapEmail = new E2_ComponentMAP(oSQLFieldMap_ADRESSE_INFO);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
	    E2_DropDownSettings oDDValues2 = new E2_DropDownSettings("JT_AKTIONSANLASS", "KURZBEZEICHNUNG", "ID_AKTIONSANLASS", null, true);
		MyE2_DB_TextField			oTF_DatumEreignis = 		new MyE2_DB_TextField(oSQLFieldMap_ADRESSE_INFO.get_SQLField("DATUMEREIGNIS"));
		MyE2_DB_SelectField			oSelectAnlass	=			new MyE2_DB_SelectField(oSQLFieldMap_ADRESSE_INFO.get_("ID_AKTIONSANLASS"),oDDValues2.getDD(),false);
		MyE2_DB_TextArea			oTF_Info = 					new MyE2_DB_TextArea(oSQLFieldMap_ADRESSE_INFO.get_SQLField("TEXT"));
		
		oTF_DatumEreignis.set_iWidthPixel(100);
		oTF_Info.set_iWidthPixel(400);
		oSelectAnlass.setWidth(new Extent(200));

		oTF_DatumEreignis.EXT().set_oColExtent(new Extent(120));
		oTF_Info.EXT().set_oColExtent(new Extent(420));
		oSelectAnlass.EXT().set_oColExtent(new Extent(200));
		
		oTF_DatumEreignis.EXT_DB().set_bIsSortable(false);
		oTF_Info.EXT_DB().set_bIsSortable(false);
		oSelectAnlass.EXT_DB().set_bIsSortable(false);
		
		oMapEmail.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapEmail.add_Component(oTF_DatumEreignis,new MyE2_String("Datum"));
		oMapEmail.add_Component(oSelectAnlass,new MyE2_String("Art Anlass"));
		oMapEmail.add_Component(oTF_Info,new MyE2_String("Info"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(200));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapEmail,
							null);
		
	}

}
