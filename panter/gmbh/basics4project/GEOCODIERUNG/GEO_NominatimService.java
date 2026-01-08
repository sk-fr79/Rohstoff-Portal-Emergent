/**
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Vector;

import panter.gmbh.BasicInterfaces.Check;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindNearestPtFromGeoMidPt;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 *
 */
public class GEO_NominatimService {

	private enum variante {
		plz_ort
		,plz
		,ort
	}


	private VEK<String> urls = new VEK<>();
	private String idAdresseUf = null;
	private boolean generateWasCalled = false;

	String hnr = null;
	String strasse = null;
	String plz = null;
	String ort = null;
	String land_iso = null;
	

	/**
	 * fills url-string
	 * @param recAdresse
	 * @param error_hm
	 * @return
	 * @throws myException
	 */
	public GEO_NominatimService _generateNominatimUrls(Rec20 recAdresse,GEO_ErrorMap error_hm) throws myException {

		this.idAdresseUf = recAdresse.get_ufs_dbVal(ADRESSE.id_adresse);

		//hier die einzelteile
		hnr = recAdresse.get_fs_dbVal(ADRESSE.hausnummer,"");
		strasse = recAdresse.get_fs_dbVal(ADRESSE.strasse,"");
		plz = recAdresse.get_fs_dbVal(ADRESSE.plz,"");
		ort = recAdresse.get_fs_dbVal(ADRESSE.ort,"");

		land_iso = null;
		Rec20 rLand = recAdresse.get_up_Rec20(LAND.id_land);
		if (rLand!=null) {
			land_iso = rLand.getUfs(LAND.iso_country_code,"");
		}

		Check<String> entscheider = (teststr)-> {return S.isFull(teststr)&& !this.urls.contains(teststr);};

		this.urls.clear();
		
		// Aufruf weiter leiten an basisfunktion
		this._generateNominatimUrls(this.idAdresseUf, hnr, strasse, plz, ort, land_iso, error_hm);

		return this;
	}

	
	
