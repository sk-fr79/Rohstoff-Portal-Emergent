package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

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
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PIPELINE_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_KONTENREGEL_NEU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PIPELINE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PIPELINE_POS;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.MyExportDataRows;
import panter.gmbh.indep.exceptions.myException;

public class FKR_FB_LIST_BT_ExportSQL extends MyE2_Button {
	
	private E2_NavigationList  	oNaviList = null;
	private MyE2_TextField 		oTF_ID_MANDANT = new MyE2_TextField(bibALL.get_ID_MANDANT(),200,20);
	
	public FKR_FB_LIST_BT_ExportSQL(E2_NavigationList naviList)
	{
		this(naviList, null);
	}

	
	public FKR_FB_LIST_BT_ExportSQL(E2_NavigationList NaviList, XX_ActionValidator oValidator)
	{
		super(E2_ResourceIcon.get_RI("sql_button.png"), true);
		
		this.oNaviList = 	NaviList;
		
		if (oValidator != null)
		{
			this.get_vGlobalValidators().add(oValidator);
		}
		
		this.setToolTipText(new MyE2_String("Erzeuge SQL-Dump der markierten Regeln").CTrans());
		
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
			FKR_FB_LIST_BT_ExportSQL oThis = FKR_FB_LIST_BT_ExportSQL.this;
			
			MyE2_Grid oGrid = new MyE2_Grid(3,0);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Zielmandant: ")),E2_INSETS.I_5_5_5_5);
			oGrid.add(oThis.oTF_ID_MANDANT,E2_INSETS.I_5_5_5_5);
			oGrid.add(this.oBT_SetzeParameterFuerSQL,E2_INSETS.I_5_5_5_5);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Sollen die IDs neu erzeugt werden ?")),2,E2_INSETS.I_5_5_5_5);
			oGrid.add(this.oCB_IDsNeuErzeugen,1,E2_INSETS.I_5_5_5_5);
			
			oGrid.add(this.oButtonCreateSQLStatement,3,E2_INSETS.I_5_20_5_5);
			
			this.add(oGrid,E2_INSETS.I_5_5_5_5);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(230), new MyE2_String("Exportiere Daten als SQL"));
		}
		
		private class ownActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FKR_FB_LIST_BT_ExportSQL.this.oTF_ID_MANDANT.setText("$$ID_MANDANT$$");
			}
			
		}
	}
	
	/** Exportiert den gesamten Filter als SQL (inklusive aller AND/OR-Bedingungen und der Kontenregel */
	private class ownActionAgentStarteXport extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vIDs = oNaviList.get_vSelectedIDs_Unformated();
			if (vIDs.size()==0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie mindestens einen Datensatz zum exportieren!")));
				return;
			}

			HashMap<String, String>  filterFields = new HashMap<String, String>();
			filterFields.put(_DB.FILTER$ID_FILTER, "SEQ_"+_DB.FILTER.substring(3)+".NEXTVAL");

			HashMap<String, String>  filterRefFields = new HashMap<String, String>();
			filterRefFields.put(_DB.FILTER$ID_FILTER, "SEQ_"+_DB.FILTER.substring(3)+".CURRVAL");

			HashMap<String, String>  filterAndRefFields = new HashMap<String, String>();
			filterAndRefFields.put(_DB.FILTER_AND$ID_FILTER_AND, "SEQ_"+_DB.FILTER_AND.substring(3)+".NEXTVAL");
			filterAndRefFields.put(_DB.FILTER$ID_FILTER, "SEQ_"+_DB.FILTER.substring(3)+".CURRVAL");

			HashMap<String, String>  filterOrRefFields = new HashMap<String, String>();
			filterOrRefFields.put(_DB.FILTER_OR$ID_FILTER_OR, "SEQ_"+_DB.FILTER_OR.substring(3)+".NEXTVAL");
			filterOrRefFields.put(_DB.FILTER_AND$ID_FILTER_AND, "SEQ_"+_DB.FILTER_AND.substring(3)+".CURRVAL");

			HashMap<String, String>  kontenregelFields = new HashMap<String, String>();
			kontenregelFields.put(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU, "SEQ_"+_DB.FIBU_KONTENREGEL_NEU.substring(3)+".NEXTVAL");
			kontenregelFields.put(_DB.FILTER$ID_FILTER, "SEQ_"+_DB.FILTER.substring(3)+".CURRVAL");
			kontenregelFields.put(_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTO, null);
						
			StringBuffer sb = new StringBuffer();

			for (String id: vIDs) {
				// The filter itself
				RECORD_FILTER rf = new RECORD_FILTER(id);
				sb.append("\n/* FILTER(").append(id).append(") */\n");
				sb.append(new MyExportDataRows(id, _DB.FILTER, oTF_ID_MANDANT.getText(), filterFields).get_cSQL_INSERT_STATEMENT()+";\n");

				// The AND conditions
				RECLIST_FILTER_AND rlfa = rf.get_DOWN_RECORD_LIST_FILTER_AND_id_filter();
				for (RECORD_FILTER_AND rfa : rlfa) {
					sb.append("/* FILTER_AND(").append(rfa.get_ID_FILTER_AND_cUF()).append(") */\n");
					sb.append(new MyExportDataRows(rfa.get_ID_FILTER_AND_cUF(), _DB.FILTER_AND, oTF_ID_MANDANT.getText(), filterAndRefFields).get_cSQL_INSERT_STATEMENT()+";\n");
					RECLIST_FILTER_OR rlfo = rfa.get_DOWN_RECORD_LIST_FILTER_OR_id_filter_and();
					for (RECORD_FILTER_OR rfo : rlfo) {
						sb.append("/* FILTER_OR(").append(rfo.get_ID_FILTER_OR_cUF()).append(") */\n");
						sb.append(new MyExportDataRows(rfo.get_ID_FILTER_OR_cUF(), _DB.FILTER_OR, oTF_ID_MANDANT.getText(), filterOrRefFields).get_cSQL_INSERT_STATEMENT()+";\n");
					}
				}

				// The rule 
				RECORD_FIBU_KONTENREGEL_NEU rfkn = new RECORD_FIBU_KONTENREGEL_NEU("ID_FILTER = "+id);

				RECORD_FIBU_KONTO rfk = new RECORD_FIBU_KONTO(rfkn.get_ID_FIBU_KONTO_cUF());
				//rfkn.set_NEW_VALUE_KOMMENTAR(rfkn.get_KOMMENTAR_cUF()+" ["+rfk.get_KONTO_cUF()+"]");
				// Append FIBU Kontonummer to the Kommentar
				String cNeu = rfkn.get_KOMMENTAR_cUF()+" ["+rfk.get_KONTO_cUF()+"]";
				cNeu.replaceAll("'", "\'");
				kontenregelFields.put(_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR, "'"+cNeu+"'");
				
				sb.append("/* FIBU_KONTENREGEL_NEU(").append(rfkn.get_ID_FIBU_KONTENREGEL_NEU_cUF()).append(") */\n");
				sb.append(new MyExportDataRows(rfkn.get_ID_FIBU_KONTENREGEL_NEU_cUF(), _DB.FIBU_KONTENREGEL_NEU, oTF_ID_MANDANT.getText(), kontenregelFields).get_cSQL_INSERT_STATEMENT()+";\n");
				
			}

			myTempFile  oTempFile = new myTempFile("exp_sql", ".txt", true);
			oTempFile.WriteTextBlock(sb.toString(), myTempFile.CHARSET_ISO_8859_1);
			oTempFile.close();
			
			E2_Download  oDownload = new E2_Download();
			oDownload.starteFileDownload(oTempFile.getFileName(),"sql-statement.txt", "application/text");
			
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Bitte die Einschluss/Ausschluss-Einstellungen der Benutzer müssen gegebenefalls nacherfasst werden !")));
		}
	}

}
