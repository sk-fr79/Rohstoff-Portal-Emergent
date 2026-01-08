package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.indep.exceptions.myException;

/**
 * Die Basisklasse für einen neu zu erzeugenden Intrastat-Record
 * @author manfred
 *
 */
public abstract class INSTAT_Record implements Comparable<INSTAT_Record>{
	
	protected String m_IdIntrastatMeldung;
	protected String m_IdVposTpaFuhre;
	protected String m_IdVposTpaFuhreOrt;
	protected String m_Meldeart;
	protected String m_Anmeldeform;
	protected String m_Jahr;
	protected String m_Monat;
	protected String m_Paginiernummer;
	protected String m_Bundesland_FA;
	protected String m_Steuernr;
	protected String m_Unterscheidungsnr;
	protected String m_Bestimmungsland;
	protected String m_Bestimmungsregion;
	protected String m_Geschaeftsart;
	protected String m_Verkehrszweig;
	protected String m_Warennr;
	protected String m_Land_Ursprung;
	protected String m_Eigenmasse;
	protected String m_Masseinheit;
	protected String m_Rechbetrag;
	protected String m_Preistyp;
	protected String m_StatistischerBetrag;
	protected String m_Bezugsmonat;
	protected String m_Bezugsjahr;
	protected String m_Waehrungskennziffer;
	protected String m_IdArtikel;
	protected String m_Kostenpauschale;
	protected String m_NichtMelden;
	protected String m_IdAbrechnungseinheit;
	protected String m_UmsatzsteuerID; 
	protected String m_UmsatzsteuerLKZ;
	
