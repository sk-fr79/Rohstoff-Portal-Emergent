
package panter.gmbh.indep.mail;

import java.util.Vector;




/**
 * @author martin
 * hilfsklasse um das versenden von mail-listen und den
 * erfolg zu sammeln und spaeter auszugeben
 */
public class myMailSendStatus
{
    public static int STATUS_ERROR = 333434343;
    public static int STATUS_OK 	= 333434344;
    
    private 	String 		cMailAdress		= "";
    private 	String 		cMessage		= "";
    private 	int			iStatus			= myMailSendStatus.STATUS_ERROR;
    
    /*
     * ein paar vectoren, wo ein fortgang der ereignisse mit jeweiligem status
     * abgelegt werden kann,
     * z.B. BELEGNUMMER -Update - ok
     * 		mail senden 	-	ok
     * 		archiv schreiben - fehler
     */
    private 	Vector<String>		vFortgangMeldung = 	new Vector<String>();
    private 	Vector<Boolean>		vFortgangOK = 		new Vector<Boolean>();
    
    
    /**
     * @param cid
     * @param mailAdress
     * @param message
     */
    public myMailSendStatus()
    {
        super();
    }

   
    

    public String get_cMailAdress()
    {
        return cMailAdress;
    }
    public String get_cMessage()
    {
        return cMessage;
    }
    public int get_iStatus()
    {
        return iStatus;
    }
    
    public Vector<String> get_vFortgangMeldung()
    {
        return vFortgangMeldung;
    }
    public Vector<Boolean> get_vFortgangOK()
    {
        return vFortgangOK;
    }
    
    public void write_fortgang(String cMeldung, boolean bOK)
    {
        this.vFortgangMeldung.add(cMeldung);
        this.vFortgangOK.add(new Boolean(bOK));
    }
    
    
    public void set_cMailAdress(String mailAdress)
    {
        cMailAdress = mailAdress;
    }
    public void set_cMessage(String message)
    {
        cMessage = message;
    }
    public void set_iStatus(int status)
    {
        iStatus = status;
    }
    

    public boolean bHasErrorEntry()
    {
        for (int i=0;i<this.vFortgangOK.size();i++)
        {
            if (!((Boolean)this.vFortgangOK.get(i)).booleanValue())
                return true;
        }
        return false;
    }
    
    
    
    
    
}
