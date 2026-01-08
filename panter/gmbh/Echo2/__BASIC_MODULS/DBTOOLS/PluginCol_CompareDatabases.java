package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PasswordField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorComparer;
import panter.gmbh.indep.myVectors.VectorSingle;

public class PluginCol_CompareDatabases extends Basic_PluginColumn
{
	private MyE2_TextField  		tfCLASSNAME = 			new MyE2_TextField("oracle.jdbc.driver.OracleDriver",600,300);

	private MyE2_TextField  		tfDataBaseURL1 = 		new MyE2_TextField("jdbc:oracle:thin:@<IP>:1521:<DBNAME>",300,200);
	private MyE2_TextField  		tfDataBaseURL2 = 		new MyE2_TextField("jdbc:oracle:thin:@<IP>:1521:<DBNAME>",300,200);
	
	private MyE2_TextField  		oTF_LOGIN1 = 			new MyE2_TextField("",80,30);
	private MyE2_PasswordField  	oTF_PASSWORD1 = 		new MyE2_PasswordField("",80,30);
	private MyE2_TextField  		oTF_LOGIN2 = 			new MyE2_TextField("",80,30);
	private MyE2_PasswordField  	oTF_PASSWORD2 = 		new MyE2_PasswordField("",80,30);


	private MyConnection            oConn1 = null;
	private MyConnection            oConn2 = null;
	
	private MyE2_Button  			oButtonBaueConnection = new MyE2_Button("Prüefe die Verbindung");
	private MyE2_Button  			oButtonCompareTablelist = 	new MyE2_Button("Vergleiche Tabellen");
	private MyE2_Button  			oButtonCompareTablelist2 = 	new MyE2_Button("Vergleiche Tabellen (neu)");
	
	

	
	public PluginCol_CompareDatabases(ContainerForVerwaltungsTOOLS mothercontainer)
	{
		super(mothercontainer);
		
		
		//damit das testen scheller geht
		tfDataBaseURL1 = new MyE2_TextField("jdbc:oracle:thin:@192.168.0.230:1521:orcl",300,200);
		tfDataBaseURL2 = new MyE2_TextField("jdbc:oracle:thin:@192.168.0.147:1521:oraleb",300,200);
		oTF_LOGIN1 = 			new MyE2_TextField("leber_test4",80,30);
		oTF_PASSWORD1 = 			new MyE2_PasswordField("",80,30);
		oTF_LOGIN2 = 			new MyE2_TextField("leber",80,30);
		oTF_PASSWORD2 = 			new MyE2_PasswordField("",80,30);
		//
		
		
		MyE2_Grid  oGrid = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGrid.add(new MyE2_Label(new MyE2_String("Oracle-Datenbank-Vergleich"), MyE2_Label.STYLE_TITEL_BIG()),4,E2_INSETS.I_0_0_10_10);
		
		oGrid.add(new MyE2_Label("Datenbank-Klassenname"),	E2_INSETS.I_0_0_10_10);
		oGrid.add(this.tfCLASSNAME,3,E2_INSETS.I_0_0_10_10);
				
		//ueberschrift
		oGrid.add(new MyE2_Label(""));
		oGrid.add(new MyE2_Label("Datenbank-URL"),	E2_INSETS.I_0_0_10_10);
		oGrid.add(new MyE2_Label("Benutzername"),	E2_INSETS.I_0_0_10_10);
		oGrid.add(new MyE2_Label("Passwort"),		E2_INSETS.I_0_0_10_10);
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Datenbank 1")),E2_INSETS.I_0_0_10_5);
		oGrid.add(tfDataBaseURL1,E2_INSETS.I_0_0_10_5);
		oGrid.add(oTF_LOGIN1,E2_INSETS.I_0_0_10_5);
		oGrid.add(oTF_PASSWORD1,E2_INSETS.I_0_0_10_5);
		
		oGrid.add(new MyE2_Label(new MyE2_String("Datenbank 2")),E2_INSETS.I_0_0_10_5);
		oGrid.add(tfDataBaseURL2,E2_INSETS.I_0_0_10_5);
		oGrid.add(oTF_LOGIN2,E2_INSETS.I_0_0_10_5);
		oGrid.add(oTF_PASSWORD2,E2_INSETS.I_0_0_10_5);
		
		oGrid.add(new E2_ComponentGroupHorizontal(0,oButtonBaueConnection,oButtonCompareTablelist,oButtonCompareTablelist2,E2_INSETS.I_0_0_10_5),4,E2_INSETS.I_0_0_10_5);
		

		this.oButtonBaueConnection.add_oActionAgent(new actionCheckAndBuildConnection());
		this.oButtonCompareTablelist.add_oActionAgent(new actionAgentVergleicheTabellen());
		this.oButtonCompareTablelist2.add_oActionAgent(new actionAgentVergleicheTabellen_V2());
		
		this.add(oGrid, E2_INSETS.I_10_10_10_10);
	}

	
	
	private class actionCheckAndBuildConnection extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			PluginCol_CompareDatabases oThis = PluginCol_CompareDatabases.this;

