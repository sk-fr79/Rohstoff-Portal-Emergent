/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 09.04.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SelV2;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 09.04.2019
 * kaskadierende popup-menues, jede ebene wird im gleichen grid angezeigt, der obere button ist immer ein
 * zurueckbutton, der eine ebene zurueckspringt und am ende schliesst
 */
public abstract class RB_SelectCascading<T> extends E2_Grid implements IF_RB_Component {

	private RB_KF key = null;
	private Vector<RB_Validator_Component>  validators = new Vector<>();

	//wird ausgefuehrt, wenn ein endpunkt angeklickt wird
	public abstract MyE2_MessageVector 	executeClickOnMenueButton(T target, RB_SelectCascading<T>.MenueButton menueButton) throws myException;

	public abstract void 				setShapeOfActionButton(				MenueButton button, T 		target) throws myException;
	public abstract void 				addActionsAgentsAndValidatorsToTargetButton(		MenueButton button, T 		target);
	
	public abstract void 				setShapeOfSubMenueButton(	MenueButton button, Menue 	subMenu) throws myException;
	public abstract void 				setShapeOfReturnButton(		MenueButton button, Menue 	backMenue) throws myException;

	//wird gebraucht, um einen bestehenden button in einer struktur zu finden
	public abstract boolean      		isSame(T t1, T t2);
	public abstract void 				setHighLightStatusOfActualValueButton(MenueButton bt);
	public abstract void 				resetHighLightStatusOfActualValueButton(MenueButton bt);
	
	private VEK<MenueButton>      		allMenueButtons = new VEK<>();
	
	private int   						widthBaseMenue = 300;
	private int   						widthButtonsLeftRight = 20;
	
	private ImageReference  			imageLeft = E2_ResourceIcon.get_RI("smallarrowleft.png");
	private ImageReference  			imageRight = E2_ResourceIcon.get_RI("smallarrowright.png");
	
	private Menue   					firstRenderedMenue = null;    //hauptmenue


	
	
	public RB_SelectCascading() {
		super();
		this.setSize(2);
	}


	/**
	 * 
	 * @author martin
	 * @date 17.05.2019
	 *
	 * @param hiddenValues: werte, die den status versteckt (buttons bekommen _setVisibleOnCall(false) )
	 *                    : on null: hiddenstatus reset
	 * @return
	 */
	public RB_SelectCascading<T> _setHidden(VEK<T> hiddenValues) {
		
		//hidden buttons reset
		for (MenueButton b: allMenueButtons) {
			if (b.getValue()!=null) {
				b._setVisibleOnCall(true);
			}
		}
		
		if (hiddenValues!=null) {
			//zuerst die buttons durchgehen
			for (MenueButton b: allMenueButtons) {
				if (b.getValue()!=null) {
					for (T t: hiddenValues) {
						if (this.isSame(b.getValue(), t)) {
							b._setVisibleOnCall(false);
						}
					}
				}
			}
		}
		return this;
	}
	

	
	
	public RB_SelectCascading<T> _gotoStartMenue() throws myException {
		if (this.firstRenderedMenue!=null) {
			this._renderMenue(this.firstRenderedMenue);
		}
		return this;
	}
	

	public RB_SelectCascading<T> _clearRenderingStatus() {
		this._clear();
		this.allMenueButtons.clear();
		this.firstRenderedMenue=null;
		
		return this;
	}
	
	
	
	public class Menue extends VEK<MenueButton> {

		private String m_title = null;

		public Menue(String title) {
			super();
			this.m_title=title;
		}

		
		public Menue _addItem(T target) throws myException {
			return this._addItem(target, true);
		}

		public Menue _addItem(T target, boolean visible) throws myException {
			MenueButton bt = new MenueButton()._setTarget(target)._setVisibleInRendering(visible);
			setShapeOfActionButton(bt,target);
			bt._setOwnMenue(this);
			super._a(bt);
			allMenueButtons._a(bt);
			return this;
		}

		
		public Menue _addItem(Menue targetMenu, boolean visible) throws myException {
			//zuerst an das targetmenue einen button zurueck auf dieses menue
			MenueButton back = new MenueButton()._setBackMenu(this)._setVisibleInRendering(visible);
			targetMenu._addInFront(back);
			back._setOwnMenue(targetMenu);
			setShapeOfReturnButton(back, this);
			
			//eigentlicher button ins targetmenue
			MenueButton fore = new MenueButton()._setSubMenu(targetMenu);
			fore._setOwnMenue(this);
			setShapeOfSubMenueButton(fore, targetMenu);
			super._a(fore);
			allMenueButtons._a(fore);
			return this;
	
		}
		
