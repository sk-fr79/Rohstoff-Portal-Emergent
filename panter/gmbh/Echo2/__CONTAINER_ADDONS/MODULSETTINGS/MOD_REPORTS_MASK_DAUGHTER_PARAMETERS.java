package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PARAMETER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MOD_REPORTS_MASK_DAUGHTER_PARAMETERS extends MyE2_DBC_DaughterTable
{

	public MOD_REPORTS_MASK_DAUGHTER_PARAMETERS(	SQLFieldMAP 			fieldMAPMotherTable, 
													E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapParameter = new SQLFieldMAP("JT_REPORT_PARAMETER",bibE2.get_CurrSession());
		oSQLFieldMapParameter.addCompleteTable_FIELDLIST("JT_REPORT_PARAMETER",":ID_REPORT_PARAMETER:ID_REPORT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapParameter.add_SQLField(new SQLFieldForPrimaryKey("JT_REPORT_PARAMETER","ID_REPORT_PARAMETER","ID_REPORT_PARAMETER",new MyE2_String("ID-Report-Parameter"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_REPORT_PARAMETER.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapParameter.add_SQLField(new SQLFieldJoinOutside("JT_REPORT_PARAMETER","ID_REPORT","ID_REPORT",
											new MyE2_String("ID-Report"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_REPORT")), false);

		oSQLFieldMapParameter.initFields();
		
		String[][] cEmailTyp = new String[4][2];
		cEmailTyp[0][0] = "-";
		cEmailTyp[0][1] = "";
		cEmailTyp[1][0] = myCONST.REPORT_TYPE_TEXT_TEXT;
		cEmailTyp[1][1] = myCONST.REPORT_TYPE_VALUE_TEXT;
		
		cEmailTyp[2][0] = myCONST.REPORT_TYPE_TEXT_INTEGER;
		cEmailTyp[2][1] = myCONST.REPORT_TYPE_VALUE_INTEGER;
		
		cEmailTyp[3][0] = myCONST.REPORT_TYPE_TEXT_DATE;
		cEmailTyp[3][1] = myCONST.REPORT_TYPE_VALUE_DATE;
		
		
		E2_ComponentMAP 				oMapParameters = 		new E2_ComponentMAP(oSQLFieldMapParameter);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 		new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_TextBenutzer = 		new MyE2_DB_TextField(oSQLFieldMapParameter.get_SQLField("TEXTUSERINTERFACE"),true,200,-1,false,new E2_FontPlain(-2));
		MyE2_DB_TextField			oTF_Parametername = 	new MyE2_DB_TextField(oSQLFieldMapParameter.get_SQLField("PARAMETERNAME_IN_REPORT"),true,200,-1,false,new E2_FontPlain(-2));
		MyE2_DB_TextField			oTF_Default = 			new MyE2_DB_TextField(oSQLFieldMapParameter.get_SQLField("DEFAULTVALUE"),true,200,-1,false,new E2_FontPlain(-2));
		MyE2_DB_TextArea 			oTF_BESCHREIBUNGSText =	new MyE2_DB_TextArea(oSQLFieldMapParameter.get_SQLField("BESCHREIBUNGSTEXT"),320,5,false,new E2_FontPlain(-2));
		MyE2_DB_TextArea 			oTF_DropDownDef =		new MyE2_DB_TextArea(oSQLFieldMapParameter.get_SQLField("DROPDOWN_DEF"),320,5,false,new E2_FontPlain(-2));
		MyE2_DB_SelectField			oSelectTyp	=			new MyE2_DB_SelectField(oSQLFieldMapParameter.get_("PARAMETERTYP"),cEmailTyp,true, new Extent(100));
		MyE2_DB_CheckBox			oCB_MustBeSet = 		new MyE2_DB_CheckBox(oSQLFieldMapParameter.get_SQLField("PARAMETER_MUST_BE_SET"));

		//neue felder fuer die ersetzungstabellen der werte
		MyE2_DB_TextArea 			oTF_ErsetzungLeer =		new MyE2_DB_TextArea(oSQLFieldMapParameter.get_SQLField("ERSATZTEXT_FUER_LEER"),320,5,false,new E2_FontPlain(-2));
		MyE2_DB_TextArea 			oTF_ErsetzungNormal =	new MyE2_DB_TextArea(oSQLFieldMapParameter.get_SQLField("ERSATZTEXT_FUER_WERT"),320,5,false,new E2_FontPlain(-2));
		
		oSelectTyp.setWidth(new Extent(100));
		oCB_MustBeSet.setWidth(new Extent(50));

		oTF_TextBenutzer.EXT().set_oColExtent(new Extent(160));
		oTF_Parametername.EXT().set_oColExtent(new Extent(160));
		oTF_Default.EXT().set_oColExtent(new Extent(160));
		oSelectTyp.EXT().set_oColExtent(new Extent(80));
		oCB_MustBeSet.EXT().set_oColExtent(new Extent(60));

		MyE2_DB_MultiComponentColumn oCol1 = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oCol2 = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oCol3 = new MyE2_DB_MultiComponentColumn();
		
		MyE2_DB_MultiComponentColumn oCol4 = new MyE2_DB_MultiComponentColumn();    //neue column fuer die ersetzungen der parameter-werte

		oCol1.add_Component(oTF_TextBenutzer,new MyE2_String("Eingabeaufforderung"),null);
		oCol1.add_Component(oTF_Parametername,new MyE2_String("Name des Parameters"),null);
		oCol1.add_Component(oTF_Default,new MyE2_String("Vorgabe"),null);

		oCol2.add_Component(oTF_BESCHREIBUNGSText,new MyE2_String("Beschreibung des Parameters"),null);
		oCol2.add_Component(oTF_DropDownDef,new MyE2_String("Definition für Dropdown-Feld"),null);
		
		oCol3.add_Component(oSelectTyp,new MyE2_String("Typ"),null);
		oCol3.add_Component(oCB_MustBeSet,new MyE2_String("Muss-Feld ?"),null);

		oCol4.add_Component(oTF_ErsetzungLeer,new MyE2_String("Ersatz-Wert wenn LEER"),null);
		oCol4.add_Component(oTF_ErsetzungNormal,new MyE2_String("Ersatz-Wert (!!!) mit #WERT#"),null);
		
		oMapParameters.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapParameters.add_Component("HASH_VAL1",oCol1,new MyE2_String("Eingabeaufforderung/Parameter-Name"));
		oMapParameters.add_Component("HASH_VAL2",oCol2,new MyE2_String("Parameter-Beschreibung/DropDown-Def"));
		oMapParameters.add_Component("HASH_VAL4",oCol4,new MyE2_String("Ersatzwerte fuer den Report"));
		oMapParameters.add_Component("HASH_VAL3",oCol3,new MyE2_String("Typ/Muss?"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollWidth(new Extent(950));
		this.set_oContainerExScrollHeight(new Extent(500));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapParameters,
							null);
		
		
		this.set_to_100_percent();
		
		this.get_oNavigationList().get_oDataGrid().setWidth(new Extent(100,Extent.PERCENT));
		
		this.get_oOwnComponentMAP_ForList().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
		this.get_oOwnComponentMAP_ForList().get_oSQLFieldMAP().add_ORDER_SEGMENT(RECORD_REPORT_PARAMETER.FIELD__TEXTUSERINTERFACE);
		
	}

}
