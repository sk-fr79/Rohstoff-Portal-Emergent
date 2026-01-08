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
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_FieldContainerComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_TextFieldReadOnly;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_Formatter;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorE2String;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_WaageHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

/**
 * @author manfred
 * @date 18.03.2020
 *
 */
public  class WK_RB_Comp_Waegung extends E2_Grid implements IF_RB_Component_Savable, IF_FieldContainerComponent, IF_HasChangeListeners<WK_RB_Comp_Waegung>{

	private RB_TransportHashMap _tpHashMap = null;
	
	private Integer _waegung_pos = null;
	private boolean _bErlaubeHandwaegung = false;
	private boolean _component_enabled = false;
	
	// Tara Übernehmen bei 
	// 		WA - 1. Wägung
	//		WE - 2. Wägung
	private boolean _bErlaubeTaraUebernehmen = false;
	
	
	private RB_TextFieldReadOnly _txt_Datum = (RB_TextFieldReadOnly) new RB_TextFieldReadOnly()._w(80);
	private RB_TextFieldReadOnly _txt_Zeit = (RB_TextFieldReadOnly) new RB_TextFieldReadOnly()._w(80);
	
	private RB_TextField _txt_Gewicht 	= (RB_TextField) new RB_TextField()._w(80)._al_right();
	private RB_TextField _txt_InfoHand 	= (RB_TextField) new RB_TextField()._width(new Extent(99, Extent.PERCENT))._maxInputSize(99);
	private RB_cb		 _cb_Hand		= (RB_cb) 		 new RB_cb(new MyE2_String("Handeingabe"))._aaa(new XX_ActionAgent() {
																		@Override
																		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																			set_enabled_on_handwaegung();
																		}
																	});
	
	private MyE2_Button  _bt_Tara_lesen = (MyE2_Button)  new MyE2_Button("Tara übernehmen")
																._ri(E2_ResourceIcon.get_RI("wizard_mini.png"))
																._al_center()
																;
	
	
	private MyE2_Button  _bt_Gewicht_uebernehmen = (MyE2_Button)  new MyE2_Button("Storno-Daten lesen")
			._ri(E2_ResourceIcon.get_RI("wizard_mini.png"))
			._al_center()
			;
	
	private MyE2_Button  _bt_Gewicht_hand = (MyE2_Button)  new MyE2_Button("Handeingabe")
