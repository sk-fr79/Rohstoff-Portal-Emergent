package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;

public class FUS_AnzeigeMengenEinheit extends MyE2_Label implements __FUS_STANDARD_Element
{
	
	
	public FUS_AnzeigeMengenEinheit(String cText)
	{
		super(cText,new E2_FontBold(2));
	}

	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return null;
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean mark_when_false) throws myException
	{
		return true;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{
		return null;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden() throws myException
	{
		return null;
	}

	@Override
	public void clean__Field() throws myException
	{
	}

	@Override
	public Boolean get_IS_EK() throws myException
	{
		return null;
	}

	@Override
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{
		// TODO Auto-generated method stub
		
	}

}
