package panter.gmbh.Echo2.ListAndMask.List;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.MyExportDataRows;
import panter.gmbh.indep.exceptions.myException;

public class E2_ButtonToCreate_SQL_ExportStatements extends MyE2_Button
{

	private String 				cTableName = null;
	private E2_NavigationList  	oNaviList = null;
	private MyE2_TextField 		oTF_ID_MANDANT = new MyE2_TextField(bibALL.get_ID_MANDANT(),200,20);
	
	private Vector<XX_ActionAgent>  vActionAgentsAfterExport = new Vector<XX_ActionAgent>();
 	
	
	/**
	 * empty constructor, mus call _init() before using
	 */
	public E2_ButtonToCreate_SQL_ExportStatements()	{
		super(E2_ResourceIcon.get_RI("sql_button.png"), true);
	}
	
	public E2_ButtonToCreate_SQL_ExportStatements _init(E2_NavigationList NaviList) {
		this.__init(NaviList, null);
		return this;
	}
	public E2_ButtonToCreate_SQL_ExportStatements _init(E2_NavigationList NaviList, XX_ActionValidator oValidator) {
		this.__init(NaviList, oValidator);
		return this;
	}
	
	
	public E2_ButtonToCreate_SQL_ExportStatements(E2_NavigationList NaviList)
	{
		super(E2_ResourceIcon.get_RI("sql_button.png"), true);
		this.__init(NaviList,null);
	}

	
	
	public E2_ButtonToCreate_SQL_ExportStatements(E2_NavigationList NaviList, XX_ActionValidator oValidator)
	{
		super(E2_ResourceIcon.get_RI("sql_button.png"), true);
		this.__init(NaviList,oValidator);
	}

	public E2_ButtonToCreate_SQL_ExportStatements(E2_NavigationList NaviList, Vector<XX_ActionAgent>  actionAgentsAfterExport)
	{
		super(E2_ResourceIcon.get_RI("sql_button.png"), true);
		if (actionAgentsAfterExport!=null) {
			this.vActionAgentsAfterExport.addAll(actionAgentsAfterExport);
		}
		this.__init(NaviList,null);
	}

	
	public E2_ButtonToCreate_SQL_ExportStatements(E2_NavigationList NaviList, XX_ActionValidator oValidator, Vector<XX_ActionAgent>  actionAgentsAfterExport)
	{
		super(E2_ResourceIcon.get_RI("sql_button.png"), true);
		if (actionAgentsAfterExport!=null) {
			this.vActionAgentsAfterExport.addAll(actionAgentsAfterExport);
		}
		this.__init(NaviList,oValidator);
	}
	
	
	private  void __init(E2_NavigationList NaviList, XX_ActionValidator oValidator) {
		this.oNaviList = 	NaviList;
		this.cTableName = 	this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE();

		if (oValidator == null) {
			this.get_vGlobalValidators().add(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER());
		} else {
			this.get_vGlobalValidators().add(oValidator);
		}
		
		this.setToolTipText(new MyE2_String("Ermöglicht es, für selektierte Datensätze ein komplettes SQL-INSERT-Statement zu erzeugen").CTrans());
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				new ownPopupWindow();
			}
		});		

		
	}
	
	
	
	private class ownPopupWindow extends E2_BasicModuleContainer
	{
		
		private MyE2_Button  	   	oButtonCreateSQLStatement = new MyE2_Button(new MyE2_String("Erzeuge SQL"), new MyE2_String("Export starten"), new ownActionAgentStarteXport());
		private MyE2_CheckBox		oCB_IDsNeuErzeugen = new MyE2_CheckBox(true,false);
		private MyE2_Button  		oBT_SetzeParameterFuerSQL = new MyE2_Button(new MyE2_String("Mandant variabel halten"),
																				MyE2_Button.StyleTextButton(true),
																				new MyE2_String("Setzt einen SQL-Parameter statt der Mandanten-ID ein"),
																				new ownActionAgent());
		
		public ownPopupWindow() throws myException
		{
			super();
			E2_ButtonToCreate_SQL_ExportStatements oThis = E2_ButtonToCreate_SQL_ExportStatements.this;
			
			MyE2_Grid oGrid = new MyE2_Grid(3,0);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Zielmandant: ")),E2_INSETS.I_5_5_5_5);
			oGrid.add(oThis.oTF_ID_MANDANT,E2_INSETS.I_5_5_5_5);
			oGrid.add(this.oBT_SetzeParameterFuerSQL,E2_INSETS.I_5_5_5_5);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Sollen die IDs neu erzeugt werden ?")),2,E2_INSETS.I_5_5_5_5);
			oGrid.add(this.oCB_IDsNeuErzeugen,1,E2_INSETS.I_5_5_5_5);
			
			oGrid.add(this.oButtonCreateSQLStatement,3,E2_INSETS.I_5_20_5_5);
			
			if (oThis.vActionAgentsAfterExport.size()>0) {
				this.oButtonCreateSQLStatement.add_oActionAgent(oThis.vActionAgentsAfterExport, false);
			}
			
			
			this.add(oGrid,E2_INSETS.I_5_5_5_5);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(230), new MyE2_String("Exportiere Daten als SQL"));
		}
		
		private class ownActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_ButtonToCreate_SQL_ExportStatements.this.oTF_ID_MANDANT.setText("$$ID_MANDANT$$");
			}
			
		}
	}
	
	
	private class ownActionAgentStarteXport extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ButtonToCreate_SQL_ExportStatements oThis = E2_ButtonToCreate_SQL_ExportStatements.this;
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			HashMap<String, String>  hmErsatz = new HashMap<String, String>();
			hmErsatz.put("ID_"+oThis.cTableName.substring(3), "SEQ_"+oThis.cTableName.substring(3)+".NEXTVAL");
			
			if (vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie mindestens einen Datensatz zum exportieren!")));
			}
			else
			{
				StringBuffer cSQL_TEXT = new StringBuffer();
				
				for (String cID: vIDs)
				{
					cSQL_TEXT.append(new MyExportDataRows(cID, oThis.cTableName, oThis.oTF_ID_MANDANT.getText(), hmErsatz).get_cSQL_INSERT_STATEMENT()+";\n");
				}

				myTempFile  oTempFile = new myTempFile("exp_sql", ".txt", true);
				oTempFile.WriteTextBlock(cSQL_TEXT.toString(), myTempFile.CHARSET_ISO_8859_1);
				oTempFile.close();
				
				E2_Download  oDownload = new E2_Download();
				oDownload.starteFileDownload(oTempFile.getFileName(),"sql-statement.txt", "application/text");
			}

			
		}
	}
	
}
