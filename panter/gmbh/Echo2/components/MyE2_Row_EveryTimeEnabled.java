package panter.gmbh.Echo2.components;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_Row_EveryTimeEnabled extends MyE2_Row
{
	
	
	
	public MyE2_Row_EveryTimeEnabled()
	{
		super();
	}

	public MyE2_Row_EveryTimeEnabled(MutableStyle oStyle)
	{
		super(oStyle);
	}

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		//hier deaktiviert, immer enabled (z.B. als Listencontainer)
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Row_EveryTimeEnabled oRowRueck = new MyE2_Row_EveryTimeEnabled();
		oRowRueck.setStyle(this.getStyle());
		oRowRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRowRueck));
		return oRowRueck;
	}

	

	
}
