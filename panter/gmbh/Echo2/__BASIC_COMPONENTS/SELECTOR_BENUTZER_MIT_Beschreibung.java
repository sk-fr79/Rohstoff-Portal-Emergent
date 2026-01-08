package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class SELECTOR_BENUTZER_MIT_Beschreibung extends MyE2_Grid 
{
	private Component_USER_DROPDOWN_NEW   		    oSelUser = null;

	
	public SELECTOR_BENUTZER_MIT_Beschreibung(	int 		iTextWidth, 
												int 		iDropdownWidth, 
												MyString 	cBeschriftung) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.oSelUser = new Component_USER_DROPDOWN_NEW(false, iDropdownWidth);
		
		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0), E2_INSETS.I_0_0_0_0);
		this.add(oSelUser, E2_INSETS.I_10_0_0_0);
	}
	
	
	public Component_USER_DROPDOWN_NEW get_oSelUser() 
	{
		return oSelUser;
	}
	
}
