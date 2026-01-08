/*
 * Created on 12.04.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep.dataTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * @author martin
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MyConnection 
{
    
    private 	Connection 	oConnection 			= null;
    
    
    
    private 	String 		cJDBC_STRING			= null;
    private 	String 		cUSER 					= null;
    private 	String 		cPASSWORT 				= null;
    private 	String 		cDBKENNUNG 				= null;
    private 	String 		cCLASSNAME				= null;

    
    
    protected void finalize()
    {
        if (this.oConnection != null)
        {
            try
            {
                this.oConnection.close();
                this.oConnection = null;
            }
            catch(Exception ex) {}
        }
    }
    
    
    

    /**
     * @param cClassName
     * @param cjdbc_string
     * @param cuser
     * @param cpasswort
     * @param cdbkennung (ORACLE oder SAPDB)
     * @throws myException
     */
    public MyConnection(String cClassName, String cjdbc_string, String cuser, String cpasswort, String cdbkennung) throws myException
    {
        super();
        cJDBC_STRING 	= cjdbc_string;
        cUSER 			= cuser;
        cPASSWORT 		= cpasswort;
        cDBKENNUNG 		= cdbkennung;
        this.cCLASSNAME	= cClassName;
        
        if (!(cdbkennung.equals(DB_META.DB_ORA)||cdbkennung.equals(DB_META.DB_SAP) ))
            throw new myException("MyConnection: Only "+DB_META.DB_ORA+" or "+DB_META.DB_SAP +" are allowed !!!"); 
            
        try
        {
            
            Class.forName(this.cCLASSNAME);
            this.oConnection = DriverManager.getConnection(this.cJDBC_STRING, this.cUSER, this.cPASSWORT);
            this.oConnection.setAutoCommit(true);
            
            
            Statement rsStmt = this.oConnection.createStatement(); // statement-object für result-set
            if (cdbkennung.equals(DB_META.DB_ORA))
            {
            	rsStmt.execute("ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'");
            	
            	//2012-13-07: hier, falls bekannt, die id_user in die userenv-variable sys_context('USERENV','CLIENT_IDENTIFIER') schreiben
            	if (bibALL.get_RECORD_USER()!=null)
            	{
            		rsStmt.execute("CALL dbms_session.set_identifier('"+bibALL.get_RECORD_USER().get_ID_USER_cUF()+"')");
            	}
            	
            	// Prefetch size auf 100 setzen anstatt auf 10(default)
            	if (this.oConnection instanceof OracleConnection){
            		
            		int prefetchSize = 10;
            		String sSize = (String) bibALL.getSessionValue("prefetch_size");
            		if (sSize != null){
            			try {
							prefetchSize = Integer.parseInt(sSize);
							((OracleConnection)this.oConnection).setDefaultRowPrefetch(prefetchSize);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
            		}
            		
            		//
            		// ORACLE-11 Workaround: Stand 2014-05-30 (bug in) _optimizer_use_feedback (neu in ver. 11) laesst laut foren wie auch in diesem Code
            		// teilweise die 2. Ausfuehrung eines statements extrem langsam werden. Ausschalten des features beendet verhalten.
            		//
            		// ab version 11 gibts ein Problem mit dem optimizer. Wird hier abgeschaltet
            		// http://www.dba-oracle.com/t_cardinality_feedback.htm
            		//
            		int nVersion = 	((OracleConnection)this.oConnection).getMetaData().getDatabaseMajorVersion();
            		if (nVersion > 10){
            			rsStmt.execute("ALTER SESSION SET \"_optimizer_use_feedback\" = false");
            		}
            	}
            }
            rsStmt.close();
            
            //hier wird ein eintrag in JD_LOG_SESSIONS geschrieben
            //select sid from v$mystat where rownum = 1 liefert die gerade erzeugte session
            //MyDBResultSet oRS = new MyDBResultSet(this,"SELECT SID FROM V$MYSTAT WHERE ROWNUM = 1",null);
//            MyDBResultSet oRS = new MyDBResultSet(this,"SELECT SYS_CONTEXT('USERENV','SESSIONID') FROM DUAL",null);
//            oRS.RS.next();
//            String cORA_SESSION = oRS.RS.getString(1);

//            DEBUG.System_println("Oracle-session-id  ????: "+cORA_SESSION, null);
            
            this.oConnection.setAutoCommit(false);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
           this.oConnection = null;
           throw new myException("MyConnection: Creating connection failed !!"+e.getLocalizedMessage()); 
        }
    }

    

    public Connection get_oConnection()
    {
        return oConnection;
    }
    public String get_cDBKENNUNG()
    {
        return cDBKENNUNG;
    }




	public String get_cUSER() 
	{
		return cUSER;
	}
	
	
    public void ownCommit() throws SQLException
    {
    	this.oConnection.commit();
        bibALL.reset_Trigger_Counter();
    }
    

    public void ownRollBack() throws SQLException
    {
    	this.oConnection.rollback();
        bibALL.reset_Trigger_Counter();
    }

	
}