//			._ri(E2_ResourceIcon.get_RI("wizard_mini.png"))
			._al_center()
			;
	
	

	private MyE2_Button  _bt_Speichern = new MyE2_Button("Speichern")._ri(E2_ResourceIcon.get_RI("save.png"));
	
	private E2_PopUp_For_TextBausteine _popTextbaustein = null;
	
	// Sammlung der Komponenten für eine einfach Ausführung von Mehtoden die auf alle Komponenten angewendet werden sollen. 
	private VEK<IF_RB_Component> _vComps_Formatter = new VEK<IF_RB_Component>();
	
	
	private RecDOWaegung  _recWaegung = null;
	private RecDOWiegekarte _recWiegekarte = null;
	
	
	private WK_RB_DialogGetStornoTara _dlgStornoTara = null;
	
	
	private RB_KF _RB_Key = null;
	
	
	/**
	 * Konstruktor für die Wägung - Anzeige
	 * @author manfred
	 * @date 20.03.2020
	 *
	 * @param Waegung_pos
	 */
	public WK_RB_Comp_Waegung(int Waegung_pos, RB_TransportHashMap _pHashMap ) {
		_tpHashMap = _pHashMap;
		_waegung_pos = Waegung_pos;
		
		try {
			_bErlaubeHandwaegung = ENUM_MANDANT_DECISION.WAAGE_ERLAUBE_HANDWIEGUNG.is_YES_FromSession();
			_popTextbaustein = (E2_PopUp_For_TextBausteine) 
								new E2_PopUp_For_TextBausteine(WK_RB_CONST.KENNER_TEXTBAUSTEINE_HANDWIEGUNG)
								.setComponentToSetValueIn(_txt_InfoHand)
								.setTooltipText("Grund der Handverwiegung...")
								.setKeyInList()
								._render()
								;
			_dlgStornoTara = new WK_RB_DialogGetStornoTara( Waegung_pos, _pHashMap);
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		_init_gui();
		
	}
	
	
	
	protected MyE2_MessageVector fillMaskComponents(Rec22 r) throws myException {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		_cb_Hand._setSelected(r.get_fs_dbVal(WAEGUNG.handeingabe,"N").equalsIgnoreCase("Y"));
		_txt_Datum._t(r.get_ufs_dbVal(WAEGUNG.datum,""));
		_txt_Zeit ._t(r.get_ufs_dbVal(WAEGUNG.zeit,""));
		_txt_Gewicht._t(r.get_fs_dbVal(WAEGUNG.gewicht, ""));
		_txt_InfoHand._t(r.get_fs_dbVal(WAEGUNG.handeingabe_bem,""));
		
		return mv;
	};

	
	/** 
	 * aufbau des Grids für die Waegung
	 * @author manfred
	 * @date 19.03.2020
	 *
	 */
	private void _init_gui() {
		
		// alle IF_
		_vComps_Formatter._a(_txt_InfoHand)
					._a(_txt_Datum)
					._a(_txt_Zeit)
					._a(_txt_Gewicht)
					._a(_cb_Hand);

		if (1==0) {
			this._bo_green();
		}
		
		this
		._setSize(100,80,100,80)
		// 1. Zeile
		._a(new RB_lab(_waegung_pos.toString() + new MyE2_String(". Wägung"))._b()._fo_s_plus(3),new RB_gld()._ins(0,0,2,0))
//		._a(new MyE2_Label(_waegung_pos.toString() + new MyE2_String(". Wägung"), MyE2_Label.STYLE_TITEL_BIG()),new RB_gld()._ins(0,0,2,0))
		._a(_bt_Tara_lesen,new RB_gld()._span(2)._ins(10, 0, 2, 0))
		._a(_cb_Hand,new RB_gld()._ins(10, 0, 2, 0))
		
		// 2. Zeile
		._a(new MyE2_Label(new MyE2_String("Datum"), MyE2_Label.STYLE_NORMAL_PLAIN()),new RB_gld()._ins(0,0,2,0))
		._a(new MyE2_Label(new MyE2_String("Zeit"), MyE2_Label.STYLE_NORMAL_PLAIN()),new RB_gld()._ins(0,0,2,0) )
		._a(new MyE2_Label(new MyE2_String("Gewicht"), MyE2_Label.STYLE_NORMAL_PLAIN()),new RB_gld()._al(Alignment.ALIGN_RIGHT)._ins(20,0,0,0))
		._a(new MyE2_Label(new MyE2_String(""), MyE2_Label.STYLE_NORMAL_PLAIN()),new RB_gld()._ins(0,0,2,0))
		// 3.Zeile
		._a(_txt_Datum,new RB_gld()._ins(0,0,2,0))
		._a(_txt_Zeit,new RB_gld()._ins(0,0,2,0) )
		._a(_txt_Gewicht,new RB_gld()._al(Alignment.ALIGN_RIGHT)._ins(0,0,0,0))
		._a(_bt_Speichern,new RB_gld()._ins(10,0,2,0))
		// 4.Zeile
		._a(_txt_InfoHand,new RB_gld()._ins(0,0,1,0)._span(3))
		._a(_popTextbaustein,new RB_gld()._ins(10,0,2,0))
		;
		
		// 5. Zeile, wenn Storno-Nachfolger
		
		this
//		._a(new MyE2_Label(new MyE2_String("Aus Storno übernehmen: "), MyE2_Label.STYLE_SMALL_PLAIN()),new RB_gld()._ins(0,0,2,0))
		._a(_bt_Gewicht_uebernehmen,new RB_gld()._span(2)._ins(0, 0, 2, 0))
		._a(_bt_Gewicht_hand,new RB_gld()._ins(0,0,2,0))
//		._a(new MyE2_Label(""))
		;

		
		_bt_Tara_lesen._aaa(new ActionOnTaraUebernehmenIntern());
		_cb_Hand._aaa(new ActionOnHandwaegungLoeschen());
		
		_bt_Gewicht_uebernehmen._aaa(new ActionOnGewichtUebernehmenIntern());
		_bt_Gewicht_hand._aaa(new ActionOnHandIntern());
		
		
	}
	
	
	private void clearMaskComponents() {
		_cb_Hand.setSelected(false);;
		_txt_Datum.setText("");
		_txt_Zeit.setText("");
		_txt_Gewicht.setText("");
		_txt_InfoHand.setText("");
	}
	
	

	@Override
	public void set_Alignment(Alignment align) throws myException {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject instanceof RB_Dataobject_V22) {
			Rec22 r = (Rec22)dataObject;
			if (r==null||r.is_newRecordSet()) {
				this.rb_set_db_value_manual("");
			} else {
				_recWiegekarte = (RecDOWiegekarte) dataObject;
				
				this.rb_set_db_value_manual(((RB_Dataobject_V22) dataObject).getIdLong().toString());
				this.mark_Disabled();
			}
		} else {
			throw new myException("Error: Only Rec22-types are allowed");
		}
	}

	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		MyLong l = new MyLong(valueFormated);
		if (l.isOK()) {
			
			if (_waegung_pos == 1 ) { 
			_recWaegung = new RecDOWaegung1(_tpHashMap.getLastMaskLoadStatus())._fill_from_wiegekarte(l.get_lValue());
			} else {
				_recWaegung = new RecDOWaegung2(_tpHashMap.getLastMaskLoadStatus())._fill_from_wiegekarte(l.get_lValue());
			}
					
			
			if (_recWaegung == null) {
				clearMaskComponents();
				bibMSG.MV()._addWarn(S.ms("Fehler beim Lesen der Waegung!"));
			} else {
				MyE2_MessageVector mv = fillMaskComponents(_recWaegung);
				if (mv.get_bHasAlarms()) {
					for (MyE2_Message m: mv) {
						bibMSG.MV()._addWarn(m.get_cMessage());
					}
				} else {
					mark_Disabled();
				}
			}
		} else {
			clearMaskComponents();
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
		boolean bEnabled = true;
		// wenn rec_waegung schon erzeugt ist, dann immer disabled!!
		if (_recWaegung != null && _recWaegung.is_ExistingRecord()) {
			bEnabled = false;
		} else {
			bEnabled &= enabled; 
		}
		super.set_bEnabled_For_Edit(bEnabled);
		_txt_Datum.set_bEnabled_For_Edit(false);
		_txt_Zeit.set_bEnabled_For_Edit(false);
		
		_cb_Hand.set_bEnabled_For_Edit(bEnabled && _bErlaubeHandwaegung);
		
		_component_enabled = bEnabled;
		
		set_enabled_on_handwaegung();
		
		// prüfen, ob der Button angezeigt werden muss
		showReadStornoDaten();
		
	}

	
	/**
	 * Setzt die Zustände der Elemente bei Handwägungen, 
	 * prüft, ob man Tara übernehmen kann.
	 * @author manfred
	 * @date 20.05.2021
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_Comp_Waegung set_enabled_on_handwaegung() throws myException {
		boolean bIstHandwaegung =  _cb_Hand.isEnabled() && _cb_Hand.isSelected();
		boolean bWE = true;

		// abhängig ob WE oder WA gewählt...
		RB_MaskController con = new RB_MaskController(this);
		WK_RB_WeWa wewa = (WK_RB_WeWa) con.get_comp(RecDOWiegekarte.key,WIEGEKARTE.ist_lieferant );
		if (wewa.isValid()) {

			if (_recWiegekarte != null) {
				bWE = _recWiegekarte.getUfs(WIEGEKARTE.ist_lieferant,"N").equalsIgnoreCase("Y") ;
			} else {
				bWE = wewa.isLieferant();
			}
			
			_bErlaubeTaraUebernehmen 	= 	( bWE && _waegung_pos.intValue() == 2);
			_bErlaubeTaraUebernehmen 	|= 	(!bWE && _waegung_pos.intValue() == 1);

		}  else {
			_bErlaubeTaraUebernehmen = false;
		}
		
		
		
		_cb_Hand.set_bEnabled_For_Edit(bIstHandwaegung || (_component_enabled && _bErlaubeHandwaegung));
		
		_bt_Tara_lesen.set_bEnabled_For_Edit(_component_enabled && _bErlaubeTaraUebernehmen);
		_bt_Gewicht_uebernehmen.set_bEnabled_For_Edit(_component_enabled);
		_bt_Gewicht_hand.set_bEnabled_For_Edit(_component_enabled);
		
		_bt_Speichern.set_bEnabled_For_Edit(_component_enabled && bIstHandwaegung);
		_popTextbaustein.set_bEnabled_For_Edit(_component_enabled && _bErlaubeHandwaegung && bIstHandwaegung);

		_txt_Gewicht.set_bEnabled_For_Edit(_component_enabled && _bErlaubeHandwaegung && bIstHandwaegung);
		_txt_InfoHand.set_bEnabled_For_Edit(_component_enabled && _bErlaubeHandwaegung && bIstHandwaegung);
		return this;
	}
	
	
	

	
	private void showReadStornoDaten() {
		boolean bVisible = false;
		if (_recWiegekarte != null) {
			try {
				String Typ = _recWiegekarte.getUfs(WIEGEKARTE.typ_wiegekarte);
				if (Typ != null && Typ.equals(WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR.db_val())) {
					bVisible = true;
				}
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		_bt_Gewicht_uebernehmen.setVisible(bVisible);
		_bt_Gewicht_hand.setVisible(bVisible);
		
	}
	
	
	
	/**
	 * Rückgabe stumm geschaltet, wird von aussen gesteuert.
	 */
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return null;
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
	
	
	/**
	 * gibt den Waegungs-Record zurück
	 * @author manfred
	 * @date 09.07.2020
	 *
	 * @return
	 */
	public RecDOWaegung _getRecWaegung() {
		return _recWaegung;
	}
	

	public WK_RB_Comp_Waegung _setRecWaegung(RecDOWaegung _recWaegung) {
		this._recWaegung = _recWaegung;
		return this;
	}
	

	/**
	 * Fügt einen Action-Agent zum Button Tara-Übernehmen ein
	 * (in front)
	 * @author manfred
	 * @date 13.07.2020
	 *
	 * @param oAgent
	 * @return
	 */
	public WK_RB_Comp_Waegung _addListenerTaraUebernehmen(XX_ActionAgent oAgent) {
		
		this._bt_Tara_lesen.add_oActionAgent(oAgent, true);
		
		return this;
		
	}
	
	
	
