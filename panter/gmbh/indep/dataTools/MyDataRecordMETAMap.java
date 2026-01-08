package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;




/*
 * klasse ,die eine abfragenkette anhand von unten nach oben aufbaut und die felder einer tabelle jeweils in einer
 * MyDataRecordHashMap ablegt
 */
public class MyDataRecordMETAMap extends HashMap<String,MyDataRecordHashMap> 
{

	private Vector<String> 	vTables = new Vector<String>();
	
	/**
	 * @param cTableName
	 * @param cTableIDFieldName
	 * @param cID
	 * @throws myException
	 */
	public MyDataRecordMETAMap(String cTableName, String cTableIDFieldName, String cID)  throws myException
	{
		super();
		
		MyDataRecordHashMap oHM = new MyDataRecordHashMap(cTableName, cTableIDFieldName, cID);
		this.put(cTableName,oHM);
		vTables.add(cTableName);
	}


	/**
	 * @param cTableName
	 * @param cWhereBlock
	 * @throws myException
	 */
	public MyDataRecordMETAMap(String cTableName, String cWhereBlock)  throws myException
	{
		super();
		
		MyDataRecordHashMap oHM = new MyDataRecordHashMap("SELECT * FROM "+bibALL.get_TABLEOWNER()+"."+cTableName+" WHERE "+cWhereBlock);
		this.put(cTableName,oHM);
		vTables.add(cTableName);
	}

	
	
	/**
	 * @param cTableName
	 * @param cTableIDFieldName
	 * @param cTableIDFieldName_in_previous_HashMap
	 * @param cWhereADDOn
	 * @throws myException
	 */
	public void add_Map_RelativeToLastTable(String cTableName, String cTableIDFieldName, String cTableIDFieldName_in_previous_HashMap, String cWhereADDOn)  throws myException
	{
		String cVorgaengerTable = (String)this.vTables.get(this.vTables.size()-1);
		String cMotherIDValueInPreviousTable = ((MyDataRecordHashMap)this.get(cVorgaengerTable)).get_UnFormatedValue(cTableIDFieldName_in_previous_HashMap); 
		
		
		String cWhere = cTableIDFieldName+"="+cMotherIDValueInPreviousTable;
		if (!bibALL.isEmpty(cWhereADDOn))
			cWhere += " AND "+cWhereADDOn;
		
		MyDataRecordHashMap oHM = new MyDataRecordHashMap("SELECT * FROM "+bibALL.get_TABLEOWNER()+"."+cTableName+
											" WHERE "+cWhere);
		
		this.vTables.add(cTableName);
		this.put(cTableName,oHM);
	}
	

	
	/**
	 * @param cTableName
	 * @param cTableIDFieldName
	 * @param cTableIDFieldName_in_previous_HashMap
	 * @param cWhereADDOn
	 * @throws myException
	 */
	public void add_Map_RelativeToTable(String cTableName, 
										String cTableIDFieldName, 
										String cTableIDFieldName_in_related_HashMap, 
										String cWhereADDOn, 
										String cVorgaengerTable)  throws myException
	{
		if (!this.vTables.contains(cVorgaengerTable))
			throw new myException(this,":Error adding Table to former Table "+cVorgaengerTable+" ! Table was not found !");
		
		String cMotherIDValueInPreviousTable = ((MyDataRecordHashMap)this.get(cVorgaengerTable)).get_UnFormatedValue(cTableIDFieldName_in_related_HashMap); 
		
		
		String cWhere = cTableIDFieldName+"="+cMotherIDValueInPreviousTable;
		if (!bibALL.isEmpty(cWhereADDOn))
			cWhere += " AND "+cWhereADDOn;
		
		MyDataRecordHashMap oHM = new MyDataRecordHashMap("SELECT * FROM "+bibALL.get_TABLEOWNER()+"."+cTableName+
											" WHERE "+cWhere);
		
		this.vTables.add(cTableName);
		this.put(cTableName,oHM);
	}
	

	
	/**
	 * @param cTableName
	 * @param cWhereBlock
	 * @throws myException
	 */
	public void add_Map(String cTableName, String cWhereBlock)  throws myException
	{
		MyDataRecordHashMap oHM = new MyDataRecordHashMap("SELECT * FROM "+bibALL.get_TABLEOWNER()+"."+cTableName+
				" WHERE "+cWhereBlock);

		this.vTables.add(cTableName);
		this.put(cTableName,oHM);
	}
	
	
	
