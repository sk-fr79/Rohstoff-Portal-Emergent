
package panter.gmbh.Echo2;


import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * Aufbau von listen für dropdown-felder mit gleichzeitigem Check
 * ob ein standard-wert vorhanden ist (für vorgaben)
 *
 */
public class E2_DropDownSettingsNew
{

		/*
	 * Feld, in dem die eintragung default = Y / N steht
	 * Wenn dieses Feld nicht vorhaden ist, dann
	 * gibt der default-wert "" zurück
	 */
	private boolean		 	bDoAnEmptyValueToList = false;
	private String[][]  	cReturnValues;
	private String		 	cDefault			   = null;


	private boolean  		bQueryFormated = true;     //je nachdem, ob der selektor im DB-Bereich oder sonst benutzt wird, ist 
														// es sinnvoll einmal als formatiert und unformatiert abzufragen
	
	
	/**
	 * 
	 * @param cTable_Name
	 * @param cList_ValueField
	 * @param cValue_Field
	 * @param cDefault_ValueField
	 * @param bDoAnEmpty_ValueToList
	 * @throws myException
	 */
	public E2_DropDownSettingsNew(String 	cTable_Name,
							   String 	cList_ValueField,
							   String 	cValue_Field,
							   String 	cDefault_ValueField,
							   boolean 	bDoAnEmpty_ValueToList,
							   boolean  QueryFormated) throws myException
	{
		this._myDropDownSettings(	cTable_Name, cList_ValueField, cValue_Field, "", cDefault_ValueField, 
									bDoAnEmpty_ValueToList, null,QueryFormated);
	}

	
	/**
	 * 
	 * @param cTable_Name
	 * @param cList_ValueField
	 * @param cValue_Field
	 * @param cWhere_Block
	 * @param cDefault_ValueField
	 * @param bDoAnEmpty_ValueToList
	 * @param cOrderString
	 * @throws myException
	 */
	public E2_DropDownSettingsNew(String 	cTable_Name,
							  String 	cList_ValueField,
							  String 	cValue_Field,
							  String 	cWhere_Block,
							  String 	cDefault_ValueField,
							  boolean 	bDoAnEmpty_ValueToList, 
							  String 	cOrderString,
							   boolean  QueryFormated) throws myException
	{
		this._myDropDownSettings(	cTable_Name, cList_ValueField, cValue_Field, 
									cWhere_Block, cDefault_ValueField, bDoAnEmpty_ValueToList, 
									cOrderString,QueryFormated);
	}


	
	/**
	 * 
	 * @param cSQLQueryComplete
	 * @param bDoAnEmpty_ValueToList
	 * @throws myException
	 */
	public E2_DropDownSettingsNew(	String 	cSQLQueryComplete,
									boolean bDoAnEmpty_ValueToList,
									boolean  QueryFormated) throws myException
	{
		this.__myDropDownSettings(cSQLQueryComplete, bDoAnEmpty_ValueToList,QueryFormated);
	}

	
	
	
	private void _myDropDownSettings(	String 		cTable_Name,
										String 		cListValue_Field,
										String 		cValue_Field,
										String 		cWhereBlockWOWhere,
										String 		cDefault_ValueField,
										boolean 	bDoAnEmpty_ValueToList, 
										String 		cOrderString,
										boolean  	QueryFormated) throws myException
	{

		String cWhereBlock		   = bibALL.null2leer(cWhereBlockWOWhere);

		String cSQLQuery = "SELECT " + cListValue_Field + "," + cValue_Field + " ";

		if (!bibALL.null2leer(cDefault_ValueField).equals(""))
		{
			cSQLQuery += ("," + cDefault_ValueField);
		}


		cSQLQuery += " FROM " + bibE2.cTO() + "." + cTable_Name;
		if (!cWhereBlock.equals(""))
		{
			cSQLQuery += " WHERE "+cWhereBlock;
		}
		if (bibALL.isEmpty(cOrderString))
			cSQLQuery +=  " ORDER BY " + cListValue_Field;
		else
			cSQLQuery +=  " ORDER BY " + cOrderString;

		this.__myDropDownSettings(cSQLQuery,bDoAnEmpty_ValueToList,QueryFormated);
	}

	
	private void __myDropDownSettings(	String 	cSQLQueryComplete,
										boolean bDoAnEmpty_ValueToList,
			   							boolean QueryFormated) throws myException
	{
		/*
		 * der feldwert muss! formatiert sein, da das tablefield eine formatierte eingabe, auch von 
		 * dropdown-feldern erwartet. deshalb wird hier immer ein formatierter wert übergeben
		 */
		this.bDoAnEmptyValueToList = bDoAnEmpty_ValueToList;
		this.bQueryFormated = QueryFormated;

		
		String[][] cResult = this.bQueryFormated?bibDB.EinzelAbfrageInArrayFormatiert(cSQLQueryComplete, ""):
												bibDB.EinzelAbfrageInArray(cSQLQueryComplete, "");

		if (cResult==null)
		{
			throw new myException(this,"Error Creating DropDownSetting: "+cSQLQueryComplete);
		}
		
		if (cResult.length==0)
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

	
	/**
	 * @return String[][2], in 0 = Beschreibung, in 1 = wert fuer datenbank
	 */
	public String[][] getDD()
	{
		return cReturnValues;
	}
}
