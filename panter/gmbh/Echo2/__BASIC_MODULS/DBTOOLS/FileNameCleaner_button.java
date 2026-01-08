package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.io.IOException;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.UP_DOWN_FileName_Checker;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.exceptions.myException;

public class FileNameCleaner_button extends MyE2_Button{

	UP_DOWN_FileName_Checker fileNameCleaning;
	
	public FileNameCleaner_button() {
		super();
		
		fileNameCleaning = new UP_DOWN_FileName_Checker();
		
		setText("Die Namen von Dateien korrigieren");
		add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new ownActionAgent().ExecuteAgentCode(oExecInfo);
			}
		});
	}


	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FileNameContainer container = new FileNameContainer();
			container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(630), new Extent(350), new MyE2_String("Dateinamen fixieren"));
			container.setVisible(true);
		}

	}

	private class buttonAL extends XX_ActionAgent{

		FileNameContainer fnc;
		UP_DOWN_FileName_Checker flnIng;
		public buttonAL(FileNameContainer fileNameContainer) {
			fnc = fileNameContainer;
			flnIng = FileNameCleaner_button.this.fileNameCleaning;
			
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {
				boolean debugMode = fnc.isDebugMode();
				flnIng.setOutputComponent(fnc.resultDisplay);
				flnIng.setDebugMode(debugMode);
				flnIng.cleanFileNames();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	
	private class FileNameContainer extends E2_BasicModuleContainer{

		private MyE2_CheckBox debugChkBox;
		private MyE2_Button button;
		private MyE2_TextArea resultDisplay;

		public FileNameContainer() {
			super();

			MyE2_Grid mainGrid = new MyE2_Grid();
			
			button = new MyE2_Button("Dateinamen fixieren");
			button.add_oActionAgent(new buttonAL(this));
			mainGrid.add(button);
			debugChkBox  =new MyE2_CheckBox("Debug mode");
			debugChkBox.setSelected(true);
			mainGrid.add(debugChkBox);
			
			resultDisplay = new MyE2_TextArea("", 600, -1, 15);
			resultDisplay.setVerticalScroll(new Extent(10));
			mainGrid.add(resultDisplay,2, E2_INSETS.I(2,2,2,2));
			
			add(mainGrid);
			
		}

		public boolean isDebugMode(){
			return debugChkBox.isSelected();
		}
	}
}
