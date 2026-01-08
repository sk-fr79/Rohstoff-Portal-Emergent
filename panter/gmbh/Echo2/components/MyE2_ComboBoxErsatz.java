package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_ComboBoxErsatz extends MyE2_TextField_WithSelektor
{

	public MyE2_ComboBoxErsatz(String cSQLQuery, int iWidth) throws myException
	{
		super(cSQLQuery, iWidth);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);

	}

	public MyE2_ComboBoxErsatz(String cSQLQuery) throws myException
	{
		super(cSQLQuery);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);

	}

	public MyE2_ComboBoxErsatz(String[][] Varianten, String cInfo_When_Empty, int iWidth) throws myException
	{
		super(Varianten, cInfo_When_Empty, iWidth);
		this.get_oPopUp().set_oIconAktiv(E2_ResourceIcon.get_RI("popdownflat.png"));
		this.get_oPopUp().setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		this.get_oPopUp().setPopUpLeftOffset(-200);

	}

	public MyE2_ComboBoxErsatz(String[][] Varianten, String cInfo_When_Empty) throws myException
	{
		super(Varianten, cInfo_When_Empty);
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
