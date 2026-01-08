/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 20.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_SETTINGS;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_USER;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_LIST_Selector;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_WaageSettings;


/**
 * @author manfred
 * @date 20.04.2020
 * Fasst alle WK_RB_Row_Waagedisplay zusammen
 *  
 */
public class WK_RB_Waagedisplay extends E2_Grid implements IF_RB_Component, IF_HasChangeListeners<WK_RB_Waagedisplay>{
	private RB_KF 								_RB_Key = null;
	private RB_TransportHashMap  				_tpHashMap 			= null;

	private ArrayList<WK_RB_Row_Waagedisplay> 	_listWaageDisplays 	= new ArrayList<WK_RB_Row_Waagedisplay>();
	private String 								_idAdresseLager 	= null;
	private	String 								_IdStandort 		= null;
	
	
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 20.04.2020
	 *
	*/
	public WK_RB_Waagedisplay(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		
		_setSize(200,800);
		_tpHashMap = p_tpHashMap;
		clearWaageData();
	}

	
	
	/**
	 * aktualisiert die Waage-Settings Daten anhand der Daten in der Transport-Hashmap
	 * @author manfred
	 * @date 28.04.2020
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_Waagedisplay refreshWaageData() throws myException {

		if (_tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.EDIT) || _tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.VIEW)) {
			RB_Dataobject_V22 dataobj = (RB_Dataobject_V22) _tpHashMap.getMaskDataObjectsCollector().getVectorDataobjects().get(0);
			_idAdresseLager = dataobj.get_ufs_dbVal(WIEGEKARTE.id_adresse_lager);
			_IdStandort 	= dataobj.get_ufs_dbVal(WIEGEKARTE.id_waage_standort);
		} else {
			WK_RB_LIST_Selector sel= (WK_RB_LIST_Selector) _tpHashMap.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR);
			_idAdresseLager = sel.get_IDLager();
			_IdStandort 	= sel.get_IDWaageStandort();
			
			_idAdresseLager = (String) _tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_LAGER);
			_IdStandort 	= (String) _tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_STANDORT);
			
		}
		
		//
		initWaageSettingsData();
		return this;
	}
	
	
	/**
	 * Setzt die Waagedaten zurück
	 * @author manfred
	 * @date 28.04.2020
	 *
	 * @return
	 */
	public WK_RB_Waagedisplay clearWaageData() {
		this.removeAll();
		this._a(new MyE2_Label(new MyE2_String("Es sind keine Waage-Informationen vorhanden.")), new RB_gld()._ins(0,0,0,0)._span(2));
		return this;
	}
	
	
	
	/**
	 * setzt die Waagezeilen im Grid
	 * @author manfred
	 * @date 28.04.2020
	 *
	 * @throws myException
	 */
	private void initWaageSettingsData() throws myException {

		// der aktuelle Benutzer
		String m_IdUser = bibALL.get_ID_USER();
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		
		
		// die dem Benutzer zugeordnete Waagen / und Lager
		SEL sel = new SEL(_TAB.waage_settings)
					.FROM(_TAB.waage_settings)
					.INNERJOIN(_TAB.waage_user, WAAGE_USER.id_waage_standort, WAAGE_SETTINGS.id_waage_standort).AND(WAAGE_USER.id_mandant, WAAGE_SETTINGS.id_mandant)
					.WHERE(new vglParam(WAAGE_USER.id_user));
		vecParam._a(new Param_Long(Long.parseLong(m_IdUser)));
		
		
		if (!bibALL.isEmpty(_idAdresseLager)) {
			sel.AND(new vglParam(WAAGE_SETTINGS.fullTabName(),WAAGE_SETTINGS.id_adresse_lager));
			vecParam._a(new Param_Long(Long.parseLong(_idAdresseLager)));
		} else {
			throw new myException("Keine Waage-Lagerzuordnung vorhanden!");
		}
		
		if (!bibALL.isEmpty(_IdStandort)){
			sel.AND(new vglParam(WAAGE_SETTINGS.fullTabName(),WAAGE_SETTINGS.id_waage_standort));
			vecParam._a(new Param_Long(Long.parseLong(_IdStandort)));
		} else {
			throw new myException("Keine Waage-Standortzuordnung vorhanden!");
		}
		
		// Sortierung
		sel.ORDERUP(WAAGE_SETTINGS.sortierung);
		

		// Records lesen
		SqlStringExtended sqlext = new SqlStringExtended(sel.s())._addParameters(vecParam);
		RecList22 rlSettings = new RecList22(_TAB.waage_settings)._fill(sqlext);
		
		//
		// die Waagedaten und GUI erzeugen
		//
		this.removeAll();
		_listWaageDisplays.clear();
		
		if (rlSettings != null  && rlSettings.size() > 0 )
		{	
			for (Rec22 rec: rlSettings) {
				Rec_WaageSettings r_ws = new Rec_WaageSettings(rec);
				WK_RB_Row_Waagedisplay o= new WK_RB_Row_Waagedisplay(r_ws);
	
				// Waagedisplays merken
				_listWaageDisplays.add(o);
//				o.setActionAgent(new actionSaveWaegung(o));
	
				// Waagedisplays auf die Maske
				this._a(new MyE2_Label(new MyE2_String(r_ws.get_ufs_dbVal(WAAGE_SETTINGS.bezeichnung, "-"))),new RB_gld()._ins(0, 5, 0, 0));
				this._a(o,new RB_gld()._ins(0,5,0,0));
				
			}

		}
		else {
			this._a(new MyE2_Label(new MyE2_String("Es sind keine Waage-Informationen vorhanden.")), new RB_gld()._ins(0,0,0,0)._span(2));
		}
	}


	/**
	 * gibt die Liste der Waagedisplay-Rows zurück
	 * @author manfred
	 * @date 17.06.2020
	 *
	 * @return
	 */
	public ArrayList<WK_RB_Row_Waagedisplay> get_ListWaageDisplays() {
		return _listWaageDisplays;
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_Formatter#mark_Neutral()
	 */
	@Override
	public void mark_Neutral() throws myException {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_Formatter#set_Alignment(nextapp.echo2.app.Alignment)
	 */
	@Override
	public void set_Alignment(Alignment align) throws myException {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_clearChangeListener()
	 */
	@Override
	public WK_RB_Waagedisplay _clearChangeListener() {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_Waagedisplay _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Waagedisplay> changeListener) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		// TODO Auto-generated method stub

	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component#rb_set_db_value_manual(java.lang.String)
	 */
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		// TODO Auto-generated method stub
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component#rb_KF()
	 */
	@Override
	public RB_KF rb_KF() throws myException {
		return _RB_Key;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component#set_rb_RB_K(panter.gmbh.Echo2.RB.BASICS.RB_KF)
	 */
	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		_RB_Key = key;
		
	}


	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return vVALIDATORS_4_INPUT;
	}
	


}
