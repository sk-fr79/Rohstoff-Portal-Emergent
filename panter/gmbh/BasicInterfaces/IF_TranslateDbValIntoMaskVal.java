/**
 * 
 */
package panter.gmbh.BasicInterfaces;

import java.util.WeakHashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * mechanismus, um beliebigen DB-Mask-klassen eine translation unterzuschieben.
 * WICHTIG: bei betreffenden masken-ojekten muessen die 2 klassen, die uebergabe db in maske und maske in db machen, ueberschrieben werden
 *          Ebenfalls ist es sinnvoll eine field-validation durchzufuehren und die validateCorrectMaskVal auszufuehren  
 */
public interface IF_TranslateDbValIntoMaskVal {
	
	public static interface IF_Translator {
		public String translateDbValIntoMaskVal(String dbVal) throws myException;
		public String translateMaskValIntoDbVal(String maskVal) throws myException;
		public MyE2_MessageVector validateCorrectMaskVal(String maskVal) throws myException;
	}
	
	//speicherplatz fuer lokale variable
	public static final WeakHashMap<IF_TranslateDbValIntoMaskVal, IF_Translator>  hmTranslator = new WeakHashMap<>();
	
	
	public default void setTranslator(IF_Translator translator) {
		IF_TranslateDbValIntoMaskVal.hmTranslator.put(this, translator);
	}
	
	public default IF_Translator getTranslator() {
		return IF_TranslateDbValIntoMaskVal.hmTranslator.get(this);
	}
	
	
	public default String translateDbValToMaskVal(String dbVal) throws myException {
		IF_Translator translate = this.getTranslator();
		
		if (translate==null) {
			throw new myException("Error: No translator was defined !!");
		}
		return translate.translateDbValIntoMaskVal(dbVal);
	}
	
	public default String translateMaskValToDBVal(String maskVal) throws myException {
		IF_Translator translate = this.getTranslator();
		
		if (translate==null) {
			throw new myException("Error: No translator was defined !!");
		}
		return translate.translateMaskValIntoDbVal(maskVal);
	}
	
	
}
