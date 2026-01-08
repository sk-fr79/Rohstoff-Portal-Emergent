package panter.gmbh.Echo2.components;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_Column_IMMER_ENABLED extends MyE2_Column
{

	public MyE2_Column_IMMER_ENABLED()
	{
		super();
	}

	public MyE2_Column_IMMER_ENABLED(MutableStyle style)
	{
		super(style);
	}

	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Column_IMMER_ENABLED  oColCopy = new MyE2_Column_IMMER_ENABLED();
		oColCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oColCopy));

		oColCopy.setStyle(this.getStyle());
		
		return oColCopy;
	}

}
