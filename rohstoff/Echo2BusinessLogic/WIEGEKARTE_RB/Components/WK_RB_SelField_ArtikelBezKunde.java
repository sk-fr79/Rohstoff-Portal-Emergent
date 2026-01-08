/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;

/**
 * @author manfred
 * @date 14.04.2020
 *
 */
public class WK_RB_SelField_ArtikelBezKunde extends RB_selField {

	private String _sqlBase = null;

	private boolean _bLinked = true;

	private String _id_artikel_bez = null;
	private String _id_artikel = null;
	private String _id_adresse = null;
	private Boolean _bIstLieferant = null;

	
	/**
	 * @author manfred
	 * @date 14.04.2020
	 *
	 */
	public WK_RB_SelField_ArtikelBezKunde() {
		super();

		String _sqlAVV = " || ' AVV: ' || " + "     ( " + "     SELECT " + "      JT_EAK_BRANCHE.KEY_BRANCHE||'-'|| "
				+ "      JT_EAK_GRUPPE.KEY_GRUPPE||'-'|| " + "      JT_EAK_CODE.KEY_CODE||' '|| "
				+ "      TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ') " + "   FROM " + "     JT_EAK_CODE "
				+ "     LEFT OUTER JOIN JT_EAK_GRUPPE ON (JT_EAK_CODE.ID_EAK_GRUPPE          = JT_EAK_GRUPPE.ID_EAK_GRUPPE) "
				+ "     LEFT OUTER JOIN JT_EAK_BRANCHE ON (JT_EAK_GRUPPE.ID_EAK_BRANCHE   = JT_EAK_BRANCHE.ID_EAK_BRANCHE) "
				+ "   WHERE " + "     JT_EAK_CODE.ID_EAK_CODE=nvl( "
				+ "                                 (SELECT MAX(ABL1.ID_EAK_CODE) FROM JT_ARTIKELBEZ_LIEF ABL1 "
				+ "                                          	WHERE ABL1.ID_ADRESSE          = A.ID_ADRESSE "
				+ "                                          	AND ABL1.ID_ARTIKEL_BEZ        = B.ID_ARTIKEL_BEZ "
				// PARAM: '#EKVK#'
				+ "                                          	AND ABL1.ARTBEZ_TYP            =  nvl(?,'-')) "
				+ "                                  , 												"
				// Param: '#EKVK#'
				+ "                                  ( SELECT CASE WHEN 'EK'= nvl( ? ,'-') "
				+ "                                                        THEN   A1.ID_EAK_CODE "
				+ "                                                        ELSE   A1.ID_EAK_CODE_EX_MANDANT "
				+ "                                                        END "
				+ "                                                  FROM JT_ARTIKEL A1 "
				+ "                                                  INNER JOIN JT_ARTIKEL_BEZ B1 ON A1.ID_ARTIKEL = B1.ID_ARTIKEL "
				+ "                                                  WHERE B1.ID_ARTIKEL_BEZ = B.ID_ARTIKEL_BEZ "
				+ "                                           ) " + "                                 ) " + "     ) ";

		_sqlBase = "select AR.ANR1 || '-' || B.ANR2 || ' ' || B.ARTBEZ1 || NVL2(B.ARTBEZ2, ' ' || B.ARTBEZ2, '')  || ' (' || to_char(B.ID_ARTIKEL_BEZ) || ')' "
				+ _sqlAVV + " , " + " B.ID_ARTIKEL_BEZ FROM " + _TAB.adresse.fullTableName() + " A " 
				+ " INNER JOIN " + _TAB.artikelbez_lief.fullTableName() + " L  ON A.ID_ADRESSE = L.ID_ADRESSE " 
				+ " INNER JOIN " + _TAB.artikel_bez.fullTableName() + " B  ON L.ID_ARTIKEL_BEZ = B.ID_ARTIKEL_BEZ " 
				+ " INNER JOIN " + _TAB.artikel.fullTableName() + " AR ON B.ID_ARTIKEL = AR.ID_ARTIKEL "
				// Param: + bibALL.get_ID_MANDANT()
				+ " WHERE A.ID_MANDANT =  ? "
				// Param : '#EKVK#'
				+ " AND L.ARTBEZ_TYP = nvl(?,'-')"
				// Param : #ID#
				+ " AND A.ID_ADRESSE = nvl(?,-1)" 
				+ " AND A.ADRESSTYP = 1 " 
				+ " AND nvl(AR.AKTIV,'N') = 'Y' "
				+ " AND nvl(B.AKTIV,'N') = 'Y' " 
				+ " " 
				+ " UNION " 
				+ " "
				+ " SELECT AR.ANR1 || '-' || B.ANR2 || ' ' || B.ARTBEZ1 || NVL2(B.ARTBEZ2, ' ' || B.ARTBEZ2, '')  || ' (' || to_char(B.ID_ARTIKEL_BEZ) || ')' "
				+ _sqlAVV + " , " + " B.ID_ARTIKEL_BEZ " + " FROM  " + _TAB.adresse.fullTableName() + " AL "
				+ " INNER JOIN " + _TAB.lieferadresse.fullTableName()
				+ " LA 	ON AL.ID_ADRESSE = LA.ID_ADRESSE_LIEFER " 
				+ " INNER JOIN "  + _TAB.adresse.fullTableName() + " A 	ON A.ID_ADRESSE = LA.ID_ADRESSE_BASIS " 
				+ " INNER JOIN " + _TAB.artikelbez_lief.fullTableName() + " L 	ON A.ID_ADRESSE = L.ID_ADRESSE " 
				+ " INNER JOIN " + _TAB.artikel_bez.fullTableName() + " B  	ON L.ID_ARTIKEL_BEZ = B.ID_ARTIKEL_BEZ " 
				+ " INNER JOIN " + _TAB.artikel.fullTableName() + " AR  ON B.ID_ARTIKEL = AR.ID_ARTIKEL "
				// Param : + bibALL.get_ID_MANDANT()
				+ " WHERE A.ID_MANDANT =  ? "
				// Param : '#EKVK#'
				+ " AND L.ARTBEZ_TYP = nvl(?,'-') "
				// Param :#ID#
				+ " AND AL.ID_ADRESSE = nvl(?,-1) " + " AND AL.ADRESSTYP = 5 " + " AND nvl(AR.AKTIV,'N') = 'Y' "
				+ " AND nvl(B.AKTIV,'N') = 'Y' " + " ORDER BY 1 ";

		refreshData("", false);

	}

