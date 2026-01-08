package panter.gmbh.indep.batch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_BATCH_TASK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BATCH_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class TaskHandler {
	
	private String m_sLogMessage = "";
	private boolean m_bStatusOK = false;
	
	
	public boolean runTask( HashMap<String, String> hmParameters){
		boolean bRet = false;
		
		boolean bTaskIsDefined = false;
		boolean bTaskIsValid = false;

		RECORD_BATCH_TASK 	oTask = null;
		RECORD_BATCH_USER 	oTaskUser = null;
		RECORD_USER			oUser = null;
		
		// user/passwort/task rausziehen. Muss immer vorhanden sein.
		String sUser = hmParameters.get("user");
		String sPw   = hmParameters.get("pw");
		String sTask = hmParameters.get("task");
		


		if (sTask == null || sTask.equals("")){
			m_sLogMessage += "Der Methoden-Parameter ist nicht definiert!";
			m_sLogMessage += "\n";
			return bRet;
		}
		
		if (sUser == null || sUser.equals("")){
			m_sLogMessage += "Der Benutzer-Parameter ist nicht definiert!";
			m_sLogMessage += "\n";
			return bRet;
		}
		
		if (sPw == null || sPw.equals("")){
			m_sLogMessage += "Der Passwort-Parameter ist nicht definiert!";
			m_sLogMessage += "\n";
			return bRet;
		}
		
		
		// prüfen, ob der Taskname auch in der Tabelle JD_BATCH_TASKS vorhanden ist.
		try {
			oTask = new RECORD_BATCH_TASK("TASKNAME = '" + sTask + "'");
			
			if (!oTask.isEmpty()){
				bTaskIsDefined = true;
			}
			
		} catch (myException e1) {
//			m_sLogMessage += e1.getMessage();
			m_sLogMessage += "\n";
		}
	
		if (!bTaskIsDefined){
			m_sLogMessage += "Task " + sTask + " ist nicht definiert.";
			m_sLogMessage += "\n";
			return bRet;
		}
			
		
		
		try {
			oTaskUser = new RECORD_BATCH_USER("ID_BATCH_TASK = " + oTask.get_ID_BATCH_TASK_cUF() + " AND ID_USER = " + bibALL.get_ID_USER());
			if (!oTaskUser.isEmpty()){
				bTaskIsValid = true;
			}
		} catch (myException e1) {
//			m_sLogMessage += e1.getMessage();
			m_sLogMessage += "\n";
		}

		
		if (!bTaskIsValid){
			m_sLogMessage += "Task " + sTask + " ist dem Benutzer nicht zugeordnet.";
			m_sLogMessage += "\n";
			return bRet;
		}

		
		// Aufruf per Reflection
		Class cBatchClass;
		ICallableTask cBatchInstance;

		Method methodRunTask;
		Method methodSetParameter;
		Method methodGetMessage;
		String sMsg = "";
		
		try {
			 	String sClassName = "?";
				try {
					sClassName = oTask.get_CLASSNAME_cUF();
				} catch (myException e) {
					m_sLogMessage += e.getMessage();
					m_sLogMessage += "\n";
					m_sLogMessage += "Klasse " + sClassName + " konnte nicht geladen werden (Refl.Problem).";
					m_sLogMessage += "\n";
					return bRet;
				}			
				
				//Ausführen via Reflection
				cBatchClass = Class.forName(sClassName);
				cBatchInstance = (ICallableTask) cBatchClass.newInstance();
				
				//this.showMethods(cBatchInstance);
				
				
				// Paramter setzen (setTaskParameters(HashMap<String, String> hmParameters)
				Class[] params = new Class[] {HashMap.class};
				Object[] args  = new Object[] { hmParameters };
				
	            sMsg += "setTaskParameters()...";
				methodSetParameter = cBatchClass.getDeclaredMethod("setTaskParameters", params);
				Object retParam =  methodSetParameter.invoke(cBatchInstance, args);
				sMsg += "OK!\n";
				
				
				//
				// die Methode "runTask" ist als Abstrakte Methode in allen batchfähigen Klassen definiert
				//
				sMsg += "runTask()...";
				methodRunTask = cBatchClass.getMethod("runTask");
		        m_bStatusOK = (Boolean)methodRunTask.invoke(cBatchInstance);
		        sMsg += "OK!\n";
				
		        
		        // die Meldungen holen und fürs Log weiterreichen
		        sMsg += "getTaskMessages()...";
				methodGetMessage = cBatchClass.getMethod("getTaskMessages");
		        Vector<String> vMessages = (Vector<String>)methodGetMessage.invoke(cBatchInstance);
		        sMsg += "OK!\n";
				
		        
		        
		        // die Meldungen des Aufrugs an die lokale Logmessage anhängen
		        m_sLogMessage += bibALL.ConcatenateWithoutException(vMessages, ";\n", "");
		        
		      } catch (NoSuchMethodException e) {
		          System.out.println(e);
		          m_sLogMessage += sMsg + "\nNoSuchMethodException"  ;
		      } catch (IllegalAccessException e) {
		          System.out.println(e);
		          m_sLogMessage += sMsg + "\nIllegalAccessException";
		      } catch (InvocationTargetException e) {
		          System.out.println(e);
		          m_sLogMessage += sMsg + "\nInvocationTargetException";
		      } catch (ClassNotFoundException e) {
		    	  System.out.println(e);
		          m_sLogMessage += sMsg + "\nClassNotFoundException";
			  } catch (InstantiationException e) {
				e.printStackTrace();
		          m_sLogMessage += sMsg + "\nInstantiationException";
			}
			  
		return bRet;
	}
	
	
	
	
	
	
	/**
	 * DEBUG: gibt die Methoden eines Objekts per reflection zurück
	 * @param o
	 */
	  void showMethods( Object o ) 
	  { 
	    for ( Method method : o.getClass().getMethods() ) 
	    { 
	      String returnString = method.getReturnType().getName(); 
	      System.out.print( returnString + " " + method.getName() + "(" ); 
	 
	      Class<?>[] parameterTypes = method.getParameterTypes(); 
	 
	      for ( int k = 0; k < parameterTypes.length; k++ ) { 
	        String parameterString = parameterTypes[k].getName(); 
	        System.out.print( " " + parameterString ); 
	 
	        if ( k < parameterTypes.length - 1 ) 
	          System.out.print( ", " ); 
	      } 
	      System.out.print( " )" ); 
	 
	      Class<?>[] exceptions = method.getExceptionTypes(); 
	 
	      if ( exceptions.length > 0 ) { 
	        System.out.print( " throws " ); 
	        for ( int k = 0; k < exceptions.length; k++ ) { 
	          System.out.print( exceptions[k].getName() ); 
	          if ( k < exceptions.length - 1 ) 
	            System.out.print( ", " ); 
	        } 
	      } 
	 
	      System.out.println(); 
	    } 
	  } 

	
	
	/**
	 * gibt die LogMeldungen zurück
	 * @return
	 */
	public String getLogMessage(){
		return m_sLogMessage;
	}

	
	public String getBatchStatusOK(){
		return (m_bStatusOK ? "Y" : "N");
	}
	

}
