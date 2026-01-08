package panter.gmbh.Echo2.ListAndMask.List.ExportSql;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_QueryInList_SELECTED_SEITE_ALL;

public class EXP_ButtonToCreateSqLExport extends E2_Button {
	
	private Vector<String>  		addressIds = new Vector<String>();
	private E2_Grid  				anzeigeGrid = new E2_Grid();

	private _TAB 					tab = null;
	private E2_NavigationList  		oNaviList = null;
	
	private Vector<XX_ActionAgent>  vActionAgentsAfterExport = new Vector<XX_ActionAgent>();
 	
	private EXP_portSqlFromList 	exporter = null;   
	
	/**
	 * empty constructor, mus call _init() before using
	 */
	public EXP_ButtonToCreateSqLExport()	{
		super();
	}
	
	public EXP_ButtonToCreateSqLExport(E2_NavigationList NaviList) throws myException {
		super();
		this.__init(NaviList,null);
	}

	
	public EXP_ButtonToCreateSqLExport(E2_NavigationList NaviList, XX_ActionValidator oValidator) throws myException	{
		super();
		this.__init(NaviList,oValidator);
	}

	public EXP_ButtonToCreateSqLExport(E2_NavigationList NaviList, Vector<XX_ActionAgent>  actionAgentsAfterExport) throws myException {
		super();
		if (actionAgentsAfterExport!=null) {
			this.vActionAgentsAfterExport.addAll(actionAgentsAfterExport);
		}
		this.__init(NaviList,null);
	}

	
	public EXP_ButtonToCreateSqLExport(E2_NavigationList NaviList, XX_ActionValidator oValidator, Vector<XX_ActionAgent>  actionAgentsAfterExport) throws myException 	{
		super();
		if (actionAgentsAfterExport!=null) {
			this.vActionAgentsAfterExport.addAll(actionAgentsAfterExport);
		}
		this.__init(NaviList,oValidator);
	}
	
	public EXP_ButtonToCreateSqLExport _init(E2_NavigationList NaviList) throws myException {
		this.__init(NaviList, null);
		return this;
	}

	public EXP_ButtonToCreateSqLExport _init(E2_NavigationList NaviList, XX_ActionValidator oValidator) throws myException {
		this.__init(NaviList, oValidator);
		return this;
	}
	
	private  void __init(E2_NavigationList NaviList, XX_ActionValidator oValidator) throws myException {
//		this._image(E2_ResourceIcon.get_RI("sql_button.png"), true);
		
		this._t("SQL")._standard_text_button()._bord_black()._bc(Color.GREEN)._b()._i()._fsa(2)._insets(2, 1, 2, 1);
		
		this.oNaviList = 	NaviList;
		this.tab =   _TAB.find_Table(this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE());

		if (oValidator == null) {
			this.get_vGlobalValidators().add(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER());
		} else {
			this.get_vGlobalValidators().add(oValidator);
		}
		
		this.setToolTipText(new MyE2_String("Ermöglicht es, für selektierte Datensätze ein komplettes SQL-INSERT-Statement zu erzeugen").CTrans());
		
		
		this.add_oActionAgent(new XX_ActionAgent()	{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				new PopupSelectAdresses().ShowPopup(NaviList, S.ms("Bitte wählen Sie die zu exportierenden Steuersätze"));;
			}
		});		

		
	}
	
	
	
	private class PopupSelectAdresses extends POPUP_QueryInList_SELECTED_SEITE_ALL {
		@Override
		public void do_when_ok_is_clicked(Vector<String> ids) 	throws myException {
			addressIds.removeAllElements();
			addressIds.addAll(ids);
			anzeigeGrid._clear();

			new ownPopupWindow();
			
			//nach der auswahl der Adressen erfolgt die definitionsmaske
		}
	}

	
	
	
	private class ownPopupWindow extends E2_BasicModuleContainer 	{
		
		public ownPopupWindow() throws myException		{
			super();
			EXP_ButtonToCreateSqLExport oThis = EXP_ButtonToCreateSqLExport.this;
			
			oThis.exporter = new EXP_portSqlFromList(EXP_ButtonToCreateSqLExport.this.tab);
			E2_Grid  gridDef = oThis.exporter.getExpDefGrid();
			
			gridDef._a( new E2_Grid()._a(
					new E2_Button()._tr("Starte Export")._standard_text_button()._aaa(new ownActionAgentStarteXport())
										), new RB_gld()._ins(5, 10, 5, 5)._span(gridDef.getSize()));
			
			this.add(gridDef,E2_INSETS.I_5_5_5_5);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(800), new MyE2_String("Definiere die Spalten"));
		}
		
	}
	
	
	private class ownActionAgentStarteXport extends XX_ActionAgent 	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException		{
			
			EXP_ButtonToCreateSqLExport oThis = EXP_ButtonToCreateSqLExport.this;

			//zuerst die werte speichern
			oThis.exporter._saveUserSettings();
			
			if (EXP_ButtonToCreateSqLExport.this.addressIds.size()==0) 	{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie mindestens einen Datensatz zum exportieren!")));
			} else {
				StringBuffer cSQL_TEXT = new StringBuffer();
				for (String id: EXP_ButtonToCreateSqLExport.this.addressIds) {
					cSQL_TEXT.append(oThis.exporter.generateExportString(new MyLong(id).get_lValue())+";\n");
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
