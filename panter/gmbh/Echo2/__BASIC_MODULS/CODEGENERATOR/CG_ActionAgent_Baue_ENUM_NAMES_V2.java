package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.HASHMAP_MyMetaFieldDef;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;


public class CG_ActionAgent_Baue_ENUM_NAMES_V2 extends XX_ActionAgent
{

	private String[][] 		cTables = null;
	private MyE2_Column  	oColOutput = null;
	
	private StringBuffer 	sourceodeTableEnum = null;
	
	//spezieller stringbuffer fuer die definition der abfrage-liste, um aus einer TABLE -enum die felder zu bekommen
	private StringBuffer 	sourceodeTableEnumMethode = null;
	
	//2016-10-25: stringbuffer fuer die definition der korrekten records
	private StringBuffer 	sourceodeTableGenerateNativeRecord = null;

	//2016-10-25: stringbuffer fuer die definition der korrekten records-new
	private StringBuffer 	sourceodeTableGenerateNativeRecordNew = null;

	private String 			path =	null;
	
	private HashMap<String, HASHMAP_MyMetaFieldDef>  hmContainer = new HashMap<String, HASHMAP_MyMetaFieldDef>(); 
	
	public CG_ActionAgent_Baue_ENUM_NAMES_V2(String[][] tables, MyE2_Column ColOutput)
	{
		super();
		this.cTables = tables;
		this.oColOutput = ColOutput; 
	}



