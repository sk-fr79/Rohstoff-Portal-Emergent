package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.SET_AND_VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

public class BSRG_Set_And_Valid_Steuervermerk extends XX_MAP_Set_And_Valid
{

	
	//valdierer setzt die zusammenhaenge der Felder ID_TAX und STEUERSATZ/STEUERTEXT:
	// wenn id_tax aktiviert wird, dann wird die manuelle bedienung der anderen beiden Felder inaktiv und umgekehrt
	public BSRG_Set_And_Valid_Steuervermerk(E2_ComponentMAP oMAP) throws myException
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
		MyE2_DB_SelectField 				    oSEL_ID_TAX = 			(MyE2_DB_SelectField)oMAP.get__Comp(_DB.VPOS_RG$ID_TAX);
		BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK  oSEL_SteuerVermerk = 	(BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK)oMAP.get__Comp(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT);
		MyE2_DB_TextArea   						oTF_SteuerVermerk =  	(MyE2_DB_TextArea)oMAP.get__Comp(_DB.VPOS_RG$EU_STEUER_VERMERK);
		BS_ComboBox_MWST 						oCBB_Steuer = 			(BS_ComboBox_MWST)oMAP.get__Comp(_DB.VPOS_RG$STEUERSATZ);
		
		String  								HASHNAME_OF_oSELSTEUERTEXTE = 	_DB.VPOS_RG$EU_STEUER_VERMERK;
		String  								HASHNAME_OF_DD_STEUERSATZ = 	_DB.VPOS_RG$STEUERSATZ;
		String  								HASHNAME_OF_oSEL_ID_TAX =  		_DB.VPOS_RG$ID_TAX;
		
		
		MyDate   		leistungsdatum = new MyDate(oMAP.get_cActualDBValueFormated(VPOS_RG.ausfuehrungsdatum.fn()));
		
		
		if 		(ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			if (leistungsdatum.isNotOK()) {
				oMV._addAlarm(S.ms("Bitte zuerst das Leistungsdatum erfassen!"));
				oTF_SteuerVermerk.setText("");
				oCBB_Steuer.set_cActualMaskValue("");
			} else {
				//unterscheiden, wer klickt
				if (oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(HASHNAME_OF_oSEL_ID_TAX))
				{
					//beim klick auf die ID-steuertexte wird der manuelle alte selektor leer und inaktiv
					if (S.isFull(oSEL_ID_TAX.get_ActualWert()))
					{
						oMAP.set_ActiveADHOC(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, false, false);
						oMAP.set_ActiveADHOC(HASHNAME_OF_DD_STEUERSATZ, false, false);
						oSEL_SteuerVermerk.set_ActiveValue("");
						this.setzeSteuerUndTextWegen_ID_TAX(oSEL_ID_TAX, oTF_SteuerVermerk, leistungsdatum, oCBB_Steuer);
					}
					else
					{
						oMAP.set_ActiveADHOC(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, true, false);
						oMAP.set_ActiveADHOC(HASHNAME_OF_DD_STEUERSATZ, true, false);
						oTF_SteuerVermerk.setText("");
						oCBB_Steuer.set_cActualMaskValue("");
					}
				}
				
				if (oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT))
				{
					//beim klick auf den manuellen selektor wird der neue id-selektor leer und inaktiv
					if (S.isFull(oSEL_SteuerVermerk.get_ActualWert()))
					{
						oMAP.set_ActiveADHOC(HASHNAME_OF_oSEL_ID_TAX, false, true);
						oSEL_ID_TAX.set_ActiveValue("");
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
			
		}
		else if		(ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (S.isFull(oSEL_ID_TAX.get_ActualWert()))
			{
				oMAP.set_ActiveADHOC(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, false, false);
				oMAP.set_ActiveADHOC(HASHNAME_OF_DD_STEUERSATZ, false, false);
			} 			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION)
		{
			try {
				MyBigDecimal 	actuellerSteuersatz = new MyBigDecimal(oMAP.get_cActualDBValueFormated(VPOS_RG.steuersatz.fn()));
				MyLong  		id_tax = new MyLong(oMAP.get_cActualDBValueFormated(VPOS_RG.id_tax.fn()));

				if (id_tax.isOK() && leistungsdatum.isOK()) {
					if (actuellerSteuersatz.isOK()) {
					
						//2020-06-23: steuersetzung
						//20171114: steuerwechseldatum beruecksichtigen
						RECORD_TAX_EXT recTax2 = new RECORD_TAX_EXT(new RECORD_TAX(new MyLong(oSEL_ID_TAX.get_cActualDBValueFormated()).get_lValue()));
						String steuer = recTax2.getSteuerSatzKorrigiert(leistungsdatum);
						MyBigDecimal bdSteuer = new MyBigDecimal(steuer);
						if (!bdSteuer.getUfRounded(2).equals(actuellerSteuersatz.getUfRounded(2))) {
							oMV._addAlarm(S.ms("Achtung! Die Steuersetzung ist falsch! Zum gegebenen Leistungsdatum ist die Steuer ")
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
				oMV._addAlarm("Error reading Tax-value ! <06360b02-b56a-11ea-b3de-0242ac130004>");
			}
			
		}
		
		
		return oMV;
	}
	
	
	
	/*
	 * wert in der (alten) auswahl setzen
	 */
	private void setzeSteuerUndTextWegenManuellemVermerk(	BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK  oSelectFieldSteuervermerk, 
															MyE2_DB_TextArea  					TF_SteuerVermerk,
															BS_ComboBox_MWST 					CBB_Steuer) throws myException
	{
		TF_SteuerVermerk.setText(oSelectFieldSteuervermerk.get_ActualWert());
		
		String cHashKey = 	oSelectFieldSteuervermerk.get_Auswahlliste()[oSelectFieldSteuervermerk.getSelectedIndex()][0];
		
		BS_STEUERVERMERK  bsVermerk = oSelectFieldSteuervermerk.get_hmSteuerVermerk().get(cHashKey);
		
		if (bsVermerk.getcSteuersatzFormated()!=null)
		{
			CBB_Steuer.set_cActualMaskValue(S.NN(bsVermerk.getcSteuersatzFormated()));
		}

	}
	
	
//	/*
//	 * wert in der (neuen) auswahl setzen
//	 */
//	private void setzeSteuerUndTextWegen_ID_TAX(	MyE2_DB_SelectField 				oSEL_ID_TAX, 
//													MyE2_DB_TextArea  					TF_SteuerVermerk,
//													BS_ComboBox_MWST 					CBB_Steuer) throws myException
//	{
//
//		
//		RECORD_TAX  recTax = new RECORD_TAX(new MyLong(oSEL_ID_TAX.get_ActualWert()).get_lValue());
//		TF_SteuerVermerk.setText(recTax.get_STEUERVERMERK_cUF_NN(""));
//		CBB_Steuer.set_cActualMaskValue(recTax.get_STEUERSATZ_cF_NN(""));
//	}

	private void setzeSteuerUndTextWegen_ID_TAX(	MyE2_DB_SelectField 				oSEL_ID_TAX, 
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
