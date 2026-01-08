package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

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
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Stream;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FileNameCleaner;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FileName_Checker_NG {

	private static String BASE_PATH 		= Archiver.get_ARCHIV_BASE_PATH(true, true);
	//		private static String BASE_PATH 		= "/daten/entwicklungstools/tomcat/webapps/rohstoff_app/archiv_old/";

	private LinkedHashMap<String, UP_DOWN_FileName_Model_NG> 		fileNameFromDbMap 			= new LinkedHashMap<>();
	private LinkedHashMap<String, UP_DOWN_FileName_Model_NG> 		fileNameFromServerList 		= new LinkedHashMap<String, UP_DOWN_FileName_Model_NG> ();
	private LinkedHashMap <Path, UP_DOWN_FileName_Model_NG>			fileNameAndCorrectionMap 	= new LinkedHashMap<>();

	private boolean DEBUG_MODE = true;

	private MyE2_TextArea outputComponent;

	private ArrayList<UP_DOWN_FileName_Model_NG> terminalFileList =  new ArrayList<>();

	public void cleanFileNames() throws myException, IOException{

	}


	/*public HashMap<String, UP_DOWN_FileName_Model_NG> extractFileNameFromServer()throws myException{

		ArrayList<String> directoryToScan = getPfadFromDb();
		for(String directory: directoryToScan){
			try {
				ArrayList<Path> fileList= standardFileList(Paths.get(BASE_PATH+directory));

				//				ArrayList<Path> problematicFileList = advancedFileList(Paths.get(BASE_PATH+directory));

				for(Path pfile:fileList){
					if(pfile.getFileName().toString().startsWith("ADRESSE_ID_16354_G")){
						pfile.getFileName();
					}

					UP_DOWN_FileName_Model_NG udm = new UP_DOWN_FileName_Model_NG(pfile);
					fileNameFromServerList.put(udm.getCorrectedFileName(), udm);
				}


			} catch (IOException e) {
				e.toString();
			}
		}



		return fileNameFromServerList;
	} */

	public static ArrayList<String> getPfadFromDb() throws myException{
		SEL query = new SEL("distinct " + ARCHIVMEDIEN.pfad).FROM(_TAB.archivmedien).WHERE(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP(ARCHIVMEDIEN.pfad);
		String [][] result = bibDB.EinzelAbfrageInArray(query.s());

		ArrayList<String> retList = new ArrayList<>();
		for(String[] r: result){
			retList.add(r[0]);
		}
		return retList;
	}


	public LinkedHashMap<String, UP_DOWN_FileName_Model_NG> extractFileNameFromDB() throws myException{

		SEL s = new SEL("DISTINCT("+ARCHIVMEDIEN.dateiname.fn()+")")
				.ADDFIELD(ARCHIVMEDIEN.downloadname)
				.ADDFIELD(ARCHIVMEDIEN.pfad)
				.FROM(_TAB.archivmedien)
				.WHERE(new vgl(ARCHIVMEDIEN.id_mandant, bibALL.get_ID_MANDANT()))
				.ORDERUP(ARCHIVMEDIEN.pfad)
				;
		String[][] tot = bibDB.EinzelAbfrageInArray(s.s());

		for(String[] t : tot){
			
			String filename  		= t[0];
			String downloadname		= t[1];
			String pfad 			= t[2];

			String corrected_filename = new FileNameCleaner_NG(filename).get_filename_clean();
			

			if(! corrected_filename.equals(filename)) {
				fileNameFromDbMap.put(
						corrected_filename,
						new UP_DOWN_FileName_Model_NG(downloadname, filename, pfad)
						);
			}
		}
		return fileNameFromDbMap;
	}

	/*public void compareDbAndServerFiles() throws myException, IOException{

		Set<String> unknownStatusFileList = fileNameFromServerList.keySet();

		for(UP_DOWN_FileName_Model_NG dbFile : fileNameFromDbMap.values()){

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
	}*/

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


	public ArrayList<UP_DOWN_FileName_Model_NG> getTerminalFileList() {
		return terminalFileList;
	}

	public HashMap<Path, UP_DOWN_FileName_Model_NG> getFileNameAndCorrectionMap() {
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
