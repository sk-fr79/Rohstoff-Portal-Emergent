package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.math.BigDecimal;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_SELECT_FIELD_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___ERMITTLE_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___ERMITTLE_STEUERVERMERK_UND_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_FUO_handlingTaxdefOhneAbrechnung;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG._HD_ActionAgent_FindTaxDef__FUHREN_MASKE;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG._HD_ActionAgent_FindTaxDef__FUHREN_ORT_MASKE;

public class FUO_MASK_BUTTON_HOLE_PREIS_STEUER_ZAUBERSTAB extends MyE2_Button
{

	public FUO_MASK_BUTTON_HOLE_PREIS_STEUER_ZAUBERSTAB()
	{
		super(E2_ResourceIcon.get_RI("wizard.png"));
		this.setToolTipText(new MyE2_String("Preise und Sorten-Ladevorgang manuell ausführen ").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		
		this.add_GlobalValidator(new ownValidatorPreisPruefung());
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FUO_MASK_ComponentMAP oMap = (FUO_MASK_ComponentMAP)FUO_MASK_BUTTON_HOLE_PREIS_STEUER_ZAUBERSTAB.this.EXT().get_oComponentMAP();
			
			boolean bEK = oMap.get_cEK_VK().equals("EK");
			
			
			
			/*
			 * @param cid_adresse_uf
			 * @param cid_artikel_uf
			 * @param cid_vpos_kon_uf
			 * @param cid_vpos_std_uf
			 * @param cpreis_manuell_y_n
			 * @param cDatum_von      formate: 31.12.2010
			 * @param cDatum_bis
			 * @param bdEINZELPREIS
			 * @param bd_MWST  (wird im moment noch nicht veraendert, ausser bei lagerpositionen)
			 * @param oMV
			 * @param bEK
			 * @param oConn
			 */
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			Long 		lID_ADRESSE_UF =   	oMap.get_LActualDBValue("ID_ADRESSE",null,null);
			Long 		lID_ADRESSE_LAGER_UF =oMap.get_LActualDBValue("ID_ADRESSE_LAGER",null,null);
			Long        lID_ARTIKEL = 		null;
			Long 		lID_ARTIKELBEZ_UF =  oMap.get_LActualDBValue("ID_ARTIKEL_BEZ",null,null);
			if (lID_ARTIKELBEZ_UF != null)
			{
				lID_ARTIKEL = new RECORD_ARTIKEL_BEZ(""+lID_ARTIKELBEZ_UF).get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_lValue(null);
			}
			Long	 	lID_ID_VPOS_KON =	oMap.get_LActualDBValue("ID_VPOS_KON",null,null);
			Long 		lID_ID_VPOS_STD =	oMap.get_LActualDBValue("ID_VPOS_STD",null,null);
			String 		cPreisManuell = 		((MyE2_DB_CheckBox)oMap.get__Comp("MANUELL_PREIS")).isSelected()?"Y":"N";
			String 		cDatumVon =			oMap.get_ActualMyDate("DATUM_LADE_ABLADE", true, true, null)==null?"":oMap.get_ActualMyDate("DATUM_LADE_ABLADE", true, true, null).get_cUmwandlungsergebnis();
			String 		cDatumBis =			cDatumVon;
			BigDecimal  bdEINZELPREIS = 		oMap.get_bdActualDBValue("EINZELPREIS", null, null);

			FU___ERMITTLE_PREIS  oErmittler = 
				new FU___ERMITTLE_PREIS(
							lID_ADRESSE_UF==null?"":""+lID_ADRESSE_UF.longValue(),
							lID_ARTIKEL==null?"":""+lID_ARTIKEL.longValue(),
							lID_ID_VPOS_KON==null?"":""+lID_ID_VPOS_KON.longValue(),	
							lID_ID_VPOS_STD==null?"":""+lID_ID_VPOS_STD.longValue(),	
							cPreisManuell,
							bdEINZELPREIS,
							cDatumVon,
							cDatumBis,
							oMV,
							bEK,
							null);


			// -----------------------------------------------------
			//EK-SEITE fuellen
			String ID_VPOS_STD_EK = oErmittler.get_ID_VPOS_STD_Rueck_UF();
			BigDecimal EINZELPREIS_EK = oErmittler.get_bdNeuerPreis_Rueck();
			String cPREIS_MANUELL_YN_EK = S.NN(oErmittler.get_cPreisManuell_Y_N_Rueck());
			
			if (ID_VPOS_STD_EK==null)
			{
				((MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_STD")).prepare_ContentForNew(false);
			}
			else
			{
				((MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_STD")).set_cActualMaskValue(ID_VPOS_STD_EK, true, false, false);
			}
			((MyE2_DB_TextField)oMap.get__Comp("EINZELPREIS")).setText(EINZELPREIS_EK==null?"":MyNumberFormater.formatDez(EINZELPREIS_EK, 2, true));
			((MyE2_DB_CheckBox)oMap.get__Comp("MANUELL_PREIS")).setSelected(cPREIS_MANUELL_YN_EK.equals("Y"));
			// ---------------------------------- PREIS FERTIG --------------
			
			

			//jetzt die EK-VK-Seite rausziehen
			//zuerst die fuhre beschaffen
			RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE( ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VPOS_TPA_FUHRE")).get_cRestictionValue_IN_DB_FORMAT());
			
			Long lID_ADRESSE_LAGER__START = null;
			Long lID_ADRESSE_LAGER__ZIEL =  null;
			
			Long lID_ARTIKEL_EK = null;
			Long lID_ARTIKEL_VK = null;
			
			String cDatumAufladen = null;
			String cDatumAbladen = null;
			
			if (bEK)
			{
				lID_ADRESSE_LAGER__START= lID_ADRESSE_LAGER_UF;
				lID_ADRESSE_LAGER__ZIEL=  recFuhre.get_ID_ADRESSE_LAGER_ZIEL_lValue(new Long(-1));
				lID_ARTIKEL_EK = lID_ARTIKEL;
				if (recFuhre.get_ID_ARTIKEL_BEZ_VK_lValue(null)!=null)
				{
					lID_ARTIKEL_VK = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_lValue(null);
				}
				cDatumAufladen = oMap.get_cActualDBValueFormated("DATUM_LADE_ABLADE");
				cDatumAbladen  = recFuhre.get_DATUM_ABLADEN_cF_NN("");
			}
			else
			{
				lID_ADRESSE_LAGER__START= recFuhre.get_ID_ADRESSE_LAGER_START_lValue(new Long(-1));
				lID_ADRESSE_LAGER__ZIEL=  lID_ADRESSE_LAGER_UF;
				lID_ARTIKEL_VK = lID_ARTIKEL;
				if (recFuhre.get_ID_ARTIKEL_BEZ_EK_lValue(null)!=null)
				{
					lID_ARTIKEL_EK = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_lValue(null);
				}
				cDatumAufladen = recFuhre.get_DATUM_ABLADEN_cF_NN(""); 
				cDatumAbladen  = oMap.get_cActualDBValueFormated("DATUM_LADE_ABLADE");
			}
			
			
			
			//2013-07-31: Steuerfindung NEU
			//2013-04-29: hier entscheiden, ob mit der alten oder neuen methode die Steuer ermittelt wird
			boolean bSteuerViaHandelsdef = __RECORD_MANDANT_ZUSATZ.IS__Value("STEUERERMITTLUNG_MIT_HANDELSDEF", "N", "N");
			
			
			if (bSteuerViaHandelsdef) {
				
				//hier die neue pruefung auf fuhren ohne abrechnung
				PAIR<Boolean, MyE2_MessageVector> check = new __FU_FUO_handlingTaxdefOhneAbrechnung().setTaxAndTaxMessageForFuhrenOhneAbrechnung(oMap);
				if (check.getVal1()== null) {
					bibMSG.MV()._add(check.getVal2());
				} else if (check.getVal1()) {	
					bibMSG.MV()._add(check.getVal2());
				} else {
					new _HD_ActionAgent_FindTaxDef__FUHREN_ORT_MASKE().executeAgentCode(execInfo);				
				}

//				if (bibMSG.get_bIsOK()) {   //20181023: keine handelsdef ziehen, wenn ein fehler aufgelaufen ist
//					new _HD_ActionAgent_FindTaxDef__FUHREN_ORT_MASKE().executeAgentCode(execInfo);
//				}
				
			} else {

				// steuervermerk EK und VK
				if (lID_ADRESSE_LAGER__START!=null && lID_ADRESSE_LAGER__ZIEL!=null && lID_ARTIKEL_EK!=null && lID_ARTIKEL_VK!=null)
				{
					FU___ERMITTLE_STEUERVERMERK_UND_MWST oErmittleSteuer = 
						  new FU___ERMITTLE_STEUERVERMERK_UND_MWST(
								  new RECORD_ADRESSE(""+lID_ADRESSE_LAGER__START),
								  new RECORD_ADRESSE(""+lID_ADRESSE_LAGER__ZIEL),
								  new RECORD_ARTIKEL(""+lID_ARTIKEL_EK),
								  new RECORD_ARTIKEL(""+lID_ARTIKEL_VK),
								  cDatumAufladen,
								  cDatumAbladen,
								  (bEK?bdEINZELPREIS:null),
								  (bEK?null:bdEINZELPREIS));
	
					String cWarnText = "";
					if (bEK)
					{
						if (oErmittleSteuer.get_bSteuerSatzErmittelt_EK() && S.isEmpty(((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ")).get_oTextField().getText()))
						{
							((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ")).get_oTextField().setText(MyNumberFormater.formatDez(oErmittleSteuer.get_bdRUECK_MWST_EK(),2,true));
							cWarnText+="Steuersatz Lieferant / ";
						}
						if (oErmittleSteuer.get_bSteuerVermerkErmittelt_EK() && S.isEmpty(((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK")).getText()))
						{
							((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK")).setText(oErmittleSteuer.get_cRUECK_STEUERVERMERK_EK());
							((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT)).setSelectedIndex(oErmittleSteuer.get_intNummerInDropDownEK());
							cWarnText+="Steuervermerk";
						}
					}
					else
					{
						if (oErmittleSteuer.get_bSteuerSatzErmittelt_VK() && S.isEmpty(((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ")).get_oTextField().getText()))
						{
							((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ")).get_oTextField().setText(MyNumberFormater.formatDez(oErmittleSteuer.get_bdRUECK_MWST_VK(),2,true));
							cWarnText+="Steuersatz Abnehmer / ";
						}
						if (oErmittleSteuer.get_bSteuerVermerkErmittelt_VK() && S.isEmpty(((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK")).getText()))
						{
							((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK")).setText(oErmittleSteuer.get_cRUECK_STEUERVERMERK_VK());
							((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT)).setSelectedIndex(oErmittleSteuer.get_intNummerInDropDownVK());
							cWarnText+="Steuervermerk";
						}
						
					}
					if (cWarnText.endsWith(" / "))
					{
						cWarnText=cWarnText.substring(0,cWarnText.length()-3);
					}
					if (S.isFull(cWarnText))
					{
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es wurden folgende Felder gesetzt: "+cWarnText+"! UNBEDINGT PRÜFEN, DA ANDERE BEWERTUNG MÖGLICH !!"));
					}
					
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Steuerermittlung erst möglich, wenn die Sortenbezeichner und alle Adressen vorhanden sind !!!"));
				}
			
			}
			
		}
	}
	
	
	
	private class ownValidatorPreisPruefung extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			FUO_MASK_BUTTON_HOLE_PREIS_STEUER_ZAUBERSTAB oThis = FUO_MASK_BUTTON_HOLE_PREIS_STEUER_ZAUBERSTAB.this;
			
			boolean bPreisAbschluss = oThis.EXT().get_oComponentMAP().get_bActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$PRUEFUNG_PREIS);
			
			MyE2_MessageVector oMV =  new MyE2_MessageVector();
			if (bPreisAbschluss)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Preisfindung ist nicht mehr möglich, wenn ein Preisabschluss erfolgt ist")));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return new MyE2_MessageVector();
		}
	}

	
	
}
