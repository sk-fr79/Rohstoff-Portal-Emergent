package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_like;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FILENAME_Correction {

	private UP_DOWN_FILENAME_RowComponent 	parent;
	private UP_DOWN_FILENAME_Row_Model 		datei;
	private String 							correctedFileName;

	private MyE2_MessageVector mv;
	
	public UP_DOWN_FILENAME_Correction(UP_DOWN_FILENAME_RowComponent p_parent) {
		this.parent = p_parent;
		this.datei 	= parent.getDatei();
		this.correctedFileName = parent.getDatei().getCorrectedFileName();
		
		this.mv = new MyE2_MessageVector();
	}
	
	public boolean correctDatabase(String newName) throws myException{
		int renamedOccurence = countCorrectedEntry();
		if(renamedOccurence == 0){
			String query = renameDbFileEntry(this.datei.getPfad(), this.datei.getDateiname(), newName);
			return bibDB.ExecSQL(query, false);
		}else {
			this.mv._addAlarm("Datenbank update hat nicht funktionniert");
			return false;
		}
	}

	private int countCorrectedEntry() throws myException {
		SEL s = new SEL("count(" + ARCHIVMEDIEN.id_archivmedien.fieldName()+")")
				.FROM(_TAB.archivmedien)
			.WHERE(new vgl(ARCHIVMEDIEN.pfad, this.datei.getPfad()))
			.AND(new vgl(ARCHIVMEDIEN.dateiname, this.correctedFileName))
			.AND(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT());
		String result = bibDB.EinzelAbfrage(s.s());
		return Integer.parseInt(result);
	}


	public boolean correctFilename(String newName){
		String normalized_filename = new FileNameCleaner_NG(this.datei.getDateiname()).clean_fs(this.datei.getDateiname());
		
		Path fsPfad =new File(Archiver.get_ARCHIV_BASE_PATH(true, true)+ this.datei.getPfad()+ File.separator + normalized_filename).toPath();
		
		return renameFile(fsPfad, newName);
	}

	public boolean renameFile(Path fileToRename, String newName){
		try {
			Path p = fileToRename.getParent();

			Path newPath = Paths.get(p.toString()+ File.separator + newName);
			Files.move(fileToRename, newPath , java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			
			return new File(p.toFile(), newName).exists();
		} catch (IOException e) {
			this.mv._addAlarm(e.toString());
			return false;
		}
	}
	
	public String renameDbFileEntry(String pfad, String oldFileName, String correctedFileName) throws myException{
		
		byte[] spByteArray = {-17, -65, -67};
		String spChar = new String(spByteArray);
		
		String oldFileNameT = oldFileName.replace(spChar, "_");
		
		SEL s = new SEL(ARCHIVMEDIEN.id_archivmedien)
				.FROM(_TAB.archivmedien)
				.WHERE(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(new vgl(ARCHIVMEDIEN.pfad, pfad))
				.AND(new vgl_like(ARCHIVMEDIEN.dateiname, oldFileNameT));
		
		String[][] idList = bibDB.EinzelAbfrageInArray(s.s());
		String f = "("+ bibTEXT.concat(bibALL.get_VectorAusArrayColumn(idList, 0), ",")+")";
		
		
		StringBuffer sqlStatement = new StringBuffer("UPDATE ");
		sqlStatement.append(RECORD_ARCHIVMEDIEN.TABLENAME);
		sqlStatement.append(" SET " + ARCHIVMEDIEN.dateiname + " = '" + correctedFileName + "' ,");
		sqlStatement.append(ARCHIVMEDIEN.downloadname + " = '" + correctedFileName + "' ");
		sqlStatement.append("WHERE " + ARCHIVMEDIEN.pfad.fn()+ " = '" + pfad + "' and " + ARCHIVMEDIEN.id_archivmedien.fn() + "");
		sqlStatement.append(" IN " + f + " AND ");
		sqlStatement.append(RECORD_ARCHIVMEDIEN.FIELD__ID_MANDANT + "= " +bibALL.get_ID_MANDANT());
		
		String sUTF8="" ;

		try {
			sUTF8 = new String(sqlStatement.toString().getBytes(Charset.defaultCharset()),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			this.mv._addAlarm(e.getMessage());
		}
		
		
		
		return sUTF8;
//		return sqlStatement.toString();
	}
	
	public MyE2_MessageVector getMessageVector() throws myException{
		return this.mv;
	}

}
