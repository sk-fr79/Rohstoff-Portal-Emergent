package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class TEST_Container extends E2_BasicModuleContainer 
{
	
	FS_Component_MASK_DAUGHTER_TELEFON oTest = null;
	
	MyE2_TextField    oTF_AdressID = new MyE2_TextField("",100,10);

	public TEST_Container() throws myException 
	{
		super();
		
		Project_SQLFieldMAP  oSqlMap = new Project_SQLFieldMAP("JT_ADRESSE", null,true);
		
		
		//E2_ComponentMAP  oMapHelp = new E2_ComponentMAP(oSqlMap);
		
		oTest =  new FS_Component_MASK_DAUGHTER_TELEFON(oSqlMap, null);

		
		MyE2_Button oButtonFill = new MyE2_Button("Fuelle");
		oButtonFill.add_oActionAgent(new ownActionPopulate());
		
		
		MyE2_Button oButtonSave = new MyE2_Button("Speichern");
		oButtonSave.add_oActionAgent(new ownActionSave());
		
		MyE2_Button oButtonEdit = new MyE2_Button("Edit");
		oButtonEdit.add_oActionAgent(new ownActionFill4Edit());
		
		MyE2_Button oButtonView = new MyE2_Button("View");
		oButtonView.add_oActionAgent(new ownActionFill4View());
		
		this.add(new E2_ComponentGroupHorizontal_NG(this.oTF_AdressID,oButtonFill, oButtonSave, oButtonEdit, oButtonView, LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2)));
		this.add(oTest);

		
		// oTest.prepare_ContentForNew(true);
		//oTest.set_cActual_Formated_DBContent_To_Mask("3003", E2_ComponentMAP.STATUS_EDIT, null);
		oTest.UB_PREPARE_4_NEW(false);
		
		
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("titel"));
		
	}

	

	private class ownActionFill4Edit extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			oTest.UB_PopulateDaughterWithMother_4_EDIT();
		}
	}
	
	
	private class ownActionPopulate extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			oTest.set_UB_MOTHER_ID(TEST_Container.this.oTF_AdressID.getText());
			oTest.UB_PopulateDaughterWithMother_4_EDIT();
		}
	}


	private class ownActionFill4View extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			oTest.UB_PopulateDaughterWithMother_4_VIEW();
		}
	}
	

	
	
	private class ownActionSave extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			bibMSG.add_MESSAGE(oTest.UB_CHECK_INPUT_IN_LIST());
			
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(oTest.UB_get_vSQLStack(),true));
			}
		}
	}
	
	
	
	public class FS_Component_MASK_DAUGHTER_TELEFON extends MyE2_DBC_DaughterTable
	{

		public FS_Component_MASK_DAUGHTER_TELEFON(	SQLFieldMAP 			fieldMAPMotherTable, 
													E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
		{
			super();
			
			SQLFieldMAP oSQLFieldMapTelefon = new SQLFieldMAP("JT_KOMMUNIKATION",bibE2.get_CurrSession());
			oSQLFieldMapTelefon.addCompleteTable_FIELDLIST("JT_KOMMUNIKATION",":ID_KOMMUNIKATION:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
			oSQLFieldMapTelefon.add_SQLField(new SQLFieldForPrimaryKey("JT_KOMMUNIKATION","ID_KOMMUNIKATION","ID_KOMMUNIKATION",new MyE2_String("ID-Kommunikation"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_KOMMUNIKATION.NEXTVAL FROM DUAL",true), false);

			oSQLFieldMapTelefon.add_SQLField(new SQLFieldJoinOutside("JT_KOMMUNIKATION","ID_ADRESSE","ID_ADRESSE",
												new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);
			
			oSQLFieldMapTelefon.get_("ID_KOMMUNIKATIONS_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			
			//2011-02-09: laendervorwahl aus der adressmaske
//			oSQLFieldMapTelefon.get_("WERT_LAENDERVORWAHL").set_cDefaultValueFormated("++49");
			
			oSQLFieldMapTelefon.initFields();
			
			
			E2_DropDownSettings oDDValues = new E2_DropDownSettings("JT_KOMMUNIKATIONS_TYP", "KURZBEZEICHNUNG", "ID_KOMMUNIKATIONS_TYP", "IST_STANDARD", true);

			
			E2_ComponentMAP 			oMapKommunikation = new E2_ComponentMAP(oSQLFieldMapTelefon);
			
			MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
			
			MyE2_DB_TextField			oTF_LaenderVorwahl = 	new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_LAENDERVORWAHL"));
			MyE2_DB_TextField			oTF_Vorwahl = 			new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_VORWAHL"));
			MyE2_DB_TextField			oTF_Rufnummer = 		new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_RUFNUMMER"));
			MyE2_DB_TextField			oTF_Sonstige = 			new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_SONSTIGE"));
			MyE2_DB_SelectField			oSelectTyp	=			new MyE2_DB_SelectField(oSQLFieldMapTelefon.get_("ID_KOMMUNIKATIONS_TYP"),oDDValues.getDD(),false);
			MyE2_DB_CheckBox			oIstStd	=				new MyE2_DB_CheckBox(oSQLFieldMapTelefon.get_("IST_STANDARD"));
			
			oTF_LaenderVorwahl.EXT_DB().set_bIsSortable(false);
			oTF_Vorwahl.EXT_DB().set_bIsSortable(false);
			oTF_Sonstige.EXT_DB().set_bIsSortable(false);
			oTF_Rufnummer.EXT_DB().set_bIsSortable(false);
			oSelectTyp.EXT_DB().set_bIsSortable(false);
			oIstStd.EXT_DB().set_bIsSortable(false);
			
			oTF_LaenderVorwahl.set_iWidthPixel(100);
			oTF_Vorwahl.set_iWidthPixel(100);
			oTF_Rufnummer.set_iWidthPixel(130);
			oTF_LaenderVorwahl.set_iWidthPixel(100);
			
			
			oMapKommunikation.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
			oMapKommunikation.add_Component(oTF_LaenderVorwahl,new MyE2_String("Länd.Vorw."));
			oMapKommunikation.add_Component(oTF_Vorwahl,new MyE2_String("Vorwahl"));
			oMapKommunikation.add_Component(oTF_Rufnummer,new MyE2_String("Rufnummer"));
			oMapKommunikation.add_Component(oTF_Sonstige,new MyE2_String("Beschreibung"));
			oMapKommunikation.add_Component(oSelectTyp,new MyE2_String("Kommunikations-Typ"));
			oMapKommunikation.add_Component(oIstStd,new MyE2_String("Std."));
		
			
			MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
			
			oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, true));
			oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
			this.get_vComponentForDifferentTasks().add(oButtonNEW);

//			this.set_oContainerExScrollHeight(new Extent(170));
			this.set_to_100_percent();
			this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));
			
			this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
								ocomponentMAP_from_Mother,
								oMapKommunikation,
								null);
			
		}

		
		
	}

	
	
}