public WK_RB_Comp_Waegung _addListenerStornoUebernehmen(XX_ActionAgent oAgent) {
		
//		this._dlgStornoTara._setActionAgentStart(oAgent);
		this._bt_Gewicht_uebernehmen.add_oActionAgent(oAgent,true);
		return this;
		
	}
	
	
	
	/**
	 * Setzt den Action-Agent zum Button Speichern ein
	 * (in front)
	 * @author manfred
	 * @date 13.07.2020
	 *
	 * @param oAgent
	 * @return
	 */
	public WK_RB_Comp_Waegung _addListenerSave(XX_ActionAgent oAgent) {
//		this._bt_Speichern.remove_AllActionAgents();
		this._bt_Speichern.add_oActionAgent(oAgent, true);
		
		return this;
		
	}
	
	
	/**
	 * gibt die Wägungs-Position zurück
	 * @author manfred
	 * @date 15.07.2020
	 *
	 * @return
	 */
	public int _getWaegungPos() {
		return _waegung_pos;
	}






	public boolean is_bErlaubeHandwaegung() {
		return _bErlaubeHandwaegung;
	}



	public boolean is_component_enabled() {
		return _component_enabled;
	}



	public RB_TextFieldReadOnly get_txt_Datum() {
		return _txt_Datum;
	}



	public RB_TextFieldReadOnly get_txt_Zeit() {
		return _txt_Zeit;
	}



	public RB_TextField get_txt_Gewicht() {
		return _txt_Gewicht;
	}



	public RB_TextField get_txt_InfoHand() {
		return _txt_InfoHand;
	}



	public RB_cb get_cb_Hand() {
		return _cb_Hand;
	}

	public boolean _isHandwaegung() {
		return _cb_Hand.isSelected();
	}


	public MyE2_Button get_bt_Tara_lesen() {
		return _bt_Tara_lesen;
	}



	public MyE2_Button get_bt_Speichern() {
		return _bt_Speichern;
	}



	public E2_PopUp_For_TextBausteine get_popTextbaustein() {
		return _popTextbaustein;
	}
	
	
	

	private VEK<IF_ExecuterOnComponentV2<WK_RB_Comp_Waegung>>    changeListeners = new   VEK<>();

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_clearChangeListener()
	 */
	@Override
	public WK_RB_Comp_Waegung _clearChangeListener() {
		this.changeListeners._clear();
		return this;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_Comp_Waegung _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Comp_Waegung> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}	
	
	
	/**
	 * löscht die Handwägung
	 * @author manfred
	 * @date 26.01.2021
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_Comp_Waegung _clearHandwaegung() throws myException {
		_cb_Hand.setSelected(false);
		_txt_Gewicht.setText("");
		_txt_InfoHand.setText("");
		
		set_enabled_on_handwaegung();
		
		WK_RB_MC_WaageHandling oMaskControllerWaageHandling = new WK_RB_MC_WaageHandling(WK_RB_Comp_Waegung.this);
		
		oMaskControllerWaageHandling
				.stopListening()
				.clearWaageDisplay()
				.clearWaageUser();
		
		return this;
	}
	
	
	public WK_RB_DialogGetStornoTara _getDialogStorno() {
		return _dlgStornoTara;
	}
	
	
	
	/**
	 * Weiterleitung der Actions
	 * @author manfred
	 * @date 19.05.2020
	 *
	 */
	private class ActionOnChangeListeners extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_Comp_Waegung> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_Comp_Waegung.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}
	
	
	
	/**
	 * wenn tara übernehmen gedrückt wird, dann werden intern die Feldstati gesetzt:
	 * Handeingabe enabled
	 * Handeingabe gesetzt
	 * Speichern-Button enabled
	 * @author manfred
	 * @date 10.08.2020
	 *
	 */
	private class ActionOnTaraUebernehmenIntern extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_cb_Hand.set_bEnabled_For_Edit(true);
			_cb_Hand.setSelected(true);

			WK_RB_MC_WaageHandling oMaskControllerWaageHandling = new WK_RB_MC_WaageHandling(WK_RB_Comp_Waegung.this);
			oMaskControllerWaageHandling
				.stopListening()
				.clearWaageDisplay()
				.clearWaageUser();
			
			set_enabled_on_handwaegung();
		}
	}
	
	
	
	private class ActionOnGewichtUebernehmenIntern extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_bErlaubeHandwaegung = ENUM_MANDANT_DECISION.WAAGE_ERLAUBE_HANDWIEGUNG.is_YES_FromSession();
			_cb_Hand.set_bEnabled_For_Edit(true);
			_cb_Hand.setSelected(true);
			
			set_enabled_on_handwaegung();
		}
		
	}
	
	private class ActionOnHandIntern extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_bErlaubeHandwaegung = true;
			_cb_Hand.set_bEnabled_For_Edit(true);
			_cb_Hand.setSelected(true);
			
			set_enabled_on_handwaegung();
		}
		
	}	
	
	/**
	 * wenn Taraübernahme gelöscht werden soll (Handeingabe abwählen)
	 * alles zurücksetzen
	 * @author manfred
	 * @date 10.08.2020
	 *
	 */
	private class ActionOnHandwaegungLoeschen extends XX_ActionAgent {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (!_cb_Hand.isSelected()) {
				_clearHandwaegung();
			}
		}
	}
	
}
