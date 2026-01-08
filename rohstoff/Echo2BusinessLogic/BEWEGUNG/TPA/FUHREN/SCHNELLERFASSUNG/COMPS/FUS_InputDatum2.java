package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;

public class FUS_InputDatum2 extends MyE2_TextField_DatePOPUP_OWN implements __FUS_STANDARD_Element
{

	public FUS_InputDatum2() throws myException
	{
		super("",80,true,true);
		this.get_oButtonEraser().__setImages(E2_ResourceIcon.get_RI("eraser_xxs.png"), true);
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
		return new MyE2_String("Bitte das Plandatum Ziel erfassen ...");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);
		
		MyDate  oDate = new MyDate(this.get_oTextField().getText());
		
		if (bMarkWhenFalse) this.mark_MUST_BE_Filled( !(oDate.get_cErrorCODE().equals(MyDate.ALL_OK)));

		
		return (oDate.get_cErrorCODE().equals(MyDate.ALL_OK));
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen()
	{
		return null;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden()
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

	public MyDate  get_oActualDate()
	{
		return new MyDate(this.get_oTextField().getText());
	}

	@Override
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{
				
	}

	
	
}
