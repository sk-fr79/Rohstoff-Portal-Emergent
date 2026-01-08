package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SELECTOR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SEL_LIST_ComponentMap extends E2_ComponentMAP 
{

//	private E2_BasicModuleContainer oCallingModuleContainer = null;
//	
//	private myTempFileAutoDel  		oTempfile = null;  
	
	
	public SEL_LIST_ComponentMap(E2_BasicModuleContainer CallingModuleContainer) throws myException
	{
		super(new SEL_LIST_SqlFieldMAP(CallingModuleContainer.get_MODUL_IDENTIFIER()));

//		this.oCallingModuleContainer = CallingModuleContainer;
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(SEL_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(SEL_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		//this.add_Component("TEST_BUTTON", new btTestSQL(), new MyE2_String("Test"));
		this.add_Component("TEST_BUTTON", new SEL__PRUEFBUTTON(CallingModuleContainer), new MyE2_String("Test"));
		this.add_Component(new ownCheckBoxAktiv(oFM.get_(_DB.SELECTOR$AKTIV)), new MyE2_String("Aktiv"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.SELECTOR$ID_SELECTOR)), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.SELECTOR$USER_TEXT)), new MyE2_String("Auswahltext"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.SELECTOR$WHEREBLOCK)), new MyE2_String("SQL-Statement"));
		
		this.get__Comp(_DB.SELECTOR$AKTIV).EXT().set_oLayout_ListElement(MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_2_0_2));

		this.get__Comp(_DB.SELECTOR$ID_SELECTOR).EXT().set_oColExtent(new Extent(100));
		this.get__Comp(_DB.SELECTOR$USER_TEXT).EXT().set_oColExtent(new Extent(100));
		this.get__Comp(_DB.SELECTOR$WHEREBLOCK).EXT().set_oColExtent(new Extent(500));
		

		this.set_oSubQueryAgent(new SEL_LIST_FORMATING_Agent());
	}

	
	
	private class ownCheckBoxAktiv extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction
	{

		public ownCheckBoxAktiv(SQLField osqlField) throws myException {
			super(osqlField);
			this.add_oActionAgent(this.get_ToggleAction());
		}

		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			ownCheckBoxAktiv oCBCopy = null;
			try {
				oCBCopy = new ownCheckBoxAktiv(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
			}
			return oCBCopy;
		}

		@Override
		public XX_ActionAgent get_ToggleAction() throws myException {
			return new ownActionToggle();
		}
		
		
		private class ownActionToggle extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownCheckBoxAktiv oThis = ownCheckBoxAktiv.this;
				
				RECORD_SELECTOR  recSel = new RECORD_SELECTOR(oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				
				recSel.set_NEW_VALUE_AKTIV(ownCheckBoxAktiv.this.isSelected()?"Y":"N");
				recSel.UPDATE(null, true);
				
				oThis.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				
				oThis.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, false, false);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Selektor wurde auf "+(oThis.isSelected()?"aktiv":"inaktiv")+" geschaltet und gespeichert !")));
			}			
		}
		
	}
	
	
	
	
}
