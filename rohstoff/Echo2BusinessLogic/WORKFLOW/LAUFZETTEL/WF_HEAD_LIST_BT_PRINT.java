package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import javax.print.DocFlavor.STRING;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_HIERARCHY;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_HIERARCHY_Settings;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_ROW_OBJECT;



public class WF_HEAD_LIST_BT_PRINT extends MyE2_Button
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5290486144842118909L;
	
	private EnumPrintOptions m_PrintOptions = EnumPrintOptions.PRINT_SELECTED;
	

	public WF_HEAD_LIST_BT_PRINT(	E2_NavigationList onavigationList, 
									E2_BasicModuleContainer_MASK omaskContainer, 
									MyE2_String caption,
									EnumPrintOptions printOptions
									)
	{
		super(caption);
		m_PrintOptions = printOptions;
		
		this.add_oActionAgent(new printActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"DRUCKE_WF_HEAD"));
	
	}

/***
 * Eventhandler zum Drucken der Reports	
 * @author manfred
 * @date 04.12.2008
 */
private class printActionAgent extends XX_ActionAgent
{
	private E2_NavigationList oNavigationList = null;


	WF_LIST_EXPANDER_HIERARCHY_Settings oSettings = new WF_LIST_EXPANDER_HIERARCHY_Settings();
	
