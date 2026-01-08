/**
 * 
 */
package panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.Break4Popup.MyE2_Alarm_Message_Break4Popup;
import panter.gmbh.Echo2.Messaging.Break4Popup.MyE2_Info_Message_Break4Popup;
import panter.gmbh.Echo2.Messaging.Break4Popup.MyE2_Warning_Message_Break4Popup;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * Sammelt spezielle messages aus der maskenvalidierung und zeigt fuer jede meldung dieser art einen
 * Block im Popup an. Jede meldung muss bestaetigt werden.
 */
public class Break4Mask_CheckWarnings4PopupAtSave extends E2_Break4Popup {

	private MyE2_String   		s_windowTitle = 	S.ms("Bitte bestätigen Sie alle angezeigten Meldungen ");
	private MyE2_String   		s_title = 			S.ms("Achtung!");
	private MyE2_String	   		s_textForYes = 		S.ms("Weiter");
	private MyE2_String	   		s_textForNo = 		S.ms("Abbruch");
	
	private int  				i_width = 600;   			//hoehe pro angezeigter meldung
	private int  				i_height_4_eachRow = 40;   //hoehe pro angezeigter meldung 
	
	private RB_ComponentMapCollector 	m_mapCollector;
	
	private VEK<meldungAndButton>       v_messages4Popup = new VEK<meldungAndButton>();

	private E2_Grid                     grid4Messages = new E2_Grid()._setSize(i_width-50,50);     //immer eine message und ein Button
	
	private E2_BasicModuleContainer    container = null;
	
	/**
	 * 
	 * @param mapCollector
	 * @param exclude
	 */
	public Break4Mask_CheckWarnings4PopupAtSave(RB_ComponentMapCollector  mapCollector) {
		super();
		this.m_mapCollector = mapCollector;
		
		this._setWidth(i_width);
		this._setHeight(4*i_height_4_eachRow);
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
		this.container = new E2_BasicModuleContainer();
		
		container.set_cADDON_TO_CLASSNAME(S.NN(this.getBreak4PopupController().getClass().getName()));  //damit die groesse individuell ist

		this.grid4Messages._clear();
		
		container.add(this.grid4Messages,E2_INSETS.I(2,2,2,2));
		
		this.renderMessageGrid(container);
		
		return container;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#check4break(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	protected boolean check4break(MyE2_MessageVector mv) throws myException {
		boolean popupZaehler =(this.getBreak4PopupController().getHmCounter().get(this)==0);

		this.v_messages4Popup.clear();
		
		MyE2_MessageVector mv1 = this.m_mapCollector.rb_SIMULATE_SAVE_VALIDATION_CYCLE();
		
		
		for (MyE2_Message m: mv1) {
			if (m instanceof MyE2_Alarm_Message_Break4Popup || m instanceof MyE2_Warning_Message_Break4Popup || m instanceof MyE2_Info_Message_Break4Popup) {
				this.v_messages4Popup.add(new meldungAndButton(m));
			}
		}
		
		return this.v_messages4Popup.size()>0  && popupZaehler;
	}


	private void renderMessageGrid(E2_BasicModuleContainer container ) throws myException {
		
		this.grid4Messages._clear();
	
		
		int iCount = 2;   //zwei fuer den rand
		for (meldungAndButton m: this.v_messages4Popup) {
			if (!m.istBestaetigt) {
				iCount++;
				RB_gld ld = new RB_gld()._ins(3,2,3,2)._left_top();
				if 			(m.message instanceof MyE2_Info_Message_Break4Popup) {
					ld._col(new E2_ColorEditBackground());
				} else if 	(m.message instanceof MyE2_Warning_Message_Break4Popup) {
					ld._col(new E2_ColorHelpBackground());
				} else {
					ld._col(new E2_ColorAlarm());
				}
				this.grid4Messages._a(m.message.get_cMessage().CTrans(), ld);
				this.grid4Messages._a(m.bt_ok, new RB_gld()._center_mid()._ins(3,2,3,2));
			}
		}

		if (iCount>6) {
			iCount=6;
		}
		
		if (container.get_oWindowPane()!=null) {
			container.get_oWindowPane().setWidth(new Extent(this.i_width));
			container.get_oWindowPane().setHeight(new Extent(iCount*this.i_height_4_eachRow));
		} else {
			container.set_oExtWidth(new Extent(this.i_width));
			container.set_oExtHeight(new Extent(iCount*this.i_height_4_eachRow));
		}
		
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


	public Break4Mask_CheckWarnings4PopupAtSave _setWindowTitle(MyE2_String s_windowTitle) {
		this.s_windowTitle = s_windowTitle;
		this.set();
		return this;
	}


	public Break4Mask_CheckWarnings4PopupAtSave _setTitle(MyE2_String s_title) {
		this.s_title = s_title;
		return this;
	}


	public Break4Mask_CheckWarnings4PopupAtSave _setTextForYes(MyE2_String s_textForYes) {
		this.s_textForYes = s_textForYes;
		this.set();
		return this;
	}


	public Break4Mask_CheckWarnings4PopupAtSave _setTextForNo(MyE2_String s_textForNo) {
		this.s_textForNo = s_textForNo;
		this.set();
		return this;
	}


	public boolean isAllDone() {
		boolean bDone = true;
		for (meldungAndButton m: this.v_messages4Popup) {
			if (!m.istBestaetigt) {
				bDone = false;
				break;
			}
		}
		return bDone;
	}

	
	private class meldungAndButton {
		private MyE2_Message message = null;
		private E2_Button    bt_ok  = null;
		private boolean      istBestaetigt = false;
		
		/**
		 * @param message
		 */
		public meldungAndButton(MyE2_Message message) {
			super();
			this.message = message;
			
			this.bt_ok = new E2_Button()._tr("OK")._standard_text_button()._aaa(new ownAction());
		}

		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				istBestaetigt=true;
				//wenn der letzte bestaetigt ist, dann fenster zu ...
				if (isAllDone()) {
					Break4Mask_CheckWarnings4PopupAtSave.this.getPopupContainer().CLOSE_AND_DESTROY_POPUPWINDOW(true);
				} else {
					renderMessageGrid(container);
				}
			}
		}
	}
	
}
