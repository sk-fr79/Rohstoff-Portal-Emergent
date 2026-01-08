/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.REC
 * @author manfred
 * @date 16.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase.ENUM_WaageResultStatus;

/**
 * @author manfred
 * @date 16.03.2020
 *
 */
public abstract class RecDOWaegung extends RB_Dataobject_V22 {
	
	private static final _TAB table = _TAB.waegung;
	
	public final long 				m_waegungPos ;
	public ENUM_WaageResultStatus 	m_waageResultStatus = null;
	public String			      	m_WaageFehlerBeschreibung = null;
	
	
	
	public RecDOWaegung(_TAB p_tab, Long id, MASK_STATUS status, int pos) throws myException {
		super(table, id, status);
		m_waegungPos = pos;
	}

	public RecDOWaegung( MASK_STATUS status, int pos) throws myException {
		super(table, status);
		m_waegungPos = pos;
	}

	public RecDOWaegung(Rec22 baseRec, MASK_STATUS status, int pos) throws myException {
		super(baseRec, status);
		m_waegungPos = pos;
		if (baseRec.get_tab()!=table) {
			throw new myException("RecWaegung can only by of TABLE " + table.baseTableName() );
		}
	}
	
	
	@Override
	public RecDOWaegung _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	
	/**
	 * Füllt die Daten für einen neuen Waegungs-Satz
	 * @author manfred
	 * @date 14.07.2020
	 *
	 * @param oWaageSatz
	 * @return
	 * @throws myException
	 */
	public RecDOWaegung _fill_from( WK_RB_Waegung_Buchungssatz oWaageSatz ) throws myException {
		
			MyE2_MessageVector mv = new MyE2_MessageVector();

			if (m_waegungPos != Long.parseLong(oWaageSatz.getWAEGUNG_POS()) ){
				throw new myException("Fehler: Wägungspositionen stimmen nicht überein.");
			}
			
			
			
//			this._setNewVal(WAEGUNG.waegung_pos, m_waegungPos, mv);

			this._setNewValue(  WAEGUNG.waegung_pos,  oWaageSatz.getWAEGUNG_POS(), mv);
			this._setNewValue(  WAEGUNG.w_brutto_gewicht,  oWaageSatz.getW_BRUTTO_GEWICHT(),mv);
			this._setNewValue(  WAEGUNG.w_datum,  oWaageSatz.getW_DATUM(),mv);
			this._setNewValue(  WAEGUNG.w_einheit,  oWaageSatz.getW_EINHEIT(),mv);
			this._setNewValue(  WAEGUNG.w_fehlercode,  oWaageSatz.getW_FEHLERCODE(),mv);
			this._setNewValue(  WAEGUNG.w_ident_nr,  oWaageSatz.getW_IDENT_NR(),mv);
			this._setNewValue(  WAEGUNG.w_netto_gewicht,  oWaageSatz.getW_NETTO_GEWICHT(),mv);
			this._setNewValue(  WAEGUNG.w_pruefziffer,  oWaageSatz.getW_PRUEFZIFFER(),mv);
			this._setNewValue(  WAEGUNG.w_taracode,  oWaageSatz.getW_TARACODE(),mv);
			this._setNewValue(  WAEGUNG.w_taragewicht,  oWaageSatz.getW_TARAGEWICHT(),mv);
			this._setNewValue(  WAEGUNG.w_terminal,  oWaageSatz.getW_TERMINAL(),mv);
			this._setNewValue(  WAEGUNG.w_waagen_nr,  oWaageSatz.getW_WAAGEN_NR(),mv);
			this._setNewValue(  WAEGUNG.w_waegebereich,  oWaageSatz.getW_WAEGEBEREICH(),mv);
			this._setNewValue(  WAEGUNG.w_zeit,  oWaageSatz.getW_ZEIT(),mv);
			
			this._setNewValue(  WAEGUNG.handeingabe,  oWaageSatz.getHANDEINGABE(), mv);
			this._setNewValue(  WAEGUNG.gewicht,  oWaageSatz.getGEWICHT(), mv);
			this._setNewValueInDatabaseTerminus(WAEGUNG.datum, oWaageSatz.getDATUM());
			this._setNewValueInDatabaseTerminus(WAEGUNG.zeit, oWaageSatz.getZEIT());
			
//			this._setNewValue(  WAEGUNG.datum,  new MyDate(oWaageSatz.getDATUM(), DATE_FORMAT.YYYY_MM_DD_DASH).get_cDateStandardFormat(), mv);
//			this._setNewValue(  WAEGUNG.zeit,  oWaageSatz.getZEIT(), mv);
			
			this._setNewValue(  WAEGUNG.storno,  oWaageSatz.getSTORNO(), mv);
			this._setNewValue(  WAEGUNG.waage_ds_ori,  oWaageSatz.getWAAGE_DS_ORI(), mv);
			this._setNewValue(  WAEGUNG.id_user_waegung,  oWaageSatz.getID_USER_WAEGUNG(), mv);
			this._setNewValue(  WAEGUNG.id_waage_settings,  oWaageSatz.getID_WAAGE_SETTINGS(), mv);
			this._setNewValue(  WAEGUNG.handeingabe_bem,  oWaageSatz.getHANDEINGABE_BEM(), mv);
					
			this.m_waageResultStatus = oWaageSatz.get_WaageResultStatus();
			this.m_WaageFehlerBeschreibung = oWaageSatz.get_WaageFehlerBeschreibung();
				
		return this;
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date 12.03.2020
	 *
	 * @param id
	 * @param pos
	 * @return
	 * @throws myException 
	 */
	public RecDOWaegung _fill_from_wiegekarte( RecDOWiegekarte oWk) throws myException {
		return _fill_from_wiegekarte(oWk.getId());
	}

	
	/**
	 * 
	 * @author manfred
	 * @date 12.03.2020
	 *
	 * @param id
	 * @param pos
	 * @return
	 * @throws myException 
	 */
	public RecDOWaegung _fill_from_wiegekarte( long id) throws myException {
		String sql = 
		   " SELECT * FROM " + bibE2.cTO() + "." + _TAB.waegung.fullTableName() 
		+  " WHERE " + WAEGUNG.id_wiegekarte.fieldName() + " = ? "
		+  " and "   + WAEGUNG.waegung_pos.fieldName() + " = ? " 
		+  " and NVL(" + WAEGUNG.storno.fieldName() + ",'N') = 'N'"
		;
		
		SqlStringExtended sqlex = new SqlStringExtended(sql)
				._addParameter(new Param_Long(id))
				._addParameter(new Param_Long(m_waegungPos));
		super._fill_sql(sqlex);
		
		return this;
	}
	
	
	
	public long get_WaegungPos () {
		return m_waegungPos;
	}

	public ENUM_WaageResultStatus get_waageResultStatus() {
		return m_waageResultStatus;
	}

	public String get_WaageFehlerBeschreibung() {
		return m_WaageFehlerBeschreibung;
	}

	
}
