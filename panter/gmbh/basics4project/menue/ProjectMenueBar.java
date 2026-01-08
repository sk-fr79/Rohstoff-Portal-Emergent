package panter.gmbh.basics4project.menue;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.extras.app.menu.DefaultMenuModel;
import nextapp.echo2.extras.app.menu.DefaultOptionModel;
import nextapp.echo2.extras.app.menu.MenuModel;
import nextapp.echo2.extras.app.menu.SeparatorModel;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_PassiveActionExecuter;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgentOpenURL;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ENUM_GLOBALE_MODULE_TO_JUMP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO.PROJECT_INFO_Container;
import panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS.ADM_BasicModulContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.CG__BasicModuleContainer_CodeGen;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ContainerForVerwaltungsTOOLS;
import panter.gmbh.Echo2.__BASIC_MODULS.DB_DOKUMENTATION.DBDO_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS.QUERY_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE.DP_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG.MODUL__LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.SESSIONMANAGEMENT.ModuleContainer_POPUP_SessionManagement;
import panter.gmbh.Echo2.__BASIC_MODULS.XML_STARTER.ModuleContainer_POPUP_ShowAllXMLDefs;
import panter.gmbh.Echo2.__BASIC_MODULS.XML_STARTER.ModuleContainer_POPUP_ShowAllXMLDefs_NG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.menue.E2_Menue;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_INTERNET_BOOKMARK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_INTERNET_BOOKMARK;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_ENUM;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.XMLDEF_TESTER.ContainerForTesting;
import rohstoff.Echo2BusinessLogic._TAX.RULE_FINDER.TF_RuleFinderBasicContainer;


public class ProjectMenueBar extends E2_Menue 
{
	public static String 		  ID_SONDERMENUE_SESSIONVERWALTUNG = 				"ID_SONDERMENUE_SESSIONVERWALTUNG";
	public static String 		  ID_SONDERMENUE_XML_LISTEN = 						"ID_SONDERMENUE_XML_LISTEN";

	//2016-06-28: neues xml tabelle manager
	public static String		  ID_SONDERMENUE_XML_LISTEN_NG = 					"ID_SONDERMENUE_XML_LISTEN_NG";

	public static String 		  ID_SONDERMENUE_INTERNET_URLS = 					"ID_SONDERMENUE_INTERNET_URLS";

	public static String 		  ID_SONDERMENUE_CODEGENERATOR = 					"ID_SONDERMENUE_CODEGENERATOR";
	public static String 		  ID_XML_DEF_TESTER = 								"ID_XML_DEF_TESTER";

	//2015-05-21: zuruecksetzen der sitzungsinfos
	public static String 		  ID_RESET_SESSION_VALUES = 						"ID_RESET_SESSION_VALUES";

	//2012-08-14: dokumentationsmodul
	public static String 		  ID_DB_FIELD_DOKU = 								"ID_DB_FIELD_DOKU";

	public static String 		  ID_SONDERMENUE_DEFINE_GLOBAL_QUERYS = 			"ID_SONDERMENUE_DEFINE_GLOBAL_QUERYS";
	public static String 		  ID_SONDERMENUE_SYSTEMEINSTELLUNGEN = 				"ID_SONDERMENUE_SYSTEMEINSTELLUNGEN";
	public static String 		  ID_SONDERMENUE_PROGRAMMVERWALTUNG = 				"ID_SONDERMENUE_PROGRAMMVERWALTUNG";
	public static String 		  ID_SONDERMENUE_MANDANTENVERWALTUNG = 				"ID_SONDERMENUE_MANDANTENVERWALTUNG";
	public static String 		  ID_SONDERMENUE_BENUTZERVERWALTUNG = 				"ID_SONDERMENUE_BENUTZERVERWALTUNG";
	public static String 		  ID_SONDERMENUE_MODULMANAGER = 					"ID_SONDERMENUE_MODULMANAGER";
	public static String 		  ID_SONDERMENUE_PRINTPIPE = 						"ID_SONDERMENUE_PRINTPIPE";
	public static String 		  ID_SONDERMENUE_INFO = 							"ID_SONDERMENUE_INFO";

