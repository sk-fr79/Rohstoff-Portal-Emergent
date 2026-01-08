package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MOD_REPORTS_ComponentMAPMASK extends E2_ComponentMAP
{
	// 2016-06-02: Modulkenner des Aufrufers merken
	private String m_MODULE_KENNER_LIST_BELONGS_TO = null;
	
	public MOD_REPORTS_ComponentMAPMASK(String cMODULE_KENNER_LIST_BELONGS_TO) throws myException
	{
		super(new MOD_REPORTS_SQLFieldMapMASK(cMODULE_KENNER_LIST_BELONGS_TO));
		
		m_MODULE_KENNER_LIST_BELONGS_TO = cMODULE_KENNER_LIST_BELONGS_TO;

		SQLFieldMAP oSQLFields = this.get_oSQLFieldMAP();
		
		this.add_Component(new MyE2_DB_TextField(	oSQLFields.get_("TITEL"),true,600), new MyE2_String("Titel"));
		this.add_Component(new MyE2_DB_TextField(	oSQLFields.get_("BUTTONTEXT"),true,600), new MyE2_String("Button-Text"));
		this.add_Component(new MyE2_DB_TextField(	oSQLFields.get_("BUTTON_AUTH_KENNER"),true,600), new MyE2_String("Button-Kenner"));
		this.add_Component(new MyE2_DB_Label(		oSQLFields.get_("ID_REPORT")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_TextArea(	oSQLFields.get_("BESCHREIBUNG"),600,5), new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_TextField(	oSQLFields.get_("NAME_OF_REPORTFILE"),true,600), new MyE2_String("Report-Datei (mit Endung"));

		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("PASS_NO_ID")), new MyE2_String("Keine ID übergeben"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("PASS_SINGLE_ID")), new MyE2_String("Einzelne ID übergeben"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("PASS_MULTI_ID")), new MyE2_String("Mehrfache ID übergeben"));

		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_PDF")), new MyE2_String("PDF-Download erlaubt"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_XLS")), new MyE2_String("XLS-Download erlaubt"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_HTML")), new MyE2_String("HTML-Download erlaubt"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_TXT")), new MyE2_String("TXT-Download erlaubt"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_XML")), new MyE2_String("XML-Download erlaubt"));

		//2014-04-03-27: einstellen bevorzugter typ
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_(_DB.REPORT$PREFER_PDF)), new MyE2_String("PDF bevorzugen"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_(_DB.REPORT$PREFER_XLS)), new MyE2_String("XLS bevorzugen"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_(_DB.REPORT$PREFER_HTML)), new MyE2_String("HTML bevorzugen"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_(_DB.REPORT$PREFER_TXT)), new MyE2_String("TXT bevorzugen"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_(_DB.REPORT$PREFER_XML)), new MyE2_String("XML bevorzugen"));
		
		//2016-07-07: report ist aktiv oder nicht
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_(REPORT.aktiv)), new MyE2_String("Aktiv"));
		
		
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_EMAIL_FREE")), new MyE2_String("Freie eMail-Adressen erlaubt"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_EMAIL_SEARCH_CUSTOMER")), new MyE2_String("eMail Adressen in Firmenstamm suchen"));
		this.add_Component(new MyE2_DB_CheckBox(	oSQLFields.get_("ALLOW_EMAIL_EMPLOYES")), new MyE2_String("eMail aus Mitarbeiterstamm erlaubt"));
		this.add_Component(new MyE2_DB_TextArea(	oSQLFields.get_("STATIC_MAIL_ADRESSES"),400,6), new MyE2_String("Statische eMail-Adressen"));

		
		MyE2_DB_CheckBox  cbOnlySupervisor = 		new MyE2_DB_CheckBox(oSQLFields.get_("SUPERVISOR"),new MyE2_String("Nur Admins"), new MyE2_String("Darf nur von Benutzern ausgeführt werden, die den Eintrag <Supervisor> haben"));
		MyE2_DB_CheckBox  cbOnlyGeschaeftsfuehrer = new MyE2_DB_CheckBox(oSQLFields.get_("GESCHAEFTSFUEHRER"),new MyE2_String("Nur Geschäftsführer"), new MyE2_String("Darf nur von Benutzern ausgeführt werden, die den Eintrag <Geschäftsführer> haben"));
		
		XX_ActionAgent oAgentDummy =new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
			}
		};
		
		cbOnlySupervisor.add_oActionAgent(oAgentDummy);
		cbOnlyGeschaeftsfuehrer.add_oActionAgent(oAgentDummy);
		
		cbOnlySupervisor.add_GlobalValidator(			new E2_ButtonAUTH_ONLY_ADMIN());
		cbOnlyGeschaeftsfuehrer.add_GlobalValidator(	new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER());
		this.add_Component(cbOnlySupervisor, 			new MyE2_String("nur für Admins"));
		this.add_Component(cbOnlyGeschaeftsfuehrer, 	new MyE2_String("nur für Geschäftsführer"));

		
		this.add_Component("DAUGHTER_PARAMETERS",
				new MOD_REPORTS_MASK_DAUGHTER_PARAMETERS(	oSQLFields,
															this),new MyE2_String("Parameter"));


		// ALLOW_PDF wird als vorgabe definiert, immer erlaubt 
		((MyE2_DB_CheckBox)this.get_Comp("ALLOW_PDF")).EXT().set_bDisabledFromBasic(false);

		
		// dafuer sorgen, dass nur ein parameter-uebergabe-typ aktiv ist
		ActionAgent_RadioFunction_CheckBoxList oRadioAgent = new ActionAgent_RadioFunction_CheckBoxList(false);
		oRadioAgent.add_CheckBox((MyE2_DB_CheckBox)this.get_Comp("PASS_NO_ID"));
		oRadioAgent.add_CheckBox((MyE2_DB_CheckBox)this.get_Comp("PASS_SINGLE_ID"));
		oRadioAgent.add_CheckBox((MyE2_DB_CheckBox)this.get_Comp("PASS_MULTI_ID"));
		
		
		
		this.register_Interactiv_settings_validation(_DB.REPORT$ALLOW_PDF, new MOD_REPORTS_MASK_Validator());
	}

	
	
	/**
	 * gibt den Modulkenner der aufrufenden Liste zurück
	 * @author manfred
	 * @date 02.06.2016
	 *
	 * @return
	 */
	public String getModulkennerListBelongsTo(){
		return m_MODULE_KENNER_LIST_BELONGS_TO;
	}
	
}
