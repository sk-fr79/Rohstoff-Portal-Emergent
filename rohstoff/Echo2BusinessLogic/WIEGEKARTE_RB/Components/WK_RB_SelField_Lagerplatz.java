/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Lagerplatz;


/**
 * @author manfred
 * @date 14.04.2020
 *
 */
public class WK_RB_SelField_Lagerplatz extends RB_selField {

	

	private RB_TransportHashMap _tpHashMap = null;
	private RecList_Lagerplatz rl_Lagerplatz = null;
	private RecList_Lagerplatz rl_LagerplatzShadow = null;
	
	
	private ENUM_Gueterkategorie 	  _kategorie = null;

	private WK_RB_SelField_Lagerplatz _selFieldParent = null;
	private WK_RB_SelField_Lagerplatz _selFieldChild = null;

	//
	//  Pfad: /Halle/Box/Compartment1 
	// display_level == 1:-> /Box/Compartment1
	//
	private int _display_level = 1;
	
	
	//
	//  Pfad: 
	// /Halle 
	// /Halle/Box
	// /Halle/Box/Compartment2 
	// /Halle/Box/Compartment3 
	// /Halle2 
	// /Halle2/Box
	// /Halle2/Box/Compartment2 
	//  
	// max_select_level == 1:-> 
	// /Halle
	// /Halle2
	//
	// max_select_level = -1 -> Alle Knoten
	private int _max_select_level = -1;

	
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 14.04.2020
	 *
	 */
	public WK_RB_SelField_Lagerplatz(RB_TransportHashMap  p_tpHashMap, ENUM_Gueterkategorie kategorie, int max_level) throws myException {
		super();
		_tpHashMap = p_tpHashMap;
		_kategorie = kategorie;
		_max_select_level = max_level;
		
		refreshData(null);
		
		this._aaa(new actionRefresh());
		
	}
	
	
	/**
	 * refresht die Daten, falls es ein ParentField gibt, auf Basis der Parent-ID
	 * @author manfred
	 * @date 04.12.2020
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_SelField_Lagerplatz refreshData() throws myException {
		String _idRoot = null;
		if (_selFieldParent != null) {
			_idRoot = _selFieldParent.get_selected_dbVal();
		}

		refreshData(_idRoot);

		return this;
	}
	
	
	/**
	 * 
	 * @author manfred
	 * @date 04.12.2020
	 *
	 * @param idRoot
	 * @return
	 */
	private WK_RB_SelField_Lagerplatz refreshData( String idRoot ) {
		
		
		String [][] cArr	 		= new String[0][2];
		String [][] cArrShadow 		= new String[0][2];
		
		String id_root = idRoot;
		
		// falls id_root ein Leerstring ist, dann leere Resultset erzwingen!
		if (id_root != null && S.isEmpty(id_root)) {
			id_root = "-1";
		}
		
		try {
			if(_kategorie.equals(ENUM_Gueterkategorie.STUECKGUT)) {
				// Stückgut / Container
				if (id_root == null) {
					rl_Lagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Lagerplatz_root(true, false));
					rl_LagerplatzShadow = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Lagerplatz_root(false, true));
				} else {
					rl_Lagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Lagerplatz_unterhalb(id_root, false, true, false));
					rl_LagerplatzShadow = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Lagerplatz_unterhalb(id_root, false, false, true));
				}
			} else {
				// Schüttgut
				if (id_root == null) {
					rl_Lagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Schuettgut_root(true, false));
					rl_LagerplatzShadow = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Schuettgut_root(false, true));
				} else {
					rl_Lagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Schuettgut_unterhalb(id_root, false, true, false));
					rl_LagerplatzShadow = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Schuettgut_unterhalb(id_root, false, false, true));
				}
			}
			

			// Größe der Arrays bestimmen
			int nCount = 0;
			for (Rec22 r : rl_Lagerplatz) {
				String level = r.getDbValueUnFormated(RecList_Lagerplatz.FIELD_LEVEL);
				Long lLevel = Long.parseLong(level);
				if (_max_select_level > 0 && lLevel > _max_select_level) {
					continue;
				} else {
					nCount++;
				}
			}
			
			int nCountShadow = 0;
			for (Rec22 r : rl_LagerplatzShadow) {
				String level = r.getDbValueUnFormated(RecList_Lagerplatz.FIELD_LEVEL);
				Long lLevel = Long.parseLong(level);
				if (_max_select_level > 0 && lLevel > _max_select_level) {
					continue;
				} else {
					nCountShadow++;
				}
			}
			
			
			cArr = new String[nCount +1][2];
			int i = 0;
			cArr[i][0] = "-";
			cArr[i][1] = "";
			i++;
			for (Rec22 r : rl_Lagerplatz) {
				String path = r.getDbValueUnFormated("path");
				
				String level = r.getDbValueUnFormated(RecList_Lagerplatz.FIELD_LEVEL);
				Long lLevel = Long.parseLong(level);
				if (_max_select_level > 0 && lLevel > _max_select_level) {
					continue;
				}
				
				
				if (_display_level > 1) {
					path = path.substring(path.indexOf(RecList_Lagerplatz.PATH_DIVIDER, _display_level));
				}
				
				cArr[i][0] = path;
				cArr[i][1] = r.get_key_value();
				i++;
			}
			
			cArrShadow = new String[nCountShadow][2];
			i = 0;
			for (Rec22 r : rl_LagerplatzShadow) {

				String level = r.getDbValueUnFormated(RecList_Lagerplatz.FIELD_LEVEL);
				Long lLevel = Long.parseLong(level);
				if (_max_select_level > 0 && lLevel > _max_select_level) {
					continue;
				}
				
				
				cArrShadow[i][0] = r.getDbValueUnFormated("path");
				cArrShadow[i][1] = r.get_key_value();
				i++;
			}

			_clear();
			_populate(cArr);
			_populateShadow(cArrShadow);
			_setActiveOrFirstMaskVal("");
		
		} catch (myException e) {
			
		}
		return this;
	}

	
	/**
	 * Setzt das Parent-Selection-Field
	 * @author manfred
	 * @date 04.12.2020
	 *
	 * @param parent
	 * @return
	 */
	public WK_RB_SelField_Lagerplatz setParentField(WK_RB_SelField_Lagerplatz parent) {
		_selFieldParent = parent;
		_selFieldParent._selFieldChild = this;
		
		_display_level = _selFieldParent._display_level + 1;
		
		try {
			// Daten neu laden
			this.refreshData();
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Fehler beim ermitteln der Lagerdaten"));
		}
		return this;
	}
	
	
    public WK_RB_SelField_Lagerplatz setChildField(WK_RB_SelField_Lagerplatz child) {
    	_selFieldChild = child;
    	_selFieldChild._selFieldParent = this;
    	
    	try {
			child.refreshData();
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Fehler beim ermitteln der Lagerdaten der Detail-Liste"));
		}
    	return this;
    }
	
	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		return;
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_selField#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() {
		return "";
	}
	
	
	/**
	 * gibt den aktuellen DB-Wert zurück
	 * @author manfred
	 * @date 19.06.2020
	 *
	 * @return
	 */
	public String get_selected_dbVal() {
		return this.getVCompleteDBVals().get(this.getSelectedIndex());
	}
	
	
	private class actionRefresh extends XX_ActionAgent{

		WK_RB_SelField_Lagerplatz _oThis;
		
		/**
		 * @author manfred
		 * @date 14.12.2020
		 *
		 */
		public actionRefresh() {
			_oThis = WK_RB_SelField_Lagerplatz.this;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (_oThis._selFieldChild != null) {
				_oThis._selFieldChild.refreshData();
			}
		}
	
		
	}
	
	
}