	/**
	 * Standard Konstruktor
	 */
	public INSTAT_Record(){
		super();
		m_IdIntrastatMeldung = null;
		m_IdVposTpaFuhre = null;
		m_IdVposTpaFuhreOrt = null;
		m_Meldeart = null;
		m_Anmeldeform = null;
		m_Jahr = null;
		m_Monat = null;
		m_Paginiernummer = null;
		m_Bundesland_FA = null;
		m_Steuernr = null;
		m_Unterscheidungsnr = null;
		m_Bestimmungsland = null;
		m_Bestimmungsregion = null;
		m_Geschaeftsart = null;
		m_Verkehrszweig = null;
		m_Warennr = null;
		m_Land_Ursprung = null;
		m_Eigenmasse = null;
		m_Masseinheit = null;
		m_Rechbetrag = null;
		m_Preistyp = null;
		m_StatistischerBetrag = null;
		m_Bezugsmonat = null;
		m_Bezugsjahr = null;
		m_Waehrungskennziffer = null;
		m_IdArtikel = null;
		m_Kostenpauschale = null;
		m_NichtMelden = null;
		m_IdAbrechnungseinheit = null;
		m_UmsatzsteuerID= null;
		m_UmsatzsteuerLKZ = null;
	}
	
	
	
	
	/**
	 * Konstruktor mit allen Feldern
	 * @param mIdIntrastatMeldung
	 * @param mIdVposTpaFuhre
	 * @param mIdVposTpaFuhreOrt
	 * @param mMeldeart
	 * @param mAnmeldeform
	 * @param mJahr
	 * @param mMonat
	 * @param mPaginiernummer
	 * @param mBundeslandFA
	 * @param mSteuernr
	 * @param mUnterscheidungsnr
	 * @param mBestimmungsland
	 * @param mBestimmungsregion
	 * @param mGeschaeftsart
	 * @param mVerkehrszweig
	 * @param mWarennr
	 * @param mLandUrsprung
	 * @param mEigenmasse
	 * @param mMasseinheit
	 * @param mRechbetrag
	 * @param mStatistischerBetrag
	 * @param mBezugsmonat
	 * @param mBezugsjahr
	 * @param mWaehrungskennziffer
	 */
	public INSTAT_Record(String mIdIntrastatMeldung, String mIdVposTpaFuhre,
			String mIdVposTpaFuhreOrt, String mMeldeart, String mAnmeldeform,
			String mJahr, String mMonat, String mPaginiernummer,
			String mBundeslandFA, String mSteuernr, String mUnterscheidungsnr,
			String mBestimmungsland, String mBestimmungsregion,
			String mGeschaeftsart, String mVerkehrszweig, String mWarennr,
			String mLandUrsprung, String mEigenmasse, String mMasseinheit,
			String mRechbetrag, String mStatistischerBetrag,
			String mBezugsmonat, String mBezugsjahr, 
			String mWaehrungskennziffer, String mPreistyp,
			String mIdArtikel,String mKostenpauschale, 
			String mNichtMelden, String mIdAbrechnungseinheit, 
			String mUmsatzsteuerID,String mUmsatzsteuerLKZ ) {
		this();
		m_IdIntrastatMeldung = mIdIntrastatMeldung;
		m_IdVposTpaFuhre = mIdVposTpaFuhre;
		m_IdVposTpaFuhreOrt = mIdVposTpaFuhreOrt;
		m_Meldeart = mMeldeart;
		m_Anmeldeform = mAnmeldeform;
		m_Jahr = mJahr;
		m_Monat = mMonat;
		m_Paginiernummer = mPaginiernummer;
		m_Bundesland_FA = mBundeslandFA;
		m_Steuernr = mSteuernr;
		m_Unterscheidungsnr = mUnterscheidungsnr;
		m_Bestimmungsland = mBestimmungsland;
		m_Bestimmungsregion = mBestimmungsregion;
		m_Geschaeftsart = mGeschaeftsart;
		m_Verkehrszweig = mVerkehrszweig;
		m_Warennr = mWarennr;
		m_Land_Ursprung = mLandUrsprung;
		m_Eigenmasse = mEigenmasse;
		m_Masseinheit = mMasseinheit;
		m_Rechbetrag = mRechbetrag;
		m_StatistischerBetrag = mStatistischerBetrag;
		m_Bezugsmonat = mBezugsmonat;
		m_Bezugsjahr = mBezugsjahr;
		m_Waehrungskennziffer = mWaehrungskennziffer;
		m_Preistyp = mPreistyp;
		m_IdArtikel = mIdArtikel;
		m_Kostenpauschale = mKostenpauschale;
		m_NichtMelden = mNichtMelden;
		m_IdAbrechnungseinheit = mIdAbrechnungseinheit;
		m_UmsatzsteuerID = mUmsatzsteuerID;
		m_UmsatzsteuerLKZ = mUmsatzsteuerLKZ;
	}
	
	
	protected String get_IdIntrastatMeldung() {
		return m_IdIntrastatMeldung;
	}
	protected void set_IdIntrastatMeldung(String mIdIntrastatMeldung) {
		m_IdIntrastatMeldung = mIdIntrastatMeldung;
	}
	
