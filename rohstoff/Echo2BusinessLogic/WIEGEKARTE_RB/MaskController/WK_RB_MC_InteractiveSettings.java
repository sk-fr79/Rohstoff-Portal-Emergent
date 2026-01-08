/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController
 * @author manfred
 * @date 07.05.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindAVVCodeV2;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindAVVCodeV2.ENUM_RetValues;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_Erfassung_Waegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_WaegungPos;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMapCollector;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Fuhre;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Waegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_ContainerAbsetzGrund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen_Trailer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Adresse;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_ArtikelBezHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Container;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_ArtikelBezKunde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_WeWa;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Spedition_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Fremdcontainer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_LagerplatzAbsetz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_LagerplatzSchuett;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_SorteHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_StrahlungGeprueft;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfHinweisAllgemein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfSortenHinweis;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.WK_RB_FuhrenInfo;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.WK_RB_RECORD_FuhrenInfo;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_OwnLKW;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;

/**
 * @author manfred
 * @date 07.05.2020
 *
 */
public class WK_RB_MC_InteractiveSettings extends RB_MaskController {

//	private MASK_STATUS _status ;

	
	
	
	
	/**
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public WK_RB_MC_InteractiveSettings(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
		super(p_component, mvForErrors);
	}

	/**
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_componentMap
	 * @param mvForErrors
	 */
	public WK_RB_MC_InteractiveSettings(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super(p_componentMap, mvForErrors);
	}


	
	/**
	 * 
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_componentMapCollector
	 * @throws myException
	 */
	public MyE2_MessageVector  _loadFuhre()   {
		
		MyE2_MessageVector _mv = new MyE2_MessageVector();
		
//		_status = this.getMaskStatus();
		
		String idFuhre 		= null;
		String idFuhreOrt 	= null;
		String idLager 		= null;
		
		WK_RB_RECORD_FuhrenInfo recFI = null;

		WK_RB_Comp_Fuhre cFuhre;
		try {
			cFuhre = (WK_RB_Comp_Fuhre) this.get_comp(WK_RB_CONST.getLeadingMaskKey(), WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key());
			idFuhre = cFuhre.getIDFuhre();
			idFuhreOrt = cFuhre.getIDFuhreOrt();
			idLager = cFuhre.getIDLager();
			
			// ***wenn die Wiegekarte geladen wird, dann stehen die Daten auch hier drin... 
			Long lidFuhre = this.getLongLiveVal(RecDOWiegekarte.key, WIEGEKARTE.id_vpos_tpa_fuhre.fk());
			Long lidFuhreOrt = this.getLongLiveVal(RecDOWiegekarte.key, WIEGEKARTE.id_vpos_tpa_fuhre_ort.fk());
			Long lidLager = this.getLongLiveVal(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_lager.fk());
			///***
			
			String idWiegekarte = null;
			if ( !this.isNew() ) {
				idWiegekarte = this.get_MyLong_dbVal(RecDOWiegekarte.key, WIEGEKARTE.id_wiegekarte).get_cUF_LongString();
			}

			// wenn die Fuhre gefüllt wurde, dann den Fuhren-Info-Record laden...
			if (S.isFull(idFuhre )) {
				
				_mv._add( checkIfFuhreIsAlreadyUsed(idWiegekarte, idFuhre, idFuhreOrt) ) ;
				if (_mv.hasAlarms()) {
					// Fuhre resetten und abbrechen
					cFuhre._clearFuhrenData();
					return _mv;
				}
				
				WK_RB_FuhrenInfo oFI = new WK_RB_FuhrenInfo();
				recFI = oFI.getFuhrenInfo(idFuhre, idFuhreOrt,idLager);
				if (! recFI.isLagerInListe()) {
					// Fuhre resetten und abbrechen
					cFuhre._clearFuhrenData();
					_mv._addAlarm("Das Lager ist nicht in der Liste der akzeptierten Lager eingetragen!");
					return _mv;
				}
				
				
				
				setzeFuhrendatenInMaske(recFI);
				
				
				_setzeStatusKundenadresse(true,_mv);
				_setzeStatusSpeditionsadresse(true,_mv);
				_setzeStatusGueterkategorie(_mv);
				_setzeSortenInfo();
				_setzeStatusWaegung(_mv);
				
				// speichern der Wiegekarte um den aktuellen Zustand festzuschreiben...
				WK_RB_MASK_ComponentMapCollector compMapColl = (WK_RB_MASK_ComponentMapCollector) get_ComponentMapCollector();
				compMapColl._DoCompleteSaveCycle();
				
			}
			
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Fuhre geladen. FuhrenID: " + idFuhre));
			
			
		} catch (myException e) {
			_mv.add_MESSAGE(new MyE2_Alarm_Message("fehler beim handling"));
		}
	
		
		return _mv;
		
	}
	
	
	

	
	
	
	
	/**
	 * liest alle Wiegekarten die schon eine Fuhre zugeordnet haben, mit dieser ID
	 * @author manfred
	 * @date 31.07.2020
	 *
	 * @param oFI
	 * @return
	 * @throws myException 
	 */
	private MyE2_MessageVector checkIfFuhreIsAlreadyUsed(String idWiegekarte, String idFuhre, String idFuhreOrt) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		// prüfen, ob die Fuhre schon in einer Wiegekarte verarbeitet wurde...
		RecList_Wiegekarte rlWK = new RecList_Wiegekarte(RecList_Wiegekarte.getSqlExt_WK_Fuhre(idWiegekarte,idFuhre,idFuhreOrt));
		
		if (rlWK.size()>0) {
			mv._addAlarm("Die Fuhre wurde schon einmal verarbeitet. Bitte ändern Sie die Fuhre oder löschen Sie die Fuhre in der anderen Wiegekarte!");
		}
		
