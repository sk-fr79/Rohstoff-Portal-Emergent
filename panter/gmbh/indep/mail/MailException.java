package panter.gmbh.indep.mail;


/**
 * Description: MyException class.
 *
 * @version 1.0
 */
public class MailException extends Exception
{
    public String ErrorMessage = "";

    public MailException()
    {
        super();
        this.ErrorMessage = "Error: Unknown error!";
    }

    public MailException(String errorMessage)
    {
        super();
        this.ErrorMessage = "Error: " + errorMessage;
    }

    public String getErrorMessage()
    {
        return this.ErrorMessage;
    }
}
