/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * Ableitung der E2_NavigationList mit kompakter und erweiterter Listendarstellung (z.B. fuer Tochterlisten in Masken) 
 */
public class E2_NavigationListCompact extends E2_NavigationList {

	private VEK<Component> componentsLeft = new VEK<Component>();
	private VEK<Component> componentsRight = new VEK<Component>();
	
	
	private E2_ComponentGroupHorizontal _rowNavigationButtons;
	private MyE2_Button _buttStart;
	private MyE2_Button _buttBack;
	private MyE2_TextField _oTextPageNumber;
	private MyE2_Button _buttGoPos;
	private MyE2_Button _buttForeward;
	private MyE2_Button _buttEnd;
	private MyE2_Button _buttReload;
	private E2_Button _buttRestore;
	private MyE2_Label _labelPosInfo;
	private MyE2_Label _labelZeilenInfo;
	private MyE2_SelectField selZeilen;     		
	
	/**
	 * @throws myException
	 */
	public E2_NavigationListCompact() throws myException {
		super();
		
		this.addActionAfterRefreshPageInfo(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				_rebuildTitleLine();
			}
		});
	}

	
	public void buildRow4Navigation(	E2_ComponentGroupHorizontal _rowNavigationButtons
										, MyE2_Button _buttStart
										, MyE2_Button _buttBack
										, MyE2_TextField _oTextPageNumber
										, MyE2_Button _buttGoPos
										, MyE2_Button _buttForeward
										, MyE2_Button _buttEnd
										, MyE2_Button _buttReload
										, E2_Button   _buttRestore
										, MyE2_Label  _labelPosInfo
										, MyE2_Label  _labelZeilenInfo
										, MyE2_SelectField selZeilen) 
	{
		this._rowNavigationButtons = _rowNavigationButtons;
		this._buttStart = _buttStart;
		this._buttBack = _buttBack;
		this._oTextPageNumber = _oTextPageNumber;
		this._buttGoPos = _buttGoPos;
		this._buttForeward = _buttForeward;
		this._buttEnd = _buttEnd;
		this._buttReload = _buttReload;
		this._buttRestore = _buttRestore;
		this._labelPosInfo = _labelPosInfo;
		this._labelZeilenInfo = _labelZeilenInfo;
		this.selZeilen = selZeilen;

		this._rebuildTitleLine();
	}
	
	
	public void _rebuildTitleLine()  {
		
		E2_Grid g = new E2_Grid();
		
		if (componentsLeft!=null) {
			for (Component c: componentsLeft) {
				g._add_raw(c);
			}
			if (componentsLeft.size()>0) {
				g._a(" ", new RB_gld()._ins(3, 0, 3, 0));  //abstandshalter
			}
		}
		
		
		this.selZeilen.setWidth(new Extent(50));
		
		E2_Grid innen = new E2_Grid()._bo_no()._s(3)
						._a(new RB_lab()._lwn()._t("Seite: "+(this.get_iActualPage()+1)+" / "+this.get_vectorSegmentation().get_iZahlSegmente()),new RB_gld()._ins(2, 0, 2, 0)._left_mid())
						._a(new RB_lab()._lwn()._t("("+this.get_vectorSegmentation().size()+" gesamt)")._ttt(S.ms("Anzahl Datensätze in der momentanen Listenansicht")),new RB_gld()._ins(2, 0, 2, 0)._left_mid())
						._a(this.selZeilen,new RB_gld()._ins(10, 0, 2, 0)._left_mid())
						;
		
		g	._a(this._buttStart, new RB_gld()._ins(2, 0, 2, 0)._left_mid())
			._a(this._buttBack, new RB_gld()._ins(2, 0, 2, 0)._left_mid())
			._a(this._oTextPageNumber, new RB_gld()._ins(2, 0, 2, 0)._left_mid())
			._a(this._buttGoPos, new RB_gld()._ins(2, 0, 2, 0)._left_mid())
			._a(this._buttForeward, new RB_gld()._ins(2, 0, 2, 0)._left_mid())
			._a(this._buttEnd, new RB_gld()._ins(2, 0, 2, 0)._left_mid())
			._a(this._buttReload, new RB_gld()._ins(5, 0, 5, 0)._left_mid())
			._a(this._buttRestore, new RB_gld()._ins(5, 0, 5, 0)._left_mid())
			._a(innen, new RB_gld()._ins(5, 0, 5, 0)._left_mid())
			;

		if (componentsRight!=null) {
			if (componentsRight.size()>0) {
				g._a(" ", new RB_gld()._ins(3, 0, 3, 0));  //abstandshalter
			}
			
			for (Component c: componentsRight) {
				g._add_raw(c);
			}
		}

		g.setSize(g.getComponentCount());

		this._rowNavigationButtons.removeAll();
		this._rowNavigationButtons.add(g);
		
	}
	
	public E2_NavigationListCompact _addLeftComponent(Component c) {
		this.componentsLeft._a(c);
		this._rebuildTitleLine();
		return this;
	}
	
	
	
	public E2_NavigationListCompact _addRightComponent(Component c) {
		this.componentsRight._a(c);
		this._rebuildTitleLine();
		return this;
	}

}
	

