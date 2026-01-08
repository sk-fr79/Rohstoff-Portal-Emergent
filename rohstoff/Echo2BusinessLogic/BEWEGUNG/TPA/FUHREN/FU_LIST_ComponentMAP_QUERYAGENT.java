package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ColorFactory;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.FUB___LIST_BT_Buchung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP_ButtonUmbuchen_in_LISTE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__SortButtonInList;
 
public class FU_LIST_ComponentMAP_QUERYAGENT extends XX_ComponentMAP_SubqueryAGENT
{ 
 
	private E2_NavigationList   oNaviList = null;
	private String 				oBASIC_MODULE_NAME = null;
	
	public FU_LIST_ComponentMAP_QUERYAGENT(E2_NavigationList  NaviList, String BASIC_MODULE_NAME) throws myException
	{
		super();
		this.oNaviList = NaviList;
		this.oBASIC_MODULE_NAME = BASIC_MODULE_NAME;
	}

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException
	{
		try
		{
			HashMap<String, MyE2IF__Component> hmRealComponents = oMAP.get_REAL_ComponentHashMap();
			((MyE2_Label)hmRealComponents.get("RECHADRESSE_LIEFERANT")).setText("");
			((MyE2_Label)hmRealComponents.get("RECHADRESSE_ABNEHMER")).setText("");
		}
		catch (Exception ex)
		{
			throw new myException("MAP_QUERYAGENT_InfoFields:clearComponents:Error clearing infolabels: ");
		}

	}

