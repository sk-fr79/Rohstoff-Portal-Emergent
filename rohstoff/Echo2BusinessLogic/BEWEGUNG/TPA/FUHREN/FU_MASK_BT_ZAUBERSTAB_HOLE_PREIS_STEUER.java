package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
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
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG._HD_ActionAgent_FindTaxDef__FUHREN_MASKE;

public class FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER extends MyE2_Button
{

	public FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER()
	{
		super(E2_ResourceIcon.get_RI("wizard.png"),true);
		this.setToolTipText(new MyE2_String("Preise und Sorten-Ladevorgang manuell ausführen ").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		
		//2013-01-20: validierer: der zauberstab darf nur gedrueckt werden, wenn mindestens noch eine Seite der preisvergabe offen ist
		this.add_GlobalValidator(new ownValidatorPreisPruefung());
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_ComponentMAP oMap = FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER.this.EXT().get_oComponentMAP();
			
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
			
			//2013-01-21: preisermittlung kann nur erfolgen, wenn es noch keinen preisabschluss gegeben hat
			
			FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER oThis = FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER.this;
			
			boolean bPreisAbschlussLinks = oThis.EXT().get_oComponentMAP().get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS);
			boolean bPreisAbschlussRechts = oThis.EXT().get_oComponentMAP().get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS);

			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			
			// ---------------------------------- beginn EK --------------
			Long 		lID_ADRESSE_UF_EK =   	oMap.get_LActualDBValue("ID_ADRESSE_START",null,null);
			Long 		lID_ADRESSE_LAGER_UF_EK =oMap.get_LActualDBValue("ID_ADRESSE_LAGER_START",null,null);
			Long        lID_ARTIKEL_EK = 		null;
			Long 		lID_ARTIKELBEZ_UF_EK =  oMap.get_LActualDBValue("ID_ARTIKEL_BEZ_EK",null,null);
			if (lID_ARTIKELBEZ_UF_EK != null)
			{
				lID_ARTIKEL_EK = new RECORD_ARTIKEL_BEZ(""+lID_ARTIKELBEZ_UF_EK).get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_lValue(null);
			}
				
				
				
