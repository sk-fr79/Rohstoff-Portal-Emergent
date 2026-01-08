/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.REC
 * @author manfred
 * @date 16.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_ZustandWiegekarte;

/**
 * @author manfred
 * @date 16.03.2020
 *
 */
public class RecDOWiegekarte extends RB_Dataobject_V22 {

	private static final _TAB table = _TAB.wiegekarte;
			
	// Erstwägung
	private RecDOWaegung1 _waegung1 = null;
	private boolean     _waegung1_exists = false;
	// Zweitwägung
	private RecDOWaegung2 _waegung2 = null;
	private boolean     _waegung2_exists = false;
	
	// Wiegekarte Befundung
	private RecDOWiegekarteBefund _befundung = null;
	private boolean             _befundung_exists = false;
	
	
	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(table.n())._setHASHKEY("RecWiegekarte@512f0e8d-6fde-41bd-99c1-ff259bd7da73");
	
	/**
	 * @author manfred
	 * @date 16.03.2020
	 *
	 * @param p_tab
	 * @param id
	 * @param status
	 * @throws myException
	 */
	public RecDOWiegekarte( Long id, MASK_STATUS status) throws myException {
		super(table, id, status);
	}
	
	/**
	 * @author manfred
	 * @date 16.03.2020
	 *
	 * @param p_tab
	 * @param status
	 * @throws myException
	 */
	public RecDOWiegekarte( MASK_STATUS status) throws myException {
		super(table, status);
	}