		public Menue _addItem(Menue targetMenu) throws myException {
			return this._addItem(targetMenu, true);
		}
		
		
		public String getTitle() {
			return m_title;
		}
	}
	
	
	public E2_Button generateImageButtonGoBackToPreviousMenu(Menue backMenue) {
		E2_Button backButton = new E2_Button();
		backButton.setIcon(imageLeft);
		backButton._aaa(()-> {_renderMenue(backMenue);});
		backButton._set_ld(new RB_gld()._center_mid());
		backButton._ttt(S.ms("Zurück ins vorige Menü"));
		return backButton;
	}
	
	public E2_Button generateImageButtonGoForewardToSubMenu(Menue subMenue) {
		E2_Button foreButton = new E2_Button();
		foreButton.setIcon(imageRight);
		foreButton._aaa(()-> {_renderMenue(subMenue);});
		foreButton._set_ld(new RB_gld()._center_mid());
		foreButton._ttt(S.ms("Zum Untermenü wechseln"));
		return foreButton;
	}
	
	
	/**
	 * ergebniss-button, fuehrt die aktion aus und beendet das menue
	 * @author martin
	 * @date 09.04.2019
	 *
	 */
	public class MenueButton extends E2_Button {

		private VEK<Object> controll = new VEK<>(); 
		//nur eines erlaubt
		private Menue subMenue = null;       // ein button fuehrt entweder zu einem folgemenue oder ...
		private Menue backMenue = null;       // ... oder zurueck ... oder ...
		private T     referedObject = null;     // ... zu einer aktion
		//nur eines erlaubt
		
		
		private boolean visibleInRendering = true;        //damit koennen shadowbereiche gebildet werden, die normalerweise ausgeblendet bleiben
		
		private boolean visibleOnCall = true;			 //normalerweise true. Es kann von aussen aber eine reihe IDs (<T>-werte verborgen werden 
		
		
		private Menue ownMenue  = null;   	    //jeder button hat ein eigenes menue, zu dem er gehoert
		
		public MenueButton() throws myException {
			super();
			this.add_oInternalActionAgent(new OwnInternalAction(this));
//			this._aaa(new OwnAction());
		}

		public MenueButton _setTarget(T refObject) throws myException {
			if (controll.size()>0) {
				throw new myException("Only one of three objects are allowed !"); 
			} else {
				controll._a(refObject);
				referedObject = refObject;
			}
			
			return this;
		}
		
		public MenueButton _setBackMenu(Menue p_backMenue) throws myException {
			if (controll.size()>0) {
				throw new myException("Only one of three objects are allowed !"); 
			} else {
				controll._a(p_backMenue);
				backMenue = p_backMenue;
			}
			
			return this;
		}
		
		public MenueButton _setSubMenu(Menue p_subMenue) throws myException {
			if (controll.size()>0) {
				throw new myException("Only one of three objects are allowed !"); 
			} else {
				controll._a(p_subMenue);
				subMenue = p_subMenue;
			}
			
			return this;
		}
		
		private class OwnInternalAction extends XX_ActionAgent {
			private MenueButton menueButton = null;
			
			
			public OwnInternalAction(MenueButton button) {
				super();
				this.menueButton=button;
			}


			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (referedObject!=null) {
					bibMSG.add_MESSAGE(executeClickOnMenueButton(referedObject,menueButton));
				} else if (subMenue!=null) {
					_renderMenue(subMenue);
				} else if (backMenue!=null) {
					_renderMenue(backMenue);
				}
			}
		}


		public Menue getSubMenue() {
			return subMenue;
		}

		public Menue getBackMenue() {
			return backMenue;
		}
		public MenueButton _setSubMenue(Menue m) {
			subMenue = m;
			return this;
		}

		public MenueButton _setBackMenue(Menue m) {
			backMenue = m;
			return this;
		}

		public T getReferedObject() {
			return referedObject;
		}

		public T getValue() {
			return referedObject;
		}
		
		public Menue getOwnMenue() {
			return ownMenue;
		}

		public MenueButton _setOwnMenue(Menue ownMenue) {
			this.ownMenue = ownMenue;
			return this;
		}


		public boolean isVisibleInRendering() {
			return visibleInRendering;
		}

