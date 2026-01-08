package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListSortHelper;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NNG;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.bibREP;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcher;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_XLS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;



public class FIBU_LIST_BT_PrintMail_Saldenliste extends MyE2_Button
{
	
	private FIBU_LIST_Selector  oFibuSelektor = null;
	
	private String   			cNummerReportAktion = null;
	
	/*
	 * es ist zwingend fuer eine saldenliste, einen kunden zu definieren
	 */
	private String      		cID_ADRESSE_FUER_LISTE = null;
	
	
	public FIBU_LIST_BT_PrintMail_Saldenliste(FIBU_LIST_Selector FibuSelektor) throws myException
	{
		super(new MyE2_String("Druck/Mail Saldenliste"));
		this.oFibuSelektor = FibuSelektor;
		
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				new ownPopupContainer();
			}
		});
	}
	
	
	
	
	private class ownPopupContainer extends E2_BasicModuleContainer
	{
		//einstellhilfen fuer den Listenselektor
		private MyE2_CheckBox       			oCB_SelektorDruckeAuchVerbuchte = 			new MyE2_CheckBox("");;
		private MyE2_SelectField                oSelDatumBereich_RechDruck_ErstellungVon =	new MyE2_SelectField(new Extent(100));
		private MyE2_SelectField              	oSelDatumBereich_RechDruck_ErstellungBis =	new MyE2_SelectField(new Extent(100));
		private MyE2_SelectField  				oSelectBelegTyp = 							new MyE2_SelectField();
		private MyE2_SelectField  				oSelectFirmen = 							new MyE2_SelectField();
		
		private MyE2_Button    					oButtonStartWithActualSelection = 			new MyE2_Button(
																								new MyE2_String("Druck/Mail auf bestehender Selektion"),
																								new MyE2_String("Startet den Druck auf Basis der aktuellen Selektion"),
																								new ownPrintAction(false));
		private MyE2_Button    					oButtonStartWithNewSelection = 				new MyE2_Button(
																								new MyE2_String("Selektiere und Drucke/Maile"),
																								new MyE2_String("Verändert die Listenselektion und startet den Druck auf Basis der neuen Selektion"),
																								new ownPrintAction(true));
		private MyE2_Button    					oButtonAbbruch = 							new MyE2_Button(new MyE2_String("Abbrechen"));

		
		private MyE2_CheckBox  					oCB_ExcelExport= 							new MyE2_CheckBox();
		
		
		private ownSelectorDatumVonBisNNG       oSelLeistungsDatum = 						new ownSelectorDatumVonBisNNG();
		
		//2012-11-26: neues feld zur selektion; faelligkeit
		private ownSelectorDatumVonBisNNG       oSelFaelligkeitsDatum = 					new ownSelectorDatumVonBisNNG();
		
		//2012-11-26: neuer selektor fuer die sortierung
		private ownSelectSortierung             oSelSortList = 								new ownSelectSortierung();
		
		
		public ownPopupContainer() throws myException
		{
			super();
			FIBU_LIST_BT_PrintMail_Saldenliste oThis = FIBU_LIST_BT_PrintMail_Saldenliste.this;
			
			
		
			
			//gleiche auswahl, wie im modul-selektor
			this.oSelectFirmen.set_oDataToView(oThis.oFibuSelektor.get_oSelKundenMitPositionen().get_DataToView());
			//jetzt noch den listRenderer uebernehmen (fuer die grau dargestellten)
			this.oSelectFirmen.setCellRenderer(oThis.oFibuSelektor.get_oSelKundenMitPositionen().getCellRenderer());
			
			
			this.oSelDatumBereich_RechDruck_ErstellungVon.set_oDataToView(oThis.oFibuSelektor.get_oSelDatumBereich_RechDruck_Erstellung().get_oSelMonateJahrVon().get_DataToView());
			this.oSelDatumBereich_RechDruck_ErstellungBis.set_oDataToView(oThis.oFibuSelektor.get_oSelDatumBereich_RechDruck_Erstellung().get_oSelMonateJahrBis().get_DataToView());
			this.oSelectBelegTyp.set_oDataToView(oThis.oFibuSelektor.get_oSelBelegTyp().get_DataToView());

			//jetzt die Eintraege des fibu-selektors uebernehmen
			this.oSelectFirmen.set_ActiveValue_OR_FirstValue(oThis.oFibuSelektor.get_oSelKundenMitPositionen().get_ActualWert());
			this.oSelectBelegTyp.set_ActiveValue_OR_FirstValue(oThis.oFibuSelektor.get_oSelBelegTyp().get_ActualWert());
			this.oSelDatumBereich_RechDruck_ErstellungVon.set_ActiveValue_OR_FirstValue(oThis.oFibuSelektor.get_oSelDatumBereich_RechDruck_Erstellung().get_oSelMonateJahrVon().get_ActualWert());
			this.oSelDatumBereich_RechDruck_ErstellungBis.set_ActiveValue_OR_FirstValue(oThis.oFibuSelektor.get_oSelDatumBereich_RechDruck_Erstellung().get_oSelMonateJahrBis().get_ActualWert());
			
			//2012-11-26: faelligkeitsdatum
			this.oSelFaelligkeitsDatum.CopyValuesFrom(oThis.oFibuSelektor.get_oSelBereichDatumFaelligkeit());
			this.oSelLeistungsDatum.CopyValuesFrom(oThis.oFibuSelektor.get_oSelLeistungsdatum());
			
			Border oBorder = new Border(0,Color.BLACK, Border.STYLE_NONE);
			
			MyE2_Grid  oGridInnen = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

			
			//masse der komponenten einstellen
			this.oSelectFirmen.setWidth(new Extent(257));
			this.oSelectBelegTyp.setWidth(new Extent(257));
			this.oSelSortList.setWidth(new Extent(257));
			
			
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Druck einer Saldenliste"), new E2_FontBold(2)), 				2, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oButtonStartWithActualSelection.get_InBorderGrid(oBorder, null, E2_INSETS.I_0_0_0_0), 	2, E2_INSETS.I_2_10_2_10);
			
			oGridInnen.add(new Separator(),																				2, E2_INSETS.I_2_2_2_2);
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Selektion verändern"), new E2_FontBold(2)),					2, E2_INSETS.I_2_10_2_10);
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Kunde/Lieferant")), 											1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oSelectFirmen, 																			1, E2_INSETS.I_2_2_2_2);
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Drucke auch verbuchte Einträge")), 							1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oCB_SelektorDruckeAuchVerbuchte.get_InBorderGrid(oBorder, null, E2_INSETS.I_0_0_0_0), 	1, E2_INSETS.I_2_2_2_2);
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Buch.Dat. von")), 											1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(new E2_ComponentGroupHorizontal(0, 
											this.oSelDatumBereich_RechDruck_ErstellungVon, 
											new MyE2_Label(new MyE2_String("bis")),
											this.oSelDatumBereich_RechDruck_ErstellungBis,E2_INSETS.I_0_0_20_0), 1, E2_INSETS.I_2_2_2_2);

			//2012-11-26: neues feld: faelligkeitsdatum
			oGridInnen.add(new MyE2_Label(new MyE2_String("Fälligkeit (von)")), 										1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oSelFaelligkeitsDatum.get_oComponentWithoutText(), 										1, E2_INSETS.I_2_2_2_2);

			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Leist.D.(von)")), 											1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oSelLeistungsDatum.get_oComponentWithoutText(), 										1, E2_INSETS.I_2_2_2_2);

			oGridInnen.add(new MyE2_Label(new MyE2_String("Belegtyp")), 												1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oSelectBelegTyp, 																		1, E2_INSETS.I_2_2_2_2);

			oGridInnen.add(new Separator(),																				2, E2_INSETS.I_2_2_2_2);

			oGridInnen.add(new MyE2_Label(new MyE2_String("Sortierung")), 												1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oSelSortList, 																				1, E2_INSETS.I_2_2_2_2);
			
			oGridInnen.add(new Separator(),																				2, E2_INSETS.I_2_2_2_2);
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Excel statt PDF")), 											1, E2_INSETS.I_2_2_10_2);
			oGridInnen.add(this.oCB_ExcelExport, 																		1, E2_INSETS.I_2_2_2_2);
			
			oGridInnen.add(new Separator(),																				2, E2_INSETS.I_2_2_2_2);

			oGridInnen.add(this.oButtonStartWithNewSelection.get_InBorderGrid(oBorder, null, E2_INSETS.I_0_0_0_0), 		1, E2_INSETS.I_2_20_10_20);
			oGridInnen.add(this.oButtonAbbruch.get_InBorderGrid(oBorder, null, E2_INSETS.I_0_0_0_0), 					1, E2_INSETS.I_2_20_2_20);
			
			this.add(oGridInnen, E2_INSETS.I_5_5_5_5);

			this.oButtonAbbruch.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					ownPopupContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);				
				}
			});
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(500), new MyE2_String("Druck Saldenliste"));
		}
		
		
		//actionagent fuer den close-vorgang des report-mail-popups
		private class ownCloseAndDestroyAction extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				bibREP.DeleteReportBlock(FIBU_LIST_BT_PrintMail_Saldenliste.this.cNummerReportAktion);
			}
		}
		
		
		
		private class ownPrintAction extends ACTIONAGENT_MAIL_AND_REPORT
		{
			private boolean bMitNeuSelektion = false;    //wird der obere oder der untere knopf benutzt
			
			public ownPrintAction(	boolean MitNeuSelektion)
			{
				super(new MyE2_String("Saldenliste"), "PRINT_SALDENLISTE", "PRINT_SALDENLISTE",new ownCloseAndDestroyAction(),false);
				this.bMitNeuSelektion = MitNeuSelektion;
			}

			
			/**
			 * ueberschreiben der (sonst leeren) hilfsmethode zum ausfuehren der selektion vor dem druck
			 */
			public boolean bACTION_BEFORE_START___TO_OVERWRITE() throws myException
			{
				FIBU_LIST_BT_PrintMail_Saldenliste 	oThis1 = FIBU_LIST_BT_PrintMail_Saldenliste.this;
				ownPopupContainer  					oThis2 = ownPopupContainer.this;

				if (this.bMitNeuSelektion)
				{
					
					oThis1.oFibuSelektor.get_oSelVector().set_NeutralWhenEverySelectorHasNeutron();
					
					if (oThis2.oCB_SelektorDruckeAuchVerbuchte.isSelected())
					{
						oThis1.oFibuSelektor.set_SelAuswahl_OFFENE_UND_GESCHLOSSENE();
					}
					else
					{
						oThis1.oFibuSelektor.set_SelAuswahl_OFFENE();
					}
					
					oThis1.oFibuSelektor.get_oSelDatumBereich_RechDruck_Erstellung().get_oSelMonateJahrVon().set_ActiveInhalt_or_FirstInhalt(
							oThis2.oSelDatumBereich_RechDruck_ErstellungVon.get_ActualView());
					
					oThis1.oFibuSelektor.get_oSelDatumBereich_RechDruck_Erstellung().get_oSelMonateJahrBis().set_ActiveInhalt_or_FirstInhalt(
							oThis2.oSelDatumBereich_RechDruck_ErstellungBis.get_ActualView());
					
					oThis1.oFibuSelektor.get_oSelKundenMitPositionen().set_ActiveInhalt_or_FirstInhalt(
							oThis2.oSelectFirmen.get_ActualView());
					
					oThis1.oFibuSelektor.get_oSelBelegTyp().set_ActiveInhalt_or_FirstInhalt(
							oThis2.oSelectBelegTyp.get_ActualView());
				
					oThis1.oFibuSelektor.get_oSelLeistungsdatum().CopyValuesFrom(oThis2.oSelLeistungsDatum);
					
					//2012-11-26: uebernahme der faelligkeitsdatums-einstellung
					oThis1.oFibuSelektor.get_oSelBereichDatumFaelligkeit().CopyValuesFrom(oThis2.oSelFaelligkeitsDatum);
					
					
					//2012-11-26: sortierung einbauen
					oThis1.oFibuSelektor.get_oNaviList().get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
					E2_ListSortHelper.reset_all_sorters(oThis1.oFibuSelektor.get_oNaviList());
					oThis1.oFibuSelektor.get_oNaviList().get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(oThis2.oSelSortList.get_ActualWert());
					//damit ist die bildschirmliste so sortiert
					
					oThis1.oFibuSelektor.get_oSelectVector().doActionPassiv();
				}
				
				
				//jetzt pruefen, ob es eine ausgewaehlte adress-id gibt:
				oThis1.cID_ADRESSE_FUER_LISTE  = FIBU_LIST_BT_PrintMail_Saldenliste.this.oFibuSelektor.get_oSelKundenMitPositionen().get_ActualWert();
				if (! (S.isFull(oThis1.cID_ADRESSE_FUER_LISTE) && bibALL.isLong(oThis1.cID_ADRESSE_FUER_LISTE)))
				{
					oThis1.cID_ADRESSE_FUER_LISTE = null;   
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss eine Firma selektiert sein, um eine Saldenliste zu erzeugen !")));
					
					return false;
				}

				return true;
			}

			
			
			@Override
			public E2_MassMailer get_MassMailer() throws myException
			{
				E2_MassMailer oMassMailer = new E2_MassMailer_STD(	"FIBU_SALDENLISTE_MAIL_BETREFF",
																	"FIBU_SALDENLISTE_MAIL_TEXT",
																	"FIBU_SALDENLISTE");
				return oMassMailer;
			}


			@Override
			public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
			{
				
				V_JasperHASH vJaspers = new V_JasperHASH();
				
				E2_NavigationList  oNaviList = FIBU_LIST_BT_PrintMail_Saldenliste.this.oFibuSelektor.get_oNaviList();
				
				Vector<String> vActualIDs = new Vector<String>(oNaviList.get_vectorSegmentation());
				vActualIDs.addAll(oNaviList.get_vActualID_Segment());
				
				if (vActualIDs.size()>0) 
				{
					// den alten reportblock loeschen (falls mehrfach hintereinander gedruckt wird
					// (sicherheitshalber, wird auch nach ausfuehren des jasperhash-file-erzeugens durchgefuehrt)
					bibREP.DeleteReportBlock(FIBU_LIST_BT_PrintMail_Saldenliste.this.cNummerReportAktion);
					
					FIBU_LIST_BT_PrintMail_Saldenliste.this.cNummerReportAktion=bibREP.WriteReportBlock(vActualIDs);

//					System.out.println(FIBU_LIST_BT_PrintMail_Saldenliste.this.cNummerReportAktion);
					
					if (S.isFull(FIBU_LIST_BT_PrintMail_Saldenliste.this.cID_ADRESSE_FUER_LISTE))
					{
						vJaspers.add(new ownJasperHASH(FIBU_LIST_BT_PrintMail_Saldenliste.this.cID_ADRESSE_FUER_LISTE, FIBU_LIST_BT_PrintMail_Saldenliste.this.cNummerReportAktion));
					}
				}
				
				return vJaspers;
			}

			
			
			private class ownJasperHASH extends E2_JasperHASH
			{

				private String cID_ADRESSE = null;
				
				public ownJasperHASH(String ID_ADRESSE, String cReportNummer)	throws myException
				{
					super("SALDENLISTE",ownPopupContainer.this.oCB_ExcelExport.isSelected()? new JasperFileDef_XLS(): new JasperFileDef_PDF());
					this.cID_ADRESSE = ID_ADRESSE;

					ownPopupContainer  	oThis = ownPopupContainer.this;
					
					if (S.isEmpty(this.cID_ADRESSE) || !bibALL.isLong(this.cID_ADRESSE))
					{
						throw new myException("Empty Adress-ID is not allowed for this report !");
					}
					
					this.put("REPORTNUMMER", cReportNummer);

					//jetzt die info- und steuerparameter uebergeben
					this.put("INFO_ZEITRAUM_VON", 				oThis.oSelDatumBereich_RechDruck_ErstellungVon.get_ActualView());
					this.put("INFO_ZEITRAUM_BIS", 				oThis.oSelDatumBereich_RechDruck_ErstellungBis.get_ActualView());
					this.put("INFO_VERBUCHTE_UNVERBUCHTE",	 	oThis.oCB_SelektorDruckeAuchVerbuchte.isSelected()?"Offene und geschlossene":"Nur offene");
					this.put("INFO_BELEGTYP", 					oThis.oSelectBelegTyp.get_ActualView());
					this.put("INFO_SORT_VALUE", 				oThis.oSelSortList.get_ActualView());

//					this.put("LEISTUNGSDATUM_VON", 				S.isEmpty(		oThis.oSelLeistungsDatum.get_oTFDatumVon().get_oTextField().getText())?"*":
//																				oThis.oSelLeistungsDatum.get_oTFDatumVon().get_oTextField().getText());
//					this.put("LEISTUNGSDATUM_BIS", 				S.isEmpty(		oThis.oSelLeistungsDatum.get_oTFDatumBis().get_oTextField().getText())?"*":
//																				oThis.oSelLeistungsDatum.get_oTFDatumBis().get_oTextField().getText());
//
//					this.put("FAELLIGKEITSDATUM_VON", 			S.isEmpty(	oThis.oSelFaelligkeitsDatum.get_oTFDatumVon().get_oTextField().getText())?"*":
//																			oThis.oSelFaelligkeitsDatum.get_oTFDatumVon().get_oTextField().getText());
//					this.put("FAELLIGKEITSDATUM_BIS", 			S.isEmpty(	oThis.oSelFaelligkeitsDatum.get_oTFDatumBis().get_oTextField().getText())?"*":
//																			oThis.oSelFaelligkeitsDatum.get_oTFDatumBis().get_oTextField().getText());

					this.put("LEISTUNGSDATUM_VON", 				S.isEmpty(	oThis.oSelLeistungsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldVon().getText())?"*":
																			oThis.oSelLeistungsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldVon().getText());
					this.put("LEISTUNGSDATUM_BIS", 				S.isEmpty(	oThis.oSelLeistungsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldBis().getText())?"*":
																			oThis.oSelLeistungsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldBis().getText());
					
					this.put("FAELLIGKEITSDATUM_VON", 			S.isEmpty(	oThis.oSelFaelligkeitsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldVon().getText())?"*":
																			oThis.oSelFaelligkeitsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldVon().getText());
					this.put("FAELLIGKEITSDATUM_BIS", 			S.isEmpty(	oThis.oSelFaelligkeitsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldBis().getText())?"*":
																			oThis.oSelFaelligkeitsDatum.get_oTFDatumVonBisPopUp().get_oTextFieldBis().getText());
					
					
					this.put("SORT_VALUE", 						oThis.oSelSortList.get_ActualWert());
					
					
					this.put("EXCEL_DRUCK", 					oThis.oCB_ExcelExport.isSelected()?"Y":"N");
					
					this.put("SPEZIELLE_SELEKTION", 			ownPrintAction.this.bMitNeuSelektion ? "Y":"N");
					
					this.put("DRUCKE_SALDO",      				oThis.oSelectBelegTyp.get_ActualWert().equals("ALLE")?"Y":"N");
					
					this.set_bVorschauDruck(false);
					this.set_cDownloadAndSendeNameStaticPart("SALDENLISTE"+(S.isFull(cID_ADRESSE)?"_"+cID_ADRESSE:""));
				}

				
				@Override
				protected MailBlock create_MailBlock() throws myException
				{
					ownMailBlock oMailBlock = new ownMailBlock();
					
					if (S.isFull(cID_ADRESSE))
					{
						oMailBlock.add_VMailAdress4MailBlock(
								new MailAdressSearcher(	this.cID_ADRESSE,
														true,
														true,
														true,
														myCONST.EMAIL_TYPE_VALUE_FIBU,
														new MyE2_String(myCONST.EMAIL_TYPE_TEXT_FIBU),
														null, 
														true, 
														new Boolean(false), 
														new Boolean(true))
								);
					}
					return oMailBlock;
				}

				@Override
				public boolean get_bIsDesignedForMail() throws myException
				{
					return true;           //wenn preview, dann keine mail
				}
				
				
				@Override
				public void doActionAfterCreatedFile() throws myException
				{
				}
				
			}

			
			
			private class ownMailBlock extends MailBlock
			{

				public ownMailBlock() throws myException
				{
					super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
				}

				@Override
				protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
				{
					return new ownMailAdress4MailBlock(mailAdresse,OWN_MailBlock,new MyE2_String("Vorhandene eMail"),new Boolean(false));
				}

				@Override
				protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
				{
					return new ownMailAdress4MailBlock("",OWN_MailBlock,new MyE2_String("Freie eMail"),new Boolean(true));
				}

				@Override
				protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
				{
					return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"),new Boolean(false));
				}

				@Override
				protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
				{
					return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-eMail"),new Boolean(false));
				}

				@Override
				public Component get_ComponentForMailerList() throws myException
				{
					return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
				}

				@Override
				public MyString get_ComponentTextForProtokoll() throws myException
				{
					return new MyE2_String("FIBU_SALDENLISTE");
				}

				@Override
				public String get_cBelegTyp() throws myException
				{
					return "FIBU_SALDENLISTE";
				}

				@Override
				public MyString get_cBelegTyp4User() throws myException
				{
					return new MyE2_String("FIBU_SALDENLISTE");
				}

				@Override
				public MyString get_cKommentar() throws myException
				{
					return new MyE2_String("Mail aus FIBU-Saldenliste");
				}

				@Override
				public String get_cModulInfo() throws myException
				{
					return E2_MODULNAMES.NAME_MODUL_FIBU_LIST;
				}
			}

			
			
			private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
			{
				private MyE2_String cStringForComponent = null;
				
				public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent, Boolean bAllowEdit) throws myException
				{
					super(mailAdress, MailBlockThisBelongsTo, bAllowEdit);
					this.cStringForComponent = StringForComponent; 
				}

				@Override
				public Component get_ComponentForMailerList() throws myException
				{
					return new MyE2_Label(this.cStringForComponent,MyE2_Label.STYLE_SMALL_ITALIC());
				}

				@Override
				public String get_cAdressInfo() throws myException
				{
					return "<keine Adressinfo>";
				}

				@Override
				public MyString get_cComponentText() throws myException
				{
					return this.cStringForComponent;
				}

				@Override
				public String get_cID_ADRESSE_EMPFAENGER() throws myException
				{
					return null;
				}
			}
		}
		
		
