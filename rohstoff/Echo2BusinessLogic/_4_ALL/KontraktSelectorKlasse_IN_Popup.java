package rohstoff.Echo2BusinessLogic._4_ALL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class KontraktSelectorKlasse_IN_Popup extends E2_BasicModuleContainer
{
	private ownSelector  						oSelector = null;
	private int   								iSpaltenBreite 	= 300;
	private int   								iSpaltenHoehe 	= 300;
	
	
	public KontraktSelectorKlasse_IN_Popup(int spaltenBreite,int spaltenHoehe) throws myException
	{
		super();
		this.iSpaltenBreite = spaltenBreite;
		this.iSpaltenHoehe = spaltenHoehe;
		
		this.oSelector = new ownSelector();
		
		this.add(this.oSelector, E2_INSETS.I_2_2_2_2);
		this.oSelector.START("",true);
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(2*this.iSpaltenBreite+50), new Extent(this.iSpaltenHoehe+120), new MyE2_String("Adressauswahl ..."));
		
		
	}
	
	private class ownSelector extends KontraktSelector
	{

		public ownSelector() throws myException
		{
			super("EK");
		}

		@Override
		public void actionForExit() throws myException
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionForGotoSecondCol() throws myException
		{
			// TODO Auto-generated method stub
			
		}
		
	}




}
