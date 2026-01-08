package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;

public class EMS_DataObject extends RB_Dataobject_V1
{

	public EMS_DataObject(MyRECORD_IF_RECORDS recORD, MASK_STATUS status) throws myException
	{
		super(recORD, status);
	}

	public EMS_DataObject(MyRECORD_NEW recNEW, MyRECORD_IF_RECORDS recORD, MASK_STATUS status) throws myException
	{
		super(recNEW, recORD, status);
	}

	public EMS_DataObject(MyRECORD_NEW recNEW) throws myException
	{
		super(recNEW);
	}



}
