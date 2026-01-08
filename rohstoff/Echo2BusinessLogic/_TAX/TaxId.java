package rohstoff.Echo2BusinessLogic._TAX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a european tax id, with leading contry code and trailing number 
 * of various length. Constructor allows parsing such a string or being 
 * called with the string parts already separated.
 * @author nils
 *
 */
public class TaxId {
	private String countryCode;
	private String number;
	
	public TaxId(String taxId) {
	   taxId = taxId.trim();
       String patternString1 = "^([A-Za-z]{2,2})\\s*?([0-9A-Za-z]{5,12})$";

       Pattern pattern = Pattern.compile(patternString1);
       Matcher matcher = pattern.matcher(taxId);
       try {
           matcher.matches();
    	   setCountryCode(matcher.group(1));
           setNumber(matcher.group(2));
       } catch (IllegalStateException e) {
    	   throw new IllegalArgumentException("Could not parse taxId into country code and number.", e);
       }
	}
	
	public TaxId(String countryCode, String number)  {
		setCountryCode(countryCode);
		setNumber(number);
	}

	private void setNumber(String number) {
		if (number == null || number.length() == 0 || !number.trim().matches("[0-9A-Za-z]{5,12}")) {
			throw new IllegalArgumentException("A tax ID must have an alphnumeric trailing number between 5 and 12 characters.");
		}
		this.number = number.trim();
	}

	private void setCountryCode(String countryCode) {
		if (countryCode == null || countryCode.length() == 0 || !countryCode.trim().matches("[A-Za-z]{2}")) {
			throw new IllegalArgumentException("A tax ID must have an alphabetic leading country code.");
		}
		this.countryCode = countryCode.trim().toUpperCase();
	}
	
	public String toString() {
		return this.countryCode+this.number;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public String getNumber() {
		return number;
	}
}