			if (oThis.tfDataBaseURL1.getText().trim().equals("")||
				oThis.tfDataBaseURL2.getText().trim().equals("") ||
				oThis.oTF_LOGIN1.getText().trim().equals("") ||
				oThis.oTF_PASSWORD1.getText().trim().equals("") ||
				oThis.oTF_LOGIN2.getText().trim().equals("") ||
				oThis.oTF_PASSWORD2.getText().trim().equals("") ||
				oThis.tfCLASSNAME.getText().trim().equals(""))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte füllen Sie die Verbindungsinformationen komplett aus !"), false);
				return;
			}
			
			try
			{
				oThis.oConn1 = new MyConnection(	oThis.tfCLASSNAME.getText().trim(),
													oThis.tfDataBaseURL1.getText().trim(),
													oThis.oTF_LOGIN1.getText().trim(),
													oThis.oTF_PASSWORD1.getText().trim(),
													DB_META.DB_ORA);

				oThis.oConn2 = new MyConnection(	oThis.tfCLASSNAME.getText().trim(),
													oThis.tfDataBaseURL2.getText().trim(),
													oThis.oTF_LOGIN2.getText().trim(),
													oThis.oTF_PASSWORD2.getText().trim(),
													DB_META.DB_ORA);

				oThis.oButtonBaueConnection.set_bEnabled_For_Edit(false);
				oThis.oButtonCompareTablelist.set_bEnabled_For_Edit(true);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Verbindungen 1+2 stehen !")), false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Verbindung hat nicht geklappt !"), false);
			}

		}
	}
	

	
	
	private class actionAgentVergleicheTabellen_V2 extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			PluginCol_CompareDatabases oThis = PluginCol_CompareDatabases.this;

			MyDBToolBox  oDB1 = new MyDBToolBox(oThis.oConn1);
			MyDBToolBox  oDB2 = new MyDBToolBox(oThis.oConn2);
			
			String cUser1 = oThis.oTF_LOGIN1.getText().trim().toUpperCase();
			String cUser2 = oThis.oTF_LOGIN2.getText().trim().toUpperCase();
			
			Vector<String> vTable1 = bibALL.get_VectorAusArrayColumn(oDB1.EinzelAbfrageInArray(DB_META.get_TablesQuery(DB_META.DB_ORA, cUser1 , true, false), false), 0);
			Vector<String> vTable2 = bibALL.get_VectorAusArrayColumn(oDB2.EinzelAbfrageInArray(DB_META.get_TablesQuery(DB_META.DB_ORA, cUser2, true, false), false), 0);
			
			//
			// sammeln der Infos über die Tabellenspalten
			//
			DEBUG.System_println("Ermittle Tabellenspalten...", "");

			
			Hashtable<String, Vector<String>> ht_TableFields1 = new Hashtable<String, Vector<String>>();
			Hashtable<String, Vector<String>> ht_TableFields2 = new Hashtable<String, Vector<String>>();
			
			String sSelectCols = "SELECT C.TABLE_NAME,C.COLUMN_NAME, C.DATA_TYPE, C.CHAR_LENGTH, C.NULLABLE, C.DATA_PRECISION, C.DATA_SCALE, C.DATA_DEFAULT " +
					" FROM SYS.USER_TABLES T INNER JOIN SYS.USER_TAB_COLUMNS C ON T.TABLE_NAME = C.TABLE_NAME ORDER BY  T.TABLE_NAME, C.COLUMN_ID  ";
			

			String[][] cFields1 = oDB1.EinzelAbfrageInArray(sSelectCols,"-");
			String[][] cFields2 = oDB2.EinzelAbfrageInArray(sSelectCols,"-");
			for (int k=0; k<cFields1.length;k++)
			{
				String sTable = cFields1[k][0];
				String sTemp = (cFields1[k][1]+"|"+cFields1[k][2]+"|"+cFields1[k][3]+"|"+cFields1[k][4]+"|"+cFields1[k][5]+"|"+cFields1[k][6]+"|"+bibALL.ReplaceTeilString(cFields1[k][7],"'","").trim()).trim();
				if (ht_TableFields1.containsKey(sTable)){
					Vector<String> v = ht_TableFields1.get(sTable);
					v.add(sTemp);
				}  else {
					Vector<String> vNew = new Vector<String>();
					vNew.add(sTemp);
					ht_TableFields1.put(sTable,vNew);
				}
			}
			
			for (int k=0; k<cFields2.length;k++)
			{
				String sTable = cFields2[k][0];
				String sTemp = (cFields2[k][1]+"|"+cFields2[k][2]+"|"+cFields2[k][3]+"|"+cFields2[k][4]+"|"+cFields2[k][5]+"|"+cFields2[k][6]+"|"+bibALL.ReplaceTeilString(cFields2[k][7],"'","").trim()).trim();
				if (ht_TableFields2.containsKey(sTable)){
					Vector<String> v = ht_TableFields2.get(sTable);
					v.add(sTemp);
				}  else {
					Vector<String> vNew = new Vector<String>();
					vNew.add(sTemp);
					ht_TableFields2.put(sTable,vNew);
				}
			}

			
			//
			// PRIMÄRSCHLÜSSEL
			//
			DEBUG.System_println("Ermittle Primärschlüssel...", "");
			
			String sSelectPrimaryKeys = "SELECT COL.TABLE_NAME, COL.COLUMN_NAME " +
					"	FROM SYS.USER_IND_COLUMNS COL " +
					"	INNER JOIN SYS.DBA_CONSTRAINTS CON ON COL.INDEX_NAME = CON.CONSTRAINT_NAME " +
					"	WHERE CON.CONSTRAINT_TYPE = 'P' " +
					"	ORDER BY CON.TABLE_NAME, COL.INDEX_NAME, COL.COLUMN_POSITION"; 

			cFields1 = oDB1.EinzelAbfrageInArray(sSelectPrimaryKeys,"-");
			cFields2 = oDB2.EinzelAbfrageInArray(sSelectPrimaryKeys,"-");
			
			for (int k=0; k<cFields1.length;k++)
			{
				String sTable = cFields1[k][0];
				String sTemp = "PRIMARY KEY: " +(cFields1[k][1].trim()).trim();
				if (ht_TableFields1.containsKey(sTable)){
					Vector<String> v = ht_TableFields1.get(sTable);
					v.add(sTemp);
				}  else {
					Vector<String> vNew = new Vector<String>();
					vNew.add(sTemp);
					ht_TableFields1.put(sTable,vNew);
				}
			}
			
			for (int k=0; k<cFields2.length;k++)
			{
				String sTable = cFields2[k][0];
				String sTemp = "PRIMARY KEY: " +(cFields2[k][1].trim()).trim();				
				if (ht_TableFields2.containsKey(sTable)){
					Vector<String> v = ht_TableFields2.get(sTable);
					v.add(sTemp);
				}  else {
					Vector<String> vNew = new Vector<String>();
					vNew.add(sTemp);
					ht_TableFields2.put(sTable,vNew);
				}
			}
			
			
			
			//
			// INDEX
			//
			DEBUG.System_println("Ermittle Indizes...", "");

			ObjectHelper  helperIndex1 = new ObjectHelper();
			ObjectHelper  helperIndex2 = new ObjectHelper();
			String sSelectIndex = "SELECT I.INDEX_NAME, I.TABLE_NAME, C.COLUMN_NAME||'-'||C.COLUMN_POSITION||'-'||I.UNIQUENESS||'-'||C.DESCEND " +
					"	FROM SYS.USER_INDEXES  I " +
					"	INNER JOIN SYS.USER_IND_COLUMNS C " +
					"		ON I.INDEX_NAME = C.INDEX_NAME " +
					"	LEFT OUTER JOIN SYS.USER_CONSTRAINTS S " +
					"		ON S.CONSTRAINT_NAME = I.INDEX_NAME " +
					"	WHERE NVL(S.CONSTRAINT_TYPE,'X') != 'P' " +
					"	ORDER BY I.TABLE_NAME, I.INDEX_NAME, C.COLUMN_NAME "; 
			
			
			cFields1 = oDB1.EinzelAbfrageInArray(sSelectIndex,"-");
			cFields2 = oDB2.EinzelAbfrageInArray(sSelectIndex,"-");
			
			for (int k=0; k<cFields1.length;k++)
			{
				String sIndex = cFields1[k][0];
				String sTable = cFields1[k][1];
				String sDesc  = cFields1[k][2];
				
				helperIndex1.add("Index", sTable, sIndex, sDesc);
			}
			
			
			for (int k=0; k<cFields2.length;k++)
			{
				String sIndex = cFields2[k][0];
				String sTable = cFields2[k][1];
				String sDesc  = cFields2[k][2];
				
				helperIndex2.add("Index", sTable, sIndex, sDesc);
			}
			

			//
			// Fremdschlüssel
			//
			DEBUG.System_println("Ermittle Fremdschlüssel...", "");

			String sSelectFKs = "SELECT  COL.CONSTRAINT_NAME,  " +
						" COL.TABLE_NAME, " +
						" COL.CONSTRAINT_NAME || ' * ' || COL.TABLE_NAME || ' - ' || COL.COLUMN_NAME ||' <-> '|| IDX.TABLE_NAME||' - '|| IDX.COLUMN_NAME||' * '|| CON.DELETE_RULE  " +
						" FROM SYS.USER_CONSTRAINTS CON "+
						" INNER JOIN SYS.USER_CONS_COLUMNS COL ON CON.CONSTRAINT_NAME = COL.CONSTRAINT_NAME "+
						" INNER JOIN SYS.USER_IND_COLUMNS IDX ON CON.R_CONSTRAINT_NAME = IDX.INDEX_NAME "+
						" WHERE CON.CONSTRAINT_TYPE='R'  "+
						" ORDER BY CON.CONSTRAINT_NAME,COL.COLUMN_NAME ";
			ObjectHelper  helperFK1 = new ObjectHelper();
			ObjectHelper  helperFK2 = new ObjectHelper();
			
			cFields1 = oDB1.EinzelAbfrageInArray(sSelectFKs,"-");
			cFields2 = oDB2.EinzelAbfrageInArray(sSelectFKs,"-");
			
			for (int k=0; k<cFields1.length;k++)
			{
				String sIndex = cFields1[k][0];
				String sTable = cFields1[k][1];
				String sDesc  = cFields1[k][2];
				
				helperFK1.add("FK", sTable, sIndex, sDesc);
			}
			
			for (int k=0; k<cFields2.length;k++)
			{
				String sIndex = cFields2[k][0];
				String sTable = cFields2[k][1];
				String sDesc  = cFields2[k][2];
				
				helperFK2.add("FK", sTable, sIndex, sDesc);
			}
			
			
			
			
			VectorComparer vComp = new VectorComparer(vTable1,vTable2,true);
			
			Vector<Component> vZusatzComponents = new Vector<Component>();
			
			for (int i=0;i<vComp.get_vBeide().size();i++)
			{
				String cTabName = vComp.get_vBeide().get(i);
				DEBUG.System_println("Table: "+cTabName, "");
				
				
				//jetzt die Felder einlesen und vergleichen

				VectorSingle vFields1 = new VectorSingle();
				VectorSingle vFields2 = new VectorSingle();
				
				
				// 
				//	Spalten + Primärschlüssel
				//
				
				
				
				// die Felder für die Tabelle füllen
				if (ht_TableFields1.containsKey(cTabName)){
					Vector<String> v = ht_TableFields1.get(cTabName);
					for (String s : v){
						vFields1.add(s);
					}
				} 
				
				// die Felder für die Tabelle füllen
				if (ht_TableFields2.containsKey(cTabName)){
					Vector<String> v = ht_TableFields2.get(cTabName);
					for (String s : v){
						vFields2.add(s);
					}
				} 
				
				
				//
				// Indizes
				//
				Vector<String> vIDX1 = helperIndex1.getEntries(cTabName);
				for (String s: vIDX1){
					vFields1.add(s);
				}
				
				Vector<String> vIDX2 = helperIndex2.getEntries(cTabName);
				for (String s: vIDX2){
					vFields2.add(s);
				}
				

				//
				// Foreign Keys
				//
				Vector<String> vFK1 = helperFK1.getEntries(cTabName);
				for (String s: vFK1){
					vFields1.add(s);
				}
				
				Vector<String> vFK2 = helperFK2.getEntries(cTabName);
				for (String s: vFK2){
					vFields2.add(s);
				}
				

				
				
				
				VectorComparer vCompFields = new VectorComparer(vFields1,vFields2,true);
				
				if (vCompFields.get_vFehltIn1().size()==0 && vCompFields.get_vFehltIn2().size()==0)
				{
					vZusatzComponents.add(new MyE2_Label("   <OK>   "));
				}
				else
				{
					vZusatzComponents.add(new buttonPopupForDifferentTables(vCompFields));
				}
			}
			new ownBasicContainerShowTables(new CompareGrid(vComp.get_vBeide(),vComp.get_vFehltIn1(),vComp.get_vFehltIn2(),vZusatzComponents,false));
			
		}
		
	}
	
	
	
	private class actionAgentVergleicheTabellen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			PluginCol_CompareDatabases oThis = PluginCol_CompareDatabases.this;

			MyDBToolBox  oDB1 = new MyDBToolBox(oThis.oConn1);
			MyDBToolBox  oDB2 = new MyDBToolBox(oThis.oConn2);
			
			String cUser1 = oThis.oTF_LOGIN1.getText().trim().toUpperCase();
			String cUser2 = oThis.oTF_LOGIN2.getText().trim().toUpperCase();
			
			Vector<String> vTable1 = bibALL.get_VectorAusArrayColumn(oDB1.EinzelAbfrageInArray(DB_META.get_TablesQuery(DB_META.DB_ORA, cUser1 , true, false), false), 0);
			Vector<String> vTable2 = bibALL.get_VectorAusArrayColumn(oDB2.EinzelAbfrageInArray(DB_META.get_TablesQuery(DB_META.DB_ORA, cUser2, true, false), false), 0);
			
			VectorComparer vComp = new VectorComparer(vTable1,vTable2,true);
			
			Vector<Component> vZusatzComponents = new Vector<Component>();
			
			
			
			
			
			
			for (int i=0;i<vComp.get_vBeide().size();i++)
			{
				String cTabName = vComp.get_vBeide().get(i);
				DEBUG.System_println("Table: "+cTabName, "");
				
				
				//jetzt die Felder einlesen und vergleichen
				String[][] cFields1 = oDB1.EinzelAbfrageInArray(DB_META.get_FieldsQueryOracle(cTabName),"-");
				String[][] cFields2 = oDB2.EinzelAbfrageInArray(DB_META.get_FieldsQueryOracle(cTabName),"-");

				VectorSingle vFields1 = new VectorSingle();
				VectorSingle vFields2 = new VectorSingle();
				
				for (int k=0; k<cFields1.length;k++)
				{
					vFields1.add((cFields1[k][1]+"|"+cFields1[k][2]+"|"+cFields1[k][3]+"|"+cFields1[k][4]+"|"+cFields1[k][5]+"|"+cFields1[k][6]+"|"+bibALL.ReplaceTeilString(cFields1[k][7],"'","").trim()).trim());
				}
				
				for (int k=0; k<cFields2.length;k++)
				{
					vFields2.add((cFields2[k][1]+"|"+cFields2[k][2]+"|"+cFields2[k][3]+"|"+cFields2[k][4]+"|"+cFields2[k][5]+"|"+cFields2[k][6]+"|"+bibALL.ReplaceTeilString(cFields2[k][7],"'","").trim()).trim());
				}
				
				//weitere merkmal als sonderfelder anhaengen
				
				//zuerst primaerschluessel
//				String cQuery = "SELECT  USER_IND_COLUMNS.COLUMN_NAME FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS" +
//													" WHERE CONSTRAINT_TYPE = 'P'" +
//													" AND DBA_CONSTRAINTS.CONSTRAINT_NAME=USER_IND_COLUMNS.INDEX_NAME" +
//													" AND UPPER(OWNER) = 'xxxOWNERxxx' AND DBA_CONSTRAINTS.TABLE_NAME='" +cTabName.toUpperCase()+  "'";
				String cQuery = "SELECT column_name FROM  USER_IND_COLUMNS   " +
				" WHERE index_name = ( " +
				"					SELECT  constraint_name FROM   dba_constraints " +
				"					WHERE  constraint_type = 'P'  " +
				"					and owner       = 'xxxOWNERxxx' " +
				"     				and table_name  = '" +cTabName.toUpperCase() +  "'" +
				" 					) ";
				
				
				vFields1.add("PRIMARY-KEY:  "+oDB1.EinzelAbfrage(bibALL.ReplaceTeilString(cQuery, "xxxOWNERxxx", cUser1)));
				vFields2.add("PRIMARY-KEY:  "+oDB2.EinzelAbfrage(bibALL.ReplaceTeilString(cQuery, "xxxOWNERxxx", cUser2)));
				

				//dann alle indizes einer tabelle ausfalten
				
				// 1. welche indizes hat eine Tabelle ?
//				String cQueryIndices = "SELECT DISTINCT DBA_INDEXES.INDEX_NAME "+  
//								"    	 FROM SYS.DBA_INDEXES,SYS.USER_IND_COLUMNS"+
//								"    	 WHERE  DBA_INDEXES.OWNER ='xxxOWNERxxx'"+
//								"    	 AND    DBA_INDEXES.INDEX_NAME=USER_IND_COLUMNS.INDEX_NAME"+
//								"        AND    UPPER(DBA_INDEXES.TABLE_NAME)="+bibALL.MakeSql(cTabName).toUpperCase()+
//								"    	 AND   DBA_INDEXES.INDEX_NAME NOT IN"+ 
//								"    	("+    
//								"    	SELECT      DBA_CONSTRAINTS.CONSTRAINT_NAME"+
//								"    	 FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS"+
//								"    	 WHERE       DBA_CONSTRAINTS.CONSTRAINT_TYPE = 'P'"+
//								"    	 AND    DBA_CONSTRAINTS.CONSTRAINT_NAME=USER_IND_COLUMNS.INDEX_NAME"+
//								"    	 AND   UPPER(DBA_CONSTRAINTS.OWNER) = UPPER('xxxOWNERxxx')"+
//								"    	)"+
//								"    	ORDER BY DBA_INDEXES.INDEX_NAME";

				
				String cQueryIndices = "SELECT  INDEX_NAME FROM  user_indexes  " +
						"     WHERE  user_indexes.table_name = "+bibALL.MakeSql(cTabName).toUpperCase() +
						"     and table_owner         = 'xxxOWNERxxx' " +
						"     AND index_name         !=    " +
						"		 ( " +
						"     		SELECT 	constraint_name  FROM   dba_constraints " +
						"     		WHERE   constraint_type = 'P' " +
						"         	and 	owner       = 'xxxOWNERxxx' " +
						"         	and 	table_name  = " + bibALL.MakeSql(cTabName).toUpperCase() +
						"     	) " +
						" ORDER BY index_name ";

				String[][] cIndex1 = oDB1.EinzelAbfrageInArray(bibALL.ReplaceTeilString(cQueryIndices, "xxxOWNERxxx", cUser1));
				String[][] cIndex2 = oDB2.EinzelAbfrageInArray(bibALL.ReplaceTeilString(cQueryIndices, "xxxOWNERxxx", cUser2));

					//schleife ueber die indizes einer tabelle und zusammenhaengen der felder
//				String cQueryIndexSpalten = "SELECT  " +
//										"    	USER_IND_COLUMNS.COLUMN_NAME||'-'||"+
//										"    	TO_CHAR(USER_IND_COLUMNS.COLUMN_POSITION)||'-'||"+
//										"   	DBA_INDEXES.UNIQUENESS||'-'||"+ 
//										"    	USER_IND_COLUMNS.DESCEND "+
//										"    	 FROM SYS.DBA_INDEXES,SYS.USER_IND_COLUMNS"+
//										"    	 WHERE  DBA_INDEXES.OWNER ='xxxOWNERxxx'"+
//										"    	 AND    DBA_INDEXES.INDEX_NAME=USER_IND_COLUMNS.INDEX_NAME"+
//										"        AND    UPPER(DBA_INDEXES.INDEX_NAME)= 'xxxINDEXxxx' "+
//										"    	 AND   DBA_INDEXES.INDEX_NAME NOT IN"+ 
//										"    	("+    
//										"    	SELECT      DBA_CONSTRAINTS.CONSTRAINT_NAME"+
//										"    	 FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS"+
//										"    	 WHERE       DBA_CONSTRAINTS.CONSTRAINT_TYPE = 'P'"+
//										"    	 AND    DBA_CONSTRAINTS.CONSTRAINT_NAME=USER_IND_COLUMNS.INDEX_NAME"+
//										"    	 AND   UPPER(DBA_CONSTRAINTS.OWNER) = UPPER('xxxOWNERxxx')"+
//										"    	)"+
//										"    	ORDER BY USER_IND_COLUMNS.COLUMN_POSITION";


				String cQueryIndexSpalten = "SELECT  USER_IND_COLUMNS.COLUMN_NAME||'-'|| " +
						"	TO_CHAR(USER_IND_COLUMNS.COLUMN_POSITION)||'-'|| " +
						"	USER_INDEXES.UNIQUENESS||'-'|| " +
 						"	USER_IND_COLUMNS.DESCEND   " +
						" 	FROM  user_ind_columns INNER JOIN user_indexes ON user_ind_columns.index_name = user_indexes.index_name" +
						" 	WHERE  user_ind_columns.INDEX_name = 'xxxINDEXxxx' " +
						" 	ORDER BY    USER_IND_COLUMNS.COLUMN_POSITION " ;
						
				
				for (int k=0; k<cIndex1.length;k++)
				{
					String cQuery1_1 = bibALL.ReplaceTeilString(bibALL.ReplaceTeilString(cQueryIndexSpalten, "xxxOWNERxxx", cUser1),"xxxINDEXxxx",cIndex1[k][0]);
					String[][] cFelderDefs = oDB1.EinzelAbfrageInArray(cQuery1_1);
					
					String cTextFuerVector = "";
					for (int l=0;l<cFelderDefs.length;l++)
					{
						cTextFuerVector += cFelderDefs[l][0]+" @ ";
					}
					if (S.isFull(cTextFuerVector))
					{
						vFields1.add("INDEX:  "+cTextFuerVector);
						DEBUG.System_println("INDEX(1): "+cTextFuerVector, "");
					}
				}
				
				for (int k=0; k<cIndex2.length;k++)
				{
					String cQuery1_2 = bibALL.ReplaceTeilString(bibALL.ReplaceTeilString(cQueryIndexSpalten, "xxxOWNERxxx", cUser2),"xxxINDEXxxx",cIndex2[k][0]);
					String[][] cFelderDefs = oDB2.EinzelAbfrageInArray(cQuery1_2);
					
					String cTextFuerVector = "";
					for (int l=0;l<cFelderDefs.length;l++)
					{
						cTextFuerVector += cFelderDefs[l][0]+" @ ";
					}
					if (S.isFull(cTextFuerVector))
					{
						vFields2.add("INDEX:  "+cTextFuerVector);
						DEBUG.System_println("INDEX(2): "+cTextFuerVector, "");
					}
				}

				//ende indizes
				
				
				//jetzt alle foreign keys ausfalten
				
				String cQueryFremdkeys = "SELECT "+ 
											" DISTINCT DBA_CONSTRAINTS.CONSTRAINT_NAME,"+
											" DBA_CONS_COLUMNS.COLUMN_NAME AS OWN_COLUMN "+
										" FROM SYS.DBA_CONSTRAINTS,SYS.DBA_CONS_COLUMNS"+ 
										" WHERE " +
										 
										" DBA_CONSTRAINTS.CONSTRAINT_NAME=DBA_CONS_COLUMNS.CONSTRAINT_NAME AND"+ 
										" DBA_CONSTRAINTS.OWNER ='xxxOWNERxxx' AND DBA_CONSTRAINTS.CONSTRAINT_TYPE='R' AND "+
										" UPPER(DBA_CONSTRAINTS.TABLE_NAME)="+bibALL.MakeSql(cTabName).toUpperCase()+
										" ORDER BY DBA_CONS_COLUMNS.COLUMN_NAME";

				
				String[][] cFK1 = oDB1.EinzelAbfrageInArray(bibALL.ReplaceTeilString(cQueryFremdkeys, "xxxOWNERxxx", cUser1));
				String[][] cFK2 = oDB2.EinzelAbfrageInArray(bibALL.ReplaceTeilString(cQueryFremdkeys, "xxxOWNERxxx", cUser2));

				
				
//				String cQueryFremdkeySpalten = "SELECT"+ 
//												" DBA_CONS_COLUMNS.COLUMN_NAME||'-'||"+
//												" USER_IND_COLUMNS.TABLE_NAME||'-'||"+
//												" USER_IND_COLUMNS.COLUMN_NAME||'-'||"+
//												" DBA_CONSTRAINTS.DELETE_RULE "+
//											" FROM SYS.DBA_CONSTRAINTS,SYS.USER_IND_COLUMNS,SYS.DBA_CONS_COLUMNS"+ 
//											" WHERE " +
//											" DBA_CONSTRAINTS.CONSTRAINT_NAME='xxxFREMDKEYxxx' AND "+
//											" USER_IND_COLUMNS.INDEX_NAME=DBA_CONSTRAINTS.R_CONSTRAINT_NAME AND"+ 
//											" DBA_CONSTRAINTS.CONSTRAINT_NAME=DBA_CONS_COLUMNS.CONSTRAINT_NAME AND"+ 
//											" DBA_CONSTRAINTS.OWNER ='xxxOWNERxxx' AND DBA_CONSTRAINTS.CONSTRAINT_TYPE='R'";
				String cQueryFremdkeySpalten = " SELECT    " +
						"	USER_CONS_COLUMNS.COLUMN_NAME||'-'|| " +
						"	USER_IND_COLUMNS.TABLE_NAME||'-'|| " +
						"	USER_IND_COLUMNS.COLUMN_NAME||'-'|| " +
						"	USER_CONSTRAINTS.DELETE_RULE " +
						" 	FROM   SYS.USER_CONSTRAINTS " +
						"			INNER JOIN  SYS.USER_CONS_COLUMNS  ON " +
						" 				USER_CONSTRAINTS.CONSTRAINT_NAME = USER_CONS_COLUMNS.CONSTRAINT_NAME " +
						" 				AND USER_CONSTRAINTS.OWNER       = USER_CONS_COLUMNS.OWNER " +
						" 			INNER JOIN   SYS.USER_IND_COLUMNS ON " +
						" 				USER_IND_COLUMNS.INDEX_NAME = USER_CONSTRAINTS.R_CONSTRAINT_NAME " +
						"	WHERE  USER_CONSTRAINTS.CONSTRAINT_NAME = 'xxxFREMDKEYxxx' " +
						"   AND user_constraints.owner       = 'xxxOWNERxxx' " ;
						
				
				
				for (int k=0; k<cFK1.length;k++)
				{
					String cQuery1_1 = bibALL.ReplaceTeilString(bibALL.ReplaceTeilString(cQueryFremdkeySpalten, "xxxOWNERxxx", cUser1),"xxxFREMDKEYxxx",cFK1[k][0]);
					String[][] cFelderDefs = oDB1.EinzelAbfrageInArray(cQuery1_1);
					
					if (cFelderDefs.length>0)
					{
						vFields1.add("FREMDKEY:  "+cFelderDefs[0][0]);
						DEBUG.System_println("FREMDKEY(1):  "+cFelderDefs[0][0], "");
					}
				}
				
				for (int k=0; k<cFK2.length;k++)
				{
					String cQuery1_2 = bibALL.ReplaceTeilString(bibALL.ReplaceTeilString(cQueryFremdkeySpalten, "xxxOWNERxxx", cUser2),"xxxFREMDKEYxxx",cFK2[k][0]);
					String[][] cFelderDefs = oDB2.EinzelAbfrageInArray(cQuery1_2);
					
					if (cFelderDefs.length>0)
					{
						vFields2.add("FREMDKEY:  "+cFelderDefs[0][0]);
						DEBUG.System_println("FREMDKEY(2):  "+cFelderDefs[0][0], "");
					}
				}
				
				//ende fremdkeys
				
				
				VectorComparer vCompFields = new VectorComparer(vFields1,vFields2,true);
				
				if (vCompFields.get_vFehltIn1().size()==0 && vCompFields.get_vFehltIn2().size()==0)
				{
					vZusatzComponents.add(new MyE2_Label("   <OK>   "));
				}
				else
				{
					vZusatzComponents.add(new buttonPopupForDifferentTables(vCompFields));
				}
			}
			new ownBasicContainerShowTables(new CompareGrid(vComp.get_vBeide(),vComp.get_vFehltIn1(),vComp.get_vFehltIn2(),vZusatzComponents,false));
			
		}
		
	}

	
	
	
	
	
	private class ownBasicContainerShowTables extends E2_BasicModuleContainer
	{

		public ownBasicContainerShowTables(CompareGrid oCompareTables) throws myException
		{
			super();
			this.add(oCompareTables, E2_INSETS.I_10_10_10_10);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Tabellen-Vergleich"));
			
		}
		
	}
	
	
	
	private class buttonPopupForDifferentTables extends MyE2_Button
	{

		private VectorComparer VCompFields = null;
		
		public buttonPopupForDifferentTables(VectorComparer vCompFields)
		{
			super(E2_ResourceIcon.get_RI("error.png"));
			this.VCompFields = vCompFields;
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) 	throws myException
				{
					buttonPopupForDifferentTables oThis = buttonPopupForDifferentTables.this;
					oE2_BasicModuleContainer oBasicContainer = new oE2_BasicModuleContainer();
					
					oBasicContainer.add(new CompareGrid(oThis.VCompFields.get_vBeide(),oThis.VCompFields.get_vFehltIn1(),oThis.VCompFields.get_vFehltIn2(),null,true), E2_INSETS.I_5_5_5_5);
					
					oBasicContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), new MyE2_String("Tabellenvergleich"));
					
				}
			});
		}
		
	}
	

	private class oE2_BasicModuleContainer extends E2_BasicModuleContainer
	{
		public oE2_BasicModuleContainer()
		{
			super();
		}
	}
	
	
	private class CompareGrid extends MyE2_Grid
	{

		public CompareGrid(Vector<String> VBeide,Vector<String> VFehltIn1,Vector<String> VFehltIn2, Vector<Component> vDazwischen, boolean bShownOnlyDifferences) throws myException
		{
			super(vDazwischen==null?2:3, MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());
			
			if (vDazwischen != null && VBeide.size()!=vDazwischen.size())
				throw new myException(this,"VBeide and VDazwischen must have the same size !!");
			
			
			this.add(new MyE2_Label("Objekt 1",MyE2_Label.STYLE_SMALL_BOLD_ITALIC()));
			if (vDazwischen != null) this.add(new MyE2_Label("",MyE2_Label.STYLE_SMALL_BOLD_ITALIC()));
			this.add(new MyE2_Label("Objekt 2",MyE2_Label.STYLE_SMALL_BOLD_ITALIC()));
			
			
			if (!bShownOnlyDifferences)
			{
				for (int i=0;i<VBeide.size();i++)
				{
					this.add(new MyE2_Label(VBeide.get(i)));
					
					if (vDazwischen!=null)
					{
						this.add(vDazwischen.get(i));
					}
					
					this.add(new MyE2_Label(VBeide.get(i)));
				}
			}
			
			for (int i=0;i<VFehltIn1.size();i++)
			{
				if (vDazwischen!=null)
				{
					this.add(new MyE2_Label("?"));
				}
				this.add(new MyE2_Label(""));
				this.add(new MyE2_Label(VFehltIn1.get(i)));
			}
			
			for (int i=0;i<VFehltIn2.size();i++)
			{
				this.add(new MyE2_Label(VFehltIn2.get(i)));
				this.add(new MyE2_Label(""));
				if (vDazwischen!=null)
				{
					this.add(new MyE2_Label("?"));
				}
			}
		}
	}

	
	
	
	/**
	 * 
	 * @author manfred
	 *
	 */
	private class ObjectHelper{
		java.util.LinkedHashMap<String, ColumnHelper> m_ObjectsInfo = new java.util.LinkedHashMap<String, ColumnHelper>();
		
		
		public ObjectHelper( ) {
		}
		 
		 
		public void add(String EntryType, String tablename, String objectname, String value){
			if (m_ObjectsInfo.containsKey(tablename)){
				ColumnHelper o = m_ObjectsInfo.get(tablename);
//				o.add(objectname,  objectname + " - " + value);
				o.add(objectname,  value);
			} else {
				ColumnHelper o = new ColumnHelper(EntryType);
//				o.add(objectname, objectname + " - " + value);
				o.add(objectname,  value);
				m_ObjectsInfo.put(tablename, o);
			}
		}
		
		
		public Vector<String> getEntries( String tablename){
			Vector<String>vRet = new Vector<String>();
			if (m_ObjectsInfo.containsKey(tablename)){
				ColumnHelper o = m_ObjectsInfo.get(tablename);
			    vRet =  o.getColumns();
			}
			return vRet;
			
		}
		
		
		public Vector<String> getObjects( String tablename){
			Vector<String>vRet = new Vector<String>();
			if (m_ObjectsInfo.containsKey(tablename)){
				ColumnHelper o = m_ObjectsInfo.get(tablename);
			    vRet =  o.getColumns();
			}
			return vRet;
			
		}
		
		
		
		/**
		 * 
		 * @author manfred
		 *
		 */
		private class ColumnHelper {
			LinkedHashMap<String, Vector<String>> m_htCols = new LinkedHashMap<String, Vector<String>>();
			
			String m_Description = "";
			
			
			public ColumnHelper( String Description) {
				m_Description = Description;
			}
			
			
			public void add(String key, String value){
				if (m_htCols.containsKey(key)){
					Vector<String> v = m_htCols.get(key);
					v.add(value);
				} else {
					Vector<String> v = new Vector<String>();
					v.add(value);
					m_htCols.put(key, v);
				}
			}
			
			public String getColumns( String key){
				String sRet = "";
				if (m_htCols.containsKey(key)){
					Vector<String> v = m_htCols.get(key);
					sRet = m_Description + v.toString();
				}
				return sRet;
			}
			
			
			
			public Vector<String> getColumns(){
				Vector<String> vRet = new Vector<String>();
				
				for (Iterator it = m_htCols.keySet().iterator(); it.hasNext();) {
					String key = (String)it.next();
					vRet.add( getColumns(key));
				}
				
				return vRet;
			}
			
		}
	}

	
	
	
	
}