			if (!bPreisAbschlussLinks)
			{
				Long	 	lID_ID_VPOS_KON_EK =	oMap.get_LActualDBValue("ID_VPOS_KON_EK",null,null);
				Long 		lID_ID_VPOS_STD_EK =	oMap.get_LActualDBValue("ID_VPOS_STD_EK",null,null);
				String 		cPreisManuell_EK = 		((MyE2_DB_CheckBox)oMap.get__Comp("MANUELL_PREIS_EK")).isSelected()?"Y":"N";
				String 		cDatumVon_EK =			oMap.get_ActualMyDate("DATUM_AUFLADEN", true, true, null)==null?"":oMap.get_ActualMyDate("DATUM_AUFLADEN", true, true, null).get_cUmwandlungsergebnis();
				String 		cDatumBis_EK =			cDatumVon_EK;
				BigDecimal  bdEINZELPREIS_EK = 		oMap.get_bdActualDBValue("EINZELPREIS_EK", null, null);
	
				
				FU___ERMITTLE_PREIS  oErmittlerEK = 
					new FU___ERMITTLE_PREIS(
								lID_ADRESSE_UF_EK==null?"":""+lID_ADRESSE_UF_EK.longValue(),
								lID_ARTIKEL_EK==null?"":""+lID_ARTIKEL_EK.longValue(),
								lID_ID_VPOS_KON_EK==null?"":""+lID_ID_VPOS_KON_EK.longValue(),	
								lID_ID_VPOS_STD_EK==null?"":""+lID_ID_VPOS_STD_EK.longValue(),	
								cPreisManuell_EK,
								bdEINZELPREIS_EK,
								cDatumVon_EK,
								cDatumBis_EK,
								oMV,
								true,
								null);
	
	
				// -----------------------------------------------------
				//EK-SEITE fuellen
				String ID_VPOS_STD_EK = oErmittlerEK.get_ID_VPOS_STD_Rueck_UF();
				BigDecimal EINZELPREIS_EK = oErmittlerEK.get_bdNeuerPreis_Rueck();
				String cPREIS_MANUELL_YN_EK = S.NN(oErmittlerEK.get_cPreisManuell_Y_N_Rueck());
				
				if (ID_VPOS_STD_EK==null)
				{
					((MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_STD_EK")).prepare_ContentForNew(false);
				}
				else
				{
					((MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_STD_EK")).set_cActualMaskValue(ID_VPOS_STD_EK, true, false, false);
				}
				((MyE2_DB_TextField)oMap.get__Comp("EINZELPREIS_EK")).setText(EINZELPREIS_EK==null?"":MyNumberFormater.formatDez(EINZELPREIS_EK, 2, true));
				((MyE2_DB_CheckBox)oMap.get__Comp("MANUELL_PREIS_EK")).setSelected(cPREIS_MANUELL_YN_EK.equals("Y"));
				// ---------------------------------- EK FERTIG --------------
			} else {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Preisermittlung auf der Ladeseite ist gesperrt, da dort schon der Preisabschluss vorliegt !")));
			}

			
			
			
			// ---------------------------------- beginn VK --------------
			Long 		lID_ADRESSE_UF_VK =   	oMap.get_LActualDBValue("ID_ADRESSE_ZIEL",null,null);
			Long 		lID_ADRESSE_LAGER_UF_VK =oMap.get_LActualDBValue("ID_ADRESSE_LAGER_ZIEL",null,null);
			Long        lID_ARTIKEL_VK = 		null;
			Long 		lID_ARTIKELBEZ_UF_VK =  oMap.get_LActualDBValue("ID_ARTIKEL_BEZ_VK",null,null);
			if (lID_ARTIKELBEZ_UF_VK != null)
			{
				lID_ARTIKEL_VK = new RECORD_ARTIKEL_BEZ(""+lID_ARTIKELBEZ_UF_VK).get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_lValue(null);
			}
			
			if (!bPreisAbschlussRechts)
			{
				Long	 	lID_ID_VPOS_KON_VK = 	oMap.get_LActualDBValue("ID_VPOS_KON_VK",null,null);
				Long 		lID_ID_VPOS_STD_VK = 	oMap.get_LActualDBValue("ID_VPOS_STD_VK",null,null);
				String 		cPreisManuell_VK = 		((MyE2_DB_CheckBox)oMap.get__Comp("MANUELL_PREIS_VK")).isSelected()?"Y":"N";
				String 		cDatumVon_VK =			oMap.get_ActualMyDate("DATUM_ABLADEN", true, true, null)==null?"":oMap.get_ActualMyDate("DATUM_ABLADEN", true, true, null).get_cUmwandlungsergebnis();
				String 		cDatumBis_VK =			cDatumVon_VK;
				BigDecimal  bdEINZELPREIS_VK = 		oMap.get_bdActualDBValue("EINZELPREIS_VK", null, null);
	
				
				FU___ERMITTLE_PREIS  oErmittlerVK = 
					new FU___ERMITTLE_PREIS(
								lID_ADRESSE_UF_VK==null?"":""+lID_ADRESSE_UF_VK.longValue(),
								lID_ARTIKEL_VK==null?"":""+lID_ARTIKEL_VK.longValue(),
								lID_ID_VPOS_KON_VK==null?"":""+lID_ID_VPOS_KON_VK.longValue(),	
								lID_ID_VPOS_STD_VK==null?"":""+lID_ID_VPOS_STD_VK.longValue(),	
								cPreisManuell_VK,
								bdEINZELPREIS_VK,
								cDatumVon_VK,
								cDatumBis_VK,
								oMV,
								false,
								null);
				
				
				// -----------------------------------------------------
				//VK-SEITE fuellen
				String ID_VPOS_STD_VK = oErmittlerVK.get_ID_VPOS_STD_Rueck_UF();
				BigDecimal EINZELPREIS_VK = oErmittlerVK.get_bdNeuerPreis_Rueck();
				String cPREIS_MANUELL_YN_VK = S.NN(oErmittlerVK.get_cPreisManuell_Y_N_Rueck());
				
				if (ID_VPOS_STD_VK==null)
				{
					((MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_STD_VK")).prepare_ContentForNew(false);
				}
				else
				{
					((MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_STD_VK")).set_cActualMaskValue(ID_VPOS_STD_VK, true, false, false);
				}
				((MyE2_DB_TextField)oMap.get__Comp("EINZELPREIS_VK")).setText(EINZELPREIS_VK==null?"":MyNumberFormater.formatDez(EINZELPREIS_VK, 2, true));
				((MyE2_DB_CheckBox)oMap.get__Comp("MANUELL_PREIS_VK")).setSelected(cPREIS_MANUELL_YN_VK.equals("Y"));
				
				// ---------------------------------- VK FERTIG --------------
			} else {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Preisermittlung auf der Abladeseite ist gesperrt, da dort schon der Preisabschluss vorliegt !")));
			}


			
			//2013-04-29: hier entscheiden, ob mit der alten oder neuen methode die Steuer ermittelt wird
			boolean bSteuerViaHandelsdef = __RECORD_MANDANT_ZUSATZ.IS__Value("STEUERERMITTLUNG_MIT_HANDELSDEF", "N", "N");
			
			
			if (bSteuerViaHandelsdef) {
				
				if (bibMSG.get_bIsOK()) {   //20181023: keine handelsdef ziehen, wenn ein fehler aufgelaufen ist
					
					//hier die neue pruefung auf fuhren ohne abrechnung
					PAIR<Boolean, MyE2_MessageVector> check = new __FU_FUO_handlingTaxdefOhneAbrechnung().setTaxAndTaxMessageForFuhrenOhneAbrechnung(oMap);
					if (check.getVal1()== null) {
						bibMSG.MV()._add(check.getVal2());
					} else if (check.getVal1()) {	
						bibMSG.MV()._add(check.getVal2());
					} else {
						new _HD_ActionAgent_FindTaxDef__FUHREN_MASKE(bPreisAbschlussLinks,bPreisAbschlussRechts).executeAgentCode(execInfo);
					}
				}
				
			} else {
			
			
				// steuervermerk EK und VK
				String cWarnText = "";
	
				if (lID_ADRESSE_LAGER_UF_EK!=null && lID_ADRESSE_LAGER_UF_VK!=null && lID_ARTIKEL_EK!=null && lID_ARTIKEL_VK!=null) {
					FU___ERMITTLE_STEUERVERMERK_UND_MWST oErmittleSteuer = 
						  new FU___ERMITTLE_STEUERVERMERK_UND_MWST(
								  new RECORD_ADRESSE(""+lID_ADRESSE_LAGER_UF_EK),
								  new RECORD_ADRESSE(""+lID_ADRESSE_LAGER_UF_VK),
								  new RECORD_ARTIKEL(""+lID_ARTIKEL_EK),
								  new RECORD_ARTIKEL(""+lID_ARTIKEL_VK),
								  oMap.get_cActualDBValueFormated("DATUM_AUFLADEN"),
								  oMap.get_cActualDBValueFormated("DATUM_ABLADEN"),
								  oMap.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$EINZELPREIS_EK, BigDecimal.ZERO, BigDecimal.ZERO),
								  oMap.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$EINZELPREIS_VK, BigDecimal.ZERO, BigDecimal.ZERO));
					
					
					if (!bPreisAbschlussLinks) {
						if (oErmittleSteuer.get_bSteuerSatzErmittelt_EK() && S.isEmpty(((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ_EK")).get_oTextField().getText()))	{
							((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ_EK")).get_oTextField().setText(MyNumberFormater.formatDez(oErmittleSteuer.get_bdRUECK_MWST_EK(),2,true));
							cWarnText+="Steuersatz Ladeseite / ";
						}
						if (oErmittleSteuer.get_bSteuerVermerkErmittelt_EK() && S.isEmpty(((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK_EK")).getText())) {
							((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK_EK")).setText(oErmittleSteuer.get_cRUECK_STEUERVERMERK_EK());
							((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK)).setSelectedIndex(oErmittleSteuer.get_intNummerInDropDownEK());
		
							cWarnText+="Steuervermerk Ladeseite  / ";
						}
					} else {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Steuerfindung Ladeseite nicht mehr möglich, da bereits der Preisabschluss vorliegt!"));
					}
					
					if (!bPreisAbschlussRechts) {
						if (oErmittleSteuer.get_bSteuerSatzErmittelt_VK() && S.isEmpty(((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ_VK")).get_oTextField().getText()))	{
							((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ_VK")).get_oTextField().setText(MyNumberFormater.formatDez(oErmittleSteuer.get_bdRUECK_MWST_VK(),2,true));
							cWarnText+="Steuersatz Abladeseite / ";
						}
						if (oErmittleSteuer.get_bSteuerVermerkErmittelt_VK() && S.isEmpty(((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK_VK")).getText())) {
							((MyE2_DB_TextArea)oMap.get__Comp("EU_STEUER_VERMERK_VK")).setText(oErmittleSteuer.get_cRUECK_STEUERVERMERK_VK());
							((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK)).setSelectedIndex(oErmittleSteuer.get_intNummerInDropDownVK());
		
							cWarnText+="Steuervermerk Abladeseite  / ";
						}
					} else {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Steuerfindung Abladeseite nicht mehr möglich, da bereits der Preisabschluss vorliegt!"));
					}
					
					
					
					if (cWarnText.endsWith(" / ")) 	{
						cWarnText=cWarnText.substring(0,cWarnText.length()-3);
					}
					if (S.isFull(cWarnText)) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es wurden folgende Felder gesetzt: "+cWarnText+"! UNBEDINGT PRÜFEN, DA ANDERE BEWERTUNG MÖGLICH !!"),true);
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
			FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER oThis = FU_MASK_BT_ZAUBERSTAB_HOLE_PREIS_STEUER.this;
			
			boolean bPreisAbschlussLinks = oThis.EXT().get_oComponentMAP().get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS);
			boolean bPreisAbschlussRechts = oThis.EXT().get_oComponentMAP().get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS);
			
			MyE2_MessageVector oMV =  new MyE2_MessageVector();
			if (bPreisAbschlussLinks && bPreisAbschlussRechts)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Preisfindung ist nicht mehr möglich, wenn auf beiden Seiten ein Preisabschluss erfolgt ist")));
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
