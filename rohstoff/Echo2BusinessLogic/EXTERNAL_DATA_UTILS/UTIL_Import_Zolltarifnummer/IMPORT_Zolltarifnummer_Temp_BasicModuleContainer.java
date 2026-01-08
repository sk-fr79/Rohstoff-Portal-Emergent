/**
 * rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer_ALT
 * @author manfred
 * @date 26.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadListener;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_UploadSelect;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.VALIDATE_INPUT_Integer;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionInFileHandling;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile.Util_Import_Row_Import;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer.ROWS_Zolltarif_Import_Temp.ENUM_ZOLLTARIF_IMPORT_COLS;
import rohstoff.utils.bibROHSTOFF;

/**
 * @author manfred
 * @date 26.07.2016
 *
 */
public class IMPORT_Zolltarifnummer_Temp_BasicModuleContainer extends Project_BasicModuleContainer implements UploadListener {

	// pfad zur temporären datei
	String 			 _sUploadFileName = null;
	cSelectCharsets _selCharsets		= null;
	
	MyE2_LabelWrap 	_lblInfo ;
	
	// GUI
	E2_UploadSelect oUpload; 
	MyE2_Label		_lblFilename;
	MyE2_Button 	_btCheck;
	MyE2_Button 	_btImport;
	
	MyE2_Button 	_btClose;
	
	MyE2_TextArea	_txtInfo;
	
	ROWS_Zolltarif_Import_Temp	_oRowsImport = null;
	
	
	private UB_TextField	 _txtWANummerStart;
	private UB_TextField	_txtWANummerLen;
	private UB_TextField	_txtText1Start;
	private UB_TextField	_txtText1Len;
	private UB_TextField	_txtText2Start;
	private UB_TextField	_txtText2Len;
	private UB_TextField	_txtText3Start;
	private UB_TextField	_txtText3Len;
	private UB_TextField	_txtEinhetTxtStart;
	private UB_TextField	_txtEinhetTxtLen;
	private UB_TextField	_txtEinheitNumStart;
	private UB_TextField	_txtEinheitNumLen;


	private MyE2_Label 		_lblLaengeWA;
	private MyE2_Label 		_lblLaengeText1;
	private MyE2_Label 		_lblLaengeText2;
	private MyE2_Label 		_lblLaengeText3;
	private MyE2_Label 		_lblLaengeEinheitTxt;
	private MyE2_Label 		_lblLaengeEinheitNum;
	
	private VECTOR_UB_FIELDS 	vUnboundFields = new VECTOR_UB_FIELDS();
	
	
	
	
	
