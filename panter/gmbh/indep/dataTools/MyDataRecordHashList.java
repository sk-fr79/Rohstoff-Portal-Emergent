package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;


public class MyDataRecordHashList extends HashMap<String, MyDataRecordHashMap>
{
	
	private Vector<String> 	vFieldListe = new Vector<String>();
	private String 			cSQLFromBlock = null;
	private String 			cSQLWhereBlock = null;
	private String 			cSQLOrderBlock = null;

	private Vector<String>  vVectorWithHashKeys = new Vector<String>();
	
	//2012-08-15: variante mit RAW-Datenbankabfrage (ohne Datenbankzusatzfelder und View-ersetzung) 
	private		boolean 	bErsetzungTableView = true; 
	private     boolean     bKeyFieldIsNumeric = true;          //und alphanumerische key-felder

	
	/**
	 * @param fieldListe (das erste feld muss in der ResultSet einen eindeutigen INDEX-Wert fuer die HashMap ergeben)
	 * @param fromBlock
	 * @param whereBlock
	 * @param orderBlock
	 */
	public MyDataRecordHashList(Vector<String> fieldListe, String fromBlock, String whereBlock, String orderBlock) throws myException
	{
		super();
		vFieldListe.addAll(fieldListe);
		cSQLFromBlock = fromBlock;
		cSQLWhereBlock = whereBlock;
		cSQLOrderBlock = orderBlock;
		
		this.refresh();
	}


	/**
	 * 2012-08-15: neue version mit raw-abfrage
	 * @param fieldListe (das erste feld muss in der ResultSet einen eindeutigen INDEX-Wert fuer die HashMap ergeben)
	 * @param fromBlock
	 * @param whereBlock
	 * @param orderBlock
	 * @param ErsetzungTableView
	 * @throws myException
	 */
	public MyDataRecordHashList(Vector<String> fieldListe, String fromBlock, String whereBlock, String orderBlock,boolean ErsetzungTableView, boolean KeyFieldIsNumeric) throws myException
	{
		super();
		vFieldListe.addAll(fieldListe);
		cSQLFromBlock = fromBlock;
		cSQLWhereBlock = whereBlock;
		cSQLOrderBlock = orderBlock;
		
		this.bErsetzungTableView = ErsetzungTableView;
		this.bKeyFieldIsNumeric = KeyFieldIsNumeric;
		
		this.refresh();
	}

	
	public int get_size()
	{
		return this.vVectorWithHashKeys.size();
	}


	public void refresh() throws myException 
	{
		String cSQL_String = "SELECT "+bibALL.Concatenate(this.vFieldListe, ",", "")+" FROM "+this.cSQLFromBlock;
		
		String cIndexField = this.vFieldListe.get(0);
		

		// zuerst query nach den Strings
		String cSQL_String_for_Index = "SELECT "+this.vFieldListe.get(0)+" FROM "+this.cSQLFromBlock;
		if (!bibALL.isEmpty(this.cSQLWhereBlock)) cSQL_String_for_Index += " WHERE "+this.cSQLWhereBlock;
		if (!bibALL.isEmpty(this.cSQLOrderBlock)) cSQL_String_for_Index += " ORDER BY "+this.cSQLOrderBlock;
		
		//2012-08-15: neuer parameter fuer raw-statements
		String[][] cIndexArray = null;
		
		if (this.bErsetzungTableView)
		{
			cIndexArray = bibDB.EinzelAbfrageInArray(cSQL_String_for_Index);
		}
		else
		{
			cIndexArray = bibDB.EinzelAbfrageInArray(cSQL_String_for_Index,false);
		}
		// ende
		
		//vorher:String[][] cIndexArray = bibDB.EinzelAbfrageInArray(cSQL_String_for_Index);
		
		
		if (cIndexArray==null)
			throw new myException(this,"Error querying indexlist ...");
		
		for (int i=0;i<cIndexArray.length;i++)
		{
			String cHash = cIndexArray[i][0];
			
			if (this.vVectorWithHashKeys.contains(cHash))
				throw new myException(this,"duplicate key-value: "+cHash);
			
			this.vVectorWithHashKeys.add(cHash);
			
			//2012-08-15: neue parameter bErsetzungTableView und bKeyFieldIsNumeric
			if (this.bErsetzungTableView)
			{
				this.put(cHash, new MyDataRecordHashMap(cSQL_String+" WHERE "+cIndexField+"="+(this.bKeyFieldIsNumeric?cHash:bibALL.MakeSql(cHash))));
			}
			else
			{
				this.put(cHash, new MyDataRecordHashMap(cSQL_String+" WHERE "+cIndexField+"="+(this.bKeyFieldIsNumeric?cHash:bibALL.MakeSql(cHash)),false));
			}
		}
	}
	

	
	
