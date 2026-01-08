/**
 * 
 */
package panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.QUAD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class Break4MaskCloseWhenSomethingWasChanged extends E2_Break4Popup {

	private MyE2_String   		s_windowTitle = 	S.ms("Bitte bestätigen ");
	private MyE2_String   		s_title = 			S.ms("Achtung!");
	private VEK<MyE2_String>	v_meldungsblock = 	new VEK<MyE2_String>()._a(S.ms(" Sie haben Maskenfelder verändert!"))._a(S.ms("Möchten Sie trotzdem die Maske schliessen/verlassen ?"));
	private MyE2_String	   		s_textForYes = 		S.ms("JA - Maske verlassen");
	private MyE2_String	   		s_textForNo = 		S.ms("NEIN - zurück zur Maske");
	
	private RB_ComponentMapCollector 	m_mapCollector;
	private Vector<Pair<RB_K>> 			m_exclude;
	
	

	/**
	 * 
	 * @param mapCollector
	 * @param exclude
	 */
	public Break4MaskCloseWhenSomethingWasChanged(RB_ComponentMapCollector  mapCollector, Vector<Pair<RB_K>> exclude) {
		super();
		this.m_mapCollector = mapCollector;
		this.m_exclude = exclude;
		this._setWidth(400);
		this._setHeight(250);
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
		E2_BasicModuleContainer container = new E2_BasicModuleContainer();
		
		container.set_cADDON_TO_CLASSNAME(S.NN(this.getBreak4PopupController().getClass().getName()));  //damit die groesse individuell ist

		E2_Grid g = new E2_Grid()._setSize(this.getPopupWidth()-10)._a(new RB_lab()._t(this.s_title)._b()._fsa(2), new RB_gld()._ins(2,4,4,2));
		for (MyE2_String s: this.v_meldungsblock) {
			g._a(new RB_lab()._t(s), new RB_gld()._ins(2,2,2,2));
		}
		
		//20190315: hier einen button einblenden, der die aenderungen anzeige
		g._a((E2_Button)new E2_Button()._tr("Zeige Änderungen")._setShapeStandardTextButton()._aaa(()-> {
				new PopupZeigeAenderungen();
		}), new RB_gld()._ins(10));
		
		
		E2_Grid gButtons = new E2_Grid()._setSize(this.getPopupWidth()/2,this.getPopupWidth()/2);
		gButtons._a(this.getOwnSaveButton(),new RB_gld()._center_mid()._ins(2))._a(this.getOwnCloseButton(),new RB_gld()._center_mid()._ins(2));
		
		g._a(gButtons, new RB_gld()._ins(2, 10, 2, 2));
		
		container.add(g,E2_INSETS.I(1,1,1,1));
		
		return container;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#check4break(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	protected boolean check4break(MyE2_MessageVector mv) throws myException {
		boolean popupZaehler =(this.getBreak4PopupController().getHmCounter().get(this)==0);
		boolean hasChanged = false;

		if (this.m_mapCollector.getNumberOfChangedFields(this.m_exclude)>0) {
			hasChanged=true;
		}
		return popupZaehler&&hasChanged;
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


	public Break4MaskCloseWhenSomethingWasChanged _setWindowTitle(MyE2_String s_windowTitle) {
		this.s_windowTitle = s_windowTitle;
		this.set();
		return this;
	}


	public Break4MaskCloseWhenSomethingWasChanged _setTitle(MyE2_String s_title) {
		this.s_title = s_title;
		return this;
	}


	public Break4MaskCloseWhenSomethingWasChanged _setTextForYes(MyE2_String s_textForYes) {
		this.s_textForYes = s_textForYes;
		this.set();
		return this;
	}


	public Break4MaskCloseWhenSomethingWasChanged _setTextForNo(MyE2_String s_textForNo) {
		this.s_textForNo = s_textForNo;
		this.set();
		return this;
	}

	public Break4MaskCloseWhenSomethingWasChanged _clearInfoBlock() {
		this.v_meldungsblock.clear();
		return this;
	}
	public Break4MaskCloseWhenSomethingWasChanged _addInfoTextLine(MyE2_String s) {
		this.v_meldungsblock._a(s);
		return this;
	}

	
	
	private class PopupZeigeAenderungen extends E2_BasicModuleContainer {

		public PopupZeigeAenderungen() throws myException {
			super();
			
			E2_Grid inhalt = new E2_Grid()._setSize(200,200,200,200);
			inhalt._a(new RB_lab(S.ms(("Liste der Änderungen: "))), new RB_gld()._ins(2)._span(4)._col_back_d());
			VEK<QUAD<RB_K,RB_K,String,String>> changes = m_mapCollector.getChangedFields(m_exclude);
			inhalt	._a(new RB_lab("Maske"), new RB_gld()._ins(2)._col_back_d())
					._a(new RB_lab("Feld"), new RB_gld()._ins(2)._col_back_d())
					._a(new RB_lab("alt"), new RB_gld()._ins(2)._col_back_d())
					._a(new RB_lab("neu"), new RB_gld()._ins(2)._col_back_d())
			;
			for (QUAD<RB_K, RB_K, String, String> q: changes) {
				inhalt	._a(new RB_lab(S.NN(q.getVal1().get_REALNAME(),"-")), new RB_gld()._ins(2)._col_back_d())
						._a(new RB_lab(q.getVal2().get_REALNAME()), new RB_gld()._ins(2)._col_back_d())
						._a(new RB_lab(q.getVal3()), new RB_gld()._ins(2)._col_back_d())
						._a(new RB_lab(q.getVal4()), new RB_gld()._ins(2)._col_back_d())
						;
			}
			
			this.add(inhalt, E2_INSETS.I(2,2,2,2));
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800),new Extent(800), S.ms("Änderungen"));
		}
		
	}
	
	
}
