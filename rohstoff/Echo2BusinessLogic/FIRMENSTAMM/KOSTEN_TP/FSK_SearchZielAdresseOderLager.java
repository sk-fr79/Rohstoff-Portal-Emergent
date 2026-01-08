package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_SEARCH_AdressBaseAndLager;

public class FSK_SearchZielAdresseOderLager extends DB_SEARCH_AdressBaseAndLager {

	
	
	public FSK_SearchZielAdresseOderLager(SQLField osqlfield) throws myException {
		super(osqlfield, false);
		this.get_oTextFieldForSearchInput().set_iWidthPixel(70);
		this.get_oLabel4Anzeige().set_iWidth(200);
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			FSK_SearchZielAdresseOderLager oRueck = new FSK_SearchZielAdresseOderLager(this.EXT_DB().get_oSQLField());
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("FSK_SearchZielAdresseOderLager: get_Copy:Error building copy-object: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
	}


	
}
