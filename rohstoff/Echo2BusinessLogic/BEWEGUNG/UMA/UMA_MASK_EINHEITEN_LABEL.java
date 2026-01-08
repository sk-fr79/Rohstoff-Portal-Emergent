package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.components.MyE2_Label;

public class UMA_MASK_EINHEITEN_LABEL extends MyE2_Label
{
	private boolean AUSGANGSSORTE = false;
	
	public UMA_MASK_EINHEITEN_LABEL(Object cText, boolean bIstAusgangssorte)
	{
		super(cText);
		this.AUSGANGSSORTE = bIstAusgangssorte;
	}

	public boolean get_bAUSGANGSSORTE()
	{
		return AUSGANGSSORTE;
	}
	
}
