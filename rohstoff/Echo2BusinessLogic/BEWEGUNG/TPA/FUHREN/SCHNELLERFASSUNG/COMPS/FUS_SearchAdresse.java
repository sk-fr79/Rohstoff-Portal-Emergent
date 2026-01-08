package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_FUHREN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingleMyString;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__CHECK_MustWarn_AVV_Kontrakt;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_ActionAfterFound;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_VALIDATOR_AllBeforeIsFilled;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Combo_Transportmittel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_Window;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAngebotsFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchKontraktFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;

public class FUS_SearchAdresse extends MyE2_MaskSearchField implements __FUS_STANDARD_Element
{

	public static String	cSQL4Label	= " SELECT "
												+ " CASE WHEN (SELECT A1.ADRESSTYP FROM JT_ADRESSE A1 WHERE A1.ID_ADRESSE=#WERT#)=1 "
												+ " THEN "
												+ " (SELECT TRIM(TRIM(  NVL(NAME1,''))|| ' ' || TRIM(  NVL(NAME2,'')))|| ', ' || TRIM(  NVL(STRASSE,''))||'  ' || TRIM(  NVL(HAUSNUMMER,''))  || ', '|| TRIM(NVL(PLZ,''))||' '||TRIM(NVL(ORT,'')) ||' '|| "
												+ " CSCONVERT('<Firmensitz>','NCHAR_CS')  FROM JT_ADRESSE WHERE ID_ADRESSE=#WERT#) " + " ELSE "
												+ " (SELECT TRIM(TRIM(  NVL(AL.NAME1,''))|| ' ' || TRIM(  NVL(AL.NAME2,'')))|| ', ' || TRIM(  NVL(AL.STRASSE,'')) || ', '|| TRIM(NVL(AL.PLZ,''))||' '||TRIM(NVL(AL.ORT,'')) ||'  '|| "
												+ " CSCONVERT('<Lieferadresse von ','NCHAR_CS')||' ' || trim(trim(  NVL(AF.NAME1,''))|| ' ' || trim(  NVL(AF.NAME2,'')))|| ', ' ||trim(NVL(AF.ORT,''))  ||'> ' " + " FROM              JT_ADRESSE AL  "
												+ " INNER JOIN   JT_LIEFERADRESSE LIEF ON (AL.ID_ADRESSE = LIEF.ID_ADRESSE_LIEFER) " + " INNER JOIN   JT_ADRESSE AF             ON (LIEF.ID_ADRESSE_BASIS = AF.ID_ADRESSE) "
												+ " WHERE AL.ID_ADRESSE=#WERT#) " + " END " + " FROM DUAL ";

	private String			cEK_VK		= null;

