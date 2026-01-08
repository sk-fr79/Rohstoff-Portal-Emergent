/**
 * 
 */
package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * Komponente eine popup mit vorangestelltem text-button
 */
public class E2_PopupWithButtonCascading extends E2_Grid {
	
	private E2_PopUp   			popUpComponent = 	new E2_PopUp();
	private E2_Button  			textButton = 		new E2_Button()._aaa(new ownActionClickToOpen());

	private RB_gld  			layoutTextButton = 	new RB_gld()._ins(1)._left_mid();
	private RB_gld  			layoutPopupIcon = 	new RB_gld()._ins(1)._left_mid();
	private E2_ResourceIcon   	iconPopupActive = 	E2_ResourceIcon.get_RI("submenue_mini.png");
	private E2_ResourceIcon   	iconPopupInActive = E2_ResourceIcon.get_RI("submenue_mini.png");
	

	private VEK<MenueEntry>     vMenue = null;
	
	private MyE2_String           buttonText = null;
	
	private IfActionAgentFactory  actionAgentFactory = null;
	
	private Integer    			container_width = null;		//falls not null, wird eine container-ex - struktur genommen  
	private int    			 	iHeightPerEntry = 20; 
	private int   				iMaxNumbersItemsInPopup = 15;
	
	/**
	 * 
	 */
	public E2_PopupWithButtonCascading(MyE2_String s, IfActionAgentFactory  p_actionAgentFactory, Integer  p_popupContainerWidth) {
		super();
		this.buttonText = s;
		this.actionAgentFactory=p_actionAgentFactory;
		this.container_width = p_popupContainerWidth;
	}
	

	public E2_PopupWithButtonCascading _init() {
		
		
		this.textButton._t(this.buttonText)._f(new E2_FontPlain());
		
		this.popUpComponent.set_oIconAktiv(this.iconPopupActive);
		this.popUpComponent.set_oIconInactiv(this.iconPopupInActive);
		
//		this.popUpComponent.set_oIconAktiv(null);
//		this.popUpComponent.set_oIconInactiv(null);
		
		this.popUpComponent.setBorder(new Border(0, null, Border.STYLE_NONE));
		this.popUpComponent.setRolloverBorder(new Border(0, null, Border.STYLE_NONE));
		this.popUpComponent.setRolloverBackground(new E2_ColorDDDark());
		
		this.popUpComponent._set_roll_over_background(new E2_ColorDDDark());
		
		this._s(2);
		this.popUpComponent._set_number_cols(1);
		this.popUpComponent.get_grid_innen().setColumnWidth(0, null);

		this.popUpComponent.setPopUpAlwaysOnTop(true);
		
		this.textButton.setHeight(new Extent(this.iHeightPerEntry));
			
		this._render();

		
		return this;
	}
	
	
	