	protected String get_IdVposTpaFuhre() {
		return m_IdVposTpaFuhre;
	}
	protected void set_IdVposTpaFuhre(String mIdVposTpaFuhre) {
		m_IdVposTpaFuhre = mIdVposTpaFuhre;
	}
	protected String get_IdVposTpaFuhreOrt() {
		return m_IdVposTpaFuhreOrt;
	}
	protected void set_IdVposTpaFuhreOrt(String mIdVposTpaFuhreOrt) {
		m_IdVposTpaFuhreOrt = mIdVposTpaFuhreOrt;
	}
	protected String get_Meldeart() {
		return m_Meldeart;
	}
	protected void set_Meldeart(String mMeldeart) {
		m_Meldeart = mMeldeart;
	}
	protected String get_Anmeldeform() {
		return m_Anmeldeform;
	}
	protected void set_Anmeldeform(String mAnmeldeform) {
		m_Anmeldeform = mAnmeldeform;
	}
	protected String get_Jahr() {
		return m_Jahr;
	}
	protected void set_Jahr(String mJahr) {
		m_Jahr = mJahr;
	}
	protected String get_Monat() {
		return m_Monat;
	}
	protected void set_Monat(String mMonat) {
		m_Monat = mMonat;
	}
	protected String get_Paginiernummer() {
		return m_Paginiernummer;
	}
	protected void set_Paginiernummer(String mPaginiernummer) {
		m_Paginiernummer = mPaginiernummer;
	}
	protected String get_Bundesland_FA() {
		return m_Bundesland_FA;
	}
	protected void set_Bundesland_FA(String mBundeslandFA) {
		m_Bundesland_FA = mBundeslandFA;
	}
	protected String get_Steuernr() {
		return m_Steuernr;
	}
	protected void set_Steuernr(String mSteuernr) {
		m_Steuernr = mSteuernr;
	}
	protected String get_Unterscheidungsnr() {
		return m_Unterscheidungsnr;
	}
	protected void set_Unterscheidungsnr(String mUnterscheidungsnr) {
		m_Unterscheidungsnr = mUnterscheidungsnr;
	}
	protected String get_Bestimmungsland() {
		return m_Bestimmungsland;
	}
	protected void set_Bestimmungsland(String mBestimmungsland) {
		m_Bestimmungsland = mBestimmungsland;
	}
	protected String get_Bestimmungsregion() {
		return m_Bestimmungsregion;
	}
	protected void set_Bestimmungsregion(String mBestimmungsregion) {
		m_Bestimmungsregion = mBestimmungsregion;
	}
	protected String get_Geschaeftsart() {
		return m_Geschaeftsart;
	}
	protected void set_Geschaeftsart(String mGeschaeftsart) {
		m_Geschaeftsart = mGeschaeftsart;
	}
	protected String get_Verkehrszweig() {
		return m_Verkehrszweig;
	}
	protected void set_Verkehrszweig(String mVerkehrszweig) {
		m_Verkehrszweig = mVerkehrszweig;
	}
	protected String get_Warennr() {
		return m_Warennr;
	}
	protected void set_Warennr(String mWarennr) {
		m_Warennr = mWarennr;
	}
	protected String get_Land_Ursprung() {
		return m_Land_Ursprung;
	}
	protected void set_Land_Ursprung(String mLandUrsprung) {
		m_Land_Ursprung = mLandUrsprung;
	}
	protected String get_Eigenmasse() {
		return m_Eigenmasse;
	}
	protected void set_Eigenmasse(String mEigenmasse) {
		m_Eigenmasse = mEigenmasse;
	}
	protected String get_Masseinheit() {
		return m_Masseinheit;
	}
	protected void set_Masseinheit(String mMasseinheit) {
		m_Masseinheit = mMasseinheit;
	}
	protected String get_Rechbetrag() {
		return m_Rechbetrag;
	}
	protected void set_Rechbetrag(String mRechbetrag) {
		m_Rechbetrag = mRechbetrag;
	}
	protected String get_StatistischerBetrag() {
		return m_StatistischerBetrag;
	}
	protected void set_StatistischerBetrag(String mStatistischerBetrag) {
		m_StatistischerBetrag = mStatistischerBetrag;
	}
	protected String get_Bezugsmonat() {
		return m_Bezugsmonat;
	}
	protected void set_Bezugsmonat(String mBezugsmonat) {
		m_Bezugsmonat = mBezugsmonat;
	}
	protected String get_Bezugsjahr() {
		return m_Bezugsjahr;
	}
	protected void set_Bezugsjahr(String mBezugsjahr) {
		m_Bezugsjahr = mBezugsjahr;
	}
	protected String get_Waehrungskennziffer() {
		return m_Waehrungskennziffer;
	}
	protected void set_Waehrungskennziffer(String mWaehrungskennziffer) {
		m_Waehrungskennziffer = mWaehrungskennziffer;
	}
	protected void set_Preistyp(String m_Preistyp) {
		this.m_Preistyp = m_Preistyp;
	}
	protected String get_Preistyp() {
		return m_Preistyp;
	}

