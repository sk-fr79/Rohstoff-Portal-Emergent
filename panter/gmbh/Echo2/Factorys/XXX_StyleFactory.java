package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.MutableStyle;

public abstract class XXX_StyleFactory
{
	
	/**
	 * 
	 * @param bEnabled
	 * @param bNullable
	 * @param bError
	 * @return
	 */
	public abstract MutableStyle get_Style(boolean bEnabled, boolean bNullable, boolean bError);
}
