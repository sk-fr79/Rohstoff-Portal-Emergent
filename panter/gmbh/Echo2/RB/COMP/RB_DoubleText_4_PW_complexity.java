package panter.gmbh.Echo2.RB.COMP;

public class RB_DoubleText_4_PW_complexity {

	public static char[] SPECIAL_CHARACTER_LIST = "&~#|`-_)('/?,;:.".toCharArray();

	public static int countUppercaseInPwd(String password){
		int counter = 0;
		if(password != null){
			char[] pwdInTab = password.toCharArray();
			for(char p : pwdInTab){
				if(Character.isUpperCase(p)) counter++;
			}
			return counter;
		}return 0;
	}

	public static int countLowercaseInPassword(String password){
		int counter = 0;
		if(password != null){
			char[] pwdInTab = password.toCharArray();
			for(char p : pwdInTab){
				if(Character.isLowerCase(p)) counter++;
			}
			return counter;
		}return 0;
	}

	public static int countNumberInPassword(String password){
		int counter = 0;
		if(password != null){
			char[] pwdInTab = password.toCharArray();
			for(char p : pwdInTab){
				if(Character.isDigit(p)) counter++;
			}
			return counter;
		}return 0;
	}

	public static int countSpecialCharacterInPassword(String password){
		int counter = 0;
		if(password != null){
			char[] pwdInTab = password.toCharArray();
			for(char p : pwdInTab){
				for(char specialCharacter: SPECIAL_CHARACTER_LIST){
					if(p == specialCharacter) counter++;		
				}
			}
			return counter;
		}return 0;
	}

	/**
	 * return always true
	 * @param password
	 * @return
	 */
	public static boolean isNotSecure(String password){
		return true;
	}

	/**
	 * Check if the password meet the following requirements:
	 * <ul>
	 * <li>	1 Upper case
	 * <li>	or 1 Digits (between 0 and 9)
	 * </ul>
	 * @param password
	 * @return <i>true</i> if enough secured
	 */
	public static boolean isMediumSecure(String password){
		if(countNumberInPassword(password)>0 || countUppercaseInPwd(password)>0 ) return true;
		return false;
	}
	
	/**
	 * Check if the password meet the following requirements:
	 * <ul>
	 * <li>	1 Upper case and 
	 * <li>	and 1 Digits (between 0 and 9) 
	 * </ul>
	 * @param password
	 * @return <i>true</i> if enough secured
	 */
	public static boolean isStrongSecured(String password){
		if((countNumberInPassword(password)>0 && countUppercaseInPwd(password)>0) ) return true;
		return false;
	}

	/**
	 * Check if the password meet the following requirements:
	 * <ul>
	 * <li>	1 Upper case
	 * <li>	and 1 Digits (between 0 and 9)
	 * <li>	and 1 Special characters ( <b>&~#|`-_)('/?,;:.</b> )
	 * </ul>
	 * @param password
	 * @return <i>true</i> if enough secured
	 */
	public static boolean isVeryStrongSecured(String password){
		if((countNumberInPassword(password)>0 && 
				countUppercaseInPwd(password)>0) && 
				countSpecialCharacterInPassword(password)>0 
				) return true;
		return false;
	}

	public enum SecurityLevel{
		LOW, 
		MEDIUM,  		//passwort mit an mindestens 1 Grossbuchstabe oder 1 Zahl
		STRONG, 		//passwort mit an mindestens 1 Grossbuchstabe und 1 Zahl
		VERY_STRONG;	//passwort mit an mindestens 1 Grossbuchstabe, 1 Zahl und 1 spezial zeichnen -> &~#|`-_)('/?,;:.
	}
}
