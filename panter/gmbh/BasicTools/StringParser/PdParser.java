/**
 * 
 */
package panter.gmbh.BasicTools.StringParser;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * Sucht in String nach Mustern der Art: <KEY:value>, wobei key der Textschluessel ist, Value der Wert, der ausgelesen wird
 * 
 *
 */
public class PdParser {
	private String leftSeparator = "<";
	private String rightSeparator = ">";
	private String separator = ":"; 

	
	public PdParser() {
		super();
	}
	
	public String getLeftSeparator() {
		return leftSeparator;
	}
	
	public String getRightSeparator() {
		return rightSeparator;
	}
	
	public String getSeparator() {
		return separator;
	}
	
	public PdParser _setLeftSeparator(String leftSeparator) {
		this.leftSeparator = leftSeparator;
		return this;
	}
	
	public PdParser _setRightSeparator(String rightSeparator) {
		this.rightSeparator = rightSeparator;
		return this;
	}
	
	public PdParser _setSeparator(String separator) {
		this.separator = separator;
		return this;
	}
	
	
	/**
	 * 
	 * @param parsText  durchsuchter text
	 * @param key		schluessel nach dem gesucht wird
	 * @return komplettes ergebnissobjekt oder null
	 * @throws myException
	 */
	public PdParserResult parse(String parsText, String key) throws myException {
		
		if (!S.isAllFull(parsText,key)) {
			throw new myException(this,"ParsText and key MUST NOT BE NULL !!");
		}
		
		PdParserResult result = null;
		
		int iPosStart = parsText.indexOf(this.leftSeparator+key+this.separator);
		
		if (iPosStart>=0) {
			
			int iPosEnd = iPosStart;
			
			//jetzt den endpunkt suchen
			for (int i=iPosStart;i<parsText.length();i++) {
				if (parsText.substring(i, i+1).equals(this.rightSeparator)) {
					iPosEnd = i;
					break;
				}
			}

			if (iPosEnd>iPosStart) {
				String sBlock = parsText.substring(iPosStart+1, iPosEnd);
				
				
				if (S.isFull(sBlock)) {
					result = new PdParserResult();
					result.startPoint=iPosStart;
					result.lenghtOfBlock=sBlock.length();
					result.key=key;
					result.value=sBlock.substring((key+this.separator).length());
					result.parseTextWithoutBlock=parsText.replace(this.leftSeparator+key+this.separator+result.value+this.rightSeparator, "");
				}
			}
		}
		
		return result;
	}
	
	
	
	
}
