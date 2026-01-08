package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainer_ADD_ON_Component;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF.SD_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS.SEL_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.objectstore.SingularSettings.ENUM_SINGULAR_SETTINGS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BUTTON_MODULVERWALTUNG extends MyE2_Button implements IF_BasicModuleContainer_ADD_ON_Component
{
	private String 						cMODULKENNER = null; 
	private E2_BasicModuleContainer 	oContainerCalling = null;
	
	private ownBasicModuleContainer		oE2_BasicModulContainer = new ownBasicModuleContainer();         // zum anzeigen der einstellmoeglichkeiten
	
	
	private MyE2_Column					oColTabbContainer = new MyE2_Column();
	
	/*
	 * hier koennen z.b. Masken-Tochterkomponenten benannt werden, die eine Druckdefinition bekommen koennen
	 */
	private Vector<String>   			vZusatzModuleKenner = new Vector<String>();
	
	
	public BUTTON_MODULVERWALTUNG(E2_BasicModuleContainer  ocontainer )
	{
		super(E2_ResourceIcon.get_RI("users.png"));
		this.oContainerCalling = 			ocontainer;
		
		this.oE2_BasicModulContainer.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_MODULVERWALTUNG);
		this.oE2_BasicModulContainer.add(this.oColTabbContainer);
		
		this.set_cMODULKENNER(this.oContainerCalling.get_MODUL_IDENTIFIER());
		this.add_oActionAgent(new ownActionAgentStartMASK());
		
		MyE2_String oStringToolTips = new MyE2_String("Zugriffe definieren für das Modul ... ");
		oStringToolTips.addUnTranslated(cMODULKENNER);
		this.setToolTipText(oStringToolTips.CTrans());
	}

	
	
	/**
	 * button wird nur angezeigt, wenn es ein administrator ist und ein Modulkenner vorhanden ist
	 * 
	 */
	public boolean get_bIsShown()
	{
		boolean bRueck = false;
		if (bibALL.get_bIST_SUPERVISOR())
			bRueck = true;
		
		return bRueck;
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
			BUTTON_MODULVERWALTUNG oThis = BUTTON_MODULVERWALTUNG.this;
			
			
			//E2_ContentPane oPaneCalling = new E2_RecursiveSearchParent_ContentPane_NUMBER_ONE().get_FoundPane();
			
			oThis.set_cMODULKENNER(oThis.oContainerCalling.get_MODUL_IDENTIFIER());

			if (bibALL.isEmpty(oThis.cMODULKENNER))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Das Modul hat keinen Modulkenner !! Deswegen ist eine Verwaltung nicht möglich !"));
				return;
			}
			
			// als erstes das modul fuer die zugriffsberechtigung einhaengen
			oThis.oColTabbContainer.removeAll();
			MyE2_TabbedPane		oTabbed = new MyE2_TabbedPane(null);
			oTabbed.set_bAutoHeight(true);
			
			oThis.oColTabbContainer.add(oTabbed);
			
			//2015-05-20: falls der container einen range-key aus der zentralen enum besitzt, dann wird der kenner fuer die uservalidierung dort rausgezogen
			oTabbed.add_Tabb(new MyE2_String("Rechte (Haupt)"), new MOD_ZUGRIFF(
					oThis.oContainerCalling.get_rangeKey()==null
					?
					oThis.oContainerCalling.get_MODUL_IDENTIFIER()   //wie vorher
					:
					oThis.oContainerCalling.get_rangeKey().db_val()));   //neu am 2015-05-20
			
			for (int i=0;i<oThis.vZusatzModuleKenner.size();i++)
			{
				oTabbed.add_Tabb(new MyE2_String("Rechte "+(i+1)), 
						new MOD_ZUGRIFF((String)oThis.vZusatzModuleKenner.get(i)));
			}
			
			
			
			oTabbed.add_Tabb(new MyE2_String("Reports (Haupt)"), new MOD_REPORTS(
														oThis.oContainerCalling.get_MODUL_IDENTIFIER()));
			
			for (int i=0;i<oThis.vZusatzModuleKenner.size();i++)
			{
				oTabbed.add_Tabb(new MyE2_String("Rep. "+(i+1)), new MOD_REPORTS(
						(String)oThis.vZusatzModuleKenner.get(i)));
			}
			
			oTabbed.add_Tabb(new MyE2_String("Sammelgruppen"), new MOD_GROUPS(oThis.oContainerCalling));
			
			
			oTabbed.add_Tabb(new MyE2_String("Benutzerdefinierte Selektoren"), 	new SEL_LIST_BasicModuleContainer(oThis.oContainerCalling));
			
			try {
				//2018-05-18: suchdefinitionen einfuegen
				oTabbed.add_Tabb(new MyE2_String("Benutzerdefinierte Suche"), 		new SD_LIST_BasicModuleContainer(oThis.oContainerCalling.get_MODUL_IDENTIFIER()));
			} catch (Exception e) {
				bibMSG.MV()._addWarn(S.ms("Benutzerdefinierte Suche fehlerhaft im Modul"));
				e.printStackTrace();
			}
			
		
			oThis.oE2_BasicModulContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(900),new Extent(600),null);
		}
		
	}


	public void add_cZusatzMODULKENNER(String cModulKenner)
	{
		this.vZusatzModuleKenner.add(cModulKenner);
	}
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{
		public ownBasicModuleContainer()
		{
			super();
		}
	};

}