	public WK_RB_SelField_ArtikelBezKunde refreshData(String ID_Adresse, boolean IstLieferant) {
		_id_adresse = ID_Adresse;
		_bIstLieferant = IstLieferant;

		// mögliche formatierte ID korrigieren
		String[][] cArrDaten = {};
		
		if (S.isFull( ID_Adresse) ) {
			String _isLieferant = IstLieferant ? "EK" : "VK";
	
			MyLong l = new MyLong(ID_Adresse);
	
			SqlStringExtended sqlExt = new SqlStringExtended(_sqlBase)._addParameters(new VEK<ParamDataObject>()
					._a(new Param_String("", _isLieferant))._a(new Param_String("", _isLieferant))
					._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())))._a(new Param_String("", _isLieferant))
					._a(new Param_Long("", l.get_oLong()))._a(new Param_String("", _isLieferant))
					._a(new Param_String("", _isLieferant))._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())))
					._a(new Param_String("", _isLieferant))._a(new Param_Long("", l.get_oLong())));
	
			cArrDaten = bibDB.EinzelAbfrageInArray(sqlExt, (String) null);
		}
		
		try {
			if (cArrDaten.length > 0) {
				cArrDaten = bibARR.add_emtpy_db_value_inFront(cArrDaten);
			} else {
				cArrDaten = new String[][] { { "-", "" } };
			}

			// immer ein leeres davor
			_clear();
			_populate(cArrDaten);
			this._setActiveOrFirstMaskVal("");
		} catch (myException e) {
			e.printStackTrace();
		}

		// den Wert setzen, falls er vorhanden ist..
		this.setIdArtikelBez(_id_artikel_bez);
		
		return this;
	}
	
	
	public WK_RB_SelField_ArtikelBezKunde setIdArtikelBez(String idArtikelBez) {
		try {
			this._setActiveDBVal(idArtikelBez);
		} catch (myException e) {
		
		}
		return this;
		
	}
	

	/*
	 * 
	 * Hier Artikel nur merken und später explizit setzen wegen Beziehung zu suche des Artikels
	 * 
	 * 
	 * @see
	 * panter.gmbh.Echo2.RB.COMP.RB_selField#rb_Datacontent_to_Component(panter.gmbh
	 * .Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (_bLinked) {
			if (dataObject.get_RecORD() != null) {
				Rec22 r = (Rec22) dataObject;
				_id_artikel_bez = r.get_ufs_dbVal(WIEGEKARTE.id_artikel_bez);
				_id_artikel = r.get_ufs_dbVal(WIEGEKARTE.id_artikel_sorte);

				if (r == null || r.is_newRecordSet()) {
					this.rb_set_db_value_manual("");
				} else {
					this.rb_set_db_value_manual(_id_artikel_bez);
				}
			}
		} else {
			clearData();
		}
	}

	
	/**
	 * Nur wenn aktiv verlinkt, das setzen des Wertes zulassen
	 */
	@Override
	public void rb_set_db_value_manual(String dbValFormated) throws myException {
		if (_bLinked) {
			if (S.isFull(dbValFormated)) {
//				this._setActiveDBVal(dbValFormated);
				super.rb_set_db_value_manual(dbValFormated);
			} else {
				super.rb_set_db_value_manual("");
				clearData();
			}
		} else {
			super.rb_set_db_value_manual("");
			clearData();
		}
	}

	
	/**
	 * Wert muss von Hand mit den Funktionen _get_idArtikel() und
	 * _get_idArtikelBez() geholt werden..
	 */
	@Override
	public String rb_readValue_4_dataobject() {
		return null;

	}

	/**
	 * Löschen der hinterlegten Artikelnummern
	 * 
	 * @author manfred
	 * @date 10.06.2020
	 *
	 */
	public void clearData() {
		_id_artikel = null;
		_id_artikel_bez = null;
		_id_adresse  = null;
		_bIstLieferant = null;
	}

	/**
	 * Selektfield deaktivieren
	 * 
	 * @author manfred
	 * @date 28.05.2020
	 *
	 * @param _bActive
	 * @return
	 */
	public WK_RB_SelField_ArtikelBezKunde _setActive(boolean _bActive) {
		if (!_bActive) {
			try {
				this.rb_set_db_value_manual(null);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		this._bLinked = _bActive;
		return this;
	}

	public String _get_idArtikelBez() {
		if (_bLinked) {
			_id_artikel_bez = getVCompleteVisibleDbVals().get(getSelectedIndex());
			return _id_artikel_bez;
		} else {
			return null;
		}
	}

	public String _get_idArtikel() {
		if (_bLinked) {
			readIdArtikel_From_IdArtbez();
			return _id_artikel;
		} else {
			return null;
		}
	}
	
	/**
	 * gibt die zugrunde liegende IDAdresse zurück
	 * @author manfred
	 * @date 22.06.2020
	 *
	 * @return
	 */
	public String _get_IdAdresse() {
		if (_bLinked) {
			return _id_adresse;
		} else {
			return null;
		}
	}
	
	/**
	 * gibt zurück, ob Lieferant oder Abnehmer oder Leer (null)
	 * @author manfred
	 * @date 22.06.2020
	 *
	 * @return
	 */
	public Boolean _get_IstLieferant() {
		return _bIstLieferant;
	}
	

	private void readIdArtikel_From_IdArtbez() {
		_id_artikel = null;
		this._get_idArtikelBez();
		
		if (_id_artikel_bez != null) {

			Rec20_artikel_bez a;
			try {
				a = new Rec20_artikel_bez()._fill_id(_id_artikel_bez);
				_id_artikel = a.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel);
			} catch (myException e) {
				// kein Artikel gefunden
			}
		}

	}

}
