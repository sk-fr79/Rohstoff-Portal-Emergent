package panter.gmbh.indep.Replacer;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class RP_Replace_Test {

//	private RP_Replace classToTest;
//	
//	public void test() throws myException {
//		classToTest = new RP_Replace();
//		
//		RECORD_ADRESSE adresseRecord =  new RECORD_ADRESSE(3112l);
//		RECORD_LAND landRecord = new RECORD_LAND("3");
//		RECORD_MANDANT mandantRecord = new RECORD_MANDANT("3");
//		
//		String testStringLand = "#JD_LAND.BESCHREIBUNG#, Code: #JD_LAND.LAENDERCODE#";
//		String testStringMandant = "#JD_MANDANT.ID_MANDANT#: #JD_MANDANT.NAME1#, #JD_MANDANT.NAME2#, #JD_MANDANT.ORT#";
//		String testStringAdresse = "JT_ADRESSE";
//
//		String testComplexString = "#JD_MANDANT.NAME1#, #JD_LAND.LAENDERCODE# ";
//		
//		String test1 ="Ich bin in #JD_LAND.BESCHREIBUNG#.";
//		
//		DEBUG.System_println(testStringLand + " --> " + testReplaceRecord(testStringLand, landRecord));
//		
//		DEBUG.System_println(testStringMandant + " --> " + testReplaceRecord(testStringMandant, mandantRecord));
//		
//		DEBUG.System_println(testComplexString + " --> " + testReplaceRecord(testComplexString, landRecord, mandantRecord));
//		
//		DEBUG.System_println(testStringAdresse + " --> " + testReplaceRecord(testStringLand, adresseRecord));
//		
//		DEBUG.System_println(test1 + " --> " + testReplaceRecord(test1, landRecord));
//	}
//	
//	public String testReplaceRecord(String testString, MyRECORD ...testRecords) throws myException{
//		for(MyRECORD testRecord : testRecords){
//			classToTest.add_record_to_replace(testRecord);
//		}
//		return classToTest.replace(testString);
//	}

}
