package panter.gmbh.Echo2.AgentsAndValidators;

import java.util.UUID;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;



/**
 * @author martin
 *
 */
public abstract class XX_ActionValidator implements Cloneable
{
	//2014-11-07: UUIDs erzeugen
	private UUID     							UUID = null;

	/*
	 * Vector speichert die zur Validierung benutzen IDs um in der 2. Prufung innerhalb der transaktion
	 * die werte nochmal zur verfuegung zu haben 
	 */
	private Vector<String>	vID_UNFORMATED = new Vector<String>();
//	private boolean bBreakAfterFirstError = false;
	
    
    // validierungsmethode
    abstract public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException;
    
    //nur von innen aufrufbar
    abstract protected MyE2_MessageVector isValid(String cID_Unformated) throws myException;
    
    
    /*
     * validierungsmethode 2 (fuer listenaktionen)
     * wird unmittelbar im actionAgent ausgefuehrt.
     */
    public MyE2_MessageVector isValid(Vector<String> vIDs_Unformated, boolean breakAfterFirstError) throws myException
    {
    	this.vID_UNFORMATED.removeAllElements();
    	this.vID_UNFORMATED.addAll(vIDs_Unformated);
//    	this.bBreakAfterFirstError = breakAfterFirstError;
    	
    	MyE2_MessageVector oMV = new MyE2_MessageVector();
    	for (int i=0;i<vIDs_Unformated.size();i++)
    	{
    		String cID = (String)vIDs_Unformated.get(i);
    		oMV.add_MESSAGE(this.isValid(cID));
    		
    		if (breakAfterFirstError && oMV.get_bHasAlarms())
    			break;
    	}
    	
    	return oMV;
    }


	/**
	 * 2014-11-07: UUID wird erzeugt
	 * @return
	 */
	public UUID get_UUID() {
		if (this.UUID == null) {
			this.UUID = java.util.UUID.randomUUID();
		}
		return this.UUID;
	}
	

    
    
}
