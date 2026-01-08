package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_ComboBoxErsatz;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;


public class FUS_Combo_Transportmittel extends MyE2_ComboBoxErsatz implements __FUS_STANDARD_Element
{

	public FUS_Combo_Transportmittel() throws myException
	{
		super("SELECT BESCHREIBUNG FROM "+bibE2.cTO()+".JT_TRANSPORTMITTEL ORDER BY BESCHREIBUNG");
		this.get_oTextField().set_iWidthPixel(160);
		this.get_oTextField().setFont(new E2_FontPlain());
		this.set_bSetTextAsTooltip(true);
		
	}

	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
		this.get_oTextField().setBackground(new E2_ColorEditBackground());
		
		if (bMarked)
		{
			this.get_oTextField().setBackground(new E2_ColorHelpBackground());
		}

	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String("Ein Transportmittel MUSS angegeben werden !!");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean mark_when_false) throws myException
	{
		this.mark_MUST_BE_Filled(false);
		
		boolean bIsOK = S.isFull(this.get_oTextField().getText());
		
		if (mark_when_false) this.mark_MUST_BE_Filled( !bIsOK);

		return bIsOK;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{
		// TODO Auto-generated method stub
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
		this.get_oTextField().setText("");
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