	//2012-12-06: steuer
	public static String 		  ID_SONDERMENUE_STEUER_TYPEN = 					"ID_SONDERMENUE_STEUER_TYPEN";
	//2020-11-05: neues steuermodul
	public static String 		  ID_TAX_RATE_V2 = 									"ID_TAX_RATE_V2";
	
	//2018-06-13: steuersatz-gruppen
	public static String 		  ID_SONDERMENUE_STEUERSATZ_GRUPPEN = 				"ID_SONDERMENUE_STEUERSATZ_GRUPPEN";
	
	public static String 		  ID_SONDERMENUE_STEUER_REGELN = 					"ID_SONDERMENUE_STEUER_REGELN";
	//2014-02-25: steuerregeln suchen
	public static String 		  ID_SONDERMENUE_STEUER_REGELN_FINDER = 			"ID_SONDERMENUE_STEUER_REGELN_FINDER";
	public static String 		  ID_SONDERMENUE_ANHANG7_PROFILE = 					"ID_SONDERMENUE_ANHANG7_PROFILE";
	public static String 		  ID_SONDERMENUE_ANHANG7_STEUERTABELLE =   			"ID_SONDERMENUE_ANHANG7_STEUERTABELLE";
	


	public static String          AUFRUFKENNER_PREFIX_FUER_WEBSEITEN=				"###AUFRUFKENNER_PREFIX_FUER_WEBSEITEN@@@";

	public static String 		ID_SONDERMENUE_COMPONENT_SANDBOX =	"ID_SONDERMENUE_COMPONENT_SANDBOX";

	
	//private E2_TabbedPaneForFirstContainer oContainerTabbedPaneFirst = null;               // hier wirds aktuell angeordnet
	//fuer jeden entwickler ein eigenes testpanel
	public static String 		  ID_TESTER_MANFRED = 								"ID_TESTER_MANFRED";
	public static String 		  ID_TESTER_MARTIN = 								"ID_TESTER_MARTIN";
	public static String 		  ID_TESTER_SEBASTIEN = 							"ID_TESTER_SEBASTIEN";

    public static String		ID_MASK_DEFINITION_TOOL	=							"ID_MASK_DEFINITION_TOOL";
	
	
	private RECLIST_INTERNET_BOOKMARK 	recList_WWW  = null;



