package panter.gmbh.Echo2.RB.COMP.BETA;

import java.util.Comparator;
import java.util.Vector;

import echopointng.ContainerEx;
import echopointng.PopUp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.myLinkedHashMap;

public class RB_selField_pop extends E2_Grid implements IF_RB_Component {

	private MyE2EXT__Component  ext = new MyE2EXT__Component(this);
	
	//den "leeren" wert definieren
	private String  			empty_keyToShow = "-";
	
	/*
	 * die werte-paare werden in einer linked-hashmap gespeichert, key=Sichtbar im DroptDown, Val = der Wert fuer die datenbank
	 */
	private myLinkedHashMap<String,String> hm_content_real = new myLinkedHashMap<>();
	private myLinkedHashMap<String,String> hm_content_shadow = new myLinkedHashMap<>();

	
	private VEK<String> v_content_complete = new VEK<>();
	
	
    private E2_ResourceIcon 	iconAktiv= 		E2_ResourceIcon.get_RI("selectfieldicon.png");

    private int sizeTextHeight = 16;
    private int sizeTextWidth = 200;
	private int sizePopX = 100;
    
    private int sizeButtonHeight = 20;
    private int sizeSearchblock_height = 20;
	private int sizeDropDownPaneWidth = 200;
	private int sizeMaxDropDownPaneHeight = 400;    //maximale y-ausdehung der dropdown-flaeche, ansonsten n x buttonHeight
	
	
    private RB_TextField        tf_actual_keyToShow = new RB_TextField()._nB();
    private PopUp				pop = new PopUp();
    private Vector<E2_Button>   v_buttons = new Vector<>();
    private E2_Grid  			grid4pop = new E2_Grid()._setSize(this.sizePopX);
    
    private MyE2_ContainerEx	contEx	= new MyE2_ContainerEx();
    private MyE2_ContainerEx	contExSearchBlock	= new MyE2_ContainerEx();
    private MyE2_ContainerEx	contExButtonList	= new MyE2_ContainerEx();
    
    private Color  				colorBase = new E2_ColorBase();
	private Border 				unseen = new Border(0, this.colorBase, Border.STYLE_NONE);
    
	
	private Color    			color_dropdown_back = new E2_ColorBase();
	private Color    			color_dropdown_highlight = new E2_ColorDDark();
	
	private RB_gld 				ld_button_std = new RB_gld()._left_mid();
	
    //die komponente ist zusammengesetzt aus RB_text und popup in einem grid
	
	//optional in der ersten zeile ein suchfeld
    private boolean 			show_searchField = false;
    private RB_TextField        tf_searchField = new RB_TextField();
    private E2_Button 	        bt_searchField = new E2_Button()._image(E2_ResourceIcon.get_RI("suche_mini.png"))._aaa(new ownStartSearch());
    
    
    //ActionAgents von aussen
    private Vector<XX_ActionAgent>    v_agents_external = new Vector<>();
    
    
    //vector mit keys (anzeigenwerten), die inaktiv sein sollen
    private Vector<String>           v_inactiv_keys = new Vector<>();
    
    
	/**
	 * @throws myException 
	 * 
	 */
	public RB_selField_pop() throws myException {
		super();
        this._rebuildContainer();
	}
	
	
	
