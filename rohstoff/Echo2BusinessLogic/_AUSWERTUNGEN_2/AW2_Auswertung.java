package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
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

public class AW2_Auswertung extends XX_Auswertungen {
  
	private RECORD_REPORT  recReport = 		null;
	private boolean        b_external = 	false;
	private boolean        b_basisGruppe = 	false;
	
	public AW2_Auswertung(RECORD_REPORT RecReport, MyE2_String zusatz, boolean p_external, boolean basisGruppe) throws myException
	{
		//
		super(	new MyE2_String(RecReport.get_BUTTONTEXT_cF_NN("<Knopftext>"),true), 
				new MyE2_String(RecReport.get_BESCHREIBUNG_cUF_NN("<Beschreibung>")), 
				RecReport.get_BUTTON_AUTH_KENNER_cUF_NN(""), 
				null,
				RecReport.get_NAME_OF_REPORTFILE_cUF(),
				RecReport.get_ID_REPORT_cUF());
		
		this.b_external = p_external;
		this.recReport = RecReport;
		this.b_basisGruppe = basisGruppe;
	} 

	public AW2_Auswertung set_gruppe(String gruppe) {
		this.set_cAuswerteGruppe(gruppe);
		return this;
	}
	
	
	@Override
	public MyE2_Button get_StartButton() throws myException {
		return new ownReportButton();
	}

	
	public MyE2_Button get_ListButton() throws myException {
		//in der uebersicht ueber alle buttons wird die buttonbeschriftung angezeigt, in den verteilten die Titel
		String lbl = this.b_basisGruppe?
				this.recReport.get_BUTTONTEXT_cUF_NN(""):
				this.recReport.get_TITEL_cUF_NN(this.recReport.get_BUTTONTEXT_cUF());
				
		
		
		AW2_bt_4_ReportList oButtonList = new AW2_bt_4_ReportList(lbl
				,this.recReport
				);
		oButtonList.setToolTipText(this.get_ToolTip());
		
		oButtonList.add_GlobalAUTHValidator_AUTO(this.get_cALLOW_FLAG());
		oButtonList.setLineWrap(true);

		return oButtonList;
	}

	
	private class ownReportButton extends MyE2_Button
	{
		public ownReportButton() throws myException {
			super(AW2_Auswertung.this.b_basisGruppe?
					new MyE2_String("Starte: "+AW2_Auswertung.this.recReport.get_BUTTONTEXT_cF_NN("<Knopftext>")):
					new MyE2_String("Starte: "+AW2_Auswertung.this.recReport.get_TITEL_cF_NN("<Knopftext>")));
					
			this.setStyle(MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
			this.setLineWrap(true);
			
			this.setToolTipText(AW2_Auswertung.this.get_ToolTip());
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					try
					{
						//hier nochmals den recreport einlese, bevor gestartet wird
						AW2_Auswertung.this.recReport.REBUILD();
						
						new REP_WindowPane_START(	AW2_Auswertung.this.recReport,
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
		vRueck.add(new E2_ButtonAUTHValidator(recReport.get_MODULE_KENNER_cUF(),recReport.get_BUTTON_AUTH_KENNER_cUF()));

		if(recReport.is_SUPERVISOR_YES()) {
			vRueck.add(new E2_ButtonAUTH_ONLY_ADMIN());
		}
		
		if(recReport.is_GESCHAEFTSFUEHRER_YES()) {
			vRueck.add(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER());
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
		RECORD_REPORT recRep = AW2_Auswertung.this.recReport;
		MyE2_String cToolTips = new MyE2_String(recRep.get_BESCHREIBUNG_cUF_NN("<keine Beschreibung>"),false," ---> ",false,
												recRep.get_NAME_OF_REPORTFILE_cUF_NN("<kein Reportfile>"),false);

		return cToolTips.CTrans();
	}

	public boolean is_external() {
		return this.b_external;
	}
	
}
