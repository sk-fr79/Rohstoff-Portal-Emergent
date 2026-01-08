package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * An Adress of a Debitor/Kreditor to be imported into Datev.
 * Container class with getters/setters, no logic.
 * @author nils
 *
 */
public class ExportAddress {
	/** from jt_firmeninfo */
	private static final String F_ID_ADRESSE = "ID_ADRESSE";
	private static final String F_DEBITOR_NUMMER = "DEBITOR_NUMMER";
	private static final String F_KREDITOR_NUMMER = "KREDITOR_NUMMER";
	private static final String F_UMSATZSTEUERLKZ = "UMSATZSTEUERLKZ";
	private static final String F_UMSATZSTEUERID = "UMSATZSTEUERID";

	private BigDecimal addressId;
	private String debitor_nummer;
	private String kreditor_nummer;
	private String umsatzsteuerLKZ;
	private String umsatzsteuerID;

	/** from jt_adresse */
	private static final String F_KDNR = "KDNR";
	private static final String F_VORNAME = "VORNAME";
	private static final String F_NAME1 = "NAME1";
	private static final String F_NAME2 = "NAME2";
	private static final String F_NAME3 = "NAME3";
	private static final String F_STRASSE = "STRASSE";
	private static final String F_HAUSNUMMER = "HAUSNUMMER";
	private static final String F_PLZ = "PLZ";
	private static final String F_ORT = "ORT";
	private static final String F_ORTZUSATZ = "ORTZUSATZ";


	private String kdnr;
	private String vorname;
	private String name1;
	private String name2;
	private String name3;
	private String strasse;
	private String hausnummer;
	private String plz;
	private String ort;
	private String ortzusatz;
	
	private String tel;
	private String fax;
	
	

	/** from jd_land */
	private static final String F_LAENDERCODE = "UST_PRAEFIX";
	private String laendercode;
	

	/**
	 * Populates the fields from an export run on the original tables
	 * (jt_vpos_rg x jt_vkopf_rg x 
	 * @param entry
	 * @return
	 */
	public ExportAddress fromDBEntry(HashMap<String, Object> entry) {
		this.setAddressId((BigDecimal) entry.get(F_ID_ADRESSE));
		this.setDebitor_nummer((String)entry.get(F_DEBITOR_NUMMER));
		this.setKreditor_nummer((String)entry.get(F_KREDITOR_NUMMER));
		this.setUmsatzsteuerLKZ((String)entry.get(F_UMSATZSTEUERLKZ));
		this.setUmsatzsteuerID((String)entry.get(F_UMSATZSTEUERID));

		this.setKdnr((String)entry.get(F_KDNR));
		this.setVorname((String)entry.get(F_VORNAME));
		this.setName1((String)entry.get(F_NAME1));
		this.setName2((String)entry.get(F_NAME2));
		this.setName3((String)entry.get(F_NAME3));
		this.setStrasse((String)entry.get(F_STRASSE));
		this.setHausnummer((String)entry.get(F_HAUSNUMMER));
		this.setPlz((String)entry.get(F_PLZ));
		
		this.setOrt((String)entry.get(F_ORT));
		this.setOrtzusatz((String)entry.get(F_ORTZUSATZ));
		
		String tel = (String)entry.get("STD_TEL");
		if (tel == null) tel = (String)entry.get("TEL");
		this.setTel(tel);

		String fax = (String)entry.get("STD_FAX");
		if (fax == null) fax = (String)entry.get("FAX");
		this.setFax(fax);
		
		String lc = (String)entry.get(F_LAENDERCODE); 
		if (lc != null && lc.length() > 2) {
			lc = lc.substring(0, 2);
		}
		this.setLaendercode(lc);
		return this;
	}

	public String getDebitor_nummer() {
		return debitor_nummer;
	}

	public void setDebitor_nummer(String debitor_nummer) {
		this.debitor_nummer = debitor_nummer;
	}

	public String getKreditor_nummer() {
		return kreditor_nummer;
	}

	public void setKreditor_nummer(String kreditor_nummer) {
		this.kreditor_nummer = kreditor_nummer;
	}

	public BigDecimal getAddressId() {
		return addressId;
	}

	public void setAddressId(BigDecimal addressId) {
		this.addressId = addressId;
	}
	
	public String getUmsatzsteuerLKZ() {
		return umsatzsteuerLKZ;
	}

	public void setUmsatzsteuerLKZ(String umsatzsteuerLKZ) {
		this.umsatzsteuerLKZ = umsatzsteuerLKZ;
	}

	public String getUmsatzsteuerID() {
		return umsatzsteuerID;
	}

	public void setUmsatzsteuerID(String umsatzsteuerID) {
		this.umsatzsteuerID = umsatzsteuerID;
	}

	public String getKdnr() {
		return kdnr;
	}

	public void setKdnr(String kdnr) {
		this.kdnr = kdnr;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getOrtzusatz() {
		return ortzusatz;
	}

	public void setOrtzusatz(String ortzusatz) {
		this.ortzusatz = ortzusatz;
	}

	public String getLaendercode() {
		return laendercode;
	}

	public void setLaendercode(String laendercode) {
		this.laendercode = laendercode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