	public FUS_SearchAdresse(String EK_VK) throws myException
	{
		super(new FUS_SearchAdresse__SearchBlock(), FUS_SearchAdresse.cSQL4Label, E2_INSETS.I_0_0_0_0, true);

		
		this.set_bShowSearchButtonLeft(true);
		
		this.cEK_VK = EK_VK;

		
		//2012-08-07: wareneingang/warenausgang-sperr-felder beruecksichtigen
		this.get_oSeachBlock().get_vZusatzWhereBedingungen().add(
				this.cEK_VK.equals("EK")
				?
				"(NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N')"
					:
				"(NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,'N')='N')");
		//-------------------
		

		this.get_oButtonErase().__setImages(E2_ResourceIcon.get_RI("eraser_xxs.png"), true);
		
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.set_oPopupWidth(new Extent(900));
		this.set_oPopupHigh(new Extent(600));

		ownHomeButton oownHomeButton = new ownHomeButton();
		oownHomeButton.add_GlobalValidator(new __FUS_VALIDATOR_AllBeforeIsFilled(this));

		this.add_AddOnComponent(oownHomeButton);
		this.add_AddOnComponent(new FUS_BT_OpenAdressMask(this));

		this.get_oTextFieldForSearchInput().set_iWidthPixel(80);

		// this.get_ValidatorStartSearch().add(new
		// ownValidatorCheckObAllesVorherGefuelltWurde());
		this.get_ValidatorStartSearch().add(new __FUS_VALIDATOR_AllBeforeIsFilled(this));

		this.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB()
		{
			@Override
			public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_MaskSearchField oSearchField, boolean bAfterAction) throws myException
			{
				MyLong lID = new MyLong(cMaskValue);
				if (!lID.get_cErrorCODE().equals(MyLong.ALL_OK))
				{
					throw new myException(this,"value is not allowed ...");
				}
				new __FUS_ActionAfterFound(FUS_SearchAdresse.this);
				// new ownPopupWarnWhenNo_HandelsVertrag(lID.get_cUF_LongString());
				FUS_SearchAdresse.this.show_pruefung_fehlender_Handelsvertrag();
				
				//2011-11-18: system-meldungen anzeigen
				new __SYSTEM_MESSAGE_GENERATOR_FUHREN(lID.get_cUF_LongString(), FUS_SearchAdresse.this.cEK_VK).ACTIVATE_MESSAGES();

				FUS_SearchAdresse.this.do_afterFieldWasFilled(cMaskValue);
			}
		});

		this.get_oButtonErase().add_oActionAgent(new actionAgentAfterRadiergummi());

	}

	/*
	 * 2014-08-04: auslagerung in globale methode, damit auch eine pruefung mit voreingestellten adressen geht
	 */
	public void show_pruefung_fehlender_Handelsvertrag() throws myException {
		new ownPopupWarnWhenNo_HandelsVertrag();
	}
	

	
	/*
	 * warnung vor nicht vorhandenem handelsvertrag
	 */
	private class ownPopupWarnWhenNo_HandelsVertrag extends E2_BasicModuleContainer
	{
		private MyE2_Button	oButtonAbbruch		= new MyE2_Button(new MyE2_String("Abbrechen"));
		private MyE2_Button	oButtonUebernehmen	= new MyE2_Button(new MyE2_String("Trotzdem übernehmen"));

		public ownPopupWarnWhenNo_HandelsVertrag() throws myException
		{
			super();
			FUS_SearchAdresse oThis = FUS_SearchAdresse.this;

			RECORD_ADRESSE recAdresseAktuell = 	oThis.get_ActualRecAdresse();
			RECORD_ADRESSE recAdresseHaupt = 	oThis.get_ActualRecHauptAdresse();
			if (recAdresseAktuell==null || recAdresseHaupt==null)
			{
				return;
			}

			//2014-08-04: umstellung auf die neue pruefmimik
			FU__CHECK_MustWarn_AVV_Kontrakt  oPruefeVertrag = new FU__CHECK_MustWarn_AVV_Kontrakt();
			
			VectorSingleMyString vMeldungen = new VectorSingleMyString();
			vMeldungen.addAll(oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recAdresseHaupt, cEK_VK, new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oTextField().getText(), true));
			vMeldungen.addAll(oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recAdresseAktuell, cEK_VK, new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oTextField().getText(), false));
			