		return mv;
	}
	
	
	
	
	/**
	 * Setzt die Daten der Fuhre in der Maske
	 * @author manfred
	 * @date 18.05.2020
	 *
	 * @param oRecFI
	 * @throws myException
	 */
	private void setzeFuhrendatenInMaske(WK_RB_RECORD_FuhrenInfo oRecFI)throws myException {

		if (oRecFI == null)
			return;

		boolean bLief 		= oRecFI.getIST_LIEFERANT().equalsIgnoreCase("Y");
		boolean bIstStrecke = oRecFI.getIST_STRECKE().equalsIgnoreCase("Y");

		//
		//  Adressen setzen
		//
		String idAdresseKunde = null;
		String idAdresseStrecke = null;
		String idAdresseSpedition = null;

		
		
		WK_RB_SelField_WiegekartenTyp wkTyp = (WK_RB_SelField_WiegekartenTyp)this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.typ_wiegekarte.fk(),bibMSG.MV());
		String typ = wkTyp.getActualDbVal();
		boolean bIstDokumentarisch = typ.equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH.db_val());
		
		// Standards setzen / Strecke löschen
		this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_abn_strecke,bibMSG.MV()).rb_set_db_value_manual("");
		
		// Wenn es eine dokumentarische Verwiegung war, darf nichts anderes gesetzt werden.
		// und nur, wenn das Feld aktiv ist
		if (!bIstDokumentarisch && wkTyp.isEnabled()){
			if (bIstStrecke) {
				wkTyp._setActiveOrFirstDBVal(WK_RB_ENUM_WKTYP.STRECKE.db_val());
			} else {
				wkTyp._setActiveOrFirstDBVal(WK_RB_ENUM_WKTYP.WIEGESCHEIN.db_val());
			}
		}
		
		/**
		 * Prüfen, ob das Lager passt...
		 */
		if (bLief) {
			idAdresseKunde = oRecFI.getID_ADRESSE_START();
			if (bIstStrecke){
				idAdresseStrecke = oRecFI.getID_ADRESSE_ZIEL();
			}
		} else {
			idAdresseKunde = oRecFI.getID_ADRESSE_ZIEL();
		}

		


		// wenn das Lager stimmt, dann die Werte übernehmen...
		if (bibMSG.get_bIsOK()) {
			
			//
			//  WE / WA
			//
			if (oRecFI.isWaageLagerOnly()) {
				// nix tun...
			} else {
				if (!bibALL.isEmpty(oRecFI.getIST_LIEFERANT())) {
					WK_RB_WeWa we = (WK_RB_WeWa)this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.ist_lieferant.fk(),bibMSG.MV());
					we.rb_set_db_value_manual(oRecFI.getIST_LIEFERANT());
				}
			}
			
			
			
			//
			// Kennzeichen
			//
			RB_TextField tfKenn 	= (RB_TextField) this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.kennzeichen, bibMSG.MV());
			RB_TextField tfTrailer 	= (RB_TextField) this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.trailer, bibMSG.MV());
			tfKenn.rb_set_db_value_manual(oRecFI.getTRANSPORTKENNZEICHEN());
			tfTrailer.rb_set_db_value_manual(oRecFI.getANHAENGERKENNZEICHEN());
			

			//
			// Adressfeld Kunde setzen
			//
			WK_RB_Search_Adresse oAdr 				= (WK_RB_Search_Adresse)	this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_lieferant.fk(),bibMSG.MV());
			RB_TextArea 		tfAdressLieferant 	= (RB_TextArea)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_lieferant.fk(),bibMSG.MV());
			if (S.isFull(idAdresseKunde)) {
				oAdr.rb_set_db_value_manual(idAdresseKunde);
				tfAdressLieferant.rb_set_db_value_manual("");
				_setzeStatusKundenadresse(true,bibMSG.MV());
				
			}

			
			//
			// Adressfeld Strecke
			//
			if (bIstStrecke) {
				WK_RB_Search_Adresse oAdrStr = (WK_RB_Search_Adresse)this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_abn_strecke.fk(),bibMSG.MV());
				oAdrStr.rb_set_db_value_manual(idAdresseStrecke);
			}
			


			
			
			//
			// Kundensorten neu laden
			//
			WK_RB_SelField_ArtikelBezKunde oSelArtbez = (WK_RB_SelField_ArtikelBezKunde)this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(),bibMSG.MV());
			WK_RB_Search_ArtikelBezHand    oSorteHand = (WK_RB_Search_ArtikelBezHand) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(),bibMSG.MV());
			RB_cb 						  cbSorteHand= (RB_cb) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand,bibMSG.MV());		

			oSelArtbez.refreshData(idAdresseKunde, bLief);
			
			if (oSelArtbez.getHMAPVisibleAndShadow().containsKey(oRecFI.getID_ARTIKEL_BEZ())) {
				
				// Sortenstatus Setzen
				cbSorteHand.rb_set_db_value_manual("N");
				_setzeStatusSorte(false,bibMSG.MV());
				
				oSelArtbez.set_bEnabled_For_Edit(true);
				oSelArtbez.rb_set_db_value_manual(oRecFI.getID_ARTIKEL_BEZ());

			} else {
				// Sortenstatus setzen
				cbSorteHand.rb_set_db_value_manual("Y");
				_setzeStatusSorte(false,bibMSG.MV());
				
				// Sorte Hand disablen
				oSorteHand.set_bEnabled_For_Edit(true);
				oSorteHand.rb_set_db_value_manual(oRecFI.getID_ARTIKEL_BEZ());
			}
			
			
			
			//
			// Adressfeld Spedition 
			//
			WK_RB_Search_Adresse oAdrSped 		= (WK_RB_Search_Adresse)	this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_spedition.fk(),bibMSG.MV());
			RB_TextArea 		tfAdressSped 	= (RB_TextArea)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_spedition.fk(),bibMSG.MV());
	
			//
			// Falls keine Spedition angegebenist ist und der LKW ein eigener ist, dann ist die Spedition die eigene Firma
			//		
			if (S.isEmpty(oRecFI.getID_ADRESSE_SPEDITION() ) && S.isFull(oRecFI.getTRANSPORTKENNZEICHEN()) ) {
				RecList21_OwnLKW rl = new RecList21_OwnLKW();
				for (Rec21 lkw: rl) {
					String kennz = lkw.getUfs(MASCHINEN.kfzkennzeichen,"*");
					
					if (StringUtils.replaceEach(kennz, new String[] {"-"," "}, new String[] {"",""}).toUpperCase()
							.equals(StringUtils.replaceEach(oRecFI.getTRANSPORTKENNZEICHEN(), new String[] {"-"," "}, new String[] {"",""}).toUpperCase() )
							)  {
						idAdresseSpedition = bibALL.get_ID_ADRESS_MANDANT();
					}
				}
			} else {
				idAdresseSpedition = oRecFI.getID_ADRESSE_SPEDITION();
			}
			if (S.isFull(idAdresseSpedition)) {
				oAdrSped.rb_set_db_value_manual(idAdresseSpedition);
				tfAdressSped.rb_set_db_value_manual("");
				_setzeStatusSpeditionsadresse(true,bibMSG.MV());
			}
			
		}
	}

	
	/**
	 * Wenn eine Lagerverwiegung gemacht wird, werden folgende Werte gesetzt
	 * ID-Adresse: IdAdresse-Mandant
	 * Kennzeichen: Lagerverwiegungs-Kennzeichzen aus Settings
	 * Sorte Hand anwählen
	 * Güterkategorie: Schüttgut / Einlagern
	 * 
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_componentMapCollector
	 * @throws myException
	 */
	public WK_RB_MC_InteractiveSettings  _setzeWerteBeiLagerverwiegung(MyE2_MessageVector mv)   {
		
		MyE2_MessageVector _mv = mv;

		try {
			// wenn der wk-Typ NICHT Lagerverwiegung ist, dann gleich zurück!
			WK_RB_SelField_WiegekartenTyp wkTyp = (WK_RB_SelField_WiegekartenTyp)this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.typ_wiegekarte.fk(),bibMSG.MV());
			if (!wkTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.LG)) {
				return this;
			}
			
			
			String idAdrMandant	= bibALL.get_ID_ADRESS_MANDANT();
			ENUM_Gueterkategorie kat = ENUM_Gueterkategorie.SCHUETTGUT;
			
			// Standards setzen / Strecke löschen
			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_abn_strecke,bibMSG.MV()).rb_set_db_value_manual("");
			
			// Adressenfelder setzen
			WK_RB_Search_Adresse oAdr = (WK_RB_Search_Adresse) getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_lieferant.fk(),bibMSG.MV());
			RB_TextArea 		tfAdressLieferant 	= (RB_TextArea)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_lieferant.fk(),bibMSG.MV());
			
			oAdr.rb_set_db_value_manual(idAdrMandant);
			tfAdressLieferant.rb_set_db_value_manual("");
			_setzeStatusKundenadresse(true,bibMSG.MV());
			
			
			
			
			// wenn das Lager stimmt, dann die Werte übernehmen...
			if (bibMSG.get_bIsOK()) {
				
				//
				//  WE / WA
				//
				
				WK_RB_WeWa we = (WK_RB_WeWa)this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.ist_lieferant.fk(),bibMSG.MV());
				we.rb_set_db_value_manual("Y");
				
				
				//
				// Kennzeichen
				//
				RB_TextField tfKenn 	= (RB_TextField) this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.kennzeichen, bibMSG.MV());
				RB_TextField tfTrailer 	= (RB_TextField) this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.trailer, bibMSG.MV());
				tfKenn.rb_set_db_value_manual(ENUM_MANDANT_CONFIG.WK_LAGERVERWIEGUNG_KENNZEICHEN.getUncheckedValue());
				tfTrailer.rb_set_db_value_manual("");
				
				
				
				
				//
				// Kundensorten neu laden
				//
				WK_RB_SelField_ArtikelBezKunde oSelArtbez = (WK_RB_SelField_ArtikelBezKunde)this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(),bibMSG.MV());
				WK_RB_Search_ArtikelBezHand    oSorteHand = (WK_RB_Search_ArtikelBezHand) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(),bibMSG.MV());
				RB_cb 						  cbSorteHand= (RB_cb) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand,bibMSG.MV());		
				
				oSelArtbez.refreshData(idAdrMandant, true);
				
				cbSorteHand.rb_set_db_value_manual("Y");
				oSorteHand.set_bEnabled_For_Edit(true);
				_setzeStatusSorte(false,bibMSG.MV());
				

				// Güterkategorie
				WK_RB_Comp_Gueterkategorie compGueterkategorie  = (WK_RB_Comp_Gueterkategorie) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.gueterkategorie, mv);
				compGueterkategorie.rb_set_db_value_manual(ENUM_Gueterkategorie.SCHUETTGUT.db_val());
				_setzeStatusGueterkategorie(_mv);
				
				
				
				
				
				//
				// Adressfeld Spedition 
				//
				WK_RB_Search_Adresse oAdrSped 		= (WK_RB_Search_Adresse)	this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_spedition.fk(),bibMSG.MV());
				RB_TextArea 		tfAdressSped 	= (RB_TextArea)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_spedition.fk(),bibMSG.MV());
				oAdrSped.rb_set_db_value_manual("");
				tfAdressSped.rb_set_db_value_manual("");
				
				
				
				//
				//  Bemerkung
				//
				RB_TextArea tfBemerkung  = (RB_TextArea) this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.bemerkung1,bibMSG.MV());
				tfBemerkung.setText(ENUM_MANDANT_CONFIG.WK_BEMERKUNG_BEI_LAGERWIEGUNG.getCheckedValue());
				
				
				//
				//  Absetz-Lagerplätze anwählen
				//
				WK_RB_cb_LagerplatzAbsetz cb = (WK_RB_cb_LagerplatzAbsetz) this.getRbComp(RecDOWiegekarte.key,WK_RB_cb_LagerplatzAbsetz.key,bibMSG.MV());
				cb.rb_set_db_value_manual("Y");
				_setzeStatusLagerplaetze(cb, false, bibMSG.MV());
					
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		return this;
		
	}
	

	
	
	
	
	
	
	public WK_RB_MC_InteractiveSettings  _setzeWerteBeiFremdverwiegung(MyE2_MessageVector mv)   {
		
		MyE2_MessageVector _mv = mv;

		try {
			// wenn der wk-Typ NICHT Lagerverwiegung ist, dann gleich zurück!
			WK_RB_SelField_WiegekartenTyp wkTyp = (WK_RB_SelField_WiegekartenTyp)this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.typ_wiegekarte.fk(),bibMSG.MV());
			if (!wkTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.FREMD)) {
				return this;
			}
						
			String idAdrMandant	= bibALL.get_ID_ADRESS_MANDANT();
			
			
			
			WK_RB_SelField_ArtikelBezKunde oSelArtbez = (WK_RB_SelField_ArtikelBezKunde)this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(),bibMSG.MV());
			WK_RB_Search_ArtikelBezHand    oSorteHand = (WK_RB_Search_ArtikelBezHand) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(),bibMSG.MV());
			RB_cb 						  cbSorteHand= (RB_cb) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand,bibMSG.MV());		

			
			// Sortenstatus setzen
			cbSorteHand.rb_set_db_value_manual("Y");
			_setzeStatusSorte(false,bibMSG.MV());
			
			// Sorte Hand disablen
			oSorteHand.set_bEnabled_For_Edit(true);
			oSorteHand.rb_set_db_value_manual(ENUM_MANDANT_CONFIG.WK_FREMDVERWIEGUNG_ARTIKEL.getCheckedValue());
				
			// Status der ContainerAuswahl setzen
			_setzeStatusContainerNr(false, mv);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		return this;
		
	}
	

	
	
	/** 
	 * Setzt die Spedition anhand der LKW-Kennzeichen, falls die Spedition leer war
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeSpeditionWennEigenerLKW(MyE2_MessageVector mv) {
		try {
			//
			// Adressfeld Spedition 
			//
			WK_RB_Search_Adresse oAdrSped 		= (WK_RB_Search_Adresse)	this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_spedition.fk(),bibMSG.MV());
			RB_TextArea 		tfAdressSped 	= (RB_TextArea)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_spedition.fk(),bibMSG.MV());
			RB_TextField 		tfKenn 			= (RB_TextField) 		this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.kennzeichen, bibMSG.MV());
			
			String sKennzeichen 		= tfKenn.getText();
			String idAdresseSpedition	= oAdrSped._getIDAdresse();
			//
			// Falls keine Spedition angegeben ist ist und der LKW ein eigener ist, dann ist die Spedition die eigene Firma
			//		
			if (S.isEmpty(idAdresseSpedition) && S.isFull(tfKenn.getText()) ) {
				RecList21_OwnLKW rl = new RecList21_OwnLKW();
				for (Rec21 lkw: rl) {
					String kennz = lkw.getUfs(MASCHINEN.kfzkennzeichen,"*");
					
					if (StringUtils.replaceEach(kennz, new String[] {"-"," "}, new String[] {"",""}).toUpperCase()
							.equals(StringUtils.replaceEach(sKennzeichen, new String[] {"-"," "}, new String[] {"",""}).toUpperCase() )
							)  {
						idAdresseSpedition = bibALL.get_ID_ADRESS_MANDANT();
					}
				}
			} 

			if (S.isFull(idAdresseSpedition)) {
				oAdrSped.rb_set_db_value_manual(idAdresseSpedition);
				tfAdressSped.rb_set_db_value_manual("");
				_setzeStatusSpeditionsadresse(true,bibMSG.MV());
			}
			
		} catch (Exception e) {
		}

		return this;
	}
		
	
	
	/**
	 * Setze den Maskenstatus für die Adressfelder (deaktivieren der unbenötigten Felder)
	 * @author manfred
	 * @date 18.05.2020
	 *
	 * @param bOnLoad : true - initialisieren der Maske, false - eventhandler der checkbox
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusKundenadresse(boolean bOnLoad, MyE2_MessageVector mv) {
		if (this.getMaskStatus().equals(MASK_STATUS.VIEW)) {
			return this;
		}

		try {
			// Objekte
			WK_RB_Search_Adresse 	oAdr 				= (WK_RB_Search_Adresse)		this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_lieferant.fk(),mv);
			RB_TextArea 			tfAdressLieferant 	= (RB_TextArea)				this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_lieferant.fk(),mv);
			WK_RB_cb_Adresse_Hand 	cbAdresse_Hand		= (WK_RB_cb_Adresse_Hand)	this.getRbComp(RecDOWiegekarte.key,WK_RB_cb_Adresse_Hand.key,mv);
			//
			
			// Werte 
			String 					sAdresseHand 		= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.adresse_lieferant.fk());
			Long 					lidAdress 			= this.getLongLiveVal(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_lieferant.fk());
			///

			// preset bAdresse Hand
			boolean bAdresseHand = S.isFull(sAdresseHand);
			
			if (bOnLoad) {
				if (bAdresseHand) {
					cbAdresse_Hand._setSelected(true);
				} else {
					cbAdresse_Hand._setSelected(false);
				}
			} 
			_updateStatusDualfelder(cbAdresse_Hand, tfAdressLieferant, oAdr);
						
			
		} catch (Exception e) {
			mv._addInfo("Fehler beim Setzen des Adress-Status");
		}
		
		return this;
	}
	
	
	/**
	 * Lagerplatz-Checkboxen gegenseitig schalten
	 * @author manfred
	 * @date 15.01.2021
	 *
	 * @param bOnLoad
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusLagerplaetze(RB_cb cbCaller, boolean bOnLoad, MyE2_MessageVector mv) {
		if (this.getMaskStatus().equals(MASK_STATUS.VIEW)) {
			return this;
		}

		try {
			// Objekte
			
			WK_RB_Comp_Lagerplatz 	compLagerplatzAbsetz 	= (WK_RB_Comp_Lagerplatz)		this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_lagerplatz_absetz.fk(),mv);
			WK_RB_Comp_Lagerplatz 	compLagerplatzSchuett 	= (WK_RB_Comp_Lagerplatz)		this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_lagerplatz_schuett.fk(),mv);
			
//			WK_RB_Popup_Kennzeichen popKennz 			= (WK_RB_Popup_Kennzeichen) 		compMap.get__Comp(WK_RB_Popup_Kennzeichen.key.get_HASHKEY());
			
//			WK_RB_Popup_ContainerAbsetzGrund popGrund       = (WK_RB_Popup_ContainerAbsetzGrund) this.get_comp(RecDOWiegekarte.key,WK_RB_Popup_ContainerAbsetzGrund.key,mv);
//			WK_RB_Popup_ContainerAbsetzGrund popGrund = (WK_RB_Popup_ContainerAbsetzGrund) this.getComp(RecDOWiegekarte.key.get_HASHKEY(),WK_RB_Popup_ContainerAbsetzGrund.key.get_HASHKEY());

			
			
//			RB_TextField			tfGrund					= (RB_TextField) 				this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.container_absetz_grund.fk(),mv);
			
			
			WK_RB_cb_LagerplatzAbsetz cbAbsetz = (WK_RB_cb_LagerplatzAbsetz) this.getRbComp(RecDOWiegekarte.key,WK_RB_cb_LagerplatzAbsetz.key,mv);
			WK_RB_cb_LagerplatzSchuett cbSchuett = (WK_RB_cb_LagerplatzSchuett) this.getRbComp(RecDOWiegekarte.key,WK_RB_cb_LagerplatzSchuett.key,mv);
			

			// Werte 
			String 					sIdLagerplatzAbsetz		= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.id_lagerplatz_absetz.fk());
			String 					sIdLagerplatzSchuett	= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.id_lagerplatz_schuett.fk());
			
			boolean bLagerplatzAbsetz = false;
			boolean bLagerplatzSchuett = false;
			
			// preset bAdresse Hand
			if (bOnLoad) {
				bLagerplatzAbsetz = S.isFull(sIdLagerplatzAbsetz);
				bLagerplatzSchuett = S.isFull(sIdLagerplatzSchuett);
			} else {
				if (cbCaller != null) {
					bLagerplatzAbsetz = false;
					bLagerplatzSchuett = false;
					
					if (cbCaller instanceof WK_RB_cb_LagerplatzAbsetz ) {
						bLagerplatzAbsetz = cbAbsetz.isSelected();
						if (bLagerplatzAbsetz == false) {
							compLagerplatzAbsetz.rb_set_db_value_manual(null);
//							tfGrund.rb_set_db_value_manual(null);
						}
					}
					if (cbCaller instanceof WK_RB_cb_LagerplatzSchuett){
						bLagerplatzSchuett = cbSchuett.isSelected();
						if (bLagerplatzSchuett == false) {
							compLagerplatzSchuett.rb_set_db_value_manual(null);
						}
					}
				}
			}
			
			
			// alle disablen
			cbAbsetz._setSelected(false);
			compLagerplatzAbsetz.set_bEnabled_For_Edit(false);
//			tfGrund.set_bEnabled_For_Edit(false);
			
			cbSchuett._setSelected(false);
			compLagerplatzSchuett.set_bEnabled_For_Edit(false);
			
			
			if (bLagerplatzAbsetz) {
				cbAbsetz._setSelected(true);
				compLagerplatzAbsetz.set_bEnabled_For_Edit(true);
//				tfGrund.set_bEnabled_For_Edit(true);

				cbSchuett._setSelected(false);
				compLagerplatzSchuett.set_bEnabled_For_Edit(false);
				compLagerplatzSchuett.rb_set_db_value_manual(null);
				
			}
			
			if (bLagerplatzSchuett) {
				cbSchuett._setSelected(true);
				compLagerplatzSchuett.set_bEnabled_For_Edit(true);
				
				cbAbsetz._setSelected(false);
				compLagerplatzAbsetz.set_bEnabled_For_Edit(false);
				compLagerplatzAbsetz.rb_set_db_value_manual(null);
				
//				tfGrund.rb_set_db_value_manual(null);
			}
//			if (bOnLoad) {
//				
//				
//			} else {
//				compLagerplatzAbsetz.set_bEnabled_For_Edit(cbAbsetz.isSelected());
//				compLagerplatzSchuett.set_bEnabled_For_Edit(cbSchuett.isSelected());
//				if (cbCaller instanceof WK_RB_cb_LagerplatzAbsetz) {
//					
//				}
//				
//				
//			}
						
			
		} catch (Exception e) {
			mv._addInfo("Fehler beim Setzen des Lagerplatzes");
		}
		
		return this;
	}
	

	
	/**
	 * Setze den Maskenstatus für die Adressfelder (deaktivieren der unbenötigten Felder)
	 * @author manfred
	 * @date 18.05.2020
	 *
	 * @param bOnLoad : true - initialisieren der Maske, false - eventhandler der checkbox
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusSpeditionsadresse(boolean bOnLoad, MyE2_MessageVector mv) {
		if (this.getMaskStatus().equals(MASK_STATUS.VIEW)) {
			return this;
		}
		
		try {
			// Objekte
			RB_TextArea 					tfSpeditionZusatz 	= (RB_TextArea)				this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.adresse_spedition.fk(),mv);
			WK_RB_cb_Adresse_Spedition_Hand cbSpeditionZusatz	= (WK_RB_cb_Adresse_Spedition_Hand)	this.getRbComp(RecDOWiegekarte.key,WK_RB_cb_Adresse_Spedition_Hand.key,mv);
			
			// Werte 
			String 					sAdresseHand 		= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.adresse_spedition.fk());
			Long 					lidAdress 			= this.getLongLiveVal(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_spedition.fk());

			// preset bAdresse Hand
			boolean bSpedZusatz = S.isFull(sAdresseHand);
			if (bOnLoad) {
				cbSpeditionZusatz._setSelected(bSpedZusatz);
				tfSpeditionZusatz.set_bEnabled_For_Edit(bSpedZusatz);
			} else {
				tfSpeditionZusatz.set_bEnabled_For_Edit(cbSpeditionZusatz.isSelected());
			}
			
		} catch (Exception e) {
			mv._addInfo("Fehler beim Setzen vom Speditions-Zusatz");
		}
		
		return this;
	}
	
	/**
	 * 
	 * @author manfred
	 * @date 19.06.2020
	 *
	 * @param bOnLoad
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusContainerNr(boolean bOnLoad, MyE2_MessageVector mv) {
		if (this.getMaskStatus().equals(MASK_STATUS.VIEW)) {
			return this;
		}
		
		
		try {
			
			WK_RB_SelField_WiegekartenTyp   oSelWKTyp = (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.typ_wiegekarte,mv);
			
			// Objekte
			WK_RB_cb_Fremdcontainer 		cbContainer_Hand		= (WK_RB_cb_Fremdcontainer)	this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.CB_FREMDCONTAINER.key(),mv);
			WK_RB_Search_Container 			oSearchContainer	= (WK_RB_Search_Container)	this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_container_eigen.fk(),mv);
			RB_TextField 					tfContainerNr 		= (RB_TextField)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.container_nr.fk(),mv);
			RB_TextField 					tfContainerTara		= (RB_TextField)			this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.container_tara.fk(),mv);
			
			
			//
			
			// Werte 
			Long 					lidContainer		= this.getLongLiveVal(RecDOWiegekarte.key, WIEGEKARTE.id_container_eigen.fk());
			String 					sContainerNr 		= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.container_nr.fk());
			String 					sContainerTara 		= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.container_tara.fk());
			
			


			// preset bAdresse Hand
			boolean bContainerHand = S.isFull(sContainerNr);
			if (bOnLoad) {
				if (bContainerHand) {
					cbContainer_Hand._setSelected(true);
				} else {
					cbContainer_Hand._setSelected(false);
				}
			} 
			
			// update Status der Felder
			boolean bSelected = cbContainer_Hand.isSelected();
			tfContainerNr.set_bEnabled_For_Edit(bSelected);
			tfContainerTara.set_bEnabled_For_Edit(bSelected);
			oSearchContainer.set_bEnabled_For_Edit(!bSelected);
		
			if (!bSelected) {
				tfContainerNr.rb_set_db_value_manual(null);
				tfContainerTara.rb_set_db_value_manual(null);
			} else {
				oSearchContainer.rb_set_db_value_manual(null);
			}
			
			/// bei Fremdverwiegung darf der eigencontainer nicht aktiv sein
			if (oSelWKTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.FREMD)) {
				oSearchContainer.set_bEnabled_For_Edit(false);
			}
			
			
			//
			// wenn eine Handverwiegung vorhanden ist, dann darf der Container nicht mehr geändert werden.
			//
			WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
			RecDOWiegekarte _recWK = (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
			RecDOWaegung1 recWaeg1 = _recWK._get_Waegung1();
			boolean bHand1 = recWaeg1 != null &&  recWaeg1.get_ufs_dbVal(WAEGUNG.handeingabe, "N").equals("Y");
			RecDOWaegung2 recWaeg2 = _recWK._get_Waegung2();
			boolean bHand2 = recWaeg2 != null && recWaeg2.get_ufs_dbVal(WAEGUNG.handeingabe, "N").equals("Y");
			
			boolean bDisableContainer = false;
			bDisableContainer |= (recWaeg2 != null && (bHand1 || bHand2));
			bDisableContainer |= (recWaeg1 != null && recWaeg2 == null && (bHand1 || bHand2));
			
			if (bDisableContainer) {
				tfContainerNr.set_bEnabled_For_Edit(false);
				tfContainerTara.set_bEnabled_For_Edit(false);
				oSearchContainer.set_bEnabled_For_Edit(false);
				cbContainer_Hand.set_bEnabled_For_Edit(false);
			}
			
			
			
			
			
		} catch (Exception e) {
			mv._addInfo("Fehler beim Setzen des Container-Status");
		}
		
		return this;
	}
	

	
	/**
	 * 
	 * @author manfred
	 * @date 26.05.2020
	 *
	 * @param bOnLoad
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusSorte(boolean bOnLoad, MyE2_MessageVector mv) {
		try {
			
			if (this.getMaskStatus().equals(MASK_STATUS.VIEW)) {
				return this;
			}
			
			// Objekte
			WK_RB_SelField_ArtikelBezKunde 	oSelArtikebezKunde 	= (WK_RB_SelField_ArtikelBezKunde) 	this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key() ,mv);
			WK_RB_Search_ArtikelBezHand 		oSearchArtikelBez 	= (WK_RB_Search_ArtikelBezHand)	 	this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(),mv);
			RB_cb 							cbSorteHand	   		= (RB_cb)						 	this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.sorte_hand.fk() ,mv);
			
			String 							sSorteHand 			= this.get_liveVal(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand.fk());
			
			boolean bSorteHand = false;
			
			if (bOnLoad) {
				bSorteHand = S.isFull(sSorteHand) && sSorteHand.equalsIgnoreCase("Y");
			} else  {
				bSorteHand = cbSorteHand.isSelected();
			} 
			
			oSearchArtikelBez.set_bEnabled_For_Edit(bSorteHand);
			oSearchArtikelBez._setActive(bSorteHand);
			oSelArtikebezKunde.set_bEnabled_For_Edit(!bSorteHand);
			oSelArtikebezKunde._setActive(!bSorteHand);

			if (bSorteHand) {
				oSelArtikebezKunde.clearData();
				
			} else {
				oSearchArtikelBez.clearData();
			}
			
			
			
		} catch (Exception e) {
			mv._addInfo("Fehler beim Setzen des Sorte-Status");
		}
		
		return this;
	}
	

	
	/**
	 * Setzt anhand der Kundensorte die zugehörigen Sorten-Hinweise
	 * @author manfred
	 * @date 22.06.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_InteractiveSettings _setzeSortenInfo()  {
		
		MyE2_MessageVector _mv = new MyE2_MessageVector();
		
		// bei Sorte-Hand keine Hinweise
		RB_cb cbSorteHand = null;;
		String sInfo ="";

		WK_RB_tfSortenHinweis			tfSortenHinweis = null ;
		
		tfSortenHinweis		= (WK_RB_tfSortenHinweis) 		  this.getComp(RecDOWiegekarte.key, WK_RB_tfSortenHinweis.key);
		tfSortenHinweis.clear();
		
		// Gefahrgut-Infos
		Vector<String> v_sGefahrgut = getGefahrgutInfo();
		
		
		// Verarbeitungsinfo lesen:
		String idArtikelbez = null;
		String idAdresse 	= null;
		Boolean bIsLieferant = null;
		
		
		
		try {
			idArtikelbez 	= _getCurrentIDArtikelBez(_mv);
			idAdresse 		= _getCurrentIDAdresse(_mv);
			bIsLieferant 	= _getCurrentIsLieferant(_mv);
				
				
			// wenn einer der Werte nicht gesetzt ist, dann kann nichts gelesen werden.
			if (!bibALL.isEmpty(idArtikelbez)  && !bibALL.isEmpty(idAdresse)  &&! (bIsLieferant == null ) ){
				// jetzt noch die Beschreibung aus dem Artikelstamm lesen, falls es einen gibt.
				String sQuery = " SELECT NVL(A.VERARBEITUNGS_INFO,'') FROM " +
						bibE2.cTO() + ".JT_ARTIKELBEZ_LIEF  A WHERE A.ID_ADRESSE = ( " +
						"     SELECT NVL(L.ID_ADRESSE_BASIS,A1.ID_ADRESSE) FROM " + 
						bibE2.cTO() + ".JT_ADRESSE A1" +
						"     LEFT OUTER JOIN " + 
						bibE2.cTO() + ".JT_LIEFERADRESSE L " +
						"    ON L.ID_ADRESSE_LIEFER = A1.ID_ADRESSE WHERE A1.ID_ADRESSE = ?  ) " +
						" AND A.ID_ARTIKEL_BEZ = ?" +  
						" AND A.ARTBEZ_TYP = ?" ;
				
				String[][] cArrDaten = {};
				SqlStringExtended sqlExt = new SqlStringExtended(sQuery)
						._addParameters(new VEK<ParamDataObject>()
								._a(new Param_Long(new MyLong(idAdresse).get_lValue()))
								._a(new Param_Long(new MyLong(idArtikelbez).get_lValue()))
								._a(new Param_String("", (bIsLieferant ? "EK" : "VK")) 
										));
				
				cArrDaten = bibDB.EinzelAbfrageInArray(sqlExt, (String) null);
				
				
				
				if (cArrDaten.length >0) {
					if (cArrDaten[0][0] != null ) {
						sInfo = cArrDaten[0][0];
					}
				}
			}
			
		} catch (myException e) {
			sInfo = "";
		}			

		
				
		
		if (v_sGefahrgut.size() > 0 && S.isFull(sInfo)) {
			sInfo += "\n";
		}
		
		for (String s: v_sGefahrgut) {
			sInfo += s + "\n";
		}
		
		tfSortenHinweis.setInfoText(sInfo);

		
		
		// AVV-Code Sorte Hand noch setzen, wenn nötig
		try {
			WK_RB_Search_ArtikelBezHand oSearchArtikelBez 	= (WK_RB_Search_ArtikelBezHand)	this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(),_mv);
			if (oSearchArtikelBez.isEnabled()) {
				oSearchArtikelBez.refreshArtbez();
			}
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return this;	
	}
	
	
	
	
	/**
	 * versucht den AVV- Code einer Handsorte zu ermitteln...
	 * @author manfred
	 * @date 03.02.2021
	 *
	 * @return
	 * @throws myException 
	 */
	public String _getAVVCodeSorteHand(Rec21_artikel_bez  artBez) throws myException {
		String sAVV = "*Kein AVV*";
		if (artBez != null) {
			WK_RB_WeWa compWEWA = (WK_RB_WeWa) this.getComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant);
			boolean bIsLeft = compWEWA.isLieferant();
			
			PdServiceFindAVVCodeV2 serviceAVV =new PdServiceFindAVVCodeV2();
			String sAVV_read = null;
			// zuerst prüfen, ob es einen Kundenspezifischen AVV gibt
			String idAdresse = this._getCurrentIDAdresse(bibMSG.MV());
			Rec21_adresse recAdresse = null;
			HashMap<PdServiceFindAVVCodeV2.ENUM_RetValues, Object> hmResult = new HashMap<>();

			if (!S.isEmpty(idAdresse) ) {
				recAdresse = new Rec21_adresse()._fill_id(idAdresse);
				sAVV_read = serviceAVV.getAVVCode_As_String(recAdresse, artBez, bIsLeft, hmResult);
				if (sAVV_read != null) {
					sAVV = sAVV_read;
					String herkunft = (String) hmResult.get(ENUM_RetValues.herkunft);
					if (herkunft != null) {
						bibMSG.MV().add_MESSAGE(new MyE2_Info_Message(herkunft));
					}
				}  
			}
			
			// wenn kein AVV bei der Adresse gefunden, oder keine Adresse vorhanden, dann
			// auf der Seite des Mandanten schauen
			if (S.isEmpty(sAVV_read)) {
				recAdresse = new Rec21_adresse()._fill_id(bibALL.get_ID_ADRESS_MANDANT());
				sAVV_read = serviceAVV.getAVVCode_As_String(recAdresse, artBez, !bIsLeft, hmResult);
				if (sAVV_read != null) {
					sAVV = sAVV_read;
					String herkunft = (String) hmResult.get(ENUM_RetValues.herkunft);
					if (herkunft != null) {
						bibMSG.MV().add_MESSAGE(new MyE2_Info_Message(herkunft));
					}
				}
				
			}
			
			
		    if (sAVV_read == null) {
				bibMSG.MV().add_MESSAGE(new MyE2_Info_Message("Kein AVV gefunden."));
		    }
		}
		return sAVV;
	}
	
	
	
	
	
	
	
	/**
	 * prüft anhand des Artikels, ob es sich um ein gefährlichen Artikel handelt.
	 * @author manfred
	 * @date 16.10.2020
	 *
	 * @return
	 */
	private  Vector<String>  getGefahrgutInfo(){
		Vector<String> vListGefahrgut = new Vector<String>();
		String idArtikel = null;
		String idArtikelbez = null;
		String idAdresse 	= null;
		Boolean bIstLieferant = null;
		
		Long lidArtikel = -1L;
		Long lidArtikelBez = -1L;
		Long lidAdresseKunde = -1L;
		
		
		// Objekte
		WK_RB_Search_ArtikelBezHand 	oSearchArtikelBezHand =null;
		WK_RB_SelField_ArtikelBezKunde  oSelArtikebezKunde = null;
		WK_RB_tfSortenHinweis			tfSortenHinweis = null ;
		WK_RB_WeWa						oWeWa = null;			
		RB_cb 							cbSorteHand	= null;

		try {

			String sWE = _isWE(bibMSG.MV());
			if (S.isEmpty(sWE)){
				return vListGefahrgut;
			} else {
				bIstLieferant = sWE.equalsIgnoreCase("Y");
			}

			
			
			try {
				oWeWa					= (WK_RB_WeWa) this.getComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WEWA.key());
				oSearchArtikelBezHand 	= (WK_RB_Search_ArtikelBezHand)	 	this.getComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key());
				oSelArtikebezKunde 		= (WK_RB_SelField_ArtikelBezKunde) this.getComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key());
				tfSortenHinweis			= (WK_RB_tfSortenHinweis) 		  this.getComp(RecDOWiegekarte.key, WK_RB_tfSortenHinweis.key);
				cbSorteHand = (RB_cb)						 	this.getComp(RecDOWiegekarte.key,WIEGEKARTE.sorte_hand.fk() );
				
				if( !cbSorteHand.isSelected()) {
					idArtikelbez 	= oSelArtikebezKunde._get_idArtikelBez();
					idArtikel 		= oSelArtikebezKunde._get_idArtikel();
				} else {
					idArtikelbez 	= oSearchArtikelBezHand._get_idArtikelBez();
					idArtikel 		= oSearchArtikelBezHand._get_idArtikel();
				}
				
				idAdresse	= _getCurrentIDAdresse(bibMSG.MV());
