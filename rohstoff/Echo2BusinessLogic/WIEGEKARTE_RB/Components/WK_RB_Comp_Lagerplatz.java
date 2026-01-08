/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 18.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_FieldContainerComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGERPLATZ;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

/**
 * 
 * @author manfred
 * @date 07.04.2020
 *
 */
public  class WK_RB_Comp_Lagerplatz extends E2_Grid implements IF_RB_Component_Savable, IF_FieldContainerComponent, IF_HasChangeListeners<WK_RB_Comp_Lagerplatz>{

	private RB_TransportHashMap  _tpHashMap = null;
	
	private WK_RB_SelField_Lagerplatz   _selIDLagerplatzRoot = null;
	private WK_RB_SelField_Lagerplatz   _selIDLagerplatzDetail = null;
	private ENUM_Gueterkategorie        _gueterkategorie = null;
	
	private String						_idLagerplatz = null;
	private String						_idLagerplatzRoot = null;
	
		
	// die Changelisteners für diese Komponente, muss aufgerufen werden, wenn die Searchfield-Komponente einen Change-Event bekommt.
	private VEK<IF_ExecuterOnComponentV2<WK_RB_Comp_Lagerplatz>>    changeListeners = new   VEK<>();
	
	
	// Sammlung der Komponenten für eine einfach Ausführung von Methoden die auf alle Komponenten angewendet werden sollen. 
	private VEK<MyE2IF__Component> _vComps_Formatter = new VEK<MyE2IF__Component>();
	
	private RB_KF _RB_Key = null;
	
	
	/**
	 *
	 * @author manfred
	 * @date 20.03.2020
	 *
	 * @param Waegung_pos
	 * @throws myException 
	 */
	public WK_RB_Comp_Lagerplatz(RB_TransportHashMap  p_tpHashMap, ENUM_Gueterkategorie kategorie ) throws myException {
		_tpHashMap = p_tpHashMap;
		_gueterkategorie = kategorie;
		if (_gueterkategorie == null ) {
			throw new myException("Güterkategorie muss bei der definition der Selektoren angegeben sein!");
		}
		_selIDLagerplatzRoot 	= new WK_RB_SelField_Lagerplatz(_tpHashMap, kategorie,1);
		_selIDLagerplatzDetail 	= new WK_RB_SelField_Lagerplatz(_tpHashMap, kategorie,-1).setParentField(_selIDLagerplatzRoot);
		
		_init_gui();
	}

	
	protected MyE2_MessageVector fillMaskComponents(Rec22 r) throws myException {
		MyE2_MessageVector mv = bibMSG.getNewMV();

		_idLagerplatz = null;
		_idLagerplatzRoot = null;
		

		if (_gueterkategorie.equals(ENUM_Gueterkategorie.SCHUETTGUT)) {
			_idLagerplatz   	= r.get_ufs_dbVal(WIEGEKARTE.id_lagerplatz_schuett);
		} else {
			_idLagerplatz   	= r.get_ufs_dbVal(WIEGEKARTE.id_lagerplatz_absetz);
		}
		
		
		// Lagerplatz-ids Lesen
		if (_idLagerplatz != null ) {
			RecList_Lagerplatz rlPlatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSql_Lagerplatz_by_ID(_idLagerplatz));
			Rec22 rec = null;
			if (rlPlatz.size() > 0) {
				rec = rlPlatz.get(0);
				_idLagerplatzRoot = rec.getDbValueUnFormated(RecList_Lagerplatz.FIELD_ROOT);
				// wenn _idLagerplatz == _idLagerplatzRoot, dann nur ROOT setzen
				if (_idLagerplatz.equals(_idLagerplatzRoot)) {
					_idLagerplatz = null;
				}
			}
		} else {
			_selIDLagerplatzRoot.setSelectedIndex(0);
			_selIDLagerplatzDetail.setSelectedIndex(0);
		}


		// 1. Dropdown initialisieren	
		_selIDLagerplatzRoot.refreshData();
		
