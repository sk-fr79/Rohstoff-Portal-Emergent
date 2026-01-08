package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;


import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER;
import rohstoff.utils.My_MWSTSaetze;

/*
 * maske definieren
 */
public class BSRG_P_MASK_MAP_SETTING_AGENT extends XX_MAP_SettingAgent
{

	private boolean bAusFreiePositionen = false;
	private boolean bNeueingabe  		= false;
	

	public BSRG_P_MASK_MAP_SETTING_AGENT(boolean AusFreiePositionen) 
	{
		super();
		this.bAusFreiePositionen = AusFreiePositionen;
	}



	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
		HashMap<String,MyE2IF__Component> hmReal = oMap.get_REAL_ComponentHashMap();
		
		
		//zuerst den startzustand herstellen
		oMap.set_DisabledFromInteractiv_ALL(false);
		
		//zuerst unterscheiden, ob neu oder edit
		this.bNeueingabe = (oMap.get_oInternalSQLResultMAP() == null);
		
		String cLIST_To_Disable = "";
		cLIST_To_Disable= ":POSITIONSNUMMER:EINHEITKURZ:EINHEIT_PREIS_KURZ:MENGENDIVISOR:" +
								"GESAMTPREIS:ANR1:ANR2:ID_ARTIKEL:ID_VKOPF_RG:ID_VPOS_RG:" +
								"ANZAHL_ABZUG:EINZELPREIS_ABZUG:EINZELPREIS_RESULT:GESAMTPREIS_ABZUG:ID_VPOS_TPA_FUHRE_ZUGEORD:";
		
		oMap.set_DisabledFromInteractive(cLIST_To_Disable,":",true);

		
		String cID_VKOPF_RG = null;
		
		/*
		 * der positionstyp-selektor ist immer disabled in Rechnungen
		 */
		((MyE2_DB_SelectField)hmReal.get("POSITION_TYP")).EXT().set_bDisabledFromInteractive(true);		

