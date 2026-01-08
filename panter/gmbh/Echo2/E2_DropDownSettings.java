/*
 * Created on 31.07.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2;


import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * Aufbau von listen für dropdown-felder mit gleichzeitigem Check
 * ob ein standard-wert vorhanden ist (für vorgaben)
 *
 */
public class E2_DropDownSettings
{
	//~ Instance fields ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private String 			cTO			= bibALL.get_TABLEOWNER();

	/*
	 * Feld, in dem die eintragung default = Y / N steht
	 * Wenn dieses Feld nicht vorhaden ist, dann
	 * gibt der default-wert "" zurück
	 */
	private boolean		bDoAnEmptyValueToList = false;
	private String[][]  	cReturnValues;
	private String		 	cDefault			   = null;


	//2013-07-24: erweiterung mit einem moeglichen shadow-bereich fuer z.b. aktiv/inaktiv-schaltung von listen
	private String[][]  	cReturnValuesShadow 	= null;;
	
	


	/**
	 * 
	 * @param cTable_Name
	 * @param cList_ValueField
	 * @param cValue_Field
	 * @param cDefault_ValueField
	 * @param bDoAnEmpty_ValueToList
	 * @throws myException
	 */
	public E2_DropDownSettings(String 	cTable_Name,
							   String 	cList_ValueField,
							   String 	cValue_Field,
							   String 	cDefault_ValueField,
							   boolean 	bDoAnEmpty_ValueToList) throws myException
	{
		this._myDropDownSettings(cTable_Name, cList_ValueField, cValue_Field, "", cDefault_ValueField, bDoAnEmpty_ValueToList, null);
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
	public E2_DropDownSettings(String 	cTable_Name,
							  String 	cList_ValueField,
							  String 	cValue_Field,
							  String 	cWhere_Block,
							  String 	cDefault_ValueField,
							  boolean 	bDoAnEmpty_ValueToList, 
							  String 	cOrderString) throws myException
	{
		this._myDropDownSettings(cTable_Name, cList_ValueField, cValue_Field, cWhere_Block, cDefault_ValueField, bDoAnEmpty_ValueToList, cOrderString);
	}


	
	/**
	 * 
	 * @param cSQLQueryComplete
	 * @param bDoAnEmpty_ValueToList
	 * @throws myException
	 */
	public E2_DropDownSettings(String 	cSQLQueryComplete,
							  boolean 	bDoAnEmpty_ValueToList) throws myException
	{
		this.__myDropDownSettings(cSQLQueryComplete,null, bDoAnEmpty_ValueToList);
	}

	

	
	/**
	 * 
	 * @param cSQLQueryComplete
	 * @param bDoAnEmpty_ValueToList
	 * @throws myException
	 */
	public E2_DropDownSettings(
							String 	cSQLQueryComplete,
							String  cSQLQueryComplete4ShadowBlock,
							 boolean 	bDoAnEmpty_ValueToList) throws myException
	{
		this.__myDropDownSettings(cSQLQueryComplete,cSQLQueryComplete4ShadowBlock, bDoAnEmpty_ValueToList);
	}


	
	
	private void _myDropDownSettings(	String 		cTable_Name,
										String 		cListValue_Field,
										String 		cValue_Field,
										String 		cWhereBlockWOWhere,
										String 		cDefault_ValueField,
										boolean 	bDoAnEmpty_ValueToList, 
										String 		cOrderString) throws myException
	{

		String cWhereBlock		   = bibALL.null2leer(cWhereBlockWOWhere);

		String cSQLQuery = "SELECT " + cListValue_Field + "," + cValue_Field + " ";

		if (!bibALL.null2leer(cDefault_ValueField).equals(""))
		{
			cSQLQuery += ("," + cDefault_ValueField);
		}


		cSQLQuery += " FROM " + this.cTO + "." + cTable_Name;
		if (!cWhereBlock.equals(""))
		{
			cSQLQuery += " WHERE "+cWhereBlock;
		}
		if (bibALL.isEmpty(cOrderString))
			cSQLQuery +=  " ORDER BY " + cListValue_Field;
		else
			cSQLQuery +=  " ORDER BY " + cOrderString;

		this.__myDropDownSettings(cSQLQuery,null,bDoAnEmpty_ValueToList);
	}

	
	private void __myDropDownSettings(String cSQLQueryComplete, String cSQLQuery4Shadow,   boolean 	bDoAnEmpty_ValueToList) throws myException
	{
		/*
		 * der feldwert muss! formatiert sein, da das tablefield eine formatierte eingabe, auch von 
		 * dropdown-feldern erwartet. deshalb wird hier immer ein formatierter wert übergeben
		 */
		this.bDoAnEmptyValueToList = bDoAnEmpty_ValueToList;

		String[][] cResult = 			bibDB.EinzelAbfrageInArrayFormatiert(cSQLQueryComplete, "");
		String[][] cResult4Shadow = 	null;
		
		if (S.isFull(cSQLQuery4Shadow)) {
			cResult4Shadow = bibDB.EinzelAbfrageInArrayFormatiert(cSQLQuery4Shadow, "");
		}

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
		
		if (cResult4Shadow!=null && cResult4Shadow.length>0) {
			this.cReturnValuesShadow	 = new String[cResult4Shadow.length][2];
			for (int i = 0; i < cResult4Shadow.length; i++)
			{
				this.cReturnValuesShadow [i] [0] = cResult4Shadow [i] [0];
				this.cReturnValuesShadow [i] [1] = cResult4Shadow [i] [1];
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

	public String[][] get_DD_Shadow() {
		return cReturnValuesShadow;
	}

}