	public String get_FormatedValue(String cFIELDNAME) throws myException
	{
		String cRueck = null;
		boolean bFound = false;
		for (int i=0;i<this.vTables.size();i++)
		{
			MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(this.vTables.get(i));
			
			if (oHM.containsKey(cFIELDNAME))
			{
				cRueck = oHM.get_FormatedValue(cFIELDNAME);
				bFound = true;
				break;
			}
		}
		if (!bFound)
			throw new myException("MyDataRecordMETAMap:get_FormatedValue:Field "+cFIELDNAME+" was not found !!");
		
		return cRueck;
	}

	
	
	
	public String get_UnFormatedValue(String cFIELDNAME)  throws myException
	{
		String cRueck = null;
		boolean bFound = false;
		for (int i=0;i<this.vTables.size();i++)
		{
			MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(this.vTables.get(i));
			
			if (oHM.containsKey(cFIELDNAME))
			{
				cRueck = oHM.get_UnFormatedValue(cFIELDNAME);
				bFound = true;
				break;
			}
		}
		if (!bFound)
			throw new myException("MyDataRecordMETAMap:get_FormatedValue:Field "+cFIELDNAME+" was not found !!");
		
		return cRueck;
	}
	
	
	
	public Double get_doubleValue(String cFIELDNAME) throws myException
	{
		Double dRueck = null;
		boolean bFound = false;
		for (int i=0;i<this.vTables.size();i++)
		{
			MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(this.vTables.get(i));
			
			if (oHM.containsKey(cFIELDNAME))
			{
				dRueck = oHM.get_doubleValue(cFIELDNAME);
				bFound = true;
				break;
			}
		}
		if (!bFound)
			throw new myException("MyDataRecordMETAMap:get_doubleValue:Field "+cFIELDNAME+" was not found !!");
		
		return dRueck;
	}
	
	
	/**
	 * @param cFIELDNAME
	 * @return s Wert, der direkt in eine SQL-anweisung eingefuegt werden kann
	 * @throws myException
	 */
	public String get_ValueStringForSQLStatement(String cFIELDNAME) throws myException
	{
		String cRueck = null;
		boolean bFound = false;
		for (int i=0;i<this.vTables.size();i++)
		{
			MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(this.vTables.get(i));
			
			if (oHM.containsKey(cFIELDNAME))
			{
				cRueck = oHM.get_ValueStringForSQLStatement(cFIELDNAME);
				bFound = true;
				break;
			}
		}

		if (!bFound)
			throw new myException("MyDataRecordMETAMap:get_ValueStringForSQLStatement:Field "+cFIELDNAME+" was not found !!");

		
		return cRueck;

	}
	
	
	
	
	
	public String get_FormatedValue(String cTableName,String cFIELDNAME) throws myException
	{
		
		MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(cTableName);
		if (oHM == null)
			throw new myException("MyDataRecordMETAMap:get_FormatedValue: Cannnot find Table "+cTableName);
		
		try
		{
			String cRueck = oHM.get_FormatedValue(cFIELDNAME);
			return cRueck;
		}
		catch (Exception ex)
		{
			throw new myException("MyDataRecordMETAMap:get_FormatedValue: Cannnot find Field "+cFIELDNAME);
		}
		
	}

	
	
	
	public String get_UnFormatedValue(String cTableName,String cFIELDNAME) throws myException
	{
		MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(cTableName);
		if (oHM == null)
			throw new myException("MyDataRecordMETAMap:get_UnFormatedValue: Cannnot find Table "+cTableName);
		
		try 
		{
			String cRueck = oHM.get_UnFormatedValue(cFIELDNAME);
			return cRueck;
		} 
		catch (myException e) 
		{
			throw new myException("MyDataRecordMETAMap:get_UnFormatedValue: Cannnot find Field "+cFIELDNAME);
		}
	}
	
	
	
	public Double get_doubleValue(String cTableName,String cFIELDNAME) throws myException
	{
		MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(cTableName);
		if (oHM == null)
			throw new myException("MyDataRecordMETAMap:get_doubleValue: Cannnot find Table "+cTableName);
		
		try 
		{
			Double dRueck = oHM.get_doubleValue(cFIELDNAME);
			return dRueck;
		} 
		catch (myException e) 
		{
			throw new myException("MyDataRecordMETAMap:get_UnFormatedValue: Cannnot find Field "+cFIELDNAME);
		}
	}
	
	
	/**
	 * @param cFIELDNAME
	 * @return s Wert, der direkt in eine SQL-anweisung eingefuegt werden kann
	 * @throws myException
	 */
	public String get_ValueStringForSQLStatement(String cTableName,String cFIELDNAME) throws myException
	{
		MyDataRecordHashMap oHM = (MyDataRecordHashMap)this.get(cTableName);
		if (oHM == null)
			throw new myException("MyDataRecordMETAMap:get_ValueStringForSQLStatement: Cannnot find Table "+cTableName);
		
		try 
		{
			String cRueck = oHM.get_ValueStringForSQLStatement(cFIELDNAME);
			return cRueck;
		} 
		catch (myException e) 
		{
			throw new myException("MyDataRecordMETAMap:get_UnFormatedValue: Cannnot find Field "+cFIELDNAME);
		}
	}
	
}
