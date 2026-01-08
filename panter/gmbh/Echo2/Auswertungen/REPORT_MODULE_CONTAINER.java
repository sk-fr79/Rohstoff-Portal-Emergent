package panter.gmbh.Echo2.Auswertungen;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.xmlDefTools.E2_ModuleContainerLIST_XML;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP_CreateDropDownForParameters;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.Project_TableNamingAgent;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class REPORT_MODULE_CONTAINER extends E2_BasicModuleContainer 
{
	
	private MyE2_SelectField oSelectQuerys = null;
	private E2_ExpandableRow oRowForStartReport = null;
	private MyE2_Column      oColForStartReport = null;

	private E2_ExpandableRow oRowForShowReport = null;
	private MyE2_Column      oColForShowReport = null;

	//Vector aus parameterfeldern, die den eintraegen in Param1 ... Param6 entsprechende je ein Textfield enthalten
	private Vector<MyE2IF__Component> 	 vParamFields = new Vector<MyE2IF__Component>();
	
	private MyE2_Button 	oButtonStartReport = new MyE2_Button("Starte Report !");
	private String     		cLast_TempTableID = null;
	
	
	/*
	 * dreigeteiler container fuer die datenbankabfragen
	 * 1. Selector mit erlaubten abfragen des benutzers
	 * 2. Bereich mit abfrage-eingaben
	 * 3. liste mit ergebnissen
	 */
	public REPORT_MODULE_CONTAINER(String cID_REPORT)  throws myException
	{
		super();
		
		this.set_bVisible_Row_For_Messages(true);
		

		if (bibALL.isEmpty(cID_REPORT))   // dann erfolgt der Aufruf ueber die mehrfachmaske und die selektion der moeglichen reports wird angezeigt
		{
			String cQuery = "SELECT  ID_QUERY,NAME FROM "+bibE2.cTO()+".JT_QUERY ORDER BY NAME ";
	
			if (!bibALL.get_bIST_SUPERVISOR())
			{
				cQuery = "SELECT ID_QUERY,NAME FROM "+bibE2.cTO()+".JT_QUERY" +
						    "  WHERE ID_QUERY IN " +
						    "(SELECT ID_QUERY FROM "+bibE2.cTO()+".JT_QUERY_TEILNEHMER WHERE ID_USER="+bibALL.get_ID_USER()+")" +
						    " ORDER BY NAME ";
			}
	
			
			String[][] cReports = bibDB.EinzelAbfrageInArray(cQuery, "");
			
			if (cReports==null)
				throw new myException(this," Reports-Query is not correct: "+cQuery);
			
			
			String[][] cHelpDropDown = new String[cReports.length+1][2];
			cHelpDropDown[0][0]="-";cHelpDropDown[0][1]="";
			for (int i=0;i<cReports.length;i++)
			{
				cHelpDropDown[i+1][0]=cReports[i][1];cHelpDropDown[i+1][1]=cReports[i][0];
			}
			this.oSelectQuerys = new  MyE2_SelectField(cHelpDropDown,"",false);
			this.oSelectQuerys.add_oActionAgent(new ownActionAgentStartReportParameterEingabeSeite());
			this.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Bitte wählen Sie die Liste ...")),this.oSelectQuerys,E2_INSETS.I_0_0_10_0), E2_INSETS.I_2_2_2_2);
		}
		
		this.oRowForStartReport = new E2_ExpandableRow(     new MyE2_String("Reportinformationen geschlossen ..."),
															new Border(1,Color.LIGHTGRAY,Border.STYLE_SOLID),
															new Border(1,Color.LIGHTGRAY ,Border.STYLE_SOLID),
															new E2_ColorLight());

		this.oRowForShowReport = new E2_ExpandableRow(     new MyE2_String("Reportergebnis geschlossen ..."),
															new Border(1,Color.LIGHTGRAY,Border.STYLE_SOLID),
															new Border(1,Color.LIGHTGRAY,Border.STYLE_SOLID));

		// parameter-eingabe-block
		this.oColForStartReport = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		this.oRowForStartReport.add(this.oColForStartReport,E2_INSETS.I_0_0_0_0);

		// ergebnis-anzeige-block
		this.oColForShowReport = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		this.oRowForShowReport.add(this.oColForShowReport,E2_INSETS.I_0_0_0_0);

		
		this.add(this.oRowForStartReport, E2_INSETS.I_2_2_2_2);
		this.add(this.oRowForShowReport, E2_INSETS.I_2_2_2_2);

		
		/*
		 * wenn die id des reports uebergeben wurde, dann kann jetzt gleich die report-vormaske angezeigt werden
		 */
		if (!bibALL.isEmpty(cID_REPORT))
		{
			this.StartReportVormaske(cID_REPORT);
		}
		
		
//		
//		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
//		{
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
//			{
//				// aus den reports temporaere tabellen loeschen
//				if (!bibALL.isEmpty(REPORT_MODULE_CONTAINER.this.get_cLast_TempTableID()))
//					REPORT_MODULE_CONTAINER.this.Aufraeumen();
//			}
//		});

		
		//this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(500),new MyE2_String("Globale Reports und Auswertungen ..."));
		
	}
	
	
	private void StartReportVormaske(String cID_REPORT) throws myException
	{
		this.vParamFields.removeAllElements();
		
		
		MyE2_Column oColForReport = this.oColForStartReport;
		
		
		this.Aufraeumen();
		
		// die liste (falls eine vorhanden war) rausschmeissen
		this.oColForShowReport.removeAll();
		if (this.oRowForShowReport.get_bOpen())
		{
			this.oRowForShowReport.get_oButtonClose().doActionPassiv();
		}
		
		
		// falls die selektionsmaske geschlossen war, die wieder aufmachen
		if (!this.oRowForStartReport.get_bOpen())
		{
			this.oRowForStartReport.get_oButtonOpen().doActionPassiv();
		}

		
		if (bibALL.isEmpty(cID_REPORT))    // leere selektion auch moeglich
			return;
		
		MyDataRecordHashMap oHMReport = new MyDataRecordHashMap("SELECT * FROM "+bibE2.cTO()+".JT_QUERY WHERE ID_QUERY="+cID_REPORT); 
		
		oColForReport.removeAll();
		oColForReport.add(new MyE2_Label(oHMReport.get_FormatedValue_LeerWennNull("NAME"),MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()), E2_INSETS.I_2_2_2_0);
		
		
		// ueberschrift
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB1")))
			oColForReport.add(new MyE2_Label(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB1")), E2_INSETS.I_2_2_2_0);
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB2")))
			oColForReport.add(new MyE2_Label(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB2")), E2_INSETS.I_2_2_2_0);
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB3")))
			oColForReport.add(new MyE2_Label(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB3")), E2_INSETS.I_2_2_2_0);
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB4")))
			oColForReport.add(new MyE2_Label(oHMReport.get_FormatedValue_LeerWennNull("BESCHREIB4")), E2_INSETS.I_2_2_2_0);
		
		
		
		
		MyE2_Grid oGridForParam = new MyE2_Grid(2,0);
		this.Bearbeite_und_fuege_parameter_ein("1", oHMReport, oGridForParam, this.vParamFields);
		this.Bearbeite_und_fuege_parameter_ein("2", oHMReport, oGridForParam, this.vParamFields);
		this.Bearbeite_und_fuege_parameter_ein("3", oHMReport, oGridForParam, this.vParamFields);
		this.Bearbeite_und_fuege_parameter_ein("4", oHMReport, oGridForParam, this.vParamFields);
		this.Bearbeite_und_fuege_parameter_ein("5", oHMReport, oGridForParam, this.vParamFields);
		this.Bearbeite_und_fuege_parameter_ein("6", oHMReport, oGridForParam, this.vParamFields);
		
		if (this.vParamFields.size()>0)
			oColForReport.add(oGridForParam, E2_INSETS.I_2_20_2_0);
		
		// jetzt den ReportButton definieren mit der kompletten Abfrage (parametriert #1# usw)
		
		
		this.oButtonStartReport = new MyE2_Button(new MyE2_String("Starte Abfrage "));
		
		// fuer die temporaere Zieltabelle ist ein ID_TEMPTABLE_XXXXXXXX von noeten, sowie die felder 
		String cHelpFieldsBlock = oHMReport.get_FormatedValue_LeerWennNull("SQLFELDLISTE");
		String cNextSequenceReportNumber = bibDB.EinzelAbfrage("SELECT SEQ_"+bibALL.get_ID_MANDANT()+"_TEMPREPORTTABLE.NEXTVAL FROM DUAL");


		/*
		 * der fieldsblock wird um bis zu 4 spalten erweitert (3 Standardspalten muessen vorhanden sein (ID... , GEAENDERT_VON, LETZTE_AENDERUNG)
		 * dann kommt noch eine spalte mit der gewuenschten sortiertinformation dazu, die spaeter in der ID... abgebildet wird
		 */
		if (cHelpFieldsBlock.indexOf("GEAENDERT_VON")<0)
			cHelpFieldsBlock += ","+bibALL.MakeSql(bibALL.get_KUERZEL())+" AS GEAENDERT_VON";
		
		if (cHelpFieldsBlock.indexOf("LETZTE_AENDERUNG")<0)
			cHelpFieldsBlock += ",SYSDATE AS LETZTE_AENDERUNG";
		
		cHelpFieldsBlock += ",1000000000 AS "+REPORT_MODULE_CONTAINER.get_NAME_INDEX_TEMP(cNextSequenceReportNumber);
		
		//jetzt noch eine ORDERSPALTE zufuegen. damit kann spaeter ein ID_xxx nach der sequence nachgebildet werden
		// da ein sequencer in der create table as select ... anweisung nicht funktioniert, wenn die select-auswahl sortiert wird
		boolean bHasOrderBlock = false;
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("SQLORDERBLOCK")))
		{
			cHelpFieldsBlock += ","+bibALL.ReplaceTeilString(oHMReport.get_FormatedValue_LeerWennNull("SQLORDERBLOCK"),",","||")+" AS "+REPORT_MODULE_CONTAINER.get_NAME_ORDER_TEMP(cNextSequenceReportNumber);   // komma wird durch || concatenate-operator ersetzt
			bHasOrderBlock = true;
		}
		
		
		
		Vector<String> vSQL = new Vector<String>();
		vSQL.add(DB_META.get_SequenceBuilder(bibE2.get_DB_KENNUNG(), bibE2.cTO(), REPORT_MODULE_CONTAINER.get_NAME_SEQ_TEMP(cNextSequenceReportNumber), "1000"));
		
		String cSQLQuery = "SELECT "+cHelpFieldsBlock;
		cSQLQuery += " FROM "+oHMReport.get_FormatedValue_LeerWennNull("SQLFROMBLOCK");
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("SQLWHEREBLOCK")))
				cSQLQuery += " WHERE "+oHMReport.get_FormatedValue_LeerWennNull("SQLWHEREBLOCK");


		//befehl zum aufbau der ergebnistabelle einfuegen
		vSQL.add("CREATE TABLE " +  REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(cNextSequenceReportNumber)+" AS "+cSQLQuery);
		if (bHasOrderBlock)
		{
			// evtl. leere order-eintraege fuellen, damit diese nicht fehlen in der liste, da ja nach der order-spalter das indexfeld gefuellt wird
			vSQL.add("UPDATE " +  REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(cNextSequenceReportNumber)+" SET  "+
																	REPORT_MODULE_CONTAINER.get_NAME_ORDER_TEMP(cNextSequenceReportNumber)+"='AAA' WHERE "+
																	REPORT_MODULE_CONTAINER.get_NAME_ORDER_TEMP(cNextSequenceReportNumber)+" IS NULL ");
		}
		
		// den SQL-Stack und die Nummer der Tabelle an den button weitergeben
		this.oButtonStartReport.EXT().set_C_MERKMAL(cNextSequenceReportNumber);
		if (oHMReport.get_UnFormatedValue_LeerWennNull("DOWNLOAD").equals("Y"))
		{
			String cDownloadname = oHMReport.get_FormatedValue_LeerWennNull("NAME");
			
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"                ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"               ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"              ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"             ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"            ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"           ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"          ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"         ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"        ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"       ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"      ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"     ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"    ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"   ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname,"  ","_");
			cDownloadname = bibALL.ReplaceTeilString(cDownloadname," ","_");
			
			this.oButtonStartReport.EXT().set_C_MERKMAL2(cDownloadname);
		}
		
		this.oButtonStartReport.EXT().set_O_PLACE_FOR_EVERYTHING(vSQL);
		this.oButtonStartReport.EXT().set_O_PLACE_FOR_EVERYTHING2(oHMReport);
		this.oButtonStartReport.add_oActionAgent(new ownActionAgentStartReport());
		
		oColForReport.add(new E2_ComponentGroupHorizontal(0,this.oButtonStartReport,E2_INSETS.I_0_0_0_0), E2_INSETS.I_2_20_2_0);

	}
	
	
	
	
	private void Bearbeite_und_fuege_parameter_ein(String cPARAMNR, MyDataRecordHashMap oHMReport, MyE2_Grid oGridForParam, Vector<MyE2IF__Component> vVectorForFields) throws myException
	{
		if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("PARAM0"+cPARAMNR)))
		{
			// es wird entweder ein textfield oder ein selectfield aufgebaut und angezeigt	
			MyE2IF__Component oComponent = null;
			
			if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("PARAMDROPDOWNDEF0"+cPARAMNR)))
			{
				// dropdown-def
				oComponent = REP_CreateDropDownForParameters.build_oSelectFieldUserInput(
						oHMReport.get_FormatedValue_LeerWennNull("PARAMDROPDOWNDEF0"+cPARAMNR),
						oHMReport.get_FormatedValue_LeerWennNull("DEFAULT0"+cPARAMNR));
				
			}
			else
			{
				oComponent = new MyE2_TextField("",200,100);
				((MyE2_TextField)oComponent).setText(oHMReport.get_FormatedValue_LeerWennNull("DEFAULT0"+cPARAMNR));
			}
			
			oComponent.EXT().set_C_MERKMAL("#"+cPARAMNR+"#");
			vVectorForFields.add(oComponent);
			oGridForParam.add(new MyE2_Label(oHMReport.get_FormatedValue_LeerWennNull("PARAM0"+cPARAMNR)), E2_INSETS.I_0_0_20_0);
			oGridForParam.add((Component)oComponent, E2_INSETS.I_0_0_20_0);
		}
	}

	
	
	public void finalize()
	{
		this.Aufraeumen();
	}
	
	
	
	private void Aufraeumen()
	{
		if (!bibALL.isEmpty(this.cLast_TempTableID))
		{
			//aufraeumen (nicht im vSQL_NEU - Vector weil evtl. nicht vorhandene objekte das Drop-statement als falsch returnen
			bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"DROP SEQUENCE "+REPORT_MODULE_CONTAINER.get_NAME_SEQ_TEMP(this.cLast_TempTableID),true);
			bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"DROP TABLE "+REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(this.cLast_TempTableID),true);
		}
		
	}
	
	
	/*
	 * aufbau der Report-Maske mit Anzeiger der Überschriften und Eingabe-Feldern
	 */
	private class ownActionAgentStartReportParameterEingabeSeite extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			REPORT_MODULE_CONTAINER oThis = REPORT_MODULE_CONTAINER.this;
			String cID_REPORT = oThis.oSelectQuerys.get_ActualWert();

			oThis.StartReportVormaske(cID_REPORT);
		}
	}
	


	
	/*
	 * fuehrt den SQL-Vector aus und baut die ergbnistabelle auf
	 */
	private class ownActionAgentStartReport extends XX_ActionAgent
	{

		@SuppressWarnings("unchecked")
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			REPORT_MODULE_CONTAINER oThis = REPORT_MODULE_CONTAINER.this;


			oThis.Aufraeumen();
			
			
			MyE2_Button oButtonStart = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			Vector<String> vSQL = (Vector<String>)oButtonStart.EXT().get_O_PLACE_FOR_EVERYTHING();
			oThis.cLast_TempTableID = oButtonStart.EXT().get_C_MERKMAL(); 

			
			MyDataRecordHashMap oHMReport = (MyDataRecordHashMap)oButtonStart.EXT().get_O_PLACE_FOR_EVERYTHING2();

			boolean bHasOrderBlock = false;
			if (!bibALL.isEmpty(oHMReport.get_FormatedValue_LeerWennNull("SQLORDERBLOCK")))
			{
				bHasOrderBlock = true;
			}

			
			// falls excel-downloads erlaubt sind, dann steht hier der name
			String cDownloadName = bibALL.null2leer(oButtonStart.EXT().get_C_MERKMAL2());
			
			
			boolean bSQL_OK = true;
			
			/*
			 * jetzt die parameter #1# bis #6# austauschen
			 */
			for (int k=0;k<vSQL.size();k++)
			{
				String cSQLQuery = (String)vSQL.get(k);
				for (int i=0;i<oThis.vParamFields.size();i++)
				{
					// die komponenten koennen ein Textfield oder ein SelectField sein
					if (oThis.vParamFields.get(i) instanceof MyE2_TextField)
					{
						MyE2_TextField oTF = (MyE2_TextField)oThis.vParamFields.get(i);
						cSQLQuery = bibALL.ReplaceTeilString(cSQLQuery, oTF.EXT().get_C_MERKMAL(), oTF.getText().trim());
					}
					else
					{
						MyE2_SelectField oTF = (MyE2_SelectField)oThis.vParamFields.get(i);
						cSQLQuery = bibALL.ReplaceTeilString(cSQLQuery, oTF.EXT().get_C_MERKMAL(), oTF.get_ActualWert());
					}
				}
				
				if (!bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLQuery, true))
				{
					bSQL_OK = false;
					if (k==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufbau der Sequence ... "), false);
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cSQLQuery,false));
					}
					else if (k==1)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufbau der Tabelle ... "), false);
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cSQLQuery,false));
					}
				}
				
			}
			
			
			if (bSQL_OK)
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Abfrage war erfolgreich ...")), false);
				
				String cQueryROWID = "SELECT ROWID FROM "+REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(oThis.cLast_TempTableID);
				
				//jetzt sortieren (in die id-spalte den sequencer-wert eintragen
				if (bHasOrderBlock)
				{
					cQueryROWID += " ORDER BY "+REPORT_MODULE_CONTAINER.get_NAME_ORDER_TEMP(oThis.cLast_TempTableID);
				}
					
				String[][] cHelp = bibDB.EinzelAbfrageInArray(cQueryROWID);

				if (cHelp==null)
					throw new myException(this,"Error building table-sorting !!!");
					
				
				//jetzt sortieren (in die id-spalte den sequencer-wert eintragen
				for (int i=0;i<cHelp.length;i++)
				{
					String cSQL = "UPDATE "+REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(oThis.cLast_TempTableID)+
									" SET "+REPORT_MODULE_CONTAINER.get_NAME_INDEX_TEMP(oThis.cLast_TempTableID)+
									"="+REPORT_MODULE_CONTAINER.get_NAME_SEQ_TEMP(oThis.cLast_TempTableID)+".NEXTVAL " +
									" WHERE ROWID='"+cHelp[i][0]+"'";
					
					if (!bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQL, true))
					{
						throw new myException(this," cannot update Index-fields !");
					}
						
				}
				// falls der selectionsbereich ofen ist, dann schliessen
				if (oThis.oRowForStartReport.get_bOpen())
					oThis.oRowForStartReport.get_oButtonClose().doActionPassiv();

				// dann (falls vorhanden) die sortierspalte wieder schlachten
				if (bHasOrderBlock)
				{
					String cSQLDrop = "ALTER TABLE "+REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(oThis.cLast_TempTableID)+
										" DROP COLUMN "+REPORT_MODULE_CONTAINER.get_NAME_ORDER_TEMP(oThis.cLast_TempTableID);
					bibDB.ExecSQL(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLDrop, true);
				}

				
				
				//jetzt die uebergebenen Spaltentitel (wenn vorhanden) einbauen
				String cSpaltenListe = oHMReport.get_FormatedValue_LeerWennNull("LISTE_TITEL");
				Vector<MyString> vTitle = new Vector<MyString>();
				if (!bibALL.isEmpty(cSpaltenListe))
				{
					Vector<String> vHelp = bibALL.TrenneZeile(cSpaltenListe, "|");

					// die zwei sonderspalten muss der benutzer nicht eingeben (Checkbox und marker)
					vTitle.add(new MyE2_String("?"));
					vTitle.add(new MyE2_String("?"));
					
					for (int i=0;i<vHelp.size();i++)
					{
						vTitle.add(new MyE2_String((String)vHelp.get(i)));
					}
				}

				
				
				// jetzt ein E2_ModuleContainerLIST_XML - container-objekt bauen
				E2_ModuleContainerLIST_XML oList = new E2_ModuleContainerLIST_XML(	
														new Project_TableNamingAgent(),
														REPORT_MODULE_CONTAINER.get_NAME_TABLE_TEMP(oThis.cLast_TempTableID),
														false,
														false, 
														false, 
														vTitle, 
														cDownloadName);
				
				oThis.oColForShowReport.removeAll();
				oList.set_bVisible_Row_For_Messages(false);
				oThis.oColForShowReport.add(oList, E2_INSETS.I_0_0_0_0);
				
				// den bereich mit der liste oeffnen
				if (!oThis.oRowForShowReport.get_bOpen())
					oThis.oRowForShowReport.get_oButtonOpen().doActionPassiv();
				
				
			}
			else
			{
				oThis.Aufraeumen();
			}
			
		}
		
	}
	
	public String get_cLast_TempTableID() 
	{
		return this.cLast_TempTableID;
	}
	
	
	public static String get_NAME_TABLE_TEMP(String cSeqTabNummer) 	{	return "JD_"+myCONST.KERN_NAME_OF_REPORT_TEMP_TABLES+"_"+cSeqTabNummer;	}
	public static String get_NAME_INDEX_TEMP(String cSeqTabNummer) 	{	return "ID_"+myCONST.KERN_NAME_OF_REPORT_TEMP_TABLES+"_"+cSeqTabNummer;	}
	public static String get_NAME_SEQ_TEMP(String cSeqTabNummer) 	{	return "SEQ_"+myCONST.KERN_NAME_OF_REPORT_TEMP_TABLES+"_"+cSeqTabNummer;	}
	public static String get_NAME_ORDER_TEMP(String cSeqTabNummer) 	{	return "ORDER_"+myCONST.KERN_NAME_OF_REPORT_TEMP_TABLES+"_"+cSeqTabNummer;	}
	
}
