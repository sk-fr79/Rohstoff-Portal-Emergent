/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController
 * @author manfred
 * @date 07.05.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_ZustandWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Adresse;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_ArtikelBezHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_ArtikelBezKunde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_WeWa;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Mehrfachverwiegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_SorteHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

/**
 * @author manfred
 * @date 07.05.2020
 *
 */
public class WK_RB_MC_ValidateOnSave extends RB_MaskController {

	private MASK_STATUS 			_status  			= null;
	private ENUM_ZustandWiegekarte 	_zustandWiegekarte 	= null;
	private RecDOWiegekarte 			_recWK 				= null;
	private RecDOWaegung1   			_recWaeg1 			= null;
	private RecDOWaegung2   			_recWaeg2 			= null;
	
	MyE2_MessageVector 				_mv					= null;
	
	/**
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public WK_RB_MC_ValidateOnSave(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
		super(p_component, mvForErrors);
		_mv = mvForErrors;
		
	}

	/**
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_componentMap
	 * @param mvForErrors
	 */
	public WK_RB_MC_ValidateOnSave(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super(p_componentMap, mvForErrors);
		_mv = mvForErrors;
	}


	
	/**
	 * @author manfred
	 * @date 30.07.2020
	 *
	 * @param p_dataObjectsCollector
	 * @param mvForErrors
	 */
	public WK_RB_MC_ValidateOnSave(RB_DataobjectsCollector p_dataObjectsCollector, MyE2_MessageVector mvForErrors) {
		super(p_dataObjectsCollector, mvForErrors);
		_mv = mvForErrors;
	}

	/**
	 * 
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_componentMapCollector
	 * @throws myException
	 */
	public MyE2_MessageVector  _validateBeforeSave() throws myException   {
		
//		MyE2_MessageVector _mv = new MyE2_MessageVector();

		WK_RB_MASK_ComponentMap_Wiegekarte  	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);

		_recWK 	= (RecDOWiegekarte) 	compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		_recWaeg1 = (RecDOWaegung1)     _recWK._get_Waegung1();
		_recWaeg2 = (RecDOWaegung2)     _recWK._get_Waegung2();
		
		// Maskenstatus
		_status = this.getMaskStatus();
		
		// Zustand nach letztem Speichern
		_zustandWiegekarte = _recWK._get_ZustandWiegekarte();
		
		// je weiter in der Zustandsliste, desto mehr muss-Felder werden geprüft,
		switch (_zustandWiegekarte) {
			case NEU:
				_validate_NEU(_mv);
				break;
	
			case STAMMDATEN:
				_validate_STAMMDATEN(_mv);
				
				break;
	
			case WAEGUNG1:
				_validate_WAEGUNG1(_mv);
				_validate_GUETERKATEGORIE(_mv);
				
				break;
				
			case WAEGUNG2:
				_validate_WAEGUNG2(_mv);
//				_validate_GUETERKATEGORIE(_mv);
				break;
				
			case GEDRUCKT:
				_validate_GEDRUCKT(_mv);
				break;
	
			default:
				break;
		}
	
