package panter.gmbh.basics4project.BasicRecords;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BASIC_RECORD_USERSETTINGS extends MyRECORD
{

	public BASIC_RECORD_USERSETTINGS() throws myException
	{
		super();
	}

	public BASIC_RECORD_USERSETTINGS(BASIC_RECORD_USERSETTINGS recordOrig)
	{
		super(recordOrig);
	}


	public BASIC_RECORD_USERSETTINGS(long lID_Unformated) throws myException
	{
		super("JD_USERSETTINGS","ID_USERSETTINGS",""+lID_Unformated);
	}

	public BASIC_RECORD_USERSETTINGS(String c_ID_or_WHEREBLOCK)   throws myException 
	{
		super();
		if (bibALL.isLong(c_ID_or_WHEREBLOCK))
		{
			this.PrepareAndBuild("*", "JD_USERSETTINGS", "ID_USERSETTINGS="+c_ID_or_WHEREBLOCK);
		}
		else
		{
			this.PrepareAndBuild("*", "JD_USERSETTINGS", c_ID_or_WHEREBLOCK);
		}
		
		
	}

	public BASIC_RECORD_USERSETTINGS(long lID_Unformated, MyConnection Conn) throws myException
	{
		super("JD_USERSETTINGS","ID_USERSETTINGS",""+lID_Unformated, Conn);
	}


	public BASIC_RECORD_USERSETTINGS(String c_ID_or_WHEREBLOCK, MyConnection Conn)   throws myException 
	{
		super(Conn);
		
		if (bibALL.isLong(c_ID_or_WHEREBLOCK))
		{
			this.PrepareAndBuild("*", "JD_USERSETTINGS", "ID_USERSETTINGS="+c_ID_or_WHEREBLOCK);
		}
		else
		{
			this.PrepareAndBuild("*", "JD_USERSETTINGS", c_ID_or_WHEREBLOCK);
		}
		
	}

	
	
	public String get_TABLENAME()
	{
		return "JD_USERSETTINGS";
	}
	
	public String get_PRIMARY_KEY_NAME()
	{
		return "ID_USERSETTINGS";
	}


	public String get_PRIMARY_KEY_UF() throws myException
	{
		return this.get_ID_USERSETTINGS_cUF();
	}
	

	public long get_PRIMARY_KEY_VALUE() throws myException
	{
		return this.get_ID_USERSETTINGS_lValue(null).longValue();
	}
	
	
	public String get_SQL_UPDATE_STATEMENT() throws myException
	{
		return this.get_StatementBuilderFilledWithActualValues().get_CompleteUPDATEString(
				"JD_USERSETTINGS", bibE2.cTO(), "ID_USERSETTINGS="+this.get_ID_USERSETTINGS_cUF(), null);
	}
	
	public MyE2_MessageVector UPDATE() throws myException
	{
		return bibDB.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STATEMENT()),true);
	}
	
	public MyE2_MessageVector DELETE() throws myException
	{
		return bibDB.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE ID_USERSETTINGS="+this.get_ID_USERSETTINGS_cUF()),true);
	}
	
	
	



		private BASIC_RECORD_USER UP_RECORD_USER = null;





	/**
	 * References the Field ID_USER
	 * Falls keine Up-Reference-Wert vorhanden, wird null zurueckgegeben 
	 */
	public BASIC_RECORD_USER get_UP_RECORD_USER() throws myException
	{
		if (S.isEmpty(this.get_ID_USER_cUF()))
			return null;
	
	
		if (this.UP_RECORD_USER==null)
		{
			this.UP_RECORD_USER = new BASIC_RECORD_USER(this.get_ID_USER_cUF(),this.get_oConn());
		}
		return this.UP_RECORD_USER;
	}


	public String get_ID_USERSETTINGS_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USERSETTINGS");
	}

	public String get_ID_USERSETTINGS_cF() throws myException
	{
		return this.get_FormatedValue("ID_USERSETTINGS");	
	}

	public String get_ID_USERSETTINGS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USERSETTINGS");
	}

	public String get_ID_USERSETTINGS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USERSETTINGS",cNotNullValue);
	}

	public String get_ID_USERSETTINGS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USERSETTINGS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USERSETTINGS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USERSETTINGS", cNewValueFormated);
	}
		public Long get_ID_USERSETTINGS_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USERSETTINGS").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USERSETTINGS_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USERSETTINGS").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USERSETTINGS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USERSETTINGS").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USERSETTINGS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USERSETTINGS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USERSETTINGS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USERSETTINGS_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public String get_ID_USER_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_USER");
	}

	public String get_ID_USER_cF() throws myException
	{
		return this.get_FormatedValue("ID_USER");	
	}

	public String get_ID_USER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER");
	}

	public String get_ID_USER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_USER",cNotNullValue);
	}

	public String get_ID_USER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_USER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}
		public Long get_ID_USER_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_USER").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_USER_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_USER").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_USER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_USER").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public String get_HASHVALUE_SESSION_cUF() throws myException
	{
		return this.get_UnFormatedValue("HASHVALUE_SESSION");
	}

	public String get_HASHVALUE_SESSION_cF() throws myException
	{
		return this.get_FormatedValue("HASHVALUE_SESSION");	
	}

	public String get_HASHVALUE_SESSION_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("HASHVALUE_SESSION");
	}

	public String get_HASHVALUE_SESSION_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("HASHVALUE_SESSION",cNotNullValue);
	}

	public String get_HASHVALUE_SESSION_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("HASHVALUE_SESSION",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_HASHVALUE_SESSION(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("HASHVALUE_SESSION", cNewValueFormated);
	}
		public String get_MODUL_KENNER_cUF() throws myException
	{
		return this.get_UnFormatedValue("MODUL_KENNER");
	}

	public String get_MODUL_KENNER_cF() throws myException
	{
		return this.get_FormatedValue("MODUL_KENNER");	
	}

	public String get_MODUL_KENNER_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MODUL_KENNER");
	}

	public String get_MODUL_KENNER_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MODUL_KENNER",cNotNullValue);
	}

	public String get_MODUL_KENNER_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MODUL_KENNER",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MODUL_KENNER(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MODUL_KENNER", cNewValueFormated);
	}
		public String get_MODULSETTINGS_cUF() throws myException
	{
		return this.get_UnFormatedValue("MODULSETTINGS");
	}

	public String get_MODULSETTINGS_cF() throws myException
	{
		return this.get_FormatedValue("MODULSETTINGS");	
	}

	public String get_MODULSETTINGS_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("MODULSETTINGS");
	}

	public String get_MODULSETTINGS_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("MODULSETTINGS",cNotNullValue);
	}

	public String get_MODULSETTINGS_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("MODULSETTINGS",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_MODULSETTINGS(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("MODULSETTINGS", cNewValueFormated);
	}
		public String get_GEAENDERT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_cF() throws myException
	{
		return this.get_FormatedValue("GEAENDERT_VON");	
	}

	public String get_GEAENDERT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("GEAENDERT_VON",cNotNullValue);
	}

	public String get_GEAENDERT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("GEAENDERT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}
		public String get_ID_MANDANT_cUF() throws myException
	{
		return this.get_UnFormatedValue("ID_MANDANT");
	}

	public String get_ID_MANDANT_cF() throws myException
	{
		return this.get_FormatedValue("ID_MANDANT");	
	}

	public String get_ID_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MANDANT");
	}

	public String get_ID_MANDANT_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ID_MANDANT",cNotNullValue);
	}

	public String get_ID_MANDANT_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ID_MANDANT",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}
		public Long get_ID_MANDANT_lValue(Long lValueWhenNULL) throws myException
	{
		Long lRueck = this.get("ID_MANDANT").getLongValue();
		if (lRueck==null) 
		{ 
			return lValueWhenNULL; 
		} 
		else 
		{ 
			return lRueck;
		} 
	}
	public Double get_ID_MANDANT_dValue(Double dValueWhenNULL) throws myException
	{
		Double dRueck = this.get("ID_MANDANT").getDoubleValue();
		if (dRueck==null) 
		{ 
			return dValueWhenNULL; 
		} 
		else 
		{ 
			return dRueck;
		} 
	}
	

	
	public Double get_ID_MANDANT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		Double dRueck = this.get("ID_MANDANT").getDoubleValue();
		if (dRueck == null)
		{
			dRueck = dValueWhenNULL;           //der wert wird auch gerunden (falls nicht null)
		}
		
		if (dRueck==null) 
		{ 
			return null; 
		} 
		else 
		{ 
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		} 
	}
	
	
	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MANDANT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException
	{
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
	   
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
		
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MANDANT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException
	{
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender) cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		
		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_dValue(dValueWhenNULL,iNachkommaRunden);
		if (dHelp==null)
		{
			return "";
		}
		
		//beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck =  df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",")||cRueck.endsWith("."))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;

	}
		public String get_LETZTE_AENDERUNG_cUF() throws myException
	{
		return this.get_UnFormatedValue("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_cF() throws myException
	{
		return this.get_FormatedValue("LETZTE_AENDERUNG");	
	}

	public String get_LETZTE_AENDERUNG_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("LETZTE_AENDERUNG",cNotNullValue);
	}

	public String get_LETZTE_AENDERUNG_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("LETZTE_AENDERUNG",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}
		public String get_ERZEUGT_VON_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_cF() throws myException
	{
		return this.get_FormatedValue("ERZEUGT_VON");	
	}

	public String get_ERZEUGT_VON_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_VON",cNotNullValue);
	}

	public String get_ERZEUGT_VON_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERZEUGT_VON",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}
		public String get_ERZEUGT_AM_cUF() throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_cF() throws myException
	{
		return this.get_FormatedValue("ERZEUGT_AM");	
	}

	public String get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT() throws myException
	{
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_cUF_NN(String cNotNullValue) throws myException
	{
		return this.get_UnFormatedValue("ERZEUGT_AM",cNotNullValue);
	}

	public String get_ERZEUGT_AM_cF_NN(String cNotNullValue) throws myException
	{
		return this.get_FormatedValue("ERZEUGT_AM",cNotNullValue);	
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException
	{
		return this.set_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}
	}