//		private class ownSelektorLeistungsdatum extends E2_SelektorDateFromTo_NG
//		{
//			public ownSelektorLeistungsdatum() throws myException
//			{
//				super();
//
//				//die selektion bezieht sich nur auf das startdatum 
//				String cSQLVon = " (CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE (SELECT MIN(VP1.AUSFUEHRUNGSDATUM) " +
//										" FROM "+bibE2.cTO()+".JT_VPOS_RG VP1 WHERE NVL(VP1.DELETED,'N')='N' AND VP1.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG) END)";
//				
//				this.INIT_Selector(null, cSQLVon, cSQLVon, new Extent(80));
//				
//				this.get_oTFDatumVon().get_oTextField().set_iWidthPixel(100);
//				this.get_oTFDatumBis().get_oTextField().set_iWidthPixel(100);		
//				
//			}
//		}
//
//		
//		private class ownSelektorFaelligkeit extends E2_SelektorDateFromTo_NG
//		{
//			public ownSelektorFaelligkeit() throws myException
//			{
//				super();
//
//				//die selektion bezieht sich nur auf das startdatum 
//				String cSQLVon = " (CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE (SELECT MIN(VP1.AUSFUEHRUNGSDATUM) " +
//										" FROM "+bibE2.cTO()+".JT_VPOS_RG VP1 WHERE NVL(VP1.DELETED,'N')='N' AND VP1.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG) END)";
//				
//				this.INIT_Selector(null, cSQLVon, cSQLVon, new Extent(80));
//				
//				this.get_oTFDatumVon().get_oTextField().set_iWidthPixel(100);
//				this.get_oTFDatumBis().get_oTextField().set_iWidthPixel(100);		
//				
//			}
//		}


		
		//2012-11-26: umstellen auf singulaeres von-bis-popup-feld
		private class ownSelectorDatumVonBisNNG extends E2_SelektorDateFromTo_NNG
		{
			public ownSelectorDatumVonBisNNG() throws myException
			{
				super();
				this.get_oTFDatumVonBisPopUp().set_bAutoCloseOnBisCalendar(false);
			}

			@Override
			public void Ordne_Komponenten_An_in_DateVonbisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN 	ownSelectVonBisPopup, 
																MyE2_TextField 							oTextFieldVon, 
																MyE2_TextField 							oTextFieldBis, 
																MyE2_Button 							oButtonCalendar, 
																MyE2_Button 							oButtonEraserVon,
																MyE2_Button 							oButtonEraserBis) throws myException
			{
				ownSelectVonBisPopup.setSize(5);
				
				ownSelectVonBisPopup.add(oTextFieldVon);
				ownSelectVonBisPopup.add(oButtonEraserVon,new Insets(0, 0, 35, 0));
				ownSelectVonBisPopup.add(oTextFieldBis);
				ownSelectVonBisPopup.add(oButtonEraserBis,E2_INSETS.I_0_0_5_0);
				ownSelectVonBisPopup.add(oButtonCalendar);
				
				oTextFieldVon.set_iWidthPixel(98);
				oTextFieldBis.set_iWidthPixel(98);
				
			}

			@Override
			public void HaengeMeineElementeAn_DateVonBisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup, MyE2_Button oButtonLos, MyE2_Button oButtonHelp) throws myException
			{
				ownSelectVonBisPopup.setSize(6);
				ownSelectVonBisPopup.add(oButtonHelp,E2_INSETS.I_0_0_5_0);
			}

				
		}
		
		
		
		private class ownSelectSortierung extends MyE2_SelectField
		{

			public ownSelectSortierung() throws myException
			{
				super();
				
				String[][] cSortierungen = new String[4][2];
				cSortierungen[0][0] = "ID-Beleg"; 			
				cSortierungen[0][1] = "JT_FIBU.ID_FIBU"; 
				
				cSortierungen[1][0] = "Beleg-Datum"; 		
				cSortierungen[1][1] = "(CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE JT_VKOPF_RG.DRUCKDATUM END)";
				
				cSortierungen[2][0] = "Leistungsdatum";		
				cSortierungen[2][1] = "(CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN NULL ELSE (SELECT MAX(VP2.AUSFUEHRUNGSDATUM) FROM "+bibE2.cTO()+".JT_VPOS_RG VP2 WHERE NVL(VP2.DELETED,'N')='N' AND VP2.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG) END)";
				
				cSortierungen[3][0] = "Zahlungsziel (aus Fibu, evtl. korrigiert)";
				cSortierungen[3][1] = "JT_FIBU.ZAHLUNGSZIEL"; 

				this.set_ListenInhalt(cSortierungen, false);
				
				this.set_ActiveValue_OR_FirstValue("ID_FIBU");
				
			}
			
		}
		
		
	}
	
	
}
