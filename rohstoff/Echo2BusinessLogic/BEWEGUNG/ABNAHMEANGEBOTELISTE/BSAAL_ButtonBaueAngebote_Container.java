package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_von_bis;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.E2_PopUpAllNoneInvert_CheckBoxes;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.myVectors.VEK;
import echopointng.Separator;

/**
 * @author martin
 * Aufbau eines popup-fensters zur auswahl, fuer welche kunden abnahmeangebote (Datensaetze) geschrieben werden
 * 
 * 
 *
 */
public class BSAAL_ButtonBaueAngebote_Container extends E2_BasicModuleContainer
{

	public static int iHeight = 780;
	private static String                       cBasisQueryMultiBereichSelektor = "SELECT DISTINCT  JT_ARTIKEL.ANR1 || ' - ' ||  JT_ARTIKEL.ARTBEZ1 , JT_ARTIKEL.ANR1  from "
			                                              + bibE2.cTO() + ".JT_LAGER_KONTO " + " JOIN " + bibE2.cTO()
			                                              + ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL " + " #WHERE# ";
	private SelectorSortenVonBis_Multi          oSortenBlockSelektor = null;
	private BSAAL__ModulContainerLIST			oModulContainerList = null;
	private MyE2_Column							oColumnBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
	
	private MyE2_TextArea_WithSelektorEASY		oTextAnfang = new MyE2_TextArea_WithSelektorEASY("PREISINFOANFANG");
	private MyE2_TextArea_WithSelektorEASY		oTextEnde = new MyE2_TextArea_WithSelektorEASY("PREISINFOENDE");
	
	private E2_DateBrowser						oCalendarGueltigVon = null;
	private E2_DateBrowser						oCalendarGueltigBis = null;

	private String 								cMONTH = null;
	private String 								cYEAR  = null;

	
	/*
	 * vector mit checkboxen fuer die Auswahl der zu bearbeitenden kunden
	 * und zugehoeriges grid zur anzeige der adressen
	 */
	private Vector<MyE2_CheckBox>				vSelectAdresses = 	new Vector<MyE2_CheckBox>();
	
	/*
	 * selektionskomponente fuer die adress-checkboxes
	 */
	private E2_PopUpAllNoneInvert_CheckBoxes	PopUpAnAus = null;
	
	private Vector<String>						vAdressGrids = 		new Vector<String>();
	private MyE2_Column							oColumnForAdress	= 	new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
	
	private ownListSelector						oOwnListSelector = new ownListSelector();
	
	private MyE2_Button							oButtonSTART = new MyE2_Button(new MyE2_String("Erzeuge Preislistensätze/Abnahmeangebote"));
	
	
	
