package calledByName.triggerExecuter;

import panter.gmbh.basics4project.BasicTools.TextFileLoader;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_TRIGGER_EXECUTER {
	
	//enum mit allen benutzbaren Trigger-executern
	TRIGGER_SHOW_SIMPLE_MESSAGE_POPUP("TriggerExecuterShowMessage","Zeige einfache Textmeldung")
	,TRIGGER_EXECUTE_XML("TriggerExecutorInterpretXML","Führt hinterlegte XML-Profile aus")
	;
	
	String class_name = null;
	String class_path = null;
	String readableName = null;
	
	private ENUM_TRIGGER_EXECUTER(String p_name, String p_readableName) {
		this.class_name = p_name;
		this.class_path = "calledByName.triggerExecuter."+this.class_name;
		this.readableName = p_readableName;
	}

	public String get_class_path() {
		return class_path;
	}

	public String get_readableName() {
		return readableName;
	}

	public String get_informationBlock() {
		try {
			return new TextFileLoader(ENUM_TRIGGER_EXECUTER.class, this.class_name+".txt").get_loadedText() ;
		} catch (myException e) {
			e.printStackTrace();
			return "<Keine Erklärung ...>";
		}
	}

	
	public static String[][] get_array4dropdown() {
		String[][] ret = new String[ENUM_TRIGGER_EXECUTER.values().length][2];
		
		int i=0;
		for (ENUM_TRIGGER_EXECUTER t: ENUM_TRIGGER_EXECUTER.values()) {
			ret[i][0]=t.get_readableName();
			ret[i][1]=t.get_class_path();
			i++;
		}
		
		return ret;
	}

	
	public static ENUM_TRIGGER_EXECUTER find_TRIGGER_EXECUTER(String classPath) {
		for (ENUM_TRIGGER_EXECUTER t: ENUM_TRIGGER_EXECUTER.values()) {
			if (t.get_class_path().equals(classPath)) {
				return t;
			}
		}
		return null;
	}
	
	
	
}
