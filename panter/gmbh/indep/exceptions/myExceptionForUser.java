/*
 * Created on 26.04.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep.exceptions;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.indep.MyString;


/**
 * @author martin
 * exception, deren meldung zu den usern kommt (klartext und hinweise)
 */
public class myExceptionForUser extends myException
{

	private MyString cErrorMessage = null;
	
    public myExceptionForUser(String ErrorMessage)
    {
        super(ErrorMessage);
        this.cErrorMessage = new MyE2_String(ErrorMessage) ;
    }

    public myExceptionForUser(MyString ErrorMessage)
    {
        super(ErrorMessage.CTrans());
        this.cErrorMessage = ErrorMessage ;
    }

    public MyE2_Message get_ErrorMessage()
    {
        return new MyE2_Message(this.cErrorMessage,MyE2_Message.TYP_ALARM,false);
    }

}
