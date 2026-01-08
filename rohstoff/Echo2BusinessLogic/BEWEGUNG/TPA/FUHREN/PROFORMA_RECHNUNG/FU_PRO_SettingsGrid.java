package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;

import java.math.BigDecimal;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT_NG;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_PROFORMA_RECHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.SEARCH_Adress;
import echopointng.Separator;

/*
 * grid mit allen Angaben fuer eine proforma-Rechnung (fuer jede moegliche proforma-rechnung eines)
 */
public class FU_PRO_SettingsGrid extends MyE2_Grid {

	
	private MyE2_TextField_DatePOPUP_OWN	oWaehle_DruckDatum = 	new MyE2_TextField_DatePOPUP_OWN();
	
	private MyE2_TextArea_WithSelektorEASY  oTF_AnfangsText = new MyE2_TextArea_WithSelektorEASY(myCONST.VORGANGSART_RECHNUNG+"ANFANG",600,5);

	private MyE2_TextArea_WithSelektorEASY  oTF_EndText = new MyE2_TextArea_WithSelektorEASY(myCONST.VORGANGSART_RECHNUNG+"ENDE",600,5);
	private MyE2_TextField 			   		 oTF_EPREIS = new MyE2_TextField("",100,12);
	private MyE2_Label  					 oLAB_BELEG_NR = new MyE2_Label();
	
	private SEARCH_Adress         			 oSearchAdressImporteur = new SEARCH_Adress();
	private SEARCH_Adress         			 oSearchAdressSpaetereAbnehmer = new SEARCH_Adress();
	
	private FU_PRO_PassendeFuhrenInfos      oFU_PRO_PassendeFuhrenInfos = null;
	private RECORD_PROFORMA_RECHNUNG        oREC_Proforma_Rechnung = null;
	
	private MyE2_TextField  				 oTF_VORNAME_SACHBEARBEITER = new MyE2_TextField("", 300, 30);
	private MyE2_TextField  				 oTF_NAME_SACHBEARBEITER = new MyE2_TextField("", 300, 40);
	private MyE2_TextField  				 oTF_TEL_SACHBEARBEITER = new MyE2_TextField("", 300, 50);
	private MyE2_TextField  				 oTF_EMAIL_SACHBEARBEITER = new MyE2_TextField("", 300, 100);
	
	private MyE2_TextField  				 oTF_LIEFERBEDINGUNGEN = new MyE2_TextField("", 600, 200);
	
	private Component_USER_DROPDOWN_NEW   	 oSelUser = new Component_USER_DROPDOWN_NEW(false, 300);
	
	private MyE2_CheckBox                   oCB_SettingIstActive = null;

	private MyE2_Button     				 oBT_HoleAlteImporteur = 			new MyE2_Button(E2_ResourceIcon.get_RI("wizard_mini.png"),true);
	private MyE2_Button     				 oBT_HoleAlteAbnehmer = 			new MyE2_Button(E2_ResourceIcon.get_RI("wizard_mini.png"),true);
	private MyE2_Button     				 oBT_HoleAlteLieferbedingungen = 	new MyE2_Button(E2_ResourceIcon.get_RI("wizard_mini.png"),true);