//				idAdresse 	= oSelArtikebezKunde._get_IdAdresse();
				
			} catch (myException e) {
				e.printStackTrace();
			}
			
			// wenn einer der Werte nicht gesetzt ist, dann kann nichts gelesen werden.
			if (bibALL.isEmpty(idArtikelbez)  ||  tfSortenHinweis ==null ){
				return vListGefahrgut;
			}
			
			
			
			lidArtikel = new MyLong(S.isFull(idArtikel) ? idArtikel : "-1").get_lValue();
			lidArtikelBez = new MyLong(S.isFull(idArtikelbez) ? idArtikelbez : "-1").get_lValue();
			lidAdresseKunde = new MyLong(S.isFull(idAdresse) ? idAdresse : "-1").get_lValue();
			
			String ekvk =  bIstLieferant ? "EK" : "VK";
		
			
			String sqlAVV = " "
					+ "			   SELECT " + 
					"			    JT_EAK_BRANCHE.KEY_BRANCHE||'-'|| " + 
					"			    JT_EAK_GRUPPE.KEY_GRUPPE||'-'|| " + 
					"			    JT_EAK_CODE.KEY_CODE||' '|| " + 
					"			    TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ') " + 
					"			 FROM " + 
					"			   JT_EAK_CODE " + 
					"			   LEFT OUTER JOIN JT_EAK_GRUPPE ON (JT_EAK_CODE.ID_EAK_GRUPPE          = JT_EAK_GRUPPE.ID_EAK_GRUPPE) " + 
					"			   LEFT OUTER JOIN JT_EAK_BRANCHE ON (JT_EAK_GRUPPE.ID_EAK_BRANCHE   = JT_EAK_BRANCHE.ID_EAK_BRANCHE) " + 
					"			 WHERE " + 
					"			   JT_EAK_CODE.ID_EAK_CODE=nvl( " + 
					"			                               (SELECT MAX(ABL1.ID_EAK_CODE) FROM JT_ARTIKELBEZ_LIEF ABL1 " + 
					"        		                                        	WHERE ABL1.ID_ADRESSE          = ?" + 
					"			                                        	AND ABL1.ID_ARTIKEL_BEZ        = ?" + 
					"			                                        	AND ABL1.ARTBEZ_TYP            =  nvl(?,'-')) " + 
					"			                                , 													" + 
					"			                                ( SELECT CASE WHEN 'EK'= nvl(?,'-')  " + 
					"			                                                      THEN   A1.ID_EAK_CODE " + 
					"			                                                      ELSE   A1.ID_EAK_CODE_EX_MANDANT  " + 
					"			                                                      END " + 
					"			                                                FROM JT_ARTIKEL A1 " + 
					"			                                                INNER JOIN JT_ARTIKEL_BEZ B1 ON A1.ID_ARTIKEL = B1.ID_ARTIKEL " + 
					"			                                                WHERE B1.ID_ARTIKEL_BEZ = ?   " + 
					"			                                         ) " + 
					"			                               ) " +
					"			AND NVL(JT_EAK_CODE.GEFAEHRLICH,'N') = 'Y' " + 
					" ";
			
			
			if (lidArtikelBez > 0L ) {
				SqlStringExtended sqlExt = new SqlStringExtended(sqlAVV)
						._addParameter(new Param_Long(lidAdresseKunde))
						._addParameter(new Param_Long(lidArtikelBez))
						._addParameter(new Param_String("", ekvk))
						._addParameter(new Param_String("", ekvk))
						._addParameter(new Param_Long(lidArtikelBez));
				
				String sResult[][] = bibDB.EinzelAbfrageInArray(sqlExt);
				if (sResult.length > 0 ) {
					vListGefahrgut.add("Kundenspezifischer Artikel als Gefahrgut gekennzeichnet! " + sResult[0][0]);
				}
			} 
			

			
			if (lidArtikel > 0) {
				Rec21_artikel recArtikel = new Rec21_artikel()._fill_id(idArtikel);
				if (recArtikel.isGefaehrlichNachBaselCode()) {
					vListGefahrgut.add("Artikel ist gefährlich nach BASEL-CODE!");
					
				}
				if (recArtikel.isGefaehrlichNachOECDCode()) {
					vListGefahrgut.add("Artikel ist gefährlich nach OECD-CODE!");
				}
				if (recArtikel.is_yes_db_val(ARTIKEL.gefahrgut) ){
					vListGefahrgut.add("Artikel ist im Artikelstamm als Gefahrgut gekennzeichnet!");
				}
			}
			
			
		} catch (myException e) {
			
		}
		
		return vListGefahrgut;
		
	}
	

	/**
	 * Schaltet nach abhängigkeit der Checkbox das eine oder andere Feld aktiv. Das inaktive Feld wird gelöscht.
	 * @author manfred
	 * @date 26.05.2020
	 *
	 * @param checkbox
	 * @param enabled_when_checked
	 * @param enabled_when_unchecked
	 * @return
	 */
	public MyE2_MessageVector _updateStatusDualfelder(RB_cb checkbox, IF_RB_Component enabled_when_checked, IF_RB_Component enabled_when_unchecked) {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		try {
			
			if (this.getMaskStatus().equals(MASK_STATUS.VIEW)) {
				return mv;
			}
			
			
			boolean bSelected = checkbox.isSelected();
			enabled_when_checked.set_bEnabled_For_Edit(bSelected);
			enabled_when_unchecked.set_bEnabled_For_Edit(!bSelected);
		
			if (bSelected) {
				enabled_when_unchecked.rb_set_db_value_manual(null);
			} else {
				enabled_when_checked.rb_set_db_value_manual(null);
			}
			
		} catch (Exception e) {
			mv._addInfo("Fehler beim Setzen des Status");
		}
		
		return mv;
	}
	
	

	
	
	/**
	 * laden der Kundensorten, abhängig vom Kunden in id_adresse_lieferant und WE/WA
	 * @author manfred
	 * @date 29.05.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_InteractiveSettings _refreshKundenSorten(MyE2_MessageVector mv)  {

		try {
			String id_adress_lieferant = "";
			WK_RB_Search_Adresse 			oAdr 				= (WK_RB_Search_Adresse)				this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.id_adresse_lieferant.fk(),mv);
			WK_RB_WeWa 						we 					= (WK_RB_WeWa)						this.getRbComp(RecDOWiegekarte.key,WIEGEKARTE.ist_lieferant.fk(),bibMSG.MV());
			WK_RB_SelField_ArtikelBezKunde 	oSelArtikebezKunde 	= (WK_RB_SelField_ArtikelBezKunde) 	this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key() ,mv);
			
			if (we.isValid()) {
				boolean bIstLieferant = we.rb_readValue_4_dataobject().equalsIgnoreCase("Y");
				String idAdresse = oAdr.rb_readValue_4_dataobject();
				oSelArtikebezKunde.refreshData(idAdresse, bIstLieferant);
			} else {
				oSelArtikebezKunde.refreshData("", true);
			}
			
		} catch (myException e) {
			mv._addInfo("Fehler bei setzen der Kundensorten");
		}
		return this;
	}
	
	
	

	/**
	 * Status Strahlung geprüft setzen
	 * @author manfred
	 * @date 23.02.2021
	 *
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatus_StrahlungGeprueft(MyE2_MessageVector mv) {
		WK_RB_cb_StrahlungGeprueft cb;
		
		try {
			cb = (WK_RB_cb_StrahlungGeprueft) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.strahlung_geprueft,mv);
			cb._checkStatus();
		} catch (myException e) {
			mv.add(new MyE2_Info_Message(new MyString("Fehler beim setzen des Status 'Strahlung geprüft'")));
		}

		return this;
	}
	
	
	/**
	 * Status der Güterkategorie-Checkboxen setzen
	 * @author manfred
	 * @date 23.02.2021
	 *
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusGueterkategorie(MyE2_MessageVector mv) {
		try {
			WK_RB_WeWa					compWeWa			= (WK_RB_WeWa)				  this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, mv);
			WK_RB_Comp_Gueterkategorie compGueterkategorie  = (WK_RB_Comp_Gueterkategorie) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.gueterkategorie, mv);
			WK_RB_cb_LagerplatzAbsetz  cbAbsetz 			= (WK_RB_cb_LagerplatzAbsetz) this.get_comp(RecDOWiegekarte.key, WK_RB_cb_LagerplatzAbsetz.key);
			WK_RB_cb_LagerplatzSchuett  cbSchuett 			= (WK_RB_cb_LagerplatzSchuett) this.get_comp(RecDOWiegekarte.key, WK_RB_cb_LagerplatzSchuett.key);
			WK_RB_SelField_WiegekartenTyp  selfieldWKTyp    = (WK_RB_SelField_WiegekartenTyp) this.get_comp(RecDOWiegekarte.key,WIEGEKARTE.typ_wiegekarte,mv);
			
			
			boolean bEnabled = !this.getMaskStatus().equals(MASK_STATUS.VIEW);
			
			if (!compWeWa.isValid() ) {
				bEnabled = false;
			} else {
				bEnabled &= !selfieldWKTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.FREMD);
				bEnabled &= !selfieldWKTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH);
				bEnabled &= compWeWa.isLieferant() ? true : false ;
				if (!bEnabled) {
					compGueterkategorie.rb_set_db_value_manual(null);

					cbAbsetz.setSelected(false);
					_setzeStatusLagerplaetze(cbAbsetz, false, mv);
					cbSchuett.setSelected(false);
					_setzeStatusLagerplaetze(cbSchuett, false, mv);
				}
			}
			
			compGueterkategorie.set_bEnabled_For_Edit(bEnabled);
			cbAbsetz.set_bEnabled_For_Edit(bEnabled);
			cbSchuett.set_bEnabled_For_Edit(bEnabled);
			
		} catch (myException e) {
			mv._addInfo("Fehler beim setzen der Güterkategorie");
		}
		
		
		
		return this;
	}
	

	
	
	

	/**
	 * Abnehmer Strecke nur dann aktiv, wenn Streckenschein gewählt wurde
	 * @author manfred
	 * @date 01.10.2020
	 * 
	 * Anpassung: Oder wenn Korrekturwiegerte und Ursprungswiegekarte war eine Streckenwiegekarte
	 *
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusAdresseAbnehmerStrecke(MyE2_MessageVector mv) {
		try {
			WK_RB_Search_Adresse compAdresse = (WK_RB_Search_Adresse) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_abn_strecke, mv);
			WK_RB_SelField_WiegekartenTyp selWKTyp = (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte, mv);
			
			if (selWKTyp._getCurrentSelectedTyp() != null &&
					(selWKTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.STRECKE)
				 || selWKTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR ))  ) {
				compAdresse.set_bEnabled_For_Edit(true);
			} else {
				compAdresse.set_bEnabled_For_Edit(false);
				compAdresse.rb_set_db_value_manual(null);
			}
		} catch (myException e) {
			mv._addInfo("Fehler beim setzen des Streckenabnehmers");
		}
		
		return this;
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date 20.05.2021
	 *
	 * @param mv
	 * @return
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusWaegung(MyE2_MessageVector mv) {
		try {
			WK_RB_Comp_Waegung  compWaeg1 = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key());
			WK_RB_Comp_Waegung  compWaeg2 = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key());
			compWaeg1.set_enabled_on_handwaegung();
			compWaeg2.set_enabled_on_handwaegung();
			
			
		} catch (myException e) {
			mv._addInfo("Fehler beim setzen des Status der Wägung");
		}
		
		return this;
	}
	
	
	/**
	 * Y - WE
	 * N - WA
	 * "" - nicht gesetzt
	 * 
	 * @author manfred
	 * @date 04.08.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	private String _isWE(MyE2_MessageVector mv) throws myException {
		String sRet = "";
		WK_RB_WeWa		compWeWa	= (WK_RB_WeWa)	 this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, mv);
		if (compWeWa.isValid()) {
			sRet = compWeWa.isLieferant() ? "Y" : "N";
		}
		return sRet;
	}
	
	
	
	/**
	 * gibt die aktuell gesetzte IDArtikelBez zurück
	 * @author manfred
	 * @date 04.08.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public String _getCurrentIDArtikelBez(MyE2_MessageVector mv) throws myException {
		String idArtikelBez = null;
		
		// Sorte muss gesetzt sein
		WK_RB_cb_SorteHand 				cbSorteHand = (WK_RB_cb_SorteHand) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand, mv);
		WK_RB_SelField_ArtikelBezKunde 	oSelArtBez 	= (WK_RB_SelField_ArtikelBezKunde) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(), mv);
		WK_RB_Search_ArtikelBezHand 		oSearchArtB = (WK_RB_Search_ArtikelBezHand) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(), mv);
		
		if (cbSorteHand.isSelected()) {
			idArtikelBez = oSearchArtB._get_idArtikelBez();
		} else {
			idArtikelBez = oSelArtBez._get_idArtikelBez();
		}

		
	
		return idArtikelBez;
	}


	
	/**
	 * gibt die gewählte IDAdresse zurück
	 * @author manfred
	 * @date 04.08.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public String _getCurrentIDAdresse(MyE2_MessageVector mv) throws myException {
		String sRet = null;
		// Adresse muss gesetzt sein
		WK_RB_cb_Adresse_Hand 	cbAdrHand 		= (WK_RB_cb_Adresse_Hand) this.getRbComp(RecDOWiegekarte.key, WK_RB_cb_Adresse_Hand.key, mv);
		WK_RB_Search_Adresse 	oSearchAdr 		= (WK_RB_Search_Adresse) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_lieferant, mv);
		
		if (!cbAdrHand.isSelected()) {
			sRet = oSearchAdr._getIDAdresse(); 
		}

		return sRet;
	}
	

	
	/**
	 * gibt die gewählte IDAdresse der Spedition zurück
	 * @author manfred
	 * @date 04.08.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public String _getCurrentIDAdresseSped(MyE2_MessageVector mv) throws myException {
		String sRet = null;
		// Adresse muss gesetzt sein
		WK_RB_Search_Adresse 	oSearchAdr 		= (WK_RB_Search_Adresse) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_spedition, mv);
		sRet = oSearchAdr._getIDAdresse(); 
		return sRet;
	}


	/**
	 * gibt die gewählte IDAdresse des Streckenziels zurück
	 * @author manfred
	 * @date 04.08.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public String _getCurrentIDAdresseStrecke(MyE2_MessageVector mv) throws myException {
		String sRet = null;
		// Adresse muss gesetzt sein
		WK_RB_Search_Adresse 	oSearchAdr 		= (WK_RB_Search_Adresse) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_abn_strecke, mv);
		sRet = oSearchAdr._getIDAdresse(); 
		return sRet;
	}

	
	
	/**
	 * gibt zurück, ob aktuell Lieferant gesetzt ist.
	 * @author manfred
	 * @date 05.02.2021
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public Boolean _getCurrentIsLieferant(MyE2_MessageVector mv) throws myException {
		Boolean bIstLieferant = null;
		// Adresse muss gesetzt sein
		WK_RB_WeWa 	oWEWA	= (WK_RB_WeWa) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, mv);
		if (oWEWA.isValid()) {
			bIstLieferant = oWEWA.isLieferant();
		} else {
			mv._addInfo("WE/WA nicht gesetzt");
			throw new myException( "WE/WA nicht gesetzt." );
		}
		
		return bIstLieferant;
	}
	
	
	
	
	/**
	 * prüft, ob die 
	 * @author manfred
	 * @date 04.08.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_InteractiveSettings _check_Fuhre_Sorte_Adressen(MyE2_MessageVector mv) throws myException {
		//Fuhrenkomponente holen
		
		if (this.getMaskStatus().equals(MASK_STATUS.NEW) || this.getMaskStatus().equals(MASK_STATUS.NEW_COPY)) {
			return this;
		}
		
		String sFuhrenstatus = "";

		WK_RB_MASK_ComponentMap_Wiegekarte 	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte 						_recWK 	= (RecDOWiegekarte) 	compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		WK_RB_Comp_Fuhre compFuhre = (WK_RB_Comp_Fuhre) this.getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key(), mv);
		String idFuhre = new MyLong( compFuhre.getIDFuhre()).get_cUF_LongString();
		String idFuhreOrt = new MyLong( compFuhre.getIDFuhreOrt()).get_cUF_LongString();
		
		String id_lager = _recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lager);
		
		
		Rec21_VPOS_TPA_FUHRE_ext oFuhre  = null;
		Rec21_VPOS_TPA_FUHRE_ORT_ext  oFuhreOrt = null;
		
		if (S.isFull(idFuhre )) {
			oFuhre = (Rec21_VPOS_TPA_FUHRE_ext) new Rec21_VPOS_TPA_FUHRE_ext()._fill_id(idFuhre);
			if (S.isFull(idFuhreOrt)) {
				oFuhreOrt = (Rec21_VPOS_TPA_FUHRE_ORT_ext) new Rec21_VPOS_TPA_FUHRE_ORT_ext()._fill_id(idFuhreOrt); 
			}
		} else {
			return this;
		}
		
		
		WK_RB_FuhrenInfo oFI = new WK_RB_FuhrenInfo();
		WK_RB_RECORD_FuhrenInfo oRecFI = oFI.getFuhrenInfo(idFuhre,idFuhreOrt,id_lager);
		
		
		// ID ARTIKEL 
		if (!_recWK.get_ufs_dbVal(WIEGEKARTE.id_artikel_bez, "-").equals(S.NN(oRecFI.getID_ARTIKEL_BEZ(),"-")) ){
			sFuhrenstatus += "Der Artikel stimmt nicht mit dem Artikel der Fuhre überein. ";
		}
				
		// ADRESSE KUNDE
		if (_isWE(mv).equals("Y")) {
			if (!_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant, "-").equals(S.NN(oRecFI.getID_ADRESSE_START(),"-")) ){
				sFuhrenstatus += "Der Kunde stimmt nicht mit dem Kunden der Fuhre überein. ";
			}
		} else {
			if (!_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant, "-").equals(S.NN(oRecFI.getID_ADRESSE_ZIEL(),"-")) ){
				sFuhrenstatus += "Der Kunde stimmt nicht mit dem Kunden der Fuhre überein. ";
			}
		}
				
		WK_RB_SelField_WiegekartenTyp typWK = (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte, mv);
		if (typWK._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.STRECKE)) {
			// ADRESSE STRECKE
			if (!_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_abn_strecke, "-").equals(S.NN(oRecFI.getID_ADRESSE_ZIEL(),"-")) ){
				sFuhrenstatus += "Das Streckenziel stimmt nicht mit dem Streckenziel der Fuhre überein. ";
			}
			if (!_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant, "-").equals(S.NN(oRecFI.getID_ADRESSE_START(),"-")) ){
				sFuhrenstatus += "Der Streckenursprung stimmt nicht mit dem Streckenursprung der Fuhre überein. ";
			}

		}
		
		// ADRESSE SPEDITION
		if (!_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_spedition, "-").equals(S.NN(oRecFI.getID_ADRESSE_SPEDITION(),"-")) ){
			sFuhrenstatus += "Die Spedition stimmt nicht mit der Spedition der Fuhre überein. ";
		}
				
		WK_RB_tfHinweisAllgemein tfHinweis = (WK_RB_tfHinweisAllgemein) this.getRbComp(RecDOWiegekarte.key, WK_RB_tfHinweisAllgemein.key, mv);
		
		tfHinweis.setInfoText(sFuhrenstatus);
		
		return this;
	}
	
	/*
			if (m_FuhrenzuordnungIstOK){
							sInfo += "Die Wiegekarte ist in der Fuhre gesetzt. ";
						}
						
						// wenn nicht, dann Anzeigen:
						oRecFI = ladeFuhrenDaten();
						if (bibALL.null2leer(oRecFI.getID_ARTIKEL_BEZ()).equals(bibALL.null2leer(oWiegekarteSatz.getID_ARTIKEL_BEZ())) == false) {
							sFuhrenstatus += "Die Sorten in der WK und der Fuhre stimmen nicht überein. ";
						}

						// wenn es ein Lieferant ist, dann muss in der Fuhre der
						// Abnehmer das Lager sein
						if (oRecFI.getIST_LIEFERANT().equalsIgnoreCase("Y")) {
							if (bibALL.null2leer(
									oWiegekarteSatz.getID_ADRESSE_LIEFERANT()).equals(bibALL.null2leer(oRecFI.getID_ADRESSE_START())) == false) {
								sFuhrenstatus += "Der Lieferant stimmt nicht überein. ";

							}
						} else {
							if (bibALL.null2leer(
									oWiegekarteSatz.getID_ADRESSE_LIEFERANT()).equals(bibALL.null2leer(oRecFI.getID_ADRESSE_ZIEL())) == false) {
								sFuhrenstatus += "Der Kunde stimmt nicht überein. ";

							}
						}

						if (bibALL.null2leer(oRecFI.getID_ADRESSE_SPEDITION()).equals(bibALL.null2leer(oWiegekarteSatz.getID_ADRESSE_SPEDITION())) == false) {
							sFuhrenstatus += "Die Spedition stimmt nicht überein. ";
						}
					}
	 */
	
	
	
	/**
	 * Wenn Folgewägung, dann darf das Kennzeichen nicht mehr editierbar sein.
	 * @author manfred
	 * @date 12.05.2021
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public WK_RB_MC_InteractiveSettings _setzeStatusKennzeichenFolgewaegung(MyE2_MessageVector mv) throws myException {
		
		WK_RB_MASK_ComponentMap_Wiegekarte 	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte 					_recWK 	= (RecDOWiegekarte) 	compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);

		String idWiegekarteParent = _recWK.getUfs(WIEGEKARTE.id_wiegekarte_parent);
		if (idWiegekarteParent != null) {
			// diese Wiegekarte iste eine WK einer Folgewägung
			WK_RB_WeWa 	oWEWA	= (WK_RB_WeWa) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, mv);
			RB_TextField tfKennz = (RB_TextField) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.kennzeichen,mv);
			RB_TextField tfTrailer = (RB_TextField) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.trailer,mv);
			
			WK_RB_Popup_Kennzeichen popKennz 			= (WK_RB_Popup_Kennzeichen) 		compMap.get__Comp(WK_RB_Popup_Kennzeichen.key.get_HASHKEY());
			WK_RB_Popup_Kennzeichen_Trailer popTrailer 	= (WK_RB_Popup_Kennzeichen_Trailer) compMap.get__Comp(WK_RB_Popup_Kennzeichen_Trailer.key.get_HASHKEY());
			
			tfKennz.set_bEnabled_For_Edit(false);
			tfTrailer.set_bEnabled_For_Edit(false);
			popKennz.set_bEnabled_For_Edit(false);
			popTrailer.set_bEnabled_For_Edit(false);
			
		}
		
		return this;
	}
	

}
