package panter.gmbh.Echo2.RB.COMP;

import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.event.EventListenerList;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.list.DefaultListModel;
import nextapp.echo2.app.list.ListCellRenderer;
import nextapp.echo2.app.list.StyledListCell;
import panter.gmbh.BasicInterfaces.IF_CreateStringFromRec;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForInternalActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_PassiveActionExecuter;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_PROGRAMM_WIDE;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.BasicInterfaces.IF_Dimension;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.Factorys.StyleFactory_SelectField;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

public class RB_selField extends SelectField implements IF_RB_Component,
														IF_RB_Component_Savable,
														E2_IF_Handles_ActionAgents, 
														IF_FontandText<RB_selField>,
														IF_Dimension<RB_selField>,
														IF_LayoutData<RB_selField>,
														IF__Reflections,
														E2_IF_Copy
														{

	private MyE2EXT__Component  ext = new MyE2EXT__Component(this);
	
	/*
	 * die werte-paare werden in arrays[2] gespeichert, 0=Sichtbar im DropDown, 1 = der Wert fuer die datenbank
	 */
	private ownHashMap hm_content_real = new ownHashMap();
	private ownHashMap hm_content_shadow = new ownHashMap();
	
	//falls ein shadow-wert zugeordnet wird, diesen hier speichern
	private ownHashMap hm_content_shadowActive = new ownHashMap();
	
	private boolean    forceEmptyValInFront = true;
	
	
	/**
	 * erweiterterung RB_selField mit merkfunktion der zuletzt eingestellten werte (um eine zuruecksetzung auf den
	 * vorgaengerwert zu ermoeglichen) - MEMORY-effekt
	 */
	private String         lastSetValue = null;
	private boolean        isMemoryEffectActive = false;

	
	
	
	/**
	 * 
	 */
	public RB_selField() {
		super();
		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setCellRenderer(new ownListCellRenderer());
	}

	
	public RB_selField _populate(Vector<String> values) throws myException {
		this.hm_content_real._put(values);
		this._render();
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 10.11.2020
	 *
	 * @param values (WICHTIG: key=DBValue, value=MaskValue)
	 * @return
	 * @throws myException
	 */
	public RB_selField _populate(HashMap<String,String> values) throws myException {
		this.hm_content_real.putAll(values);
		this._render();
		return this;
	}
	
	
	public RB_selField _populate(String[] values) throws myException {
		this.hm_content_real._put(values);
		this._render();
		return this;
	}
	
	//IF_enumForDb<EnTpVerantwortung>
	
	
	public RB_selField _setStyleFlat() {
		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true, true, false));
		return this;
	}
	
	
	public RB_selField _populate(IF_enumForDb enum4db, boolean emptyValInFront) throws myException {
		
		String[][] vals = enum4db.getArray4Selfield(emptyValInFront);
		
		this.hm_content_real._put(vals);
		this._render();
		return this;
	} 
	
	
	public RB_selField _populate(IF_enum_4_db enum4db, boolean emptyValInFront) throws myException {
		
		String[][] vals = enum4db.get_dd_Array(emptyValInFront);
		
		this.hm_content_real._put(vals);
		this._render();
		return this;
	} 
	
	
	public RB_selField _populate(RecList20 rl, IF_Field field4MaskStrings, IF_Field field4DBValues, boolean bEmptyValInFront) throws myException {

		VEK<IF_Field>  vF = new VEK<IF_Field>()._a(rl.get_tab().get_fields());
		if (vF.contains(field4MaskStrings) && vF.contains(field4DBValues)) {
			
			int iZeilen = rl.size();
			if (bEmptyValInFront) {
				iZeilen++;
			}
			
			String[][] vals = new String[iZeilen][2];
			
			int i=0;
			if (bEmptyValInFront) {
				vals[i][0]="-"; vals[i][1]="";
				i++;
			}
			
			for (Rec20 r: rl) {
				vals[i][0]=r.get_fs_dbVal(field4MaskStrings, ""); vals[i][1]=r.get_fs_dbVal(field4DBValues, "");
				i++;
			}
			
			this._populate(vals);
		} else {
			throw new myException(this, "Reclist20 doesnt contain field: "+field4MaskStrings.fieldName()+" / "+field4DBValues.fieldName());
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param rl
	 * @param field4MaskStrings
	 * @param field4DBValues
	 * @param bEmptyValInFront
	 * @return
	 * @throws myException
	 */
	public RB_selField _populate(RecList21 rl, IF_Field field4MaskStrings, IF_Field field4DBValues, boolean bEmptyValInFront) throws myException {

		VEK<IF_Field>  vF = new VEK<IF_Field>()._a(rl.get_tab().get_fields());
		if (vF.contains(field4MaskStrings) && vF.contains(field4DBValues)) {
			
			int iZeilen = rl.size();
			if (bEmptyValInFront) {
				iZeilen++;
			}
			
			String[][] vals = new String[iZeilen][2];
			
			int i=0;
			if (bEmptyValInFront) {
				vals[i][0]="-"; vals[i][1]="";
				i++;
			}
			
			for (Rec21 r: rl) {
				vals[i][0]=r.get_fs_dbVal(field4MaskStrings, ""); vals[i][1]=r.get_fs_dbVal(field4DBValues, "");
				i++;
			}
			
			this._populate(vals);
		} else {
			throw new myException(this, "Reclist20 doesnt contain field: "+field4MaskStrings.fieldName()+" / "+field4DBValues.fieldName());
		}
		return this;
	}

	
	/**
	 * 
	 * @param rl
	 * @param stringCreator
	 * @param field4DBValues
	 * @param bEmptyValInFront
	 * @return
	 * @throws myException
	 */
	public RB_selField _populate(RecList21 rl, IF_CreateStringFromRec stringCreator, IF_Field field4DBValues, boolean bEmptyValInFront) throws myException {

		VEK<IF_Field>  vF = new VEK<IF_Field>()._a(rl.get_tab().get_fields());
		if (vF.contains(field4DBValues)) {
			
			int iZeilen = rl.size();
			if (bEmptyValInFront) {
				iZeilen++;
			}
			
			String[][] vals = new String[iZeilen][2];
			
			int i=0;
			if (bEmptyValInFront) {
				vals[i][0]="-"; vals[i][1]="";
				i++;
			}
			
			for (Rec21 r: rl) {
				vals[i][0]=stringCreator.createString(r); vals[i][1]=r.get_fs_dbVal(field4DBValues, "");
				i++;
			}
			
			this._populate(vals);
		} else {
			throw new myException(this, "Reclist20 doesnt contain field: "+field4DBValues.fieldName());
		}
		return this;
	}

	
	
	
	
	
	/**
	 * 
	 * @param pair (1=UserText, 2=DBVal)
	 * @return
	 * @throws myException 
	 */
	public RB_selField _addPair(PairS ... pair) throws myException {
		for (PairS p: pair) {
			this.hm_content_real._put(p.getVal1(),p.getVal2());
		}
		this._render();
		return this;
	}
	
	/**
	 * 
	 * @param values[][]   
	 * @return
	 * @throws myException
	 * array nx2, pos0 ist maskVal fur Dropdown, pos1=dbValue
	 */
	public RB_selField _populate(String[][] values) throws myException {
		this.hm_content_real._put(values);
		this._render();
		return this;
	}

	
	/**
	 * 
	 * @param values[][]   array nx2, pos0 ist text, pos1=val
	 * @return
	 * @throws myException
	 */
	public RB_selField _populateShadow(String[][] values) throws myException {
		this.hm_content_shadow._put(values);
		this._render();
		return this;
	}

	
	public RB_selField _clear() throws myException {
		this.hm_content_real.clear();
		this.hm_content_shadow.clear();
		this._render();
		return this;
	}
	
	
	
	
	public VEK<String> getVCompleteVisibleTags() {
		VEK<String> v = new VEK<String>();
		v._a(this.hm_content_real.getvVisibleTags());
		v._a(this.hm_content_shadowActive.getvVisibleTags());
		
		return v;
	}
	
	public VEK<String> getVCompleteDBVals() {
		VEK<String> v = new VEK<String>();
		v._a(this.hm_content_real.getvDbValues());
		v._a(this.hm_content_shadow.getvDbValues());
		
		return v;
	}
	
	
	public VEK<String> getVCompleteVisibleDbVals() {
		VEK<String> v = new VEK<String>();
		v._a(this.hm_content_real.getvDbValues());
		v._a(this.hm_content_shadowActive.getvDbValues());
		
		return v;
	}
	
	
	

	
	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {
		this.ext=oEXT;
	}

	@Override
	public MyE2EXT__Component EXT() {
		return this.ext;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		boolean bEnabled = enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		this.setEnabled(bEnabled);
		//this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bEnabled,true,false));
		if (this.isEnabled()) {
			this.mark_Enabled();
		} else {
			this.mark_Disabled();
		}
		
	}

	@Override
	public void mark_Neutral() throws myException {
		this.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.setBackground(new E2_ColorEditBackground());
	}

//	@Override
//	public void mark_MustField() throws myException	{
//		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
//	}

//	@Override
//	public void mark_Disabled() throws myException	{
//		this.setBackground(new E2_ColorGray(230));
//	}

//	@Override
//	public void mark_FalseInput() throws myException {
//		this.setBackground(new E2_ColorHelpBackground());
//	}

	
	
	private RB_KF Key = null;

	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}


	
	
	@Override
	public void show_InputStatus(boolean bInputIsOK) {
	}



	@Override
	public void rb_set_db_value_manual(String dbValFormated) throws myException {
		if (!this.getVCompleteDBVals().contains(dbValFormated)) {
			this.hm_content_shadow._put(dbValFormated);
		}
		this._render();
		this._setActiveDBVal(dbValFormated);
	}

	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	
		if (this.forceEmptyValInFront) {
			//immer pruefen, ob es eine leere zeile am anfang gibt
			if (!(this.hm_content_real.getvDbValues().size()>0 && S.isEmpty(this.hm_content_real.getvDbValues().get(0)))) {
//				this.hm_content_real._putEmptyValInFront();
//				this._render();
				this._putEmptyValInFront();
			}
		}
		
		if (dataObject.rec20().is_newRecordSet()) {
			//dann wird 
			this.setSelectedIndex(0);
		} else {
			//falls der wert nicht im hm_content_real ist, dann in den shadow reinschreiben
			String value = S.NN(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
			this.rb_set_db_value_manual(value);
		}
	}

	
	public RB_selField _render() throws myException {
		this.setModel(new DefaultListModel(this.getVCompleteVisibleTags().toArray()));
		return this;
	}
	

	
	public RB_selField _setActiveDBVal(String activDBVal) throws myException {
		String val = S.NN(activDBVal);
		int iSel = 0;
		if (this.hm_content_real.containsDbVal(val)) {
			iSel = this.hm_content_real.getPosOfDbVal(val);
			if (this.hm_content_shadowActive.size()>0) {   //dann der evtl. eingeblendete shadow-eintrag wieder loeschen
				this.hm_content_shadowActive.clear();
				this._render();
			}
		} else {
			if (this.hm_content_shadow.containsDbVal(val)) {
				this.hm_content_shadowActive.clear();
				this.hm_content_shadowActive.put(val, this.hm_content_shadow.get(val));
				this._render();
				
				iSel = this.hm_content_real.getvDbValues().size();  //dann wird der erste eintrag nach den aktiven elementen gewaehlt
			}else {
				throw new myException(this,"Value :<"+val+"> was not found in selection !");
			}
		}
		this.setSelectedIndex(iSel);
		return this;
	}
	
	
	public RB_selField _setActiveMaskVal(String activMaskVal) throws myException {
		String val = S.NN(activMaskVal);
		int iSel = 0;
		if (this.hm_content_real.containsMaskVal(val)) {
			iSel = this.hm_content_real.getPosOfMaskVal(val);
			if (this.hm_content_shadowActive.size()>0) {   //dann der evtl. eingeblendete shadow-eintrag wieder loeschen
				this.hm_content_shadowActive.clear();
				this._render();
			}
		} else {
			if (this.hm_content_shadow.containsMaskVal(val)) {
				String keyFromShadow = this.hm_content_shadow.getKeyOfMaskVal(val);
				this.hm_content_shadowActive.clear();
				this.hm_content_shadowActive.put(keyFromShadow, this.hm_content_shadow.get(keyFromShadow));
				this._render();
				
				iSel = this.hm_content_real.getvVisibleTags().size();  //dann wird der erste eintrag nach den aktiven elementen gewaehlt
			}else {
				throw new myException(this,"Value :<"+val+"> was not found in selection !");
			}
		}
		this.setSelectedIndex(iSel);
		return this;
	}

	public RB_selField _setActiveOrFirstDBVal(String activDBVal) throws myException {
		String val = S.NN(activDBVal);
		int iSel = 0;
		if (this.hm_content_real.containsDbVal(val)) {
			iSel = this.hm_content_real.getPosOfDbVal(val);
			if (this.hm_content_shadowActive.size()>0) {   //dann der evtl. eingeblendete shadow-eintrag wieder loeschen
				this.hm_content_shadowActive.clear();
				this._render();
			}
		} else {
			if (this.hm_content_shadow.containsDbVal(val)) {
				this.hm_content_shadowActive.clear();
				this.hm_content_shadowActive.put(val, this.hm_content_shadow.get(val));
				this._render();
				
				iSel = this.hm_content_real.getvDbValues().size();  //dann wird der erste eintrag nach den aktiven elementen gewaehlt
			} else {
				iSel=0;
			}
		}
		if (this.hm_content_real.size()>0 || this.hm_content_shadow.size()>0) {
			this.setSelectedIndex(iSel);
		}
		return this;
	}
	
	
	public RB_selField _setActiveOrFirstMaskVal(String activMaskVal) throws myException {
		String val = S.NN(activMaskVal);
		int iSel = 0;
		if (this.hm_content_real.containsMaskVal(val)) {
			iSel = this.hm_content_real.getPosOfMaskVal(val);
			if (this.hm_content_shadowActive.size()>0) {   //dann der evtl. eingeblendete shadow-eintrag wieder loeschen
				this.hm_content_shadowActive.clear();
				this._render();
			}
		} else {
			if (this.hm_content_shadow.containsMaskVal(val)) {
				String keyFromShadow = this.hm_content_shadow.getKeyOfMaskVal(val);
				this.hm_content_shadowActive.clear();
				this.hm_content_shadowActive.put(keyFromShadow, this.hm_content_shadow.get(keyFromShadow));
				this._render();
				
				iSel = this.hm_content_real.getvVisibleTags().size();  //dann wird der erste eintrag nach den aktiven elementen gewaehlt
			}else {
				iSel=0;
			}
		}
		if (this.hm_content_real.size()>0 || this.hm_content_shadow.size()>0) {
			this.setSelectedIndex(iSel);
		}
		return this;
	}


	

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}
	
	
	
	
	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}

	public ownHashMap get_hm_content() {
		return hm_content_real;
	}

	
	
	private  class ownListCellRenderer implements ListCellRenderer {
		public ownListCellRenderer() {
			super();
		}


		@Override
		public Object getListCellRendererComponent(Component list, final Object value, final int index) {

			return new StyledListCell()  {
            
                public Color getForeground()  {
                	try {
						if (RB_selField.this.hm_content_shadow.getvVisibleTags().contains((S.NN((String)value))))	{
							return Color.DARKGRAY;
						} else {
							return Color.BLACK;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return Color.DARKGRAY;

					}
                }
            
                public Font getFont() {
                    return new E2_FontPlain();
                }
            
                public Color getBackground() {
                    return new E2_ColorBase();
                }
                
                
                //das ist der bestandteil des DefaultListCellRenderers
                public String toString() {
                    return value == null ? null : value.toString();
                }
            };
		}
		
	}
	

	public String getActualDbVal() {
		return this.rb_readValue_4_dataobject();
	}
	
	

	public String getActualVisibleVal() {
		return this.getVCompleteVisibleTags().get(this.getSelectedIndex());
	}
	
	
	
	
	
	// implements E2_IF_Handles_ActionAgents
	/*
	 * ein vector fuer die speicherung der MyE2_ActionAgent - object.
	 * dieser werden dann nach der reihenfolge ihrer zufuegung ausgefuehrt 
	 */
	private Vector<XX_ActionAgent> 	vActionAgents = new Vector<XX_ActionAgent>();
	private Vector<ActionListener>	vExternalActionListeners = new Vector<ActionListener>();

	
	/*
	 * vector fuer validatoren (je einer fuer globale und fuer ID bezogene) 
	 */
	private Vector<XX_ActionValidator>	vGlobalValidators = 	new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>	vIDValidators = 		new Vector<XX_ActionValidator>();
	
	
	/*
	 * innerer action-listener, der benutzt wird, um die ActionAgents, 
	 * die dem button zugewiesen wurden, abzuarbeiten
	 */
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();
			
	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;
	
	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	
	@Deprecated
	/**
	 * Does some thing in old style.
	 *
	 * @deprecated use {@link doActionPassivManual()} instead.  
	 */
	public void doActionPassiv() 
	{
		bActionEventIsPassive =true;
		//this.doAction();   //select-field hat kein doAction(); 
		bActionEventIsPassive =false;
	}

	
	public void doActionPassivManual() {
		bActionEventIsPassive =true;
		
		EventListenerList list =  this.getEventListenerList();
		if (list!=null && list.getListeners(ActionListener.class).length>0) {
			
			EventListener[] clickListener = list.getListeners(ActionListener.class);
			for (EventListener l: clickListener) {
				if (l instanceof E2_InnerActionListenerForActionAgents) {
					((E2_InnerActionListenerForActionAgents)l).actionPerformed(new ActionEvent(this, null));
				}
			}
		}
		bActionEventIsPassive =false;
	}
	


	@Override
	public boolean get_bIsPassivAction() {
		return this.bActionEventIsPassive;
	}


	@Override
	public void set_bPassivAction(boolean bPassiv) {
		this.bActionEventIsPassive = bPassiv;
	}

	
	
	/**
	 * 2013-12-18: neue methode um meldungen zu manipulieren
	 */
	private XX_MessageManipulator      oMessageManipulator = null;
	
	
	@Override
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

	
	@Override
	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		this.oMessageManipulator = oManipulator;
	}

	
	//20170906: unterbrechungen mit benutzerinteraktion einfuegen
	private  E2_Break4PopupController break4PopupController = null;
	
	
	@Override
	public E2_Break4PopupController  getBreak4PopupController() {
		return this.break4PopupController;
	}


	/**
	 * 2018-01-16: martin: break4popup-controller setter dazugefuegt
	 * @param controller
	 * @return
	 */
	@Override
	public void setBreak4PopupController(E2_Break4PopupController controller) {
		this.break4PopupController = controller;
	}
	


	@Override
	public  RB_selField  _aaa(XX_ActionAgent agent) {
		this.add_oActionAgent(agent);
		return this;
	}

	
	@Override
	public  RB_selField  _aaaV(Vector<XX_ActionAgent> v_agents) {
		for (XX_ActionAgent agent: v_agents) {
			this.add_oActionAgent(agent);
		}
		return this;
	}

	
		


	
	@Override
	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session()	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}

	
	@Override
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) {
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}

			

	

	
	@Override
	public void add_GlobalValidator(XX_ActionValidator oValid) {
		this.vGlobalValidators.add(oValid);
	}
	
	
	/**
	 * 2013-05-16: martin panter
	 * @param oValid
	 */
	public void add_GlobalValidator_removeOthers(XX_ActionValidator oValid) {
		this.vGlobalValidators.removeAllElements();
		this.vGlobalValidators.add(oValid);
	}
	
	
	
	
	//2012-10-15: falls nicht der liste
	public void add_GlobalValidator_only_if_ClassNotInList(XX_ActionValidator oValid) {
		boolean bValidatorIstSchonDa = false;
		
		if (oValid!=null) {
			for (XX_ActionValidator oValidVorhanden: this.vGlobalValidators) {
				if (oValidVorhanden.getClass().getName().equals(oValid.getClass().getName())) {
					bValidatorIstSchonDa=true;
				}
			} if (!bValidatorIstSchonDa) {
				this.vGlobalValidators.add(oValid);
			}
		}
	}


	@Override
	public void add_IDValidator(XX_ActionValidator oValid) {
		this.vIDValidators.add(oValid);
	}
	
	@Override
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid) {
		this.vGlobalValidators.addAll(vValid);
	}

	@Override
	public void add_IDValidator(Vector<XX_ActionValidator> vValid){
		this.vIDValidators.addAll(vValid);
	}


	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cModuleKenner
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator(String cModuleKenner,String cButtonKenner)	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator(cModuleKenner,cButtonKenner));
	}
	
	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_AUTO(String cButtonKenner){
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_AUTO(cButtonKenner));
	}
	

	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_PROGRAMM_WIDE(String cButtonKenner)	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_PROGRAMM_WIDE(cButtonKenner));
	}
	
	

	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	@Override
	public MyE2_MessageVector valid_GlobalValidation() throws myException {
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vGlobalValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vGlobalValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(this));
		}
		return vRueck;
	}

	
	
	public MyE2_MessageVector  valid_SingleGlobalValidator(XX_ActionValidator oValidator) throws myException {
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		vRueck.add_MESSAGE(oValidator.isValid(this));
		return vRueck;
		
	}
	

	
	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	@Override
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}
		return vRueck;
	}


	

	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent) {
		if (actionAgent != null) {
			this.vActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront) {
		if (actionAgent != null) {
			if (bInFront) {
				this.vActionAgents.add(0,actionAgent);
			} else {
				this.vActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListener);
		}
	}

	

	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * 2015-11-25: geaendert: wenn leer oder null, dann darf kein listener zugefuegt werden
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	@Override
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront){
		boolean bHadActionAgentsBefore = (this.vActionAgents.size()>0);   //2012-10-15: fehlerkorrektur

		if (vActionAgent==null || vActionAgent.size()==0) {
			return;   //nichts tun
		}
		
		if (bInFront) {
			this.vActionAgents.addAll(0,vActionAgent);
		} else {
			this.vActionAgents.addAll(vActionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (!bHadActionAgentsBefore)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}


	
	
	
	
	
	
	
	
	
	@Override
	public void remove_oActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vActionAgents.size();i++) {
			if (this.vActionAgents.get(i)==actionAgent)	{
				this.vActionAgents.remove(i);
			}
		}
		
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==0) {
			super.removeActionListener(this.oInnerActionListener);
		}
	}

	
	@Override
	public void remove_AllActionAgents() {
		this.vActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListener);
	}

	
	
	
	@Override
	public Vector<XX_ActionAgent> get_vActionAgents() {
		return this.vActionAgents;
	}


	public Vector<ActionListener> get_vExternalActionListeners() {
		return vExternalActionListeners;
	}


	@Override
	public void addActionListener(ActionListener oActionListener)	{
		this.vExternalActionListeners.add(oActionListener);
		super.addActionListener(oActionListener);
	}
	
	@Override
	public void removeActionListener(ActionListener oActionListener) {
		for (int i=0;i<this.vExternalActionListeners.size();i++)
		{
			if (this.vExternalActionListeners.get(i) == oActionListener)
			{
				this.vExternalActionListeners.remove(i);
			}
		}
		super.removeActionListener(oActionListener);
	}

	
	
	
	//2013-01-04: codeblock fuer interalActionAgents
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	
	@Override
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents() {
		return this.vInternalActionAgents;
	}

	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent) {
		if (actionAgent != null) {
			this.vInternalActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	
	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)	{
		if (actionAgent != null){
			if (bInFront) {
				this.vInternalActionAgents.add(0,actionAgent);
			} else {
				this.vInternalActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}
	
	@Override
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vInternalActionAgents.size();i++) {
			if (this.vInternalActionAgents.get(i)==actionAgent) 	{
				this.vInternalActionAgents.remove(i);
			}
		}
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==0) {
			super.removeActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	@Override
	public void remove_AllInternalActionAgents()  {
		this.vInternalActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListenerInternalAction);
	}

	//2013-01-04 -- ende codeblock internalActionAgents
	
	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	@Override
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}


	@Override
	public String rb_readValue_4_dataobject() {
		
		
		if (this.getVCompleteVisibleDbVals().size()>this.getSelectedIndex() && this.getSelectedIndex()>=0) {
			return this.getVCompleteVisibleDbVals().get(this.getSelectedIndex());
		}
		return null;
	}

	
	
	private class ownHashMap extends LinkedHashMap<String, String> {
		

		public ownHashMap _putEmptyValInFront() throws myException {
			ownHashMap hmSave = new ownHashMap();
			hmSave.putAll(this);
			this.clear();
			
			this._put("-", "");
			this.putAll(hmSave);
			
			return this;
		}
		
		
		public ownHashMap _put(String valSingle) throws myException {
			this.put(valSingle, valSingle);
			return this;
		}

		public ownHashMap _put(String visible, String val4DB) throws myException {
			this.put(val4DB, visible);
			return this;
		}
		
		public ownHashMap _put(Vector<String> v) throws myException {
			for (String c: v) {
				this.put(c, c);
			}
			return this;
		}
		
		public ownHashMap _put(String[] a) throws myException {
			for (String c: a) {
				this.put(c, c);
			}
			return this;
		}
		
		/**
		 * 
		 * @param a (a[0]=Text4user, a[1]=dbVal
		 * @return
		 * @throws myException
		 */
		public ownHashMap _put(String[][] a) throws myException {
			for (String[] c: a) {
				if (c.length!=2) {
					throw new myException(this,"only array with nx2 allowed !");
				}
				this.put(c[1], c[0]);
			}
			return this;
		}
		
		//werte fuer dropdown
		public VEK<String> getvVisibleTags() {
			VEK<String> v = new VEK<>();
			for (HashMap.Entry<String, String> e: this.entrySet()) {
				v.add(e.getValue());
			}
			return v;
		}
		
		//werte fuer dropdown
		public VEK<String> getvDbValues() {
			VEK<String> v = new VEK<>();
			for (HashMap.Entry<String, String> e: this.entrySet()) {
				v.add(e.getKey());
			}
			return v;
		}
	
		
		public boolean containsDbVal(String dbval) {
			return (this.getPosOfDbVal(dbval)>=0) ;
		}
		
		public int getPosOfDbVal(String dbval) {
			int pos=-1;
			int posCount=0;
			for (String c: this.keySet()) {
				if (c.equals(S.NN(dbval))){
					pos=posCount;
					break;
				}
				posCount++;
			}
			return pos;
		}
		
	
		public boolean containsMaskVal(String dbval) {
			return (this.getPosOfMaskVal(dbval)>=0) ;
		}
		
		public int getPosOfMaskVal(String maskVal) {
			int pos=-1;
			int posCount=0;
			for (String c: this.values()) {
				if (c.equals(S.NN(maskVal))){
					pos=posCount;
					break;
				}
				posCount++;
			}
			return pos;
		}
		
		public String getKeyOfMaskVal(String maskVal) {
			String  key = null;
			for (String c: this.keySet()) {
				if (this.get(c).equals(S.NN(maskVal))){
					key = c;
					break;
				}
			}
			return key;
		}

		
	}


	public boolean isForceEmptyValInFront() {
		return forceEmptyValInFront;
	}


	public RB_selField _setForceEmptyValInFront(boolean p_forceEmptyValInFront) {
		this.forceEmptyValInFront = p_forceEmptyValInFront;
		return this;
	}
	
	
	public RB_selField  _putEmptyValInFront() throws myException {
		if (!this.hm_content_real.containsDbVal("")) {
			this.hm_content_real._putEmptyValInFront();
			this._render();
		}
		return this;
	}
	
	
	/**
	 * fuegt einen wert zum shadow-bereich hinzu
	 * @param val4shadow
	 * @return
	 * @throws myException
	 */
	public RB_selField _putValueToShadow(String val4shadow) throws myException {
		this.hm_content_shadow._put(val4shadow);
		this._render();
		return this;
	}
	
	
	
	
	@Override
	public void setSelectedIndex(int index) {
		super.setSelectedIndex(index);
		if (this.isMemoryEffectActive) {
			this.lastSetValue=this.rb_readValue_4_dataobject();
		}
	}

	
	/**
	 * speichert (falls der komponente zudefiniert) den zuletzt 
	 * @author martin
	 *
	 */
	private class ownActionAgentForMemoryEffect extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_selField.this.lastSetValue = RB_selField.this.rb_readValue_4_dataobject();
		}
	}
	
	
	/**
	 * activiert den memory-effekt bei RB_selField
	 * @return
	 */
	public RB_selField _setMemoryEffectActive() {
		boolean actionAgentIsPresent = false;
		for (XX_ActionAgent a: this.get_vActionAgents()) {
			if (a instanceof ownActionAgentForMemoryEffect) {
				actionAgentIsPresent = true;
			}
		}
		
		if (!actionAgentIsPresent) {
			this.add_oActionAgent(new ownActionAgentForMemoryEffect(), true);
		}
		
		this.isMemoryEffectActive=true;
		return this;
	}
	
	/**
	 * deactiviert den memory-effekt bei RB_selField
	 * @return
	 */
	public RB_selField _removeMemoryEffectActive() {
		VEK<XX_ActionAgent> v = new VEK<XX_ActionAgent>();
		for (XX_ActionAgent a: this.get_vActionAgents()) {
			if (a instanceof ownActionAgentForMemoryEffect) {
				v._a(a);
			}
		}

		for (XX_ActionAgent a: v) {
			this.get_vActionAgents().remove(a);
		}
		this.isMemoryEffectActive=false;

		return this;
	}


	public String getLastSetValue() {
		return lastSetValue;
	}


	public void setLastSetValue(String lastSetValue) {
		this.lastSetValue = lastSetValue;
	}

	
	
	public HMAP<String, String> getHMAPVisible() {
		return new HMAP<String, String>()._putAll(this.hm_content_real);
	}

	public HMAP<String, String> getHMAPShadow() {
		return new HMAP<String, String>()._putAll(this.hm_content_shadow);
	}

	public HMAP<String, String> getHMAPVisibleAndShadow() {
		return new HMAP<String, String>()._putAll(this.hm_content_real)._putAll(this.hm_content_shadow);
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.E2_IF_Copy#get_Copy(java.lang.Object)
	 */
	@Override
	public RB_selField get_Copy(Object objHelp) throws myExceptionCopy {
		
		RB_selField ret =  new RB_selField();
		ret.setWidth(this.getWidth());
		ret.setStyle(this.getStyle());
		
		ret.hm_content_real.putAll(this.hm_content_real);
		ret.hm_content_shadow.putAll(this.hm_content_shadow);
		
		try {
			ret._render();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
		
		
		return ret;
	}
	
	
	
	//20180229: moeglichkeit, simple actions zu erzeugen und zu uebergebben
	public RB_selField  _aaa(IF_agentSimple agent) {
		this.add_oActionAgent(agent.genActionAgent());
		return this;
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 09.11.2020
	 *
	 * @param objHelp
	 * @param indexOnInit (preselect-index)
	 * @return
	 * @throws myExceptionCopy
	 */
	public RB_selField get_Copy(Object objHelp, Integer indexOnInit) throws myExceptionCopy {
		RB_selField copy = this.get_Copy(objHelp);
		if (indexOnInit!=null && indexOnInit<this.hm_content_real.size()) {
			copy.setSelectedIndex(indexOnInit);
		}
		return copy;
	}
}