	/**
	 * @author manfred
	 * @date 16.03.2020
	 *
	 * @param baseRec
	 * @param status
	 * @throws myException
	 */
	public RecDOWiegekarte(Rec22 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status);
		if (baseRec.get_tab()!=table) {
			throw new myException("RecWiegekarte can only by of TABLE " + table.baseTableName() );
		}
	}

	
	@Override
	public RecDOWiegekarte _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	@Override
	public RecDOWiegekarte _fill_id(String id) throws myException {
		MyLong lid = new MyLong(id);
		if (lid.get_bOK()) {
			this._fill_id(lid.get_lValue());
		} else {
			throw new myException(this,"Error ID "+id+" is no number !");
		}
		return this;
	}
	
	

	
	/**
	 * liest alle Waegungen neu
	 * @author manfred
	 * @date 18.06.2020
	 *
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _read_WaegungAll() throws myException {

		_read_Waegung1();
		_read_Waegung2();
		
		return this;
	}

	/**
	 * liest Waegung1 neu
	 * @author manfred
	 * @date 08.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _read_Waegung1() throws myException {
		_waegung1 = null;
		_waegung1_exists = false;

		
		try {
			_waegung1 =  new RecDOWaegung1(this.rb_MASK_STATUS())._fill_from_wiegekarte(this);
			// nur eine gefüllter Record ist gültig
			_waegung1_exists = _waegung1.is_ExistingRecord();
			
			if (!_waegung1_exists) {
				_waegung1 = null;
			}		
		} catch (Exception e) 
		{
			_waegung1 = null;
			_waegung1_exists = false;
		}

		return this;

	}
	
	/**
	 * liest Waegung2 neu
	 * @author manfred
	 * @date 08.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _read_Waegung2() throws myException {
		_waegung2 = null;
		_waegung2_exists = false;

		try {
			_waegung2 = new RecDOWaegung2(this.rb_MASK_STATUS())._fill_from_wiegekarte(this);
			_waegung2_exists = _waegung2.is_ExistingRecord();
			
			if (!_waegung2_exists) {
				_waegung2 = null;
			}
			
		} catch (Exception e) {
			_waegung2 = null;
			_waegung2_exists = false;
		}
		
		return this;

	}
	
	
	
	/**
	 * Liest die Waegungen nur einmal und holt sie dann aus der lokalen Variable 
	 * (evtl. nicht mehr aktuell)
	 * @author manfred
	 * @date 18.06.2020
	 *
	 * @return
	 * @throws myException
	 */
	private void _get_WaegungAll()  {
		_get_Waegung1();
		_get_Waegung2();
	}
	
	
	
	/**
	 * Hängt die Waegung an die Wiegekarte 
	 * @author manfred
	 * @date 15.07.2020
	 *
	 * @param waegung
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _set_Waegung(RecDOWaegung waegung) throws myException {
		if (waegung instanceof RecDOWaegung1) {
			return _set_Waegung1((RecDOWaegung1)waegung);
		} else if (waegung instanceof RecDOWaegung2) {
			return _set_Waegung2((RecDOWaegung2)waegung);
		} else {
			throw new myException(this,"Fehler bei der Zuordnung der Wägung.");
		}
	}
	
	
	/**
	 * Setzen einer neuen Waegung von aussen. Falls eine Waegung schon existiert, wird eine Exception geschmissen.
	 * @author manfred
	 * @date 13.03.2020
	 *
	 * @param waegung
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _set_Waegung1(RecDOWaegung1 waegung) throws myException {
		boolean bStatus = this.isStatusNew();
		bStatus = this.is_newRecordSet();
		
		if (waegung == null && _get_Waegung1() != null && _get_Waegung1().is_newRecordSet()) {
			_waegung1 = null;
		} else if (_get_Waegung1() != null) throw new myException(this,"Waegung1 already exists.");
		_waegung1 = waegung;
		return this;
	}
	
	/**
	 * Setzt die Referenz der Waegung 1 auf null;
	 * @author manfred
	 * @date 17.09.2020
	 *
	 * @return
	 */
	public RecDOWiegekarte _clearWaegung1() {
		_waegung1 = null;
		_waegung1_exists = false;
		return this;
	}
	
	public RecDOWiegekarte _clearWaegung2() {
		_waegung2 = null;
		_waegung1_exists = false;
		return this;
	}
	
	
	
	public RecDOWaegung1 _get_Waegung1() {
		if (_waegung1 == null) {
			try {
				_read_Waegung1();
			} catch (Exception e) {
				_waegung1 = null;
			}
		}
		return _waegung1;
	}

	public String _get_IDWaegung1 () {
		String id = null;
		try {
			if (_get_Waegung1() != null) {
				id = _waegung1.getIdLong().toString();
			}
		} catch (myException e) {
			
		}
		return id;
	}
	
	
	/**
	 * true, wenn storniert
	 * @author manfred
	 * @date 16.09.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public boolean _is_Storno() {
		boolean bStorno = false;
		try {
			bStorno = this.get_fs_lastVal(WIEGEKARTE.storno, "N").equalsIgnoreCase("Y");
		} catch (myException e) {
			e.printStackTrace();
		}
		return bStorno;
	}
	
	
	/**
	 * Setzen einer neuen Waegung von aussen. Falls eine Waegung schon existiert, wird eine Exception geschmissen.
	 * @author manfred
	 * @date 13.03.2020
	 *
	 * @param waegung
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _set_Waegung2(RecDOWaegung2 waegung) throws myException {
		if (waegung == null && _get_Waegung2() != null && _get_Waegung2().is_newRecordSet()) {
			_waegung2 = null;
		} else if (_get_Waegung2() != null) throw new myException(this,"Waegung2 already exists.");
		_waegung2 = waegung;
		return this;
	}
	
	
	public RecDOWaegung2 _get_Waegung2()  {
		if (_waegung2 == null) {
			try {
				_read_Waegung2();
			} catch (Exception e) {
				_waegung2 = null;
			}
		}
		return _waegung2;
	}

	
	public String _get_IDWaegung2 () {
		String id = null;
		try {
			if (_get_Waegung2() != null) {
				id = _waegung2.getIdLong().toString();
			}
		} catch (myException e) {
			
		}
		return id;
	}
	
	
	
	/**
	 * gibt den Zustand der Wiegekarte und der zugehörigen Waegungen an, für die Maskendarstellung
	 * @author manfred
	 * @date 16.03.2020
	 *
	 * @return
	 */
	public ENUM_ZustandWiegekarte _get_ZustandWiegekarte() {
		ENUM_ZustandWiegekarte e = ENUM_ZustandWiegekarte.NEU;
				
		
		_get_WaegungAll();
		
		String sGedruckt = null;
		String sDruckzaehlerES = null;
		boolean bGedruckt = false;
		try {
			sGedruckt = get_fs_dbVal(WIEGEKARTE.gedruckt_am) ;
			sDruckzaehlerES = get_fs_dbVal(WIEGEKARTE.druckzaehler_eingangsschein);
			
		} catch (myException e1) {
			
		}
		bGedruckt = sGedruckt != null || sDruckzaehlerES != null;
		
		
		if (is_newRecordSet()) {
			e = ENUM_ZustandWiegekarte.NEU;
		} else if (_waegung1 == null || _waegung1.is_newRecordSet()) {
			e = ENUM_ZustandWiegekarte.STAMMDATEN;
		} else if (_waegung2 == null || _waegung2.is_newRecordSet() ) {
			e = ENUM_ZustandWiegekarte.WAEGUNG1;
		} else if (!bGedruckt) {
			e = ENUM_ZustandWiegekarte.WAEGUNG2;
		} else {
			e = ENUM_ZustandWiegekarte.GEDRUCKT;
		} ;
		
		if (_is_Storno()) {
			e = ENUM_ZustandWiegekarte.STORNO;
		}
		
		return e;
	}
	

	/**
	 * Gibt die aktuell getätigten Waegungen zurück 
	 * 0 - keine Wägung
	 * 1 - erste Wägung
	 * 2 - zweite Wägung
	 * 
	 * @author manfred
	 * @date 03.08.2020
	 *
	 * @return
	 */
	public int _get_CurrentWaegungPos () {
		int iWaegungPos = 0;
		
		ENUM_ZustandWiegekarte zustand = _get_ZustandWiegekarte();
		
		if (  zustand.equals(ENUM_ZustandWiegekarte.NEU) || zustand.equals(ENUM_ZustandWiegekarte.STAMMDATEN)  ) {
			iWaegungPos = 0;
		} else if (zustand.equals(ENUM_ZustandWiegekarte.WAEGUNG1)) {
			iWaegungPos = 1;
		} else {
			iWaegungPos = 2;
		}
		return iWaegungPos;
	}
	
	

	
	
	
	/**
	 * Gibt den Befundungs-Record zurück falls vorhanden
	 * @author manfred
	 * @date 22.01.2021
	 *
	 * @return
	 */
	public RecDOWiegekarteBefund _get_Befundung()  {
		if (_befundung == null) {
			try {
				_read_Befundung();
			} catch (Exception e) {
				_befundung = null;
			}
		}
		return _befundung;
	}
	
	
	
	/**
	 * Liest den Befundungs-Record der Wiegekarte
	 * @author manfred
	 * @date 22.01.2021
	 *
	 * @return
	 * @throws myException
	 */
	public RecDOWiegekarte _read_Befundung() throws myException {
		_befundung= null;
		_befundung_exists = false;

		try {
			_befundung = new RecDOWiegekarteBefund(this.rb_MASK_STATUS())._fill_from_wiegekarte(this);
			_befundung_exists = _befundung.is_ExistingRecord();
			
			if (!_befundung_exists) {
				_befundung = null;
			}
			
		} catch (Exception e) {
			_befundung = null;
			_befundung_exists = false;
		}
		
		return this;

	}
	
	
}
