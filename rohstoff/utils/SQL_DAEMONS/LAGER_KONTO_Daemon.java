package rohstoff.utils.SQL_DAEMONS;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerHandler;

public class LAGER_KONTO_Daemon extends XX_SQL_STACK_DAEMON {
	
	
	/*
	 * 
	 */

	Vector<String> vTest = new Vector<String>();
	
	public LAGER_KONTO_Daemon() throws myException 
	{
		super();
	}


	
	// wird unmittelbar nach jedem einzelnen sql-statement ausgefuehrt
	public MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> vLogInfos, MyConnection oConn) throws myException 
	{

		//die betroffenen tabellen-LogInfos sammeln
		for (MyDBToolBox_LOG_INFO test: vLogInfos)
		{
			
			if (    (test.get_cTABLENAME().equals("JT_VPOS_TPA_FUHRE") ||
					test.get_cTABLENAME().equals("JT_VPOS_TPA_FUHRE_ORT"))
					)
//					
//					&& !test.get_IS_DELETE()) //nur insert und update werden beruecksichtigt !
			{
				// nur eindeutige Einträge einfügen, doppelte Einträge verwerfen
				if (!vTest.contains(test.get_cID_TABLE())){
					vTest.add(test.get_cID_TABLE());
					this.get_vLogInfos().add(test);
				}
			}
		}
		return new MyE2_MessageVector();
	}


	
	public MyE2_MessageVector doValidate(MyConnection oConn) throws myException 
	{
		MyE2_MessageVector vErrorRueck = new MyE2_MessageVector();
		return vErrorRueck;
	}
	
	
	

	public Vector<String> getTriggerStatementsAfterSQL(MyConnection oConn, MyE2_MessageVector oMV) throws myException 
	{

		Vector<String> vSQL_Rueck 		= new Vector<String>();

		
		for (MyDBToolBox_LOG_INFO test: this.get_vLogInfos())
		{
			String cID_TABLE = test.get_cID_TABLE();

			LAG_LagerHandler handler = new LAG_LagerHandler(); 

			if (test.get_cTABLENAME().toUpperCase().equals("JT_VPOS_TPA_FUHRE"))  {
				handler.LagerBuchung(cID_TABLE);
				vSQL_Rueck.addAll(handler.getSqlStatements());
			}
			if (test.get_cTABLENAME().toUpperCase().equals("JT_VPOS_TPA_FUHRE_ORT")){
				handler.LagerbuchungOrt(cID_TABLE);
				vSQL_Rueck.addAll(handler.getSqlStatements());
			}
		}	
		
		vTest.clear();
		return vSQL_Rueck;
		
	}

}
