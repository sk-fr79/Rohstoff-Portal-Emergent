package panter.gmbh.Echo2.components.DB.QUALIFIER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Q_DEF_Element
{

	private String   			cDB_DATENBANKTAG = 	null;   	// text fuer die datenbank


	private MyE2_String 		cLONG_TEXT_4_USER = 			null;       // text, der in der checkbox-liste steht
	private MyE2_String 		cSHORT_TEXT_4_USER = 		null;     	// textfragment, der in der liste als aufzaehlungselement steht
	private MyE2_String 		cToolTipText =   	null;
	
	private String  			cSORTSTRING = null;             // wird normalerweise mit dem cCB_TEXT.CTrans() gefuellt
	
	private MyE2IF__Component   oMotherComponent = null;

	/**
	 * 
	 * @param DB_DATENBANKTAG  = 	text fuer die datenbank
 	 * @param cb_TEXT = 		text fuer die liste im popup-fenster
	 * @param cSHORT_TEXT_4_USER = 		text fuer die uebersicht der zugeordnet element auf dem button (wenn null, dann wird der cb_TEXT genommen)
	 * @param ToolTips = 		text fuer die uebersicht der zugeordnet element auf dem button
	 */
	public Q_DEF_Element(String DB_DATENBANKTAG, MyE2_String cb_TEXT, MyE2_String shortText, String cSortString, MyE2IF__Component  MotherComponent, MyE2_String ToolTips) throws myException
	{
		super();
		this.cDB_DATENBANKTAG = 	DB_DATENBANKTAG;
		this.cLONG_TEXT_4_USER = 		cb_TEXT;
		this.cSHORT_TEXT_4_USER = 		shortText;
		if (this.cSHORT_TEXT_4_USER == null)
		{
			this.cSHORT_TEXT_4_USER = this.cLONG_TEXT_4_USER;
		}
		
		this.cSORTSTRING = cSortString;
		
		if (S.isEmpty(this.cSORTSTRING))
		{
			this.cSORTSTRING = this.cLONG_TEXT_4_USER.CTrans();
		}
		
		this.cToolTipText = 	ToolTips;
		this.oMotherComponent = MotherComponent;
		
		
	}
	

	
	/**
	 * 
	 * @param DB_DATENBANKTAG  = 	ext fuer die datenbank
 	 * @param cb_TEXT = 		text fuer die liste im popup-fenster
	 * @param cSHORT_TEXT_4_USER = 		text fuer die uebersicht der zugeordnet element auf dem button
	 * @param ToolTips = 		text fuer die uebersicht der zugeordnet element auf dem button
	 */
	public Q_DEF_Element(String DB_DATENBANKTAG, String cb_TEXT, String shortText, String cSortString, MyE2IF__Component  MotherComponent, String ToolTips) throws myException
	{
		super();
		this.cDB_DATENBANKTAG = 	DB_DATENBANKTAG;
		this.cLONG_TEXT_4_USER = 			new MyE2_String(cb_TEXT);
		this.cSHORT_TEXT_4_USER = 			new MyE2_String(shortText);
		this.cToolTipText = 		new MyE2_String(ToolTips);
		this.oMotherComponent = 	MotherComponent;
		
		this.cSORTSTRING = cSortString;
		
		if (S.isEmpty(this.cSORTSTRING))
		{
			this.cSORTSTRING = this.cLONG_TEXT_4_USER.CTrans();
		}

	}

	

	
	
	public String get_cDB_DATENBANKTAG()
	{
		return cDB_DATENBANKTAG;
	}

	
	public MyE2_String get_cLONG_TEXT_4_USER()
	{
		return cLONG_TEXT_4_USER;
	}


	public MyE2_String get_cSHORT_TEXT_4_USER()
	{
		return cSHORT_TEXT_4_USER;
	}




	public MyE2_String get_cToolTipText()
	{
		return cToolTipText;
	}


	public MyE2IF__Component get_oMotherComponent()
	{
		return oMotherComponent;
	}


	public String get_cSORTSTRING()
	{
		return cSORTSTRING;
	}


	public void set_cSORTSTRING(String cSORTSTRING)
	{
		this.cSORTSTRING = cSORTSTRING;
	}
	
	
	

}
