package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_PopDownMenue extends MyE2_PopUpMenue  implements MyE2IF__Component, E2_IF_Copy
{
    private static E2_ResourceIcon oIconAktiv= E2_ResourceIcon.get_RI("popdownflat.png");
    private static E2_ResourceIcon oIconInactiv = E2_ResourceIcon.get_RI("leer.png");

	public MyE2_PopDownMenue(E2_ResourceIcon oiconAktiv, E2_ResourceIcon oiconinaktiv)
	{
		super(MyE2_PopDownMenue.oIconAktiv, MyE2_PopDownMenue.oIconInactiv, false);
		
		if (oiconAktiv != null)
		{
			this.set_oIconAktiv(oiconAktiv);
		}
		if (oiconinaktiv != null)
		{
			this.set_oIconInactiv(oiconAktiv);
		}
		
		this.setPopUpAlignment(new Alignment(Alignment.CENTER,Alignment.BOTTOM));
		
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_PopDownMenue oPopRueck = new MyE2_PopDownMenue(this.get_oIconAktiv(),this.get_oIconInactiv());
		for (int i=0;i<this.get_vMenueButtons().size();i++)
		{
			oPopRueck.addButton((MyE2_Button)((MyE2_Button)this.get_vMenueButtons().get(i)).get_Copy(null), false);
		}
		return oPopRueck;
	}

}
