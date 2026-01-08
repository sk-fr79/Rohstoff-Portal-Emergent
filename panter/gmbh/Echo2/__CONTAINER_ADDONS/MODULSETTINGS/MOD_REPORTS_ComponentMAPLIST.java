package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MOD_REPORTS_ComponentMAPLIST extends E2_ComponentMAP
{

	public MOD_REPORTS_ComponentMAPLIST(String cMODULE_KENNER_LIST_BELONGS_TO) throws myException
	{
		super(new MOD_REPORTS_SQLFieldMapLIST(cMODULE_KENNER_LIST_BELONGS_TO));
		
		SQLFieldMAP oSQLFields = this.get_oSQLFieldMAP();
		
		GridLayoutData  oGLLeftCentered = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,1,2,1));
		GridLayoutData  oGLRightCentered = MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I(2,1,2,1));
		
		this.add_Component("CHECK_BOX",new E2_CheckBoxForList(),new MyE2_String("?"));
		
		this.add_Component(new ownCheckBoxInList(oSQLFields.get_(REPORT.aktiv)), new MyE2_String("Aktiv ?"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFields.get_(_DB.REPORT$TITEL),true), new MyE2_String("Titel"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFields.get_(_DB.REPORT$BUTTONTEXT),true), new MyE2_String("Button-Text"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFields.get_(_DB.REPORT$MODULE_KENNER),true), new MyE2_String("Modul-Kenner"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFields.get_(_DB.REPORT$BUTTON_AUTH_KENNER),true), new MyE2_String("Button-Kenner"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFields.get_(_DB.REPORT$NAME_OF_REPORTFILE),true), new MyE2_String("Report-Datei"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFields.get_(_DB.REPORT$ID_REPORT)), new MyE2_String("ID"));
		
		//2014-01-21: die spalten einblenden, die den datentyp anzeigen
		this.add_Component(new MyE2_DB_CheckBox(oSQLFields.get_(_DB.REPORT$ALLOW_PDF)), new MyE2_String("PDF"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFields.get_(_DB.REPORT$ALLOW_XLS)), new MyE2_String("XLS"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFields.get_(_DB.REPORT$ALLOW_XML)), new MyE2_String("XML"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFields.get_(_DB.REPORT$ALLOW_TXT)), new MyE2_String("TXT"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFields.get_(_DB.REPORT$ALLOW_HTML)), new MyE2_String("HTML"));
		
		
		((MyE2IF__Component)this.get(_DB.REPORT$TITEL)).EXT().set_oColExtent(new Extent(300));
		((MyE2IF__Component)this.get(_DB.REPORT$BUTTONTEXT)).EXT().set_oColExtent(new Extent(300));
		((MyE2IF__Component)this.get(_DB.REPORT$MODULE_KENNER)).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get(_DB.REPORT$BUTTON_AUTH_KENNER)).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get(_DB.REPORT$NAME_OF_REPORTFILE)).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get(_DB.REPORT$ID_REPORT)).EXT().set_oColExtent(new Extent(50));
	
		((MyE2IF__Component)this.get(_DB.REPORT$TITEL)).EXT().set_oLayout_ListElement(oGLLeftCentered);
		((MyE2IF__Component)this.get(_DB.REPORT$BUTTONTEXT)).EXT().set_oLayout_ListElement(oGLLeftCentered);
		((MyE2IF__Component)this.get(_DB.REPORT$MODULE_KENNER)).EXT().set_oLayout_ListElement(oGLLeftCentered);
		((MyE2IF__Component)this.get(_DB.REPORT$BUTTON_AUTH_KENNER)).EXT().set_oLayout_ListElement(oGLLeftCentered);
		((MyE2IF__Component)this.get(_DB.REPORT$NAME_OF_REPORTFILE)).EXT().set_oLayout_ListElement(oGLLeftCentered);
		((MyE2IF__Component)this.get(_DB.REPORT$ID_REPORT)).EXT().set_oLayout_ListElement(oGLRightCentered);

		
		
		((MyE2_DB_Label_INROW)this.get(_DB.REPORT$MODULE_KENNER)).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label_INROW)this.get(_DB.REPORT$BUTTON_AUTH_KENNER)).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label_INROW)this.get(_DB.REPORT$NAME_OF_REPORTFILE)).setFont(new E2_FontPlain(-2));
		((MyE2_DB_Label_INROW)this.get(_DB.REPORT$ID_REPORT)).setFont(new E2_FontPlain(-2));
		
	}

	
	
	private class ownCheckBoxInList extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction {
		
		public ownCheckBoxInList(SQLField osqlField) throws myException {
			super(osqlField);
			this.EXT().set_oLayout_ListElement(new RB_gld()._center_top());
			this.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col(new E2_ColorDark()));
			this.add_oActionAgent(this.get_ToggleAction());
		}

		@Override
		public XX_ActionAgent get_ToggleAction() throws myException {
			return new ownActionToggle();
		}

		private class ownActionToggle extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String id_report = ownCheckBoxInList.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				RECORD_REPORT  rec_rep = new RECORD_REPORT(id_report);
				boolean was_aktiv = rec_rep.is_AKTIV_YES();
				rec_rep.set_NEW_VALUE_AKTIV(was_aktiv?"N":"Y");
				
				bibMSG.add_MESSAGE(rec_rep.UPDATE(true));
				
				ownCheckBoxInList.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);

				if (bibMSG.get_bIsOK()) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Report mit der ID: ").ut(rec_rep.fs(REPORT.id_report)).t(was_aktiv?" wurde inaktiv gesetzt":" wurde aktiviert")));
				}
			}
		}
		
		
		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			try {
				return new ownCheckBoxInList(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.getLocalizedMessage());
			}
		}

		
	}
	
}
