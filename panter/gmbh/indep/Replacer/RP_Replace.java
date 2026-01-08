package panter.gmbh.indep.Replacer;

import java.util.Vector;

import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS.RECORD_GROOVYSCRIPT_SPECIAL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_GROOVYSCRIPT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_GROOVYSCRIPT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class RP_Replace {

//	private int    				i_max_count_loop = 100;
//
//	private boolean 			replace_sys_variables = 	false;
//	private boolean 			replace_groovy_variables = 	false;
//
//	private Vector<MyRECORD>   	v_records_to_replace = new Vector<>();
//
//
//	public RP_Replace() {
//		super();
//	}
//
//	public String replace(String cStringWithVariables) throws myException {
//		boolean found_anything = true;
//
//		int i_count_loop = 0;
//
//		while (found_anything) {
//			String c_before = new String(cStringWithVariables);
//
//			if (this.replace_sys_variables){
//				cStringWithVariables=RP_Replace.replace_SYS_Fields(cStringWithVariables);
//			}
//
//			if (this.replace_groovy_variables) {
//				cStringWithVariables = RP_Replace.replace_GROOVY_Fields(cStringWithVariables);
//			}
//
//			for (MyRECORD rec: this.v_records_to_replace) {
//				cStringWithVariables = RP_Replace.replace_record_fields(cStringWithVariables, rec);
//			}
//
//			found_anything = !(c_before.equals(cStringWithVariables));
//
//			i_count_loop++;
//
//			if (found_anything) {
//				if (i_count_loop>=i_max_count_loop) {
//					found_anything=false;     //dann trotzdem abbrechen
//				}
//			}
//		}
//
//		return cStringWithVariables;
//	}
//
//
//
//	public static String replace_record_fields(String cStringWithVariables, MyRECORD  record) throws myException {
//		//###sebastien hier codieren
//		StringBuffer stringBufferWithVariables = new StringBuffer(cStringWithVariables);
//
//		if(cStringWithVariables.contains("#")){
//			String tableName = "";
//			String field = "";
//			String fieldValue ="";
//
//			int iStartOfField = stringBufferWithVariables.indexOf("#");
//			int iEndOfField = stringBufferWithVariables.indexOf("#",iStartOfField+1);
//
//			if(iStartOfField>-1 && iEndOfField>-1){
//
//				String[] fieldToReplace = stringBufferWithVariables.substring(iStartOfField+1, iEndOfField).split("\\.");
//
//				if( fieldToReplace.length==2){
//					tableName = fieldToReplace[0];
//					field = fieldToReplace[1];
//
//					if(record.get_TABLE_NAME().equals(tableName)){
//						fieldValue = record.get_UnFormatedValue(field);
//						stringBufferWithVariables.replace(iStartOfField, iEndOfField + 1, fieldValue);
//					}
//
//
//				}else{
//
//				}
//			}
//		}
//		return stringBufferWithVariables.toString();
//	}
//
//
//	public RP_Replace add_record_to_replace(MyRECORD record) {
//		this.v_records_to_replace.add(record);
//		return this;
//	}
//
//
//
//	public static String replace_SYS_Fields(String cStringWithVariables)  throws myException {
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_USERNAME#", bibALL.get_USERNAME());
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_KUERZEL#", bibALL.get_KUERZEL());
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_USERID#", bibALL.get_ID_USER());
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_MANDANT_ID#", bibALL.get_ID_MANDANT());
//		//fehler bei der ersten definition, wird fuer vorhandene reports drinngelassen
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#ID_ADRESSE_MANDANT#", bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));  
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_ID_ADRESSE_MANDANT#", bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_REPORTDATE#", bibALL.get_cDateNOW());
//
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_REPORTDATE_FIRST_DAY_OF_MONTH#", bibALL.get_cDateFirstDateActualMonth());
//		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_REPORTDATE_LAST_DAY_OF_MONTH#", bibALL.get_cDateLastDateActualMonth());
//
//		return cStringWithVariables;
//	}
//
//
//	public static String replace_GROOVY_Fields(String cStringWithVariables)  throws myException {
//		//hier werden noch weitere sys-variable aus der groovy-definitionstabelle erzeugt
//		RECLIST_GROOVYSCRIPT rlGroovy = new RECLIST_GROOVYSCRIPT("","");
//		for (RECORD_GROOVYSCRIPT recScript: rlGroovy.values())
//		{
//
//			try
//			{
//				RECORD_GROOVYSCRIPT_SPECIAL recSpec = new RECORD_GROOVYSCRIPT_SPECIAL(recScript);
//				if (cStringWithVariables.contains("#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+"#"))
//				{
//					cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+"#", recSpec._get_KeyValuePair()[1]);
//				}
//			}
//			catch (myException e)
//			{
//				throw e;
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		return cStringWithVariables;
//	}
//
//
//
//
//
//	public boolean is_replace_sys_variables() {
//		return replace_sys_variables;
//	}
//
//
//
//
//
//	public void set_replace_sys_variables(boolean p_replace_sys_variables) {
//		this.replace_sys_variables = p_replace_sys_variables;
//	}
//
//
//
//
//
//	public boolean is_replace_groovy_variables() {
//		return this.replace_groovy_variables;
//	}
//
//
//
//
//
//	public void set_replace_groovy_variables(boolean p_replace_groovy_variables) {
//		this.replace_groovy_variables = p_replace_groovy_variables;
//	}
//
//
//
//
//
//	public Vector<MyRECORD> get_v_records_to_replace() {
//		return this.v_records_to_replace;
//	}
//
//


}
