package panter.gmbh.Echo2.__CONTAINER_ADDONS.GLOBAL_REPORTS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.AW_RohstoffAuswertungen;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2_Auswertung;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2__BasicContainer_RohstoffAuswertungen;

public class REPORT__BUTTON_StartReports extends MyE2_Button implements IF_BasicModuleContainer_ADD_ON_Component
{
	private String 						cMODULKENNER = null; 
	private E2_BasicModuleContainer 	oContainerCalling = null;
	
	
	/*
	 * hier koennen z.b. Masken-Tochterkomponenten benannt werden, die eine Druckdefinition bekommen koennen
	 */
	private Vector<String>  vZusatzModuleKenner = new Vector<String>();
	
	
	public REPORT__BUTTON_StartReports(E2_BasicModuleContainer  ocontainer)
	{
		super(E2_ResourceIcon.get_RI("userreports.png"));
		this.oContainerCalling = ocontainer;
		
		this.set_cMODULKENNER(this.oContainerCalling.get_MODUL_IDENTIFIER());
		
		this.add_oActionAgent(new ownActionAgentStartMASK());
		
		MyE2_String oStringToolTips = new MyE2_String("Auswertungen starten ... ");
		oStringToolTips.addUnTranslated(cMODULKENNER);
		this.setToolTipText(oStringToolTips.CTrans());
	}

	
	
	/**
	 * @throws myException 
	 * 
	 */
	public boolean get_bIsShown() throws myException
	{
		return bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_GLOBAL_REPORTS_YES();
	}

	public void set_cMODULKENNER(String cModulKenner)
	{
		this.cMODULKENNER = cModulKenner;
	}

	public String get_cMODULKENNER()
	{
		return this.cMODULKENNER;
	}

	
	/*
	 * actionagent, startet die maske
	 */
	private class ownActionAgentStartMASK extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			if (REPORT__BUTTON_StartReports.this.cMODULKENNER.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_ROHSTOFFAUSWERTUNGEN.get_callKey()))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mehrfachaufruf ist nicht möglich !!")));
				return;
			}
			
			//new REPORT_MODULE_CONTAINER(null);
			if (!bib_Settigs_Mandant.is_DIRECTACCESS_REPORT_LIST_IS_NEW_MODULE()) {
				new AW_RohstoffAuswertungen().CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Auswertungen"));
			} else {
				new AW2__BasicContainer_RohstoffAuswertungen().CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Auswertungen"));
			}
		}
	}


	public void add_cZusatzMODULKENNER(String cModulKenner)
	{
		this.vZusatzModuleKenner.add(cModulKenner);
	}

}
