package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.exceptions.myException;


public class CG_ActionAgent_Baue_TableSchemaNew extends XX_ActionAgent
{

	private static final String NAMESPACE = "package panter.gmbh.basics4project.records.gen;\n";
	private String[][] 		cTables = null;
	private MyE2_Column  	oColOutput = null;
	
	private static String ERZEUGT_AM = "ERZEUGT_AM";
	private static String ERZEUGT_VON = "ERZEUGT_VON";
	private static String GEANDERT_VON = "GEANDERT_VON";
	private static String LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	private static String ID_MANDANT = "ID_MANDANT";
	
	public CG_ActionAgent_Baue_TableSchemaNew(String[][] tables, MyE2_Column ColOutput)
	{
		super();
		this.cTables = tables;
		this.oColOutput = ColOutput; 
	}


	
	protected String getTableRecordTemplate(String name, String prefOrig, String nameOrig, Vector<String> fields) {
		StringBuffer sb = new StringBuffer(NAMESPACE);
		sb.append("import panter.gmbh.basics4project.records.Record;\n\n");
		sb.append("// THIS CLASS IS GENERATED AUTOMATICALLY. DO NOT EDIT.\n");
		sb.append("public class "+name+"Record extends Record<"+name+"> {\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic String getTableName() {\n");
		sb.append("\t\treturn \""+prefOrig+nameOrig+"\";\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tprotected Class<"+name+"> getRowClass() {\n");
		sb.append("\t\treturn "+name+".class;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tprotected "+name+" getRowInstance() {\n");
		sb.append("\t\treturn new "+name+"();\n");
		sb.append("\t}\n");
		sb.append("}");
		return sb.toString();
	}
	
	protected String getTableTemplate(String name, String prefOrig, String nameOrig, Vector<String> fields) {
		StringBuffer sb = new StringBuffer(NAMESPACE);
		sb.append("import java.util.Arrays;\nimport java.util.List;\n");
		sb.append("import panter.gmbh.basics4project.records.Row;\n\n");
		sb.append("import panter.gmbh.basics4project.records.Column;\n\n");
		sb.append("import panter.gmbh.basics4project.records.Table;\n\n");
		
		sb.append("// THIS CLASS IS GENERATED AUTOMATICALLY. DO NOT EDIT.\n");
		sb.append("public class "+name+" extends Row {\n");
		int fi = 0;
		// Initialize all Fields
		for (String cField: fields) {
			fi++;
			sb.append("\tpublic static final Column "+cField+" = DBTable."+name+"."+cField+";\n");
		}
		sb.append("\n");
		appendColumn(sb, name, "createdOnKey", fields, ERZEUGT_AM);
		appendColumn(sb, name, "createdByKey", fields, ERZEUGT_VON);
		appendColumn(sb, name, "changedOnKey", fields, LETZTE_AENDERUNG);
		appendColumn(sb, name, "changedByKey", fields, GEANDERT_VON);
		appendColumn(sb, name, "principalKey", fields, ID_MANDANT);
		appendColumn(sb, name, "primaryKey", fields, "ID_"+nameOrig);

//		appendColumn(sb, name, "next", null, "SEQ_"+nameOrig+".NEXTVAL");
//		appendColumn(sb, name, "curr", null, "ID_"+nameOrig+".CURRVAL");

		sb.append("\tpublic static final String tablePrefix = \""+prefOrig+"\".intern();\n");
		sb.append("\tpublic static final String tableName = \""+nameOrig+"\".intern();\n");
		sb.append("\tpublic static List<Column> columns = Arrays.asList(");
		fi = 0;
		for (String cField: fields) {
			fi++;
			if (fi > 1) {
				sb.append(", ");
			}
			sb.append(cField);
		}
		sb.append(");\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic "+name+" instance() {\n");
		sb.append("\t\treturn new "+name+"();\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic List<Column> getColumns() {\n");
		sb.append("\t\treturn columns;\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic String getCurrval() {\n");
		sb.append("\t\treturn \"SEQ_"+nameOrig+".CURRVAL\";\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic String getNextval() {\n");
		sb.append("\t\treturn \"SEQ_"+nameOrig+".NEXTVAL\";\n");
		sb.append("\t}\n");
		sb.append("\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic Table getTableName() {\n");
		sb.append("\t\treturn DB."+nameOrig+";\n");
		sb.append("\t}\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic String getTablePrefix() {\n");
		sb.append("\t\treturn \""+prefOrig+"\";\n");
		sb.append("\t}\n");
		sb.append("}");
		return sb.toString();
	}

	private void appendColumn(StringBuffer sb, String tableName, String columnName, Vector<String> fields, String field) {
		if (fields != null) {
			sb.append("\tpublic static final Column ").append(columnName).append(" = ");
			if (fields.contains(field)) {
				sb.append("DBTable.").append(tableName).append(".").append(field).append(";\n");
			} else {
				sb.append("null;\n");
			}
		}
	//	sb.append("\t@Override\n");
		sb.append("\tpublic Column get"+columnName.substring(0,1).toUpperCase()+columnName.substring(1)+"() {\n");
		if (fields != null) {
			sb.append("\t\treturn "+columnName+";\n");
		} else {
			//sb.append()
		}
		sb.append("\t}\n");

	}
	
	



	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		
		/*
		 * erst einen Vector bauen, der alle ID_<TABLE_NAMES> enthaelt.  Wenn in einer tabelle ein solcher 
		 * vorkommt, dann kann ein MAP-Object zur Vater-Tabelle aufgebaut werden
		 */
		Vector<String> vSammleAlleTabellen = new Vector<String>();
		for (int i=0;i<this.cTables.length;i++)
		{
			vSammleAlleTabellen.add(this.cTables[i][0]);
		}
		
