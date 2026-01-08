package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.Factorys.StyleFactory_SelectField;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField_20Hoch_Font2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class FU___MENGENTEXT_STYLE_FACTORY extends StyleFactory_TextField_20Hoch_Font2
{
	private String   		 	cFieldnameNormalAbRechnen = null;
	private boolean   			NormalField = false;
	private E2_ComponentMAP  	oMap = null;
	

	/**
	 * 
	/**
	 * 
	 * @param fieldnameNormalAbRechnen - Feldname der Checkbox Buchung=Lademenge ...
	 * @param MAP
	 * @param bNormalField             - true, wenn das standard-feld damit belegt wird
	 */
	public FU___MENGENTEXT_STYLE_FACTORY(String fieldnameNormalAbRechnen, E2_ComponentMAP MAP,  boolean bNormalField)
	{
		super();
		this.cFieldnameNormalAbRechnen = fieldnameNormalAbRechnen;
		this.NormalField = bNormalField;
		this.oMap = MAP;
	}

	@Override
	public MutableStyle get_Style(boolean enabled, boolean nullable,boolean error)
	{
		MutableStyle oStyle = super.get_Style(enabled, nullable, error);
		
		try
		{
			boolean bNormalAbrechnen = ((MyE2_DB_CheckBox)this.oMap.get__Comp(this.cFieldnameNormalAbRechnen)).isSelected();

			if (NormalField)    //der style gilt fuer das "normale" abrechnungsfeld
			{
				if (bNormalAbrechnen && enabled)
				{
					oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(2, Color.BLACK, Border.STYLE_SOLID)); 
				}
			}
			else   //der style gilt fuer das "normale" abrechnungsfeld
			{
				if (!bNormalAbrechnen && enabled)
				{
					oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(2, Color.BLACK, Border.STYLE_SOLID)); 
				}
			}
		} 
		catch (myException e)
		{
			
			e.printStackTrace();
		}
		
		return oStyle;
	}
	
}
