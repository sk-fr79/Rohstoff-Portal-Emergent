package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class CG_ActionAgent_Baue_RECORDS_AND_RECORDLISTS extends XX_ActionAgent
{

	private String[][] 		cTables = null;
	private MyE2_Column  	oColOutput = null;
	private String 			path = null; 
	
	
	public CG_ActionAgent_Baue_RECORDS_AND_RECORDLISTS(String[][] tables, MyE2_Column ColOutput)
	{
		super();
		this.cTables = tables;
		this.oColOutput = ColOutput; 
	}



	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		
		int iCountOK = 0;
		int iCountError = 0;
		
		//ausgabepfade anlegen
		File outFolderREC = new File(bibALL.get_CompleteOutPutPath(true)+"DB_RECORDS");
		if (!outFolderREC.exists()) {
			try {
				FileUtils.forceMkdir(outFolderREC);
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new myException("Please clear /../OUTPUT/");
			}
		}
		this.path = bibALL.get_CompleteOutPutPath(true)+"DB_RECORDS"+File.separator;

		
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
		
		for (int i=0;i<this.cTables.length;i++)
		{
			String cErgebnis = this.cTables[i][0];
			
			try
			{
				String[] cCode = this.build_Code_for_RECORDS_AND_RECORDLISTS( this.cTables[i][0]);
				
				String cOutName_RECORD = path+"RECORD_"+this.cTables[i][0].substring(3)+".java";
				File oFileOut_RECORD = new File(cOutName_RECORD);

				String cOutName_RECORD_LIST = path+"RECLIST_"+this.cTables[i][0].substring(3)+".java";
				File oFileOut_RECORD_LIST = new File(cOutName_RECORD_LIST);

				String cOutName_RECORD_NEW = path+"RECORDNEW_"+this.cTables[i][0].substring(3)+".java";
				File oFileOut_RECORD_NEW = new File(cOutName_RECORD_NEW);
				
				
				FileUtils.writeStringToFile(oFileOut_RECORD, cCode[0], "UTF-8");
				FileUtils.writeStringToFile(oFileOut_RECORD_LIST, cCode[1], "UTF-8");
				FileUtils.writeStringToFile(oFileOut_RECORD_NEW, cCode[2], "UTF-8");
				
				cErgebnis+="  *** OK *** ";
				iCountOK++;
				
				
				
				File fTest = new File(cOutName_RECORD);
				if (!fTest.exists())
				{
					DEBUG.System_println(cOutName_RECORD, "");	
				}
				
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				cErgebnis+="  *** ERROR *** ";
				iCountError++;
			}
			
			this.oColOutput.add(new MyE2_Label(new MyE2_String(cErgebnis,false)), E2_INSETS.I_2_2_2_2);

		}
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl erzeugte Klassenfiles: "+iCountOK));
		bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Anzahl NICHT erzeugte Klassenfiles: "+iCountError));
		bibMSG.add_MESSAGE(new MyE2_Info_Message("Stehen in Ordner: "));
		bibMSG.add_MESSAGE(new MyE2_Info_Message(path,false));
	}

	
	private String[] build_Code_for_RECORDS_AND_RECORDLISTS(String cTableName) throws myException
	{
		
		String[] cRueck = new String[3];     //0: RECORD, 1: RECORD_LIST, 2: RECORD_NEW
		
		
		//zuerst eine Dummy-query machen
		String cQueryAnalyse = "SELECT * FROM "+bibE2.cTO()+"."+cTableName+" WHERE ID_"+cTableName.substring(3)+"=-1";
		
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		MyDBResultSet oRS = oDB.OpenResultSet(cQueryAnalyse);
		
		String cTemplate4Records = new RECORD_ResourceStringLoader("RECORD_HEAD").get_cText();
		cTemplate4Records = cTemplate4Records.replace("#TABLEBASE#",cTableName.substring(3));
		cTemplate4Records = cTemplate4Records.replace("#TABLENAME#",cTableName);
		cTemplate4Records = cTemplate4Records.replace("#tablebase#",cTableName.substring(3).toLowerCase());
		cTemplate4Records = cTemplate4Records.replace("#tablename#",cTableName.toLowerCase());
		
		

		String cTemplate4RecordLists = new RECORD_ResourceStringLoader("RECORD__LIST_HEAD").get_cText();
		cTemplate4RecordLists = cTemplate4RecordLists.replace("#TABLEBASE#",cTableName.substring(3));
		cTemplate4RecordLists = cTemplate4RecordLists.replace("#TABLENAME#",cTableName);
		
		String cTemplate4RecordNews = new RECORD_ResourceStringLoader("RECORD__NEW_HEAD").get_cText();
		cTemplate4RecordNews = cTemplate4RecordNews.replace("#TABLEBASE#",cTableName.substring(3));
		cTemplate4RecordNews = cTemplate4RecordNews.replace("#TABLENAME#",cTableName);
		cTemplate4RecordNews = cTemplate4RecordNews.replace("#tablebase#",cTableName.substring(3).toLowerCase());
		cTemplate4RecordNews = cTemplate4RecordNews.replace("#tablename#",cTableName.toLowerCase());
		

		
		StringBuffer cProgrammCodeRecord = new StringBuffer(cTemplate4Records);
		cProgrammCodeRecord.append("\n\n");

		StringBuffer cProgrammCodeRecordList = new StringBuffer(cTemplate4RecordLists);
		cProgrammCodeRecordList.append("\n\n");

		StringBuffer cProgrammCodeRecordNew = new StringBuffer(cTemplate4RecordNews);
		cProgrammCodeRecordNew.append("\n\n");
		
		
		// abfrage, die die matrix der mother-table/field zu daughter-table/field definiert
//		String cQueryKeyZusammenhang = 
//			"SELECT A.TABLE_NAME AS MOTHER_TABLE, CA.COLUMN_NAME AS MOTHER_FIELD, B.TABLE_NAME AS DAUGHTER_TABLE, CB.COLUMN_NAME AS DAUGHTER_FIELD "+
//				" FROM  USER_CONSTRAINTS A ,  USER_CONSTRAINTS B,  USER_CONS_COLUMNS CA, USER_CONS_COLUMNS CB "+  
//				" WHERE  A.CONSTRAINT_NAME=B.R_CONSTRAINT_NAME "+
//				" AND     A.CONSTRAINT_NAME=CA.CONSTRAINT_NAME "+
//				" AND     B.CONSTRAINT_NAME=CB.CONSTRAINT_NAME ";
//		

		/*
		 * aenderung am 20101005: Generierung sortieren
		 */
		String cQueryKeyZusammenhang = 
			"SELECT A.TABLE_NAME AS MOTHER_TABLE, CA.COLUMN_NAME AS MOTHER_FIELD, B.TABLE_NAME AS DAUGHTER_TABLE, CB.COLUMN_NAME AS DAUGHTER_FIELD "+
				" FROM  USER_CONSTRAINTS A ,  USER_CONSTRAINTS B,  USER_CONS_COLUMNS CA, USER_CONS_COLUMNS CB "+  
				" WHERE  A.CONSTRAINT_NAME=B.R_CONSTRAINT_NAME "+
				" AND     A.CONSTRAINT_NAME=CA.CONSTRAINT_NAME "+
				" AND     B.CONSTRAINT_NAME=CB.CONSTRAINT_NAME "+
				" ORDER BY  A.TABLE_NAME,CA.COLUMN_NAME,B.TABLE_NAME,CB.COLUMN_NAME ";

		
		String[][] cFK = bibDB.EinzelAbfrageInArray(cQueryKeyZusammenhang);

		if (cFK == null || cFK.length==0)
			throw new myException("Error queriing the key-Structure !");
		
		
		
		
		if (oRS.RS != null)
		{
			try
			{
				
				// sammelvector fuer mehrfache referenzen mehrer felder in die gleiche tabelle oder aus der gleichen tabellen
				Vector<String> vNamen_DOWN_MAPs = new Vector<String>();
				Vector<String> vNamen_UP_MAPs = new Vector<String>();
				
				for (int i=0;i<cFK.length;i++)
				{
					String cMotherTable = 	cFK[i][0];
					//String cMotherField = 	cFK[i][1];
					String cDaughterTable = cFK[i][2];
					String cDaughterField = cFK[i][3];

					String cDAUGHTERTABLEBASE = cDaughterTable.substring(3);
					String cMOTHERTABLEBASE = cMotherTable.substring(3);
					String cDAUGHTERFIELD   = cDaughterField;
					
					if (cTableName.equals(cMotherTable))
					{
						//falls der name schon vergeben war, dann einen neuen suchen
						//String cNameDownMap = this.Search_free_name(cDAUGHTERTABLEBASE,vNamen_DOWN_MAPs);

						cProgrammCodeRecord.append("\n\n");
//						cProgrammCodeRecord.append("		private RECLIST_"+cDAUGHTERTABLEBASE+" "+"DOWN_RECLIST_"+cNameDownMap+" = null ;\n");
						cProgrammCodeRecord.append("		private RECLIST_"+cDAUGHTERTABLEBASE+" "+"DOWN_RECLIST_"+cDAUGHTERTABLEBASE+"_"+cDAUGHTERFIELD.toLowerCase()+" = null ;\n");
						cProgrammCodeRecord.append("\n\n");
					}


					if (cTableName.equals(cDaughterTable))
					{
						//String cNameUpMap = this.Search_free_name(cMOTHERTABLEBASE,vNamen_UP_MAPs);

						cProgrammCodeRecord.append("\n\n");
//						cProgrammCodeRecord.append("		private RECORD_"+cMOTHERTABLEBASE+" "+"UP_RECORD_"+cNameUpMap+" = null;\n");
						cProgrammCodeRecord.append("		private RECORD_"+cMOTHERTABLEBASE+" "+"UP_RECORD_"+cMOTHERTABLEBASE+"_"+cDAUGHTERFIELD.toLowerCase()+" = null;\n");
						cProgrammCodeRecord.append("\n\n");
					}
					
					
				}
				
				
				vNamen_DOWN_MAPs.removeAllElements();
				vNamen_UP_MAPs.removeAllElements();
				
				//jetzt die mapping-code-bloecke
				for (int i=0;i<cFK.length;i++)
				{
					String cMotherTable = 	cFK[i][0];
					String cMotherField = 	cFK[i][1];
					String cDaughterTable = cFK[i][2];
					String cDaughterField = cFK[i][3];

					String cMOTHERTABLEBASE = cMotherTable.substring(3);
					String cDAUGHTERFIELD   = cDaughterField;
					String cTABLEBASE   	= cTableName.substring(3);
					String cMOTHERFIELD 	= cMotherField;
					String cDAUGHTERTABLEBASE = cDaughterTable.substring(3);
					String cDAUGHTERTABLENAME = cDaughterTable;
					
					if (cTableName.equals(cMotherTable))
					{
						//falls der name schon vergeben war, dann einen neuen suchen
						String cNAME_OF_DAUGHTERMAP = this.Search_free_name(cDAUGHTERTABLEBASE,vNamen_DOWN_MAPs);

						
						cProgrammCodeRecord.append("\n\n");
						
						String cDaughterMappings = new RECORD_ResourceStringLoader("RECORD_DAUGHTER_LISTS").get_cText();
						cDaughterMappings = cDaughterMappings.replace("#NAME_OF_DAUGHTERMAP#", 	cNAME_OF_DAUGHTERMAP);
						cDaughterMappings = cDaughterMappings.replace("#MOTHERTABLEBASE#", 	cMOTHERTABLEBASE);
						cDaughterMappings = cDaughterMappings.replace("#DAUGHTERFIELD#", 	cDAUGHTERFIELD);
						cDaughterMappings = cDaughterMappings.replace("#daughterfield#", 	cDAUGHTERFIELD.toLowerCase());
						cDaughterMappings = cDaughterMappings.replace("#TABLEBASE#", 		cTABLEBASE);
						cDaughterMappings = cDaughterMappings.replace("#MOTHERFIELD#", 		cMOTHERFIELD);
						cDaughterMappings = cDaughterMappings.replace("#motherfield#", 		cMOTHERFIELD.toLowerCase());
						cDaughterMappings = cDaughterMappings.replace("#DAUGHTERTABLEBASE#", cDAUGHTERTABLEBASE);
						cDaughterMappings = cDaughterMappings.replace("#DAUGHTERTABLENAME#", cDAUGHTERTABLENAME);
						
						cProgrammCodeRecord.append(cDaughterMappings);
						cProgrammCodeRecord.append("\n\n");

					}

					if (cTableName.equals(cDaughterTable))
					{
						//falls der name schon vergeben war, dann einen neuen suchen
						String cNAME_OF_MOTHERMAP = this.Search_free_name(cMOTHERTABLEBASE,vNamen_UP_MAPs);

						
						cProgrammCodeRecord.append("\n\n");
						
						String cMotherMappings = new RECORD_ResourceStringLoader("RECORD_MOTHER_RECORDS").get_cText();
						cMotherMappings = cMotherMappings.replace("#NAME_OF_MOTHERMAP#", 	cNAME_OF_MOTHERMAP);
						cMotherMappings = cMotherMappings.replace("#MOTHERTABLEBASE#", 	cMOTHERTABLEBASE);
						cMotherMappings = cMotherMappings.replace("#DAUGHTERFIELD#", 	cDAUGHTERFIELD);
						cMotherMappings = cMotherMappings.replace("#daughterfield#", 	cDAUGHTERFIELD.toLowerCase());
						cMotherMappings = cMotherMappings.replace("#TABLEBASE#", 		cTABLEBASE);
						cMotherMappings = cMotherMappings.replace("#MOTHERFIELD#", 		cMOTHERFIELD);
						cMotherMappings = cMotherMappings.replace("#motherfield#", 		cMOTHERFIELD.toLowerCase());
						cMotherMappings = cMotherMappings.replace("#DAUGHTERTABLEBASE#", cDAUGHTERTABLEBASE);
						cMotherMappings = cMotherMappings.replace("#DAUGHTERTABLENAME#", cDAUGHTERTABLENAME);
						
						cProgrammCodeRecord.append(cMotherMappings);
						cProgrammCodeRecord.append("\n\n");
					}
					
					
				}
				

				// 20101004:  die felder ueber sortierten vector iterieren
				Vector<String> 						vFields = new Vector<String>();
				HashMap<String, MyMetaFieldDEF>  	hmMetaDefs = new HashMap<String, MyMetaFieldDEF>();
				
				for (int i=0;i<oRS.RS.getMetaData().getColumnCount();i++)
				{
					String cFieldName = oRS.RS.getMetaData().getColumnLabel(i+1);
					vFields.add(cFieldName);
					MyMetaFieldDEF oMetaDef = new MyMetaFieldDEF(oRS.RS,i,cTableName);
					hmMetaDefs.put(cFieldName, oMetaDef);
				}
				Collections.sort(vFields);
				
				

				//2011-03-17: public static string fuer feldnamen einfuegen
				for (int i=0;i<vFields.size();i++)
				{
					cProgrammCodeRecord.append("\tpublic static String FIELD__"+vFields.get(i)+" = "+"\""+vFields.get(i)+"\";\n");
				}				
				cProgrammCodeRecord.append("\n\n");
				
				
				//2012-02-03: in den quellcode eine statische hashmap einbauen mit 
				StringBuffer cHashmapFieldsDeclaration = new StringBuffer("\tpublic static HashMap<String, MyRECORD.DATATYPES>  HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {{\n\t");
				
				
				for (int i=0;i<vFields.size();i++)
				{
					String cFieldName = vFields.get(i);
					
					//MyMetaFieldDEF oMetaDef = new MyMetaFieldDEF(oRS.RS,i,cTableName);
					MyMetaFieldDEF oMetaDef = hmMetaDefs.get(cFieldName);
					
					
					//record-code
					String cAllFields_RECORD = new RECORD_ResourceStringLoader("RECORD_STRING_GETTERS").get_cText();
					cAllFields_RECORD = cAllFields_RECORD.replace("#FIELDNAME#",cFieldName);
					cAllFields_RECORD = cAllFields_RECORD.replace("#TABLENAME#",cTableName);
					cAllFields_RECORD = cAllFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));
					cProgrammCodeRecord.append(cAllFields_RECORD);
	
					
					//recordlist-code
					String cAllFields_RECORD_LIST =new RECORD_ResourceStringLoader("RECORD__LIST_ALL_GETTERS").get_cText();
					cAllFields_RECORD_LIST= cAllFields_RECORD_LIST.replace("#FIELDNAME#",cFieldName);
					cAllFields_RECORD_LIST = cAllFields_RECORD_LIST.replace("#TABLENAME#",cTableName);
					cAllFields_RECORD_LIST = cAllFields_RECORD_LIST.replace("#TABLEBASE#",cTableName.substring(3));
					cProgrammCodeRecordList.append(cAllFields_RECORD_LIST);

					
					//record_new-code
					String cAllFields_RECORD_NEW = new RECORD_ResourceStringLoader("RECORD__NEW_SETTERS").get_cText();
					cAllFields_RECORD_NEW = cAllFields_RECORD_NEW.replace("#FIELDNAME#",cFieldName);
					cAllFields_RECORD_NEW = cAllFields_RECORD_NEW.replace("#TABLENAME#",cTableName);
					cAllFields_RECORD_NEW = cAllFields_RECORD_NEW.replace("#TABLEBASE#",cTableName.substring(3));
					cProgrammCodeRecordNew.append(cAllFields_RECORD_NEW);

					

					if (oMetaDef.get_bIsTextType() && oMetaDef.get_FieldTextLENGTH()==1)
					{
						String cBooleanFields_RECORD = new RECORD_ResourceStringLoader("RECORD_BOOLEAN_GETTERS").get_cText();
						cBooleanFields_RECORD = cBooleanFields_RECORD.replace("#FIELDNAME#",cFieldName);
						cBooleanFields_RECORD = cBooleanFields_RECORD.replace("#TABLENAME#",cTableName);
						cBooleanFields_RECORD = cBooleanFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));
						cProgrammCodeRecord.append(cBooleanFields_RECORD);
					}
						
					
					
					// nachschauen, ob es ein feld vom Typ nummer ist
					if (oMetaDef.get_bIsNumberTypeWithOutDecimals())
					{
						//RECORDS schreiben
						String cIntFields_RECORD =new RECORD_ResourceStringLoader("RECORD_LONG_GETTERS").get_cText();
						cIntFields_RECORD= cIntFields_RECORD.replace("#FIELDNAME#",cFieldName);
						cIntFields_RECORD = cIntFields_RECORD.replace("#TABLENAME#",cTableName);
						cIntFields_RECORD = cIntFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));
						
						String cDoubleFields_RECORD = new RECORD_ResourceStringLoader("RECORD_DOUBLE_GETTERS").get_cText();
						cDoubleFields_RECORD =cDoubleFields_RECORD.replace("#FIELDNAME#",cFieldName);
						cDoubleFields_RECORD = cDoubleFields_RECORD.replace("#TABLENAME#",cTableName);
						cDoubleFields_RECORD = cDoubleFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));

						String cBIGDECIMALFields_RECORD = new RECORD_ResourceStringLoader("RECORD_BIGDECIMAL_GETTERS").get_cText();
						cBIGDECIMALFields_RECORD =cBIGDECIMALFields_RECORD.replace("#FIELDNAME#",cFieldName);
						cBIGDECIMALFields_RECORD = cBIGDECIMALFields_RECORD.replace("#TABLENAME#",cTableName);
						cBIGDECIMALFields_RECORD = cBIGDECIMALFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));

						
						cProgrammCodeRecord.append(cIntFields_RECORD);
						cProgrammCodeRecord.append(cDoubleFields_RECORD);
						cProgrammCodeRecord.append(cBIGDECIMALFields_RECORD);
						
						
						//RECORDLISTS schreiben
						String cIntFields_RECORD_LIST =new RECORD_ResourceStringLoader("RECORD__LIST_LONG_GETTERS").get_cText();
						cIntFields_RECORD_LIST= cIntFields_RECORD_LIST.replace("#FIELDNAME#",cFieldName);
						cIntFields_RECORD_LIST = cIntFields_RECORD_LIST.replace("#TABLENAME#",cTableName);
						cIntFields_RECORD_LIST = cIntFields_RECORD_LIST.replace("#TABLEBASE#",cTableName.substring(3));
						
						String cDoubleFields_RECORD_LIST = new RECORD_ResourceStringLoader("RECORD__LIST_DOUBLE_GETTERS").get_cText();
						cDoubleFields_RECORD_LIST =cDoubleFields_RECORD_LIST.replace("#FIELDNAME#",cFieldName);
						cDoubleFields_RECORD_LIST = cDoubleFields_RECORD_LIST.replace("#TABLENAME#",cTableName);
						cDoubleFields_RECORD_LIST = cDoubleFields_RECORD_LIST.replace("#TABLEBASE#",cTableName.substring(3));

						cProgrammCodeRecordList.append(cIntFields_RECORD_LIST);
						cProgrammCodeRecordList.append(cDoubleFields_RECORD_LIST);
						
						
						
						
					}
					
					// nachschauen, ob es ein feld vom Typ nummer, nachkomma ist
					if (oMetaDef.get_bIsNumberTypeWithDecimals())
					{
						//RECORDS schreiben
						String cDoubleFields_RECORD = new RECORD_ResourceStringLoader("RECORD_DOUBLE_GETTERS").get_cText();
						cDoubleFields_RECORD = cDoubleFields_RECORD.replace("#FIELDNAME#",cFieldName);
						cDoubleFields_RECORD = cDoubleFields_RECORD.replace("#TABLENAME#",cTableName);
						cDoubleFields_RECORD = cDoubleFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));
						
						String cBIGDECIMALFields_RECORD = new RECORD_ResourceStringLoader("RECORD_BIGDECIMAL_GETTERS").get_cText();
						cBIGDECIMALFields_RECORD =cBIGDECIMALFields_RECORD.replace("#FIELDNAME#",cFieldName);
						cBIGDECIMALFields_RECORD = cBIGDECIMALFields_RECORD.replace("#TABLENAME#",cTableName);
						cBIGDECIMALFields_RECORD = cBIGDECIMALFields_RECORD.replace("#TABLEBASE#",cTableName.substring(3));

						
						cProgrammCodeRecord.append(cDoubleFields_RECORD);
						cProgrammCodeRecord.append(cBIGDECIMALFields_RECORD);
						
						
						//RECORDLISTS schreiben
						String cDoubleFields_RECORD_LIST = new RECORD_ResourceStringLoader("RECORD__LIST_DOUBLE_GETTERS").get_cText();
						cDoubleFields_RECORD_LIST =cDoubleFields_RECORD_LIST.replace("#FIELDNAME#",cFieldName);
						cDoubleFields_RECORD_LIST = cDoubleFields_RECORD_LIST.replace("#TABLENAME#",cTableName);
						cDoubleFields_RECORD_LIST = cDoubleFields_RECORD_LIST.replace("#TABLEBASE#",cTableName.substring(3));

						cProgrammCodeRecordList.append(cDoubleFields_RECORD_LIST);

					}
					
					//2012-02-03: den statischen typ-hash fortschreiben
					if (oMetaDef.get_bIsTextType() && oMetaDef.get_FieldTextLENGTH()==1)
					{
						cHashmapFieldsDeclaration.append("put(\""+cFieldName+"\",MyRECORD.DATATYPES.YESNO);\n\t");
					}
					else if (oMetaDef.get_bIsNumberTypeWithOutDecimals())
					{
						cHashmapFieldsDeclaration.append("put(\""+cFieldName+"\",MyRECORD.DATATYPES.NUMBER);\n\t");
					}
					else if (oMetaDef.get_bIsNumberTypeWithDecimals())
					{
						cHashmapFieldsDeclaration.append("put(\""+cFieldName+"\",MyRECORD.DATATYPES.DECIMALNUMBER);\n\t");
					}
					else if (oMetaDef.get_bIsDateType())
					{
						cHashmapFieldsDeclaration.append("put(\""+cFieldName+"\",MyRECORD.DATATYPES.DATE);\n\t");
					}
					else
					{
						cHashmapFieldsDeclaration.append("put(\""+cFieldName+"\",MyRECORD.DATATYPES.TEXT);\n\t");
					}
					
				}
				
				//die statische deklaration steht dann am schluss, macht aber nix
				cHashmapFieldsDeclaration.append("}};");

				cProgrammCodeRecord.append("\n\n");
				cProgrammCodeRecord.append(cHashmapFieldsDeclaration);
				cProgrammCodeRecord.append("\n\n");
				
				//2012-02-06: interface MyRecord.ID_RECORD
				String cInterfaceCode= "    @Override \n\tpublic HashMap<String, DATATYPES> get_HM_FIELDNAMES() \n\t{ 		\n\t\treturn RECORD_"+cTableName.substring(3)+".HM_FIELDS;	\n\t}";
				cProgrammCodeRecord.append("\n\n");
				cProgrammCodeRecord.append(cInterfaceCode);
				cProgrammCodeRecord.append("\n\n");

