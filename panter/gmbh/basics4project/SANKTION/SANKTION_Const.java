package panter.gmbh.basics4project.SANKTION;

import java.math.BigDecimal;

import panter.gmbh.basics4project.ENUM_REGISTRY;
import panter.gmbh.indep.exceptions.myException;

public class SANKTION_Const {
	
	/**
	 * define all words with a length superior or equals at x characters 
	 * for ex. 3 characters: <i>"aaa"</i>,  <i>"bbbb"</i>, ....  </br>
	 * <b>default value</b> = 3
	 */
	public static int minimal_length() throws myException{
		return ENUM_REGISTRY.SANKTION_MIN_WORD_LENGTH.getBdValue(new BigDecimal(3)).intValue();
	}
	
	/**
	 * define a Vector with max n words
	 * for ex. VEK {<i>word 1</i>, <i>word 2</i>, ..., <i>word n</i>} </br>
	 * <b>default value</b> = 4
	 */
	public static int max_word_length() throws myException{
		return ENUM_REGISTRY.SANKTION_MAX_VEKTOR_LENGTH.getBdValue(new BigDecimal(4)).intValue();
	}
}
