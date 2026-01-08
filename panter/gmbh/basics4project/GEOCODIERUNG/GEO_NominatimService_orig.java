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

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 *
 */
public class GEO_NominatimService_orig {

	private String url = null;
	private String idAdresseUf = null;
	private boolean generateWasCalled = false;


	public String getUrl() {
		return url;
	}



	/**
	 * fills url-string
	 * @param recAdresse
	 * @param error_hm
	 * @return
	 * @throws myException
	 */
	public GEO_NominatimService_orig _generateNominatimUrl(Rec20 recAdresse,GEO_ErrorMap error_hm) throws myException {

		this.idAdresseUf = recAdresse.get_ufs_dbVal(ADRESSE.id_adresse);

		//hier die einzelteile
		String hnr = recAdresse.get_fs_dbVal(ADRESSE.hausnummer,"");
		String strasse = recAdresse.get_fs_dbVal(ADRESSE.strasse,"");
		String plz = recAdresse.get_fs_dbVal(ADRESSE.plz,"");
		String ort = recAdresse.get_fs_dbVal(ADRESSE.ort,"");

		String land_iso = null;
		Rec20 rLand = recAdresse.get_up_Rec20(LAND.id_land);
		if (rLand!=null) {
			land_iso = rLand.getUfs(LAND.iso_country_code,"");
		}



		this.url= this.generate(this.idAdresseUf, hnr, strasse, plz, ort, land_iso, error_hm);

		return this;
	}




	/**
	 * fills url-string
	 * @param id_adresse_uf
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param error_hm
	 * @return
	 * @throws myException
	 */
	public GEO_NominatimService_orig _generateNominatimUrl(String id_adresse_uf, String hnr,String strasse,String plz,String ort, String iso_laender_code, GEO_ErrorMap error_hm)  throws myException {

		this.idAdresseUf=id_adresse_uf;

		this.url=  this.generate(id_adresse_uf, hnr, strasse, plz, ort, iso_laender_code, error_hm);

		return this;
	}


	private boolean checkAngabenSufficient(String hnr,String strasse,String plz,String ort, String iso_laender_code, GEO_ErrorMap error_hm) throws myException {
		boolean b = true;

		if (S.NN(strasse).length()<3) {
			b=false;
		} else if (S.NN(ort).length()<2 && S.NN(plz).length()<2) {
			b=false;
		} else if (S.NN(iso_laender_code).length()<2) {
			b=false;
		}

		if (!b && !error_hm.containsErrorForId(this.idAdresseUf)) {
			error_hm._add(ENUM_GEO_Error.ERROR_RECORD, this.idAdresseUf);
		}
		return b;
	}





	/**
	 * 
	 * @param id_adresse_uf  (can be null if usered without existing record, then id is registrated as 0)
	 * @param hnr
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param error_hm
	 * @return correct string or null
	 * @throws myException
	 */
	private String generate(String id_adresse_uf, String hnr,String strasse,String plz,String ort, String iso_laender_code, GEO_ErrorMap error_hm)  throws myException {

		this.generateWasCalled=true;

		if (this.checkAngabenSufficient(hnr, strasse, plz, ort, iso_laender_code, error_hm)) {

			this.idAdresseUf =id_adresse_uf;

			String addr_block = S.NN(hnr);

			if(S.isFull(addr_block)) {
				addr_block = addr_block + "+" + strasse;
			}else {
				addr_block = strasse;
			}

			boolean is_ok = true;

			if(S.isFull(addr_block)) {
				addr_block = addr_block.replace(" ", "+");
			}else {
				error_hm._add(ENUM_GEO_Error.ERROR_RECORD,S.NN(id_adresse_uf,"0"));
				return null;
			}

			String plz_ort_block ="%2C+" + plz;

			plz_ort_block = plz_ort_block + (S.isFull(plz_ort_block)?"+":"")+ ort;

			if(plz_ort_block.equals("%2C")){
				error_hm._add(ENUM_GEO_Error.ERROR_RECORD,S.NN(id_adresse_uf,"0"));
				return null;
			}



			if(is_ok) {
				String net_abfrage =  ENUM_MANDANT_CONFIG.NOMINATIM_URL.getUncheckedValue() ;
				if(! ENUM_MANDANT_CONFIG.NOMINATIM_URL.getUncheckedValue().endsWith("?")) {
					net_abfrage = net_abfrage + "?";
				}
				net_abfrage = net_abfrage+"q="
						+ addr_block +"%2C+"
						+ plz_ort_block +"&countrycodes="+iso_laender_code
						+ "&format=" +  ENUM_GEO_OUTPUT_FORMAT.XML.user_text()
						+ "&addressdetails=1"
						+ "&polygon=1";

				DEBUG._print("@@@NOMINATIM-URL: "+net_abfrage);

				return net_abfrage;

			} else {
				error_hm._add(ENUM_GEO_Error.UNDEFINED_ERROR,S.NN(id_adresse_uf,"0"));
				return null;
			}
		} else {
			return null;
		}
	}







	/**
	 * 
	 * @param error_hm
	 * @return when successfull one or multi geolocations in vektor
	 * @throws myException
	 */
	public VEK<GEO_Location> findGeoLocations(GEO_ErrorMap error_hm) throws myException {
		VEK<GEO_Location> vLocations = new VEK<GEO_Location>();

		if (S.isFull(this.url)) {
			try {

				//DEBUG._print("@@@Roh-URL:"+this.url);

				URL nominatim_url= new URL(url);

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

			} catch (MalformedURLException e) {
				error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
			} catch (IOException e) {
				error_hm._add(ENUM_GEO_Error.ERROR_URL, this.idAdresseUf);
			}
		} else {
			if (!this.generateWasCalled) {
				throw new myException(this, "Please call _generateNominatimUrl() before !");
			}
		}
		return vLocations;
	}


}
