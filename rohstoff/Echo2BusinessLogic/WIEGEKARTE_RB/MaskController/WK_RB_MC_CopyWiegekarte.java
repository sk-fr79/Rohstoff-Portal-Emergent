/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController
 * @author manfred
 * @date 07.05.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMapCollector;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Fuhre;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Waegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_ArtikelBezHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_ArtikelBezKunde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_SorteHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;

/**
 * @author manfred
 * @date 07.05.2020
 *
 */
public class WK_RB_MC_CopyWiegekarte extends RB_MaskController {
	
	MyE2_MessageVector 				_mv					= null;
	
	/**
	 * @author manfred
	 * @date 07.05.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public WK_RB_MC_CopyWiegekarte(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
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
	public WK_RB_MC_CopyWiegekarte(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
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
	public WK_RB_MC_CopyWiegekarte(RB_DataobjectsCollector p_dataObjectsCollector, MyE2_MessageVector mvForErrors) {
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
	public Long  _createWKFolgewaegung( boolean bCommit) throws myException   {

		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		if (rec._get_IDWaegung2() == null) {
			_mv._addAlarm("Eine Folgewägung kann nur durchgeführt werden, wenn die Zweitwägung durchgeführt wurde!");
			return null;
		}
		
		if (rec.is_newRecordSet()) {
			_mv._addAlarm("Bevor eine Folgewägung durchgeführt werden kann, muss die Wiegekarte gespeichert sein!");
			return null;
		}
		
		// die Parent-Wiegekarte für die Folgewiegekarte 
		WK_RB_MASK_ComponentMapCollector compMapColl = (WK_RB_MASK_ComponentMapCollector) this.get_ComponentMapCollector();
		
		
		// Nochmal prüfen, ob man speichern muss/kann...
		if (rec.get_fs_lastVal(WIEGEKARTE.gedruckt_am) == null) {
			// wenn die Wiegekarte schon mal gedruckt wurde, dann nicht mehr speichern
			WK_RB_MC_ValidateOnSave valOnSave = new WK_RB_MC_ValidateOnSave(compMap, _mv);
			valOnSave._validateBeforeSave();
			if (_mv.hasAlarms()) {
				return null;
			}

			//Speichern der aktuellen WK...
			compMapColl._DoCompleteSaveCycle();
		}
		
		
		
		// Anlegen der Folgewiegekarte...		
		Long idBaseParent = rec.get_raw_resultValue_Long(WIEGEKARTE.id_wiegekarte);
		Long idParent = rec.get_raw_resultValue_Long(WIEGEKARTE.id_wiegekarte_parent, idBaseParent);
		
		String typ = rec.get_ufs_dbVal(WIEGEKARTE.typ_wiegekarte);
		boolean bIstDokumentarisch = typ.equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH.db_val());
		boolean bIstWKKorrektur = typ.equals(WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR.db_val());
		
		
		
		VEK<IF_Field> fieldsToClear = new VEK<IF_Field>();
		fieldsToClear._a(rec.getAutoFieldsStd());
		fieldsToClear._a(WIEGEKARTE.anz_behaelter)
					  ._a(WIEGEKARTE.anz_bigbags)
					  ._a(WIEGEKARTE.anz_gitterboxen)
					  ._a(WIEGEKARTE.anz_paletten)
					  ._a(WIEGEKARTE.befund)
					  ._a(WIEGEKARTE.bemerkung1)
					  ._a(WIEGEKARTE.bemerkung2)
					  ._a(WIEGEKARTE.bemerkung_intern)
					  ._a(WIEGEKARTE.container_nr)
					  ._a(WIEGEKARTE.container_tara)
					  ._a(WIEGEKARTE.id_container_eigen)
					  ._a(WIEGEKARTE.gedruckt_am)
					  ._a(WIEGEKARTE.druckzaehler)
					  ._a(WIEGEKARTE.druckzaehler_eingangsschein)
					  ._a(WIEGEKARTE.befund)
					  ._a(WIEGEKARTE.es_nr)
					  ._a(WIEGEKARTE.id_artikel_bez)
					  ._a(WIEGEKARTE.id_artikel_sorte)
					  ._a(WIEGEKARTE.id_wiegekarte)
					  ._a(WIEGEKARTE.id_wiegekarte_parent)
					  ._a(WIEGEKARTE.sorte_hand)
					  ._a(WIEGEKARTE.storno)
					  ._a(WIEGEKARTE.storno_grund)
					  ._a(WIEGEKARTE.storniert_am)
					  ._a(WIEGEKARTE.storniert_von)
					  ._a(WIEGEKARTE.ist_gesamtverwiegung)
					  ._a(WIEGEKARTE.lfd_nr)
					  ._a(WIEGEKARTE.container_nr)
					  ._a(WIEGEKARTE.container_tara)
					  ._a(WIEGEKARTE.siegel_nr)
					  ._a(WIEGEKARTE.nettogewicht)
					  ._a(WIEGEKARTE.gewicht_abzug)
					  ._a(WIEGEKARTE.gewicht_abzug_fuhre)
					  ._a(WIEGEKARTE.gewicht_nach_abzug_fuhre)
					  ._a(WIEGEKARTE.gewicht_nach_abzug)
					  ._a(WIEGEKARTE.gueterkategorie)
					  ._a(WIEGEKARTE.id_lagerplatz_absetz)
//					  ._a(WIEGEKARTE.container_absetz_grund)
					  ._a(WIEGEKARTE.id_lagerplatz_schuett)
					  ._a(WIEGEKARTE.id_wiegekarte_storno)
					  ;
					  
	
		
		
		RecDOWiegekarte _recCopy  = new RecDOWiegekarte(rec.getRecForCreateCopy(fieldsToClear,_mv),MASK_STATUS.NEW_COPY);
		_recCopy._setNewValue(WIEGEKARTE.id_wiegekarte_parent, idParent, _mv);
		_recCopy._setNewValueInDatabaseTerminus(WIEGEKARTE.lfd_nr, "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL");

		// wenn der Parent eine dokumentarische Verwiegung war, dann auf Wiegeschein setzen... 
		if (bIstDokumentarisch || bIstWKKorrektur) {
			_recCopy._setNewValue(WIEGEKARTE.typ_wiegekarte, WK_RB_ENUM_WKTYP.WIEGESCHEIN.db_val(), _mv);
		}
		// wenn der Parent eine Gesamtverwiegung ist, dann Sortierverwiegung
		if (typ.equals(WK_RB_ENUM_WKTYP.GESAMTVERWIEGUNG.db_val())) {
			_recCopy._setNewValue(WIEGEKARTE.typ_wiegekarte, WK_RB_ENUM_WKTYP.SORTIERVERWIEGUNG.db_val(), _mv);
		}
		
		
		// WK_BEFUND erzeugen
		RecDOWiegekarteBefund _recwkBefund = null;
		_recwkBefund = new RecDOWiegekarteBefund(MASK_STATUS.NEW);
		_recwkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, _TAB.wiegekarte.seq_currval());
		
		
		
		_recCopy._SAVE(false, _mv);
		_recwkBefund._SAVE(bCommit, _mv);
		
		Long id = null;
		
		if (_mv.isOK()) {
			id = _recCopy.get_rec_after_save_new().getId();
			Long id_new = _recCopy.getActualID();
			_mv.add_MESSAGE(new MyE2_Info_Message("Folge-Wiegekarte angelegt: ID " + String.valueOf(id) + " ... " + String.valueOf(id_new)));
			compMap.getParams().getNavigationList().RefreshList();
		}
		
		return id;
	}
	
	
	/**
	 * Setzen der Werte für eine kopierte Wiegekarte...
	 * @author manfred
	 * @date 18.09.2020
	 *
	 * @return
	 */
	public WK_RB_MC_CopyWiegekarte _setValuesForCopyOfWK() {
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		// falls der Mask-Status new Copy ist dann Werte setzten, sonst nicht
		if (compMap.getStatus().equals(MASK_STATUS.NEW_COPY)) {
		
			RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
			
			try {
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.id_wiegekarte, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.id_wiegekarte_parent, null);

				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.lfd_nr, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.es_nr, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.anz_behaelter, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.anz_bigbags, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.anz_gitterboxen, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.anz_paletten, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.nettogewicht, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.gewicht_abzug, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.gewicht_abzug_fuhre, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.gewicht_nach_abzug, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.gewicht_nach_abzug_fuhre, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.befund, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.bemerkung1, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.bemerkung2, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.bemerkung_intern, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.container_nr, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.container_tara, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.id_container_eigen, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.druckzaehler_eingangsschein, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.druckzaehler, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.gedruckt_am, null);

				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.ist_gesamtverwiegung, "N");
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.siegel_nr, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.id_vpos_tpa_fuhre, null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.id_vpos_tpa_fuhre_ort, null);
				
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.storno,null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.storniert_am,null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.storniert_von,null);
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.id_wiegekarte_storno,null);
				
				
				//_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.storno_grund,null);
				//_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.storniert_am,null);
				//_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.storniert_von,null);
				
				
				// sped. Zusatz löschen...
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.adresse_spedition, null);
				
				WK_RB_Comp_Waegung compWaeg1 = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key());
				compWaeg1.rb_set_db_value_manual(null);
				WK_RB_Comp_Waegung compWaeg2 = (WK_RB_Comp_Waegung) this.get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key());
				compWaeg2.rb_set_db_value_manual(null);
				
				// Güterkategorie
				WK_RB_Comp_Gueterkategorie compGueterkat = (WK_RB_Comp_Gueterkategorie) this.get_comp(RecDOWiegekarte.key, WIEGEKARTE.gueterkategorie);
				compGueterkat.rb_set_db_value_manual(null);
				
				// WK-Typ auf WIegekarte
				WK_RB_SelField_WiegekartenTyp selTyp = (WK_RB_SelField_WiegekartenTyp) this.get_comp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte,_mv);
				if (selTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH)
						|| selTyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR)){
					selTyp._setCurrentSelectedTyp(WK_RB_ENUM_WKTYP.WIEGESCHEIN);
				}
				
				// Sorte löschen
				_setMaskValue(RecDOWiegekarte.key, WIEGEKARTE.sorte_hand, "N");
				
				WK_RB_cb_SorteHand cbSorteHand = (WK_RB_cb_SorteHand) get_comp(RecDOWiegekarte.key,WIEGEKARTE.sorte_hand);
				cbSorteHand.rb_set_db_value_manual("N");
				
				WK_RB_Search_ArtikelBezHand   searchArtbez = (WK_RB_Search_ArtikelBezHand) get_comp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key());
				searchArtbez._setActive(false);
				searchArtbez.rb_set_db_value_manual(null);
				
				WK_RB_SelField_ArtikelBezKunde selArtbez = (WK_RB_SelField_ArtikelBezKunde) get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key());
				selArtbez._setActive(true);
				selArtbez.rb_set_db_value_manual("");
				
				WK_RB_Comp_Fuhre compFuhre = (WK_RB_Comp_Fuhre)  get_comp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key());
				compFuhre._clearFuhrenData();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return this;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_MaskController#_setMaskValue(panter.gmbh.Echo2.RB.BASICS.RB_KM, panter.gmbh.indep.dataTools.IF_Field, java.lang.String)
	 */
	@Override
	public RB_MaskController _setMaskValue(RB_KM maskKey, IF_Field field, String value) throws Exception {
		try {
			return super._setMaskValue(maskKey, field, value);
		} catch (Exception e) {
			//Feld wohl nicht auf der Maske
		}
		return this; 
		
	}
	
}