	public RB_selField_pop _clearAddonActionAgents() throws myException {
		this.v_agents_external.clear();
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}
	
	
	public RB_selField_pop _addAddonActionAgent(XX_ActionAgent addonAction) throws myException {
		this.v_agents_external.add(addonAction);
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}
	
	
	
	
	public RB_selField_pop _rebuildContainer() throws myException {
		this._clear();
		this.grid4pop._clear();
		this.setBorder(this.unseen);
		RB_gld ld = new RB_gld()._col(this.colorBase);
		
		this._a(this.tf_actual_keyToShow, ld)._a(this.pop,ld)._setSize(100,16)._bc(this.colorBase)._bo(this.unseen);
		
		this.tf_actual_keyToShow._w(this.sizeTextWidth);
		this.tf_actual_keyToShow._h(this.sizeTextHeight);
		
		this.pop.setPopUpOnRollover(false);
		this.pop.setPopUpAlwaysOnTop(true);
		this.pop.setBorder(this.unseen);
		this.pop.setRolloverBorder(this.unseen);
		this.pop.setPopUpBackground(this.colorBase);
		
		this.pop.setBackground(this.colorBase);
		this.pop.setToggleIcon(this.iconAktiv);
		this.pop.setTogglePressedIcon(this.iconAktiv);
		this.pop.setToggleRolloverIcon(this.iconAktiv);
		
		
		this.contExButtonList.add(this.grid4pop);

		this.contExButtonList.setBackground(this.color_dropdown_back);
		this.contExSearchBlock.setBackground(this.color_dropdown_back);
		this.contEx.setBackground(this.color_dropdown_back);
		
		this.contExButtonList.setInsets(E2_INSETS.I(0));
		this.contExButtonList.setOutsets(E2_INSETS.I(2,0,0,0));
		
		this.pop.setPopUpAlignment(new Alignment(Alignment.LEFT,Alignment.BOTTOM));
		this.pop.setPopUpLeftOffset(-1*this.sizeTextWidth-5);
		this.pop.setPopUpTopOffset(this.sizeTextHeight-6);
		this.pop.setPopUpBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));

		
		int iWidth = this.sizeDropDownPaneWidth<(this.sizeTextWidth+20)?(this.sizeTextWidth+20):sizeDropDownPaneWidth;
		
		//jetzt die dropdown-flaeche definieren
		int iCountButtons = this.hm_content_real.size()+this.hm_content_shadow.size();
		
		int iHeight = (iCountButtons+1)*this.sizeButtonHeight;    //iCountButtons+1 weil es noch einen leeren eintrag am anfang geben kann
		if (this.show_searchField) {
			iHeight = iHeight+20;
		}

		
		if (iHeight>this.sizeMaxDropDownPaneHeight) {
			iHeight=this.sizeMaxDropDownPaneHeight;
		}
		
		if (this.show_searchField) {
			this.contExSearchBlock.setWidth(new Extent(iWidth-5));
			this.contExSearchBlock.setHeight(new Extent(this.sizeSearchblock_height));
			
			//searchblock
			this.tf_searchField._h(this.sizeSearchblock_height-6)._w(this.sizeDropDownPaneWidth-30)._bord_dgrey()._f(new E2_FontBoldItalic(-2));
			E2_Grid  g_search = new E2_Grid()._a(this.tf_searchField)._a(this.bt_searchField);
			this.contExSearchBlock.add(g_search);
			
			this.contExButtonList.setWidth(new Extent(iWidth));
			this.contExButtonList.setHeight(new Extent(iHeight-this.sizeSearchblock_height));
			this.contEx.setScrollBarPolicy(ContainerEx.NEVER);
			this.contExButtonList.setScrollBarPolicy(ContainerEx.AUTO);
			
			this.contEx.setWidth(new Extent(iWidth));
			this.contEx.setHeight(new Extent(iHeight));
			
			E2_Grid  g = new E2_Grid()._setSize(this.sizeDropDownPaneWidth)
									  ._a(this.contExSearchBlock, new RB_gld()._ins(0))
									  ._a(this.contExButtonList, new RB_gld()._ins(0));
			this.contEx.removeAll();
			this.contEx.add(g);
			this.pop.setPopUp(this.contEx);
		
		} else {
			this.contExButtonList.setWidth(new Extent(iWidth));
			this.contExButtonList.setHeight(new Extent(iHeight));
			this.pop.setPopUp(this.contExButtonList);
			
		}
		
		
		return this;
	}
	
	
	
	public RB_selField_pop _setTextWidth(int w) throws myException {
		this.sizeTextWidth = w;
		this._rebuildContainer();
		return this;
	}
	
	
	
	public RB_selField_pop _renderButtons() throws myException {
		
		this.v_content_complete.clear();
		this.v_content_complete.addAll(this.hm_content_real.keySet());
		this.v_content_complete.addAll(this.hm_content_shadow.keySet());
		
		this.v_buttons.clear();
		this.grid4pop._clear();
		
		
		int iWidth = this.sizeDropDownPaneWidth<(this.sizeTextWidth+20)?(this.sizeTextWidth+20):sizeDropDownPaneWidth;

		String searchText = "";
		if (S.isFull(this.tf_searchField.getText())) {
			searchText = this.tf_searchField.getText().trim();
		}
		
		for (String s: this.v_content_complete) {
			boolean add = true;
			if (S.isFull(searchText)) {
				add = (s.toUpperCase().contains(searchText.toUpperCase()));
			}

			if (add) {
				if(this.hm_content_real.keySet().contains(s)) {
					if (this.v_inactiv_keys.contains(s)) {
						this.v_buttons.add(new ownButtonDisabled(s));
					} else {
						this.v_buttons.add(new ownButton(s));
					}
				} else {
					this.v_buttons.add(new E2_Button()._t(s)	._aaa(new ownAgentCollapse())
																._fc(Color.DARKGRAY)
																._i()
																._height(this.sizeButtonHeight)
																._width(new Extent(iWidth))
																);
				}
			}
		}
		
		RB_gld ld_std = this.ld_button_std._c()._col(this.color_dropdown_back);
		
		for (E2_Button b: this.v_buttons) {
			this.grid4pop._a(b, ld_std);
		}
		
		return this;
	}


	private class ownButton extends E2_Button {
		public ownButton(String s) {
			super();
			RB_selField_pop oThis = RB_selField_pop.this;
			
			int iWidth = oThis.sizeDropDownPaneWidth<(oThis.sizeTextWidth+20)?(oThis.sizeTextWidth+20):sizeDropDownPaneWidth;
			
			this._t(s)	._aaa(new ownActionSetKeyToField())
				._aaa(new ownAgentCollapse())
				._aaa(new ownActionMarkActual())
				._aaa(new ownActionClearSearchField())
				._aaaV(oThis.v_agents_external)
				._height(oThis.sizeButtonHeight)
				._width(new Extent(iWidth))
				;
			if (S.isFull(oThis.tf_actual_keyToShow.getText()) && (!s.equals(oThis.empty_keyToShow))  && s.equals(oThis.tf_actual_keyToShow.getText().trim())) {
				this.setBackground(oThis.color_dropdown_highlight);
			}
				
			
			this.setRolloverEnabled(true);
			this.setRolloverBackground(oThis.color_dropdown_highlight);
			
		}
	}
	
	
	private class ownButtonDisabled extends E2_Button {
		public ownButtonDisabled(String s) {
			super();
			RB_selField_pop oThis = RB_selField_pop.this;
			
			this	._t(s)
					._height(oThis.sizeButtonHeight)
					._fc(Color.DARKGRAY)
					._width(new Extent(oThis.sizeDropDownPaneWidth))
					;
			if (S.isFull(oThis.tf_actual_keyToShow.getText()) && (!s.equals(oThis.empty_keyToShow))  && s.equals(oThis.tf_actual_keyToShow.getText().trim())) {
				this.setBackground(oThis.color_dropdown_highlight);
			}
				
			
			this.setRolloverEnabled(true);
			this.setRolloverBackground(oThis.color_dropdown_highlight);
			
		}
	}

	
	
	public RB_selField_pop _setPopupWidth(int w) throws myException {
		this.sizePopX=w;
		this._rebuildContainer();
		return this;
	}
	
	public RB_selField_pop _setPopupHeight(int h) throws myException {
		this.sizeMaxDropDownPaneHeight=h;
		this._rebuildContainer();
		return this;
	}
	
	
	
	
	public RB_selField_pop _populate(Vector<String> values) throws myException {
		for (String c: values) {
			this.hm_content_real._put(c, c);
		}
		this.hm_content_real._sortKeys(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}
	
	public RB_selField_pop _populate(String[] values) throws myException {
		for (String c: values) {
			this.hm_content_real._put(c, c);
		}
		this.hm_content_real._sortKeys(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}
	

	
	public RB_selField_pop _populate(String[][] values) throws myException {
		for (int i=0; i< values.length;i++) {
			this.hm_content_real._put(values[i][0], values[i][1]);
		}
		this.hm_content_real._sortKeys(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}

	
	
	public RB_selField_pop _setShowSearchField(boolean showSearchField) throws myException {
		this.show_searchField=showSearchField;
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}

	
	
	
	public RB_selField_pop _resetComponent() throws myException {
		this.tf_actual_keyToShow.setText("");
		this.hm_content_real.clear();
		this.hm_content_shadow.clear();
		this._rebuildContainer();
		this._renderButtons();
		return this;
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
		this.pop.setEnabled(bEnabled);
		this.tf_actual_keyToShow.setEnabled(false);   //im Textfeld sind keine eingaben moeglich
	}

	
	
	
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
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.hm_content_shadow.clear();
		this.set_value(valueFormated);
		this._rebuildContainer();
		this._renderButtons();
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this.hm_content_shadow.clear();
		if (dataObject.rec20().is_newRecordSet()) {
			//dann wird geprueft, ob ein leer-Eintrag am anfang steht
			if (!(this.hm_content_real.size()>0 && this.hm_content_real.get_first_key().equals(this.empty_keyToShow))) {
				this.hm_content_real._putInFront(this.empty_keyToShow, "");
			}
			this.tf_actual_keyToShow.setText(this.hm_content_real.get_first_key());
		} else {
			//falls der wert nicht im hm_content_real ist, dann in den shadow reinschreiben
			String valueFormated = ((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME());
			this.set_value(valueFormated);
		}
		this._rebuildContainer();
		this._renderButtons();
	}

	
	/**
	 * wird mehrmals gebraucht
	 * @param valueFormated
	 * @throws myException
	 */
	private void set_value(String valueFormated) throws myException {
		if (S.isEmpty(valueFormated)) {
			if (!this.hm_content_real.contains_val("")) {
				this._put_empty_in_front();
			}
			this.tf_actual_keyToShow.setText(this.empty_keyToShow);
		} else {
		
			String keyToShow = this.hm_content_real.getKeyFromValue(S.NN(valueFormated));
			
			if (S.isEmpty(keyToShow)) {
				keyToShow = this.hm_content_shadow.getKeyFromValue(S.NN(valueFormated));
				if (S.isEmpty(keyToShow)) {
					this.hm_content_shadow._put(S.NN(valueFormated), S.NN(valueFormated));
				}
			}
			if (S.isEmpty(keyToShow)) {
				this.tf_actual_keyToShow.setText(S.NN(valueFormated));
			} else {
				this.tf_actual_keyToShow.setText(keyToShow);
			}
		}

	}
	
	
	/**
	 * einen leeren eintrag am anfang einfuegen
	 * @return
	 * @throws myException
	 */
	public RB_selField_pop _put_empty_in_front() throws myException {
		this.hm_content_real._putInFront(this.empty_keyToShow, "");
		this._rebuildContainer();
		this._renderButtons();
		return this;
	}
	
	
	@Override
	public void mark_Neutral() throws myException {
		this.tf_actual_keyToShow.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.tf_actual_keyToShow.setBackground(new E2_ColorEditBackground());
	}

	@Override
	public void mark_MustField() throws myException	{
		this.tf_actual_keyToShow.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.tf_actual_keyToShow.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.tf_actual_keyToShow.setBackground(new E2_ColorHelpBackground());
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

	public myLinkedHashMap<String, String> get_hm_content() {
		return hm_content_real;
	}


	
	private  class ownAgentCollapse extends XX_ActionAgent {
		public ownAgentCollapse() {
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo){
			RB_selField_pop.this.pop.setExpanded(false);
		}
	}

	

	private class ownActionSetKeyToField extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_Button bt = ((E2_Button)oExecInfo.get_MyActionEvent().getSource());
			RB_selField_pop.this.tf_actual_keyToShow.setText(bt.getText());
		}
	}


	private class ownActionMarkActual extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_selField_pop oThis = RB_selField_pop.this;
			E2_Button bt = ((E2_Button)oExecInfo.get_MyActionEvent().getSource());
			RB_gld ld = 		oThis.ld_button_std._c()._col(oThis.color_dropdown_back);
			RB_gld ld_high = 	oThis.ld_button_std._c()._col(oThis.color_dropdown_highlight);
			for (E2_Button b: RB_selField_pop.this.v_buttons) {
				b.setBackground(oThis.color_dropdown_back);
				b.setLayoutData(ld);
				if (b.getText().equals(bt.getText())){
					bt.setBackground(oThis.color_dropdown_highlight);
					bt.setLayoutData(ld_high);
				}
			}
		}
	}


	private class ownStartSearch extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_selField_pop.this._renderButtons();
		}
	}
	
	
	private class ownActionClearSearchField extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (S.isFull(RB_selField_pop.this.tf_searchField.getText())) {
				RB_selField_pop.this.tf_searchField.setText("");
				RB_selField_pop.this._renderButtons();
			}
		}
	}

	
	
	
	public RB_TextField get_tf_actual_keyToShow() {
		return tf_actual_keyToShow;
	}


	public RB_selField_pop _add_inactive_keys(Vector<String>  inactiv_keys) throws myException {
		this.v_inactiv_keys.addAll(inactiv_keys);
		this._rebuildContainer();
		this._renderButtons();

		return this;
		
	}



	public String get_empty_keyToShow() {
		return this.empty_keyToShow;
	}
	
	
	
	
}
