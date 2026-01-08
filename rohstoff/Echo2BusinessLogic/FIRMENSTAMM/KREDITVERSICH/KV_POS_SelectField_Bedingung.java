package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.SelectField;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class KV_POS_SelectField_Bedingung extends MyE2_DB_SelectField 
{

	public KV_POS_SelectField_Bedingung(SQLField osqlField, int iWidth) throws myException 
	{
		super(osqlField);
	
		E2_DropDownSettings ddBedingung = new E2_DropDownSettings( 	"JT_KREDITLIMIT_BEDINGUNG", 
																	"BEDINGUNG", 
																	"ID_KREDITLIMIT_BEDINGUNG",
																	null, 
																	null, 
																	true,
																	"BEDINGUNG");
		this.set_ListenInhalt(ddBedingung.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
		this.EXT().set_STYLE_FACTORY(new ownStyleFactory_SelectField());
	}

	private class ownStyleFactory_SelectField extends XXX_StyleFactory
	{

		public MutableStyle get_Style(boolean bEnabled, boolean bNullable,	boolean bError)
		{
			MutableStyle oStyle = new MutableStyle();
			
			oStyle.setProperty( SelectField.PROPERTY_BACKGROUND, new E2_ColorBase(0)); 
			oStyle.setProperty( SelectField.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
			oStyle.setProperty( SelectField.PROPERTY_INSETS, new Insets(2,0,0,2)); 
			oStyle.setProperty( SelectField.PROPERTY_FONT, new E2_FontPlain());
			oStyle.setProperty( SelectField.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
			
			if (bEnabled)
			{
				if (bError)
				{
					oStyle.setProperty( SelectField.PROPERTY_BACKGROUND, Color.YELLOW);
				}
				else
				{
					oStyle.setProperty(SelectField.PROPERTY_BACKGROUND, new E2_ColorEditBackground());
				}
				if (!bNullable)
				{
					oStyle.setProperty( SelectField.PROPERTY_BORDER, new Border(1, Color.RED, Border.STYLE_SOLID)); 
				}
			}
			else
			{
				oStyle.setProperty( SelectField.PROPERTY_BACKGROUND, new E2_ColorLLight()); 
			}
			
			return oStyle;
		}

	}

}
