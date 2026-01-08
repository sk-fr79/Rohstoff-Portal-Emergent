package panter.gmbh.Echo2.ActionEventTools.Break4Popup;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_ExecuterReturnsMV;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class E2_Break4Popup {

	private E2_BasicModuleContainer    popupContainer = null;
	private E2_Break4PopupController   break4PopupController = null; 
	
	//zeigt an, ob der unterbrecher akktiviert werden muss
	private boolean  relevant4break = false;
	
	//jeder Break enthaelt einen eigenen Container, um sachverhalte darzustellen
	public abstract E2_BasicModuleContainer  generatePopUpContainer() throws myException;
	
	//hier wird die pruefung durchgefuehrt
	protected abstract boolean  check4break(MyE2_MessageVector mv) throws myException;

//	private MyE2_MessageVector          		mv = new MyE2_MessageVector();   //sammelt die Messages innerhalb der unterbrechung

	
	private int   								popupWidth = 600;
	private int   								popupHeight = 600;
	
	private int   								popupLeft = 300;
	private int   								popupTop = 300;
	
	private MyE2_String  						s_title = new MyE2_String("?titel?");

	private E2_BtSaveAndContinue       			ownSaveButton = (E2_BtSaveAndContinue)new E2_BtSaveAndContinue(this)._tr("OK");
	private E2_BtCancelAndClosePopups 	 		ownCloseButton = (E2_BtCancelAndClosePopups)new E2_BtCancelAndClosePopups(this)._tr("Cancel");
	
	
	//2020-10-21: weitere listener before der saveAndCloseEvent ausgefuehrt wird
	private VEK<IF_ExecuterReturnsMV<E2_Break4Popup>> executersBeforeSave = new VEK<IF_ExecuterReturnsMV<E2_Break4Popup>>();
	
	/**
	 * diese methode wird in E2_Break4PopupController ausgefuehrt
	 * @throws myException
	 */
	public void executeCheck(MyE2_MessageVector mv) throws myException {
		this.relevant4break = this.check4break(mv);
	}
	
	/**
	 * diese methode wird in E2_Break4PopupController ausgefuehrt
	 * @throws myException
	 */
	public void popupIfRelevant() throws myException {
		if (this.relevant4break) {
			this.break4PopupController.increasePopupCounter();
			
			this.popupContainer = this.generatePopUpContainer();
			this.popupContainer.set_oExtLeftPos(new Extent(this.popupLeft));
			this.popupContainer.set_oExtTopPos(new Extent(this.popupTop));
			this.popupContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(this.popupWidth), new Extent(this.popupHeight), this.s_title);
			this.popupContainer.get_oWindowPane().setStyle(MyE2_WindowPane.STYLE_WINDOW_STANDARD(false));
			
			if (this.break4PopupController.getPopupCounter()==1) {
				//startpositionswerte abfragen
				if (this.popupContainer.get_oWindowPane().getPositionX()!=null && this.popupContainer.get_oWindowPane().getPositionY()!=null) {
					int x = this.popupContainer.get_oWindowPane().getPositionX().getValue();
					int y = this.popupContainer.get_oWindowPane().getPositionY().getValue();
					if (x<30 || x>1300) {
						x=30;
					}
					if (y<30 || y>1000) {
						y=30;
					}
					this.break4PopupController.setLeftPosOfFistPopup(x);
					this.break4PopupController.setTopPosOfFistPopup(y);
				}
			}
			
			if (this.break4PopupController.getLeftPosOfFistPopup()>0 && this.break4PopupController.getTopPosOfFistPopup()>0 ) {
				int iVerschiebung = (this.break4PopupController.getPopupCounter()-1)*10;
				this.popupContainer.get_oWindowPane().setPositionX(new Extent(this.break4PopupController.getLeftPosOfFistPopup()+iVerschiebung));
				this.popupContainer.get_oWindowPane().setPositionY(new Extent(this.break4PopupController.getTopPosOfFistPopup()+iVerschiebung));
			}
		}
	}

	
	
	public boolean isRelevant4Break() {
		return relevant4break;
	}

	
	public void setRelevant4Break(boolean canBeDone) {
		this.relevant4break = canBeDone;
	}

	public E2_Break4PopupController getBreak4PopupController() {
		return break4PopupController;
	}

	public void setBreak4PopupController(E2_Break4PopupController p_break4PopupController) {
		this.break4PopupController = p_break4PopupController;
	}

	
	
	

	
	// alle anhaengigen fenster schliessen
	public void closeAllPopups() throws myException {
		//DEBUG.System_println("Anzahl breaks: "+this.getBreak4PopupController().getvBreaks().size());
		
		for (E2_Break4Popup break4popup: this.getBreak4PopupController().getvBreaks()) {
			if (break4popup.popupContainer!=null && break4popup.popupContainer.IS_Popup()) {
				break4popup.popupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
	}

	public E2_BasicModuleContainer getPopupContainer() {
		return popupContainer;
	}

//	public MyE2_MessageVector getMV() {
//		return mv;
//	}

	public E2_BtSaveAndContinue getOwnSaveButton() {
		return ownSaveButton;
	}

	public E2_BtCancelAndClosePopups getOwnCloseButton() {
		return ownCloseButton;
	}

	public int getPopupWidth() {
		return popupWidth;
	}

	public int getPopupHeight() {
		return popupHeight;
	}

	public MyE2_String getTitle() {
		return s_title;
	}

	public void setPopupWidth(int popupWidth) {
		this.popupWidth = popupWidth;
	}

	public void setPopupHeight(int popupHeight) {
		this.popupHeight = popupHeight;
	}

	public void setTitle(MyE2_String s_title) {
		this.s_title = s_title;
	}

	public int getPopupLeft() {
		return popupLeft;
	}

	public int getPopupTop() {
		return popupTop;
	}

	public void setPopupLeft(int popupLeft) {
		this.popupLeft = popupLeft;
	}

	public void setPopupTop(int popupTop) {
		this.popupTop = popupTop;
	}
	
	public E2_Break4Popup _setLeftPos(int iLeftPos) {
		this.popupLeft = iLeftPos;
		return this;
	}
	public E2_Break4Popup _setTopPos(int iTopPos)	  {
		this.popupTop = iTopPos;
		return this;
	}
	public E2_Break4Popup _setWidth(int iWidth)	 {	
		this.popupWidth =iWidth;				
		return this;
	}

	public E2_Break4Popup _setHeight(int iHeight)	 {	
		this.popupHeight = iHeight;				
		return this;
	}

	/**
	 * 2020-10-21: martin
	 * @return the executersBeforeSave
	 */
	public VEK<IF_ExecuterReturnsMV<E2_Break4Popup>> getExecutersBeforeSave() {
		return executersBeforeSave;
	}

	/**
	 * @author martin
	 * @date 21.10.2020
	 *
	 * @param executer
	 * @return
	 */
	public E2_Break4Popup _registerExecuterBeforeSave(IF_ExecuterReturnsMV<E2_Break4Popup> executer) {
		this.executersBeforeSave._a(executer);
		return this;
	}
	
	
	/**
	 * nach jedem ok geht der controller die breaks wieder durch.
	 * bei jedem checkforBreak-aufruf ist es deshalb wichtig, ob der Break in dem durchlauf bereits
	 * gestartet war.
	 * @author martin
	 * @date 29.10.2020
	 *
	 * @return
	 */
	public boolean isFirstCallInCascade() {
		return (this.getBreak4PopupController().getHmCounter().get(this)==0);
	}
	
	
}
