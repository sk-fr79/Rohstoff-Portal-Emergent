import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.createSession;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.TaskHandler;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;




/*
 * 
 * Servlet zum starten von Batch-Modulen
 * startBatch muss in der web.xml als Applet definiert sein (im Beispiel unten mit dem Startpunkt "/batch"
 * z.B.
 * curl "http://localhost:8080/rohstoff_app/batch?user=batchuser&pw=xxxx&task=lagerstatus"
 * 
 */

public class startBatch extends HttpServlet
{
    public void init() throws ServletException
    {
    }

    public void destroy()
    {
    }

    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

        /*
         * hier auf jeden fall eine neue session eroeffnen
         */
       HttpSession SES = request.getSession(true);
       
       System.out.println("startBatch::doGet::SessionID..." + SES.getId());
       
       
        if (! SES.isNew())
        {
        	try
        	{
        		bibALL.CloseConnection_In_SESSION(SES);   // falls die session bereits aktiv war, dann die connections zur datenbank schliessen
        	}
        	catch (Exception ex)
        	{}
        	
            SES.invalidate();
            SES = request.getSession(true);
        }
        
        
        //
        // die übergebenen Parameter in die Session übernehmen...
        //
        HashMap<String, String> hmParameters = new HashMap<String, String>();
        Enumeration<?> names = request.getParameterNames();
        while (names.hasMoreElements())
        {
        	String key = (String) names.nextElement();
        	String[] value = request.getParameterValues(key);
        	String sValue = value[0];
        	hmParameters.put(key, sValue);
        }

        String cAblaufFehler = "";
        
    	// die Parameter an die Session übergeben um sie an das Echo-Servlet weiterzureichen
        SES.setAttribute("__BATCH_PARAMS__", hmParameters);

        try {
        	
        	bibE2.setSessionManually(SES);

        	cAblaufFehler = runBatch(SES,request,response);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cAblaufFehler = "Fehler bei der Batch-Ausführung:" + e.getMessage();
		}
  
        
        // falls ein fehler aufgetreten ist, dies jetzt melden
        // zum beispiel falsches passwort
        if (!cAblaufFehler.equals(""))
        {
        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(this.cHTML_BaueStatusMeldung(cAblaufFehler));
            
        }
        
        
        
        // DB-Connection beenden
        try
    	{
    		bibALL.CloseConnection_In_SESSION();   // falls die session bereits aktiv war, dann die connections zur datenbank schliessen
    	}
    	catch (Exception ex)
    	{}
    	