	public E2_PopupWithButtonCascading _render() {
		this._clear()._a(this.textButton, this.layoutTextButton)._a(this.popUpComponent, this.layoutPopupIcon);
		
		return this;
	}
	
	
	public boolean isUsingContainerEx() { 
		return this.container_width!=null;
	}

	
	/**
	 * 
	 * @param menueItems  Vector<MenueEntry> 
	 * @return
	 * @throws myException
	 */
	public E2_PopupWithButtonCascading _populate(VEK<MenueEntry> menueItems) throws myException {
		
		this.vMenue= new VEK<E2_PopupWithButtonCascading.MenueEntry>()._a(menueItems);
		
		//preufen, ob ein eintrag oder ein wert doppelt ist
		VEK<MenueEntry> menueItemsReal = this.getRealMenueButtonEntrys();
		VEK<String> werte = new VEK<>();
		VEK<String> texte = new VEK<>();
		VEK<String> werteSingle = new VEK<>();
		VEK<String> texteSingle = new VEK<>();
		for (MenueEntry e: menueItemsReal) {
			werte._a(e.m_dbVal);
			werteSingle._addIfNotIn(e.m_dbVal);
			texte._a(e.m_text);
			texteSingle._addIfNotIn(e.m_text);
		}
		if (werte.size()!=werteSingle.size() || werte.size()!=werteSingle.size() || werte.size()!=texte.size() || werte.size()!=texteSingle.size()) {
			throw new myException(this,"All menue-entrys MUST be singular in text and values !");
		}
		
		
		for (MenueEntry menueEntry: this.vMenue) {
			MyE2IF__Component comp  = null;
			
			
			Font f = menueEntry.getFont();
			
			
			if (menueEntry.isButton()) {
				E2_Button b = new E2_Button()._t(menueEntry.getText())._f(f)._aaaV((this.actionAgentFactory!=null?this.actionAgentFactory.getActionAgents(menueEntry):new VEK<>()),true);
				b.setRolloverEnabled(true);
				b.setRolloverBackground(new E2_ColorDDDark());
				b.setHeight(new Extent(this.iHeightPerEntry));
				comp = b;
				menueEntry.setButtonEndPoint(b);
			} else if (menueEntry.isMenue()){
				String 			text = 	menueEntry.getText();
				VEK<MenueEntry> sub = 	menueEntry.getMenue();
				
				text = text+" ...";
				
				//dann neues popup anzeigen
				comp = new E2_PopupWithButtonCascading(new MyE2_String(text,false), this.actionAgentFactory, this.container_width)._init()._populate(sub);
				((E2_PopupWithButtonCascading)comp).getPopUp().setMotherPopup(this.getPopUp());

				//die sub-buttons auch mit rollover
				((E2_PopupWithButtonCascading)comp).getButton().setRolloverEnabled(true);
				((E2_PopupWithButtonCascading)comp).getButton().setRolloverBackground(new E2_ColorDDDark());
				((E2_PopupWithButtonCascading)comp).getButton().setFont(f);
				((E2_PopupWithButtonCascading)comp).getPopUp().setLayoutData(new RB_gld()._ins(10, 2, 2, 2));

				menueEntry.setPopupWithButtonCascading((E2_PopupWithButtonCascading)comp);
				
				//hier den Actionagenten, der alle gleich-ebenen-popups einklappt
				((E2_PopupWithButtonCascading)comp).getButton()._aaa(new CloseActionAllOtherPopups(menueEntry));
				
				//ab der ersten ebene die popup-buttons "unsichtbar" machen
				((E2_PopupWithButtonCascading)comp).getPopUp()._setUnVisible();
				
			} else {
				//duerfte nicht vorkommen
				throw new myException(this, "Definition-Error: not correct MenueEntry-object!");
			}
			
			if (comp != null) {
				this.popUpComponent._add_line_withoutRebuild(comp);
			}
			
		}
		
		if (this.container_width!=null) {
			int zeilen = this.vMenue.size();
			if (zeilen>this.iMaxNumbersItemsInPopup) { zeilen=this.iMaxNumbersItemsInPopup;}
			this.popUpComponent._use_container_ex(true,false)._set_container_width(new Extent(this.container_width))._set_container_heigth(new Extent(zeilen*(this.iHeightPerEntry+6)));
		}
		this.popUpComponent._rebuild();
		
		return this;
	}
	
	


	public E2_PopupWithButtonCascading _setLayoutButton(RB_gld layout) throws myException {
		this.layoutTextButton = layout;
		return this;
	}

	public E2_PopupWithButtonCascading _setLayoutPopupIcon(RB_gld layout) throws myException {
		this.layoutPopupIcon = layout;
		return this;
	}

	

	public E2_PopupWithButtonCascading _setLayoutButtonAndRender(RB_gld layout) throws myException {
		this.layoutTextButton = layout;
		return this._render();
	}

