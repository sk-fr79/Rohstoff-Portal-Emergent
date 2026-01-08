/**
 * rohstoff.Echo2BusinessLogic.CONTAINERTYP
 * @author manfred
 * @date 08.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL;

/**
 * @author manfred
 * @date 08.12.2017
 *
 */
public class E2_ListSelector_JaNeinEgal  extends XX_ListSelektor {

	BTN_JaNeinEgal _button = null;
	
	private HashMap<BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL,String> hmSql = new HashMap<>();
	
	
	
	/**
	 * @author manfred
	 * @date 08.12.2017
	 *
	 */
	public E2_ListSelector_JaNeinEgal(BTN_JaNeinEgal button ) {
		_button = button;
	}

	
	public E2_ListSelector_JaNeinEgal setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL btnZustand,String Sql){
		hmSql.put(btnZustand, Sql);
		return this;
	}
	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_WhereBlock()
	 */
	@Override
	public String get_WhereBlock() throws myException {
		return hmSql.get(_button.getZustand());
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_oComponentForSelection()
	 */
	@Override
	public Component get_oComponentForSelection() throws myException {
		return _button;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_oComponentWithoutText()
	 */
	@Override
	public Component get_oComponentWithoutText() throws myException {
		return _button;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#add_ActionAgentToComponent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
	 */
	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		// TODO Auto-generated method stub
		_button._aaa(oAgent);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#doInternalCheck()
	 */
	@Override
	public void doInternalCheck() {
		
	}


}
