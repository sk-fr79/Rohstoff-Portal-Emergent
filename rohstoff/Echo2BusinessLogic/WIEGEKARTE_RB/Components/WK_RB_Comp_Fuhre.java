/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 18.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_FieldContainerComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_Formatter;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_LIST_Selector;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

/**
 * 
 * @author manfred
 * @date 07.04.2020
 *
 */
public  class WK_RB_Comp_Fuhre extends E2_Grid implements IF_RB_Component_Savable, IF_FieldContainerComponent, IF_HasChangeListeners<WK_RB_Comp_Fuhre>{

	private RB_TransportHashMap  _tpHashMap = null;
	
	
	private RB_TextField		_tf_IDFuhre 	= null; //(RB_TextFieldReadOnly) new RB_TextFieldReadOnly()._w(80);
	private RB_TextField		_tf_IDFuhreOrt 	= null; //(RB_TextFieldReadOnly) new RB_TextFieldReadOnly()._w(80);
	
	
//	private MyE2_Button  					_bt_ClearFuhre 	= new MyE2_Button("Fuhre löschen");
	// gekapselte Suchkomponente für die Fuhre
	private WK_RB_Comp_Fuhre_SearchField 	_searchFieldFuhre 	= null;
		
	// die Changelisteners für diese Komponente, muss aufgerufen werden, wenn die Searchfield-Komponente einen Change-Event bekommt.
	private VEK<IF_ExecuterOnComponentV2<WK_RB_Comp_Fuhre>>    changeListeners = new   VEK<>();
	
	
	// Sammlung der Komponenten für eine einfach Ausführung von Methoden die auf alle Komponenten angewendet werden sollen. 
	private VEK<IF_RB_Component> _vComps_Formatter = new VEK<IF_RB_Component>();
	
	private RB_KF _RB_Key = null;
	private String _idLager = null;
	
	
	/**
	 *
	 * @author manfred
	 * @date 20.03.2020
	 *
	 * @param Waegung_pos
	 * @throws myException 
	 */
	public WK_RB_Comp_Fuhre(RB_TransportHashMap  p_tpHashMap ) throws myException {
		_tpHashMap = p_tpHashMap;
	}

	public WK_RB_Comp_Fuhre setDataFields(RB_TextField tfIDFuhre, RB_TextField tfIDFuhreOrt) {
		_tf_IDFuhre = tfIDFuhre;
		_tf_IDFuhreOrt = tfIDFuhreOrt;
		return this;
	}
	
	
	public WK_RB_Comp_Fuhre initData() throws myException {
		
		if (_tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.EDIT) 
				|| _tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.VIEW) 
				|| _tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.NEW_COPY)) {
			RB_Dataobject_V22 dataobj = (RB_Dataobject_V22) _tpHashMap.getMaskDataObjectsCollector().getVectorDataobjects().get(0);
			_idLager 	= dataobj.get_ufs_dbVal(WIEGEKARTE.id_adresse_lager);
		} else {
			
			
			
			WK_RB_LIST_Selector sel= (WK_RB_LIST_Selector) _tpHashMap.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR);
			_idLager 	= sel.get_IDLager();
		}
		
		_searchFieldFuhre = new WK_RB_Comp_Fuhre_SearchField(_tpHashMap, _tf_IDFuhre, _tf_IDFuhreOrt, _idLager, new ActionOnChangeListeners(), new ActionOnChangeListeners());
		_searchFieldFuhre.get_oTextForAnzeige().setWidth(new Extent(400));
		_searchFieldFuhre.get_oTextForAnzeige().setFont(new E2_FontPlain());
		_searchFieldFuhre._setLDC(new RB_gld()._ins(0, 0, 0, 0)._left_mid());
		
		_init_gui();
		
		return this;
	}
	
	
	
	protected MyE2_MessageVector fillMaskComponents(Rec22 r) throws myException {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		String id_fuhre 	= r.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre);
		String id_fuhre_ort = r.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort) ;
		String search = S.isFull(id_fuhre) ? id_fuhre+"-" : "";
		search += S.isFull(id_fuhre_ort) ? id_fuhre_ort : "";
		
		_searchFieldFuhre.get_oTextFieldForSearchInput().setText(search);
		_searchFieldFuhre.FillLabelWithDBQuery(search);
		
		_tf_IDFuhre._t(id_fuhre);
		_tf_IDFuhreOrt._t(id_fuhre_ort);

		return mv;
	};

	
	
	private void _init_gui() {
		
		// alle IF_
		_vComps_Formatter
					._a(_tf_IDFuhre)
					._a(_tf_IDFuhreOrt)
					;

		this._clear();
		
		this
		._setSize(80,80,700)
		// 1. Zeile
		._a(_tf_IDFuhre,new RB_gld()._ins(0, 0, 2, 0)._left_mid())
		._a(_tf_IDFuhreOrt,new RB_gld()._ins(0, 0, 2, 0)._left_mid())
		._a(_searchFieldFuhre, new RB_gld()._ins(0,0,2,0)._left_mid())
//		._a(_bt_ClearFuhre, new RB_gld()._ins(20,0,2,0)._left_mid())
		;
		
	}
	
	
	private void clearMaskComponents() {
		_tf_IDFuhre.setText("");
		_tf_IDFuhreOrt.setText("");
		_searchFieldFuhre.clean();
	}
	
	
	/**
	 * Setzt die Werte der Fuhre zurück
	 * 
	 * @author manfred
	 * @date 31.07.2020
	 *
	 */
	public WK_RB_Comp_Fuhre _clearFuhrenData() {
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
				_tf_IDFuhre._t("");
				_tf_IDFuhreOrt._t("");
			} else {
				
				this.fillMaskComponents(rec);
//				this.rb_set_db_value_manual(((RB_Dataobject_V22) dataObject).getIdLong().toString());
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
		_tf_IDFuhre.set_bEnabled_For_Edit(enabled);
		_tf_IDFuhreOrt.set_bEnabled_For_Edit(enabled);
		_searchFieldFuhre.set_bEnabled_For_Edit(enabled);
		
	}

	
	
	
	@Override
	public void mark_Neutral()  {
		for (IF_Formatter o: _vComps_Formatter) {
			try {
				o.mark_Neutral();
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void mark_Disabled() {
		for (IF_Formatter o: _vComps_Formatter) {
			try {
				o.mark_Disabled();
			} catch (myException e) {
				e.printStackTrace();
			}
		}

	}



	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_clearChangeListener()
	 */
	@Override
	public WK_RB_Comp_Fuhre _clearChangeListener() {
		this.changeListeners._clear();
		return this;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_Comp_Fuhre _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Comp_Fuhre> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() throws myException {
//		RecWiegekarte rec = (RecWiegekarte) this._tpHashMap.getMaskDataObjectsCollector().get(RecWiegekarte.key);
//		rec._setNewVal(WIEGEKARTE.id_vpos_tpa_fuhre, new MyLong(getIDFuhre()).get_oLong(), null);
//		rec._setNewVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort, new MyLong(getIDFuhreOrt()).get_oLong(), null);
		return null;
	}

	
	
	
	public String getIDFuhre () {
		return _tf_IDFuhre.getText().trim();
	}
	
	public String getIDFuhreOrt() {
		return _tf_IDFuhreOrt.getText().trim();
	}
	
	public String getIDLager() {
		return _idLager;
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
			for (IF_ExecuterOnComponentV2<WK_RB_Comp_Fuhre> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_Comp_Fuhre.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}
	
	
}
