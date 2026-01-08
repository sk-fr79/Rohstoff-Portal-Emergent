package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_TextFieldWithEraser;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;

public class FUS_InputAnzahlContainer extends MyE2_TextFieldWithEraser implements __FUS_STANDARD_Element
{

	public FUS_InputAnzahlContainer()
	{
		super(true);
		this.set_iMaxInputSize(3);
		this.set_iWidthPixel(30);
	}

	
	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
		this.setBackground(new E2_ColorEditBackground());

		if (bMarked)
		{
			this.setBackground(new E2_ColorHelpBackground());
		}
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String("Bitte die Anzahl Container korrekt ausfüllen");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);

		
		MyBigDecimal  bdTest = new MyBigDecimal(this.getText());
		
		if (bMarkWhenFalse) this.mark_MUST_BE_Filled(!(bdTest.get_cErrorCODE().equals(MyBigDecimal.ALL_OK)));

		
		return (bdTest.get_cErrorCODE().equals(MyBigDecimal.ALL_OK));
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{

		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();
		return vRueck;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();
		return vRueck;
	}

	@Override
	public void clean__Field() throws myException
	{
		this.setText("");
	}

	public MyBigDecimal  get_bd_AnzahlContainer()
	{
		
		return new MyBigDecimal(this.getText());
		
	}


	@Override
	public Boolean get_IS_EK() throws myException
	{
		return Boolean.TRUE;
	}


	@Override
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{
		
	}

}
