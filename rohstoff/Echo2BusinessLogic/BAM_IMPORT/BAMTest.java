package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.TestSession;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BAM_IMPORT_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.ArchiverFileChecker;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver.ENUM_ARCHIV_AUFTEILUNG;
import panter.gmbh.indep.archive.Archiver_Delete_File_WhenAllowed;
import panter.gmbh.indep.dataTools.MyDBToolBox_Statement_Analyzer;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.query.ID;
import panter.gmbh.indep.dataTools.query.INSERT;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;
import junit.framework.TestCase;

public class BAMTest extends TestCase {
	static {
		try {
			TestSession ts = new TestSession("nils", "nilsandre");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// INSERT ins = new INSERT("id_bam_import", "id_mandant",
	// "erzeugt_am", "erzeugt_von", "belegnummer").into(
	// "jt_bam_import").values("id_bam_import", 1).values("id_mandant", "2");
	// //, "erzeugt_am", "erzeugt_von", "belegnummer"")
	//
	// System.out.println(ins.toString());
	// INSERT ins2 = new INSERT("id_bam_import", "id_mandant",
	// "erzeugt_am", "erzeugt_von", "belegnummer").into(
	// "jt_bam_import").values(hm);
	//
	// System.out.println(ins2.toString());

	//@Test
	public void testImport() throws myException, IOException {
		//BAMImporter.rollback();
		//ArrayList<String> answer = BAMImporter.doImport();
		// Assign unassigned
		System.out.println("\n\n\n");
		System.out.println("-----------------------------------------------------------");
/*		
		BAMImporter.assignFromRegularBAMTouple(_DB.BAM_IMPORT, answer.get(0), _DB.WIEGEKARTE, "56443");
		System.out.println("-----------------------------------------------------------");
		// Reassign new
		BAMImporter.assignFromRegularBAMTouple(_DB.WIEGEKARTE, "56443", _DB.WIEGEKARTE, "56444");
	*/	
	}
	@Test
	public void testSel() throws myException {
//		BAMImporter.assignFromRegularBAMTouple(_DB.BAM_IMPORT, "1147", _DB.WIEGEKARTE, "56444");
	}

	/*

    //cSql = this.makeExecutableStatement(cSql,this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields); // sql-string erweitern
    
    MyDBToolBox_Statement_Analyzer oStatementAnalyse = new MyDBToolBox_Statement_Analyzer(cSql,this.cZusatzFelderInsertFields, this.cZusatzFelderInsertValues, this.cZusatzUpdateFields);
    cSql = oStatementAnalyse.get_PlainSQL_Statement();
	
*/

}