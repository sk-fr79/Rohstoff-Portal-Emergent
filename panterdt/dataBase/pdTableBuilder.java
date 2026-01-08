package panterdt.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.VectorDataBaseQuery;
import panter.gmbh.indep.exceptions.myException;
import panterdt.common.pdStringHandler;
import panterdt.exceptions.pdException;
import panterdt.exceptions.pdExceptionFalseDATABASE;

public class pdTableBuilder
{
    //~ Instance fields
    // ====================================================================================================================================================================================================================================================================================

    /** Creates a new instance of tableBuilder */
    private String tableOwner = null;

    private Vector<String> vTableDef = new Vector<String>(); // data definition query

    private Vector<String> vPrimaryKey = new Vector<String>();

    private Vector<String> vForeignKey = new Vector<String>();

    private Vector<String> vTableIndex = new Vector<String>();

    private Vector<String> vViews = new Vector<String>();

    private Vector<String> vSequences = new Vector<String>();

    private Vector<String> vTablesOfUser = new Vector<String>();

 
    private MyConnection   oConnection = null;
    
    
    
    private boolean bMigrate = false;            	// es ist nur eine moeglichkeit vorhanden: Source = sapdb, migrate = true -> uebersetzung in oracle-syntax
                       								// andersrum ist verboten
    
    //~ Constructors
    // =======================================================================================================================================================================================================================================================================================

    public pdTableBuilder(MyConnection Conn, boolean Migrate) throws myException
    {
    	this.oConnection 		= Conn;
    	this.tableOwner 		= this.oConnection.get_cUSER().toUpperCase().trim();
        this.bMigrate 			= Migrate;
        
        if (this.oConnection.get_cDBKENNUNG().equals(DB_META.DB_ORA) && this.bMigrate)
        	throw new myException("pdTableBuilder: Oracle-DB cannot be migrated !!");   // migration ist nur von sap zu oracle definiert
        
    }


    //~ Methods
    // ============================================================================================================================================================================================================================================================================================



    public Vector<String> getTablesOfUser()
    {
        return this.vTablesOfUser;
    }

    public Vector getTableDef()
    {
        return vTableDef;
    }

    public Vector getPrimaryKey()
    {
        return vPrimaryKey;
    }

    public Vector getForeignKey()
    {
        return vForeignKey;
    }

    public Vector getTableIndex()
    {
        return vTableIndex;
    }

