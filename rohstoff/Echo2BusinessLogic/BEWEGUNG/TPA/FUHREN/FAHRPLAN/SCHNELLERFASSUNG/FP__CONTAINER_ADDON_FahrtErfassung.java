package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FP__CONTAINER_ADDON_FahrtErfassung extends MyE2_Button implements IF_BasicModuleContainer_ADD_ON_Component
{
	private String 						cMODULKENNER = null; 
	private E2_BasicModuleContainer 	oContainerCalling = null;
	
	
	/*
	 * hier koennen z.b. Masken-Tochterkomponenten benannt werden, die eine Druckdefinition bekommen koennen
	 */
	private Vector<String>  vZusatzModuleKenner = new Vector<String>();
	
	
	public FP__CONTAINER_ADDON_FahrtErfassung(E2_BasicModuleContainer  ocontainer)
	{
		super(E2_ResourceIcon.get_RI("lkw_button.png"));
		this.oContainerCalling = ocontainer;
		
		this.set_cMODULKENNER(this.oContainerCalling.get_MODUL_IDENTIFIER());
		
		this.add_oActionAgent(new ownActionAgentStartMASK());
		
		MyE2_String oStringToolTips = new MyE2_String("Fahrten erfassen  ... ");
		oStringToolTips.addUnTranslated(cMODULKENNER);
		this.setToolTipText(oStringToolTips.CTrans());
	}

	
	
	/**
	 * @throws myException 
	 * 
	 */
	public boolean get_bIsShown() throws myException
	{
		//auf dem eigenen fenster darf der button nicht vorhanden sein
		if (this.oContainerCalling instanceof FPSE_Container_SCHNELL_Erfassung_Fahrt)
			return false;
		
		
		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES() || bibALL.get_RECORD_USER().is_HAT_FAHRPLAN_BUTTON_YES())
		{
			return bibALL.get_RECORD_MANDANT().is_ZEIGE_MODUL_FAHRPLANERFASSUNG_YES();
		}
		return false;
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
			/*
			 * zuerst pruefen, ob es einen modulkenner gibt, wenn ja dann popup anzeigen, sonst nicht, da bei manchen
			 * zustaenden am anfang der modulkenner erst nach einfuegen des E2_ROHSTOFF_SetButtonAccess erfolgt, und damit
			 * bei der ersten anzeige noch nicht bekannt ist
			 */
			FP__CONTAINER_ADDON_FahrtErfassung oThis = FP__CONTAINER_ADDON_FahrtErfassung.this;
			
			oThis.set_cMODULKENNER(oThis.oContainerCalling.get_MODUL_IDENTIFIER());
			new FPSE_Container_SCHNELL_Erfassung_Fahrt();
		}
		
	}


	public void add_cZusatzMODULKENNER(String cModulKenner)
	{
		this.vZusatzModuleKenner.add(cModulKenner);
	}


	
}
