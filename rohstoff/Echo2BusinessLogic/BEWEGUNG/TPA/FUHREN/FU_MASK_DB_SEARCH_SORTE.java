package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.utils.MyArtikel;
import rohstoff.utils.MyArtikelbezeichung_NG;
import rohstoff.utils.ECHO2.DB_SEARCH_Sorte;

public class FU_MASK_DB_SEARCH_SORTE extends DB_SEARCH_Sorte
{

	private String  				MODULE_IDENIFIER_OF_MASK = 	null;
	
	
	public FU_MASK_DB_SEARCH_SORTE(	SQLField 			osqlField)   throws myException
	{
		super(osqlField);
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());
		this.add_ValidatorStartSearch(new VALID_ADRESS_INPUT());
		this.get_oSeachBlock().set_bAllowEmptySearchField(true);
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().set_iWidth(220);
		this.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontBold());
		this.get_oTextFieldForSearchInput().set_iWidthPixel(60);
		
		
		//2013-01-18: erweiterte Meldung bei der suche nach einer sorte in der fuhre:
		this.set_cMeldungNichtsGefunden(new MyE2_String("Es konnte keine passende Sorte gefunden werden. Evtl. muss die gewünschte Sorte in der Adresse des Lieferanten als EK-Sorte angelegt werden (Adressmaske, Reiter: <Art. EK>)"));
	}

	
	
	private class ownMaskSettings extends XX_MaskActionAfterFound
	{
		
		public void doMaskSettingsAfterValueWrittenInMaskField(String cID_ARTIKEL, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			if (!bAfterAction) return;
			
			String cMaskValue = bibALL.ReplaceTeilString(bibALL.null2leer(cID_ARTIKEL), ".", "");

			/* 
			 * zuerst nachsehen, ob es kontrakte gibt, dann (falls beide seiten einen kontrakt
			 * habe, die maske gar nicht oeffnen, sonst jeweils nur die eine seite anzeigen
			 */
			Long lID_VOPS_KON_EK = FU_MASK_DB_SEARCH_SORTE.this.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_EK", true, true, null);
			Long lID_VOPS_KON_VK = FU_MASK_DB_SEARCH_SORTE.this.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_VK", true, true, null);

			
			E2_RecursiveSearch_MaskInfo oMI = new E2_RecursiveSearch_MaskInfo(oSearchField);
			
			if (lID_VOPS_KON_EK==null)
			{
				new Fuhren_MaskFiller_EK_AfterArtikelPopupSelektion(oMI.get_ComponentMAP("JT_VPOS_TPA_FUHRE").get_REAL_ComponentHashMap(),null,false);          // EK-Bereich loeschen
			}
			if (lID_VOPS_KON_VK==null)
			{
				new Fuhren_MaskFiller_VK_AfterArtikelPopupSelektion(oMI.get_ComponentMAP("JT_VPOS_TPA_FUHRE").get_REAL_ComponentHashMap(),null);          // VK-Bereich loeschen
			}
			
			String cID_Lieferant = bibALL.ReplaceTeilString(bibALL.null2leer(oMI.get_ActualMaskValue("ID_ADRESSE_START")),".","");
			String cID_Abnehmer = bibALL.ReplaceTeilString(bibALL.null2leer(oMI.get_ActualMaskValue("ID_ADRESSE_ZIEL")),".","");
			

			//2013-09-26: wegen steuerfindung werden die steuerfelder auf beiden seiten der fuhre geloescht
			E2_ComponentMAP  oMAP = oSearchField.EXT().get_oComponentMAP();
			oMAP.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_EK).set_cActualMaskValue("");
			oMAP.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_VK).set_cActualMaskValue("");
			oMAP.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_EK).set_cActualMaskValue("");
			oMAP.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue("");
			oMAP.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_EK).set_cActualMaskValue("");
			oMAP.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_VK).set_cActualMaskValue("");

			
			
			
			
			if (bibALL.isInteger(cMaskValue) && bibALL.isInteger(cID_Lieferant) &&  bibALL.isInteger(cID_Abnehmer) )
			{

				// jetzt zuerst feststellen, ob beide seiten eindeutige zuordnung haben
				String[][] cArtBezEK = bibDB.EinzelAbfrageInArray(new BuildQueryString(cMaskValue, "EK",cID_Lieferant).get_cQuery());
				String[][] cArtBezVK = bibDB.EinzelAbfrageInArray(new BuildQueryString(cMaskValue, "VK",cID_Abnehmer).get_cQuery());
				
				if (cArtBezEK==null || cArtBezVK==null || cArtBezEK.length==0)
				{
					DEBUG.System_println(new BuildQueryString(cMaskValue, "EK",cID_Lieferant).get_cQuery(), DEBUG.DEBUG_FLAG_SQL_QUERY);
					DEBUG.System_println(new BuildQueryString(cMaskValue, "VK",cID_Abnehmer).get_cQuery(), DEBUG.DEBUG_FLAG_SQL_QUERY);
					throw new myException("FU_MASK_DB_SEARCH_SORTE_Fuhre:ownMaskSettings:Error Quering Artbez EK/VK");
				}
				
				//den modulkenner der rufendenden maske suchen (Fuhre / fahrplan usw) fuer die validierung oben
				E2_RecursiveSearchParent_BasicModuleContainer oRCS = new E2_RecursiveSearchParent_BasicModuleContainer(oSearchField);
				String cMODULKENNER = oRCS.get_First_FoundContainer_With_MODUL_IDENTIFIER().get_MODUL_IDENTIFIER();
				
				new PopUpToSelectArtBez(cID_ARTIKEL,cID_Lieferant,cID_Abnehmer,oSearchField,oMI.get_ComponentMAP("JT_VPOS_TPA_FUHRE").get_REAL_ComponentHashMap(),cMODULKENNER);
			}
		}
	}

	
	//NEU_09
	private class BuildQueryString
	{
		private String cQuery = null;

		public BuildQueryString(String cID_SORTE, String cEK_VK, String cID_ADRESSE) throws myException 
		{
			super();

			this.cQuery = "SELECT JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ,JT_ARTIKEL.ANR1,JT_ARTIKEL_BEZ.ANR2 FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL " +
									"  WHERE  JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL "+
									"  AND      NVL(JT_ARTIKEL.AKTIV,'N')='Y' "+
									"  AND      NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' "+
									"  AND    JT_ARTIKEL_BEZ.ID_ARTIKEL="+cID_SORTE+ " ORDER BY ANR1,ANR2 ";

			if (!  ( bibALL.isEmpty(cID_ADRESSE) || bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--").equals(cID_ADRESSE) ) )
			{
				this.cQuery = "SELECT JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ,JT_ARTIKEL.ANR1,JT_ARTIKEL_BEZ.ANR2 FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF,"+bibE2.cTO()+".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL " +
									"  WHERE  JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ " +
									"  AND    JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL "+
									"  AND      NVL(JT_ARTIKEL.AKTIV,'N')='Y' "+
									"  AND      NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' "+
									"  AND    JT_ARTIKEL_BEZ.ID_ARTIKEL="+cID_SORTE+
									"  AND    JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP='"+cEK_VK+"' "+
									"  AND    JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE+ " ORDER BY ANR1,ANR2 ";
			}

			
		}

		public String get_cQuery() {return this.cQuery;}
		
	}
	
	
	
	//NEU_09
	
	// popup-fenster zum definieren der sortenbezeichner, die in die maske geladen werden sollen
	private class PopUpToSelectArtBez extends E2_BasicModuleContainer 
	{
		private String cID_ADRESSE_LIEFERANT = null;
		private String cID_ADRESSE_ABNEHMER = null;
		
		private Long   lID_VOPS_KON_EK = null;
		private Long   lID_VOPS_KON_VK = null;
		
		private MyE2_DB_MaskSearchField 				oSearchFieldSorte =null;
		private Vector<MyE2_CheckBox>   				vEKSortenCheckboxen = new Vector<MyE2_CheckBox>();
		private Vector<MyE2_CheckBox>					vVKSortenCheckboxen = new Vector<MyE2_CheckBox>();
		private HashMap<String,MyE2IF__Component>  		ohmRealComponents = null;
		
		public PopUpToSelectArtBez(	String 								cid_artikel, 	
									String 								cid_adresse_lieferant, 
									String 								cid_adresse_abnehmer,
									MyE2_DB_MaskSearchField 			SearchFieldSorte,
									HashMap<String,MyE2IF__Component> 	hmRealComponents,
									String cMODUL_IDENTIFIER_OF_MASK   ) throws myException
		{
			super();
			
			/* 
			 * zuerst nachsehen, ob es kontrakte gibt, dann (falls beide seiten einen kontrakt
			 * habe, die maske gar nicht oeffnen, sonst jeweils nur die eine seite anzeigen
			 */
			this.lID_VOPS_KON_EK = FU_MASK_DB_SEARCH_SORTE.this.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_EK", true, true, null);
			this.lID_VOPS_KON_VK = FU_MASK_DB_SEARCH_SORTE.this.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_VK", true, true, null);

			//falls beide kontrakte vorhanden sind, dann wird gar nix gemacht, nur plausi-check
			if (this.lID_VOPS_KON_EK != null && this.lID_VOPS_KON_VK != null)
			{
				new FU__MaskSettingsAfterInput__Plausibilitaet(FU_MASK_DB_SEARCH_SORTE.this);
				return;
			}
			
			cID_ADRESSE_LIEFERANT = 	cid_adresse_lieferant;
			cID_ADRESSE_ABNEHMER = 		cid_adresse_abnehmer;
			this.oSearchFieldSorte = 	SearchFieldSorte;
			this.ohmRealComponents =    hmRealComponents;	
			
			FU_MASK_DB_SEARCH_SORTE.this.MODULE_IDENIFIER_OF_MASK = cMODUL_IDENTIFIER_OF_MASK;
			
			RowLayoutData oRowLayout = new RowLayoutData();
			oRowLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			oRowLayout.setInsets(E2_INSETS.I_5_5_5_5);
			
			
			//EK-sorten-gelistet
			String[][] cArtikelbezEK =  bibDB.EinzelAbfrageInArray(new BuildQueryString(cid_artikel,"EK",cid_adresse_lieferant).get_cQuery());
			//VK-sorten - gelistet
			String[][] cArtikelbezVK1 = bibDB.EinzelAbfrageInArray(new BuildQueryString(cid_artikel,"VK",cid_adresse_abnehmer).get_cQuery());
			//VK-sorten - ungelistet
			String[][] cArtikelbezVK2 = bibDB.EinzelAbfrageInArray(new BuildQueryString(cid_artikel,"VK",null).get_cQuery());

			if (cArtikelbezEK==null || cArtikelbezEK.length==0 || cArtikelbezVK1==null || cArtikelbezVK2==null || cArtikelbezVK2.length==0)
			{
				DEBUG.System_println(new BuildQueryString(cid_artikel,"EK",cid_adresse_lieferant).get_cQuery(),DEBUG.DEBUG_FLAG_SQL_ERROR);
				DEBUG.System_println(new BuildQueryString(cid_artikel,"VK",cid_adresse_abnehmer).get_cQuery(), DEBUG.DEBUG_FLAG_SQL_ERROR);
				DEBUG.System_println(new BuildQueryString(cid_artikel,"VK",null).get_cQuery(), DEBUG.DEBUG_FLAG_SQL_ERROR);
				throw new myException("FU_MASK_DB_SEARCH_SORTE_Fuhre:PopUpToSelectArtBez:Error Querying ArtBez");
			}
			
			MyE2_Row 		oRowBasic = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			MyE2_Column     oColEK    = new MyE2_Column( MyE2_Column.STYLE_THIN_BORDER() );
			MyE2_Column     oColVK    = new MyE2_Column( MyE2_Column.STYLE_THIN_BORDER() );

			MyE2_Row   		oRowEK    = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			MyE2_Row   		oRowVK    = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
			this.add(oRowBasic, E2_INSETS.I_5_5_5_5);
			oRowBasic.add(oColEK, oRowLayout);
			oRowBasic.add(oColVK, oRowLayout);
			
			oColEK.add(new MyE2_Label(new MyE2_String("EK-Sortenbezeichner")), E2_INSETS.I_2_2_10_2);
			oColVK.add(new MyE2_Label(new MyE2_String("VK-Sortenbezeichner")), E2_INSETS.I_2_2_10_2);
			oColEK.add(oRowEK, E2_INSETS.I_2_2_10_2);
			oColVK.add(oRowVK, E2_INSETS.I_2_2_10_2);
			
			
			ActionAgent_RadioFunction_CheckBoxList oRB_EK= new ActionAgent_RadioFunction_CheckBoxList(true);
			ActionAgent_RadioFunction_CheckBoxList oRB_VK= new ActionAgent_RadioFunction_CheckBoxList(true);
			
//			int iGridWithEK = (int) ((float)cArtikelbezEK.length/30); iGridWithEK++;
//			int iGridWithVK = (int) ((float)cArtikelbezVK2.length/30); iGridWithVK++;

			int iAnzahlUmbruch = 15;
			
			MyE2_Column oColHelp = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER() );
			
			boolean bStandardGesetzt = false;
			for (int i=0;i<cArtikelbezEK.length;i++)
			{
				MyE2_CheckBox oCBHelp = this.get_CheckBoxArtBez(cArtikelbezEK[i][0],oRB_EK, null);
				this.vEKSortenCheckboxen.add(oCBHelp);
				oColHelp.add(oCBHelp,  E2_INSETS.I_2_2_2_2);
				if (!bStandardGesetzt)                   // der erste, gelistete wird vorbesetzt
				{
					oCBHelp.setSelected(true);
					bStandardGesetzt = true;
				}
				
				if ((i+1) % iAnzahlUmbruch == 0)
				{
					oRowEK.add(oColHelp,oRowLayout);
					oColHelp = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER() );
				}
			}
			oRowEK.add(oColHelp, oRowLayout);
			if (this.lID_VOPS_KON_EK!=null)           //falls ein EK-Kontrakt vorhanden ist, die EK-seite ausblenden, da die sortenbez. feststeht
			{
				oColEK.setVisible(false);
			}
			
			
			
			Vector<String> vHelpVKZuordnungen = new Vector<String>();

			/*
			 * aenderung 2010-12-17
			 * new BuildQueryString(cid_artikel,"VK",cid_adresse_abnehmer) liefert beim mandant als abnehmer alle sorten, deshalb wuerden in  diesem fall alle
			 * selektionen Fettgedruckt werden, was irritiert. deshalb wird dann nix mehr fett (bei der adresse des mandanten) 
			 */
			if (!bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--").equals(cid_adresse_abnehmer))
			{
				for (int i=0;i<cArtikelbezVK1.length;i++)
				{
					vHelpVKZuordnungen.add(cArtikelbezVK1[i][0]);
				}
			}
			bStandardGesetzt = false;
			oColHelp = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER() );
			for (int i=0;i<cArtikelbezVK2.length;i++)
			{
				
				MyE2_CheckBox oCBHelp = this.get_CheckBoxArtBez(cArtikelbezVK2[i][0],oRB_VK, vHelpVKZuordnungen);
				this.vVKSortenCheckboxen.add(oCBHelp);
				oColHelp.add(oCBHelp,  E2_INSETS.I_2_2_2_2);
				if (!bStandardGesetzt && vHelpVKZuordnungen.contains(cArtikelbezVK2[i][0]))
				{
					oCBHelp.setSelected(true);
					bStandardGesetzt = true;
				}

				
				if ((i+1) % iAnzahlUmbruch == 0)
				{
					oRowVK.add(oColHelp, oRowLayout);
					oColHelp = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER() );
				}
			}

			// aenderung 2010-12-17
			// wenn die zieladresse der mandant ist, dann immer die erste VK-Sortenbezeichung anhaken
			if (bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--").equals(cid_adresse_abnehmer))
			{
				if (this.vVKSortenCheckboxen.size()>0)
				{
					this.vVKSortenCheckboxen.get(0).setSelected(true);
				}
			}

			
			
			oRowVK.add(oColHelp, oRowLayout);
			if (this.lID_VOPS_KON_VK!=null)           //falls ein VK-Kontrakt vorhanden ist, die VK-seite ausblenden, da die sortenbez. feststeht
			{
				oColVK.setVisible(false);
			}
			

			// save- und cancel-buttons
			MyE2_Button oButtonSave = 		new MyE2_Button(new MyE2_String("Speichern"));
			MyE2_Button oButtonCancel = 	new MyE2_Button(new MyE2_String("Abbrechen"));
			
			E2_ComponentGroupHorizontal oHelpGroup = new E2_ComponentGroupHorizontal(0,oButtonSave,oButtonCancel,E2_INSETS.I_10_5_0_0);
			
			this.set_Component_To_ButtonPane(oHelpGroup);

			oButtonSave.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) 
				{
					PopUpToSelectArtBez oThis = PopUpToSelectArtBez.this;
					
					// die angeklickten suchen
					String cID_ARTIKEL_BEZ_EK = null;
					String cID_ARTIKEL_BEZ_VK = null;
					for (int i=0;i<oThis.vEKSortenCheckboxen.size();i++)
					{
						MyE2_CheckBox oCB = (MyE2_CheckBox)oThis.vEKSortenCheckboxen.get(i);
						if (oCB.isSelected())
							cID_ARTIKEL_BEZ_EK = oCB.EXT().get_C_MERKMAL();
					}
					for (int i=0;i<oThis.vVKSortenCheckboxen.size();i++)
					{
						MyE2_CheckBox oCB = (MyE2_CheckBox)oThis.vVKSortenCheckboxen.get(i);
						if (oCB.isSelected())
							cID_ARTIKEL_BEZ_VK = oCB.EXT().get_C_MERKMAL();
					}
			
					
					try
					{
						if (oThis.lID_VOPS_KON_EK == null)
						{
							//aenderung 2010-12-17: mandant kann alle sorten laden
							//der schalter must be listed nur wenn der lieferant nicht der mandant ist
							boolean bMustBeListed = !(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("").equals(oThis.cID_ADRESSE_LIEFERANT));
							//20170825: cID_ARTIKEL_BEZ_EK==null fuehrt zu exception MyArtikelbezeichung_NG oArtBezEK = new MyArtikelbezeichung_NG(cID_ARTIKEL_BEZ_EK,oThis.cID_ADRESSE_LIEFERANT,"EK",bMustBeListed);
							if (cID_ARTIKEL_BEZ_EK == null)
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte eine EK-Bezeichnung wählen !"));
								return;
							}
							//20170825:
							MyArtikelbezeichung_NG oArtBezEK = new MyArtikelbezeichung_NG(cID_ARTIKEL_BEZ_EK,oThis.cID_ADRESSE_LIEFERANT,"EK",bMustBeListed);

							boolean bLieferantIstMandant = !bMustBeListed;
							new Fuhren_MaskFiller_EK_AfterArtikelPopupSelektion(oThis.ohmRealComponents,oArtBezEK, bLieferantIstMandant);
						}
						if (oThis.lID_VOPS_KON_VK == null)
						{
							//20170825: cID_ARTIKEL_BEZ_VK==null MyArtikelbezeichung_NG oArtBezVK = new MyArtikelbezeichung_NG(cID_ARTIKEL_BEZ_VK,oThis.cID_ADRESSE_ABNEHMER,"VK",false);
							if (cID_ARTIKEL_BEZ_VK == null)
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte eine VK-Bezeichnung wählen !"));
								return;
							}
							//20170825:
							MyArtikelbezeichung_NG oArtBezVK = new MyArtikelbezeichung_NG(cID_ARTIKEL_BEZ_VK,oThis.cID_ADRESSE_ABNEHMER,"VK",false);
							new Fuhren_MaskFiller_VK_AfterArtikelPopupSelektion(oThis.ohmRealComponents,oArtBezVK);
						}
						

						oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						
						new FU__MaskSettingsAfterInput__Plausibilitaet(FU_MASK_DB_SEARCH_SORTE.this);
					}
					catch (myException ex)
					{
						ex.printStackTrace();
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
						return;
					}
					
				}
			});

			oButtonCancel.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					PopUpToSelectArtBez oThis = PopUpToSelectArtBez.this;
					
					oThis.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
			
			
			
			this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
			{
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					if (PopUpToSelectArtBez.this.get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL))
							PopUpToSelectArtBez.this.oSearchFieldSorte.prepare_ContentForNew(false);

				}
			});

			
			

			this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(970), new Extent(600),new MyE2_String("Bitte wählen Sie die Sortenbezeichner :"));
		}
		
		
		
		
		
		//checkbox die in der popup-maske benutzt wird, um die gewuenschten artikelbezeichungen zu laden
		private MyE2_CheckBox get_CheckBoxArtBez(	String cID_ARTBEZ,
													ActionAgent_RadioFunction_CheckBoxList oActionAgent_RadioButton_CheckBox,
													Vector<String> vBoldWhenContains) throws myException
		{
			MyE2_CheckBox oCB = new MyE2_CheckBox();
			
			String cQuery = "SELECT  JT_ARTIKEL.ANR1||'-'||JT_ARTIKEL_BEZ.ANR2||' '||JT_ARTIKEL_BEZ.ARTBEZ1 " +					
								"  FROM " +
								bibE2.cTO()+".JT_ARTIKEL_BEZ, "+
								bibE2.cTO()+".JT_ARTIKEL " +
								"  WHERE " +
								" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  AND " +
								" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+cID_ARTBEZ;
			
			String[][] cBezeichung = bibDB.EinzelAbfrageInArray(cQuery);
			
			if (cBezeichung == null || cBezeichung.length!=1)
				throw new myException("FU_MASK_DB_SEARCH_SORTE_Fuhre:get_CheckBoxArtBez:Error Querying ArtBez:"+cQuery);
			
			oCB.setText(cBezeichung[0][0]);
			oCB.EXT().set_C_MERKMAL(cID_ARTBEZ);
			oCB.setFont(new E2_FontPlain(-2));
			if (vBoldWhenContains != null && vBoldWhenContains.contains(cID_ARTBEZ))
				oCB.setFont(new E2_FontBold(-2));
		
			oCB.add_GlobalValidator(new E2_ButtonAUTHValidator(FU_MASK_DB_SEARCH_SORTE.this.MODULE_IDENIFIER_OF_MASK,"FREIE_AUSWAHL_SORTENBEZEICHNUNG"));
			
			oActionAgent_RadioButton_CheckBox.add_CheckBox(oCB);
			return oCB;
		}
	}
	

	
	
	private class Fuhren_MaskFiller_EK_AfterArtikelPopupSelektion
	{
		public Fuhren_MaskFiller_EK_AfterArtikelPopupSelektion(HashMap<String,MyE2IF__Component> hmRealCompents,MyArtikelbezeichung_NG  ArtBezEK, boolean bLieferantIstMandant)  throws myException
		{
			super();
			
			// zuerst alles leermachen
			// artikeldefinierte werte setzen
			((MyE2IF__DB_Component)hmRealCompents.get("EUCODE")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("EUNOTIZ")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("BASEL_CODE")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("BASEL_NOTIZ")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ZOLLTARIFNR")).prepare_ContentForNew(false);
			((FU_MASK_DB_COMBO_MengenEinheit)hmRealCompents.get("EINHEIT_MENGEN")).prepare_ContentForNew(false);
			
			((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ1_EK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ2_EK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ID_ARTIKEL_BEZ_EK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ANR1_EK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ANR2_EK")).prepare_ContentForNew(false);
			
			MASK_COMPONENT_SEARCH_EAK_CODES  oFieldEAK_Code = (MASK_COMPONENT_SEARCH_EAK_CODES)hmRealCompents.get("ID_EAK_CODE");
			oFieldEAK_Code.prepare_ContentForNew(false);
			
		
			if (ArtBezEK != null )
			{
				
				MyArtikel oArt = new MyArtikel(ArtBezEK.get_ID_ARTIKEL());
				
				// artikeldefinierte werte setzen
				((MyE2IF__DB_Component)hmRealCompents.get("EUCODE")).set_cActualMaskValue(oArt.get_EUCODE());
				((MyE2IF__DB_Component)hmRealCompents.get("EUNOTIZ")).set_cActualMaskValue(oArt.get_EUNOTIZ());
				((MyE2IF__DB_Component)hmRealCompents.get("BASEL_CODE")).set_cActualMaskValue(oArt.get_BASEL_CODE());
				((MyE2IF__DB_Component)hmRealCompents.get("BASEL_NOTIZ")).set_cActualMaskValue(oArt.get_BASEL_NOTIZ());
				((MyE2IF__DB_Component)hmRealCompents.get("ZOLLTARIFNR")).set_cActualMaskValue(oArt.get_ZOLLTARIFNR());
				((FU_MASK_DB_COMBO_MengenEinheit)hmRealCompents.get("EINHEIT_MENGEN")).set_cActualMaskValue(oArt.get_EINHEIT());

				
				// jetzt ergebnisse eintragen
				((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ1_EK")).set_cActualMaskValue(ArtBezEK.get_ARTBEZ1());
				((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ2_EK")).set_cActualMaskValue(ArtBezEK.get_ARTBEZ2());
				((MyE2IF__DB_Component)hmRealCompents.get("ID_ARTIKEL_BEZ_EK")).set_cActualMaskValue(ArtBezEK.get_cID_ARTIKEL_BEZ());
				((MyE2_DB_MaskSearchField)hmRealCompents.get("ID_ARTIKEL_BEZ_EK")).FillLabelWithDBQuery(ArtBezEK.get_cID_ARTIKEL_BEZ());
				
				((MyE2IF__DB_Component)hmRealCompents.get("ANR1_EK")).set_cActualMaskValue(ArtBezEK.get_ANR1());
				((MyE2IF__DB_Component)hmRealCompents.get("ANR2_EK")).set_cActualMaskValue(ArtBezEK.get_ANR2());

				//laden des AVV-codes
				new FU___Fill_EAK_Code(ArtBezEK,oFieldEAK_Code,bLieferantIstMandant);
					
			}
		}
	}
	

	
	private class Fuhren_MaskFiller_VK_AfterArtikelPopupSelektion
	{
		public Fuhren_MaskFiller_VK_AfterArtikelPopupSelektion(HashMap<String,MyE2IF__Component> hmRealCompents,MyArtikelbezeichung_NG  ArtBezVK)  throws myException
		{
			super();
			
			((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ1_VK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ2_VK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ID_ARTIKEL_BEZ_VK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ANR1_VK")).prepare_ContentForNew(false);
			((MyE2IF__DB_Component)hmRealCompents.get("ANR2_VK")).prepare_ContentForNew(false);
	
			if (ArtBezVK != null)
			{
				
				((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ1_VK")).set_cActualMaskValue(ArtBezVK.get_ARTBEZ1());
				((MyE2IF__DB_Component)hmRealCompents.get("ARTBEZ2_VK")).set_cActualMaskValue(ArtBezVK.get_ARTBEZ2());
				((MyE2IF__DB_Component)hmRealCompents.get("ID_ARTIKEL_BEZ_VK")).set_cActualMaskValue(ArtBezVK.get_ID_ARTIKEL_BEZ());
				((MyE2_DB_MaskSearchField)hmRealCompents.get("ID_ARTIKEL_BEZ_VK")).FillLabelWithDBQuery(ArtBezVK.get_cID_ARTIKEL_BEZ());
				((MyE2IF__DB_Component)hmRealCompents.get("ANR1_VK")).set_cActualMaskValue(ArtBezVK.get_ANR1());
				((MyE2IF__DB_Component)hmRealCompents.get("ANR2_VK")).set_cActualMaskValue(ArtBezVK.get_ANR2());
				
			}
		}
	}

	
	
	
	/**
	 * @author martin
	 * Validator hat 2 aufgaben:
	 * 1. muss sicherstellen, dass korrekte adress-ids in start- und ziel - adresse angegeben sind 
	 * 2. fuegt zusatz-bedingung bei der suche ein, die nur noch sorten zulaesst, die in der kundenspezifischen
	 *    artikelbezeichnung vorhanden sind.
	 */
	private class VALID_ADRESS_INPUT extends XX_ActionValidator
	{
		private MyE2_String cError = new MyE2_String("");
		
		public MyE2_Message get_ErrorMessage()
		{
			return new MyE2_Message(cError,MyE2_Message.TYP_ALARM,false);
		}

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			boolean bRueck = false;
			
			FU_MASK_DB_SEARCH_SORTE oThis = FU_MASK_DB_SEARCH_SORTE.this;
			
			E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();
			
			try
			{
				String cID_ADRESSE_START = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMap.get__Comp("ID_ADRESSE_START")).get_cActualMaskValue(),".","");
				String cID_ADRESSE_ZIEL = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMap.get__Comp("ID_ADRESSE_ZIEL")).get_cActualMaskValue(),".","");
				
				if (!bibALL.isInteger(cID_ADRESSE_START)  || !bibALL.isInteger(cID_ADRESSE_ZIEL))
				{
					this.cError = new MyE2_String("Bitte geben Sie zuerst die Adressen (START und ZIEL) ein !!");
				}
				else
				{
					bRueck = true;

					//NEU_09  anderer Wherezusatz         
					String cWhereZusatz = 
								"  NVL(JT_ARTIKEL.AKTIV,'N')='Y' AND JT_ARTIKEL.ID_ARTIKEL IN (SELECT DISTINCT JT_ARTIKEL_BEZ.ID_ARTIKEL FROM "+
								bibE2.cTO()+".JT_ARTIKELBEZ_LIEF," +
								bibE2.cTO()+".JT_ARTIKEL_BEZ" +
										"  WHERE " +
								" JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
								" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE_START+" AND "+
								" JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP='EK' AND "+
								"   NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y'"+
								")";
					
					//aenderung 2010-12-17: wenn die startadresse der mandant ist, alle sorten zulassen, die 
					//                      einen AVV-code ex mandant oder ein produkt/dienstleistung sind
//CODE:AVWRTZ					
					if (cID_ADRESSE_START.equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
					{
						cWhereZusatz = 
							"  NVL(JT_ARTIKEL.AKTIV,'N')='Y'" +
							" AND " +
							" (JT_ARTIKEL.ID_ARTIKEL IN (SELECT DISTINCT JT_ARTIKEL_BEZ.ID_ARTIKEL FROM "+
							   bibE2.cTO()+".JT_ARTIKELBEZ_LIEF," +
							   bibE2.cTO()+".JT_ARTIKEL_BEZ" +
							   "  WHERE " +
							   " JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
							   " JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE_START+" AND "+
							   " JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP='EK' AND "+
							   "   NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y'"+
							                             ")" +
						            " OR" +
							   " JT_ARTIKEL.ID_ARTIKEL IN " +
							   "	(SELECT ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE NVL(AKTIV,'N')='Y' AND " +
							   " (ID_EAK_CODE_EX_MANDANT IS NOT NULL OR NVL(DIENSTLEISTUNG,'N')='Y' OR NVL(IST_PRODUKT,'N')='Y' OR NVL(END_OF_WASTE,'N')='Y'))" +
							   ")";
					}
					//ende aenderung 2010-12-17	
					
					
					oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
					oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cWhereZusatz);
				}
				
			}
			catch (myException ex)
			{
				this.cError = new MyE2_String("Fehler beim Pruefen der Adressenfelder !!");
			}
			if (!bRueck)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
			}
			return oMV;
		}

		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			return new MyE2_MessageVector();
		}
	}
	
	
}
