package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.JASPER_MODUL_LISTEN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Auswertungen.XX_Auswertungen;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP_WindowPane_START;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class Auswertungen_standardReports extends XX_Auswertungen {
  
	private RECORD_REPORT  recReport = null;
	
	public Auswertungen_standardReports(RECORD_REPORT RecReport) throws myException
	{
		super(	new MyE2_String(RecReport.get_BUTTONTEXT_cF_NN("<Knopftext>")), 
				new MyE2_String(RecReport.get_BESCHREIBUNG_cUF_NN("<Beschreibung>")), 
				RecReport.get_BUTTON_AUTH_KENNER_cUF_NN(""), 
				null,
				RecReport.get_NAME_OF_REPORTFILE_cUF(),
				RecReport.get_ID_REPORT_cUF());
		
		this.recReport = RecReport;
		
	} 

	@Override
	public MyE2_Button get_StartButton() throws myException
	{
		return new ownReportButton();
		
	}

	
	private class ownReportButton extends MyE2_Button
	{
		public ownReportButton() throws myException
		{
			super(new MyE2_String("Starte: "+Auswertungen_standardReports.this.recReport.get_BUTTONTEXT_cF_NN("<Knopftext>")));
			
			this.setStyle(MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
			this.setLineWrap(true);
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(recReport.get_MODULE_KENNER_cUF(),recReport.get_BUTTON_AUTH_KENNER_cUF()));
			
			if(recReport.is_SUPERVISOR_YES())
			{
				this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());
			}
			
			if(recReport.is_GESCHAEFTSFUEHRER_YES())
			{
				this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER());
			}
			
			
			this.setToolTipText(Auswertungen_standardReports.this.get_ToolTip());
			
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					try
					{
						//hier nochmals den recreport einlese, bevor gestartet wird
						Auswertungen_standardReports.this.recReport.REBUILD();
						
						new REP_WindowPane_START(	Auswertungen_standardReports.this.recReport,
													null);
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					}
				}
			});
		}
	}


	@Override
	public Vector<XX_ActionValidator> get_GlobalValidators4ListButton() throws myException
	{
		Vector<XX_ActionValidator>  vRueck = new Vector<XX_ActionValidator>();
		
		if(recReport.is_SUPERVISOR_YES())
		{
			vRueck.add(new E2_ButtonAUTH_ONLY_ADMIN());
		}
		
		if(recReport.is_GESCHAEFTSFUEHRER_YES())
		{
			vRueck.add(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER());
		}
		
		return vRueck;
	}

	@Override
	public Component get_Zusatzkomponente() throws myException
	{
		return null;
	}

	@Override
	public String get_ToolTip() throws myException {
		RECORD_REPORT recRep = Auswertungen_standardReports.this.recReport;
		MyE2_String cToolTips = new MyE2_String(recRep.get_BESCHREIBUNG_cUF_NN("<keine Beschreibung>"),false," ---> ",false,
												recRep.get_NAME_OF_REPORTFILE_cUF_NN("<kein Reportfile>"),false);

		return cToolTips.CTrans();
	}

	@Override
	public MyE2_Button get_ListButton() throws myException {
		
		//2016-08-09: fehlerkorrektur id wird mehrfach angehaengt (bei jeder aktion neu) 
		//MyE2_String lbl = this.get_cAuswertungsNamen();
		MyE2_String lbl = new MyE2_String(this.get_cAuswertungsNamen().CTrans(),false);
		
		
		MyE2_Button oButtonList = new MyE2_Button(lbl);
		oButtonList.setToolTipText(this.get_ToolTip());
		
		oButtonList.add_GlobalAUTHValidator_AUTO(this.get_cALLOW_FLAG());
		oButtonList.setLineWrap(true);

		if(new RECORD_MANDANT(bibSES.get_ID_MANDANT_UF()).is_SHOW_IDS_ON_REPORT_LABELS_YES()){
			oButtonList.EXT().set_C_MERKMAL(new String(" (ID: " + this.get_id_report() + ")"));
		}

		return oButtonList;
	}
	
}
