package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;

public class FUO_Set_And_Valid_Preisfreigabe extends XX_MAP_Set_And_Valid
{

	
	public FUO_Set_And_Valid_Preisfreigabe()
	{
		super();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	private MyE2_MessageVector makeInterneErmittlung(E2_ComponentMAP oMAP, int ActionType) throws myException
	{
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		
		boolean bEK = new FUO_Ermittle_EK_VK(oMAP).is_EK();
		
		String FIELD_PREISABSCHLUSS = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS;
		String FIELD_PREISABSCHLUSS_VON = 	RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS_VON;
		String FIELD_PREISABSCHLUSS_AM = 	RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS_AM;
		String FIELD_PREIS = 				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EINZELPREIS;
		String FIELD_STEUERSATZ = 			RECORD_VPOS_TPA_FUHRE_ORT.FIELD__STEUERSATZ;
		String FIELD_STEUERVERMERK = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EU_STEUER_VERMERK;
		String FIELD_MENGENABSCHLUSS = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE;
		
		String cWert_Preis = 			oMAP.get_cActualDBValueFormated(FIELD_PREIS);
		String cWert_Steuersatz = 		oMAP.get_cActualDBValueFormated(FIELD_STEUERSATZ);
		String cWert_Steuervermerk = 	oMAP.get_cActualDBValueFormated(FIELD_STEUERVERMERK);
		
		boolean bMengeIstAbgeschlossen = oMAP.get_bActualDBValue(FIELD_MENGENABSCHLUSS);

		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			
			if (oMAP.get_bActualDBValue(FIELD_PREISABSCHLUSS))
			{
				//zuerst die validierung, ob der benutzer ueberhaupt das recht hat, freizugeben
				if (bibALL.get_RECORD_USER().is_PREISABSCHLUSS_FUHRE_OK_NO() && bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_NO())
				{
					oMAP.get__DBComp(FIELD_PREISABSCHLUSS).set_cActualMaskValue("N");

					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben nicht die Berechtigung für die Preisfreigabe !")));
					return oMV_Rueck;
				}

				
				FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses oPruefePreis =
						new FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses(cWert_Preis,
																						oMAP.get_bActualDBValue(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__MANUELL_PREIS),
																						oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_VPOS_STD, null, null),
																						oMAP.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_VPOS_KON, null, null),
																						bEK);
				
				String cFehlerMeldung = oPruefePreis.get_cFehlerMeldung();   //alles ok, wenn leer
			
				
				if ((	!bMengeIstAbgeschlossen) || 
						 S.isEmpty(cWert_Preis) || 
						 S.isEmpty(cWert_Steuersatz) || 
						 S.isEmpty(cWert_Steuervermerk) || 
						!bibALL.isNumber(cWert_Preis, true) || 
						!bibALL.isNumber(cWert_Steuersatz, true))
				{
					//die meldung nur bringen, wenn die kompoenten aktiv ist, da sonst der aufruf aus der Mengenfreigabe mit erfolgt
					//und eine evtl. fehlermeldung wegen unvollstaendigem ausfuellen nicht verstanden wuerde
					if (!((MyE2_DB_CheckBox)oMAP.get__DBComp(FIELD_PREISABSCHLUSS)).get_bIsPassivAction())
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für die Preisfreigabe auf der "+(bEK?" Ladeseite ":" Abladeseite ")+ 
																				" müssen folgende Felder einen Wert enthalten: "+(bEK?" Einkaufspreis ":" Verkaufspreis ")+
																				", Steuersatz und Steuervermerk ! Ausserdem MUSS zuerst der Mengenabschluss erfolgen")));
					}
					
					//dann den haken wieder entfernen
					oMAP.get__DBComp(FIELD_PREISABSCHLUSS).set_cActualMaskValue("N");
					
				} else if (S.isFull(cFehlerMeldung)) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cFehlerMeldung)));
					//dann den haken wieder entfernen
					oMAP.get__DBComp(FIELD_PREISABSCHLUSS).set_cActualMaskValue("N");
				} else 	{
					oMAP.get__DBComp(FIELD_PREISABSCHLUSS_AM).set_cActualMaskValue(bibALL.get_cDateNOW());
					oMAP.get__DBComp(FIELD_PREISABSCHLUSS_VON).set_cActualMaskValue(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("--"));
					this.set_Enable_Relevant_MaskComponents(oMAP, false);
				}
				
			}
			else
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, true);
				oMAP.get__DBComp(FIELD_PREISABSCHLUSS_VON).set_cActualMaskValue("");
				oMAP.get__DBComp(FIELD_PREISABSCHLUSS_AM).set_cActualMaskValue("");
				
				//die gegenseitigen Selektoren fuer die steuertexte richtig einstellen
				new FUO_Set_And_Valid_Steuervermerk(oMAP).make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null, null);
			}
			
			
			
			
			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (oMAP.get_bActualDBValue(FIELD_PREISABSCHLUSS))
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, false);
			}
		}
		
		return oMV_Rueck;
	}

	
	private void set_Enable_Relevant_MaskComponents(E2_ComponentMAP oMAP, boolean bEnabled) throws myException
	{
		
		String FIELD_PREIS = 				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EINZELPREIS;
		String FIELD_STEUERSATZ = 			RECORD_VPOS_TPA_FUHRE_ORT.FIELD__STEUERSATZ;
		String FIELD_STEUERVERMERK = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EU_STEUER_VERMERK;
	
		String FIELD_MENGENABSCHLUSS = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE;
		
		String FIELD_OF_oSELSTEUERTEXTE = 	FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT;
		String FIELD_OF_oSEL_ID_TAX =  		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX;

		String FIELD_OF_KontraktField = 	RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_VPOS_KON;
		String FIELD_OF_AngebotField = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_VPOS_STD;
		String FIELD_OF_ManuellPreis = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__MANUELL_PREIS;
		
		String FIELD_FIRMA = 				RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_ADRESSE;
		String FIELD_FIRMA_LADEORT = 		RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_ADRESSE_LAGER;

		oMAP.set_ActiveADHOC(FIELD_PREIS, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_STEUERSATZ, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_STEUERVERMERK, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_OF_oSELSTEUERTEXTE, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_OF_oSEL_ID_TAX, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_OF_KontraktField, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_OF_AngebotField, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_OF_ManuellPreis, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_FIRMA, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_FIRMA_LADEORT, bEnabled, false);
		oMAP.set_ActiveADHOC(FIELD_MENGENABSCHLUSS, bEnabled, false);
		
		//2013-09-30: sortenbezeichungs--suchfeld auch schliessen
		//2014-03-17: aenderung, sortenbezeichnung wird bereits bei mengenfreigabe gesperrt
		//oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE_ORT$ID_ARTIKEL_BEZ, bEnabled, false);
		
	}
	
	
	
}