	public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException
	{
		try
		{
			HashMap<String, MyE2IF__Component>  hmRealComponents = oMAP.get_hmRealComponents();
			
			//nachsehen, ob es zusatzorte gibt
			boolean bZusatzOrteVorhanden = !oUsedResultMAP.get_UnFormatedValue("AA_ANZAHL_ZUSATZORTE").equals("0");

			
			
			/*
			 * nachsehen, ob die adressen-id und die lager-id uebereinstimmen, wenn ja, nix einblenden,
			 * wenn nein, die rechnungs/gutschriftsadresse laden 
			 */
			String cID_VPOS_TPA_FUHRE = 		oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
			String cID_ADRESSE_LAGER_START = 	oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_LAGER_START");
			String cID_ADRESSE_START = 			oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_START");
			String cID_ADRESSE_LAGER_ZIEL = 	oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_LAGER_ZIEL");
			String cID_ADRESSE_ZIEL = 			oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_ZIEL");
			
			// init
			((MyE2_Label)hmRealComponents.get("RECHADRESSE_LIEFERANT")).setText("<leer>");
			((MyE2_Label)hmRealComponents.get("RECHADRESSE_ABNEHMER")).setText("<leer>");
		
			//wenn leer dann grau
			((MyE2_Label)hmRealComponents.get("RECHADRESSE_LIEFERANT")).setForeground(E2_ColorFactory.get_COLOR(80, 80, 80));
			((MyE2_Label)hmRealComponents.get("RECHADRESSE_ABNEHMER")).setForeground(E2_ColorFactory.get_COLOR(80, 80, 80));
			
			String cINFO_Query = "SELECT   NVL(NAME1,'-')||' - '||  NVL(ORT,'-') FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE=";
			if (!cID_ADRESSE_LAGER_START.equals(cID_ADRESSE_START))
			{
				//2014-03-02: aenderung in E2_ComponentMAP
				//alt: String cInfo = oMAP.get_oDB().EinzelAbfrage(cINFO_Query+cID_ADRESSE_START);
				String cInfo = bibDB.EinzelAbfrage(cINFO_Query+cID_ADRESSE_START);
				((MyE2_Label)hmRealComponents.get("RECHADRESSE_LIEFERANT")).setText(cInfo);
				((MyE2_Label)hmRealComponents.get("RECHADRESSE_LIEFERANT")).setForeground(E2_ColorFactory.get_COLOR(0, 0, 0));
			}
			if (!cID_ADRESSE_LAGER_ZIEL.equals(cID_ADRESSE_ZIEL))
			{
				//2014-03-02: aenderung in E2_ComponentMAP
				//ALT: String cInfo = oMAP.get_oDB().EinzelAbfrage(cINFO_Query+cID_ADRESSE_ZIEL);
				String cInfo = bibDB.EinzelAbfrage(cINFO_Query+cID_ADRESSE_ZIEL);
				((MyE2_Label)hmRealComponents.get("RECHADRESSE_ABNEHMER")).setText(cInfo);
				((MyE2_Label)hmRealComponents.get("RECHADRESSE_ABNEHMER")).setForeground(E2_ColorFactory.get_COLOR(0, 0, 0));
			}
			
			// nachschauen, ob eine transportrechnung eingegangen ist
			String cCount = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN LEFT OUTER JOIN JT_FUHREN_KOSTEN_TYP " +
												" ON (JT_VPOS_TPA_FUHRE_KOSTEN.ID_FUHREN_KOSTEN_TYP=JT_FUHREN_KOSTEN_TYP.ID_FUHREN_KOSTEN_TYP) " +
												" WHERE" +
												" NVL(JT_VPOS_TPA_FUHRE_KOSTEN.DELETED,'N')='N' AND " +
												" NVL(JT_FUHREN_KOSTEN_TYP.SPEDITIONSRECHNUNG,'N')='Y' AND " +
												" ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE).trim();
			
			if (cCount.equals("1"))
			{
				((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_RECHNUNGEINGANG)).setIcon(E2_ResourceIcon.get_RI("geld.png"));
			}
			else if (cCount.equals("0"))
			{
				((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_RECHNUNGEINGANG)).setIcon(E2_ResourceIcon.get_RI("listlabel_empty.png"));
			}
			else
				((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_RECHNUNGEINGANG)).setIcon(E2_ResourceIcon.get_RI("error.png"));
				

			
			MyE2_Column_IMMER_ENABLED  oColHelp =((MyE2_Column_IMMER_ENABLED)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_BUCHUNG_FERTIG));
			oColHelp.removeAll();
			
			String cBuchungsstatus = oUsedResultMAP.get_UnFormatedValue("STATUS_BUCHUNG");
			if (bibALL.null2leer(cBuchungsstatus).equals(""))
				cBuchungsstatus = "---";
			
			//2013-01-24: fuhrenpruefung bei ungebuchten fuhren mit anzeigen
			boolean bFuhreGeschlossen = (S.NN(oUsedResultMAP.get_UnFormatedValue(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE)).equals("Y"));
			
			
			if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__IST_STORNIERT))
			{
				//2013-02-05: aenderung: statt storno-Label wird der storno-info-button eingeblendet
//				MyE2_Label  labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_fuhre_status_storno.png"));
//				labelHelp.setToolTipText(new MyE2_String("Die Fuhre wurde storniert und kann nicht gebucht werden !").CTrans());
//				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
				
				FU___ButtonStornoInfo  oBT_StornoInfo = new FU___ButtonStornoInfo(cID_VPOS_TPA_FUHRE);
				oBT_StornoInfo.setToolTipText(new MyE2_String("Die Fuhre wurde storniert und kann nicht gebucht werden !").CTrans());
				oColHelp.add(oBT_StornoInfo, E2_INSETS.I_0_0_0_0);
				
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT))
			{
				MyE2_Label  labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_vollstaendig_weil_alt.png"));
				labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist aus dem Archivbestand und kann nicht gebucht werden !").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG))
			{
				MyE2_Label  labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_fuhre_ist_unvollstaendig.png"));
				labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist noch nicht komplett ausgefüllt ").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__UNGEBUCHT))
			{	
				FUB___LIST_BT_Buchung  buttonHelp = new FUB___LIST_BT_Buchung(this.oNaviList,true);
				buttonHelp.EXT().set_C_MERKMAL(oUsedResultMAP.get_cUNFormatedROW_ID());
				buttonHelp.setIcon(E2_ResourceIcon.get_RI("buchung_fuhre_ungebucht.png"));
				oColHelp.add(buttonHelp, E2_INSETS.I_0_0_0_0);
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS) && !bFuhreGeschlossen)
			{
				MyE2_Label  labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_hat_keine_buchungspositionen.png"));
				labelHelp.setToolTipText(new MyE2_String("Die Fuhre hat keine Buchungs-Positionen: kann nicht gebucht werden ! ACHTUNG! FUHRE IST NOCH NICHT GESCHLOSSEN").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS) && bFuhreGeschlossen)
			{
				MyE2_Label  labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_hat_keine_buchungspositionen_gruen.png"));
				labelHelp.setToolTipText(new MyE2_String("Die Fuhre hat keine Buchungs-Positionen: kann nicht gebucht werden ! Die Fuhre ist geschlossen.").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
			{
				FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE  labelHelp = 
						new FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE(this.oNaviList,E2_ResourceIcon.get_RI("buchung_vollstaendig.png"), cID_VPOS_TPA_FUHRE);
				labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist vollständig verbucht -> Kann hiermit wieder geöffnet werden !").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT))
			{
				FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE  labelHelp = 
					new FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE(this.oNaviList,E2_ResourceIcon.get_RI("buchung_teilweise.png"), cID_VPOS_TPA_FUHRE);
				labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist teilweise verbucht -> Kann hiermit wieder geöffnet werden !").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			else
			{
				MyE2_Label  labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_empty.png"));
				labelHelp.setToolTipText(new MyE2_String("Buchungsstatus der Fuhre ist unbestimmt").CTrans());
				oColHelp.add(labelHelp, E2_INSETS.I_0_0_0_0);
			}
			
			/*
			 * jetzt geloeschte datensaetze markieren
			 */
			if (oUsedResultMAP.get_FormatedValue("DELETED").equals("Y"))
			{
				oMAP.set_AllComponentsAsDeleted();
			}
			
			boolean bGefahr = false;

			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			
			if (recFuhre.get_UP_RECORD_EAK_CODE_id_eak_code()!=null)
			{
				bGefahr = recFuhre.get_UP_RECORD_EAK_CODE_id_eak_code().is_GEFAEHRLICH_YES();
			}
			bGefahr = bGefahr || recFuhre.get_EUCODE_cUF_NN("").startsWith("A");
			bGefahr = bGefahr || recFuhre.get_BASEL_CODE_cUF_NN("").startsWith("A");
			if (recFuhre.get_UP_RECORD_ARTIKEL_id_artikel()!=null)
			{
				bGefahr = bGefahr || recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().is_GEFAHRGUT_YES();
			}
			
			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_GEFAHR_1)).setToolTipText("");
			if (bGefahr)
			{
				((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_GEFAHR_1)).setIcon(E2_ResourceIcon.get_RI("warnschild_16white.png"));
				((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_GEFAHR_1)).setToolTipText(new MyE2_String("Fuhre enthält gefährliche/begleitscheinpflichtige Sorte oder Gefahrgut !").CTrans());
			}
			else
				((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_GEFAHR_1)).setIcon(E2_ResourceIcon.get_RI("empty10.png"));


			boolean bStorno = false;
			
			//((BST__ButtonStornoInfo)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_STORNO))._INIT(oUsedResultMAP.get_cUNFormatedROW_ID());
			

			
			
			//NEU_09  -  tooltips reinschreiben
//			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_INFOFIELD)).setToolTipText(new MyE2_String("Status Beanstandungsmeldung (behoben/abgeschlossen)").CTrans());
			//((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_TPA_FIELD)).setToolTipText(new MyE2_String("F = Freie Transport-Position /  T = TPA-Fuhre").CTrans());
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_RECHNUNGEINGANG)).setToolTipText(new MyE2_String("Status Rechnungskontrolle (es gibt mehrere Transportrechnungen zu dieser Fuhre) ").CTrans());
//			((MyE2_ButtonInLIST)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_CHECK_HANDELSDEF)).setToolTipText(new MyE2_String("Stornierte Transport-Position").CTrans());
			//NEU_09 -- ende
			
			
			// jetzt die spalte mit den statuslabels fuellen
			// zuerst auf start
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_PLANMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_trans.png"));			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_LADEMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_trans.png"));			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_ABLADEMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_trans.png"));			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_FERTIGBUCHUNG)).setIcon(E2_ResourceIcon.get_RI("listlabel_trans.png"));
			
			if (!bStorno)
			{
				if (Double.valueOf(oUsedResultMAP.get_UnFormatedValue("AA__MENGE_VORGABE_KO")).doubleValue()>0)
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_PLANMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_green_border.png"));			
					

				if (Double.valueOf(oUsedResultMAP.get_UnFormatedValue("AA__MENGE_AUFLADEN_KO")).doubleValue()>0)
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_LADEMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_green_border.png"));			
					

				if (Double.valueOf(oUsedResultMAP.get_UnFormatedValue("AA__MENGE_ABLADEN_KO")).doubleValue()>0)
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_ABLADEMENGE)).setIcon(E2_ResourceIcon.get_RI("listlabel_green_border.png"));			

				if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_FERTIGBUCHUNG)).setIcon(E2_ResourceIcon.get_RI("listlabel_green_border.png"));			

				// zum schluss den fall der OHNE_ABRECHUNG - Fuhre
				if (bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("OHNE_ABRECHNUNG")).equals("Y"))
				{
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_PLANMENGE)).setIcon(E2_ResourceIcon.get_RI("fuhre_ohne_abrechnung.png"));			
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_LADEMENGE)).setIcon(E2_ResourceIcon.get_RI("fuhre_ohne_abrechnung.png"));		
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_ABLADEMENGE)).setIcon(E2_ResourceIcon.get_RI("fuhre_ohne_abrechnung.png"));			
					((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_FERTIGBUCHUNG)).setIcon(E2_ResourceIcon.get_RI("fuhre_ohne_abrechnung.png"));
				}

			}
			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_PLANMENGE)).setToolTipText(new MyE2_String("Status Planmenge gefüllt ").CTrans());			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_LADEMENGE)).setToolTipText(new MyE2_String("Status Lademenge gefüllt ").CTrans());			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_ABLADEMENGE)).setToolTipText(new MyE2_String("Status Ablademenge gefüllt ").CTrans());			
			((MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_FERTIGBUCHUNG)).setToolTipText(new MyE2_String("Status Buchung der Transport-Position wurde abgeschlossen ").CTrans());
			
			
			//jetzt den button zum sprung in die freien positionen dazu
			MyE2_Column_IMMER_ENABLED oColFPJump = (MyE2_Column_IMMER_ENABLED)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_BUTTON_SPRINGE_EX_FUHRE);
			oColFPJump.removeAll();
			//oColFPJump.add(new FU__ListButton_SPRINGE_IN_FREIE_POS_ZU_FUHRE(cID_VPOS_TPA_FUHRE), E2_INSETS.I_0_0_0_0);
			oColFPJump.add(new FU__ListButton_SPRUNGBUTTONS_EX_FUHRE(cID_VPOS_TPA_FUHRE), E2_INSETS.I_0_0_0_0);
			
			