	/***
	 * Übernehmen der Parameter in den ActionAgent
	 * 
	 * @param oNaviList
	 ***/
	public printActionAgent(E2_NavigationList oNaviList)
	{
		oNavigationList = oNaviList;
	}
	
	
	
	
	@Override
	public void executeAgentCode(ExecINFO execInfo) throws myException
	{
		Vector<String> vIDs =  new Vector<String>();
//		sIDUserToShow = null;

		
		
		switch (m_PrintOptions)
		{
			case PRINT_SELECTED:
				vIDs.addAll(oNavigationList.get_vSelectedIDs_Unformated());
				break;
			case PRINT_CURRENT_PAGE:
				vIDs.addAll(oNavigationList.get_AllVisibleIDs_Unformated());
				break;

			default:
				vIDs.addAll(oNavigationList.get_vectorSegmentation());
				break;
		}
		
		
		
				
		// prüfen, welche Einträge man drucken soll.. (SqlStatement generieren abhängig von den Schaltern)
		E2_RecursiveSearchParent_BasicModuleContainer oSearch = 
			new E2_RecursiveSearchParent_BasicModuleContainer(this.oNavigationList);
		
		E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();
		
		E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(
				oContainerList, bibALL.get_Vector(WF_CheckBoxOption.class.getName()), null);
		
		Vector<Component> vResult = oSearchComps.get_vAllComponents();
		for (Component cb : vResult)
		{
			WF_CheckBoxOption cbOptions = (WF_CheckBoxOption) cb;
			oSettings.setValue(cbOptions);
		}
		
		
		// suchen nach der Zeitraum-Combobox
		String sZeitraum = null;
		oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WF_HEAD_LIST_SelectField_Zeitraum.class.getName()), null);
		vResult = oSearchComps.get_vAllComponents();
		if (vResult.size() > 0){
			WF_HEAD_LIST_SelectField_Zeitraum oSelect = (WF_HEAD_LIST_SelectField_Zeitraum)vResult.get(0);
			sZeitraum = oSelect.get_ActualView();
		}
		
		

		// suchen nach der User-Selektion WF_HEAD_LIST_Selection_User_DropDown
		oSearchComps = new E2_RecursiveSearch_Component(oContainerList, bibALL.get_Vector(WF_HEAD_LIST_Selection_User_DropDown.class.getName()), null);
		vResult = oSearchComps.get_vAllComponents();
		if (vResult.size() > 0){
					WF_HEAD_LIST_Selection_User_DropDown oSelect = (WF_HEAD_LIST_Selection_User_DropDown)vResult.get(0);
					String sID = oSelect.get_ActualWert();
					if (!bibALL.isEmpty(sID)){
						oSettings.set_idOwnUser(sID);
					}
		}
		
		
		String sMitarbeiter = null;
		if (!bibALL.isEmpty(oSettings.get_idOwnUser())){
			RECORD_USER recUser = new RECORD_USER(oSettings.get_idOwnUser());
			sMitarbeiter = recUser.get_VORNAME_cUF() + " " + recUser.get_NAME1_cF();
		}
		
	
		

		// die Report-Tabelle jetzt füllen
		String sReportNummer = this.FillReportTable(vIDs);
		
		// prüfen, ob die nummern richtig geschrieben wurden
		if (sReportNummer == null) 
		{
			throw new myException("Fehler beim Schreiben der Report IDs");
		}
		
		
		E2_JasperHASH_STD jasperHash = new E2_JasperHASH_STD("laufzettel_info",new JasperFileDef_PDF());
		jasperHash.put("REPORTNUMMER",sReportNummer);
		
		jasperHash.put("MITARBEITER", sMitarbeiter);
		jasperHash.put("BETRACHTUNGSZEITRAUM", sZeitraum);
			
		
		
		
		//jasperHash.set_HM_JASPERREPORT_BASE_NAME("laufzettel_info.jasper");
		
		//E2_JasperHandler jasperHandler = new E2_JasperHandler(jasperHash);
		//jasperHandler.CHECK_JASPER_HASH_and_SET_REPORTNAME_WITH_PATH(jasperHash);
		
		//String sDownloadname = "LaufzettelInfo_" + bibALL.get_cDateNOW() + ".pdf";
		
		jasperHash.set_cDownloadAndSendeNameStaticPart("LaufzettelInfo_" + bibALL.get_cDateNOW());
		
		jasperHash.Build_tempFile(false);
		jasperHash.get_oTempFileWithSendeName().Download_File();
		
		//jasperHandler.starteDownloadPDFFile(sDownloadname);
		
		// jetzt die Daten aus der Temporären Reporttabelle löschen
		DeleteReportData(sReportNummer);
		
	}

	
	/***
	 * Löschen der temporären Report-Daten aus der Tabelle JD_REPORTAKTION
	 * @author manfred
	 * @date 22.01.2009
	 * @param sReportNr
	 * @return
	 * @throws myException
	 */
	private boolean DeleteReportData(String sReportNummer)  throws myException
	{
			boolean bFehler = false;
			
			String sSql = "DELETE FROM  "
				+ bibE2.cTO() + ".JD_REPORTAKTION WHERE REPORTNUMMER = " + sReportNummer ;

			if (!bibDB.ExecSQL(sSql, true))
			{
		 		bibMSG.add_MESSAGE(new MyE2_Alarm_Message(
						"Fehler beim Schreiben der Reportliste"));
				bFehler = true;
			}
			return !bFehler;
		
	}
	
	
	
	/***
	 * 
	 * @author manfred
	 * @date 04.12.2008
	 * @param vIDs die in die Reporttabelle geschrieben werden müssen
	 * @return
	 * 	wenn ok, dann die Reportnummern
	 *  wenn fehler, dann null
	 */
	private String FillReportTable(Vector<String> vIDs)  throws myException
	{
			String sReportNummer = bibDB.EinzelAbfrage("SELECT " + bibE2.cTO() + ".SEQ_REPORTNUMMER.NEXTVAL FROM DUAL");
			boolean bFehler = false;
			String sDepth = "                                                       -->";
			
			String sSqlInsertHead = "INSERT INTO "
				+ bibE2.cTO()
				+ ".JD_REPORTAKTION (REPORTNUMMER,ID_REPORTAKTION,ID_TABLE) "
				+ "VALUES (" + sReportNummer
				+ ",SEQ_REPORTAKTION.NEXTVAL,";

			String sSqlInsertPos = "INSERT INTO "
				+ bibE2.cTO()
				+ ".JD_REPORTAKTION (REPORTNUMMER,ID_REPORTAKTION,add1,add2,ID_TABLE) "
				+ "VALUES (" + sReportNummer
				+ ",SEQ_REPORTAKTION.NEXTVAL,";

			
			if (!bibALL.isLong(sReportNummer))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(
						"Ich konnte keine freie Report-Nummer ermitteln !"));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(
						"Sequence: SEQ_REPORTNUMMER !!", false)));
				sReportNummer = null;
			} 
			else
			{
				// alle gewählten Workflows durchlaufen, die Daten lesen und die Einträge lesen.
				for (int i = 0, k = vIDs.size(); i < k; i++)
				{
					// liste der einzelnen Laufzettel-Einträge
					RECLIST_LAUFZETTEL_EINTRAG oLE = new RECLIST_LAUFZETTEL_EINTRAG("SELECT * FROM  " + bibE2.cTO()+ ".JT_LAUFZETTEL_EINTRAG WHERE id_laufzettel = " + (String) vIDs.get(i) + "  ORDER BY ID_LAUFZETTEL_EINTRAG ");
					
					//MAP_LAUFZETTEL_EINTRAG oLE = new MAP_LAUFZETTEL_EINTRAG(" id_laufzettel = " + (String) vIDs.get(i));
					WF_LIST_EXPANDER_HIERARCHY oH = new WF_LIST_EXPANDER_HIERARCHY(oLE);
					oH.set_Settings(oSettings);
					oH.BuildEntryList();

					Vector<WF_LIST_EXPANDER_ROW_OBJECT> oEntries = oH.getVDisplayRows();
					
					// schreiben der id und 
					String cSQL = sSqlInsertHead + (String) vIDs.get(i) + ")";
					if (!bibDB.ExecSQL(cSQL, false))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(
								"Fehler beim Schreiben der Reportliste"));
						bFehler = true;
						break;
					}
					
					if (!oEntries.isEmpty())
					{
						// die Displayrows in die Tabelle aufnehmen
						for (WF_LIST_EXPANDER_ROW_OBJECT oRow :oEntries)
						{
							// schreiben der id und 
							cSQL = sSqlInsertPos + "'" + sDepth.substring(sDepth.length() - (oRow.getDepth()*2)  ) + "' ,"+  oRow.getId_Laufzettel_Eintrag()  + " , " + (String) vIDs.get(i) +  ")";
							if (!bibDB.ExecSQL(cSQL, false))
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(
										"Fehler beim Schreiben der Reportliste"));
								bFehler = true;
								break;
							}

						}
					}
					
					
				}
				

				if (!bFehler)
				{
					bibDB.Commit();
				} else
				{
					bibDB.Rollback();
					sReportNummer = null;
				}

			}

			return sReportNummer;
		}

	}
}
