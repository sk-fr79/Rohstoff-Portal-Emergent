/**
 * rohstoff.Echo2BusinessLogic.WUT.HANDLER
 * @author manfred
 * @date 22.11.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.WUT.HANDLER;

/**
 * @author manfred
 * @date 22.11.2016
 *
 */
public enum ENUM_WUT_Command {
	input("input"),
	output("output"),
	counter("counter"),
	counterclear("counterclear"),
	allout("allout")
	;
	
	private String commandstring;
	
	ENUM_WUT_Command(String command){
		commandstring = command;
	}
	
	

	/**
	 * Allegmeines Kommando mit Passwort
	 * @author manfred
	 * @date 22.11.2016
	 *
	 * @param command
	 * @param password
	 * @return
	 */
	public String get_command( String password){
		return get_command( password, null) ;
	};

	

	/**
	 * Kommando mit Positionsnummer und Passwort
	 * Passwort null, wenn leer
	 * @author manfred
	 * @date 22.11.2016
	 *
	 * @param command
	 * @param pin
	 * @param password
	 * @return
	 */
	public String get_command( String password , Integer pin){
		String sPin = "";
		if (pin != null){
			sPin = pin.toString();
		}
		
		String sPassword = "?PW=" + (password != null ? password   : "") + "&";
		return "GET /" + commandstring + sPin +  sPassword;
	};
}