		public MenueButton _setVisibleInRendering(boolean p_visibleInRendering) {
			this.visibleInRendering = p_visibleInRendering;
			return this;
		}

		public boolean isVisibleOnCall() {
			return visibleOnCall;
		}

		public MenueButton _setVisibleOnCall(boolean visibleOnCall) {
			this.visibleOnCall = visibleOnCall;
			return this;
		}
		
	}
	
	

	public void DebugMenue(Menue m) {
		DEBUG._print("");
		DEBUG._print("------------------------------------------------------------");
		DEBUG._print("Menue: ID: "+m.hashCode());
		for (MenueButton b: m) {
			DEBUG._print("Menu: "+m.m_title+": ... "+b.getText());
		}
		DEBUG._print("------------------------------------------------------------");
		DEBUG._print("");
	}
	
	
	
	public RB_SelectCascading<T>  _renderMenue(Menue menue) throws myException {
		this._clear();
		
		//DEBUG._print("e7061098-63c6-4908-a619-6787dafbe42d: Anzahl menue-members:"+menue.size());
		
//		this.DebugMenue(menue);
		if (this.firstRenderedMenue==null) {
			this.firstRenderedMenue=menue;
		}
		
		
		boolean needsBackwardCol = false;
		boolean needsForwardCol = false;

		for (MenueButton b: menue) {
			if (mustBeShown(b)) {
				if (b.getSubMenue()!=null) {
					needsForwardCol = true;
				}
				if (b.getBackMenue()!=null) {
					needsBackwardCol = true;
				}
			}
		}
		
		
		
		
		VEK<Integer>  breiten = new VEK<>();
		if (!needsBackwardCol && !needsForwardCol) {
			breiten._a(this.widthBaseMenue);
		} else if (needsBackwardCol && !needsForwardCol) {
			breiten._a(this.widthButtonsLeftRight);
			breiten._a(this.widthBaseMenue-this.widthButtonsLeftRight);
		} else if (!needsBackwardCol && needsForwardCol) {
			breiten._a(this.widthBaseMenue-this.widthButtonsLeftRight);
			breiten._a(this.widthButtonsLeftRight);
		} else {
			breiten._a(this.widthButtonsLeftRight);
			breiten._a(this.widthBaseMenue-2*this.widthButtonsLeftRight);
			breiten._a(this.widthButtonsLeftRight);
		}

		int[] a_breiten = new int[breiten.size()];
		int count = 0;
		for (Integer i: breiten) {
			a_breiten[count++]=i;
		}
		
		this._clear();
		this._setSize(a_breiten);
		
		for (MenueButton b1: menue) {
			if (mustBeShown(b1)) {
				Component b = this.wrapButton(b1);
				if (!needsBackwardCol && !needsForwardCol) {
					this._add_raw(b);
				} else if (needsBackwardCol && !needsForwardCol) {
					if (b1.getBackMenue()!=null) {
						this._add_raw(generateImageButtonGoBackToPreviousMenu(b1.getBackMenue()));
					} else {
						this._a();
					}
					this._add_raw(b);
				} else if (!needsBackwardCol && needsForwardCol) {
					this._add_raw(b);
					if (b1.getSubMenue()!=null) {
						this._add_raw(generateImageButtonGoForewardToSubMenu(b1.getSubMenue()));
					} else {
						this._a();
					}
				} else {			//braucht sowohl for, wie zurueck-buttons
					if (b1.getSubMenue()!=null) {
						this._a();
						this._add_raw(b);
						this._add_raw(generateImageButtonGoForewardToSubMenu(b1.getSubMenue()));
					} else if (b1.getBackMenue()!=null){
						this._add_raw(generateImageButtonGoBackToPreviousMenu(b1.getBackMenue()));
						this._add_raw(b);
						this._a();
					} else {
						this._a();
						this._add_raw(b);
						this._a();
					}
				}
			}
			resetHighLightStatusOfActualValueButton(b1);
		}
		return this;
	}

	
	
	
	/**
	 * deligiert alle dem RB_SelectCascading-object zugewiesenen ActionAgents und global Validators an die target-Buttons 
	 * @author martin
	 * @date 06.05.2019
	 *
	 * @return
	 */
	public RB_SelectCascading<T> _delegateActionsAndValidatorsToTargetButtons()  {
		for (RB_SelectCascading<T>.MenueButton b : allMenueButtons) {
			if (b.getReferedObject()!=null) {
				//actions werden nur an "endpunkte" angehaengt
				b.get_vActionAgents().clear();
				addActionsAgentsAndValidatorsToTargetButton(b,b.getReferedObject());
			}
		}
		
		return this;
	}
	
	

	/**
	 * kann überschrieben werden, um z.B. weitere komponenten in bestimmte zeilen einzubauen ...
	 * @author martin
	 * @date 03.05.2019
	 *
	 * @param b
	 * @return
	 */
	public Component wrapButton(MenueButton b) {
		return b;
	}


	
	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF p_key) throws myException {
		key = p_key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return validators;
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	

	public RB_SelectCascading<T> _renderStatus(T actual) throws myException {
		Menue m = findMenueOf(actual);
		if (m!=null) {
			_renderMenue(m);
			MenueButton bt = findMenueButtonOf(actual) ;
			setHighLightStatusOfActualValueButton(bt);
		 
		} else {
			throw new myException("559f80d0-5f92-11e9-8647-d663bd873d93: cannot identify value !");
		}
		 
		return this;
	}
	
	
	public Menue findMenueOf(T ob) {
		
		Menue ret = null;
		
		for (MenueButton butt: this.allMenueButtons) {
			if (this.isSame(ob, butt.referedObject)) {
				return butt.ownMenue;
			}
		}
		
		return ret;
	}
	
	
	public MenueButton findMenueButtonOf(T ob) {
		
		MenueButton ret = null;
		
		for (MenueButton butt: this.allMenueButtons) {
			if (this.isSame(ob, butt.referedObject)) {
				return butt;
			}
		}
		
		return ret;
	}
	
	
	
	
	
	public static E2_MutableStyle StyleForActionButton() {
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColorBackground = new E2_ColorBase(-10);
		Color					oColorBackgroundDisabled = new E2_ColorBase(-10);
		Color					oColorForeground = Color.BLACK;
		Color					oColorForegroundDisabled = Color.DARKGRAY;

		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisabled); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, 			new Border(1, new E2_ColorDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_BACKGROUND, new E2_ColorDDDark()); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_FOREGROUND, Color.BLACK);
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,Alignment.ALIGN_LEFT);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_TEXT_ALIGNMENT,new Alignment(Alignment.LEFT,Alignment.CENTER));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleForSubMenueButton() {
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColorBackground = new E2_ColorBase(-30);
		Color					oColorBackgroundDisabled = new E2_ColorBase(-30);
		Color					oColorForeground = Color.BLACK;
		Color					oColorForegroundDisabled = Color.DARKGRAY;
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisabled); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontBoldItalic());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,Alignment.ALIGN_LEFT);
		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleForReturnMenueButton() {
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColorBackground = new E2_ColorBase(-30);
		Color					oColorBackgroundDisabled = new E2_ColorBase(-30);
		Color					oColorForeground = Color.BLACK;
		Color					oColorForegroundDisabled = Color.DARKGRAY;

		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisabled); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontBoldItalic());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,Alignment.ALIGN_LEFT);
		
		return ostyleTextButton;
	}

	public int getWidthBaseMenue() {
		return widthBaseMenue;
	}

	public RB_SelectCascading<T> _setWidthBaseMenue(int p_widthBaseMenue) {
		this.widthBaseMenue = p_widthBaseMenue;
		return this;
	}

	public int getWidthButtonsLeftRight() {
		return widthButtonsLeftRight;
	}

	public  RB_SelectCascading<T> _setWidthButtonsLeftRight(int p_widthButtonsLeftRight) {
		this.widthButtonsLeftRight = p_widthButtonsLeftRight;
		return this;
	}

	public VEK<MenueButton> getAllMenueButtons() {
		return allMenueButtons;
	}

	
	private boolean mustBeShown(MenueButton b) {
		boolean showIt = true;
		
		if (b.getValue()!=null) {
			showIt = (b.visibleInRendering && b.visibleOnCall);
		} else if (b.getSubMenue()!=null) {
			showIt = false;
			for (MenueButton bu: b.getSubMenue()) {
				if (bu.getBackMenue()==null) {				//in die betrachtung, ob eine menue angezeigt wird, gehen nur die dortigen submenues und die aktionsbuttons, nicht die back-menues ein
					showIt = (showIt||mustBeShown(bu));
				}
			}
		}
		return showIt;
	}

	public Menue getFirstRenderedMenue() {
		return firstRenderedMenue;
	}


	
}
