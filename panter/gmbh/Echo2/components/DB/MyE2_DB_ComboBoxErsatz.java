package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;


/*
 * nachbau der sql-query-combobox mit popup-selector wg echopoint-bug
 */
public class MyE2_DB_ComboBoxErsatz extends	MyE2_DB_TextField_WithSelektor   implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	public MyE2_DB_ComboBoxErsatz(SQLField osqlField, String[] aDefArray, boolean bAddEmptyValueInFront) throws myException 
	{
		super(osqlField, aDefArray,null, bAddEmptyValueInFront);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);
	}

	public MyE2_DB_ComboBoxErsatz(SQLField osqlField, String[][] aDefArray, boolean bAddEmptyValueInFront) throws myException 
	{
		super(osqlField, aDefArray,null, bAddEmptyValueInFront);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);
	}
	
	public MyE2_DB_ComboBoxErsatz(SQLField osqlField, String[][] aDefArray, boolean bAddEmptyValueInFront, int iWidthTextField) throws myException 
	{
		super(osqlField, aDefArray,null, bAddEmptyValueInFront);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		
		this.get_oTextField().set_iWidthPixel(iWidthTextField);
		this.get_oPopUp().setPopUpLeftOffset(-1*iWidthTextField);
		this.get_oPopUp().get_oContainerEx().setWidth(new Extent(iWidthTextField));
		
	}

	
	public MyE2_DB_ComboBoxErsatz(SQLField osqlField, String cSQLQuery, boolean bAddEmptyValueInFront) throws myException 
	{
		super(osqlField, cSQLQuery, bAddEmptyValueInFront);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);
	}

	
	public MyE2_DB_ComboBoxErsatz(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);
	}

	
	public TextField getTextField()
	{
		return this.get_oTextField();
	}
	
	
	
	
	public void set_WidthAndHeightOfDropDown(Extent oWidth, Extent oHeight, Integer oLeftOffset, Integer oWidthOfTextField)
	{
		this.get_oPopUp().get_oContainerEx().setWidth(oWidth);
		this.get_oPopUp().get_oContainerEx().setHeight(oHeight);
		
		
		if (oLeftOffset == null)
			this.get_oPopUp().setPopUpLeftOffset(this.get_oTextField().get_iWidthPixel());
		else
			this.get_oPopUp().setPopUpLeftOffset(oLeftOffset.intValue());
			
		
		// setzt die breite des textfeldes und den leftoffset in korrelation
		if (oWidthOfTextField != null)
		{
			this.get_oTextField().set_iWidthPixel(oWidthOfTextField.intValue());
			if (oLeftOffset == null)
			{
				this.get_oPopUp().setPopUpLeftOffset(-1* oWidthOfTextField.intValue()-20);
			}
		}
		
		
	}
	
	
}
