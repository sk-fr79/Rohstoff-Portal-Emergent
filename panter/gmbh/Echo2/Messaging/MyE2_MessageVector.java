package panter.gmbh.Echo2.Messaging;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;



// user-message-objekt, wird vor jeder event-schleife gelöscht, dann werden die
// anfallenden meldungen gesammelt, getrennt nach meldungstyp und
// am ende jeder event-schleife komplett angezeigt. die rückgabe ist ein grid
public class MyE2_MessageVector extends Vector<MyE2_Message>
{
    
    
    public MyE2_MessageVector()
    {
    	super();
    }
   

    public MyE2_MessageVector(MyE2_Message oMessage)
    {
    	super();
    	this.add(oMessage);
    }

    
    /**
     * @param oMessage
     * @param bInFront
     */
    public void add_MESSAGE(MyE2_Message oMessage, boolean bInFront)
    {
    	if (this.is_double_message(oMessage))
    		return;
    	
    	
        if (oMessage!=null && !this.contains(oMessage))
        {
            if (bInFront)
            	super.add(0,oMessage);
            else
            	super.add(oMessage);
        }
    }


    /**
     * @param oMessage
     */
    public void add_MESSAGE(MyE2_Message oMessage)
    {
    	if (this.is_double_message(oMessage))
    		return;

    	if (oMessage!=null && !this.contains(oMessage))
        {
           	super.add(oMessage);
        }
    }


    
    
    /**
     * @param cMessage (MyString)
     * @param iTyp
     * @param bInFront
     */
    public void add_MESSAGE(MyString cMessage, int iTyp, boolean bInFront)
    {
    	MyE2_Message oMSG = new MyE2_Message(cMessage,iTyp,false);
    	
    	if (this.is_double_message(oMSG))
    		return;

    	
        if (cMessage!=null && !bibALL.isEmpty(cMessage.CTrans()) && !this.contains(oMSG))
        {
        	if (bInFront)
        		super.add(0,oMSG);
        	else
        		super.add(oMSG);
        }
    }


    
    /**
     * @param oMessage
     * @param bInFront
     */
    public void add_MESSAGE_AllowDoubles(MyE2_Message oMessage, boolean bInFront)
    {
    	
        if (oMessage!=null)
        {
            if (bInFront)
            	super.add(0,oMessage);
            else
            	super.add(oMessage);
        }
    }


    /**
     * @param oMessage
     */
    public void add_MESSAGE_AllowDoubles(MyE2_Message oMessage)
    {

    	if (oMessage!=null)
        {
           	super.add(oMessage);
        }
    }


    
    
    /**
     * @param cMessage (MyString)
     * @param iTyp
     * @param bInFront
     */
    public void add_MESSAGE_AllowDoubles(MyString cMessage, int iTyp, boolean bInFront)
    {
    	MyE2_Message oMSG = new MyE2_Message(cMessage,iTyp,false);
    	
    	
        if (cMessage!=null && !bibALL.isEmpty(cMessage.CTrans()))
        {
        	if (bInFront)
        		super.add(0,oMSG);
        	else
        		super.add(oMSG);
        }
    }

    