	public void set_IdArtikel(String m_IdArtikel) {
		this.m_IdArtikel = m_IdArtikel;
	}
	public String get_IdArtikel() {
		return m_IdArtikel;
	}
	public void set_Kostenpauschale(String m_Kostenpauschale) {
		this.m_Kostenpauschale = m_Kostenpauschale;
	}
	public String get_Kostenpauschale() {
		return m_Kostenpauschale;
	}
	public void set_NichtMelden(String m_NichtMelden) {
		this.m_NichtMelden = m_NichtMelden;
	}
	public String get_NichtMelden() {
		return m_NichtMelden;
	}
	public void set_IdAbrechnungseinheit(String m_IdAbrechnungseinheit) {
		this.m_IdAbrechnungseinheit = m_IdAbrechnungseinheit;
	}
	public String get_IdAbrechnungseinheit() {
		return m_IdAbrechnungseinheit;
	}
	public String get_IdUmstatzsteuer() {
		return m_UmsatzsteuerID;
	}
	public void set_IdUmstatzsteuer(String m_UmstatzsteuerID) {
		this.m_UmsatzsteuerID = m_UmstatzsteuerID;
	}
	public String get_UmsatzsteuerLKZ() {
		return m_UmsatzsteuerLKZ;
	}
	public void set_UmsatzsteuerLKZ(String m_UmsatzsteuerLKZ) {
		this.m_UmsatzsteuerLKZ = m_UmsatzsteuerLKZ;
	}

	


	/**
	 * gibt einen String mit dem Wert und der geforderten Länge zurück. Wenn der Wert nicht
	 * lang genug ist, wird der Rest mit dem Füll-Zeichen gefüllt.
	 * Rechtsbündig
	 * @param value
	 * @param length
	 * @return
	 */
	public static String getStringForColumnRight(String value, int length, String cFill){
		String sRet = "                                                          ";
		sRet = sRet.replace(" ", cFill);
		sRet = sRet + value;
		sRet = sRet.substring(sRet.length() - length );
		return sRet;
	}
	
	/**
	 * gibt einen String mit dem Wert und der geforderten Länge zurück. Wenn der Wert nicht
	 * lang genug ist, wird der Rest mit dem Füll-Zeichen gefüllt.
	 * Linksbündig
	 * @param value
	 * @param length
	 * @return
	 */
	public static String getStringForColumnLeft(String value, int length, String cFill){
		String sRet = "                                                          ";
		sRet = sRet.replace(" ", cFill);
		sRet = value + sRet;
		sRet = sRet.substring(0,length);
		return sRet;
	}
	
	
	
	 @Override
	 /**
	  * 0, wenn idFuhre und idFuhrenOrt übereinstimmen
	  * -1 sonst
	  */
	public int compareTo(INSTAT_Record o) {
		 String idFuhre = (m_IdVposTpaFuhre != null ? m_IdVposTpaFuhre : "");
		 String idFuhrenOrt = (m_IdVposTpaFuhreOrt != null ? m_IdVposTpaFuhreOrt : "");
		 
		 if (idFuhre.equals(o.m_IdVposTpaFuhre) && idFuhrenOrt.equals(o.m_IdVposTpaFuhreOrt)){
			 return 0;
		 } else {
			 return -1;
		 }
	}
	 
	 
	 @Override
	public boolean equals(Object o) {
		 String idFuhre = (m_IdVposTpaFuhre != null ? m_IdVposTpaFuhre : "");
		 String idFuhrenOrt = (m_IdVposTpaFuhreOrt != null ? m_IdVposTpaFuhreOrt : "");
		 
		 if ( o instanceof INSTAT_Record){
			 if (idFuhre.equals(((INSTAT_Record)o).m_IdVposTpaFuhre) && idFuhrenOrt.equals(((INSTAT_Record)o).m_IdVposTpaFuhreOrt)){
				 return true;
			 }
		 }
			 
		 return false;
	}
	 
	/**
	 * Erzeugung des Sql-Statements für die Generierung des Satzes in die Tabelle
	 * @return
	 */
	public abstract String GetSqlForInsert() throws myException ;



	/**
	 * Erzeugung einer Zeile für das ASCII-Exportdokument.
	 * @return
	 */
	public abstract String GetRowForExportfile() throws myException ;






















	
}
