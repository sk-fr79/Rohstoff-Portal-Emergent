package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FILENAME_ListView extends E2_Grid {

	private UP_DOWN_FileName_Checker_NG filenameChecker;
	//	private UP_DOWN_FILENAME_Navigation_Bar naviList;

	private LinkedHashMap<String , UP_DOWN_FILENAME_Row_Model> fileHashMap;

	private LinkedHashMap<String , UP_DOWN_FILENAME_Row_Model> onlyFileInDb;
	//	private HashMap<String , UP_DOWN_FILENAME_Row_Model> onlyFileInFs;
	private LinkedHashMap<String, UP_DOWN_FileName_Model_NG> fileInDb;
	//	private HashMap<String, UP_DOWN_FileName_Model_NG> fileInFs;
	private LinkedHashMap<String , UP_DOWN_FILENAME_Row_Model> fileToCorrect;

	private E2_Grid titleGrid 									= null;
	private E2_Grid elementGrid 								= null;
	
	private UP_DOWN_FILENAME_Navigation_Bar naviList;

	public UP_DOWN_FILENAME_ListView() throws myException, IOException {
		super();
		this._s(1)._w100()._bo_no();

		this.titleGrid = new E2_Grid()._setSize(30,150,550,550,400,100,100,20)._bo_ddd();
		this.elementGrid = new E2_Grid()._setSize(30,150,550,550,400,100,100)._bo_ddd();

		this.filenameChecker = new UP_DOWN_FileName_Checker_NG();
		this.fileHashMap = new LinkedHashMap<>();
		this.onlyFileInDb = new LinkedHashMap<>();
		//		this.onlyFileInFs = new HashMap<>();
		this.fileToCorrect = new LinkedHashMap<>();
		this.fileInDb = new LinkedHashMap<>();
		//		this.fileInFs = new HashMap<>();

		RB_gld gldTitle = new RB_gld()._left_mid()._col_back_d()._ins(2);

		this.titleGrid._clear()
		._a(new RB_lab("")						._bi(), 			gldTitle)
		._a(new RB_lab("Pfad")					._bi(), 			gldTitle)
		._a(new RB_lab("Dateiname (Datenbank)")._bi(), 			gldTitle)
		._a(new RB_lab("Dateiname (Filesystem)")._bi(),			gldTitle)
		//		._a(new RB_lab("Filesystem Eintrag")._bi(),				gldTitle)
		._a(new RB_lab("Korrekturvorschlag")._bi(),				gldTitle)
		._a(new RB_lab(E2_ResourceIcon.get_RI("empty.png")), 	gldTitle)
		._a(new RB_lab(E2_ResourceIcon.get_RI("empty.png")),	gldTitle)
		._a(new RB_lab(""),new RB_gld()._left_mid()._col_back(new E2_ColorBase())._ins(2)); 
		;

		this._initComponents();

		this._a(this.naviList, new RB_gld()._ins(2,10,2,10)._left_top());
		
		this._a(titleGrid, new RB_gld()._ins(1)._left_top());

		MyE2_ContainerEx cont = new MyE2_ContainerEx(this.elementGrid);
		cont.setHeight(new Extent(865));//(ContainerEx.AUTO);

		this._a(cont, new RB_gld()._ins(1)._left_top());
		
	}

	public void aufruf() throws myException, IOException {
		loadItemList();

		extractOnlyNameInDb();

		loadItemList();

		
	}

	private void _initComponents() throws myException {
		this.naviList = new UP_DOWN_FILENAME_Navigation_Bar(this,32);

	}

	/*public void buildHeaderLabels() throws myException {
		this.removeAll();

		this.setBackground(new E2_ColorDark());

		this.add(naviList, 				4,1, E2_INSETS.I(0,5,0,5), E2_ALIGN.LEFT_MID,new E2_ColorBase());
		this.add(einseitigListChkBox,	3,1,E2_INSETS.I(0,5,0,5), E2_ALIGN.LEFT_MID,new E2_ColorBase());

	}*/


	public void loadItemList() throws myException, IOException {
		//		fileInFs = this.filenameChecker.extractFileNameFromServer();
		fileInDb = this.filenameChecker.extractFileNameFromDB();

		HashMap<String,UP_DOWN_FileName_Model_NG> dirtyFileInFs = new HashMap<>();
		HashMap<String,UP_DOWN_FileName_Model_NG> dirtyFileInDb = new HashMap<>();
		HashMap<String,UP_DOWN_FileName_Model_NG> fileToCorrect = new HashMap<>();

		//Extraktion der Dateinamen von der Filesystem, der die haben einen korrupt Name.
		//		for (Entry<String, UP_DOWN_FileName_Model_NG> entryFs: fileInFs.entrySet()) {
		//			if(entryFs.getKey().startsWith("JT_ADRESSE_ID_3114")){
		//				entryFs.getValue().isDirty();
		//			}	
		//			if(entryFs.getValue().isDirty()){
		//				dirtyFileInFs.put(entryFs.getKey(), entryFs.getValue());
		//			}
		//		}

		//Extraktion der Dateinamen von der Datenbank, der die haben einen korrupt Name.
		for(Entry<String, UP_DOWN_FileName_Model_NG> entrydb: fileInDb.entrySet()) {
			if(entrydb.getKey().startsWith("JT_ADRESSE_ID_3114")){
				entrydb.getValue().isDirty();
			}	
			if(entrydb.getValue().isDirty()){
				dirtyFileInDb.put(entrydb.getKey(), entrydb.getValue());
			}
		}


		//Suche von die Dateinamen die haben eine korrupt Dateiname in Filesystem und existieren in Datenbank.
		for(Entry<String, UP_DOWN_FileName_Model_NG> entryFs: dirtyFileInFs.entrySet()){

			for(Entry<String, UP_DOWN_FileName_Model_NG> entrydb: dirtyFileInDb.entrySet()){
				if(entrydb.getKey().equals(entryFs.getKey())){
					if(entryFs.getKey().startsWith("JT_ADRESSE_ID_3114_Blech gro")){
						entryFs.getValue().getCorrectedFileName();
					}
					fileToCorrect.put(entryFs.getKey(), entryFs.getValue());
					if(entrydb.getKey().startsWith("JT_ADRESSE_ID_17818_BerichtS")){
						DEBUG.System_println("####### ----- " + entrydb.getKey());
					}
				}
			}
		}

		//		fileInFs.get("JT_ADRESSE_ID_3114_Blech_gro__Analyse198.jpg");
		//		fileInDb.get("JT_ADRESSE_ID_3114_Blech_gross_Analyse198.jpg");
		//		fileToCorrect.get("JT_ADRESSE_ID_3114_Blech_gro__Analyse198.jpg");

		for(Entry<String, UP_DOWN_FileName_Model_NG> correctionEntry: fileToCorrect.entrySet()){
			UP_DOWN_FileName_Model_NG dbFileModel = fileInDb.get(correctionEntry.getKey());
//			String fileName = a.getFileName();
//			String pfad = a.getPfad();

//			if(fileName.contains("ADRESSE_ID_7059")) {
//				DEBUG.System_println("******* " + pfad);
//			}

//			UP_DOWN_FILENAME_Row_Model dateiModel =  new UP_DOWN_FILENAME_Row_Model(correctionEntry.getValue().getPath(), correctionEntry.getValue().getFileName());
//			dateiModel.setDbFileName(fn);
//			dateiModel.setDbPath(Archiver.get_ARCHIV_BASE_PATH(true, true) + pf + File.separator + fn);
//			dateiModel.setDbPfad(pf);
//			dateiModel.setCorrectedFileName(correctionEntry.getKey());
			
			UP_DOWN_FILENAME_Row_Model dateiModel2 = new UP_DOWN_FILENAME_Row_Model(
					correctionEntry.getKey(), 
					dbFileModel.getDownloadname(), 
					dbFileModel.getDateiname(), 
					dbFileModel.getPfad()
			);
			
			this.fileHashMap.put(correctionEntry.getKey(), dateiModel2);
			this.fileToCorrect.put(correctionEntry.getKey(), dateiModel2);
		}	

		this.fileHashMap.putAll(onlyFileInDb);

		this.naviList.loadItems(fileHashMap);
	}

	public void extractOnlyNameInDb()throws myException{
		for(Entry<String, UP_DOWN_FileName_Model_NG> entryDb: fileInDb.entrySet()){
			String fName = entryDb.getValue().getDownloadname();
			String fPfad = entryDb.getValue().getPfad();
			String dbPath = Archiver.get_ARCHIV_BASE_PATH(true, true)+ fPfad +File.separator + fName;

			if(!new File(dbPath).exists()){
				onlyFileInDb.put(entryDb.getValue().getCorrectedFileName(), 
						new UP_DOWN_FILENAME_Row_Model(
								entryDb.getValue().getCorrectedFileName(), 
								entryDb.getValue().getDateiname(), 
								entryDb.getValue().getDownloadname(), 
								entryDb.getValue().getPfad()
								));
//						new UP_DOWN_FILENAME_Row_Model(entryDb.getValue().getCorrectedFileName(), entryDb.getValue().get_id_archivmedien(),fPfad,fName));
			}
		}

		ArrayList<String> fileToRemove= new ArrayList<>();
		for(Entry<String, UP_DOWN_FILENAME_Row_Model> entryFs: onlyFileInDb.entrySet()){
			if(fileHashMap.containsKey(entryFs.getValue().getCorrectedFileName())){
				fileToRemove.add(entryFs.getValue().getCorrectedFileName());
			}
		}

		for(String k: fileToRemove){
			onlyFileInDb.remove(k);
		}
	}



	//	public void extractOnlyNameInFs() throws myException{
	//		for(Entry<String, UP_DOWN_FileName_Model_NG> entryFs: fileInFs.entrySet()){
	//			String fName = entryFs.getValue().getCorrectedFileName();
	//			if(!new File(entryFs.getValue().getFilePath()).isDirectory()){
	//				if(! fileInDb.containsKey(fName)){
	//					onlyFileInFs.put(entryFs.getValue().getCorrectedFileName(), 
	//							new UP_DOWN_FILENAME_Row_Model(entryFs.getValue().getPath(),entryFs.getValue().getFileName() ) 
	//							);
	//				}
	//			}
	//		}
	//	}

//	private class ownCheckActionAgent extends XX_ActionAgent{
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//
//			try {
//				einseitig = einseitigListChkBox.isSelected();
//				if( ! einseitig){
//					//					for(String keyToRemove: onlyFileInFs.keySet()){
//					//						fileHashMap.remove(keyToRemove);
//					//					}
//					for(String keyToRemove: onlyFileInDb.keySet()){
//						fileHashMap.remove(keyToRemove);
//					}
//				}else{
//					//					fileHashMap.putAll(onlyFileInFs);
//					fileHashMap.putAll(onlyFileInDb);
//				}
//
//				fileHashMap.putAll(fileToCorrect);
//				populateList();
//			} catch (IOException e) {
//
//				bibMSG.add_MESSAGE(new MyE2_Message(new MyString(e.getMessage()), MyE2_Message.TYP_WARNING, false));
//			}
//		}
//
//	}

	public void populateList() throws myException, IOException{

		this.elementGrid._clear();

		for(UP_DOWN_FILENAME_Row_Model model: this.fileHashMap.values()) {
			new UP_DOWN_FILENAME_RowComponent(this.elementGrid, model);
		}
		MyE2_ContainerEx cont = new MyE2_ContainerEx(this.elementGrid);
		cont.setHeight(new Extent(865));//(ContainerEx.AUTO);

		this._a(cont, new RB_gld()._ins(1)._left_top());
	}
	
	public E2_Grid getElementGrid() throws myException{
		return this.elementGrid;
	}
}
