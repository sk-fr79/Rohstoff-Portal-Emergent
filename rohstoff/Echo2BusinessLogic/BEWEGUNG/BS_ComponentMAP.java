package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Map.Entry;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.dataTools.SQLFieldMAP;


public class BS_ComponentMAP extends E2_ComponentMAP
{

	public BS_ComponentMAP(SQLFieldMAP sqlfieldMAP)
	{
		super(sqlfieldMAP);
	}

	
	/*
	 * methode um labels mit dem eintrag #FW# in solche mit einem fremdwaehrungssymbol umzustellen
	 */
	public void set_FremdWaehrungsSymbol(String Symbol)
	{
		for (Entry<String, MyE2IF__Component> oEntry: this.entrySet())
		{
			if (oEntry.getValue() instanceof MyE2_Label)
			{
				((MyE2_Label)oEntry.getValue()).set_ReplaceText("#FW#", Symbol);				
			}
		}
	}

}
