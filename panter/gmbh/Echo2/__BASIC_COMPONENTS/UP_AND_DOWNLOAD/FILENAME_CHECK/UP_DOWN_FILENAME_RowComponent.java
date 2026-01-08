package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import java.io.File;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FILENAME_RowComponent {

	private MyE2_Button 	correctButton 						= null;
	private MyE2_Button		downloadButton						= null;

	private RB_lab			statusLabel	= null;
	private RB_lab			pfadLabel							= null;
	private RB_lab 			originalDateiNameFileSystemLabel 	= null;
	private RB_lab 			originalDateiNameDBLabel 			= null;
//	private RB_lab 			lbl_id_archivmedien 				= null;
	private ownTextFeld		correctedDateiNameFeld 				= null;

	private UP_DOWN_FILENAME_Correction correctClazz			= null;

	private E2_Grid		mainGrid 								= null;

	private UP_DOWN_FILENAME_Row_Model datei					= null;	

	private String 			correctedFileName					= "";

	private boolean			tested								= true;

	private RB_gld gld = new RB_gld()._left_mid()._col(new E2_ColorBase())._ins(2);

	public UP_DOWN_FILENAME_RowComponent(E2_Grid c_parentGrid, UP_DOWN_FILENAME_Row_Model o_datei ) throws myException {
		super();

		this.mainGrid = c_parentGrid;
		this.datei = o_datei;

		this.correctClazz = new UP_DOWN_FILENAME_Correction(this);

		_init_components();

		_build_row();

		_populate_row();
	}


	private void _populate_row() throws myException {
		if(! (datei == null)){

			this.originalDateiNameDBLabel._t(datei.getDateiname());

			if(check_if_data_exist_on_fs()) {
				this.originalDateiNameFileSystemLabel.setText(datei.getDateiname());
			}
			
			this.correctedFileName = datei.getCorrectedFileName();

			this.correctedDateiNameFeld.setText(datei.getCorrectedFileName());

			updateStatus(this.datei.getStatus());

			this.pfadLabel.setText(this.datei.getPfad());

			if(S.isFull(datei.getDateiname()) && S.isEmpty(originalDateiNameFileSystemLabel.getText() )){
				correctButton.set_bEnabled_For_Edit(false);
				downloadButton.set_bEnabled_For_Edit(false);
				updateStatus(RENAME_STATUS.WARNING);
			}

			if(S.isEmpty(datei.getDateiname()) && S.isFull(datei.getDownloadname())){
				correctButton.set_bEnabled_For_Edit(true);
				downloadButton.set_bEnabled_For_Edit(true);
			}

		}
	}

	private boolean check_if_data_exist_on_fs() {
		
		String fsCompletePfad = Archiver.get_ARCHIV_BASE_PATH(true, true)+ this.datei.getPfad()+ File.separator + this.datei.get_fs_dateiname();
		
		File file_2_test = new File(fsCompletePfad);
		if(file_2_test.exists() && !file_2_test.isDirectory()) {
			return true;
		}
		return false;
	}

	private void _build_row() {

		this.mainGrid
		._a(statusLabel,						new RB_gld()._col(new E2_ColorBase())._ins(2)._center_mid())
		._a(pfadLabel,							gld)
		._a(originalDateiNameDBLabel, 			gld)
		._a(originalDateiNameFileSystemLabel,	gld)
		._a(correctedDateiNameFeld, 			gld)
		._a(correctButton, 						new RB_gld()._col(new E2_ColorBase())._ins(2)._center_mid())
		._a(downloadButton,						new RB_gld()._col(new E2_ColorBase())._ins(2)._center_mid())
		;

	}

	public void updateStatus(RENAME_STATUS status) throws myException{
		datei.setStatus(status);

		correctedDateiNameFeld.setStatus(status);

		switch(status){
		case UNCORRECT:
			statusLabel._icon(E2_ResourceIcon.get_RI("empty.png"));
			correctButton.set_bEnabled_For_Edit(true);
			downloadButton.set_bEnabled_For_Edit(false);
			break;
		case OK:
			statusLabel._icon(E2_ResourceIcon.get_RI("ok.png"));
			break;
		case KO:
			statusLabel._icon(E2_ResourceIcon.get_RI("clear.png"));
			break;
		case WARNING:
			statusLabel._icon(E2_ResourceIcon.get_RI("warnschild_16.png"));
			statusLabel._ttt("Die Datei wurde nicht auf den Dateisystem gefunden.");
			break;	
		}
	}

	public UP_DOWN_FILENAME_Row_Model getDatei() {
		return datei;
	}

	public String getCorrectedFileName() {
		return correctedFileName;
	}


	public void setCorrectedFileName(String correctedFileName) {
		this.correctedFileName = correctedFileName;
	}


	private void _init_components() throws myException {
		Extent w = new Extent(75);
		Extent h = new Extent(20);

		//		mainGrid.setLayoutData(MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));

		correctButton = new MyE2_Button("Korrektur", null,new ownCorrectActionAgent(), true);
		correctButton.set_bEnabled_For_Edit(tested);
		correctButton.setWidth(w);
		correctButton.setHeight(h);

		downloadButton = new MyE2_Button("Download", null, new ownDownloadButton(), true );
		downloadButton.set_bEnabled_For_Edit(false);
		downloadButton.setWidth(w);
		downloadButton.setHeight(h);
		downloadButton.setToolTipText("Download Datei");

		originalDateiNameFileSystemLabel = new RB_lab()._lw();
		originalDateiNameDBLabel = new RB_lab()._lw();

		correctedDateiNameFeld = new ownTextFeld();
		correctedDateiNameFeld.set_bEnabled_For_Edit(true);
		correctedDateiNameFeld.set_iWidthPixel(380);

		statusLabel = new RB_lab();

		pfadLabel = new RB_lab()._lw();

//		lbl_id_archivmedien = new RB_lab();

	}

	private class ownCorrectActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(tested){

				String newName = "";
				boolean nameOk = false;
				if(! datei.getCorrectedFileName().equals(correctedDateiNameFeld.getText())){
					String step1_str = new FileNameCleaner_NG(correctedDateiNameFeld.getText()).get_filename_clean();
					if(! step1_str.equals(correctedDateiNameFeld.getText())){
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("#--# Invalid Dateiname, name enthalt Ä, Ö, Ü, ß, ä, ö oder ü"));
						updateStatus(RENAME_STATUS.KO);
					}else if(S.isEmpty(correctedDateiNameFeld.getText())){
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("#--# keine Dateiname"));
						updateStatus(RENAME_STATUS.KO);
					}else{
						nameOk = true;
						newName = step1_str;
					}


				}else{
					nameOk = true;
					newName = datei.getCorrectedFileName();
				}

				if(nameOk){
					correctClazz.getMessageVector().clear();
					
					if(correctClazz.correctFilename(newName)){
						if(correctClazz.getMessageVector().get_bIsOK()) {
							if(correctClazz.correctDatabase(newName)){
								if(correctClazz.getMessageVector().get_bIsOK()) {
									updateStatus(RENAME_STATUS.OK);
									downloadButton.set_bEnabled_For_Edit(true);			
									bibDB.Commit();
									correctedFileName = newName;
									datei.setCorrectedFileName(newName);
								}else{
									updateStatus(RENAME_STATUS.KO);
									correctedDateiNameFeld.show_InputStatus(false);
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message("#--# Problem mit datenbank Update"));
									bibDB.Rollback();
								}

							}
						}
					}else{
						updateStatus(RENAME_STATUS.KO);
						bibMSG.add_MESSAGE(correctClazz.getMessageVector());
						bibDB.Rollback();
					}
				}else{
					updateStatus(RENAME_STATUS.KO);
				}
			}

		}

	}

	private class ownDownloadButton	extends	XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String downPath = Archiver.get_ARCHIV_BASE_PATH(true, true);

			String extension = "";

			if(S.isFull(originalDateiNameFileSystemLabel.getText()) && S.isEmpty(originalDateiNameDBLabel.getText())){
				downPath = Archiver.get_ARCHIV_BASE_PATH(true, true)+ pfadLabel.getText()+ File.separator +correctedDateiNameFeld.getText();
				extension=originalDateiNameFileSystemLabel.getText().substring(originalDateiNameFileSystemLabel.getText().lastIndexOf(".")+1);
			}else if(tested){
				downPath = downPath+datei.getPfad()+File.separator+correctedDateiNameFeld.getText();
				extension=correctedDateiNameFeld.getText().substring(correctedDateiNameFeld.getText().lastIndexOf(".")+1);
			}
			new E2_Download().starteFileDownload(downPath, extension);
		}

	}


	private class ownTextFeld extends MyE2_TextField{

		public void setStatus(RENAME_STATUS status){
			this.setForeground(status.getTextColor());
		}

		@Override
		public void show_InputStatus(boolean bInputIsOK) {
			if(! bInputIsOK){ 
				this.setStyle(MyE2_Grid.STYLE_GRID_BORDER(RENAME_STATUS.KO.getTextColor()));
				this.setBackground(Color.YELLOW);
			}
		}
	}



	public void _populate_for_test(){
		originalDateiNameFileSystemLabel.setText("Original Name in Filesystem.");
		originalDateiNameDBLabel.setText("Original Name in Datenbank.");
		correctedDateiNameFeld.setText("Name in der Datenbank korrigiert.");
	}


}
