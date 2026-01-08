package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.exceptions.myException;


public class Vector_SQL_STACK_DAEMON extends Vector<XX_SQL_STACK_DAEMON>
{
	
	/**
	 * fuehrt bei allen Deamons einen reset durch und sammelt die vorhandenen locking-statements
	 */
	public void INIT()
	{
      	//falls welchle da sind, diese resetten und evtl. vorhandene integritaetspruefer einsammeln
  		for (int i=0;i<this.size();i++)
   		{
   			this.get(i).init();
   		}
	}
	
	
	
	
	
	/**
	 * @param cSQL
	 * @param oConn
	 * @param oEvent 
	 * @return MessageVector
	 * Wird direckt nach dem ausfuehren eines statements aufgerufen, muss die validierungen vorbereiten
	 * und (falls vorhanden) die davon ausgeloesten trigger-statements sammeln
	 * 
	 * @throws myException
	 */
	public MyE2_MessageVector  collect_LOG_INFOs(	Vector<MyDBToolBox_LOG_INFO> 	vLogInfos, 
													MyConnection 					oConn) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
 		for (int i=0;i<this.size();i++)
   		{
 			vRueck.add_MESSAGE(((XX_SQL_STACK_DAEMON)this.get(i)).collect_LOG_INFOs(vLogInfos,oConn));
   		}

 		return vRueck;
	}
	
	
	
}