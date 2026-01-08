package panter.gmbh.Echo2.staticStyles;

import nextapp.echo2.app.MutableStyle;

public class E2_MutableStyle extends MutableStyle
{

	private MutableStyle oStyleDisabled = new MutableStyle();

	public E2_MutableStyle()
	{
		super();
		this.setProperty("", "");
	}

	public void setProperty(String key, Object wert)
	{
		super.setProperty(key, wert);
		this.oStyleDisabled.setProperty(key, wert);
	}


	public void setProperty(String key, Object wertEnabled, Object wertDisabled)
	{
		super.setProperty(key, wertEnabled);
		this.oStyleDisabled.setProperty(key, wertDisabled);
	}
	
	
	public MutableStyle get_oStyleDisabled()
	{
		return oStyleDisabled;
	}

	

	/**
	 * 
	 * 
	 * @author martin
	 * @date 24.09.2019
	 *
	 * @param key
	 * @param wertEnabled
	 * @param wertDisabled
	 * @return
	 */
	public E2_MutableStyle _setProp(String key, Object wertEnabled, Object wertDisabled) {
		this.setProperty(key, wertEnabled, wertDisabled);
		return this;
	}
}
