package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;


/*
 * nachbau der sql-query-combobox mit popup-selector wg echopoint-bug,
 * allerdings mit textarea fuer mehrzeilige eingabe
 */
public class MyE2_DB_ComboBoxErsatzArea extends	MyE2_DB_TextArea_WithSelektor   implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{


	public MyE2_DB_ComboBoxErsatzArea(SQLField osqlField, String[][] aDefArray) throws myException 
	{
		super(osqlField, aDefArray,null,null);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);
	}
	
	public MyE2_DB_ComboBoxErsatzArea(SQLField osqlField, String cSQLQuery) throws myException 
	{
		super(osqlField, cSQLQuery,null);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);
	}

	

		
	
	
	public void set_WidthAndHeightOfDropDown(Extent oWidth, Extent oHeight, Integer oLeftOffset, Integer oWidthOfTextField)
	{
		this.get_oPopUp().get_oContainerEx().setWidth(oWidth);
		this.get_oPopUp().get_oContainerEx().setHeight(oHeight);

		
		if (oLeftOffset == null)
			this.get_oPopUp().setPopUpLeftOffset(this.get_oTextArea().get_iWidthPixel());
		else
			this.get_oPopUp().setPopUpLeftOffset(oLeftOffset.intValue());
			
		
		// setzt die breite des textfeldes und den leftoffset in korrelation
		if (oWidthOfTextField != null)
		{
			this.get_oTextArea().set_iWidthPixel(oWidthOfTextField.intValue());
			if (oLeftOffset == null)
			{
				this.get_oPopUp().setPopUpLeftOffset(-1* oWidthOfTextField.intValue()-20);
			}
		}
		
		
	}
	
	
}
