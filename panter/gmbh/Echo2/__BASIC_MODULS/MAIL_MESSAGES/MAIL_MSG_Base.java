package panter.gmbh.Echo2.__BASIC_MODULS.MAIL_MESSAGES;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_NACHRICHT_MAIL_LOG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;

/**
 * Basisklasse für die Mail-Messages innerhalb des Systems
 * @author manfred
 * @date   31.01.2012
 */
public class MAIL_MSG_Base {

private String m_Sender = null;
private String m_Address = null;
private String m_Heading = null;
private String m_Text = null;
//private Vector<String> m_vCC = null;
private HashSet<String> m_hsCC = null;

private String m_MailKenner = null;
private String m_IDTable = null;
private String m_SendKey = null;	



public MAIL_MSG_Base() {
	// cc-Vector aufbauen
	m_hsCC = new HashSet<String>();
}
	

/**
 * Verschicken der Mail
 * @author manfred
 * @date   31.01.2012
 * @return
 */
public boolean sendMail(){
	boolean bOk=false ;
	
	// Mailobjekt 
	MyMailHelper oMail = new MyMailHelper(
			this.get_Sender(),
			this.get_Address(),
			this.get_Heading(),
			this.get_Text(),
			this.get_vCC());
	
	try 
	{
		bOk = oMail.doSendWith_REAL_Adress();
	} 
	catch (myException e) 
	{
		// TODO Auto-generated catch block
		bOk = false;
		e.printStackTrace();
	}				
	return bOk;
}



/**
 * schreiben eines Logeintrags für die Mail
 * @author manfred
 * @date   31.01.2012
 */
public void logMail(){
	try {
		RECORDNEW_NACHRICHT_MAIL_LOG log = new RECORDNEW_NACHRICHT_MAIL_LOG();
		
		log.set_NEW_VALUE_VERSAND_AM(new GregorianCalendar());
		log.set_NEW_VALUE_BETREFF(this.get_Heading());
		
		String sAdresszeile = "Mail von " + this.get_Sender() + " an " + this.m_Address + "; " +  System.getProperty("line.separator");
		if (m_hsCC.size() > 0){
			for (String cc: m_hsCC){
				sAdresszeile += "CC: " + cc + "; " +  System.getProperty("line.separator");
			}
		}
		
		log.set_NEW_VALUE_TEXT(sAdresszeile  +  this.get_Text()  );
		log.set_NEW_VALUE_MAIL_KENNER(this.get_MailKenner());
		log.set_NEW_VALUE_ID_TABLE(this.get_IDTable());
		log.set_NEW_VALUE_IDENTIFIER(this.get_SendKey());
		
		log.do_WRITE_NEW_NACHRICHT_MAIL_LOG(bibMSG.MV());
		
		
	} catch (myException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	// TODO: Logging implementieren
}








/**
 * @param m_Sender the m_Sender to set
 */
public void set_Sender(String m_Sender) {
	this.m_Sender = m_Sender;
}


/**
 * @return the m_Sender
 */
public String get_Sender() {
	return m_Sender;
}


/**
 * @param m_Address the m_Address to set
 */
public void set_Address(String m_Address) {
	this.m_Address = m_Address;
}


/**
 * @return the m_Address
 */
public String get_Address() {
	return m_Address;
}


/**
 * @return the _Heading
 */
public String get_Heading() {
	return m_Heading;
}


/**
 * @param _Heading the _Heading to set
 */
public void set_Heading(String _Heading) {
	this.m_Heading = _Heading;
}


/**
 * @return the _Text
 */
public String get_Text() {
	return m_Text;
}


/**
 * @param _Text the _Text to set
 */
public void set_Text(String _Text) {
	this.m_Text = _Text;
}


/**
 * @return the _CC
 */
public Vector<String> get_vCC() {
	Vector<String> vRet = new Vector(m_hsCC);
	return vRet;
}


/**
 * @param _CC the _CC to set
 */
public void add_CC(String _CC) {
	if (!bibALL.isEmpty(_CC)){
		this.m_hsCC.add(_CC);
	}
}



/**
 * @param _MailKenner the _MailKenner to set
 */
public void set_MailKenner(String _MailKenner) {
	this.m_MailKenner = _MailKenner;
}



/**
 * @return the _MailKenner
 */
public String get_MailKenner() {
	return m_MailKenner;
}



/**
 * @param _IDTable the _IDTable to set
 */
public void set_IDTable(String _IDTable) {
	this.m_IDTable = _IDTable;
}



/**
 * @return the _IDTable
 */
public String get_IDTable() {
	return m_IDTable;
}



/**
 * @param m_SendKey the m_SendKey to set
 */
public void set_SendKey(String m_SendKey) {
	this.m_SendKey = m_SendKey;
}



/**
 * @return the m_SendKey
 */
public String get_SendKey() {
	return m_SendKey;
}





}
