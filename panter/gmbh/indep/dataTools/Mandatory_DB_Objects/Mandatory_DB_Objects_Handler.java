package panter.gmbh.indep.dataTools.Mandatory_DB_Objects;

import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;


public class Mandatory_DB_Objects_Handler {

	/**
	 * Vektor hält alle Mandatory Objekte, die beim Start nach dem Registrieren geprüft und ggf. 
	 * (neu) erzeugt werden müssen
	 */
	private Vector<Mandatory_DB_Object_Base> m_List_Of_Mandatory_Objects = null;

	public Mandatory_DB_Objects_Handler() {
		super();
		
		m_List_Of_Mandatory_Objects = new Vector<Mandatory_DB_Object_Base>();
	}
	/** 
	 * Registriert ein Objekt für die Prüfung auf Existenz und ggf. Generierung
	 * @author manfred
	 * @date   21.01.2013
	 * @param ObjectToRegister
	 */
	public void Register_Mandatory_DB_Object(Mandatory_DB_Object_Base ObjectToRegister){
		if (ObjectToRegister != null){
			m_List_Of_Mandatory_Objects.add(ObjectToRegister);
		}
	}

	
	
	/**
	 * Hauptroutine: Prüft alle angemeldeten Objekte 
	 * 1. Aufruf von ObjectExists
	 * 2. Fall negativ, CreateObject
	 * @author manfred
	 * @date   21.01.2013
	 * @return
	 */
	public boolean Check_Mandatory_DB_Objects(){
		boolean bRet = true;
		boolean bRetAll = true;
		
		for (Mandatory_DB_Object_Base o: m_List_Of_Mandatory_Objects){
			if ( o.getObjectName() != null){
				
				DEBUG.System_print("Datenbank-Objekt " + o.getObjectName() + " wird geprüft...", DEBUG.DEBUG_FLAG_DIVERS1);
				
				if (!o.ObjectExists()){
					DEBUG.System_print("Objekt nicht vorhanden! Wird generiert.... " , DEBUG.DEBUG_FLAG_DIVERS1);
						
					// falls das Objekt nicht existiert, wird verucht es zu erstellen
					bRet = o.CreateObject();
					bRetAll &= bRet;
					
					if (bRet){
						DEBUG.System_println("OK!", DEBUG.DEBUG_FLAG_DIVERS1);
					} else {
						DEBUG.System_println("FEHLGESCHLAGEN !!!!", DEBUG.DEBUG_FLAG_DIVERS1);
					}
				} else {
					DEBUG.System_println("OK", DEBUG.DEBUG_FLAG_DIVERS1);
				}
			}
		}
		
		return bRetAll;
	}
	
	
	/**
	 * Hauptroutine: Prüft alle angemeldeten Objekte 
	 * 1. Aufruf von ObjectExists
	 * 2. Fall negativ, CreateObject
	 * @author manfred
	 * @date   21.01.2013
	 * @return
	 */
	public boolean Is_Valid_Mandatory_DB_Objects() throws myException {
		
		boolean bRueck = true;
		
		for (Mandatory_DB_Object_Base o: m_List_Of_Mandatory_Objects){
			if ( o.getObjectName() != null){
				boolean bRet = o.ObjectIsValid();    //falls so definiert, dann hier exception bei fehler
				bRueck &= bRet;
			}
		}
		
		return bRueck;
	}
	

}
