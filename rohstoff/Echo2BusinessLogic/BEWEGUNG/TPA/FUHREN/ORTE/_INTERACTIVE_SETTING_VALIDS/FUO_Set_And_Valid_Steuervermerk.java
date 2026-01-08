package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_SELECT_FIELD_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_SelectTaxViaPopup;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

public class FUO_Set_And_Valid_Steuervermerk extends XX_MAP_Set_And_Valid
{

	public FUO_Set_And_Valid_Steuervermerk(E2_ComponentMAP oMAP) throws myException
	{
		super();
	}


	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.make_Checks_and_Settings(oMAP, ActionType, oExecInfo);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.make_Checks_and_Settings(oMAP, ActionType, oExecInfo);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.make_Checks_and_Settings(oMAP, ActionType, oExecInfo);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.make_Checks_and_Settings(oMAP, ActionType, oExecInfo);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	private MyE2_MessageVector make_Checks_and_Settings(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		// felder beschaffen
		MyE2IF__DB_Component 				oSEL_ID_TAX = 			(MyE2IF__DB_Component)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX);
		FU__MASK_SELECT_FIELD_STEUERVERMERK oSEL_SteuerVermerk = 	(FU__MASK_SELECT_FIELD_STEUERVERMERK)oMAP.get__Comp(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT);
		MyE2_DB_TextArea   					oTF_SteuerVermerk =  	(MyE2_DB_TextArea)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EU_STEUER_VERMERK);
		BS_ComboBox_MWST 					oCBB_Steuer = 			(BS_ComboBox_MWST)oMAP.get__Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__STEUERSATZ);
		
		String  							HASHNAME_OF_oSELSTEUERTEXTE = 	FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT;
		String  							HASHNAME_OF_oSEL_ID_TAX =  		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX;
		//---ende felder beschaffen
		
		
		if 		(ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			//unterscheiden, wer klickt
			if (oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(HASHNAME_OF_oSEL_ID_TAX))
			{
				//beim klick auf die ID-steuertexte wird der manuelle alte selektor leer und inaktiv
				if (S.isFull(oSEL_ID_TAX.get_cActualDBValueFormated()))
				{
					oMAP.set_ActiveADHOC(HASHNAME_OF_oSELSTEUERTEXTE, false, false);
					oSEL_SteuerVermerk.set_ActiveValue("");
					this.setzeSteuerUndTextWegen_ID_TAX(oSEL_ID_TAX, oTF_SteuerVermerk, new MyDate(oMAP.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.datum_lade_ablade.fn())), oCBB_Steuer);
				}
				else
				{
					oMAP.set_ActiveADHOC(HASHNAME_OF_oSELSTEUERTEXTE, true, false);
					oTF_SteuerVermerk.setText("");
					oCBB_Steuer.set_cActualMaskValue("");
				}
			}
			
			if (oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(HASHNAME_OF_oSELSTEUERTEXTE))
			{
				//beim klick auf den manuellen selektor wird der neue id-selektor leer und inaktiv
				if (S.isFull(oSEL_SteuerVermerk.get_ActualWert()))
				{
					oMAP.set_ActiveADHOC(HASHNAME_OF_oSEL_ID_TAX, false, true);
//					oSEL_ID_TAX.set_ActiveValue("");
					//2018-07-05: neue varianz, optional neues selectfeld (gruppiertes popup) 
					if (oSEL_ID_TAX instanceof MyE2_DB_SelectField) {
					   ((MyE2_DB_SelectField)oSEL_ID_TAX).set_ActiveValue("");
					} else {
						((FUO_MASK_SelectTaxViaPopup)oSEL_ID_TAX).set_cActualMaskValue("");
					}
					this.setzeSteuerUndTextWegenManuellemVermerk(oSEL_SteuerVermerk, oTF_SteuerVermerk, oCBB_Steuer);
				}
				else
				{
					oMAP.set_ActiveADHOC(HASHNAME_OF_oSEL_ID_TAX, true, false);
					oTF_SteuerVermerk.setText("");
					oCBB_Steuer.set_cActualMaskValue("");
				}
			}
			
			
		}
		else if		(ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (S.isFull(oSEL_ID_TAX.get_cActualDBValueFormated()))
			{
				oMAP.set_ActiveADHOC(HASHNAME_OF_oSELSTEUERTEXTE, false, false);
			}
			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION)
		{
			//hier muss geprueft werden, ob die steuerfindung mit den maskenwerten uebereinstimmt
			//2020-06-23: mwst-validierung
			try {
				MyBigDecimal 	actuellerSteuersatz = 	new MyBigDecimal(oCBB_Steuer.get_cActualDBValueFormated());
				MyLong  		id_tax =		      	new MyLong(oSEL_ID_TAX.get_cActualDBValueFormated());
				MyDate 			leistungsDatum  =		new MyDate(oMAP.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.datum_lade_ablade.fn()));
				if (id_tax.isOK() && leistungsDatum.isOK()) {
					if (actuellerSteuersatz.isOK() ) {
					
						//2020-06-23: steuersetzung
						//20171114: steuerwechseldatum beruecksichtigen
						RECORD_TAX_EXT recTax2 = new RECORD_TAX_EXT(new RECORD_TAX(new MyLong(oSEL_ID_TAX.get_cActualDBValueFormated()).get_lValue()));
						String steuer = recTax2.getSteuerSatzKorrigiert(leistungsDatum);
						MyBigDecimal bdSteuer = new MyBigDecimal(steuer);
						if (!bdSteuer.getUfRounded(2).equals(actuellerSteuersatz.getUfRounded(2))) {
							oMV._addAlarm(S.ms("Achtung! Die Steuersetzung ist falsch! ")
												.t("Zum gegebenen Leistungsdatum ist die Steuer ")
												.ut(actuellerSteuersatz.getFRounded(2))
												.t(" - Nach der Steuerauswahl müsste dort aber ")
												.ut(bdSteuer.getFRounded(2))
												.t(" stehen !")
												);
						}
						
					} else {
						oMV._addAlarm(S.ms("Achtung! Steuersatz ist nicht korrekt!"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				oMV._addAlarm("Error reading Tax-value ! <3506726e-b56a-11ea-b3de-0242ac130004>");
			}

			
		}
		
		
		return oMV;
	}
	
	
	
	/*
	 * wert in der (alten) auswahl setzen
	 */
	private void setzeSteuerUndTextWegenManuellemVermerk(	FU__MASK_SELECT_FIELD_STEUERVERMERK oSelectFieldSteuervermerk, 
															MyE2_DB_TextArea  					TF_SteuerVermerk,
															BS_ComboBox_MWST 					CBB_Steuer) throws myException
	{
		TF_SteuerVermerk.setText(oSelectFieldSteuervermerk.get_ActualWert());
		
		String cHashKey = 	oSelectFieldSteuervermerk.get_Auswahlliste()[oSelectFieldSteuervermerk.getSelectedIndex()][0];
		
		BS_STEUERVERMERK  bsVermerk = oSelectFieldSteuervermerk.get_hmVermerke().get(cHashKey);
		
		if (bsVermerk.getcSteuersatzFormated()!=null)
		{
			CBB_Steuer.set_cActualMaskValue(S.NN(bsVermerk.getcSteuersatzFormated()));
		}

	}
	
	
	/*
	 * wert in der (neuen) auswahl setzen
	 */
	private void setzeSteuerUndTextWegen_ID_TAX(	MyE2IF__DB_Component 				oSEL_ID_TAX, 
													MyE2_DB_TextArea  					TF_SteuerVermerk,
													MyDate  	                        leistungsdatum,
													BS_ComboBox_MWST 					CBB_Steuer) throws myException
	{
		RECORD_TAX  recTax = new RECORD_TAX(new MyLong(oSEL_ID_TAX.get_cActualDBValueFormated()).get_lValue());
		
		//20171114: steuerwechseldatum beruecksichtigen
		RECORD_TAX_EXT recTax2 = new RECORD_TAX_EXT(recTax);
		
		String steuer = recTax2.getSteuerSatzKorrigiert(leistungsdatum);

		TF_SteuerVermerk.setText(recTax.get_STEUERVERMERK_cUF_NN(""));
		CBB_Steuer.set_cActualMaskValue(steuer);
	}

	
}
