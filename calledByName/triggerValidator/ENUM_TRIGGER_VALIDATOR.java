package calledByName.triggerValidator;

import panter.gmbh.basics4project.BasicTools.TextFileLoader;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_TRIGGER_VALIDATOR {
	
	//enum mit allen benutzbaren Trigger-validators
	//enum mit allen benutzbaren Trigger-executern
	TRIGGER_VALIDATOR_ALLWAYS_TRUE("TriggerValidatorAlwaysTrue","Immer ausführen")
	,TRIGGER_VALIDATOR_ALLWAYS_TRUE_AT_INSERT("TriggerValidatorAlwaysTrueWhenInsertRecord","Immer ausführen bei neuen Datensätzen")
	,TRIGGER_VALIDATOR_ALLWAYS_TRUE_AT_UPDATE("TriggerValidatorAlwaysTrueWhenUpdateRecord","Immer ausführen bei Updates")
	,TRIGGER_VALIDATOR_ALLWAYS_TRUE_AT_DELETE("TriggerValidatorAlwaysTrueWhenDeleteRecord","Immer ausführen bei Löschung eines Datensatzes")
	,TriggerValidatorFieldValueChanges("TriggerValidatorFieldValueChanges","Wird ausgeführt bei Feldänderung von Wert a zu b")

	
	;
	
	String class_name = null;
	String class_path = null;
	String readableName = null;
	
	private ENUM_TRIGGER_VALIDATOR(String p_classname, String p_readableName) {
		this.class_name = p_classname;
		this.class_path = "calledByName.triggerValidator."+this.class_name;
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
			return new TextFileLoader(ENUM_TRIGGER_VALIDATOR.class, this.class_name+".txt").get_loadedText() ;
		} catch (myException e) {
			e.printStackTrace();
			return "<Keine Erklärung ...>";
		}
	}

	
	public static String[][] get_array4dropdown() {
		String[][] ret = new String[ENUM_TRIGGER_VALIDATOR.values().length][2];
		
		int i=0;
		for (ENUM_TRIGGER_VALIDATOR t: ENUM_TRIGGER_VALIDATOR.values()) {
			ret[i][0]=t.get_readableName();
			ret[i][1]=t.get_class_path();
			i++;
		}
		
		
		
		return ret;
	}

	
	public static ENUM_TRIGGER_VALIDATOR find_TRIGGER_VALIDATOR(String classPath) {
		for (ENUM_TRIGGER_VALIDATOR t: ENUM_TRIGGER_VALIDATOR.values()) {
			if (t.get_class_path().equals(classPath)) {
				return t;
			}
		}
		return null;
	}

	
}
