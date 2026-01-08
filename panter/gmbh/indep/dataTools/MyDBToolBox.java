package panter.gmbh.indep.dataTools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.Vector_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;



public class MyDBToolBox extends java.lang.Object
{
	
	// beginnt ein sqlstatement mit diesem marker, dann werden die in der MyDBToolBox vorhandenen add-on-felder nicht eingefuegt
	public static String       MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS = "@NOADDON@";
	public static String       MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION = "@NODAEMON@";
	
	
	
    private 	Connection 		conn;
    private     MyConnection    oMyConnection = null;
    private 	String[][] 		cZusatzFelder = null; 			// zusatzfelder werden in jedes übergebene sql-insert und update eingefügt
                                             					// wenn diese null sind, dann nicht. Damit können datenkonstanten übergeben werden
    															// cZusatzFelder[x][0] = feldnamenstring
    private 	String 			cZusatzFelderInsertFields = ""; // aus den feldname zusammengesetzter String
    private 	String 			cZusatzFelderInsertValues = ""; // aus den feldwerten zusammengesetzter String
    private 	String 			cZusatzUpdateFields = ""; 		// der update-bereich, der für die zusatzfelder gebraucht wird
    private 	String[][] 		cErsetzungTableView = null; 	// ersetzungstabelle für tables / views
			                                                   	// falls diese tabelle gesetzt (!=null) ist,
			                                                   	// wird bei jeder SELECT-Query die liste der 
			                                                   	// tabellen durchlaufen und durch die entsprechende view 
			                                                   	// ersetzt.
    private		boolean 		bErsetzungTableView = true; 	// kann durch einen schalter deaktiviert werden


    /*
     * schalter, der bei einzelabfrageinarray- querys benutzt wird, falls eine beschränkung auf
     * eine bestimmte anzahl datensätze übergeben wird, falls diese beschränkung überschritten wird, dann wird dieser parameter true
     */
    private 	boolean 		bMaxNumberOfRowsExceeded = false;
    
    
    
    /*
     * ein metaset, das bei den intern erzeugten results jedesmal mitnotiert, wie die datentype aussehen
     * 
     */
    private MyMetaFieldDEF_HashMap   oMetaColumnsOfResultSet = null;
    
 
    
    /*
     * 2016-07-19: martin: ein field, das die letzte anzahl updates uebernimmt 
     */
    private Integer  				i_count_last_updates_executed = null;
    
    

	// initData kann nur noch mit gültigem session-objekt aufgerufen werden
    public MyDBToolBox(MyConnection oMyConnNormal) throws myException
    {
         
        this.conn 	= 			oMyConnNormal.get_oConnection();
        this.oMyConnection = 	oMyConnNormal;
        
        try
        {
            // alle connections haben autocommit = false
            conn.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            throw new myException("Error creating MyDBToolBox:"+e.getLocalizedMessage());
        }
        
        
    }

    

    /**
     * MyDBToolBox mit eigenem MyConnection-Objekt, das die Verbindung
     * nach zerstoerung dieses Objekts automatisch im finalize() schliesst 
     * @param jdbc_className
     * @param connectString
     * @param userName
     * @param password
     * @param DB_kenner
     * @throws myException
     */
    public MyDBToolBox(	String jdbc_className, 
						String connectString, 
						String userName, 
						String password, 
						String DB_kenner) throws myException
    {
         
        this.oMyConnection = new MyConnection(jdbc_className,connectString,userName,password,DB_kenner);
        this.conn = this.oMyConnection.get_oConnection();
        
        try
        {
            // alle connections haben autocommit = false
            conn.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            throw new myException("Error creating MyDBToolBox (2):"+e.getLocalizedMessage());
        }
    }


    
    
    

