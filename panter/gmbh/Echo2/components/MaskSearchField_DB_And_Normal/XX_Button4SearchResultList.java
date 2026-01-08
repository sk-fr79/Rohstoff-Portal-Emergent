package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;



public abstract class XX_Button4SearchResultList extends MyE2_Button
{



	private DefSpalteLayout_And_Else      DefSpalteLayout = null;
	
	
	private boolean                       bLineWrapIsDefined = false;
	



	public abstract String get_SortText() throws myException;
	
	
	public XX_Button4SearchResultList(String cText)
	{
		super(cText);
	}

	public XX_Button4SearchResultList(MyString cText)
	{
		super(cText);
	}
	
	public XX_Button4SearchResultList(String cText, E2_MutableStyle oStyle)
	{
		super(cText,oStyle);
	}
	
	public XX_Button4SearchResultList(MyString cText, E2_MutableStyle oStyle)
	{
		super(cText,oStyle);
	}

	/**
	 * ueberschriebene methode um zu verhindern, dass ein speziell definierter button mit
	 * definiertem linewrap im aufbau der liste vom inhalt des DefSpalteLayout_And_Else - linewrap ueberschrieben wird,
	 * da das dort immer gesetzt wird
	 */
	public void setLineWrap(boolean bLineWrap)
	{
		if (this.bLineWrapIsDefined)
		{
			return;
		}
		super.setLineWrap(bLineWrap);
		this.bLineWrapIsDefined=true;
	}
	
	public DefSpalteLayout_And_Else get_DefSpalteLayout()
	{
		return DefSpalteLayout;
	}


	public void set_DefSpalteLayout(DefSpalteLayout_And_Else defSpalteLayout)
	{
		DefSpalteLayout = defSpalteLayout;
	}

}