        SES.invalidate(); // session abschiessen
        System.gc();
        
    }

    
    
	
	/**
	 * Starten des Batchlaufes.
	 * Prüfung der nötigen Parameter
	 * @param ses
	 * @param request
	 * @param response
	 * @return
	 */
	private String runBatch(HttpSession ses,HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, String> hmParameters =getParameters(ses);
		String sErrorMessage = "";
		boolean bOK = true;
		
		Date dtStart = null;
		Date dtEnd = null;
		
		// user/passwort/task rausziehen. Muss immer vorhanden sein.
		String sUser = hmParameters.get("user");
		String sPw   = hmParameters.get("pw");
		String sTask = hmParameters.get("task");
		
		
		TaskHandler taskHandler = new TaskHandler();
		
		//hier wird jetzt die session mit den noetigen infos ausgestattet
		createSession oSES_Cr = null;
		try
		{
			// Beginn der Verarbeitung
			dtStart = new GregorianCalendar().getTime();

			// Session aufbauen, damit die DB-Verbindung korrekt initialisiert wird und der Benutzer dem Mandanten richtig zugeordnet wird.
			oSES_Cr = new createSession(
					   sUser.trim(), 
					   sPw.trim(), 
//					   bibE2.get_CurrSession(),
					   ses,
					   getServletContext(),
					   request);
			
		       if (! oSES_Cr.getSessionOK())
			   {
		    	   sErrorMessage += oSES_Cr.getErrorMessage();
			   }
		       else
		       {
		    	    
		    		// Schreiben des Timestamp
		    		WriteTimeStamp();
		    		
		    		
					//
					//  ausführen der Batch
					//
					taskHandler.runTask(hmParameters);
					
					// Rückgabewerte ermitteln
					sErrorMessage = taskHandler.getLogMessage();
					
					
					// Ende der Verarbeitung
					dtEnd = new GregorianCalendar().getTime();
					
		       }
		       
		       
		       // Logmessage schreiben
		       this.writeLog(sUser, sTask, hmParameters, sErrorMessage, taskHandler.getBatchStatusOK(), dtStart, dtEnd);
		       
		       
		       // Session und die Datenbank-Verbindungen beenden 
		       
		       
		} 
		catch (myException e1)
		{
			sErrorMessage+= e1.getMessage();	
		}
		
		return sErrorMessage;
	}

	
    
    /**
     * Hilfmethode zum lesen von Parametern aus der Session
     * @param ses
     * @return
     */
	private HashMap<String, String> getParameters(HttpSession ses){
		HashMap<String, String>  hmParameters =  (HashMap<String, String>) ses.getAttribute("__BATCH_PARAMS__");
		return hmParameters;
	}	
	
	
	/**
	 * Hilfsmethode zum schreiben eines Timestamps für die Sicherstellung der Verarbeitung der Daten
	 * @throws myException
	 */
	private void WriteTimeStamp()throws myException{
		String cStamp = bibDB.EinzelAbfrage("SELECT "+DB_META.ORA_TIMESTAMP_MILLISECS+" FROM DUAL");
		if (cStamp.startsWith("2"))                 //sicherheitsabfrage, gilt bis ins Jahr 2999, dann beginnt der stamp mit 3
		{
			bibE2.set_LAST_DB_TIMESTAMP(cStamp);
			DEBUG.System_println("DB-Timestamp geschrieben:   ...: " + cStamp + "  <Batchverarbeitung>", DEBUG.DEBUG_FLAG_SQL_TIMESTAMP);
		}
		else
		{
			throw new myException(this,"Error with Timestamp !!");
		}
	}
	

	/** 
	 * Schreibt einen Logeintrag in die DB
	 * 
	 * @param sUser
	 * @param sTask
	 * @param hmParameters
	 * @param sMessage
	 * @param dtStart
	 * @param dtEnd
	 */
	private void writeLog(  String sUser, 
							String sTask, 
							HashMap<String, String> hmParameters, 
							String sMessage, 
							String sStatusOK, 
							Date dtStart, 
							Date dtEnd){
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		
		String sParameters = "";
		for ( Map.Entry<String, String> e : hmParameters.entrySet() ){
			if (e.getKey().equals("pw")){
				sParameters += e.getKey() + "= **** ;";
			} else {
				sParameters += e.getKey() + "=" + e.getValue() + ";";
			}
		}
				
		
		String sStart = "";
		String sEnde = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		if (dtStart != null){
			sStart = "to_date( '" + sdf.format(dtStart) +"','dd.mm.yyyy hh24:mi:ss')";
		} else {
			sStart = "to_date( '" + sdf.format(new Date()) +"','dd.mm.yyyy hh24:mi:ss')";
		}
		if (dtEnd != null){
			sEnde= "to_date( '" + sdf.format(dtEnd) +"','dd.mm.yyyy hh24:mi:ss')";
		} else {
			sEnde = "to_date( '" + sdf.format(new Date()) +"','dd.mm.yyyy hh24:mi:ss')";
		}

		String sTaskName = (sTask != null ? sTask : "- fehlt -" );
		
		try {
			oSql.addSQL_Paar("ID_BATCH_LOG", "SEQ_BATCH_LOG.NEXTVAL", false);
			oSql.addSQL_Paar("TASKNAME", sTaskName , true);
			oSql.addSQL_Paar("USERNAME",sUser , true);
			oSql.addSQL_Paar("PARAMETERLIST", sParameters , true);
			oSql.addSQL_Paar("BEGIN_TIME", sStart , false);
			oSql.addSQL_Paar("END_TIME", sEnde , false);
			oSql.addSQL_Paar("MESSAGE", sMessage, true);
			oSql.addSQL_Paar("STATUS_OK", sStatusOK, true);
		} catch (myException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    String sSql = oSql.get_CompleteInsertString("JD_BATCH_LOG", bibE2.cTO());

	    bibDB.ExecSQL(sSql, true);
	    
		
	}
    
	
    
	/**
	 * Weiterleitung von Request und Respons via get
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
    

    /**
     * Statusmeldung als HTML-Text für die einfache Ausgabe
     * @param cMsg
     * @return
     */
    private String cHTML_BaueStatusMeldung(String cMsg)
    {
		String cHTML = "";
		cHTML +="<HTML>"+"\n";
		cHTML +="<HEAD>"+"\n";
		cHTML +="<TITLE>Batchmode</TITLE>"+"\n";
		cHTML +="</HEAD>"+"\n";
		cHTML +="<BODY>"+"\n";
		cHTML +="<H1>Meldung :</H1><BR>"+"\n";
		cHTML += cMsg +"\n";
		cHTML +="</BODY>"+"\n";
		cHTML +="</HTML>"+"\n";
        return cHTML;
    }
    
}
