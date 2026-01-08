package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatzArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_MASK__ComponentMAP;
import rohstoff.Echo2BusinessLogic._4_ALL.FirmenSelectorKlasse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS extends DB_SEARCH_Adress
{

	private String ctABLE_NAME = null;
	private String cVorgang_Typ = null;
	
	
	public BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS(SQLField osqlfield, String cNAME_OF_DBTABLE, SQLFieldMAP oSQLFieldMAP) throws myException
	{
		super(osqlfield);
		this.ctABLE_NAME = cNAME_OF_DBTABLE;
		
		//aenderung 2010-10-04
		if (oSQLFieldMAP.get_("VORGANG_TYP") instanceof SQLFieldForRestrictTableRange)
		{
			this.cVorgang_Typ = ((SQLFieldForRestrictTableRange) oSQLFieldMAP.get_("VORGANG_TYP")).get_cRestictionValue_IN_DB_FORMAT();
			if (this.cVorgang_Typ.startsWith("'")) {this.cVorgang_Typ=this.cVorgang_Typ.substring(1);}
			if (this.cVorgang_Typ.endsWith("'")) {this.cVorgang_Typ=this.cVorgang_Typ.substring(0,this.cVorgang_Typ.length()-1);}
		}
		
		
		this.set_oPopupWidth(new Extent(600));   
		this.set_oPopupHigh(new Extent(600));
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		if (this.ctABLE_NAME.equals("JT_VKOPF_TPA"))
		{
			this.set_AddOnComponent(new zusatzSearch_TPA());
		}
		if (this.ctABLE_NAME.equals("JT_VKOPF_RG"))
		{
			this.set_AddOnComponent(new zusatzSearch_RG());
		}
		if (this.ctABLE_NAME.equals("JT_VKOPF_KON"))
		{
			this.set_AddOnComponent(new zusatzSearch_KON());
		}
		if (this.ctABLE_NAME.equals("JT_VKOPF_STD"))
		{
			this.set_AddOnComponent(new zusatzSearch_STD());
		}
	}

	/*
	 * mask-setting-agent fuer das laden der adress-werte in die maske
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS oThis = BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this;
			
			if (bAfterAction)
			{
				MyLong lID_ADRESSE = new MyLong(cMaskValue, new Long(-1), new Long(-1));
				if (lID_ADRESSE.get_lValue()==-1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Die Adresse konnte nicht bestimmt werden !"));
					return;
				}
						
				
				RECORD_ADRESSE_extend oAdress = new RECORD_ADRESSE_extend(lID_ADRESSE.get_lValue());
				
				E2_ComponentMAP 			oMap = 				oSearchField.EXT().get_oComponentMAP();
				
				String[] cTextFieldsBase = {"VORNAME","NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","ORTZUSATZ","PLZ_POB","POB","KDNR"};
				
				// felder fuellen
				for (int i=0;i<cTextFieldsBase.length;i++)
				{
					((MyE2_DB_TextField) oMap.get__Comp(cTextFieldsBase[i])).set_cActualMaskValue(oAdress.get_UnFormatedValue(cTextFieldsBase[i]));
				}
				
				//2011-02-27: aenderung an der Feldfindung:
				HashMap<String, String>  hmAnsprechInfos = oAdress.get_hmFormularFelderAnsprechpartner(oThis.cVorgang_Typ);
				
				((MyE2_DB_TextField) oMap.get__Comp("NAME_ANSPRECHPARTNER")).set_cActualMaskValue(hmAnsprechInfos.get("ANSPRECHPARTNER"));
				((MyE2_DB_TextField) oMap.get__Comp("TELEFON_AUF_FORMULAR")).set_cActualMaskValue(hmAnsprechInfos.get("TELEFON"));
				((MyE2_DB_TextField) oMap.get__Comp("TELEFAX_AUF_FORMULAR")).set_cActualMaskValue(hmAnsprechInfos.get("TELEFAX"));
				((MyE2_DB_TextField) oMap.get__Comp("EMAIL_AUF_FORMULAR")).set_cActualMaskValue(hmAnsprechInfos.get("EMAIL"));
				
				
				//20180528: zusatz-waehrungen beachten, die alte waehrung einlesen, die dropdowns neu setzen und anschliessend 
				//          entweder die vorige oder die hautwaehrung der adresse einsetzen
				MyE2_DB_SelectField selWaehrung = ((MyE2_DB_SelectField) oMap.get__Comp("ID_WAEHRUNG_FREMD"));
				//
				
				//nur aktualisieren, wenn das selectfield noch enabled ist
				if (selWaehrung.isEnabled()) {
					((MyE2_DB_SelectField) oMap.get__Comp("ID_WAEHRUNG_FREMD")).set_ActiveValue(oAdress.get_ID_WAEHRUNG_cF_NN(""));
				}
				
				((MyE2_DB_ComboBoxErsatz) oMap.get__Comp("LAENDERCODE")).set_cActualMaskValue(oAdress.get_UP_RECORD_LAND_id_land()!=null?oAdress.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cF_NN(""):"");

				
				//aenderung 2010-11-26: zahlungs- und lieferbedingungen EK- und VK- unterscheiden
				this.set_Zahlungs_und_LieferbedingungsBlock(oMap, oAdress.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen(), oAdress.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen());

				if (	oThis.cVorgang_Typ.equals(myCONST.VORGANGSART_ANGEBOT)  || 
						oThis.cVorgang_Typ.equals(myCONST.VORGANGSART_VK_KONTRAKT)  ||
						oThis.cVorgang_Typ.equals(myCONST.VORGANGSART_RECHNUNG))
				{
					this.set_Zahlungs_und_LieferbedingungsBlock(oMap, oAdress.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk(), oAdress.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk());
				}
				
				
				
				/*
				 * wenn es eine Gutschrift ist, dann wird aus dem mandanten-stamm der Scheckdruckvermerk (falls der lieferant dafuer vorgesehen ist) 
				 * in den Fuss-Text geschrieben
				 */
				E2_ComponentMAP  oMapOWN = BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this.EXT().get_oComponentMAP();
				if (oMapOWN instanceof BSRG_K_MASK__ComponentMAP)
				{
					boolean bIsGutschrift = false;
					bIsGutschrift = ((BSRG_K_MASK__ComponentMAP)oMapOWN).get_oBS_Setting().get_cBELEGTYP().equals(myCONST.VORGANGSART_GUTSCHRIFT);
					
					if (bIsGutschrift && oAdress.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_SCHECKDRUCK_JN_YES())
					{
						String cText = ((MyE2_DB_TextArea_WithSelektorEASY)((BSRG_K_MASK__ComponentMAP)oMapOWN).get__Comp("FORMULARTEXT_ENDE")).get_oTextArea().getText();
						String cScheckdruckText = bibALL.get_RECORD_MANDANT().get_SCHECKVERMERK_AUF_GUTSCHRIFT_cF_NN("");
						
						((MyE2_DB_TextArea_WithSelektorEASY)((BSRG_K_MASK__ComponentMAP)oMapOWN).get__Comp("FORMULARTEXT_ENDE")).get_oTextArea().setText(
								S.isEmpty(cText)?cScheckdruckText:cText+"\n"+cScheckdruckText);
					}
				}
				
				
				//aenderung 2010-11-03: weitere definitionen ansprechpartner und sachbearbeiter nachladen
				//zuerst reset
				oMap.get__DBComp("ID_USER_ANSPRECH_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("NAME_ANSPRECH_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("TEL_ANSPRECH_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("FAX_ANSPRECH_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("ID_USER_SACHBEARB_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("NAME_SACHBEARB_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("TEL_SACHBEARB_INTERN").set_cActualMaskValue("");
				oMap.get__DBComp("FAX_SACHBEARB_INTERN").set_cActualMaskValue("");
				
				if (oAdress.get_UP_RECORD_USER_id_user()!=null)
				{
					oMap.get__DBComp("ID_USER_ANSPRECH_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user().get_ID_USER_cF());
					oMap.get__DBComp("NAME_ANSPRECH_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "));
					oMap.get__DBComp("TEL_ANSPRECH_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user().get_TELEFON_cF_NN(""));
					oMap.get__DBComp("FAX_ANSPRECH_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user().get_TELEFAX_cF_NN(""));
				}
				
				if (oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter()!=null)
				{
					oMap.get__DBComp("ID_USER_SACHBEARB_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get_ID_USER_cF());
					oMap.get__DBComp("NAME_SACHBEARB_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "));
					oMap.get__DBComp("TEL_SACHBEARB_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get_TELEFON_cF_NN(""));
					oMap.get__DBComp("FAX_SACHBEARB_INTERN").set_cActualMaskValue(oAdress.get_UP_RECORD_USER_id_user_sachbearbeiter().get_TELEFAX_cF_NN(""));
				}
				
				
				//aenderung 2010-10-04: der inhalt des felds: BELEG_IST_INTERN wird aus der adresse geladen
				if (oThis.ctABLE_NAME.equals("JT_VKOPF_RG"))
				{
					if (oThis.cVorgang_Typ!=null && oThis.cVorgang_Typ.equals(myCONST.VORGANGSART_RECHNUNG))
					{
						oMap.get__DBComp("BELEG_IST_INTERN").set_cActualMaskValue(oAdress.get_RECHNUNGEN_SPERREN_cF_NN("N"));
					}
					else
					{
						oMap.get__DBComp("BELEG_IST_INTERN").set_cActualMaskValue(oAdress.get_GUTSCHRIFTEN_SPERREN_cF_NN("N"));
					}

					
					//2011-01-14: nur die "Haupt-UST-Ids standardmaessig uebernehmen
//					FirmenSearch_USTID oUST_Search = new FirmenSearch_USTID(oAdress);
//					oMap.get__DBComp("UMSATZSTEUERID").set_cActualMaskValue(S.NN(oUST_Search.get_cUST_ID()));
//					oMap.get__DBComp("UMSATZSTEUERLKZ").set_cActualMaskValue(S.NN(oUST_Search.get_cUST_LKZ()));
					oMap.get__DBComp("UMSATZSTEUERID").set_cActualMaskValue(oAdress.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERID_cUF_NN(""));
					oMap.get__DBComp("UMSATZSTEUERLKZ").set_cActualMaskValue(oAdress.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERLKZ_cUF_NN(""));
					
					if (S.isFull(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF()))
					{
						RECORD_ADRESSE recAdresseMandant = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
					
						oMap.get__DBComp("UMSATZSTEUERID_MANDANT").set_cActualMaskValue(recAdresseMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERID_cUF_NN(""));
						oMap.get__DBComp("UMSATZSTEUERLKZ_MANDANT").set_cActualMaskValue(recAdresseMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERLKZ_cUF_NN(""));
					}
				}
				
				//2011-11-17: meldungen einblenden
				new __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(lID_ADRESSE.get_cUF_LongString(), BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this.cVorgang_Typ).ACTIVATE_MESSAGES();
			}
			
			
			
			
			
		}
		
		
		
		
		
		private void set_Zahlungs_und_LieferbedingungsBlock(E2_ComponentMAP oMap, RECORD_ZAHLUNGSBEDINGUNGEN recZahl, RECORD_LIEFERBEDINGUNGEN recLief) throws myException
		{
			
			if (recZahl!=null)
			{
				((MyE2_DB_SelectField) oMap.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).set_ActiveValue(recZahl.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(""));

				((MyE2_DB_TextField) oMap.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recZahl.get_BEZEICHNUNG_cUF_NN(""));
				((MyE2_DB_TextField) oMap.get__Comp("ZAHLTAGE")).set_cActualMaskValue(recZahl.get_ZAHLTAGE_cUF_NN(""));
				((MyE2_DB_TextField) oMap.get__Comp("FIXMONAT")).set_cActualMaskValue(recZahl.get_FIXMONAT_cUF_NN(""));
				((MyE2_DB_TextField) oMap.get__Comp("FIXTAG")).set_cActualMaskValue(recZahl.get_FIXTAG_cUF_NN(""));
				((MyE2_DB_TextField) oMap.get__Comp("SKONTO_PROZENT")).set_cActualMaskValue(recZahl.get_SKONTO_PROZENT_cF_NN(""));
			}
			else
			{
				((MyE2_DB_SelectField) oMap.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).setSelectedIndex(0);
				((MyE2_DB_TextField) oMap.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue("");
				((MyE2_DB_TextField) oMap.get__Comp("ZAHLTAGE")).set_cActualMaskValue("");
				((MyE2_DB_TextField) oMap.get__Comp("FIXMONAT")).set_cActualMaskValue("");
				((MyE2_DB_TextField) oMap.get__Comp("FIXTAG")).set_cActualMaskValue("");
				((MyE2_DB_TextField) oMap.get__Comp("SKONTO_PROZENT")).set_cActualMaskValue("");
			}

			
			
			
			if (recLief!=null)
			{
				((MyE2_DB_ComboBoxErsatzArea) oMap.get__Comp("LIEFERBEDINGUNGEN")).set_cActualMaskValue(recLief.get_BEZEICHNUNG_cF_NN(""));
			}
			else
			{
				((MyE2_DB_ComboBoxErsatzArea) oMap.get__Comp("LIEFERBEDINGUNGEN")).set_cActualMaskValue("");
			}

		}
		
		
		
		
		
		
	}
	
	
	
	
	/*
	 * zusatzbutton fuer das search-field
	 */
	private class zusatzSearch_RG extends MyE2_Button
	{
		public zusatzSearch_RG()
		{
			super(E2_ResourceIcon.get_RI("suchkaskade.png"), true);
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new PopupContainer4Selector();
				}
			});
		}
		
		//zusaetze fuer einen multicolumn -selector 
		private class PopupContainer4Selector extends E2_BasicModuleContainer
		{
			public PopupContainer4Selector() throws myException
			{
				super();
				FirmenSelector oFS = new FirmenSelector();
				this.add(oFS);
			    oFS.START("", true);
				
			    this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(630), new Extent(400), new MyE2_String("Auswahl einer Adresse ..."));
			}
			
			private class FirmenSelector extends FirmenSelectorKlasse
			{
				public FirmenSelector()	throws myException
				{
					super();
					this.set_ActionAgentFuerLastSelection(new actionAfterSelectLastCol());
				}
			}
				
			private class actionAfterSelectLastCol extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS oThis = BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this;
					
					//die execinfo wird vom eigentlichen selectionsbutton weitergereicht
					MyE2_Button oButton_Mit_ID_Adress = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
					
					oThis.set_cActualMaskValue(oButton_Mit_ID_Adress.EXT().get_C_MERKMAL(), true, true,true);
					
					if (bibMSG.get_bIsOK())
					{
						PopupContainer4Selector.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
		}
	}
	

	
	
	/*
	 * zusatzbutton fuer das search-field
	 */
	private class zusatzSearch_KON extends MyE2_Button
	{
		public zusatzSearch_KON()
		{
			super(E2_ResourceIcon.get_RI("suchkaskade.png"), true);
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new PopupContainer4Selector();
				}
			});
		}
		
		//zusaetze fuer einen multicolumn -selector 
		private class PopupContainer4Selector extends E2_BasicModuleContainer
		{
			public PopupContainer4Selector() throws myException
			{
				super();
				FirmenSelector oFS = new FirmenSelector();
				this.add(oFS);
			    oFS.START("", true);
				
			    this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(630), new Extent(400), new MyE2_String("Auswahl einer Adresse ..."));
			}
			
			private class FirmenSelector extends FirmenSelectorKlasse
			{
				public FirmenSelector()	throws myException
				{
					super();
					this.set_ActionAgentFuerLastSelection(new actionAfterSelectLastCol());
				}
			}
				
			private class actionAfterSelectLastCol extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS oThis = BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this;
					
					//die execinfo wird vom eigentlichen selectionsbutton weitergereicht
					MyE2_Button oButton_Mit_ID_Adress = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
					
					oThis.set_cActualMaskValue(oButton_Mit_ID_Adress.EXT().get_C_MERKMAL(), true, true,true);
					
					if (bibMSG.get_bIsOK())
					{
						PopupContainer4Selector.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
		}
	}

	
	
	/*
	 * zusatzbutton fuer das search-field
	 */
	private class zusatzSearch_TPA extends MyE2_Button
	{
		public zusatzSearch_TPA()
		{
			super(E2_ResourceIcon.get_RI("suchkaskade.png"), true);
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new PopupContainer4Selector();
				}
			});
		}
		
		//zusaetze fuer einen multicolumn -selector 
		private class PopupContainer4Selector extends E2_BasicModuleContainer
		{
			public PopupContainer4Selector() throws myException
			{
				super();
				FirmenSelector oFS = new FirmenSelector();
				this.add(oFS);
			    oFS.START("", true);
				
			    this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(630), new Extent(400), new MyE2_String("Auswahl einer Adresse ..."));
			}
			
			private class FirmenSelector extends FirmenSelectorKlasse
			{
				public FirmenSelector()	throws myException
				{
					super();
					this.set_ActionAgentFuerLastSelection(new actionAfterSelectLastCol());
				}
			}
				
			private class actionAfterSelectLastCol extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS oThis = BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this;
					
					//die execinfo wird vom eigentlichen selectionsbutton weitergereicht
					MyE2_Button oButton_Mit_ID_Adress = (MyE2_Button)execInfo.get_MyActionEvent().getSource();

					oThis.set_cActualMaskValue(oButton_Mit_ID_Adress.EXT().get_C_MERKMAL(), true, true,true);

					if (bibMSG.get_bIsOK())
					{
						PopupContainer4Selector.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
		}
	}

	

	
	
	/*
	 * zusatzbutton fuer das search-field
	 */
	private class zusatzSearch_STD extends MyE2_Button
	{
		public zusatzSearch_STD()
		{
			super(E2_ResourceIcon.get_RI("suchkaskade.png"), true);
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new PopupContainer4Selector();
				}
			});
		}
		
		//zusaetze fuer einen multicolumn -selector 
		private class PopupContainer4Selector extends E2_BasicModuleContainer
		{
			public PopupContainer4Selector() throws myException
			{
				super();
				FirmenSelector oFS = new FirmenSelector();
				this.add(oFS);
			    oFS.START("", true);
				
			    this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(630), new Extent(400), new MyE2_String("Auswahl einer Adresse ..."));
			}
			
			private class FirmenSelector extends FirmenSelectorKlasse
			{
				public FirmenSelector()	throws myException
				{
					super();
					this.set_ActionAgentFuerLastSelection(new actionAfterSelectLastCol());
				}
			}
				
			private class actionAfterSelectLastCol extends XX_ActionAgent
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS oThis = BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS.this;
					
					//die execinfo wird vom eigentlichen selectionsbutton weitergereicht
					MyE2_Button oButton_Mit_ID_Adress = (MyE2_Button)execInfo.get_MyActionEvent().getSource();

					oThis.set_cActualMaskValue(oButton_Mit_ID_Adress.EXT().get_C_MERKMAL(), true, true,true);
					
					if (bibMSG.get_bIsOK())
					{
						PopupContainer4Selector.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				}
			}
		}
	}

	
}