//			//2011-12-05:  jetzt den button zum sprung in die kontrakte
//			MyE2_Column_IMMER_ENABLED oColF_KON = (MyE2_Column_IMMER_ENABLED)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_BUTTON_SPRINGE_IN_KONTRAKT);
//			oColF_KON.removeAll();
//			oColF_KON.add(new FU__ListButton_SPRUNGBUTTONS_EX_FUHRE(cID_VPOS_TPA_FUHRE), E2_INSETS.I_0_0_0_0);
		
			
			
			//falls es eine Fahrplan-anzeige ist, dann einen sortbutton einbauen
			MyE2_Column_IMMER_ENABLED oCol = (MyE2_Column_IMMER_ENABLED)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_SORTBUTTON);
			oCol.removeAll();
			oCol.add(new MyE2_Label(""));
			if (this.oBASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN))
			{
				FP__SortButtonInList oSortButton = new FP__SortButtonInList(cID_VPOS_TPA_FUHRE,S.NN(oUsedResultMAP.get_UnFormatedValue("SORTIERUNG_FP")));
				oCol.add(oSortButton);
				oSortButton.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo) 	throws myException
					{
						FU_LIST_ComponentMAP_QUERYAGENT.this.oNaviList._REBUILD_COMPLETE_LIST("");
					}
				});
			}
			
			
			//anzeigen, wie der fahrplanstatus ist
			MyE2_Column oLabelFahrplan_yes_no_geplant = ((MyE2_Column)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_FAHRPLAN_YES_NO_GEPLANT));
			oLabelFahrplan_yes_no_geplant.removeAll();
			if (oUsedResultMAP.get_UnFormatedValue("FUHRE_AUS_FAHRPLAN").equals("Y"))
			{
				if (S.isEmpty(oUsedResultMAP.get_UnFormatedValue("FUHRE_KOMPLETT")) || oUsedResultMAP.get_UnFormatedValue("FUHRE_KOMPLETT").equals("N"))
				{
					MyE2_Label  oLabelInnen = new MyE2_Label();
					oLabelInnen.setIcon(E2_ResourceIcon.get_RI("lkw_grau_durchgestrichen.png"));
					oLabelInnen.setToolTipText(new MyE2_String("Fahrplanfuhre, muss noch ueberarbeitet werden ! ").CTrans());
					oLabelFahrplan_yes_no_geplant.add(oLabelInnen, E2_INSETS.I_0_0_0_0);
				}
				else
				{
					if (! bibALL.isEmpty(oUsedResultMAP.get_UnFormatedValue("DAT_FAHRPLAN_FP")) &&  	! bibALL.isEmpty(oUsedResultMAP.get_UnFormatedValue("ID_MASCHINEN_LKW_FP")))
					{
						MyE2_Label  oLabelInnen = new MyE2_Label();
						oLabelInnen.setIcon(E2_ResourceIcon.get_RI("lkw.png"));
						oLabelInnen.setToolTipText(new MyE2_String("Fahrplanfuhre, wurde bereits in Fahrplan gebucht ").CTrans());
						oLabelFahrplan_yes_no_geplant.add(oLabelInnen, E2_INSETS.I_0_0_0_0);
					}
					else
					{
						//falls erlaubt, dann wird ein umbuchungsbutton erzeugt1
						FP_ButtonUmbuchen_in_LISTE oButton = new FP_ButtonUmbuchen_in_LISTE(E2_ResourceIcon.get_RI("lkw_grau.png"), oLabelFahrplan_yes_no_geplant, this.oNaviList);
						oButton.setToolTipText(new MyE2_String("Fahrplanfuhre, noch NICHT in Fahrplan").CTrans());
						oLabelFahrplan_yes_no_geplant.add(oButton, E2_INSETS.I_0_0_0_0);
						
					}
				}
			}
			else
			{
				oLabelFahrplan_yes_no_geplant.add(new MyE2_Label(E2_ResourceIcon.get_RI("empty10.png")), E2_INSETS.I_0_0_0_0);
			}
			
			
			//wenn dir artikelbezeichung groesser als 20 ist, schrift verkleinern
			if ( S.NN(((MyE2_Label)hmRealComponents.get("ART_INFO")).getText()).length()>20)
			{
				((MyE2_Label)hmRealComponents.get("ART_INFO")).setFont(new E2_FontPlain(-2));
			}
			
			
			
			
			MyE2_Label  oLabel_Hat_Zusatzort = (MyE2_Label)hmRealComponents.get(FU_LIST_ModulContainer.NAME_OF_ICON_Zusatzorte);
			if (!bZusatzOrteVorhanden)
			{
				oLabel_Hat_Zusatzort.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
				oLabel_Hat_Zusatzort.setToolTipText("");
				oMAP.get_OPEN_ToggleButton().set_bCanBeOpened(false);
			}
			else
			{
				oLabel_Hat_Zusatzort.setIcon(E2_ResourceIcon.get_RI("zusatzort.png"));
				oLabel_Hat_Zusatzort.setToolTipText(new MyE2_String("Fuhre hat Zusatzorte / Zusatzkontrakte").CTrans());
				oMAP.get_OPEN_ToggleButton().set_bCanBeOpened(true);
			}
			
			
			
			//die eingabe-buttons rot faerben, wenn noch keine Werte drinne sind
			// jetzt die info-spalte fuellen. angezeigt wird, ob aktiv oder nicht und
			// auch, ob die position abzuege hat 
			boolean bDeleted = 	oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");

			GridLayoutData oGLRED = new GridLayoutData();
			oGLRED.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
			oGLRED.setBackground(new E2_ColorAlarm());
