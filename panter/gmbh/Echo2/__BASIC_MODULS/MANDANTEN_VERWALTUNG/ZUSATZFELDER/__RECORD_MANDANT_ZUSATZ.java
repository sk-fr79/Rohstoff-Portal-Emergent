package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class __RECORD_MANDANT_ZUSATZ extends RECORD_MANDANT_ZUSATZ
{

	public __RECORD_MANDANT_ZUSATZ() throws myException
	{
		super();
	}

	public __RECORD_MANDANT_ZUSATZ(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}

	public __RECORD_MANDANT_ZUSATZ(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}

	public __RECORD_MANDANT_ZUSATZ(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public __RECORD_MANDANT_ZUSATZ(RECORD_MANDANT_ZUSATZ recordOrig)
	{
		super(recordOrig);
	}

	public __RECORD_MANDANT_ZUSATZ(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public __RECORD_MANDANT_ZUSATZ(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}


	/**
	 * @deprecated
	 * @param cFeldName
	 * @param cValueWhenNotDefined
	 * @param cValueWhenNotSet
	 * @return
	 */
	public static boolean  IS___Value(String cFeldName, String cValueWhenNotDefined, String cValueWhenNotSet)
	{
		String cValue = cValueWhenNotDefined;
		
		try
		{
			RECORD_MANDANT_ZUSATZ  recZusatz = new RECORD_MANDANT_ZUSATZ("ID_MANDANT="+bibALL.get_ID_MANDANT()+
					" AND UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
			
			
			if (recZusatz!=null)
			{
				cValue = recZusatz.get_TEXT_VALUE_cUF_NN("");
				if (S.isEmpty(cValue))
				{
					cValue = cValueWhenNotSet;
				}
			}
		}
		catch (myException e)
		{
			e.printStackTrace();
		}

		return cValue.equals("Y");
	}
	
	
	/*
	 * neue version, die zuerst das vorhandensein des parameters prueft und dann einen wert nachschaut
	 */
	public static boolean  IS__Value(String cFeldName, String cValueWhenNotDefined, String cValueWhenNotSet)
	{
		
		return bib_Settigs_Mandant.IS__Value(cFeldName, cValueWhenNotDefined, cValueWhenNotSet);
		
		
//		String cValue = cValueWhenNotDefined;
//		
//		String cAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ " +
//				" WHERE JD_MANDANT_ZUSATZ.ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
//		
//		if (!cAnzahl.trim().equals("0"))
//		{
//			try
//			{
//				RECORD_MANDANT_ZUSATZ  recZusatz = new RECORD_MANDANT_ZUSATZ("ID_MANDANT="+bibALL.get_ID_MANDANT()+
//						" AND UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
//				
//				
//				if (recZusatz!=null)
//				{
//					cValue = recZusatz.get_TEXT_VALUE_cUF_NN("");
//					if (S.isEmpty(cValue))
//					{
//						cValue = cValueWhenNotSet;
//					}
//				}
//			}
//			catch (myException e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//		
//		return cValue.equals("Y");
	}

	
	public static String  get___Value(String cFeldName, String cValueWhenNotDefined, String cValueWhenNotSet)
	{
		return bib_Settigs_Mandant.get__Value(cFeldName, cValueWhenNotDefined, cValueWhenNotSet);
		
//		
//		
//		String cValue = cValueWhenNotDefined;
//		
//		try
//		{
//			RECORD_MANDANT_ZUSATZ  recZusatz = new RECORD_MANDANT_ZUSATZ("ID_MANDANT="+bibALL.get_ID_MANDANT()+
//					" AND UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
//			
//			
//			if (recZusatz!=null)
//			{
//				cValue = recZusatz.get_TEXT_VALUE_cUF_NN("");
//				if (S.isEmpty(cValue))
//				{
//					cValue = cValueWhenNotSet;
//				}
//			}
//		}
//		catch (myException e)
//		{
//			e.printStackTrace();
//		}
//
//		return cValue;
	}
	

	
	/**
	 * Liest den Wert aus der DB.
	 * Falls der Wert nicht explizit beim Mandanten gesetzt ist aber es gibt einen Defaultwert, wird der Default genommen
	 * sonst der alternative übergebenen Wert cValueWhenNoDefault.
	 * Dabei ist es egal ob der Wert überhaupt definiert wurde in der JD_MANDANT_ZUSATZ_FELDNAMEN.
	 * 
	 * Schneller da ohne RECORDS und mit JOIN 
	 * 
	 * @author manfred
	 * @date   12.04.2013
	 * @param cFeldName
	 * @param cValueWhenNoDefault
	 * @return
	 */
	public static String  get___Value(String cFeldName,  String cValueWhenNoDefault)
	{
		return bib_Settigs_Mandant.get__Value(cFeldName, cValueWhenNoDefault);
//		
//		
//		String cValue 			= cValueWhenNoDefault;
//		String cValueSet 		= null;
//		String cValueDefault 	= null;
//	
//		String[][] cWerte = bibDB.EinzelAbfrageInArray(
//			" SELECT Z.TEXT_VALUE, ZF.DEFAULT_TEXT_VALUE FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN ZF " +
//			" LEFT OUTER JOIN  "+bibE2.cTO()+".JD_MANDANT_ZUSATZ  Z " +
//			"		ON ZF.ID_MANDANT = Z.ID_MANDANT " +
//			"		AND ZF.ID_MANDANT_ZUSATZ_FELDNAMEN = Z.ID_MANDANT_ZUSATZ_FELDNAMEN " +
//			" WHERE ZF.ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  UPPER(ZF.FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")"
//			,(String)null);
//		
//		
//		if(cWerte.length > 0){
//			cValueSet 		= cWerte[0][0];
//			cValueDefault 	= cWerte[0][1];
//			
//			if (cValueSet != null) {
//				cValue = cValueSet;
//			} else if (cValueDefault != null){
//				cValue = cValueDefault;
//			}
//		}
//		
//		return cValue;
	}
	
	
	
}