//				//2016-10-27: interface IF_record_record_new_synthese
//				cProgrammCodeRecord.append("\n\n");
//				cProgrammCodeRecord.append("    @Override\n");
//				cProgrammCodeRecord.append("    public RECORD_ADRESSE set_recordNew(RECORDNEW_ADRESSE recnew) throws myException {\n");
//				cProgrammCodeRecord.append("        if (this.is_ExistingRecord()) {\n");
//				cProgrammCodeRecord.append("            throw new myException(this,\"set_recordNew() only allowed, when record is empty\");\n");
//				cProgrammCodeRecord.append("        }\n");
//				cProgrammCodeRecord.append("        this.recNEW=recnew;\n)");
//				cProgrammCodeRecord.append("        return this;\n");
//				cProgrammCodeRecord.append("    }\n");
//				cProgrammCodeRecord.append("\n\n");
//				//-----------------------------------------
				
				cProgrammCodeRecord.append( "}\n");
				cProgrammCodeRecordList.append( "}\n");
				cProgrammCodeRecordNew.append( "}\n");
				
			}
			catch (SQLException ex)
			{
				oRS.Close();
				bibALL.destroy_myDBToolBox(oDB);
				throw new myException("SQLFieldMAP:initFields:SQL-Exception:"+ex.getMessage()+" -->  SQL-Query:"+cQueryAnalyse);
			}
			oRS.Close();
		}
		else
		{
			throw new myException("SQLFieldMAP:initFields:Error opening empty resultSet!   -->  SQL-Query:"+cQueryAnalyse);
		}
		
		cRueck[0]	=	cProgrammCodeRecord.toString();
		cRueck[1]	=	cProgrammCodeRecordList.toString();
		cRueck[2]	=	cProgrammCodeRecordNew.toString();
		
		return cRueck;
	}

	
	private String Search_free_name(String cName_Base, Vector<String> vVergleiche)
	{
		//falls der name schon vergeben war, dann einen neuen suchen
		String cRueck = new String(cName_Base);
		
		for (int k=1;k<100;k++)
		{
			if (! vVergleiche.contains(cRueck))
			{
				vVergleiche.add(cRueck);
				break;
			}
			else
			{
				cRueck = cName_Base+"_"+k;
			}
		}
		
		return cRueck;
	}

}