    public Vector get_vSequences()
    {
        return vSequences;
    }
    public Vector get_vViews()
    {
        return vViews;
    }

    
    public void buildTableDef() throws myException
    {
		if (this.oConnection.get_cDBKENNUNG().equals(DB_META.DB_SAP))
		{
			this.buildTableDefSAP();
		}
		else if (this.oConnection.get_cDBKENNUNG().equals(DB_META.DB_ORA))
		{
			this.buildTableDefORACLE();
		}
		else
		{
			throw new pdExceptionFalseDATABASE(this.oConnection.get_cDBKENNUNG());
		}
    		
    }
    
    
    private void buildTableDefORACLE() throws myException
    {
    	
    	MyDBToolBox oDB = bibALL.get_myDBToolBox(false, false);
    	
    	// tables
    	String[][] cTables = 
    		oDB.EinzelAbfrageInArray("SELECT TABLE_NAME FROM SYS.USER_TABLES ORDER BY TABLE_NAME", "");
    	
    	Vector<String> vCreateStringsTables = new Vector<String>();
 
    	for (int i=0;i<cTables.length;i++)
    	{
    		this.vTablesOfUser.add(cTables[i][0]);
    		
    		String cHelp = "CREATE TABLE "+cTables[i][0]+ "(";
    		hmFieldDEF oFields = new hmFieldDEF(cTables[i][0]);
    		
    		for (int k=0;k<oFields.size();k++)
    		{
    			if (k>0)
    				cHelp+=",";
    			
    			cHelp+=oFields.get_cCreateFieldPart(k);
    		}
    		cHelp+=")";
    		vCreateStringsTables.add(cHelp);
    	}
    	this.vTableDef.addAll(vCreateStringsTables);
    	// --fertig tables
    	
    	
    	

    	// -- primary keys
    	String[][] cPKTables = 
    		oDB.EinzelAbfrageInArray("SELECT DISTINCT DBA_CONSTRAINTS.TABLE_NAME,DBA_CONSTRAINTS.CONSTRAINT_NAME " +
										" FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS" +
					    				" WHERE" +
					    				" CONSTRAINT_TYPE = 'P' AND" +
					    				" DBA_CONSTRAINTS.CONSTRAINT_NAME=USER_IND_COLUMNS.INDEX_NAME AND " +
					    				" UPPER(OWNER) = UPPER('"+bibE2.cTO()+"') ORDER BY TABLE_NAME", "");
    	
    	Vector<String> vCreatePrimaryKeys = 	new Vector<String>();

    	if (cPKTables == null)
			throw new myException(this,"Error Quering primary-key-tables ...");
 
    	for (int i=0;i<cPKTables.length;i++)
    	{
    		String cQueryFields = "SELECT 	DBA_CONSTRAINTS.TABLE_NAME," +
											"USER_IND_COLUMNS.COLUMN_NAME," +
											"USER_IND_COLUMNS.COLUMN_POSITION," +
											"DBA_CONSTRAINTS.CONSTRAINT_NAME, " +
											"USER_IND_COLUMNS.DESCEND "+
								" FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS" +
								" WHERE" +
								   " CONSTRAINT_TYPE = 'P' AND " +
								   " DBA_CONSTRAINTS.CONSTRAINT_NAME=USER_IND_COLUMNS.INDEX_NAME" +
								   " AND DBA_CONSTRAINTS.TABLE_NAME = "+bibALL.MakeSql(cPKTables[i][0])+
								   " AND UPPER(OWNER) = UPPER('"+bibE2.cTO()+"')" +
								   		" ORDER BY COLUMN_POSITION";
    		
    		// jetzt die spalten des index mit der position und der richtung
    		String[][] cSpalten = oDB.EinzelAbfrageInArray(cQueryFields,"");
    		   	   		
    		if (cSpalten == null)
    			throw new myException(this,"Error Quering primary-key-fields ...");
 
    		if (cSpalten.length>0)
    		{
    			String cFieldList = "";
    			for (int k=0;k<cSpalten.length;k++)
    			{
    				cFieldList += ","+pdTableBuilder.MakeAFZ(cSpalten[k][1])+" "+cSpalten[k][4];
    			}
    			cFieldList = cFieldList.substring(1);        // erstes komma weg
    			
	    		String cHelp = "ALTER TABLE "+pdTableBuilder.MakeAFZ(cPKTables[i][0])+" ADD PRIMARY KEY ("+cFieldList+ ")";
	    		vCreatePrimaryKeys.add(cHelp);
    		}
    		else
    		{
    			System.out.println(cPKTables[i][0]);
    		}
    		
    	}
    	this.vPrimaryKey.addAll(vCreatePrimaryKeys);
    	// --- ende primary keys
    	
    	
    	
    	// ---- indizes
    	Vector<String> vIndexStatemtens = new Vector<String>();
    	
    	String cQueryIndex = "SELECT " +
    			"DBA_INDEXES.INDEX_NAME, " +
    			"DBA_INDEXES.TABLE_NAME,"+ 
    			"DBA_INDEXES.UNIQUENESS," +
    			"USER_IND_COLUMNS.COLUMN_NAME,"+
    			"USER_IND_COLUMNS.COLUMN_POSITION,"+
    			"USER_IND_COLUMNS.DESCEND"+
    			" FROM SYS.DBA_INDEXES,SYS.USER_IND_COLUMNS"+
    			" WHERE  DBA_INDEXES.OWNER ='"+bibE2.cTO()+"'"+
    			" AND    DBA_INDEXES.INDEX_NAME=USER_IND_COLUMNS.INDEX_NAME"+
    			" AND   DBA_INDEXES.INDEX_NAME NOT IN"+ 
    			"("+    
    			"SELECT      DBA_CONSTRAINTS.CONSTRAINT_NAME"+
    			" FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS"+
    			" WHERE       DBA_CONSTRAINTS.CONSTRAINT_TYPE = 'P'"+
    			" AND    DBA_CONSTRAINTS.CONSTRAINT_NAME=USER_IND_COLUMNS.INDEX_NAME"+
    			" AND   UPPER(DBA_CONSTRAINTS.OWNER) = UPPER('"+bibE2.cTO()+"')"+
    			")"+
    			" ORDER BY DBA_INDEXES.INDEX_NAME,USER_IND_COLUMNS.COLUMN_POSITION";
	    	
    	String[][] cIndexTables = oDB.EinzelAbfrageInArray(cQueryIndex, "");
    	if (cIndexTables == null)
			throw new myException(this,"Error Quering index-tables ...");
    	
    	//zuerst einen vector mit den indexnamen fuellen
    	Vector<String> vNamenIndex = new Vector<String>();
    	for (int i=0;i<cIndexTables.length;i++)
    	{
    		if (!vNamenIndex.contains(cIndexTables[i][0]))
    			vNamenIndex.add(cIndexTables[i][0]);
    	}
    	
    	
    	for (int i=0;i<vNamenIndex.size();i++)
    	{
    		String cHelp = "CREATE ";
    		boolean bStart = true;
    		for (int l=0;l<cIndexTables.length;l++)
    		{
    			if (cIndexTables[l][0].equals((String)vNamenIndex.get(i)))
    			{
    				if (bStart)   // erste spalte
    				{
    					cHelp +=" "+(cIndexTables[l][2].equals("UNIQUE")?"UNIQUE":"")+" INDEX "+
    										pdTableBuilder.MakeAFZ(cIndexTables[l][0])+" ON "+
    										pdTableBuilder.MakeAFZ(cIndexTables[l][1])+" (";
    					bStart = false;
    				}
    				cHelp += pdTableBuilder.MakeAFZ(cIndexTables[l][3])+" "+cIndexTables[l][5]+",";
    			}
    		}
    		cHelp = cHelp.substring(0,cHelp.length()-1)+")";
    		vIndexStatemtens.add(cHelp);
    	}
    	this.vTableIndex.addAll(vIndexStatemtens);
    	//---ende indizes
    	

    	
    	//----- foreign keys
    	// bsp: ALTER TABLE  "JD_TEST" ADD CONSTRAINT "JT_TEST_JT_HAUPTMEN" FOREIGN KEY  ("ID_HAUPTMENUE") REFERENCES "JD_HAUPTMENUE" ("ID_HAUPTMENUE")  
    	Vector<String> vFKStatements = new Vector<String>();
    	
    	String cQueryFK = "SELECT "+
					    	"DBA_CONSTRAINTS.CONSTRAINT_NAME,"+   			//0
					    	"DBA_CONSTRAINTS.TABLE_NAME,"+					//1
					    	"DBA_CONSTRAINTS.DELETE_RULE,"+					//2
					    	"USER_IND_COLUMNS.TABLE_NAME AS REF_TABLE,"+	//3
					    	"USER_IND_COLUMNS.COLUMN_NAME AS REF_COLUMN,"+	//4
					    	"DBA_CONS_COLUMNS.COLUMN_NAME AS OWN_COLUMN "+	//5
				    	" FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS,SYS.DBA_CONS_COLUMNS  " +
				    	" WHERE "+
					    	"USER_IND_COLUMNS.INDEX_NAME=DBA_CONSTRAINTS.R_CONSTRAINT_NAME AND "+
					    	"DBA_CONSTRAINTS.CONSTRAINT_NAME=DBA_CONS_COLUMNS.CONSTRAINT_NAME AND "+
					    	" DBA_CONSTRAINTS.OWNER ='"+bibE2.cTO()+"' AND DBA_CONSTRAINTS.CONSTRAINT_TYPE='R'";
    	
    	String[][] cFK = oDB.EinzelAbfrageInArray(cQueryFK, "");
    	if (cFK == null)
			throw new myException(this,"Error Quering foreign-keys ...");
    	
    	for (int i=0;i<cFK.length;i++)
    	{
    		String cHelp = "ALTER TABLE "+	pdTableBuilder.MakeAFZ(cFK[i][1])+" ADD CONSTRAINT "+
    										pdTableBuilder.MakeAFZ(cFK[i][0])+" FOREIGN KEY ("+
   											pdTableBuilder.MakeAFZ(cFK[i][5])+") REFERENCES "+
   											pdTableBuilder.MakeAFZ(cFK[i][3])+" ("+
   											pdTableBuilder.MakeAFZ(cFK[i][5])+")"+
   											(cFK[i][2].equals("NO ACTION")?"":" "+cFK[i][2]);
   											
    		vFKStatements.add(cHelp);
    	}
    	this.vForeignKey.addAll(vFKStatements);
    	//---- ende foreign keys

    	
    	
    	//-- views
    	// bsp: CREATE OR REPLACE  VIEW V1_ADRESSE_GESCHENK AS SELECT * FROM JT_ADRESSE_GESCHENK WHERE ID_MANDANT=1
    	Vector<String> vVIEWStatements = new Vector<String>();
    	
    	String cQueryVIEW = "SELECT "+
					    	"VIEW_NAME,"+   			//0
					    	"TEXT"+						//1
				    	" FROM SYS.USER_VIEWS  ORDER BY VIEW_NAME";
    	
    	String[][] cVIEW = oDB.EinzelAbfrageInArray(cQueryVIEW, "");
    	if (cVIEW == null)
			throw new myException(this,"Error Quering views ...");
    	
    	for (int i=0;i<cVIEW.length;i++)
    	{
    		String cHelp = "CREATE OR REPLACE  VIEW "+	pdTableBuilder.MakeAFZ(cVIEW[i][0])+"AS "+cVIEW[i][1];
    		vVIEWStatements.add(cHelp);
    	}
    	this.vViews.addAll(vVIEWStatements);
    	// -- ENDE views
    	
    	
    	
    	
    	//---- sequences
    	// bsp:  CREATE SEQUENCE "SEQ_1_GUTSCHRIFT" INCREMENT BY 1  START WITH 4206000019  MAXVALUE 9999999999   MINVALUE 1  NOCYCLE 
    	Vector<String> vSEQStatements = new Vector<String>();
    	
    	String cQuerySEQ = "SELECT " +
    							"SEQUENCE_NAME," +   	// 0
    							"MIN_VALUE," +			// 1
    							"MAX_VALUE," +			// 2
    							"INCREMENT_BY," +		// 3
    							"LAST_NUMBER " +		// 4
    						" FROM ALL_SEQUENCES WHERE SEQUENCE_OWNER='"+bibE2.cTO()+"'";
    	
    	String[][] cSEQ = oDB.EinzelAbfrageInArray(cQuerySEQ, "");
    	if (cSEQ == null)
			throw new myException(this,"Error Quering sequences ...");
    	
    	for (int i=0;i<cSEQ.length;i++)
    	{
    		String cHelp = "CREATE SEQUENCE "+	pdTableBuilder.MakeAFZ(cSEQ[i][0])+
    									" INCREMENT BY "+cSEQ[i][3]+
    									" START WITH "+cSEQ[i][4]+
    									" MAXVALUE "+cSEQ[i][2]+
    									" MINVALUE "+cSEQ[i][1]+
    									" NOCYCLE ";
    		vSEQStatements.add(cHelp);
    	}
    	this.vSequences.addAll(vSEQStatements);
    	// -- ENDE sequences

    	
	    /*
	     * info oracle:
	     * Tabellen stehen in SYS.USER_TABLES
	     * Spalten stehen in SYS.USER_TAB_COLUMNS
	     * Indices stehen in SYS.USER_IND_COLUMNS
	     * views stehen in SYS.USER_VIEWS
	     * 
	     * 
	     * Foreign-key
	     * in oracle: Create table : zuerst <default '1' not null> statt umgekehrt
	     * (sapdb form: ALTER TABLE  "JD_BUTTON_USER" ADD FOREIGN KEY "JD_BUTTON_USER_FK1" ("ID_USER") REFERENCES "JD_USER" ("ID_USER") ON DELETE CASCADE)
	     * 
	     * oracle
	     * ALTER TABLE  JT_ARTIKEL ADD CONSTRAINT FK_JT_ARTIKEL_1 FOREIGN KEY (ID_EINHEIT) REFERENCES JT_EINHEIT(ID_EINHEIT) ON DELETE CASCADE
	     * ALTER TABLE  JT_ARTIKEL ADD CONSTRAINT FK_JT_ARTIKEL_1 FOREIGN KEY (ID_EINHEIT) REFERENCES JT_EINHEIT(ID_EINHEIT) <ON DELETE NO ACTION>  (standard wird nicht mit uebergeben)     
	     * ALTER TABLE  JT_ARTIKEL ADD CONSTRAINT FK_JT_ARTIKEL_1 FOREIGN KEY (ID_EINHEIT) REFERENCES JT_EINHEIT(ID_EINHEIT) ON DELETE SET NULL
	     * 
	     * statt: on delete restrict : on delete no action

					// abfrage der contraints
					select 	c.OWNER,
						c.TABLE_NAME,
						c.CONSTRAINT_NAME,
						cc.COLUMN_NAME,
						r.TABLE_NAME AS REF_TABLE,
						rc.COLUMN_NAME AS REF_COLUMN,
						cc.POSITION,
					        c.DELETE_RULE
					from 	dba_constraints c, 
						dba_constraints r, 
						dba_cons_columns cc, 
						dba_cons_columns rc
					where 	c.CONSTRAINT_TYPE = 'R'
					and 	c.OWNER = 'LEBER'
					and 	c.R_OWNER = r.OWNER
					and 	c.R_CONSTRAINT_NAME = r.CONSTRAINT_NAME
					and 	c.CONSTRAINT_NAME = cc.CONSTRAINT_NAME
					and 	c.OWNER = cc.OWNER
					and 	r.CONSTRAINT_NAME = rc.CONSTRAINT_NAME
					and 	r.OWNER = rc.OWNER
					and 	cc.POSITION = rc.POSITION
					order 	by c.OWNER, c.TABLE_NAME, c.CONSTRAINT_NAME, cc.POSITION
			
			
			   	
			        -- abfrage der primary keys
			    	SELECT      dba_constraints.table_name,user_ind_columns.column_name,user_ind_columns.column_position
			    	FROM sys.dba_constraints,sys.user_ind_columns
			    	WHERE       constraint_type = 'P'
			    	and    dba_constraints.constraint_name=user_ind_columns.index_name
			    	AND   upper(owner) = upper('leber')
			    	ORDER BY table_name
			
					-- abfrage der indizes (ausser pks)
					
			SELECT      DBA_INDEXES.INDEX_NAME, DBA_INDEXES.TABLE_NAME, 
			            DBA_INDEXES.UNIQUENESS,USER_IND_COLUMNS.COLUMN_NAME,
			            USER_IND_COLUMNS.COLUMN_POSITION,
			            USER_IND_COLUMNS.DESCEND
			      FROM SYS.DBA_INDEXES,SYS.USER_IND_COLUMNS
			      WHERE  DBA_INDEXES.OWNER ='LEBER'
			and    DBA_INDEXES.INDEX_NAME=user_ind_columns.index_name
			AND   DBA_INDEXES.INDEX_NAME NOT IN 
			(    
				SELECT      dba_constraints.CONSTRAINT_NAME
			    	FROM sys.dba_constraints,sys.user_ind_columns
			    	WHERE       dba_constraints.constraint_type = 'P'
			    	and    dba_constraints.constraint_name=user_ind_columns.index_name
			    	AND   upper(dba_constraints.owner) = upper('leber')
			)
			ORDER BY DBA_INDEXES.TABLE_NAME;
			
			
	     */
    	
    	/*
    	 * kleine hilfe:
    	 * Script zum loeschen aller benutzertabellen
    	 * select 'drop table '||TABLE_NAME||';'  from SYS.USER_TABLES
    	 */
    	
    }
    
    
    private class hmFieldDEF extends VectorDataBaseQuery
    {

