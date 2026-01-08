/**
 * 
 */
package panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class Break4Confirm extends E2_Break4Popup {

	
	private MyE2_String   		s_windowTitle = 	S.ms("Bitte bestätigen ");
	private MyE2_String   		s_title = 			S.ms("Bitte bestätigen ");
	private VEK<MyE2_String>	v_meldungsblock = 	new VEK<MyE2_String>()._a(S.ms("Sicher ?"));
	private MyE2_String	   		s_textForYes = 		S.ms("JA");
	private MyE2_String	   		s_textForNo = 		S.ms("NEIN");
	
	
	
	/**
	 * 
	 */
	public Break4Confirm() {
		super();
		this._setWidth(400);
		this._setHeight(200);
		this.set();
	}

	private void set() {
		this.setTitle(this.s_windowTitle);
		this.getOwnSaveButton()._t(this.s_textForYes.CTrans())._s_BorderText();
		this.getOwnCloseButton()._t(this.s_textForNo.CTrans())._s_BorderText();;
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#generatePopUpContainer()
	 */
	@Override
	public E2_BasicModuleContainer generatePopUpContainer() throws myException {
		return this.fillContainer(new E2_BasicModuleContainer());
	}

	
	public E2_BasicModuleContainer fillContainer(E2_BasicModuleContainer container) {
		container.set_cADDON_TO_CLASSNAME(S.NN(this.getBreak4PopupController().getClass().getName()));  //damit die groesse individuell ist

		E2_Grid g = new E2_Grid()._setSize(this.getPopupWidth()-10)._a(new RB_lab()._t(this.s_title)._b()._fsa(2), new RB_gld()._ins(2,4,4,2));
		for (MyE2_String s: this.v_meldungsblock) {
			g._a(new RB_lab()._t(s), new RB_gld()._ins(2,2,2,2));
		}
		
		E2_Grid gButtons = new E2_Grid()._setSize(this.getPopupWidth()/2,this.getPopupWidth()/2);
		gButtons._a(this.getOwnSaveButton(),new RB_gld()._center_mid()._ins(2,2,5,2))._a(this.getOwnCloseButton(),new RB_gld()._center_mid()._ins(5,2,2,2));
		
		g._a(gButtons, new RB_gld()._ins(2, 10, 2, 2));
		
		container.add(g,E2_INSETS.I(10,5,10,5));
		return container;
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#check4break(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	protected boolean check4break(MyE2_MessageVector mv) throws myException {
		return (this.getBreak4PopupController().getHmCounter().get(this)==0);
	}




	public MyE2_String getWindowTitle() {
		return s_windowTitle;
	}




	public MyE2_String getTitle() {
		return s_title;
	}




	public MyE2_String getTextForYes() {
		return s_textForYes;
	}




	public MyE2_String getTextForNo() {
		return s_textForNo;
	}


	public Break4Confirm _setWindowTitle(MyE2_String s_windowTitle) {
		this.s_windowTitle = s_windowTitle;
		this.set();
		return this;
	}


	public Break4Confirm _setTitle(MyE2_String s_title) {
		this.s_title = s_title;
		return this;
	}


	public Break4Confirm _setTextForYes(MyE2_String s_textForYes) {
		this.s_textForYes = s_textForYes;
		this.set();
		return this;
	}


	public Break4Confirm _setTextForNo(MyE2_String s_textForNo) {
		this.s_textForNo = s_textForNo;
		this.set();
		return this;
	}

	public Break4Confirm _clearInfoBlock() {
		this.v_meldungsblock.clear();
		return this;
	}
	public Break4Confirm _addInfoTextLine(MyE2_String s) {
		this.v_meldungsblock._a(s);
		return this;
	}

	
}