    /**
     * prueft, ob die meldung mit diesem MessageTyp schon vorhanden ist
     * @param oNewMessage
     */
    private boolean is_double_message(MyE2_Message oNewMessage)
    {
     	for (int i=0;i<this.size();i++)
    	{
    		if (this.get(i).equals(oNewMessage))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    

 
    
	public int get_iCountAlarm()
	{
		int iCount=0;
		
		for (int i=0;i<this.size();i++)
		{
			MyE2_Message oMess = (MyE2_Message)this.get(i);
			if (oMess.get_iType()==MyE2_Message.TYP_ALARM)
			{
				iCount++;
			}
		}
		return iCount;
	}

	public int get_iCountWarning()
	{
		int iCount=0;
		
		for (int i=0;i<this.size();i++)
		{
			MyE2_Message oMess = (MyE2_Message)this.get(i);
			if (oMess.get_iType()==MyE2_Message.TYP_WARNING)
			{
				iCount++;
			}
		}
		return iCount;
	}

	public int get_iCountMessage()
	{
		int iCount=0;
		
		for (int i=0;i<this.size();i++)
		{
			MyE2_Message oMess = (MyE2_Message)this.get(i);
			if (oMess.get_iType()==MyE2_Message.TYP_INFO)
			{
				iCount++;
			}
		}
		return iCount;
	}

	
	
	public boolean get_bMustBeExpandedAtStart()
	{
		boolean bRueck = false;
		for (int i=0;i<this.size();i++)
		{
			MyE2_Message oMess = (MyE2_Message)this.get(i);
			if (oMess.get_bMustBeExpandedAtStart())
			{
				bRueck = true;
			}
		}
		
		return bRueck;
		
	}
	

	public boolean get_bHasAlarms()
	{
		return (this.get_iCountAlarm()>0);
	}

	
	public boolean hasAlarms() {
		return this.get_bHasAlarms();
	}
	
	
	public boolean get_bHasWarnings()
	{
		return (this.get_iCountWarning()>0);
	}

	
	
	public boolean get_bIsOK()
	{
		return (this.get_iCountAlarm()==0);
	}

	public boolean isOK() {
		return this.get_bIsOK();
	}

	
	public boolean add_MESSAGE(MyE2_MessageVector oMV)
	{
		if (oMV==null)
			return false;
			
		
		//falls addAll an mehreren stellen ausgefuert wird, dann dafuer sorgen, dass jede meldung nur einmal eingetragen wird
		for (int i=0;i<oMV.size();i++)
		{
			if (this.is_double_message(oMV.get(i)))
			{
				continue;
			}
			
			if (!this.contains(oMV.get(i)))
			{
				this.add_MESSAGE((MyE2_Message)oMV.get(i),false);
			}
		}
		
		return true;
	}



	
	public boolean add_MESSAGE_AllowDoubles(MyE2_MessageVector oMV)
	{
		if (oMV==null)
			return false;
		
		//falls addAll an mehreren stellen ausgefuert wird, dann dafuer sorgen, dass jede meldung nur einmal eingetragen wird
		for (int i=0;i<oMV.size();i++)
		{
			this.add_MESSAGE_AllowDoubles((MyE2_Message)oMV.get(i),false);
		}
		return true;
	}


	
	
	public void RemoveAllMessagesFromSpecialTyp(int iRemovedType)
	{
		for (int i=this.size()-1;i>=0;i--)
		{
			if (((MyE2_Message)this.get(i)).get_iType()==iRemovedType)
			{
				this.remove(i);
			}
		}
	}
	
	
	
	//2012-02-07: neue methode
	public MyE2_MessageVector get_InfoMessages()
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
		for (int i=this.size()-1;i>=0;i--)
		{
			if ((this.get(i)).get_iType()==MyE2_Message.TYP_INFO)
			{
				oMVRueck.add(this.get(i));
			}
		}
		return oMVRueck;
	}
	

	
	//2012-02-07: neue methode
	public MyE2_MessageVector get_WarnMessages()
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
		for (int i=this.size()-1;i>=0;i--)
		{
			if ((this.get(i)).get_iType()==MyE2_Message.TYP_WARNING)
			{
				oMVRueck.add(this.get(i));
			}
		}
		return oMVRueck;
	}
	
