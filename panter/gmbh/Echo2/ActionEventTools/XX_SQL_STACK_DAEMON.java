package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.exceptions.myException;



/*
 * klasse kann einem event untergeschoben werden und wird dann ausgefuehrt, nachdem in der DBToolBox der SQL-Stack
 * ausgefuehrt wurde, aber vor dem commit - fuer validierungen eines zustandes in der datenbank (beispiel fuhrenmengen nach speicherung) 
 */
public abstract class XX_SQL_STACK_DAEMON 
{

	// nimmt die logging-zeilen der tabellen auf
	private Vector<MyDBToolBox_LOG_INFO> vLogInfos = new Vector<MyDBToolBox_LOG_INFO>();
	
	
	/*
	 * gibt einen Vector mit MyStrings zurueck, die die fehlermeldungen beinhalten (wenn vector.size()==0, dann ok
	 */
	public abstract  MyE2_MessageVector doValidate(MyConnection oConn) throws myException;
	
	
	/*
	 * wird unmittelbar nach der ausfuehrung eines einzelnen statemtents ausgefuehrt
	 * gibt einen Vector mit MyStrings zurueck, die die fehlermeldungen beinhalten (wenn vector.size()==0, dann ok
	 */
	public abstract  MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> LogInfos, MyConnection oConn) throws myException;
	
	
	/*
	 * falls ein triggering vorgenommen wird, dann werden die triggerstatements hiermit zurueckgegeben
	 * gibt einen Vector mit SQL-Statemtents zurueck, die im anschluss an den normalen sqlstack ausgefuehrt werden
	 */
	public abstract  Vector<String> 	getTriggerStatementsAfterSQL(MyConnection oConn, MyE2_MessageVector oMV) throws myException;
	
	
	
	/*
	 * eine methode, die das objekt resettet. Wird vor dem ausfuehren eines sql-Stacks ausgefuehrt (beispiel: leeren von ID-sammlern
	 */
	public void init() 
	{
		this.get_vLogInfos().removeAllElements();
	}

	

	public Vector<MyDBToolBox_LOG_INFO> get_vLogInfos() 
	{
		return vLogInfos;
	}
	
	
	/**
	 * @param cSQLStatement
	 * @param oDB
	 * fuer statements, die innerhalb der transaktion ausgefuehrt werden muessen
	 * @return 
	 */
	public MyE2_MessageVector exec_StatementInTransaction(String cSQLStatement, MyDBToolBox oDB)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (oDB.ExecSQL(cSQLStatement, false))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("XX_SQL_STACK_DAEMON:exec_StatementInTransaction:Error Excecute: "+cSQLStatement,false)));
		}
		
		return oMV;
	}
	
}
