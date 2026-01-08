package rohstoff.Echo2BusinessLogic.LAGER;


public class LAG_Const {
	
	// Stati die die Lagerpreise annehmen können bei der Ermittlung der Lagerpreise
	public static final String STATUS_PREIS_LAGER_OK = "OK";
	public static final String STATUS_PREIS_LAGER_FEHLER = "ERROR";  // Preis nicht ermittelbar ( n-m-Fuhren)
	public static final String STATUS_PREIS_LAGER_LAGER = "LAGER";  // Lager-Durchschnittspreis
	public static final String STATUS_PREIS_LAGER_FUHRE = "FUHRE";  // Preis aus Fuhre
	public static final String STATUS_PREIS_LAGER_RECHNUNG = "RECHNUNG";  // Preis aus Rechnung
	public static final String STATUS_PREIS_LAGER_KONTRAKT = "KONTRAKT";  // Preis aus Kontrakt
	
}
