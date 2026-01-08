package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;

public class FU_Set_And_Valid_Preisfreigabe extends XX_MAP_Set_And_Valid
{

	private String DEF_QUELLE_ZIEL = null;
	
	private String FIELD_PREISABSCHLUSS =     null;
	private String FIELD_PREISABSCHLUSS_VON = null;
	private String FIELD_PREISABSCHLUSS_AM =  null;
	
	private String FIELD_PREIS = null;
	private String FIELD_STEUERSATZ = null;
	private String FIELD_STEUERVERMERK = null;
	
	private String FIELD_MENGENABSCHLUSS = null;
	
	
	private String FIELD_OF_oSELSTEUERTEXTE = 	null;
	private String FIELD_OF_oSEL_ID_TAX =  		null;

	private String FIELD_OF_KontraktField = null;
	private String FIELD_OF_AngebotField = null;
	private String FIELD_OF_ManuellPreis = null;
	
	private String FIELD_FIRMA = null;
	private String FIELD_FIRMA_LADEORT	= null;
	
	
	private String FIELD_ID_ARTIKEL_BEZ = null;
	
	
	public FU_Set_And_Valid_Preisfreigabe(String dEF_QUELLE_ZIEL)
	{
		super();
		DEF_QUELLE_ZIEL = dEF_QUELLE_ZIEL;
		
		boolean bEK = (this.DEF_QUELLE_ZIEL.equals("EK"));
		
		this.FIELD_PREISABSCHLUSS = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS:			RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS;
		this.FIELD_PREISABSCHLUSS_VON = 	bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS_VON:		RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS_VON;
		this.FIELD_PREISABSCHLUSS_AM = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS_AM:		RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS_AM;
		
		this.FIELD_PREIS = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__EINZELPREIS_EK:			RECORD_VPOS_TPA_FUHRE.FIELD__EINZELPREIS_VK;
		this.FIELD_STEUERSATZ = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__STEUERSATZ_EK:				RECORD_VPOS_TPA_FUHRE.FIELD__STEUERSATZ_VK;
		this.FIELD_STEUERVERMERK = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__EU_STEUER_VERMERK_EK:		RECORD_VPOS_TPA_FUHRE.FIELD__EU_STEUER_VERMERK_VK;
	
		this.FIELD_MENGENABSCHLUSS = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE:		RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE;
		
		this.FIELD_OF_oSELSTEUERTEXTE = 	bEK?FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK:	FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK;
		this.FIELD_OF_oSEL_ID_TAX =  		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ID_TAX_EK:					RECORD_VPOS_TPA_FUHRE.FIELD__ID_TAX_VK;

		this.FIELD_OF_KontraktField = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_KON_EK: 			RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_KON_VK;
		this.FIELD_OF_AngebotField = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_STD_EK: 			RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_STD_VK;
		this.FIELD_OF_ManuellPreis = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__MANUELL_PREIS_EK: 			RECORD_VPOS_TPA_FUHRE.FIELD__MANUELL_PREIS_VK;
		
		this.FIELD_FIRMA = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_START: 			RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_ZIEL;
		this.FIELD_FIRMA_LADEORT = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_LAGER_START: 	RECORD_VPOS_TPA_FUHRE.FIELD__ID_ADRESSE_LAGER_ZIEL;
	
		this.FIELD_ID_ARTIKEL_BEZ = 		bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_EK: 		RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL_BEZ_VK;
		
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
		
		String cWert_Preis = 			oMAP.get_cActualDBValueFormated(this.FIELD_PREIS);
		String cWert_Steuersatz = 		oMAP.get_cActualDBValueFormated(this.FIELD_STEUERSATZ);
		String cWert_Steuervermerk = 	oMAP.get_cActualDBValueFormated(this.FIELD_STEUERVERMERK);
		
		boolean bMengeIstAbgeschlossen = oMAP.get_bActualDBValue(this.FIELD_MENGENABSCHLUSS);

		boolean bEK = (this.DEF_QUELLE_ZIEL.equals("EK"));
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			
			if (oMAP.get_bActualDBValue(this.FIELD_PREISABSCHLUSS))
			{
				//zuerst die validierung, ob der benutzer ueberhaupt das recht hat, freizugeben
				if (bibALL.get_RECORD_USER().is_PREISABSCHLUSS_FUHRE_OK_NO() && bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_NO())
				{
					oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS).set_cActualMaskValue("N");

					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben nicht die Berechtigung für die Preisfreigabe !")));
					return oMV_Rueck;
				}

				FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses oPruefePreis =
						new FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses(cWert_Preis,
																						oMAP.get_bActualDBValue(this.FIELD_OF_ManuellPreis),
																						oMAP.get_LActualDBValue(this.FIELD_OF_AngebotField, null, null),
																						oMAP.get_LActualDBValue(this.FIELD_OF_KontraktField, null, null),
																						bEK);
				String cFehlerMeldung = oPruefePreis.get_cFehlerMeldung();   //alles ok, wenn leer
				
				
				if ((!bMengeIstAbgeschlossen) || S.isEmpty(cWert_Preis) || S.isEmpty(cWert_Steuersatz) || S.isEmpty(cWert_Steuervermerk) || !bibALL.isNumber(cWert_Preis, true) || !bibALL.isNumber(cWert_Steuersatz, true))
				{
					//die meldung nur bringen, wenn die kompoenten aktiv ist, da sonst der aufruf aus der Mengenfreigabe mit erfolgt
					//und eine evtl. fehlermeldung wegen unvollstaendigem ausfuellen nicht verstanden wuerde
					if (!((MyE2_DB_CheckBox)oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS)).get_bIsPassivAction())
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für die Preisfreigabe auf der "+(this.DEF_QUELLE_ZIEL.equals("EK")?" Ladeseite ":" Abladeseite ")+ 
																				" müssen folgende Felder einen Wert enthalten: "+(this.DEF_QUELLE_ZIEL.equals("EK")?" Einkaufspreis ":" Verkaufspreis ")+
																				", Steuersatz und Steuervermerk ! Ausserdem MUSS zuerst der Mengenabschluss erfolgen")));
					}
					
					//dann den haken wieder entfernen
					oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS).set_cActualMaskValue("N");
					
				} else if (S.isFull(cFehlerMeldung)) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cFehlerMeldung)));
					//dann den haken wieder entfernen
					oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS).set_cActualMaskValue("N");
				}
				else {
					oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS_AM).set_cActualMaskValue(bibALL.get_cDateNOW());
					oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS_VON).set_cActualMaskValue(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("--"));
					this.set_Enable_Relevant_MaskComponents(oMAP, false);
				}
				
			}
			else
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, true);
				oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS_VON).set_cActualMaskValue("");
				oMAP.get__DBComp(this.FIELD_PREISABSCHLUSS_AM).set_cActualMaskValue("");
				
				//die gegenseitigen Selektoren fuer die steuertexte richtig einstellen
				new FU_Set_And_Valid_Steuervermerk(oMAP, this.DEF_QUELLE_ZIEL).make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
			}
			
			
			
			
			
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (oMAP.get_bActualDBValue(this.FIELD_PREISABSCHLUSS))
			{
				this.set_Enable_Relevant_MaskComponents(oMAP, false);
			}
		}
		
		return oMV_Rueck;
	}

	
	private void set_Enable_Relevant_MaskComponents(E2_ComponentMAP oMAP, boolean bEnabled) throws myException
	{
		oMAP.set_ActiveADHOC(this.FIELD_PREIS, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_STEUERSATZ, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_STEUERVERMERK, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_OF_oSELSTEUERTEXTE, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_OF_oSEL_ID_TAX, bEnabled, false);
		
		oMAP.set_ActiveADHOC(this.FIELD_OF_KontraktField, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_OF_AngebotField, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_OF_ManuellPreis, bEnabled, false);
		
		oMAP.set_ActiveADHOC(this.FIELD_FIRMA, bEnabled, false);
		oMAP.set_ActiveADHOC(this.FIELD_FIRMA_LADEORT, bEnabled, false);
		
		oMAP.set_ActiveADHOC(this.FIELD_MENGENABSCHLUSS, bEnabled, false);
		
		//2013-09-26: auch die felder fuer die sortensuche werden nach preisfreigabe gesperrt
		//            und zwar unabhaengig immer auf beiden seiten, da diese felder die steuerdefinition leermachen (beidseitig)
		//            und damit 
		
		//2014-03-17: es werden bereits bei der mengefreigabe die Sortenfelder gesperrt
//		oMAP.set_ActiveADHOC(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL, bEnabled, false);
//		
//		oMAP.set_ActiveADHOC(this.FIELD_ID_ARTIKEL_BEZ, bEnabled, false);
		
	}
	
	
	
}
