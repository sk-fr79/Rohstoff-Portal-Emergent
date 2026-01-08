package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_PopMiddleMenue extends MyE2_PopUpMenue  implements MyE2IF__Component, E2_IF_Copy
{
    private static E2_ResourceIcon oIconAktiv= E2_ResourceIcon.get_RI("popdownflat.png");
    private static E2_ResourceIcon oIconInactiv = E2_ResourceIcon.get_RI("leer.png");

	public MyE2_PopMiddleMenue(E2_ResourceIcon oiconAktiv, E2_ResourceIcon oiconinaktiv)
	{
		super(MyE2_PopMiddleMenue.oIconAktiv, MyE2_PopMiddleMenue.oIconInactiv, false);
		
		if (oiconAktiv != null)
		{
			this.set_oIconAktiv(oiconAktiv);
		}
		if (oiconinaktiv != null)
		{
			this.set_oIconInactiv(oiconAktiv);
		}
		
		this.setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		
	}

	
	/**
	 * 2015-07-17: neue version mit parametriebarem alignment
	 * @param oiconAktiv
	 * @param oiconinaktiv
	 * @param alignment
	 */
	public MyE2_PopMiddleMenue(E2_ResourceIcon oiconAktiv, E2_ResourceIcon oiconinaktiv, Alignment alignment)
	{
		super(MyE2_PopMiddleMenue.oIconAktiv, MyE2_PopMiddleMenue.oIconInactiv, false);
		
		if (oiconAktiv != null)
		{
			this.set_oIconAktiv(oiconAktiv);
		}
		if (oiconinaktiv != null)
		{
			this.set_oIconInactiv(oiconAktiv);
		}
		
		this.setPopUpAlignment(alignment==null?new Alignment(Alignment.RIGHT,Alignment.CENTER):alignment);
		
		
	}

	
	
	
	   public void addButton(MyE2_Button oNewButton, boolean bSetDefaultStyle)
	    {
		   super.addButton(oNewButton, bSetDefaultStyle);
		   if (this.get_ButtonCount()>10)
		   {
			   this.setPopUpTopOffset(6*(this.get_ButtonCount()-10));
		   }
	    }

	    

	    
	    /**
	     * Baut eine impliziten button aus dem uebergebenen string
	     * @param cButtonText
	     */
	    public void addTextButton(MyString cButtonText, String cEVENTKENNUNG)
	    {
	    	super.addTextButton(cButtonText, cEVENTKENNUNG);
	    	if (this.get_ButtonCount()>10)
	    	{
	    		this.setPopUpTopOffset(10*(this.get_ButtonCount()-10));
	    	}
	    }

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_PopMiddleMenue oPopRueck = new MyE2_PopMiddleMenue(this.get_oIconAktiv(),this.get_oIconInactiv());
		for (int i=0;i<this.get_vMenueButtons().size();i++)
		{
			oPopRueck.addButton((MyE2_Button)((MyE2_Button)this.get_vMenueButtons().get(i)).get_Copy(null), false);
		}
		return oPopRueck;
	}

}