	public BSAAL_ButtonBaueAngebote_Container(BSAAL__ModulContainerLIST	oModulContainer, String cMonat, String cJahr)  throws myException
	{
		super();
		this.oModulContainerList = oModulContainer;

		this.PopUpAnAus = new E2_PopUpAllNoneInvert_CheckBoxes(this.vSelectAdresses, false);
		
		this.oButtonSTART.setFont(new E2_FontBold(2));
		this.oButtonSTART.setForeground(Color.RED);
		this.oButtonSTART.add_oActionAgent(new ownActionAgentStartAngebot());

		this.cMONTH = cMonat;
		this.cYEAR = cJahr;
		
		if (!bibALL.isInteger(this.cMONTH) || !bibALL.isInteger(this.cYEAR))
			throw new myExceptionForUser("Bitte Monat und Jahr exakt angeben !!!");

		
		int iDay=2;
		int iMonth = new Integer(cMonat).intValue();
		int iYear  = new Integer(cJahr).intValue();
		myDateHelper oDateHelp = new myDateHelper(iDay,iMonth,iYear);
		
		this.oCalendarGueltigVon = new E2_DateBrowser();
		this.oCalendarGueltigBis = new E2_DateBrowser();
		
		this.oCalendarGueltigVon.get_oDatumsFeld().setText(oDateHelp.get_cDateFormatForMask_FirstDayInMonth());
		this.oCalendarGueltigBis.get_oDatumsFeld().setText(oDateHelp.get_cDateFormatForMask_LastDayInMonth());
		
		this.oCalendarGueltigVon.DO_EvaluateActualText();
		this.oCalendarGueltigBis.DO_EvaluateActualText();
		
		this.oTextAnfang.get_oTextArea().set_iWidthPixel(600);
		this.oTextEnde.get_oTextArea().set_iWidthPixel(600);
		this.oTextAnfang.get_oTextArea().set_iRows(6);
		this.oTextEnde.get_oTextArea().set_iRows(6);
		this.oTextAnfang.setFont(new E2_FontPlain(-2));
		this.oTextEnde.setFont(new E2_FontPlain(-2));
		

		MyE2_Grid oGridForText = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oGridForText.add(new MyE2_Label(new MyE2_String("Einführung:")), E2_INSETS.I_0_2_10_2);
		oGridForText.add(this.oTextAnfang, E2_INSETS.I_10_2_10_2);
		oGridForText.add(new MyE2_Label(new MyE2_String("Abschluss:")), E2_INSETS.I_0_2_10_2);
		oGridForText.add(this.oTextEnde, E2_INSETS.I_10_2_10_2);
		
		this.oColumnBasic.add(this.oOwnListSelector,E2_INSETS.I_10_0_10_0);    // listselector baut die auswahl der adresse auf
		this.oColumnBasic.add(new Separator());
		this.oColumnBasic.add(this.oColumnForAdress,E2_INSETS.I_10_0_10_0);
		this.oColumnBasic.add(new Separator());
		this.oColumnBasic.add(oGridForText,E2_INSETS.I_10_0_10_0);
		this.oColumnBasic.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Gültig von-bis :")),this.oCalendarGueltigVon,this.oCalendarGueltigBis, new Insets(0,0,10,0)),E2_INSETS.I_10_2_10_2);
		
		MyE2_Button	oButtonCloseWindow = new MyE2_Button(new MyE2_String("Abbrechen und Fenster schliessen"));
		oButtonCloseWindow.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				BSAAL_ButtonBaueAngebote_Container.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		});

		
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				BSAAL_ButtonBaueAngebote_Container.this.oModulContainerList.get_oSelector().do_RefreshSelectDateRange();
				BSAAL_ButtonBaueAngebote_Container.this.oModulContainerList.get_oSelector().get_oSelVector().doActionPassiv();
			}
		});

		
		
		// liste in der basisversion bauen
		this.oOwnListSelector.get_oSelVector().doActionPassiv();

		this.add(this.oColumnBasic);
		this.set_Component_To_ButtonPane(new E2_ComponentGroupHorizontal(0,this.oButtonSTART,oButtonCloseWindow,new Insets(10,0,20,0)));
		this.get_oSplitPane().setSeparatorPosition(new Extent(BSAAL_ButtonBaueAngebote_Container.iHeight-90));
		this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(	new Extent(1100),
												new Extent(BSAAL_ButtonBaueAngebote_Container.iHeight),
												new MyE2_String("Kundenauswahl: Welche Abnahme-Angebote sollen gebaut werden ?"));
	}

	
	
	/*
	 * eine selektorklasse fuer die auswahl der kunden, die ein angebot erhalten sollen,
	 * reduziert die anzahl der checkboxen
	 */
	private class ownListSelector extends MyE2_Row
	{
		
		/*
		 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
		 */
		private ownSelectionsComponentVector 		oSelVector = null;
		
		//private MyE2_SelectField					oSelectMitarbeiterADR = null;
		private Component_USER_DROPDOWN_NEW  		oSelectMitarbeiterADR = 	new Component_USER_DROPDOWN_NEW(false,120);

		
		private MyE2_SelectField					oSelectLand = null;
		private MyE2_SelectField					oSelectAdressKlasse = null;
		private SELECTOR_COMPONENT_FirmenAuswahl   	oSelKundenMitAngebot = null;

		
		public ownListSelector() throws myException
		{
			super();
			oSortenBlockSelektor =          new SelectorSortenVonBis_Multi();
			this.oSelVector = 				new ownSelectionsComponentVector();
			
//			this.oSelectMitarbeiterADR = new MyE2_SelectField(
//					"select  NVL(name,'-')||' ('|| NVL(vorname,'-')||') ',id_user from "+bibE2.cTO()+".jd_user where id_mandant="+bibALL.get_ID_MANDANT()+" order by name",
//					false,true,false,false);

			//2011-01-09: neuer kundenselektor fuer die auswahl
			String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
												"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
												" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE  NVL(JT_ADRESSE.AKTIV,'N')='Y' AND ID_ADRESSE IN "+
												" (SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE NVL(ANGEBOT,'N')='Y') "+
												" ORDER BY NAMEN";
			
//			this.oSelKundenMitAngebot = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor, 60, 120, this.oSelVector, new MyE2_String("Lieferant: "));
			this.oSelKundenMitAngebot = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor, 120, this.oSelVector);
			this.oSelKundenMitAngebot.REFRESH_KundenSelektor();

			
			
			this.oSelectLand = new MyE2_SelectField(
					"select laendername,id_land from "+bibE2.cTO()+".jd_land order by laendername",
					false,true,false,false,new Extent(120));

			this.oSelectAdressKlasse = new MyE2_SelectField(
					"select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
					false,true,false,false,new Extent(120));

//			oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"JT_ADRESSE.ID_USER = #WERT#", null, null));
			//2012-02-21: es werden adresse auch selektiert, wenn der benutzer der 2. betreuer ist
			oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"(NVL(JT_ADRESSE.ID_USER,-1) = #WERT#  OR NVL(JT_ADRESSE.ID_USER_ERSATZ,-1) = #WERT#)", null, null));
			
			
			oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_ADRESSE.ID_LAND = #WERT#", null, null));
			oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse,"JT_ADRESSE.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));
			oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitAngebot.get_oSelKunden(),"JT_ADRESSE.ID_ADRESSE=#WERT# ", null, null));
			oSelVector.add(oSortenBlockSelektor);
			