	//es gibt einen satz get_methoden mit einer nummer (index im Vector) und einem index-Wert
	public String get_FormatedValue(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");
		
		return get_FormatedValue(cRowName,this.vVectorWithHashKeys.get(iNumber));
	}

	
	public String get_UnFormatedValue(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");

		return get_UnFormatedValue(cRowName,this.vVectorWithHashKeys.get(iNumber));
	}


	
	
	
	public String get_FormatedValue_LeerWennNull(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");

		return get_FormatedValue_LeerWennNull(cRowName,this.vVectorWithHashKeys.get(iNumber));
	}

	
	public String get_UnFormatedValue_LeerWennNull(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");
		
		return get_UnFormatedValue_LeerWennNull(cRowName,this.vVectorWithHashKeys.get(iNumber));
	}

	
	
	
	public Double get_doubleValue(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");
		
		return get_doubleValue(cRowName,this.vVectorWithHashKeys.get(iNumber));
		
	}
	
	
	public Integer get_intValue(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");
		
		return get_intValue(cRowName,this.vVectorWithHashKeys.get(iNumber));
	}
	
	
	
	
	public Long get_longValue(String cRowName, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");
		
		return get_longValue(cRowName,this.vVectorWithHashKeys.get(iNumber));
	}
	
	
	