	/**
	 * fuer jede fuhre wird ein Grid-Objekt generiert, das den passenden jasperhash und das RECORD_PROFORMA - objekt enthaelt 
	 * @param oActionAgent
	 * @throws myException
	 */
	public FU_PRO_SettingsGrid( 	ACTIONAGENT_MAIL_AND_REPORT_NG 	oActionAgent, 
									FU_PRO_PassendeFuhrenInfos 		o_FU_PRO_PassendeFuhrenInfos,
									MyE2_CheckBox   				o_cb_MustBePrinted) throws myException {
		
		super(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oSelUser.set_ActiveInhalt_or_FirstInhalt("");
		this.oSelUser.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FU_PRO_SettingsGrid.this.fuelle_user_daten();
			}
		});
		
		
		this.oSearchAdressSpaetereAbnehmer.add_AddOnComponent(this.oBT_HoleAlteAbnehmer);
		this.oSearchAdressImporteur.add_AddOnComponent(this.oBT_HoleAlteImporteur);
		
		this.oSearchAdressSpaetereAbnehmer.get_oTextForAnzeige().setWidth(new Extent(485));
		this.oSearchAdressImporteur.get_oTextForAnzeige().setWidth(new Extent(485));
		
		this.oFU_PRO_PassendeFuhrenInfos = o_FU_PRO_PassendeFuhrenInfos;
		this.oREC_Proforma_Rechnung = this.oFU_PRO_PassendeFuhrenInfos.get_RecProforma();
		this.oCB_SettingIstActive = o_cb_MustBePrinted;
		
		this.oCB_SettingIstActive.setToolTipText(new MyE2_String("Nur wenn das Formular selektiert ist, wird eine Proforma-Rechnung erzeugt und gedruckt").CTrans());
		
		this.oCB_SettingIstActive.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FU_PRO_SettingsGrid.this.set_FieldsActive(
				FU_PRO_SettingsGrid.this.oCB_SettingIstActive.isSelected());
			}
		});
		
		
		this.oWaehle_DruckDatum.get_oTextField().set_bEnabled_For_Edit(false);
		
		//int[] iBreit = {150,150,150};
		
		this.setColumnWidth(0,new Extent(120));

		Insets  insetStd = new Insets(5,1,5,1);
		