	//2012-02-07: neue methode
	public MyE2_MessageVector get_AlarmMessages()
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
		for (int i=this.size()-1;i>=0;i--)
		{
			if ((this.get(i)).get_iType()==MyE2_Message.TYP_ALARM)
			{
				oMVRueck.add(this.get(i));
			}
		}
		return oMVRueck;
	}
	

	
	
	
	
	/**
	 * @param vSQL_Stmt Vector can contain Strings or Statements-Objects
	 */
	public void add_ALARMMESSAGE_Vector_Untranslated(Vector<String> vSQL_Stmt) 
	{
		for (int i=0;i<vSQL_Stmt.size();i++)
		{
			super.add( new MyE2_Message(new MyE2_String(vSQL_Stmt.get(i),false),MyE2_Message.TYP_ALARM,false));
		}
	}

	
	
	
	public String get_MessagesAsText(MyString VorsatzError, MyString VorsatzWarning, MyString VorsatzInfo)
	{
		StringBuffer  cRueck = new StringBuffer();
		
		for (int i=0;i<this.size();i++)
		{
			if (this.get(i).get_iType()==MyE2_Message.TYP_ALARM)
			{
				cRueck.append(VorsatzError.CTrans()+" "+this.get(i).get_cMessage().CTrans()+"\n");
			}
			if (this.get(i).get_iType()==MyE2_Message.TYP_WARNING)
			{
				cRueck.append(VorsatzWarning.CTrans()+" "+this.get(i).get_cMessage().CTrans()+"\n");
			}
			if (this.get(i).get_iType()==MyE2_Message.TYP_INFO)
			{
				cRueck.append(VorsatzInfo.CTrans()+" "+this.get(i).get_cMessage().CTrans()+"\n");
			}
		}
		
		return cRueck.toString();
	}
	

	public String get_MessagesAsText()
	{
		return this.get_MessagesAsText(new MyE2_String("FEHLER !! -->"), new MyE2_String("WARNUNG !! -->"), new MyE2_String(""));
	}

	
	

	
	
	public Vector<MyString> get_MessagesAsMyStringVector()
	{
		Vector<MyString>  vRueck = new Vector<MyString>();
		
		for (int i=0;i<this.size();i++)
		{
			vRueck.add(this.get(i).get_cMessage());
		}
		
		return vRueck;
	}

	
	
	public MyE2_MessageVector _add(MyE2_Message message) {
		this.add_MESSAGE(message);
		return this;
	}
	
	public MyE2_MessageVector _add(MyE2_MessageVector message) {
		this.add_MESSAGE(message);
		return this;
	}
	

	/**
	 * add without changeing order
	 * @param message
	 * @return
	 */
	public MyE2_MessageVector _addRaw(MyE2_Message message) {
		super.add(message);
		return this;
	}
	
	/**
	 * add without changeing order
	 * @param message
	 * @return
	 */
	public MyE2_MessageVector _addRaw(MyE2_MessageVector message) {
		super.addAll(message);
		return this;
	}

	
	/**
	 * adds alarmmessage with string (is translated)
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addAlarm(String s) {
		this.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(s)));
		return this;
	}
	
	
	/**
	 * adds alarmmessage with string (is translated)
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addAlarm(MyString s) {
		this.add_MESSAGE(new MyE2_Alarm_Message(s));
		return this;
	}
/**
	 * adds warning with translated string
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addWarn(String s) {
		this.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String(s)));
		return this;
	}
	
	/**
	 * adds info with MyE2_String
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addWarn(MyString s) {
		this.add_MESSAGE(new MyE2_Warning_Message(s));
		return this;
	}

	
	/**
	 * adds info with translated string
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addInfo(String s) {
		this.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(s)));
		return this;
	}
	
	/**
	 * adds info with MyE2_String
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addInfo(MyString s) {
		this.add_MESSAGE(new MyE2_Info_Message(s));
		return this;
	}

	
	/**
	 * adds alarmmessage with UN-translated string
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addAlarmUT(String s) {
		this.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(s,false)));
		return this;
	}
	
	
	/**
	 * adds warning with UN-translated string
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addWarnUT(String s) {
		this.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String(s,false)));
		return this;
	}
	
	
	/**
	 * adds info with UN-translated string
	 * @param s
	 * @return
	 */
	public MyE2_MessageVector _addInfoUT(String s) {
		this.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(s,false)));
		return this;
	}
	
	/**
	 * methode, um errormessages "lesbarer" zu machen, s.b. bei DB-fehlern
	 * @param v_keys
	 * @param newMessage
	 */
	public void replaceMessageWhenAllSubstringsAreInMessageText(Vector<String> v_keys, MyString newMessage) {
		for (MyE2_Message m: this) {
			String c_orig = S.NN(m.get_cMessage().COrig());
			
			boolean must_replace = true;
			for (String c: v_keys) {
				if (!c_orig.toUpperCase().contains(c.toUpperCase())) {
					must_replace=false;
					break;
				}
			}
			
			if (must_replace) {
				m.set_Message(newMessage);
			}
		}
	}
	
	
	/**
	 * methode, um errormessages "lesbarer" zu machen, s.b. bei DB-fehlern
	 * @param v_keys
	 * @param newMessage
	 */
	public void replaceMessageWhenAllSubstringsAreInMessageText(VEK<String> v_keys, MyString newMessage) {
		for (MyE2_Message m: this) {
			String c_orig = S.NN(m.get_cMessage().COrig());
			
			boolean must_replace = true;
			for (String c: v_keys) {
				if (!c_orig.toUpperCase().contains(c.toUpperCase())) {
					must_replace=false;
					break;
				}
			}
			
			if (must_replace) {
				m.set_Message(newMessage);
			}
		}
	}
	

	

	/**
	 * 
	 * @author martin
	 * @date 08.11.2018
	 *
	 * @param message
	 * @param bInFront
	 * @return
	 */
    public MyE2_MessageVector _addWhenNN(MyE2_Message message, boolean bInFront)  {
    	if (message==null) {
    		return this;
    	}
    	this.add_MESSAGE(message);
    	return this;
    }

    /**
     * 
     * @author martin
     * @date 08.11.2018
     *
     * @param message
     * @return
     */
    public MyE2_MessageVector _addWhenNN(MyE2_Message message)  {
    	return this._addWhenNN(message,false);
    }

    
    
    public MyE2_MessageVector _clear() {
    	this.clear();
    	return this;
    }


    public MyE2_MessageVector _changeErrorsToWarnings() {
    	for (MyE2_Message m: this) {
    		if (m.get_iType()==MyE2_Message.TYP_ALARM) {
    			m.set_type_WARNING();
    		}
    	}
    	return this;
    }
    
	
    /**
     * faltet den stacktrace eines fehlers aus
     * @author martin
     * @date 11.03.2020
     *
     * @param ex
     * @return
     */
    public MyE2_MessageVector _add(Exception ex) {
    	
    	if (ex instanceof myException) {
    		this.add( ((myException)ex).get_ErrorMessage());
    	} else {
    		StringBuffer errors = new StringBuffer();
    		for (StackTraceElement ste : ex.getStackTrace()) {
    			errors.append(ste.toString()+"\n");
    		}
    		String cname = S.NN(ex.getClass().getSimpleName(),"Unknown: Systemerror");
    		this._addAlarm(cname+": "+S.NN(ex.getLocalizedMessage()));
    		this._addAlarm(errors.toString());
    		
    	}
    	ex.printStackTrace();
    	return this;
    }
	
    
    
    public static class MessageException extends Exception {
    	private MyE2_MessageVector mv = null;
		public MessageException(MyE2_MessageVector mv) {
			super("Fehlermeldungen:\n"+mv.get_MessagesAsText());
			this.mv=mv;
		}

		public MyE2_MessageVector getMv() {
			return mv;
		}
		
    }
    
    
    public MyE2_MessageVector _add2(Exception ex) {
    	MyE2_Alarm_Message msg = null;
    	if (ex!=null) {
        	if (ex instanceof myException) {
        		myException me = (myException)ex;
        	
        		if (S.isFull(me.getOriginalMessage())) {
        			msg = new MyE2_Alarm_Message(me.getOriginalMessage());
        		} else {
        			msg = new MyE2_Alarm_Message("Unknown: Systemerror");
        		}
        		this.add(msg);
        	} else {
        		StringBuffer errors = new StringBuffer();
        		for (StackTraceElement ste : ex.getStackTrace()) {
        			errors.append(ste.toString()+"\n");
        		}
        		String cname = S.NN(ex.getClass().getSimpleName(),"Unknown: Systemerror");
        		this._addAlarm(cname+": "+S.NN(ex.getLocalizedMessage()));
        		this._addAlarm(errors.toString());
        		
        	}   		
    	}
    	return this;
    }

    
}