	/**
	 * @param cFIELDNAME
	 * @return s Wert, der direkt in eine SQL-anweisung eingefuegt werden kann
	 * @throws myException
	 */
	public String get_ValueStringForSQLStatement(String cFIELDNAME, int iNumber) throws myException
	{
		if (iNumber>this.vVectorWithHashKeys.size())
			throw new myException(this,"iNumber is bigger than rowNumber !");

		MyDataRecordHashMap oHMResultSet = this.get(this.vVectorWithHashKeys.get(iNumber));

		
		if (!oHMResultSet.containsKey(cFIELDNAME))
				throw new myException("MyDataRecordHashMap:	get_ValueStringForSQLStatement:"+cFIELDNAME+" not in MAP!");
		
		
		String cRueck = null;
		
		if (!this.containsKey(cFIELDNAME))
			throw new myException("MyDataRecordHashMap:get_ValueStringForSQLStatement:"+cFIELDNAME+" is not in the list!");

		if (this.get(cFIELDNAME)==null || oHMResultSet.get_FormatedValue(cFIELDNAME)==null || oHMResultSet.get_FormatedValue(cFIELDNAME).equals(""))
			return "NULL";
		
		
		MyMetaFieldDEF oMF = (MyMetaFieldDEF)oHMResultSet.get_hmMetaInformations().get(cFIELDNAME);
		String cTyp = oMF.get_FieldType();
		
		if (DB_META.vDB_NUMBER_TYPES.contains(cTyp))
		{
			cRueck = oHMResultSet.get_UnFormatedValue(cFIELDNAME);
	    }
		else if (DB_META.vDB_DATE_TYPES.contains(cTyp))
		{
			TestingDate oTD = new TestingDate(oHMResultSet.get_FormatedValue(cFIELDNAME));
			if (!oTD.testing())
				throw new myException("MyDataRecordHashMap:get_ValueStringForSQLStatement:"+cFIELDNAME+" is bad Dateformat!");
			
			cRueck = oTD.get_ISO_DateFormat(true);
		}
		else if (DB_META.vDB_TEXT_TYPES.contains(cTyp))
		{
			cRueck = bibALL.MakeSql(oHMResultSet.get_UnFormatedValue(cFIELDNAME));
		}
		
		return cRueck;
	}
	
	
	
///////////////////////////////////////////////////////////////777	
	
	
	
	
	
	
	//es gibt einen satz get_methoden mit einer nummer (index im Vector) und einem index-Wert
	public String get_FormatedValue(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
		
		if (!oHMResultSet.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:get_FormatedValue:"+cRowName+" not in MAP!");
	
		String cRueck = null;
		
		String[] cWert = (String[])oHMResultSet.get(cRowName);
		if (cWert != null)
		{
			cRueck = cWert[1];
		}
		
		return cRueck;
	}

	
	public String get_UnFormatedValue(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
		
		if (!oHMResultSet.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_UnFormatedValue:"+cRowName+" not in MAP!");
	
		String cRueck = null;
		String[] cWert = (String[])oHMResultSet.get(cRowName);
		if (cWert != null)
		{
			cRueck = cWert[0];
		}
		
		return cRueck;
	}



	
	
	public String get_FormatedValue_LeerWennNull(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);

		return bibALL.null2leer(oHMResultSet.get_FormatedValue(cRowName));
	}

	
	public String get_UnFormatedValue_LeerWennNull(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
		
		
		return bibALL.null2leer(oHMResultSet.get_UnFormatedValue(cRowName));
	}

	
	
	
	public Double get_doubleValue(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
		
		if (!oHMResultSet.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_doubleValue:"+cRowName+" not in MAP!");
		
		Double dRueck = null;
		
		String[] cWert = (String[])oHMResultSet.get(cRowName);
		if (cWert != null)
		{
			try
			{
				dRueck = new Double(cWert[0]);
			}
			catch (Exception ex)
			{
				throw new myException("MyDataRecordHashMap:get_doubleValue: Cannnot create double-Value "+cRowName);
			}
		}
		
		return dRueck;
		
	}
	
	
	
	public Integer get_intValue(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
		
		if (!oHMResultSet.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_intValue:"+cRowName+" not in MAP!");
		
		Integer iRueck = null;
		
		String[] cWert = (String[])oHMResultSet.get(cRowName);
		if (cWert != null)
		{
			try
			{
				iRueck = new Integer(cWert[0]);
			}
			catch (Exception ex)
			{
				throw new myException("MyDataRecordHashMap:get_intValue: Cannnot create integer-Value "+cRowName);
			}
		}
		
		return iRueck;
		
	}
	
	
	
	
	public Long get_longValue(String cRowName, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
	
		if (!oHMResultSet.containsKey(cRowName))
			throw new myException("MyDataRecordHashMap:	get_longValue:"+cRowName+" not in MAP!");
		
		Long lRueck = null;
		
		String[] cWert = (String[])oHMResultSet.get(cRowName);
		if (cWert != null)
		{
			try
			{
				lRueck = new Long(cWert[0]);
			}
			catch (Exception ex)
			{
				throw new myException("MyDataRecordHashMap:get_longValue: Cannnot create Long-Value "+cRowName);
			}
		}
		
		return lRueck;
		
	}
	
	

	
	
	/**
	 * @param cFIELDNAME
	 * @return s Wert, der direkt in eine SQL-anweisung eingefuegt werden kann
	 * @throws myException
	 */
	public String get_ValueStringForSQLStatement(String cFIELDNAME, String cHashKey) throws myException
	{
		if (!this.vVectorWithHashKeys.contains(cHashKey))
			throw new myException(this,"HashKey in not in this rows-collection !");
		
		MyDataRecordHashMap oHMResultSet = this.get(cHashKey);
		
		if (!oHMResultSet.containsKey(cFIELDNAME))
				throw new myException("MyDataRecordHashMap:	get_ValueStringForSQLStatement:"+cFIELDNAME+" not in MAP!");
		
		
		String cRueck = null;
		
		if (!this.containsKey(cFIELDNAME))
			throw new myException("MyDataRecordHashMap:get_ValueStringForSQLStatement:"+cFIELDNAME+" is not in the list!");

		if (this.get(cFIELDNAME)==null || oHMResultSet.get_FormatedValue(cFIELDNAME)==null || oHMResultSet.get_FormatedValue(cFIELDNAME).equals(""))
			return "NULL";
		
		
		MyMetaFieldDEF oMF = (MyMetaFieldDEF)oHMResultSet.get_hmMetaInformations().get(cFIELDNAME);
		String cTyp = oMF.get_FieldType();
		
		if (DB_META.vDB_NUMBER_TYPES.contains(cTyp))
		{
			cRueck = oHMResultSet.get_UnFormatedValue(cFIELDNAME);
	    }
		else if (DB_META.vDB_DATE_TYPES.contains(cTyp))
		{
			TestingDate oTD = new TestingDate(oHMResultSet.get_FormatedValue(cFIELDNAME));
			if (!oTD.testing())
				throw new myException("MyDataRecordHashMap:get_ValueStringForSQLStatement:"+cFIELDNAME+" is bad Dateformat!");
			
			cRueck = oTD.get_ISO_DateFormat(true);
		}
		else if (DB_META.vDB_TEXT_TYPES.contains(cTyp))
		{
			cRueck = bibALL.MakeSql(oHMResultSet.get_UnFormatedValue(cFIELDNAME));
		}
		
		return cRueck;
	}




	public String get_cSQLFromBlock()
	{
		return cSQLFromBlock;
	}




	public String get_cSQLOrderBlock()
	{
		return cSQLOrderBlock;
	}




	public String get_cSQLWhereBlock()
	{
		return cSQLWhereBlock;
	}




	public Vector<String> get_vFieldListe()
	{
		return vFieldListe;
	}


	public Vector<String> get_vVectorWithHashKeys()
	{
		return vVectorWithHashKeys;
	}


	public boolean get_bErsetzungTableView()
	{
		return bErsetzungTableView;
	}


	public void set_bErsetzungTableView(boolean ErsetzungTableView)
	{
		this.bErsetzungTableView = ErsetzungTableView;
	}

	public boolean get_bKeyFieldIsNumeric()
	{
		return bKeyFieldIsNumeric;
	}


	public void set_bKeyFieldIsNumeric(boolean KeyFieldIsNumeric)
	{
		this.bKeyFieldIsNumeric = KeyFieldIsNumeric;
	}

	
	
	
	
}