	/**
	 * 
	 * @param id_adresse_uf
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param land_iso
	 * @param error_hm
	 * @return
	 * @throws myException
	 */
	public GEO_NominatimService _generateNominatimUrls_ori(String id_adresse_uf, String hnr,String strasse,String plz,String ort, String land_iso, GEO_ErrorMap error_hm)  throws myException {

		this.idAdresseUf=id_adresse_uf;

		Check<String> entscheider = (teststr)-> {return S.isFull(teststr)&& !this.urls.contains(teststr);};

		
		this.urls.clear();
		this.urls._addValidated(entscheider, this.generateWithoutQuery(true, 	variante.plz_ort, 	this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//klassischer fall
		this.urls._addValidated(entscheider, this.generateWithoutQuery(false, 	variante.plz_ort, 	this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//ort nur bis zum ersten trenner
		this.urls._addValidated(entscheider, this.generateWithoutQuery(true, 	variante.plz, 		this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//nur plz
		this.urls._addValidated(entscheider, this.generateWithoutQuery(true, 	variante.ort, 		this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//nur ort
		this.urls._addValidated(entscheider, this.generateWithoutQuery(false, 	variante.ort, 		this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/)); 	//nur verkuerzter ort

		//DEBUG._print(urls);

		return this;
	}

	
	
	/**
	 * erweiterte Such nach allen möglichen Kombinationen
	 * 
	 * @param id_adresse_uf
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param land_iso
	 * @param error_hm
	 * @return
	 * @throws myException
	 */
	public GEO_NominatimService _generateNominatimUrls(String id_adresse_uf, String hnr,String strasse,String plz,String ort, String land_iso, GEO_ErrorMap error_hm)  throws myException {

		this.idAdresseUf=id_adresse_uf;

		Check<String> entscheider = (teststr)-> {return S.isFull(teststr)&& !this.urls.contains(teststr);};

		// Vorbereiten des Ortes, Strasse und PLZ
		Vector<String> vOrte = new Vector<>();
		Vector<String> vStrasse = new Vector<>();
		Vector<String> vPlz= new Vector<>();
		
		String norm_ort = GEO_Utils.normalizeOrt(ort);
		String norm_str = GEO_Utils.normalizeStreet(strasse);
		String norm_hausnr = GEO_Utils.normalizeHausnr(hnr);
		String norm_plz = plz;
		
		String[] orte = norm_ort.split(" ");
		String[] strassen = norm_str.split(" ");
		String[] plzs = norm_plz.split(" ");
		
		for (int i= orte.length; i > 0; i--){
			vOrte.add( String.join("+", Arrays.copyOfRange(orte, 0, i)  ));
		}
		if (vOrte.size() == 0) vOrte.add("");
		
		for (int i= strassen.length; i > 0; i--){
			vStrasse.add( String.join("+", Arrays.copyOfRange(strassen, 0, i)  ));
		}
		if (vStrasse.size() == 0) vStrasse.add("");
		
		for (int i= plzs.length; i > 0; i--){
			vPlz.add( String.join("", Arrays.copyOfRange(plzs, 0, i)  ));
		}
		vPlz.add("");
		
		

		
		this.urls.clear();
		
		this.urls._addValidated(entscheider, this.generateQuery(true, variante.plz_ort, this.idAdresseUf, norm_hausnr, strasse, plz, norm_ort, land_iso));
		this.urls._addValidated(entscheider, this.generateQuery(true, variante.plz_ort, this.idAdresseUf, norm_hausnr, strasse, plz, norm_ort, ""));

		//vordefinierte Such-Kombinationen
		this.urls._addValidated(entscheider, this.generateWithoutQuery(true, 	variante.plz_ort, 	this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//klassischer fall
		this.urls._addValidated(entscheider, this.generateWithoutQuery(false, 	variante.plz_ort, 	this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//ort nur bis zum ersten trenner
		this.urls._addValidated(entscheider, this.generateWithoutQuery(true, 	variante.plz, 		this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//nur plz
		this.urls._addValidated(entscheider, this.generateWithoutQuery(true, 	variante.ort, 		this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/));  	//nur ort
		this.urls._addValidated(entscheider, this.generateWithoutQuery(false, 	variante.ort, 		this.idAdresseUf, hnr, strasse, plz, ort, land_iso/*, error_hm*/)); 	//nur verkuerzter ort

		
		// erweiterte Suchkombinationen
		// komplett
		this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, norm_hausnr, vStrasse.get(0), vPlz.get(0), vOrte.get(0), land_iso/*, error_hm*/));
		
		// Strassen durchiterieren (mit und ohne hausnummer)
		for(String s : vStrasse){
			this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, norm_hausnr, s, vPlz.get(0), vOrte.get(0), land_iso/*, error_hm*/));  	
		}

		// Strassen durchiterieren (mit und ohne hausnummer)
		for(String s : vStrasse){
			this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, "", s, vPlz.get(0), vOrte.get(0), land_iso/*, error_hm*/));  	
		}

		
		// orte durchiterieren (mit und ohne hausnummer)
		for(String s : vOrte){
			this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, norm_hausnr, vStrasse.get(0), vPlz.get(0), s, land_iso/*, error_hm*/));
			this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, "", vStrasse.get(0), vPlz.get(0), s, land_iso/*, error_hm*/));
		}
		
		// alles durchiterieren
		for (String s1: vOrte){
			for (String s2: vStrasse){
				for (String s3: vPlz){
					this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, norm_hausnr, s2, s3, s1, land_iso/*, error_hm*/));
					this.urls._addValidated(entscheider, this.generateWithoutQuery_basic(this.idAdresseUf, "", s2, s3, s1, land_iso/*, error_hm*/));
				}
			}
		}
		
		// Fehler nur dann, wenn keine URL die Bedingungen erfüllt
		if (this.urls.size() == 0){
			error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
		}
		
		DEBUG._print(urls);

		return this;
	}


	
	
	/**
	 * prüft ob die angaben sinnvoll sind
	 * 
	 * EDIT: error_hm wird nicht hier befüllt, da sonst immer der erste der Fehler eingetragen wird, obwohl danach noch korrekte Prüfungen stattfinden, und
	 * man den Errorcode nicht überschreiben kann. 
	 *
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param iso_laender_code
	 * @return
	 * @throws myException
	 */
	private boolean checkAngabenSufficient(String hnr,String strasse,String plz,String ort, String iso_laender_code/*, GEO_ErrorMap error_hm*/) throws myException {
		
		boolean b = true;

		if (S.NN(strasse).length()<3) {
			b=false;
		} else if (S.NN(ort).length()<2 && S.NN(plz).length()<2) {
			b=false;
		} else if (S.NN(iso_laender_code).length()<2) {
			b=false;
		}
		// Eintragen des Errors erfolgt im aufrufenden Modul, spezifischer, da mehrere URLs für eine Adresse geprüft werden müssen
		
//		if (!b && !error_hm.containsErrorForId(this.idAdresseUf)) {
//			error_hm._add(ENUM_GEO_Error.ERROR_RECORD, this.idAdresseUf);
//		}
		return b;
	}

	/**
	 * @since 25.01.2019
	 * @author sebastien
	 * 
	 * prüft ob die angaben sinnvoll sind - version ohne laendercode
	 * 
	 * EDIT: error_hm wird nicht hier befüllt, da sonst immer der erste der Fehler eingetragen wird, obwohl danach noch korrekte Prüfungen stattfinden, und
	 * man den Errorcode nicht überschreiben kann. 
	 *
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param iso_laender_code
	 * @return
	 * @throws myException
	 */
	private boolean checkAngabenSufficient_v2(String hnr,String strasse,String plz,String ort) throws myException {
		
		boolean b = true;

		if (S.NN(strasse).length()<3) {
			b=false;
		} else if (S.NN(ort).length()<2 && S.NN(plz).length()<2) {
			b=false;
		} 
		return b;
	}

	/**
	 * @param fullOrt  wenn false, dann wird nur der erste teil eines ortes bis zum ersten leerzeichen genommen)
	 * @param id_adresse_uf  (can be null if usered without existing record, then id is registrated as 0)
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param error_hm
	 * @return correct string or null
	 * @throws myException
	 */
	private String generateWithoutQuery_basic(  String id_adresse_uf, String hnr,String strasse,String plz,String ort, String iso_laender_code/*, GEO_ErrorMap error_hm*/)  throws myException {

		this.generateWasCalled=true;

		if (this.checkAngabenSufficient(hnr, strasse, plz, ort, iso_laender_code/*, error_hm*/)) {

			this.idAdresseUf =id_adresse_uf;

			VEK<String> vHnrStrasse = new VEK<String>()	._addValidated(   val->{ return S.isFull(val); }   , hnr, strasse);
			String sStrasse = bibALL.Concatenate(vHnrStrasse, "+", "");

			String s_ganz=this.buildUrlWithoutQuery(sStrasse, plz, ort, iso_laender_code);
			
			return s_ganz;

		} else {
//			error_hm._add(ENUM_GEO_Error.ERROR_RECORD,S.NN(id_adresse_uf,"0"));
			return null;
		}
	}





	/**
	 * @param fullOrt  wenn false, dann wird nur der erste teil eines ortes bis zum ersten leerzeichen genommen)
	 * @param id_adresse_uf  (can be null if usered without existing record, then id is registrated as 0)
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param error_hm
	 * @return correct string or null
	 * @throws myException
	 */
	private String generateWithoutQuery(boolean fullOrt, variante var,  String id_adresse_uf, String hnr,String strasse,String plz,String ort, String iso_laender_code/*, GEO_ErrorMap error_hm*/)  throws myException {

		this.generateWasCalled=true;

		if (this.checkAngabenSufficient(hnr, strasse, plz, ort, iso_laender_code/*, error_hm*/)) {

			this.idAdresseUf =id_adresse_uf;

			hnr=GEO_Utils.normalizeHausnr(hnr);
			strasse=GEO_Utils.normalizeStreet(strasse);
			strasse=strasse.replace(" ", "+");

			VEK<String> vHnrStrasse = new VEK<String>()	._addValidated(   val->{ return S.isFull(val); }   , hnr, strasse);

			plz=S.clean(plz);
			plz = plz.replaceAll(" ", "%20");
			plz=S.clean(plz);
			
			if (fullOrt) {
				ort=GEO_Utils.normalizeOrt(ort);
			} else {
				ort=GEO_Utils.normalizeOrt(ort);
				if (ort.contains(" ")) {
					ort = ort.substring(0,ort.indexOf(" "));
				}
			}
			ort = ort.replace(" ", "+");

			String s_ganz = "";

			switch (var) {
			case plz_ort :
				s_ganz=this.buildUrlWithoutQuery(bibALL.Concatenate(vHnrStrasse, "+", ""), plz, ort, iso_laender_code);
				break;
			case plz :
				s_ganz=this.buildUrlWithoutQuery(bibALL.Concatenate(vHnrStrasse, "+", ""), plz, "", iso_laender_code);
				break;
			case ort :
				s_ganz=this.buildUrlWithoutQuery(bibALL.Concatenate(vHnrStrasse, "+", ""), "", ort, iso_laender_code);
				break;
			}


			return s_ganz;

		} else {
//			error_hm._add(ENUM_GEO_Error.UNDEFINED_ERROR,S.NN(id_adresse_uf,"0"));
			return null;
		}
	}

	
	/**
	 * @since 25.01.2019
	 * @author sebastien
	 * @param fullOrt  wenn false, dann wird nur der erste teil eines ortes bis zum ersten leerzeichen genommen)
	 * @param id_adresse_uf  (can be null if usered without existing record, then id is registrated as 0)
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param error_hm
	 * @return correct string or null
	 * @throws myException
	 */
	private String generateQuery(boolean fullOrt, variante var,  String id_adresse_uf, String hnr,String strasse,String plz,String ort, String iso_laender_code/*, GEO_ErrorMap error_hm*/)  throws myException {

		this.generateWasCalled=true;

		if (this.checkAngabenSufficient_v2(hnr, strasse, plz, ort)) {

			this.idAdresseUf =id_adresse_uf;

			hnr=GEO_Utils.normalizeHausnr(hnr);
			strasse=GEO_Utils.normalizeStreet(strasse);
			strasse=strasse.replace(" ", "+");

			VEK<String> vHnrStrasse = new VEK<String>()	._addValidated(   val->{ return S.isFull(val); }   , hnr, strasse);
			plz = plz.replaceAll(" ", "%20");
			plz=S.clean(plz);
			if (fullOrt) {
				ort=GEO_Utils.normalizeOrt(ort);
			} else {
				ort=GEO_Utils.normalizeOrt(ort);
				if (ort.contains(" ")) {
					ort = ort.substring(0,ort.indexOf(" "));
				}
			}
			ort = ort.replace(" ", "+");

			String s_ganz = "";

			switch (var) {
			case plz_ort :
				s_ganz=this.buildUrlQuery(bibALL.Concatenate(vHnrStrasse, "+", ""), plz, ort, iso_laender_code);
				break;
			case plz :
				s_ganz=this.buildUrlQuery(bibALL.Concatenate(vHnrStrasse, "+", ""), plz, "", iso_laender_code);
				break;
			case ort :
				s_ganz=this.buildUrlQuery(bibALL.Concatenate(vHnrStrasse, "+", ""), "", ort, iso_laender_code);
				break;
			}


			return s_ganz;

		} else {
//			error_hm._add(ENUM_GEO_Error.UNDEFINED_ERROR,S.NN(id_adresse_uf,"0"));
			return null;
		}
	}
	

	private String buildUrlWithoutQuery(String hnr_strasse_block, String plz, String ort, String iso_laender_code) throws myException {
		String code = ENUM_MANDANT_CONFIG.NOMINATIM_URL.getUncheckedValue() ;
		if(!ENUM_MANDANT_CONFIG.NOMINATIM_URL.getUncheckedValue().endsWith("?")) {
			code = code + "?";
		}
		code = code  
				+ ("street="+hnr_strasse_block+"&") 
				+ (S.isFull(ort)?("city="+ort+"&"):"")
				+ (S.isFull(plz)?("postalcode="+plz+"&"):"")
				+ ("countrycodes="+iso_laender_code+"&")
				+ ("format=" +  ENUM_GEO_OUTPUT_FORMAT.XML.user_text()+"&")
				+ ("addressdetails=1"+"&")
				+ ("polygon=1")
				;


		return code;
	}

	
	private String buildUrlQuery(String hnr_strasse_block, String plz, String ort, String iso_laender_code) throws myException {
		String code = ENUM_MANDANT_CONFIG.NOMINATIM_URL.getUncheckedValue() ;
		if(!ENUM_MANDANT_CONFIG.NOMINATIM_URL.getUncheckedValue().endsWith("?")) {
			code = code + "?";
		}
		code = code  
				+ ("q="+hnr_strasse_block+"%2C") 
				+ (S.isFull(ort)?(ort+"%2C"):"")
				+ (S.isFull(plz)?(plz+"%2C"):"")
				+ (S.isFull(iso_laender_code)?(iso_laender_code+"%2C"):"")
				+ ("&format=" +  ENUM_GEO_OUTPUT_FORMAT.XML.user_text()+"&")
				+ ("addressdetails=1"+"&")
				+ ("polygon=1")
				;


		return code;
	}

//	private String normalizeOrt(String ort) {
//		ort = ort.replace("/", " ");
//		ort = ort.replace("-", " ");
//		ort = ort.replace(".", " ");
//		ort = this.replaceUmlauteToUpper(ort);
//
//		ort = S.clean(ort);
//		return ort;
//	}
//
//
//	private String normalizeStreet(String strasse) {
//		strasse = this.replaceUmlauteToUpper(strasse);
//		strasse =strasse.replace("STR.", "STR ");
//		strasse =strasse.replace("STR ", "STRASSE ");
//		strasse =strasse.replace("STRAßE", "STRASSE");
//		strasse=S.clean(strasse);
//		return strasse;
//	}
//
//
//	private String replaceUmlauteToUpper(String orig) {
//		String ret = orig;
//		ret = ret.toUpperCase();
//		ret = ret.replace("ä", "AE");
//		ret = ret.replace("ö", "OE");
//		ret = ret.replace("ü", "UE");
//		ret = ret.replace("Ä", "AE");
//		ret = ret.replace("Ö", "OE");
//		ret = ret.replace("Ü", "UE");
//		ret = ret.replace("ß", "SS");
//		return ret;
//	}


//	/**
//	 * angaben 10-12 werden zu 10
//	 * @param hnr
//	 * @return
//	 */
//	private String normalizeHausnr(String hnr) {
//		if (hnr.contains("-")) {
//			hnr = hnr.substring(0,hnr.indexOf("-"));
//		}
//		hnr=S.clean(hnr);
//		hnr = hnr.replace(" ", "");
//		
//		return hnr;
//	}


//	/**
//	 * 
//	 * @param error_hm
//	 * @return when successfull one geolocation in vektor
//	 * @throws myException
//	 */
//	public GEO_Location findGeoLocation(GEO_ErrorMap error_hm) throws myException {
//
//		if (this.urls.size()>0) {
//
//				//DEBUG._print("@@@Roh-URL:"+this.url);
//				//die varianten von oben nach unten durchsuchen, bis er etwas findet
//
//				ENUM_GEO_Error  lastError = null;
//
//				for (String c_url: this.urls) {
//					try {
//						
//						GEO_Locations 	vLocations = new GEO_Locations();
//						
//						
//						URL nominatim_url= new URL(c_url);
//						
//						//verbindung oeffnen
//						URLConnection nominatim_connection = nominatim_url.openConnection();
//						
//						BufferedReader in = new BufferedReader(new InputStreamReader(nominatim_connection.getInputStream()));
//						
//						String inputLine;
//						
//						StringBuffer buff = new StringBuffer();
//						
//						while ((inputLine = in.readLine()) != null) {
//							buff.append(inputLine);
//						}
//						if(buff !=null && buff.length()>0) {
//							GEO_Parser_XML parser = new GEO_Parser_XML(this.idAdresseUf, buff).parse(error_hm);
//							vLocations._a(parser.get_locations());
//						}
//						in.close();
//						
//						//jetzt nachsehen, ob er etwas korrektes gefunden hat
//						GEO_Location loc = vLocations.getSingleLocation();
//						
//						
//						if (loc!=null) {
//							//eine url-variante liefert einen korrekten wert
//							//error_hm._add(ENUM_GEO_Error.OK, this.idAdresseUf);
//							
//							return loc;
//						} else {
//							lastError = vLocations.getLastError();
//						}
//					} catch (MalformedURLException e) {
//						error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
//					} catch (IOException e) {
//						error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
//					}
//				}
//
//				//wenn er hierher kommt, dann hat er nix gefunden und der letzte fehler wird protokolliert
//				if (lastError !=null) {
//					error_hm._add(lastError, this.idAdresseUf);
//				}
//
//		} else {
//			if (!this.generateWasCalled) {
//				throw new myException(this, "Please call _generateNominatimUrl() before !");
//			}
//		}
//
//		return null;
//	}



	/**
	 * 
	 * @param error_hm
	 * @return when successfull one geolocation in vektor
	 * @throws myException
	 */
	public GEO_Location findGeoLocation(GEO_ErrorMap error_hm) throws myException {

		ENUM_GEO_Error  lastError = null;
		GEO_Location loc = null;
		
		
		GEO_ErrorMap localErrors = new GEO_ErrorMap("temp");
		
		GEO_Locations vLocations = findGeoLocations(localErrors);
		
		if (vLocations != null && vLocations.size() > 0){
			vLocations.setSearchDataForWeight(this.idAdresseUf,this.hnr,this.strasse, this.plz, this.ort, this.land_iso, 60);
		
			loc = vLocations.getSingleLocation();
//			loc = vLocations.getSingleLocationByWeight();
			
			lastError = vLocations.getLastError();
			
			if (loc!=null) {
				//eine url-variante liefert einen korrekten wert
				//error_hm._add(ENUM_GEO_Error.OK, this.idAdresseUf);
			
				return loc;
			}
			else {
				lastError = vLocations.getLastError();
			}
			
			
			
			//wenn er hierher kommt, dann hat er nix gefunden und der letzte fehler wird protokolliert
			if (lastError !=null) {
				
				//error_hm._add(lastError, this.idAdresseUf);
				
				//TODO: 2.04.2019 - when scan of adresses, find the nearest point of a calculated point.
				if(lastError == ENUM_GEO_Error.NO_EXACT_LOCALISATION) {
					GEO_Location loc_2 = new PdServiceFindNearestPtFromGeoMidPt().find_nearest_point_from_geographic_midpoint(vLocations);
					if(loc_2 != null) {
						return loc_2;
					}else {
						error_hm._add(lastError, this.idAdresseUf);
					}
				}else {
					error_hm._add(lastError, this.idAdresseUf);
				}				
				
			}
			
		} else {
			// keine Adresse gefunden
			error_hm._add(ENUM_GEO_Error.NO_COORDINATE, this.idAdresseUf);
		}

		return null;
	}

	
	

	/**
	 * 
	 * @author martin
	 * @date 15.10.2018
	 *
	 * methode, um auf mehrfache oder weiter als die konstanten entfernte zusammenhaenge zu reagieren
	 * 
	 * @param error_hm
	 * @return vector of geolocations or null (if nothing found)
	 * @throws myException
	 */
	public GEO_Locations findGeoLocations(GEO_ErrorMap error_hm) throws myException {

		if (this.urls.size()>0) {

				//DEBUG._print("@@@Roh-URL:"+this.url);

				//die varianten von oben nach unten durchsuchen, bis er etwas findet

				for (String c_url: this.urls) {
					try {
						GEO_Locations 	vLocations = new GEO_Locations();
						
						
						URL nominatim_url= new URL(c_url);
						
						//verbindung oeffnen
						URLConnection nominatim_connection = nominatim_url.openConnection();
						
						BufferedReader in = new BufferedReader(new InputStreamReader(nominatim_connection.getInputStream()));
						
						String inputLine;
						
						StringBuffer buff = new StringBuffer();
						
						while ((inputLine = in.readLine()) != null) {
							buff.append(inputLine);
						}
						if(buff !=null && buff.length()>0) {
							GEO_Parser_XML parser = new GEO_Parser_XML(this.idAdresseUf, buff).parse(error_hm);
							vLocations._a(parser.get_locations());
						}
						in.close();
						
						// wenn irgendeine location gefunden wurde 
						if (vLocations.size()>0) {
							return vLocations;
						} 

					} catch (MalformedURLException e) {
						error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
					} catch (IOException e) {
						error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
					}
				}


		} else {
			if (!this.generateWasCalled) {
				throw new myException(this, "Please call _generateNominatimUrl() before !");
			}
		}

		return null;
	}

	
	
	
	
}
