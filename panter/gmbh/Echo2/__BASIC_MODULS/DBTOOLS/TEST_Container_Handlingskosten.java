package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_ARTBEZ.FSK_Daughter_Kosten_Artbez_Lief;

public class TEST_Container_Handlingskosten extends E2_BasicModuleContainer 
{
	 
	FSK_Daughter_Kosten_Artbez_Lief    oTest = null;
	
	MyE2_TextField    oTF_ID = new MyE2_TextField("",100,10);

	public TEST_Container_Handlingskosten() throws myException 
	{
		super();
		
		Project_SQLFieldMAP  oSqlMap = new Project_SQLFieldMAP("JT_ARTIKELBEZ_LIEF", null,true);
		 
		
		//E2_ComponentMAP  oMapHelp = new E2_ComponentMAP(oSqlMap);
		
		oTest =  new FSK_Daughter_Kosten_Artbez_Lief(oSqlMap, null);

		
		MyE2_Button oButtonFill = new MyE2_Button("Fuelle");
		oButtonFill.add_oActionAgent(new ownActionPopulate());
		
		
		MyE2_Button oButtonSave = new MyE2_Button("Speichern");
		oButtonSave.add_oActionAgent(new ownActionSave());
		
		MyE2_Button oButtonEdit = new MyE2_Button("Edit");
		oButtonEdit.add_oActionAgent(new ownActionFill4Edit());
		
		MyE2_Button oButtonView = new MyE2_Button("View");
		oButtonView.add_oActionAgent(new ownActionFill4View());
		
		this.add(new E2_ComponentGroupHorizontal_NG(this.oTF_ID,oButtonFill, oButtonSave, oButtonEdit, oButtonView, LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_2_2_2_2)));
		this.add(oTest);

		
		// oTest.prepare_ContentForNew(true);
		//oTest.set_cActual_Formated_DBContent_To_Mask("3003", E2_ComponentMAP.STATUS_EDIT, null);
		oTest.UB_PREPARE_4_NEW(true);
		
		
		
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
			oTest.set_UB_MOTHER_ID(oTF_ID.getText());
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
			oTest.refresh_list();
		}
	}
	
	
	
	
}