//			MyE2_Grid oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL());
//			Insets oIN  = new Insets(0, 2, 2, 2);
//			Insets oIN2 = new Insets(0, 2, 20, 2);
//			
//			oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (1 oder 2) (Adr):")), 1, oIN);
//			oGrid.add(new MyE2_Label(new MyE2_String("Land:")), 1, oIN);
//			oGrid.add(oSelectMitarbeiterADR, 1, oIN2);
//			oGrid.add(oSelectLand, 1, oIN2);
//
//			oGrid.add(new MyE2_Label(new MyE2_String("Adress-Klasse:")), 1, oIN);
//			oGrid.add(new MyE2_Label(new MyE2_String("Sortenbereich:")), 1, oIN);
//			oGrid.add(oSelectAdressKlasse, 1, oIN2);
//			oGrid.add(oSortenBlockSelektor.get_oComponentForSelection(), 1, 1, oIN2, Alignment.ALIGN_TOP);
//
//			oGrid.add(oSelKundenMitAngebot, 1, oIN2);
//			oGrid.add(oSelVector.get_AktivPassivComponent());

			E2_Grid grid = new E2_Grid()._setSize(140,140,150,170,350,80);
			grid	._a(new RB_lab("Bearb. (1 oder 2) (Adr)")._fsa(-2)._i(),new RB_gld()._ins(3, 2, 2, 0))
					._a(new RB_lab("Land")._fsa(-2)._i(),new RB_gld()._ins(3, 2, 2, 0))
					._a(new RB_lab("Adress-Klasse")._fsa(-2)._i(),new RB_gld()._ins(3, 2, 2, 0))
					._a(new RB_lab("Lieferant")._fsa(-2)._i(),new RB_gld()._ins(25, 2, 2, 0))
					._a(new RB_lab("Sortenbereich")._fsa(-2)._i(),new RB_gld()._ins(3, 2, 2, 0))
					._a(new RB_lab("")._fsa(-2)._i(),new RB_gld()._ins(1, 2, 2, 0))
					;
			grid	._a(oSelectMitarbeiterADR,								new RB_gld()._ins(1, 1, 2, 1))
					._a(oSelectLand,										new RB_gld()._ins(1, 1, 2, 1))
					._a(oSelectAdressKlasse,								new RB_gld()._ins(1, 1, 2, 1))
					._a(oSelKundenMitAngebot,								new RB_gld()._ins(1, 1, 2, 1))
					._a(oSortenBlockSelektor.get_oComponentForSelection(),	new RB_gld()._ins(1, 1, 2, 1))
					._a(oSelVector.get_AktivPassivComponent(),				new RB_gld()._ins(1, 1, 2, 1))
					;
			
			
					
			
			
			this.add(grid);
			
		}

		public E2_SelectionComponentsVector get_oSelVector() 		{			return oSelVector;		}