	/**
	 * @author manfred
	 * @date 26.07.2016
	 *
	 * @param cMODULEIDENTIFIER
	 * @throws myException 
	 */
	public IMPORT_Zolltarifnummer_Temp_BasicModuleContainer() throws myException {
		super(E2_MODULNAMES.NAME_MODUL_IMPORT_ZOLLTARIFNUMMER);
		
		_oRowsImport    = new ROWS_Zolltarif_Import_Temp();	
		_btCheck 		= new MyE2_Button("Daten prüfen");
		_btImport 		= new MyE2_Button("Daten importieren");
		_btClose		= new MyE2_Button("Beenden");
		_lblFilename	= new MyE2_Label("-");


		_selCharsets	= new cSelectCharsets();
		
		_lblInfo		= new MyE2_LabelWrap(new MyE2_String("Für den Import der Zolltarifnummern sollte immer der gesamte Zolltarif-Bestand importiert werden. "
				+ "Die Daten werden in Form der Sova-Leitdatei von der DESTATIS geliefert. (www.destatis.de). " 
				+ "Die Sova-Leitdatei kann von der Website der DESTATIS heruntergeladen werden: https://www.destatis.de/DE/Methoden/Klassifikationen/Aussenhandel/Content75/sova_leitdatei.html. " 
				+ "Falls einzelne bzw. unvollständige Einträge oder Listen importiert werden, muss bei der späteren Übernahme der Daten dies gekennzeichnet werden, sonst werden alle nicht vorhandenen Daten als NICHT AKTIV markiert."));
		
		
		
		_txtInfo 		= new MyE2_TextArea	("", 800, 100000, 10);
		_txtInfo.set_bEnabled_For_Edit(false);
		
		
		_btCheck.set_bEnabled_For_Edit(false);
		_btImport.set_bEnabled_For_Edit(false);

		
		_lblLaengeWA = new MyE2_Label(new MyString("Start / Länge der WA-Nummer"));
		
		_txtWANummerStart = new UB_TextField("1",50,8);
		_txtWANummerLen = new UB_TextField("8",50,8);
		
		_lblLaengeText1 = new MyE2_Label(new MyString("Start / Länge Text1"));
		_txtText1Start = new UB_TextField("11",50,8);
		_txtText1Len = new UB_TextField("110",50,8);
		
		_lblLaengeText2 = new MyE2_Label(new MyString("Start / Länge Text2"));
		_txtText2Start = new UB_TextField("121",50,8);
		_txtText2Len = new UB_TextField("120",50,8);
		
		_lblLaengeText3 = new MyE2_Label(new MyString("Start / Länge Text3"));
		_txtText3Start = new UB_TextField("241",50,8);
		_txtText3Len = new UB_TextField("18",50,8);
		
		_lblLaengeEinheitTxt = new MyE2_Label(new MyString("Start / Länge Einheit"));
		_txtEinhetTxtStart = new UB_TextField("259",50,8);
		_txtEinhetTxtLen = new UB_TextField("16",50,8);
		
		_lblLaengeEinheitNum = new MyE2_Label(new MyString("Start / Länge Einheit numerisch"));
		_txtEinheitNumStart = new UB_TextField("275",50,8);
		_txtEinheitNumLen = new UB_TextField("2",50,8);

		
		// Validierer definieren
		_txtWANummerStart.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtWANummerLen.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtText1Start.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtText1Len.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtText2Start.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtText2Len.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtText3Start.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtText3Len.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtEinhetTxtStart.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtEinhetTxtLen.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtEinheitNumStart.add_InputValidator(new VALIDATE_INPUT_Integer());
		_txtEinheitNumLen.add_InputValidator(new VALIDATE_INPUT_Integer());
		
		vUnboundFields.add( _txtWANummerStart);
		vUnboundFields.add( _txtWANummerLen );
		vUnboundFields.add( _txtText1Start);
		vUnboundFields.add( _txtText1Len);
		vUnboundFields.add( _txtText2Start);
		vUnboundFields.add( _txtText2Len);
		vUnboundFields.add( _txtText3Start);
		vUnboundFields.add( _txtText3Len);
		vUnboundFields.add( _txtEinhetTxtStart );
		vUnboundFields.add( _txtEinhetTxtLen);
		vUnboundFields.add( _txtEinheitNumStart );
		vUnboundFields.add( _txtEinheitNumLen);
		
		
		
		
		_btCheck.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				boolean bRet = checkFile();
				if (bRet){
					_btImport.set_bEnabled_For_Edit(true);
				}
			}
		});
		
		
		
		_btImport.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				saveData();
			}
		});

		
		
		_btClose.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});

		

	}

	
	
	/**
	 * startet den Dialog (von Aussen)
	 * @author manfred
	 * @date 14.09.2016
	 *
	 * @throws myException
	 */
	public void show() throws myException{
		initGUI();
	}
	



	/**
	 * 
	 * @author manfred
	 * @date 20.09.2016
	 *
	 * @throws myException
	 */
	private void initGUI() throws myException{
		oUpload = new E2_UploadSelect(File.separator + bibALL.get_WEBROOTPATH() + File.separator  + bibALL.get_TEMPPATH() + File.separator, true);
		oUpload.setUploadListener(this);
		
		MyE2_Grid oGridMain = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		oGridMain.add(_lblInfo, 3, E2_INSETS.I_0_5_0_5);
		
		oGridMain.add(new MyE2_Label(new MyE2_String("1. Importdatei wählen und hochladen")));
		oGridMain.add(oUpload,2,E2_INSETS.I_0_0_0_0);
		oGridMain.add(new MyE2_Label(""));
		oGridMain.add(_lblFilename,2,E2_INSETS.I_0_0_0_0);
		
		// masse angeben
		oGridMain.add(_lblLaengeWA);
		oGridMain.add(_txtWANummerStart);
		oGridMain.add(_txtWANummerLen);
		
		oGridMain.add(_lblLaengeText1);
		oGridMain.add(_txtText1Start);
		oGridMain.add(_txtText1Len);
		
		oGridMain.add(_lblLaengeText2);
		oGridMain.add(_txtText2Start);
		oGridMain.add(_txtText2Len);
		
		oGridMain.add(_lblLaengeText3);
		oGridMain.add(_txtText3Start);
		oGridMain.add(_txtText3Len);
		
		oGridMain.add(_lblLaengeEinheitTxt);
		oGridMain.add(_txtEinhetTxtStart);
		oGridMain.add(_txtEinhetTxtLen);

		oGridMain.add(_lblLaengeEinheitNum);
		oGridMain.add(_txtEinheitNumStart);
		oGridMain.add(_txtEinheitNumLen);


		oGridMain.add(new MyE2_Label(new MyE2_String("Charset")),1,E2_INSETS.I_0_0_0_0);
		oGridMain.add(_selCharsets,2,E2_INSETS.I_0_0_0_0);
		
		
		oGridMain.add(new MyE2_Label(new MyE2_String("2. Daten importiern und prüfen")),1,E2_INSETS.I_0_0_0_0);
		oGridMain.add(_btCheck,2,E2_INSETS.I_0_0_0_0);
		
		
		oGridMain.add(new MyE2_Label(new MyE2_String("Info")),1,E2_INSETS.I_0_0_0_0);
		oGridMain.add(_txtInfo,2,E2_INSETS.I_0_0_0_0);
		
		
		oGridMain.add(new MyE2_Label(new MyE2_String("3. Daten in die Datenbank schreiben")),1,E2_INSETS.I_0_0_0_0);
		oGridMain.add(_btImport,2,E2_INSETS.I_0_0_0_0);
		
		
		oGridMain.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0);
		oGridMain.add(_btClose,2,E2_INSETS.I_0_0_0_0);
		
		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this) {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
			}
		});

		this.add(oGridMain);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500),
				new MyE2_String("Import der Zolltarifnummern"));


	}


	
	

	
	private void listCharsets(){
		SortedMap<String, Charset> allCharsets = Charset.availableCharsets();
		for (String key: allCharsets.keySet()){
			DEBUG.System_println(key + " : " + allCharsets.get(key).name());
		}
	}

	
	
	
	/**
	 * prüft die Eingabewerte der Feldbegrenzer auf numerisch
	 * Importiert die Datei 
	 * @author manfred
	 * @date 28.07.2016
	 *
	 * @return
	 */
	private boolean importFile(){
		boolean bRet = false;
	
//		listCharsets();
		String sCharset;
		try {
			sCharset = _selCharsets.get_ActualWert();
		} catch (myException e1) {
			sCharset = "windows-1252";
		}
		
		try {
			bibMSG.add_MESSAGE(vUnboundFields.get_MV_AllFieldsAreOK_ShowErrorInput());
		} catch (myException e) {
			e.printStackTrace();
			
		}
		
		if (bibMSG.get_bIsOK()){
			_oRowsImport.setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER, getNum(_txtWANummerStart),getNum(_txtWANummerLen));
			_oRowsImport.setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT1, getNum(_txtText1Start),getNum(_txtText1Len));
			_oRowsImport.setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT2,  getNum(_txtText2Start),getNum(_txtText2Len));
			_oRowsImport.setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT3,  getNum(_txtText3Start),getNum(_txtText3Len));
			_oRowsImport.setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_NUM, getNum(_txtEinheitNumStart),getNum(_txtEinheitNumLen));
			_oRowsImport.setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_TXT, getNum(_txtEinhetTxtStart),getNum(_txtEinhetTxtLen));
			bRet = _oRowsImport.readFile(_sUploadFileName, sCharset) ;
		}

		return bRet;
		
	}
	
	
	private int getNum(MyE2_TextField field){
		int ret = 0;
		try {
			ret = Integer.parseInt(field.getText());
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}
	
	
	
	
	
	/**
	 * Datei lesen und in String inportieren
	 * @author manfred
	 * @date 27.07.2016
	 *
	 */
	private boolean checkFile(){
		boolean bRet = true;
		
		bRet = importFile();
		bRet &= bibMSG.get_bIsOK();
		
		String 	sInfo ="";
		if (bRet && bibMSG.get_bIsOK()){
			sInfo += Integer.toString(_oRowsImport.getHt_Rows().size()) + " sind in der Importdatei vorhanden." +  System.lineSeparator();
			sInfo += System.lineSeparator();
			sInfo += System.lineSeparator();
			sInfo += "Beispiel: " + System.lineSeparator();
			Collection<Util_Import_Row_Import> col = _oRowsImport.getHt_Rows().values();
			Iterator<Util_Import_Row_Import> it = col.iterator();
//			for (int i=0; i<5; i++){
				while (it.hasNext()){
					Util_Import_Row_Import row = it.next();
					
					sInfo += row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).get_value();
					sInfo += "\t";
					sInfo += row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT1.toString()).get_value();
					sInfo += System.lineSeparator();
							
				}
//			}
			sInfo += System.lineSeparator();
			 
			
		} else {
			sInfo = "Es gab einen Fehler beim lesen der Daten aus der Datei." + System.lineSeparator();
			sInfo += bibMSG.MV().get_MessagesAsText();
		};
		_txtInfo.setText(sInfo);
		
		return bRet;
	}
	
	

	/**
	 * erzeugt die SQL-Statements für den Import und führt diese durch
	 * @author manfred
	 * @date 28.07.2016
	 *
	 */
	protected void saveData() {
		Vector<String> vSQL = new Vector<>();
		String  sDel = "DELETE FROM " + ZOLLTARIFNUMMER_IMPORT.fullTabName() + " WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT();
		vSQL.add(sDel);
		
		vSQL.addAll(_oRowsImport.getSQLForNew());
		
		
		if (bibALL.get_bDebugMode()){
			
			DEBUG.System_println("SQL-Statements");
			DEBUG.System_print(vSQL);
			
			bibALL.get_bDebugMode();
		}

		boolean bOK = bibDB.ExecSQL_in_Batch(vSQL, true);
		if (!bOK){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim Updaten der Daten")));
		} else {
			_txtInfo.setText(_txtInfo.getText() + System.lineSeparator()+ System.lineSeparator()+ "Import erfolgreich.");
			
			try {
				_btImport.set_bEnabled_For_Edit(false);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see nextapp.echo2.app.filetransfer.UploadListener#fileUpload(nextapp.echo2.app.filetransfer.UploadEvent)
	 */
	@Override
	public void fileUpload(UploadEvent e) {
		E2_UploadSelect oUploadSelect = (E2_UploadSelect) e.getSource();
		try
		{
			oUploadSelect.doSaveFile(e,bibALL.get_SESSIONID(null) + "_",true);
			_sUploadFileName = oUploadSelect.get_cStoragePathAndFileNameWithEnding();
			this._lblFilename.setText(_sUploadFileName);
			this._btCheck.set_bEnabled_For_Edit(true);
		}
		catch (myExceptionInFileHandling ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.get_oMessage().CTrans(),false)));
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Hochladen der Datei:"));
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		this.showActualMessages();
	}



	/* (non-Javadoc)
	 * @see nextapp.echo2.app.filetransfer.UploadListener#invalidFileUpload(nextapp.echo2.app.filetransfer.UploadEvent)
	 */
	@Override
	public void invalidFileUpload(UploadEvent arg0) {

	}
	
	
	
	
	/**
	 * Charsets-Selektor mit vorgabe "windows-1252"
	 * @author manfred
	 * @date 20.09.2016
	 *
	 */
	private class cSelectCharsets extends MyE2_SelectField
	{
		public cSelectCharsets() throws myException
		{
			super();
			SortedMap<String, Charset> allCharsets = Charset.availableCharsets();
			String[][] cSelectCharsets = new String[allCharsets.size()][2];
			int i = 0;
			for (String key: allCharsets.keySet()){
				cSelectCharsets[i][0] = key; 			
				cSelectCharsets[i][1] = allCharsets.get(key).name();
				i++;
			}
			this.set_ListenInhalt(cSelectCharsets, false);
			this.set_ActiveValue_OR_FirstValue("windows-1252");
		}
	}
	

}