		public hmFieldDEF(String cTABLE_NAME) throws myException 
		{
			super("TABLE_NAME,COLUMN_NAME,DATA_TYPE,CHAR_LENGTH,NULLABLE,DATA_PRECISION,DATA_SCALE,DATA_DEFAULT ", 
					" SYS.USER_TAB_COLUMNS", "", "TABLE_NAME="+bibALL.MakeSql(cTABLE_NAME),
					"COLUMN_NAME");
			
		}
		
		public String get_cCreateFieldPart(int FieldNum) throws myException
		{
			String cRueck = "";
			
			if 		(DB_META.vDB_TEXT_TYPES.contains(this.get_resultValue("DATA_TYPE", FieldNum)))
			{
				cRueck += pdTableBuilder.MakeAFZ(this.get_resultValue("COLUMN_NAME", FieldNum))+" "+"NVARCHAR2(";
				cRueck += this.get_resultValue("CHAR_LENGTH", FieldNum)+")";

			}
			else if (DB_META.vDB_NUMBER_TYPES.contains(this.get_resultValue("DATA_TYPE", FieldNum)))
			{
				cRueck += pdTableBuilder.MakeAFZ(this.get_resultValue("COLUMN_NAME", FieldNum))+" "+"NUMBER(";
				cRueck += this.get_resultValue("DATA_PRECISION", FieldNum);
				cRueck += this.get_resultValue("DATA_SCALE", FieldNum).equals("0")?")":","+this.get_resultValue("DATA_SCALE", FieldNum)+")";
			}
			else if (DB_META.vDB_DATE_TYPES.contains(this.get_resultValue("DATA_TYPE", FieldNum)))
			{
				cRueck += pdTableBuilder.MakeAFZ(this.get_resultValue("COLUMN_NAME", FieldNum))+" "+" DATE";
			}
			else
				throw new myException(this,": FieldTyp not known: "+this.get_resultValue("DATA_TYPE", FieldNum));
			
			
			if (!bibALL.isEmpty(this.get_resultValue("DATA_DEFAULT", FieldNum)))
				cRueck += " DEFAULT "+this.get_resultValue("DATA_DEFAULT", FieldNum);
			
			if (bibALL.null2leer(this.get_resultValue("NULLABLE", FieldNum)).equals("N"))
				cRueck += " NOT NULL ";

			return cRueck;
		}
    	
    	
    }
    
    
    private static String MakeAFZ(String cOrig)
    {
    	String cRueck = cOrig;
    	if (!cRueck.startsWith("\""))
    		cRueck = "\""+cRueck;
    	
    	if (!cRueck.endsWith("\""))
    		cRueck = cRueck+"\"";
    	
    	return cRueck;	
    }
    
    
    