//		public MyE2_SelectField get_oSelectAdressKlasse()			{			return oSelectAdressKlasse;		}
//		public MyE2_SelectField get_oSelectLand()					{			return oSelectLand;		}
//		public MyE2_SelectField get_oSelectMitarbeiterADR()			{			return oSelectMitarbeiterADR;		}

	}


	
	/*
	 * eigene selektionsklasse mit ueberschriebenem actionPerformed()
	 */
	private class ownSelectionsComponentVector extends E2_SelectionComponentsVector
	{
		public ownSelectionsComponentVector() throws myException 
		{
			super(null, new actionReload(),new agentForSelector(false));
		}
	}
	
	
	//actionAgent fuer den reload-button im E2_SelectionComponentsVector
	private class actionReload extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new agentForSelector(true).ExecuteAgentCode(new ExecINFO_OnlyCode());
		}
	}


	//actionAgent fuer den eigene selector
	private class agentForSelector extends XX_ActionAgent
	{

		private boolean bReloadButton = false;
		

		public agentForSelector(boolean reloadButton) {
			super();
			bReloadButton = reloadButton;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSAAL_ButtonBaueAngebote_Container oThis = BSAAL_ButtonBaueAngebote_Container.this;
			
			Vector<String> vBedingungen = new Vector<String>();
			
			Font 	oSmallFont = new  E2_FontPlain(-2);
			
			/*
			 * gestartet wird, wenn der reload-button gedrueckt wird oder wenn
			 * der schalter passiv nicht gesetzt ist !!
			 */
			boolean bStart = (!oThis.oOwnListSelector.get_oSelVector().get_oCheckPassiv().isSelected() || this.bReloadButton);

			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
	
			
			if (bStart)
			{
				try
				{
					oThis.vSelectAdresses.removeAllElements();
					oThis.vAdressGrids.removeAllElements();
					oThis.oColumnForAdress.removeAll();
					
					for (int i=0;i<oThis.oOwnListSelector.get_oSelVector().size();i++)
					{
					   XX_ListSelektor oSel = (XX_ListSelektor)oThis.oOwnListSelector.get_oSelVector().get(i);
					   if (!oSel.get_WhereBlock().trim().equals(""))
						   vBedingungen.add(oSel.get_WhereBlock());
					}
					
					vBedingungen.add("JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO);
					
					//aenderung 2010-11-25: nur noch aktive adresse erfassen
					vBedingungen.add("NVL(JT_ADRESSE.AKTIV,'N')='Y'");
					
					
					String cSQL = "SELECT ID_ADRESSE, NAME1, NAME2, PLZ, ORT FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ";
					cSQL += bibALL.Concatenate(vBedingungen," AND ", null);
					cSQL += " AND ID_ADRESSE IN (SELECT DISTINCT ID_ADRESSE FROM "+   //NEU_09  nur EK-Artikel beruecksichtigen
											bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE   NVL(ARTBEZ_TYP,'-')='EK' AND   NVL(ANGEBOT,'-')='Y') ";
					cSQL += " ORDER BY NAME1 ";
	
					
					String[][] cAdressen = bibDB.EinzelAbfrageInArray(cSQL,"");
					
					if (cAdressen == null)
						throw new myException("AAL_WindowPaneToSelectAdresses:ownSelectionsComponentVector:Error Querying Adresses");
					
					
					if (cAdressen.length==0)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Es wurden keine passenden Adressen gefunden !!"));
					}
					else 
					{
						for (int i=0;i<cAdressen.length;i++)
						{
							String cNameText = cAdressen[i][1].trim()+" - "+ cAdressen[i][3].trim()+" - "+ cAdressen[i][4].trim()+" ("+cAdressen[i][0]+")"; 
							MyE2_CheckBox oCB = new MyE2_CheckBox(cNameText);
							oCB.setFont(oSmallFont);
							oCB.EXT().set_C_MERKMAL(cAdressen[i][0]);         // id steht im merkmal
							oCB.setSelected(true);
							
							oThis.vSelectAdresses.add(oCB);
						}
						oMV.add_MESSAGE(new MyE2_Info_Message("Anzahl selektierte Adressen: "+cAdressen.length));
					}
					
					
					
					if (oMV.get_bIsOK())
					{
						if (oThis.vSelectAdresses.size()>0)
						{
							// hier wird die liste aufgebaut
							MyE2_TabbedPane oTabbed = 		new MyE2_TabbedPane(new Integer(280));
							MyE2_Grid 		oGridAdresses = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER());
							int 			iCounter = 		0;
							int 			iCountTabs = 	1;
							
							oTabbed.add_Tabb(new MyE2_String(""+iCountTabs++,false),oGridAdresses);
			
							for (int i=0;i<oThis.vSelectAdresses.size();i++)
							{
								oGridAdresses.add((MyE2_CheckBox)oThis.vSelectAdresses.get(i),E2_INSETS.I_2_2_10_2);
								iCounter ++;
								
								if (iCounter>29 && i!=(oThis.vSelectAdresses.size()-1))
								{
									oGridAdresses = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER());
									oTabbed.add_Tabb(new MyE2_String(""+iCountTabs++,false),oGridAdresses);
									iCounter=0;
								}
								
							}
							oThis.oColumnForAdress.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Lieferanten ein/ausschalten")),oThis.PopUpAnAus,E2_INSETS.I_0_2_10_2));
							oThis.oColumnForAdress.add(oTabbed,E2_INSETS.I_0_2_2_2);
						}
					}
					
				}
				catch (myException ex)
				{
					oMV.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
				
				bibMSG.add_MESSAGE(oMV);
			
			}
		}
		
		
	}
	
	


	private class ownActionAgentStartAngebot extends XX_ActionAgent
	{

		//NEU_09
		//private BSAAL_PreisBuilding 	oPreisBuild = 	new BSAAL_PreisBuilding();
		private int[] 					iInfo             = null;
		private MyString   				oInfo             = null;
		private Vector<String> 			vIDs              = new Vector<String>();
		private Vector<String>          vIdArtikelbezLief = new Vector<String>();
		private String[][]              cArtikelbezLief   = null;
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSAAL_ButtonBaueAngebote_Container oThis = BSAAL_ButtonBaueAngebote_Container.this;
			
			this.vIDs.removeAllElements();
			
			for (int i=0;i<oThis.vSelectAdresses.size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)oThis.vSelectAdresses.get(i);
				if (oCB.isSelected())
					vIDs.add(oCB.EXT().get_C_MERKMAL());
			}			

			// ID_ARTIKELBEZ_LIEF_Vector erstellen und als Parameter an build_Preise_Methode übergeben
			
			this.vIdArtikelbezLief.removeAllElements();

			int    size     = getArrayOfIds_Sortenbereich().size();
			String cSQL     = null;
			String wert[][] = new String[size][2];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < 2; j++) {
					wert[i][j] = getArrayOfIds_Sortenbereich().get(i)[j];					
				}
			}

			if (size == 0) {
				if  (vIDs.size() == 0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind keine Kunden angewählt !"));
				} else {

					String str  = vIDs.toString();
					       cSQL = "SELECT ID_ARTIKELBEZ_LIEF FROM " + bibE2.cTO()
					              + ".JT_ARTIKELBEZ_LIEF JOIN JT_ADRESSE USING (ID_ADRESSE)" + " WHERE ID_ADRESSE IN ("
					              + str.substring(1, str.length() - 1) + ")";

					cArtikelbezLief   = bibDB.EinzelAbfrageInArray(cSQL);

					vIdArtikelbezLief = new VEK<String>()._addVektor(() -> {
						Vector<String> ret = new Vector<String>();
						for (int i = 0; i < cArtikelbezLief.length; i++) {
							ret.add(cArtikelbezLief[i][0]);
						}
						return ret;
					});
				}

			} else {
				if (vIDs.size()==0)	{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind keine Kunden angewählt !"));
				} else {
					     cSQL = "SELECT ID_ARTIKELBEZ_LIEF FROM " + bibE2.cTO()
					            + ".JT_ARTIKELBEZ_LIEF JOIN JT_ARTIKEL_BEZ USING (ID_ARTIKEL_BEZ)"
					            + " WHERE JT_ARTIKEL_BEZ.ID_ARTIKEL IN (SELECT A.ID_ARTIKEL FROM " + bibE2.cTO()
					            + ".JT_ARTIKEL A " + "WHERE ( A.ANR1>='" + wert[0][0] + "' AND A.ANR1<='" + wert[0][1] + "')";

					for (int i = 1; i < size; i++) {
						 cSQL += " or (A.ANR1>='" + wert[i][0] + "' AND A.ANR1<='" + wert[i][1] + "')";
					}
					cSQL += ")";

					cArtikelbezLief = bibDB.EinzelAbfrageInArray(cSQL);

					vIdArtikelbezLief = new VEK<String>()._addVektor(() -> {
						Vector<String> ret = new Vector<String>();
						for (int i = 0; i < cArtikelbezLief.length; i++) {
							ret.add(cArtikelbezLief[i][0]);
						}
						return ret;
					});
				}
			}

			if (bibMSG.get_bIsOK())
			{
				TestingDate oDateVon = new TestingDate(oThis.oCalendarGueltigVon.get_oDatumsFeld().getText());
				TestingDate oDateBis = new TestingDate(oThis.oCalendarGueltigBis.get_oDatumsFeld().getText());
				
				if (!(oDateVon.testing() & oDateBis.testing()))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Datumsangaben sind fehlerhaft !"));
				}
				else if (oDateVon.get_Calendar().after(oDateBis.get_Calendar()))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Datumsreihenfolge ist nicht korrekt !"));
				}
				else if (bibALL.isEmpty(oThis.oTextAnfang.get_oTextArea().getText()) | bibALL.isEmpty(oThis.oTextEnde.get_oTextArea().getText()))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Einleitungs- und Abschlusstext ein !"));
				}
				
				if (bibMSG.get_bIsOK())
				{
					
					//2011-01-09: wenn weniger als 10 angebote, dann ohne fortschrittsbalken
					if (this.vIDs.size()<=10)
					{
						new build_angebote(null);
//						new InfoPopUp(ownActionAgentStartAngebot.this.iInfo);
					}
					else
					{
						new E2_ServerPushMessageContainer(new Extent(530),new Extent(210),new MyE2_String("Aufbau der Angebote läuft ..."),true,true,true,1000)
						{
							@Override
							public void Run_Loop() throws myException
							{
								new build_angebote(this);
							}
							
							@Override
							public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
							{
							}

						};
					}
				}
			}
		}
		
		//2011-01-09: ausgelagert
		private class build_angebote
		{

			public build_angebote(E2_ServerPushMessageContainer   oServerPushContainer) throws myException
			{
				super();
				BSAAL_ButtonBaueAngebote_Container o_This = BSAAL_ButtonBaueAngebote_Container.this;
				
				BSAAL_ButtonBaueAngebote_StatementBuilder 	oPreisBuild = 	new BSAAL_ButtonBaueAngebote_StatementBuilder();
		
				iInfo = oPreisBuild.build_Preise(	vIDs, 
						                            vIdArtikelbezLief,
													o_This.oCalendarGueltigVon.get_oDatumsFeld().getText(),
													o_This.oCalendarGueltigBis.get_oDatumsFeld().getText(),
													o_This.oTextAnfang.get_oTextArea().getText(),
													o_This.oTextEnde.get_oTextArea().getText(),
													oServerPushContainer);

//				oInfo = new MyE2_String("Neue Angebote: ");
//				oInfo.addUnTranslated(""+iInfo[0]+"  ---   ");
//				oInfo.addString(new MyE2_String("Bereits vorhanden: "));
//				oInfo.addUnTranslated(""+iInfo[1]+"  ---   ");
//				oInfo.addString(new MyE2_String("Positionen zugefügt: "));
//				oInfo.addUnTranslated(""+iInfo[3]);
//				
//				bibMSG.add_MESSAGE(new MyE2_Info_Message(oInfo),true);
				
				new InfoPopUp(ownActionAgentStartAngebot.this.iInfo);

				
			}
			
		}
		
	}
	
	
	
	private class InfoPopUp extends E2_MessageBoxYesNo
	{

		public InfoPopUp(int[] iErgebnis) throws myException
		{
			super(	new MyE2_String("Information über die Angebote"), 
					new MyE2_String("OK"), 
					new MyE2_String("Abbruch"), 
					true, 
					false, 
					new InfoGrid(iErgebnis), 
					null, 
					new Extent(300), 
					new Extent(250));
		}

	}
	
	private class InfoGrid extends MyE2_Grid
	{

		public InfoGrid(int[] iErgebnis)
		{
			super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			this.add(new MyE2_Label(new MyE2_String("Neu erzeugte Angebote: "), new E2_FontPlain(2)), 			E2_INSETS.I_2_2_2_2);
			this.add(new MyE2_Label(""+iErgebnis[0], new E2_FontPlain(2)), 										MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));

			this.add(new MyE2_Label(new MyE2_String("Bereits vorhanden: "), new E2_FontPlain(2)),     			E2_INSETS.I_2_2_2_2);
			this.add(new MyE2_Label(""+iErgebnis[1], new E2_FontPlain(2)), 										MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));
			
			this.add(new MyE2_Label(new MyE2_String("Insgesamt erzeugte Positionen: "), new E2_FontPlain(2)), 	E2_INSETS.I_2_2_2_2);
			this.add(new MyE2_Label(""+iErgebnis[3], new E2_FontPlain(2)), 										MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));
		}
		
	}
	
	
	public String getWHERESortenbereich() throws myException {
		return oSortenBlockSelektor.get_WhereBlock();

	}

	public ArrayList<String[]> getArrayOfIds_Sortenbereich() throws myException {
		return oSortenBlockSelektor.get_ArrayOfSelectedValues();
	}

	// 2012-10-15: mehrfachselektion der sorten/sortenbereiche
	private class SelectorSortenVonBis_Multi extends E2_ListSelectorMultiDropDown_von_bis {

		public SelectorSortenVonBis_Multi() throws myException {
			super(bibALL.ReplaceTeilString(
					BSAAL_ButtonBaueAngebote_Container.cBasisQueryMultiBereichSelektor + " ORDER BY 1", "#WHERE#", ""),

					"JT_ADRESSE.ID_ADRESSE IN (SELECT ID_ADRESSE FROM " + bibE2.cTO() + ".JT_ARTIKELBEZ_LIEF "
							+ "JOIN JT_ARTIKEL_BEZ USING (ID_ARTIKEL_BEZ) WHERE\n"
							+ " JT_ARTIKEL_BEZ.ID_ARTIKEL IN (SELECT A.ID_ARTIKEL FROM " + bibE2.cTO()
							+ ".JT_ARTIKEL A WHERE A.ANR1>='#WERT1#' AND A.ANR1<='#WERT2#') AND NVL(ANGEBOT,'-')='Y') ");

		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownContainer();
		}

		@Override
		public void fill_Grid4AnzeigeStatusMulti() {

			this.get_grid4Anzeige().removeAll();
//			this.get_grid4Anzeige().set_Spalten(ATOM_LAG_LIST_Selector.this.iSpaltenGridMain);
			this.get_grid4Anzeige().setSize(5);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_von(), 1, E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(new MyE2_Label(new MyE2_String(" bis ")), 2, E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_bis(), 1, E2_INSETS.I_0_0_0_0);
			this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(), 1, E2_INSETS.I_2_0_0_0);

			this.get_oSelFieldBasis_von().setWidth(new Extent(136));
			this.get_oSelFieldBasis_bis().setWidth(new Extent(136));

		}

		@Override
		public void fill_Grid4AnzeigeStatusSingle() {
			this.fill_Grid4AnzeigeStatusMulti();
		}

		private class ownContainer extends E2_BasicModuleContainer {
			public ownContainer() {
				super();
			}
		}

	}

}