    // setzt evtl. nötige zusatzfelder auf, die allen insert und update-statements untergeschoben werden
    // bedingung: pZusatzFelder <> null
    // die zusatzstrings sind vorne und hinten ohne komma
    public void setZusatzFelder(String[][] pZusatzFelder)
    {
        cZusatzFelderInsertFields = ""; // aus den feldname zusammengesetzter String
        cZusatzFelderInsertValues = ""; // aus den feldwerten zusammengesetzter String
        cZusatzUpdateFields = ""; // der update-bereich, der für die zusatzfelder gebraucht wird

        if (pZusatzFelder != null)
        {
            cZusatzFelder = new String[pZusatzFelder.length][2];

            for (int i = 0; i < pZusatzFelder.length; i++)
            {
                cZusatzFelder[i][0] = pZusatzFelder[i][0];
                cZusatzFelder[i][1] = pZusatzFelder[i][1];
            }

            for (int i = 0; i < pZusatzFelder.length; i++)
            {
                if ((pZusatzFelder[i][0] != null) && (pZusatzFelder[i][1] != null))
                {
                    if (!pZusatzFelder[i][0].trim().equals("") && !pZusatzFelder[i][1].trim().equals(""))
                    {
                        cZusatzFelderInsertFields += (pZusatzFelder[i][0] + " ,");
                        cZusatzFelderInsertValues += (pZusatzFelder[i][1] + " ,");
                        cZusatzUpdateFields += (pZusatzFelder[i][0] + " = " + pZusatzFelder[i][1] + " ,");
                    }
                }
            }

            // jetzt das rechte komma weg
            cZusatzFelderInsertFields = cZusatzFelderInsertFields.substring(0, cZusatzFelderInsertFields.length() - 1);
            cZusatzFelderInsertValues = cZusatzFelderInsertValues.substring(0, cZusatzFelderInsertValues.length() - 1);
            cZusatzUpdateFields = cZusatzUpdateFields.substring(0, cZusatzUpdateFields.length() - 1);
        }
    }

     
    /**
     * Cached Rowset anstatt recordset
     * @author manfred
     * @date   13.04.2012
     * @param ppSql
     * @return
     */
    public MyDBResultSet_JdbcRowSet OpenResultSet_JdbcRowSet (String ppSql){
        String[][] cErsetzeTableView = this.bErsetzungTableView?this.cErsetzungTableView:null;

        MyDBResultSet_JdbcRowSet oRS = null;
        
		try {
			oRS = new MyDBResultSet_JdbcRowSet(this.oMyConnection,ppSql,cErsetzeTableView);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return oRS;
    }

    







    public MyDBResultSet OpenResultSet(String ppSql)
    {
        String[][] cErsetzeTableView = this.bErsetzungTableView?this.cErsetzungTableView:null;

        MyDBResultSet oRS = new MyDBResultSet(this.oMyConnection,ppSql,cErsetzeTableView);
        return oRS;
    }

 


    /**
     * raw-ausfuehrung von SQL-Stack
     * @param vSql
     * @param bMitCommit
     * @return
     */
    public boolean ExecSQL(Vector<String> vSql, boolean bMitCommit)
    {
    	boolean bRueck = true;
    	
    	for (int i=0;i<vSql.size();i++)
    	{
    		bRueck = bRueck & this.ExecSQL(vSql.get(i), false);
    		
    		if (!bRueck)
    			break;
    	}
    	
    	if (!bRueck)
    	{
    		/*
			 * geaendert am: 03.02.2010 von: martin
			 * wenn eines dieser statements schief geht, dann muss ob das rollback erfolgen, genauso wie das commit
			 */
    		if (bMitCommit)
    		{
    			this.Rollback();
    		}
    	}
    	else
    	{
    		if (bMitCommit)
    		{
    			if (!this.Commit())
    			{
    				this.Rollback();
    				bRueck = false;
    			}
    		}
    	}
    	return bRueck;
    }    
    
    /**
     * raw-Ausführung im Batch des SQL-Vektors
     * @param vSql
     * @param bMitCommit
     * @return
     */
    public boolean ExecSQL_in_Batch(Vector<String> vSql, boolean bMitCommit)
    {
    	boolean bRueck = true;
    	boolean 	bMustRollback = 	false;
    	String 		cSql = 	null;
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	
    	
       	
    	if (oST.STMT != null)
    	{
    		try
	        {
	    		for (int i=0;i<vSql.size();i++)
	    		{
	    	
	    			// das Statement vorbereiten
	    			cSql = 				vSql.get(i);
	    			MyDBToolBox_Statement_Analyzer oStatementAnalyse = new MyDBToolBox_Statement_Analyzer(cSql,this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
	    			cSql = oStatementAnalyse.get_PlainSQL_Statement();
	
	    			// das Statement in die Batch einfügen
	    			oST.STMT.addBatch(cSql);
	    		}

	    		
	    		int[] count = oST.STMT.executeBatch();
	    	
	    		if (bMitCommit){
	    			this.ownCommit();
	    		} 
	    		
    		    bRueck = true;
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	            bRueck = false;
	            bMustRollback = true;
	           
	        }
    		finally {
    			 oST.Close();
    		}
    	}
       
        // wenn commit schiefgeht, dann rollback
        if (bMustRollback)
        {
            try
            {
            	this.ownRollBack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bRueck;
    	
    	
    }    
    
    

    /**
     * 2015-06-30: erzeugt das komplette statement inclusive zusatzfelder/values paaren (fuer externe nutzung)
     * @param p_sql
     * @return
     */
    public String generate_plain_sql_Statement(String p_sql) {
        MyDBToolBox_Statement_Analyzer oStatementAnalyse = new MyDBToolBox_Statement_Analyzer(p_sql,this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
        return oStatementAnalyse.get_PlainSQL_Statement();
    }
    
    
    
    
    // kompletter aufruf
    public boolean ExecSQL(String pSql, boolean bMitCommit)
    {
        boolean 	bMustRollback = 	false;
        String 		cSql = 				pSql;
        boolean 	bRueck = 			false;

        
        //cSql = this.makeExecutableStatement(cSql,this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields); // sql-string erweitern
        
        MyDBToolBox_Statement_Analyzer oStatementAnalyse = new MyDBToolBox_Statement_Analyzer(cSql,this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
        cSql = oStatementAnalyse.get_PlainSQL_Statement();
        
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	if (oST.STMT != null)
    	{
	       try
	        {
 	        	oST.execute(cSql);
	        	oST.Close();
	            bRueck = true;
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	            bRueck = false;
	            bMustRollback = true;
	            oST.Close();
	        }
    	}
        
        if (bRueck && bMitCommit)
        {
            try
            {
            	this.ownCommit();
            }
            catch (Exception e)
            {
            	e.printStackTrace();
                bMustRollback = true; // falls commit schiefgeht, dann muss ein rollback probiert werden
                bRueck = false;
            }
        }

        // wenn commit schiefgeht, dann rollback
        if (bMustRollback)
        {
            try
            {
            	this.ownRollBack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bRueck;
     }



    
    // 2011-09-06: neuer raw-aufruf  VORSICHT !!!!!
    public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(String cSql, boolean bMitCommit)
    {
        boolean 	bMustRollback = 	false;
        boolean 	bRueck = 			false;
        
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	if (oST.STMT != null)
    	{
	       try
	        {
 	        	oST.execute(cSql);
	        	oST.Close();
	            bRueck = true;
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	            bRueck = false;
	            bMustRollback = true;
	            oST.Close();
	        }
    	}
        
        if (bRueck && bMitCommit)
        {
            try
            {
            	this.ownCommit();
            }
            catch (Exception e)
            {
            	e.printStackTrace();
                bMustRollback = true; // falls commit schiefgeht, dann muss ein rollback probiert werden
                bRueck = false;
            }
        }

        // wenn commit schiefgeht, dann rollback
        if (bMustRollback)
        {
            try
            {
            	this.ownRollBack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bRueck;
     }

    
    
    

    

    /**
     * 2015-11-18: neuer raw-aufruf  mit vector aus sql-strings
     * @param vSql
     * @param bMitCommit
     * @param v_intCounter   (neu ab 2016-07-20: damit kann ein zaehler mit der aenerungsanzahl zurueckgegeben werden)
     * @param tabTriggersToDisable (neu ab 2016-07-20: damit koennen trigger inaktiv geschaltet werden, werden IMMER wieder aktiv gesetzt)
     * @return
     */
    public MyE2_MessageVector ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(Vector<String> vSql, boolean bMitCommit, Vector<Integer> v_intCounter, Vector<_TAB> tabTriggersToDisable)     {
        boolean 	bMustRollback = 	false;
        
        MyE2_MessageVector  mv= new MyE2_MessageVector();
        
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	
    	if (v_intCounter==null) {
    		v_intCounter=new Vector<>();
    	}
    	if (v_intCounter.size()==0 || v_intCounter.get(0)==null) {
    		v_intCounter.add(0,new Integer(0));
    	}
    	
    	
    	int i_count = 0;
    	
    	boolean b_triggersetting_ok = true;
    	
    	if (tabTriggersToDisable!=null && tabTriggersToDisable.size()>0) {
    		if (!this.set_triggers(false,tabTriggersToDisable)) {
    			b_triggersetting_ok = false;
    		}
    	}

    	//falls trigger nicht fuktioniert, dann rollback und raus
    	if (!b_triggersetting_ok) {
    		this.set_triggers(true, tabTriggersToDisable);   //trigger wieder einschalten
    		this.Rollback();
    		mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("CRITICAL ERROR !!!! Error setting Triggers !!! ")));
    		return mv;
    	}
    	
    	
    	if (oST.STMT != null)  	{
    		for (String sql: vSql) {
	    		try  {
	 	        	oST.execute(sql);
	 	        	
	 	        	//2016-07-20: anzahl aenderungen protokollieren
	 	        	int i_actual = oST.get_UpdateCount();
	 	        	if (i_actual>0) {
	 	        		i_count+=i_actual;
	 	        	}
		        }  catch (SQLException e) {
		        	mv.add_MESSAGE(new MyE2_Alarm_Message("Error: "+sql));
		        	
		        	e.printStackTrace();
		            bMustRollback = true;
		            oST.Close();
		            break;
		        }
    		}
    	} else {
    		mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error building statement !!!")));
    		bMustRollback = true;    //damit das auf jeden fall abgeschlossen ist
    	}
        
        if (mv.get_bIsOK() && bMitCommit) {
            try {
                //auf jeden fall alle trigger wieder einschalten
        		this.set_triggers(true, tabTriggersToDisable);   //trigger wieder einschalten
            	this.ownCommit();
            } catch (Exception e)  {
            	e.printStackTrace();
            	mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error Commiting !!")));
            	
                bMustRollback = true; // falls commit schiefgeht, dann muss ein rollback probiert werden
            }
        }

        // wenn commit schiefgeht, dann rollback
        if (bMustRollback) {
            try {
                //auf jeden fall alle trigger wieder einschalten
        		this.set_triggers(true, tabTriggersToDisable);   //trigger wieder einschalten
            	this.ownRollBack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        Integer int_count = v_intCounter.remove(0);
        v_intCounter.add(0,new Integer(int_count.intValue()+i_count));
        

        //IMMER beim verlassen wieder einschalten (auch wenn kein commit geschaltet ist)
		this.set_triggers(true, tabTriggersToDisable);   //trigger wieder einschalten

        return mv;
     }

    
    private boolean set_triggers(boolean b_enabled, Vector<_TAB> v_triggers) {
    	boolean b_rueck = true;
    	if (v_triggers!=null && v_triggers.size()>0) {
	    	try {
				for (_TAB t: v_triggers) {
					if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS("ALTER TRIGGER "+t.triggerName()+(b_enabled?" ENABLE ":" DISABLE"), false)) {
						b_rueck = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return b_rueck;
    }
    
    
    
    /**
     * 2014-04-14: neuer aufruf mit zaehler der durchgefuehrten updates 
     * @param cSql
     * @param bMitCommit
     * @param vUpdateCount ist Vector aus Integers, der an Stelle 0 die summe der aenderungen mitfuehrt
     * @return
     */
    public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(String cSql, boolean bMitCommit, Vector<Integer> vUpdateCount)
    {
        boolean 	bMustRollback = 	false;
        boolean 	bRueck = 			false;
       
        if (vUpdateCount==null) {
        	vUpdateCount=new Vector<Integer>();
        }
        Integer intCount = new Integer(0);
        
        if (vUpdateCount.size()==1) {
        	intCount = vUpdateCount.get(0);
        }
        
        
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	if (oST.STMT != null)
    	{
	       try
	        {
 	        	oST.execute(cSql);
 	        	intCount = new Integer(intCount.intValue()+oST.get_UpdateCount());
 	        	vUpdateCount.removeAllElements();
 	        	vUpdateCount.add(intCount);
	        	oST.Close();
	            bRueck = true;
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	            bRueck = false;
	            bMustRollback = true;
	            oST.Close();
	        }
    	}
        
        if (bRueck && bMitCommit)
        {
            try
            {
            	this.ownCommit();
            }
            catch (Exception e)
            {
            	e.printStackTrace();
                bMustRollback = true; // falls commit schiefgeht, dann muss ein rollback probiert werden
                bRueck = false;
            }
        }

        // wenn commit schiefgeht, dann rollback
        if (bMustRollback)
        {
            try
            {
            	this.ownRollBack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bRueck;
     }


    
    
    
    
    /**
     * raw-Ausführung im Batch des SQL-Vektors im BATCH-Modus
     * @param vSql
     * @param bMitCommit
     * @return
     */
    public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS_in_BATCH(Vector<String> vSql, boolean bMitCommit)
    {
    	boolean bRueck = true;
    	boolean 	bMustRollback = 	false;
    	String 		cSql = 	null;
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	
    	if (oST.STMT != null)
    	{
    		try
	        {
	    		for (int i=0;i<vSql.size();i++)
	    		{
	    		    // das Statement in die Batch einfügen
	    			oST.STMT.addBatch(vSql.get(i));
	    		}

	    		int[] count = oST.STMT.executeBatch();
	    	
	    		if (bMitCommit){
	    			this.ownCommit();
	    		} 
	    		
    		    bRueck = true;
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	            bRueck = false;
	            bMustRollback = true;
	           
	        }
    		finally {
    			 oST.Close();
    		}
    	}
       
        // wenn commit schiefgeht, dann rollback
        if (bMustRollback)
        {
            try
            {
            	this.ownRollBack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bRueck;
    	
    	
    }    

    
    
    
    
    
    
 
    /**
     * @param bCommit
     * @param oSql = Vector of Strings
     * @return s Vector of MyString - Error-infos
     */
    public MyE2_MessageVector ExecMultiSQLVector(	Vector<String> 				vSql, 
			    									boolean 					bCommit)
    {
        return _ExecMultiSql(vSql, bCommit);
    }



    
    /**
     * @param vSql
     * @param bCommit
     * @param oEvent
     * ES WIRD AUF WUNSCH EIN COMMIT AUSGEFUEHRT,
     * falls kein commit, muss dies in der rufenden einheit durchgefuehrt werden
     * Ebenfalls werden globale sql_daemonen ausgefuehrt 
     * @return
     */
    private MyE2_MessageVector 	_ExecMultiSql(		Vector<String> 				vSql, 
			    									boolean 					bCommit)
    {
    	
       MyE2_MessageVector 		oMV = 	new MyE2_MessageVector();
       MyDBStatement		 	oST =	new MyDBStatement(this.oMyConnection);
       
       this.i_count_last_updates_executed=null;
       
       /*
       	*  hier wird immer der trigger-counter um 1 erhoeht.
       	*  Wenn der TriggerCount in den kontrollabfragen der SQL_DAEMONEN dann != 1 ist,
       	*  wurde entweder innerhalb der Daemonen selbst ein commit ausgefuehrt
       	*/  
       bibALL.increase_Trigger_Counter();
       
       if (oST.STMT == null)
       {
		   oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Statement konnte nicht geöffnet werden !")), false);
       }
       else
       {
	   
	        // sammeln evtl. vorhandener trigger_and_validator (sowohl globale, als auch von dem event transportierte)
	        Vector_SQL_STACK_DAEMON		vSQL_STACK_DAEMON = new Vector_SQL_STACK_DAEMON();

	        //dann die globalen
	        vSQL_STACK_DAEMON.addAll(bibALL.get_vGlobal_SQL_STACK_DAEMON());

	       	//falls welchle da sind, diese resetten 
	       	vSQL_STACK_DAEMON.INIT();
	        
	       	String cSQL_MIT_Fehler = "";    //mitfuehren, damit eine fehlermeldung ausgegeben werden kann
	       	
	        try
	        {
        	
	        	//zuerst wird die transaktion gelocked ------------- IMMER -----------------------------------
	        	oMV.add_MESSAGE(bibALL.get_oTransactionLock().make_Lock(this.oMyConnection));
	        	// -------------------------------------------------------------------------------------------

	        	//alternative ausfuehrung
	        	if (oMV.get_bIsOK())
	        	{
	        		
		        	//dann wird der aktuelle Timestamp-millisecs geholt
		        	String cORA_SESSION = 	this.EinzelAbfrage("SELECT "+DB_META.ORA_SESSION_QUERY+" FROM DUAL");

		        	
		        	Vector<MyDBToolBox_LOG_INFO> vLogInfos = new Vector<MyDBToolBox_LOG_INFO>();
		        	int iUpdateCount = 0;
		        	
		            for (int i = 0; i < vSql.size(); i++)      
		            {
		            	
			        	String cTimeStamp = this.EinzelAbfrage("SELECT "+DB_META.ORA_TIMESTAMP_MILLISECS+" FROM DUAL");
		   
			        	//pruefung -transaktionsfehler
			        	//System.out.println(cTimeStamp);
			        	
			        	
	                	//String cSQL = this.makeExecutableStatement(vSql.get(i), this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
			            MyDBToolBox_Statement_Analyzer oStatementAnalyse = new MyDBToolBox_Statement_Analyzer(vSql.get(i), this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
			            String cSQL = oStatementAnalyse.get_PlainSQL_Statement();

		                
		                // ----------------------------------hier ist die Ausfuehrung des statements ------------------------------
		                // ----------------------------------hier ist die Ausfuehrung des statements ------------------------------
		                cSQL_MIT_Fehler=cSQL;
		                
		                DEBUG.System_println(cSQL, DEBUG.DEBUG_FLAG_SQL_EXEC);
		                
		               	oST.execute(cSQL);
		                // ----------------------------------hier ist die Ausfuehrung des statements ------------------------------
		                // ----------------------------------hier ist die Ausfuehrung des statements ------------------------------
		               	
		               	int iHelp= oST.get_UpdateCount();
		               	
		               	
			            if (iHelp>=1 && oStatementAnalyse.is_StatementCallsDeamons())    //nur dann den STACK_DAEMON einschalten, wenn tatsaechlich ein update erfolgt ist. Falls das updatestatement keine zeile aktualisiert, dann wurde auch kein
		               	{
				        	//welche saetze wurden von den triggern protkolliert
				        	vLogInfos.addAll(this.get_LogInfoVector(cORA_SESSION, cTimeStamp));
				        	iUpdateCount += iHelp;
		               	}
	  	            }

		            this.i_count_last_updates_executed = new Integer(iUpdateCount);

		            if (vLogInfos.size()>=iUpdateCount)    //cascadierende loeschungen werden nicht angezeigt in UpdateCount, stehen aber in der log-tabelle drin
               		{
               			oMV.add_MESSAGE(vSQL_STACK_DAEMON.collect_LOG_INFOs(vLogInfos, this.oMyConnection));
               		}
               		else
               		{
               			oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Ausführen der SQL-Anweisung - Änderungszähler stimmt nicht mit der LOG-Tabelle überein !"));
               		}
	        	}


	            /*
	             * jetzt die sql-statements der SQL_STACK_DAEMONS ausfuehren
	             */
	            if (oMV.get_bIsOK())
	            {
	            	for (int i=0;i<vSQL_STACK_DAEMON.size();i++)
	            	{
	            		//jetzt nachsehen, ob  XX_SQL_STACK_DAEMON() - objekte existieren
			            oMV.add_MESSAGE(this.doValidate(vSQL_STACK_DAEMON.get(i)));
		          		if (oMV.get_bIsOK())
		          		{
		          			Vector<String> vSQL_Trigger_Statements = this.getTriggerStatementsAfterSQL(vSQL_STACK_DAEMON.get(i), oMV);
		          			
		          			if (oMV.get_bIsOK())
		          			{
			          			//hier die eingeschleussten triggerstatements abarbeiten
		          				for (int ii=0;ii<vSQL_Trigger_Statements.size();ii++)
		          				{
		          					//String cSQL = this.makeExecutableStatement(vTriggers.get(i), this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
		    			            MyDBToolBox_Statement_Analyzer oStatementAnalyse = new MyDBToolBox_Statement_Analyzer(vSQL_Trigger_Statements.get(ii), this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
		    			            String cSQL = oStatementAnalyse.get_PlainSQL_Statement();
	
		          			        cSQL_MIT_Fehler=cSQL;
		          			        
		    		                DEBUG.System_println(cSQL, DEBUG.DEBUG_FLAG_SQL_EXEC);
		          			        
		      			        	oST.execute(cSQL);
			          			}
		          			}
		          		}
		          		if (oMV.get_bHasAlarms())
		          		{
		          			break;        //rollback folgt zwingend
		          		}
	            	}
	            }

	        
	        }
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	            oMV.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
	            oMV.add_MESSAGE(new MyE2_Alarm_Message(cSQL_MIT_Fehler,false), false);
	            
	            DEBUG.System_println("---------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
	            DEBUG.System_println("----- SQL-EXECUTION-ERROR -------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
	            DEBUG.System_println("----- Error: "+e.getLocalizedMessage(), DEBUG.DEBUG_FLAG_SQL_ERROR);
	            DEBUG.System_println(cSQL_MIT_Fehler, DEBUG.DEBUG_FLAG_SQL_ERROR);
	            DEBUG.System_println("---------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
	            
	        }
	        catch (myException e)
	        {
	        	e.printStackTrace();
	            oMV.add_MESSAGE(e.get_ErrorMessage(), false);
	        }
	
	        
	        // falls vErrorsRueck.get_bHasAlarms() = true MUSS ein rollback erfolgen
	        if (bCommit || oMV.get_bHasAlarms())
	        {
		        // jetzt prüfen, ob commit oder rollback
		        try
		        {
		            if (oMV.get_bIsOK())
		            {
		            	this.ownCommit();
		            }
		            else
		            {
		            	this.ownRollBack();
		            	bibALL.reset_Trigger_Counter();
		            }
		        }
		        catch (SQLException e)
		        {
		            oMV.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
		        }
	        }
	       
	       oST.Close();
       }
       return oMV;
    }
    
    
	private MyE2_MessageVector doValidate(XX_SQL_STACK_DAEMON oDaemon) throws myException 
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		/*
		 * sicherung der transaction:
		 * falls im inneren der doValidate - operationen ein commit auf der connection stattfindet, dann wuerde die zahl der sperren abnehmen.
		 * die darf nicht passieren
		 */
//		int intLocksStart = new Integer(bibDB.EinzelAbfrage(DB_META.get_SQL_CountLocks_Oracle_Session())).intValue();
		
		oMV.add_MESSAGE(oDaemon.doValidate(this.oMyConnection));
	  			
//		int intLocksEnde = new Integer(bibDB.EinzelAbfrage(DB_META.get_SQL_CountLocks_Oracle_Session())).intValue();
		
		if (bibALL.get_Trigger_Counter()==0)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("System-Error: Vector_SQL_STACK_DAEMON: doValidate: In validator must be a commit or rollback - statement !",false));
		}
		return oMV;
	}

    
    
	private Vector<String> getTriggerStatementsAfterSQL(XX_SQL_STACK_DAEMON oDaemon, MyE2_MessageVector oMV) throws myException 
	{
		Vector<String> vRueck = new Vector<String>();

		/*
		 * sicherung der transaction:
		 * falls im inneren der doValidate - operationen ein commit auf der connection stattfindet, dann wuerde die zahl der sperren abnehmen.
		 * die darf nicht passieren
		 */
//		int intLocksStart = new Integer(bibDB.EinzelAbfrage(DB_META.get_SQL_CountLocks_Oracle_Session())).intValue();

		vRueck.addAll(oDaemon.getTriggerStatementsAfterSQL(this.oMyConnection, oMV));
		
//		int intLocksEnde = new Integer(bibDB.EinzelAbfrage(DB_META.get_SQL_CountLocks_Oracle_Session())).intValue();

		if (bibALL.get_Trigger_Counter()==0)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("System-Error: Vector_SQL_STACK_DAEMON: doValidate: In Daemon.getTriggerStatementsAfterSQL() must be a commit - statement !",false));
		}

		return vRueck;
	}


	
	
    
    
    private Vector<MyDBToolBox_LOG_INFO> get_LogInfoVector(String cORA_SESSION,String cTimeStampReferenz)
    {
   		String[][] cAenderung = this.EinzelAbfrageInArray("SELECT TABLENAME,ID_TABLE,AKTION FROM " +
					bibE2.cTO()+".JD_DB_LOG " +
							" WHERE DBSESSION="+bibALL.MakeSql(cORA_SESSION)+
							" AND TIMESTAMPMILLISECS>="+bibALL.MakeSql(cTimeStampReferenz));
   		
   		Vector<MyDBToolBox_LOG_INFO> oRueck = new Vector<MyDBToolBox_LOG_INFO>();
   		
   		for (int i=0;i<cAenderung.length;i++)
   		{
   			oRueck.add(new MyDBToolBox_LOG_INFO(cAenderung[i][0],cAenderung[i][1],cAenderung[i][2],cORA_SESSION));
   		}
   		return oRueck; 
    }
    
    

    

    // hiermit wird bei nicht autocommit-connections das commit ausgeloesst
    public boolean Commit()
    {
        boolean RetWert = false;

        try
        {
        	this.ownCommit();
            RetWert = true;
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
        return RetWert;
    }

    
    // hiermit wird bei nicht autocommit-connections das rollback ausgeloesst
    public boolean Rollback()
    {
    
        boolean RetWert = false;

        try
        {
            this.ownRollBack();
            RetWert = true;
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }

        return RetWert;
    }


     
    
 
    /**
     * methode macht eine komplette einzelabfrage
     * übernimmt den sql-string und gibt einen
     * ergebnis-string zurück
     * Bei fehler kommt @@@@ERROR zurueck
     */
    public String EinzelAbfrage(String cSqlString)
    {
        String cRueck = "";

        String[][] cErsetzeTableView = this.bErsetzungTableView?this.cErsetzungTableView:null;

        MyDBResultSet oRS = new MyDBResultSet(this.oMyConnection,cSqlString,cErsetzeTableView);
        
        if (oRS.RS != null)
        {
            // dann wert abfragen  
            try
            {
            	this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);
            	
                oRS.RS.next();
                cRueck = oRS.RS.getString(1);

                if (oRS.RS.wasNull())
                { // kann sein, wenn noch kein datensatz vorhanden war
                    cRueck = "null";
                }
            }
            catch (SQLException e)
            {
                cRueck = "@@@@ERROR";
            }
            
            oRS.Close();
        }
        return (cRueck);
    }


    
    
    // methode macht eine komplette einzelabfrage
    // übernimmt den sql-string und gibt einen
    // ergebnis-string zurück
    public String EinzelAbfrage(String cSqlString, String cNullValue, String cErrorNotFoundValue, String cErrorOpenValue)
    {
        String cRueck = cErrorOpenValue; // falls oDB nicht offen oder die abfrage nicht funktioniert

        String[][] cErsetzeTableView = this.bErsetzungTableView?this.cErsetzungTableView:null;

        MyDBResultSet oRS = new MyDBResultSet(this.oMyConnection,cSqlString,cErsetzeTableView);
        
        if (oRS.RS != null)
        {
            // dann wert abfragen  
            try
            {
               	this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);
            	
               	oRS.RS.next();
                cRueck = oRS.RS.getString(1);

                if (oRS.RS.wasNull())
                { // kann sein, wenn noch kein datensatz vorhanden war
                    cRueck = cNullValue;
                }
            }
            catch (SQLException e)
            {
                cRueck = cErrorNotFoundValue;
            }
            
            oRS.Close();
        }

        return (cRueck);
    }

    // public methoden für einzelabfrage mit arrayrückgabe
    public String[][] EinzelAbfrageInArray(String cSqlString)
    {
        return _EinzelAbfrageInArray(cSqlString, "", false, false,-1,this.bErsetzungTableView);
    }

    // public methoden für einzelabfrage mit arrayrückgabe
    public String[][] EinzelAbfrageInArray(String cSqlString,boolean bErsetzeTableWithView)
    {
        return _EinzelAbfrageInArray(cSqlString, "", false, false,-1,bErsetzeTableWithView);
    }
    
    public String[][] EinzelAbfrageInArray(String cSqlString, String cNullWert,boolean bErsetzeTableWithView )
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, true, false,-1,bErsetzeTableWithView);
    }

    
    public String[][] EinzelAbfrageInArray(String cSqlString, String cNullWert)
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, true, false,-1,this.bErsetzungTableView);
    }

    public String[][] EinzelAbfrageInArray(String cSqlString, String cNullWert, int iMaxRowNumbers)
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, true, false,iMaxRowNumbers,this.bErsetzungTableView);
    }

    
    // public methoden für einzelabfrage mit arrayrückgabe
    public String[][] EinzelAbfrageInArrayFormatiert(String cSqlString)
    {
        return _EinzelAbfrageInArray(cSqlString, "", false, true,-1,this.bErsetzungTableView);
    }

    public String[][] EinzelAbfrageInArrayFormatiert(String cSqlString, String cNullWert)
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, true, true,-1,this.bErsetzungTableView);
    }

    
 
    private String[][] _EinzelAbfrageInArray(	String 		cSqlString, 
    											String 		cNullWert, 
    											boolean 	bNullErlaubt, 
    											boolean 	bFormatiert, 
    											int 		iMaxRowNumbers,
    											boolean     bErsetzeTableWithViews)
    {
        String[][] cRueck = null;
        Vector<String[]> oTemp = new Vector<String[]>();
        int i = 0;
        int j = 0;

        boolean bHatZeilen = false;
        boolean bNullWarDa = false;

        this.bMaxNumberOfRowsExceeded = false;

        int iCounter = 0;
        
        String[][] cErsetzeTableView = bErsetzeTableWithViews?this.cErsetzungTableView:null;

        MyDBResultSet oRS = new MyDBResultSet(this.oMyConnection,cSqlString,cErsetzeTableView);
        
        if (oRS.RS != null)
        {
            // dann wert abfragen
            cRueck = new String[0][0]; // leer, aber nicht null

            try
            {
               	this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);
            	
                int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();
                String[] cZeile = new String[iAnzahlSpalten];
                if (iAnzahlSpalten > 0)
                {
//                    cZeile = new String[iAnzahlSpalten];

                    while (oRS.RS.next())
                    {
                        bHatZeilen = true;
                        cZeile = new String[iAnzahlSpalten];

                        for (i = 0; i < iAnzahlSpalten; i++)
                        {
                           	MyResultValue oRSF = new MyResultValue(new MyMetaFieldDEF(oRS.RS,i, null),oRS.RS,false);
                       	
                        	if (bFormatiert)
                        	{
                        		cZeile[i] = oRSF.get_FieldValueFormated();
                        	}
                        	else
                        	{
                        		cZeile[i] = oRSF.get_FieldValueUnformated();
                        	}

                            if (cZeile[i] == null)
                            { // kann sein, wenn noch kein datensatz vorhanden war
                                cZeile[i] = cNullWert;
                                bNullWarDa = true; // falls null nicht erlaubt
                            }
                        }

                        DEBUG.System_println( Arrays.deepToString(cZeile) ,DEBUG.DEBUG_FLAG_DIVERS1);
                        
                        // jetzt rein in den vector
                        oTemp.addElement(cZeile.clone());
                        
                        
                        /*
                         * jetzt maxrows checken
                         */
                        if (iMaxRowNumbers>0)
                        {
                            iCounter ++;
                            if (iCounter>=iMaxRowNumbers)
                            {
                                this.bMaxNumberOfRowsExceeded = true;
                                break;
                            }
                        }
                        /*
                         * done
                         */
                        
                    }

                    if (bHatZeilen)
                    {
                        // dann die elemente des vector in das rückgabearray
                        cRueck = new String[oTemp.size()][iAnzahlSpalten];

                        for (i = 0; i < oTemp.size(); i++)
                        {
                            cZeile = (String[]) oTemp.elementAt(i);

                            for (j = 0; j < iAnzahlSpalten; j++)
                            {
                                cRueck[i][j] = cZeile[j];
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
            	e.printStackTrace();
                cRueck = null;
            }

            // falls die abfrage keine null implements Ergebnis zulässt, dann null zurückgeben
            if (bNullWarDa && !bNullErlaubt)
            {
                cRueck = null;
            }
            
            oRS.Close();
        }
         // sonst geht false zurück


        return (cRueck);
    }




    public MyResultValue[][] EinzelAbfrageInRSArray(String cSqlString) throws myException
    {
    	MyResultValue[][] 			oRueck =	null;
        Vector<MyResultValue[]>    	oTemp = 	new Vector<MyResultValue[]>();
        int 						i = 		0;
        int 						j = 		0;
        boolean 					bHatZeilen = false;
    //    int 						iCounter = 	0;

        this.bMaxNumberOfRowsExceeded = false;
        String[][] cErsetzeTableView = this.bErsetzungTableView?this.cErsetzungTableView:null;

        MyDBResultSet oRS = new MyDBResultSet(this.oMyConnection,cSqlString,cErsetzeTableView);
        
        if (oRS.RS != null)
        {
            try
            {
                // dann wert abfragen
                oRueck = new MyResultValue[0][0]; // leer, aber nicht null

            	
               	this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);

               	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();
                
               	if (iAnzahlSpalten > 0)
                {
                    while (oRS.RS.next())
                    {
                        bHatZeilen = true;

                        MyResultValue[] oZeile = new MyResultValue[iAnzahlSpalten];
                        
                        for (i = 0; i < iAnzahlSpalten; i++)
                        {
                        	oZeile[i] = new MyResultValue(this.oMetaColumnsOfResultSet.get_oMetaColumnDefinition(i),oRS.RS,true);
                        }

                        // jetzt rein in den vector
                        oTemp.addElement(oZeile);
                    }

                    if (bHatZeilen)
                    {
                        // dann die elemente des vector in das rückgabearray
                    	oRueck = new MyResultValue[oTemp.size()][iAnzahlSpalten];

                        for (i = 0; i < oTemp.size(); i++)
                        {
                        	MyResultValue[] oZeile = (MyResultValue[]) oTemp.get(i);
                            for (j = 0; j < iAnzahlSpalten; j++)
                            {
                            	oRueck[i][j] = oZeile[j];
                            }
                        }
                    }
                }
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            	throw new myException(this,"Error: "+e.getLocalizedMessage());
            }
            
            oRS.Close();
        }
        return (oRueck);
    }

    


    public MyConnection  get_MyConnection()
    {
    	return this.oMyConnection;
    }



    



    
    
    
    public void set_ErsetzungTableView(String[][] pErsetzungTableView){
//    	this.cErsetzungTableView = sort_ErsetzungTableView(pErsetzungTableView);
    	this.cErsetzungTableView = pErsetzungTableView;
    }
    
    
    
    /**
     * Sortiert pErsetzungTableView DESC und gibt das Ergebnis zurück
     * @author manfred
     * @date   21.01.2014
     * @param pErsetzungstabelleUnsortiert
     * @return
     */
    public String[][] sort_ErsetzungTableView(String[][] pErsetzungstabelleUnsortiert)
    {
    	 String[][] 		cResultSorted = null;
    	 
        if (pErsetzungstabelleUnsortiert == null)
        {
            cResultSorted = null;
        }
        else
        {
            cResultSorted = new String[pErsetzungstabelleUnsortiert.length][2];

            // jetzt sortieren, damit nicht z.b. eine ersetzung von JT_TABLES ueber eine Tabelle JT_TABLES_DEF gemacht wird
            String[] cHelp = new String[pErsetzungstabelleUnsortiert.length];

            for (int i = 0; i < pErsetzungstabelleUnsortiert.length; i++)
            {
                cHelp[i] = pErsetzungstabelleUnsortiert[i][0];
            }

            Arrays.sort(cHelp); // ist aufsteigende sortiert

            // absteigend erzeugen
            for (int i = pErsetzungstabelleUnsortiert.length - 1; i >= 0; i--)
            {
                cResultSorted[i][0] = cHelp[i];

                // jetzt den korresponierenden View-namen raussuchen
                for (int k = 0; k < pErsetzungstabelleUnsortiert.length; k++)
                {
                    if (pErsetzungstabelleUnsortiert[k][0].equals(cHelp[i]))
                    {
                        cResultSorted[i][1] = pErsetzungstabelleUnsortiert[k][1];
                    }
                }
            }
        }
        
        return cResultSorted;
    }

    
    
    
    
    
    public void ownCommit() throws SQLException
    {
    	this.conn.commit();
        bibALL.reset_Trigger_Counter();
    }
    

    public void ownRollBack() throws SQLException
    {
    	this.conn.rollback();
        bibALL.reset_Trigger_Counter();
    }

    
    
    public void set_bErsetzungTableView(boolean bWert)
    {
        this.bErsetzungTableView = bWert;
    }



	public MyMetaFieldDEF_HashMap get_oMetaColumnsOfResultSet() 
	{
		return this.oMetaColumnsOfResultSet;
	}


	public boolean get_bMaxNumberOfRowsExceeded() 
	{
		return bMaxNumberOfRowsExceeded;
	}



	//2014-10-24: neue Methode liefert native Objects
	public  ArrayList<HashMap<String, Object>> get_Native_DataObjects(String 		cSqlString, 
																			int 		iMaxRowNumbers,
																			boolean     bErsetzeTableWithViews) throws SQLException, myException  
	{

        ArrayList<HashMap<String, Object>>  al_Rueck = new ArrayList<HashMap<String,Object>>();
		
	
		int iCounter = 0;
	
		String[][] cErsetzeTableView = bErsetzeTableWithViews?this.cErsetzungTableView:null;
	
		MyDBResultSetWithException oRS = new MyDBResultSetWithException(this.oMyConnection,cSqlString,cErsetzeTableView);
		
		if (oRS.RS != null)	{
			
			this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);
			int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

			if (iAnzahlSpalten > 0) {
				
				while (oRS.RS.next()) {
					HashMap<String, Object>  hmTemp = new HashMap<String, Object>();
		
					for (int i = 0; i < iAnzahlSpalten; i++) {
						MyMetaFieldDEF oMetaDef = new MyMetaFieldDEF(oRS.RS,i, null);
						MyResultValue oRSF = new MyResultValue(oMetaDef,oRS.RS,false);
						hmTemp.put(oMetaDef.get_FieldLabel(), oRSF.get_oNativeDataObject());
					}
					al_Rueck.add(hmTemp);
					
					/*
					 * jetzt maxrows checken
					 */
					if (iMaxRowNumbers>0)
					{
						iCounter ++;
						if (iCounter>=iMaxRowNumbers) {
							this.bMaxNumberOfRowsExceeded = true;
							break;
						}
					}
					/*
					 * done
					 */
				}
			}
		}
		
		oRS.Close();
		return al_Rueck;
	}
	
	
	
	/**
	 * generiert eine sammlung von feldern, die automatisch gesetzt werden und deshalb 
	 * in den Feldlisten uebersprungen werden
	 * @return (normalerweise) {"ID_MANDANT","LETZTE_AENDERUNG","GEAENDERT_VON","ERZEUGT_VON","ERZEUGT_AM"});

	 */
	public Vector<String> get_AutomaticWrittenFields() {
		Vector<String>  vFields = new Vector<String>();
		
		//zuerst die ausnahme-felder dieser ToolBox
		if (this.cZusatzFelder != null) {
			for (int i=0;i<this.cZusatzFelder.length;i++) {
				vFields.add(this.cZusatzFelder[i][0]);
			}
		}

		//dann die vom Trigger gefuellten
		for (DB_STATICS.TRIGGER_FIELDS trf: DB_STATICS.TRIGGER_FIELDS.values()) {
			vFields.add(trf.toString());
		}
		return vFields;
	}



	
	/**
	 * 
	 * @return number of updates from last execMultiSql-statement
	 */
	public Integer get_count_last_updates_executed() {
		return this.i_count_last_updates_executed;
	}
	
	
	
	
	
	
	
	
	 
    ///
    ///
    ///  Statements mit Prepared Statements
    ///
    ///
	
	
	// public methoden für einzelabfrage mit arrayrückgabe
    public String[][] EinzelAbfrageInArray(SqlStringExtended cSqlString)
    {
        return _EinzelAbfrageInArray_Prepared(cSqlString, "", true, false,-1,this.bErsetzungTableView);
    }

    /**
     * Prepared Statement mit null-Wert-Definition
     * @author manfred
     * @date 08.05.2018
     *
     * @param cSqlString
     * @param cNullWert
     * @return
     */
    public String[][] EinzelAbfrageInArray(SqlStringExtended cSqlString, String cNullWert ){
    	return _EinzelAbfrageInArray_Prepared(cSqlString,cNullWert,true,false,-1,this.bErsetzungTableView);
    }
	
    /**
     * Abfrage, die die Werte in einem Array zurückliefert
     * @author manfred
     * @date 22.03.2018
     *
     * @param oSqlExtended
     * @param cNullWert
     * @param bNullErlaubt
     * @param bFormatiert
     * @param iMaxRowNumbers
     * @param bErsetzeTableWithViews
     * @return
     */
	private String[][] _EinzelAbfrageInArray_Prepared(
				SqlStringExtended oSqlExtended,
				String cNullWert, 
				boolean bNullErlaubt,
				boolean bFormatiert, 
				int iMaxRowNumbers, 
				boolean bErsetzeTableWithViews) {
		String[][] cRueck = null;
		Vector<String[]> oTemp = new Vector<String[]>();
		int i = 0;
		int j = 0;

		boolean bHatZeilen = false;
		boolean bNullWarDa = false;

		this.bMaxNumberOfRowsExceeded = false;

		int iCounter = 0;

		String[][] cErsetzeTableView = bErsetzeTableWithViews ? this.cErsetzungTableView : null;

		MyDBResultSet oRS = new MyDBResultSet_Prepared(this.oMyConnection, oSqlExtended.getSqlString(), oSqlExtended.getValuesList(), cErsetzeTableView);

		if (oRS.RS != null) {
			// dann wert abfragen
			cRueck = new String[0][0]; // leer, aber nicht null

			try {
				this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);

				int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();
				String[] cZeile = new String[iAnzahlSpalten];
				if (iAnzahlSpalten > 0) {
					// cZeile = new String[iAnzahlSpalten];

					while (oRS.RS.next()) {
						bHatZeilen = true;
						cZeile = new String[iAnzahlSpalten];

						for (i = 0; i < iAnzahlSpalten; i++) {
							MyResultValue oRSF = new MyResultValue(new MyMetaFieldDEF(oRS.RS, i, null), oRS.RS, false);

							if (bFormatiert) {
								cZeile[i] = oRSF.get_FieldValueFormated();
							} else {
								cZeile[i] = oRSF.get_FieldValueUnformated();
							}

							if (cZeile[i] == null) { // kann sein, wenn noch
														// kein datensatz
														// vorhanden war
								cZeile[i] = cNullWert;
								bNullWarDa = true; // falls null nicht erlaubt
							}
						}

						DEBUG.System_println(Arrays.deepToString(cZeile), DEBUG.DEBUG_FLAG_DIVERS1);

						// jetzt rein in den vector
						oTemp.addElement(cZeile.clone());

						/*
						 * jetzt maxrows checken
						 */
						if (iMaxRowNumbers > 0) {
							iCounter++;
							if (iCounter >= iMaxRowNumbers) {
								this.bMaxNumberOfRowsExceeded = true;
								break;
							}
						}
						/*
						 * done
						 */

					}

					if (bHatZeilen) {
						// dann die elemente des vector in das rückgabearray
						cRueck = new String[oTemp.size()][iAnzahlSpalten];

						for (i = 0; i < oTemp.size(); i++) {
							cZeile = (String[]) oTemp.elementAt(i);

							for (j = 0; j < iAnzahlSpalten; j++) {
								cRueck[i][j] = cZeile[j];
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				cRueck = null;
			}

			// falls die abfrage keine null implements Ergebnis zulässt, dann
			// null zurückgeben
			if (bNullWarDa && !bNullErlaubt) {
				cRueck = null;
			}

			oRS.Close();
		}
		// sonst geht false zurück

		return (cRueck);
	}
	
	
	
	

	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param oSqlExtended
	 * @param bErsetzeTableWithViews
	 * @return s VEK<Object[]> where Object[] represents one dataset, empty VEK on no results, null onm error
	 */
	public VEK<Object[]> getResultLines(SqlStringExtended oSqlExtended,	boolean bErsetzeTableWithViews) {

		VEK<Object[]> retArr =  new VEK<Object[]>();

		String[][] cErsetzeTableView = bErsetzeTableWithViews ? this.cErsetzungTableView : null;

		MyDBResultSet oRS = new MyDBResultSet_Prepared(this.oMyConnection, oSqlExtended.getSqlString(), oSqlExtended.getValuesList(), cErsetzeTableView);

		if (oRS.RS != null) {
			// dann wert abfragen
			
			try {
				this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);

				int countCols = oRS.RS.getMetaData().getColumnCount();
				
				if (countCols > 0) {
					while (oRS.RS.next()) {
						Object[] zeile = new Object[countCols];
						for (int i = 0; i < countCols; i++) {
							zeile[i]=new MyResultValue(new MyMetaFieldDEF(oRS.RS, i, null), oRS.RS, false).get_oNativeDataObject();
						}
						retArr.add(zeile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				retArr = null;
			}

			oRS.Close();
		} else {
			retArr=null;
		}

		return (retArr);
	}

	
	
	
	

	/**
	 * 
	 * @author martin
	 * @date 29.10.2020
	 *
	 * @param oSqlExtended
	 * @param bErsetzeTableWithViews
	 * @return
	 */
	public VEK<DBMap> getResultMaps(SqlStringExtended oSqlExtended,	boolean bErsetzeTableWithViews) {

		VEK<DBMap> retArr =  new VEK<DBMap>();

		String[][] cErsetzeTableView = bErsetzeTableWithViews ? this.cErsetzungTableView : null;

		MyDBResultSet oRS = new MyDBResultSet_Prepared(this.oMyConnection, oSqlExtended.getSqlString(), oSqlExtended.getValuesList(), cErsetzeTableView);

		if (oRS.RS != null) {
			// dann wert abfragen
			
			try {
				this.oMetaColumnsOfResultSet = new MyMetaFieldDEF_HashMap(oRS.RS);

				int countCols = oRS.RS.getMetaData().getColumnCount();
				
				if (countCols > 0) {
					while (oRS.RS.next()) {
						DBMap zeile = new DBMap();
						for (int i = 0; i < countCols; i++) {
							MyResultValue resVal =new MyResultValue(new MyMetaFieldDEF(oRS.RS, i, null), oRS.RS, false);
							zeile._put(resVal.get_cFieldLABEL(), resVal.get_oNativeDataObject());
						}
						retArr.add(zeile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				retArr = null;
			}

			oRS.Close();
		} else {
			retArr=null;
		}

		return (retArr);
	}

	
	
	/**
	 * 
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param ppSql
	 * @param parameters
	 * @return
	 */
	public MyDBResultSet_Prepared OpenResultSet_Prepared (String ppSql, Vector<ParamDataObject> parameters){
    	
    	String[][] cErsetzeTableView = this.bErsetzungTableView?this.cErsetzungTableView:null;

    	MyDBResultSet_Prepared oRS = null;
    	oRS = new MyDBResultSet_Prepared(this.oMyConnection, ppSql,parameters, cErsetzeTableView);
    	return oRS;
    }
    
    
    
    // 2011-09-06: neuer raw-aufruf  VORSICHT !!!!!

    /**
     * Statement wird geschlossen nach Aufruf.
     * @author manfred
     * @date 22.02.2018
     *
     * @param prepStmt
     * @param bMitCommit
     * @return
     */
    public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(MyDBStatementPrepared prepStmt, boolean bMitCommit)
    {
        return ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(prepStmt,bMitCommit,true, null);
    }

    
    /**
     * 
     * @author manfred
     * @date 31.08.2018
     *
     * @param sqlStringExt
     * @param bMitCommit
     * @return
     * @throws myException 
     */
	public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(SqlStringExtended sqlStringExt, boolean bMitCommit) {
		MyDBStatementPrepared prep;
		boolean bRet = false;
		try {
			prep = sqlStringExt.generatePreparedStatement(this);
			bRet = this.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(prep, bMitCommit);
		} catch (myException e) {
			e.printStackTrace();
		}
		return bRet;
	}

	
	/**
	 * 
	 * @author manfred
	 * @date 31.08.2018
	 *
	 * @param sqlStringExt
	 * @param bMitCommit
	 * @param bCloseStatement
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(SqlStringExtended sqlStringExt, boolean bMitCommit,boolean bCloseStatement, MyE2_MessageVector mv){
		boolean bRet = false;
		MyDBStatementPrepared prep;
		try {
			prep = sqlStringExt.generatePreparedStatement(this);
			bRet = this.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(prep, bMitCommit, bCloseStatement, mv);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return bRet;
	}
	

	
    /**
     * 
     * @author manfred
     * @date 22.02.2018
     *
     * @param prepStmt
     * @param bMitCommit
     * @return
     */
    public boolean ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(MyDBStatementPrepared prepStmt, boolean bMitCommit,boolean bCloseStatement, MyE2_MessageVector mv)
    {
        boolean 	bMustRollback = 	false;
        boolean 	bRueck = 			false;
        
        if (prepStmt == null) return false;
    	
       try
        {
        	prepStmt.execute();
        	if (bCloseStatement ){
        		prepStmt.Close();
        	}
            bRueck = true;
        }
        catch (SQLException e)
        {
        	if (mv != null) {
        		mv._addAlarmUT("MyDBToolBox:ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS:Error executing preparedStatement: "+prepStmt.get_sql()+": "+e.getLocalizedMessage());
        	}
        	e.printStackTrace();
            bRueck = false;
            bMustRollback = true;
            if (bCloseStatement){
            	prepStmt.Close();
            }
        }
        
        if (bRueck && bMitCommit)
        {
            try
            {
            	this.ownCommit();
            }
            catch (Exception e)
            {
            	e.printStackTrace();
               	if (mv != null) {
            		mv._addAlarmUT("MyDBToolBox:ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS:Error executing ownCommit: "+prepStmt.get_sql()+": "+e.getLocalizedMessage());
            	}
     
            	
                bMustRollback = true; // falls commit schiefgeht, dann muss ein rollback probiert werden
                bRueck = false;
            }
        }

        // wenn commit schiefgeht, dann rollback
        if (bMustRollback)
        {
            try
            {
            	this.ownRollBack();
            }
            catch (Exception e)
            {
                e.printStackTrace();
               	if (mv != null) {
            		mv._addAlarmUT("MyDBToolBox:ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS:Error executing ownRollback: "+prepStmt.get_sql()+": "+e.getLocalizedMessage());
            	}
            }
        }

        return bRueck;
     }

  ///
  ///
  ///
  ///
  ///
  ///
  ///
  ///
  ///
  ///
  ///
  ///

	/**
	 * execution of multisave-records in one transaction
	 * @author martin
	 * @date 12.02.2019
	 *
	 * @param recs
	 * @param commit
	 * @return messagevektor
	 */
    public <T extends Rec20> MyE2_MessageVector  execMultiRecSave(Collection<T> recs, boolean commit) {
    	MyE2_MessageVector mv = bibMSG.newMV();
    	
    	for (Rec20 r: recs) {
    		
    		try {
				r._SAVE(false, mv);
			} catch (myException e) {
				e.printStackTrace();
				mv._add(e.get_ErrorMessage());
			}
    		
    		if (mv.hasAlarms()) {
    			break;
    		}
    	}
    	
        // falls vErrorsRueck.get_bHasAlarms() = true MUSS ein rollback erfolgen
        if (commit || mv.get_bHasAlarms())   {
	        // jetzt prüfen, ob commit oder rollback
	        try   {
	            if (mv.get_bIsOK())  {
	            	this.ownCommit();
	            } else {
	            	this.ownRollBack();
	            	bibALL.reset_Trigger_Counter();
	            }
	        } catch (SQLException e) {
	        	mv.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage(),false), false);
            	try {
					this.ownRollBack();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

	        }
        }
    	return mv;
    }
	
	
	
	
    
    /**
     * simple raw-sql-methode
     * @author martin
     * @date 29.10.2019
     *
     * @param vSql
     * @param bMitCommit
     * @return
     */
    public boolean execRawStatements(VEK<String> vSql, boolean bMitCommit) throws myException  {
    	
        boolean 	ret = 			    false;
        
    	MyDBStatement oST = new MyDBStatement(this.oMyConnection);
    	
    	
    	try {
    		for (String sql: vSql) {
 	        	oST.execute(sql);
    		}
    		if (bMitCommit) {
            	this.ownCommit();
    		}
    		ret = true;
    	} catch (SQLException ex) {
    		ret = false;
    		throw new myException(ex.getErrorCode()+" Code: a4354c56-fa6b-11e9-8f0b-362b9e155667(2)");
    	} catch (Exception ex) {
    		ret = false;
    		throw new myException(ex.getLocalizedMessage()+" Code: a4354c56-fa6b-11e9-8f0b-362b9e155667(3)");

    	} finally {
    		oST.Close();
    	}

    	if (!ret) {
            try  {
            	this.ownRollBack();
            } catch (Exception e)  {
                e.printStackTrace();
        		throw new myException(e.getLocalizedMessage()+" Code: a4354c56-fa6b-11e9-8f0b-362b9e155667(4)");
            }
    	}
    	return ret;
     }
    

    
    
    
    
    

    /**
     * 
     * @author martin
     * @date 07.11.2019
     *
     * @param statements
     * @param commit
     * @param mv
     * @return number ob executed statements
     * @throws myException 
     */
    public int executeRaw(VEK<SqlStringExtended> statements, boolean commit) throws myException   {
    	
    	boolean  				mustRollBack = 	false;
        MyDBStatementPrepared  	prepStmt = 		null;
        int 					counter = 0;
    	
        String 					actualStatement =  null;
        
        try {
        	for (SqlStringExtended s_ex: statements) {
        		actualStatement = s_ex.getSqlString();
        		prepStmt = s_ex.generatePreparedStatement(this);
        		counter += prepStmt.execute();
        		prepStmt.Close();
        		prepStmt = null;
        	}
            if (commit) {
              	this.ownCommit();
            }
            return counter;
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	mustRollBack=true;
        	throw new myException(e.getLocalizedMessage()+" <"+S.NN(actualStatement)+">");
        } finally {
        	if (prepStmt!=null) {
        		prepStmt.Close();
        	}
        	
			if (mustRollBack) {
				try {
					this.ownRollBack();
				} catch (SQLException e) {
		        	throw new myException(e.getLocalizedMessage()+" <"+S.NN(actualStatement)+">");
				}
			}
		}
     }

	
    
    

    /**
     * 
     * @author martin
     * @date 07.11.2019
     *
     * @param statements
     * @param commit
     * @param mv
     * @return number ob executed statements
     * @throws myException 
     */
    public int executeStatements(VEK<SqlStringExtended> statements, boolean commit) throws myException, SQLException   {
    	
    	boolean  				mustRollBack = 	false;
        MyDBStatementPrepared  	prepStmt = 		null;
        int 					counter = 0;
    	
        String 					actualStatement =  null;
        
        try {
        	for (SqlStringExtended s_ex: statements) {
        		actualStatement = s_ex.getSqlString();
        		prepStmt = s_ex.generatePreparedStatement(this);
        		counter += prepStmt.execute();
        		prepStmt.Close();
        		prepStmt = null;
        	}
            if (commit) {
              	this.ownCommit();
            }
            return counter;
        }
        catch (SQLException e) {
        	e.printStackTrace();
        	mustRollBack=true;
        	throw e;
        } finally {
        	if (prepStmt!=null) {
        		prepStmt.Close();
        	}
        	
			if (mustRollBack) {
				try {
					this.ownRollBack();
				} catch (SQLException e) {
		        	throw new myException(e.getLocalizedMessage()+" <"+S.NN(actualStatement)+">");
				}
			}
		}
     }

	
}