		this.oColOutput.removeAll();
		
		
		StringBuffer dbClass= new StringBuffer(NAMESPACE);
		StringBuffer dbTableClass= new StringBuffer(NAMESPACE);
		
		
		String 	cOutName_DBClassFile 		= 	bibALL.get_CompleteOutPutPath(true)+"db/DB.java";
		String 	cOutName_DBTableClassFile 	= 	bibALL.get_CompleteOutPutPath(true)+"db/DBTable.java";
		File 	oFileOut_DBClass 			= 	new File(cOutName_DBClassFile);
		File 	oFileOut_DBTableClass 		= 	new File(cOutName_DBTableClassFile);
		
		
		dbClass.append("import panter.gmbh.basics4project.records.Table;\n\n");
		dbClass.append("\n");
		dbClass.append("public enum DB implements Table\n");
		dbClass.append("{\n");
		
		dbTableClass.append("import panter.gmbh.basics4project.records.Column;\n\n");
		dbTableClass.append("\n");
		dbTableClass.append("public class DBTable\n");
		dbTableClass.append("{\n");
		
		ArrayList<String> alreadyWritten = new ArrayList<String>();
		
		for (int i=0;i<this.cTables.length;i++)
		{
			if (this.cTables[i][0].toUpperCase().startsWith("JT_")||this.cTables[i][0].toUpperCase().startsWith("JD_"))
			{


				
				String cTabLang = this.cTables[i][0];
				String cTabPrefix = this.cTables[i][0].substring(0, 3);
				String cTabKurz = this.cTables[i][0].substring(3);
				String enumName = S.toCamelCase(cTabKurz.toLowerCase()); // +"Column";
				enumName = Character.toUpperCase(enumName.charAt(0)) + enumName.substring(1);
				
				if (alreadyWritten.contains(enumName)) {
					continue;
				}
				alreadyWritten.add(enumName);

				
				//cStaticClass.append("public static final String "+cTabKurz+"=\""+this.cTables[i][0]+"\";\n");
				dbClass.append("\t"+cTabKurz+"(\""+this.cTables[i][0]+"\")");
				if (i == (this.cTables.length - 1)) {
					dbClass.append(";\n\n");
				} else {
					dbClass.append(",\n");
				}
				
				
				Vector<String>  vFields = DB_META.get_ORA_FIELDS(this.cTables[i][0]);

				
				File 	oFileOutR1 = 			new File(bibALL.get_CompleteOutPutPath(true)+"db/"+enumName+"Record.java");
				System.out.println(enumName);
				try {
					FileUtils.writeStringToFile(oFileOutR1, getTableRecordTemplate(enumName, cTabPrefix, cTabKurz, vFields), "UTF-8");
				} catch (Exception e) {System.out.println("EX:"+e.toString());}
				
				dbTableClass.append("\tpublic static enum "+enumName+" implements Column {\n");
				
				//2015-03-10: sequencer in den namespace mitaufnehmen
				//cStaticClass.append("public static final String "+cTabKurz+"$$SEQ=\"SEQ_"+cTabKurz+"\";\n");
				//cStaticClass.append("public static final String "+cTabKurz+"$$SEQ_NEXT=\"SEQ_"+cTabKurz+".NEXTVAL\";\n");
				//cStaticClass.append("public static final String "+cTabKurz+"$$SEQ_CURR=\"SEQ_"+cTabKurz+".CURRVAL\";\n");
				//2015-03-10: ende
				

				
				File 	oFileOutR2 = 			new File(bibALL.get_CompleteOutPutPath(true)+"db/"+enumName+".java");
				try {
					FileUtils.writeStringToFile(oFileOutR2, getTableTemplate(enumName, cTabPrefix, cTabKurz, vFields), "UTF-8");
				} catch (Exception e) {System.out.println("EX:"+e.toString());}

				
				int fi = 0;
				for (String cField: vFields)
				{
					fi++;
					dbTableClass.append("\t\t"+cField+"(\""+cField+"\")");
					if (fi == vFields.size()) {
						dbTableClass.append(";\n\n");
					} else {
						dbTableClass.append(", \n");
					}
				}
				dbTableClass.append("\t\t"+enumName+"(String colName){};\n");
				dbTableClass.append("\t\tpublic static String getTablePrefix(){ return \""+cTabPrefix+"\";}\n");
				dbTableClass.append("\t\tpublic static String getTableSuffix(){ return \""+cTabKurz+"\";}\n");
				if (vFields.contains("ID_"+cTabKurz)) {
					dbTableClass.append("\t\tpublic static Column getPrimaryKey(){ return ID_"+cTabKurz+";}\n");
				} else {
					dbTableClass.append("\t\tpublic static Column getPrimaryKey(){ return null;}\n");
				}
				dbTableClass.append("\t}\n\n");
				
				//2014-01-21: weiterer Static-Block, der ein Feld in der Schreibweise: TABELLE.FELD uebergibt (fuer SQL-Statements)
				for (String cField: vFields)
				{
				//	cStaticClass.append("public static final String "+"Z_"+cTabKurz+"$"+cField+"=\""+cTabLang+"."+cField+"\";\n");
				}
				
				
			}
		}
		dbClass.append("\tDB(String tableName) { }\n ");
		dbClass.append("}\n");

		dbTableClass.append("\n");
		dbTableClass.append("}\n");

		
		try
		{
		
			FileUtils.writeStringToFile(oFileOut_DBClass, dbClass.toString(), "UTF-8");
			FileUtils.writeStringToFile(oFileOut_DBTableClass, dbTableClass.toString(), "UTF-8");
		}
		catch (IOException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error writing file: "+cOutName_DBClassFile));
		}
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message("DB.java is generated !"));
	}



}