	/**
	 * 2013-12-18: neue methode um meldungen zu manipulieren
	 */
	private XX_MessageManipulator      oMessageManipulator = null;
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		this.oMessageManipulator = oManipulator;
	}


	//2015-04-17: aenderung: E2_TabbedPaneForFirstContainer wird immer aus der session geladen
	public ProjectMenueBar() throws myException
	{
		super();
		//        this.oContainerTabbedPaneFirst = ocontainerTabbedPaneFirst;

		this.setDisabledBackground(new E2_ColorDark());
		this.setBackground(new E2_ColorDDark());
		this.setSelectionBackground(new E2_ColorDDDark());
		this.setMenuBorder(new Border(1,Color.DARKGRAY,Border.STYLE_SOLID));
		this.setForeground(Color.BLACK);
		this.setSelectionForeground(Color.BLACK);

		//bei update auf diese version kann es sein, dass die Datenbank noch nicht mit views versehen ist und deshalb
		//hier ein fehler auflaeuft
		try
		{
			this.recList_WWW =new RECLIST_INTERNET_BOOKMARK("SELECT * FROM JT_INTERNET_BOOKMARK ORDER BY "+RECORD_INTERNET_BOOKMARK.FIELD__TITEL_MENUE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.add_oActionAgent(new ownActionAgent());

	}


	// static - methoden, die die hauptmenuefelder fuellen
	// liefert die menue-Strings und die zugehoerigen menue-ids 
	private  String[][] queryHauptmenuePunkte(String cIDUser, String cTO) throws myException
	{
		// hauptmenue anhand der gruppen
		String cQueryString = "select hm.menuetxt,hm.ID_HAUPTMENUE " + "  from  " + 
				cTO + ".JD_HAUPTMENUE hm " + 
				"  where hm.ID_HAUPTMENUE >0 " + "  and  hm.ID_HAUPTMENUE in( " + 
				" select SV.ID_HAUPTMENUE from " + cTO + ".JD_SERVLETS sv," + cTO + ".JD_ZUGRIFF zu, " +cTO + ".JD_USER  us " + 
				" where sv.ID_SERVLETS= zu.ID_SERVLETS" + 
				" and zu.ID_USER   = us.ID_USER" + 
				" and   (sv.SERVLETAUFRUF like 'echo2_starter:%'"+
				"       or sv.SERVLETAUFRUF like 'echo2_tableedit:%'"+
				"       or sv.SERVLETAUFRUF like 'echo2_xmledit:%')"+
				" and us.ID_USER  =" + cIDUser + ") order by SORTNUMMER";


		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())
		{
			cQueryString = "select hm.menuetxt,hm.ID_HAUPTMENUE " + "  from  " + 
					cTO + ".JD_HAUPTMENUE hm " + 
					"  where hm.ID_HAUPTMENUE >0 " + "  and  hm.ID_HAUPTMENUE in( " + 
					" select SV.ID_HAUPTMENUE from " + cTO + ".JD_SERVLETS sv "+ 
					" where (sv.SERVLETAUFRUF like 'echo2_starter:%'"+
					"       or sv.SERVLETAUFRUF like 'echo2_tableedit:%'"+
					"       or sv.SERVLETAUFRUF like 'echo2_xmledit:%')) order by SORTNUMMER";

		}
		String[][] cRueck = bibDB.EinzelAbfrageInArray(cQueryString, "");
		return cRueck;
	}

	private String[][] queryMenuePunkte(String cIDUser, String cGruppenId, String cTO) throws myException
	{
		String cQueryString = "select SV.menueeintrag,sv.servletaufruf, nvl(sv.tabtext,sv.menueeintrag) FROM " + 
				cTO + ".JD_SERVLETS sv, " + cTO + ".JD_ZUGRIFF  zu, " + cTO + ".JD_USER     us " +
				" where sv.ID_SERVLETS=zu.ID_SERVLETS " + 
				" and   zu.ID_USER   =us.ID_USER " + 
				" and   sv.ID_HAUPTMENUE = " + cGruppenId + " and   us.ID_USER      = " + cIDUser +
				" and   (sv.SERVLETAUFRUF like 'echo2_starter:%'"+
				"       or sv.SERVLETAUFRUF like 'echo2_tableedit:%'"+
				"       or sv.SERVLETAUFRUF like 'echo2_xmledit:%')"+
				" order by SORTNUMMER";

		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())
		{
			cQueryString = "select SV.menueeintrag,sv.servletaufruf, nvl(sv.tabtext,sv.menueeintrag) FROM " + 
					cTO + ".JD_SERVLETS sv " +
					" where sv.ID_HAUPTMENUE = " + cGruppenId +
					" and   (sv.SERVLETAUFRUF like 'echo2_starter:%'"+
					"       or sv.SERVLETAUFRUF like 'echo2_tableedit:%'"+
					"       or sv.SERVLETAUFRUF like 'echo2_xmledit:%')"+
					" order by SORTNUMMER";

		}

		String[][] cRueck = bibDB.EinzelAbfrageInArray(cQueryString, "");

		return cRueck;
	}



	@Override
	protected MenuModel createMenuModel() throws myException
	{
		DefaultMenuModel menuModel = new DefaultMenuModel();

		DefaultMenuModel startMenuModel = new DefaultMenuModel(null, "Start");
		DefaultMenuModel zusatzMenuModel = new DefaultMenuModel(null, "Zusätze");

		String[][] cMainMenu = this.queryHauptmenuePunkte(bibALL.get_ID_USER(),bibE2.cTO());

		HashMap<String, String> hmMenuePunkte = new HashMap<String, String>();

		if (cMainMenu != null && cMainMenu.length>0)
		{
			for (int i = 0;i<cMainMenu.length;i++)
			{
				DefaultMenuModel ebene1 = new DefaultMenuModel(null,new MyE2_String(cMainMenu[i][0]).CTrans());

				startMenuModel.addItem(ebene1);

				/*
				 * jetzt die menuepunkte abfragen (SV.menueeintrag,sv.servletaufruf, nvl(sv.tabtext,sv.menueeintrag)
				 */
				String[][] cMenuPunkte = this.queryMenuePunkte(bibALL.get_ID_USER(),cMainMenu[i][1],bibE2.cTO());
				if (cMenuPunkte != null && cMenuPunkte.length>0)
				{
					for (int k=0;k<cMenuPunkte.length;k++)
					{
						//der menue-text wird an das actioncommand angehaengt, damit aus der event.getActionCommand das extrahiert werden kann
						ebene1.addItem(new DefaultOptionModel(cMenuPunkte[k][1]+"@@@@@"+cMenuPunkte[k][2], cMenuPunkte[k][0], null));
						hmMenuePunkte.put(cMenuPunkte[k][1], cMenuPunkte[k][2]);
					}
				}
			}
		}
		bibSES.set_ALLOWED_MENUES_4_USER(hmMenuePunkte);

		zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_SESSIONVERWALTUNG, new MyE2_String("Sitzungsmanagement").CTrans(), null));

		//2016-07-06: Deaktiviert in Echt System 
		//        zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_XML_LISTEN, 		new MyE2_String("Vorgabe-/Systemtabellen (XML)").CTrans(), null));

		//2016-06-28: neue XML tabelle
		//2016-07-06: Jetzt in Echt System 
		zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_XML_LISTEN_NG, 		new MyE2_String("Vorgabe-/Systemtabellen (XML)").CTrans(), null));


		//2015-05-21: sessionwerte zum neu einlesen zwingen
		zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_RESET_SESSION_VALUES, 		new MyE2_String("Sitzungsparameter neu einlesen").CTrans(), null));

		//2012-08-14: dokumentation
		zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_DB_FIELD_DOKU, 		new MyE2_String("Feld-Dokumentation").CTrans(), null));

		//2012-01-17: Web-urls aus der tabelle JT_INTERNET_BOOKMARK einlesen

		try
		{
			this.recList_WWW = new RECLIST_INTERNET_BOOKMARK("SELECT * FROM JT_INTERNET_BOOKMARK ORDER BY "+RECORD_INTERNET_BOOKMARK.FIELD__TITEL_MENUE);

			//			this.recList_WWW = new RECLIST_INTERNET_BOOKMARK("SELECT * FROM JT_INTERNET_BOOKMARK");
			if (recList_WWW.get_vKeyValues().size()>0)
			{
				DefaultMenuModel internet_seiten = new DefaultMenuModel(null,new MyE2_String("Internet-Bookmarks").CTrans());

				for (int i=0;i<recList_WWW.get_vKeyValues().size();i++)
				{
					RECORD_INTERNET_BOOKMARK  recIB = recList_WWW.get(i);
					internet_seiten.addItem(new DefaultOptionModel(ProjectMenueBar.AUFRUFKENNER_PREFIX_FUER_WEBSEITEN+recList_WWW.get_vKeyValues().get(i),recIB.get_TITEL_MENUE_cUF_NN("<menue-eintrag>") , null));
				}

				zusatzMenuModel.addItem(internet_seiten);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


		boolean bDeveloper = false;
		try {
			if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
				bDeveloper = true;
			}
		} catch (Exception ex) {

		}


		//items hier nur fuer Admin-Benutzer
		if (bibALL.get_bIST_SUPERVISOR())
		{
			zusatzMenuModel.addItem(new SeparatorModel());
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_SYSTEMEINSTELLUNGEN, 		new MyE2_String("Systemeinstellungen").CTrans(), null));
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_PROGRAMMVERWALTUNG, 		new MyE2_String("Wartung: Programm").CTrans(), null));
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_DEFINE_GLOBAL_QUERYS, 	new MyE2_String("Globale Abfragen erstellen").CTrans(), null));
			zusatzMenuModel.addItem(new SeparatorModel());

			if (bDeveloper) {
				zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_CODEGENERATOR, 			new MyE2_String("Software-Entwicklung").CTrans(), null));
				zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_XML_DEF_TESTER, 						new MyE2_String("Teste XML-Definition").CTrans(), null));
				//2017-07-25: testmodule
				if (bibALL.get_bDebugMode()) {
					zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_TESTER_MANFRED, 			       new MyE2_String("Testmodul für Manfred").CTrans(), null));
					zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_TESTER_SEBASTIEN, 			   new MyE2_String("Testmodul für Sebastien").CTrans(), null));
					zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_TESTER_MARTIN, 			       new MyE2_String("Testmodul für Martin").CTrans(), null));
				}
				
				//2017-11-24: masken definition
				zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_MASK_DEFINITION_TOOL, 				new MyE2_String("Mask definition").CTrans(), 							null));
				
				zusatzMenuModel.addItem(new SeparatorModel());
			}

			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_MANDANTENVERWALTUNG, 		new MyE2_String("Mandanten verwalten").CTrans(), null));
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_BENUTZERVERWALTUNG, 		new MyE2_String("Benutzer verwalten").CTrans(), null));
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_MODULMANAGER, 			new MyE2_String("Module verwalten").CTrans(), null));
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_PRINTPIPE, 				new MyE2_String("Druck-Verarbeitung").CTrans(), null));
			zusatzMenuModel.addItem(new SeparatorModel());

			//2012-12-06: steuersachverhalte
			DefaultMenuModel steuerMenue = new DefaultMenuModel(null,new MyE2_String("Steuer/Anhang 7").CTrans());
			
			steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_STEUERSATZ_GRUPPEN,   		new MyE2_String("Steuersatz-Gruppen").CTrans(), null));
		//	steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_STEUER_TYPEN, 				new MyE2_String("Steuertexte/-Sätze").CTrans(), null));
			steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_TAX_RATE_V2, 							new MyE2_String("Steuersätze").CTrans(), null));
			steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_STEUER_REGELN, 				new MyE2_String("USt./Steuertext: Regeln für Bewegungssatz-Prüfung").CTrans(), null));
			steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_STEUER_REGELN_FINDER, 		new MyE2_String("Regeln suchen/vorschlagen").CTrans(), null));
			steuerMenue.addItem(new SeparatorModel());
			steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_ANHANG7_PROFILE, 				new MyE2_String("Anhang-7 Profile bearbeiten").CTrans(), null));
			steuerMenue.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_ANHANG7_STEUERTABELLE, 		new MyE2_String("Anhang-7 Druck-Steuertabelle").CTrans(), null));
			
			zusatzMenuModel.addItem(steuerMenue);
			zusatzMenuModel.addItem(new SeparatorModel());

			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_INFO, 					new MyE2_String("Programminformationen").CTrans(), null));

		} else {
			//2014-02-17: beschraenkte nutzung als normaler benutzer
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_BENUTZERVERWALTUNG, 		new MyE2_String("Benutzereinstellungen").CTrans(), null));
			zusatzMenuModel.addItem(new DefaultOptionModel(ProjectMenueBar.ID_SONDERMENUE_INFO, 					new MyE2_String("Programminformationen").CTrans(), null));
		}

		menuModel.addItem(startMenuModel);
		menuModel.addItem(zusatzMenuModel);

		return menuModel;
	}





	private class ownActionAgent extends XX_ActionAgent
	{


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//E2_TabbedPaneForFirstContainer ContainerTabbedPane =  ProjectMenueBar.this.oContainerTabbedPaneFirst;

			String cAufrufString = bibE2.get_LAST_ACTIONEVENT().getActionCommand();

			//die standard-aufrufe enthalten immer den TabText im getActionCommand() - String durch @@@@@ getrennt
			if (cAufrufString.indexOf("@@@@@")>0)
			{
				bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule(cAufrufString,true);
			}
			else
			{
				E2_BasicModuleContainer  oContainer_Ref = null;
				//sondermenue-punkte
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_SESSIONVERWALTUNG))
				{
					oContainer_Ref = new ModuleContainer_POPUP_SessionManagement();
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_XML_LISTEN))
				{
					oContainer_Ref = new ModuleContainer_POPUP_ShowAllXMLDefs();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(800), new MyE2_String("Systemtabellen"));
				}

				//2016-06-28: neue XML Tabelle
				if(cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_XML_LISTEN_NG)){
					oContainer_Ref = new ModuleContainer_POPUP_ShowAllXMLDefs_NG();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(800), new MyE2_String("Systemtabellen NG"));
				}

				//2016-07-04: neues PluginColTest modul
				if(bibALL.get_bDebugMode()){

				}

				//2015-05-21: werte der sitzung neu einlesen lassen (alle die den betreffenden schalter tragen)
				if (cAufrufString.equals(ProjectMenueBar.ID_RESET_SESSION_VALUES)) 	{
					StringBuffer info = new StringBuffer();
					for (bibSES_ENUM val: bibSES_ENUM.values()) {
						if (val.is_Resetted()) {
							bibALL.setSessionValue(val.name(), null);
							info.append(new MyE2_String(val.user_text(),true).CTrans()+"/");
						}
					}
					//ebenfalls loeschen: evtl. gespeicherte werte der bib_settings
					bib_Settigs_Mandant.clear_mandantensettings_in_session();

					//2018-05-30: alle werte der ENUM_MANDANT_DECISION aus der SESSION loeschen
					ENUM_MANDANT_DECISION.clearAllValuesFrom_ENUM_MANDANT_DECISION();
					ENUM_MANDANT_SESSION_STORE.clearAllValuesFrom_ENUM_MANDANT_SESSION_STORE();
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Systemparameter wurden neu gesetzt: ",true,S.NN(info.toString(), ""),false)));
				}

				//2012-08-14: db-dokumentation
				if (cAufrufString.equals(ProjectMenueBar.ID_DB_FIELD_DOKU))
				{
					oContainer_Ref = new DBDO_BasicModuleContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(800), new MyE2_String("Datenbank-Feld-Dokumentation"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_DEFINE_GLOBAL_QUERYS))
				{
					oContainer_Ref = new QUERY_LIST_BasicModuleContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Globale Abfragen erzeugen"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_CODEGENERATOR))
				{
					oContainer_Ref = new CG__BasicModuleContainer_CodeGen();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Software-Entwickler-Tools"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_SYSTEMEINSTELLUNGEN))
				{
					oContainer_Ref = new ContainerForVerwaltungsTOOLS();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Systemsteuerung"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_PROGRAMMVERWALTUNG))
				{
					oContainer_Ref = new ADM_BasicModulContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Wartungsmodul Programm"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_MANDANTENVERWALTUNG))
				{
					oContainer_Ref = new MANDANT_LIST_BasicModuleContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Mandantenverwaltung"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_BENUTZERVERWALTUNG))
				{
					//2017-09-15: in den tabs anzeigen, kein popup mehr
//					oContainer_Ref = new USER__LIST_BasicModuleContainer();
//					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Benutzerverwaltung"));
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.NAME_MODUL_USERVERWALTUNG_LISTE.get_callKey()+"@@@@@Benutzerverwaltung",true);

				}
				if (cAufrufString.equals(ProjectMenueBar.ID_XML_DEF_TESTER))
				{
					oContainer_Ref = new ContainerForTesting();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Modul-Manager"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_MODULMANAGER))
				{
					oContainer_Ref = new MODUL__LIST_BasicModuleContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Modul-Manager"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_PRINTPIPE))
				{
					oContainer_Ref = new DP_LIST_BasicModuleContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1100), new Extent(800), new MyE2_String("Druck-Verwaltung"));
				}
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_INFO))
				{
					oContainer_Ref = new PROJECT_INFO_Container();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Programminformationen"));
				}




				//2012-01-17: internet-seiten-aufruf
				if (cAufrufString.startsWith(ProjectMenueBar.AUFRUFKENNER_PREFIX_FUER_WEBSEITEN))
				{
					if (ProjectMenueBar.this.recList_WWW!=null)
					{
						String ID_INTERNET_BOOKMARK = cAufrufString.substring(ProjectMenueBar.AUFRUFKENNER_PREFIX_FUER_WEBSEITEN.length());

						RECORD_INTERNET_BOOKMARK recIB = ProjectMenueBar.this.recList_WWW.get(ID_INTERNET_BOOKMARK);

						if (recIB != null)
						{
							new ActionAgentOpenURL(recIB.get_TITEL_MENUE_cUF_NN("<titel>"), recIB.get_URL_cUF_NN(""), true).executeAgentCode(null);
						}
					}
				}

				//2018-06-13: steuersatzgruppen
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_STEUERSATZ_GRUPPEN)) {
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.TAX_GROUP_LIST.get_callKey()+"@@@@@Steuersatz-Gruppen",true);
				}
				
				//2012-12-06: steuersachverhalte
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_STEUER_TYPEN))
				{
					//					oContainer_Ref = new TAX__LIST_BasicModuleContainer();
					//					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Steuersätze und Steuertexte"));
					//					
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.MODUL_TAX_LIST.get_callKey()+"@@@@@Steuer-Sätze",true);

				}
				//2020-11-05: steuersachverhalte (Neu)
				if (cAufrufString.equals(ProjectMenueBar.ID_TAX_RATE_V2))
				{
					//					oContainer_Ref = new TAX__LIST_BasicModuleContainer();
					//					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Steuersätze und Steuertexte"));
					//					
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.TAX_RATE_V2_LIST.get_callKey()+"@@@@@Steuersätze",true);

				}


				//2012-12-06: steuersachverhalte
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_STEUER_REGELN))
				{
					//2014-04-23: die regeln als tab-modul statt als popup
					//oContainer_Ref = new TR__LIST_BasicModuleContainer(null);
					//oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Regeln für Steuern in Bewegungsätzen"));

					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.MODUL_TAXRULES_LIST.get_callKey()+"@@@@@USt./Steuertext",true);

				}

				//2012-12-06: steuersachverhalte suchen und vorschlagen
				if (cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_STEUER_REGELN_FINDER))
				{
					oContainer_Ref = new TF_RuleFinderBasicContainer();
					oContainer_Ref.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(200), new MyE2_String("Regeln für Steuern in Bewegungsätzen suchen und vorschlagen"));
				} 

						
				//2017-07-25: testmodule, fuer jeden eins
				if (cAufrufString.equals(ProjectMenueBar.ID_TESTER_MARTIN))	{
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.TESTMODUL_MARTIN.get_callKey()+"@@@@@Martins Test",true);
				} 
				if (cAufrufString.equals(ProjectMenueBar.ID_TESTER_MANFRED))	{
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.TESTMODUL_MANFRED.get_callKey()+"@@@@@Manfreds Test",true);
				} 
				if (cAufrufString.equals(ProjectMenueBar.ID_TESTER_SEBASTIEN))	{
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.TESTMODUL_SEBASTIEN.get_callKey()+"@@@@@Sebastiens Test",true);
				} 
				
				//2017-11-24: Mask definition
				if(cAufrufString.equals(ProjectMenueBar.ID_MASK_DEFINITION_TOOL)) {
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule("echo2_starter:"+E2_MODULNAME_ENUM.MODUL.MASK_DESIGN_LIST.get_callKey()+"@@@@@Masken definition",true);

				}

				
				//20171127: ah7-module
				if(cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_ANHANG7_PROFILE)) {
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule(
							"echo2_starter:"+E2_MODULNAME_ENUM.MODUL.AH7_PROFIL_LIST.get_callKey()+"@@@@@"+ENUM_GLOBALE_MODULE_TO_JUMP.get_hmModule().get(E2_MODULNAME_ENUM.MODUL.AH7_PROFIL_LIST),true);
				}
			
				//20171127: ah7-module
				if(cAufrufString.equals(ProjectMenueBar.ID_SONDERMENUE_ANHANG7_STEUERTABELLE)) {
					bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule(
							"echo2_starter:"+E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE.get_callKey()+"@@@@@"+ENUM_GLOBALE_MODULE_TO_JUMP.get_hmModule().get(E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE),true);
				}
				
				
				if (oContainer_Ref != null)
					oContainer_Ref.set_bIsStartContainer_4_DBTimeStamps(true);
			}
		}
	}


	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException 
	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}




	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}

		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}


	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}

}