    //  build talble creating definition and index creating definition
    private void buildTableDefSAP() throws myException
    {
        String sResult = "";
        String sPrimaryKey = "";
        
        
        //DriverManager.registerDriver(new com.sap.dbtech.jdbc.DriverSapDB());
        
        // cClassName="oracle.jdbc.driver.OracleDriver";
//        String cClassName="com.sap.dbtech.jdbc.DriverSapDB";

        pdDBToolBox   oDB =  new pdDBToolBox(oConnection);
        
        try
		{
		    
		    Statement stmtTableName = oConnection.get_oConnection().createStatement();
		    Statement stmtColumnName = oConnection.get_oConnection().createStatement();
		    Statement stmtForeignKey = oConnection.get_oConnection().createStatement();
		    Statement stmtTableIndex = oConnection.get_oConnection().createStatement();
		
		    // get all the table name from the system table
		    String sQuery = "SELECT TABLENAME FROM TABLES WHERE OWNER = '" + this.tableOwner.toUpperCase() + "'  and TYPE = 'TABLE' ";
		
		    // get all the table name of the tableOwner
		    ResultSet reTableName = stmtTableName.executeQuery(sQuery);
		
		    while (reTableName.next())
		    {
		        sResult = "CREATE TABLE  \"" + reTableName.getString(1) + "\" ( ";
		        sQuery = "SELECT TABLENAME,COLUMNNAME,MODE,DATATYPE,NULLABLE,LEN,DEC,CODETYPE,\"DEFAULT\" FROM COLUMNS WHERE TABLENAME = '"
		                + reTableName.getString(1) + "' ORDER BY 'KEYPOS' ";
		
		        this.vTablesOfUser.add(reTableName.getString(1));
		
		        ResultSet reColumnName = stmtColumnName.executeQuery(sQuery);
		
		        //			// build primary key vector
		        int iPNumber = 0;
		
		        while (reColumnName.next())
		        {
		            if (reColumnName.getString(3).equals("KEY"))
		            {
		                iPNumber++;
		            }
		        }
		
		        sPrimaryKey = "ALTER TABLE \"" + reTableName.getString(1) + "\"" + " add PRIMARY KEY (\"";
		        reColumnName.beforeFirst();
		
		        while (reColumnName.next())
		        {
		            //								vPrimaryKey.add(reColumnName.getString(3));
		            if (reColumnName.getString(3).equals("KEY"))
		            {
		                iPNumber--;
		
		                if (iPNumber > 0)
		                {
		                    sPrimaryKey += reColumnName.getString(2) + "\",\"";
		                }
		
		                if (iPNumber == 0)
		                {
		                    sPrimaryKey += reColumnName.getString(2) + "\")";
		                    vPrimaryKey.add(sPrimaryKey);
		                }
		            }
		        }
		
		        //			build the whole table def
		        reColumnName.beforeFirst();
		
		        while (reColumnName.next())
		        {
		            sResult += "\"" + reColumnName.getString(2) + "\"  " + reColumnName.getString(4);
		
		            if (reColumnName.getString(4).equalsIgnoreCase("FIXED"))
		            {
		                sResult += "(" + reColumnName.getString(6); // 6 is len 7 is dec
		                sResult += "," + reColumnName.getString(7) + ")  ";
		            } 
		            else if (reColumnName.getString(4).equalsIgnoreCase("VARCHAR") || reColumnName.getString(4).equalsIgnoreCase("CHAR"))
		            {
		                sResult += "(" + reColumnName.getString(6) + ")";
		
		                if (reColumnName.getString(8).equalsIgnoreCase("UNICODE"))
		                {
		                    sResult += " UNICODE ";
		                }
		            }
		
		            if (!this.bMigrate)
		            {
			            if (reColumnName.getString(5).equalsIgnoreCase("NO"))
			            {
			                sResult += " NOT NULL ";
			            }
			
			            if (reColumnName.getString(9) != null)
			            {
			                if (reColumnName.getString(9).toUpperCase().startsWith("DEFAULT"))    // then it is a defaultfunction
			                {
			                    sResult += " " + reColumnName.getString(9) + " ";
			                }
			                else
			                {
			                    sResult += " DEFAULT '" + reColumnName.getString(9) + "'";
			                }
			            }
		            }
		            else           // in oracle ist die reihenfolge default / not null genau gedreht
		            {
			            if (reColumnName.getString(9) != null)
			            {
			                if (reColumnName.getString(9).toUpperCase().startsWith("DEFAULT"))    // then it is a defaultfunction
			                {
			                    sResult += " " + reColumnName.getString(9) + " ";
			                }
			                else
			                {
			                	if (reColumnName.getString(4).equalsIgnoreCase("FIXED"))   // in oracle ohne hochkomma
			                	{
			                		sResult += " DEFAULT " + reColumnName.getString(9) + "";
			                	}
			                	else
			                	{
			                		sResult += " DEFAULT '" + reColumnName.getString(9) + "'";
			                	}
			                }
			            }

			            if (reColumnName.getString(5).equalsIgnoreCase("NO"))
			            {
			                sResult += " NOT NULL ";
			            }
			
		            	
		            }
		
		            sResult += ",";
		        }
		
		        
		        sResult = sResult.substring(0,sResult.length()-1)+")";        // das letzte komma rausschmeissen
		        
		        if (bMigrate)   // dann feldtype austauschen
		        {
                	sResult = pdStringHandler.ReplaceTeilString(sResult,"VARCHAR(","NVARCHAR2(");
                	sResult = pdStringHandler.ReplaceTeilString(sResult,"FIXED(","NUMBER(");
                	sResult = pdStringHandler.ReplaceTeilString(sResult,"TIMESTAMP","DATE");
                	sResult = pdStringHandler.ReplaceTeilString(sResult," CHAR("," NVARCHAR2(");
                	sResult = pdStringHandler.ReplaceTeilString(sResult,"UNICODE"," ");
                	sResult = pdStringHandler.ReplaceTeilString(sResult,"DEFAULT DATE","DEFAULT SYSDATE");
		        	
		        }
		        
		        this.vTableDef.add(sResult);
		
		        sResult = "";
		        sPrimaryKey = "";
		    }
		
		    // build foreignKey clause
		    if (reTableName.first())
		    {
		        do
		        {
		            sQuery = "SELECT TABLENAME,COLUMNNAME,FKEYNAME,REFOWNER,REFTABLENAME,REFCOLUMNNAME,RULE FROM FOREIGNKEYCOLUMNS WHERE TABLENAME = '"
		                    + reTableName.getString(1) + "'";
		
		            ResultSet reForeignkeyName = stmtForeignKey.executeQuery(sQuery);
		
		            while (reForeignkeyName.next())
		            {
		            	String cNAME = "\"" + reForeignkeyName.getString(3) + "\"";
		            	
		                sResult = "ALTER TABLE  \"" + reTableName.getString(1) + "\"" + " ADD FOREIGN KEY "+cNAME+" (\"";
		                sResult += reForeignkeyName.getString(2) + "\") REFERENCES \"" + reForeignkeyName.getString(5);
		                sResult += "\" (\"" + reForeignkeyName.getString(6) + "\") ON " + reForeignkeyName.getString(7);
		                
		                // falls migration, statement manipulieren
		                if (this.bMigrate)
		                {
			                 /*
			                  * (sapdb form: 	ALTER TABLE  JT_ARTIKEL ADD FOREIGN KEY FK_JT_ARTIKEL_1 ("ID_USER") REFERENCES "JD_USER" ("ID_USER") ON DELETE CASCADE)
			                  * 	
			                  *	oracle
			                  * 				ALTER TABLE  JT_ARTIKEL ADD CONSTRAINT FK_JT_ARTIKEL_1 FOREIGN KEY (ID_EINHEIT) REFERENCES JT_EINHEIT(ID_EINHEIT) ON DELETE CASCADE
			                  */
		                	sResult = pdStringHandler.ReplaceTeilString(sResult,"ADD FOREIGN KEY","ADD CONSTRAINT");
		                	sResult = pdStringHandler.ReplaceTeilString(sResult,cNAME,cNAME+" FOREIGN KEY ");
		                	sResult = pdStringHandler.ReplaceTeilString(sResult,"ON DELETE RESTRICT"," ");    // restricion ist in oracle standard und wird nicht mitgegeben   
		                														
		                }
		                
		                vForeignKey.add(sResult);
		            }
		        } while (reTableName.next());
		    }
		
		    // build table index
		    // sQuery = "SELECT TABLENAME,INDEXNAME,TYPE,COLUMNNAME,SORT,LEN FROM
		    // INDEXCOLUMNS";
		    sQuery = "SELECT DISTINCT  TABLENAME,INDEXNAME FROM INDEXCOLUMNS WHERE OWNER = '" + this.tableOwner.toUpperCase()+"'";
		    ResultSet reTableIndex = stmtTableIndex.executeQuery(sQuery);
		    Statement stmtTableIndex2 = oConnection.get_oConnection().createStatement();
		    ResultSet reSet;
		    String sQ;
		    while (reTableIndex.next())
		    {
		        sQ = "SELECT TABLENAME,INDEXNAME,TYPE,COLUMNNAME,SORT,LEN FROM INDEXCOLUMNS where TABLENAME =  '" + reTableIndex.getString(1)+"'"+
		        																			" AND INDEXNAME = '"+reTableIndex.getString(2)+"'"+
		        																			" ORDER BY COLUMNNO";
		        reSet = stmtTableIndex2.executeQuery(sQ);
		        sResult = "";
		        if (reSet.next())
		        {
		            sResult = "CREATE " + reSet.getString(3) + " INDEX " + reSet.getString(2) + " ON " + reSet.getString(1);
		            sResult += " (" + reSet.getString(4) + "  " + reSet.getString(5);
		            while (reSet.next())
		            {
		                sResult += ", \"" + reSet.getString(4) + "\"  " + reSet.getString(5);
		            }
		        }
		        sResult += " ) ";
		        vTableIndex.add(sResult);
		    }

		
		    
		    /*
		     * build views
		     */
		    String sQueryViews = "SELECT VIEWNAME,DEFINITION  FROM VIEWDEFS WHERE OWNER = '"+ this.tableOwner+ "'";
		    Statement stmtViews = oConnection.get_oConnection().createStatement();
		    ResultSet reViews = stmtViews.executeQuery(sQueryViews);
		
		    while (reViews.next())
		    {
		        String cHelp = reViews.getString("DEFINITION");
		        cHelp = pdStringHandler.ReplaceTeilString(cHelp,"CREATE","CREATE OR REPLACE ",true);
			    this.vViews.add(cHelp);
			}
		
		 
		        /*
		     * build sequences
		     */
		    String sQuerySeqs = "SELECT SEQUENCE_NAME,MIN_VALUE,MAX_VALUE,INCREMENT_BY,CYCLE_FLAG,ORDER_FLAG,CACHE_SIZE,  VALUE(LAST_NUMBER,0)+1 AS LAST_NUMBER FROM SEQUENCES WHERE OWNER='"+ this.tableOwner+ "'";
		    Statement stmtSeqs = oConnection.get_oConnection().createStatement();
		    ResultSet reSeqs = stmtSeqs.executeQuery(sQuerySeqs);
		    
		    while (reSeqs.next())
		    {
		        String cCycle = "CYCLE";
		        if (!reSeqs.getString("CYCLE_FLAG").equals("Y")) cCycle = "NOCYCLE";
		        String cOrder = "ORDER";
		        if (!reSeqs.getString("ORDER_FLAG").equals("Y")) cOrder = "NOORDER";
		        String cCache = "CACHE "+reSeqs.getString("CACHE_SIZE");
		        
		        /*
		         * es laesst sich nicht unterscheiden, ob eine seq bereits benutzt wurde (neuer start-value = lastnumber +1)
		         * oder nicht (neuer start-value = lastnumber)
		         * 
		         * <create_sequence_statement> ::=
							  CREATE SEQUENCE [<schema_name>.]<sequence_name>
							    [INCREMENT BY <integer>]
							    [START WITH <integer>]
							    [MAXVALUE <integer> | NOMAXVALUE]
							    [MINVALUE <integer> | NOMINVALUE]
							    [CYCLE | NOCYCLE]
							    [CACHE <unsigned_integer> | NOCACHE]
							    [ORDER|NOORDER]
		         */
		        long lIncrement = reSeqs.getLong("INCREMENT_BY");
		        
		        String cNextVal = oDB.EinzelAbfrage("SELECT "+reSeqs.getString("SEQUENCE_NAME")+".NEXTVAL FROM DUAL");
		        
		        String cSEQ = "CREATE SEQUENCE \"" + reSeqs.getString("SEQUENCE_NAME") + "\""  + 
						      " INCREMENT BY "+lIncrement+" "+
							  " START WITH "+cNextVal+" "+
							  " MAXVALUE "+reSeqs.getLong("MAX_VALUE")+"  "+
							  " MINVALUE "+reSeqs.getLong("MIN_VALUE")+"  "+
							  cCycle+" ";
		        
		        // falls sapdb nach sapdb, dann zusaetze dran
		        if (!this.bMigrate)
		        	cSEQ += cCache+" "+ cOrder+" ";
		        
		        this.vSequences.add(cSEQ);
		
		    }
		

		}
        catch (SQLException ex)
        {
        	ex.printStackTrace();
        	throw new pdException(ex.getLocalizedMessage());
        }
    }

}