//		this.add(new MyE2_Label(new MyE2_String("Druck einer Proforma-Rechnung ...")),3, insetStd);
		this.add(new MyE2_Label(this.oFU_PRO_PassendeFuhrenInfos.get_Text4Info()),3, insetStd);
		this.add(new Separator(),this.getSize(), insetStd);

		this.add(new MyE2_Label(new MyE2_String("Beleg: ")),1, insetStd);
		this.add(new MyE2_Label(new MyE2_String(this.oREC_Proforma_Rechnung.get_BELEGNR_PROFORMA_cUF_NN("-"))),2, insetStd);

		this.add(new Separator(),this.getSize(), insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Formular drucken: ")),1, insetStd);
		this.add(this.oCB_SettingIstActive,2, insetStd);
		
		this.add(new Separator(),this.getSize(), insetStd);

		this.add(new MyE2_Label(new MyE2_String("Formulardatum: ")),1, insetStd);
		this.add(this.oWaehle_DruckDatum,2, insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Eingangstext: ")),1, insetStd);
		this.add(this.oTF_AnfangsText,2, insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Schlusstext: ")),1, insetStd);
		this.add(this.oTF_EndText,2, insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Importeur: ")),1, insetStd);
		this.add(this.oSearchAdressImporteur,2, insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Abnehmer: ")),1, insetStd);
		this.add(this.oSearchAdressSpaetereAbnehmer,2, insetStd);
		
		
		MyE2_Grid oGridHelp = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add(new MyE2_Label(new MyE2_String("Lieferbedingungen: ")),1, insetStd);
		oGridHelp.add(this.oTF_LIEFERBEDINGUNGEN, E2_INSETS.I_0_0_0_0);
		oGridHelp.add(this.oBT_HoleAlteLieferbedingungen, E2_INSETS.I_5_0_0_0);
		this.add(oGridHelp,2, insetStd);
		
		this.add(new Separator(),this.getSize(), insetStd);

		this.add(new MyE2_Label(new MyE2_String("Einzelpreis: ")),1, insetStd);
		this.add(this.oTF_EPREIS,2, insetStd);
		
		this.add(new Separator(),this.getSize(), insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Sachbearbeiter: ")),1, insetStd);
		this.add(this.oSelUser,2, insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Vorname / Name: ")),1, insetStd);
		this.add(this.oTF_VORNAME_SACHBEARBEITER,1, insetStd);
		this.add(this.oTF_NAME_SACHBEARBEITER,1, insetStd);
		
		this.add(new MyE2_Label(new MyE2_String("Telefon / eMail: ")),1, insetStd);
		this.add(this.oTF_TEL_SACHBEARBEITER,1, insetStd);
		this.add(this.oTF_EMAIL_SACHBEARBEITER,1, insetStd);
		
		
		this.fuelle_Felder_mit_RECORD(this.oREC_Proforma_Rechnung);
		
		
		//jetzt noch ein paar einstellungen (zoll- und abnehmeradresse nur offen, wenn es ein sonderfall (lieferung an eigene auslandsadresse mit spaeterem Verkauf) ist
		if (!this.oFU_PRO_PassendeFuhrenInfos.get_bSonderFall()) {
			this.oSearchAdressImporteur.clean();
			this.oSearchAdressImporteur.set_bEnabled_For_Edit(false);
			
			this.oSearchAdressSpaetereAbnehmer.clean();
			this.oSearchAdressSpaetereAbnehmer.set_bEnabled_For_Edit(false);
		}
		if (S.isEmpty(this.oWaehle_DruckDatum.get_oTextField().getText())) {
			this.oWaehle_DruckDatum.get_oTextField().setText(bibALL.get_cDateNOW());
		}
		
		
		this.oBT_HoleAlteLieferbedingungen.add_oActionAgent(new FU_PRO_Action_SucheAlteLieferbedingungen(this.oTF_LIEFERBEDINGUNGEN));
		this.oBT_HoleAlteImporteur.add_oActionAgent(new FU_PRO_Action_SucheAlteAdressen(this.oSearchAdressImporteur, _DB.PROFORMA_RECHNUNG$ID_ADRESSE_VERTRETER));
		this.oBT_HoleAlteAbnehmer.add_oActionAgent(new FU_PRO_Action_SucheAlteAdressen(this.oSearchAdressSpaetereAbnehmer, _DB.PROFORMA_RECHNUNG$ID_ADRESSE_KAEUFER));
		
		this.oBT_HoleAlteLieferbedingungen.setToolTipText(new MyE2_String("Öffnet einen Dialog mit den maximal hundert letzten Einträgen in diesem Feld").CTrans());
		this.oBT_HoleAlteImporteur.setToolTipText(new MyE2_String("Öffnet einen Dialog mit den maximal hundert letzten Einträgen in diesem Feld").CTrans());
		this.oBT_HoleAlteAbnehmer.setToolTipText(new MyE2_String("Öffnet einen Dialog mit den maximal hundert letzten Einträgen in diesem Feld").CTrans());
		
		
		
	}
	
	/**
	 * uebernimmt die daten des handelnden benutzers in die Felder der proforma-rechnungs-table
	 * @throws myException
	 */
	private void fuelle_user_daten() throws myException {
		this.oTF_VORNAME_SACHBEARBEITER.setText("");
		this.oTF_NAME_SACHBEARBEITER.setText("");
		this.oTF_TEL_SACHBEARBEITER.setText("");
		this.oTF_EMAIL_SACHBEARBEITER.setText("");
		
		if (S.isFull(this.oSelUser.get_ActualWert())) {
			RECORD_USER recUser = new RECORD_USER(this.oSelUser.get_ActualWert());
			
			this.oTF_VORNAME_SACHBEARBEITER.setText(recUser.get_VORNAME_cUF_NN(""));
			this.oTF_NAME_SACHBEARBEITER.setText(recUser.get_NAME1_cUF_NN(""));
			this.oTF_TEL_SACHBEARBEITER.setText(recUser.get_TELEFON_cUF_NN(""));
			this.oTF_EMAIL_SACHBEARBEITER.setText(recUser.get_EMAIL_cUF_NN(""));
		}
	}
	
	
	private void fuelle_Felder_mit_RECORD(RECORD_PROFORMA_RECHNUNG recPro) throws myException {
		this.oTF_AnfangsText.get_oTextArea().setText(recPro.get_FORMULARTEXT_ANFANG_cUF_NN(""));
		this.oTF_EndText.get_oTextArea().setText(recPro.get_FORMULARTEXT_ENDE_cUF_NN(""));
		this.oLAB_BELEG_NR.set_Text(recPro.get_BELEGNR_PROFORMA_cUF_NN("-"));
		
		if (recPro.get_ID_ADRESSE_VERTRETER_lValue(-1l)>0) {
			this.oSearchAdressImporteur.fill_MaskText_And_Label(recPro.get_ID_ADRESSE_VERTRETER_cUF_NN(""));
		}
		if (recPro.get_ID_ADRESSE_KAEUFER_lValue(-1l)>0) {
			this.oSearchAdressSpaetereAbnehmer.fill_MaskText_And_Label(recPro.get_ID_ADRESSE_KAEUFER_cUF_NN(""));
		}
		
		//wenn der fuhrenpreis 0 ist, dann den aus den recPro - daten nehmen
		if (this.oFU_PRO_PassendeFuhrenInfos.get_RelevantPriceDB().compareTo(BigDecimal.ZERO)==0) {
			this.oTF_EPREIS.setText(recPro.get_E_PREIS_cF_NN("0"));
		} else {
			//sonst immer den Fuhrenpreis uebernehmen
			this.oTF_EPREIS.setText(this.oFU_PRO_PassendeFuhrenInfos.get_RelevantPrice());
		}
		
		this.oWaehle_DruckDatum.get_oTextField().setText(recPro.get_BELEGDATUM_cF_NN(bibALL.get_cDateNOW()));
		this.oTF_VORNAME_SACHBEARBEITER.setText(recPro.get_VORNAME_SACHBEARBEITER_cUF_NN(""));
		this.oTF_NAME_SACHBEARBEITER.setText(recPro.get_NAME_SACHBEARBEITER_cUF_NN(""));
		this.oTF_TEL_SACHBEARBEITER.setText(recPro.get_TELEFON_SACHBEARBEITER_cUF_NN(""));
		this.oTF_EMAIL_SACHBEARBEITER.setText(recPro.get_EMAIL_SACHBEARBEITER_cUF_NN(""));
		
		this.oTF_LIEFERBEDINGUNGEN.setText(recPro.get_LIEFERBEDINGUNGEN_cUF_NN(""));
	}
	
	
	public MyE2_TextField_DatePOPUP_OWN get_oWaehle_DruckDatum() {
		return oWaehle_DruckDatum;
	}

	public MyE2_TextArea_WithSelektorEASY get_oTF_AnfangsText() {
		return oTF_AnfangsText;
	}

	public MyE2_TextArea_WithSelektorEASY get_oTF_EndText() {
		return oTF_EndText;
	}

	public MyE2_TextField get_oTF_EPREIS() {
		return oTF_EPREIS;
	}

	public MyE2_Label get_oLAB_BELEG_NR() {
		return oLAB_BELEG_NR;
	}

	public SEARCH_Adress get_oSearchAdressZollvertreter() {
		return oSearchAdressImporteur;
	}

	public SEARCH_Adress get_oSearchAdressSpaetereAbnehmer() {
		return oSearchAdressSpaetereAbnehmer;
	}

	public FU_PRO_PassendeFuhrenInfos get_oFU_PRO_PassendeFuhrenInfos() {
		return oFU_PRO_PassendeFuhrenInfos;
	}

	public RECORD_PROFORMA_RECHNUNG get_oREC_Proforma_Rechnung() {
		return oREC_Proforma_Rechnung;
	}

	

	
	
	
	public MyE2_MessageVector  pruefe_Inputs_und_schreibe_werte_in_PROFORMA_RECHUNG_TABLE() throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
			
		//zwingende bedingung: wenn die Zieladresse die adresse des mandanten ist, dann muessen zwei zusatzadressen hinterlegt werden
		if (this.oFU_PRO_PassendeFuhrenInfos.get_bSonderFall()) {
			String cID_AdresseZollvertreter = 		S.NN(this.oSearchAdressImporteur.get_cActualMaskValue());
			String cID_AdresseSpaetererAbnehmer = 	S.NN(this.oSearchAdressSpaetereAbnehmer.get_cActualMaskValue());
			
			if (!(bibALL.isLong(cID_AdresseZollvertreter) && bibALL.isLong(cID_AdresseSpaetererAbnehmer))) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String(
								"Achtung: Bei Lieferung auf eigenes Auslandslager müssen die Adressen des Zollvertreters und des späteren Abnehmers vorhanden sein !!")));
			}
		}
		
		if (S.isEmpty(this.oWaehle_DruckDatum.get_oTextField().getText())) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String(
							"Achtung: Es muss immer ein Datum für jeden Beleg vorhanden sein !!")));
			
		}
		
		if (S.isEmpty(this.oTF_EPREIS.getText())) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(
					new MyE2_String(
							"Achtung: Es muss immer ein Preis für jeden Beleg vorhanden sein !!")));
			
		}
		
		
		if (oMV.get_bIsOK()) {
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_BELEGDATUM(this.oWaehle_DruckDatum.get_oTextField().getText()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_ID_ADRESSE_KAEUFER(this.oSearchAdressSpaetereAbnehmer.get_cActualMaskValue()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_ID_ADRESSE_VERTRETER(this.oSearchAdressImporteur.get_cActualMaskValue()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_E_PREIS(this.oTF_EPREIS.getText()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_FORMULARTEXT_ANFANG(this.oTF_AnfangsText.get_oTextArea().getText()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_FORMULARTEXT_ENDE(this.oTF_EndText.get_oTextArea().getText()));

			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_VORNAME_SACHBEARBEITER(this.oTF_VORNAME_SACHBEARBEITER.getText()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_NAME_SACHBEARBEITER(this.oTF_NAME_SACHBEARBEITER.getText()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_TELEFON_SACHBEARBEITER(this.oTF_TEL_SACHBEARBEITER.getText()));
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_EMAIL_SACHBEARBEITER(this.oTF_EMAIL_SACHBEARBEITER.getText()));
			
			oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.set_NEW_VALUE_LIEFERBEDINGUNGEN(this.oTF_LIEFERBEDINGUNGEN.getText()));

			if (oMV.get_bIsOK()) {
				oMV.add_MESSAGE(this.oREC_Proforma_Rechnung.UPDATE(null, true));
				this.oREC_Proforma_Rechnung.REBUILD();
			}
		}
		
		return oMV;
	}
	
	public MyE2_CheckBox get_oCB_SettingIstActive() {
		return oCB_SettingIstActive;
	}



	public void set_FieldsActive(boolean bActive) throws myException {
		this.oTF_AnfangsText.set_bEnabled_For_Edit(bActive);
		this.oTF_EndText.set_bEnabled_For_Edit(bActive);
		this.oTF_EPREIS.set_bEnabled_For_Edit(bActive);
		this.oSearchAdressImporteur.set_bEnabled_For_Edit(bActive);
		this.oSearchAdressSpaetereAbnehmer.set_bEnabled_For_Edit(bActive);
		this.oWaehle_DruckDatum.set_bEnabled_For_Edit(bActive);
		
		this.oTF_LIEFERBEDINGUNGEN.set_bEnabled_For_Edit(bActive);
		
		
		
		this.oSelUser.set_bEnabled_For_Edit(bActive);
		this.oTF_VORNAME_SACHBEARBEITER.set_bEnabled_For_Edit(bActive);
		this.oTF_NAME_SACHBEARBEITER.set_bEnabled_For_Edit(bActive);
		this.oTF_TEL_SACHBEARBEITER.set_bEnabled_For_Edit(bActive);
		this.oTF_EMAIL_SACHBEARBEITER.set_bEnabled_For_Edit(bActive);
		
		if (bActive) {
			if (!this.oFU_PRO_PassendeFuhrenInfos.get_bSonderFall()) {
				this.oSearchAdressImporteur.set_bEnabled_For_Edit(false);
				this.oSearchAdressSpaetereAbnehmer.set_bEnabled_For_Edit(false);
			}
		}
	}
	
	
}
