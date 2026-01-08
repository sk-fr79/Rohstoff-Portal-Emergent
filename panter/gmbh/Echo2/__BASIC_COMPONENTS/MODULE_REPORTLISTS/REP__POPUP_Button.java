package panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS;

import java.util.Hashtable;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_PopUp;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
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
public class REP__POPUP_Button extends MyE2_Column {
	
	private E2_PopUp 						oPopUP_Listen = new E2_PopUp()	._set_icon_activ(E2_ResourceIcon.get_RI("listen.png"))
																			._set_icon_inactiv(E2_ResourceIcon.get_RI("listen__.png"));
	
	private E2_NavigationList       		oNaviList = null;
	private E2_SelectionComponentsVector  	oSelVector = null;
	
	
	/**
	 * @param cMODULKENNER
	 * @param NavigationList
	 * @throws myException
	 */
	public REP__POPUP_Button(String 				cMODULKENNER, 	
							 E2_NavigationList  	NavigationList) throws myException	{
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
			if (new RECORD_MANDANT(bibSES.get_ID_MANDANT_UF()).is_SHOW_IDS_ON_REPORT_LABELS_YES()) {
//				this.oPopUP_Listen._set_inner_size(200,80);
				this.oPopUP_Listen._set_number_cols(2);
				
				for (int i=0;i<oReclistReport.size();i++) {
					RECORD_REPORT RecReport = oReclistReport.get(i);
					
					this.oPopUP_Listen._add_line(	new ownReportButton(RecReport), 						  new RB_gld()._ins(2,0,2,0)._left_top(), 
													new RB_lab()._t("(ID: "+ RecReport.fs(REPORT.id_report, "")+")")._i(), new RB_gld()._ins(5,0,2,0)._right_top());
				}

			} else {

				// this.oPopUP_Listen._set_inner_size(200);
				this.oPopUP_Listen._set_number_cols(1);
				for (int i=0;i<oReclistReport.size();i++) {
					RECORD_REPORT RecReport = oReclistReport.get(i);
					
					this.oPopUP_Listen._add_line(	new ownReportButton(RecReport), 						new RB_gld()._ins(2,0,2,0)._left_top());
				}

			}
			
			if (oReclistReport.size()>8) {
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
	public REP__POPUP_Button setSelectionComponentsVector(E2_SelectionComponentsVector oSelectionVector){
		this.oSelVector = oSelectionVector;
		return this;
	}
	
	
	
	
	private class ownReportButton extends MyE2_Button
	{
		private RECORD_REPORT        recReport = null;

		public ownReportButton(RECORD_REPORT RecReport) throws myException
		{
			
			super();
			
			this.setLineWrap(false);
			this.setFont(new E2_FontPlain(0));
			
			this.setText(RecReport.get_BUTTONTEXT_cF_NN("-"));
			
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
						if (REP__POPUP_Button.this.oSelVector != null){
							htParams = REP__POPUP_Button.this.oSelVector.get_ValueTable();
						}
						
						new REP_WindowPane_START(	ownReportButton.this.recReport,
													REP__POPUP_Button.this.oNaviList,
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
