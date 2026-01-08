package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.unboundDataFields.UB_SelectField;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.exceptions.myException;


public class UB_SelectField_USERS extends UB_SelectField 
{


	public UB_SelectField_USERS(String NameDBField, boolean EmptyAllowed, boolean bFormatedIDs, Integer iWidth) throws myException 
	{
		super(NameDBField, EmptyAllowed);
		
		//zuerst die buero-benutzer, dann die anderen, dann die inaktiven (versteckt-shadow)
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

}
