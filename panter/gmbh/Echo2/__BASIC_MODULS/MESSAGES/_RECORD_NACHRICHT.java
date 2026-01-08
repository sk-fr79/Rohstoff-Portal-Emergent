package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class _RECORD_NACHRICHT extends RECORD_NACHRICHT
{

	public _RECORD_NACHRICHT(RECORD_NACHRICHT recordOrig)
	{
		super(recordOrig);
	}

	
	public boolean get_bIsSystemNachricht() throws myException
	{
		boolean bRueck = false;
		
		if (this.get_TYP_NACHRICHT_cUF_NN("").equals(MESSAGE_CONST.MESSAGE_TYP_SYSTEM))
		{
			bRueck=true;
		}
		return bRueck;
	}
	
	
	public boolean get_bIsUserNachricht() throws myException
	{
		boolean bRueck = false;
		
		if (this.get_TYP_NACHRICHT_cUF_NN("").equals(MESSAGE_CONST.MESSAGE_TYP_USER))
		{
			bRueck=true;
		}
		return bRueck;
	}

	public boolean get_bIsUndefinedNachricht() throws myException
	{
		boolean bRueck = false;
		
		if (S.isEmpty(this.get_TYP_NACHRICHT_cUF_NN("")))
		{
			bRueck=true;
		}
		return bRueck;
	}

	
}