//			Vector<String> vHelp = bibALL.get_Vector("ANTEIL_LADEMENGE_LIEF", "ANTEIL_ABLADEMENGE_LIEF", "ANTEIL_PLANMENGE_LIEF", "DATUM_AUFLADEN","DATUM_ABLADEN");
			Vector<String> vHelp = bibALL.get_Vector("C_ANTEIL_LADEMENGE_LIEF", "C_ANTEIL_ABLADEMENGE_ABN", "DATUM_AUFLADEN","DATUM_ABLADEN");
			if (!bDeleted)
			{
				for (String cHelp:vHelp)
				{
					((FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN)hmRealComponents.get(cHelp)).setBackground(null);
					if (bibALL.isEmpty(oUsedResultMAP.get_UnFormatedValue(cHelp)))
					{
						((FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN)hmRealComponents.get(cHelp)).setBackground(new E2_ColorAlarm());
					}
				}
			}

			
			((MyE2_DB_Button)hmRealComponents.get("AA_ANZAHL_IN_GRUPPE")).setToolTipText(new MyE2_String("Zeigt alle Fahrten der Fahrtengruppe ",true,S.NN(oUsedResultMAP.get_FormatedValue("FAHRPLANGRUPPE_FP")),false," an.",true).CTrans());

			
			//die Kontrakt-Buttons besser beschriften
			FU_LIST_BT_OPEN_KONTRAKT_POS oButt_EK_KON =  ((FU_LIST_BT_OPEN_KONTRAKT_POS)hmRealComponents.get("ID_VPOS_KON_EK"));
			FU_LIST_BT_OPEN_KONTRAKT_POS oButt_VK_KON =  ((FU_LIST_BT_OPEN_KONTRAKT_POS)hmRealComponents.get("ID_VPOS_KON_VK"));
			String cID_VPOS_KON_EK = oButt_EK_KON.EXT().get_C_MERKMAL();
			String cID_VPOS_KON_VK = oButt_VK_KON.EXT().get_C_MERKMAL();
			if (S.isFull(cID_VPOS_KON_EK))
			{
				RECORD_VPOS_KON recEK = new RECORD_VPOS_KON(cID_VPOS_KON_EK);
				String cNeuText = recEK.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cF_NN("");
				if (S.isFull(cNeuText))
				{
					oButt_EK_KON.setText(cNeuText+"-"+recEK.get_POSITIONSNUMMER_cF_NN("-"));
					oButt_EK_KON.setFont(new E2_FontItalic(-2));
					oButt_EK_KON.setToolTipText(new MyE2_String("ID-Kontrakt-Position: ",true,recEK.get_ID_VPOS_KON_cF(),false).CTrans());
				}
			}
			if (S.isFull(cID_VPOS_KON_VK))
			{
				RECORD_VPOS_KON recVK = new RECORD_VPOS_KON(cID_VPOS_KON_VK);
				String cNeuText = recVK.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cF_NN("");
				if (S.isFull(cNeuText))
				{
					oButt_VK_KON.setText(cNeuText+"-"+recVK.get_POSITIONSNUMMER_cF_NN("-"));
					oButt_VK_KON.setFont(new E2_FontItalic(-2));
					oButt_VK_KON.setToolTipText(new MyE2_String("ID-Kontrakt-Position: ",true,recVK.get_ID_VPOS_KON_cF(),false).CTrans());
				}
			}
			//-----------------------------------------------
			
			
			
			//jetzt die preis- und steuer-felder noch rot hinterlegen, wenn diese leer sind
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__EINZELPREIS_EK"));
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__STEUERSATZ_EK"));
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__STEUERVERMERK_EK"));
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__MENGENPRUEFUNG_EK"));
			
			
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__EINZELPREIS_VK"));
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__STEUERSATZ_VK"));
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__STEUERVERMERK_VK"));
			PruefeUndMarkiere_LabelInRow((MyE2_DB_Label_INROW) hmRealComponents.get("BB__MENGENPRUEFUNG_VK"));
			
			// ----------------------------------------------
			// jetzt den BAM-Button reinflicken in die fuhre
			MyE2_Row_EveryTimeEnabled  oRow_4_ButtonBAM = (MyE2_Row_EveryTimeEnabled)hmRealComponents.get(FU___CONST.HASH_KEY_LISTE_BAM_AN_FUHRE);
			oRow_4_ButtonBAM.addAfterRemoveAll(new FU_LIST_BT_OPEN_BAM(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),null,this.oNaviList), E2_INSETS.I_0_0_0_0);
			
			
			
			//aenderung 2010-12-16: In der Fuhrenliste alle rechnungsnummern anzeigen (wenn druck erfolgt ist)
			MyE2_Grid oGrid_RECH = (MyE2_Grid)oMAP.get__Comp_From_RealComponents(FU___CONST.HASH_GRID_LIST_RECHNUNGEN);
			MyE2_Grid oGrid_GUT = (MyE2_Grid)oMAP.get__Comp_From_RealComponents(FU___CONST.HASH_GRID_LIST_GUTSCHRIFTEN);

			oGrid_RECH.removeAll();
			oGrid_GUT.removeAll();
			
			RECLIST_VPOS_RG  reclistRGs =  recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord();
			VectorSingle  vRechungen = new VectorSingle();
			VectorSingle  vGutschriften = new VectorSingle();
			
			for (int i=0;i<reclistRGs.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_RG  recRG = reclistRGs.get(i);
				if (recRG.is_DELETED_NO())
				{
					if (recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
					{
						if (S.isFull(recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF()))
						{
							if (recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG))
							{
								vRechungen.add(recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF());
							}
							else
							{
								vGutschriften.add(recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF());
							}
						}
					}
				}
			}

			for (int i=0;i<vRechungen.size();i++)
			{
				oGrid_RECH.add(new MyE2_Label(vRechungen.get(i), new E2_FontPlain(-2)), E2_INSETS.I_1_1_1_1);
			}
			for (int i=0;i<vGutschriften.size();i++)
			{
				oGrid_GUT.add(new MyE2_Label(vGutschriften.get(i), new E2_FontPlain(-2)), E2_INSETS.I_1_1_1_1);
			}
			//aenderung 2010-12-16   ----- ENDE
			
			
			//2012-01-09: uma-buttons automatisch richtig anzeigen
			((__FU_LIST_MASK_BT_DEFINIERE_UMA_VERTRAG)oMAP.get_REAL_ComponentHashMap().get(FU___CONST.HASH_LIST_BUTTON_UMA_KONTRAKT)).KorrigiereAnzeigeFuer_UMA_STATUS(
					oUsedResultMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_UMA_KONTRAKT, true)
					);
			
			
			//2013-09-06: wenn es proforma-saetze gibt, den dazugehoerenden button fettmachen und umbenennen
			FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG oBT_Proforma = (FU__LIST_BT_CREATE_OR_OPEN_PROFORMA_RECHNUNG)hmRealComponents.get(FU___CONST.HASH_LIST_FIELD_ANZAHL_PROFROMARECHNUNG);
			if (oMAP.get_oInternalSQLResultMAP()!=null && oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(FU___CONST.HASH_LIST_FIELD_ANZAHL_PROFROMARECHNUNG, true)>0) {
				oBT_Proforma.setFont(new E2_FontBold());
				String cQuery = "SELECT NVL("+_DB.PROFORMA_RECHNUNG$BELEGNR_PROFORMA+",'-') FROM "+bibE2.cTO()+"."+_DB.PROFORMA_RECHNUNG+
								" WHERE "+_DB.PROFORMA_RECHNUNG$ID_VPOS_TPA_FUHRE+"="+oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()+
								" AND " + _DB.PROFORMA_RECHNUNG$BELEGNR_PROFORMA+" IS NOT NULL "+
										"  ORDER BY 1";
				String[][] vBuchungsnummern = bibDB.EinzelAbfrageInArray(cQuery,"");
				
				String cBuchungsnummernText = "";
				for (int i=0;i<vBuchungsnummern.length;i++) {
					if (!vBuchungsnummern[i][0].equals("-")) {
						if (!S.isEmpty(cBuchungsnummernText)) {
							cBuchungsnummernText +=", ";
						}
						cBuchungsnummernText += vBuchungsnummern[i][0];
					}
				}
				
				oBT_Proforma.setText(new MyE2_String("Proforma: ",true,
									""+cBuchungsnummernText,false).CTrans());
			}
			
			
			FU_LIST_BT_EditWiegekarteInListe oWiegekartenButtonLadeseite = 
					(FU_LIST_BT_EditWiegekarteInListe)hmRealComponents.get(FU___CONST.HASH_LIST_FIELD_WIEGEKARTE_LINKS);
			FU_LIST_BT_EditWiegekarteInListe oWiegekartenButtonAbladeseite = 
					(FU_LIST_BT_EditWiegekarteInListe)hmRealComponents.get(FU___CONST.HASH_LIST_FIELD_WIEGEKARTE_RECHTS);
			
			oWiegekartenButtonLadeseite.set_Text(S.NN(oUsedResultMAP.get_UnFormatedValue(_DB.VPOS_TPA_FUHRE$WIEGEKARTENKENNER_LADEN),
														oWiegekartenButtonLadeseite.get_cText4EmptyButton()));
			oWiegekartenButtonAbladeseite.set_Text(S.NN(oUsedResultMAP.get_UnFormatedValue(_DB.VPOS_TPA_FUHRE$WIEGEKARTENKENNER_ABLADEN),
														oWiegekartenButtonLadeseite.get_cText4EmptyButton()));

			oWiegekartenButtonLadeseite.setFont(new E2_FontPlain());
			oWiegekartenButtonLadeseite.setForeground(Color.BLACK);
			oWiegekartenButtonAbladeseite.setFont(new E2_FontPlain());
			oWiegekartenButtonAbladeseite.setForeground(Color.BLACK);
			

			if (oWiegekartenButtonLadeseite.getText().equals(oWiegekartenButtonLadeseite.get_cText4EmptyButton())) {
				oWiegekartenButtonLadeseite.setFont(new E2_FontItalic());
				oWiegekartenButtonLadeseite.setForeground(Color.DARKGRAY);
			}
			if (oWiegekartenButtonAbladeseite.getText().equals(oWiegekartenButtonAbladeseite.get_cText4EmptyButton())) {
				oWiegekartenButtonAbladeseite.setFont(new E2_FontItalic());
				oWiegekartenButtonAbladeseite.setForeground(Color.DARKGRAY);
			}
			
			
			
		}
		catch (Exception ex)
		{
			//NEU_09
			ex.printStackTrace();
			throw new myException("FU_LIST_QUERYAGENT:fillComponents:Error filling infolabels: "+ex.getLocalizedMessage());
		}
	}
	
	
	private void PruefeUndMarkiere_LabelInRow(MyE2_DB_Label_INROW oLabel)
	{
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setAlignment(((ColumnLayoutData)oLabel.get_oRowContainer().getLayoutData()).getAlignment());
			oColLayout.setInsets(((ColumnLayoutData)oLabel.get_oRowContainer().getLayoutData()).getInsets());
			oColLayout.setBackground(null);
			if (oLabel.get_oErsatzButton().getText().equals("(--)"))
			{
				oColLayout.setBackground(new E2_ColorAlarm());
			}
			oLabel.get_oRowContainer().setLayoutData(oColLayout);
	}

}
