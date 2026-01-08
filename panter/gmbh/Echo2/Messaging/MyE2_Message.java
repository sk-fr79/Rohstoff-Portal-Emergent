package panter.gmbh.Echo2.Messaging;

import java.util.Vector;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;


public class MyE2_Message   implements Comparable<MyE2_Message>
{

	// 3 verschiedene MessageTypen
	public static int TYP_INFO = 1001;

	public static int TYP_ALARM = 1002;

	public static int TYP_WARNING = 1003;

//	// datenbank-meldungen koennen mit einem weiteren parameter versehen werden
//	public static String NO_DB_ERROR = "NO_DB_ERROR";
//
//	public static String DB_ERROR_TYPE_UNKNOWN = "DB_ERROR_TYPE_UNKNOWN";
//
//	public static String DB_ERROR_TYPE_SQLEXCEUTE_ERROR = "DB_ERROR_TYPE_SQLEXCEUTE_ERROR";
//
//	public static String DB_ERROR_TYPE_INTEGRITY_CHECKER_ERROR = "DB_ERROR_TYPE_INTEGRITY_CHECKER_ERROR";
//
//	public static String DB_ERROR_TYPE_PRE_COMMIT_VALIDATOR = "DB_ERROR_TYPE_PRE_COMMIT_VALIDATOR";
//
//	public static String DB_ERROR_TYPE_INTEGRITY_VIOLATION_ERROR = "DB_ERROR_TYPE_INTEGRITY_VIOLATION_ERROR";

	private int iType = MyE2_Message.TYP_ALARM;

	private MyString cMessage = null;

	private boolean bMustBeExpandedAtStart = false;

	
//	//zusatzkomponenten fuer die meldung (z.B. buttons usw)
//	private Vector<MyE2IF__Component> vZusatzKomponents = new Vector<MyE2IF__Component>();

	/**
	 * @param cmessage (MyString)
	 * @param itype
	 * @param MustBeShownInPopup
	 */
	public MyE2_Message(MyString cmessage, int itype, boolean MustBeShownInPopup)
	{
		this.cMessage = cmessage;
		this.iType = itype;
		this.bMustBeExpandedAtStart = MustBeShownInPopup;
	}


	
	
	
	public boolean equals(Object oToCompare)
	{
		if (oToCompare instanceof MyE2_Message)
		{
			return this.cMessage.COrig().equals(((MyE2_Message)oToCompare).get_cMessage().COrig());
		}
		else
		{
			return super.equals(oToCompare);
		}
	}

	
	
	
	public MyString get_cMessage()
	{
		return cMessage;
	}

	
	public void set_Message(MyString Message) {
		this.cMessage=Message;
	}
	
	
	public int get_iType()
	{
		return iType;
	}

	public boolean get_bMustBeExpandedAtStart()
	{
		return bMustBeExpandedAtStart;
	}

	
	public void set_bMustBeExpandedAtStart(boolean mustBeExpandedAtStart) {
		this.bMustBeExpandedAtStart = mustBeExpandedAtStart;
	}
	

	public MessageLabel get_MessageLabel()
	{
		return new MessageLabel(this);
	}

	
	
//	public Vector<MyE2IF__Component> get_vZusatzKomponents()
//	{
//		return vZusatzKomponents;
//	}

	@Override
	public int compareTo(MyE2_Message o)
	{
		int iRueck = -1;
		if ((this.cMessage.COrig().compareTo(o.get_cMessage().COrig())==0) && (this.iType==o.get_iType()))
		{
			iRueck = 0;
		}
		return iRueck;
	}

//	@Override
//	public int compareTo(MyE2_Message o)
//	{
//		return this.cMessage.COrig().compareTo(o.get_cMessage().COrig());
//	}

	public void set_type_INFO() {
		this.iType=MyE2_Message.TYP_INFO;
	}
	
	public void set_type_WARNING() {
		this.iType=MyE2_Message.TYP_WARNING;
	}
	
	public void set_type_ALARM() {
		this.iType=MyE2_Message.TYP_ALARM;
	}
	

	
	/**
	 * methode, um errormessages "lesbarer" zu machen, s.b. bei DB-fehlern
	 * @param v_keys
	 * @param newMessage
	 */
	public void replaceMessageWhenAllSubstringsAreInMessageText(Vector<String> v_keys, MyString newMessage) {
		String c_orig = S.NN(this.cMessage.COrig());
		
		boolean must_replace = true;
		for (String c: v_keys) {
			if (!c_orig.toUpperCase().contains(c.toUpperCase())) {
				must_replace=false;
				break;
			}
		}
		
		if (must_replace) {
			this.set_Message(newMessage);
		}
	}
	
	
	
	
}
