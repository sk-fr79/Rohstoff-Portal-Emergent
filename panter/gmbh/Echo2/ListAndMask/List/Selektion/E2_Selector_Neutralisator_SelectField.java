package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class E2_Selector_Neutralisator_SelectField extends XX_ListSelektor_Neutralisator
{
	private MyE2_SelectField  oSelectField = null;
	
	public E2_Selector_Neutralisator_SelectField(MyE2_SelectField SelField)
	{
		super();
		this.oSelectField = SelField;
	}

	@Override
	public void set_to_Neutral() throws myException
	{
		this.oSelectField.setSelectedIndex(0);
	}

}
