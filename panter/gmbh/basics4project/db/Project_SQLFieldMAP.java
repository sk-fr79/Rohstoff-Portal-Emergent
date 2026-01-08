package panter.gmbh.basics4project.db;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class Project_SQLFieldMAP extends SQLFieldMAP {

	
	private boolean bHasBeenInitialized = false;
	private String  cMAIN_TABLE = null;
	//private String  cID_NAME = null;
	
	/**
	 * @param cMain_Table
	 * @param cAusnahmeFelder (getrennt durch :)
	 * @param bInit
	 * @throws myException
	 * Baut eine dem namensschema konforme sqlfieldmap auf 
	 */
	public Project_SQLFieldMAP(String cMain_Table, String cAusnahmeFelder, boolean bInit)  throws myException
	{
		super(cMain_Table, bibE2.get_CurrSession());
		
		
		String cAusnahmen = ":"+"ID"+cMain_Table.substring(2)+":ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:";
		cAusnahmen = cAusnahmen+ bibALL.null2leer(cAusnahmeFelder)+":";
		
		this.addCompleteTable_FIELDLIST(cMain_Table,cAusnahmen,false,true, "");
		
		String cID_Name = "ID"+cMain_Table.substring(2);
		String cSeq_Name = "SEQ"+cMain_Table.substring(2);
		
		this.add_SQLField(new SQLFieldForPrimaryKey(cMain_Table,cID_Name,cID_Name,new MyE2_String("ID"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+cSeq_Name+".NEXTVAL FROM DUAL",true), false);

		if (bInit) this.initFields();
		
		
		this.bHasBeenInitialized = bInit;
		//this.cID_NAME = cID_Name;
		this.cMAIN_TABLE = cMain_Table;
	}

	
	
	/**
	 * Einfache moeglichkeit, eine look-up-verbindung in eine Mutter-Tabelle zu bauen.
	 * 
	 * @param cTableName
	 * @param cADDFieldNames (durch : getrennt)
	 * @param cNamePrefix
	 * @param cConnectionString (wenn NULL, muss der Feldname der Mutter-ID in beiden tabellen gleich sein)
	 * @throws myException
	 */
	public void add_ConnectedLookUpTable(String cTableName, String cADDFieldNames,String cNamePrefix, String cConnectionString) throws myException
	{
		if (this.bHasBeenInitialized)
			throw new myException("ROHSTOFF_SQLFieldMAP:add_ConnectedTable: SQLFieldMAP is initialized !! ");
		
		String cID_Name2 = 	"ID"+cTableName.substring(2);
		String cSeq_Name2 = "SEQ"+cTableName.substring(2);
	
		this.addCompleteTable_FIELDLIST(cTableName,cADDFieldNames,true,false, cNamePrefix);
		
		this.add_SQLField(new SQLFieldForPrimaryKey(cTableName,cID_Name2,cNamePrefix+cID_Name2,new MyE2_String("ID"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+cSeq_Name2+".NEXTVAL FROM DUAL",true), false);
		
		if (cConnectionString == null)
			this.add_InnerConnector(new SQLConnectorInnerTables(cTableName+"."+cID_Name2+"="+this.cMAIN_TABLE+"."+cID_Name2));
		else
			this.add_InnerConnector(new SQLConnectorInnerTables(cConnectionString));
	
		
	
		
		
	}
	
	public void addField(String sqlValue, String sqlAsAlias) throws myException {
		SQLField oField = new SQLField(sqlValue, sqlAsAlias, new MyString(sqlAsAlias), bibE2.get_CurrSession());
		oField.set_bWriteable(false);
		oField.set_bFieldCanBeWrittenInMask(false);
		this.add_SQLField(oField, false);
	}
	
	
	
}
