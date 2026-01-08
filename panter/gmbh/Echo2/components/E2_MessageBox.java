/**
 * 
 */
package panter.gmbh.Echo2.components;

import static panter.gmbh.indep.S.ms;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class E2_MessageBox extends E2_BasicModuleContainer {
	


	private E2_Button   		btYes = new E2_Button()._tr("Ja")._standard_text_button()._backDDark()._aaa(new ownActionCloseWindow());
	private E2_Button   		btNo = new E2_Button()._tr("Nein")._standard_text_button()._backDDark()._aaa(new ownActionCloseWindow());
	
	private MyE2_String 		titleOfPopup = new MyE2_String("Frage:");
	private VEK<MyE2_String> 	vInfos    = 	new VEK<>();
	
	private E2_Grid     grid = new E2_Grid();

	/**
	 * 
	 */
	public E2_MessageBox() {
		super();
		this.add(this.grid, E2_INSETS.I(5,5,5,5));
	}

	
	public E2_MessageBox _show(int iWidth, int iHeight) throws myException {
		this.arrangeContent(grid);
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iWidth), new Extent(iHeight),this.titleOfPopup);
		return this;
	}
	
	

	/**
	 * kann ueberschrieben werden
	 * @param g
	 */
	public void arrangeContent(E2_Grid g) throws myException {
		this.grid._clear()._setSize(new Extent(100, Extent.PERCENT));
		
		for (MyE2_String s: this.vInfos) {
			this.grid._a(new RB_lab()._t(s)._line_wrap(true), new RB_gld()._ins(2));
		}
		
		this.grid._a(new E2_Grid()._s(2)._a(this.btYes, new RB_gld()._ins(0, 0, 10, 0))._a(this.btNo, new RB_gld()._ins(0, 0, 0, 0))
						, new RB_gld()._ins(2,10,2,2));
	}
	
	
	public E2_Button getBtYes() {
		return btYes;
	}


	public E2_Button getBtNo() {
		return btNo;
	}


	public MyE2_String getTitleOfPopup() {
		return titleOfPopup;
	}


	public VEK<MyE2_String> getVInfos() {
		return this.vInfos;
	}


	public E2_MessageBox _setTitleOfPopup(MyE2_String titleOfPopup) {
		this.titleOfPopup = titleOfPopup;
		return this;
	}

	
	
	private class ownActionCloseWindow extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_MessageBox.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	
	public E2_MessageBox _setYesText(String s) {
		this.btYes.setText(S.ms(s).CTrans());
		return this;
	}

	
	public E2_MessageBox _setNoText(String s) {
		this.btNo.setText(S.ms(s).CTrans());
		return this;
	}

	
	public E2_MessageBox _setYesFont(Font f) {
		this.btYes.setFont(f);
		return this;
	}

	
	public E2_MessageBox _setNoFont(Font f) {
		this.btNo.setFont(f);
		return this;
	}

	
	public E2_MessageBox _addInfo(String s) {
		this.vInfos.add(S.ms(s));
		return this;
	}

	
	public E2_MessageBox _addInfo(MyE2_String s) {
		this.vInfos.add(s);
		return this;
	}

	
	public E2_MessageBox _addActionInFrontYesBt(IF_agentSimple agent) {
		this.btYes._aaaInFront(agent, true);
		return this;
	}
	
	public E2_MessageBox _addActionYesBt(IF_agentSimple agent) {
		this.btYes._aaa(agent);
		return this;
	}

	
	public E2_MessageBox _addActionNoBt(IF_agentSimple agent) {
		this.btNo._aaa(agent);
		return this;
	}


	
}
