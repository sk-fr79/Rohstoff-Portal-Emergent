package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;


/*
 * neuer user-dropdown, der die Benutzer in der Verwaltung vorne gruppiert
 */
public class DB_Component_USER_DROPDOWN_NEW extends MyE2_DB_SelectField 
{

	public DB_Component_USER_DROPDOWN_NEW(SQLField osqlField, boolean bFormatedIDs) throws myException 
	{
		super(	osqlField);

		this._fill(bFormatedIDs);
		
	}

	
	public DB_Component_USER_DROPDOWN_NEW(SQLField osqlField, boolean bFormatedIDs, int iWidth) throws myException 
	{
		super(	osqlField);
		this._fill(bFormatedIDs);
		
		this.setWidth(new Extent(iWidth));
		
	}
	

	
	public DB_Component_USER_DROPDOWN_NEW(SQLField osqlField, boolean bFormatedIDs, int iWidth, Font oFont) throws myException 
	{
		super(	osqlField);
		this._fill(bFormatedIDs);
		
		this.setWidth(new Extent(iWidth));
		if (oFont!=null)
		{
			this.setFont(oFont);
		}
		
	}
	

	
	
	private void _fill(boolean bFormatedIDs) throws myException
	{
		//zuerst die buero-benutzer, dann die anderen, dann die inaktiven (versteckt-shadow)
		//2012-03-05: umstellung auf einheitliche select-struktur USER_SELECTOR_GENERATOR
		USER_SELECTOR_GENERATOR   oUserSelector = new USER_SELECTOR_GENERATOR(bFormatedIDs);
		
		this.set_ListenInhalt(oUserSelector.get_AktiveBenutzer(true, null), false);
		
		if (oUserSelector.get_arrInaktive_User() != null && oUserSelector.get_arrInaktive_User().length>0)
		{
			dataToView oShadow = new dataToView(oUserSelector.get_arrInaktive_User(),false, bibE2.get_CurrSession());
			this.set_odataToViewShadow(oShadow);
		}

	}
	
	
	
}
