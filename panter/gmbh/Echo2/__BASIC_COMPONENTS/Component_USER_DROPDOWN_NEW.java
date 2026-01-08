package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField_WithAutoToolTip;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.exceptions.myException;


/*
 * neuer user-dropdown, der die Benutzer in der Verwaltung vorne gruppiert
 */
public class Component_USER_DROPDOWN_NEW extends MyE2_SelectField_WithAutoToolTip 
{


	/**
	 * 	
	 **2013-06-21: selektor fuer die benutzerauswahl, erweitert mit einem eintrag fuer: suche alle raus, die keine eintrag haben
	 * @param bFormatedIDs
	 * @param iWidth
	 * @param arrTextValuePair_4_SelectWhereUsersNotSet (wenn null oder laenge<>2 wirds ignoriert)
	 * @throws myException
	 */
	public Component_USER_DROPDOWN_NEW(boolean bFormatedIDs, Integer iWidth, String[] arrTextValuePair_4_SelectWhereUsersNotSet) throws myException 
	{
		super();

	
		USER_SELECTOR_GENERATOR  oSelUsers = new USER_SELECTOR_GENERATOR(bFormatedIDs);
		
		this.set_ListenInhalt(oSelUsers.get_AktiveBenutzer(true, arrTextValuePair_4_SelectWhereUsersNotSet), false);
		
		if (oSelUsers.get_arrInaktive_User() != null && oSelUsers.get_arrInaktive_User().length>0)
		{
			dataToView oShadow = new dataToView(oSelUsers.get_arrInaktive_User(),false, bibE2.get_CurrSession());
			this.set_odataToViewShadow(oShadow);
		}
		
		if (iWidth != null)
		{
			this.setWidth(new Extent(iWidth));
		}
		
	}

	
	
	
	
	public Component_USER_DROPDOWN_NEW(boolean bFormatedIDs, Integer iWidth) throws myException 
	{
		super();
	
		
		//2012-02-09: umstellung auf einheitlichen benutzergenerator
		
		USER_SELECTOR_GENERATOR  oSelUsers = new USER_SELECTOR_GENERATOR(bFormatedIDs);
		
		this.set_ListenInhalt(oSelUsers.get_AktiveBenutzer(true, null), false);
		
		if (oSelUsers.get_arrInaktive_User() != null && oSelUsers.get_arrInaktive_User().length>0)
		{
			dataToView oShadow = new dataToView(oSelUsers.get_arrInaktive_User(),false, bibE2.get_CurrSession());
			this.set_odataToViewShadow(oShadow);
		}
		
		if (iWidth != null)
		{
			this.setWidth(new Extent(iWidth));
		}
		
	}

	
	
	
	
	
	
	public Component_USER_DROPDOWN_NEW(boolean bFormatedIDs, Integer iWidth, Font oFont) throws myException 
	{
		this(bFormatedIDs, iWidth);
		
		this.setFont(oFont);
	}
	
	public Component_USER_DROPDOWN_NEW(boolean bFormatedIDs, Integer iWidth, Font oFont, MyE2_String cToolTip) throws myException 
	{
		this(bFormatedIDs, iWidth, oFont);
		
		this.setToolTipText(cToolTip.CTrans());
	}

	
}