		_selIDLagerplatzRoot.rb_set_db_value_manual(_idLagerplatzRoot);
		_selIDLagerplatzDetail.refreshData();
		_selIDLagerplatzDetail.rb_set_db_value_manual(_idLagerplatz);
		
		
		return mv;
	};

	
	
	private void _init_gui() {
		
		// alle IF_
		_vComps_Formatter
					._a(_selIDLagerplatzDetail)
					._a(_selIDLagerplatzRoot)
					;

		this._clear();
		
		this
		._setSize(300,500)

		// 1. Zeile
		._a(_selIDLagerplatzRoot,new RB_gld()._ins(0, 0, 2, 0)._left_mid())
		._a(_selIDLagerplatzDetail,new RB_gld()._ins(0, 0, 2, 0)._left_mid())
		;
		
	}
	
	
	private void clearMaskComponents() {
		_selIDLagerplatzRoot.setSelectedIndex(0);
		_selIDLagerplatzDetail.setSelectedIndex(0);
		_idLagerplatz = null;
		_idLagerplatzRoot = null;
	}
	
	
	/**
	 * Setzt die Werte der Fuhre zurück
	 * 
	 * @author manfred
	 * @date 31.07.2020
	 *
	 */
	public WK_RB_Comp_Lagerplatz _clearLagerplatzData() {
		this.clearMaskComponents();
		return this;
	}
	

	@Override
	public void set_Alignment(Alignment align) throws myException {
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject instanceof RB_Dataobject_V22) {
			Rec22 rec = (Rec22)dataObject;
			
			if (rec==null||rec.is_newRecordSet()) {
				clearMaskComponents();
			} else {
				this.fillMaskComponents(rec);
				this.mark_Disabled();
			}
		} else {
			throw new myException("Error: Only Rec22-types are allowed");
		}
	}
	
	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		MyLong l = new MyLong(valueFormated);
		clearMaskComponents();

		if (l.isOK()) {
			RecDOWiegekarte recWK = new RecDOWiegekarte(_tpHashMap.getLastMaskLoadStatus())._fill_id(l.get_lValue());

			if (recWK != null) {

				MyE2_MessageVector mv = fillMaskComponents(recWK);
				
				if (mv.get_bHasAlarms()) {
					for (MyE2_Message m : mv) {
						bibMSG.MV()._addWarn(m.get_cMessage());
					}
				}
			}
		} else {
			mark_Disabled();
		}
	}

	
	@Override
	public RB_KF rb_KF() throws myException {
		return _RB_Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		_RB_Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return vVALIDATORS_4_INPUT;
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		_selIDLagerplatzDetail.set_bEnabled_For_Edit(enabled);
		_selIDLagerplatzRoot.set_bEnabled_For_Edit(enabled);
	}

	
	
	
	@Override
	public void mark_Neutral()  {
		for (MyE2IF__Component o: _vComps_Formatter) {
			try {
				o.set_bEnabled_For_Edit(true);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void mark_Disabled() {
		for (MyE2IF__Component o: _vComps_Formatter) {
			try {
				o.set_bEnabled_For_Edit(false);
			} catch (myException e) {
				e.printStackTrace();
			}
		}

	}



	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_clearChangeListener()
	 */
	@Override
	public WK_RB_Comp_Lagerplatz _clearChangeListener() {
		this.changeListeners._clear();
		return this;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_Comp_Lagerplatz _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Comp_Lagerplatz> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		String idLagerplatzRoot = bibALL.convertID2FormattedID(_selIDLagerplatzRoot.get_selected_dbVal());
		String idLagerplatz = bibALL.convertID2FormattedID(_selIDLagerplatzDetail.get_selected_dbVal());
		
		return (S.isEmpty(idLagerplatz) ? idLagerplatzRoot : idLagerplatz);
	}

	
	
	/**
	 * Private ActionAgent: ruft die in diese Komponente übergebenen Change-Listener auf, wenn er (hier von der eingebetteten Komponente)
	 * aufgerufen wird
	 * @author manfred
	 * @date 07.05.2020
	 *
	 */
	private class ActionOnChangeListeners extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_Comp_Lagerplatz> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_Comp_Lagerplatz.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}
	
	
}
