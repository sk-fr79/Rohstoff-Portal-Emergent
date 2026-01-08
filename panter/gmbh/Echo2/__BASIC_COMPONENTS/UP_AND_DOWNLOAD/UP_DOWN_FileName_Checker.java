package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class UP_DOWN_FileName_Checker {

	private static String BASE_PATH 		= Archiver.get_ARCHIV_BASE_PATH(true, true);
	//		private static String BASE_PATH 		= "/daten/entwicklungstools/tomcat/webapps/rohstoff_app/archiv_old/";

	private HashMap<String, UP_DOWN_FileName_Model> 		fileNameFromDbMap 			= new HashMap<>();
	private HashMap<String, UP_DOWN_FileName_Model> 		fileNameFromServerList 		= new HashMap<String, UP_DOWN_FileName_Model> ();
	private HashMap <Path, UP_DOWN_FileName_Model>			fileNameAndCorrectionMap 	= new HashMap<>();

	private boolean DEBUG_MODE = true;

	private MyE2_TextArea outputComponent;

	private ArrayList<UP_DOWN_FileName_Model> terminalFileList =  new ArrayList<>();

	public void cleanFileNames() throws myException, IOException{

	}


	public HashMap<String, UP_DOWN_FileName_Model> extractFileNameFromServer()throws myException{

		ArrayList<String> directoryToScan = getPfadFromDb();
		for(String directory: directoryToScan){
			try {
				ArrayList<Path> fileList= standardFileList(Paths.get(BASE_PATH+directory));

				//				ArrayList<Path> problematicFileList = advancedFileList(Paths.get(BASE_PATH+directory));

				for(Path pfile:fileList){
					if(pfile.getFileName().toString().startsWith("ADRESSE_ID_16354_G")){
						pfile.getFileName();
					}

					UP_DOWN_FileName_Model udm = new UP_DOWN_FileName_Model(pfile);
					fileNameFromServerList.put(udm.getCorrectedFileName(), udm);
				}


			} catch (IOException e) {
				e.toString();
			}
		}



		return fileNameFromServerList;
	} 

	public static ArrayList<String> getPfadFromDb() throws myException{
		SEL query = new SEL("distinct " + ARCHIVMEDIEN.pfad).FROM(_TAB.archivmedien).WHERE(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP(ARCHIVMEDIEN.pfad);
		String [][] result = bibDB.EinzelAbfrageInArray(query.s());

		ArrayList<String> retList = new ArrayList<>();
		for(String[] r: result){
			retList.add(r[0]);
		}
		return retList;
	}


	public HashMap<String, UP_DOWN_FileName_Model> extractFileNameFromDB() throws myException{

		SEL s = new SEL(ARCHIVMEDIEN.dateiname, ARCHIVMEDIEN.pfad).FROM(_TAB.archivmedien).WHERE(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP(ARCHIVMEDIEN.id_archivmedien);
		String[][] tot = bibDB.EinzelAbfrageInArray(s.s());

		for(String[] t : tot){

			//			SEL subQueryForTheIds = 
			//					new SEL(ARCHIVMEDIEN.id_archivmedien)
			//					.FROM(_TAB.archivmedien)
			//					.WHERE(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT()))
			//					.AND(new vgl(ARCHIVMEDIEN.dateiname, t[0]))
			//					.AND(new vgl(ARCHIVMEDIEN.pfad, t[1]));
			//
			//			String[][] IdList = bibDB.EinzelAbfrageInArray(subQueryForTheIds.s());
			//			

			String filename  = t[0];
			String pfad = t[1];


			UP_DOWN_FileName_Model mod = new UP_DOWN_FileName_Model(filename,pfad);

			if(mod.isDirty()) {
				fileNameFromDbMap.put(
						mod.getCorrectedFileName(),
						mod
						);
			}
		}
		return fileNameFromDbMap;
	}

	public void compareDbAndServerFiles() throws myException, IOException{

		Set<String> unknownStatusFileList = fileNameFromServerList.keySet();

		for(UP_DOWN_FileName_Model dbFile : fileNameFromDbMap.values()){

			if(fileNameFromServerList.containsKey(dbFile.getFileName())){
				unknownStatusFileList.remove(dbFile);
			}
		}

		unknownStatusFileList.size();

		for(String unknownStatusFile : unknownStatusFileList){
			fileNameAndCorrectionMap.put(
					fileNameFromServerList.get(unknownStatusFile).getPath(), 
					fileNameFromServerList.get(unknownStatusFile));
		}
	}

	public static ArrayList<Path> standardFileList(Path directory) throws IOException{
		ArrayList<Path> fileNames = new ArrayList<>();
		Stream<Path> streamFiles = Files.walk(directory);

		File folder = new File(directory.toString());

		File [] listFiles = folder.listFiles();

		for (int i=0; i<listFiles.length ; i++){
			if (Files.isRegularFile(listFiles[i].toPath())){
				fileNames.add(listFiles[i].toPath());
			}
		}

		streamFiles.close();
		return fileNames;
	}

	public static ArrayList<Path> advancedFileList(Path directory) throws myException, IOException {
		ArrayList<Path> fileNames = new ArrayList<>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
		try{
			Iterator<Path> iterator = stream.iterator();
			while(iterator.hasNext()) {
				if(! Files.isDirectory(iterator.next())) {
					fileNames.add(iterator.next());
				}else{
					continue;
				}
			}
		}catch(Exception e){
			e.getMessage();
		}finally{
			stream.close();
			stream = null;
		}
		return fileNames;
	}

	public static String cleanString(String strToCorrect) throws UnsupportedEncodingException{
		String step1_str 	= correctIllegalUTF_8_CharList(strToCorrect);

		String step2_str = correctIllegalCharList(step1_str);

		String step3_str = step2_str .replaceAll("%20", " ");

		return step3_str;
	}


	public boolean isDebugMode() {
		return DEBUG_MODE;
	}

	public void setDebugMode(boolean debugMode) {
		DEBUG_MODE = debugMode;
	}

	public MyE2_TextArea getOutputComponent() {
		return outputComponent;
	}


	public void setOutputComponent(MyE2_TextArea outputComponent) {
		this.outputComponent = outputComponent;
	}


	public ArrayList<UP_DOWN_FileName_Model> getTerminalFileList() {
		return terminalFileList;
	}

	public HashMap<Path, UP_DOWN_FileName_Model> getFileNameAndCorrectionMap() {
		return fileNameAndCorrectionMap;
	}



	public static String correctIllegalUTF_8_CharList(String entryString){
		String[] illegalChrList = {"%C3%84"	,"%C3%A4"	,"%C3%96"	,"%C3%B6"	,"%C3%9C"	
				,"%C3%BC"	,"%C3%9F"	,"%C4"	,"%D6", "%DC",	"%E4",	"%F6",	"%FC",	"%DF", "%20"};

		String[] correctChrList = {"AE"		,"ae"		,"OE"		,"oe"		,"UE"		
				,"ue"		,"ss"		,"AE"	,"OE",	"UE",	"ae" ,	"oe" ,	"ue", 	"ss" , " "};

		String outputString = new String(entryString);

		for(int i=0; i<illegalChrList.length;i++){
			if(outputString.contains(illegalChrList[i])){
				outputString = outputString.replace(illegalChrList[i], correctChrList[i]);
			}
		}

		return outputString;
	}

	public static String correctIllegalCharList(String entryString){
		String[] illegalChrList = {"ä","ö","ü","Ä","Ö","Ü","ß"};
		String[] correctChrList = {"ae","oe","ue","AE","OE","UE","ss"};

		String outputString = new String(entryString);

		for(int i=0; i<illegalChrList.length;i++){
			if(outputString.contains(illegalChrList[i])){
				outputString = outputString.replace(illegalChrList[i], correctChrList[i]);
			}
			outputString.length();
		}

		return outputString;
	}
}