//			//2014-05-26:  hier wird ab jetzt jede adresse, auch das lager geprueft
//			FU__CHECK_MustWarn_AVV_Kontrakt oCheck = new FU__CHECK_MustWarn_AVV_Kontrakt(cEK_VK, cID_ADRESSE_INPUT_UF, new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oTextField().getText());

			//2012-03-27: das pruefen auf fehlenden handelsvertrag ist bei gesetztem Schalter <VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG>
			//            in den daemon FUHREN_PRUEFUNG_FEHLENDER_HANDELSVERTRAG gewandert, somit ist das hide des ueberstimm-buttons wirkungslos 
			boolean bVerbieteUeberstimmen = __RECORD_MANDANT_ZUSATZ.IS__Value("VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG", "N", "N");
			
			
			if (vMeldungen.size()>0 && (!bVerbieteUeberstimmen))
			{
				this.oButtonUebernehmen.add_GlobalValidator(new E2_ButtonAUTHValidator(new _SEARCH_FUS_Window().get_Found_FUS_Window().get_MODUL_IDENTIFIER(), FU___CONST.HASH_UEBERSTIMMEN_FEHLENDEN_KONTRAKT));

//				MyE2_Label oLabelError = new MyE2_Label(new MyE2_String("Es existiert mit dem Abnehmer KEIN EU-VERKAUFSVERTRAG !"), MyE2_Label.STYLE_ERROR_BIG());
////				MyE2_Row oRowHelp = new MyE2_Row();
//				oRowHelp.setBackground(new E2_ColorHelpBackground());
//				oRowHelp.add(oLabelError, E2_INSETS.I_10_10_10_10);

				MyE2_Row   oRowHelp = new MyE2_Row();
				oRowHelp.add(oPruefeVertrag.get_ColumnWithMeldungen(vMeldungen), E2_INSETS.I_0_0_0_0);

				
				this.add(oRowHelp, E2_INSETS.I_10_10_10_10);
				this.add(this.oButtonAbbruch, E2_INSETS.I_10_10_10_10);
				this.add(this.oButtonUebernehmen, E2_INSETS.I_10_10_10_10);

				this.oButtonAbbruch.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						FUS_SearchAdresse.this.clean__Field();
						ownPopupWarnWhenNo_HandelsVertrag.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				});

				this.oButtonUebernehmen.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						ownPopupWarnWhenNo_HandelsVertrag.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				});

				this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						if (ownPopupWarnWhenNo_HandelsVertrag.this.get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL))
						{
							FUS_SearchAdresse.this.clean__Field();
						}
					}
				});

				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300), new MyE2_String("WARNUNG ! Kein gültiger AVV-Vertrag vorhanden !!!"));
			}

		}
	}

	public class actionAgentAfterRadiergummi extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new _SEARCH_SearchKontraktFields().get_Found_KontraktField(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
			new _SEARCH_SearchAngebotsFields().get_Found_AngebotField(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
			
			new _SEARCH_SearchKontraktFields().get_Found_KontraktField(FUS_SearchAdresse.this.get_bIS_EK()).clean__Field();
			new _SEARCH_SearchAngebotsFields().get_Found_AngebotField(FUS_SearchAdresse.this.get_bIS_EK()).clean__Field();
			new _SEARCH_SearchSortenFields().get_Found_SortenField(FUS_SearchAdresse.this.get_bIS_EK()).clean__Field();
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchAdresse.this.get_bIS_EK()).clean__Field();
		}
	}

	public String get_cEK_VK()
	{
		return cEK_VK;
	}

	public boolean get_bIS_EK()
	{
		return (this.cEK_VK.equals("EK"));
	}

	// zusatzbutton lager
	private class ownHomeButton extends MyE2_Button
	{

		public ownHomeButton()
		{
			super(E2_ResourceIcon.get_RI("lager_xxs.png"),true);

			this.add_oActionAgent(new actionOpenHomeAdresses());
			this.add_oActionAgent(new actionAgentAfterRadiergummi());
		}

	}

	private class actionOpenHomeAdresses extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new ownBasicModuleContainerShowHomeAdresses();
		}
	}

	private class ownBasicModuleContainerShowHomeAdresses extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainerShowHomeAdresses() throws myException
		{
			super();

			MyE2_Grid oGrid = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

			this.add(oGrid);

			RECORD_ADRESSE ownAdresse = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());

			Vector<RECORD_ADRESSE> vRecAdresse = new Vector<RECORD_ADRESSE>();

			vRecAdresse.add(ownAdresse);

			// alle eignenen lager anhaengen (sortiert)
			RECLIST_ADRESSE ownLieferadressen = new RECLIST_ADRESSE();
			RECLIST_LIEFERADRESSE reclistLiefer = ownAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
			for (int i = 0; i < reclistLiefer.get_vKeyValues().size(); i++)
			{
				ownLieferadressen.ADD(reclistLiefer.get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer(), true);
			}
			// sortieren

			vRecAdresse.addAll(ownLieferadressen.GET_SORTED_VECTOR(bibALL.get_Vector("NAME1", "NAME2"), true));

			Font oFontNormal = new E2_FontPlain();
			Font oFontBold = new E2_FontBold();

			// jetzt anzeigen
			for (int i = 0; i < vRecAdresse.size(); i++)
			{
				RECORD_ADRESSE recA = vRecAdresse.get(i);
				if (i == 0)
				{
					oGrid.add(new ownButtonToSelectAdress(recA.get___KETTE(bibALL.get_Vector("NAME1", "NAME2")), 		recA, oFontBold));
					oGrid.add(new ownButtonToSelectAdress(recA.get___KETTE(bibALL.get_Vector("STRASSE", "HAUSNUMMER")), recA, oFontNormal));
					oGrid.add(new ownButtonToSelectAdress(recA.get___KETTE(bibALL.get_Vector("PLZ", "ORT")), 			recA, oFontNormal));
				} else
				{
					oGrid.add(new ownButtonToSelectAdress(recA.get___KETTE(bibALL.get_Vector("NAME1", "NAME2")), 		recA, oFontNormal));
					oGrid.add(new ownButtonToSelectAdress(recA.get___KETTE(bibALL.get_Vector("STRASSE", "HAUSNUMMER")), recA, oFontNormal));
					oGrid.add(new ownButtonToSelectAdress(recA.get___KETTE(bibALL.get_Vector("PLZ", "ORT")), 			recA, oFontNormal));
				}
			}

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(900), new Extent(600), new MyE2_String("Bitte wählen Sie ..."));
		}

		private class ownButtonToSelectAdress extends MyE2_Button
		{

			public ownButtonToSelectAdress(String cText, RECORD_ADRESSE recADRESSE, Font oFont) throws myException
			{
				super(cText);
				this.EXT().set_C_MERKMAL(recADRESSE.get_ID_ADRESSE_cUF());
				if (oFont != null)
				{
					this.setFont(oFont);
				}
				this.setLineWrap(true);

				this.add_oActionAgent(new actionButtonToSelectAdress());
				if (recADRESSE.is_AKTIV_NO())
				{
					this.set_bEnabled_For_Edit(false);
				}
				
				
			}

			private class actionButtonToSelectAdress extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					FUS_SearchAdresse oThis = FUS_SearchAdresse.this;
					oThis.fill_MaskText_And_Label(ownButtonToSelectAdress.this.EXT().get_C_MERKMAL());
					ownBasicModuleContainerShowHomeAdresses.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);

					//jetzt die aktion ausloesen, die auch nach der standard-suche ausgeloest wird
					FUS_SearchAdresse.this.do_afterFieldWasFilled(ownButtonToSelectAdress.this.EXT().get_C_MERKMAL());

				}
			}

		}

	}

	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
		this.get_oTextFieldForSearchInput().setBackground(new E2_ColorEditBackground());

		if (bMarked)
		{
			this.get_oTextFieldForSearchInput().setBackground(new E2_ColorHelpBackground());
		}
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String(this.cEK_VK.equals("EK") ? "Bitte die Ladestelle ausfüllen" : "Bitte die Abladestelle ausfüllen");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);

		
		MyInteger intInput = new MyInteger(this.get_oTextFieldForSearchInput().getText());

		if (intInput.get_cErrorCODE().equals(MyInteger.ALL_OK))
		{
			if (this.get_bValueWasSearched())
			{
				return true;
			}
		}
		
		if (bMarkWhenFalse) this.mark_MUST_BE_Filled(true);
		return false;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{

		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();

		if (this.get_bIS_EK())
		{
			vRueck.add(new _SEARCH_InputDatum().get_Found_FUS_InputDatum());
			vRueck.add(new _SEARCH_Combo_Transportmittel().get_found_ComboBox());
		} else
		{
			vRueck.add(new _SEARCH_InputDatum().get_Found_FUS_InputDatum());
			vRueck.add(new _SEARCH_Combo_Transportmittel().get_found_ComboBox());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField());
		}

		return vRueck;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();

		vRueck.add(new _SEARCH_SearchSortenFields().get_Found_SortenField(this.get_bIS_EK()));
		vRueck.add(new _SEARCH_SearchKontraktFields().get_Found_KontraktField(this.get_bIS_EK()));
		vRueck.add(new _SEARCH_SearchAngebotsFields().get_Found_AngebotField(this.get_bIS_EK()));
		return vRueck;
	}

	@Override
	public void clean__Field() throws myException
	{
		this.clean();
	}

	public RECORD_ADRESSE get_ActualRecAdresse() throws myException
	{
		RECORD_ADRESSE recRueck = null;

		if (this.get_bIsCorrectFilled(false))
		{
			MyInteger intID_Adresse = new MyInteger(this.get_oTextFieldForSearchInput().getText());

			if (intID_Adresse.get_cErrorCODE().equals(MyInteger.ALL_OK))
			{
				recRueck = new RECORD_ADRESSE(intID_Adresse.get_iValue());
			}
		}
		return recRueck;
	}

	public RECORD_ADRESSE get_ActualRecHauptAdresse() throws myException
	{
		RECORD_ADRESSE recRueck = null;

		if (this.get_bIsCorrectFilled(false))
		{
			MyInteger intID_Adresse = new MyInteger(this.get_oTextFieldForSearchInput().getText());

			if (intID_Adresse.get_cErrorCODE().equals(MyInteger.ALL_OK))
			{
				recRueck = new RECORD_ADRESSE(intID_Adresse.get_iValue());

				if (recRueck.get_ADRESSTYP_lValue(-1l) == myCONST.ADRESSTYP_LIEFERADRESSE)
				{
					recRueck = recRueck.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
				}
			}
		}
		return recRueck;
	}

	@Override
	public Boolean get_IS_EK() throws myException
	{
		return new Boolean(this.get_bIS_EK());
	}

	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}

	@Override
	public void do_afterFieldWasFilled(String cID) throws myException
	{
		
		new _SEARCH_SearchKontraktFields().get_Found_KontraktField(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
		new _SEARCH_SearchAngebotsFields().get_Found_AngebotField(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
		new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
		
		//pruefen, ob eine Adresse des mandanten, dann felder disablen und vorbesetzen
		RECORD_ADRESSE  recAdresse = FUS_SearchAdresse.this.get_ActualRecHauptAdresse();
		
		if (bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(0l).longValue()==recAdresse.get_ID_ADRESSE_lValue(-1l).longValue())
		{
			new _SEARCH_SearchKontraktFields().get_Found_KontraktField(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(false);
			new _SEARCH_SearchAngebotsFields().get_Found_AngebotField(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(false);
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchAdresse.this.get_bIS_EK()).set_bEnabled_For_Edit(false);
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchAdresse.this.get_bIS_EK()).setText("0");
		}
		else
		{
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchAdresse.this.get_bIS_EK()).setText("");
		}

		
	}
	

}
