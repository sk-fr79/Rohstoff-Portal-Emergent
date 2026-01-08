package panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS;

import java.util.Hashtable;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


/*
 * klasse mit einem gekapselten popup-menue, das reportdefinitionen
 * enthaelt. Sind keine vorhanden, dann ist das container-element unsichtbar
 */
public class REP__POPUP_Button_old extends MyE2_Column
{
	
	private MyE2_PopUpMenue 		oPopUP_Listen = new MyE2_PopUpMenue(
			E2_ResourceIcon.get_RI("listen.png"), E2_ResourceIcon.get_RI("listen__.png"), 
			false); 
	
	private E2_NavigationList       		oNaviList = null;
	private E2_SelectionComponentsVector  	oSelVector = null;
	
	
	/**
	 * @param cMODULKENNER
	 * @param NavigationList
	 * @throws myException
	 */
	public REP__POPUP_Button_old(	String 				cMODULKENNER, 	
								E2_NavigationList 	NavigationList) throws myException
	{
		super();
		
		if (bibALL.isEmpty(cMODULKENNER) || NavigationList==null )
			throw new myException("POPUP_ReportLists:Constructor:empty parameters not allowed !!");

		this.oNaviList = NavigationList; 
		
		
		//07.07.2016 - Sebastien : Refaktoring von die SQL Abfrage
		SEL reportListQuery = new SEL()
				.FROM(_TAB.report)
				.WHERE(new vgl(REPORT.module_kenner, cMODULKENNER))	
				.AND(new vgl(REPORT.id_mandant, bibSES.get_ID_MANDANT_UF()))
				.AND(new vgl(REPORT.aktiv, "Y"))
				.ORDERUP(REPORT.buttontext);
			
		RECLIST_REPORT  oReclistReport = new RECLIST_REPORT(reportListQuery.s());
		
		if (oReclistReport.get_vKeyValues().size()==0)
		{
			this.setVisible(false);   // das column-object wird unsichtbar
		}
		else
		{
			this.add(this.oPopUP_Listen, new Insets(1));  // den popup-button in die column einblenden
			
			// die REP_LISTDEF-hashmaps aufbauen
			for (int i=0;i<oReclistReport.size();i++)
			{
				this.oPopUP_Listen.addButton(new ownReportButton(oReclistReport.get(i)),true);
			}
			
			if (oReclistReport.size()>8)
			{
				this.oPopUP_Listen.setPopUpTopOffset(20*(oReclistReport.size()-8));
			}
			
		}
		
		
		//aenderung 2010-11-11: vertikaler offset, weil einige liste zu hoch werden
	}
	

	/**
	 * Selektions-Vektor der Liste setzen, für die weitergabe der im Selektor definierten Parameterliste für den Report
	 * @author manfred
	 * @date 01.06.2016
	 *
	 * @param oSelectionVector
	 */
	public REP__POPUP_Button_old setSelectionComponentsVector(E2_SelectionComponentsVector oSelectionVector){
		this.oSelVector = oSelectionVector;
		return this;
	}
	
	
	
	
	private class ownReportButton extends MyE2_Button
	{
		private RECORD_REPORT        recReport = null;

		public ownReportButton(RECORD_REPORT RecReport) throws myException
		{
			
			super();
			
			if(new RECORD_MANDANT(bibSES.get_ID_MANDANT_UF()).is_SHOW_IDS_ON_REPORT_LABELS_YES()){
				this.setText(RecReport.get_BUTTONTEXT_cF_NN("-") + " (ID: " + RecReport.get_ID_REPORT_cF_NN("-") + ")");
			}else{
				this.setText(RecReport.get_BUTTONTEXT_cF_NN("-"));
			}
			
			this.recReport = RecReport;
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(recReport.get_MODULE_KENNER_cUF(),recReport.get_BUTTON_AUTH_KENNER_cUF()));
			
			if(this.recReport.is_SUPERVISOR_YES())
			{
				this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());
			}
			
			if(this.recReport.is_GESCHAEFTSFUEHRER_YES())
			{
				this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER());
			}
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					try
					{
						//2014-03-27: der report wird vor dem start nochmals eingelesen (damit das modul nicht nochmals gestartet werden muss, wenn ein report startet
						ownReportButton.this.recReport.REBUILD();
						
						// ReportDefaultparameter übergeben falls vorhanden, sonst NULL übergeben
						Hashtable<ENUM_Selector_Report_Params, Object> htParams = null;
						if (REP__POPUP_Button_old.this.oSelVector != null){
							htParams = REP__POPUP_Button_old.this.oSelVector.get_ValueTable();
						}
						
						new REP_WindowPane_START(	ownReportButton.this.recReport,
													REP__POPUP_Button_old.this.oNaviList,
													htParams);
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					}
				}
			});
		}
	}
	
}