	public E2_PopupWithButtonCascading _setLayoutPopupIconAndRender(RB_gld layout) throws myException {
		this.layoutPopupIcon = layout;
		return this._render();
	}

	
	private class ownActionClickToOpen extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			popUpComponent.setExpanded(!popUpComponent.isExpanded());
		}
	}





	public E2_PopUp getPopUp() {
		return popUpComponent;
	}


	public E2_Button getButton() {
		return textButton;
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.textButton.set_bEnabled_For_Edit(enabled);
		this.popUpComponent.set_bEnabled_For_Edit(enabled);
	}


	public E2_ResourceIcon getIconPopupActive() {
		return iconPopupActive;
	}


	public E2_ResourceIcon getIconPopupInActive() {
		return iconPopupInActive;
	}


	public E2_PopupWithButtonCascading _setIconPopupActive(E2_ResourceIcon iconPopupActive) {
		this.iconPopupActive = iconPopupActive;
		return this;
	}


	public E2_PopupWithButtonCascading _setIconPopupInActive(E2_ResourceIcon iconPopupInActive) {
		this.iconPopupInActive = iconPopupInActive;
		return this;
	}
	
	public E2_PopupWithButtonCascading _setIconPopupActiveAndRender(E2_ResourceIcon iconPopupActive) {
		this.iconPopupActive = iconPopupActive;
		return this._render();
	}


	public E2_PopupWithButtonCascading _setIconPopupInActiveAndRender(E2_ResourceIcon iconPopupInActive) {
		this.iconPopupInActive = iconPopupInActive;
		return this._render();
	}

	public E2_PopupWithButtonCascading _setButtonText(MyE2_String buttonText) {
		this.buttonText = buttonText;
		return this;
	}


	public E2_PopupWithButtonCascading _setActionAgentFactory(IfActionAgentFactory actionAgentFactory) {
		this.actionAgentFactory = actionAgentFactory;
		return this;
	}


	public E2_PopupWithButtonCascading _setContainer_width(Integer p_container_width) {
		this.container_width = p_container_width;
		return this;
	}


	public E2_PopupWithButtonCascading _setHeightPerEntry(int heightPerEntry) {
		this.iHeightPerEntry = heightPerEntry;
		return this;
	}


	public E2_PopupWithButtonCascading _setMaxNumbersItemsInPopup(int maxNumbersItemsInPopup) {
		this.iMaxNumbersItemsInPopup = maxNumbersItemsInPopup;
		return this;
	}
 	
	/**
	 * sucht alle buttons in allen eintraegen raus, auch in tochterelementen
	 * @return
	 * @throws myException
	 */
	public VEK<MenueEntry> getRealMenueButtonEntrys() throws myException {
		VEK<MenueEntry> v = new VEK<MenueEntry>();
		
		for (MenueEntry o: this.vMenue) {
			v._a(o.getRealMenueButtonEntrys());
		}

		return v;
	}

	
	/**
	 * sucht alle buttons in allen eintraegen raus, auch in tochterelementen
	 * @return E2_Button - objecte
	 * @throws myException
	 */
	public VEK<E2_Button> getRealMenueButtons() throws myException {
		VEK<E2_Button> v_buttons = new VEK<E2_Button>();
		
		VEK<MenueEntry> v = this.getRealMenueButtonEntrys();
		
		for (MenueEntry o: v) {
			v_buttons._a(o.getButtonEndPoint());
		}

		return v_buttons;
	}


	
	
	/**
	 * 
	 * @param dbVal
	 * @return MenueEntry or null
	 * @throws myException 
	 */
	public MenueEntry findDbVal(String dbVal) throws myException {
		VEK<MenueEntry> v = this.getRealMenueButtonEntrys();
		
		for (MenueEntry m: v) {
			if (m.getDbVal().equals(dbVal)) {
				return m;
			}
		}
		return null;
	}
	

	
	/**
	 * 
	 * @param userText
	 * @return MenueEntry or null
	 * @throws myException 
	 */
	public MenueEntry findMenueEntry(String userText) throws myException {
		VEK<MenueEntry> v = this.getRealMenueButtonEntrys();
		
		for (MenueEntry m: v) {
			if (m.getText().equals(userText)) {
				return m;
			}
		}
		return null;
	}

	
	
	
	
	public static class MenueEntry {
		
		private String  					m_text = null;      	//menuetext
		private String  					m_dbVal = null;			//ergbnis beim klicken (falls endpunkt)
		private VEK<MenueEntry>   			m_SubMenue = null;		//submenue 
		
		//hier werden nach dem rendern die objecte gehalten
		private E2_Button     				m_endPointButton = null;  //variante "button"
		private E2_PopupWithButtonCascading m_popupWithButtonCascading = null;   //variante subMenue
		
		private Font  						m_font = new E2_FontPlain();
		
		/**
		 * @param m_text
		 * @param m_id
		 */
		public MenueEntry(String m_text, String m_id) {
			super();
			this.m_text = m_text;
			this.m_dbVal = m_id;
		}
		
		
		/**
		 * @param m_text
		 * @param m_vSubMenue
		 */
		public MenueEntry(String m_text, Vector<MenueEntry> m_vSubMenue) {
			super();
			this.m_text = m_text;
			this.m_SubMenue = new VEK<MenueEntry>()._a(m_vSubMenue);
		}
		
		public boolean isButton() {
			return (this.m_dbVal!=null);
		}
		
		public boolean isMenue() {
			return (this.m_SubMenue!=null);
		}

		public String getText() {
			return m_text;
		}

		public String getDbVal() {
			return m_dbVal;
		}


		public VEK<MenueEntry> getMenue() {
			return m_SubMenue;
		}
		
		/**
		 * sucht alle buttons in allen eintraegen raus, auch in tochterelementen
		 * @return
		 * @throws myException
		 */
		public VEK<MenueEntry> getRealMenueButtonEntrys() throws myException {
			VEK<MenueEntry> v = new VEK<MenueEntry>();
			
			if (this.isMenue()) {
				for (MenueEntry o: this.m_SubMenue) {
					if (o.isButton()) {
						v._a(o);
					} else if (o.isMenue()) {
						v._a(o.getRealMenueButtonEntrys());
					} else {
						throw new myException(this, "Definition-Error: Vector-Members must be types PAIR<String, Long>  or PAIR<String, Vector<PAIR<String,Long>>> !");
					}
				}
			} else {
				v._a(this);
			}

			return v;
		}


		public E2_Button getButtonEndPoint() {
			return m_endPointButton;
		}


		public void setButtonEndPoint(E2_Button m_endPointButton) {
			this.m_endPointButton = m_endPointButton;
		}


		public E2_PopupWithButtonCascading getPopupWithButtonCascading() {
			return m_popupWithButtonCascading;
		}


		public void setPopupWithButtonCascading(E2_PopupWithButtonCascading m_popupWithButtonCascading) {
			this.m_popupWithButtonCascading = m_popupWithButtonCascading;
		}


		public MenueEntry setFont(Font font) {
			this.m_font = font;
			return this;
		}


		public Font getFont() {
			return m_font;
		}
	}


	
	/*
	 * bei eintraegen von untermenus muessen beim klick auf ein menue alle anderen menuse gleicher ebene einge 
	 */
	private class CloseActionAllOtherPopups extends XX_ActionAgent {

		private MenueEntry m_entry =null;
		
		/**
		 * @param entry
		 */
		public CloseActionAllOtherPopups(MenueEntry entry) {
			super();
			this.m_entry = entry;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			for (MenueEntry m: vMenue) {
				if (m != m_entry && m.isMenue()) {
					m.getPopupWithButtonCascading().getPopUp().setExpanded(false);
				}
			}
		}
	}
	
	
	

	public static interface IfActionAgentFactory {
		public VEK<XX_ActionAgent> getActionAgents(MenueEntry entry) throws myException;
	}





     


	
}