		// fuhre und Kontrakt-position kann nur durch das fuhrenbuchungsmodul definiert werden
		((MyE2IF__Component)hmReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT().set_bDisabledFromInteractive(true);
		((MyE2IF__Component)hmReal.get("ID_VPOS_KON_ZUGEORD")).EXT().set_bDisabledFromInteractive(true);
		
		((MyE2IF__DB_Component)hmReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);

		
		// bessere lesbarkeit
		boolean bNeuAusStandardmodul 	= this.bNeueingabe && !this.bAusFreiePositionen;
		boolean bNeuAusFreiePositionen	= this.bNeueingabe && this.bAusFreiePositionen;

		
		
		/*
		 * neueingabe aus dem kopf-positions-modul
		 */
		if 		(bNeuAusStandardmodul)
		{
			cID_VKOPF_RG = ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VKOPF_RG")).get_cRestictionValue_IN_DB_FORMAT();
			String cCount = bibDB.EinzelAbfrage("SELECT COUNT(*)+1 FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+cID_VKOPF_RG);
			((MyE2IF__DB_Component)hmReal.get("POSITIONSNUMMER")).set_cActualMaskValue(cCount);
			
			// erlauben
			((MyE2IF__Component)hmReal.get("ANZAHL")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("EINZELPREIS")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("STEUERSATZ")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT().set_bDisabledFromInteractive(false);

			//verbieten
			((MyE2IF__Component)hmReal.get("LAGER_VORZEICHEN")).EXT().set_bDisabledFromInteractive(true);
			((MyE2IF__Component)hmReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT().set_bDisabledFromInteractive(true);
			((MyE2IF__Component)hmReal.get("ID_ADRESSE")).EXT().set_bDisabledFromInteractive(true);

			//null oder nicht null
			((MyE2IF__DB_Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
			((MyE2IF__DB_Component)hmReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);
			((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);


			/*
			 * bei neueingabe aus der Standard-Maske, muss rechnung/Gutschrift vordefiniert sein
			 */
			RECORD_VKOPF_RG  oKopfsatz = new RECORD_VKOPF_RG(cID_VKOPF_RG);
			if (oKopfsatz.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG))
			{
				((MyE2_DB_SelectField)hmReal.get("LAGER_VORZEICHEN")).set_ActiveValue("-1");
			}
			else
			{
				((MyE2_DB_SelectField)hmReal.get("LAGER_VORZEICHEN")).set_ActiveValue("1");
			}
			
			
			// bei neueingabe die zahlungsbedingungen der anderen postitionen uebernehmen und felder deaktivieren
			if (oKopfsatz.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,false).get_vKeyValues().size()>0)   // dann gibt es positionen
			{
				//der erste datensatz wird als referenz der einstellung rangezogen
				RECORD_VPOS_RG recPOS = oKopfsatz.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,false).get(0);
				((MyE2_DB_SelectField)hmReal.get("ID_ZAHLUNGSBEDINGUNGEN")).set_ActiveValue_OR_FirstValue(recPOS.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(""));
				((MyE2_DB_TextField)hmReal.get("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recPOS.get_ZAHLUNGSBEDINGUNGEN_cF_NN(""));
				((MyE2_DB_TextField)hmReal.get("ZAHLTAGE")).set_cActualMaskValue(recPOS.get_ZAHLTAGE_cF_NN(""));
				((MyE2_DB_TextField)hmReal.get("FIXMONAT")).set_cActualMaskValue(recPOS.get_FIXMONAT_cF_NN(""));
				((MyE2_DB_TextField)hmReal.get("FIXTAG")).set_cActualMaskValue(recPOS.get_FIXTAG_cF_NN(""));
				((MyE2_DB_TextField)hmReal.get("SKONTO_PROZENT")).set_cActualMaskValue(recPOS.get_SKONTO_PROZENT_cF_NN(""));
			}
			
			//Steuersaetze einstellen
			((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ")).set_Varianten(new My_MWSTSaetze(oKopfsatz.get_ID_ADRESSE_cUF(),null).get_MWST_DropDownArray_AdressMWST(true),"-",null, false);
		}
		else if (bNeuAusFreiePositionen)
		{
			((MyE2IF__DB_Component)hmReal.get("POSITIONSNUMMER")).set_cActualMaskValue("0");
			
			// das lagervorzeichen muss eingegeben werden
			((MyE2IF__Component)hmReal.get("ANZAHL")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("EINZELPREIS")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("STEUERSATZ")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("LAGER_VORZEICHEN")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("ID_ADRESSE")).EXT().set_bDisabledFromInteractive(false);
			((MyE2IF__Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT().set_bDisabledFromInteractive(false);

			((MyE2IF__Component)hmReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT().set_bDisabledFromInteractive(true);
			
			((MyE2IF__DB_Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
			((MyE2IF__DB_Component)hmReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);
			((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);

		}
		else 		//aenderungsmodul
		{
			boolean bHatFuhre = 	!(bibALL.isEmpty(oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ZUGEORD")));

			RECORD_VPOS_RG recVPOS = new RECORD_VPOS_RG(oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_RG"));
			
			boolean bAusFreiePos = recVPOS.get_ID_VKOPF_RG_cUF_NN("--").equals("--");
			 
			//voreinstellung der null-erlaubnis
			((MyE2IF__DB_Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
			((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
			
			//das lagervorzeichen kann nur bei neueingabe veraendert werden
			((MyE2IF__Component)hmReal.get("LAGER_VORZEICHEN")).EXT().set_bDisabledFromInteractive(true);

			/*
			 * wird aus dem kopfmodul editiert, dann darf keine Adresse vorhanden sein,
			 * sonst MUSS eine vorhanden sein
			 */
			if (bAusFreiePos)
			{
				if (bHatFuhre)             //falls fuhre vorhanden ist, dann kann die adresse nicht veraendert werden, diese kommt aus der fuhre
				{
					((MyE2IF__Component)hmReal.get("ID_ADRESSE")).EXT().set_bDisabledFromInteractive(true);
					((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
				}
				else
				{
					((MyE2IF__Component)hmReal.get("ID_ADRESSE")).EXT().set_bDisabledFromInteractive(false);
					((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(false);
				}
			}
			else
			{
				//aus dem kopf/pos-modul kann die adresse nicht veraendert werden, sie steht fest, ebenfalls nicht veraenderbar, wenn es einen kontrakt gibt
				((MyE2IF__Component)hmReal.get("ID_ADRESSE")).EXT().set_bDisabledFromInteractive(true);
				((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);
			}

			
			if (bHatFuhre)       
			{
				((MyE2IF__Component)hmReal.get("ID_ADRESSE")).EXT().set_bDisabledFromInteractive(true);
				((MyE2IF__DB_Component)hmReal.get("ID_ADRESSE")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_ForInteractiveModules(true);
				((MyE2IF__Component)hmReal.get("ID_ARTIKEL_BEZ")).EXT().set_bDisabledFromInteractive(true);
				
				//anzahl kann nur in der Fuhre geaendert werden 
				((MyE2IF__Component)hmReal.get("ANZAHL")).EXT().set_bDisabledFromInteractive(true);
				
				//preis kann nur geaendert werden, wenn der mandant das erlaubt
				if (bibALL.get_RECORD_MANDANT().is_ALLOW_EDIT_PRICE_IN_FUHREN_RG_NO())
				{
					((MyE2IF__Component)hmReal.get("EINZELPREIS")).EXT().set_bDisabledFromInteractive(true);
				}

				//abzuege koennen nur geaendert werden, wenn der mandant das erlaubt
				if (bibALL.get_RECORD_MANDANT().is_ALLOW_EDIT_ABZUG_IN_FUHREN_RG_NO())
				{
					((MyE2IF__Component)hmReal.get(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS)).EXT().set_bDisabledFromInteractive(true);
				}

			
			}
			
			
			//jetzt pruefen, ob es eine position aus einer storno-kette ist, wenn ja, dann die maske zumachen
			boolean bPositionIstAusStornoKette = S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN(""));
			
			if (bPositionIstAusStornoKette)
			{
				oMap.set_DisabledFromInteractiv_ALL(true);
			}
			
		}
		
		//den steuervermerk-selektor immer neutral schalten
		((BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK)hmReal.get(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT)).set_Neutral();
		
		
		new BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER("RG",oMap);

	}
	
	
	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}
	
}
