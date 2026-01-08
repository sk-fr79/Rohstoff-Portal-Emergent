package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class SELECTOR_ADRESSKLASSE_MIT_Beschreibung extends MyE2_Grid 
{

	private MyE2_SelectField				oSelectAdressKlasse = null;

	
	public SELECTOR_ADRESSKLASSE_MIT_Beschreibung(int 		iTextWidth, 
												  int       iDropdownWidth, 
												  MyString 	cBeschriftung) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		
		this.oSelectAdressKlasse = new MyE2_SelectField(
				"select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
				false,true,false,false);

		this.oSelectAdressKlasse.setWidth(new Extent(iDropdownWidth));
		
		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0), E2_INSETS.I_0_0_0_0);
		this.add(oSelectAdressKlasse, E2_INSETS.I_10_0_0_0);
	}
	
	
	public MyE2_SelectField get_oSelAdressKlasse() 
	{
		return oSelectAdressKlasse;
	}
	
	
}