		return _mv;
	}
	

	/**
	 * Validiert, bevor man explizit in einen neuen Zustand übergeht, z.B. Wägung1, um zu prüfen,
	 * ob alle vorherigen Bedingungen erfüllt sind
	 * 
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_componentMapCollector
	 * @throws myException
	 */
	public MyE2_MessageVector  _validateBEFORE_Zustand(ENUM_ZustandWiegekarte zustand) throws myException   {
		
		WK_RB_MASK_ComponentMap_Wiegekarte  	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);

		_recWK 	= (RecDOWiegekarte) 	compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		_recWaeg1 = (RecDOWaegung1)     _recWK._get_Waegung1();
		_recWaeg2 = (RecDOWaegung2)     _recWK._get_Waegung2();
		
		// Maskenstatus
		_status = this.getMaskStatus();
		
		// Zustand nach letztem Speichern
		_zustandWiegekarte = _recWK._get_ZustandWiegekarte();

		
		// je weiter in der Zustandsliste, desto mehr muss-Felder werden geprüft,
		switch (zustand) {
			case NEU:
//				_validate_NEU(_mv);
				break;
	
			case STAMMDATEN:
				_validate_NEU(_mv);
				break;
	
			case WAEGUNG1:
				_validate_STAMMDATEN(_mv);
				_validate_GUETERKATEGORIE(_mv);
				break;
				
			case WAEGUNG2:
				_validate_WAEGUNG1(_mv);
				break;
				
			case GEDRUCKT:
				_validate_WAEGUNG2(_mv);
				break;
	
			default:
				break;
		}
	
		return _mv;
	}
	
	
	
	
	/**
	 * spezielle Prüfung, vor dem Drucken. Nach dem Druck sind nur noch bestimmte Änderungen möglich, da abgeschlossen.
	 * @author manfred
	 * @throws myException 
	 * @date 13.10.2020
	 *
	 */
	public void _validateBeforePrint(MyE2_MessageVector mv) throws myException {
		
		WK_RB_MASK_ComponentMap_Wiegekarte  	compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);

		_recWK 	= (RecDOWiegekarte) 	compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		_recWaeg1 = (RecDOWaegung1)     _recWK._get_Waegung1();
		_recWaeg2 = (RecDOWaegung2)     _recWK._get_Waegung2();

		
		
		// alles was bei waegung gesetzt sein muss
		_validate_WAEGUNG2(mv);
		
		if (_recWK.getUfs(WIEGEKARTE.gedruckt_am) == null) {
			// beim ersten mal drucken, prüfen, ob die Güterkategorie gesetzt ist
			// hier die Güterkategorie beim speichern testen...
			_validate_GUETERKATEGORIE(mv);
			
			// prüfen, ob ein Lagerplatz ausgewählt ist, wenn ein Container vorhanden ist
			_validate_CONTAINER_LAGERPLATZ(mv);
			
		}
	}
	
	
	
	
	/**
	 * Alles was beim Speichern gesetzt sein muss, im Status NEU
	 * @author manfred
	 * @date 30.07.2020
	 *
	 * @param mv
	 * @throws myException 
	 */
	private void _validate_NEU(MyE2_MessageVector mv) throws myException {
		
		// Sorte muss gesetzt sein
		WK_RB_WeWa			oWEWA 			= (WK_RB_WeWa) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, mv);
		RB_TextField        tfKennz 		= (RB_TextField) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.kennzeichen, mv);
		WK_RB_SelField_WiegekartenTyp oTyp 	= (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte, mv);
		
		// WE/WA
		if (S.isEmpty(oWEWA.rb_readValue_4_dataobject())) { 
				mv.add_MESSAGE(new MyE2_Alarm_Message("WE oder WA muss gesetzt sein!"));
		}
		
		// KENNZEICHEN
		if (S.isEmpty(tfKennz.rb_readValue_4_dataobject())) {
			mv.add_MESSAGE(new MyE2_Alarm_Message("Kennzeichen muss angegeben sein!"));
		}
		
		// WIEGEPROZESS
		if (S.isEmpty(oTyp.rb_readValue_4_dataobject())) {
			mv.add_MESSAGE(new MyE2_Alarm_Message("Wiegeprozess muss gesetzt sein!"));
		}
				
