package panter.gmbh.indep.dataTools;


import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;



/*
 * klasse, die eine spezielle tabelle mit einem Lock-Eintrag 
 * behandelt. Jede Standard-Transaktion wird mit diesem lock-statetment begonnen
 * Damit ist gesichert, dass in der inneren abwicklung der transaktion keine
 * gegenseitigen beeinflussingen stattfinden
 */
public abstract class XX_TransActionLock 
{

	protected abstract String get_LockStatement();
	

	/**
	 * @param oConn
	 * @return
	 * Fuehrt das locking-statemtent aus ohne die transaktion zu schliessen
	 */
	public MyE2_MessageVector make_Lock(MyConnection oConn)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		MyDBStatement oST = new MyDBStatement(oConn);
		try
		{
	       	if (oST.STMT != null)
	       	{
	        	oST.execute(this.get_LockStatement());
	        	oST.Close();
	       	}
	       	else
	       	{
	       		oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyString("XX_TransActionLock:Error creating MyDBStatement !",false)));
	       	}
		}
		catch (Exception ex)
		{
       		oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyString("XX_TransActionLock:"+ex.getLocalizedMessage(),false)));
       		oST.Close();
		}
		
		return oMV;
	}

}
