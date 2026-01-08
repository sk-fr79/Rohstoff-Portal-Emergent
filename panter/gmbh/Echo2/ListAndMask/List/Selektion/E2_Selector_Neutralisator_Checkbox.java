package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class E2_Selector_Neutralisator_Checkbox extends XX_ListSelektor_Neutralisator
{
	private MyE2_CheckBox  oCB = null;
	
	public E2_Selector_Neutralisator_Checkbox(MyE2_CheckBox CB)
	{
		super();
		this.oCB = CB;
	}

	@Override
	public void set_to_Neutral() throws myException
	{
		this.oCB.setSelected(false);
	}

}
