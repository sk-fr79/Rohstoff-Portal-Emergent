package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Label;

public class BSRG__allgemeinLabelAbzuege extends MyE2_Label 
{
	public BSRG__allgemeinLabelAbzuege()
	{
		super(BSRG__CONST.ICON_HAT_ABZUEGE);
		this.setToolTipText(new MyE2_String("Position hat Abzüge").CTrans());
	}
}