	public void executeAgentCode(ExecINFO oExecInfo) throws myException  {
		
		this.sourceodeTableEnum = new StringBuffer();
		this.sourceodeTableEnumMethode = new StringBuffer();
		this.sourceodeTableGenerateNativeRecord = new StringBuffer();
		this.sourceodeTableGenerateNativeRecordNew = new StringBuffer();
		
		File outFolderENUM = new File(bibALL.get_CompleteOutPutPath(true)+"DB_ENUMS");
		if (!outFolderENUM.exists()) {
			try {
				FileUtils.forceMkdir(outFolderENUM);
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new myException("Please clear /../OUTPUT/");
			}
		}	
		this.path = bibALL.get_CompleteOutPutPath(true)+"DB_ENUMS"+File.separator;

		/*
		 * erst einen Vector bauen, der alle ID_<TABLE_NAMES> enthaelt.  Wenn in einer tabelle ein solcher 
		 * vorkommt, dann kann ein MAP-Object zur Vater-Tabelle aufgebaut werden
		 */
		Vector<String> vSammleAlleTabellen = new Vector<String>();
		for (int i=0;i<this.cTables.length;i++)	{
			if (this.cTables[i][0].startsWith("JD_") || this.cTables[i][0].startsWith("JT_")) {
				vSammleAlleTabellen.add(this.cTables[i][0]);
			}
		}
		
		this.oColOutput.removeAll();

		for (String fulltable: vSammleAlleTabellen) {
			
			Vector<String>  vFields = new Vector<String>();
			
			try {
				vFields.addAll(this.get_hmMetaFieldDEF(fulltable).keySet());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (vFields.size()==0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(S.t("Fehlerhafte Tabelle: "),S.ut(fulltable))));
			} else {
			
				this.FIELD_ENUM_GenerateAndWrite(fulltable, vFields);
				
				//sammelt die einzelnen listen zu der enum TABLE
				this.TABLE_add_tableLine(fulltable);
				this.TABLE_add_searchMethodLine(fulltable);
				this.TABLE_add_generateNativeRecordLine(fulltable);
				this.TABLE_add_generateNativeRecordNewLine(fulltable);
				
			}
		}

//		this.TAB_ENUM_generateEnd();
		this.TAB_ENUM_writeFile();
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message("Table-Enums are generated !"));
	}


	
	private void FIELD_ENUM_GenerateAndWrite(String table, Vector<String> vFields) throws myException {
		
		String baseTable = table.toUpperCase().substring(3);
		
//		new File(bibALL.get_CompleteOutPutPath(true)+"DB_RECORDS").mkdir();
//		new File(bibALL.get_CompleteOutPutPath(true)+"DB_ENUMS").mkdir();
		
		
		String 	cOutName_StaticClassFile = 	this.path+baseTable+".java";
		File 	oFileOut_RECORD = 			new File(cOutName_StaticClassFile);
		
		
		StringBuffer sourcecodeFieldEnum= new StringBuffer("");
		sourcecodeFieldEnum.append("package panter.gmbh.basics4project.DB_ENUMS;\n\n");
		sourcecodeFieldEnum.append("\n");
		sourcecodeFieldEnum.append("import panter.gmbh.indep.S;\n");
		sourcecodeFieldEnum.append("import panter.gmbh.indep.dataTools.IF_Field;\n");
		sourcecodeFieldEnum.append("import panter.gmbh.indep.dataTools.TERM.Term;\n");
		sourcecodeFieldEnum.append("import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;\n");
		sourcecodeFieldEnum.append("import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTermList;\n");
		sourcecodeFieldEnum.append("import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;\n");

		sourcecodeFieldEnum.append("import panter.gmbh.indep.dataTools.MyMetaFieldDEF;\n");
		
		sourcecodeFieldEnum.append("\n");
		sourcecodeFieldEnum.append("\n");
		sourcecodeFieldEnum.append("public enum "+baseTable+" implements IF_Field {\n");
		sourcecodeFieldEnum.append("\n");
		sourcecodeFieldEnum.append("\n");
		
		DEBUG.System_println("Tabelle zum Generieren :"+table, DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
		
		Collections.sort(vFields);
		
		for (String field: vFields){
		  try {
			MyMetaFieldDEF md =  this.get_MetaFieldDEF(table, field);
				
			String pkfield = "false";
			if (field.equals("ID_"+table.substring(3))) {
				pkfield = "true";
			}
			
			String cHelpBase = "_TAB."+baseTable.toLowerCase()+".baseTableName()";
			String cHelpFull = "_TAB."+baseTable.toLowerCase()+".fullTableName()";
				
				
			//enum-liste 
			sourcecodeFieldEnum.append(	"     "+
									field.toLowerCase()+"("+
					  				"\""+md.get(MyMetaFieldDEF.KEY_FIELDNAME)+"\""+","+
					  				"\""+md.get(MyMetaFieldDEF.KEY_FIELDLABEL)+"\""+","+
					  				"\""+md.get_FieldType()+"\""+","+
					  				md.get_FieldTextLENGTH()+","+
					  				md.get_FieldNumberLENGTH()+","+
					  				md.get_FieldDecimals()+","+
					  				(md.get_bFieldNullableBasic()?"true":"false")+","+
					  				cHelpFull+","+
					  				cHelpBase+","+
					  				pkfield+
					  				 "),"+"\n");

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sourcecodeFieldEnum.append(";\n");
		
		//hier wird der methodentext zugefuegt
		this.appendLineWise(sourcecodeFieldEnum, this.gen_constuctor_methode(table), "§");
		sourcecodeFieldEnum.append("} \n");

		try	{
			FileUtils.writeStringToFile(oFileOut_RECORD, sourcecodeFieldEnum.toString(), "UTF-8");
		} catch (IOException ex) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error writing file: "+cOutName_StaticClassFile));
		}
		
	}
	
	
	

	private void appendLineWise(StringBuffer targetBuffer, String TextBlock, String LineSeparator) throws myException {
		if (LineSeparator.length()>1) {
			throw new myException(this,"LineSeparator must have length 1");
		}
		ArrayList<String> vLines = S.Separat(TextBlock, LineSeparator.charAt(0));
		
		for (String s: vLines) {
			targetBuffer.append(s+"\n");
		}
	}
	
	
	
	
	
	
	/**
	 * 2014-08-26: serverweiter container mit MyMetaFieldDef-objekten
	 * @param cTable
	 * @param cField
	 * @return
	 * @throws myException
	 */
	public  MyMetaFieldDEF  get_MetaFieldDEF(String cTable, String cField) throws myException {
		
		//dann schauen, ob die tabelle definiert ist
		HASHMAP_MyMetaFieldDef hmDefTable = this.hmContainer.get(cTable);
		if (hmDefTable == null) {
			hmDefTable = new HASHMAP_MyMetaFieldDef(cTable,true);
			hmContainer.put(cTable, hmDefTable);
		}
		
		return hmDefTable.get(cField);
	}


	/**
	 * 2014-08-26: serverweiter container mit MyMetaFieldDef-objekten
	 * @param cTable
	 * @param cField
	 * @return
	 * @throws myException
	 */
	public  HASHMAP_MyMetaFieldDef  get_hmMetaFieldDEF(String cTable) throws myException {
		
		//dann schauen, ob die tabelle definiert ist
		HASHMAP_MyMetaFieldDef hmDefTable = this.hmContainer.get(cTable);
		if (hmDefTable == null) {
			hmDefTable = new HASHMAP_MyMetaFieldDef(cTable,true);
			hmContainer.put(cTable, hmDefTable);
		}

		return hmDefTable;
		
	}

	
	
	
	private String  gen_constuctor_methode(String fullTable) {
		
		String tableBaseName = fullTable.substring(3);
		String tableBaseLower = tableBaseName.toLowerCase();
		
			return 
			"     private String  s_fullTableName = null; §"+
			"     private String  s_baseTableName = null;  §"+
			"     private boolean b_PK = false; §"+
			"     private String  s_fieldName = null; §"+
			"     private String  s_fieldLabel = null; §"+
			"     private String  s_fieldType = null; §"+
			"     private Integer i_fieldTextLENGTH = null; §"+
			"     private Integer i_fieldNumberLENGTH= null;  §"+
			"     private Integer i_fieldDecimals= null; §"+
			"     private Boolean b_fieldNullableBasic= null; §"+
			"§"+
			"     private "+tableBaseName+"( String   p_fieldName,  §"+
			"                     String   p_fieldLabel, §"+
			"                     String   p_fieldType,  §"+
			"                     int      p_fieldTextLENGTH, §"+
			"                     int      p_fieldNumberLENGTH,  §"+
			"                     int      p_fieldDecimals, §"+
			"                     boolean  p_fieldNullableBasic,  §"+
			"                     String   p_fullTableName, §"+
			"                     String   p_baseTableName,  §"+
			"                     boolean  p_PK)  §"+
			"     { §"+
			"         this.s_fieldName =           p_fieldName; §"+
			"         this.s_fieldLabel =          p_fieldLabel; §"+
			"         this.s_fieldType =           p_fieldType; §"+
			"         this.i_fieldTextLENGTH =     p_fieldTextLENGTH; §"+
			"         this.i_fieldNumberLENGTH =   p_fieldNumberLENGTH; §"+
			"         this.i_fieldDecimals =       p_fieldDecimals; §"+
			"         this.b_fieldNullableBasic =  p_fieldNullableBasic; §"+
			"         this.s_fullTableName =       p_fullTableName; §"+
			"         this.s_baseTableName =       p_baseTableName; §"+
			"         this.b_PK =                  p_PK; §"+
			"     } §"
			+"§"
			+"§"
			+"§"
	        +"    public String    fullTableName()          { return s_fullTableName; } §"
			+"    public String    baseTableName()          { return s_baseTableName;} §"
			+"    public boolean   is_PK()                  { return b_PK;} §"
			+"    public String    fieldName()              { return s_fieldName;} §"
			+"    public String    fieldLabel()             { return s_fieldLabel;} §"
			+"    public String    fieldType()              { return s_fieldType;} §"
			+"    public Integer   fieldTextLength()        { return i_fieldTextLENGTH;} §"
			+"    public Integer   fieldNumberLength()      { return i_fieldNumberLENGTH; } §"
			+"    public Integer   fieldDecimals()          { return i_fieldDecimals;} §"
			+"    public Boolean   is_fieldNullableBasic()  { return b_fieldNullableBasic;}  §"
		    +"    /** §"
		    +"     *  §"
		    +"     * @return fieldname without tablealias i.e. NAME1 §"
		    +"     */ §"
		    +"     public String    fn() 		             { return s_fieldName;} §"
		    +"§"     
		    +"     /** §"
		    +"     * @return fieldname with tablealias i.e. <alias>.NAME1 §"
		    +"     */ §"
		    +"     public String    fn(String alias) 		             { return alias+\".\"+s_fieldName;} §"
		    +"§"     
		    +"     /**  §"
		    +"      * @return full-tablename i.e. JT_ADRESSE §"
		    +"      */ §"
		    +"      public String    tn() 		             { return s_fullTableName;} §"
		    +"§"
		    +"     /** §"
		    +"      *  §"
		    +"      * @return full-tablename.fieldname i.e. JT_ADRESSE.NAME1 §"
		    +"      */ §"
		    +"      public String    tnfn() 		         { return s_fullTableName+\".\"+s_fieldName;} §"
			+"§"
			+"    /** §"
			+"     *  §"
			+"     * @return Term: Fieldname,i.e. TABLE.NAME1 §"
			+"     */ §"
			+"    public Term    t()                      { return new FieldTerm(this);}  §"
			+"§"
			+"    /** §"
			+"     *  §"
			+"     * @return  Term: Fieldname with tabAlias, i.e. alias.FIELD §"
			+"     */ §"
			+"    public Term    t(String tabAlias)         {  return new FieldTerm(tabAlias,this);}  §"
			+"§"
			+"§"
			+"    /** §"
			+"     *  §"
			+"     * @return  Term: Fieldname with tableAlias and fieldAlias, i.e. alias.FIELD AS fieldAlias §"
			+"     */ §"
			+"    public Term    t(String tabAlias, String fieldAlias)         {return new FieldTerm(tabAlias,this,fieldAlias);}  §"
			+"§"
			+"§"
			+"    public MyMetaFieldDEF generate_MetaFieldDef() { §"
	        +"       return new MyMetaFieldDEF(s_fullTableName, s_fieldName, s_fieldLabel, s_fieldType, i_fieldTextLENGTH, i_fieldNumberLENGTH, i_fieldDecimals, b_fieldNullableBasic); §"
			+"    } §"
			+"§"
			+" §"
            +"    /**  §"	
            +"     *  §"	
            +"     * @param tableAlias (wenn fuer diese tabelle ein alias definiert wurde)  kann null sein  §"	
            +"     * @param fieldPrefix (wird allen feldern vorangestellt) kann null sein  §"	
            +"     * @return FieldTermList ...  §"	
            +"     */  §"	
            +"     public static FieldTermList gen_FieldList(String tableAlias, String fieldPrefix) { §"	
            +"         FieldTermList  ftlist = new FieldTermList(); §"	
            +"§"	
            +"         for (IF_Field field: "+tableBaseName+".values()) { §"	
            +"            ftlist.add(new FieldTerm( §"	
            +"                       S.isFull(tableAlias)?tableAlias:field.fullTableName(),  §"	
            +"                       field, §"	
            +"                       S.isFull(fieldPrefix)?fieldPrefix+field.fieldName():null)); §"	
            +"§"	
            +"          }   §"	
            +"§"	
            +"          return ftlist; §"	
            +"     } §"	
			+"§"
			+"§"
			+"§"
			+"    public static String fullTabName() { §"
	        +"       return _TAB."+tableBaseLower+".fullTableName(); §"
			+"    } §"
			+" §"
			+" §"
			+"    public static String baseTabName() { §"
	        +"       return _TAB."+tableBaseLower+".baseTableName(); §"
			+"    } §"
			+" §"
			+" §"
			+"    /** §"
			+"     *  §"
			+"     * @return full Tablename, ie "+fullTable+" §"
			+"     */ §"
			+"    public static TableTerm T() { §"
	        +"       return new TableTerm(_TAB."+tableBaseLower+".fullTableName()); §"
			+"    } §"
			+" §"
			+" §"
			+"    /** §"
			+"     *  §"
			+"     * @return Tablename with alias, ie ("+fullTable+" AS tableAlias) §"
			+"     */ §"
			+"    public static TableTerm T(String tableAlias) { §"
	        +"       return new TableTerm(_TAB."+tableBaseLower+".fullTableName(),tableAlias); §"
			+"    } §"
			+" §"
			+" §"
			+" §"
			
//2016-06-21: neue methoden, um an das _TAB-objekt zu kommen			
			+"    /** §"
			+"     *  §"
			+"     * @return _TAB - objekt , ie ("+fullTable+" AS tableAlias) §"
			+"     */ §"
			+"    public static _TAB _tab() { §"
	        +"       return _TAB."+tableBaseLower+"; §"
			+"    } §"
			+" §"
			+" §"
			+" §"
			+"    /** §"
			+"     *  §"
			+"     * @return _TAB - objekt , ie ("+fullTable+" AS tableAlias) §"
			+"     */ §"
			+"    public _TAB _t() { §"
	        +"       return _TAB."+tableBaseLower+"; §"
			+"    } §"
			+" §"
			+" §"
			+" §"
			//2016-06-21: neue methoden, um an das _TAB-objekt zu kommen			
			
			+"    /** §"
			+"     *  §"
			+"     * @return field from fieldname or null §"
			+"     */ §"
			+"    public static IF_Field field(String fieldName) { §"
			+"      IF_Field field_rueck = null; §"
			+" §"
		    +"      for (IF_Field field: "+tableBaseName+".values()) { §"
		    +"         if (field.fieldName().toUpperCase().equals(fieldName.toUpperCase().trim())) { §"
       		+"            field_rueck=field; §"
      		+"         } §"
		    +"       } §"   
		    +"       return field_rueck; §"
		    +"   } §"
			+" §"
			;
			
	}
	
	
	
	
	public void TABLE_add_tableLine(String fullTable) {
		String baseTable = fullTable.toLowerCase().substring(3);
		this.sourceodeTableEnum.append(	"     "+baseTable+"(\""+fullTable+"\""+"),\n");
	}

	public void TABLE_add_searchMethodLine(String fullTable) {
		String baseTable = fullTable.toLowerCase().substring(3);
		this.sourceodeTableEnumMethode.append(	"       if (this.fullTableName.equals(_TAB."+baseTable+".fullTableName)) { return "+baseTable.toUpperCase()+".values(); }; \n");
	}
	
	public void TABLE_add_generateNativeRecordLine(String fullTable) {
		String baseTable = fullTable.toLowerCase().substring(3);
		this.sourceodeTableGenerateNativeRecord.append(	"        if (this.fullTableName.equals(_TAB."+baseTable+".fullTableName)) { return new RECORD_"+baseTable.toUpperCase()+"(); }; \n");
	}
	
	public void TABLE_add_generateNativeRecordNewLine(String fullTable) {
		String baseTable = fullTable.toLowerCase().substring(3);
		this.sourceodeTableGenerateNativeRecordNew.append(	"        if (this.fullTableName.equals(_TAB."+baseTable+".fullTableName)) { return new RECORDNEW_"+baseTable.toUpperCase()+"(); }; \n");
	}
	
	
	
	
	private void TAB_ENUM_writeFile() {
		String 	cOutName_StaticClassFile = 	this.path+"_TAB.java";
		File 	oFileOut_RECORD = 			new File(cOutName_StaticClassFile);
		
		StringBuffer tabBuffer = new StringBuffer();
		tabBuffer = new StringBuffer();
		tabBuffer.append("package panter.gmbh.basics4project.DB_ENUMS;\n\n");
		tabBuffer.append("\n");
		tabBuffer.append("import panter.gmbh.Echo2.bibE2;\n");
		tabBuffer.append("import panter.gmbh.Echo2.RB.BASICS.RB_KM;\n");
		tabBuffer.append("import panter.gmbh.indep.MyLong;\n");

		tabBuffer.append("import panter.gmbh.indep.S;\n");
		tabBuffer.append("import panter.gmbh.indep.dataTools.IF_Field;\n");
		tabBuffer.append("import panter.gmbh.indep.dataTools.MyRECORD;\n");
		tabBuffer.append("import panter.gmbh.indep.exceptions.myException;\n");
		tabBuffer.append("import panter.gmbh.basics4project.DB_RECORDS.*;\n");
		tabBuffer.append("import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;\n");
		tabBuffer.append("import panter.gmbh.indep.dataTools.bibDB;\n");
		tabBuffer.append("import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;\n");

		
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		tabBuffer.append("public enum _TAB {\n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		tabBuffer.append(this.sourceodeTableEnum);               //die tabliste
		
		tabBuffer.append("    ;\n");
		tabBuffer.append("\n");
		tabBuffer.append("    private String     fullTableName = null;\n");
		tabBuffer.append("\n");
		tabBuffer.append("    _TAB(String full_tableName) {\n");
		tabBuffer.append("       this.fullTableName = full_tableName;\n");
		tabBuffer.append("    }\n\n");

		
		
		tabBuffer.append("    ;\n");
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return base-Table-name i.e. \"ADRESSE\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String baseTableName() {\n");
		tabBuffer.append("       return this.fullTableName.substring(3);\n");
		tabBuffer.append("    }\n\n");
		
		//2015-10-12: keyfield generieren
		tabBuffer.append("    ;\n");
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return key-field-name i.e. \"ID_ADRESSE\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String keyFieldName() {\n");
		tabBuffer.append("       return \"ID_\"+this.fullTableName.substring(3);\n");
		tabBuffer.append("    }\n\n");
		//2015-10-12: keyfield generieren
		
		
		//2016-03-22: keyField als field-objekt
		tabBuffer.append("    ;\n");
		tabBuffer.append("    public IF_Field  key() throws myException {\n");
		tabBuffer.append("      IF_Field field = null;\n");
		tabBuffer.append("      for (IF_Field f: this.get_fields()) {\n");
		tabBuffer.append("        if (f.fn().toUpperCase().equals(this.keyFieldName().toUpperCase())) {\n");
		tabBuffer.append("          field = f;\n");
		tabBuffer.append("          break;\n");
		tabBuffer.append("        }\n");
		tabBuffer.append("      }\n");
		tabBuffer.append("      return field;\n");
		tabBuffer.append("      }\n");
		tabBuffer.append("    \n\n");
		
		tabBuffer.append("    ;\n");
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return full-table-name i.e. \"JT_ADRESSE\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String fullTableName() {\n");
		tabBuffer.append("       return this.fullTableName;\n");
		tabBuffer.append("    }\n\n");
		
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return full-table-name i.e. \"JT_ADRESSE\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String n() {\n");
		tabBuffer.append("       return this.fullTableName;\n");
		tabBuffer.append("    }\n\n");
		tabBuffer.append(" \n");
		
		
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return trigger-name i.e. \"TRIGG_ADRESSE\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String triggerName() {\n");
		tabBuffer.append("       return \"TRIGG_\"+this.baseTableName();\n");
		tabBuffer.append("    }\n\n");
		tabBuffer.append(" \n");
		

		tabBuffer.append("    public RB_KM rb_km() throws myException { \n");
		tabBuffer.append("	    return new RB_KM(this); \n");
		tabBuffer.append("    } \n");
		tabBuffer.append(" \n");
		tabBuffer.append(" \n");
		tabBuffer.append("    public RB_KM rb_km(int iCount) throws myException { \n");
		tabBuffer.append("	    return new RB_KM(this,this.n()+\"/\"+iCount); \n");
		tabBuffer.append("    } \n");
		tabBuffer.append(" \n");
		tabBuffer.append(" \n");
	
		
		//2016-04-27: neue methoden fuer sequencen
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return i.e. \"SEQ_ADRESSE\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String seq_name() {  \n");
		tabBuffer.append("    	return \"SEQ_\"+this.baseTableName();  \n");
		tabBuffer.append("    }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");

		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return i.e. \"SEQ_ADRESSE.NEXTVAL\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String seq_nextval() {  \n");
		tabBuffer.append("    	return \"SEQ_\"+this.baseTableName()+\".NEXTVAL\";  \n");
		tabBuffer.append("    }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return i.e. \"SEQ_ADRESSE.CURRVAL\"  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("   public String seq_currval() {  \n");
		tabBuffer.append("    	return \"SEQ_\"+this.baseTableName()+\".CURRVAL\";  \n");
		tabBuffer.append("    }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		
		tabBuffer.append("   /**  \n");
		tabBuffer.append("    *   \n");
		tabBuffer.append("    * @return i.e. \"SELECT SEQ_ADRESSE.NEXTVAL FROM DUAL\"  \n");
		tabBuffer.append("    */  \n");
		tabBuffer.append("   public String sql_nextval() {  \n");
		tabBuffer.append("    	return \"SELECT \" +this.seq_nextval()+\" FROM DUAL\";  \n");
		tabBuffer.append("    }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		
		
		tabBuffer.append("   /**  \n");
		tabBuffer.append("    *   \n");
		tabBuffer.append("    * @return i.e. \"SELECT SEQ_ADRESSE.CURRVAL FROM DUAL\"  \n");
		tabBuffer.append("    */  \n");
		tabBuffer.append("    public String sql_currval() {  \n");
		tabBuffer.append("    	return \"SELECT \" +this.seq_currval()+\" FROM DUAL\";  \n");
		tabBuffer.append("    }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		
		tabBuffer.append("    "+"public static _TAB find_Table(String fullTableName)  throws myException {"+"\n");
		tabBuffer.append("       "+"for (_TAB tab: _TAB.values()) {"+"\n");
		tabBuffer.append("          "+"if (tab.fullTableName.equals(fullTableName)) {"+"\n");
		tabBuffer.append("             "+"return tab;"+"\n");
		tabBuffer.append("          "+"}"+"\n");
		tabBuffer.append("       "+"}"+"\n");
		tabBuffer.append("       "+"throw new myException(\"_TAB.find_Table(): Tablename: \"+fullTableName+\" was not found !\");"+"\n");
		tabBuffer.append("    "+"}"+"\n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");

		
		//20180830: neuer finder, liefer null aber keine exception
		tabBuffer.append("    "+"public static _TAB findTable(String fullTableName) {"+"\n");
		tabBuffer.append("       "+"for (_TAB tab: _TAB.values()) {"+"\n");
		tabBuffer.append("          "+"if (tab.fullTableName.equals(fullTableName)) {"+"\n");
		tabBuffer.append("             "+"return tab;"+"\n");
		tabBuffer.append("          "+"}"+"\n");
		tabBuffer.append("       "+"}"+"\n");
		tabBuffer.append("       "+"return null;"+"\n");
		tabBuffer.append("    "+"}"+"\n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");

		
		
		//20171220: direktes ermitteln von currval und nextval
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return currval of table-sequencer (on error null)  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String getCurrVal() {  \n");
		tabBuffer.append("        String query = this.sql_currval();  \n");
		tabBuffer.append("        String val = bibDB.EinzelAbfrage(query);  \n");
		tabBuffer.append("        if (new MyLong(val).isOK()) {  \n");
		tabBuffer.append("            return val;  \n");
		tabBuffer.append("        }  \n");
		tabBuffer.append("        return null;  \n");
		tabBuffer.append("     }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		    
		    
		tabBuffer.append("    /**  \n");
		tabBuffer.append("     *   \n");
		tabBuffer.append("     * @return nextval of table-sequencer (on error null)  \n");
		tabBuffer.append("     */  \n");
		tabBuffer.append("    public String getNextVal() {  \n");
		tabBuffer.append("        String query = this.sql_nextval();  \n");
		tabBuffer.append("        String val = bibDB.EinzelAbfrage(query);  \n");
		tabBuffer.append("            if (new MyLong(val).isOK()) {  \n");
		tabBuffer.append("                return val;  \n");
		tabBuffer.append("            }  \n");
		tabBuffer.append("        return null;  \n");
		tabBuffer.append("    }  \n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		    

		
		
		//2016-01-14: suche nach baseTableName einfuegen
		tabBuffer.append("    "+"public static _TAB find_TableFromBasename(String baseTableName)  throws myException {"+"\n");
		tabBuffer.append("       "+"for (_TAB tab: _TAB.values()) {"+"\n");
		tabBuffer.append("          "+"if (tab.baseTableName().equals(baseTableName)) {"+"\n");
		tabBuffer.append("             "+"return tab;"+"\n");
		tabBuffer.append("          "+"}"+"\n");
		tabBuffer.append("       "+"}"+"\n");
		tabBuffer.append("       "+"throw new myException(\"_TAB.find_Table(): Base-Tablename: \"+baseTableName+\" was not found !\");"+"\n");
		tabBuffer.append("    "+"}"+"\n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");

		
	    /**
	     * 
	     * @param tab
	     * @param fieldName
	     * @return s IF_Field - object or null
	     * @throws myException
	     */
		tabBuffer.append("    public static IF_Field find_field(_TAB tab, String fieldName) throws myException {"+"\n");
		tabBuffer.append("        IF_Field[] fields = tab.get_fields();"+"\n");
		tabBuffer.append("\n");
		tabBuffer.append("        for (IF_Field f: fields) {"+"\n");
		tabBuffer.append("            if (f.fieldName().equalsIgnoreCase(fieldName)) {"+"\n");
		tabBuffer.append("              return f;"+"\n");
		tabBuffer.append("            }"+"\n");
		tabBuffer.append("        }"+"\n");
		tabBuffer.append("\n");
		tabBuffer.append("        return null;"+"\n");
		tabBuffer.append("    }"+"\n");
		tabBuffer.append("\n");
		tabBuffer.append("\n");
	     
		
		
		
		//jetzt die if-then-bloecke einbauen (via zusammengesetzte methode)
		tabBuffer.append("    public IF_Field[] get_fields() throws myException {\n");
		tabBuffer.append(this.sourceodeTableEnumMethode);
		tabBuffer.append("    throw new myException(this,this.name()+\": fieldsEnum was not found !\");\n");
		tabBuffer.append("    }\n\n\n\n");
		//ende die if-then-bloecke einbauen (via zusammengesetzte methode)

		//2016-10-25: nativ-records erzeugen
		//jetzt die if-then-bloecke einbauen (via zusammengesetzte methode)
		tabBuffer.append("    public MyRECORD_IF_RECORDS nativeRecord() throws myException {\n");
		tabBuffer.append(this.sourceodeTableGenerateNativeRecord);
		tabBuffer.append("    throw new myException(this,this.name()+\": Table was not found !\");\n");
		tabBuffer.append("    }\n\n\n\n");
		//ende die if-then-bloecke einbauen (via zusammengesetzte methode)

		//2016-10-25: nativ-records-news erzeugen
		//jetzt die if-then-bloecke einbauen (via zusammengesetzte methode)
		tabBuffer.append("    public MyRECORD_IF_FILLABLE nativeRecordNEW() throws myException {\n");
		tabBuffer.append(this.sourceodeTableGenerateNativeRecordNew);
		tabBuffer.append("    throw new myException(this,this.name()+\": Table was not found !\");\n");
		tabBuffer.append("    }\n\n\n\n");
		//ende die if-then-bloecke einbauen (via zusammengesetzte methode)
	
		
		//2016-01-07: neue statische methoden
		tabBuffer.append("\n");
		tabBuffer.append("\n");
		tabBuffer.append("    public  MyRECORD get_RECORD() throws myException { \n");
		tabBuffer.append("     \n");
		tabBuffer.append("        try { \n");
		tabBuffer.append("            @SuppressWarnings(\"rawtypes\") \n");
		tabBuffer.append("            Class c = Class.forName(\"panter.gmbh.basics4project.DB_RECORDS.RECORD_\"+this.baseTableName()); \n");
		tabBuffer.append("            return (MyRECORD)c.newInstance(); \n");
		tabBuffer.append("            } catch (ClassNotFoundException e) { \n");
		tabBuffer.append("                e.printStackTrace(); \n");
		tabBuffer.append("                new myException(\"Class: \"+\"RECORD_\"+this.baseTableName()+\" can not be found !!!\"); \n");
		tabBuffer.append("            } catch (InstantiationException e) { \n");
		tabBuffer.append("                e.printStackTrace(); \n");
		tabBuffer.append("                new myException(\"Class: \"+\"RECORD_\"+this.baseTableName()+\" can not be found !!!\"); \n");
		tabBuffer.append("            } catch (IllegalAccessException e) { \n");
		tabBuffer.append("                e.printStackTrace(); \n");
		tabBuffer.append("                new myException(\"Class: \"+\"RECORD_\"+this.baseTableName()+\" can not be found !!!\"); \n");
		tabBuffer.append("            }  \n");
		tabBuffer.append("            return null; \n");
		tabBuffer.append("         } \n");
		tabBuffer.append("     \n");
		tabBuffer.append("     \n");
		tabBuffer.append("    public MyRECORD get_RECORD(String id) throws myException { \n");
		tabBuffer.append("        MyRECORD rec = this.get_RECORD(); \n");
		tabBuffer.append("        if (S.isFull(id)) { \n");
		tabBuffer.append("            rec.PrepareAndBuild(\"*\", bibE2.cTO()+\".\"+this.fullTableName, this.keyFieldName()+\"=\"+id); \n");
		tabBuffer.append("        } \n");
		tabBuffer.append("        return rec; \n");
		tabBuffer.append("    } \n");
		tabBuffer.append("     \n");
		tabBuffer.append("    @SuppressWarnings(\"rawtypes\") \n");
		tabBuffer.append("    public  Class get_RECORD_Class() throws myException { \n");
		tabBuffer.append("        try { \n");
		tabBuffer.append("            Class c = Class.forName(\"panter.gmbh.basics4project.DB_RECORDS.RECORD_\"+this.baseTableName()); \n");
		tabBuffer.append("            return c; \n");
		tabBuffer.append("        } catch (ClassNotFoundException e) { \n");
		tabBuffer.append("            e.printStackTrace(); \n");
		tabBuffer.append("            new myException(\"Class: \"+\"RECORD_\"+this.baseTableName()+\" can not be found !!!\"); \n");
		tabBuffer.append("        } \n");
		tabBuffer.append("        return null; \n");
		tabBuffer.append("    } \n");	
		
		//2016-01-07: ende
		
		
		
		tabBuffer.append("}\n");
		
		try	{
			FileUtils.writeStringToFile(oFileOut_RECORD, tabBuffer.toString(), "UTF-8");
		} catch (IOException ex) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error writing file: "+cOutName_StaticClassFile));
		}
	}
	
	
	
}
