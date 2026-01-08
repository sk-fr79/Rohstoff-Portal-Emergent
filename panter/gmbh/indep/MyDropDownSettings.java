/*
 * Created on 31.07.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.indep;



import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;



/*
 * Aufbau von listen für dropdown-felder mit gleichzeitigem Check
 * ob ein standard-wert vorhanden ist (für vorgaben)
 *
 */
public class MyDropDownSettings
{
	//~ Instance fields ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	String	    cTableOwner = "";
	String	    cTableName  = "";

	/*
	 * Feld mit den Werten für die datenbank
	 */
	String cValueField = "";

	/*
	 * Feld mit den Strings für die Liste
	 */
	String cListValueField = "";

	/*
	 * Feld, in dem die eintragung default = Y / N steht
	 * Wenn dieses Feld nicht vorhaden ist, dann
	 * gibt der default-wert "" zurück
	 */
	String		 	cDefaultValueField    = "";
	boolean			bDoAnEmptyValueToList = false;
	String[][]   	cReturnValues;
	String		 	cDefault			   = null;

	//~ Constructors ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




	/**
	 * @param cTableOwner
	 * @param cTableName
	 * @param cListValueField
	 * @param cValueField
	 * @param cWhereBlock
	 * @param cDefaultValueField
	 * @param bDoAnEmptyValueToList
	 * @param cOrderBlock
	 * @throws myException
	 */
	public MyDropDownSettings(	  String cTableOwner,
								  String cTableName,
								  String cListValueField,
								  String cValueField,
								  String cWhereBlock,
								  String cDefaultValueField,
								  boolean bDoAnEmptyValueToList, 
								  String cOrderBlock) throws myException
	{
		this._myDropDownSettings(cTableOwner, cTableName, cListValueField, cValueField, cWhereBlock, cDefaultValueField, bDoAnEmptyValueToList, cOrderBlock,true);
	}

	
	
	/**
	 * @param cTableOwner
	 * @param cTableName
	 * @param cListValueField
	 * @param cValueField
	 * @param cWhereBlock
	 * @param cDefaultValueField
	 * @param bDoAnEmptyValueToList
	 * @param cOrderBlock
	 * @param bFormated
	 * @throws myException
	 */
	public MyDropDownSettings(	  String cTableOwner,
								  String cTableName,
								  String cListValueField,
								  String cValueField,
								  String cWhereBlock,
								  String cDefaultValueField,
								  boolean bDoAnEmptyValueToList, 
								  String cOrderBlock,
								  boolean bFormated) throws myException
	{
		this._myDropDownSettings(cTableOwner, cTableName, cListValueField, cValueField, cWhereBlock, cDefaultValueField, bDoAnEmptyValueToList, cOrderBlock,bFormated);
	}

	
	
	//~ Methods --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private void _myDropDownSettings(String TableOwner,
									 String TableName,
									 String ListValueField,
									 String ValueField,
									 String cWhereBlockWOWhere,
									 String DefaultValueField,
									 boolean DoAnEmptyValueToList,
									 String cOrderBlock,
									 boolean bFormatedValues) throws myException          //2012-01-16: formatierte und unformatierte werte uebergeben
	{
		this.cTableOwner		   = TableOwner;
		this.cTableName			   = TableName;
		this.cValueField		   = ValueField;
		this.cListValueField	   = ListValueField;
		this.cDefaultValueField    = DefaultValueField;
		this.bDoAnEmptyValueToList = DoAnEmptyValueToList;

		String cWhereBlock		   = bibALL.null2leer(cWhereBlockWOWhere);

		String cSQLQuery = "SELECT " + this.cListValueField + "," + this.cValueField + " ";

		if (!bibALL.null2leer(this.cDefaultValueField).equals(""))
		{
			cSQLQuery += ("," + this.cDefaultValueField);
		}


		cSQLQuery += " FROM " + this.cTableOwner + "." + this.cTableName;
		if (!cWhereBlock.equals(""))
		{
			cSQLQuery += " WHERE "+cWhereBlock;
		}
		
		if (bibALL.isEmpty(cOrderBlock))
		{
			cSQLQuery +=  " ORDER BY " + ListValueField;
		}
		else
		{
			cSQLQuery +=  " ORDER BY " + cOrderBlock;
		}


		/*
		 * der feldwert muss! formatiert sein, da das tablefield eine formatierte eingabe, auch von 
		 * dropdown-feldern erwartet. deshalb wird hier immer ein formatierter wert übergeben
		 */
		
		//2012-01-16: formatierte und unformatierte werte uebergeben
		String[][] cResult = null;
		if (bFormatedValues)               //standard-wert fuer datenfelder in masken
		{
			cResult = bibDB.EinzelAbfrageInArrayFormatiert(cSQLQuery,"");
		}
		else
		{
			cResult = bibDB.EinzelAbfrageInArray(cSQLQuery,"");
		}
		
		//Fehler, wenn cResult == null
		if (cResult == null)
		{
			throw new myException(this,"Error Creating DropDownSettings: "+cSQLQuery);
		}

		if (cResult.length == 0)   // falls die abfrage nix ergibt, dann wenigstens leerzeile zurueck
		{
			this.cReturnValues		   = new String[1][2];
			this.cReturnValues [0] [0] = "-";
			this.cReturnValues [0] [1] = "";
		}
		else
		{
			int iAnzahl = cResult.length;

			if (this.bDoAnEmptyValueToList)
			{
				iAnzahl++; // dann einen weiteren eintrag generieren
			}

			this.cReturnValues = new String[iAnzahl][2];

			int iOffset = 0;

			if (this.bDoAnEmptyValueToList)
			{
				this.cReturnValues [0] [0] = "-";
				this.cReturnValues [0] [1] = "";
				iOffset					   = 1;
			}

			this.cDefault = null;

			for (int i = 0; i < cResult.length; i++)
			{
				this.cReturnValues [i + iOffset] [0] = cResult [i] [0];
				this.cReturnValues [i + iOffset] [1] = cResult [i] [1];

				if (cResult [0].length == 3)
				{ // dann wurde auch default abgefragt

					if (this.cDefault == null)
					{
						if (cResult [i] [2].equalsIgnoreCase("Y"))
						{
							this.cDefault = cResult [i] [1];
						}
					}
				}
			}
		}
	}

	public String getDefault()
	{
		return bibALL.null2leer(cDefault);
	}

	public String[][] getDD()
	{
		return cReturnValues;
	}
}