//		_validate_GUETERKATEGORIE(mv);
		
	}
	
	
	/**
	 * Alles was beim Speichern gesetzt sein muss, im Status NEU
	 * @author manfred
	 * @date 30.07.2020
	 *
	 * @param mv
	 * @throws myException 
	 */
	private void _validate_STAMMDATEN(MyE2_MessageVector mv) throws myException {
		// alles vorher
		_validate_NEU(mv);
		_validate_Mehrfachverwiegung(mv);

	}

	
	
	/**
	 * Alles was beim Speichern gesetzt sein muss, im Status NEU
	 * @author manfred
	 * @date 30.07.2020
	 *
	 * @param mv
	 * @throws myException 
	 */
	private void _validate_WAEGUNG1(MyE2_MessageVector mv) throws myException {
		// alles vorher
		_validate_STAMMDATEN(mv);
		_validate_Mehrfachverwiegung(mv);
	}
	
	
	/**
	 * Alles was beim Speichern gesetzt sein muss, im Status NEU
	 * @author manfred
	 * @date 30.07.2020
	 *
	 * @param mv
	 * @throws myException 
	 */
	private void _validate_WAEGUNG2(MyE2_MessageVector mv) throws myException {
		// alles vorher
		_validate_WAEGUNG1(mv);
		
		
		// Sorte muss gesetzt sein
		WK_RB_cb_SorteHand 				cbSorteHand = (WK_RB_cb_SorteHand) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand, mv);
		WK_RB_SelField_ArtikelBezKunde 	oSelArtBez 	= (WK_RB_SelField_ArtikelBezKunde) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(), mv);
		WK_RB_Search_ArtikelBezHand 		oSearchArtB = (WK_RB_Search_ArtikelBezHand) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(), mv);
		
		boolean bErr = false;
		if (cbSorteHand.isSelected()) {
			bErr = S.isEmpty(oSearchArtB._get_idArtikel());
		} else {
			bErr = S.isEmpty(oSelArtBez._get_idArtikel());
		}

		if (bErr) {
			mv._addAlarm("Ein Artikel muss ausgewählt sein!");
		}
		
		// Adresse muss gesetzt sein
		WK_RB_cb_Adresse_Hand 	cbAdrHand 		= (WK_RB_cb_Adresse_Hand) this.getRbComp(RecDOWiegekarte.key, WK_RB_cb_Adresse_Hand.key, mv);
		RB_TextArea 			oAdresseHand  	= (RB_TextArea) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.adresse_lieferant, mv);
		WK_RB_Search_Adresse 	oSearchAdr 		= (WK_RB_Search_Adresse) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_lieferant, mv);
		
		bErr = false;
		if (cbAdrHand.isSelected()) {
			bErr = S.isEmpty(oAdresseHand.getText());
		} else {
			bErr =S.isEmpty(oSearchAdr._getIDAdresse()); 
		}
		if (bErr) {
			mv._addAlarm("Ein Adresse muss angegeben sein!");
		}
		
		
		// Wenn Strecke, dann muss auch das Streckenziel gesetzt sein
		if (getWiegekarteTyp(mv).equals(WK_RB_ENUM_WKTYP.STRECKE)) {
			WK_RB_Search_Adresse 	oSearchAdrStrecke 		= (WK_RB_Search_Adresse) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.id_adresse_abn_strecke, mv);
			
			if (S.isEmpty(oSearchAdrStrecke._getIDAdresse())) {
				mv._addAlarm("Ein Streckenziel muss angegeben sein!");
			}
		}
		
		
	}
	
	
	/**
	 * Güterkategorie muss nur beim 2. Verwiegen gesetzt werden...
	 * @author manfred
	 * @date 08.10.2020
	 *
	 * @param mv
	 * @throws myException
	 */
	private void _validate_GUETERKATEGORIE(MyE2_MessageVector mv) throws myException {
		WK_RB_WeWa			oWEWA 			= (WK_RB_WeWa) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, mv);
		// Wenn wareneingang, dann muss die Güterkategorie gesetzt sein
		if (oWEWA.isLieferant()) {
//		if (_recWK.get_fs_lastVal(WIEGEKARTE.ist_lieferant,"N").equalsIgnoreCase("Y")) {
			// prüfung für alte Wiegekarten ausschalten, z.b. für die Kopie
			if ( S.isEmpty( this.get_liveVal(WIEGEKARTE.gedruckt_am) )) {
				WK_RB_Comp_Gueterkategorie 		compGueterkat = (WK_RB_Comp_Gueterkategorie)this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.gueterkategorie,mv);
				WK_RB_SelField_WiegekartenTyp 	selTyp = (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte,mv);
				if (compGueterkat.isEnabled()) {
					if (S.isEmpty(compGueterkat.rb_readValue_4_dataobject())) {
						mv._addAlarm("Die Güterkategorie muss gesetzt sein!");
					}
				}
				
			}

		}
	}
	
	/**
	 * Wenn ein Container angegeben wurde, muss auch der Ladeplatz belegt sein!
	 * @author manfred
	 * @date 08.10.2020
	 *
	 * @param mv
	 * @throws myException
	 */
	private void _validate_CONTAINER_LAGERPLATZ(MyE2_MessageVector mv) throws myException {
		// Wenn wareneingang, dann muss die Güterkategorie gesetzt sein
		if (_recWK.get_fs_lastVal(WIEGEKARTE.ist_lieferant,"N").equalsIgnoreCase("Y")) {
			// dokumentarische Verwiegung braucht kein containerplatz
			if (_recWK.get_ufs_lastVal(WIEGEKARTE.typ_wiegekarte).equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH.db_val())) {
				return;
			}
			
			// dokumentarische Verwiegung braucht kein containerplatz
			if (_recWK.get_ufs_lastVal(WIEGEKARTE.typ_wiegekarte).equals(WK_RB_ENUM_WKTYP.FREMD.db_val())) {
				return;
			}
			
			// wenn ein Container gesetzt wurde, dann muss auch ein Lagerplatz gesetzt sein
			if( S.isFull(_recWK.getUfs(WIEGEKARTE.id_container_eigen) ) || S.isFull(_recWK.getUfs(WIEGEKARTE.container_nr))  ) {
				if (S.isEmpty(_recWK.getUfs(WIEGEKARTE.id_lagerplatz_absetz)) && S.isEmpty(_recWK.getUfs(WIEGEKARTE.id_lagerplatz_schuett)) ){
					if (ENUM_MANDANT_CONFIG.WK_LAGERPLATZ_CONTAINER_MANDATORY.getCheckedValue().equalsIgnoreCase("Y")) {
						mv._addAlarm("Wenn ein Container angegeben ist, muss ein Lagerplatz angegeben sein!");
					} else {
						mv._addInfo("Wenn kein Lagerplatz angegeben ist, wird der Container auf den Standard-Lagerplatz gebucht!");
					}
					
					// wenn es der Container abgestellt wird, dann wird auch ein Grund benötigt...
					if (S.isFull(_recWK.getUfs(WIEGEKARTE.id_lagerplatz_absetz) )) {
//						if (S.isEmpty(_recWK.getUfs(WIEGEKARTE.container_absetz_grund))) {
//							mv._addAlarm("Wenn ein Container abgestellt wird, muss ein Grund angegeben werden!");
//						}
					}
					
				}
				
			}
			
		}
	}
	
	
	/**
	 * Alles was beim Speichern gesetzt sein muss, im Status wenn alles schon gedruckt ist...
	 * @author manfred
	 * @date 30.07.2020
	 *
	 * @param mv
	 * @throws myException 
	 */
	private void _validate_GEDRUCKT(MyE2_MessageVector mv) throws myException {
		// alles vorher, nur ohne Güterkategorie, da alte Wiegekarten die Güterkategorie nicht kennen und diese deshalb nicht gesetzt ist.
		_validate_WAEGUNG2(mv);
		
	
	}
	

	
	/**
	 *
	 * 
	 * @author manfred
	 * @date 03.08.2020
	 *
	 * @param mv
	 * @throws myException
	 */
	private void _validate_Mehrfachverwiegung(MyE2_MessageVector mv) throws myException {
		String id_Sorte = "";
		String id_Sorte_db = "";
		String Kennzeichen = "";
		String Kennzeichen_db = "";
		
		// Mehrfachverwiegung
		WK_RB_cb_Mehrfachverwiegung cbMehrfach =  (WK_RB_cb_Mehrfachverwiegung) this.getRbComp(RecDOWiegekarte.key, WK_RB_cb_Mehrfachverwiegung.key,mv);
		if (cbMehrfach.isSelected()) {
			return;
		}

		// Werte aus der Maske
		WK_RB_cb_SorteHand 				cbSorteHand = (WK_RB_cb_SorteHand) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand, mv);
		WK_RB_SelField_ArtikelBezKunde 	oSelArtBez 	= (WK_RB_SelField_ArtikelBezKunde) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(), mv);
		WK_RB_Search_ArtikelBezHand 	oSearchArtB = (WK_RB_Search_ArtikelBezHand) this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(), mv);
		
		// Kennzeichen
		RB_TextField			tfKennzeichen = (RB_TextField) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.kennzeichen, mv);
		Kennzeichen = tfKennzeichen.getText().trim();
		if (cbSorteHand.isSelected()) {
			id_Sorte = oSearchArtB._get_idArtikelBez();
		} else {
			id_Sorte = oSelArtBez._get_idArtikelBez();
		}
		
		// Werte aus der Datenbank
		id_Sorte_db = _recWK.get_ufs_dbVal(WIEGEKARTE.id_artikel_bez,"");
		Kennzeichen_db = _recWK.get_fs_dbVal(WIEGEKARTE.kennzeichen,"");
		
		
	
		boolean bMehrfachwiegungErlaubnisErforderlich = true;
		String sDate = ""; 
		
		if (_recWK.is_newRecordSet()) {
			bMehrfachwiegungErlaubnisErforderlich = true;
			sDate = "SYSDATE";
		} else {
			bMehrfachwiegungErlaubnisErforderlich = 
					( 
							( S.isFull(Kennzeichen) && S.isFull(id_Sorte)  ) && 
							( !Kennzeichen.equalsIgnoreCase(Kennzeichen_db) || !id_Sorte.equalsIgnoreCase(id_Sorte_db) )
					);
			
		}	
				
		// Falls man prüfen muss:		
		if (bMehrfachwiegungErlaubnisErforderlich ) {		
				
			// prüfen, ob das Kennzeichen mit der Sorte am gleichen Tag schon mal genutzt wurde...
			sDate = "";
			String idWiegekarte = "";
			
			if ( !_recWK.is_newRecordSet() ){
				sDate = "to_date(" + _recWK.get_myDate_dbVal(WIEGEKARTE.erzeugt_am).get_cDBFormatErgebnis_4_SQLString()+ ")" ;
				idWiegekarte = _recWK.get_key_value();
			}
			
			RecList_Wiegekarte rl = new RecList_Wiegekarte(
						RecList_Wiegekarte.getSqlExt_WK_Mehrfachverwiegung(idWiegekarte, Kennzeichen, id_Sorte, sDate)
					);

			if (rl.size() > 0) {
				mv._addAlarm(new MyString("Die Kennzeichen/Sorten-Kombination wurde heute schon verwendet. Bitte ändern Sie das Kennzeichen, oder setzen Sie den aktivieren Sie die Mehrfachverwiegung."));
			}
			
		}
	}
	

	/**
	 * ermittelt den WK-Typ
	 * @author manfred
	 * @date 03.08.2020
	 *
	 * @param mv
	 * @return
	 * @throws myException
	 */
	private WK_RB_ENUM_WKTYP getWiegekarteTyp(MyE2_MessageVector mv) throws myException {
		WK_RB_SelField_WiegekartenTyp 		selWK = (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte, mv);
		return selWK._getCurrentSelectedTyp();
	}

	
}
