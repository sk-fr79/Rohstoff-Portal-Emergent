package panter.gmbh.indep.mail;


/**
 * Description: Definition of constants.
 *
 * @version 1.0
 */
public class MailConst
{
    public static String SMTP_SERVER_ERROR_NO_SERVER_NAME = "Wrong smtp server name: null or empty (white space) string not allowed";
    public static String SMTP_USER_ERROR = "No or empty (white space) smtp user name";
    public static String SMTP_PASSWORD_ERROR = "No or empty (white space) smtp user password";
    public static String ABSENDER_ERROR_NO_ABSENDER = "Wrong 'from': null or empty (white space) string not allowed";
    public static String ABSENDER_ERROR_WRONG_ABSENDER_FORMAT = "Wrong 'from' address format (user@domain format required)";
    public static String EMPFAENGER_ERROR_NO_EMPFAENGER = "Wrong 'to': null or empty (white space) string not allowed";
    public static String EMPFAENGER_ERROR_WRONG_EMPFAENGER = "Wrong 'to' address";
    public static String CC_EMPFAENGER_ERROR_NO_CC_EMPFAENGER = "Wrong 'cc address': null or empty (white space) string not allowed";
    public static String CC_EMPFAENGER_ERROR_WRONG_CC_EMPFAENGER_DATA_TYPE = "Wrong 'cc address': STRING type required";
    public static String CC_EMPFAENGER_ERROR_WRONG_CC_EMPFAENGER = "Wrong 'cc address'";
    public static String BETREFF_ERROR_NO_BETREFF = "Subject required";
    public static String ANLAGE_ERROR_NO_ANLAGE = "Wrong attachment: null or empty (white space) string not allowed as attached filename";
    public static String ANLAGE_ERROR_NO_ANLAGE2 = "Wrong attachment: null or empty file not allowed as attached file";
    public static String ANLAGE_ERROR_WRONG_FILENAME = "Wrong attachment: the file with specified filename doesn't exist";
    public static String ANLAGE_ERROR_WRONG_FILENAME2 = "Wrong attachment: the specified file doesn't exist";
    public static String ANLAGE_ERROR_WRONG_ANLAGE_DATA_TYPE = "Wrong attachment: STRING type required for filename";
    public static String ANLAGE_ERROR_WRONG_ANLAGE_DATA_TYPE2 = "Wrong attachment: FILE type required for file";
    public static String INHALT_ATTACHMENT_ERROR = "No mail text and no attachment";
    public static String PLATZ_ERROR_NO_PLATZ = "No attachment directory: null or empty (white space) string not allowed as directory";
    public static String PLATZ_ERROR_WRONG_PLATZ = "Wrong attachment directory: the specified directory doesn't exist";
    public static String MESSAGING_EXCEPTION = "Messaging exception";
    public static String NO_SUCH_PROVIDER_EXCEPTION = "No such provider exception";

    public static String BCC_EMPFAENGER_ERROR_NO_BCC_EMPFAENGER = "Wrong 'bcc address': null or empty (white space) string not allowed";
    public static String BCC_EMPFAENGER_ERROR_WRONG_BCC_EMPFAENGER_DATA_TYPE = "Wrong 'bcc address': STRING type required";
    public static String BCC_EMPFAENGER_ERROR_WRONG_BCC_EMPFAENGER = "Wrong 'bcc address'";

    public MailConst()
    {
    }
}
