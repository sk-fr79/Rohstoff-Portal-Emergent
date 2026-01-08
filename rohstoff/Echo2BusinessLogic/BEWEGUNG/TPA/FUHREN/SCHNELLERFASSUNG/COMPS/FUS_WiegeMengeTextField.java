package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_TextFieldWithEraser;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;

public class FUS_WiegeMengeTextField extends MyE2_TextFieldWithEraser implements __FUS_STANDARD_Element
{

	public FUS_WiegeMengeTextField()
	{
		super(true);
		
		this.set_iWidthPixel(110);
		this.setFont(new E2_FontBold(2));
		this.setHeight(new Extent(22));
		this.setBorder(new Border(2, Color.BLACK, Border.STYLE_SOLID));
		this.setAlignment(E2_ALIGN.RIGHT_MID);

		
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
		return new MyE2_String("Wiegemenge MUSS ausgefüllt sein !");
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
		this.setText("");
	}

	@Override
	public Boolean get_IS_EK() throws myException
	{
		return null;
	}

	public MyBigDecimal  get_bd_WiegeMenge()
	{
		
		return new MyBigDecimal(this.getText());
		
	}

	@Override
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{
		
	}

}
